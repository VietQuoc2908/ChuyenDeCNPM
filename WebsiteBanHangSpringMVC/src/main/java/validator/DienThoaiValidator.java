package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import pojo.DienThoai;

public class DienThoaiValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return DienThoai.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {

		DienThoai dt = (DienThoai) target;

		if (dt.getTenDt() == null || dt.getTenDt().isEmpty()) {
			errors.rejectValue("tenDt", "dt.tenDt", "Tên điện thoại không được để trống");
		} else if (dt.getNhaSanXuat() == null) {
			errors.rejectValue("nhaSanXuat", "dt.nhaSanXuat", "Nhà sản xuất không được để trống");
		} else if (dt.getDanhMuc() == null) {
			errors.rejectValue("danhMuc", "dt.danhMuc", "Danh mục không được bỏ trống");
		} else if (dt.getDanhMuc().getTenDm().equalsIgnoreCase("Giảm giá") && dt.getGiamGia() <= 0) {
			errors.rejectValue("giamGia", "dt.giamGia", "Giảm giá không phù hợp");
		} else if (dt.getGiamGia() < 0) {
			errors.rejectValue("giamGia", "dt.giamGia", "Giảm giá không phù hợp");
		} else if (dt.getGiaBan() <= 0) {
			errors.rejectValue("giaBan", "dt.giaBan", "Giá bán không phù hợp");
		} else if (dt.getTonKho() <= 0) {
			errors.rejectValue("tonKho", "dt.tonKho", "Tồn kho không phù hợp");
		}
	}

}
