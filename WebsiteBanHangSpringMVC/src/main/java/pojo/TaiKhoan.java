package pojo;
// Generated Dec 22, 2021, 9:51:21 AM by Hibernate Tools 5.5.7.Final

import java.util.HashSet;
import java.util.Set;

/**
 * TaiKhoan generated by hbm2java
 */
public class TaiKhoan implements java.io.Serializable {

	private String taikhoan;
	private PhanQuyen phanQuyen;
	private String matkhau;
	private Set khachHangs = new HashSet(0);

	public TaiKhoan() {
	}

	public TaiKhoan(String taikhoan, PhanQuyen phanQuyen, String matkhau) {
		this.taikhoan = taikhoan;
		this.phanQuyen = phanQuyen;
		this.matkhau = matkhau;
	}

	public TaiKhoan(String taikhoan, PhanQuyen phanQuyen, String matkhau, Set khachHangs) {
		this.taikhoan = taikhoan;
		this.phanQuyen = phanQuyen;
		this.matkhau = matkhau;
		this.khachHangs = khachHangs;
	}

	public String getTaikhoan() {
		return this.taikhoan;
	}

	public void setTaikhoan(String taikhoan) {
		this.taikhoan = taikhoan;
	}

	public PhanQuyen getPhanQuyen() {
		return this.phanQuyen;
	}

	public void setPhanQuyen(PhanQuyen phanQuyen) {
		this.phanQuyen = phanQuyen;
	}

	public String getMatkhau() {
		return this.matkhau;
	}

	public void setMatkhau(String matkhau) {
		this.matkhau = matkhau;
	}

	public Set getKhachHangs() {
		return this.khachHangs;
	}

	public void setKhachHangs(Set khachHangs) {
		this.khachHangs = khachHangs;
	}

}