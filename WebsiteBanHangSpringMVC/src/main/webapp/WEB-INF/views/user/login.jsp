<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<div class="container">
	<div class="row">
		<div class="col-md-6">
			
		</div>
	</div>
	<div class="col-md-6">
		<h2>Đăng nhập</h2>
		<f:form modelAttribute="nguoidung" method="post">

			<div class="form-group">
				<label for="taikhoan">Tên đăng nhập</label>
				<f:input type="text" path="taikhoan" id="taikhoan"
					class="form-control" />
				<f:errors path="taikhoan" cssClass="text-danger" />
			</div>

			<div class="form-group">
				<label for="matkhau">Mật khẩu</label>
				<f:input type="password" path="matkhau" id="matkhau"
					class="form-control" />
				<f:errors path="matkhau" cssClass="text-danger" />
			</div>

			<input type="submit" value="Đăng nhập"
				class="btn btn-success float-right">
		</f:form>
	</div>
</div>

</div>
<br />