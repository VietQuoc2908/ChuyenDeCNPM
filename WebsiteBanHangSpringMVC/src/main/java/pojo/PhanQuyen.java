package pojo;
// Generated Jan 1, 2022, 12:34:54 PM by Hibernate Tools 5.5.7.Final

import java.util.HashSet;
import java.util.Set;

/**
 * PhanQuyen generated by hbm2java
 */
public class PhanQuyen implements java.io.Serializable {

	private int maQuyen;
	private String tenQuyen;
	private Set taiKhoans = new HashSet(0);

	public PhanQuyen() {
	}

	public PhanQuyen(int maQuyen, String tenQuyen) {
		this.maQuyen = maQuyen;
		this.tenQuyen = tenQuyen;
	}

	public PhanQuyen(int maQuyen, String tenQuyen, Set taiKhoans) {
		this.maQuyen = maQuyen;
		this.tenQuyen = tenQuyen;
		this.taiKhoans = taiKhoans;
	}

	public int getMaQuyen() {
		return this.maQuyen;
	}

	public void setMaQuyen(int maQuyen) {
		this.maQuyen = maQuyen;
	}

	public String getTenQuyen() {
		return this.tenQuyen;
	}

	public void setTenQuyen(String tenQuyen) {
		this.tenQuyen = tenQuyen;
	}

	public Set getTaiKhoans() {
		return this.taiKhoans;
	}

	public void setTaiKhoans(Set taiKhoans) {
		this.taiKhoans = taiKhoans;
	}

}
