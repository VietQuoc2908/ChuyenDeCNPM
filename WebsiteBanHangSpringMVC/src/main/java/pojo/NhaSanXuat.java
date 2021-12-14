package pojo;
// Generated Dec 14, 2021, 3:57:01 PM by Hibernate Tools 5.5.7.Final

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * NhaSanXuat generated by hbm2java
 */
public class NhaSanXuat implements java.io.Serializable {

	private int maNsx;
	private String tenNsx;
	private byte[] hinhAnh;
	private String base64image;
	private Set dienThoais = new HashSet(0);

	public NhaSanXuat() {
	}

	public NhaSanXuat(int maNsx, String tenNsx) {
		this.maNsx = maNsx;
		this.tenNsx = tenNsx;
	}

	public NhaSanXuat(int maNsx, String tenNsx, byte[] hinhAnh, String base64image, Set dienThoais) {
		this.maNsx = maNsx;
		this.tenNsx = tenNsx;
		this.hinhAnh = hinhAnh;
		this.base64image = base64image;
		this.dienThoais = dienThoais;
	}

	public int getMaNsx() {
		return this.maNsx;
	}

	public void setMaNsx(int maNsx) {
		this.maNsx = maNsx;
	}

	public String getTenNsx() {
		return this.tenNsx;
	}

	public void setTenNsx(String tenNsx) {
		this.tenNsx = tenNsx;
	}

	public byte[] getHinhAnh() {
		return this.hinhAnh;
	}

	public void setHinhAnh(byte[] hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public String getBase64image() {
		return this.base64image;
	}

	public void setBase64image(String base64image) {
		this.base64image = base64image;
	}

	public Set getDienThoais() {
		return this.dienThoais;
	}

	public void setDienThoais(Set dienThoais) {
		this.dienThoais = dienThoais;
	}

}
