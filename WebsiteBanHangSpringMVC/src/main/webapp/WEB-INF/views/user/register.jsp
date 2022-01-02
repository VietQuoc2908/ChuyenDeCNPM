<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<div class="product-big-title-area">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="product-bit-title text-center">
					<h2>Đăng ký</h2>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="container">
	<div class="row">
		<div class="col-md-6"></div>

		<div class="col-md-6">
			<h2>Đăng ký</h2>
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

				<div class="form-group">
					<label for="matkhau1">Nhập lại mật khẩu</label> <input
						type="password" id="matkhau1" name="matkhau1" class="form-control" />
					<c:if test="${not empty error}">
						<p class="text-danger">${error}</p>
					</c:if>
				</div>
				<c:if test="${not empty success}">
						<p class="text-success">${success}</p>
				</c:if>
				<input type="submit" value="Đăng ký"
					class="btn btn-success float-right">
				<p style="line-height: 3.2; display: inline; float: right">Đã có tài khoản?<a href="./login"> Đăng nhập</a></p>
			</f:form>
		</div>
	</div>

</div>
<br />
