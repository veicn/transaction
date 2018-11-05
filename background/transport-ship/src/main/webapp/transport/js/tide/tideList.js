layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;

	//执行一个laydate实例
  	laydate.render({
    	elem: '#searchDate', //指定元素
    	lang: lang_ver
  	});
  	
	//页面记录的uuid
	var uuid = "";

	// 初始化区域列表
	initAreaList();
	
	window.onload = function() {
		
	};
	
	// 查询函数
	$('.searchBtn').on('click',function(){
		search('1');
		//testFindTideListByPortDate();
	}); 

	// 重置
	$('.resetBtn').on('click',function() {
		window.location.href=shipServer+"/tide/tideList.htm";
	});
	
	// 查询港口(clickVal:1点击查询按钮，0:自动查询)
	function search(clickVal){
		var portCode = $("#port option:checked").val();
		var portName = $("#port option:checked").text();
		
		if (clickVal == '0' && portCode=="") {
			layer.alert('请选择港口!', function(index) {
			    layer.close(index);
			});
			return;
		}
		
		var stateSearch = $('#state').val();
		var countrySearch = $('#country').val();
		var provinceSearch = $('#province').val();
		var portSearch = $('#port').val();
		var dateSearch = $('#searchDate').val();
		
		window.location.href=shipServer+"/tide/tideList.htm?stateSearch="+encodeURI(stateSearch)
		+"&countrySearch="+ encodeURI(countrySearch)+"&provinceSearch="+ encodeURI(provinceSearch)
		+"&portSearch="+ encodeURI(portSearch)+"&dateSearch="+ encodeURI(dateSearch);
	}

	// 新规页面
	$('.insertBtn').on('click',function(){
		var portCode = $("#port option:checked").val();
		var portName = $("#port option:checked").text();
		var searchDate = $("#searchDate").val();
		
		if (portCode=="") {
			layer.alert('请选择港口!', function(index) {
			    layer.close(index);
			});
			return;
		}
		
		// 初始化检索条件
		initSearchCondition();
		
		// 请求弹框
		$.ajax({
			type:"get",
			url:"../transport/bomb/editTide.vm",
			success:function(data) {
				//弹框运行
			 	layer.open({
					  type: 1,
					  area: ['600px','550px'],
					  btn: ['提交', '返回'],
					  btnAlign: 'c' ,//按钮居中
					  title:'添加潮汐（*为必填项）',
					  content: data,
					  yes: function(index, layero){
						  // 校验
						  if($("#portName").val()=="") {
							  layer.alert("港口名称不能为空！").style.zIndex = 9999999999;
							  return;
						  }
						  if($("#date1").val()=="") {
							  layer.alert("潮汐时间不能为空！").style.zIndex = 9999999999;
							  return;
						  }
						  
						  // 数值正则
						  var reg = new RegExp("^[0-9]+.?[0-9]*$");  
						  var hour = $("#hour").val();
						  var minute = $("#minute").val();
						  var height = $("#height").val();
						  
						  if (hour!="" && !reg.test(hour)) {
							  layer.alert("小时请输入数字！").style.zIndex = 9999999999;
							  return;
						  }
						  
						  if (minute!="" && !reg.test(minute)) {
							  layer.alert("分钟请输入数字！").style.zIndex = 9999999999;
							  return;
						  }
						  
						  if (height!="" && !reg.test(height)) {
							  layer.alert("潮高请输入数字！").style.zIndex = 9999999999;
							  return;
						  }
						  
						  var params = {};
						  params.portCode = $("#portCode").val();
						  params.portName = $("#portName").val();
						  params.hour = $("#hour").val();
						  params.minute = $("#minute").val();
						  params.height = $("#height").val();
						  params.date = $("#date1").val();
						  params.remark = $("#remark").val();
						  
						  $.ajax({
							type: "POST",
							url: shipServer+"/tide/saveTide.json",
							data: JSON.stringify(params),
							contentType:"application/json",
							async: false,
							success: function(data) {  
									if(data.status == 0){
										layer.alert('潮汐保存成功!', function(index) {
										    layer.close(index);
										    //location.reload();
										    search('0');
										});
									}else{
										layer.alert("错误:" + data.message, function(index) {
											layer.close(index);
										});
									}
							},
							error: function(data) {
								layer.alert("错误:" + data.message, function(index) {
									layer.close(index);
								});
				            }
						});
					  },
					  btn2: function(index, layero){
						  layer.alert('放弃保存吗!', function(index) {
								    layer.close(index);
								    //location.reload();
								    search('0');
						  });
						  return false;
					  },
					  cancel: function(index, layero){
						  layer.alert('放弃保存吗!', function(index) {
							    layer.close(index);
							    search('0');
						  });
						  return false;
					  }
			    });

			 	$("#portCode").val(portCode);
			 	$("#portName").val(portName);
			 	$("#date1").val(searchDate);
					
			 	form.render();
			}
		})
	});
  
	//编辑页面
	$('.editBtn').on('click',function(){
		var portCode = $("#port option:checked").val();
		
		// 初始化检索条件
		initSearchCondition();
		
		uuid = $(this).attr("attr-data");
		
		// 请求弹框
		$.ajax({
		  type:"POST",
		  url:"../transport/bomb/editTide.vm",
		  success:function(data) {
			//弹框运行
		 	layer.open({
				  type: 1,
				  area: ['600px','550px'],
				  btn: ['提交', '返回'],
				  btnAlign: 'c' ,//按钮居中
				  title:'编辑潮汐（*为必填项）',
				  content: data,
				  yes: function(index, layero){
					  // 校验
					  if($("#portName").val()=="") {
						  layer.alert("港口名称不能为空！").style.zIndex = 9999999999;
						  return;
					  }
					  if($("#date1").val()=="") {
						  layer.alert("潮汐时间不能为空！").style.zIndex = 9999999999;
						  return;
					  }

					  // 数值正则
					  var reg = new RegExp("^[0-9]+.?[0-9]*$");  
					  var hour = $("#hour").val();
					  var minute = $("#minute").val();
					  var height = $("#height").val();
					  
					  if (hour!="" && !reg.test(hour)) {
						  layer.alert("小时请输入数字！").style.zIndex = 9999999999;
						  return;
					  }
					  
					  if (minute!="" && !reg.test(minute)) {
						  layer.alert("分钟请输入数字！").style.zIndex = 9999999999;
						  return;
					  }
					  
					  if (height!="" && !reg.test(height)) {
						  layer.alert("潮高请输入数字！").style.zIndex = 9999999999;
						  return;
					  }
					  
					  var params = {};
					  var params = {};
					  params.uuid = uuid;
					  params.portCode = $("#portCode").val();
					  params.portName = $("#portName").val();
					  params.hour = $("#hour").val();
					  params.minute = $("#minute").val();
					  params.height = $("#height").val();
					  params.date = $("#date1").val();
					  params.remark = $("#remark").val();
					  
					$.ajax({
						type: "POST",
						url: shipServer+"/tide/updateTide.json",
						data: JSON.stringify(params),
						contentType:"application/json",
						async: false,
						success: function(data) {  
								if(data.status == 0){
									layer.alert(data.message, function(index) {
									    layer.close(index);
									    if (portCode == "") {
									    	location.reload();
									    } else {
									    	search('0');
									    }
									});
								}else{
									layer.alert("错误:" + data.message, function(index) {
										layer.close(index);
									});
								}
						},
						error: function(data) {
							layer.alert("错误:" + data.message, function(index) {
								layer.close(index);
							});
			            }
					});
				  },
				  btn2: function(index, layero){
					  layer.alert('放弃保存吗!', function(index) {
						    layer.close(index);
						    if (portCode == "") {
						    	location.reload();
						    } else {
						    	search('0');
						    }
					  });
					  return false;
				  },
				  cancel: function(index, layero){
					  layer.alert('放弃保存吗!', function(index) {
						  layer.close(index);
						    if (portCode == "") {
						    	location.reload();
						    } else {
						    	search('0');
						    }
					  });
					  return false;
				  }
		 	});

		 	//查询详细
		 	var params = {};
		 	params.uuid = uuid;
    	
		 	$.ajax({
	    		type: "POST",
	    		url: shipServer+"/tide/findTideDetail.json",
	    		data: JSON.stringify(params),
	    		contentType:"application/json",
	    		async: false,
	    		success: function(data) {
					if(data.status == 0){
						$("#portCode").val(data.datas.portCode);
						$("#portName").val(data.datas.portName);
						$("#hour").val(PrefixInteger(data.datas.hour,2));
						$("#minute").val(PrefixInteger(data.datas.minute,2));
						$("#height").val(data.datas.height);
						
						var timestamp = data.datas.date;
						var date = new Date(timestamp).Format("yyyy-MM-dd");;
						
						$("#date1").val(date);
						
						$("#remark").val(data.datas.remark);
					}else{
						layer.alert("错误:" + data.message, function(index) {
							layer.close(index);
						});
					}
				},
				error: function(data) {
					layer.alert("错误:" + data.message, function(index) {
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
		
		// 请求弹框
		$.ajax({
		  type:"POST",
		  url:"../transport/bomb/lookTide.vm",
		  success:function(data) {
			//弹框运行
		 	layer.open({
		 		  skin: 'backbtn-class',
				  type: 1,
				  area: ['600px','550px'],
				  btn: ['返回'],
				  btnAlign: 'c' ,//按钮居中
				  title:'查看潮汐',
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
	    		url: shipServer+"/tide/findTideDetail.json",
	    		data: JSON.stringify(params),
	    		contentType:"application/json",
	    		async: false,
	    		success: function(data) {
					if(data.status == 0){
						$("#portCode").val(data.datas.portCode);
						$("#portName").val(data.datas.portName);
						$("#hour").val(PrefixInteger(data.datas.hour,2));
						$("#minute").val(PrefixInteger(data.datas.minute,2));
						$("#height").val(data.datas.height);
						var timestamp = data.datas.date;
						var date = new Date(timestamp).Format("yyyy-MM-dd");
						$("#date1").val(date);
						$("#remark").val(data.datas.remark);
					}else{
						layer.alert("错误:" + data.message, function(index) {
							layer.close(index);
						});
					}
				},
				error: function(data) {
					layer.alert("错误:" + data.message, function(index) {
						layer.close(index);
					});
		        }
		 	});
		
		 	form.render();
		  }
		})
	});

	// 删除函数
	$('.delBtn').on('click',function(){
		var portCode = $("#port option:checked").val();
		
		// 初始化检索条件
		initSearchCondition();
		
		uuid = $(this).attr("attr-data");
	    //弹框运行
	    layer.confirm('确定删除吗？', function(index){
	    	var params = {};
	    	params.uuid = uuid;
	    	
	    	$.ajax({
	    		type: "POST",
	    		url: shipServer+"/tide/delTide.json",
	    		data: JSON.stringify(params),
	    		contentType:"application/json",
	    		async: false,
	    		success: function(data) {
					if(data.status == 0){
						layer.alert('删除成功!', function(index) {
						    layer.close(index);
						    if (portCode == "") {
						    	location.reload();
						    } else {
						    	search('0');
						    }
						});
					}else{
						layer.alert("错误:" + data.message, function(index) {
							layer.close(index);
						});
					}
				},
				error: function(data) {
					layer.alert("错误:" + data.message, function(index) {
						layer.close(index);
					});
		        }
	    	});
	    });
	});
	
	// 州选择事件
	$("#state").change(function(e){
		// 值
  		var val = $("#state option:checked").val();
  		// 名
  		var text = $("#state option:checked").text();
  		
  		// 清空
  		if (val == "") {
  			$("#country option:not(:first)").remove();
  			$("#province option:not(:first)").remove();
  			$("#port option:not(:first)").remove();
  		}
  		
  		var params = {};
  		params.valueSetType = "100";
  		params.subGroup = val;
	 	$.ajax({
			type: "POST",
			url: shipServer+"/shipPact/queryValueSet.json",
			data: JSON.stringify(params),
			contentType:"application/json",
			async: false,
			success: function(data) {
				if(data.status == 0){
					var arrs = data.datas;
					if (arrs != null && arrs.length > 0){
						for(var i =0; i< arrs.length; i++){
				            var arr = arrs[i];
				            if (arr.type == '100'){ // 
				            	$("#country").append("<option value='"+arr.subGroup+"'>"+arr.value+"</option>");
				            }
						}
					}
				}else{
					layer.alert("值集错误:" + data.message, function(index) {
						layer.close(index);
					});
				}
			},
			error: function(data) {
				layer.alert("错误:" + data.message, function(index) {
					layer.close(index);
				});
	        }
	 	});
	});
	
	// 国家选择事件
	$("#country").change(function(e){
		// 值
  		var val = $("#country option:checked").val();
  		// 名
  		var text = $("#country option:checked").text();
  		
  		// 清空
  		if (val == "") {
  			$("#province option:not(:first)").remove();
  			$("#port option:not(:first)").remove();
  		}
  		
  		var params = {};
  		params.valueSetType = "100";
  		params.subGroup = val;
	 	$.ajax({
			type: "POST",
			url: shipServer+"/shipPact/queryValueSet.json",
			data: JSON.stringify(params),
			contentType:"application/json",
			async: false,
			success: function(data) {
				if(data.status == 0){
					var arrs = data.datas;
					if (arrs != null && arrs.length > 0){
						for(var i =0; i< arrs.length; i++){
				            var arr = arrs[i];
				            if (arr.type == '100'){ // 
				            	$("#province").append("<option value='"+arr.subGroup+"'>"+arr.value+"</option>");
				            }
						}
					}
				}else{
					layer.alert("值集错误：" + data.message, function(index) {
						layer.close(index);
					});
				}
			},
			error: function(data) {
				layer.alert("错误:" + data.message, function(index) {
					layer.close(index);
				});
	        }
	 	});
	});
	
	// 省选择事件
	$("#province").change(function(e){
		// 值
  		var val = $("#province option:checked").val();
  		// 名
  		var text = $("#province option:checked").text();
  		
  		// 清空
		$("#port option:not(:first)").remove();
		
  		var params = {};
  		params.valueSetType = "100";
  		params.subGroup = val;
	 	$.ajax({
			type: "POST",
			url: shipServer+"/shipPact/queryValueSet.json",
			data: JSON.stringify(params),
			contentType:"application/json",
			async: false,
			success: function(data) {
				if(data.status == 0){
					var arrs = data.datas;
					if (arrs != null && arrs.length > 0){
						for(var i =0; i< arrs.length; i++){
				            var arr = arrs[i];
				            if (arr.type == '100'){ // 
				            	$("#port").append("<option value='"+arr.code+"'>"+arr.value+"</option>");
				            }
						}
					}
				}else{
					layer.alert("值集错误:" + data.message, function(index) {
						layer.close(index);
					});
				}
			},
			error: function(data) {
				layer.alert("错误:" + data.message, function(index) {
					layer.close(index);
				});
	        }
	 	});
	});
	
	function initAreaList() {
		var stateSearch = $("#stateSearch").val();
		var countrySearch = $("#countrySearch").val();
		var provinceSearch = $("#provinceSearch").val();
		var portSearch = $("#portSearch").val();
		
		// 查询州列表
	 	var params = {};
		params.valueSetType="12";
	 	$.ajax({
			type: "POST",
			url: shipServer+"/shipPact/queryValueSet.json",
			data: JSON.stringify(params),
			contentType:"application/json",
			async: false,
			success: function(data) {
				if(data.status == 0){
					var arrs = data.datas;
					if (arrs != null && arrs.length > 0){
						for(var i =0; i< arrs.length; i++){
				            var arr = arrs[i];
				            if (arr.type == '12'){ // 
				            	$("#state").append("<option value='"+arr.subGroup+"'>"+arr.value+"</option>");
				            }
						}
					}
				}else{
					layer.alert("值集错误:" + data.message, function(index) {
						layer.close(index);
					});
				}
			},
			error: function(data) {
				layer.alert("错误:" + data.message, function(index) {
					layer.close(index);
				});
	        }
	 	});
	 	
	 	if (stateSearch != "") {
	 		$("#state").val(stateSearch);
	 		
	 		// 查询国家列表
	 		var params = {};
	  		params.valueSetType = "100";
	  		params.subGroup = stateSearch;
		 	$.ajax({
				type: "POST",
				url: shipServer+"/shipPact/queryValueSet.json",
				data: JSON.stringify(params),
				contentType:"application/json",
				async: false,
				success: function(data) {
					if(data.status == 0){
						var arrs = data.datas;
						if (arrs != null && arrs.length > 0){
							for(var i =0; i< arrs.length; i++){
					            var arr = arrs[i];
					            if (arr.type == '100'){ // 
					            	$("#country").append("<option value='"+arr.subGroup+"'>"+arr.value+"</option>");
					            }
							}
						}
					}else{
						layer.alert("值集错误:" + data.message, function(index) {
							layer.close(index);
						});
					}
				},
				error: function(data) {
					layer.alert("错误:" + data.message, function(index) {
						layer.close(index);
					});
		        }
		 	});
		 	
		 	$("#country").val(countrySearch);
	 	}
	 	
	 	if (countrySearch != "") {
	 		// 查询省列表
	 		var params = {};
	  		params.valueSetType = "100";
	  		params.subGroup = countrySearch;
		 	$.ajax({
				type: "POST",
				url: shipServer+"/shipPact/queryValueSet.json",
				data: JSON.stringify(params),
				contentType:"application/json",
				async: false,
				success: function(data) {
					if(data.status == 0){
						var arrs = data.datas;
						if (arrs != null && arrs.length > 0){
							for(var i =0; i< arrs.length; i++){
					            var arr = arrs[i];
					            if (arr.type == '100'){ // 
					            	$("#province").append("<option value='"+arr.subGroup+"'>"+arr.value+"</option>");
					            }
							}
						}
					}else{
						layer.alert("值集错误:" + data.message, function(index) {
							layer.close(index);
						});
					}
				},
				error: function(data) {
					layer.alert("错误:" + data.message, function(index) {
						layer.close(index);
					});
		        }
		 	});
		 	
		 	$("#province").val(provinceSearch);
		 	
	 	}
	 	
	 	if (provinceSearch != "") {
	 		// 查询港口列表
	 		var params = {};
	  		params.valueSetType = "100";
	  		params.subGroup = provinceSearch;
		 	$.ajax({
				type: "POST",
				url: shipServer+"/shipPact/queryValueSet.json",
				data: JSON.stringify(params),
				contentType:"application/json",
				async: false,
				success: function(data) {
					if(data.status == 0){
						var arrs = data.datas;
						if (arrs != null && arrs.length > 0){
							for(var i =0; i< arrs.length; i++){
					            var arr = arrs[i];
					            if (arr.type == '100'){ // 
					            	$("#port").append("<option value='"+arr.code+"'>"+arr.value+"</option>");
					            }
							}
						}
					}else{
						layer.alert("值集错误:" + data.message, function(index) {
							layer.close(index);
						});
					}
				},
				error: function(data) {
					layer.alert("错误:" + data.message, function(index) {
						layer.close(index);
					});
		        }
		 	});
		 	
		 	$("#port").val(portSearch);
	 	}
	 	
	}
	
	// 初始化检索条件
	function initSearchCondition() {
		var stateSearch = $("#state").val();
		var countrySearch = $("#country").val();
		var provinceSearch = $("#province").val();
		var portSearch = $("#port").val();
		var dateSearch = $("#searchDate").val();
		
		$("#stateSearch").val(stateSearch);
		$("#countrySearch").val(countrySearch);
		$("#provinceSearch").val(provinceSearch);
		$("#portSearch").val(portSearch);
		$("#dateSearch").val(dateSearch);
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
	
	// 自动补0（num传入的数字，n需要的字符长度）
	function PrefixInteger(num, n) {
        return (Array(n).join(0) + num).slice(-n);
    }
	
	function testFindTideListByPortDate() {
		var params = {};
		params.portCode = "01";
		params.date = "2018-01-29";
		$.ajax({
			type: "POST",
			url: shipServer+"/tide/findTideListByPortDate.json",
			data: JSON.stringify(params),
			contentType:"application/json",
			async: false,
			success: function(data) {  
					if(data.status == 0){
						alert(JSON.stringify(data.datas));
					}else{
						layer.alert("错误:" + data.message, function(index) {
							layer.close(index);
						});
					}
			},
			error: function(data) {
				layer.alert("错误:" + data.message, function(index) {
					layer.close(index);
				});
            }
		});
	}

});
