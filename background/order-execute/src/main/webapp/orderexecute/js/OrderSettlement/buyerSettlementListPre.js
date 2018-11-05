layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer;
	var laydate = layui.laydate;	
 	//执行一个laydate实例
  	laydate.render({
    	elem: '#checkTimeDesc' //指定元素
    	,range: true
  	});
  	 
	// 查看函数
	$('.showFn').on('click',function(){
		var orderSettlementUuid = $(this).attr("orderSettlementUuid");
		var orderUuid = $(this).attr("orderUuid");
		window.location.href = orderServer+"/buyerCenter/orderSettlement/infoPre.htm"
		+ "?orderSettlementUuid=" + orderSettlementUuid;
	});
	
});	