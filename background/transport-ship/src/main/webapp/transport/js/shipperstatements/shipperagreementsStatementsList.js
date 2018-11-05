layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	
	// 查询函数
	$('.searchBtn').on('click',function(){
		search();
	}); 
	function search(){
		var receiptCode = $('#receiptCode').val();
		var agreementCode = $('#agreementCode').val();
		window.location.href=shipServer+"/shipperstatements/shipperagreementsStatementsList.htm?receiptCode="+ receiptCode+'&'+"agreementCode="+agreementCode;
	}

	// 查看结算数据
	$('.check').on('click',function(){
		var that = this;
		checked(that);
	}); 
	function checked(that){
		var uuid = $(that).parents('.s-mod-bcont').find('#uuid').val();
		window.location.href= shipServer + "/shipperstatements/toshipperagreementsStatementsByUuid.htm?uuid="+uuid;
	}
	
})


