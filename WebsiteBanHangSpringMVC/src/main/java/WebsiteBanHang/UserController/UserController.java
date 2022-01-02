package WebsiteBanHang.UserController;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import dao.ChiTietGioHangDAO;
import dao.ChiTietHoaDonDAO;
import dao.DienThoaiDAO;
import dao.GioHangDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.NhaSanXuatDAO;
import dao.TaiKhoanDAO;
import pojo.ChiTietGioHang;
import pojo.ChiTietHoaDon;
import pojo.DienThoai;
import pojo.GioHang;
import pojo.HoaDon;
import pojo.KhachHang;

@Controller
@RequestMapping(value = "/")
public class UserController {
	
	@RequestMapping(value = { "", "/index" })
	public String Index(ModelMap model, HttpSession session) {
		model.addAttribute("listNew", DienThoaiDAO.getInstance().getListByDm("Mới"));
		model.addAttribute("listDis", DienThoaiDAO.getInstance().getListByDm("Giảm giá"));
		model.addAttribute("listNSX", NhaSanXuatDAO.getInstance().getList());
		if (session.getAttribute("taikhoan") != null) {
			String taikhoan = (String) session.getAttribute("taikhoan");
			KhachHang kh = KhachHangDAO.getInstance().getByTaiKhoan(taikhoan);
			model.addAttribute("gioHang", GioHangDAO.getInstance().getGioHangByKH(kh.getMaKh()));
		}
		return "user/index";
	}

	@RequestMapping(value = "/shop", method = RequestMethod.GET)
	public String Shop(@RequestParam("maNsx") int maNsx, ModelMap model, HttpSession session) {
		model.addAttribute("list", DienThoaiDAO.getInstance().getListByNsx(maNsx));
		if (session.getAttribute("taikhoan") != null) {
			String taikhoan = (String) session.getAttribute("taikhoan");
			KhachHang kh = KhachHangDAO.getInstance().getByTaiKhoan(taikhoan);
			model.addAttribute("gioHang", GioHangDAO.getInstance().getGioHangByKH(kh.getMaKh()));
		}
		return "user/shop";
	}

