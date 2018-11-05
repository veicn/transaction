layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;	

  	//执行一个laydate实例
  	laydate.render({
    	elem: '#receiveTime' //指定元素
    		,lang: 'en'
  	});	
  	
	// 保存
	$('.saveReceive').on('click',function(){
		
		var isPass = $(this).attr("isPass");
		var receiveAmount = delNumFormat($("#receiveAmount").val().trim());
		var receiveTime = $("#receiveTime").val();
		var payee = $("#payee").val().trim();
	
    	if(receiveAmount == "") {
    		layer.alert($.i18n("orderexecute.code.00139"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
    		return;
    	} else {
    		// 数值正则
    		var reg = new RegExp("^[0-9]+.?[0-9]*$");  
    		if (receiveAmount!="" && !reg.test(receiveAmount)) {
    		    layer.alert($.i18n("orderexecute.code.00140"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
    		    return;
    		} else {
    			var arr = receiveAmount.split(".");
    			if(arr[0].length > 10) {
        		    layer.alert($.i18n("orderexecute.code.00141"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
        		    return;
    			}
    			
    			if(arr.length  == 2) {
    				if(arr[1].length == 0) {
            		    layer.alert($.i18n("orderexecute.code.00142"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
            		    return;
    				}
        			if(arr[1].length > 8) {
            		    layer.alert($.i18n("orderexecute.code.00143"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
            		    return;
        			}
    			}
    		}
    	}
    	if(receiveTime == "") {
    		layer.alert($.i18n("orderexecute.code.00144"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
    		return;
    	}
    	if(payee == "") {
    		layer.alert($.i18n("orderexecute.code.00145"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
    		return;
    	}
    	
    	var params = {};
    	params.id = $("#orderId").val();
    	params.receiveAmount = receiveAmount;
    	params.receiveTime = new Date(receiveTime);
    	params.payee = payee;
    	
    	$.ajax({
    		type: "POST",
    		url: orderServer+"/sellerCenter/order/savePayConfirm.json",
    		data: JSON.stringify(params),
    		async: false,
			contentType:"application/json",
    		success: function(data) {
				if(data.status == 0){
					layer.alert($.i18n("orderexecute.code.00146"), function(index) {
						back();
					});
				} else {
					layer.alert($.i18n(data.code,data.params));
				}
			},
			error: function(data) {
	            layer.alert("request error");
	        }
    	});
	});
	
	// 返回
	$('.backBtn').on('click',function(){
		back();
	});	
	
	function back() {
		// LIST  DETAIL
		if($("#formPage").val() == "LIST") {
			window.location.href = orderServer + "/sellerCenter/order/list.htm";
		} else {
			window.location.href = orderServer + "/sellerCenter/order/detail.htm?uid=" + $("#uuid").val();
		}
	}
	
});		