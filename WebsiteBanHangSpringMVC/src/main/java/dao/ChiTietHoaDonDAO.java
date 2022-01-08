package dao;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import pojo.ChiTietGioHang;
import pojo.ChiTietHoaDon;
import pojo.HoaDon;
import utils.HibernateUtil;

public class ChiTietHoaDonDAO {

	// sử dụng singleton để truy vấn đến lớp
	private static ChiTietHoaDonDAO instance;

	private ChiTietHoaDonDAO() {
	}

	public static ChiTietHoaDonDAO getInstance() {
		if (instance == null)
			instance = new ChiTietHoaDonDAO();
		return instance;
	}
	// lấy ra danh sách tất cả các chi tiết hoá đơn
	public List<ChiTietHoaDon> getList() {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			Query q = ses.createQuery("from ChiTietHoaDon");
			ses.getTransaction().commit();
			return q.list();
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
			return new ArrayList<ChiTietHoaDon>();
		} finally {
			ses.close();
		}
	}
	
	// thêm các chi tiết giỏ hàng vào bảng chi tiết hoá đơn.
	// sử dụng khi thanh toán đơn hàng thì giỏ hàng sẽ bị reset và cần phải lưu chi tiết giỏ hàng vào chi tiết tiết hoá đơn.
	public void insertListCTHD(List<ChiTietGioHang> listCTGH) {
		for (ChiTietGioHang chiTietGioHang : listCTGH) {
			ChiTietHoaDon newCTHD = new ChiTietHoaDon();
			
			
			newCTHD.setHoaDon(HoaDonDAO.getInstance().getLastId());
			newCTHD.setDienThoai(chiTietGioHang.getDienThoai());
			newCTHD.setDonGia(chiTietGioHang.getDonGia());
			newCTHD.setSoLuong(chiTietGioHang.getSoLuong());
			newCTHD.setTongTien(chiTietGioHang.getTongTien());
			newCTHD.setHienThiTongTien(chiTietGioHang.getHienThiTongTien());
			
			ChiTietHoaDonDAO.getInstance().insertOneCTHD(newCTHD);
		}
	}
	// thêm một chi tiết hoá đơn
	public void insertOneCTHD(ChiTietHoaDon cthd) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			
			ses.save(cthd);
			ses.getTransaction().commit();
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
		} finally {
			ses.close();
		}
	}
	// lấy ra danh sách chi tiết hoá đơn theo mã hoá đơn.
	// hiển thị thông tin lịch sử mua hàng.
	public List<ChiTietHoaDon> getListByMaHd(int maHd) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			Query q = ses.createQuery("from ChiTietHoaDon where maHD = " + maHd + "");
			ses.getTransaction().commit();
			return q.list();
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
			return new ArrayList<ChiTietHoaDon>();
		} finally {
			ses.close();
		}

	}
	
	// kiểm tra có tồn tại điện thoại trong hoá đơn
		public boolean checkExitCTHD(int maDt) {
			List<ChiTietHoaDon> list = ChiTietHoaDonDAO.getInstance().getList();
			for (ChiTietHoaDon chiTietHoaDon : list) {
				if (chiTietHoaDon.getDienThoai().getMaDt() == maDt) {
					return true;
				}
			}
			
			return false;
		}

}
