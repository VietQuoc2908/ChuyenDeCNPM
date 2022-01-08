<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="slider-area">
	<!-- Slider -->
	<div class="block-slider block-slider4">
		<ul class="" id="bxslider-home4">
			<li><img
				src="<c:url value="/assets/resources/img/h4-slide.png"/>"
				alt="Slide">
				<div class="caption-group">
					<h2 class="caption title">
						iPhone <span class="primary">6 <strong>Plus</strong></span>
					</h2>
					<h4 class="caption subtitle">Dual SIM</h4>
					<a class="caption button-radius" href="#"><span class="icon"></span>Shop
						now</a>
				</div></li>
			<li><img
				src="<c:url value="/assets/resources/img/h4-slide2.png"/>"
				alt="Slide">
				<div class="caption-group">
					<h2 class="caption title">
						by one, get one <span class="primary">50% <strong>off</strong></span>
					</h2>
					<h4 class="caption subtitle">school supplies & backpacks.*</h4>
					<a class="caption button-radius" href="#"><span class="icon"></span>Shop
						now</a>
				</div></li>
			<li><img
				src="<c:url value="/assets/resources/img/h4-slide3.png"/>"
				alt="Slide">
				<div class="caption-group">
					<h2 class="caption title">
						Apple <span class="primary">Store <strong>Ipod</strong></span>
					</h2>
					<h4 class="caption subtitle">Select Item</h4>
					<a class="caption button-radius" href="#"><span class="icon"></span>Shop
						now</a>
				</div></li>
			<li><img
				src="<c:url value="/assets/resources/img/h4-slide4.png"/>"
				alt="Slide">
				<div class="caption-group">
					<h2 class="caption title">
						Apple <span class="primary">Store <strong>Ipod</strong></span>
					</h2>
					<h4 class="caption subtitle">& Phone</h4>
					<a class="caption button-radius" href="#"><span class="icon"></span>Shop
						now</a>
				</div></li>
		</ul>
	</div>
	<!-- ./Slider -->
</div>
<!-- End slider area -->

<div class="maincontent-area">
	<div class="zigzag-bottom"></div>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="latest-product">
					<h2 class="section-title">Sản phẩm mới nhất</h2>
					<div class="product-carousel">
						<c:forEach items="${listNew}" var="item" varStatus="idx">
							<div class="single-product">
								<div class="product-f-image">
									<img style="height: 180px; object-fix: cover"
										src="data:image/jpg;base64,${item.base64image}" alt="">
									<div class="product-hover">
										<c:if test="${item.tonKho==0}">
											<a class="add-to-cart-link">Hết hàng</a> 
										</c:if>
										<c:if test="${item.tonKho!=0}">
											<a href="#" class="add-to-cart-link btnAddCart"
											data-id="${item.maDt}"
											data-name="<%=session.getAttribute("taikhoan") %>"><i
											class="fa fa-shopping-cart"></i>Thêm vào giỏ</a> 
										</c:if>
										
											
										<a href="./single-product?maDt=${item.maDt}"
										class="view-details-link"><i class="fa fa-link"></i> Xem
										chi tiết</a>
									</div>
								</div>

								<h2>
									<a href="single-product.html"> ${item.tenDt} </a>
								</h2>

								<c:if test="${item.giamGia > 0}">
									<div class="product-carousel-price">
										<ins> ${item.hienThiThanhTien} </ins>
										<del> ${item.hienThiGiaBan} </del>
									</div>
								</c:if>
								<c:if test="${item.giamGia == 0}">
									<div class="product-carousel-price">
										<ins> ${item.hienThiThanhTien} </ins>
									</div>
								</c:if>
							</div>
						</c:forEach>

					</div>
				</div>
			</div>

			<div class="col-md-12">
				<div class="latest-product">
					<h2 class="section-title">Sản phẩm giảm giá</h2>
					<div class="product-carousel">
						<c:forEach items="${listDis}" var="item" varStatus="idx">
							<div class="single-product">
								<div class="product-f-image">
									<img style="height: 180px; object-fix: cover"
										src="data:image/jpg;base64,${item.base64image}" alt="">
									<div class="product-hover">
										<c:if test="${item.tonKho==0}">	
											<a class="add-to-cart-link">Hết hàng</a>
										</c:if>
										<c:if test="${item.tonKho!=0}">
										<a href="#" class="add-to-cart-link btnAddCart"
											data-id="${item.maDt}"
											data-name="<%=session.getAttribute("taikhoan") %>"><i
											class="fa fa-shopping-cart"></i>Thêm vào giỏ</a> 
										</c:if>
										<a href="./single-product?maDt=${item.maDt}"
											class="view-details-link"><i class="fa fa-link"></i> Xem
											chi tiết</a>
									</div>
								</div>

								<h2>
									<a href="single-product.html"> ${item.tenDt} </a>
								</h2>

								<c:if test="${item.giamGia > 0}">
									<div class="product-carousel-price">
										<ins> ${item.hienThiThanhTien} </ins>
										<del> ${item.hienThiGiaBan} </del>
									</div>
								</c:if>
								<c:if test="${item.giamGia == 0}">
									<div class="product-carousel-price">
										<ins> ${item.hienThiThanhTien} </ins>
									</div>
								</c:if>
							</div>
						</c:forEach>

					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- End main content area -->

<div class="brands-area">
	<div class="zigzag-bottom"></div>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="brand-wrapper">
					<div class="brand-list">
						<c:forEach items="${listNSX}" var="item" varStatus="idx">
							<a href="./search?txtSearch=${item.tenNsx}&pageid=1"> <img
								style="width: 200px; object-fix: cover"
								src="data:image/jpg;base64,${item.base64image}" alt="">
							</a>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- End brands area -->


