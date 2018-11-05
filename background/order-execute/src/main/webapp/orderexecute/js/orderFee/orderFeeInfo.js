layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;	
	
  	//执行一个laydate实例
  	laydate.render({
    	elem: '#billDate' //指定元素
    		,lang: 'en'
  	});	
	
});  	
  	
	var feeItemList = new Array();	
	// 提交函数
	function submitData(){

    	$.ajax({
    		type: "POST",
    		url: orderServer+"/orderFee/saveOrderFee.json",
    		data: $('#orderFeeForm').serialize().replace(/%2C/g,""),
    		success: function(data) {
				if(data.status == 0){
					layer.alert($.i18n("orderexecute.code.00169"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")}, function(index) {
					window.location.href = orderServer+"/sellerCenter/order/list.htm";
					});
				}else{
					layer.alert("error:" + $.i18n(data.code,data.params),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
				}
			},
			error: function(data) {
				layer.alert("request error",{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
	        }
    	});
	}
	
