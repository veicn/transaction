layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	own = layui.own,
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	
	
	//页面记录的uuid
	var uuid = "";
	
	var shipPlateUuid = own.getHref('shipPlateUuid');
	

	//查看询盘列表
	$('.intentionListBtn').click(function(){
		window.location.href=shipServer+"/intention/talkingList.htm?shipPlateUuid="+shipPlateUuid;
	});
	
	//查看详情
	$('.lookBtn').on('click',function(){	
		uuid = $(this).attr("attr-data");
		window.location.href=shipServer+"/clause/findClauseDetails.htm?clauseUuid="+uuid+"&roleFlag=2&shipPlateUuid="+shipPlateUuid;
	});
	
	//返回
	$('.returnBtn').click(function(){
		window.location.href=shipServer+"/shipPlate/shipAgentShipPlateList.htm?status=2";
	});
	
});
