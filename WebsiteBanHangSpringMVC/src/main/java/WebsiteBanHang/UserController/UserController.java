package WebsiteBanHang.UserController;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import dao.ChiTietGioHangDAO;
import dao.DienThoaiDAO;
import dao.GioHangDAO;
import dao.KhachHangDAO;
import dao.NhaSanXuatDAO;
import dao.TaiKhoanDAO;
import pojo.ChiTietGioHang;
import pojo.DienThoai;
import pojo.KhachHang;

@Controller
@RequestMapping(value="/")
public class UserController {

	@RequestMapping(value={"","/index"})
	public String Index(ModelMap model, HttpSession session) {
		model.addAttribute("list",DienThoaiDAO.getInstance().getList());
		model.addAttribute("listNSX",NhaSanXuatDAO.getInstance().getList());
		if(session.getAttribute("taikhoan")!=null) {
			String taikhoan = (String) session.getAttribute("taikhoan");
			KhachHang kh = KhachHangDAO.getInstance().getByTaiKhoan(taikhoan);
			model.addAttribute("gioHang",GioHangDAO.getInstance().getGioHangByKH(kh.getMaKh()));
		}
		return "user/index";
	}
	
	@RequestMapping(value="/shop", method = RequestMethod.GET)
	public String Shop(@RequestParam("maNsx") int maNsx,ModelMap model, HttpSession session) {
		model.addAttribute("listByNsx", DienThoaiDAO.getInstance().getListByNsx(maNsx));
		if(session.getAttribute("taikhoan")!=null) {
			String taikhoan = (String) session.getAttribute("taikhoan");
			KhachHang kh = KhachHangDAO.getInstance().getByTaiKhoan(taikhoan);
			model.addAttribute("gioHang",GioHangDAO.getInstance().getGioHangByKH(kh.getMaKh()));
		}
		return "user/shop";
	}
	
	@RequestMapping(value="/cart")
	public String Cart(ModelMap model,HttpSession session) {
		if(session.getAttribute("taikhoan")!=null) {
			String taikhoan = (String) session.getAttribute("taikhoan");
			KhachHang kh = KhachHangDAO.getInstance().getByTaiKhoan(taikhoan);
			model.addAttribute("gioHang",GioHangDAO.getInstance().getGioHangByKH(kh.getMaKh()));
			
			int maGH = GioHangDAO.getInstance().getGioHangByKH(kh.getMaKh()).getMaGh();
			model.addAttribute("chiTietGH",ChiTietGioHangDAO.getInstance().getList(maGH));
			
		}
		
		return "user/cart";
	}
	
	@RequestMapping(value="/checkout")
	public String Checkout() {
		return "user/checkout";
	}
	
	@RequestMapping(value="/single-product", method = RequestMethod.GET)
	public String SingleProduct(@RequestParam("maDt") int maDt,ModelMap model, HttpSession session) {
		DienThoai dt = DienThoaiDAO.getInstance().getById(maDt);
		model.addAttribute("dienthoai",dt);
		if(session.getAttribute("taikhoan")!=null) {
			String taikhoan = (String) session.getAttribute("taikhoan");
			KhachHang kh = KhachHangDAO.getInstance().getByTaiKhoan(taikhoan);
			model.addAttribute("gioHang",GioHangDAO.getInstance().getGioHangByKH(kh.getMaKh()));
		}
		return "user/single-product";
	}
}
