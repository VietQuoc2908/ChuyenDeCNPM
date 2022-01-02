package WebsiteBanHang.AdminController;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.DienThoaiDAO;
import dao.GioHangDAO;
import dao.KhachHangDAO;
import pojo.DienThoai;
import pojo.KhachHang;

@Controller
@RequestMapping("/admin/customers")
public class KhachHangController {

	@RequestMapping(value = "")
	public String Index(@RequestParam("txtSearch") String txtSearch, ModelMap model, HttpSession session) throws Exception {
		model.addAttribute("list", KhachHangDAO.getInstance().getListByText(txtSearch));
		return "admin/customer/customers";
	}
}
