layui.use(['layer', 'form', 'jquery','laypage', 'upload','table', 'laydate','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var upload = layui.upload,
	laydate = layui.laydate,
	own = layui.own;
	//日期；初始化
	function startDate(){
		$('.time').each(function(){
			laydate.render({
			    elem: $(this).get(0), //指定元素
			    lang: lang_ver
			})
		})
	}
	startDate();
	//计算水杂
	$('.calculate').change(function(){
		var blHairBarrel = $('.blHairBarrel').val();
		var blNightstool = $('.blNightstool').val();
		if(blHairBarrel!=""&&blNightstool!=""&&blHairBarrel!=null&&blNightstool!=null){
			blHairBarrel = delNumFormat(blHairBarrel);
			blNightstool = delNumFormat(blNightstool);
			var waterImpCon = $.subduction(blHairBarrel,blNightstool);
			waterImpCon = formatNumber(waterImpCon,2,1);
			$('.waterImpCon').val(waterImpCon);
		}
	});
	
	function more(){
		$('.time').each(function(index){
			console.log()
			$(this).attr('lay-key',new Date().getTime()+index);
		});
		startDate();
	}
	var uuid = own.getHref('uuid');
	if (!uuid){message($.i18n("JAVASCRIPT00101"));};
	//货物卸港信息
	$('#unload').on('click',function(){
		window.location.href=shipServer+"/shipPact/goodsUnload.htm?uuid="+uuid;
	});
	//返回
	$('.returnFn').on('click',function(){
		//window.location.href=shipServer+"/shipPact/shipPactList.htm";
		 layer.confirm($.i18n("JAVASCRIPT0022"), {title:$.i18n("001"),btn:[$.i18n("002"),$.i18n("003")]}, function(index){
			 window.close();
		 });
	});
	
	//提交
	$('#save').on('click',function(){
		if (!uuid){message($.i18n("JAVASCRIPT00101")); return false;};
		var arr =[];
		var flag =true;
		$('.charter-cont').each(function(){
			var obj2 = own.serializeObject($(this).find('.form'));
			
			// 去除千分位
			// 提单净桶
			/*obj2.blNightstool = delNumFormat(obj2.blNightstool);
			// 提单毛桶
			obj2.blHairBarrel = delNumFormat(obj2.blHairBarrel);
			// 提单净吨
			obj2.blNetTonnage = delNumFormat(obj2.blNetTonnage);
			// 提单毛吨
			obj2.blHairTonnage = delNumFormat(obj2.blHairTonnage);
			// API
			obj2.api = delNumFormat(obj2.api);
			var waterImpCons = $(this).find('.waterImpCon').val();
			// 水杂含量(桶)
			obj2.waterImpCon = delNumFormat(waterImpCons);
			// 装港明水数量(桶)
			obj2.waterQuantity = delNumFormat(obj2.waterQuantity);
			// 船量毛桶
			obj2.shHairBarrel = delNumFormat(obj2.shHairBarrel);
			// 船量毛吨
			obj2.shHairTonnage = delNumFormat(obj2.shHairTonnage);
			// 船量净桶
//			obj2.shNetBarrel = delNumFormat(obj2.shNetBarrel);
			// 船量净吨
//			obj2.shNetTonnage = delNumFormat(obj2.shNetTonnage);
			// 短量比例%（桶）
			obj2.ratioBarrel = delNumFormat(obj2.ratioBarrel);
			// 短量比例%（吨）
			obj2.ratioTonnage = delNumFormat(obj2.ratioTonnage);
			// OBQ
			obj2.quantity = delNumFormat(obj2.quantity);
			
			 var num =  /^-?\d*\.?\d+$/;  
			 if (obj2.ratioBarrel != null && obj2.ratioBarrel != ''){
				 if(!num.test(obj2.ratioBarrel)){   message('短量比例%（桶）输入不正确');flag=false;return false;  } 
			 }
			 if (obj2.ratioTonnage != null && obj2.ratioTonnage != ''){
				 if(!num.test(obj2.ratioTonnage)){   message('短量比例%（吨）输入不正确');flag=false;return false;  } 
			 }*/
			var types=$('#type').val();
			if (types =='0'){
				
				obj2.billOfLadingName = $(this).find('.billOfLading').find('input').attr('title');
				obj2.billOfLadingPath = $(this).find('.billOfLading').find('input').attr('data-address');
				obj2.cerOfQuantityName = $(this).find('.cerOfQuantity').find('input').attr('title');
				obj2.cerOfQuantityPath = $(this).find('.cerOfQuantity').find('input').attr('data-address');
				obj2.cerOfQualityName = $(this).find('.cerOfQuality').find('input').attr('title');
				obj2.cerOfQualityPath = $(this).find('.cerOfQuality').find('input').attr('data-address');
				obj2.cerOfOriginalName = $(this).find('.cerOfOriginal').find('input').attr('title');
				obj2.cerOfOriginalPath = $(this).find('.cerOfOriginal').find('input').attr('data-address');
				obj2.manifestName = $(this).find('.manifest').find('input').attr('title');
				obj2.manifestPath = $(this).find('.manifest').find('input').attr('data-address');
				
			} else {
				 message($.i18n("JAVASCRIPT0098"));flag=false;return false; 
			}
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
			url: shipServer+"/shipment/saveShipment.json",
			data: JSON.stringify(obj),
			dataType: "json",
			contentType:"application/json",
			success: function(data) {
				layer.close(loadIndex);
				console.log(data)
				if(data.status == 0){
					layer.alert($.i18n("JAVASCRIPT00102"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) { layer.close(index);
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
			url: shipServer+"/shipment/findShipmentDeatil.json",
			data: JSON.stringify(obj),
			dataType: "json",
			contentType:"application/json",
			success: function(data) {
				console.log(data)
				if(data.status == 0){
					var arr = data.datas;
					if ( arr != null && arr.length > 0){
						var f=arr[0].type;
						//判断是否有订单租船
						if (f =='0'){
							$('.orders').attr('hidden','hidden');
							$('.unOrders').removeAttr('hidden','hidden');
							$('#save').removeAttr('hidden','hidden');
							$('#type').val('0');
						} else {
							$('.unOrders').attr('hidden','hidden');
							$('.orders').removeAttr('hidden','hidden');
							$('#type').val('1');
						}
						
						$('.all').removeAttr('hidden','true');
						for (var i = 0; i < arr.length; i++) {
							var dd = arr[i];
							if (i == 0){
								$('.loadPort').val(dd.loadPort);
								$('.oilType').val(dd.oilType);
								$('.agreementCode').val(dd.agreementCode);
								
								if (f =='0'){
									$('.billOfLading').find('#billOfLadingFile1').attr('title',dd.billOfLadingName).attr('data-address',dd.billOfLadingPath).val(dd.billOfLadingName);
									$('.cerOfQuantity').find('#cerOfQuantityFile1').attr('title',dd.cerOfQuantityName).val(dd.cerOfQuantityName).attr('data-address',dd.cerOfQuantityPath);
									$('.cerOfQuality').find('#cerOfQualityFile1').attr('title',dd.cerOfQualityName).val(dd.cerOfQualityName).attr('data-address',dd.cerOfQualityPath);
									$('.cerOfOriginal').find('#cerOfOriginalFile1').attr('title',dd.cerOfOriginalName).val(dd.cerOfOriginalName).attr('data-address',dd.cerOfOriginalPath);
									$('.manifest').find('#manifestFile1').attr('title',dd.manifestName).val(dd.manifestName).attr('data-address',dd.manifestPath);
									filex(dd.billOfLadingName,'.billOfLading');
									filex(dd.cerOfQuantityName,'.cerOfQuantity');
									filex(dd.cerOfQualityName,'.cerOfQuality');
									filex(dd.cerOfOriginalName,'.cerOfOriginal');
									filex(dd.manifestName,'.manifest');
								} else {
									if (dd.blDate != null){$('.blDate').val(new Date(dd.blDate).Format('yyyy-MM-dd'));}
									$('.blNightstool').val(formatNumber(dd.blNightstool,6,1));
									$('.blHairBarrel').val(formatNumber(dd.blHairBarrel,6,1));
									$('.blNetTonnage').val(formatNumber(dd.blNetTonnage,6,1));
									$('.blHairTonnage').val(formatNumber(dd.blHairTonnage,6,1));
									$('.api').val(formatNumber(dd.api,6,1));
									$('.waterImpCon').val(formatNumber(dd.waterImpCon,6,1));
									$('.waterQuantity').val(formatNumber(dd.waterQuantity,6,1));
									$('.shHairBarrel').val(formatNumber(dd.shHairBarrel,6,1));
									$('.shHairTonnage').val(formatNumber(dd.shHairTonnage,6,1));
									$('.ratioBarrel').val(formatNumber(dd.ratioBarrel,6,1));
									$('.ratioTonnage').val(formatNumber(dd.ratioTonnage,6,1));
//								$('.shNetBarrel').val(formatNumber(dd.shNetBarrel,2,1));
//								$('.shNetTonnage').val(formatNumber(dd.shNetTonnage,3,1));
									$('.quantity').val(formatNumber(dd.quantity,6,1));
									$('.remark').val(dd.remark);
									$('.shGrossCubicMeter').val(formatNumber(dd.shGrossCubicMeter,6,1));
									$('.shNetCubicMeter').val(formatNumber(dd.shNetCubicMeter,6,1));
									$('.shDensity').val(formatNumber(dd.shDensity,6,1));
								}
							} else {
								var indexs=i+1;
								var cloneDiv=$('.charter-cont:eq(0)').clone(true);
								cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
								cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
								cloneDiv.find('.inps').attr('hidden','true');
								cloneDiv.find('.c-int').val('');	
								cloneDiv.find('.loadPort').val(dd.loadPort);
								cloneDiv.find('.oilType').val(dd.oilType);
								cloneDiv.find('.agreementCode').val(dd.agreementCode);
								cloneDiv.find('.clearFile').attr('hidden','hidden');
								cloneDiv.find('.clearFile').parent('div').find('a').removeClass('file1Fns').addClass('file1Fn');
								if (f == '0'){
									//上传附件
									
									//生成动态id
									cloneDiv.find('.unOrders').find('input').attr('data-address','');
									cloneDiv.find('.unOrders').find('input').attr('title','');
									cloneDiv.find('.unOrders').find('input').val('');
									cloneDiv.find('.billOfLading').find('input').attr('id','billOfLadingFile'+indexs);
									cloneDiv.find('.billOfLading').find('a').attr('id','billOfLadingBtn'+indexs);
									cloneDiv.find('.cerOfQuantity').find('input').attr('id','cerOfQuantityFile'+indexs);
									cloneDiv.find('.cerOfQuantity').find('a').attr('id','cerOfQuantityBtn'+indexs);
									cloneDiv.find('.cerOfQuality').find('input').attr('id','cerOfQualityFile'+indexs);
									cloneDiv.find('.cerOfQuality').find('a').attr('id','cerOfQualityBtn'+indexs);
									cloneDiv.find('.cerOfOriginal').find('input').attr('id','cerOfOriginalFile'+indexs);
									cloneDiv.find('.cerOfOriginal').find('a').attr('id','cerOfOriginalBtn'+indexs);
									cloneDiv.find('.manifest').find('input').attr('id','manifestFile'+indexs);
									cloneDiv.find('.manifest').find('a').attr('id','manifestBtn'+indexs);
									
									//赋值
									cloneDiv.find('.billOfLading').find('.billOfLadingFile').attr('title',dd.billOfLadingName).val(dd.billOfLadingName).attr('data-address',dd.billOfLadingPath);
									cloneDiv.find('.cerOfQuantity').find('.cerOfQuantityFile').attr('title',dd.cerOfQuantityName).val(dd.cerOfQuantityName).attr('data-address',dd.cerOfQuantityPath);
									cloneDiv.find('.cerOfQuality').find('.cerOfQualityFile').attr('title',dd.cerOfQualityName).val(dd.cerOfQualityName).attr('data-address',dd.cerOfQualityPath);
									cloneDiv.find('.cerOfOriginal').find('.cerOfOriginalFile').attr('title',dd.cerOfOriginalName).val(dd.cerOfOriginalName).attr('data-address',dd.cerOfOriginalPath);
									cloneDiv.find('.manifest').find('.manifestFile').attr('title',dd.manifestName).val(dd.manifestName).attr('data-address',dd.manifestPath);
									
									filex(dd.billOfLadingName,cloneDiv.find('.billOfLading'));
									filex(dd.cerOfQuantityName,cloneDiv.find('.cerOfQuantity'));
									filex(dd.cerOfQualityName,cloneDiv.find('.cerOfQuality'));
									filex(dd.cerOfOriginalName,cloneDiv.find('.cerOfOriginal'));
									filex(dd.manifestName,cloneDiv.find('.manifest'));
									var cloneDivs=$('.accClone:eq(0)').clone();
									cloneDivs.find('.billOfLadingAcc').attr('id','billOfLadingAcc'+indexs);
									cloneDivs.find('.cerOfQuantityAcc').attr('id','cerOfQuantityAcc'+indexs);
									cloneDivs.find('.cerOfQualityAcc').attr('id','cerOfQualityAcc'+indexs);
									cloneDivs.find('.cerOfOriginalAcc').attr('id','cerOfOriginalAcc'+indexs);
									cloneDivs.find('.manifestAcc').attr('id','manifestAcc'+indexs);
									$('.accAll').append(cloneDivs);
									
								} else {
									//赋值
									if (dd.blDate != null){cloneDiv.find('.blDate').val(new Date(dd.blDate).Format('yyyy-MM-dd'));}
									cloneDiv.find('.blNightstool').val(formatNumber(dd.blNightstool,6,1));
									cloneDiv.find('.blHairBarrel').val(formatNumber(dd.blHairBarrel,6,1));
									cloneDiv.find('.blNetTonnage').val(formatNumber(dd.blNetTonnage,6,1));
									cloneDiv.find('.blHairTonnage').val(formatNumber(dd.blHairTonnage,6,1));
									cloneDiv.find('.api').val(formatNumber(dd.api,6,1));
									cloneDiv.find('.waterImpCon').val(formatNumber(dd.waterImpCon,6,1));
									cloneDiv.find('.waterQuantity').val(formatNumber(dd.waterQuantity,6,1));
									cloneDiv.find('.shHairBarrel').val(formatNumber(dd.shHairBarrel,6,1));
									cloneDiv.find('.shHairTonnage').val(formatNumber(dd.shHairTonnage,6,1));
//								cloneDiv.find('.shNetTonnage').val(formatNumber(dd.shNetTonnage,3,1));
//								cloneDiv.find('.shNetBarrel').val(formatNumber(dd.shNetBarrel,2,1));
									cloneDiv.find('.ratioBarrel').val(formatNumber(dd.ratioBarrel,6,1));
									cloneDiv.find('.ratioTonnage').val(formatNumber(dd.ratioTonnage,6,1));
									cloneDiv.find('.quantity').val(formatNumber(dd.quantity,6,1));
									cloneDiv.find('.remark').val(dd.remark);
									cloneDiv.find('.shGrossCubicMeter').val(formatNumber(dd.shGrossCubicMeter,6,1));
									cloneDiv.find('.shNetCubicMeter').val(formatNumber(dd.shNetCubicMeter,6,1));
									cloneDiv.find('.shDensity').val(formatNumber(dd.shDensity,6,1));
									
								}
								
								$('.all').append(cloneDiv);
								more();
								
								//初始化上传方法
								fileUp('billOfLadingAcc'+indexs, '#billOfLadingBtn'+indexs,'transport','#billOfLadingFile'+indexs,'#billOfLadingAcc'+indexs);
								fileUp('cerOfQuantityAcc'+indexs, '#cerOfQuantityBtn'+indexs,'transport','#cerOfQuantityFile'+indexs,'#cerOfQuantityAcc'+indexs);
								fileUp('cerOfQualityAcc'+indexs, '#cerOfQualityBtn'+indexs,'transport','#cerOfQualityFile'+indexs,'#cerOfQualityAcc'+indexs);
								fileUp('cerOfOriginalAcc'+indexs, '#cerOfOriginalBtn'+indexs,'transport','#cerOfOriginalFile'+indexs,'#cerOfOriginalAcc'+indexs);
								fileUp('manifestAcc'+indexs, '#manifestBtn'+indexs,'transport','#manifestFile'+indexs,'#manifestAcc'+indexs);
							}
						}
					} else {
						$('#save').attr('hidden','true');
						layer.alert($.i18n("JAVASCRIPT00103"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) { layer.close(index);
						//window.location.href=shipServer+"/shipPact/shipPactList.htm";
						window.close();
					});
					}
				} else {
					message($.i18n(data.message));
				}}
		});
	}
	
	//检查必填
/*	function checkObj(obj){
		var flag = true;
		if ( obj.blDate == null || obj.blDate == ''){message('请选择提单日') ; flag =false;return false;}
		if ( obj.blNightstool == null || obj.blNightstool == ''){message('请输入提单净桶') ; flag =false;return false;}
		if ( obj.blHairBarrel == null || obj.blHairBarrel == ''){message('请输入提单毛桶') ; flag =false;return false;}
		if ( obj.blNetTonnage == null || obj.blNetTonnage == ''){message('请输入提单净吨') ; flag =false;return false;}
		if ( obj.blHairTonnage == null || obj.blHairTonnage == ''){message('请输入提单毛吨') ; flag =false;return false;}
		if ( obj.api == null || obj.api == ''){message('请输入API') ; flag =false;return false;}
		if ( obj.waterImpCon == null || obj.waterImpCon == ''){message('请输入水杂含量') ; flag =false;return false;}
		if ( obj.waterQuantity == null || obj.waterQuantity == ''){message('请输入装港明水数量') ; flag =false;return false;}
		if ( obj.shHairBarrel == null || obj.shHairBarrel == ''){message('请输入船量毛桶') ; flag =false;return false;}
		if ( obj.shHairTonnage == null || obj.shHairTonnage == ''){message('请输入船量毛吨') ; flag =false;return false;}
		if ( obj.ratioBarrel == null || obj.ratioBarrel == ''){message('请输入短量比例（桶）') ; flag =false;return false;}
		if ( obj.ratioTonnage == null || obj.ratioTonnage == ''){message('请输入短量比例（吨）') ; flag =false;return false;}
		if ( obj.quantity == null || obj.quantity == ''){message('请输入On board Quantity(OBQ)') ; flag =false;return false;}
		return flag;
	}*/
	
	//弹框
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
	
	
	
	
	/*文件 上传*/
	  var temp = '<p>';
	  	temp = temp + '<input type="hidden" class="storePath" value="{{filePath}}" />';
		temp = temp + '<input type="hidden" class="fileName" value="{{fileName}}" />';
		temp = temp + '<input type="hidden" class="fileSize" value="{{fileSize}}" />';
		temp = temp + '<input type="hidden" class="storeName" value="{{fileId}}" /></p>'; 
	  
	  function fileUp(id, clazz,dir,file,id2) {
				var options = {
					multi_selection: false, //设置是否多选
					listId: id, 
					itemTemplate: temp, //设置模板
					signatureUrl: configUitl.getOssServerPath(shipServer,dir),
					dir: dir,
					filters: {
						max_file_size: '3M',
						mime_types: [ //上传文件后缀名
							{
								extensions: "pdf,txt,jpg,xls,xlsx,docx,doc"
							}
						],
					},
					swfUrl: '../../lib/upload/Moxie.swf',
					xapUrl: '../../lib/upload/Moxie.xap',
					success: function(imgUrl) {
						$(file).attr('data-address',imgUrl).val($(id2).find('.fileName').val()).attr('title',$(id2).find('.fileName').val());
						$(file).next('em').removeAttr('hidden','hidden');
						$(file).parent('div').find('a').removeClass('file1Fn').addClass('file1Fns');
//						$(clazz).parents('div').next().show();
//						$(clazz).parents('div').next().find('a').attr('href',imgUrl);
					}
				};
				$(clazz).bsPlupload(options);
			}
	  		
	 fileUp('billOfLadingAcc1', '#billOfLadingBtn1','transport','#billOfLadingFile1','#billOfLadingAcc1');
	 fileUp('cerOfQuantityAcc1', '#cerOfQuantityBtn1','transport','#cerOfQuantityFile1','#cerOfQuantityAcc1');
	 fileUp('cerOfQualityAcc1', '#cerOfQualityBtn1','transport','#cerOfQualityFile1','#cerOfQualityAcc1');
	 fileUp('cerOfOriginalAcc1', '#cerOfOriginalBtn1','transport','#cerOfOriginalFile1','#cerOfOriginalAcc1');
	 fileUp('manifestAcc1', '#manifestBtn1','transport','#manifestFile1','#manifestAcc1');
	
	
});
