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

	private static ChiTietHoaDonDAO instance;

	private ChiTietHoaDonDAO() {
	}

	public static ChiTietHoaDonDAO getInstance() {
		if (instance == null)
			instance = new ChiTietHoaDonDAO();
		return instance;
	}
	
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

}
