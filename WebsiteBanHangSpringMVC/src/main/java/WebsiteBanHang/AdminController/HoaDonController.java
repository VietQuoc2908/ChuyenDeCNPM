package WebsiteBanHang.AdminController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import pojo.HoaDon;

@Controller
@RequestMapping("/admin/orders")
public class HoaDonController {

	@RequestMapping(value = "")
	public String Index(ModelMap model) throws Exception {
		model.addAttribute("list", HoaDonDAO.getInstance().getList());
		return "admin/order/orders";
	}
	
	@RequestMapping(value = "/detail-order", method = RequestMethod.GET)
	public String DetailOrder(@RequestParam("maHd") int maHd, ModelMap model) {
		model.addAttribute("hoadon", HoaDonDAO.getInstance().getById(maHd));
		model.addAttribute("chiTietHoaDon", ChiTietHoaDonDAO.getInstance().getListByMaHd(maHd));
		return "admin/order/detail-order";
	}
}
