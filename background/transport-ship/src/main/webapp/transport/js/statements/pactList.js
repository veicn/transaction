layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;

	// 查询函数
	$('.chaxun').on('click',function(){
		search16();
	}); 
	
	function search16(){
		var receiptCode = $('#receiptCode').val();
		var pactCode = $('#pactCode').val();
		console.log(receiptCode);
		console.log(pactCode);
		window.location.href= shipServer + "/statements/pactList.htm?receiptCode=" + receiptCode+'&'+ "pactCode=" + pactCode;
	};
	// 查看结算数据
	$('.check').on('click',function(){
		var that = this;
		checked(that);
	}); 
	function checked(that){
		var uuid = $(that).parents('.s-mod-bcont').find('#uuid').val();
		window.location.href= shipServer + "/statements/tofindPactStatementsByUuid.htm?uuid="+uuid;
	}
});