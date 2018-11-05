layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	
	// 回复保存
	$('.saveContent').on('click',function(){
		saveDissentContent(1);
	});
	
	// 请求弹框
	$('.editContent').on('click',function(){
		// 请求弹框 
		$.ajax({
			type:"get",
			url: orderServer+'/bomp/orderFeeDissent/editContent.htm',
			success:function(data) {
				//弹框运行
			 	layer.open({
					  type: 1,
					  area: ['550px','350px'],
					  btn: [$.i18n("orderexecute.code.00153"), $.i18n("orderexecute.code.00154")],
					  btnAlign: 'c' ,//按钮居中
					  title:$.i18n("orderexecute.code.00187"),
					  content: data,
					  yes: function(index, layero){
							if($("#dissentContent").val().trim() == "") {
					    		layer.alert($.i18n("orderexecute.code.00188"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
					    		return;
					    	}
							
						  saveDissentContent(0);
					  },
					  btn2: function(index, layero){
						  layer.close(index);
						  return false;
					  },
					  cancel: function(index, layero){
						  layer.close(index);
						  return false;
					  }
			    });
			}
		})
	});
	
	function saveDissentContent(isPass) {
    	var params = {};
    	params.orderId = $("#orderId").val();
    	params.dissentType = $("#dissentType").val();
    	params.orderStatementId = $("#orderStatementId").val();
    	params.isPass = isPass;
    	if(isPass == 0) {
        	params.dissentContent = $("#dissentContent").val().trim();
    	}
    	
    	$.ajax({
    		type: "POST",
    		url: orderServer + "/buyerCenter/orderFeeDissent/saveDissentContent.json",
    		data: JSON.stringify(params),
    		async: false,
			contentType:"application/json",
    		success: function(data) {
				if(data.status == 0){
					layer.alert($.i18n("orderexecute.code.00169"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")}, function(index) {
						location.reload();
					});
				} else {
					layer.alert($.i18n(data.code,data.params),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
				}
			},
			error: function(data) {
				layer.alert("request error",{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
	        }
    	});
	}
	
	// 返回
	$('.backPageDissent').on('click',function(){
		backPageDissent();
	});	
	
	function backPageDissent() {
		
	}
});		