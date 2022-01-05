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

@Controller
@RequestMapping(value = "/admin")
public class AdminLoginController {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new TaiKhoanValidator());
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String Login(ModelMap model) {
		model.addAttribute("admin", new TaiKhoan());
		return "admin/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView Login(@Valid @ModelAttribute("admin") TaiKhoan model, Errors err, HttpSession session) {
		ModelAndView mv = new ModelAndView("/admin/login");
		mv.addObject("admin", model);
		if (err.hasErrors()) {
			System.out.println("Lỗi thông tin");
		} else {
			if (TaiKhoanDAO.getInstance().checkLogin(model.getTaikhoan(), model.getMatkhau())) {
				session.setAttribute("taikhoanAdmin", model.getTaikhoan());
				return new ModelAndView("redirect:./products");
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
