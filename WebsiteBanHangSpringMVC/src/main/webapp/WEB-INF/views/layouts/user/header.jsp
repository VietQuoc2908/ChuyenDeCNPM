<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
	prefix="decorator"%>

<div class="header-area">
	<div class="container">
		<div class="row">
			<div class="col-md-8">
				<div class="user-menu">
					<ul>
						<%
						if (session.getAttribute("taikhoan") == null || session.getAttribute("taikhoan").equals("")){
						%>
						<li><a href="./login"><i class="fa fa-user"></i> Đăng nhập</a></li>
						<%}else{ %>
						<li><a href="./"><i class="fa fa-user"></i> Xin chào <%=session.getAttribute("taikhoan") %></a></li>
						<%} %>
						<li><a href="#"><i class="fa fa-heart"></i> Wishlist</a></li>
						<li><a href="./cart"><i class="fa fa-user"></i> Giỏ hàng</a></li>
						<li><a href="./checkout"><i class="fa fa-user"></i>
								Checkout</a></li>
						
					</ul>
				</div>
			</div>

			<div class="col-md-4">
				<div class="header-right">
					<ul class="list-unstyled list-inline">
						<li class="dropdown dropdown-small"><a data-toggle="dropdown"
							data-hover="dropdown" class="dropdown-toggle" href="#"><span
								class="key">currency :</span><span class="value">USD </span><b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#">USD</a></li>
								<li><a href="#">INR</a></li>
								<li><a href="#">GBP</a></li>
							</ul></li>
						<%
						if (session.getAttribute("taikhoan") == null || session.getAttribute("taikhoan").equals("")){
						%>
						<%}else{ %>
						<li><a href="./logout">Đăng xuất</a></li>
						<%} %>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- End header area -->

<div class="site-branding-area">
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <div class="logo">
                        <h1><a href="index"><img src="<c:url value="/assets/resources/img/logo.png"/>"></a></h1>
                    </div>
                </div>
                
                <div class="col-sm-6">
                	<%
					if (session.getAttribute("taikhoan") == null || session.getAttribute("taikhoan").equals("")){
					%>
					<%}else{ %>
                    <div class="shopping-item">
                        <a href="cart">Giỏ hàng - <span id="giohang" class="cart-amunt">${gioHang.hienThiTongTien }</span> <i class="fa fa-shopping-cart"></i> <span class="product-count">5</span></a>
                    </div>
                    <%} %>
                </div>
            </div>
        </div>
    </div> <!-- End site branding area -->
    
    <div class="mainmenu-area">
        <div class="container">
            <div class="row">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                </div> 
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="./index">Trang chủ</a></li>
                        <li class="active"><a href="./shop">Shop page</a></li>
                        <li><a href="./cart">Giỏ hàng</a></li>
                        <li><a href="./checkout">Checkout</a></li>
                        <li><a href="#">Category</a></li>
                        <li><a href="#">Others</a></li>
                        <li><a href="#">Contact</a></li>
                    </ul>
                </div>  
            </div>
        </div>
    </div> <!-- End mainmenu area -->
