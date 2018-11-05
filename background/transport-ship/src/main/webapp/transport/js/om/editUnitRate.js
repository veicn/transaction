layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	
	var paramsBroker = {};
	paramsBroker.valueSetTypes="14;";
	//取值集单位类型
	$.ajax({
		type: "POST",
		url: shipServer+"/shipPact/queryValueSet.json",
		data: JSON.stringify(paramsBroker),
		dataType: "json",
		contentType:"application/json",
		async: false,
		success: function(data) {  
				if(data.status == 0){
					var broker_arr = data.datas;
					if (broker_arr != null && broker_arr.length > 0){
						for(var i =0; i<broker_arr.length; i++){
				            var broker = broker_arr[i];
				            if (broker.type == '14'){ //单位类型
				            	$("#type").append("<option value='"+broker.value+"'>"+broker.value+"</option>");
				            }
						}
					}
				}else{
					layer.alert("错误:" + data.message, function(index) {});
				}
		},
		error: function(data) {
            alert("错误:" + data.message);
        }
	});
	
	form.on('select(province)', function(data){
		var obj2 = {};
		obj2.type=$("#type").val();
		aj(obj2);
	});
	
	function aj(obj2){
		
	
	$.ajax({
		type: "POST",
		url: shipServer+"/om/unit/findAll.json",
		data: JSON.stringify(obj2),
		dataType: "json",
		contentType:"application/json",
		success: function(data2) {
			console.log(data2)
			$("#oneName").empty();
			$("#twoName").empty();
            $("#twoName").append($("<option/>").text("请选择对比单位（必选）").attr("value",""));
				if(data2.status == 0){
	                $.each(data2.datas, function(i,item){
	                	if(item.isDefaultUnit == "1"){
	                		$("#oneName").val(item.chName);
	                	}
	                	if(item.chName==data2.datas.twoName){
	                		$('#twoName').append('<option value="'+item.chName+'" selected="selected">'+item.chName+'</option>')
	                	}else{
	                		$('#twoName').append('<option value="'+item.chName+'">'+item.chName+'</option>')
	                	}
	                	
	                	
	                	
	                });
	                form.render('select');
				}
		},
		error: function(data2) {
            alert("错误:" + data2.message);
        }
	});
	}
	
});