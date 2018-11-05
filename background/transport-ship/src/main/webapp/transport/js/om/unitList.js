layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	
	
	//页面记录的uuid
	var uuid = "";
	var versionu = "";
  
	function getUrlParam(name) {
		  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
		  var r = window.location.search.substr(1).match(reg); //匹配目标参数
		  if (r != null) return decodeURI(r[2]); return null; //返回参数值
	}
	var xx = getUrlParam('type');
	$(".layui-unselect").val(xx)
	$('.selectData dd').each(function(){
		$(this).removeClass('layui-this')
		if ($(this).attr('lay-value') == xx){
			$(this).addClass('layui-this')
		}
	});
	var key = "";
	
	// 查询函数
	$('.cxtype').on('click',function(){
		key = $('.selectData .layui-this').text();
		if(key == "请选择"){
			key = "";
		}
		search(key);
	}); 
	
	
	
	
	//重置
	$('.searchBtn2').on('click',function(){
		search(key);
	});

	function search(key){
		window.location.href=shipServer+"/om/unit/unitList.htm?type="+ key;
	}
	
	// 新增页面
	$('.insertBtns').on('click',function(){
		uuid = $(this).attr("attr-data");
		$.ajax({
			type:"get",
			url:"/transport/bomb/editUnit.vm",
			success:function(data){
				//弹框运行
				layer.open({
					type: 1,
					area: ['500px','450px'],
					btn: ['提交', '返回'],
					btnAlign: 'c' ,//按钮居中
					title:'添加信息（*为必填项）',
					content: data,
					yes: function(index, layero){
						// 校验
						  if($("#chName").val()=="" || $("#chName").length >= 10) {
							  layer.alert("单位中文名不能为空,且不能超过10位！").style.zIndex = 9999999999;
					  		  return;
					      }
						  if($("#atype").val()=="") {
							  layer.alert("类型不能为空！").style.zIndex = 9999999999;
							  return;
						  }
						  var params = {};
						  params.chName = $("#chName").val();
						  params.usName = $("#usName").val();
						  params.type = $("#atype").val();
						  var isnot = $(".layui-form-radioed span").html();
						  if(isnot == "是"){
							  params.isDefaultUnit = "1";
						  }else{
							  params.isDefaultUnit = "0";
						  }
						  $.ajax({
								type: "POST",
								url: shipServer+"/om/unit/saveUnit.json",
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
							            	$("#atype").append("<option value='"+broker.value+"'>"+broker.value+"</option>");
							            }
									}
								}
							}else{
								layer.alert("错误:" + data.message, function(index) {});
								layer.close(index);
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
	
	//删除
	$(".delFn").on('click', function(){
		uuid = $(this).attr("attr-data");
		//弹框询问
		 layer.confirm('确定删除吗？', function(index){
		    	var params = {};
		    	params.uuid = uuid;
		    	
		    	$.ajax({
		    		type: "POST",
		    		url: shipServer+"/om/unit/deleteUnit.json",
		    		data: JSON.stringify(params),
		    		dataType: "json",
		    		contentType:"application/json",
		    		async: false,
		    		success: function(data) {
						if(data.status == 0){
							//layer.alert("删除成功!").style.zIndex = 9999999999;
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
	

	$('.cxtype').on('click',function(){
		var params = {};
		params.type = $(".untype").val();
		$.ajax({
			type:"post",
			url:shipServer+"/om/unit/unitList.htm",
			data: JSON.stringify(params),
			dataType: "json",
			contentType:"application/json",
			success:function(){
				
			}
			
		});
	});
	
	//修改
	$(".editFn").on('click', function(){
		uuid = $(this).attr("attr-data");
		
		// 请求弹框
		$.ajax({
			type:"get",
			url:"/transport/bomb/editUnit.vm",
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
						// 校验
						  if($("#atype").val()=="") {
							  layer.alert("类型不能为空！").style.zIndex = 9999999999;
							  return;
						  }
						  if($("#chName").val()==""|| $("#chName").length >= 10) {
							  layer.alert("单位中文名不能为空,且不能超过10位！").style.zIndex = 9999999999;
							  return;
						  }
						  var params = {};
						  params.uuid = uuid;
						  params.chName = $("#chName").val();
						  params.usName = $("#usName").val();
						  params.type = $("#atype").val();
						  params.version = versionu;
						  var isnot = $(".layui-form-radioed span").html();
						  if(isnot == "是"){
							  params.isDefaultUnit = "1";
						  }else{
							  params.isDefaultUnit = "0";
						  }
						  $.ajax({
								type: "POST",
								url: shipServer+"/om/unit/updateUnit.json",
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
							            	$("#atype").append("<option value='"+broker.value+"'>"+broker.value+"</option>");
							            }
									}
								}
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
				
				//查询详细
			 	var params = {};
			 	params.uuid = uuid;
	    	
			 	$.ajax({
		    		type: "POST",
		    		url: shipServer+"/om/unit/findUnit.json",
		    		data: JSON.stringify(params),
		    		dataType: "json",
		    		contentType:"application/json",
		    		async: false,
		    		success: function(data) {
						if(data.status == 0){
							$("#chName").val(data.datas.chName);
							$("#usName").val(data.datas.usName);
							$("#atype").val(data.datas.type);
							$("[name='isDefaultUnit'][value="+data.datas.isDefaultUnit+"]").attr("checked", true);
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