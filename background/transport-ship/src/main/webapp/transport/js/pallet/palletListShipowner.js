layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;

	//页面记录的uuid
	var uuid = "";
  
	// 查询函数
	$('.searchBtn').on('click',function(){
		search();
	});
	
	//回车查询
	$("body").keydown(function() {
        if (event.keyCode == "13") {//keyCode=13是回车键
            $('.searchBtn').click();
        }
    });
	
	//超出的字符隐藏
	$(function(){
		$('.omit').each(function(){
			$(this).omit(20);
		})
	})
/*	// 重置函数
	$('.searchBtn2').on('click',function(){
		window.location.href=shipServer+"/pallet/palletList.htm";
	}); */
	function search(){
		var searchLoadPort = $('#searchLoadPort').val();
		var searchUnLoadPort = $('#searchUnLoadPort').val();
		var searchStatus = $('#searchStatus').val();
		window.location.href=shipServer+"/pallet/palletListShipowner.htm?loadPort="+ searchLoadPort+'&'+"unloadPort="+searchUnLoadPort+'&'+"status="+searchStatus;
	}
	//查询需求页面
	$('.queryBtn').on('click',function(){
		uuid = $(this).attr("attr-data");
		window.location.href=shipServer+"/pallet/seePallet.htm?uuid="+uuid;
	});
	//查询报盘
	$('.queryClause').on('click',function(){
		uuid = $(this).attr("attr-data");
	  window.location.href=shipServer+"/clause/clauseDetails.htm?palletUuid="+uuid+"&roleFlag=2";
	});
	//发送报盘
	$('.sendClause').on('click',function(){
		uuid = $(this).attr("attr-data");
		window.location.href=shipServer+"/shipPlate/sendClauseShipPlateList.htm?palletUuid="+uuid;
	});
	//编辑页面
	$('.editBtn').on('click',function(){
		var uuid = $(this).attr("attr-data");
			window.location.href=shipServer+"/pallet/palletEdit.htm?palletUuid="+uuid;
	});
	// 关闭函数
	$('.delBtn').on('click',function(){
		uuid = $(this).attr("attr-data");
	    //弹框运行
		layer.confirm($.i18n("JAVASCRIPT00153"),{title:$.i18n("001"),btn:[$.i18n("002"),$.i18n("003")]},function(index){
	    	var params = {};
	    	params.uuid = uuid;
	    	$.ajax({
	    		type: "POST",
	    		url: shipServer+"/pallet/updateStatus.json",
	    		data: JSON.stringify(params),
	    		dataType:"json",
	    		contentType:"application/json",
	    		async: false,
	    		success: function(data) {
					if(data.status == 0){
						layer.alert($.i18n("CONSTANTS008"), function(index) {
						    layer.close(index);
						    location.reload();
						});
					}else {
							layer.alert($.i18n(data.message), function(index) {
							    layer.close(index);
							});
					}
				},
				error: function(data) {
					layer.alert($.i18n("JAVASCRIPT003"), function(index) {
					    layer.close(index);
					});
		        }
	    	});
	    });
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
	//获取地址栏参数
	function getQueryString(name) {
	    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	    var r = window.location.search.substr(1).match(reg);
	    if ( r != null ){
	       return decodeURI(r[2]);
	    }else{
	       return null;
	    } 
	 }
});

//跳转页面
$('.insertBtns').on('click',function(){
	window.location.href=shipServer+"/palletList.htm"
})
$('.insertBtnsd').on('click',function(){
	window.location.href=shipServer+"/palletQueryList.htm"
})


