layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate','upload'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	own = layui.own,
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;

	// 查看页面
	$('.detail').on('click',function(){
		var uuid = $(this).attr("attr-data");
		
		// 请求弹框
		$.ajax({
		  type:"POST",
		  url:shipServer+"/transport/bomb/platform/agreementDetail.vm",
		  success:function(data) {
			//弹框运行
		 	layer.open({
		 		  skin: 'backbtn-class',
				  type: 1,
				  area: ['850px','580px'],
				  btn: '关闭',
				  btnAlign: 'c' ,//按钮居中
				  title:'查看租船协议',
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
		 	var obj = {};
		 	obj.uuid = uuid;
    	
		 	$.ajax({
	    		type: "POST",
	    		url: shipServer+"/agreement/findAgreementDetail.json",
	    		data: JSON.stringify(obj),
				dataType: "json",
				contentType:"application/json",
	    		success: function(data) {
					if(data.status == 0){
						var dd=data.datas;
						$('.agreementCode').val(dd.agreementCode).attr('title',dd.agreementCode);
						$('.signDate').val(new Date(dd.signDate).Format("yyyy-MM-dd")).attr('title',new Date(dd.signDate).Format("yyyy-MM-dd"));
						$('.shipOwner').val(dd.shipOwner).attr('title',dd.shipOwner);
						$('.secParty').val(dd.secParty).attr('title',dd.secParty);
						$('.shipName').val(dd.shipName).attr('title',dd.shipName);
						$('.quantity').val(formatStr(dd.quantity,3,1)).attr('title',formatStr(dd.quantity,3,1));
						$('.oilType').val(dd.oilType).attr('title',dd.oilType);
						$('.loadPort').val(dd.loadPort).attr('title',dd.loadPort);
						$('.unloadPort').val(dd.unloadPort).attr('title',dd.unloadPort);
						$('.laycan').val(dd.laycan).attr('title',dd.laycan);
						$('.carrierName').val(dd.carrierName).attr('title',dd.carrierName);
						$('.shipOwner').val(dd.shipOwner).attr('title',dd.shipOwner);
						var one = new Date(dd.pactBeg).Format("yyyy-MM-dd hh:mm:ss");
						var two = new Date(dd.pactEnd).Format("yyyy-MM-dd hh:mm:ss");
						var three = one+" -- "+two;
						$('.pactBeg').val(three).attr('title',three);
						$('.pactSpeed').val(formatNumber(dd.pactSpeed,2,1)+" KNOTS，CHOPT WEATHER AND SAFE NAVIGATION PERMITTING").attr('title',formatNumber(dd.pactSpeed,2,1)+" KNOTS，CHOPT WEATHER AND SAFE NAVIGATION PERMITTING");
						$('.wsRes').val(dd.wsRes).attr('title',dd.wsRes);
						$('.ws').val(formatNumber(dd.ws,2,1)).attr('title',formatNumber(dd.ws,2,1));
						$('.demurrage').val(formatNumber(dd.demurrage,2,1)+" USD/PDPR").attr('title',formatNumber(dd.demurrage,2,1)+" USD/PDPR");
						$('.dockTime').val(formatNumber(dd.dockTime,2,1)+" HOURS SHINC").attr('title',formatNumber(dd.dockTime,2,1)+" HOURS SHINC");
						$('.minQuantity').val(formatNumber(dd.minQuantity,3,1)+" MT").attr('title',formatNumber(dd.minQuantity,3,1)+" MT");
						$('.brokerName').val(dd.brokerName).attr('title',dd.brokerName);
						$('.remark').val(dd.remark).attr('title',dd.remark);
						if (dd.accessory != null && dd.accessoryPath != null ){
							$('#btn').show();
							$('.accessory').val(dd.accessory).attr('title',dd.accessory);
							$('#btn').attr('address',dd.accessoryPath);
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
		 	
		 	form.render();
		  }
		})
	});

	// 下载
	$('body').on('click','.xiazai',function(){
		window.location.href=$(this).attr('address');
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
	

});
