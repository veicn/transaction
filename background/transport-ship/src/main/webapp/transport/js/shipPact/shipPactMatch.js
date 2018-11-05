layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	own = layui.own,
	table = layui.table;
	window.$ = layui.jquery;
		  
	var uuid = own.getHref('shipPactUuid')
	var agreeUuid = own.getHref('agreementUuid')
	agreeUuid = decodeURIComponent(agreeUuid)
	var spl = agreeUuid.split(";");
	var arr = [];
 	for (var i = 0; i < spl.length; i++) {
		if (spl[i] != null && spl[i] != ''){
			arr.push(spl[i]);
		}
	}
	//查询经纪人和船东和船名
	if (uuid && agreeUuid){edit(uuid,agreeUuid)};
	function edit(uuid,agreeUuid){
		var obj ={}; obj.uuid=uuid;
		$.ajax({
    		type: "POST",
    		url: shipServer+"/shipPact/findShipPactDetail.json",
    		data: JSON.stringify(obj),
			dataType: "json",
			contentType:"application/json",
    		success: function(data) {
				if(data.status == 0){
					var shipPact = data.datas.shipPact;
					$('.shipPactCode').html(shipPact.pactCode);
					$('.signDate').html(new Date(shipPact.signDate).Format("yyyy-MM-dd"));
					$('.carrierName').html(shipPact.carrierName);
					var one = new Date(shipPact.pactBeg).Format("yyyy-MM-dd hh:mm:ss");
					var two = new Date(shipPact.pactEnd).Format("yyyy-MM-dd hh:mm:ss");
					var three = one+" -- "+two;
					$('.pactBeg').html(three);
					//$('.pactEnd').html();
					$('.pactText').html(shipPact.pactText);
					$('.minQuantity').html(formatNumber(shipPact.minQuantity,3,1)+" MT");
					$('.wsRes').html(shipPact.wsRes);
					$('.demurrage').html(formatNumber(shipPact.demurrage,2,1)+" USD/PDPR");
					$('.dockTime').html(formatNumber(shipPact.dockTime,2,1)+" HOURS SHINC");
					$('.pactSpeed').html(shipPact.pactSpeed+" KNOTS，CHOPT WEATHER AND SAFE NAVIGATION PERMITTING");
					$('.remark').html(shipPact.remark);
					$('.accessory').html(shipPact.accessory);
					$('.shipOwner').html(shipPact.shipOwner);
					$('.brokerName').html(shipPact.brokerName);
					$('.shipName').html(shipPact.shipName);
				} else {
					layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {layer.close(index);});
				}
			},
			error: function(data) {
				layer.alert($.i18n("JAVASCRIPT003"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {layer.close(index);});
	        }
    	});
		
		var params = {};
	 	
	 	params.agreementUuids = arr;
		$.ajax({
    		type: "POST",
    		url: shipServer+"/agreement/findAgreementDetailMany.json",
    		data: JSON.stringify(params),
			dataType: "json",
			contentType:"application/json",
    		success: function(data) {
    			console.log(data)
    			if(data.status == 0){
    				var arrs=data.datas;
    				for (var i = 0; i < arrs.length; i++) {
    					var dd = arrs[i];
						var clone = $('.agree:eq(0)').clone();
						clone.removeAttr('hidden');
						clone.find('.htm').html('');
						clone.find('.agreementCode').html(dd.agreementCode);
						clone.find('.signDate2').html(new Date(dd.signDate).Format("yyyy-MM-dd"));
    					clone.find('.firParty2').html(dd.firParty);
    					clone.find('.secParty2').html(dd.secParty);
    					clone.find('.shipName2').html(dd.shipName);
    					clone.find('.quantity2').html(dd.quantity);
    					clone.find('.accessory2').html(dd.accessory);
    					clone.find('.quantity').html(formatStr(dd.quantity,3,1));
    					clone.find('.oilType').html(dd.oilType);
    					clone.find('.loadPort').html(dd.loadPort);
    					clone.find('.unloadPort').html(dd.unloadPort);
    					clone.find('.laycan').html(dd.laycan);
    					$('.all').append(clone);
					}
				}
			}
	 	});
	}
	//取消
	$('.back').on('click',function(){
		//window.location.href = document.referrer;
		 window.location.href=shipServer+"/shipPact/shipPactList.htm";
	});
	//确认
	$('#submits').on('click',function(){
		var obj = {};
	 	obj.shipPactUuid = uuid;
	 	obj.agreementUuids = arr;
	 	if (!uuid){message($.i18n("JAVASCRIPT0020"));return false;}
	 	if (arr.length == 0){message($.i18n("JAVASCRIPT00142"));return false;}
		$.ajax({
    		type: "POST",
    		url: shipServer+"/waybill/saveWaybillMany.json",
    		data: JSON.stringify(obj),
			dataType: "json",
			contentType:"application/json",
    		success: function(data) {
    			if(data.status == 0){
    				layer.alert($.i18n("JAVASCRIPT00220"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
    					layer.close(index);
    				 window.location.href=shipServer+"/shipPact/shipPactList.htm";
    				});
				} else {
					message($.i18n(data.message));
				}
			},
			error:function(){
				message($.i18n("JAVASCRIPT003"));
			}
	 	});
	});
	//弹出框
	function message(mess){
		layer.alert(mess, {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
			layer.close(index);
		});
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
	
});
