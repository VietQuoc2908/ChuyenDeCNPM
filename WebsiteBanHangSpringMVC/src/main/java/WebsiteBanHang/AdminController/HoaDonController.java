package WebsiteBanHang.AdminController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@RequestMapping(value = "/confirm-order/{maHd}", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, String> ConfirmOrder(@PathVariable("maHd") int maHd, ModelMap model) {
		HashMap<String, String> map = new HashMap<String, String>();
		HoaDon hd = HoaDonDAO.getInstance().getById(maHd);
		if (hd != null) {
			hd.getMaHd();

			hd.setStatus(1);
			HoaDonDAO.getInstance().updateStatus(hd);
			map.put("isvalid", "true");
			return map;
		}
		map.put("isvalid", "false");
		return map;
	}
	
	@RequestMapping(value = "/received-order/{maHd}", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, String> ReceivedOrder(@PathVariable("maHd") int maHd, ModelMap model) {
		HashMap<String, String> map = new HashMap<String, String>();
		HoaDon hd = HoaDonDAO.getInstance().getById(maHd);
		if (hd != null) {
			hd.getMaHd();

			hd.setStatus(2);
			HoaDonDAO.getInstance().updateStatus(hd);
			map.put("isvalid", "true");
			return map;
		}
		map.put("isvalid", "false");
		return map;
	}
	
	@RequestMapping(value = "/cancel-order/{maHd}", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, String> CancelOrder(@PathVariable("maHd") int maHd, ModelMap model) {
		HashMap<String, String> map = new HashMap<String, String>();
		HoaDon hd = HoaDonDAO.getInstance().getById(maHd);
		if (hd != null) {
			hd.getMaHd();

			hd.setStatus(3);
			HoaDonDAO.getInstance().updateStatus(hd);
			map.put("isvalid", "true");
			return map;
		}
		map.put("isvalid", "false");
		return map;
	}
}
