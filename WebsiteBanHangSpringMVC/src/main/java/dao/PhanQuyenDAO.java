package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import pojo.NhaSanXuat;
import pojo.PhanQuyen;
import utils.HibernateUtil;

public class PhanQuyenDAO {

	private static PhanQuyenDAO instance;

	private PhanQuyenDAO() {
	}

	public static PhanQuyenDAO getInstance() {
		if (instance == null)
			instance = new PhanQuyenDAO();
		return instance;
	}

	// lấy ra phân quyền theo mã quyền
	// Sau khi khách hàng đăng kí vào hệ thống thì phân quyền sẽ được đặt là khách hàng. 
	//Dùng để kiểm tra session đăng nhập vào hệ thống của admin
	public PhanQuyen roleKH(int maQuyen) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		ses.getTransaction().begin();
		try {
			PhanQuyen p = (PhanQuyen) ses.get(PhanQuyen.class, maQuyen);
			return p;
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		} finally {
			ses.close();
		}
		return new PhanQuyen();
	}

}
