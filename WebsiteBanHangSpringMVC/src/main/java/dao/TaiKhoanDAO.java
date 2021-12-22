package dao;


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
    
    public boolean checkLogin(String taikhoan, String matkhau) {
    	Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			Query q = ses.createQuery("from TaiKhoan n where n.taikhoan = '"+taikhoan+"' and n.matkhau = '"+matkhau+"'");
			if(!q.getResultList().isEmpty()) {
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
    
}
