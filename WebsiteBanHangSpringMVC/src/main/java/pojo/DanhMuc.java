package pojo;
// Generated Dec 14, 2021, 3:57:01 PM by Hibernate Tools 5.5.7.Final

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * DanhMuc generated by hbm2java
 */
public class DanhMuc implements java.io.Serializable {

	private String tenDm;
	private Set dienThoais = new HashSet(0);

	public DanhMuc() {
	}

	public DanhMuc(String tenDm) {
		this.tenDm = tenDm;
	}

	public DanhMuc(String tenDm, Set dienThoais) {
		this.tenDm = tenDm;
		this.dienThoais = dienThoais;
	}

	public String getTenDm() {
		return this.tenDm;
	}

	public void setTenDm(String tenDm) {
		this.tenDm = tenDm;
	}

	public Set getDienThoais() {
		return this.dienThoais;
	}

	public void setDienThoais(Set dienThoais) {
		this.dienThoais = dienThoais;
	}

}