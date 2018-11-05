layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	own = layui.own,
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
  	//执行一个laydate实例
  	laydate.render({
    	elem: '#time1',
    	lang: lang_ver,
	    done:function(value, date){
		   var htmlStr='<input type="text" class="form-inp laycanEnd" name="laycanEnd" value="" placeholder="'+$.i18n("JAVASCRIPT001")+'" id="time2" readonly="readonly">';
	    	$('#time1').parent().find('#time2').remove();
	    	$('#time1').parent().append(htmlStr);
	    	laydate.render({
	    		elem: '#time2',
	    		min:value,
	    		lang: lang_ver,
	    		btns: ['clear', 'confirm'],
	    		done:function(value, date){
	    			if( parseInt( new Date($('#time1').val()).getTime()) > parseInt( new Date(value).getTime())){
	    				message($.i18n("JAVASCRIPT00104"));
	    				setTimeout(function(){
	    					$('#time2').val('');
	    				},1000)
	    			}
	    		}
	    	});
       }
  	});
  	//装卸港 船型船龄
  	ports();
  	//查询公司名称
  	queryCompanyName();
  	var orderCode = own.getHref('orderCode');
  	var quantity = own.getHref('quantity');
  	var loadPort = own.getHref('loadPort');
    var unloadPort = own.getHref('unloadPort');
    var oilName = own.getHref('oilType');
  	var loadPortCode = own.getHref('loadPortCode');
    var unloadPortCode = own.getHref('unloadPortCode');
    var oilNameCode = own.getHref('oilTypeCode');
    var beg = own.getHref('beg');
    var end = own.getHref('end');
  	var unitName = own.getHref('unitName');
  	//判断是新增还是修改
  	var palletUuid = own.getHref('palletUuid');
  	//判断是否先选船盘
  	var shipPlateUuid = own.getHref('shipPlateUuid');
  	if (quantity){
  		quantity = decodeURIComponent(quantity);
  		$('.quantity').val(formatNumber(quantity,3,1));
  	}
  	if (unitName){
  		unitName = decodeURIComponent(unitName);
  		if (unitName == '吨'){
  			unitName = 'MT';
  		} else if (unitName == '桶'){
  			unitName = 'BBL';
  		}
  		$('.unitName').val(unitName);
  	}
  	if (loadPort){
  		loadPort = decodeURIComponent(loadPort);
  		loadPortCode = decodeURIComponent(loadPortCode);
  		$('.loadPort').val(loadPort).attr("code",loadPortCode).attr("nameFn",loadPort);
  	}
  	if (unloadPort){
  		unloadPort = decodeURIComponent(unloadPort);
  		unloadPortCode = decodeURIComponent(unloadPortCode);
  		$('.unloadPort').val(unloadPort).attr("code",unloadPortCode).attr("nameFn",unloadPort);
  		
  	}
  	if (beg){
  		beg = decodeURIComponent(beg);
  		$('.laycanBeg').val(beg);
  	}
  	if (end){
  		end = decodeURIComponent(end);
  		$('.laycanEnd').val(end);
  	}
  	if(oilName){
  		oilName = decodeURIComponent(oilName);
  		oilNameCode = decodeURIComponent(oilNameCode);
  		$('.oilType').val(oilName).attr("code",oilNameCode).attr("nameFn",oilName);
  	}
  	
  	//按港口
  	$('#load').on('click',function(){
  		$('#quyu').parents('li').removeClass('c-cur');
  		$(this).parents('li').addClass('c-cur');
  		$('.load1').removeAttr('hidden','true');
  		$('.quyu1').attr('hidden','true');
  		$('#fn').val("1");
  	})
  	//按区域
  	$('#quyu').on('click',function(){
  		$('#load').parents('li').removeClass('c-cur');
  		$(this).parents('li').addClass('c-cur');
  		$('.quyu1').removeAttr('hidden','true');
  		$('.load1').attr('hidden','true');
  		$('#fn').val("2");
  	})
	//加
	$('.w-ico-plus').on('click',function(){
		var cloneDiv=$(this).parents('.charter-add').clone();
		cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
		cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
		cloneDiv.find('.form-inp').val('');
		cloneDiv.find('.inps').attr('hidden','true');
		autocomplete(oilType,cloneDiv.find('.oilType'));
		autocomplete(load,cloneDiv.find('.loadPort'));
		autocomplete(unload,cloneDiv.find('.unloadPort'));
		$(this).parents('.all').append(cloneDiv);
	});
	//减
	$('body').on('click',' .w-ico-Less',function(){
		$(this).parents('.charter-add').remove();
	});
	//取消
  	$('.returnBtn').on('click',function(){
  		window.location.href=shipServer+"/pallet/palletQueryList.htm";
  	});
  	//下拉框改变事件
	/*$('body').on('change','.sel',function(){
		if ($(this).val() == "其它"){
			$(this).parents('.charter-row').next('div').removeAttr('hidden','true');
		} else {
			$(this).parents('.charter-row').next('div').attr('hidden','true');
			$(this).parents('.charter-row').next('div').find('input').val('');
		}
	})*/
	$('body').on('click','.other',function(){
		if($(this).is(':checked')){
			$(this).parents('.checkboxFn').next('div').removeAttr('hidden','true');	
		}else{
			$(this).parents('.checkboxFn').next('div').find('input').val('');	
			$(this).parents('.checkboxFn').next('div').attr('hidden','true');	
		}
	})
	
	//查询值集
	/*function queryValueSet(){
		var paramsBroker = {};
		paramsBroker.valueSetTypes="9;13;";
		$.ajax({
			type: "POST",
			url: shipServer+"/shipPact/queryValueSet.json",
			data: JSON.stringify(paramsBroker),
			dataType: "json",
			contentType:"application/json",
			async: false,
			success: function(data) {
				if(data.status == 0){
					var broker_arr = data.datas;
					if (broker_arr != null && broker_arr.length > 0){
						for(var i =0; i<broker_arr.length; i++){
							var broker = broker_arr[i];
							if (broker.type == '5'){ //装港值集
								$(".loadPort").append("<option class='loadPortOpp' value='"+broker.value+"'>"+broker.value+"</option>");
							}
							if (broker.type == '4'){ // 卸港值集
								$(".unloadPort").append("<option class='unloadPortOpp' value='"+broker.value+"'>"+broker.value+"</option>");
							}
							if (broker.type == '9'){ // 船型
								if(broker.value=="其它"){
									$(".shipType").append("<div class='controls charter-radio '><input type='checkbox' class='other' value='"+broker.value+"' /><span>"+broker.value+"</span></div>");
								}else{
									$(".shipType").append("<div class='controls charter-radio '><input type='checkbox'  value='"+broker.value+"' /><span>"+broker.value+"</span></div>");
								}
							}
							if (broker.type == '13'){ // 船龄
								if(broker.value=="其它"){
									$(".shipAge").append("<div class='controls charter-radio '><input type='checkbox' class='other' value='"+broker.value+"' /><span>"+broker.value+"</span></div>");
								}else{
									$(".shipAge").append("<div class='controls charter-radio '><input type='checkbox' value='"+broker.value+"' /><span>"+broker.value+"</span></div>");
								}
							}
						}
					}
				}
			},
			error: function(data) {
				alert("失败:" + data.message);
			}
		});
		
	}*/
	//查询公司名称
	function queryCompanyName(){
		var paramsBroker = {};
		paramsBroker.valueSetTypes="9;13;";
		//查询当前公司名称
		$.ajax({
			type: "POST",
			url: shipServer+"/shipPact/getUserInfo.json",
			data: JSON.stringify(paramsBroker),
			dataType: "json",
			contentType:"application/json",
			async: false,
			success: function(data) {
				if(data.status == 0){
					$('.companyName').val(data.datas.epMemberName);
					$('.contacts').val(data.datas.contacts);
					$('.email').val(data.datas.email);
					$('.tel').val(data.datas.tel);
				}
			}
		});
	}
	/*queryTraderName();
	//查询承运商列表
	function queryTraderName(){
		var obj ={};
		$.ajax({
			type: "POST",
			url: shipServer+"/traderNameListForBack.json",
			data: JSON.stringify(obj),
			dataType: "json",
			contentType:"application/json",
			async: false,
			success: function(data) {
				if(data.status == 0){
					var arr = data.datas;
					if (arr != null && arr.length > 0){
						for(var i =0; i<arr.length; i++){
							var broker = arr[i];
						$(".traderName").append("<option value='"+broker.traderId+"'>"+broker.traderName+"</option>");
						}
					}
				}
			},
			error: function(data) {
				alert("失败:" + data.message);
			}
		});
	}
*/	
  	if (palletUuid){
  		init(palletUuid);
  	}
  	
  	/*//切换是否通过代理租船
  	$('input:radio[name="traderSel"]').change(function(){  
        if($('input:radio[name="traderSel"]:checked ').val()==1){  
        	$('.traderNameHide').removeAttr('hidden','true');
         }else{  
        	 $('.traderNameHide').attr('hidden','true');
         }  
     });  */
	//提交
	$('#save').on('click',function(){
		var obj = own.serializeObject($('.form'));
		var shipType = "";
		$('.shipType').find('div').find('input:checked').each(function(){  
			if ( $(this).val() == $.i18n("JAVASCRIPT00225")){
				shipType=shipType+$(this).parents('.shipType').next('div').find('.shipTypes').val()+";";
			} else {
				shipType=shipType+$(this).val()+';';
			}
		});
		obj.shipType=shipType;
		/*var shipAge ="";
		$('.shipAge').find('div').find('input:checked').each(function(){  
			if ( $(this).val() == '其它'){
				shipAge=shipAge+$(this).parents('.shipAge').next('div').find('.shipAges').val()+";";
			} else {
				shipAge=shipAge+$(this).val()+';';
			}
		});*/
		/*//是否代理
		var traderSel =$('input:radio[name="traderSel"]:checked').val();
		obj.traderSel=traderSel;
		if(traderSel==1){
			obj.traderName = $(".traderName").find("option:selected").text();
			obj.traderId =$(".traderName").find("option:selected").val();
		}else{
			obj.traderName = "";
			obj.traderId = "";
		}	*/
		if (obj.laycanBeg != null && obj.laycanBeg != ''){
			obj.laycanBeg=new Date(obj.laycanBeg);
		}
		if(obj.laycanEnd != null && obj.laycanEnd != ''){
			obj.laycanEnd=new Date(obj.laycanEnd);
		}
	
		var arr = [];
		if ($('#fn').val() == '1'){
			var flag2=true;
			var flag3=true;
			$('.zhuanggang').each(function(){
				var object = {};
				object.unitName = $(this).find('.unitName').val();
				object.quantity = delNumFormat($(this).find('.quantity').val());
				object.loadPort = $(this).find('.loadPort').val();
				object.oilType = $(this).find('.oilType').val();
				/*if (object.loadPort == '其它'){object.loadPort=$(this).find('.loadPorts').val();}*/
				if (object.oilType == null || object.oilType == ''){	message($.i18n("JAVASCRIPT006")); flag2 = false; return false;}
				if (object.unitName == null || object.unitName == ''){	message($.i18n("JAVASCRIPT00215")); flag2 = false; return false;}
				if (object.quantity == null || object.quantity == ''){message($.i18n("JAVASCRIPT005")); flag2 = false; return false;}
				if (object.loadPort == null || object.loadPort == ''){message($.i18n("JAVASCRIPT007")); flag2 = false; return false;}
				//判断是否为自定义油种
				object.oilTypeCode = $(this).find('.oilType').attr("code");
				var oilTypeTemp =  $(this).find('.oilType').attr("nameFn");
				if(oilTypeTemp!=object.oilType){
					object.oilTypeCode = object.oilType;
				}
				//判断装港是否为自定义
				object.loadPortCode =  $(this).find('.loadPort').attr("code");
				var loadPortTemp =  $(this).find('.loadPort').attr("nameFn");
				if(loadPortTemp!=object.loadPort){
					object.loadPortCode = object.loadPort;
				}
				arr.push(object);
			});
			if (!flag2){return false;}
			$('.xiegang').each(function(){
				var object = {};
				object.unloadPort = $(this).find('.unloadPort').val();
				/*if (object.unloadPort == '其它'){
					object.unloadPort=$(this).find('.unloadPorts').val();
				}*/
				if (object.unloadPort == null || object.unloadPort == ''){message($.i18n("JAVASCRIPT008")); flag3 = false; return false;}
				//判断卸港是否为自定义
				object.unloadPortCode =  $(this).find('.unloadPort').attr("code");
				unloadPortTemp = $(this).find('.unloadPort').attr("nameFn");
				if(unloadPortTemp!=object.unloadPort){
					object.unloadPortCode = object.unloadPort;
				}
				arr.push(object);
			});
			if (!flag3){return false;}
		} else {
			var flag2=true;
			var flag3=true;
			$('.zhuanghuo').each(function(){
				var object = {};
				object.oilType = $(this).find('.oilType').val();
				object.unitName = $(this).find('.unitName').val();
				object.quantity = delNumFormat($(this).find('.quantity').val());
				object.loadRegion = $(this).find('.loadRegion').val();
				if (object.oilType == null || object.oilType == ''){message($.i18n("JAVASCRIPT006")); flag2 = false; return false;}
				if (object.unitName == null || object.unitName == ''){message($.i18n("JAVASCRIPT00201")); flag2 = false; return false;}
				if (object.quantity == null || object.quantity == ''){message($.i18n("JAVASCRIPT005")); flag2 = false; return false;}
				if (object.loadRegion == null || object.loadRegion == ''){message($.i18n("JAVASCRIPT007")); flag2 = false; return false;}
				
				arr.push(object);
			});
			if (!flag2){return false;}
			$('.xiehuo').each(function(){
				var object = {};
				object.unloadRegion = $(this).find('.unloadRegion').val();
				if (object.unloadRegion == null || object.unloadRegion == ''){message($.i18n("JAVASCRIPT008")); flag3 = false; return false;}
				arr.push(object);
			});
			if (!flag3){return false;}
			}
			obj.list =arr;
			var flag =check(obj);
			if (obj.laycanBeg > obj.laycanEnd){
				message($.i18n("JAVASCRIPT0028"));
				return false;
			}
			if (parseInt(obj.shipAgeBeg) > parseInt(obj.shipAgeEnd)){
				message($.i18n("JAVASCRIPT00226"));
				return false;
			}
			if (!flag){return false};
			if (orderCode){ 
				obj.orderCode = orderCode; 
				obj.orderNo = "1";
			} else {
				obj.orderNo = "0";
			}
			var url = '';
			if (palletUuid){
				obj.uuid = palletUuid;
				 url =shipServer+"/pallet/update.json";
			} else {
				var palletType = own.getHref('palletType');
				if (!palletType){
					message($.i18n("ITSH533"));
					return false;
				}
				obj.palletType=palletType;
				url =shipServer+"/pallet/save.json";
			}
			//是否先选船盘
			if(shipPlateUuid){
				obj.shipPlateUuid = shipPlateUuid;
				obj.plateSel = "1";
			}else{
				obj.plateSel = "0";
				obj.shipPlateUuid = "";
			}
			//默认不指定转租船东
			obj.traderSel = "0";
			$.ajax({
				type: "POST",
				url: url,
				data: JSON.stringify(obj),
				dataType: "json",
				contentType:"application/json",
				success: function(data) {
					console.log(data)
					if(data.status == 0){
						if(!palletUuid){
							layer.alert($.i18n("JAVASCRIPT00204"),{title:$.i18n("001"),btn:$.i18n("002")},function(index) {
								layer.close(index);
								var palletUuid1 = data.message;
								window.location.href=shipServer+"/shipPlateNew/recommendShipPlate.htm?from=1&palletUuid="+palletUuid1;
							});
						}else{
							layer.alert($.i18n("JAVASCRIPT00205"),{title:$.i18n("001"),btn:$.i18n("002")},function(index) {
								layer.close(index);
								if (own.getHref('orderCode')){
									window.location.href=shipServer+"/pallet/palletQueryList.htm";
								} else {
									window.location.href=shipServer+"/pallet/palletList.htm";
								}
							});
						}
					} else {
						message($.i18n(data.message));
					}},
			   error:function(){
				   message($.i18n("JAVASCRIPT003"));
			   }
			});
		});
