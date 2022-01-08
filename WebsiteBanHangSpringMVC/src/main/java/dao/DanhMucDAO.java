package dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;

import pojo.DanhMuc;
import utils.HibernateUtil;

public class DanhMucDAO {
	// sử dụng singleton để truy vấn đến lớp
	private static DanhMucDAO instance;

	private DanhMucDAO() {
	}

	public static DanhMucDAO getInstance() {
		if (instance == null)
			instance = new DanhMucDAO();
		return instance;
	}

	// lấy ra danh sách danh mục
	@Transactional
	public List<DanhMuc> getList() {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			Query q = ses.createQuery("from DanhMuc");
			ses.getTransaction().commit();
			return q.list();
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
			return new ArrayList<DanhMuc>();
		} finally {
			ses.close();
		}
	}

	// thêm danh mục
	@Transactional
	public static boolean add(DanhMuc dm) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			ses.save(dm);
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

	// chỉnh sửa danh mục
	@Transactional
	public boolean edit(DanhMuc dm) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			ses.update(dm);
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

	//xoá danh mục
	@Transactional
	public boolean delete(DanhMuc dm) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			ses.delete(dm);
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

	// lấy ra danh mục theo mã
	@Transactional
	public DanhMuc getByID(int id) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		ses.beginTransaction();
		DanhMuc dm = (DanhMuc) ses.get(DanhMuc.class, id);
		ses.close();
		return dm;
	}

}
