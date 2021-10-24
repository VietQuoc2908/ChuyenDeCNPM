package WebsiteBanHangSpringMVC.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	@RequestMapping(value="/")
	public ModelAndView Index() {
		ModelAndView mv = new ModelAndView("user/index");
		return mv;
	}
	@RequestMapping(value="shop")
	public ModelAndView Shop() {
		ModelAndView mv = new ModelAndView("user/shop");
		return mv;
	}
	@RequestMapping(value="single-product")
	public ModelAndView SinggleProduct() {
		ModelAndView mv = new ModelAndView("user/single-product");
		return mv;
	}
	@RequestMapping(value="cart")
	public ModelAndView Cart() {
		ModelAndView mv = new ModelAndView("user/cart");
		return mv;
	}
	@RequestMapping(value="checkout")
	public ModelAndView CheckOut() {
		ModelAndView mv = new ModelAndView("user/checkout");
		return mv;
	}
}
