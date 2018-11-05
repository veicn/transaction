layui.use(['layer', 'form', 'jquery','laypage', 'table', 'own','laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	own = layui.own,
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	
	//----------------船盘管理（转租船东）
	
	
	//页面记录的uuid
	var uuid = "";
	var status=own.getHref('status');
	if(status==false){
		status="";
	}
	
	
	//首次加载，样式赋值
	if(status!=null && status!=""){
		$("a[attr-data='"+status+"']").parent().attr('class','curr');
	}else{
		$("a[attr-data='1']").parent().attr('class','curr');
	}
	
	
	//已发布、洽谈中、确认租船按钮
	$('.statusBtn').on('click',function(){
		status = $(this).attr("attr-data");
		window.location.href=shipServer+"/shipPlate/shipAgentShipPlateList.htm?status="+status;
	});
	
  
	// 查询
	$('.searchBtn').on('click',function(){
		search();
	}); 
	
	
	//页面加载数据
	function search(){
		var searchName = $('#searchName').val();
		searchName = $.trim(searchName);
		window.location.href=shipServer+"/shipPlate/shipAgentShipPlateList.htm?name="+ encodeURI(searchName)+"&status="+status;
	}
	//条件回显
	var searchData = getQueryString('name');
	if (searchData != null && searchData !=''){
		while(searchData.indexOf("+")>-1){
			searchData=searchData.replace("+"," ");
		}
		$('#searchName').val($.trim(searchData));
	}
	
	
	//洽谈列表
	$('.talkingListBtn').on('click',function(){
		uuid = $(this).attr("attr-data");
		window.location.href=shipServer+"/intention/talkingList.htm?shipPlateUuid="+uuid+"&from=/shipPlate/shipAgentShipPlateList.htm";
	});
	
	
	// 租船确认单
	$('.shipConfirmBtn').on('click',function(){
		uuid = $(this).attr("attr-data");
		// 跳转到租船确认单页面（roleType：1船东/经纪人，2货主，3代理，用于显示”对家信息“）
		window.location.href=shipServer+"/intention/confirmBill.htm?shipPlateUuid="+uuid+"&roleType=3&from=/shipPlate/shipAgentShipPlateList.htm";
	});
	
	
	// 新规页面
	$('.insertBtn').on('click',function(){
		uuid = "";
		window.location.href=shipServer+"/shipPlate/shipAgentEditShipPlate.htm?uuid="+uuid;
		return;
		
		// 请求弹框
		$.ajax({
			type:"get",
			url:"../transport/bomb/editShipPlate.vm",
			success:function(data) {
				//弹框运行
			 	layer.open({
					  type: 1,
					  area: ['600px','650px'],
					  btn: [$.i18n("JAVASCRIPT0071"), $.i18n("JAVASCRIPT0025")],
					  btnAlign: 'c' ,//按钮居中
					  title:$.i18n("JAVASCRIPT00149"),
					  content: data,
					  yes: function(index, layero){
						  // 校验
						  if($("#name").val()=="") {
							  layer.alert($.i18n("JAVASCRIPT0089"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
							  return;
						  }
						  if($("#date1").val()=="") {
							  layer.alert($.i18n("JAVASCRIPT00150"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
							  return;
						  }
						  
						  var params = {};
						  params.sysShipUuid = $("#name").val();
						  params.name = $("#name option:checked").text();
						  params.type = $("#type").val();
						  params.completeDate = $("#completeDate").val();
						  
						  if ($("#owner").val() != '') {
							  params.shipOwner = $("#owner option:checked").text();
						  } else {
							  params.shipOwner = "";
						  }
						  
						  params.loadQuantity = $("#loadQuantity").val();
						  params.position = $("#position").val();
						  
						  if ($("#eta").val() != '') {
							  params.eta = $("#date1").val();
						  }
						  
						  params.brokerId = $("#broker option:checked").val();
						  
						  if ($("#broker").val() != '') {
							  params.brokerName = $("#broker option:checked").text();
						  } else {
							  params.brokerName = "";
						  }
						  
						  params.remark = $("#remark").val();
						  
						  $.ajax({
							type: "POST",
							url: shipServer+"/shipPlate/saveShipPlate.json",
							data: JSON.stringify(params),
							dataType: "json",
							contentType:"application/json",
							async: false,
							success: function(data) {  
									if(data.status == 0){
										layer.alert($.i18n("JAVASCRIPT00151"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
										    layer.close(index);
										    location.reload();
										});
									}
							},
							error: function(data) {
				                layer.alert($.i18n(data.message), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
				            		layer.close(index);
				            	});
				            }
						});
					  },
					  btn2: function(index, layero){
						  layer.alert($.i18n("JAVASCRIPT0083"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
								    layer.close(index);
								    location.reload();
							  });
							return false;
					  }
			    });
				
			 	// 查询船名列表
				var paramsShip = {};
			 	$.ajax({
		    		type: "POST",
		    		url: shipServer+"/sysShip/findSysShipList.json",
		    		data: JSON.stringify(paramsShip),
					dataType: "json",
					contentType:"application/json",
		    		async: false,
		    		success: function(data) {
						if(data.status == 0){
							var ship_arr = data.datas;
							for(var i = 0; i < ship_arr.length; i++){  
					            var ship = ship_arr[i];
					            $("#name").append("<option value='"+ship.uuid+"'>"+ship.name+"</option>");
							}
						}
					},
					error: function(data) {
						layer.alert($.i18n(data.message), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
							layer.close(index);
						});
			        }
			 	});
			 	
			 	// 查询经纪人列表
				var paramsBroker = {};
				paramsBroker.valueSetTypes="1;8;";
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
						            if (broker.type == '1'){ // 经纪人
						            	$("#broker").append("<option value='"+broker.code+"'>"+broker.value+"</option>");
						            }
						            if (broker.type == '8'){ // 船东
						            	$("#owner").append("<option value='"+broker.code+"'>"+broker.value+"</option>");
						            }
								}
							}
						}
					},
					error: function(data) {
						layer.alert($.i18n(data.message), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
							layer.close(index);
						});
			        }
			 	});
			 	form.render();
			}
		})
	});
  
	//编辑页面
	$('.editBtn').on('click',function(){
		uuid = $(this).attr("attr-data");
		window.location.href=shipServer+"/shipPlate/shipAgentEditShipPlate.htm?uuid="+uuid;
		return;
		
		// 请求弹框
		$.ajax({
		  type:"POST",
		  url:"../transport/bomb/editShipPlate.vm",
		  success:function(data) {
			//弹框运行
		 	layer.open({
				  type: 1,
				  area: ['600px','650px'],
				  btn: [$.i18n("JAVASCRIPT0071"), $.i18n("JAVASCRIPT0025")],
				  btnAlign: 'c' ,//按钮居中
				  title:$.i18n("JAVASCRIPT00152"),
				  content: data,
				  yes: function(index, layero){
					  // 校验
					  if($("#name").val()=="") {
						  layer.alert($.i18n("JAVASCRIPT0089"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
						  return;
					  }
					  if($("#date1").val()=="") {
						  layer.alert($.i18n("JAVASCRIPT00150"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
						  return;
					  }
					  if($("#date2").val()=="") {
						  layer.alert($.i18n("JAVASCRIPT00150"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
						  return;
					  }
					  
					  // 数值正则
					  var reg = new RegExp("^[0-9]+.?[0-9]*$");
					  var loadQuantity = $("#loadQuantity").val();
					  
					  if (loadQuantity!="" && !reg.test(loadQuantity)) {
						  layer.alert($.i18n("JAVASCRIPT0094"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
						  return;
					  }

					  var params = {};
					  params.uuid = uuid;
					  params.sysShipUuid = $("#name option:checked").val();
					  params.name = $("#name option:checked").text();
					  params.type = $("#type").val();
					  params.completeDate = $("#completeDate").val();
					  
					  if ($("#owner").val() != '') {
						  params.shipOwner = $("#owner option:checked").text();
					  } else {
						  params.shipOwner = "";
					  }
					  
					  params.loadQuantity = $("#loadQuantity").val();
					  params.position = $("#position").val();
					  
					  if ($("#eta").val() != '') {
						  params.eta = $("#date1").val();
					  }
					  
					  params.brokerId = $("#broker option:checked").val();
					  
					  if ($("#broker").val() != '') {
						  params.brokerName = $("#broker option:checked").text();
					  } else {
						  params.brokerName = "";
					  }
					  
					  params.remark = $("#remark").val();
					  
					  $.ajax({
						type: "POST",
						url: shipServer+"/shipPlate/updateShipPlate.json",
						data: JSON.stringify(params),
						dataType: "json",
						contentType:"application/json",
						async: false,
						success: function(data) {
								if(data.status == 0){
									//layer.alert("船盘保存成功!").style.zIndex = 9999999999;
									layer.alert($.i18n(data.message), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
									    layer.close(index);
									    location.reload();
									});
								}
						},
						error: function(data) {
							layer.alert($.i18n(data.message), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
								layer.close(index);
							});
			            }
					});
				  },
				  btn2: function(index, layero){
					  layer.alert($.i18n("JAVASCRIPT00227"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
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
	    		url: shipServer+"/shipPlate/findShipPlateDetail.json",
	    		data: JSON.stringify(params),
				dataType: "json",
				contentType:"application/json",
	    		async: false,
	    		success: function(data) {
					if(data.status == 0){
						var completeDateTimeStamp = data.datas.completeDate;
						var completeDate = new Date(completeDateTimeStamp).Format("yyyy");
						var etaTimeStamp = data.datas.eta;
						var etaCabindaTimeStamp = data.datas.etaCabinda;
						var etaDate = new Date(etaTimeStamp).Format("yyyy-MM-dd hh:mm:ss");
						var etaCabindaDate = new Date(etaCabindaTimeStamp).Format("yyyy-MM-dd hh:mm:ss");
						var name = data.datas.name;
						var brokerName = data.datas.brokerName;
						var shipOwner = data.datas.shipOwner;
						$("#type").val(data.datas.type);
						$("#completeDate").val(completeDate);
						$("#loadQuantity").val(formatNumber(data.datas.loadQuantity,3,1));
						$("#position").val(data.datas.position);
						$("#date1").val(etaDate);
						$("#date2").val(etaCabindaDate);
						
						// 查询船名列表
						var paramsShip = {};
					 	$.ajax({
				    		type: "POST",
				    		url: shipServer+"/sysShip/findSysShipList.json",
				    		data: JSON.stringify(paramsShip),
							dataType: "json",
							contentType:"application/json",
				    		async: false,
				    		success: function(data) {
								if(data.status == 0){
									var ship_arr = data.datas;
									for(var i = 0; i < ship_arr.length; i++){  
							            var ship = ship_arr[i];
							            $("#name").append("<option value='"+ship.uuid+"'>"+ship.name+"</option>");
									}
								}
							},
							error: function(data) {
								layer.alert($.i18n(data.message), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
									layer.close(index);
								});
					        }
					 	});
					 	
					 	// 船名赋初始值
					 	$("#name option").each(function(){
				 		    if($(this).text() == name){
				 		    $(this).attr("selected",true);
				 		    }
					 	});
					 	
						// 查询经纪人列表
					 	var paramsBroker = {};
						paramsBroker.valueSetTypes="1;8;";
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
								            if (broker.type == '1'){ // 经纪人
								            	$("#broker").append("<option value='"+broker.code+"'>"+broker.value+"</option>");
								            }
								            if (broker.type == '8'){ // 船东
								            	$("#owner").append("<option value='"+broker.code+"'>"+broker.value+"</option>");
								            }
										}
									}
								}
							},
							error: function(data) {
								layer.alert($.i18n(data.message), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
									layer.close(index);
								});
					        }
					 	});
					 	
					 	// 经纪人赋初始值
					 	$("#broker option").each(function(){
				 		    if($(this).text() == brokerName){
				 		    $(this).attr("selected",true);
				 		    }
					 	});
					 	
					 	// 船东赋初始值
					 	$("#owner option").each(function(){
				 		    if($(this).text() == shipOwner){
				 		    $(this).attr("selected",true);
				 		    }
					 	});
						
						$("#remark").val(data.datas.remark);
					}
				},
				error: function(data) {
					layer.alert($.i18n(data.message), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
						layer.close(index);
					});
		        }
		 	});
		 	
		 	form.render();
		  }
		})
	});
	
	// 查看页面
	$('.lookBtn').on('click',function(){
		uuid = $(this).attr("attr-data");
		window.location.href=shipServer+"/shipPlate/shipAgentLookShipPlate.htm?uuid="+uuid;
		
		return;
		// 请求弹框
		$.ajax({
		  type:"POST",
		  url:"../transport/bomb/editShipPlate.vm",
		  success:function(data) {
			//弹框运行
		 	layer.open({
				  type: 1,
				  area: ['600px','650px'],
				  btn: [$.i18n("JAVASCRIPT0071"), $.i18n("JAVASCRIPT0025")],
				  btnAlign: 'c' ,//按钮居中
				  title:$.i18n("JAVASCRIPT00152"),
				  content: data,
				  yes: function(index, layero){
					  // 校验
					  if($("#name").val()=="") {
						  layer.alert($.i18n("JAVASCRIPT0089"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
						  return;
					  }
					  if($("#date1").val()=="") {
						  layer.alert($.i18n("JAVASCRIPT00150"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
						  return;
					  }
					  
					  // 数值正则
					  var reg = new RegExp("^[0-9]+.?[0-9]*$");
					  var loadQuantity = $("#loadQuantity").val();
					  
					  if (loadQuantity!="" && !reg.test(loadQuantity)) {
						  layer.alert($.i18n("JAVASCRIPT0094"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
						  return;
					  }

					  var params = {};
					  params.uuid = uuid;
					  params.sysShipUuid = $("#name option:checked").val();
					  params.name = $("#name option:checked").text();
					  params.type = $("#type").val();
					  params.completeDate = $("#completeDate").val();
					  params.leaveFlag = $("#leaveFlag option:checked").text();
					  
					  if ($("#owner").val() != '') {
						  params.shipOwner = $("#owner option:checked").text();
					  } else {
						  params.shipOwner = "";
					  }
					  
					  params.loadQuantity = $("#loadQuantity").val();
					  params.position = $("#position").val();
					  
					  if ($("#eta").val() != '') {
						  params.eta = $("#date1").val();
					  }
					  
					  params.brokerId = $("#broker option:checked").val();
					  
					  if ($("#broker").val() != '') {
						  params.brokerName = $("#broker option:checked").text();
					  } else {
						  params.brokerName = "";
					  }
					  
					  params.remark = $("#remark").val();
					  
					  $.ajax({
						type: "POST",
						url: shipServer+"/shipPlate/updateShipPlate.json",
						data: JSON.stringify(params),
						dataType: "json",
						contentType:"application/json",
						async: false,
						success: function(data) {
								if(data.status == 0){
									layer.alert($.i18n(data.message), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
									    layer.close(index);
									    location.reload();
									});
								}
						},
						error: function(data) {
							layer.alert($.i18n(data.message), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
								layer.close(index);
							});
			            }
					});
				  },
				  btn2: function(index, layero){
					  layer.alert($.i18n("JAVASCRIPT0083"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
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
	    		url: shipServer+"/shipPlate/findShipPlateDetail.json",
	    		data: JSON.stringify(params),
				dataType: "json",
				contentType:"application/json",
	    		async: false,
	    		success: function(data) {
					if(data.status == 0){
						var completeDateTimeStamp = data.datas.completeDate;
						var completeDate = new Date(completeDateTimeStamp).Format("yyyy");
						var etaTimeStamp = data.datas.eta;
						var etaDate = new Date(etaTimeStamp).Format("yyyy-MM-dd hh:mm:ss");
						var name = data.datas.name;
						var brokerName = data.datas.brokerName;
						var shipOwner = data.datas.shipOwner;
						$("#type").val(data.datas.type);
						$("#completeDate").val(completeDate);
						$("#loadQuantity").val(formatNumber(data.datas.loadQuantity,3,1));
						$("#position").val(data.datas.position);
						$("#date1").val(etaDate);
						
						// 查询船名列表
						var paramsShip = {};
					 	$.ajax({
				    		type: "POST",
				    		url: shipServer+"/sysShip/findSysShipList.json",
				    		data: JSON.stringify(paramsShip),
							dataType: "json",
							contentType:"application/json",
				    		async: false,
				    		success: function(data) {
								if(data.status == 0){
									var ship_arr = data.datas;
									for(var i = 0; i < ship_arr.length; i++){  
							            var ship = ship_arr[i];
							            $("#name").append("<option value='"+ship.uuid+"'>"+ship.name+"</option>");
									}
								}
							},
							error: function(data) {
								layer.alert($.i18n(data.message), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
									layer.close(index);
								});
					        }
					 	});
					 	
					 	// 船名赋初始值
					 	$("#name option").each(function(){
				 		    if($(this).text() == name){
				 		    $(this).attr("selected",true);
				 		    }
					 	});
					 	
						// 查询经纪人列表
					 	var paramsBroker = {};
						paramsBroker.valueSetTypes="1;8;";
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
								            if (broker.type == '1'){ // 经纪人
								            	$("#broker").append("<option value='"+broker.code+"'>"+broker.value+"</option>");
								            }
								            if (broker.type == '8'){ // 船东
								            	$("#owner").append("<option value='"+broker.code+"'>"+broker.value+"</option>");
								            }
										}
									}
								}
							},
							error: function(data) {
								layer.alert($.i18n(data.message), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
									layer.close(index);
								});
					        }
					 	});
					 	
					 	// 经纪人赋初始值
					 	$("#broker option").each(function(){
				 		    if($(this).text() == brokerName){
				 		    $(this).attr("selected",true);
				 		    }
					 	});
					 	
					 	// 船东赋初始值
					 	$("#owner option").each(function(){
				 		    if($(this).text() == shipOwner){
				 		    $(this).attr("selected",true);
				 		    }
					 	});
						
						$("#remark").val(data.datas.remark);
					}
				},
				error: function(data) {
					layer.alert($.i18n(data.message), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
						layer.close(index);
					});
		        }
		 	});
		 	
		 	form.render();
		  }
		})
	});
	
	
	//导出模板
	$('.exportBtn').on('click',function(){
		var obj={};
		obj.shipPactUuid=uuid;
		obj.type='5';	//导出类型（1船在途）（2配载计划）（3装港信息）（4卸港信息）（5船盘信息）
		$.ajax({
			type: "POST",
			url: shipServer+"/exportShipPlateTemple.json",
			data: JSON.stringify(obj),
			dataType: "json",
			contentType:"application/json",
			success: function(data) {
				if(data.status == 0){
					window.location.href=data.datas;
				} else {
					message($.i18n(data.message));
				}
			},
			error:function(){
			   message($.i18n("JAVASCRIPT003"));
			}
		});
	});

	//导入数据
	var loadIndex;
	var uploaderObj = new plupload.Uploader({ //实例化一个plupload上传对象
		browse_button : 'importBtn',
		url : shipServer+'/importShipPlateData.json',
		headers:{'Authorization':""},
		flash_swf_url : 'js/Moxie.swf',
		silverlight_xap_url : 'js/Moxie.xap',
		multi_selection:false,
		//multipart_params: {'uuid':uuid }
	});
	uploaderObj.init(); //初始化
	//开始上传
	uploaderObj.bind('FilesAdded', function(up, files) {
		loadIndex = layer.load(0, {shade: false});
		uploaderObj.start(); 
	});
	//上传完成
	uploaderObj.bind('FileUploaded',function(uploader,file){
		$.ajaxFn({
			type : "POST",
			url : shipServer+"/import/returnFlag.json",
			data:{},
			success : function(data) {
				layer.close(loadIndex);
				if(data.status == 0){
					layer.alert($.i18n(data.datas), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
						layer.close(index);
						location.reload();
					});
				}else{
					message($.i18n(data.message));
				}
			},
			error:function(data){
				layer.close(loadIndex);
				layer.alert($.i18n("JAVASCRIPT0027"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
					layer.close(index);
					location.reload();
				});
			}
		});
	});
	
	
	//撤销，走删除函数
	$('.delBtn').on('click',function(){
		uuid = $(this).attr("attr-data");
		var statusStr = $(this).attr("attr-status");
		
		var alertMsg="";
		if(statusStr=="1"){
			alertMsg=$.i18n("JAVASCRIPT00153");
		}else if(statusStr=="2"){
			alertMsg=$.i18n("JAVASCRIPT00216");
		}
		
	    //弹框运行
	    layer.confirm(alertMsg,{title:$.i18n("001"),btn:[$.i18n("002"),$.i18n("003")]}, function(index){
	    	var params = {};
	    	params.uuid = uuid;
	    	
	    	$.ajax({
	    		type: "POST",
	    		url: shipServer+"/shipPlate/delShipPlate.json",
	    		data: JSON.stringify(params),
				dataType: "json",
				contentType:"application/json",
	    		async: false,
	    		success: function(data) {
					if(data.status == 0){
						layer.alert($.i18n("CONSTANTS008"), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
						    layer.close(index);
						    location.reload();
						});
					}
				},
				error: function(data) {
					layer.alert($.i18n(data.message), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
						layer.close(index);
					});
		        }
	    	});
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
	
	function testFindShipPlateList() {
		var params = {};
		$.ajax({
			type: "POST",
			url: shipServer+"/shipPlate/findShipPlateList.json",
			data: JSON.stringify(params),
			dataType: "json",
			contentType:"application/json",
			async: false,
			success: function(data) {  
					if(data.status == 0){
						layer.alert(JSON.stringify(data.datas), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
							layer.close(index);
						});
					}
			},
			error: function(data) {
				layer.alert($.i18n(data.message), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
					layer.close(index);
				});
            }
		});
	}
	
	//获取地址栏参数
	function getQueryString(name) {
	    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	    var r = window.location.search.substr(1).match(reg);
	    if ( r != null ){
	       return decodeURI(r[2]);
	    }else{
	       return null;
	    } 
	 }

});
