layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer;
	
	// 删除函数
	$('.delContract').on('click',function(){
		var contractUuid = $(this).attr("contractUuid");
	    //弹框运行
	    layer.confirm($.i18n("orderexecute.code.00170"),{title:$.i18n("orderexecute.code.00148"),btn:[$.i18n("orderexecute.code.00197"),$.i18n("orderexecute.code.00198")]}, function(index){
	    	var params = {};
	    	params.uid = contractUuid;
	    	$.ajax({
	    		type: "POST",
	    		url: orderServer+"/buyerCenter/contract/delete.htm",
	    		data: params,
	    		async: false,
	    		success: function(data) {
					
					layer.alert($.i18n("orderexecute.code.00171"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")}, function(index) {
					    layer.close(index);
					    location.reload();
					});
				
				},
				error: function(data) {
					layer.alert("request error",{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
		        }
	    	});
	    });
	});
});	