	@RequestMapping(value = "/cart")
	public String Cart(ModelMap model, HttpSession session) {
		if (session.getAttribute("taikhoan") != null) {
			String taikhoan = (String) session.getAttribute("taikhoan");
			KhachHang kh = KhachHangDAO.getInstance().getByTaiKhoan(taikhoan);
			model.addAttribute("gioHang", GioHangDAO.getInstance().getGioHangByKH(kh.getMaKh()));

			int maGH = GioHangDAO.getInstance().getGioHangByKH(kh.getMaKh()).getMaGh();
			model.addAttribute("chiTietGH", ChiTietGioHangDAO.getInstance().getList(maGH));

		}

		return "user/cart";
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public String Checkout(ModelMap model, HttpSession session) {
		if (session.getAttribute("taikhoan") != null) {
			String taikhoan = (String) session.getAttribute("taikhoan");
			KhachHang kh = KhachHangDAO.getInstance().getByTaiKhoan(taikhoan);
			model.addAttribute("gioHang", GioHangDAO.getInstance().getGioHangByKH(kh.getMaKh()));

			int maGH = GioHangDAO.getInstance().getGioHangByKH(kh.getMaKh()).getMaGh();
			model.addAttribute("chiTietGH", ChiTietGioHangDAO.getInstance().getList(maGH));
			model.addAttribute("khachhang",kh);
			model.addAttribute("hoadon", new HoaDon());
		}

		return "user/checkout";
	}
	
	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public ModelAndView Checkout(@ModelAttribute("hoadon") HoaDon model, @RequestParam("maGh") int maGh, HttpSession session) throws IOException{
		ModelAndView mv = new ModelAndView("/user/checkout");
		if (session.getAttribute("taikhoan") != null) {
			String taikhoan = (String) session.getAttribute("taikhoan");
			KhachHang kh = KhachHangDAO.getInstance().getByTaiKhoan(taikhoan);
			GioHang gh = GioHangDAO.getInstance().getGioHangByKH(kh.getMaKh());
			mv.addObject("gioHang", gh);

			mv.addObject("chiTietGH", ChiTietGioHangDAO.getInstance().getList(maGh));
			mv.addObject("khachhang",kh);

			model.setKhachHang(kh);
			model.setStatus(0);
			model.setTongTien(gh.getTongGiaTien());
			model.setHienThiTongTien(gh.getHienThiTongTien());
			if(HoaDonDAO.getInstance().addHoaDon(model)) {
				
				ChiTietHoaDonDAO.getInstance().insertListCTHD(ChiTietGioHangDAO.getInstance().getList(maGh));
				mv.addObject("message","Đặt hàng thành công");
				
			
				
				GioHangDAO.getInstance().resetGioHang(maGh);
				return new ModelAndView("redirect:../products");
			}
		}

		return mv;
	}
	

	@RequestMapping(value = "/single-product", method = RequestMethod.GET)
	public String SingleProduct(@RequestParam("maDt") int maDt, ModelMap model, HttpSession session) {
		DienThoai dt = DienThoaiDAO.getInstance().getById(maDt);
		model.addAttribute("dienthoai", dt);
		if (session.getAttribute("taikhoan") != null) {
			String taikhoan = (String) session.getAttribute("taikhoan");
			KhachHang kh = KhachHangDAO.getInstance().getByTaiKhoan(taikhoan);
			model.addAttribute("gioHang", GioHangDAO.getInstance().getGioHangByKH(kh.getMaKh()));
		}
		return "user/single-product";
	}
	
	
	@RequestMapping(value = "/account")
	public String Account(ModelMap model, HttpSession session) {
		if (session.getAttribute("taikhoan") != null) {
			String taikhoan = (String) session.getAttribute("taikhoan");
			KhachHang kh = KhachHangDAO.getInstance().getByTaiKhoan(taikhoan);
			model.addAttribute("gioHang", GioHangDAO.getInstance().getGioHangByKH(kh.getMaKh()));

			int maGH = GioHangDAO.getInstance().getGioHangByKH(kh.getMaKh()).getMaGh();
			model.addAttribute("chiTietGH", ChiTietGioHangDAO.getInstance().getList(maGH));
			model.addAttribute("khachhang",kh);

		}

		return "user/account";
	}

	@RequestMapping(value = "/account", method = RequestMethod.POST)
	public ModelAndView Account(@Valid @ModelAttribute("khachhang") KhachHang model, @RequestParam("taikhoan") String taikhoan, @RequestParam("maKh") int maKh, Errors err, HttpSession session) {
		ModelAndView mv = new ModelAndView("/user/account");
		mv.addObject("khachhang", model);
		if (model.getTenKh()==null || model.getTenKh().isEmpty()) {
			mv.addObject("errorTen", "Tên không được để trống");
		} else if (model.getSdt()==null || model.getSdt().isEmpty()){
			mv.addObject("errorSdt", "Số điện thoại không được để trống");
		}else if (model.getDiachi()==null || model.getDiachi().isEmpty()){
			mv.addObject("errorDc", "Địa chỉ không được để trống");
		}else {
			model.setMaKh(maKh);
			model.setTaiKhoan(TaiKhoanDAO.getInstance().getById(taikhoan));
			if(KhachHangDAO.getInstance().updateKhachHang(model)) {
				mv.addObject("message", "Cập nhật thông tin thành công");
			}else {
				mv.addObject("message", "Cập nhật thông tin thất bại");
			}
		}

		return mv;
	}
	
	@RequestMapping(value = "/detail-hoadon")
	public String DetailHoaDon(ModelMap model, @RequestParam("maHd") int maHd ,HttpSession session) {
		if (session.getAttribute("taikhoan") != null) {
			String taikhoan = (String) session.getAttribute("taikhoan");
			KhachHang kh = KhachHangDAO.getInstance().getByTaiKhoan(taikhoan);
			model.addAttribute("gioHang", GioHangDAO.getInstance().getGioHangByKH(kh.getMaKh()));

			int maGH = GioHangDAO.getInstance().getGioHangByKH(kh.getMaKh()).getMaGh();
			model.addAttribute("chiTietGH", ChiTietGioHangDAO.getInstance().getList(maGH));
			model.addAttribute("khachhang",kh);
			model.addAttribute("hoadon",HoaDonDAO.getInstance().getById(maHd));
			model.addAttribute("chiTietHoadon", ChiTietHoaDonDAO.getInstance().getListByMaHd(maHd));
		}

		return "user/detail-hoadon";
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String Search(@RequestParam("txtSearch") String txtSearch, @RequestParam("pageid") int pageid, ModelMap model, HttpSession session) {
		model.addAttribute("list", DienThoaiDAO.getInstance().getListByText(txtSearch));
		int total = 2;
		if(pageid==1) {}
		else {
			pageid=(pageid-1)*total+1;
		}
		List<DienThoai> listWithPage = DienThoaiDAO.getInstance().getDienThoaiByPage(pageid, total);
		model.addAttribute("listWithPage", listWithPage);
		if (session.getAttribute("taikhoan") != null) {
			String taikhoan = (String) session.getAttribute("taikhoan");
			KhachHang kh = KhachHangDAO.getInstance().getByTaiKhoan(taikhoan);
			model.addAttribute("gioHang", GioHangDAO.getInstance().getGioHangByKH(kh.getMaKh()));
		}
		return "user/shop";
	}
	
}
