package WebsiteBanHang.UserController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dao.ChiTietGioHangDAO;
import dao.ChiTietHoaDonDAO;
import dao.DienThoaiDAO;
import dao.GioHangDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import pojo.DienThoai;
import pojo.HoaDon;
import pojo.KhachHang;

@Controller
@RequestMapping(value = "/")
public class HistoryController {

	@RequestMapping(value = "/history")
	public String History(ModelMap model, @RequestParam(value="pageid") int pageid, HttpSession session) {
		int total=8;    
        if(pageid==1){}    
        else{    
            pageid=(pageid-1)*total+1;    
        }
		model.addAttribute("tongsotrang", HoaDonDAO.getInstance().getTotalPage(total));
		
		if (session.getAttribute("taikhoan") != null) {
			String taikhoan = (String) session.getAttribute("taikhoan");
			KhachHang kh = KhachHangDAO.getInstance().getByTaiKhoan(taikhoan);
			model.addAttribute("gioHang", GioHangDAO.getInstance().getGioHangByKH(kh.getMaKh()));

			int maGH = GioHangDAO.getInstance().getGioHangByKH(kh.getMaKh()).getMaGh();
			model.addAttribute("chiTietGH", ChiTietGioHangDAO.getInstance().getList(maGH));
			model.addAttribute("khachhang",kh);
			
			model.addAttribute("hoadon", HoaDonDAO.getInstance().getHoaDonByPage(pageid, total,kh.getMaKh()));
		}

		return "user/history";
	}
	
	@RequestMapping(value = "/history/cancel-invoice/{maHd}", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, String> CancelInvoice(@PathVariable("maHd") int maHd, ModelMap model) {
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
	
	@RequestMapping(value = "/history/received-invoice/{maHd}", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Map<String, String> ReceivedInvoice(@PathVariable("maHd") int maHd, ModelMap model) {
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

}
