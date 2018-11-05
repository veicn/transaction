layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	own = layui.own,
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	var uuid = "";
	var palletUuid = own.getHref('palletUuid');
	
	//超出的字符隐藏
	$(function(){
		$('.omit').each(function(){
			$(this).omit(20);
		})
	})
	//指定二船东
	$('#appoint').on('click',function(){
		var params = {};
		params.uuid = palletUuid;
		layer.confirm($.i18n("JAVASCRIPT00222")+'?',{title:$.i18n("001"),btn:[$.i18n("002"),$.i18n("003")]}, function(index){
			$.ajax({
	    		type: "POST",
	    		url: shipServer+"/pallet/appointTradertrader.json",
	    		data: JSON.stringify(params),
	    		dataType:"json",
	    		contentType:"application/json",
	    		success: function(data) {
					if(data.status == 0){
						layer.alert($.i18n("JAVASCRIPT00214"),{title:$.i18n("001"),btn:$.i18n("002")},function(index) {
							layer.close(index);
							window.location.href = shipServer + "/pallet/palletQueryList.htm";
						});
					} else {
						message($.i18n(data.message));
					}
				},
				error: function(data) {
					message($.i18n("JAVASCRIPT003"));
		        }
	    	});
		});
	});
	
	//更多船盘
	$('.more').on('click',function(){
	  window.location.href=shipServer+"/shipPlate/moreShipPlate.htm?palletUuid="+palletUuid;
	});
	
	
	//查看船盘详情
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
	
	/*
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
	//获取地址栏参数
	function getQueryString(name) {
	    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	    var r = window.location.search.substr(1).match(reg);
	    if ( r != null ){
	       return decodeURI(r[2]);
	    }else{
	       return null;
	    } 
	 }*/

	//错误信息
	function message(mess){
		layer.alert(mess,{title:$.i18n("001"),btn:$.i18n("002")},function(index) {
			layer.close(index);
		});
	}	
});


