package dao;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import pojo.DienThoai;
import pojo.GioHang;
import pojo.HoaDon;
import pojo.TaiKhoan;
import utils.HibernateUtil;

public class HoaDonDAO {

	private static HoaDonDAO instance;

	private HoaDonDAO() {
	}

	public static HoaDonDAO getInstance() {
		if (instance == null)
			instance = new HoaDonDAO();
		return instance;
	}

	@Transactional
	public boolean addHoaDon(HoaDon hd) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			ses.save(hd);
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
	
	public List<HoaDon> getList() {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			Query q = ses.createQuery("from HoaDon");
			ses.getTransaction().commit();
			return q.list();
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
			return new ArrayList<HoaDon>();
		} finally {
			ses.close();
		}
	}
	
	public HoaDon getLastId() {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		ses.getTransaction().begin();
		try {
			HoaDon hd = (HoaDon) ses.createQuery("from HoaDon t order by t.maHd desc").setMaxResults(1).uniqueResult();

			return hd;
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		} finally {
			ses.close();
		}
		return new HoaDon();
	}
	
	public List<HoaDon> getHoaDonByKH(int maKH) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		ses.getTransaction().begin();
		try {
			Query q = ses.createQuery("from HoaDon where maKH = '" + maKH + "' order by maHD desc");
			List<HoaDon> ls = q.list();
			return ls;
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		} finally {
			ses.close();
		}
		return new ArrayList<HoaDon>();
	}
	
	public HoaDon getById(int maHd) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		ses.getTransaction().begin();
		try {
			HoaDon hd = (HoaDon) ses.get(HoaDon.class, maHd);
			return hd;
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		} finally {
			ses.close();
		}
		return new HoaDon();
	}
	
	public boolean updateStatus(HoaDon hd) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.getTransaction().begin();
			ses.update(hd);
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
	
	
	public List<HoaDon> getHoaDonByPage(int pageid, int total, int maKH){
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			List<HoaDon> hd = ses.createQuery("from HoaDon where maKH = '" + maKH + "' order by maHD desc").setFirstResult(pageid-1).setMaxResults(total).list();
			ses.getTransaction().commit();
			return hd;
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
			return new ArrayList<HoaDon>();
		} finally {
			ses.close();
		}
	}
	
	public List<HoaDon> getHoaDonByPageAdmin(int pageid, int total){
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			List<HoaDon> hd = ses.createQuery("from HoaDon order by maHD desc").setFirstResult(pageid-1).setMaxResults(total).list();
			ses.getTransaction().commit();
			return hd;
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
			return new ArrayList<HoaDon>();
		} finally {
			ses.close();
		}
	}
	
	public List<HoaDon> getHoaDonByKHPageAdmin(int pageid, int total, int maKH) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		ses.getTransaction().begin();
		try {
			Query q = ses.createQuery("from HoaDon where maKH = '" + maKH + "' order by maHD desc");
			List<HoaDon> ls = q.setFirstResult(pageid-1).setMaxResults(total).list();
			return ls;
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		} finally {
			ses.close();
		}
		return new ArrayList<HoaDon>();
	}
	
	public int getTotalPage(int total) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			List<HoaDon> hd = ses.createQuery("from HoaDon").list();
			ses.getTransaction().commit();
			return hd.size()/total;
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
			return 0;
		} finally {
			ses.close();
		}
	}
	
}
