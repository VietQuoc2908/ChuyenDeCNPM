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
	public String Index(@RequestParam("txtSearch") String txtSearch, @RequestParam(value="pageid") int pageid, ModelMap model, HttpSession session) throws Exception {
		int total=10;    
        if(pageid==1){}    
        else{    
            pageid=(pageid-1)*total+1;    
        }
		if (session.getAttribute("taikhoanAdmin") != null) {
			model.addAttribute("list", KhachHangDAO.getInstance().getKhachHangByPage(pageid,total,txtSearch));
			return "admin/customer/customers";
		}
		return "redirect:../login";
	}
}
