layui.use(['layer', 'form', 'laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	
	//查看船合同
	$('.queryBtn').on('click',function(){
		var uuid = $(this).attr('attr-data');
		window.location.href=shipServer+"/shipPact/shipOwnerShipPactDetailQuery.htm?uuid="+uuid;
	});
	
	//物流跟踪
	$('.track').on('click',function(){
		var uuid = $(this).attr('attr-data');
		//alert('等待页面链接')
		window.open(shipServer+"/page/#/transport?uuid="+uuid);
	});
	
	
	//错误
	function message(mess){
		 layer.alert(mess, {title:$.i18n("001"),btn:$.i18n("002")}, function(index) { 
			 layer.close(index);
		});
	}
	
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
