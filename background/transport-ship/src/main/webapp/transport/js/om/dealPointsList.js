layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	
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

	
	
	//页面记录的uuid
	var uuid = "";
	var versionu = "";
	
	var regionSh = "";
	// 查询函数
	$('.searchBtn').on('click',function(){
		regionSh = $('#regionName').val();
		search(regionSh);
	});
	//重置查询
	$('.searchBtn2').on('click',function(){
		search(regionSh);
	});

	function search(regionSh){
		
		window.location.href="/om/dealPoints/dealPointsList.htm?region="+ regionSh;
	}
	
	//新增
	$('.insertBtn').on('click', function (){
		uuid = $(this).attr("attr-data");
		$.ajax({
			type:"get",
			url:shipServer+"/transport/bomb/editDealPoints.vm",
			success:function(data){
				//弹框运行
				layer.open({
					type: 1,
					area: ['600px','650px'],
					btn: ['提交', '返回'],
					btnAlign: 'c' ,//按钮居中
					title:'添加信息（*为必填项）',
					content: data,
					yes: function(index, layero){
						  // 校验
						  var reg = new RegExp("^[0-9]{1,10}$");
						  var region = $("#region").val();
						  var price = $("#price").val();
						  var name = $("#name").val();
						  var singleFare = $("#singleFare").val();
						  var refRoute = $("#refRoute").val();
						
						  var params = {};
						  params.region = region;
						  params.type = $("#type").val();
						  params.price = price;
						  params.name = name;
						  params.singleFare = singleFare;
						  params.refRoute = refRoute;
						  params.date = $("#date").val();
						  params.remark = $("#remark").val();
						  
						  if(region == "" || region.length > 10) {
							  layer.alert("地区必填 10个字符以内").style.zIndex = 9999999999;
					  		  return;
					      }
						  if($("#type").val()=="") {
							  layer.alert("船型必选").style.zIndex = 9999999999;
					  		  return;
					      }
						  if($("#type").val()=="其它"){
							  if($("#typeq").val() == "" || $("#typeq").val().length > 10){
								  layer.alert("自定义船型必填,且10个字符以内").style.zIndex = 9999999999;
							  }
							  params.type = $("#typeq").val();
						  }
						  if(price == "" || !reg.test(price)) {
							  layer.alert("ws点必填 10位数以内").style.zIndex = 9999999999;
					  		  return;
					      }
						  if(name == "" || name.length > 10) {
							  layer.alert("名称必填").style.zIndex = 9999999999;
					  		  return;
					      }
						  if(refRoute == "" || refRoute.length > 255) {
							  layer.alert("参考路线限制长度255个字符以内").style.zIndex = 9999999999;
					  		  return;
					      }
						  if(price == "" || !reg.test(price)) {
							  layer.alert("价格必填 10位数以内").style.zIndex = 9999999999;
					  		  return;
					      }
						  if($("#date").val()=="") {
							  layer.alert("日期必填").style.zIndex = 9999999999;
					  		  return;
					      }
						  if($("#remark").length > 50){
							  layer.alert("描述内容过长！").style.zIndex = 9999999999;
					  		  return;
						  }
						  $.ajax({
								type: "POST",
								url: shipServer+"/om/dealPoints/saveDealPointsIn.json",
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
		    		url: shipServer+"/om/dealPoints/deleteDealPoints.json",
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
			url:shipServer+"/transport/bomb/editDealPoints.vm",
			success:function(data){
				//弹框运行
				layer.open({
					type: 1,
					area: ['600px','680px'],
					btn: ['提交', '返回'],
					btnAlign: 'c' ,//按钮居中
					title:'修改信息（*为必填项）',
					content: data,
					yes: function(index, layero){
						  // 校验
						var reg = new RegExp("^[0-9]{1,10}$");
						var region = $("#region").val();
						var price = $("#price").val();
						var name = $("#name").val();
						  var singleFare = $("#singleFare").val();
						  var refRoute = $("#refRoute").val();
						
						  var params = {};
						  params.uuid = uuid;
						  params.region = region;
						  params.type = $("#type").val();
						  params.price = price;
						  params.name = name;
						  params.singleFare = singleFare;
						  params.refRoute = refRoute;
						  params.date = $("#date").val();
						  params.remark = $("#remark").val();
						  params.version = versionu;
						  
						  if(region == "" || region.length > 10) {
							  layer.alert("地区必填 10个字符以内").style.zIndex = 9999999999;
					  		  return;
					      }
						  if($("#type").val()=="") {
							  layer.alert("船型必选").style.zIndex = 9999999999;
					  		  return;
					      }
						  if($("#type").val()=="其它"){
							  if($("#typeq").val() == "" || $("#typeq").val().length > 10){
								  layer.alert("自定义船型必填,且10个字符以内").style.zIndex = 9999999999;
							  }
							  params.type = $("#typeq").val();
						  }
						  if(price == "" || !reg.test(price)) {
							  layer.alert("ws点必填 10位数以内").style.zIndex = 9999999999;
					  		  return;
					      }
						  if(name == "" || name.length > 10) {
							  layer.alert("名称必填").style.zIndex = 9999999999;
					  		  return;
					      }
						  if(refRoute == "" || refRoute.length > 255) {
							  layer.alert("参考路线限制长度255个字符以内").style.zIndex = 9999999999;
					  		  return;
					      }
						  if(price == "" || !reg.test(price)) {
							  layer.alert("价格必填 10位数以内").style.zIndex = 9999999999;
					  		  return;
					      }
						  if($("#date").val()=="") {
							  layer.alert("日期必填").style.zIndex = 9999999999;
					  		  return;
					      }
						  if($("#remark").length > 50){
							  layer.alert("描述内容过长！").style.zIndex = 9999999999;
					  		  return;
						  }
						 
						  $.ajax({
								type: "POST",
								url: shipServer+"/om/dealPoints/updateDealPoints.json",
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
		    		url: shipServer+"/om/dealPoints/getDealPoints.json",
		    		data: JSON.stringify(params),
					dataType: "json",
					contentType:"application/json",
		    		async: false,
		    		success: function(data) {
						if(data.status == 0){
							

							uuid = data.datas.uuid;
							$("#region").val(data.datas.region);
							$("#price").val(data.datas.price);
							$("#name").val(data.datas.name);
							//下拉框回显
							var flag =false;
							$("[name='type'] option").each(function () {
								if($(this).val()==data.datas.type){  
							    	$(this).attr("selected","selected"); 
							    	flag=true;
							    }                         
							});
							if (!flag){
								$("[name='type'] option").each(function () {
									if($(this).val()=='其它'){  
								    	$(this).attr("selected","selected"); 
								    }                         
								});
						    	$("#tollgle").show();
						    	$("#typeq").val(data.datas.type);
							}
							$("#singleFare").val(data.datas.singleFare);
							$("#refRoute").val(data.datas.refRoute);
							var timestamp = data.datas.date;
							var completeDate = new Date(timestamp).Format("yyyy-MM-dd");
							$("#date").val(completeDate);
							$("#remark").val(data.datas.remark);
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
	
	
});