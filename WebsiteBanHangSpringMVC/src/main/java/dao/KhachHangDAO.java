package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import pojo.DienThoai;
import pojo.KhachHang;
import pojo.TaiKhoan;
import utils.HibernateUtil;

public class KhachHangDAO {

	private static KhachHangDAO instance;
    private KhachHangDAO(){}

    public static KhachHangDAO getInstance()
    {
        if (instance == null)
            instance = new KhachHangDAO();
        return instance;
    }
    
    
    public KhachHang getByTaiKhoan(String taikhoan) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		ses.getTransaction().begin();
		try {
			Query q = ses.createQuery("from KhachHang where taikhoan = '"+ taikhoan +"'");
			List<KhachHang> ls = q.list();
			return ls.get(0);
		}catch(HibernateException ex) {
			System.out.println(ex.getMessage());
		}finally {
			ses.close();
		}
		return new KhachHang();
	}
}
