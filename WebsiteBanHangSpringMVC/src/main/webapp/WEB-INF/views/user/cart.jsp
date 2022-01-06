<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>

<div class="product-big-title-area">
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="product-bit-title text-center">
					<h2>Giỏ hàng</h2>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- End Page title area -->


<div class="single-product-area">
	<div class="zigzag-bottom"></div>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="product-content-right">
					<div class="woocommerce">
						<f:form modelAttribute="chitietgiohang" method="post">
							<table cellspacing="0" class="shop_table cart">
								<thead>
									<tr>
										<th class="product-remove">&nbsp;</th>
										<th class="product-thumbnail">&nbsp;</th>
										<th class="product-name">Sản phẩm</th>
										<th class="product-price">Giá</th>
										<th class="product-quantity">Số lượng</th>
										<th class="product-subtotal">Thành tiền</th>
									</tr>
								</thead>
								<tbody>

									<c:forEach items="${chiTietGH}" var="item" varStatus="idx">
										<tr class="cart_item">
											<td class="product-remove"><a title="Remove this item"
												class="remove btnDelCart" data-id="${item.maCtgh}"
												data-name="<%=session.getAttribute("taikhoan") %>" href="#">Xoá</a>
											</td>

											<td class="product-thumbnail"><a
												href="single-product.html"><img width="145" height="145"
													alt="poster_1_up" class="shop_thumbnail"
													src="data:image/jpg;base64,${item.dienThoai.base64image}"></a>
											</td>

											<td class="product-name"><a href="single-product.html">${item.dienThoai.tenDt }</a>
											</td>

											<td class="product-price"><span class="amount">${item.dienThoai.hienThiThanhTien }</span>
											</td>

											<td class="product-quantity">
												<div class="quantity buttons_added">
													<input type="button" class="minus update_cart"
														data-value="minus"
														data-name="<%=session.getAttribute("taikhoan") %>"
														data-id="${item.maCtgh }" value="-"> <input
														type="number" size="4" class="input-text qty text"
														title="Qty" id="${item.maCtgh}_soluong"
														value="${item.soLuong }" min="1" step="1"> <input
														type="button" class="plus update_cart" data-value="plus"
														data-name="<%=session.getAttribute("taikhoan") %>"
														data-id="${item.maCtgh }" value="+">
												</div>
											</td>

											<td class="product-subtotal"><span
												id="${item.maCtgh}_amount" class="amount">
													${item.hienThiTongTien }</span></td>
										</tr>
									</c:forEach>
									<c:if test="${gioHang.tongGiaTien>0}">
									<tr>
										
										<td class="actions" colspan="6"><a href="./checkout"><input type="button"
											value="Thanh toán" name="checkout"
											class="btn btn-primary"></a></td>
										
									</tr>
									</c:if>
								</tbody>
							</table>
						</f:form>

						<div class="cart-collaterals">

							<div class="cart_totals ">
								<h2>Tổng giỏ hàng</h2>

								<table cellspacing="0">
									<tbody>
										<tr class="shipping">
											<th>Phí giao hàng</th>
											<td>Miễn phí</td>
										</tr>

										<tr class="order-total">
											<th>Tổng tiền</th>
											<td><strong><span id="amount" class="amount">${gioHang.hienThiTongTien }</span></strong>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

