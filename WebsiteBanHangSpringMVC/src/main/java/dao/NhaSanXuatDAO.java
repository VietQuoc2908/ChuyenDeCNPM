package dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.web.multipart.MultipartFile;

import pojo.DienThoai;
import pojo.NhaSanXuat;
import utils.HibernateUtil;

public class NhaSanXuatDAO {

	private static NhaSanXuatDAO instance;

	private NhaSanXuatDAO() {
	}

	public static NhaSanXuatDAO getInstance() {
		if (instance == null)
			instance = new NhaSanXuatDAO();
		return instance;
	}

	// lấy ra danh sách nhà sản xuất
	@Transactional
	public List<NhaSanXuat> getList() {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			Query q = ses.createQuery("from NhaSanXuat");
			ses.getTransaction().commit();
			return q.list();
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
			return new ArrayList<NhaSanXuat>();
		} finally {
			ses.close();
		}
	}

	// thêm một nhà sản xuất
	@Transactional
	public boolean add(NhaSanXuat nsx, MultipartFile file) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			nsx.setHinhAnh(file.getBytes());
			ses.save(nsx);
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

	// cập nhật lại nhà sản xuất
	@Transactional
	public boolean edit(NhaSanXuat nsx) {
		if (NhaSanXuatDAO.getInstance().getById(nsx.getMaNsx()) == null) {
			return false;
		}
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.getTransaction().begin();
			;
			ses.update(nsx);
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

	// xoá nhà sản xuất
	@Transactional
	public boolean delete(int id) {
		if (NhaSanXuatDAO.getInstance().getById(id) == null) {
			return false;
		}
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.getTransaction().begin();
			;
			NhaSanXuat nsx = (NhaSanXuat) ses.get(NhaSanXuat.class, id);
			ses.delete(nsx);
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

	// lấy ra một nhà sản xuất theo mã
	public NhaSanXuat getById(int id) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		ses.getTransaction().begin();
		try {
			NhaSanXuat nsx = (NhaSanXuat) ses.get(NhaSanXuat.class, id);
			return nsx;
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		} finally {
			ses.close();
		}
		return new NhaSanXuat();
	}
	
	public boolean tontaima(int id) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.getTransaction().begin();
			NhaSanXuat dt = (NhaSanXuat) ses.get(NhaSanXuat.class, id);
			List<DienThoai> listdt = DienThoaiDAO.getInstance().getList();
			for (DienThoai dienThoai : listdt) {
				if (dienThoai.getNhaSanXuat().getMaNsx() == id) {
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
}
