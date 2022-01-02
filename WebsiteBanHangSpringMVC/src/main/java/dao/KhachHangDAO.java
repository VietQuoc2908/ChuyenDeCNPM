package dao;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import pojo.DienThoai;
import pojo.KhachHang;
import utils.HibernateUtil;

public class KhachHangDAO {

	private static KhachHangDAO instance;

	private KhachHangDAO() {
	}

	public static KhachHangDAO getInstance() {
		if (instance == null)
			instance = new KhachHangDAO();
		return instance;
	}
	
	public List<KhachHang> getList() {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			Query q = ses.createQuery("from KhachHang");
			ses.getTransaction().commit();
			return q.list();
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
			return new ArrayList<KhachHang>();
		} finally {
			ses.close();
		}
	}

	public KhachHang getByTaiKhoan(String taikhoan) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		ses.getTransaction().begin();
		try {
			Query q = ses.createQuery("from KhachHang where taikhoan = '" + taikhoan + "'");
			List<KhachHang> ls = q.list();
			return ls.get(0);
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		} finally {
			ses.close();
		}
		return new KhachHang();
	}
	
	@Transactional
	public boolean updateKhachHang(KhachHang kh) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.getTransaction().begin();
			ses.update(kh);
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
	
	public List<KhachHang> getListByText(String txtSearch) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			Query q = ses.createQuery("from KhachHang where taikhoan like :txtSearch or tenKH like :txtSearch or sdt like :txtSearch or diachi like :txtSearch");
			q.setParameter("txtSearch", "%" + txtSearch + "%");
			ses.getTransaction().commit();
			return q.list();
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
			return new ArrayList<KhachHang>();
		} finally {
			ses.close();
		}
	}
}
