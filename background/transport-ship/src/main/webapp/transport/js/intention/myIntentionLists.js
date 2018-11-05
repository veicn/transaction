layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	own = layui.own,
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	
	
	//页面记录的uuid
	var uuid = "";
	
	//操作之后，返回跳转的页面
	var fromPage = own.getHref('fromPage');
	
	
	//查看详情
	//roleType：1船东/经纪人，2货主，3转租船东
	var status="";
	$('.lookBtn').on('click',function(){	//点击详情，连续返回，须带fromPage
		uuid = $(this).attr("attr-data");
		status = $(this).attr("statusData");
		if(status=='4' || status=='3'){	
			window.location.href=shipServer+"/intention/intentionDetail2.htm?uuid="+uuid+"&roleType=2&from=/intention/myIntentionLists.htm&fromPage="+fromPage+"&status="+status;
		}else if(status=='1'){
			window.location.href=shipServer+"/intention/intentionDetail3.htm?uuid="+uuid+"&roleType=2&from=/intention/myIntentionLists.htm&fromPage="+fromPage;
		}else if(status=='2'){
			window.location.href=shipServer+"/intention/intentionDetail4.htm?uuid="+uuid+"&roleType=2&from=/intention/myIntentionLists.htm&fromPage="+fromPage;
		}
	});
	
	
	// 终止，修改status为已关闭
	$('.terminationBtn').on('click',function(){
		uuid = $(this).attr("attr-data");
	    //弹框运行
	    layer.confirm($.i18n("JAVASCRIPT0045"), {title:$.i18n("001"),btn:[$.i18n("002"),$.i18n("003")]},function(index){
	    	var params = {};
	    	params.uuid = uuid;
	    	
	    	$.ajax({
	    		type: "POST",
	    		url: shipServer+"/intention/updateIntentionStatus.json",
	    		data: JSON.stringify(params),
				dataType: "json",
				contentType:"application/json",
	    		async: false,
	    		success: function(data) {
					if(data.status == 0){
						layer.alert($.i18n("CONSTANTS011"), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
						    layer.close(index);
						    location.reload();
						});
					}
				},
				error: function(data) {
					message($.i18n(data.message));
		        }
	    	});
	    });
	});
	
	
	//查看租船协议
	$('.lookAgreementBtn').on('click',function(){
		uuid = $(this).attr("attr-data");
		window.location.href=shipServer+"";
	});
	
	
	//返回
	$('.returnBtn').on('click',function(){
		window.location.href=shipServer+fromPage;
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



//错误信息
function message(mess){
	layer.alert(mess, {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
		layer.close(index);
	});
}