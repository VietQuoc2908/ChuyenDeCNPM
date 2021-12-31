package WebsiteBanHang.UserController;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import dao.TaiKhoanDAO;
import pojo.DienThoai;
import pojo.KhachHang;
import pojo.TaiKhoan;
import validator.DienThoaiValidator;
import validator.TaiKhoanValidator;

@Controller
@RequestMapping(value="/")
public class LoginController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder){
        binder.addValidators(new TaiKhoanValidator());
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
    }

	@RequestMapping(value="/register",method = RequestMethod.GET)
	public String Register(ModelMap model) {
		model.addAttribute("nguoidung",new TaiKhoan());
		return "user/register";
	}
	
	@RequestMapping(value="/register",method = RequestMethod.POST)
	public ModelAndView Register(@Valid @ModelAttribute("nguoidung") TaiKhoan model, 
			Errors err, @RequestParam("matkhau1") String matkhau1) 
	{
		ModelAndView mv = new ModelAndView("/user/register");
		mv.addObject("nguoidung",model);
		if(matkhau1.isEmpty() || matkhau1==null || !matkhau1.equalsIgnoreCase(model.getMatkhau())) {
			mv.addObject("error","Xác nhận mật khẩu không chính xác");
			return mv;
		}
		if(err.hasErrors()) {
			System.out.println("Lỗi thông tin");
		}else {
			mv.addObject("success","Đăng ký thành công");
			TaiKhoanDAO.getInstance().createAccount(model);
			return mv;
		}
		return new ModelAndView( "redirect:./register");
	}
	
	@RequestMapping(value="/login",method = RequestMethod.GET)
	public String Login(ModelMap model) {
		model.addAttribute("nguoidung",new TaiKhoan());
		return "user/login";
	}
	
	@RequestMapping(value="/login",method = RequestMethod.POST)
	public ModelAndView Login(@Valid @ModelAttribute("nguoidung") TaiKhoan model, Errors err, HttpSession session) 
	{
		ModelAndView mv = new ModelAndView("/user/login");
		mv.addObject("nguoidung",model);
		if(err.hasErrors()) {
			System.out.println("Lỗi thông tin");
		}else {
			if(TaiKhoanDAO.getInstance().checkLogin(model.getTaikhoan(), model.getMatkhau())) {
				session.setAttribute("taikhoan", model.getTaikhoan());
				return new ModelAndView( "redirect:./");
			}else {
				mv.addObject("error","Sai tên đăng nhập hoặc mật khẩu");
			}
		}
		
		return mv;
	}

	@RequestMapping(value="/logout")
	public String Logout(HttpSession session ) {
	    session.invalidate();
	    return "redirect:/";
	} 
}
