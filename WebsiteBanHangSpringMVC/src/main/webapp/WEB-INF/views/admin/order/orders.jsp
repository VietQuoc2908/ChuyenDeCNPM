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
					<h1>Quản lý đơn hàng</h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="./">Trang chủ</a></li>
						<li class="breadcrumb-item active">Quản lý đơn hàng</li>
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
									<input type="text" name="table_search"
										class="form-control float-right" placeholder="Search">

									<div class="input-group-append">
										<button type="submit" class="btn btn-default">
											<i class="fas fa-search"></i>
										</button>
									</div>
								</div>
							</div>
						</div>
						<!-- /.card-header -->
						<div class="card-body table-responsive p-0">
							<table class="table table-hover text-nowrap text-center">
								<thead>
									<tr>
										<th>STT</th>
										<th>Mã hóa đơn</th>
										<th>Mã khách hàng</th>
										<th>Trạng thái</th>
										<th>Tổng tiền</th>
										<th>Hiển thị tổng tiền</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${list}" var="item" varStatus="idx">
									<tr>
										<td>${idx.index+1}</td>
										<td>${item.maHd}</td>
										<td>${item.khachHang.maKh}</td>
										<td>Chờ xác nhận</td>
										<td>${item.tongTien}</td>
										<td>${item.hienThiTongTien}</td>
										<td class="project-actions text-right"><a
											class="btn btn-primary btn-sm" href="../detail-hoadon?maHd=${item.maHd }"> <i
												class="fas fa-folder"> </i> Chi tiết
										</a> <a class="btn btn-info btn-sm" href="#"> <i
												class="fas fa-pencil-alt"> </i> Xác nhận
										</a> <a class="btn btn-danger btn-sm btnDelProduct" href="#" data-id="${item.maHd}"> <i
												class="fas fa-trash"> </i> Xoá
										</a></td>
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

