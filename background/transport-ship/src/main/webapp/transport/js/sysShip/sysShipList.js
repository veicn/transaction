layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate','upload'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	own = layui.own,
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;

	// 页面记录的uuid
	var uuid = "";
	
	// 查询函数
	$('.searchBtn').on('click',function(){
		search();
	}); 

	function search(){
		var searchName = $('#searchName').val();
		window.location.href=shipServer+"/sysShip/sysShipList.htm?name="+ encodeURI(searchName);
	}
	
	
	//导出模板
	$('.exportBtn').on('click',function(){
		var obj={};
		obj.type='7';	//（1船在途）（2配载计划）（3装港信息）（4卸港信息）（5船盘信息）（6船舶信息：船东/经纪人）（7船舶信息：平台）
		$.ajax({
			type: "POST",
			url: shipServer+"/exportSysShipTemple.json",
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
	
	
	//批量导入船舶
	var loadIndex;
	var uploaderObj = new plupload.Uploader({ //实例化一个plupload上传对象
		browse_button : 'batchImportBtn',
		url : shipServer+'/importSysShipData.json',
		headers:{'Authorization':""},
		flash_swf_url : 'js/Moxie.swf',
		silverlight_xap_url : 'js/Moxie.xap',
		multi_selection:false,
		multipart_params: {'type':"7"}	//7船舶信息：平台
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
					layer.alert($.i18n(data.datas), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
						layer.close(index);
						location.reload();
					});
				}else{
					message($.i18n(data.message));
				}
			},
			error:function(data){
				layer.close(loadIndex);
				layer.alert($.i18n("JAVASCRIPT0027"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
					layer.close(index);
					location.reload();
				});
			}
		});
	});
	
	
	// 新规页面
	$('.insertBtn').on('click',function(){
		uuid = $(this).attr("attr-data");
		// 请求弹框
		$.ajax({
			type:"get",
			url:"../transport/bomb/editSysShip.vm",
			success:function(data) {
				//弹框运行
			 	layer.open({
					  type: 1,
					  area: ['850px','580px'],
					  btn: [$.i18n("JAVASCRIPT0071"), $.i18n("JAVASCRIPT0025")],
					  btnAlign: 'c' ,//按钮居中
					  title:$.i18n("JAVASCRIPT00171"),
					  content: data,
					  yes: function(index, layero){
						// 校验
						  if($("#imo").val()=="") {
							  layer.alert($.i18n("JAVASCRIPT0088"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
							  return;
						  }
						  if($("#name").val()=="") {
							  layer.alert($.i18n("JAVASCRIPT0089"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
							  return;
						  }
						  if($("#date1").val()=="") {
							  layer.alert($.i18n("JAVASCRIPT0090"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
							  return;
						  }
						  
						  // 数值正则
						  var reg = new RegExp("^[0-9]+.?[0-9]*$");  
						  var length = delNumFormat($("#length").val());
						  var width = delNumFormat($("#wide").val());
						  var draft = delNumFormat($("#draft").val());
						  var loadQuantity = delNumFormat($("#loadQuantity").val());
						  var capacity = delNumFormat($("#capacity").val());
						  
						  if (length!="" && !reg.test(length)) {
							  layer.alert($.i18n("JAVASCRIPT0091"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
							  return;
						  }
						  
						  if (width!="" && !reg.test(width)) {
							  layer.alert($.i18n("JAVASCRIPT0092"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
							  return;
						  }
						  
						  if (draft!="" && !reg.test(draft)) {
							  layer.alert($.i18n("JAVASCRIPT0093"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
							  return;
						  }
						  
						  if (loadQuantity!="" && !reg.test(loadQuantity)) {
							  layer.alert($.i18n("JAVASCRIPT0094"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
							  return;
						  }
						  
						  if (capacity!="" && !reg.test(capacity)) {
							  layer.alert($.i18n("JAVASCRIPT0095"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
							  return;
						  }
						  
						  var params = {};
						  params.uuid = uuid;
						  params.imo = $("#imo").val();
						  params.name = $("#name").val();
						  //params.pennant = $("#pennant").val();
						  
						  // 船型值设定
						  var val =  $("#type option:checked").val();
						  var text =  $("#type option:checked").text();
						  if ( val!= '' && text!=$.i18n("JAVASCRIPT00225")) {
							  params.type = $("#type option:checked").text();
						  } else {
							  if (val == '') {
								  params.type = "";
							  }
							  if (text == $.i18n("JAVASCRIPT00225")) {
								  params.type = $("#typeOther").val();
							  }
						  }
						  
						  // MMSI值设定
						  var val =  $("#mmsi option:checked").val();
						  var text =  $("#mmsi option:checked").text();
						  if ( val!= '' && text!=$.i18n("JAVASCRIPT00225")) {
							  params.mmsi = $("#mmsi option:checked").text();
						  } else {
							  if (val == '') {
								  params.mmsi = "";
							  }
							  if (text == $.i18n("JAVASCRIPT00225")) {
								  params.mmsi = $("#mmsiOther").val();
							  }
						  }
						  
						  params.useType = $("#useType").val();
						  params.completeDate = $("#date1").val();
						  params.hull = $("#hull").val();
						  params.length = delNumFormat($("#length").val());
						  params.wide = delNumFormat($("#wide").val());
						  params.draft = delNumFormat($("#draft").val());
						  params.loadQuantity = delNumFormat($("#loadQuantity").val());
						  params.capacity = delNumFormat($("#capacity").val());
						  params.fileInfoList = fileInfoList;
						  
						  // 船舶保存类型（1:待审核，2:有效） 
						  var saveType="2"
						  params.saveType=saveType;
						  
						  $.ajax({
							type: "POST",
							url: shipServer+"/sysShip/saveSysShip.json",
							data: JSON.stringify(params),
							contentType:"application/json",
							async: false,
							success: function(data) {  
									if(data.status == 0){
										layer.alert($.i18n("JAVASCRIPT00172"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
										    layer.close(index);
										    location.reload();
										});
									}else{
										layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
											layer.close(index);
										});
									}
							},
							error: function(data) {
								layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
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
					  },
					  cancel: function(index, layero){
						  layer.alert($.i18n("JAVASCRIPT0083"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
							    layer.close(index);
							    location.reload();
						  });
						  return false;
					  }
			    });
			 	
			 	// 隐藏下载列表
			 	$(".layui-upload").hide();
			 	
			 	// 查询船型列表
				var paramsBroker = {};
				paramsBroker.valueSetTypes="9;";
			 	$.ajax({
		    		type: "POST",
		    		url: shipServer+"/shipPact/queryValueSet.json",
		    		data: JSON.stringify(paramsBroker),
		    		contentType:"application/json",
		    		async: false,
		    		success: function(data) {
						if(data.status == 0){
							var broker_arr = data.datas;
							if (broker_arr != null && broker_arr.length > 0){
								for(var i =0; i<broker_arr.length; i++){
									
						            var broker = broker_arr[i];
						            if (broker.type == '9'){ // 船型
						            	$("#type").append("<option value='"+broker.code+"'>"+broker.value+"</option>");
						            }
								}
							}
						}else{
							layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
								layer.close(index);
							});
						}
					},
					error: function(data) {
						layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
							layer.close(index);
						});
			        }
			 	});
			 	
			 	// 船型其他
			 	$("#typeOther").hide();
			 	// MMSI
			 	$("#mmsiOther").hide();
			 	form.render();
			}
		})
	});
  
	//编辑页面
	$('.editBtn').on('click',function(){
		uuid = $(this).attr("attr-data");
		
		// 请求弹框
		$.ajax({
		  type:"POST",
		  url:"../transport/bomb/editSysShip.vm",
		  success:function(data) {
			//弹框运行
		 	layer.open({
				  type: 1,
				  area: ['850px','580px'],
				  btn: [$.i18n("JAVASCRIPT0071"), $.i18n("JAVASCRIPT0025")],
				  btnAlign: 'c' ,//按钮居中
				  title:$.i18n("JAVASCRIPT00173"),
				  content: data,
				  yes: function(index, layero){
					  // 校验
					  if($("#imo").val()=="") {
						  layer.alert($.i18n("JAVASCRIPT0088"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
						  return;
					  }
					  if($("#name").val()=="") {
						  layer.alert($.i18n("JAVASCRIPT0089"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
						  return;
					  }
					  if($("#date1").val()=="") {
						  layer.alert($.i18n("JAVASCRIPT0090"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
						  return;
					  }

					  // 数值正则
					  var reg = new RegExp("^[0-9]+.?[0-9]*$");  
					  var length = delNumFormat($("#length").val());
					  var width = delNumFormat($("#wide").val());
					  var draft = delNumFormat($("#draft").val());
					  var loadQuantity = delNumFormat($("#loadQuantity").val());
					  var capacity = delNumFormat($("#capacity").val());
					  
					  if (length!="" && !reg.test(length)) {
						  layer.alert($.i18n("JAVASCRIPT0091"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
						  return;
					  }
					  
					  if (width!="" && !reg.test(width)) {
						  layer.alert($.i18n("JAVASCRIPT0092"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
						  return;
					  }
					  
					  if (draft!="" && !reg.test(draft)) {
						  layer.alert($.i18n("JAVASCRIPT0093"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
						  return;
					  }
					  
					  if (loadQuantity!="" && !reg.test(loadQuantity)) {
						  layer.alert($.i18n("JAVASCRIPT0094"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
						  return;
					  }
					  
					  if (capacity!="" && !reg.test(capacity)) {
						  layer.alert($.i18n("JAVASCRIPT0095"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
						  return;
					  }

					  var params = {};
					  params.uuid = uuid;
					  params.imo = $("#imo").val();
					  params.name = $("#name").val();
					  
					  // 船型值设定
					  var val = $("#type option:checked").val();
					  var text = $("#type option:checked").text();
					  if ( val!= '' && text!=$.i18n("JAVASCRIPT00225")) {
						  params.type = $("#type option:checked").text();
					  } else {
						  if (val == '') {
							  params.type = "";
						  }
						  if (text == $.i18n("JAVASCRIPT00225")) {
							  params.type = $("#typeOther").val();
						  }
					  }
					  
					  // MMSI设定
					  var val = $("#mmsi option:checked").val();
					  var text = $("#mmsi option:checked").text();
					  if ( val!= '' && text!=$.i18n("JAVASCRIPT00225")) {
						  params.mmsi = $("#mmsi option:checked").text();
					  } else {
						  if (val == '') {
							  params.mmsi = "";
						  }
						  if (text == $.i18n("JAVASCRIPT00225")) {
							  params.mmsi = $("#mmsiOther").val();
						  }
					  }
					  
					  params.useType = $("#useType").val();
					  params.completeDate = $("#date1").val();
					  params.hull = $("#hull").val();
					  params.length = delNumFormat($("#length").val());
					  params.wide = delNumFormat($("#wide").val());
					  params.draft = delNumFormat($("#draft").val());
					  params.loadQuantity = delNumFormat($("#loadQuantity").val());
					  params.capacity = delNumFormat($("#capacity").val());
					  params.fileInfoList = fileInfoList;
					  
					  $.ajax({
						type: "POST",
						url: shipServer+"/sysShip/updateSysShip.json",
						data: JSON.stringify(params),
						contentType:"application/json",
						async: false,
						success: function(data) {  
								if(data.status == 0){
									layer.alert($.i18n("JAVASCRIPT00172"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
									    layer.close(index);
									    location.reload();
									});
								}else{
									layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
										layer.close(index);
									});
								}
						},
						error: function(data) {
							layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
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
				  },
				  cancel: function(index, layero){
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
	    		url: shipServer+"/sysShip/findSysShipDetail.json",
	    		data: JSON.stringify(params),
				contentType:"application/json",
				async: false,
	    		success: function(data) {
					if(data.status == 0){
						$("#uuid").val(data.datas.uuid);
						$("#imo").val(data.datas.imo);
						$("#name").val(data.datas.name);
						var type = data.datas.type;
						var imo = data.datas.imo;
						var mmsi = data.datas.mmsi;
						$("#useType").val(data.datas.useType);
						
						var timestamp = data.datas.completeDate;
						var completeDate = new Date(timestamp).Format("yyyy");
						
						$("#date1").val(completeDate);
						$("#hull").val(data.datas.hull);
						$("#length").val(formatNumber(data.datas.length,2,1));
						$("#wide").val(formatNumber(data.datas.wide,2,1));
						$("#draft").val(formatNumber(data.datas.draft,2,1));
						$("#loadQuantity").val(formatNumber(data.datas.loadQuantity,3,1));
						$("#capacity").val(formatNumber(data.datas.capacity,2,1));
						
						// 查询船型列表
					 	var paramsBroker = {};
						paramsBroker.valueSetTypes="9;";
					 	$.ajax({
				    		type: "POST",
				    		url: shipServer+"/shipPact/queryValueSet.json",
				    		data: JSON.stringify(paramsBroker),
							contentType:"application/json",
							async: false,
				    		success: function(data) {
								if(data.status == 0){
									var broker_arr = data.datas;
									if (broker_arr != null && broker_arr.length > 0){
										for(var i =0; i<broker_arr.length; i++){
								            var broker = broker_arr[i];
								            
								            if (broker.type == '9'){ // 船型
								            	$("#type").append("<option value='"+broker.code+"'>"+broker.value+"</option>");
								            }
										}
									}
								}else{
									layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
										layer.close(index);
									});
								}
							},
							error: function(data) {
								layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
									layer.close(index);
								});
					        }
					 	});
					 	
					 	// 船型赋初始值
					 	$("#type option").each(function(){
				 		    if($(this).text() == type){
				 		    $(this).attr("selected",true);
				 		    }
					 	});
					 	
					 	// 船型编辑框设定
				  		var val = $("#type option:checked").val();
				  		var text = $("#type option:checked").text();
				  		var size = $("#type option").size();
				  		
					 	if (size>1 && val=="" && type !="") {
					 		$("#type").find("option:contains("+$.i18n("JAVASCRIPT00225")+")").attr("selected",true);
					 		$("#typeOther").show();
					 		$("#typeOther").val(type);
					 	} else {
					 		$("#typeOther").hide();
					 	}
					 	
					 	// 初始化MMSI
					 	initMmsi(imo);
					 	// MMSI赋初始值
					 	$("#mmsi option").each(function(){
				 		    if($(this).text() == mmsi){
				 		    $(this).attr("selected",true);
				 		    }
					 	});
					 	
					 	// MMSI编辑框设定
				  		var val = $("#mmsi option:checked").val();
				  		var text = $("#mmsi option:checked").text();
				  		var size = $("#mmsi option").size();
				  		
					 	if (size>1 && val=="" && mmsi !="") {
					 		$("#mmsi").find("option:contains("+$.i18n("JAVASCRIPT00225")+")").attr("selected",true);
					 		$("#mmsiOther").show();
					 		$("#mmsiOther").val(mmsi);
					 	} else {
					 		$("#mmsiOther").hide();
					 	}
					}
				},
				error: function(data) {
					layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
						layer.close(index);
					});
		        }
		 	});
		 	
		 	// 下载列表初始化（编辑模式）
		 	downloadList('1');
		 	form.render();
		  }
		})
	});
	
	// 查看页面
	$('.lookBtn').on('click',function(){
		uuid = $(this).attr("attr-data");
		
		// 请求弹框
		$.ajax({
		  type:"POST",
		  url:"../transport/bomb/lookSysShip.vm",
		  success:function(data) {
			//弹框运行
		 	layer.open({
		 		  skin: 'backbtn-class',
				  type: 1,
				  area: ['850px','580px'],
				  btn: $.i18n("JAVASCRIPT0025"),
				  btnAlign: 'c' ,//按钮居中
				  title:$.i18n("JAVASCRIPT00174"),
				  content: data,
				  yes: function(index, layero){
					  layer.close(index);
					  return false;
				  },
				  cancel: function(index, layero){
					  layer.close(index);
					  return false;
				  }
		 	});

		 	//查询详细
		 	var params = {};
		 	params.uuid = uuid;
    	
		 	$.ajax({
	    		type: "POST",
	    		url: shipServer+"/sysShip/findSysShipDetail.json",
	    		data: JSON.stringify(params),
				contentType:"application/json",
				async: false,
	    		success: function(data) {
					if(data.status == 0){
						$("#uuid").val(data.datas.uuid);
						$("#imo").val(data.datas.imo);
						$("#mmsi").val(data.datas.mmsi);
						$("#name").val(data.datas.name);
						$("#type").val(data.datas.type);
						$("#useType").val(data.datas.useType);
						
						$("#date1").val(data.datas.completeDate);
						$("#hull").val(data.datas.hull);
						$("#length").val(formatNumber(data.datas.length,2,1));
						$("#wide").val(formatNumber(data.datas.wide,2,1));
						$("#draft").val(formatNumber(data.datas.draft,2,1));
						$("#loadQuantity").val(formatNumber(data.datas.loadQuantity,3,1));
						$("#capacity").val(formatNumber(data.datas.capacity,2,1));
					}else{
						layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
							layer.close(index);
						});
					}
				},
				error: function(data) {
					layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
						layer.close(index);
					});
		        }
		 	});
		 	
		 	// 下载列表初始化（查看模式）
		 	downloadList('0');
		 	form.render();
		  }
		})
	});

	// 删除函数
	$('.delBtn').on('click',function(){
		uuid = $(this).attr("attr-data");
		var params = {};
    	params.uuid = uuid;
    	
		// 校验船舶状态
		$.ajax({
    		type: "POST",
    		url: shipServer+"/sysShip/findSysShipStatus.json",
    		data: JSON.stringify(params),
			contentType:"application/json",
			async: false,
    		success: function(data) {
				if(data.status == 0){
					var status = [];
					status = data.datas;
					// 判断是否关联船盘
					if (status != null && status.length > 0) {
						layer.alert($.i18n("JAVASCRIPT0086"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
						    layer.close(index);
						});
						return;
					} else {
						//弹框运行
					    layer.confirm($.i18n("JAVASCRIPT0018"),{title:$.i18n("001"),btn:[$.i18n("002"),$.i18n("003")]}, function(index){
					    	$.ajax({
					    		type: "POST",
					    		url: shipServer+"/sysShip/delSysShip.json",
					    		data: JSON.stringify(params),
								contentType:"application/json",
								async: false,
					    		success: function(data) {
									if(data.status == 0){
										layer.alert($.i18n("JAVASCRIPT0019"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
										    layer.close(index);
										    location.reload();
										});
									}else{
										layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
											layer.close(index);
										});
									}
								},
								error: function(data) {
									layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
										layer.close(index);
									});
						        }
					    	});
					    });
					}
				}
			},
			error: function(data) {
				layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
					layer.close(index);
				});
	        }
    	});
	});
	

	// 船舶审核
	$('.checkBtn').on('click',function(){
		uuid = $(this).attr("attr-data");
		
		var params = {};
    	params.uuid = uuid;
    	
    	// 请求弹框
		$.ajax({
		  type:"POST",
		  url:"../transport/bomb/checkShip.vm",
		  success:function(data) {
			//弹框运行
		 	layer.open({
		 		  skin: 'backbtn-class',
				  type: 1,
				  area: ['850px','580px'],
				  btn: [$.i18n("JAVASCRIPT00175"), $.i18n("JAVASCRIPT00176") , $.i18n("JAVASCRIPT0025")],
				  btnAlign: 'c' ,//按钮居中
				  title:$.i18n("JAVASCRIPT00177"),
				  content: data,
				  
				  // 审核通过
				  yes: function(index, layero){
						params.checkFlag='1';  // 审核通过标识
						$.ajax({
							type: "POST",
							url: shipServer+"/sysShip/checkShip.json",
							data: JSON.stringify(params),
								contentType:"application/json",
								async: false,
							success: function(data) {
									if(data.status == 0){
										layer.alert($.i18n("JAVASCRIPT00178") ,{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
											layer.close(index);
											location.reload();
										});
									}
								},
								error: function(data) {
									layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
										layer.close(index);
									});
							}
						});
						layer.close(index);
						return false;
				  },
				  
				  // 驳回
				  btn2: function(index, layero){
					  params.checkFlag='2';  // 审核驳回标识
					  
					  var refuseMessage=$("#refuseMessage").val()
					  if(refuseMessage=="" || $.trim(refuseMessage)=="") {
						  layer.alert($.i18n("JAVASCRIPT00179"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
						  return;
					  }
					  params.refuseMessage=refuseMessage;
					  
					  //弹框运行
					  $.ajax({
							type: "POST",
							url: shipServer+"/sysShip/checkShip.json",
							data: JSON.stringify(params),
							contentType:"application/json",
							async: false,
							success: function(data) {
								if(data.status == 0){
									layer.alert($.i18n("JAVASCRIPT00180") ,{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
										layer.close(index);
										location.reload();
									});
								}
							},
							error: function(data) {
								layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
									layer.close(index);
								});
							}
					  });
				  },
				  
				  // 返回
				  btn3: function(index, layero){
					  layer.close(index);
					  return false;
				  },
				  cancel: function(index, layero){
					  layer.close(index);
					  return false;
				  }
		 	});

		 	findShipDetail(uuid);
		 	
		 	// 下载列表初始化（查看模式）
		 	downloadList('0');
		 	form.render();
		  }
		})
	})
	
	// 船舶数据详细
	function findShipDetail(uuid){
		//查询详细
	 	var params = {};
	 	params.uuid = uuid;
	 	
	 	$.ajax({
    		type: "POST",
    		url: shipServer+"/sysShip/findSysShipDetail.json",
    		data: JSON.stringify(params),
			contentType:"application/json",
			async: false,
    		success: function(data) {
				if(data.status == 0){
					$("#uuid").val(data.datas.uuid);
					$("#imo").val(data.datas.imo);
					$("#mmsi").val(data.datas.mmsi);
					$("#name").val(data.datas.name);
					$("#type").val(data.datas.type);
					$("#useType").val(data.datas.useType);
					
					$("#date1").val(data.datas.completeDate);
					$("#hull").val(data.datas.hull);
					$("#length").val(formatNumber(data.datas.length,2,1));
					$("#wide").val(formatNumber(data.datas.wide,2,1));
					$("#draft").val(formatNumber(data.datas.draft,2,1));
					$("#loadQuantity").val(formatNumber(data.datas.loadQuantity,3,1));
					$("#capacity").val(formatNumber(data.datas.capacity,2,1));
				}else{
					layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
						layer.close(index);
					});
				}
			},
			error: function(data) {
				layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
					layer.close(index);
				});
	        }
	 	});
	}
	
	// 下载列表初始化(mode:0查看 1编辑)
	function downloadList(mode) {
		var uuidShip = $('#uuid').val();
		
		// 初始化下载列表
	  	var downloadListView = $('#downloadList');
	  	
	  	// 查询下载列表
	 	var paramsFile = {};
	 	paramsFile.uuid = uuidShip;
	 	paramsFile.type = "1";	//业务类型（1船舶2船航次开始3船装港4船在途5船卸港）
	 	$.ajax({
    		type: "POST",
    		url: shipServer+"/accessory/findAccessoryList.json",
    		data: JSON.stringify(paramsFile),
			contentType:"application/json",
			async: false,
    		success: function(data) {
				if(data.status == 0){
				
					var downloadList = [];
					downloadList = data.datas;
					
					if (downloadList.length==0) {
						// 隐藏下载列表
					 	$(".layui-upload").hide();
					}
				 	
					for(index=0; index < downloadList.length; index++) {
				  		var file = downloadList[index];
				  		var fileIndex = index + 1;
				  		
				  		var tr = $(['<tr id="download-'+ index +'">'
				          				,'<td style="text-align:center">'+ fileIndex +'</td>'
				          				,'<td>'+ file.name +'</td>'
				          				,'<td>'
				            			//,'<a href="'+file.path+'" class="layui-btn layui-btn-mini file-download" download="">下载</a>'
				            			,'<a href="'+'/common/doc/download.htm?path='+file.path+'" class="layui-btn layui-btn-mini file-download">'+$.i18n("JAVASCRIPT00181")+'</a>'
				            			,'<button type="button" class="layui-btn layui-btn-mini layui-btn-danger file-delete" attr-data="'+file.uuid+'" attr-data1="'+index+'">'+$.i18n("JAVASCRIPT00196")+'</button>'
				          				,'</td>'
				        				,'</tr>'].join(''));
				  					
				  		// 查看模式
				  		if (mode == '0') {
				  			tr.find('.file-delete').hide();
				  		}
				  		
				  		// 下载事件
				  		tr.find('.file-download').on('click', function(){
				  			var path = $(this).attr("attr-data");
				  			//window.open(path,'_blank');
				  			//window.location.href=shipServer+"/common/doc/download.htm?path="+ encodeURI(path);
				  		});
				  		
				  		// 删除事件
				  		tr.find('.file-delete').on('click', function(){
				  			var accesoryUuid = $(this).attr("attr-data");
				  			var trIndex = $(this).attr("attr-data1");
				  			//弹框运行
				  		    layer.confirm($.i18n("JAVASCRIPT0087"),{title:$.i18n("001"),btn:[$.i18n("002"),$.i18n("003")]}, function(index){
				  		    	var params = {};
				  		    	params.uuid = accesoryUuid;
				  		    	
				  		    	$.ajax({
				  		    		type: "POST",
				  		    		url: shipServer+"/accessory/delAccessory.json",
				  		    		data: JSON.stringify(params),
				  					contentType:"application/json",
				  					async: false,
				  		    		success: function(data) {
				  						if(data.status == 0){
				  							layer.alert($.i18n("JAVASCRIPT0019"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
				  								//tr.remove();
				  								$("tr#download-" + trIndex).remove();
				  								//console.log($(this));
				  							    layer.close(index);
				  							});
				  						}else{
											layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
												layer.close(index);
											});
										}
				  					},
				  					error: function(data) {
				  						layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
											layer.close(index);
										});
				  			        }
				  		    	});
				  		    });
						});
				  		
				        downloadListView.append(tr);		
				  	}
				}
			},
			error: function(data) {
				layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
					layer.close(index);
				});
	        }
	 	});
	}

	// 船型选择事件
	form.on('select(typeFilter)', function (data) {
		// 船型值
  		var typeVal = $("#type option:checked").val();
  		// 船型名
  		var typeText = $("#type option:checked").text();
  		if (typeText == $.i18n("JAVASCRIPT00225")) {
  			$("#typeOther").show();
  		} else {
  			$("#typeOther").hide();
  		}
	});
	
	// IMO输入事件
	$('#imo').on('blur',function() {
		var imo = $('#imo').val();
		if (imo) {
			// 初始化MMSI
			initMmsi(imo);
		}
	}); 
	
	// MMSI选择事件
	form.on('select(mmsiFilter)', function (data) {
		// 船型值
  		var mmsiVal = $("#mmsi option:checked").val();
  		// 船型名
  		var mmsiText = $("#mmsi option:checked").text();
  		if (mmsiText == $.i18n("JAVASCRIPT00225")) {
  			$("#mmsiOther").show();
  		} else {
  			$("#mmsiOther").hide();
  		}
  		form.render();
	});
	
	//初始化MMSI
	function initMmsi(imo) {
		// 清空处理
		$("#mmsi option:not(:first)").remove();
		
		// 查询MMSI列表
		var params = {};
		params.imo= imo;
	 	$.ajax({
			type: "POST",
			url: shipServer+"/sysShip/findSysShipMmsi.json",
			data: JSON.stringify(params),
			contentType:"application/json",
			async: false,
			success: function(data) {
				if (data.status == 0) {
					var datas = data.datas;
					if (datas) {
						var mmsi_arr = data.datas.data;
						if (mmsi_arr != null && mmsi_arr.length > 0){
							for(var i =0; i< mmsi_arr.length; i++){
					            var mmsi = mmsi_arr[i];
					            if (mmsi.IMO == imo) {
					            	$("#mmsi").append("<option value='"+mmsi.mmsi+"'>"+mmsi.mmsi+"</option>");
					            }
							}
						}
					}
				}else{
					layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
						layer.close(index);
					});
				}
			},
			error: function(data) {
				layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
					layer.close(index);
				});
	        }
	 	});
	 	
	 	// MMSI其他
	 	$("#mmsi").append("<option value='other'>"+$.i18n("JAVASCRIPT00225")+"</option>");
	 	$("#mmsiOther").hide();
	 	
	 	if ($("#mmsi option").size() > 2) {
	 		$("#mmsi").find("option:eq(1)").attr("selected",true);
	 	}
	 	
	 	form.render();
	}
	
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
	
	function testFindShipByImoName() {
		var params = {};
		params.imo = "";
		params.name = "";
		params.type = "";
		params.pageSize = "5";
		params.pageNum ="2";
		$.ajax({
			type: "POST",
			url: shipServer+"/sysShip/findSysShipByImoName.json",
			data: JSON.stringify(params),
			contentType:"application/json",
			async: false,
			success: function(data) {  
					if(data.status == 0){
						alert(JSON.stringify(data.datas));
						alert(data.total);
					}else{
						layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
							layer.close(index);
						});
					}
			},
			error: function(data) {
				layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
					layer.close(index);
				});
            }
		});
	}

});


//上传文件
var fileInfoList = new Array();	


//加上传
function addFile(fileInfo) {
	var info = {};
	info.path = fileInfo.path;
	info.name = fileInfo.name;
	fileInfoList.push(info);
}


// 减上传
function delFile(filePath) {
	if(filePath != "") {
		for(var i = 0; i < fileInfoList.length; i++) {
			if(filePath == fileInfoList[i].path) {
				fileInfoList.splice(i, 1);
				break;
			}
		}
	}
	//alert(JSON.stringify(fileInfoList));
}
