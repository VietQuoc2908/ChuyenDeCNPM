package WebsiteBanHang.AdminController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import dao.TaiKhoanDAO;
import pojo.TaiKhoan;
import validator.TaiKhoanValidator;

// controller đăng nhập
@Controller
@RequestMapping(value = "/admin")
public class AdminLoginController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new TaiKhoanValidator());
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
	// get trang login
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String Login(ModelMap model) {
		// truyền model qua view
		model.addAttribute("admin", new TaiKhoan());
		return "admin/login";
	}
	//post trang login
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView Login(@Valid @ModelAttribute("nguoidung") TaiKhoan model, Errors err, HttpSession session) {
		ModelAndView mv = new ModelAndView("/admin/login");
		mv.addObject("nguoidung", model);
		if (err.hasErrors()) {
			System.out.println("Lỗi thông tin");
		} else {
			// kiểm tra tài khoản và mật khẩu của admin
			// đúng thì trả về session và chuyển về trang đơn hàng
			if (TaiKhoanDAO.getInstance().checkLoginAdmin(model.getTaikhoan(), model.getMatkhau())) {
				session.setAttribute("taikhoanAdmin", model.getTaikhoan());
				return new ModelAndView("redirect:./orders?pageid=1");
			} else {
				mv.addObject("error", "Sai tên đăng nhập hoặc mật khẩu");
			}
		}

		return mv;
	}
	
	@RequestMapping(value = "/logout")
	public String Logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}
