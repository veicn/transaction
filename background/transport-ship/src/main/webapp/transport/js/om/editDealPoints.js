layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;

 	var paramsBroker = {};
	paramsBroker.valueSetTypes="9;";
	//
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
			            if (broker.type == '9'){ //船型
			            	$("#type").append("<option value='"+broker.value+"'>"+broker.value+"</option>");
			            }
					}
				}
			}
		},
		error: function(data) {
            alert("error:" + data.message);
        }
	});
	$("#tollgle").hide();
	form.on('select(test1)', function (data) {
		$("#tollgle").hide();
        if(data.value == "其它"){
        	$("#tollgle").show();
        }
    });

});