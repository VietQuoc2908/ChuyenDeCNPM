<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>



<div class="product-big-title-area">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="product-bit-title text-center">
					<h2>Thanh toán</h2>
				</div>
			</div>
		</div>
	</div>
</div>


<div class="single-product-area">
	<div class="zigzag-bottom"></div>
	<div class="container">
		<f:form modelAttribute="hoadon" method="post">
			<input type="hidden" name="maGh" value=${gioHang.maGh } />
			<div class="row">
				<div class="col-lg-4">

					<div class="form-group">
						<label for="tenKh">Họ tên</label>
						<input type="text" name="tenKh" id="tenKh" value="${khachhang.tenKh }" class="form-control" />
						<c:if test="${not empty errorTen}">
							<p class="text-danger">${errorTen}</p>
						</c:if>
					</div>

					<div class="form-group">
						<label for="sdt">Số điện thoại</label>
						<input type="text" name="sdt" id="sdt" value="${khachhang.sdt }" class="form-control" />
						<c:if test="${not empty errorSdt}">
							<p class="text-danger">${errorSdt}</p>
						</c:if>
					</div>

					<div class="form-group">
						<label for="diachi">Địa chỉ</label>
						<input type="text" name="diachi" id="diachi" value="${khachhang.diachi }" class="form-control" />
						<c:if test="${not empty errorDc}">
							<p class="text-danger">${errorDc}</p>
						</c:if>
					</div>


				</div>
				<div class="col-lg-8">
					<h3 id="order_review_heading">Đơn hàng của bạn</h3>

					<div id="order_review" style="position: relative;">
						<table class="shop_table">
							<thead>
								<tr>
									<th class="product-name">Sản phẩm</th>
									<th class="product-total">Thành tiền</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${chiTietGH}" var="item" varStatus="idx">
									<tr class="cart_item">
										<td class="product-name"> ${item.dienThoai.tenDt } <strong
											class="product-quantity"> - SL:${item.soLuong }</strong>
										</td>
										<td class="product-total"><span class="amount">${item.hienThiTongTien }</span>
										</td>
									</tr>
								</c:forEach>
							</tbody>
							<tfoot>

								<tr class="cart-subtotal">
									<th>Tổng tiền cần thanh toán</th>
									<td><span class="amount">${gioHang.hienThiTongTien }</span></td>
								</tr>

								<tr class="shipping">
									<th>Vận chuyển</th>
									<td>Miễn phí <input type="hidden"
										class="shipping_method" value="free_shipping"
										id="shipping_method_0" data-index="0"
										name="shipping_method[0]">
									</td>
								</tr>


								<tr class="order-total">
									<th>Tổng cộng</th>
									<td><strong><span class="amount">${gioHang.hienThiTongTien }</span></strong></td>
								</tr>

							</tfoot>
						</table>


						<div id="payment">
							<ul class="payment_methods methods">
								<li class="payment_method_bacs"><input type="radio"
									data-order_button_text="" checked="checked" value="bacs"
									name="payment_method" class="input-radio"
									id="payment_method_bacs"> <label
									for="payment_method_bacs">Thanh toán bằng tiền mặt </label>
									<div class="payment_box payment_method_bacs">
										<p>Đảm bảo thông tin địa chỉ, số điện thoại của bạn đã đúng.
										Đơn đặt hàng của bạn sẽ được vận chuyển trong 2-3 ngày.</p>
									</div></li>
	

							</ul>

							<div class="form-row place-order">

								<input type="submit" data-value="Place order"
									value="Đặt hàng" id="place_order"
									name="woocommerce_checkout_place_order" class="button alt">


							</div>

						</div>
					</div>
				</div>
			</div>
			<c:if test="${not empty message}">
				<p class="text-success">${message}</p>
			</c:if>
		</f:form>
	</div>
</div>


