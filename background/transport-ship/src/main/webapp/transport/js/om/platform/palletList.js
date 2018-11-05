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
		var publisher = $('#publisher').val();
		var oilName = $('#oilName').val();
		/*var shipType = $('#shipType').val();*/
		var loadPort = $('#loadPort').val();
		
		var shipAgeBeg = $('#shipAgeBeg').val();
		var shipAgeEnd = $('#shipAgeEnd').val();
		var publishTimeBeg = $('#publishTimeBeg').val();
		var publishTimeEnd = $('#publishTimeEnd').val();
		
		var layCanBeg = $('#layCanBeg').val();
		var layCanEnd = $('#layCanEnd').val();
		var status = $('#status').val();
		var unloadPort = $('#unloadPort').val();
		
		var palletCode = $('#palletCode').val();
		var orderNo = $('#orderNo').val();
		
		window.location.href=shipServer+"/om/platform/palletList.htm?" +
				"publisher="+ publisher+
				"&oilName="+ oilName+
				"&loadPort="+ loadPort+
				
				"&shipAgeBeg="+ shipAgeBeg+
				"&shipAgeEnd="+ shipAgeEnd+
				"&publishTimeBeg="+ publishTimeBeg+
				"&publishTimeEnd="+ publishTimeEnd+
				
				"&layCanBeg="+ layCanBeg+
				"&layCanEnd="+ layCanEnd+
				"&status="+ status+
				"&unloadPort="+ unloadPort+
				"&palletCode="+ palletCode+
				"&orderNo="+ orderNo;
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
	$('.resetBtn').on('click',function(){
		window.location.href=shipServer+"/om/platform/palletList.htm";
	}); 
	// 查看页面
	$('.lookBtn').on('click',function(){
		uuid = $(this).attr("attr-data");
		
		// 请求弹框
		$.ajax({
		  type:"POST",
		  url:"/transport/bomb/platform/palletDetail.vm",
		  success:function(data) {
			//弹框运行
		 	layer.open({
		 		  skin: 'backbtn-class',
				  type: 1,
				  area: ['850px','580px'],
				  btn: '关闭',
				  btnAlign: 'c' ,//按钮居中
				  title:"查看租船需求",
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
	    		url: shipServer+"/pallet/palleDetail.json",
	    		data: JSON.stringify(params),
				contentType:"application/json",
				async: false,
	    		success: function(data){
					if(data.status == 0){
						var arr =[];
						var arr2 =[];
						var type =data.datas.type;
						if(type=='1'){
							arr = data.datas.listLoad;
							arr2 = data.datas.listUnload;
						}
						if(type=='2'){
							arr = data.datas.listRegion;
							arr2 = data.datas.listUnregion;
						}
						var quantityStr = "";
						var loadPortStr = "";
						var oilTypeStr = "";
						for(var i = 0 ; i < arr.length ; i++){
							if ( i== 0){
								quantityStr = formatNumber(arr[i].quantity,2,1)+arr[i].unitName;
								loadPortStr = arr[i].loadPort;
								oilTypeStr = arr[i].oilType;
							}else{
								quantityStr += ";"+formatNumber(arr[i].quantity,2,1)+arr[i].unitName;
								loadPortStr += ";" +arr[i].loadPort;
								oilTypeStr	+= ";" + arr[i].oilType;
								/*var cloneDiv=$('.append1:eq(0)').clone();
								cloneDiv.find('.quantity').val(formatNumber(arr[i].quantity,2,1)+arr[i].unitName);
								cloneDiv.find('.loadPort').val(arr[i].loadPort);
								cloneDiv.find('.oilType').val(arr[i].oilType)
								$('.zhuanggangAll').append(cloneDiv);*/
							}
						}
						$(".quantity").val(quantityStr);
						$(".quantity").attr("title",quantityStr);
						$(".loadPort").val(loadPortStr);
						$(".loadPort").attr("title",loadPortStr);
						$(".oilType").val(oilTypeStr);
						$(".oilType").attr("title",oilTypeStr);
						var unloadPortStr = "";
						for(var i = 0 ; i < arr2.length;i++){
							if ( i== 0){
								unloadPortStr = arr2[i].unloadPort;
							}else{
								unloadPortStr += ";" + arr2[i].unloadPort;
								/*var cloneDiv=$('.append2:eq(0)').clone();
								cloneDiv.find('.unloadPort').val(arr2[i].unloadPort);
								$('.xiegangAll').append(cloneDiv);*/
							}
						}
						$(".unloadPort").val(unloadPortStr);
						$(".unloadPort").attr("title",unloadPortStr);
						var pallet =data.datas.pallet;
						var laycan = new Date(pallet.laycanBeg).Format('yyyy-MM-dd')+"--"+new Date(pallet.laycanEnd).Format('yyyy-MM-dd');
						$(".laycan").val(laycan);
						$(".laycan").attr("title",laycan);
						$(".remark").val(pallet.remark);
						$(".remark").attr("title",pallet.remark);
						$(".companyName").val(pallet.companyName);
						$(".companyName").attr("title",pallet.companyName);
						$(".tel").val(pallet.tel);
						$(".tel").attr("title",pallet.tel);
						$(".email").val(pallet.email);
						$(".email").attr("title",pallet.email);
						$(".shipAge").val(pallet.shipAge);
						$(".shipAge").attr("title",pallet.shipAge);
						$(".contacts").val(pallet.contacts);
						$(".contacts").attr("title",pallet.contacts);
						$(".shipType").val(pallet.shipType);
						$(".shipType").attr("title",pallet.shipType);
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
		 /*	
		 	// 下载列表初始化（查看模式）
		 	downloadList('0');*/
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
