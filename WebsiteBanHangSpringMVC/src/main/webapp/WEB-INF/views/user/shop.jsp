<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <body>
    
    
    
    <div class="product-big-title-area">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="product-bit-title text-center">
                        <h2>Shop</h2>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    
    <div class="single-product-area">
        <div class="zigzag-bottom"></div>
        <div class="container">
            <div class="row">
            <c:forEach items="${list}" var="item" varStatus="idx">
                <div class="col-md-3 col-sm-6">
                    <div class="single-shop-product">
                        <div class="product-upper">
                            <img style="height:230; object-fix:cover" src="data:image/jpg;base64,${item.base64image}" alt="">
                        </div>
                        <h2><a href="./single-product?maDt=${item.maDt}">${item.tenDt}</a></h2>
                        <c:if test="${item.giamGia > 0}">
                        <div class="product-carousel-price">
                            <ins>${item.hienThiThanhTien}</ins> <del>${item.hienThiGiaBan}</del>
                        </div>  
                        </c:if>
                        <c:if test="${item.giamGia == 0}">
                        <div class="product-carousel-price">
                            <ins>${item.hienThiThanhTien}</ins>
                        </div>  
                        </c:if>
                        <div class="product-option-shop">
                            <a class="add_to_cart_button" data-quantity="1" data-product_sku="" data-product_id="70" rel="nofollow" href="/canvas/shop/?add-to-cart=70">Add to cart</a>
                        </div>                       
                    </div>
                </div>
            </c:forEach>
            </div>
            
            <div class="row">
                <div class="col-md-12">
                    <div class="product-pagination text-center">
                        <nav>
                          <ul class="pagination">
                            <li>
                              <a href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                              </a>
                            </li>

                            <c:forEach items="${listWithPage}" var="item" varStatus="idx">
                            <li><a href="./search?txtSearch=<%= request.getParameter("name") %>&pageid=${idx.index+1}">1</a></li>
                            </c:forEach>
                            <li>
                              <a href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                              </a>
                            </li>
                          </ul>
                        </nav>                        
                    </div>
                </div>
            </div>
        </div>
    </div>


    
  </body>
