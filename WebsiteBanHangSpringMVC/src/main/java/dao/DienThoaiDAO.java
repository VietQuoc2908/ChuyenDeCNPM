package dao;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.web.multipart.MultipartFile;

import pojo.ChiTietGioHang;
import pojo.ChiTietHoaDon;
import pojo.DienThoai;
import pojo.NhaSanXuat;
import utils.HibernateUtil;

public class DienThoaiDAO {
	// sử dụng singleton để truy vấn đến lớp
	private static DienThoaiDAO instance;

	private DienThoaiDAO() {
	}

	public static DienThoaiDAO getInstance() {
		if (instance == null)
			instance = new DienThoaiDAO();
		return instance;
	}

	// lấy ra danh sách điện thoại
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
	
	// lấy ra danh sách điện thoại theo danh mục mới hoặc giảm giá 
	public List<DienThoai> getListByDm(String tenDm) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			Query q = ses.createQuery("from DienThoai where tenDm like :tenDm order by maDt desc");
			q.setParameter("tenDm", "%" + tenDm + "%");
			q.setMaxResults(6);
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

	// lấy ra danh sách điện thoại theo nhà sản xuất
	// ví dụ: samsung, oppo
	@Transactional
	public List<DienThoai> getListByNsx(int maNsx) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			Query q = ses.createQuery("from DienThoai where maNsx = " + maNsx + "");
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
	//lấy ra danh sách điện thoại khi khách hàng tìm kiếm gần đúng
	public List<DienThoai> getListByText(String txtSearch) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			Query q = ses.createQuery("from DienThoai where tenDt like :txtSearch");
			q.setParameter("txtSearch", "%" + txtSearch + "%");
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
	
	//lấy ra danh sách điện thoại theo số trang
	// chức năng phân trang
	public List<DienThoai> getDienThoaiByPage(int pageid, int total, String txtSearch){
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			List<DienThoai> dt = ses.createQuery("from DienThoai where tenDt like :txtSearch").setParameter("txtSearch", "%" + txtSearch + "%").setFirstResult(pageid-1).setMaxResults(total).list();
			ses.getTransaction().commit();
			return dt;
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
			return new ArrayList<DienThoai>();
		} finally {
			ses.close();
		}
	}
	
	// khi phân trang, cần phải lấy ra số lượng trang 
	public int getTotalPage(int total, String txtSearch) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			List<DienThoai> dt = ses.createQuery("from DienThoai where tenDt like :txtSearch").setParameter("txtSearch", "%" + txtSearch + "%").list();
			ses.getTransaction().commit();
			return dt.size()/total;
		} catch (Exception e) {
			ses.getTransaction().rollback();
			System.out.println(e);
			return 0;
		} finally {
			ses.close();
		}
	}

	// thêm điện thoại
	@Transactional
	public boolean add(DienThoai dt) {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			// thêm giá bán, thành tiền, giảm giá
			dt.setHienThiGiaBan(formatter.format(dt.getGiaBan()) + " VNĐ");
			double thanhTien = dt.getGiaBan() - dt.getGiaBan() * dt.getGiamGia() / 100;
			dt.setThanhTien(thanhTien);
			dt.setHienThiThanhTien(formatter.format(thanhTien) + " VNĐ");
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

	//chỉnh sửa điiện thoại
	@Transactional
	public boolean edit(DienThoai dt) {
		DecimalFormat formatter = new DecimalFormat("###,###,###");
		if (DienThoaiDAO.getInstance().getById(dt.getMaDt()) == null) {
			return false;
		}
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.getTransaction().begin();
			// sửa thành tiền
			dt.setHienThiGiaBan(formatter.format(dt.getGiaBan()) + " VNĐ");
			double thanhTien = dt.getGiaBan() - dt.getGiaBan() * dt.getGiamGia() / 100;
			dt.setThanhTien(thanhTien);
			dt.setHienThiThanhTien(formatter.format(thanhTien) + " VNĐ");
			ses.update(dt);
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

	//xoá điện thoại
	@Transactional
	public boolean delete(int id) {
		if (DienThoaiDAO.getInstance().getById(id) == null) {
			return false;
		}
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.getTransaction().begin();
			DienThoai dt = (DienThoai) ses.get(DienThoai.class, id);
			ses.delete(dt);
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

	// lấy điện thoại theo mã
	public DienThoai getById(int id) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		ses.getTransaction().begin();
		try {
			DienThoai dt = (DienThoai) ses.get(DienThoai.class, id);
			return dt;
		} catch (HibernateException ex) {
			System.out.println(ex.getMessage());
		} finally {
			ses.close();
		}
		return new DienThoai();
	}
	
	public boolean tontaima(int id) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.getTransaction().begin();
			DienThoai dt = (DienThoai) ses.get(DienThoai.class, id);
			if(ChiTietGioHangDAO.getInstance().checkExitCTGH(id)) {
				return true;
			}
			if(ChiTietHoaDonDAO.getInstance().checkExitCTHD(id)) {
				return true;
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
	
	public void updatetonKho(int maGh) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			List<DienThoai> listDt = DienThoaiDAO.getInstance().getList();
			List<ChiTietGioHang> listCTGH = ChiTietGioHangDAO.getInstance().getList(maGh);
			for (ChiTietGioHang chiTietGioHang : listCTGH) {
				for (DienThoai dt : listDt) {
					if(dt.getMaDt()==chiTietGioHang.getDienThoai().getMaDt()) {
						dt.setTonKho(dt.getTonKho()-chiTietGioHang.getSoLuong());
						ses.update(dt);
						
					}
				}
			}
			ses.getTransaction().commit();
		} catch (Exception e) {
			ses.getTransaction().rollback();
		} finally {
			ses.close();
		}
	}
	
	public void updatetonKhoHuyDon(int maHd) {
		Session ses = HibernateUtil.getSessionFactory().openSession();
		try {
			ses.beginTransaction();
			List<DienThoai> listDt = DienThoaiDAO.getInstance().getList();
			List<ChiTietHoaDon> listCTHD = ChiTietHoaDonDAO.getInstance().getListByMaHd(maHd);
			for (ChiTietHoaDon chiTietHoaDon : listCTHD) {
				for (DienThoai dt : listDt) {
					if(dt.getMaDt()==chiTietHoaDon.getDienThoai().getMaDt()) {
						dt.setTonKho(dt.getTonKho()+chiTietHoaDon.getSoLuong());
						ses.update(dt);
						
					}
				}
			}
			ses.getTransaction().commit();
		} catch (Exception e) {
			ses.getTransaction().rollback();
		} finally {
			ses.close();
		}
	}


}
