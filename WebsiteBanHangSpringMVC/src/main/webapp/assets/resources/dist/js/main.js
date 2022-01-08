$(document).ready(function() {

	$('body').delegate('.btnDelProduct', 'click', function(e) {
		e.preventDefault();
		var atag = $(this);
		var maDt = atag.attr('data-id');
		var tenDt = atag.attr('data-name');

		if (!confirm("Bạn có chắc chắn xoá sản phẩm " + tenDt + "?"))
			return;
		$.ajax(
			{
				url: './products/delete-product/' + maDt,
				method: "post",
				success: function(data) {
					console.log(data);
					if(data.tontaima=="true"){
						alert("Hiện tại không thể xoá vì khách hàng đã mua điện thoại trong giỏ hàng");
						return;
					}else if(data.tontaima=="false"){
						if (data.isvalid=="true") {
							atag.closest('tr').remove();
							alert("Xoá thành công");
							return;
						}
						else {
							alert("Xoá không thành công");
							return;
						}
					}
					

				},
				failed: function() {

				}
			}
		)
	});


	$('body').delegate('.btnDelManufactor', 'click', function(e) {
		e.preventDefault();
		var atag = $(this);
		console.log("ok");
		var maNsx = atag.attr('data-id');
		var tenNsx = atag.attr('data-name');

		if (!confirm("Bạn có chắc chắn xoá sản phẩm " + tenNsx + "?"))
			return;
		$.ajax(
			{
				url: './manufactores/delete-manufactor/' + maNsx,
				method: "post",
				success: function(data) {
					if(data.tontaima=="true"){
						alert("Hiện tại không thể xoá vì có điện thoại thuộc nhà sản xuất này!");
						return;
					}else{
						if (data.isvalid) {
							atag.closest('tr').remove();
							alert("Xoá thành công");
							return;
						}
						else {
							alert("Xoá không thành công");
						}
					}
				},
				failed: function() {

				}
			}
		)
	});
	
	$('body').delegate('.btnCancelOrder', 'click', function(e) {
		e.preventDefault();
		var atag = $(this);
		var maHd = atag.attr('data-id');
		var btnConfirm = $('#btnConfirm_'+maHd+'');
		if (!confirm("Bạn có chắc chắn huỷ đơn hàng " + maHd + "?"))
			return;
		$.ajax(
			{
				url: './orders/cancel-order/'+maHd,
				method: "post",
				success: function(data) {
					console.log("ok");
					if(data.isvalid){
						
						$('#txtWait_'+maHd+'').html("Đã huỷ").css('color','red');
						atag.remove();
						btnConfirm.remove();
					}

				},
				failed: function() {

				}
			}
		)
	});
	
	$('body').delegate('.btnConfirmOrder', 'click', function(e) {
		e.preventDefault();
		var atag = $(this);
		var maHd = atag.attr('data-id');
		var btnCancel = $('#btnCancel_'+maHd+'');
		if (!confirm("Xác nhận đơn hàng " + maHd + "?"))
			return;
		$.ajax(
			{
				url: './orders/confirm-order/'+maHd,
				method: "post",
				success: function(data) {
					console.log("ok");
					if(data.isvalid){
						
						$('#txtWait_'+maHd+'').html("Đang giao").css('color','yellow');
						atag.remove();
						btnCancel.remove();
					}

				},
				failed: function() {

				}
			}
		)
	});
	
	$('body').delegate('.btnReceivedOrder', 'click', function(e) {
		e.preventDefault();
		var atag = $(this);
		var maHd = atag.attr('data-id');
		if (!confirm("Xác nhận đã nhận được hàng từ hoá đơn " + maHd + "?"))
			return;
		$.ajax(
			{
				url: './orders/received-order/'+maHd,
				method: "post",
				success: function(data) {
					console.log("ok");
					if(data.isvalid){
						
						$('#txtDeli_'+maHd+'').html("Đã giao").css('color','green');
						atag.remove();
					}

				},
				failed: function() {

				}
			}
		)
	});


})

