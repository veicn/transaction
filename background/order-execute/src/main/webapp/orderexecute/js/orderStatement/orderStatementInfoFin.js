layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form, // 载入form表单
	table = layui.table; // 表格
	window.$ = layui.jquery; // zai'lu'
	
	if($('#isEdit').val()!= '3'){
		$('#statementFinForm').find('input').attr('readonly','readonly');
	}else{
		var laydate = layui.laydate;	
	 	//执行一个laydate实例
	  	laydate.render({
	    	elem: '#billDate' //指定元素
	    		,lang: 'en'
	  	});	

	  	laydate.render({
	    	elem: '#paymentDate' //指定元素
	    		,lang: 'en'
	  	});	
	  	laydate.render({
	    	elem: '#checkTime', //指定元素
	    	lang: 'en',
	    	type: 'datetime'
	  	});	
	}
	
	/* //自定义验证规则  
	  form.verify({ 
			title: function(value){
			  if(value.length > 18){
					return '数字总长度不能超过18位';
			  }
			},
			numberV:function(value){
				if(value.indexOf(".") >= 0){
					var array = [];
					array = value.split(".");
					if(array[0].length > 12 || array[1].length > 8){
						return '数字点前数字长度不能超过12位和小数点后长度不能超过8位';
					}
				}
			},
			email: [/^[a-z0-9._%-]+@([a-z0-9-]+\.)+[a-z]{2,4}$|^1[3|4|5|7|8]\d{9}$/, '邮箱格式不对']  
		})*/
	    
	  //监听提交  
	  form.on('submit(saveFn)', function(data){ 
		//去除千分位
		$("#billWeightT").val(delNumFormat($("#billWeightT").val()));//
		$("#billWeightBbl").val(delNumFormat($("#billWeightBbl").val()));//
		$("#settlementQuantityBbl").val(delNumFormat($("#settlementQuantityBbl").val()));//
		$("#contractQuantity").val(delNumFormat($("#contractQuantity").val()));//
		$("#settlementPrice").val(delNumFormat($("#settlementPrice").val()));//
		$("#goodsAmount").val(delNumFormat($("#goodsAmount").val()));//
		$("#totalFee").val(delNumFormat($("#totalFee").val()));//
		$("#totalAmount").val(delNumFormat($("#totalAmount").val()));//
		$("#settledAmount").val(delNumFormat($("#settledAmount").val()));//
		
		$.ajax({
    		type: "POST",
    		url: orderServer + "/orderStatement/saveOrderStatementPre.json",
    		data: $('#statementFinForm').serialize(),
    		success: function(data) {
				if(data.status == 0){
					layer.alert($.i18n("orderexecute.code.00169"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")}, function(index) {
					window.location.href = orderServer+"/sellerCenter/orderStatement/infoFin.htm"
						+ "?uuid=" + $('#uuid').val();
					});
				}else{
					layer.alert("error:" + $.i18n(data.code,data.params),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
				}
			},
			error: function(data) {
	            alert("request error");
	        }
    	});	 
	    return false;  
	  }); 
	
	
	
	/*
	$('.saveFn').on('click', function(){
		
	 	$.ajax({
    		type: "POST",
    		url: orderServer+"/orderStatement/saveOrderStatementPre.json",
    		data: $('#statementPreForm').serialize(),
    		success: function(data) {
				if(data.status == 0){
					alert("成功");
					window.location.href = orderServer+"/sellerCenter/orderStatement/infoPre.htm"
						+ "?orderId=" + $('#orderId').val();
				}else{
					 alert("error:" + data.message);
				}
			},
			error: function(data) {
	            alert("error:" + data.message);
	        }
    	});
	});*/
	
	$('.comfirmFn').on('click', function(){
		var param = {}
		param.orderStatementId=$('#orderStatementId').val();
	 	$.ajax({
    		type: "POST",
    		url: orderServer+"/orderStatement/statementComfirm.json",
    		data: JSON.stringify(param),
			contentType:"application/json",
    		success: function(data) {
				if(data.status == 0){
					layer.alert($.i18n("orderexecute.code.00169"), function(index) {
					window.location.href = orderServer+"/sellerCenter/orderStatement/infoFin.htm"
						+ "?uuid=" + $('#uuid').val();
					});
				}else{
					layer.alert("error:" + $.i18n(data.code,data.params),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
				}
			},
			error: function(data) {
	            alert("request error");
	        }
    	});
	});
	//
	form.on('submit(formId)', function(data){
		var params =data.field;
		submit($, params);
		return false;
	});
	
	
	
	// 修改对账单
	$('.updateFn').on('click',function(){
		window.location.href = orderServer+"/sellerCenter/orderStatement/infoFin.htm"
		+ "?orderStatementUuid=" + $('#orderStatementUuid').val()
		+ "&isAgain=" + '1'
	 });
	//重开
	$('.againFn').on('click',function(){
		window.location.href = orderServer+"/sellerCenter/orderStatement/infoFin.htm"
		+ "?orderStatementUuid=" + $('#orderStatementUuid').val()
		+ "&isAgain=" + '2'
	 });
	
	// 撤销
	$('.repeal').on('click',function(){
		var orderStatementIdValue = $('#orderStatementId').val();
		$.ajax({
			type:"post",
			url:orderServer+'/orderStatement/infoPreEditStatus.json',
			data:{
				orderStatementId:orderStatementIdValue,
			},
			success:function(data){
				location.reload(); 
			}
		})
		return false;
	});
});	