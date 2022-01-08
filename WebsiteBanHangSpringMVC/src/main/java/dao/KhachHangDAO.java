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
	
	// lấy ra danh sách khách hàng
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

	// lấy ra khách hàng theo tài khoản đăng nhập
	// dùng lấy ra để hiển thị tên sđt và địa chỉ
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
	
	// cập nhật lại thông tin khách hàng
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
	
	// Lấy ra danh sách khách hàng theo tìm kiếm của admin
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
	
	// Lấy ra danh sách khách hàng có phân trang và tìm kiếm của admin
	public List<KhachHang> getKhachHangByPage(int pageid, int total, String txtSearch){
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			List<KhachHang> list = ses.createQuery("from KhachHang where taikhoan like :txtSearch or tenKH like :txtSearch or sdt like :txtSearch or diachi like :txtSearch").setParameter("txtSearch", "%" + txtSearch + "%").setFirstResult(pageid-1).setMaxResults(total).list();
			ses.getTransaction().commit();
			return list;
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
			return new ArrayList<KhachHang>();
		} finally {
			ses.close();
		}
	}
}
