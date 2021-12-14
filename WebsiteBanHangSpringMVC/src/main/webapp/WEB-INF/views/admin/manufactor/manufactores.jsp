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
					<h1>Quản lý nhà sản xuất</h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item"><a href="./">Trang chủ</a></li>
						<li class="breadcrumb-item active">Quản lý nhà sản xuất</li>
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
							<h3 class="card-title"><a href="./manufactores/add-manufactor">Thêm mới</a></h3>

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
										<th>Hình ảnh</th>
										<th>Tên nhà sản xuất</th>
										<th></th>
									</tr>
								</thead>
								<tbody>
								<c:forEach items="${list}" var="item" varStatus="idx">
									<tr>
										<td>${idx.index+1}</td>


										<td><img src="data:image/jpg;base64,${item.base64image}" style="width:auto;height:50px;"></img></td>
										<td>${item.tenNsx}</td>
										<td class="project-actions text-right"><a class="btn btn-info btn-sm" href="./manufactores/edit-manufactor?maNsx=${item.maNsx}"> <i
												class="fas fa-pencil-alt"> </i> Sửa
										</a> <a class="btn btn-danger btn-sm btnDelManufactor" href="#" data-name="${item.tenNsx}" data-id="${item.maNsx}"> <i
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