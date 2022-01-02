package dao;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.web.multipart.MultipartFile;

import pojo.DienThoai;
import pojo.NhaSanXuat;
import utils.HibernateUtil;

public class DienThoaiDAO {

	private static DienThoaiDAO instance;

	private DienThoaiDAO() {
	}

	public static DienThoaiDAO getInstance() {
		if (instance == null)
			instance = new DienThoaiDAO();
		return instance;
	}

	public List<DienThoai> getList() {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			Query q = ses.createQuery("from DienThoai");
			ses.getTransaction().commit();
			return q.list();
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
			return new ArrayList<DienThoai>();
		} finally {
			ses.close();
		}
	}
	
	public List<DienThoai> getListByDm(String tenDm) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			Query q = ses.createQuery("from DienThoai where tenDm like :tenDm order by maDt desc");
			q.setParameter("tenDm", "%" + tenDm + "%");
			q.setMaxResults(6);
			ses.getTransaction().commit();
			return q.list();
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
			return new ArrayList<DienThoai>();
		} finally {
			ses.close();
		}
	}

	@Transactional
	public List<DienThoai> getListByNsx(int maNsx) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			Query q = ses.createQuery("from DienThoai where maNsx = " + maNsx + "");
			ses.getTransaction().commit();
			return q.list();
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
			return new ArrayList<DienThoai>();
		} finally {
			ses.close();
		}

	}
	
	public List<DienThoai> getListByText(String txtSearch) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			Query q = ses.createQuery("from DienThoai where tenDt like :txtSearch");
			q.setParameter("txtSearch", "%" + txtSearch + "%");
			ses.getTransaction().commit();
			return q.list();
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
			return new ArrayList<DienThoai>();
		} finally {
			ses.close();
		}
	}
	
	public List<DienThoai> getDienThoaiByPage(int pageid, int total){
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			List<DienThoai> dt = ses.createQuery("from DienThoai").setFirstResult(pageid).setMaxResults(total).list();
			
			ses.getTransaction().commit();
			return dt;
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
			return new ArrayList<DienThoai>();
		} finally {
			ses.close();
		}
	}

	@Transactional
	public boolean add(DienThoai dt) {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			dt.setHienThiGiaBan(formatter.format(dt.getGiaBan()) + " VNĐ");
			double thanhTien = dt.getGiaBan() - dt.getGiaBan() * dt.getGiamGia() / 100;
			dt.setThanhTien(thanhTien);
			dt.setHienThiThanhTien(formatter.format(thanhTien) + " VNĐ");
			ses.save(dt);
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

	@Transactional
	public boolean edit(DienThoai dt) {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		if (DienThoaiDAO.getInstance().getById(dt.getMaDt()) == null) {
			return false;
		}
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.getTransaction().begin();
			dt.setHienThiGiaBan(formatter.format(dt.getGiaBan()) + " VNĐ");
			double thanhTien = dt.getGiaBan() - dt.getGiaBan() * dt.getGiamGia() / 100;
			dt.setThanhTien(thanhTien);
			dt.setHienThiThanhTien(formatter.format(thanhTien) + " VNĐ");
			ses.update(dt);
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

	@Transactional
	public boolean delete(int id) {
		if (DienThoaiDAO.getInstance().getById(id) == null) {
			return false;
		}
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.getTransaction().begin();
			;
			DienThoai dt = (DienThoai) ses.get(DienThoai.class, id);
			ses.delete(dt);
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

	public DienThoai getById(int id) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		ses.getTransaction().begin();
		try {
			DienThoai dt = (DienThoai) ses.get(DienThoai.class, id);
			return dt;
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		} finally {
			ses.close();
		}
		return new DienThoai();
	}

}
