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
			alert("B???n c???n ????ng nh???p");
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
						alert("S???n ph???m ???? c?? trong gi??? h??ng");
						return;
					}else{
						if(data.notenough=="true"){
							alert("S??? l?????ng trong kho kh??ng ?????");
							return;
						}else{
							if (data.isvalid) {
								alert("Th??m s???n ph???m th??nh c??ng");
								return;
							}
							else {
								alert("error");
								return;
							}
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
			alert("B???n c???n ????ng nh???p");
			return;
		}
		if (!confirm("B???n c?? ch???c ch???n xo?? s???n ph???m?"))
			return;
		console.log(maCtgh);
		$.ajax(
			{
				url: './cart/del-product/' + maCtgh+"/"+taikhoan,
				method: "post",
				success: function(data) {
					if (data.isvalid) {
						atag.closest('tr').remove();
						alert("Xo?? s???n ph???m th??nh c??ng");
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
			alert("B???n c???n ????ng nh???p");
			return;
		}
		
		var op = atag.attr('data-value');
		
		var soluong = parseInt($('#'+maCtgh+'_soluong').val());
		if(soluong>1){
			if(op=="minus"){
				soluong-=1;
				//$('#'+maCtgh+'_soluong').val(soluong);
			}
		}
		if(soluong>=1){
			if(op=="plus"){
				soluong+=1;
				//$('#'+maCtgh+'_soluong').val(soluong);
			}
		}
		
		$.ajax(
			{
				url: './cart/update-product/'+maCtgh+'/' + soluong+'/'+taikhoan,
				method: "post",
				success: function(data) {
					if(data.notenough){
						alert("S??? l?????ng t???n kho kh??ng ?????");
					}
					if(data.isvalid){
						$('#giohang').text(data.hienThiTongTienGH);
						$('#'+maCtgh+'_amount').text(data.hienThiThanhTienCTGH);
						$('#amount').text(data.hienThiTongTienGH);
						$('#'+maCtgh+'_soluong').val(soluong);
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
		if (!confirm("B???n c?? ch???c ch???n hu??? ????n h??ng " + maHd + "?"))
			return;
		$.ajax(
			{
				url: './history/cancel-invoice/'+maHd,
				method: "post",
				success: function(data) {
					console.log("ok");
					if(data.isvalid){
						
						$('#txtWait_'+maHd+'').html("???? hu???").css('color','#cc2121');
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
		if (!confirm("X??c nh???n ???? nh???n ???????c h??ng t??? ho?? ????n " + maHd + "?"))
			return;
		$.ajax(
			{
				url: './history/received-invoice/'+maHd,
				method: "post",
				success: function(data) {
					console.log("ok");
					if(data.isvalid){
						
						$('#txtDeli_'+maHd+'').html("???? giao").css('color','#3aeb34');
						atag.remove();
					}

				},
				failed: function() {

				}
			}
		)
	});
});


