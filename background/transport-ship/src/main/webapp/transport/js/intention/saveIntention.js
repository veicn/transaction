layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	own = layui.own,
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	
	laydate.render({
  		elem: '#inPactBeg', //inPactBeg
  		lang: lang_ver,
  		type: 'date'
  	});
	laydate.render({
		elem: '#inPactEnd', //inPactEnd
		lang: lang_ver,
		type: 'date'
	});
	
	
	//获取船盘推荐页面，带回的uuid
	var shipPlateUuid = own.getHref('shipPlateUuid');
	if(shipPlateUuid==false){
		shipPlateUuid="";
	}
	var palletUuid = own.getHref('palletUuid');
	if(palletUuid==false){
		palletUuid="";
	}
	
	
	var successFlag="0";

	//查询船盘详情
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
				
				
				//对家信息
				var otherName=data.datas.epMemberName;
				if(otherName!=null && otherName!=""){
					$(".otherName").html("( "+otherName+" )");
				}
				
				
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
							
							successFlag="1";
							
						}else{
							message($.i18n("ITSH023"))
						}
					},
					error: function(data) {
						message($.i18n(data.message));
			        }
			 	});
				
			}else{
				layer.alert($.i18n("ITSH013"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
					layer.close(index);
					window.location.href = document.referrer;
				});
			}
		},
		error: function(data) {
			message($.i18n(data.message));
        }
 	});
  	
  	
 	//查询货盘详情，拼接装货量、装货地点要求、卸货地点要求
 	if(successFlag=="1"){
	 	var recordOrderNo="";
		var paramsPallet = {};
		paramsPallet.uuid=palletUuid;
	 	$.ajax({
			type: "POST",
			url: shipServer+"/pallet/palleDetail.json",
			data: JSON.stringify(paramsPallet),
			dataType: "json",
			contentType:"application/json",
			async: false,
			success: function(data) {
				if(data.status == 0){
					
					//货盘信息
					var pallet=data.datas.pallet;
					//装港信息
					var loadArr=data.datas.listLoad;
					//卸港信息
					var unloadArr=data.datas.listUnload;
					//装港区域信息
					var loadRegionArr=data.datas.listRegion;
					//卸港区域信息
					var unloadRegionArr=data.datas.listUnregion;
					
					
					//装期LAYCAN
					var laycanBegTimeStamp = pallet.laycanBeg;	//laycanBeg
					if(laycanBegTimeStamp!=null){
						var laycanBeg = new Date(laycanBegTimeStamp).Format("yyyy-MM-dd");
						$("#inPactBeg").val(laycanBeg);
					}else{
						$("#inPactBeg").val("");
					}
					var laycanEndTimeStamp = pallet.laycanEnd;	//laycanEnd
					if(laycanEndTimeStamp!=null){
						var laycanEnd = new Date(laycanEndTimeStamp).Format("yyyy-MM-dd");
						$("#inPactEnd").val(laycanEnd);
					}else{
						$("#inPactEnd").val("");
					}
					
					
					//拼接装港信息
					var loads="";
					var loadQuantitys=0;
					for(var i=0;i<loadArr.length;i++){
						var loadPort=loadArr[i].loadPort;
						if(loadPort!=null && loadPort!=''){
							loads+=loadPort+"/";
						}
						var quantity=loadArr[i].quantity;	//获取数量
						loadQuantitys+=quantity;
					}
					//拼接卸港信息
					var unloads="";
					for(var i=0;i<unloadArr.length;i++){
						//装港/装港区域
						var unloadPort=unloadArr[i].unloadPort;
						if(unloadPort!=null && unloadPort!=''){
							unloads+=unloadPort+"/";
						}
					}
					
					//拼接装港区域信息
					var loadRegions="";
					var loadRegionQuantitys=0;
					for(var i=0;i<loadRegionArr.length;i++){
						//装港/装港区域
						var loadRegion=loadRegionArr[i].loadRegion;
						if(loadRegion!=null && loadRegion!=''){
							loadRegions+=loadRegion+"/";
						}
						var quantity=loadRegionArr[i].quantity;	//获取数量
						loadRegionQuantitys+=quantity;
					}
					//拼接卸港区域信息
					var unloadRegions="";
					for(var i=0;i<unloadRegionArr.length;i++){
						//装港/装港区域
						var unloadRegion=unloadRegionArr[i].unloadRegion;
						if(unloadRegion!=null && unloadRegion!=''){
							unloadRegions+=unloadRegion="/";
						}
					}
					
					//切除字符串最后 "/"
					var loadResults=splitStr(loads+loadRegions);
					var unloadResults=splitStr(unloads+unloadRegions);
					var quantitys=loadQuantitys+loadRegionQuantitys;
					
					
					//装货量
					$("#inMinQuantity").val(formatNumber(quantitys,3,1));
					//装货地点要求
					$("#inLoadRegion").val(loadResults);
					//卸货地点要求
					$("#inUnloadRegion").val(unloadResults);
					
					//获取orderNo
					var orderNo=data.datas.pallet.orderNo;
					recordOrderNo=orderNo;
					
				}else{
					message($.i18n("ITSH405"))
				}
			},
			error: function(data) {
				message($.i18n(data.message));
	        }
	 	});
 	}
  	
 	
 	
  	//查询货盘详情，拼接装货量、装货地点要求、卸货地点要求
  	/*if (palletUuid){
  		var paramsPallet = {};
		paramsPallet.uuid=palletUuid;
	 	$.ajax({
    		type: "POST",
    		url: shipServer+"/pallet/palleDetail.json",
    		data: JSON.stringify(paramsPallet),
			dataType: "json",
			contentType:"application/json",
    		async: false,
    		success: function(data) {
				if(data.status == 0){
					
					//货盘信息回显
					//货盘信息
					var pallet=data.datas.pallet;
					//装港信息
					var loadArr=data.datas.listLoad;
					//卸港信息
					var unloadArr=data.datas.listUnload;
					//装港区域信息
					var loadRegionArr=data.datas.listRegion;
					//卸港区域信息
					var unloadRegionArr=data.datas.listUnregion;
					
					//油品名称
					$(".oilName").html(pallet.oilName);	
					
					var load="";//装港/装港区域
					var unload="";//卸港/卸港区域
					
					//拼接装港信息
					var loads="";
					var loadQuantitys="";
					for(var i=0;i<loadArr.length;i++){
						var loadPort=loadArr[i].loadPort;
						if(loadPort!=null && loadPort!=''){
							loads+=loadPort+"/";
						}
						var quantity=loadArr[i].quantity;	//获取数量
						loadQuantitys+=quantity+"/"
					}
					//拼接卸港信息
					var unloads="";
					for(var i=0;i<unloadArr.length;i++){
						//装港/装港区域
						var unloadPort=unloadArr[i].unloadPort;
						if(unloadPort!=null && unloadPort!=''){
							unloads+=unloadPort+"/";
						}
					}
					
					//拼接装港区域信息
					var loadRegions="";
					var loadRegionQuantitys="";
					for(var i=0;i<loadRegionArr.length;i++){
						//装港/装港区域
						var loadRegion=loadRegionArr[i].loadRegion;
						if(loadRegion!=null && loadRegion!=''){
							loadRegions+=loadRegion+"/";
						}
						var quantity=loadRegionArr[i].quantity;	//获取数量
						loadRegionQuantitys+=quantity+"/";
					}
					//拼接卸港区域信息
					var unloadRegions="";
					for(var i=0;i<unloadRegionArr.length;i++){
						//装港/装港区域
						var unloadRegion=unloadRegionArr[i].unloadRegion;
						if(unloadRegion!=null && unloadRegion!=''){
							unloadRegions+=unloadRegion+"/";
						}
					}
					
					//切除字符串最后 "/"
					var loadResults=splitStr(loads+loadRegions);
					var unloadResults=splitStr(unloads+unloadRegions);
					var quantitys=splitStr(loadQuantitys+loadRegionQuantitys);
					
					//拼接装港/装港区
					$(".loadPortOrRegion").html(loadResults);
					//拼接卸港/卸港区
					$(".unLoadPortOrRegion").html(unloadResults);
					//数量
					$(".quantity").html(quantitys);	
					
					//装期LAYCAN
					var laycanBegTimeStamp = pallet.laycanBeg;	//laycanBeg
					if(laycanBegTimeStamp!=null){
						var laycanBeg = new Date(laycanBegTimeStamp).Format("yyyy-MM-dd");
						$(".laycanBeg").html(laycanBeg);
					}else{
						$(".laycanBeg").html("");
					}
					var laycanEndTimeStamp = pallet.laycanEnd;	//laycanEnd
					if(laycanEndTimeStamp!=null){
						var laycanEnd = new Date(laycanEndTimeStamp).Format("yyyy-MM-dd");
						$(".laycanEnd").html(laycanEnd);
					}else{
						$(".laycanEnd").html("");
					}
					
					//油品名称
					var oilName = pallet.oilName;
					if(oilName!='' && oilName!=null){
						$(".oilName").html(pallet.oilName);	
					}else{
						$(".oilNameShow").hide();
					}
					
					$(".shipAgeBeg").html(pallet.shipAgeBeg);	//船龄开始
					$(".shipAgeEnd").html(pallet.shipAgeEnd);	//船龄结束
					$(".shipType").html(pallet.shipType);	//船型
					$(".companyName").html(pallet.companyName);	//公司
					$(".palletRemark").html(pallet.remark);	//备注
					
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
  	}*/
  	


  	//立即询盘
	$('.submitBtn').on('click',function(){
		
		/*if(!$('#read').is(":checked")){
			message($.i18n("JAVASCRIPT00202"));
			return false;
		}*/
		
		//意向装期 LAYCAN校验
		if($("#inPactBeg").val()=="") {
			layer.alert($.i18n("JAVASCRIPT0063"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
				layer.close(index);
			});
			return;
		}
		if($("#inPactEnd").val()=="") {
			layer.alert($.i18n("JAVASCRIPT0064"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
				layer.close(index);
			});
			return;
		}
		//意向装货量校验
		if($("#inMinQuantity").val()=="") {
			layer.alert($.i18n("JAVASCRIPT0065"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
				layer.close(index);
			});
			return;
		}
		//还盘装货地点要求校验
		if($.trim($("#inLoadRegion").val())=="") {
			layer.alert($.i18n("JAVASCRIPT0067"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
				layer.close(index);
			});
			return;
		}
		//还盘卸货地点要求校验
		if($.trim($("#inUnloadRegion").val())=="") {
			layer.alert($.i18n("JAVASCRIPT0068"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
				layer.close(index);
			});
			return;
		}
		
		
	    //弹框运行
	    layer.confirm($.i18n("JAVASCRIPT0070"),{title:$.i18n("001"),btn:[$.i18n("002"),$.i18n("003")]}, function(index){
	    	layer.close(index);
	    	var params = {};
	    	params.shipPlateUuid = shipPlateUuid;
	    	params.palletUuid = palletUuid;
	    	
			params.inPactBeg = $("#inPactBeg").val();
			params.inPactEnd = $("#inPactEnd").val();
			params.inMinQuantity = delNumFormat($("#inMinQuantity").val());
			params.inLoadRegion = $("#inLoadRegion").val();
			params.inUnloadRegion = $("#inUnloadRegion").val();
			params.inDockTime = delNumFormat($("#inDockTime").val());
			params.inRemark = $("#inRemark").val();
			
			
			$.ajax({
	    		type: "POST",
	    		url: shipServer+"/intention/checkAgainIntention.json",
	    		data: JSON.stringify(params),
				dataType: "json",
				contentType:"application/json",
	    		async: false,
	    		success: function(data) {
					if(data.status == 0){
						var arr=[];
						arr=data.datas;
						if(arr!=null && arr.length>0){	
							//存在询盘
							layer.alert($.i18n("JAVASCRIPT00239"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
							    layer.close(index);
							});
							return;
							
						}else{
							//不存在询盘，立即询盘
							$.ajax({
					    		type: "POST",
					    		url: shipServer+"/intention/saveIntention.json",
					    		data: JSON.stringify(params),
								dataType: "json",
								contentType:"application/json",
					    		async: false,
					    		success: function(data) {
									if(data.status == 0){
										layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
											//跳转到租船需求管理
											if(recordOrderNo=='1'){	
												window.location.href=shipServer+"/pallet/palletQueryList.htm";	//有订单
											}else if(recordOrderNo=='0'){	
												window.location.href=shipServer+"/pallet/palletList.htm";	//无订单
											}
										});
									}else{
										layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
											window.location.href = document.referrer;
										});
									}
								},
								error: function(data) {
									message($.i18n(data.message));
						        }
					    	});
						}
					}else{
						layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
							layer.close(index);
							//window.location.href = document.referrer;
						});
					}
				},
				error: function(data) {
					message($.i18n(data.message));
		        }
	    	});
	    });
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

//切除字符串最后 "/"
var splitStr=function(str){
	var result="";
	var index=str.lastIndexOf("/");
	result=str.substring(0,index);
	return result;
}


//错误信息
function message(mess){
	layer.alert(mess, {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
		layer.close(index);
	});
}