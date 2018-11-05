layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	
	//页面记录的uuid
	var uuid = "";
	var versionu = "";
  
	var keyOne = "";
	var keyTwo = "";
	// 查询函数
	$('.searchBtn').on('click',function(){
		keyOne = $('#searchNameOne').val();
		keyTwo = $('#searchNameTwo').val();
		search(keyOne, keyTwo);
	});
	//重置查询
	$('.searchBtn2').on('click',function(){
		search(keyOne, keyTwo);
	});

	function search(keyOne, keyTwo){
		window.location.href=shipServer+"/om/unit/unitRateList.htm?oneName="+ keyOne + "&twoName="+ keyTwo;
	}
	

	// 新增页面
	$('.insertBtna').on('click',function(){
		uuid = $(this).attr("attr-data");
		$.ajax({
			type:"get",
			url:"/transport/bomb/editUnitRate.vm",
			success:function(data){
				//弹框运行
				layer.open({
					type: 1,
					area: ['500px','450px'],
					btn: ['提交', '返回'],
					btnAlign: 'c' ,//按钮居中
					title:'添加信息（*为必填项）[最终比率为【底数*10】的指数次幂]',
					content: data,
					yes: function(index, layero){
						var reg = new RegExp("^[0-9]{1,10}$"); 
						var oneName = $("#oneName").val();
						var twoName = $("#twoName").val();
						var baseName = $("#baseName").val();
						var power = $("#power").val();
						// 校验
						  if(oneName == "") {
							  layer.alert("参考单位不能为空！");
							  return;
						  }
						  if(twoName == "") {
							  layer.alert("对比单位不能为空！");
							  return;
						  }
						  if(baseName == "") {
							  layer.alert("底数不能为空,且必须为10位以内数字！");
							  return;
						  }
						  if(power == "") {
							  layer.alert("指数不能为空,且必须为10位以内数字！");
							  return;
						  }
						  var params = {};
						  params.type = $("#type").val();
						  params.oneName = oneName;
						  params.twoName = twoName;
						  params.baseName = baseName;
						  params.power = power;
						  $.ajax({
								type: "POST",
								url: shipServer+"/om/unit/saveUnitRate.json",
								data: JSON.stringify(params),
								dataType: "json",
								contentType:"application/json",
								async: false,
								success: function(data) {  
										if(data.status == 0){
											layer.alert('保存成功!', function(index) {
											    layer.close(index);
											    location.reload();
											});
										}else{
											layer.alert("错误:" + data.message, function(index) {
												layer.close(index);
											});
										}
								},
								error: function(data) {
					                alert("错误:" + data.message);
					            }
							});
					},
				btn2: function(index, layero){
					  layer.alert('放弃保存吗!', function(index) {
							    layer.close(index);
							    location.reload();
						  });
						  return false;
				  }
				});    
			form.render();
			}
		});
	});
	
	//删除
	$(".delFn").on('click', function(){
		uuid = $(this).attr("attr-data");
		//弹框询问
		 layer.confirm('确定删除吗？', function(index){
		    	var params = {};
		    	params.uuid = uuid;
		    	
		    	$.ajax({
		    		type: "POST",
		    		url: shipServer+"/om/unit/deleteUnitRate.json",
		    		data: JSON.stringify(params),
		    		dataType: "json",
		    		contentType:"application/json",
		    		async: false,
		    		success: function(data) {
						if(data.status == 0){
							layer.alert('删除成功!', function(index) {
							    layer.close(index);
							    location.reload();
							});
						}else{
							layer.alert("错误:" + data.message, function(index) {
								layer.close(index);
							});
						}
					},
					error: function(data) {
			            alert("错误:" + data.message);
			        }
		    	});
		    });
	});
	
	//修改
	$(".editFn").on('click', function(){
		uuid = $(this).attr("attr-data");
		
		// 请求弹框
		$.ajax({
			type:"get",
			url:"/transport/bomb/editUnitRate.vm",
			success:function(data){
				//弹框运行
				layer.open({
					type: 1,
					area: ['500px','450px'],
					btn: ['提交', '返回'],
					btnAlign: 'c' ,//按钮居中
					title:'修改信息（*为必填项）',
					content: data,
					yes: function(index, layero){
						var reg = new RegExp("^[0-9]{1,10}$"); 
						var oneName = $("#oneName").val();
						var twoName = $("#twoName").val();
						var baseName = $("#baseName").val();
						var power = $("#power").val();
	
						// 校验
						  if(oneName == "") {
							  layer.alert("参考单位不能为空！");
							  return;
						  }
						  if(twoName == "") {
							  layer.alert("对比单位不能为空！");
							  return;
						  }
						  if(baseName == "") {
							  layer.alert("底数不能为空,且必须为10位以内数字！");
							  return;
						  }
						  if(power == "") {
							  layer.alert("指数不能为空,且必须为10位以内数字！");
							  return;
						  }
						  var params = {};
						  params.type = $("#type").val();
						  params.uuid = uuid;
						  params.oneName = oneName;
						  params.twoName = twoName;
						  params.baseName = baseName;
						  params.power = power;
						  params.version = versionu;
						  $.ajax({
								type: "POST",
								url: shipServer+"/om/unit/updateUnitRate.json",
								data: JSON.stringify(params),
								dataType: "json",
								contentType:"application/json",
								async: false,
								success: function(data) {  
										if(data.status == 0){
											layer.alert('保存成功!', function(index) {
											    layer.close(index);
											    location.reload();
											});
										}else{
											layer.alert("错误:" + data.message, function(index) {
												layer.close(index);
											});
										}
								},
								error: function(data) {
					                alert("错误:" + data.message);
					            }
							});
					},
				btn2: function(index, layero){
					  layer.alert('放弃保存吗!', function(index) {
							    layer.close(index);
							    location.reload();
						  });
						  return false;
				  }
				});    
				
				//查询详细
			 	var params = {};
			 	params.uuid = uuid;
	    	
			 	$.ajax({
		    		type: "POST",
		    		url: shipServer+"/om/unit/findUnitRate.json",
		    		data: JSON.stringify(params),
		    		async: false,
		    		contentType:"application/json",
		    		success: function(data) {
						if(data.status == 0){
							var obj ={};
							obj.type=data.datas.type;
							 $.ajax({
									type: "POST",
									url: shipServer+"/om/unit/findAll.json",
									data: JSON.stringify(obj),
									dataType: "json",
									contentType:"application/json",
									success: function(data2) {
										console.log(data2)
										$("#oneName").empty();
										$("#twoName").empty();
						                $("#twoName").append($("<option/>").text("请选择对比单位（必选）").attr("value",""));
											if(data.status == 0){
								                $.each(data2.datas, function(i,item){
								                	$("#type").val(item.type);
								                	if(item.usName == null){
								                		item.usName = "";
								                	}
								                	if(item.isDefaultUnit == "1"){
								                		$("#oneName").val(item.chName);
								                	}
								                    /*$("#twoName").append($("<option/>").text(item.chName).attr("value",item.chName));
								                    $("#twoName").val(item.usName);*/
								                	if(item.chName==data.datas.twoName){
								                		$('#twoName').append('<option value="'+item.chName+'" selected="selected">'+item.chName+'</option>')
								                	}else{
								                		$('#twoName').append('<option value="'+item.chName+'">'+item.chName+'</option>')
								                	}
								                	
								                	
								                	
								                });
								                form.render('select');
											}else{
												layer.alert("错误:" + data.message, function(index) {
													layer.close(index);
												});
											}
									},
									error: function(data) {
						                alert("错误:" + data.message);
						            }
								});
								$("#baseName").val(data.datas.baseName);
								$("#power").val(data.datas.power);
								versionu = data.datas.version;
						}else{
							layer.alert("错误:" + data.message, function(index) {
								layer.close(index);
							});
						}
					},
					error: function(data) {
			            alert("错误:" + data.message);
			        }
			 	});
			form.render();
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

	
});