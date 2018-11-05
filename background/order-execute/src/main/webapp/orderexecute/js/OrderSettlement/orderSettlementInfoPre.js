layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form, // 载入form表单
	table = layui.table; // 表格
	window.$ = layui.jquery; // 
	
	var laydate = layui.laydate;	
 	//执行一个laydate实例

	//添加
	$('.saveFn').on('click', function(){
	 	$.ajax({
    		type: "POST",
    		url: orderServer + "/orderSettlement/saveOrderSettlementPre.json",
    		data: $('#settlementPreForm').serialize(),
    		success: function(data) {
    			
				if(data.status == 0){
					layer.alert($.i18n("orderexecute.code.00146"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")}, function(index) {
						window.location.href = orderServer+"/sellerCenter/orderSettlement/infoPre.htm"
						+ "?uuid=" + $('#uuid').val();
					});
					
					
				}else{
					 alert("error:" + $.i18n(data.code,data.params));
				}
			},
			error: function(data) {
	            alert("request error");
	        }
    	});
	});
	
	function back(){
		
	}

});	