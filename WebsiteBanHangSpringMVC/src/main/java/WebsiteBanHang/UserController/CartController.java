package WebsiteBanHang.UserController;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import dao.ChiTietGioHangDAO;
import dao.DanhMucDAO;
import dao.DienThoaiDAO;
import dao.GioHangDAO;
import dao.KhachHangDAO;
import dao.NhaSanXuatDAO;
import pojo.ChiTietGioHang;
import pojo.DienThoai;
import pojo.GioHang;
import pojo.KhachHang;

@Controller
@RequestMapping(value = "/cart")
public class CartController {

	// ajax thêm điện thoại
	@RequestMapping(value = "/add-product/{maDt}/{taikhoan}/{quantity_dt}", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, String> AddProductToCart(@PathVariable("maDt") int maDt,
			@PathVariable("taikhoan") String taikhoan, @PathVariable("quantity_dt") int quantity_dt, ModelMap model) {

		HashMap<String, String> map = new HashMap<String, String>();
		// lấy ra điện thoại theo mã điện thoại
		DienThoai dt = DienThoaiDAO.getInstance().getById(maDt);
		// lấy ra khách hàng theo tài khoản
		KhachHang kh = KhachHangDAO.getInstance().getByTaiKhoan(taikhoan);
		// lấy ra giỏ hàng theo khách hàng
		GioHang gh = GioHangDAO.getInstance().getGioHangByKH(kh.getMaKh());
		ChiTietGioHang ctgh = new ChiTietGioHang();
		System.out.println(ChiTietGioHangDAO.getInstance().checkExitCTGH(gh.getMaGh(), maDt));
		// kiểm tra tồn tại mã điện thoại trong chi tiết giỏ hàng thì xuất thông báo đã tồn tại
		if (ChiTietGioHangDAO.getInstance().checkExitCTGH(gh.getMaGh(), maDt)) {
			map.put("exits", "true");
			return map;
		} else if(dt.getTonKho()>=quantity_dt){
			if (ChiTietGioHangDAO.getInstance().insertCTGH(ctgh, dt, gh, quantity_dt)) {
				map.put("isvalid", "true");
				return map;
			} else {
				map.put("isvalid", "false");
			}
		}else {
			map.put("notenough", "true");
		}
		return map;
	}

	// ajax xoá chi tiết giỏ hàng
	@RequestMapping(value = "/del-product/{maCtgh}/{taikhoan}", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, String> DelProductToCart(@PathVariable("maCtgh") int maCtgh,
			@PathVariable("taikhoan") String taikhoan, ModelMap model) {

		HashMap<String, String> map = new HashMap<String, String>();

		KhachHang kh = KhachHangDAO.getInstance().getByTaiKhoan(taikhoan);

		GioHang gh = GioHangDAO.getInstance().getGioHangByKH(kh.getMaKh());
		if (ChiTietGioHangDAO.getInstance().deleteCTGH(maCtgh, gh)) {
			map.put("isvalid", "true");
		}
		GioHangDAO.getInstance().updateTongTien(gh);
		map.put("hienThiTongTienGH", gh.getHienThiTongTien());
		map.put("isvalid", "false");
		return map;
	}

	// ajã cập nhật giỏ hàng
	@RequestMapping(value = "/update-product/{maCtgh}/{soluong}/{taikhoan}", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, String> UpdateCart(@PathVariable("maCtgh") int maCtgh, @PathVariable("soluong") int soluong,
			@PathVariable("taikhoan") String taikhoan, ModelMap model) {

		HashMap<String, String> map = new HashMap<String, String>();

		KhachHang kh = KhachHangDAO.getInstance().getByTaiKhoan(taikhoan);

		GioHang gh = GioHangDAO.getInstance().getGioHangByKH(kh.getMaKh());
		DienThoai dt = DienThoaiDAO.getInstance().getById(ChiTietGioHangDAO.getInstance().getById(maCtgh).getDienThoai().getMaDt());
		if(dt.getTonKho()>=soluong) {
			// cập nhật số lượng của điện thoại
			if (ChiTietGioHangDAO.getInstance().updateCTGH(maCtgh, soluong)) {
				map.put("hienThiThanhTienCTGH", ChiTietGioHangDAO.getInstance().getById(maCtgh).getHienThiTongTien());
				GioHangDAO.getInstance().updateTongTien(gh);
				map.put("hienThiTongTienGH", gh.getHienThiTongTien());
				map.put("isvalid", "true");
				return map;
			}
		}else {
			map.put("notenough", "true");
		}
		
		map.put("isvalid", "false");
		return map;
	}

}
