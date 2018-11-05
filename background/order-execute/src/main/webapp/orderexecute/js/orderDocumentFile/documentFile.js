layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer;
	
	// 查询函数
	$('.searchBtn').on('click',function(){
		//search();
	});
	
	function search(){
		var orderNo = $('#orderNo').val();
		var contractNo = $('#contractNo').val();
		var documentType = $('#documentType').val();
		window.location.href = orderServer+"/buyerCenter/orderDocumentFile/documentFileList.htm"
			+ "?orderNo=" + orderNo
			+ "&contractNo=" + contractNo
			+ "&documentType=" + documentType;
	}
	
	
});	