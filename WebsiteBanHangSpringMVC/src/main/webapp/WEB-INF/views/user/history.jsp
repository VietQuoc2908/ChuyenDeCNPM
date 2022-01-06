<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<br />


<div class="product-big-title-area">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="product-bit-title text-center">
					<h2>Lịch sử mua hàng</h2>
				</div>
			</div>
		</div>
	</div>
</div>
<br>
<div class="container">
	<div class="row">
		<div class="col-md-12">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>STT</th>
						<th>Mã hoá đơn</th>
						<th>Tổng tiền</th>
						<th>Trạng thái</th>
						<th></th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${hoadon}" var="item" varStatus="idx">
						<tr>
							<td>${idx.index+1 }</td>
							<td>${item.maHd}</td>
							<td>${item.hienThiTongTien }</td>

							<c:if test="${item.status==0 }">
								<td id="txtWait_${item.maHd }" style="color: #eb7d34;">Chờ
									xác nhận</td>
							</c:if>

							<c:if test="${item.status==1 }">
								<td id="txtDeli_${item.maHd }" style="color: #ebd334;">
									Đang giao hàng</td>
							</c:if>

							<c:if test="${item.status==2 }">
								<td id="txtReceived_${item.maHd }" style="color: #3aeb34;">
									Đã giao</td>
							</c:if>


							<c:if test="${item.status==3 }">
								<td id="txtCancel_${item.maHd }" style="color: #cc2121;">
									Đã huỷ</td>
							</c:if>

							<td><c:if test="${item.status==0 }">
									<input class="btn btn-danger btnCancel" data-id="${item.maHd }"
										type="button" value="Huỷ đơn hàng" />
								</c:if> <c:if test="${item.status==1 }">
									<input class="btn btn-warning btnReceived"
										data-id="${item.maHd }" type="button" value="Đã nhận hàng" />
								</c:if></td>

							<td><a href="./detail-hoadon?maHd=${item.maHd }"><input
									class="btn btn-info" value="Chi tiết" /></a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<div class="product-pagination text-center">
				<nav>
					<ul class="pagination">


						<c:forEach var="i" begin="0" end="${tongsotrang }" step="1">
							<li><a href="./history?pageid=${i+1}">${i+1}</a></li>
						</c:forEach>

					</ul>
				</nav>
			</div>
		</div>
	</div>

</div>
<br />