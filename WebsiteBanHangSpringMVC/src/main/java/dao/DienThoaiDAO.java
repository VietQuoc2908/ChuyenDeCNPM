package dao;

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
    private DienThoaiDAO(){}

    public static DienThoaiDAO getInstance()
    {
        if (instance == null)
            instance = new DienThoaiDAO();
        return instance;
    }
    @Transactional
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
	@Transactional
	public boolean add(DienThoai dt, MultipartFile file) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			dt.setHinhAnh(file.getBytes());
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
		if(DienThoaiDAO.getInstance().getById(dt.getMaDt())==null) {
			return false;
		}
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.getTransaction().begin();;
			ses.update(dt);
			System.out.println("edit: "+dt.getNhaSanXuat().getMaNsx());
			ses.getTransaction().commit();
			return true;
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
			return false;
		}finally {
			ses.close();
		}
	}
	@Transactional
	public boolean delete(int id) {
		if(DienThoaiDAO.getInstance().getById(id)==null) {
			return false;
		}
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.getTransaction().begin();;
			DienThoai dt = (DienThoai) ses.get(DienThoai.class, id);
			ses.delete(dt);
			ses.getTransaction().commit();
			return true;
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
			return false;
		}finally {
			ses.close();
		}
	}

	public DienThoai getById(int id) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		ses.getTransaction().begin();
		try {
			DienThoai dt = (DienThoai) ses.get(DienThoai.class, id);
			return dt;
		}catch(HibernateException ex) {
			System.out.println(ex.getMessage());
		}finally {
			ses.close();
		}
		return new DienThoai();
	}

}
