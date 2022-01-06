package WebsiteBanHang.AdminController;

import java.io.*;
import java.util.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import dao.*;
import pojo.*;
import validator.DienThoaiValidator;

@Controller
@RequestMapping("/admin/products")
public class DienThoaiController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new DienThoaiValidator());
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}

	@RequestMapping(value = "")
	public ModelAndView Index(ModelMap model, HttpSession session) throws Exception {
		if (session.getAttribute("taikhoanAdmin") != null) {
			//model.addAttribute("list", DienThoaiDAO.getInstance().getList());
			return new ModelAndView("admin/product/products", "list", DienThoaiDAO.getInstance().getList());
		}
		return new ModelAndView("redirect:../login", "admin", new TaiKhoan());
	}

	@RequestMapping(value = "/add-product", method = RequestMethod.GET)
	public String AddProduct(ModelMap model) {
		model.addAttribute("dienthoai", new DienThoai());
		model.addAttribute("listNSX", NhaSanXuatDAO.getInstance().getList());
		model.addAttribute("listDM", DanhMucDAO.getInstance().getList());
		return "admin/product/add-product";
	}

	@RequestMapping(value = "/add-product", method = RequestMethod.POST)
	public ModelAndView AddProduct(@Valid @ModelAttribute("dienthoai") DienThoai model, Errors err,
			@RequestParam("hinhAnh") MultipartFile hinhAnh) throws IOException {
		ModelAndView mv = new ModelAndView("/admin/product/add-product");
		System.out.println(model);
		mv.addObject("dienthoai", model);
		mv.addObject("listNSX", NhaSanXuatDAO.getInstance().getList());
		mv.addObject("listDM", DanhMucDAO.getInstance().getList());

		if (hinhAnh == null || hinhAnh.isEmpty() || hinhAnh.toString().isEmpty()) {
			mv.addObject("message", "Vui lÃ²ng chá»�n áº£nh");
		} else {
			try {
				// lÆ°u Base64image
				byte[] encode = java.util.Base64.getEncoder().encode(hinhAnh.getBytes());
				model.setBase64image(new String(encode, "UTF-8"));
			} catch (Exception e) {
				mv.addObject("message", "Lá»—i lÆ°u file");
			}
		}

		if (err.hasErrors()) {
			System.out.println("Lá»—i thÃ´ng tin");
		} else
			try {
				{
					DienThoaiDAO.getInstance().add(model);
					System.out.println(model);
					return new ModelAndView("redirect:../products");

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return mv;
	}

	@RequestMapping(value = "/edit-product", method = RequestMethod.GET)
	public String EditProduct(@RequestParam("maDt") int maDt, ModelMap model) {
		DienThoai dt = DienThoaiDAO.getInstance().getById(maDt);
		model.addAttribute("dienthoai", dt);
		model.addAttribute("listNSX", NhaSanXuatDAO.getInstance().getList());
		model.addAttribute("listDM", DanhMucDAO.getInstance().getList());
		return "admin/product/edit-product";
	}

	@RequestMapping(value = "/edit-product", method = RequestMethod.POST)
	public ModelAndView EditProduct(@Valid @ModelAttribute("dienthoai") DienThoai model, Errors err,
			@RequestParam("hinhAnh") MultipartFile hinhAnh) throws IOException {
		ModelAndView mv = new ModelAndView("/admin/product/edit-product");
		mv.addObject("dienthoai", model);
		mv.addObject("listNSX", NhaSanXuatDAO.getInstance().getList());
		mv.addObject("listDM", DanhMucDAO.getInstance().getList());

		// náº¿u khÃ´ng cÃ³ áº£nh thÃ¬ lÆ°u láº¡i áº£nh cÅ©
		if (hinhAnh == null || hinhAnh.isEmpty()) {
			DienThoai dt = DienThoaiDAO.getInstance().getById(model.getMaDt());
			model.setHinhAnh(dt.getHinhAnh());
			byte[] encode = java.util.Base64.getEncoder().encode(dt.getHinhAnh());
			model.setBase64image(new String(encode, "UTF-8"));
		} else {
			try {
				// lÆ°u Base64image
				model.setHinhAnh(hinhAnh.getBytes());
				byte[] encode = java.util.Base64.getEncoder().encode(hinhAnh.getBytes());
				model.setBase64image(new String(encode, "UTF-8"));
			} catch (Exception e) {
				mv.addObject("message", "Lá»—i lÆ°u file");
			}
		}

		if (err.hasErrors()) {
			System.out.println("Lá»—i thÃ´ng tin");
		} else
			try {

				System.out.println(model);

				DienThoaiDAO.getInstance().edit(model);
				return new ModelAndView("redirect:../products");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return mv;
	}

	@RequestMapping(value = "/delete-product/{maDt}", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, String> DeleteProduct(@PathVariable("maDt") int maDt, ModelMap model) {
		HashMap<String, String> map = new HashMap<String, String>();
		DienThoai dt = DienThoaiDAO.getInstance().getById(maDt);

		if (dt != null) {
			DienThoaiDAO.getInstance().delete(maDt);
			map.put("isvalid", "true");
			return map;
		}
		map.put("isvalid", "false");
		return map;
	}

	@RequestMapping(value = "/detail-product", method = RequestMethod.GET)
	public String DetailProduct(@RequestParam("maDt") int maDt, ModelMap model) {
		DienThoai dt = DienThoaiDAO.getInstance().getById(maDt);
		model.addAttribute("dienthoai", dt);
		return "admin/product/detail-product";
	}
}