// 编辑
function init(palletUuid){
		//查询详细
 	var params = {};
 	params.uuid = palletUuid;
 	$.ajax({
		type: "POST",
		url: shipServer+"/pallet/palleDetail.json",
		data: JSON.stringify(params),
		dataType: "json",
		contentType:"application/json",
		success: function(data) {
			console.log(data)
			if(data.status == 0){
				var type =data.datas.type;
				if (type == '1'){
					//港口
					$('#fn').val('1')
					var arr =data.datas.listLoad;
					if (arr != null){
						for(var i = 0 ; i < arr.length ; i++){
							if ( i== 0){
								$('.quantity').val(formatNumber(arr[i].quantity,2,1));
								$('.unitName').val(arr[i].unitName);
								$('.loadPort').val(arr[i].loadPort);
								$('.loadPort').attr('code',arr[i].loadPortCode).attr('nameFn',arr[i].loadPort); 
								$('.oilType').val(arr[i].oilType)
								$('.oilType').attr('code',arr[i].oilTypeCode).attr('nameFn',arr[i].oilType); 
							} else {
								var cloneDiv=$('.zhuanggang:eq(0)').clone();
								cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
								cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
								cloneDiv.find('.form-inp').val('');
								cloneDiv.find('.inps').attr('hidden','true');
								autocomplete(load,cloneDiv.find('.loadPort'));
								cloneDiv.find('.quantity').val(formatNumber(arr[i].quantity,2,1));
								cloneDiv.find('.unitName').val(arr[i].unitName);
								cloneDiv.find('.loadPort').val(arr[i].loadPort);
								cloneDiv.find('.oilType').val(arr[i].oilType);
								cloneDiv.find('.loadPort').attr('code',arr[i].loadPortCode).attr('nameFn',arr[i].loadPort);
								cloneDiv.find('.oilType').attr('code',arr[i].oilTypeCode).attr('nameFn',arr[i].oilType);
								$('.zhuanggangAll').append(cloneDiv);
							}
							
						}
					}
					var arr2 =data.datas.listUnload;
					if (arr2 != null){
						for(var i = 0 ; i < arr2.length ; i++){
							if ( i== 0){
								//下拉框回显
								$('.unloadPort').val(arr2[i].unloadPort);
								$('.unloadPort').attr('code',arr2[i].unloadPortCode).attr('nameFn',arr2[i].unloadPort);
								/*var unloadPort=arr2[i].unloadPort,flag=false;
								$('.unloadPortOpp').each(function(){ if ($(this).val() == unloadPort){ flag=true; } });
								if (flag) {$('.unloadPort').val(unloadPort)} else {
									$('.unloadPort').val('其它') ; $('.unloadPortZ').removeAttr('hidden','true') ;
									$('.unloadPortZ').find('input').val(unloadPort)
								}*/
							} else {
								var cloneDiv=$('.xiegang:eq(0)').clone();
								cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
								cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
								cloneDiv.find('.form-inp').val('');
								cloneDiv.find('.inps').attr('hidden','true');
							  	autocomplete(unload,cloneDiv.find('.unloadPort'));
								//下拉框回显
								/*var unloadPort=arr2[i].unloadPort;
								var flag=false;
								cloneDiv.find('.unloadPortOpp').each(function(){if ($(this).val() == unloadPort){ flag=true; } });
								if (flag) {cloneDiv.find('.unloadPort').val(unloadPort)} else {cloneDiv.find('.unloadPort').val('其它') ; 
								cloneDiv.find('.unloadPortZ').removeAttr('hidden','true') ;
								cloneDiv.find('.unloadPortZ').find('input').val(unloadPort)
								}*/
								cloneDiv.find('.unloadPort').val(arr2[i].unloadPort);
								cloneDiv.find('.unloadPort').attr('code',arr[i].unloadPortCode).attr('nameFn',arr[i].unloadPort);
								$('.xiegangAll').append(cloneDiv);
							}
							
						}
					}
					
				} else if (type == '2'){
					$('#fn').val('2')
					$('#load').parents('li').removeClass('c-cur');
			  		$('#quyu').parents('li').addClass('c-cur');
			  		$('.quyu1').removeAttr('hidden','true');
			  		$('.load1').attr('hidden','true');
			  		var arr =data.datas.listRegion;
					if (arr != null){
						for(var i = 0 ; i < arr.length ; i++){
							if ( i== 0){
								$('.quantity').val(formatNumber(arr[i].quantity,2,1));
								$('.unitName').val(arr[i].unitName);
								$('.loadRegion').val(arr[i].loadRegion);
								$('.oilType').val(arr[i].oilType)
							} else {
								var cloneDiv=$('.zhuanghuo:eq(0)').clone();
								cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
								cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
								cloneDiv.find('.form-inp').val('');
								cloneDiv.find('.inps').attr('hidden','true');
								//下拉框回显
								cloneDiv.find('.quantity').val(formatNumber(arr[i].quantity,2,1));
								cloneDiv.find('.unitName').val(arr[i].unitName);
								cloneDiv.find('.loadRegion').val(arr[i].loadRegion);
								$('.oilType').val(arr[i].oilType)
								$('.zhuanghuoAll').append(cloneDiv);
							}
						}
					}
					var arr2 =data.datas.listUnregion;
					if (arr2 != null){
						for(var i = 0 ; i < arr2.length ; i++){
							if ( i== 0){
								//下拉框回显
								$('.unloadRegion').val(arr2[i].unloadRegion);
							} else {
								var cloneDiv=$('.xiehuo:eq(0)').clone();
								cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
								cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
								cloneDiv.find('.form-inp').val('');
								cloneDiv.find('.inps').attr('hidden','true');
								cloneDiv.find('.unloadRegion').val(arr2[i].unloadRegion);
								$('.xiehuoAll').append(cloneDiv);
							}
							
						}
					}
				} else {
					message($.i18n("ITSH110"));
				}
				
				var dd =data.datas.pallet;
				$(".laycanBeg").val(new Date(dd.laycanBeg).Format('yyyy-MM-dd'));
				$(".laycanEnd").val(new Date(dd.laycanEnd).Format('yyyy-MM-dd'));
				$(".remark").val(dd.remark);
				$(".companyName").val(dd.companyName);
				$(".tel").val(dd.tel);
				$(".email").val(dd.email);
				$(".shipAgeBeg").val(dd.shipAgeBeg);
				$(".shipAgeEnd").val(dd.shipAgeEnd);
				$(".contacts").val(dd.contacts);
				//复选框回显
				var types= dd.shipType;
				if (types != null){
					var arr = types.split(";");
					var flag = true;
					var val ="";
					for (var i = 0 ; i <arr.length; i++){
						var s =arr[i];
						var flags =false;
						$('.shipType').find('div').find('input').each(function(){
							if ($(this).val().trim() == arr[i].trim()){
								$(this).attr('checked','checked');
								flags=true;
							}
						});
						if (!flags){ flag=false; val=s;};
					}
					if (!flag){
						$('.shipType').find('div').find("input:checkbox[value="+$.i18n("JAVASCRIPT00225")+"]").attr('checked','checked');
						$('.shipTypess').removeAttr('hidden','true');
						$('.shipTypes').val(val);
					}
				}
			}else{
				message($.i18n(data.message));
			}
		},
		error: function(data) {
            message($.i18n("JAVASCRIPT003"));
        }
 	});
	}
	function check(obj){
		var flag =true;
		if (obj.laycanBeg == null || obj.laycanBeg == '' || obj.laycanEnd == null || obj.laycanEnd == ''){message($.i18n("JAVASCRIPT009"));flag =false;return false;}
		if (obj.shipType == null || obj.shipType == '' ){message($.i18n("JAVASCRIPT00146"));flag =false;return false;}
		if (obj.shipAgeBeg == null || obj.shipAgeBeg == '' ){message($.i18n("JAVASCRIPT00228"));flag =false;return false;}
		if (obj.shipAgeEnd == null || obj.shipAgeEnd == '' ){message($.i18n("JAVASCRIPT00229"));flag =false;return false;}
//		if (obj.companyName == null || obj.companyName == '' ){message($.i18n("JAVASCRIPT00230"));flag =false;return false;}
		if (obj.contacts == null || obj.contacts == '' ){message($.i18n("JAVASCRIPT00231"));flag =false;return false;}
		if (obj.email == null || obj.email == '' ){message($.i18n("JAVASCRIPT00232"));flag =false;return false;}
		if (obj.tel == null || obj.tel == '' ){message($.i18n("JAVASCRIPT00233"));flag =false;return false;}
		var fff = checkEmail(obj.email);
		if (!fff){ message($.i18n("JAVASCRIPT00234"));flag =false;return false; }
		/*var ff = checkTel(obj.tel);
		if (!ff){ message($.i18n("JAVASCRIPT00235"));flag =false;return false; }*/
		return flag;
	}
	//装卸港维护
	function ports(){
		valueSets("5;4;9;10;13");
	  	window.load = [];
	  	window.unload = [];
	  	window.oilType = [];
	  	if (arrsFn != null){
	  		for (var i = 0; i < arrsFn.length; i++) {
				if (arrsFn[i].type == '5' && arrsFn[i].value != $.i18n("JAVASCRIPT00225")){
					load.push(arrsFn[i])
				} else if (arrsFn[i].type == '4' && arrsFn[i].value != $.i18n("JAVASCRIPT00225")){
					unload.push(arrsFn[i])
				} else if (arrsFn[i].type == '9'){ // 船型
					if(arrsFn[i].value==$.i18n("JAVASCRIPT00225")){
						$(".shipType").append("<div class='controls charter-radio '><input type='checkbox' class='other' value='"+arrsFn[i].value+"' /><span>"+arrsFn[i].value+"</span></div>");
					} else{
						$(".shipType").append("<div class='controls charter-radio '><input type='checkbox'  value='"+arrsFn[i].value+"' /><span>"+arrsFn[i].value+"</span></div>");
					}
				} else if(arrsFn[i].type == 10){//油种
					oilType.push(arrsFn[i])
				}
			}
	  	}
	  	autocomplete(load,'.loadPort');
	  	autocomplete(unload,'.unloadPort');
	  	autocomplete(oilType,'.oilType');
	}
	//错误信息
	function message(mess){
		layer.alert(mess,{title:$.i18n("001"),btn:$.i18n("002")},function(index) {
			layer.close(index);
		});
	}	
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
	//校验电话号码
	function checkTel(value){  
		var flag =true;
	    var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;  
	    var isMob=/^((\+?86)|(\+86))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|17[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;  
	    if(isMob.test(value)||isPhone.test(value)){  
	    	flag=true;  
	    } else{  
	    	flag=false;  
	    }  
	    return flag;
	 }  
	//校验邮箱
	function checkEmail(value){  
		var flag =true;
		var isEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/; 	
		if(!isEmail.test(value)){  
			flag=false;  
		}  
		 return flag;
	} 
	if (!String.prototype.trim){
		 
		 /*---------------------------------------
		  * 清除字符串两端空格，包含换行符、制表符
		  *---------------------------------------*/
		 String.prototype.trim = function () { 
		  return this.replace(/(^[\s\n\t]+|[\s\n\t]+$)/g, "");
		 }
	}
});