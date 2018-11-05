layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	own = layui.own,
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	
	
	//船盘uuid
	var shipPlateUuid = own.getHref('shipPlateUuid');
	if(shipPlateUuid==false){
		shipPlateUuid="";
	}
	
	//roleType：1船东/经纪人，2货主，3转租船东
	var roleType = own.getHref('roleType');
	
	//操作之后，返回跳转的页面
	var from = own.getHref('from');	
	
	//记录货盘uuid
	var recordPalletUuid="";
	
	
	
	//根据船盘uuid查询船盘信息
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
			}else{
				message($.i18n("ITSH013"))
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
						
					}else{
						message($.i18n("ITSH023"))
					}
				},
				error: function(data) {
					message($.i18n(data.message));
		        }
		 	});
		 	
		},
		error: function(data) {
			message($.i18n(data.message));
        }
 	});
 	
	

	//根据船盘uuid，查询询盘
 	var params = {};
 	params.shipPlateUuid = shipPlateUuid;
 	$.ajax({
		type: "POST",
		url: shipServer+"/intention/findCharterShipConfirm.json",
		data: JSON.stringify(params),
		dataType: "json",
		contentType:"application/json",
		async: false,
		success: function(data) {
			if(data.status == 0){
				
				var boolBackBtn=false;
				
				//获取询盘信息
				var intentionList=data.datas.intentionList;
				//若询盘不为空
				if(intentionList!=null && intentionList.length>0){
					
					//添加信息框
					var randomData = Math.round(Math.random() * 100);
					var confirmInfo = $('.confirmList');
					var cloneDiv=confirmInfo.clone();
					cloneDiv.removeClass('confirmList');	//随机数替换class
					cloneDiv.addClass('confirmList'+randomData);
					cloneDiv.find('.styleShow').removeAttr("style");

					
					var intention=intentionList[0];
					//还盘信息回显
					var coPactBegTimeStamp = intention.coPactBeg;	//回显还盘装期开始
					if(coPactBegTimeStamp!=null){
						var coPactBeg = new Date(coPactBegTimeStamp).Format("yyyy-MM-dd");
						//var etaDate = new Date(etaTimeStamp).Format("yyyy-MM-dd hh:mm:ss");
						//$(".coPactBeg").html(coPactBeg);
						cloneDiv.find('.coPactBeg').html(coPactBeg);
					}else{
						cloneDiv.find('.coPactBeg').html("");
					}
					
					var coPactEndTimeStamp = intention.coPactEnd;	//回显还盘装期结束
					if(coPactEndTimeStamp!=null){
						var coPactEnd = new Date(coPactEndTimeStamp).Format("yyyy-MM-dd");
						cloneDiv.find('.coPactEnd').html(coPactEnd);
					}else{
						cloneDiv.find('.coPactEnd').html("");
					}
					
					//$(".coMinQuantity").html(intention.coMinQuantity);
					cloneDiv.find('.coMinQuantity').html(formatNumber(intention.coMinQuantity,3,1));
					cloneDiv.find('.coLoadRegion').html(intention.coLoadRegion);
					cloneDiv.find('.coUnloadRegion').html(intention.coUnloadRegion);
					cloneDiv.find('.coPactSpeed').html(intention.coPactSpeed);
					cloneDiv.find('.coWs').html(formatNumber(intention.coWs,2,1));
					cloneDiv.find('.coDemurrage').html(formatNumber(intention.coDemurrage,2,1));
					cloneDiv.find('.coDockTime').html(formatNumber(intention.coDockTime,2,1));
					cloneDiv.find('.coPactSpeed').html(intention.coPactSpeed);
					cloneDiv.find('.coCommission').html(formatNumber(intention.coCommission,2,1));
					cloneDiv.find('.coRemark').html(intention.coRemark);
					
					
					//对家信息
					//roleType：1船东/经纪人，2货主，3转租船东
					if(roleType=='1' || roleType=='3'){
						var otherName=intention.epMemberName;
						if(otherName!=null && $.trim(otherName)!=""){
							cloneDiv.find('.otherName').html("( "+otherName+" )");
						}else{
							cloneDiv.find('.otherName').html(null);
						}
					}else if(roleType=='2'){
						var otherName=intention.shipPlateMemberName;
						if(otherName!=null && $.trim(otherName)!=""){
							cloneDiv.find('.otherName').html("( "+otherName+" )");
						}else{
							cloneDiv.find('.otherName').html(null);
						}
					}
					
					
					//设置“完善租船协议”按钮
					//如果当前询盘关联的货盘，AGREEMENT_FLAG=‘1’，不现显示“完善祖传协议”按钮
					var palletUuidData=intention.palletUuid;
					var paramsss = {};
					paramsss.uuid = palletUuidData;
					$.ajax({
						type: "POST",
						url: shipServer+"/pallet/palleDetail.json",
						data: JSON.stringify(paramsss),
						dataType: "json",
						contentType:"application/json",
						async: false,
						success: function(data) {
							if(data.status == 0){
								var palletData=data.datas.pallet;
								var agreementFlag=palletData.agreementFlag;
								if(agreementFlag=='1'){
									//隐藏“完善租船协议”按钮
									cloneDiv.find('.finishAgreementBtn').hide();
								}else{
									//设置按钮
									cloneDiv.find('.finishAgreementBtn').attr("attr-intentionUuid",intention.uuid);
								}
							}else{
								message($.i18n(data.message));
							}
						},
						error: function(data) {
							message($.i18n(data.message));
						}
					});
					
					$('.superDiv').append(cloneDiv);
					boolBackBtn=true;
				}
				
				
				if(!boolBackBtn){
					$('.superDiv').append("<div class='s-btn-box'><button class='s-btn s-btn-primary returnBtn'>"+$.i18n("JAVASCRIPT0025")+"</button></div>");
				}
				
			}else{
				message($.i18n("ITSH405"))
			}
		},
		error: function(data) {
			message($.i18n(data.message));
        }
 	});
  	

  	
  	//完善租船协议
  	$('.finishAgreementBtn').on('click',function(){
  		//type  1询盘2报盘
  		var uuidData=$(this).attr("attr-intentionUuid");
  		window.location.href=shipServer+"/agreement/agreementSave.htm?shipPlateUuid="+shipPlateUuid+"&uuid="+uuidData+"&"+"type=1"+"&roleType=1";
  	});
  	
  	
	//返回
	$('.returnBtn').on('click',function(){
		//window.location.href=shipServer+from;
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
