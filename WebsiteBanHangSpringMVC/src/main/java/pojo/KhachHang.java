package pojo;
// Generated Jan 1, 2022, 12:34:54 PM by Hibernate Tools 5.5.7.Final

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * KhachHang generated by hbm2java
 */
public class KhachHang implements java.io.Serializable {

	private int maKh;
	private TaiKhoan taiKhoan;
	private String tenKh;
	private String sdt;
	private String diachi;
	private Set hoaDons = new HashSet(0);
	private Set gioHangs = new HashSet(0);

	public KhachHang() {
	}

	public KhachHang(int maKh, TaiKhoan taiKhoan, String tenKh, String sdt, String diachi) {
		this.maKh = maKh;
		this.taiKhoan = taiKhoan;
		this.tenKh = tenKh;
		this.sdt = sdt;
		this.diachi = diachi;
	}

	public KhachHang(int maKh, TaiKhoan taiKhoan, String tenKh, String sdt, String diachi, Set hoaDons,
			Set gioHangs) {
		this.maKh = maKh;
		this.taiKhoan = taiKhoan;
		this.tenKh = tenKh;
		this.sdt = sdt;
		this.diachi = diachi;
		this.hoaDons = hoaDons;
		this.gioHangs = gioHangs;
	}

	public int getMaKh() {
		return this.maKh;
	}

	public void setMaKh(int maKh) {
		this.maKh = maKh;
	}

	public TaiKhoan getTaiKhoan() {
		return this.taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public String getTenKh() {
		return this.tenKh;
	}

	public void setTenKh(String tenKh) {
		this.tenKh = tenKh;
	}

	public String getSdt() {
		return this.sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getDiachi() {
		return this.diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public Set getHoaDons() {
		return this.hoaDons;
	}

	public void setHoaDons(Set hoaDons) {
		this.hoaDons = hoaDons;
	}

	public Set getGioHangs() {
		return this.gioHangs;
	}

	public void setGioHangs(Set gioHangs) {
		this.gioHangs = gioHangs;
	}

}
