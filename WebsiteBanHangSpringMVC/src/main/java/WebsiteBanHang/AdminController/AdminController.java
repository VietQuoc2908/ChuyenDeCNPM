package WebsiteBanHang.AdminController;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import dao.DienThoaiDAO;
import dao.GioHangDAO;
import dao.KhachHangDAO;
import dao.NhaSanXuatDAO;
import pojo.KhachHang;
import pojo.TaiKhoan;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	
	@RequestMapping(value = "")
	public ModelAndView Index(ModelMap model, HttpSession session) {
		// Nếu chưa có session thì sẽ về login, ngược lại về trang chủ
		if (session.getAttribute("taikhoanAdmin") != null)
			return new ModelAndView("redirect:./");
		else
			return new ModelAndView("redirect:../login", "admin", new TaiKhoan());
	}
}
