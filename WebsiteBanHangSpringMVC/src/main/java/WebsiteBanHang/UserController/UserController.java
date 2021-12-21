package WebsiteBanHang.UserController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.DienThoaiDAO;
import dao.NhaSanXuatDAO;
import pojo.DienThoai;

@Controller
@RequestMapping(value="/")
public class UserController {

	@RequestMapping(value={"","/index"})
	public String Index(ModelMap model) {
		model.addAttribute("list",DienThoaiDAO.getInstance().getList());
		model.addAttribute("listNSX",NhaSanXuatDAO.getInstance().getList());
		return "user/index";
	}
	
	@RequestMapping(value="/shop", method = RequestMethod.GET)
	public String Shop(@RequestParam("maNsx") int maNsx,ModelMap model) {
		model.addAttribute("listByNsx", DienThoaiDAO.getInstance().getListByNsx(maNsx));
		return "user/shop";
	}
	
	@RequestMapping(value="/cart")
	public String Cart() {
		return "user/cart";
	}
	
	@RequestMapping(value="/checkout")
	public String Checkout() {
		return "user/checkout";
	}
	
	@RequestMapping(value="/single-product", method = RequestMethod.GET)
	public String SingleProduct(@RequestParam("maDt") int maDt,ModelMap model) {
		DienThoai dt = DienThoaiDAO.getInstance().getById(maDt);
		model.addAttribute("dienthoai",dt);
		return "user/single-product";
	}
}
