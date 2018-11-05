layui.use(['layer', 'form', 'jquery','laypage', 'upload','table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	//撤销协议
	$('.delBtn').on('click',function(){
		var uuid = $(this).attr("attr-data");
	    //弹框运行
	    layer.confirm($.i18n("JAVASCRIPT00153"), function(index){
	    	var params = {};
	    	params.uuid = uuid;
	    	$.ajax({
	    		type: "POST",
	    		url: shipServer+"/agreement/revokeAgreement.json",
	    		data: JSON.stringify(params),
				dataType: "json",
				contentType:"application/json",
	    		async: false,
	    		success: function(data) {
					if(data.status == 0){
						layer.alert($.i18n("JAVASCRIPT0021"), function(index) { layer.close(index);
							location.reload();
						});
					} else {
						layer.alert($.i18n(data.message), function(index) { layer.close(index);});
					}
				},
				error: function(data) {
					layer.alert($.i18n("JAVASCRIPT003"), function(index) { layer.close(index);});
		        }
	    	});
	    });
	});
	//删除协议
	$('.delsBtn').on('click',function(){
		var uuid = $(this).attr("attr-data");
		//弹框运行
		layer.confirm($.i18n("JAVASCRIPT0018"), function(index){
			var params = {};
			params.uuid = uuid;
			$.ajax({
				type: "POST",
				url: shipServer+"/agreement/deleteAgreement.json",
				data: JSON.stringify(params),
				dataType: "json",
				contentType:"application/json",
				success: function(data) {
					if(data.status == 0){
						layer.alert($.i18n("JAVASCRIPT0021"), function(index) {layer.close(index);
							 location.reload();
						});
					} else {
						layer.alert($.i18n(data.message), function(index) {layer.close(index);});
					}
				},
				error: function(data) {
					layer.alert($.i18n("JAVASCRIPT003"), function(index) { layer.close(index);});
				}
			});
		});
	});
	//查询页面
	$('.queryBtn').on('click',function(){
		var uuid = $(this).attr("attr-data");
		window.location.href=shipServer+"/agreement/agreementDetailQuery.htm?uuid="+uuid;
	});
	
	// 修改代理协议
	$('.editBtn').on('click',function(){
		var uuid = $(this).attr("attr-data");
		var palletUuid =  $(this).attr("attr-palletUuid");
		window.location.href=shipServer+"/agreement/agreementDetail.htm?uuid="+uuid+"&palletUuid="+palletUuid;
  });
	// 日期格式化函数
	Date.prototype.Format = function (fmt) {
	    var o = {
	        "M+": this.getMonth() + 1, 						//月份 
	        "d+": this.getDate(), 							//日 
	        "h+": this.getHours(), 							//小时 
	        "m+": this.getMinutes(), 						//分 
	        "s+": this.getSeconds(), 						//秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), 	//季度 
	        "S": this.getMilliseconds() 					//毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	}
});
