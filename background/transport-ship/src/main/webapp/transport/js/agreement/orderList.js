layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;

	/*$(function(){
		$('.omit').each(function(){
			$(this).omit(8);
		})
		$('.omit2').each(function(){
			$(this).omit(10);
		})
		$('.omit3').each(function(){
			$(this).omit(12);
		})
	})*/
	
	//拼船
	$('.jointShip').on('click',function(){
		var uuid = $(this).attr("attr-data");
		window.location.href=shipServer+""+uuid;
	});
	//选择船盘
	$('.chooseAsk').on('click',function(){
		var uuid = $(this).attr("attr-data");
		window.location.href=shipServer+""+uuid;
	});
	//查询询盘信息
	$('.queryAsk').on('click',function(){
		var uuid = $(this).attr("attr-data");
		window.location.href=shipServer+""+uuid;
	});
	
	// 生成代理协议
	$('.editBtn').on('click',function(){
		var uuid = $(this).attr("attr-uuid");
		var quantity = $(this).attr("attr-quantity");
		window.location.href=shipServer+"/agreement/agreementDetail.htm?palletUuid="+uuid;
		// 请求弹框
	});
	//查询详情
	$('.queryBtn').on('click',function(){
		var uuid = $(this).attr("attr-data");
		window.location.href=shipServer+"/pallet/seePallet.htm?uuid="+uuid;
	});
	// 查询代理协议
	$('.queryBtna').on('click',function(){
		var uuid = $(this).attr("attr-data");
		window.location.href=shipServer+"/agreement/agreementDetailQuery.htm?palletUuid="+uuid;
		// 请求弹框
	});
	//编辑页面
	$('.editBtn').on('click',function(){
		uuid = $(this).attr("attr-data");
		// 请求弹框
	});

	// 删除函数
	$('.delBtn').on('click',function(){
		uuid = $(this).attr("attr-data");
	    //弹框运行
	    layer.confirm($.i18n("JAVASCRIPT0018"), function(index){
	    	var params = {};
	    	params.uuid = uuid;
	    	
	    	$.ajax({
	    		type: "POST",
	    		url: shipServer+"/pallet/delete.json",
	    		data: JSON.stringify(params),
				dataType: "json",
				contentType:"application/json",
	    		async: false,
	    		success: function(data) {
					if(data.status == 0){
						//layer.alert("删除成功!").style.zIndex = 9999999999;
						layer.alert($.i18n("JAVASCRIPT0019"), function(index) {
						    layer.close(index);
						    location.reload();
						});
					}
				},
				error: function(data) {
		            alert("error:" + $.i18n(data.message));
		        }
	    	});
	    });
	});
});
//跳转页面
$('.insertBtnsd').on('click',function(){
	window.location.href=shipServer+"/noOrderPalletList.htm"
})


