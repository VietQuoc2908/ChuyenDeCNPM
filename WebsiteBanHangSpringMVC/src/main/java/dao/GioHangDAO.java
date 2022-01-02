package dao;

import java.text.DecimalFormat;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import pojo.ChiTietGioHang;
import pojo.DienThoai;
import pojo.GioHang;
import pojo.KhachHang;
import pojo.PhanQuyen;
import utils.HibernateUtil;

public class GioHangDAO {

	private static GioHangDAO instance;

	private GioHangDAO() {
	}

	public static GioHangDAO getInstance() {
		if (instance == null)
			instance = new GioHangDAO();
		return instance;
	}

	public GioHang getGioHangByKH(int maKH) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		ses.getTransaction().begin();
		try {
			Query q = ses.createQuery("from GioHang where maKH = '" + maKH + "'");
			List<GioHang> ls = q.list();
			return ls.get(0);
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		} finally {
			ses.close();
		}
		return new GioHang();
	}

	public void updateTongTien(GioHang gh) {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		Session ses = HibernateUtil.getSessionFactory().openSession();
		ses.getTransaction().begin();
		try {
			List<ChiTietGioHang> listCTGH = ChiTietGioHangDAO.getInstance().getList(gh.getMaGh());
			Double tongtien = 0.0;
			for (ChiTietGioHang chiTietGioHang : listCTGH) {
				tongtien += chiTietGioHang.getDonGia() * chiTietGioHang.getSoLuong();
			}
			gh.setTongGiaTien(tongtien);
			gh.setHienThiTongTien(formatter.format(tongtien) + " VNĐ");
			ses.update(gh);
			ses.getTransaction().commit();
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		} finally {
			ses.close();
		}
	}
	
	public GioHang getById(int id) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		ses.getTransaction().begin();
		try {
			GioHang dt = (GioHang) ses.get(GioHang.class, id);
			return dt;
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		} finally {
			ses.close();
		}
		return new GioHang();
	}

	public void resetGioHang(int maGh) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		ses.getTransaction().begin();
		try {
			
			GioHang gh = (GioHang) ses.get(GioHang.class, maGh);
			
			List<ChiTietGioHang> listCTGH = ChiTietGioHangDAO.getInstance().getList(maGh);
			for (ChiTietGioHang ctgh : listCTGH) {
				int maCTGH = ctgh.getMaCtgh();
				ChiTietGioHang c =  ChiTietGioHangDAO.getInstance().getById(maCTGH);
				ses.delete(c);
			}
			
			gh.setTongGiaTien(0);
			gh.setHienThiTongTien("0 VNĐ");
			ses.update(gh);
			ses.getTransaction().commit();
			
			
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		} finally {
			ses.close();
		}
		
	}
}
