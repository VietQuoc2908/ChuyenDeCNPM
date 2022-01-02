package WebsiteBanHang.AdminController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.HoaDonDAO;

@Controller
@RequestMapping("/admin/orders")
public class HoaDonController {

	@RequestMapping(value = "")
	public String Index(ModelMap model) throws Exception {
		model.addAttribute("list", HoaDonDAO.getInstance().getList());
		return "admin/order/orders";
	}
}
