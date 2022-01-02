package pojo;
// Generated Jan 1, 2022, 12:34:54 PM by Hibernate Tools 5.5.7.Final

/**
 * ChiTietHoaDon generated by hbm2java
 */
public class ChiTietHoaDon implements java.io.Serializable {

	private int maCthd;
	private DienThoai dienThoai;
	private HoaDon hoaDon;
	private int soLuong;
	private double donGia;
	private double tongTien;
	private String hienThiTongTien;

	public ChiTietHoaDon() {
	}

	public ChiTietHoaDon(int maCthd, DienThoai dienThoai, HoaDon hoaDon, int soLuong, double donGia, double tongTien,
			String hienThiTongTien) {
		this.maCthd = maCthd;
		this.dienThoai = dienThoai;
		this.hoaDon = hoaDon;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.tongTien = tongTien;
		this.hienThiTongTien = hienThiTongTien;
	}

	public int getMaCthd() {
		return this.maCthd;
	}

	public void setMaCthd(int maCthd) {
		this.maCthd = maCthd;
	}

	public DienThoai getDienThoai() {
		return this.dienThoai;
	}

	public void setDienThoai(DienThoai dienThoai) {
		this.dienThoai = dienThoai;
	}

	public HoaDon getHoaDon() {
		return this.hoaDon;
	}

	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}

	public int getSoLuong() {
		return this.soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getDonGia() {
		return this.donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public double getTongTien() {
		return this.tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	public String getHienThiTongTien() {
		return this.hienThiTongTien;
	}

	public void setHienThiTongTien(String hienThiTongTien) {
		this.hienThiTongTien = hienThiTongTien;
	}

}
