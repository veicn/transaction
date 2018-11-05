layui.use(['layer', 'form', 'jquery','laypage', 'upload','table', 'laydate','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var upload = layui.upload,
	laydate = layui.laydate,
	own = layui.own;
	
	var uuid = own.getHref('uuid');
	if (!uuid){message($.i18n("JAVASCRIPT0097"));};
	//货物卸港信息
	$('#load').on('click',function(){
		window.location.href=shipServer+"/shipPact/goodsLoad.htm?uuid="+uuid;
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
		if (!uuid){message($.i18n("JAVASCRIPT0097")); return false;};
		var arr =[];
		var flag =true;
		$('.charter-cont').each(function(){
			var obj2 = own.serializeObject($(this).find('.form'));
			
			/*// 去除千分位
			// 卸港船毛桶
			obj2.shipHairBar = delNumFormat(obj2.shipHairBar);
			// 卸港船毛吨
			obj2.shipHairTon = delNumFormat(obj2.shipHairTon);
			// 卸港商检毛桶
			obj2.commHairBar = delNumFormat(obj2.commHairBar);
			// 卸港商检毛吨
			obj2.commHairTon = delNumFormat(obj2.commHairTon);
			// 卸港商检净桶
			obj2.commCleanBar = delNumFormat(obj2.commCleanBar);
			// 卸港商检净吨
			obj2.commCleanTon = delNumFormat(obj2.commCleanTon);
			// 卸港国检净桶
			obj2.counCleanBar = delNumFormat(obj2.counCleanBar);
			// 卸港国检净吨
			obj2.counCleanTon = delNumFormat(obj2.counCleanTon);
			// 卸港罐毛桶
			obj2.potHairBar = delNumFormat(obj2.potHairBar);
			// 卸港罐毛吨
			obj2.potHairTon = delNumFormat(obj2.potHairTon);
			// 船/提单短量%(毛桶)
			obj2.blHairBarRate = delNumFormat(obj2.blHairBarRate);
			// 船/提单短量%(毛吨)
			obj2.blHairTonRate = delNumFormat(obj2.blHairTonRate);
			// 船/国检短量%(净桶)
			obj2.counCleanBarRate = delNumFormat(obj2.counCleanBarRate);
			// 船/国检短量%(净吨)
			obj2.counCleanTonRate = delNumFormat(obj2.counCleanTonRate);
			// ROB(桶）
			obj2.robQuanatity = delNumFormat(obj2.robQuanatity);
			
			 var num =  /^-?\d*\.?\d+$/;  
			 if (obj2.blHairBarRate != null && obj2.blHairBarRate != ''){
				 if(!num.test(obj2.blHairBarRate)){   message('船/提单短量%(毛桶)输入不正确');flag=false;return false;  } 
			 }
			 if (obj2.blHairTonRate != null && obj2.blHairTonRate != ''){
				 if(!num.test(obj2.blHairTonRate)){   message('船/提单短量%(毛吨)输入不正确');flag=false;return false;  } 
			 }
			 if (obj2.counCleanBarRate != null && obj2.counCleanBarRate != ''){
				 if(!num.test(obj2.counCleanBarRate)){   message('船/国检短量%(净桶)输入不正确');flag=false;return false;  } 
			 }
			 if (obj2.counCleanTonRate != null && obj2.counCleanTonRate != ''){
				 if(!num.test(obj2.counCleanTonRate)){   message('船/国检短量%(净吨)输入不正确');flag=false;return false;  } 
			 }
			*/
			var types=$('#type').val();
			if (types =='0'){
				
				obj2.accessoryName = $(this).find('.accessory').find('input').attr('title');
				obj2.accessoryPath = $(this).find('.accessory').find('input').attr('data-address');
				
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
							if ( i == 0){
								$('.unloadPort').val(dd.unloadPort);
								$('.oilType').val(dd.oilType);
								$('.agreementCode').val(dd.agreementCode);
								
								if (f =='0'){
									$('.accessory').find('.accessoryFile').attr('title',dd.accessoryName).attr('data-address',dd.accessoryPath).val(dd.accessoryName);
									filex(dd.accessoryName,$('.accessory'))
								} else {
								
									$('.shipHairBar').val(formatNumber(dd.shipHairBar,6,1));
									$('.shipHairTon').val(formatNumber(dd.shipHairTon,6,1));
									$('.commHairBar').val(formatNumber(dd.commHairBar,6,1));
									$('.commHairTon').val(formatNumber(dd.commHairTon,6,1));
									$('.commCleanBar').val(formatNumber(dd.commCleanBar,6,1));
									$('.commCleanTon').val(formatNumber(dd.commCleanTon,6,1));
									$('.counCleanBar').val(formatNumber(dd.counCleanBar,6,1));
									$('.counCleanTon').val(formatNumber(dd.counCleanTon,6,1));
									$('.potHairBar').val(formatNumber(dd.potHairBar,6,1));
									$('.potHairTon').val(formatNumber(dd.potHairTon,6,1));
									$('.blHairBarRate').val(formatNumber(dd.blHairBarRate,6,1));
									$('.blHairTonRate').val(formatNumber(dd.blHairTonRate,6,1));
									$('.counCleanBarRate').val(formatNumber(dd.counCleanBarRate,6,1));
									$('.counCleanTonRate').val(formatNumber(dd.counCleanTonRate,6,1));
									$('.robQuanatity').val(formatNumber(dd.robQuanatity,6,1));
									$('.remark').val(dd.remark);
									if (dd.cod != null){$('.cod').val(new Date(dd.cod).Format('yyyy-MM-dd hh:mm:ss'));}
									$('.shipHairBarVef').val(formatNumber(dd.shipHairBarVef,6,1));
									$('.shipHairTonVef').val(formatNumber(dd.shipHairTonVef,6,1));
									$('.api').val(formatNumber(dd.api,6,1));
									$('.waterImpCon').val(formatNumber(dd.waterImpCon,6,1));
									$('.waterQuantity').val(formatNumber(dd.waterQuantity,6,1));
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
								
								cloneDiv.find('.oilType').val(dd.oilType);
								cloneDiv.find('.unloadPort').val(dd.unloadPort);
								cloneDiv.find('.agreementCode').val(dd.agreementCode);
								cloneDiv.find('.clearFile').attr('hidden','hidden');
								cloneDiv.find('.clearFile').parent('div').find('a').removeClass('file1Fns').addClass('file1Fn');
								if (f == '0'){
									//上传附件
									
									//生成动态id
									cloneDiv.find('.unOrders').find('input').attr('data-address','');
									cloneDiv.find('.unOrders').find('input').attr('title','');
									cloneDiv.find('.unOrders').find('input').val('');
									cloneDiv.find('.accessory').find('input').attr('id','accessoryFile'+indexs);
									cloneDiv.find('.accessory').find('a').attr('id','accessoryBtn'+indexs);
									
									//赋值
									cloneDiv.find('.accessory').find('.accessoryFile').attr('title',dd.accessoryName).val(dd.accessoryName).attr('data-address',dd.accessoryPath);
									filex(dd.accessoryName,cloneDiv.find('.accessory'))
									var cloneDivs=$('.accessoryAcc:eq(0)').clone();
									cloneDivs.attr('id','accessoryAcc'+indexs);
									$('.accAll').append(cloneDivs);
									
								} else {
								
									cloneDiv.find('.shipHairBar').val(formatNumber(dd.shipHairBar,6,1));
									cloneDiv.find('.shipHairTon').val(formatNumber(dd.shipHairTon,6,1));
									cloneDiv.find('.commHairBar').val(formatNumber(dd.commHairBar,6,1));
									cloneDiv.find('.commHairTon').val(formatNumber(dd.commHairTon,6,1));
									cloneDiv.find('.commCleanBar').val(formatNumber(dd.commCleanBar,6,1));
									cloneDiv.find('.commCleanTon').val(formatNumber(dd.commCleanTon,6,1));
									cloneDiv.find('.counCleanBar').val(formatNumber(dd.counCleanBar,6,1));
									cloneDiv.find('.counCleanTon').val(formatNumber(dd.counCleanTon,6,1));
									cloneDiv.find('.potHairBar').val(formatNumber(dd.potHairBar,6,1));
									cloneDiv.find('.potHairTon').val(formatNumber(dd.potHairTon,6,1));
									cloneDiv.find('.blHairBarRate').val(formatNumber(dd.blHairBarRate,6,1));
									cloneDiv.find('.blHairTonRate').val(formatNumber(dd.blHairTonRate,6,1));
									cloneDiv.find('.counCleanBarRate').val(formatNumber(dd.counCleanBarRate,6,1));
									cloneDiv.find('.counCleanTonRate').val(formatNumber(dd.counCleanTonRate,6,1));
									cloneDiv.find('.robQuanatity').val(formatNumber(dd.robQuanatity,6,1));
									cloneDiv.find('.remark').val(dd.remark);
									
									if (dd.cod != null){cloneDiv.find('.cod').val(new Date(dd.cod).Format('yyyy-MM-dd hh:mm:ss'));}
									cloneDiv.find('.shipHairBarVef').val(formatNumber(dd.shipHairBarVef,6,1));
									cloneDiv.find('.shipHairTonVef').val(formatNumber(dd.shipHairTonVef,6,1));
									cloneDiv.find('.api').val(formatNumber(dd.api,6,1));
									cloneDiv.find('.waterImpCon').val(formatNumber(dd.waterImpCon,6,1));
									cloneDiv.find('.waterQuantity').val(formatNumber(dd.waterQuantity,6,1));
									cloneDiv.find('.shGrossCubicMeter').val(formatNumber(dd.shGrossCubicMeter,6,1));
									cloneDiv.find('.shNetCubicMeter').val(formatNumber(dd.shNetCubicMeter,6,1));
									cloneDiv.find('.shDensity').val(formatNumber(dd.shDensity,6,1));
								}
								$('.all').append(cloneDiv);
								//初始化上传方法
								fileUp('accessoryAcc'+indexs, '#accessoryBtn'+indexs,'transport','#accessoryFile'+indexs,'#accessoryAcc'+indexs);
							}
						}
					} else {
						$('#save').attr('hidden','true');
						layer.alert($.i18n("JAVASCRIPT00100"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) { layer.close(index);
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
	/*function checkObj(obj){
		var flag = true;
		if ( obj.shipHairBar == null || obj.shipHairBar == ''){message('请输入卸港船毛桶') ; flag =false;return false;}
		if ( obj.shipHairTon == null || obj.shipHairTon == ''){message('请输入卸港船毛吨') ; flag =false;return false;}
		if ( obj.commHairBar == null || obj.commHairBar == ''){message('请输入卸港商检毛桶') ; flag =false;return false;}
		if ( obj.commHairTon == null || obj.commHairTon == ''){message('请输入卸港商检毛吨') ; flag =false;return false;}
		if ( obj.commCleanBar == null || obj.commCleanBar == ''){message('请输入卸港商检净桶') ; flag =false;return false;}
		if ( obj.commCleanTon == null || obj.commCleanTon == ''){message('请输入卸港商检净吨') ; flag =false;return false;}
		if ( obj.counCleanBar == null || obj.counCleanBar == ''){message('请输入卸港国检净桶') ; flag =false;return false;}
		if ( obj.counCleanTon == null || obj.counCleanTon == ''){message('请输入卸港国检净吨') ; flag =false;return false;}
		if ( obj.potHairBar == null || obj.potHairBar == ''){message('请输入卸港罐毛桶') ; flag =false;return false;}
		if ( obj.potHairTon == null || obj.potHairTon == ''){message('请输入卸港罐毛吨') ; flag =false;return false;}
		if ( obj.blHairBarRate == null || obj.blHairBarRate == ''){message('请输入提单短量（%毛桶）') ; flag =false;return false;}
		if ( obj.blHairTonRate == null || obj.blHairTonRate == ''){message('请输入提单短量（%毛吨）') ; flag =false;return false;}
		if ( obj.counCleanBarRate == null || obj.counCleanBarRate == ''){message('请输入国检短量（%净吨）') ; flag =false;return false;}
		if ( obj.counCleanTonRate == null || obj.counCleanTonRate == ''){message('请输入国检短量（%净桶）') ; flag =false;return false;}
		if ( obj.robQuanatity == null || obj.robQuanatity == ''){message('请输入ROB桶（Reain on board）') ; flag =false;return false;}
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
	  		
	 fileUp('accessoryAcc1', '#accessoryBtn1','transport','#accessoryFile1','#accessoryAcc1');
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
});
