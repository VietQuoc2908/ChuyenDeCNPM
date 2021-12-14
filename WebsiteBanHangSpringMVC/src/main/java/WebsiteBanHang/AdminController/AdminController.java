package WebsiteBanHang.AdminController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/admin")
public class AdminController {

	@RequestMapping(value="")
	public String Index() {
		return "admin/index";
	}
}
