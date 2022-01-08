<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<div class="product-big-title-area">
		<div class="container">
			<div class="row">
				<div class="col-md-12">
					<div class="product-bit-title text-center">
						<h2>Chi tiết hoá đơn #${hoadon.maHd }</h2>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- End Page title area -->

	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<f:form modelAttribute="chiTietHoaDon" method="post">
					<table class="table table-hover table-bordered text-center">
						<thead>
							<tr>
								<th>&nbsp;</th>
								<th>Sản phẩm</th>
								<th>Giá</th>
								<th>Số lượng</th>
								<th>Thành tiền</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${chiTietHoaDon}" var="item" varStatus="idx">
								<tr>
									<td><a><img width="145" height="145" alt="poster_1_up"
											src="data:image/jpg;base64,${item.dienThoai.base64image}"></a>
									</td>
									<td><a>${item.dienThoai.tenDt }</a></td>

									<td><span class="amount">${item.dienThoai.hienThiThanhTien }</span>
									</td>

									<td>${item.soLuong }</td>

									<td><span id="${item.maCthd}_amount" class="amount">
											${item.hienThiTongTien }</span></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</f:form>
			</div>
		</div>
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<h2>Tổng đơn hàng</h2>
				<table class="table table-hover table-bordered">
					<tbody>
						<tr>
							<th>Phí giao hàng</th>
							<td>Miễn phí</td>
						</tr>

						<tr>
							<th>Tổng tiền</th>
							<td><strong><span id="amount" class="amount">${hoadon.hienThiTongTien }</span></strong>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div class="col-md-2"></div>
	</div>
</div>

