jQuery(document).ready(function($){
    
    // jQuery sticky Menu
    
	$(".mainmenu-area").sticky({topSpacing:0});
    
    
    $('.product-carousel').owlCarousel({
        loop:true,
        nav:true,
        margin:20,
        responsiveClass:true,
        responsive:{
            0:{
                items:1,
            },
            600:{
                items:3,
            },
            1000:{
                items:5,
            }
        }
    });  
    
    $('.related-products-carousel').owlCarousel({
        loop:true,
        nav:true,
        margin:20,
        responsiveClass:true,
        responsive:{
            0:{
                items:1,
            },
            600:{
                items:2,
            },
            1000:{
                items:2,
            },
            1200:{
                items:3,
            }
        }
    });  
    
    $('.brand-list').owlCarousel({
        loop:true,
        nav:true,
        margin:20,
        responsiveClass:true,
        responsive:{
            0:{
                items:1,
            },
            600:{
                items:3,
            },
            1000:{
                items:4,
            }
        }
    });    
    
    
    // Bootstrap Mobile Menu fix
    $(".navbar-nav li a").click(function(){
        $(".navbar-collapse").removeClass('in');
    });    
    
    // jQuery Scroll effect
    $('.navbar-nav li a, .scroll-to-up').bind('click', function(event) {
        var $anchor = $(this);
        var headerH = $('.header-area').outerHeight();
        $('html, body').stop().animate({
            scrollTop : $($anchor.attr('href')).offset().top - headerH + "px"
        }, 1200, 'easeInOutExpo');

        event.preventDefault();
    });    
    
    // Bootstrap ScrollPSY
    $('body').scrollspy({ 
        target: '.navbar-collapse',
        offset: 95
    })      
});

  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','https://www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-10146041-21', 'auto');
  ga('send', 'pageview');



$(document).ready(function() {
	$('body').delegate('.btnAddCart', 'click', function(e) {
		e.preventDefault();
		var atag = $(this);
		var maDt = atag.attr('data-id');
		var quantity_dt = $('#quantity_dt').val();
		console.log(quantity_dt);
		
		if(!quantity_dt){
			quantity_dt=1;
		}
		
		var taikhoan = atag.attr('data-name');
		if(taikhoan=="null" || !taikhoan){
			alert("Bạn cần đăng nhập");
			return;
		}
		console.log(maDt);
		$.ajax(
			{
				url: './cart/add-product/' + maDt+'/'+taikhoan+'/'+quantity_dt,
				method: "post",
				success: function(data) {
					console.log(data)
					if (data.exits){
						alert("Sản phẩm đã có trong giỏ hàng");
						return;
					}else{
						if (data.isvalid) {
							alert("Thêm sản phẩm thành công");
							return;
						}
						else {
							alert("error");
							return;
						}
					}
					

				},
				failed: function() {

				}
			}
		)
	});
	
	$('body').delegate('.btnDelCart', 'click', function(e) {
		e.preventDefault();
		var atag = $(this);
		var maCtgh = atag.attr('data-id');
		var taikhoan = atag.attr('data-name');
		if(taikhoan=="null" || !taikhoan){
			alert("Bạn cần đăng nhập");
			return;
		}
		if (!confirm("Bạn có chắc chắn xoá sản phẩm?"))
			return;
		console.log(maCtgh);
		$.ajax(
			{
				url: './cart/del-product/' + maCtgh+"/"+taikhoan,
				method: "post",
				success: function(data) {
					if (data.isvalid) {
						atag.closest('tr').remove();
						alert("Xoá sản phẩm thành công");
						$('#giohang').text(data.hienThiTongTienGH);
						$('#amount').text(data.hienThiTongTienGH);
						return;
					}
					else {
						alert("error");
					}

				},
				failed: function() {

				}
			}
		)
	});
	
	
	$('body').delegate('.update_cart', 'click', function(e) {
		e.preventDefault();
		var atag = $(this);
		var maCtgh = atag.attr('data-id');
		
		var taikhoan = atag.attr('data-name');
		if(taikhoan=="null" || !taikhoan){
			alert("Bạn cần đăng nhập");
			return;
		}
		
		var op = atag.attr('data-value');
		
		var soluong = parseInt($('#'+maCtgh+'_soluong').val());
		if(soluong>1){
			if(op=="minus"){
				soluong-=1;
				$('#'+maCtgh+'_soluong').val(soluong);
			}
		}
		if(soluong>=1){
			if(op=="plus"){
				soluong+=1;
				$('#'+maCtgh+'_soluong').val(soluong);
			}
		}
		
		$.ajax(
			{
				url: './cart/update-product/'+maCtgh+'/' + soluong+'/'+taikhoan,
				method: "post",
				success: function(data) {
					if(data.isvalid){
						$('#giohang').text(data.hienThiTongTienGH);
						$('#'+maCtgh+'_amount').text(data.hienThiThanhTienCTGH);
						$('#amount').text(data.hienThiTongTienGH);
					}

				},
				failed: function() {

				}
			}
		)
	});
	
	
	$('body').delegate('.btnCancel', 'click', function(e) {
		e.preventDefault();
		var atag = $(this);
		var maHd = atag.attr('data-id');
		if (!confirm("Bạn có chắc chắn huỷ đơn hàng " + maHd + "?"))
			return;
		$.ajax(
			{
				url: './history/cancel-invoice/'+maHd,
				method: "post",
				success: function(data) {
					console.log("ok");
					if(data.isvalid){
						
						$('#txtWait_'+maHd+'').html("Đã huỷ").css('color','#cc2121');
						atag.remove();
					}

				},
				failed: function() {

				}
			}
		)
	});
	
	$('body').delegate('.btnReceived', 'click', function(e) {
		e.preventDefault();
		var atag = $(this);
		var maHd = atag.attr('data-id');
		if (!confirm("Xác nhận đã nhận được hàng từ hoá đơn " + maHd + "?"))
			return;
		$.ajax(
			{
				url: './history/received-invoice/'+maHd,
				method: "post",
				success: function(data) {
					console.log("ok");
					if(data.isvalid){
						
						$('#txtDeli_'+maHd+'').html("Đã giao").css('color','#3aeb34');
						atag.remove();
					}

				},
				failed: function() {

				}
			}
		)
	});
	
	
});


