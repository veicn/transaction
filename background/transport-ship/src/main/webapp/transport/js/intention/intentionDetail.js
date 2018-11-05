layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	own = layui.own,
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	
	//记录船盘uuid，跳回“查看向我询盘列表页”
	var recordShipPlateUuid="";
	
	var fromPage = own.getHref('fromPage');	//船盘/货盘，列表->询盘列表信息->询盘详情页
	
	
	//询盘uuid
	var uuid = own.getHref('uuid');
	if(uuid==false){
		uuid="";
	}
	
	var status = own.getHref('status');	//状态不同，显示不同按钮
	var from = own.getHref('from');	//操作之后，返回跳转的页面
	var roleType = own.getHref('roleType');	//roleType：1船东/经纪人，2货主，3转租船东
	
	
	//执行一个laydate实例
	if(status=='1'){
		laydate.render({
			elem: '#coPactBeg',
			type: 'date',
			lang:lang_ver
		});
		laydate.render({
			elem: '#coPactEnd',
			type: 'date',
			lang:lang_ver
		});
	} 
	
	//按钮显示
	if(status=='2'){
		//已还盘，不显示“提交还盘”，“终止” 按钮
		$(".returnIntentionBtn").hide();
		$(".terminationBtn").hide();
		
		//还盘信息输入框，设置 readonly=‘true’
		$(".isRead").attr("readonly","readonly");
		//去除边框
		$(".isRead").attr("style","border:none");
		
		//*不显示
		$(".xingShow").hide();
	}
	
	
  	
	//查询详细
 	var params = {};
 	params.uuid = uuid;
 	$.ajax({
		type: "POST",
		url: shipServer+"/intention/findIntentionDetail.json",
		data: JSON.stringify(params),
		dataType: "json",
		contentType:"application/json",
		async: false,
		success: function(data) {
			if(data.status == 0){
				
				//询盘信息回显
				var inPactBegTimeStamp = data.datas.inPactBeg;	//回显询盘装期开始
				if(inPactBegTimeStamp!=null){
					var inPactBeg = new Date(inPactBegTimeStamp).Format("yyyy-MM-dd");
					//var etaDate = new Date(etaTimeStamp).Format("yyyy-MM-dd hh:mm:ss");
					$(".inPactBeg").html(inPactBeg);
				}else{
					$("#inPactBeg").html("");
				}
				
				var inPactEndTimeStamp = data.datas.inPactEnd;	//回显询盘装期结束
				if(inPactEndTimeStamp!=null){
					var inPactEnd = new Date(inPactEndTimeStamp).Format("yyyy-MM-dd");
					$(".inPactEnd").html(inPactEnd);
				}else{
					$(".inPactEnd").html("");
				}
				$(".inMinQuantity").html(formatNumber(data.datas.inMinQuantity,3,1));
				$(".inLoadRegion").html(data.datas.inLoadRegion);
				$(".inUnloadRegion").html(data.datas.inUnloadRegion);
				$(".inDockTime").html(formatNumber(data.datas.inDockTime,2,1));
				$(".inRemark").html(data.datas.inRemark);
				
				//还盘信息回显
				if(status=='1'){	//已收到询盘，回显一部分询盘信息
					if(inPactBegTimeStamp!=null){		//还盘装期开始回显
						var inPactBeg = new Date(inPactBegTimeStamp).Format("yyyy-MM-dd");
						$("#coPactBeg").val(inPactBeg);
					}else{
						$("#coPactBeg").val("");
					}
					if(inPactEndTimeStamp!=null){		//还盘装期结束回显
						var inPactEnd = new Date(inPactEndTimeStamp).Format("yyyy-MM-dd");
						$("#coPactEnd").val(inPactEnd);
					}else{
						$("#coPactEnd").val("");
					}
					$("#coMinQuantity").val(formatNumber(data.datas.inMinQuantity,3,1));
					$("#coLoadRegion").val(data.datas.inLoadRegion);
					$("#coUnloadRegion").val(data.datas.inUnloadRegion);
					$("#coPactSpeed").val(data.datas.inPactSpeed);
					$("#coDockTime").val(formatNumber(data.datas.inDockTime,2,1));
					
				}else if(status=='2'){	//已还盘，回显数据库中数据
					var coPactBegTimeStamp = data.datas.coPactBeg;	//回显还盘装期开始
					if(coPactBegTimeStamp!=null){
						var coPactBeg = new Date(coPactBegTimeStamp).Format("yyyy-MM-dd");
						$("#coPactBeg").val(coPactBeg);
					}else{
						$("#coPactBeg").val("");
					}
					
					var coPactEndTimeStamp = data.datas.coPactEnd;	//回显还盘装期结束
					if(coPactEndTimeStamp!=null){
						var coPactEnd = new Date(coPactEndTimeStamp).Format("yyyy-MM-dd");
						$("#coPactEnd").val(coPactEnd);
					}else{
						$("#coPactEnd").val("");
					}
					$("#coMinQuantity").val(formatNumber(data.datas.coMinQuantity,3,1));
					$("#coLoadRegion").val(data.datas.coLoadRegion);
					$("#coUnloadRegion").val(data.datas.coUnloadRegion);
					$("#coWs").val(formatNumber(data.datas.coWs,2,1));
					$("#coDemurrage").val(formatNumber(data.datas.coDemurrage,2,1));
					$("#coDockTime").val(formatNumber(data.datas.coDockTime,2,1));
					$("#coPactSpeed").val(formatNumber(data.datas.coPactSpeed,2,1));
					$("#coCommission").val(data.datas.coCommission);
					$("#coRemark").val(data.datas.coRemark);
				}
				
				
				//对家信息
				//roleType：1船东/经纪人，2货主，3转租船东
				if(roleType=='1' || roleType=='3'){
					var otherName=data.datas.epMemberName;
					if(otherName!=null && $.trim(otherName)!=""){
						$(".otherName").html("( "+otherName+" )");
					}else{
						$(".otherName").html(null);
					}
				}else if(roleType=='2'){
					var otherName=data.datas.shipPlateMemberName;
					if(otherName!=null && $.trim(otherName)!=""){
						$(".otherName").html("( "+otherName+" )");
					}else{
						$(".otherName").html(null);
					}
				}
				
				//根据船盘uuid查询船盘信息
				var shipPlateUuid=data.datas.shipPlateUuid;
				recordShipPlateUuid=shipPlateUuid;	//记录船盘uuid
				var paramsShipPlate = {};
				paramsShipPlate.uuid=shipPlateUuid;
			 	$.ajax({
		    		type: "POST",
		    		url: shipServer+"/shipPlate/findShipPlateDetail.json",
		    		data: JSON.stringify(paramsShipPlate),
					dataType: "json",
					contentType:"application/json",
		    		async: false,
		    		success: function(data) {
						if(data.status == 0){
							//船盘信息回显
							$(".name").html(data.datas.name);
							$(".name").attr('title',data.datas.name);
							
							$(".type").html(data.datas.type);
							$(".type").attr('title',data.datas.type);
							
							$(".position").html(data.datas.position);
							$(".position").attr('title',data.datas.position);
							
							var openTimeStamp = data.datas.open;	//open回显
							if(openTimeStamp!=null){
								var open = new Date(openTimeStamp).Format("yyyy-MM-dd");
								//var etaDate = new Date(etaTimeStamp).Format("yyyy-MM-dd hh:mm:ss");
								$(".open").html(open);
								$(".open").attr('title',open);
							}else{
								$(".open").html("");
								$(".open").attr('title',"");
							}
							var etaTimeStamp = data.datas.eta;	//ETA Fujairah回显
							if(etaTimeStamp!=null){
								var etaDate = new Date(etaTimeStamp).Format("yyyy-MM-dd");
								$(".eta").html(etaDate);
								$(".eta").attr('title',etaDate);
							}else{
								$(".eta").html("");
								$(".eta").attr('title',"");
							}
							var etaCabindaTimeStamp = data.datas.etaCabinda;	//ETA Cabinda回显
							if(etaCabindaTimeStamp!=null){
								var etaCabindaDate = new Date(etaCabindaTimeStamp).Format("yyyy-MM-dd");
								$(".etaCabinda").html(etaCabindaDate);
								$(".etaCabinda").attr('title',etaCabindaDate);
							}else{
								$(".etaCabinda").html("");
								$(".etaCabinda").attr('title',"");
							}
							
							$(".shipOwner").html(data.datas.shipOwner);
							$(".shipOwner").attr('title',data.datas.shipOwner);
							
							$(".epMemberName").html(data.datas.epMemberName);
							$(".epMemberName").attr('title',data.datas.epMemberName);
							
							$(".shipPlateRemark").html(data.datas.remark);
							$(".shipPlateRemark").attr('title',data.datas.remark);
							
							
							//根据船舶uuid查询船舶信息
							var sysShipUuid=data.datas.sysShipUuid;
							var paramsSysShip = {};
							paramsSysShip.uuid=sysShipUuid;
						 	$.ajax({
					    		type: "POST",
					    		url: shipServer+"/sysShip/findSysShipDetail.json",
					    		data: JSON.stringify(paramsSysShip),
								dataType: "json",
								contentType:"application/json",
					    		async: false,
					    		success: function(data) {
									if(data.status == 0){
										//船舶信息回显
										$(".shipName").html(data.datas.name);
										$(".shipName").attr('title',data.datas.name);
										
										$(".imo").html(data.datas.imo);
										$(".imo").attr('title',data.datas.imo);
										
										$(".completeDate").html(data.datas.completeDate);
										$(".completeDate").attr('title',data.datas.completeDate);
										
										$(".useType").html(data.datas.useType);
										$(".useType").attr('title',data.datas.useType);
										
										$(".sysShipType").html(data.datas.type);
										$(".sysShipType").attr('title',data.datas.type);
										
										$(".capacity").html(data.datas.capacity);
										$(".capacity").attr('title',data.datas.capacity);
										
										$(".loadQuantity").html(data.datas.loadQuantity);
										$(".loadQuantity").attr('title',data.datas.loadQuantity);
										
										$(".length").html(data.datas.length);
										$(".length").attr('title',data.datas.length);
										
										$(".wide").html(data.datas.wide);
										$(".wide").attr('title',data.datas.wide);
										
										$(".draft").html(data.datas.draft);
										$(".draft").attr('title',data.datas.draft);
										
										$(".hull").html(data.datas.hull);
										$(".hull").attr('title',data.datas.hull);
										
									}else{
										message($.i18n("ITSH023"));
									}
								},
								error: function(data) {
									message($.i18n(data.message));
						        }
						 	});
							
						}else{
							message($.i18n("ITSH013"));
						}
					},
					error: function(data) {
						message($.i18n(data.message));
			        }
			 	});
			}else{
				message($.i18n("ITSH405"));
			}
		},
		error: function(data) {
			message($.i18n(data.message));
        }
 	});
  	

	//提交还盘
	$('.returnIntentionBtn').on('click', function() {
		
		//数据格式校验
		var exp = /^([1-9][\d]{0,7}|0)(\.[\d]{1,3})?$/;	
		
		//意向装期 LAYCAN校验
		if($("#coPactBeg").val()=="") {
			layer.alert($.i18n("JAVASCRIPT0046"), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
				layer.close(index);
			});
			return;
		}
		if($("#coPactEnd").val()=="") {
			layer.alert($.i18n("JAVASCRIPT0047"), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
				layer.close(index);
			});
			return;
		}
		
		//意向装货量校验
		if($("#coMinQuantity").val()=="" || $.trim($("#coMinQuantity").val())=="") {
			layer.alert($.i18n("JAVASCRIPT0048"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
				layer.close(index);
			});
			return;
		}
		
		//还盘装货地点要求校验
		if($("#coLoadRegion").val()=="" || $.trim($("#coLoadRegion").val())=="") {
			layer.alert($.i18n("JAVASCRIPT0050"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
				layer.close(index);
			});
			return;
		}
		
		//还盘卸货地点要求校验
		if($("#coUnloadRegion").val()=="" || $.trim($("#coUnloadRegion").val())=="") {
			layer.alert($.i18n("JAVASCRIPT0051"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
				layer.close(index);
			});
			return;
		}
		
		
		//WS点校验
		if($("#coWs").val()=="" || $.trim($("#coWs").val())=="") {
			layer.alert($.i18n("JAVASCRIPT0052"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
				layer.close(index);
			});
			return;
		}
		
		//滞期费率校验
		if($("#coDemurrage").val()=="" || $.trim($("#coDemurrage").val())=="") {
			layer.alert($.i18n("JAVASCRIPT0054"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
				layer.close(index);
			});
			return;
		}
		
		//允许装卸时间校验
		if($("#coDockTime").val()=="" || $.trim($("#coDockTime").val())=="") {
			layer.alert($.i18n("JAVASCRIPT0056"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
				layer.close(index);
			});
			return;
		}
		
		//航速校验
		if($("#coPactSpeed").val()=="" || $.trim($("#coPactSpeed").val())=="") {
			layer.alert($.i18n("JAVASCRIPT0058"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
				layer.close(index);
			});
			return;
		}
		
		
		layer.confirm($.i18n("JAVASCRIPT0060"),{title:$.i18n("001"),btn:[$.i18n("002"),$.i18n("003")]}, function(index){
			var params = {};
			params.uuid = uuid;	//获取询盘uuid
			params.coPactBeg = $("#coPactBeg").val();
			params.coPactEnd = $("#coPactEnd").val();
			params.coMinQuantity = delNumFormat($("#coMinQuantity").val());
			params.coLoadRegion = $("#coLoadRegion").val();
			params.coUnloadRegion = $("#coUnloadRegion").val();
			
			params.coWs = delNumFormat($("#coWs").val());
			params.coDemurrage = delNumFormat($("#coDemurrage").val());
			params.coDockTime = delNumFormat($("#coDockTime").val());
			params.coPactSpeed = $("#coPactSpeed").val();
			params.coCommission = delNumFormat($("#coCommission").val());
			params.coRemark = $("#coRemark").val();
			//params.name = $("#name option:checked").text();
			
			//修改询盘
			$.ajax({
				type: "POST",
				url: shipServer+"/intention/updateIntention.json",
				data: JSON.stringify(params),
				dataType: "json",
				contentType:"application/json",
				success: function(data) {
						if(data.status == 0){
							layer.alert($.i18n("JAVASCRIPT0061"), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
							    layer.close(index);
							    window.location.href=shipServer+from+"?shipPlateUuid="+recordShipPlateUuid+"&fromPage="+fromPage;
							});
						} else {
							layer.alert($.i18n("JAVASCRIPT0062"), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
							    layer.close(index);
							});
						}
				},
				error: function(data) {
					message($.i18n(data.message));
				}
			});
	    });
	})
		
	
	// 终止，修改status为已关闭
	$('.terminationBtn').on('click',function(){
	    //弹框运行
	    layer.confirm($.i18n("JAVASCRIPT0045"),{title:$.i18n("001"),btn:[$.i18n("002"),$.i18n("003")]},function(index){
	    	var params = {};
	    	params.uuid = uuid;
	    	$.ajax({
	    		type: "POST",
	    		url: shipServer+"/intention/updateIntentionStatus.json",
	    		data: JSON.stringify(params),
				dataType: "json",
				contentType:"application/json",
	    		async: false,
	    		success: function(data) {
					if(data.status == 0){
						layer.alert($.i18n("CONSTANTS011"), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
						    layer.close(index);
						    window.location.href=shipServer+from+"?shipPlateUuid="+recordShipPlateUuid+"&fromPage="+fromPage;
						});
					}else{
						message($.i18n(data.message));
					}
				},
				error: function(data) {
					message($.i18n(data.message));
		        }
	    	});
	    });
	});
	
	
	//返回
	$('.returnBtn').on('click',function(){
		window.location.href = document.referrer;
	});

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


//日期格式化
function date(str){
	var str;
	if ( str != null && str != '' && str != 0){
		str = new Date(str).Format("yyyy-MM-dd");
	}
	if (str == 0){
		str ='';
	}
	return str;
}


//错误信息
function message(mess){
	layer.alert(mess, {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
		layer.close(index);
	});
}
