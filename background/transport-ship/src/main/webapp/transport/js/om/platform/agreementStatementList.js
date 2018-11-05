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
	//回车查询
	$("body").keydown(function() {
        if (event.keyCode == "13") {//keyCode=13是回车键
            $('.searchBtn').click();
        }
    });
	function search(){
		var receiptCode = $('#receiptCode').val();
		var agreementCode = $('#agreementCode').val();
		
		window.location.href=shipServer+"/om/platform/agreementStatementList.htm?" +
				"receiptCode="+receiptCode+
				"&agreementCode="+agreementCode;
	}
	laydate.render({
    	elem:'#publishTimeEnd',
  	});
	laydate.render({
    	elem:'#publishTimeBeg',
  	});
	laydate.render({
    	elem:'#layCanBeg',
  	});
	laydate.render({
    	elem:'#layCanEnd',
  	});
	// 重置函数
	$('.reset').on('click',function(){
		window.location.href=shipServer+"/om/platform/agreementStatementList.htm";
	}); 
	// 查看页面
	$('.lookBtn').on('click',function(){
		uuid = $(this).attr("attr-data");
		
		// 请求弹框
		$.ajax({
		  type:"POST",
		  url:"/transport/bomb/platform/agreementStatementDetail.vm",
		  success:function(data) {
			//弹框运行
		 	layer.open({
		 		  skin: 'backbtn-class',
				  type: 1,
				  area: ['850px','580px'],
				  btn: $.i18n("JAVASCRIPT0025"),
				  btnAlign: 'c' ,//按钮居中
				  title:$.i18n("xxx"),
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
	    		url: shipServer+"/statements/findStatementsDetails.json",
	    		data: JSON.stringify(params),
				contentType:"application/json",
				async: false,
	    		success: function(data){
					if(data.status == 0){
						$("#receiptCode1").val(data.datas.receiptCode);
						$("#receiptCode1").attr("title",data.datas.receiptCode);
						$("#agreementCode1").val(data.datas.agreementCode);
						$("#agreementCode1").attr("title",data.datas.agreementCode);
						
						$("#payee").val(data.datas.payee);
						$("#payee").attr("title",data.datas.payee);
						$("#payer").val(data.datas.payer);
						$("#payer").attr("title",data.datas.payer);
						
						$("#freightQuantily").val(data.datas.freightQuantily);
						$("#freightQuantily").attr("title",data.datas.freightQuantily);
						$("#freightOverageQuantily").val(data.datas.freightOverageQuantily);
						$("#freightOverageQuantily").attr("title",data.datas.freightOverageQuantily);
						
						$("#freightFlatrate").val(data.datas.freightFlatrate);
						$("#freightFlatrate").attr("title",data.datas.freightFlatrate);
						
						$("#freightRate").val(data.datas.freightRate);
						$("#freightRate").attr("title",data.datas.freightRate);
						$("#freightOveragePct").val(data.datas.freightOveragePct);
						$("#freightOveragePct").attr("title",data.datas.freightOveragePct);
						
						$("#addressCommissionPct").val(data.datas.addressCommissionPct);
						$("#addressCommissionPct").attr("title",data.datas.addressCommissionPct);
						$("#freightTotal").val(data.datas.freightTotal);
						$("#freightTotal").attr("title",data.datas.freightTotal);
						
						$("#freightOverageTotal").val(data.datas.freightOverageTotal);
						$("#freightOverageTotal").attr("title",data.datas.freightOverageTotal);
						$("#addressCommissionTotal").val(data.datas.addressCommissionTotal);
						$("#addressCommissionTotal").attr("title",data.datas.addressCommissionTotal);
						
						if (data.datas.receiptAccessory != null && data.datas.receiptAccessoryPath != null ){
							$('#file').show();
							$('.fileName').html(data.datas.receiptAccessory).attr('title',data.datas.receiptAccessory);
							$('#btn').attr('address',data.datas.receiptAccessoryPath);
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
		
		 	// 下载列表初始化（查看模式）
		 	downloadList('0');
		 	form.render();
		  }
		})
	});
	
	
	// 下载
	$('body').on('click','#btn',function(){
		window.location.href=$(this).attr('address');
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
