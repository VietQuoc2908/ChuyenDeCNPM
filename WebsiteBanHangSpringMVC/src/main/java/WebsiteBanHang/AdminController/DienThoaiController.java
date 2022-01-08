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
		// dùng để lưu hình ảnh dạng byte
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}

	// get danh sách điện thoại
	@RequestMapping(value = "")
	public String Index(ModelMap model, @RequestParam(value="pageid") int pageid, @RequestParam(value="txtSearch", required = false) String txtSearch, HttpSession session) throws Exception {
		// pageid là số thứ tự trang, total là tổng số dòng trong trang
		int total=10;
        if(pageid==1){}    
        else{    
            pageid=(pageid-1)*total+1;    
        }
		if (session.getAttribute("taikhoanAdmin") != null) {
			model.addAttribute("list", DienThoaiDAO.getInstance().getDienThoaiByPage(pageid,total,txtSearch));
			model.addAttribute("name",txtSearch);
			model.addAttribute("tongsotrang", DienThoaiDAO.getInstance().getTotalPage(total, txtSearch));
			return "admin/product/products";
		}else {
			return "redirect:../login";
		}
		
	}

	// get trang thêm sản phẩm
	@RequestMapping(value = "/add-product", method = RequestMethod.GET)
	public String AddProduct(ModelMap model, HttpSession session) {
		if (session.getAttribute("taikhoanAdmin") != null) {
			model.addAttribute("dienthoai", new DienThoai());
			model.addAttribute("listNSX", NhaSanXuatDAO.getInstance().getList());
			model.addAttribute("listDM", DanhMucDAO.getInstance().getList());
			return "admin/product/add-product";
		}
		return "redirect:../../login";
	}

	// post thêm sản phẩm
	@RequestMapping(value = "/add-product", method = RequestMethod.POST)
	public ModelAndView AddProduct(@Valid @ModelAttribute("dienthoai") DienThoai model, Errors err,
			@RequestParam("hinhAnh") MultipartFile hinhAnh) throws IOException {
		// Luu biến mv để trả về trang thêm sản phẩm các nhà sản xuất, danh mục
		ModelAndView mv = new ModelAndView("/admin/product/add-product");
		System.out.println(model);
		mv.addObject("dienthoai", model);
		mv.addObject("listNSX", NhaSanXuatDAO.getInstance().getList());
		mv.addObject("listDM", DanhMucDAO.getInstance().getList());

		if (hinhAnh == null || hinhAnh.isEmpty() || hinhAnh.toString().isEmpty()) {
			mv.addObject("message", "Vui lòng chọn ảnh");
		} else {
			try {
				// lưu Base64image
				byte[] encode = java.util.Base64.getEncoder().encode(hinhAnh.getBytes());
				model.setBase64image(new String(encode, "UTF-8"));
			} catch (Exception e) {
				mv.addObject("message", "Lỗi lưu file");
			}
		}

		if (err.hasErrors()) {
			System.out.println("Lỗi thông tin");
		} else
			try {
				{
					DienThoaiDAO.getInstance().add(model);
					System.out.println(model);
					return new ModelAndView("redirect:../products?txtSearch=&pageid=1");

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return mv;
	}

	@RequestMapping(value = "/edit-product", method = RequestMethod.GET)
	public String EditProduct(@RequestParam("maDt") int maDt, ModelMap model, HttpSession session) {
		if (session.getAttribute("taikhoanAdmin") != null) {
			DienThoai dt = DienThoaiDAO.getInstance().getById(maDt);
			model.addAttribute("dienthoai", dt);
			model.addAttribute("listNSX", NhaSanXuatDAO.getInstance().getList());
			model.addAttribute("listDM", DanhMucDAO.getInstance().getList());
			return "admin/product/edit-product";
		}
		return "redirect:../../login";
	}

	@RequestMapping(value = "/edit-product", method = RequestMethod.POST)
	public ModelAndView EditProduct(@Valid @ModelAttribute("dienthoai") DienThoai model, Errors err,
			@RequestParam("hinhAnh") MultipartFile hinhAnh) throws IOException {
		ModelAndView mv = new ModelAndView("/admin/product/edit-product");
		mv.addObject("dienthoai", model);
		mv.addObject("listNSX", NhaSanXuatDAO.getInstance().getList());
		mv.addObject("listDM", DanhMucDAO.getInstance().getList());

		// Nếu không có hình ảnh thì lưu lại hình ảnh trong database
		if (hinhAnh == null || hinhAnh.isEmpty()) {
			DienThoai dt = DienThoaiDAO.getInstance().getById(model.getMaDt());
			model.setHinhAnh(dt.getHinhAnh());
			byte[] encode = java.util.Base64.getEncoder().encode(dt.getHinhAnh());
			model.setBase64image(new String(encode, "UTF-8"));
		} else {
			try {
				// Lưu Base64image
				model.setHinhAnh(hinhAnh.getBytes());
				byte[] encode = java.util.Base64.getEncoder().encode(hinhAnh.getBytes());
				model.setBase64image(new String(encode, "UTF-8"));
			} catch (Exception e) {
				mv.addObject("message", "Lỗi lưu file");
			}
		}

		if (err.hasErrors()) {
			System.out.println("Lỗi thông tin");
		} else
			try {

				System.out.println(model);

				DienThoaiDAO.getInstance().edit(model);
				return new ModelAndView("redirect:../products?txtSearch=&pageid=1");

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
			if(DienThoaiDAO.getInstance().tontaima(maDt)) {
				
				map.put("tontaima", "true");
				return map;
			}else {
				DienThoaiDAO.getInstance().delete(maDt);
				map.put("tontaima", "false");
				map.put("isvalid", "true");
				System.out.println(map);
				return map;
			}
		}
		map.put("isvalid", "false");
		return map;
	}

	@RequestMapping(value = "/detail-product", method = RequestMethod.GET)
	public String DetailProduct(@RequestParam("maDt") int maDt, ModelMap model, HttpSession session) {
		if (session.getAttribute("taikhoanAdmin") != null) {
			DienThoai dt = DienThoaiDAO.getInstance().getById(maDt);
			model.addAttribute("dienthoai", dt);
			return "admin/product/detail-product";
		}
		return "redirect:../../login";
	}
}
