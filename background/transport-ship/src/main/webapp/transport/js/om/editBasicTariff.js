layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;

 	var paramsBroker = {};
	paramsBroker.valueSetTypes="4;5;15;";
	//
	$.ajax({
		type: "POST",
		url: shipServer+"/shipPact/queryValueSetEn.json",
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
			            if (broker.type == '4'){ //卸港
			            	$("#unloadPort1").append("<option value='"+broker.value+"'>"+broker.value+"</option>");
			            	$("#unloadPort2").append("<option value='"+broker.value+"'>"+broker.value+"</option>");
			            	$("#unloadPort3").append("<option value='"+broker.value+"'>"+broker.value+"</option>");
			            }
			            if (broker.type == '5'){ //装港
			            	$("#loadPort1").append("<option value='"+broker.value+"'>"+broker.value+"</option>");
			            	$("#loadPort2").append("<option value='"+broker.value+"'>"+broker.value+"</option>");
			            	$("#loadPort3").append("<option value='"+broker.value+"'>"+broker.value+"</option>");
			            }
			            if (broker.type == '15'){ //年份
			            	$("#year").append("<option value='"+broker.value+"'>"+broker.value+"</option>");
			            }
					}
				}
			}
		},
		error: function(data) {
            alert("错误:" + data.message);
        }
	});
	
	$("#toto1").hide();
	form.on('select(test1)', function (data) {
		$("#toto1").hide();
        if(data.value == "其它"){
        	$("#toto1").show();
        }
    });
	$("#toto2").hide();
	form.on('select(test2)', function (data) {
		$("#toto2").hide();
        if(data.value == "其它"){
        	$("#toto2").show();
        }
    });
	$("#toto3").hide();
	form.on('select(test3)', function (data) {
		$("#toto3").hide();
        if(data.value == "其它"){
        	$("#toto3").show();
        }
    });
	$("#toto4").hide();
	form.on('select(test4)', function (data) {
		$("#toto4").hide();
        if(data.value == "其它"){
        	$("#toto4").show();
        }
    });
	$("#toto5").hide();
	form.on('select(test5)', function (data) {
		$("#toto5").hide();
        if(data.value == "其它"){
        	$("#toto5").show();
        }
    });
	$("#toto6").hide();
	form.on('select(test6)', function (data) {
		$("#toto6").hide();
        if(data.value == "其它"){
        	$("#toto6").show();
        }
    });
	
 
});