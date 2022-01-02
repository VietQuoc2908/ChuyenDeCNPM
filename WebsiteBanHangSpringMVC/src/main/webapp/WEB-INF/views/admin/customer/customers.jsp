<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<div class="container-fluid">
			<div class="row mb-2">
				<div class="col-sm-6">
					<h1>Danh sách khách hàng</h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="./">Trang chủ</a></li>
						<li class="breadcrumb-item active">Danh sách khách hàng</li>
					</ol>
				</div>
			</div>
		</div>
		<!-- /.container-fluid -->
	</section>

	<section class="content">
		<div class="container-fluid">

			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-header">

							<div class="card-tools">
								<div class="input-group input-group-sm" style="width: 150px;">
									<f:form method="get" class="navbar-form navbar-right" role="search">
										<input type="text" name="txtSearch"
											class="form-control float-right" placeholder="Search">
	
										<div class="input-group-append">
											<button type="submit" class="btn btn-default">
												<i class="fas fa-search"></i>
											</button>
										</div>
									</f:form>
								</div>
							</div>
						</div>
						<!-- /.card-header -->
						<div class="card-body table-responsive p-0">
							<table class="table table-hover text-nowrap text-center">
								<thead>
									<tr>
										<th>Mã KH</th>
										<th>Tài khoản</th>
										<th>Tên khách hàng</th>
										<th>Số điện thoại</th>
										<th>Địa chỉ</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${list}" var="item" varStatus="idx">
									<tr>
										<td>${item.maKh}</td>
										<td>${item.taiKhoan.taikhoan}</td>
										<td>${item.tenKh}</td>
										<td>${item.sdt}</td>
										<td>${item.diachi}</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</div>

