layui.use(['layer', 'form', 'jquery','laypage', 'upload','table', 'laydate','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var upload = layui.upload,
	laydate = layui.laydate,
	own = layui.own;
  
	/*//初始化查询
	var palletUuid = own.getHref('palletUuid');
	if (palletUuid){ initPallet(palletUuid); }
	function initPallet (palletUuid){
		var params = {};
	 	params.palletUuid = palletUuid;
		$.ajax({
    		type: "POST",
    		url: shipServer+"/agreement/findAgreementDetailByPalletUuid.json",
    		data: JSON.stringify(params),
			dataType: "json",
			contentType:"application/json",
    		success: function(data) {
    			console.log(data)
    			if(data.status == 0){
    				var dd=data.datas;
    				$('.agreementCode').html(dd.agreementCode);
					$('.signDate').html(new Date(dd.signDate).Format("yyyy-MM-dd"));
					$('.firParty').html(dd.firParty);
					$('.secParty').html(dd.secParty);
					$('.shipName').html(dd.shipName);
					$('.quantity').html(formatStr(dd.quantity,3,1));
					$('.oilType').html(dd.oilType);
					$('.loadPort').html(dd.loadPort);
					$('.unloadPort').html(dd.unloadPort);
					$('.laycan').html(dd.laycan);
					
					if (dd.accessory != null && dd.accessoryPath != null ){
						$('#btn').parents('span').show();
						$('.accessory').html(dd.accessory);
						$('#btn').attr('href',dd.accessoryPath);
					}
				}
			}
	 	});
	}
	//初始化查询
	var uuid = own.getHref('uuid');
	if (uuid){ init(uuid); }
	function init (uuid){
		var params = {};
		params.uuid = uuid;
		$.ajax({
			type: "POST",
			url: shipServer+"/agreement/findAgreementDetail.json",
			data: JSON.stringify(params),
			dataType: "json",
			contentType:"application/json",
			success: function(data) {
				console.log(data)
				if(data.status == 0){
					var dd=data.datas;
					$('.agreementCode').html(dd.agreementCode);
					$('.signDate').html(new Date(dd.signDate).Format("yyyy-MM-dd"));
					$('.firParty').html(dd.firParty);
					$('.secParty').html(dd.secParty);
					$('.shipName').html(dd.shipName);
					$('.quantity').html(formatStr(dd.quantity,3,1));
					$('.oilType').html(dd.oilType);
					$('.loadPort').html(dd.loadPort);
					$('.unloadPort').html(dd.unloadPort);
					$('.laycan').html(dd.laycan);
					
					$('.carrierName').html(dd.carrierName);
					$('.shipOwner').html(dd.shipOwner);
					var one = new Date(dd.pactBeg).Format("yyyy-MM-dd hh:mm:ss");
					var two = new Date(dd.pactEnd).Format("yyyy-MM-dd hh:mm:ss");
					var three = one+" -- "+two;
					$('.pactBeg').html(three);
					$('.pactSpeed').html(formatNumber(dd.pactSpeed,2,1)+" KNOTS，CHOPT WEATHER AND SAFE NAVIGATION PERMITTING");
					$('.wsRes').html(dd.wsRes);
					$('.demurrage').html(formatNumber(dd.demurrage,2,1)+" USD/PDPR");
					$('.dockTime').html(formatNumber(dd.dockTime,2,1)+" HOURS SHINC");
					$('.minQuantity').html(formatNumber(dd.minQuantity,3,1)+" MT");
					$('.brokerName').html(dd.brokerName);
					$('.remark').html(dd.remark);
					if (dd.accessory != null && dd.accessoryPath != null ){
						$('#btn').parents('span').show();
						$('.accessory').html(dd.accessory);
						$('#btn').attr('href',dd.accessoryPath);
					}
				}
			}
		});
	};*/
	var uuid ="";
	(function(){
		 
		 // 接收地址栏参数
			function GetRequest() {   
			   var url = location.search; //获取url中"?"符后的字串   
			   var theRequest = new Object();   
			   if (url.indexOf("?") != -1) {   
			      var str = url.substr(1);   
			      strs = str.split("&");   
			      for(var i = 0; i < strs.length; i ++) {   
			         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);   
			      }   
			   }   
			   return theRequest;   
			}   
			var data = GetRequest();
			uuid = data.uuid;
		 })();
	
	(function(){
		var params = {};
		params.uuid = uuid;
		$.ajax({
			type: "POST",
			url:shipServer+"/agreement/findAgreementDetail.json",
			data: JSON.stringify(params),
			dataType: "json",
			contentType:"application/json",
			success: function(data) {
				console.log(data)
				if(data.status == 0){
					var dd=data.datas;
					$('.agreementCode').html(dd.agreementCode);
					$('.signDate').html(new Date(dd.signDate).Format("yyyy-MM-dd"));
					$('.shipOwner').html(dd.shipOwner);
					$('.secParty').html(dd.secParty);
					$('.shipName').html(dd.shipName);
					$('.quantity').html(formatStr(dd.quantity,3,1));
					$('.oilType').html(dd.oilType);
					$('.loadPort').html(dd.loadPort);
					$('.unloadPort').html(dd.unloadPort);
					$('.laycan').html(dd.laycan);
					$('.carrierName').html(dd.carrierName);
					$('.shipOwner').html(dd.shipOwner);
					var one = new Date(dd.pactBeg).Format("yyyy-MM-dd hh:mm:ss");
					var two = new Date(dd.pactEnd).Format("yyyy-MM-dd hh:mm:ss");
					var three = one+" -- "+two;
					$('.pactBeg').html(three);
					if(dd.pactSpeed!=null && dd.pactSpeed!=""){
						$('.pactSpeed').html(formatNumber(dd.pactSpeed,2,1)+" KNOTS，CHOPT WEATHER AND SAFE NAVIGATION PERMITTING");
					}
					$('.wsRes').html(dd.wsRes);
					$('.ws').html(formatNumber(dd.ws,2,1));
					if(dd.demurrage!=null && dd.demurrage!=""){
						$('.demurrage').html(formatNumber(dd.demurrage,2,1)+" USD/PDPR");
					}
					if(dd.dockTime!=null && dd.dockTime!=""){
						$('.dockTime').html(formatNumber(dd.dockTime,2,1)+" HOURS SHINC");
					}
					if(dd.minQuantity!=null && dd.minQuantity!=""){
						$('.minQuantity').html(formatNumber(dd.minQuantity,3,1)+" MT");
					}
					$('.brokerName').html(dd.brokerName);
					$('.remark').html(dd.remark);
					if (dd.accessory != null && dd.accessoryPath != null ){
						$('#btn').parents('span').show();
						$('.accessory').html(dd.accessory);
						$('#btn').attr('address',dd.accessoryPath);
					}
				}
			}
		});
	})();
	
	//取消
	$('.cancel').on('click',function(){
		window.location.href = document.referrer;
	});

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
	
	//弹框
	function message(mess){
		layer.alert(mess, function(index) {
			layer.close(index);
		});
	}
	
});
