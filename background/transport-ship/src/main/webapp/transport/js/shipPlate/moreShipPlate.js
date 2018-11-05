layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	own = layui.own,
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	var uuid = "";
	
	//页面记录的uuid
	var palletUuid = own.getHref('palletUuid');
	// 查询
	$('#palletUuid').val(palletUuid);
	
	// 查询
	$('.searchBtn').on('click',function(){
		search();
	}); 
	function search(){
		var name = $('#searchName').val();
		window.location.href=shipServer+"/shipPlate/moreShipPlate.htm?palletUuid="+ palletUuid+'&'+"name="+name;
	}
	//船盘详情
	$('.queryBtn').on('click',function(){
		uuid = $(this).attr("attr-data");
	  window.location.href=shipServer+"/shipPlate/lookShipPlate.htm?uuid="+uuid;
	});
	//询盘
	$('.askBtn').on('click',function(){
		//船盘uuid
		uuid = $(this).attr("attr-data");
		//palletUuid
		window.location.href=shipServer+"/intention/saveIntention.htm?shipPlateUuid="+uuid+"&palletUuid="+palletUuid;
	});
	//询盘详情
	$('.details').on('click',function(){
		//船盘uuid
		uuid = $(this).attr("attr-data");
		status = $(this).attr("attr-status");
		if(status=='4' || status=='3'){	
			window.location.href=shipServer+"/intention/intentionDetail2.htm?uuid="+uuid+"&roleType=1&from=/shipPlate/moreShipPlate.htm&status="+status;
		}else if(status=='1'){
			window.location.href=shipServer+"/intention/intentionDetail3.htm?uuid="+uuid+"&roleType=1&from=/shipPlate/moreShipPlate.htm"
		}else if(status=='2'){
			window.location.href=shipServer+"/intention/intentionDetail4.htm?uuid="+uuid+"&roleType=1&from=/shipPlate/moreShipPlate.htm"
		}
	});
});
