package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import pojo.NhaSanXuat;
import pojo.PhanQuyen;
import utils.HibernateUtil;

public class PhanQuyenDAO {

	private static PhanQuyenDAO instance;
    private PhanQuyenDAO(){}

    public static PhanQuyenDAO getInstance()
    {
        if (instance == null)
            instance = new PhanQuyenDAO();
        return instance;
    }
    
    public PhanQuyen roleKH(int maQuyen) {
    	Session ses = HibernateUtil.getSessionFactory().openSession();
		ses.getTransaction().begin();
		try {
			PhanQuyen p = (PhanQuyen) ses.get(PhanQuyen.class, maQuyen);
			return p;
		}catch(HibernateException ex) {
			System.out.println(ex.getMessage());
		}finally {
			ses.close();
		}
		return new PhanQuyen();
    }

}
