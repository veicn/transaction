layui.use(['layer', 'form', 'jquery','laypage', 'upload','table', 'laydate','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var upload = layui.upload,
	laydate = layui.laydate,
	own = layui.own;
	
	var uuid = own.getHref('uuid');
	if (!uuid){message($.i18n("JAVASCRIPT0020"));};
	//货物卸港信息
	$('#load').on('click',function(){
		window.location.href=shipServer+"/shipPactNew/goodsLoad.htm?uuid="+uuid;
	});
	//返回
	$('.returnFn').on('click',function(){
		//window.location.href=shipServer+"/shipPact/shipPactList.htm";
		 layer.confirm($.i18n("JAVASCRIPT0022"),{title:$.i18n("001"),btn:[$.i18n("002"),$.i18n("003")]}, function(index){
			 window.close();
		 });
	});
	//提交
	$('#save').on('click',function(){
		if (!uuid){message($.i18n("JAVASCRIPT0020")); return false;};
		var arr =[];
		var flag =true;
		$('.charter-cont').each(function(){
				
				obj2.accessoryName = $(this).find('.accessory').find('input').attr('title');
				obj2.accessoryPath = $(this).find('.accessory').find('input').attr('data-address');
				
			arr.push(obj2);
		});
		if (!flag){
			return false;
		}
		var obj = {};
		obj.list =arr;
		obj.waybillUuid=uuid;
		console.log(obj);
		  var loadIndex = layer.load(0, {shade: false});
		$.ajax({
			type: "POST",
			url: shipServer+"/disburden/saveDisburdent.json",
			data: JSON.stringify(obj),
			dataType: "json",
			contentType:"application/json",
			success: function(data) {
				console.log(data)
				layer.close(loadIndex);
				if(data.status == 0){
					layer.alert($.i18n("JAVASCRIPT0099"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) { layer.close(index);
						//window.location.href=shipServer+"/shipPact/shipPactList.htm";
					});
				} else { message($.i18n(data.message)); }}
		});
	});
	
	if (uuid != null){
		init(uuid)
	}
	//初始化查询
	function init(uuid){
		var obj={};
		obj.waybillUuid=uuid
		$.ajax({
			type: "POST",
			url: shipServer+"/disburden/findDisburdenDeatil.json",
			data: JSON.stringify(obj),
			dataType: "json",
			contentType:"application/json",
			success: function(data) {
				console.log(data)
				if(data.status == 0){
					var arr =data.datas;
					if (arr != null && arr.length>0){
						$('.all').removeAttr('hidden','true');
						for (var i = 0; i < arr.length; i++) {
							var dd = arr[i];
							if ( i == 0){
								$('.unloadPort').html(dd.unloadPort);
								$('.oilType').html(dd.oilType);
								$('.agreementCode').html(dd.agreementCode);
								$('.shipHairBar').html(formatNumber(dd.shipHairBar,6,1));
								$('.shipHairTon').html(formatNumber(dd.shipHairTon,6,1));
								$('.commHairBar').html(formatNumber(dd.commHairBar,6,1));
								$('.commHairTon').html(formatNumber(dd.commHairTon,6,1));
								$('.commCleanBar').html(formatNumber(dd.commCleanBar,6,1));
								$('.commCleanTon').html(formatNumber(dd.commCleanTon,6,1));
								$('.counCleanBar').html(formatNumber(dd.counCleanBar,6,1));
								$('.counCleanTon').html(formatNumber(dd.counCleanTon,6,1));
								$('.potHairBar').html(formatNumber(dd.potHairBar,6,1));
								$('.potHairTon').html(formatNumber(dd.potHairTon,6,1));
								$('.blHairBarRate').html(formatNumber(dd.blHairBarRate,6,1));
								$('.blHairTonRate').html(formatNumber(dd.blHairTonRate,6,1));
								$('.counCleanBarRate').html(formatNumber(dd.counCleanBarRate,6,1));
								$('.counCleanTonRate').html(formatNumber(dd.counCleanTonRate,6,1));
								$('.robQuanatity').html(formatNumber(dd.robQuanatity,6,1));
								$('.remark').html(dd.remark);
								if (dd.cod != null){$('.cod').html(new Date(dd.cod).Format('yyyy-MM-dd hh:mm:ss'));}
								$('.shipHairBarVef').html(formatNumber(dd.shipHairBarVef,6,1));
								$('.shipHairTonVef').html(formatNumber(dd.shipHairTonVef,6,1));
								$('.api').html(formatNumber(dd.api,6,1));
								$('.waterImpCon').html(formatNumber(dd.waterImpCon,6,1));
								$('.waterQuantity').html(formatNumber(dd.waterQuantity,6,1));
								$('.shGrossCubicMeter').html(formatNumber(dd.shGrossCubicMeter,6,1));
								$('.shNetCubicMeter').html(formatNumber(dd.shNetCubicMeter,6,1));
								$('.shDensity').html(formatNumber(dd.shDensity,6,1));
							} else {
								var indexs=i+1;
								var cloneDiv=$('.charter-cont:eq(0)').clone(true);
								cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
								cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
								cloneDiv.find('.inps').attr('hidden','true');
								cloneDiv.find('.c-int').val('');	
								
								cloneDiv.find('.oilType').html(dd.oilType);
								cloneDiv.find('.unloadPort').html(dd.unloadPort);
								cloneDiv.find('.agreementCode').html(dd.agreementCode);
								cloneDiv.find('.clearFile').attr('hidden','hidden');
								cloneDiv.find('.clearFile').parent('div').find('a').removeClass('file1Fns').addClass('file1Fn');
									cloneDiv.find('.shipHairBar').html(formatNumber(dd.shipHairBar,6,1));
									cloneDiv.find('.shipHairTon').html(formatNumber(dd.shipHairTon,6,1));
									cloneDiv.find('.commHairBar').html(formatNumber(dd.commHairBar,6,1));
									cloneDiv.find('.commHairTon').html(formatNumber(dd.commHairTon,6,1));
									cloneDiv.find('.commCleanBar').html(formatNumber(dd.commCleanBar,6,1));
									cloneDiv.find('.commCleanTon').html(formatNumber(dd.commCleanTon,6,1));
									cloneDiv.find('.counCleanBar').html(formatNumber(dd.counCleanBar,6,1));
									cloneDiv.find('.counCleanTon').html(formatNumber(dd.counCleanTon,6,1));
									cloneDiv.find('.potHairBar').html(formatNumber(dd.potHairBar,6,1));
									cloneDiv.find('.potHairTon').html(formatNumber(dd.potHairTon,6,1));
									cloneDiv.find('.blHairBarRate').html(formatNumber(dd.blHairBarRate,6,1));
									cloneDiv.find('.blHairTonRate').html(formatNumber(dd.blHairTonRate,6,1));
									cloneDiv.find('.counCleanBarRate').html(formatNumber(dd.counCleanBarRate,6,1));
									cloneDiv.find('.counCleanTonRate').html(formatNumber(dd.counCleanTonRate,6,1));
									cloneDiv.find('.robQuanatity').html(formatNumber(dd.robQuanatity,6,1));
									cloneDiv.find('.remark').html(dd.remark);
									
									if (dd.cod != null){cloneDiv.find('.cod').html(new Date(dd.cod).Format('yyyy-MM-dd hh:mm:ss'));}
									cloneDiv.find('.shipHairBarVef').html(formatNumber(dd.shipHairBarVef,6,1));
									cloneDiv.find('.shipHairTonVef').html(formatNumber(dd.shipHairTonVef,6,1));
									cloneDiv.find('.api').html(formatNumber(dd.api,6,1));
									cloneDiv.find('.waterImpCon').html(formatNumber(dd.waterImpCon,6,1));
									cloneDiv.find('.waterQuantity').html(formatNumber(dd.waterQuantity,6,1));
									cloneDiv.find('.shGrossCubicMeter').html(formatNumber(dd.shGrossCubicMeter,6,1));
									cloneDiv.find('.shNetCubicMeter').html(formatNumber(dd.shNetCubicMeter,6,1));
									cloneDiv.find('.shDensity').html(formatNumber(dd.shDensity,6,1));
								$('.all').append(cloneDiv);
							}
						}
					} else {
						$('#save').attr('hidden','true');
						layer.alert($.i18n("JAVASCRIPT00100"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) { layer.close(index);
						//window.location.href=shipServer+"/shipPact/shipPactList.htm";
						window.close();
					});
				}
                    initClass();
				} else {
					message($.i18n(data.message));
				}}
		});
	}
	
	
	//弹框
	function message(mess){
		layer.alert(mess , {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
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
