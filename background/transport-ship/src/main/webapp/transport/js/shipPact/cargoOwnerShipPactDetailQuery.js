layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	own = layui.own,
	table = layui.table;
	window.$ = layui.jquery;
		  
	var uuid = own.getHref('uuid')
	//查询经纪人和船东和船名
	if (uuid){ 	edit(uuid) 	}
	function edit(uuid){
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
					$('.ws').html(shipPact.ws);
					$('.wsRes').html(shipPact.wsRes);
					$('.demurrage').html(formatNumber(shipPact.demurrage,2,1)+" USD/PDPR");
					$('.dockTime').html(formatNumber(shipPact.dockTime,2,1)+" HOURS SHINC");
					$('.pactSpeed').html(formatNumber(shipPact.pactSpeed,2,1)+" KNOTS，CHOPT WEATHER AND SAFE NAVIGATION PERMITTING");
					$('.remark').html(shipPact.remark);
					var acc = shipPact.accessory;
					if (acc != null && acc != "" && shipPact.accessoryPath != null && shipPact.accessoryPath != ""){
						$('.accessory').attr("title",shipPact.accessory);
						$('.accessory').html(acc);
						$('#btn').removeAttr('hidden','true');
						$('#btn').find('a').attr('href',shipPact.accessoryPath);
					}
					$('.shipOwner').html(shipPact.shipOwner);
					$('.brokerName').html(shipPact.brokerName);
					$('.shipName').html(shipPact.shipName);
				} else {
					layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {layer.close(index);});
				}
			},
			error: function(data) {
				layer.alert($.i18n("JAVASCRIPT003"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {layer.close(index);});
	        }
    	});
	}
	
	// 获取页面来源（船盘列表中->租船合同）
	var fromPage = own.getHref('fromPage');
	//取消
	$('.back').on('click',function(){
		//window.location.href = document.referrer;
		if(fromPage!=false && fromPage!=""){
			// 返回到船盘列表页面
			window.location.href=shipServer+fromPage;
			return;
		}
		// 返回到船合同列表
		window.location.href=shipServer+"/shipPact/cargoOwnerShipPactList.htm";
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
