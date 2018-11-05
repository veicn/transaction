layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	own = layui.own,
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	
	//页面记录的uuid
	var uuid = "";
	
	//货盘uuid
	var palletUuid=own.getHref('palletUuid');
	
	
	//船盘详情
	$('.lookBtn').on('click',function(){
		uuid = $(this).attr("attr-data");
		window.location.href=shipServer+"/shipPlate/lookShipPlate.htm?uuid="+uuid;
	});
	
	
	//发送报盘
	$('.sendClauseBtn').on('click',function(){
		//船盘uuid
		uuid = $(this).attr("attr-data");
		window.location.href=shipServer+"/clause/addClause.htm?palletUuid="+palletUuid+"&shipPlateUuid="+uuid;
	});
	
	
	//日期格式化函数
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
