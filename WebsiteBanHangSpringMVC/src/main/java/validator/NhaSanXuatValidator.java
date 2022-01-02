package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import pojo.DienThoai;
import pojo.NhaSanXuat;

public class NhaSanXuatValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return NhaSanXuat.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		NhaSanXuat nsx = (NhaSanXuat) target;
		if (nsx.getTenNsx() == null || nsx.getTenNsx().isEmpty()) {
			errors.rejectValue("tenDt", "dt.tenDt", "Tên điện thoại không được để trống");
		}
	}

}
