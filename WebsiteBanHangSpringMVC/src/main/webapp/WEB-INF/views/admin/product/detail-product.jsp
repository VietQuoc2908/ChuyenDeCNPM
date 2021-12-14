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
					<h1>Chi tiết sản phẩm</h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="../">Trang chủ</a></li>
						<li class="breadcrumb-item"><a href="../products">Sản phẩm</a></li>
						<li class="breadcrumb-item active">Chi tiết sản phẩm</li>
					</ol>
				</div>
			</div>
		</div>
		<!-- /.container-fluid -->
	</section>

	<!-- Main content -->
	<section class="content">

		<!-- Default box -->
		<div class="card card-solid">
			<div class="card-body">
				<div class="row">
					<div class="col-12 col-sm-6">
						<div class="col-12">
							<img src="data:image/jpg;base64,${dienthoai.base64image}" class="product-image"
								alt="Product Image">
						</div>
					</div>
					<div class="col-12 col-sm-6">
						<h3 class="my-3"> ${dienthoai.tenDt} </h3>
						<p> ${dienthoai.moTa} </p>
						<p style="color:red"> ${dienthoai.giaBan} VNĐ</p>
						<p>Giảm giá: ${dienthoai.giamGia} </p>
						<hr>
						<h4>Thông tin sản phẩm</h4>
						<div class="btn-group btn-group-toggle">
						<table class="table table-striped">
							<tbody>
								<tr>
									<td><b>Màn hình:</b></td>
									<td>${dienthoai.manHinh}</td>
								</tr>
								<tr>
									<td><b>Hệ điều hành:</b></td>
									<td>${dienthoai.heDieuHanh}</td>
								</tr>
								<tr>
									<td><b>Camera Sau:</b></td>
									<td>${dienthoai.cameraSau}</td>
								</tr>
								<tr>
									<td><b>Camera Trước:</b></td>
									<td>${dienthoai.cameraTruoc}</td>
								</tr>
								<tr>
									<td><b>Chip:</b></td>
									<td>${dienthoai.chip}</td>
								</tr>
								<tr>
									<td><b>RAM:</b></td>
									<td>${dienthoai.ram}</td>
								</tr>
								<tr>
									<td><b>ROM:</b></td>
									<td>${dienthoai.rom}</td>
								</tr>
								<tr>
									<td><b>SIM:</b></td>
									<td>${dienthoai.sim}</td>
								</tr>
								<tr>
									<td><b>Pin:</b></td>
									<td>${dienthoai.pin}</td>
								</tr>
							</tbody>
						</table>
						</div>

						

						<div class="mt-4">
								<a class="btn btn-secondary" href="../products">Quay lại</a>

								<a class="btn btn-success" href="../products/edit-product?maDt=${dienthoai.maDt}">Chỉnh sửa</a>
						</div>
					</div>
				</div>
			</div>
			<!-- /.card-body -->
		</div>
		<!-- /.card -->

	</section>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->