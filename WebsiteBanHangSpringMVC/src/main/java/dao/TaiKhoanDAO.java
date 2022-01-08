package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import pojo.DienThoai;
import pojo.GioHang;
import pojo.KhachHang;
import pojo.NhaSanXuat;
import pojo.PhanQuyen;
import pojo.TaiKhoan;
import utils.HibernateUtil;

public class TaiKhoanDAO {

	private static TaiKhoanDAO instance;

	private TaiKhoanDAO() {
	}

	public static TaiKhoanDAO getInstance() {
		if (instance == null)
			instance = new TaiKhoanDAO();
		return instance;
	}

	// khách hàng đăng kí tài khoản
	// hệ thống tạo một tài khoản trong bảng TaiKhoan
	// tạo một khách hàng trong bảng KhachHang
	// tạo một giỏ hàng trong bảng GioHang
	public boolean createAccount(TaiKhoan tk) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		try {
			ses.beginTransaction();
			// tạo taikhoan
			// dùng phân quyền là khách hàng
			tk.setPhanQuyen(PhanQuyenDAO.getInstance().roleKH(2));
			ses.save(tk);
			// tạo khachHang
			KhachHang kh = new KhachHang();
			kh.setTenKh("");
			kh.setSdt("");
			kh.setDiachi("");
			kh.setTaiKhoan(tk);
			ses.save(kh);
			// tạo giohang
			GioHang gh = new GioHang();
			gh.setKhachHang(kh);
			gh.setTongGiaTien(0);
			gh.setHienThiTongTien(formatter.format(0) + " VNĐ");
			ses.save(gh);
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

	// kiểm tra đăng nhập, nếu đúng tài khoản và mật khẩu: true
	public boolean checkLogin(String taikhoan, String matkhau) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			Query q = ses.createQuery(
					"from TaiKhoan n where n.phanQuyen.maQuyen=2 and n.taikhoan = '" + taikhoan + "' and n.matkhau = '" + matkhau + "'");
			if (!q.getResultList().isEmpty()) {
				ses.getTransaction().commit();
				return true;
			}
			ses.getTransaction().commit();
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
		} finally {
			ses.close();
		}
		return false;
	}
	
	// kiểm tra tài khoản và mật khẩu của admin 
	public boolean checkLoginAdmin(String taikhoan, String matkhau) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			Query q = ses.createQuery(
					"from TaiKhoan n where n.phanQuyen.maQuyen=1 and n.taikhoan = '" + taikhoan + "' and n.matkhau = '" + matkhau + "'");
			if (!q.getResultList().isEmpty()) {
				ses.getTransaction().commit();
				return true;
			}
			ses.getTransaction().commit();
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
		} finally {
			ses.close();
		}
		return false;
	}
	
	// lấy ra tài khoản theo mã tài khoản
	public TaiKhoan getById(String taikhoan) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		ses.getTransaction().begin();
		try {
			Query q = ses.createQuery("from TaiKhoan where taikhoan = '" + taikhoan + "'");
			List<TaiKhoan> ls = q.list();
			return ls.get(0);
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		} finally {
			ses.close();
		}
		return new TaiKhoan();
	}
	
	public List<TaiKhoan> getList() {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			Query q = ses.createQuery("from TaiKhoan");
			ses.getTransaction().commit();
			return q.list();
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
			return new ArrayList<TaiKhoan>();
		} finally {
			ses.close();
		}
	}

	public boolean checkTonTaiUsername(String username) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			List<TaiKhoan> tk = TaiKhoanDAO.getInstance().getList();
			for (TaiKhoan taiKhoan : tk) {
				if(taiKhoan.getTaikhoan().equals(username)){
					return true;
				}
			}
			ses.getTransaction().commit();
			return false;
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
		} finally {
			ses.close();
		}
		return false;
	}
}
