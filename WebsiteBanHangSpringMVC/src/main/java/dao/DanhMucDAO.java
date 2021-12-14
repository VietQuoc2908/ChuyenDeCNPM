package dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;

import pojo.DanhMuc;
import utils.HibernateUtil;

public class DanhMucDAO {

	private static DanhMucDAO instance;
    private DanhMucDAO(){}

    public static DanhMucDAO getInstance()
    {
        if (instance == null)
            instance = new DanhMucDAO();
        return instance;
    }
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
    @Transactional
	public DanhMuc getByID(int id) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		ses.beginTransaction();
		DanhMuc dm = (DanhMuc) ses.get(DanhMuc.class, id);
		ses.close();
		return dm;
	}

}
