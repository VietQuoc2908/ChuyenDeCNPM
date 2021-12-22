package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import pojo.PhanQuyen;
import pojo.TaiKhoan;
import utils.HibernateUtil;

public class TaiKhoanDAO {

	private static TaiKhoanDAO instance;
    private TaiKhoanDAO(){}

    public static TaiKhoanDAO getInstance()
    {
        if (instance == null)
            instance = new TaiKhoanDAO();
        return instance;
    }

    public boolean createAccount(TaiKhoan tk) {
    	Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			tk.setPhanQuyen(PhanQuyenDAO.getInstance().roleKH(2));
			ses.save(tk);
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
    
    public TaiKhoan getByUsername(String taikhoan) {
    	Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			List<TaiKhoan> users = new ArrayList<TaiKhoan>();
			users = ses.createQuery("from TaiKhoan t where t.taikhoan=:taikhoan").setParameter("taikhoan", taikhoan).list();
			System.out.println(users);
			if(users.size()>0) {
				System.out.println(users);
				return users.get(0);
			}
			ses.getTransaction().commit();
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
		} finally {
			ses.close();
		}
		return null;
    }
    
    public boolean isUser(String taikhoan, String matkhau) {
    	TaiKhoan user = TaiKhoanDAO.getInstance().getByUsername(taikhoan);
        if(user != null && user.getMatkhau().equals(matkhau)) {
            return true;
        }
        return false;
    }
}
