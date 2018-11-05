layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate','own','upload'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	own = layui.own,
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
  
  	//执行一个laydate实例
  	laydate.render({
    	elem: '#date1', //ETA Fujairah
    	lang: lang_ver,
    	type: 'date'
  	});
  	laydate.render({
  		elem: '#date2', //ETA Cabinda
  		lang: lang_ver,
  		type: 'date'
  	});
  	laydate.render({
  		elem: '#date3', //open（到港之后，卸货完成时间 ）
  		lang: lang_ver,
  		type: 'date'
  	});
  	
  	
	
	//弹框
	function message(mess){
		layer.alert(mess, {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
			layer.close(index);
		});
	}
	
	
  	//添加船舶
  	$('.addShip').on('click',function(){
  		window.location.href=shipServer+"/shipPlate/shipAgentAddSysShip.htm";
  	});
  	
  	var uuid = own.getHref('uuid');
  	
  	//查询经纪人船东
  	ports()
  	
  	
  	// 编辑
  	if (uuid){
  		edit(uuid);
  	} else {
  		add();
  	}
  	
  	// 新增
  	function add() {
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
					window.shipNameArr = [];
					
					for(var i = 0; i < ship_arr.length; i++){  
						ship_arr[i].value=ship_arr[i].name;		/**下拉框显示的文本*/
						ship_arr[i].code=ship_arr[i].uuid;		/**为每个option 添加 uuid*/
						shipNameArr.push(ship_arr[i]);
					}
					autocompleteMy(shipNameArr,'#name');
					
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
	 	
	 	//获取船盘发布人
	 	$.ajax({
    		type: "POST",
    		url: shipServer+"/shipPact/getUserInfo.json",
    		data: JSON.stringify(paramsShip),
			dataType: "json",
			contentType:"application/json",
    		async: false,
    		success: function(data) {
				if(data.status == 0){
					var epMemberName=data.datas.epMemberName;
					$("#epMemberName").val(epMemberName);
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
	 	
	 	
	 	//发布日期初始化
	 	var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = year + seperator1 + month + seperator1 + strDate;
        $('#date4').val(currentdate);
	 	
	 	form.render();
  	}
  	
  	// 编辑
  	function edit(uuid){
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
					var completeDate = new Date(completeDateTimeStamp).Format("yyyy-MM-dd");
					//校验 ETA Fujairah
					var etaTimeStamp = data.datas.eta;
					if(etaTimeStamp!=null){
						var etaDate = new Date(etaTimeStamp).Format("yyyy-MM-dd");
						//var etaDate = new Date(etaTimeStamp).Format("yyyy-MM-dd hh:mm:ss");
						$("#date1").val(etaDate);
					}else{
						$("#date1").val("");
					}
					//校验 ETA Cabinda
					var etaCabindaTimeStamp = data.datas.etaCabinda;
					if(etaCabindaTimeStamp!=null){
						var etaCabindaDate = new Date(etaCabindaTimeStamp).Format("yyyy-MM-dd");
						$("#date2").val(etaCabindaDate);
					}else{
						$("#date2").val("");
					}
					
					var name = data.datas.name;
					var brokerName = data.datas.brokerName;
					var shipOwner = data.datas.shipOwner;
					var position = data.datas.position;
					//var leaveFlag = data.datas.leaveFlag;
					
					var openTimeStamp = data.datas.open;  //到港之后，卸货完成时间
					if(openTimeStamp!=null){
						$("#date3").val(new Date(openTimeStamp).Format("yyyy-MM-dd"));
					}else{
						$("#date3").val("");
					}
					
					
					//发布日期
					if(data.datas.status=='5'){		//若船盘的状态为 5未发布，发布日期显示未发布
						$("#date4").val("未发布");
					}else{
						var publishTimeStamp = data.datas.publishTime;	
						if(publishTimeStamp!=null){
							$("#date4").val(new Date(publishTimeStamp).Format("yyyy-MM-dd"));
						}else{
							$("#date4").val("");
						}
					}
					
					var epMemberName = data.datas.epMemberName;	//船盘发布人
					var period = data.datas.period;			    //时效

					$("#type").val(data.datas.type);
					$("#completeDate").val(completeDateTimeStamp);
					$("#loadQuantity").val(formatNumber(data.datas.loadQuantity,3,1));
					$("#position").val(position);
					$("#epMemberName").val(epMemberName);	

					
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
								window.shipNameArr = [];
								
								for(var i = 0; i < ship_arr.length; i++){  
									ship_arr[i].value=ship_arr[i].name;		/**下拉框显示的文本*/
									ship_arr[i].code=ship_arr[i].uuid;		/**为每个option 添加 uuid*/
									shipNameArr.push(ship_arr[i]);
								}
								autocompleteMy(shipNameArr,'#name');
								
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
				 	
				 	
				 	//是否离港赋初始值
				 	/*$("#leaveFlag option").each(function(){
			 		    if($(this).val() == leaveFlag){
		 			    $(this).attr("selected",true);
		 			    }
				 	});*/
				 	
				 	//时效赋初始值
				 	var flag=false;
				 	$("#period option").each(function(){
			 		    if($(this).val() == period){
			 				flag=true;
		 			    	$(this).attr("selected",true);
		 			    }
				 	});
				 	if(!flag){
				 		//$(".defaultPeriod").attr("selected",true);	//修改回显，若时效值为空，显示 null
				 		$("#period").attr("value",null);
				 	}
				 	
				 	$("#name").val(name);								/**船名*/
				 	$("#name").attr('code',data.datas.sysShipUuid);		
					$("#owner").val(shipOwner);							/**船东*/
					$("#owner").attr('code',data.datas.shipOwnerId);	
					$("#broker").val(brokerName);						/**经纪人*/
					$("#broker").attr('code',data.datas.brokerId);		
					
					$("#remark").val(data.datas.remark);
				}
			},
			error: function(data) {
				layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
					layer.close(index);
				});
	        }
	 	});
	 	form.render();
  	}
  	
  	
  	//提交
	$('.saveBtn').on('click', function() {
		
		// 船名校验
		if($("#name").val()=="") {
			layer.alert($.i18n("JAVASCRIPT0089"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
				layer.close(index);
			});
			return;
		}
		
		// 船位校验
		if($.trim($("#position").val())=="") {
			layer.alert($.i18n("JAVASCRIPT00145"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
				layer.close(index);
			});
			return;
		}
		
		if($("#date3").val()=="") {
			layer.alert($.i18n("JAVASCRIPT00197"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
				layer.close(index);
			});
			return;
		}
		
		//船型校验
		if($("#type").val()=="") {
			layer.alert($.i18n("JAVASCRIPT00146"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
				layer.close(index);
			});
			return;
		}
		
		//校验 ETA Fujairah 和  ETA Cabinda
		if($("#date1").val()=="" && $("#date2").val()=="") {
			layer.alert($.i18n("JAVASCRIPT00147"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
				layer.close(index);
			});
			return;
		}
		
		if($.trim($("#owner").val())=="") {
			layer.alert($.i18n("JAVASCRIPT00148"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
				layer.close(index);
			});
			return;
		}
		
		
		// 数值正则
		var reg = new RegExp("^[0-9]+.?[0-9]*$");
		var loadQuantity = delNumFormat($("#loadQuantity").val());
		  
		if (loadQuantity!="" && !reg.test(loadQuantity)) {
			layer.alert($.i18n("JAVASCRIPT0094"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
				layer.close(index);
			});
			return;
		}

		
		var params = {};
		params.uuid = uuid;
		params.name = $("#name").val();						/**获取船名*/
		params.sysShipUuid = $("#name").attr("code");		/**获取船uuid*/
		params.type = $("#type").val();
		params.completeDate = $("#completeDate").val();
		params.loadQuantity = loadQuantity;
		
		//params.brokerName = $("#broker").val();
		//params.shipOwner = $("#owner").val();
		params.brokerId = $("#broker").attr("code");
		params.shipOwnerId = $("#owner").attr("code");
		
		//校验ETA字段，防止空串传到后台报错
		if($("#date1").val()!="" && $("#date1").val()!=null){
			params.eta = $("#date1").val();
		}else{
			params.eta=null;
		}
		if($("#date2").val()!="" && $("#date2").val()!=null){
			params.etaCabinda = $("#date2").val();
		}else{
			params.etaCabinda=null;
		}
		if($("#date3").val()!="" && $("#date3").val()!=null){
			params.open = $("#date3").val();
		}else{
			params.open=null;
		}
		
		
	    params.position =$("#position").val();
		params.remark = $("#remark").val();
		//params.epMemberName = $("#epMemberName").val();
		params.period = $("#period").val();
		
		if (uuid) {
			// 修改
			$.ajax({
				type: "POST",
				url: shipServer+"/shipPlate/updateShipPlate.json",
				data: JSON.stringify(params),
				dataType: "json",
				contentType:"application/json",
				success: function(data) {
						if(data.status == 0){
							layer.alert($.i18n("CONSTANTS017"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
							    layer.close(index);
							    window.location.href=shipServer+"/shipPlate/shipAgentShipPlateList.htm";
							});
						} else {
							layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
							    layer.close(index);
							});
						}
				},
				error: function(data) {
	              layer.alert($.i18n("JAVASCRIPT003"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
	            	  layer.close(index);
	              });
				}
			});
		} else {
			// 新增
			$.ajax({
				type: "POST",
				url: shipServer+"/shipPlate/saveShipPlate.json",
				data: JSON.stringify(params),
				dataType: "json",
				contentType:"application/json",
				success: function(data) {
					if(data.status == 0){
						layer.alert($.i18n("CONSTANTS017"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
						    layer.close(index);
						    
						    //入口：首页新增船盘/经纪人新增船盘，若是首页，需要校验用户角色，决定返回页面
						    //查询当前用户角色
							var currentUserRoles=[];
							var ss={};
							$.ajax({
					    		type: "POST",
					    		url: shipServer+"/shipPact/getUserInfo.json",
					    		data: JSON.stringify(ss),
								dataType: "json",
								contentType:"application/json",
					    		async: false,
					    		success: function(data2) {
									if(data2.status == 0){
										currentUserRoles=data2.datas.roles;	//记录当前用户的角色，船东/经纪人
									}else{
										layer.alert($.i18n(data2.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
											layer.close(index);
										});
									}
								},
								error: function(data2) {
									layer.alert($.i18n(data2.message), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
										layer.close(index);
									});
						        }
						 	});
							//校验当前用户角色   roles
							var Href="";
							if(isContains(currentUserRoles,"ship_owner") || isContains(currentUserRoles,"ship_broker")){		// 船东/经纪人
								Href="/shipPlate/shipPlateList.htm";
							}
							if(isContains(currentUserRoles,"ship_trader") || isContains(currentUserRoles,"ship_executor")){		// 为转租船东
								Href="/shipPlate/shipAgentShipPlateList.htm";
							}
							window.location.href=shipServer+Href;
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
		}
		
	});
	
	
	//取消
	$('.returnBtn').on('click',function(){
		window.location.href=shipServer+"/shipPlate/shipAgentShipPlateList.htm";
	});
	
	
	//船名清空联动
	$("#name").blur(function(){
		var textStr=$("#name").attr("value");	/**获取文本值*/
		if(textStr==null || textStr==""){
			//清空船型、建造年份、载重吨
			$("#type").val("");
			$("#completeDate").val("");
			$("#loadQuantity").val("");
		}
	});
	
	
	//查询经纪人船东
	function ports(){
		 var paramType={};
		 var types=["4","5"];	// 会员资质类型 1炼厂,2贸易商,3商检,4船东,5船经纪人,6船代,7转租船东
		 paramType.typeList=types;
		 
		 $.ajax({
    		type: "POST",
    		url: shipServer+"/traderNameListForBack.json",
    		data: JSON.stringify(paramType),
			dataType: "json",
			contentType:"application/json",
    		async: false,
    		success: function(data) {
				if(data.status == 0){
					
					//经纪人
					window.brokerArr = [];
					//船东
					window.ownerArr = [];	
					
					var arr=data.datas;
					if(arr!=null){
						for(var i=0;i<arr.length;i++){
							if(arr[i].type=="4"){	//船东
								arr[i].value=arr[i].traderName;
								arr[i].subGroup=arr[i].traderName;
								arr[i].code=arr[i].traderId;
								ownerArr.push(arr[i]);
								
							}else if(arr[i].type=="5"){	//经纪人
								arr[i].value=arr[i].traderName;
								arr[i].subGroup=arr[i].traderName;
								arr[i].code=arr[i].traderId;
								brokerArr.push(arr[i]);
							}
						}
						
						
						//若从首页新增船盘，查询当前用户角色，回显船东/经纪人
						var currentUserRoles=[];
						var currentUserName="";
						var ss={};
						$.ajax({
				    		type: "POST",
				    		url: shipServer+"/shipPact/getUserInfo.json",
				    		data: JSON.stringify(ss),
							dataType: "json",
							contentType:"application/json",
				    		async: false,
				    		success: function(data2) {
								if(data2.status == 0){
									currentUserRoles=data2.datas.roles;	//记录当前用户的角色，船东/经纪人
									currentUserName=data2.datas.epMemberName;	//记录当前用户的名称
								}else{
									layer.alert($.i18n(data2.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
										layer.close(index);
									});
								}
							},
							error: function(data2) {
								layer.alert($.i18n(data2.message), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
									layer.close(index);
								});
					        }
					 	});
						
						//新增时，才设置船东/经纪人名称
						if(!uuid){
							//校验当前用户角色   roles
							if(isContains(currentUserRoles,"ship_owner")){		//为船东
								//船东显示当前用户名
								$("#owner").val(currentUserName);
							}
							if(isContains(currentUserRoles,"ship_broker")){		//为经纪人
								//经纪人显示当前用户名
								$("#broker").val(currentUserName);
							}
						}
						
						autocomplete(brokerArr,'.broker');
						autocomplete(ownerArr,'.owner');
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
	}
	
	
});

//日期格式化函数
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

//判断是否是子串
function isContains(str, substr) {
    return str.indexOf(substr) >= 0;
}


//船名联动处理
function fillByName(){
	//var aaa=$("#name").attr("value");	/**获取文本值*/
	var uuid = $("#name").attr("code");		/**船uuid*/
	
	if(uuid!=null && uuid!=""){
		var params = {};
		params.uuid = uuid;
		
		$.ajax({
			type: "POST",
			url: shipServer+"/sysShip/findSysShipDetail.json",		/**查询详细*/
			data: JSON.stringify(params),
			dataType: "json",
			contentType:"application/json",
			async: false,
			success: function(data) {
				if(data.status == 0){
					$("#type").val(data.datas.type);
					$("#completeDate").val(data.datas.completeDate);
					$("#loadQuantity").val(formatNumber(data.datas.loadQuantity,3,1));
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
	}else{
		//清空船型、建造年份、载重吨
		$("#type").val("");
		$("#completeDate").val("");
		$("#loadQuantity").val("");
	}
}

//填充下拉框数据
function autocompleteMy(arrZ,classZ){
	if (arrZ == null){
		arrZ=arrsFn;
	}
	 $(classZ).autocomplete(
			 arrZ,
             {	
				 max:2000,		/**显示数量*/
                 minChars: 0,	
                 width: $(classZ).outerWidth(),
                 matchContains: true,
                 scroll: true,
                 mustMatch: false,
                 scrollHeight: 122,
                 formatItem: function (row, i, max) {
                     return  row.value ;
                 },
                 formatMatch: function (row, i, max) {
                     return row.value;
                 },
                 formatResult: function (row) {
                     return row.value;
                 },
             }).result(function (event, row, formatted) {		/**选中某一项后触发*/
            	 $(classZ).attr('code',row.code); 
            	 fillByName();		/**根据船名，自动填充船型、建造年份、载重吨*/
             });
}