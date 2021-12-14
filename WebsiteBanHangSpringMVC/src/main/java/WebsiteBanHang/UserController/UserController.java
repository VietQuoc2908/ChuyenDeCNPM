package WebsiteBanHang.UserController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/")
public class UserController {

	@RequestMapping(value={"","/index"})
	public String Index() {
		return "user/index";
	}
	
	@RequestMapping(value="/shop")
	public String Shop() {
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
	
	@RequestMapping(value="/single-product")
	public String SingleProduct() {
		return "user/single-product";
	}
}
