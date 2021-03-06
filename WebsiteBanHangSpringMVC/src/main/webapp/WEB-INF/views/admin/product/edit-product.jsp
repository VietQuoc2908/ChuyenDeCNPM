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
					<h1>Chỉnh sửa sản phẩm</h1>
				</div>
				<div class="col-sm-6">
					<ol class="breadcrumb float-sm-right">
						<li class="breadcrumb-item">Trang chủ</li>
						<li class="breadcrumb-item"><a href="../products?txtSearch=&pageid=1">Sản phẩm</a></li>
						<li class="breadcrumb-item active">Chỉnh sửa sản phẩm</li>
					</ol>
				</div>
			</div>
		</div>
		<!-- /.container-fluid -->
	</section>

	<!-- Main content -->
	<f:form modelAttribute="dienthoai" method="post" enctype="multipart/form-data">
	<section class="content">
		<div class="row">
			<div class="col-md-6">
				<div class="card card-primary">
					<div class="card-body">
						<div class="form-group">
							<label for="hinhAnh">Hình ảnh</label>
							<div class="form-group product-img">
								<img src="data:image/jpg;base64,${dienthoai.base64image}" id="anhsp"/>
							</div>
							<input accept="image/*" type="file" id="hinhAnh" name="hinhAnh"
								onchange="document.getElementById('anhsp').src = window.URL.createObjectURL(this.files[0])"/>
							<br>
							<%-- <f:errors path="hinhAnh" cssClass="text-danger"/> --%>
						</div>
						<f:input type="text" path="maDt" id="maDt" class="form-control" hidden="true"/>
						<div class="form-group">
							<label for="tenDt">Tên điện thoại</label> 
							<f:input type="text" path="tenDt" id="tenDt" class="form-control" value="${dienthoai.tenDt}"/>
							<f:errors path="tenDt" cssClass="text-danger"/>
						</div>
						<div class="form-group">
							<label for="moTa">Mô tả</label>
							<f:textarea id="moTa" path="moTa" class="form-control" value="${dienthoai.moTa}" rows="4"/>
						</div>
						<div class="form-group">
							<label for="nhaSanXuat">Nhà sản xuất</label> 
							<f:select id="nhaSanXuat" path="nhaSanXuat.maNsx" class="form-control custom-select">
								<option value="${dienthoai.nhaSanXuat.maNsx}" selected>${dienthoai.nhaSanXuat.tenNsx}</option>
								<c:forEach items="${listNSX}" var="item" varStatus="idx">
									<option value="${item.maNsx}">${item.tenNsx}</option>
								</c:forEach>
							</f:select>
							<f:errors path="nhaSanXuat" cssClass="text-danger"/>
						</div>
						<div class="form-group">
							<label for="danhMuc">Danh mục</label> 
							<f:select id="danhMuc" path="danhMuc.tenDm" class="form-control custom-select">
								<option value="${dienthoai.danhMuc.tenDm}" selected>${dienthoai.danhMuc.tenDm}</option>
								<c:forEach items="${listDM}" var="item" varStatus="idx">
									<option value="${item.tenDm}">${item.tenDm}</option>
								</c:forEach>
							</f:select>
							<f:errors path="danhMuc" cssClass="text-danger"/>
						</div>
						<div class="form-group">
							<label for="giamGia">Gảm giá</label> 
							<f:input type="number" path="giamGia" id="giamGia" class="form-control" value="${dienthoai.giamGia}"/>
							<f:errors path="giamGia" cssClass="text-danger"/>
						</div>
						<div class="form-group">
							<label for="giaBan">Giá bán</label> 
							<f:input type="number" path="giaBan" id="giaBan" class="form-control" value="${dienthoai.giaBan}"/>
							<f:errors path="giaBan" cssClass="text-danger"/>
						</div>
						<div class="form-group">
							<label for="tonKho">Tồn kho</label> 
							<f:input type="number" path="tonKho" id="tonKho" class="form-control" value="${dienthoai.tonKho}"/>
							<f:errors path="tonKho" cssClass="text-danger"/>
						</div>
					</div>
					<!-- /.card-body -->
				</div>
				<!-- /.card -->
			</div>
			<div class="col-md-6">
				<div class="card card-secondary">
					<div class="card-header">
						<h3 class="card-title">Thông số kĩ thuật</h3>
					</div>
					<div class="card-body">
						<div class="form-group">
							<label for="manHinh">Màn hình</label> 
							<f:input type="text" id="manHinh" path="manHinh" class="form-control"/>
						</div>
						<div class="form-group">
							<label for="heDieuHanh">Hệ điều hành</label> 
							<f:input type="text" id="heDieuHanh" path="heDieuHanh" class="form-control"/>
						</div>
						<div class="form-group">
							<label for="cameraTruoc">Camera Trước</label> 
							<f:input type="text" id="cameraTruoc" path="cameraTruoc" class="form-control"/>
						</div>
						<div class="form-group">
							<label for="cameraSau">Camera Sau</label> 
							<f:input type="text" id="cameraSau" path="cameraSau" class="form-control"/>
						</div>
						<div class="form-group">
							<label for="chip">Chip</label> 
							<f:input type="text" id="chip" path="chip" class="form-control"/>
						</div>
						<div class="form-group">
							<label for="ram">RAM</label> 
							<f:input type="text" id="ram" path="ram" class="form-control"/>
						</div>
						<div class="form-group">
							<label for="rom">ROM</label> 
							<f:input type="text" id="rom" path="rom" class="form-control"/>
						</div>
						<div class="form-group">
							<label for="sim">Sim</label> 
							<f:input type="text" id="sim" path="sim" class="form-control"/>
						</div>
						<div class="form-group">
							<label for="pin">Pin</label> 
							<f:input type="text" id="pin" path="pin" class="form-control"/>
						</div>
					</div>
					<!-- /.card-body -->
				</div>
				<!-- /.card -->
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<a href="../products?txtSearch=&pageid=1" class="btn btn-secondary">Quay lại</a> 
				<input type="submit" value="Cập nhật" class="btn btn-success float-right">
			</div>
		</div>
	</section>
	</f:form>
	<!-- /.content -->
</div>
<!-- /.content-wrapper -->