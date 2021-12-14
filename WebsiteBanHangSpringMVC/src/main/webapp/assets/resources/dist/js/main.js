$('body').delegate('.btnDelProduct', 'click', function(e) {
	e.preventDefault();
	var atag = $(this);
	var maDt = atag.attr('data-id');
	var tenDt = atag.attr('data-name');

	if (!confirm("Bạn có chắc chắn xoá sản phẩm "+tenDt+"?"))
		return;
	$.ajax(
		{
			url: './products/delete-product/' + maDt,
			method: "post",
			success: function(data) {
				if (data.isvalid) {
					atag.closest('tr').remove();
					alert("Xoá thành công");
					return;
				}
				else{
					alert("Xoá không thành công");
				}
				
			},
			failed: function() {

			}
		}
	)
});
