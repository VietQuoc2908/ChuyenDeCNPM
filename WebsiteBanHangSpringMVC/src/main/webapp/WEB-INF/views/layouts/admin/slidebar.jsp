<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
if (session.getAttribute("taikhoanAdmin") != null){
%>
<!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4">
	<!-- Sidebar -->
	<div class="sidebar">
		<!-- Sidebar user panel (optional) -->
		<div class="user-panel mt-3 pb-3 mb-3 d-flex">
			<div class="image">
				<img
					src="<c:url value="/assets/resources/dist/img/user2-160x160.jpg"/>"
					class="img-circle elevation-2" alt="User Image">
			</div>
			<div class="info">
				<a href="#" class="d-block">Admin</a>
			</div>
		</div>

		<!-- Sidebar Menu -->
		<nav class="mt-2">
			<ul class="nav nav-pills nav-sidebar flex-column"
				data-widget="treeview" role="menu" data-accordion="false">
				<li class="nav-header">DANH MỤC QUẢN LÝ</li>
				<li class="nav-item"><a href="../../../<%=request.getContextPath() %>/admin/products"
					class="nav-link"> <i class="nav-icon far fa-image"></i>
						<p>Sản phẩm</p>
				</a></li>
				<li class="nav-item"><a href="../../../<%=request.getContextPath() %>/admin/manufactores"
					class="nav-link"> <i class="nav-icon fas fa-columns"></i>
						<p>Nhà sản xuất</p>
				</a></li>
				<li class="nav-item"><a href="../../../<%=request.getContextPath() %>/admin/customers?txtSearch="
					class="nav-link"> <i class="nav-icon fas fa-columns"></i>
						<p>Khách hàng</p>
				</a></li>
				<li class="nav-item"><a href="../../../<%=request.getContextPath() %>/admin/orders"
					class="nav-link"> <i class="nav-icon fas fa-columns"></i>
						<p>Đơn hàng</p>
				</a></li>
			</ul>
		</nav>
		<!-- /.sidebar-menu -->
	</div>
</aside>

<% }%>