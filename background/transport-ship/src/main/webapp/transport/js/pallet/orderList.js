layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	var own = layui.own;
	//页面记录的uuid
	var uuid = "";
  
	// 查询函数
	$('.searchBtn').on('click',function(){
		search();
	}); 
	// 重置函数
	$('.searchBtn2').on('click',function(){
		window.location.href=shipServer+"/pallet/buyPalletList.htm";
	}); 
	function search(){
		var searchOrderCode = $('#searchOrderCode').val();
		var searchOilName = $('#searchOilName').val();
		window.location.href=shipServer+"/pallet/buyPalletList.htm?oilName="+ searchOilName+'&'+"orderCode="+searchOrderCode;
		
	}
	var orderCode = getQueryString('orderCode');
	if (orderCode != null && orderCode !=''){
		$('#searchOrderCode').val(orderCode);
	}
	var oilName = getQueryString('oilName');
	if (oilName != null && oilName !=''){
		$('#searchOilName').val(oilName);
	}

	//我要租船页面
	$('.orderShipBtn').on('click',function(){
		var orderNo = encodeURIComponent($(this).attr("attr-data"));
		var quantity = encodeURIComponent($(this).attr("attr-quantity"));
		var loadPort = encodeURIComponent($(this).attr("attr-loadPort"));
		var unloadPort = encodeURIComponent($(this).attr("attr-unloadPort"));
		var beg = encodeURIComponent($(this).attr("attr-beg"));
		var end = encodeURIComponent($(this).attr("attr-end"));
		var unitName = encodeURIComponent($(this).attr("attr-unitName"));
		var oilType = encodeURIComponent($(this).attr("attr-oilType"));
		var unloadPortCode = encodeURIComponent($(this).attr("attr-unloadPortCode"));
		var loadPortCode = encodeURIComponent($(this).attr("attr-loadPortCode"));
		var oilTypeCode = encodeURIComponent($(this).attr("attr-oilTypeCode"));
		var palletType = own.getHref('palletType');
		if (!palletType){
			message($.i18n("ITSH533"));
			return false;
		}
		var obj = {};
		obj.orderCode = orderNo;
		$.ajax({
			type: "POST",
			url: shipServer+"/agreement/checkAgreement.json",
			data: JSON.stringify(obj),
			dataType: "json",
			contentType:"application/json",
			success: function(data) {
				if(data.status == 0){
					window.location.href=shipServer+"/pallet/palletEdit.htm?type=true&orderCode="+orderNo+"&palletType="+palletType+"&quantity="+quantity+"&loadPort="+loadPort+"&unloadPort="+unloadPort+"&beg="+beg+"&end="+end+"&unitName="+unitName+"&oilType="+oilType+"&loadPortCode="+loadPortCode+"&unloadPortCode="+unloadPortCode+"&oilTypeCode="+oilTypeCode;
				} else {
				  message($.i18n(data.message));
				}
			},
		   error:function(){
			   message($.i18n("JAVASCRIPT003"));
		   	}
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
	//错误信息
	function message(mess){
		layer.alert(mess,{title:$.i18n("001"),btn:$.i18n("002")},function(index) {
			layer.close(index);
		});
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




