package dao;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import pojo.ChiTietGioHang;
import pojo.DienThoai;
import pojo.GioHang;
import pojo.KhachHang;
import utils.HibernateUtil;

public class ChiTietGioHangDAO {

	private static ChiTietGioHangDAO instance;

	private ChiTietGioHangDAO() {
	}

	public static ChiTietGioHangDAO getInstance() {
		if (instance == null)
			instance = new ChiTietGioHangDAO();
		return instance;
	}

	public boolean insertCTGH(ChiTietGioHang ctgh, DienThoai dt, GioHang gh, int quantity_dt) {

		DecimalFormat formatter = new DecimalFormat("###,###,###");
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();

			ctgh.setGioHang(gh);
			ctgh.setDienThoai(dt);
			ctgh.setSoLuong(quantity_dt);
			ctgh.setDonGia(dt.getThanhTien());
			double thanhTien = quantity_dt*dt.getThanhTien();
			ctgh.setTongTien(thanhTien);
			ctgh.setHienThiTongTien(formatter.format(thanhTien) + " VNĐ");
			ses.save(ctgh);

			Double tongTien = gh.getTongGiaTien() + ctgh.getTongTien();
			gh.setTongGiaTien(tongTien);
			gh.setHienThiTongTien(formatter.format(tongTien) + " VNĐ");
			ses.update(gh);

			ses.getTransaction().commit();

			return true;

		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
			return false;
		} finally {
			ses.close();
		}
	}

	public boolean deleteCTGH(int maCTGH, GioHang gh) {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.getTransaction().begin();

			ChiTietGioHang ctgh = (ChiTietGioHang) ses.get(ChiTietGioHang.class, maCTGH);
			ses.delete(ctgh);

			Double tongTien = gh.getTongGiaTien() - ctgh.getTongTien();
			gh.setTongGiaTien(tongTien);
			gh.setHienThiTongTien(formatter.format(tongTien) + " VNĐ");
			ses.update(gh);

			ses.getTransaction().commit();
			return true;
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
			return false;
		} finally {
			ses.close();
		}
	}
	

	public boolean updateCTGH(int maCTGH, int soluong) {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.getTransaction().begin();
			ChiTietGioHang ctgh = (ChiTietGioHang) ses.get(ChiTietGioHang.class, maCTGH);
			ctgh.setSoLuong(soluong);
			Double tongTien = soluong * ctgh.getDonGia();
			ctgh.setTongTien(tongTien);
			ctgh.setHienThiTongTien(formatter.format(tongTien) + " VNĐ");

			ses.update(ctgh);
			ses.getTransaction().commit();
			return true;
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
			return false;
		} finally {
			ses.close();
		}
	}

	public List<ChiTietGioHang> getList(int maGH) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			Query q = ses.createQuery("from ChiTietGioHang where maGH = " + maGH + "");
			ses.getTransaction().commit();
			return q.list();
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
			return new ArrayList<ChiTietGioHang>();
		} finally {
			ses.close();
		}

	}

	public boolean checkExitCTGH(int maGH, int maDt) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.getTransaction().begin();
			;
			List<ChiTietGioHang> list = ChiTietGioHangDAO.getInstance().getList(maGH);
			for (ChiTietGioHang chiTietGioHang : list) {
				if (chiTietGioHang.getDienThoai().getMaDt() == maDt) {
					ses.getTransaction().commit();
					return true;
				}
			}
			ses.getTransaction().commit();
			return false;
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
			return false;
		} finally {
			ses.close();
		}
	}

	public ChiTietGioHang getById(int maCTGH) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		ses.getTransaction().begin();
		try {
			ChiTietGioHang ctgh = (ChiTietGioHang) ses.get(ChiTietGioHang.class, maCTGH);
			return ctgh;
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		} finally {
			ses.close();
		}
		return new ChiTietGioHang();
	}
}
