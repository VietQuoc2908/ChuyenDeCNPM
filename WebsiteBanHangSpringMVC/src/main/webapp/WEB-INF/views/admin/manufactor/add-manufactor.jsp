<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1>Thêm nhà sản xuất</h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item">Trang chủ</li>
						<li class="breadcrumb-item"><a href="../manufactores">Nhà sản xuất</a></li>
						<li class="breadcrumb-item active">Thêm nhà sản xuất</li>
					</ol>
				</div>
			</div>
		</div>
		<!-- /.container-fluid -->
	</section>

	<!-- Main content -->
	<f:form modelAttribute="nhasanxuat" method="post" enctype="multipart/form-data">
	<section class="content">
		<div class="row">
			<div class="col-md-6">
				<div class="card card-primary">
					<div class="card-body">
						<div class="form-group">
							<label for="hinhAnh">Hình ảnh</label>
							<div class="form-group product-img">
								<img src="" id="anhsp"/>
							</div>
							<f:input accept="image/*" type="file" id="hinhAnh" path="hinhAnh"
								onchange="document.getElementById('anhsp').src = window.URL.createObjectURL(this.files[0])"/>
							<br>
							<c:if test="${not empty message}">
							<p class="text-danger">${message}</p>
							</c:if>
						</div>
						
					</div>
					<!-- /.card-body -->
				</div>
				<!-- /.card -->
			</div>
			<div class="col-md-6">
				<div class="card card-secondary">
					<div class="card-body">
						<div class="form-group">
							<label for="tenNsx">Tên nhà sản xuất</label> 
							<f:input type="text" id="tenNsx" path="tenNsx" class="form-control"/>
						</div>
					</div>
					<!-- /.card-body -->
				</div>
				<!-- /.card -->
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<a href="../manufactores" class="btn btn-secondary">Quay lại</a> 
				<input type="submit" value="Thêm mới" class="btn btn-success float-right">
			</div>
		</div>
	</section>
	</f:form>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->