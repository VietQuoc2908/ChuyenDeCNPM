package WebsiteBanHang.AdminController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

import dao.DanhMucDAO;
import dao.DienThoaiDAO;
import dao.NhaSanXuatDAO;
import pojo.NhaSanXuat;
import validator.NhaSanXuatValidator;

@Controller
@RequestMapping("/admin/manufactores")
public class NhaSanXuatController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new NhaSanXuatValidator());
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}

	// get trang nhà sản xuất
	@RequestMapping(value = "")
	public String Index(ModelMap model, HttpSession session) throws Exception {
		if (session.getAttribute("taikhoanAdmin") != null) {
			model.addAttribute("list", NhaSanXuatDAO.getInstance().getList());
			return "admin/manufactor/manufactores";
		}
		return "redirect:../login";
	}

	// get trang thêm nhà sản xuất
	@RequestMapping(value = "/add-manufactor", method = RequestMethod.GET)
	public String AddProduct(ModelMap model, HttpSession session) {
		if (session.getAttribute("taikhoanAdmin") != null) {
			model.addAttribute("nhasanxuat", new NhaSanXuat());
			return "admin/manufactor/add-manufactor";
		}
		return "redirect:../../login";
	}

	// post trang thêm nhà sản xuất
	@RequestMapping(value = "/add-manufactor", method = RequestMethod.POST)
	public ModelAndView AddProduct(@Valid @ModelAttribute("nhasanxuat") NhaSanXuat model, Errors err,
			@RequestParam("hinhAnh") MultipartFile hinhAnh) throws IOException {
		ModelAndView mv = new ModelAndView("/admin/manufactor/add-manufactor");
		System.out.println(model);
		mv.addObject("nhasanxuat", model);

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
					NhaSanXuatDAO.getInstance().add(model, hinhAnh);
					System.out.println(model);
					return new ModelAndView("redirect:../manufactores");

				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return mv;
	}

	// get trang sửa nhà sản xuất
	@RequestMapping(value = "/edit-manufactor", method = RequestMethod.GET)
	public String EditProduct(@RequestParam("maNsx") int maNsx, ModelMap model, HttpSession session) {
		if (session.getAttribute("taikhoanAdmin") != null) {
			NhaSanXuat nsx = NhaSanXuatDAO.getInstance().getById(maNsx);
			model.addAttribute("nhasanxuat", nsx);
			return "admin/manufactor/edit-manufactor";
		}
		return "redirect:../../login";
	}

	// post trang sửa nhà sản xuất
	@RequestMapping(value = "/edit-manufactor", method = RequestMethod.POST)
	public ModelAndView EditProduct(@Valid @ModelAttribute("nhasanxuat") NhaSanXuat model, Errors err,
			@RequestParam("hinhAnh") MultipartFile hinhAnh) throws IOException {
		ModelAndView mv = new ModelAndView("/admin/manufactor/edit-manufactor");
		mv.addObject("nhasanxuat", model);

		// nếu không có ảnh thì lưu lại ảnh cũ
		if (hinhAnh == null || hinhAnh.isEmpty()) {
			NhaSanXuat nsx = NhaSanXuatDAO.getInstance().getById(model.getMaNsx());
			model.setHinhAnh(nsx.getHinhAnh());
			byte[] encode = java.util.Base64.getEncoder().encode(nsx.getHinhAnh());
			model.setBase64image(new String(encode, "UTF-8"));
		} else {
			try {
				// lưu Base64image
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

				NhaSanXuatDAO.getInstance().edit(model);
				return new ModelAndView("redirect:../manufactores");

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return mv;
	}

	// ajax gửi lên xoá nhà sản xuất
	@RequestMapping(value = "/delete-manufactor/{maNsx}", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, String> DeleteProduct(@PathVariable("maNsx") int maNsx, ModelMap model) {
		HashMap<String, String> map = new HashMap<String, String>();
		NhaSanXuat nsx = NhaSanXuatDAO.getInstance().getById(maNsx);

		if (nsx != null) {
			if(NhaSanXuatDAO.getInstance().tontaima(maNsx)) {
				map.put("tontaima", "true");
				return map;
			}else {
				NhaSanXuatDAO.getInstance().delete(maNsx);
				map.put("tontaima", "false");
				map.put("isvalid", "true");
				return map;
			}
			
		}
		map.put("isvalid", "false");
		return map;
	}

}
