package pojo;
// Generated Jan 1, 2022, 12:34:54 PM by Hibernate Tools 5.5.7.Final

/**
 * ChiTietGioHang generated by hbm2java
 */
public class ChiTietGioHang implements java.io.Serializable {

	private int maCtgh;
	private DienThoai dienThoai;
	private GioHang gioHang;
	private int soLuong;
	private double donGia;
	private double tongTien;
	private String hienThiTongTien;

	public ChiTietGioHang() {
	}

	public ChiTietGioHang(int maCtgh, DienThoai dienThoai, GioHang gioHang, int soLuong, double donGia, double tongTien,
			String hienThiTongTien) {
		this.maCtgh = maCtgh;
		this.dienThoai = dienThoai;
		this.gioHang = gioHang;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.tongTien = tongTien;
		this.hienThiTongTien = hienThiTongTien;
	}

	public int getMaCtgh() {
		return this.maCtgh;
	}

	public void setMaCtgh(int maCtgh) {
		this.maCtgh = maCtgh;
	}

	public DienThoai getDienThoai() {
		return this.dienThoai;
	}

	public void setDienThoai(DienThoai dienThoai) {
		this.dienThoai = dienThoai;
	}

	public GioHang getGioHang() {
		return this.gioHang;
	}

	public void setGioHang(GioHang gioHang) {
		this.gioHang = gioHang;
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
