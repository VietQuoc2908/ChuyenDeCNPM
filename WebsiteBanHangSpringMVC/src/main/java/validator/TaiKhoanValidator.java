package validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import pojo.NhaSanXuat;
import pojo.TaiKhoan;

public class TaiKhoanValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return TaiKhoan.class.equals(clazz);
	}

	public void validate(Object target, Errors errors) {
		TaiKhoan tk = (TaiKhoan)target;
		if(tk.getTaikhoan().isEmpty() || tk.getTaikhoan()==null) {
			errors.rejectValue("taikhoan", "tk.taikhoan","Tên đăng nhập không được để trống");
		}else if(tk.getMatkhau().isEmpty() || tk.getMatkhau()==null){
			errors.rejectValue("matkhau", "tk.matkhau","Mật khẩu không được để trống");
		}
		
	}


}
