<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>


<div class="product-big-title-area">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="product-bit-title text-center">
					<h2>Thông tin tài khoản</h2>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="container">
	<div class="row">
		<div class="col-md-3"></div>

		<div class="col-md-9">
			<f:form modelAttribute="khachhang" method="post">
				<input type="hidden" name=maKh value="${khachhang.maKh }"
										class="form-control" />
				<input type="hidden" name="taikhoan" value="<%=session.getAttribute("taikhoan") %>"
										class="form-control" />
				<div class="form-group">
					<label for="tenKh">Họ tên</label>
					<f:input type="text" path="tenKh" id="tenKh" value="${khachhang.tenKh }"
						class="form-control" />
					<c:if test="${not empty errorTen}">
						<p class="text-danger">${errorTen}</p>
					</c:if>
				</div>

				<div class="form-group">
					<label for="sdt">Số điện thoại</label>
					<f:input type="text" path="sdt" id="sdt" value="${khachhang.sdt }"
						class="form-control" />
					<c:if test="${not empty errorSdt}">
						<p class="text-danger">${errorSdt}</p>
					</c:if>
				</div>
				
				<div class="form-group">
					<label for="diachi">Địa chỉ</label>
					<f:input type="text" path="diachi" id="diachi" value="${khachhang.diachi }"
						class="form-control" />
					<c:if test="${not empty errorDc}">
						<p class="text-danger">${errorDc}</p>
					</c:if>
				</div>
				<c:if test="${not empty message}">
						<p class="text-infor">${message}</p>
					</c:if>
				<input type="submit" value="Cập nhật"
					class="btn btn-success float-right">
			</f:form>
		</div>
	</div>

</div>
<br />