layui.use(['layer', 'form', 'jquery','laypage', 'upload','table', 'laydate','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var upload = layui.upload,
	laydate = layui.laydate,
	own = layui.own;
	laydate.render({
	    elem: '#date', //指定元素
	    lang: lang_ver
	})
	laydate.render({
		elem: '#pactBeg',
		lang: lang_ver,
	    done:function(value, date){
	      var htmlStr='<input type="text" id="pactEnd" placeholder="请选择日期" readonly="readonly"  style="width: 28%;" name="pactEnds" class="c-int zq-text" />';
	    	$('#pactBeg').parent().find('#pactEnd').remove();
	    	$('#pactBeg').parent().append(htmlStr);
	    	laydate.render({
	    		elem: '#pactEnd',
	    		min:value,
	    		lang: lang_ver,
	    		btns: ['clear', 'confirm'],
	    		done:function(value, date){
	    			if( parseInt( new Date($('#pactBeg').val()).getTime()) > parseInt( new Date(value).getTime())){
	    				layer.msg($.i18n("JAVASCRIPT002")); 
	    				setTimeout(function(){
	    					$('#pactEnd').val('');
	    				},1000)
	    			}
	    			
	    		}
	    	});
       }
	})
	/*laydate.render({
		elem: '#pactEnd' //指定元素
	})*/
	
	//下拉框改变事件
/*	$('body').on('change','.sel',function(){
		if ($(this).val() == "其它"){
			$(this).parents('.charter-row').next('div').removeAttr('hidden','true');
		} else {
			$(this).parents('.charter-row').next('div').attr('hidden','true');
			$(this).parents('.charter-row').next('div').find('input').val('');
		}
	})*/
  //加
	$('.w-ico-plus').on('click',function(){
		var cloneDiv=$(this).parents('.charter-add2').clone();
		cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
		cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
		cloneDiv.find('.inps').attr('hidden','true');
		cloneDiv.find('input').val('');
		autocomplete(load,cloneDiv.find('.loadPort'));
	  	autocomplete(unload,cloneDiv.find('.unloadPort'));
	  	autocomplete(oil,cloneDiv.find('.oilType'));
		$(this).parents('.all').append(cloneDiv);
	});
	
	//减
	$('body').on('click',' .w-ico-Less',function(){
		$(this).parents('.charter-add2').remove();
	});
	
	//查询船名和装卸港
	queryShip();
	//装卸港油种查询
	ports();
	//queryValueSet();
	var palletUuid = own.getHref('palletUuid');
	var uuid = own.getHref('uuid');
	if (palletUuid && !uuid) {queryPallet(palletUuid);}
	
	//查询货盘信息
	function queryPallet(palletUuid){
		var params = {};
	 	params.uuid = palletUuid;
		 $.ajax({
				type: "POST",
				url: shipServer+"/pallet/palleDetail.json",
				data: JSON.stringify(params),
				dataType: "json",
				contentType:"application/json",
				async:false,
				success: function(data) {  
					
					if(data.status == 0){
						var oilName = data.datas.pallet.oilName;
						var epMemberName = data.datas.pallet.epMemberName;
						var traderName = data.datas.pallet.traderName;
						var laycanBeg = data.datas.pallet.laycanBeg;
						var laycanEnd = data.datas.pallet.laycanEnd;
						if (epMemberName != null){
							$('.firParty').val(epMemberName);
						}
						if (traderName != null){
							$('.secParty').val(traderName);
						}
						$('#pactBeg').val(dateChangeD(laycanBeg));
						$('#pactEnd').val(dateChangeD(laycanEnd));
						var arrLoad =data.datas.listLoad;
						var listRegion =data.datas.listRegion;
						if (arrLoad != null && arrLoad.length > 0){
							for (var i = 0; i < arrLoad.length; i++) {
								var loadPort = arrLoad[i].loadPort;
								var quantity = arrLoad[i].quantity;
								var unitName = arrLoad[i].unitName;
								if (i == 0){
									$('.loadPort').val(loadPort) ;
									$('.oilType').val(oilName) ;
									$('.quantity').val(formatStr(quantity,3,1));
									$('.unitName').val(unitName);
									//下拉框回显
									/*var flag=false;
									$('.loadPortOpp').each(function(){ if ($(this).val() == loadPort){ flag=true; } });
									if (flag) {$('.loadPort').val(loadPort)} else {
										$('.loadPort').val('其它') ; $('.loadPortZ').removeAttr('hidden','true') ;
										$('.loadPortZ').find('input').val(loadPort)
									}
									if (oilName != null && oilName != ''){
										var flag2=false;
										$('.oilTypeOpp').each(function(){ if ($(this).val() == oilName){ flag2=true; } });
										if (flag2) {$('.oilType').val(oilName)} else {
											$('.oilType').val('其它') ; $('.oilTypeZ').removeAttr('hidden','true') ;
											$('.oilTypeZ').find('input').val(oilName)
										}
									}*/
									
								} else {
									var cloneDiv=$('.zhuang:eq(0)').parents('.charter-add').clone();
									cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
									cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
									cloneDiv.find('.inps').attr('hidden','true');
									cloneDiv.find('.c-int').val('');
									//下拉框回显
									var loadPort=arrLoad[i].loadPort;
								  /*var flag=false;
									cloneDiv.find('.loadPortOpp').each(function(){if ($(this).val() == loadPort){ flag=true; } });
									if (flag) {cloneDiv.find('.loadPort').val(loadPort)} else {cloneDiv.find('.loadPort').val('其它') ; 
										cloneDiv.find('.loadPortZ').removeAttr('hidden','true') ;
										cloneDiv.find('.loadPortZ').find('input').val(loadPort)
									}
									if (oilName != null && oilName != ''){
										var flag2=false;
										cloneDiv.find('.oilTypeOpp').each(function(){if ($(this).val() == oilName){ flag2=true; } });
										if (flag2) {cloneDiv.find('.oilType').val(oilName)} else {cloneDiv.find('.oilType').val('其它') ; 
										cloneDiv.find('.oilTypeZ').removeAttr('hidden','true') ;
										cloneDiv.find('.oilTypeZ').find('input').val(oilName)
										}
									}*/
									cloneDiv.find('.loadPort').val(loadPort) ;
									cloneDiv.find('.oilType').val(oilName) ;
									cloneDiv.find('.quantity').val(formatStr(quantity,3,1));
									cloneDiv.find('.unitName').val(unitName);
									autocomplete(load,cloneDiv.find('.loadPort'));
								  	autocomplete(oil,cloneDiv.find('.oilType'));
									$('.zhuanggangAll').append(cloneDiv);
								}
							}
						} else if (listRegion != null && listRegion.length > 0){
							for (var i = 0; i < listRegion.length; i++) {
								var quantity = listRegion[i].quantity;
								var unitName = listRegion[i].unitName;
								if (i == 0){
									//下拉框回显
									$('.quantity').val(formatStr(quantity,3,1));
									$('.unitName').val(unitName);
									$('.oilType').val(oilName);
									/*if (oilName != null && oilName != ''){
										var flag2=false;
										$('.oilTypeOpp').each(function(){ if ($(this).val() == oilName){ flag2=true; } });
										if (flag2) {$('.oilType').val(oilName)} else {
											$('.oilType').val('其它') ; $('.oilTypeZ').removeAttr('hidden','true') ;
											$('.oilTypeZ').find('input').val(oilName)
										}
									}*/
									
								} else {
									var cloneDiv=$('.zhuang:eq(0)').parents('.charter-add').clone();
									cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
									cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
									cloneDiv.find('.inps').attr('hidden','true');
									cloneDiv.find('.c-int').val('');
								/*	if (oilName != null && oilName != ''){
										var flag2=false;
										cloneDiv.find('.oilTypeOpp').each(function(){if ($(this).val() == oilName){ flag2=true; } });
										if (flag2) {cloneDiv.find('.oilType').val(oilName)} else {cloneDiv.find('.oilType').val('其它') ; 
										cloneDiv.find('.oilTypeZ').removeAttr('hidden','true') ;
										cloneDiv.find('.oilTypeZ').find('input').val(oilName)
										}
									}*/
								  	autocomplete(oil,cloneDiv.find('.oilType'));
								  	cloneDiv.find('.oilType').val(oilName);
									cloneDiv.find('.quantity').val(formatStr(quantity,3,1));
									cloneDiv.find('.unitName').val(unitName);
									$('.zhuanggangAll').append(cloneDiv);
								}
							}
						}
						var arrUnload =data.datas.listUnload;
						if (arrUnload != null && arrUnload.length > 0){
							for (var i = 0; i < arrUnload.length; i++) {
								var unloadPort = arrUnload[i].unloadPort;
								if (i == 0){
									$('.unloadPort').val(unloadPort);
									//下拉框回显
									/*var flag=false;
									$('.unloadPortOpp').each(function(){ if ($(this).val() == unloadPort){ flag=true; } });
									if (flag) {$('.unloadPort').val(unloadPort)} else {
										$('.unloadPort').val('其它') ; $('.unloadPortZ').removeAttr('hidden','true') ;
										$('.unloadPortZ').find('input').val(unloadPort)
									}*/
								} else {
									var cloneDiv=$('.xie:eq(0)').parents('.charter-add').clone();
									cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
									cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
									cloneDiv.find('.inps').attr('hidden','true');
									cloneDiv.find('.c-int').val('');
									//下拉框回显
									cloneDiv.find('.unloadPort').val(unloadPort);
									/*var flag=false;
									cloneDiv.find('.unloadPortOpp').each(function(){if ($(this).val() == unloadPort){ flag=true; } });
									if (flag) {cloneDiv.find('.unloadPort').val(unloadPort)} else {cloneDiv.find('.unloadPort').val('其它') ; 
										cloneDiv.find('.unloadPortZ').removeAttr('hidden','true') ;
										cloneDiv.find('.unloadPortZ').find('input').val(loadPort)
									}*/
									autocomplete(unload,cloneDiv.find('.unloadPort'));
									$('.xiegangAll').append(cloneDiv);
								}
								
							}
						}
						
					} else {
						layer.alert($.i18n(data.message),  function(index) {	 layer.close(index);});
					}
				},
				error:function(){
					layer.alert($.i18n("JAVASCRIPT003"),  function(index) { layer.close(index);});
				}
			});
	}
	
	//保存
	$('.save').on('click',function(){
		var palletUuid = own.getHref('palletUuid');
		var uuid = own.getHref('uuid');
		if (!uuid && !palletUuid) {message($.i18n("JAVASCRIPT004")) ;return false;}
		var obj = own.serializeObject($('.form'));
		if (palletUuid){obj.palletUuid=palletUuid;}
		if (uuid){obj.uuid=uuid; }
		obj.shipName = $(".shipName option:selected").text();
		obj.sysShipUuid = $(".shipName").val();
		obj.accessory=$("#cunImg").attr('title');
		obj.accessoryPath=$("#cunImg").attr('data-address');
		var quantitys="";
		var oilTypes="";
		var loadPorts="";
		var unloadPorts="";
		
		// 油种子字段
		var oilTypeCopys="";
		// 装港子字段
		var loadPortCopys="";
		// 卸港子字段
		var unloadPortCopys="";
		var flag=true;
		$('.zhuanggangAll .charter-add2').each(function(){
			var quantity = delNumFormat($(this).find('.quantity').val());
			var oilType = $(this).find('.oilType').val();
			var unitName = $(this).find('.unitName').val();
			var loadPort = $(this).find('.loadPort').val();
			
			// 油种子字段
			var oilTypeCopy = $(this).find('.oilType').val();
			// 装港子字段
			var loadPortCopy = $(this).find('.loadPort').val();
			
			/*if (loadPort == '其它'){
				loadPort=$(this).find('.loadPorts').val();
			}
			if (oilType == '其它'){
				oilType=$(this).find('.oilTypes').val();
			}*/
			if (quantity == null || quantity == ''){message($.i18n("JAVASCRIPT0017"));flag=false;return false;}
			if (oilType == null || oilType == ''){message($.i18n("JAVASCRIPT006"));flag=false;return false;}
			if (loadPort == null || loadPort == ''){message($.i18n("JAVASCRIPT007"));flag=false;return false;}
			quantitys=quantitys+quantity+unitName+"/";
			oilTypes=oilTypes+oilType+"/";
			loadPorts=loadPorts+loadPort+"/";
			
			// 油种子字段
			oilTypeCopys=oilTypeCopys+oilTypeCopy+"&&";
			
			// 装港子字段
			loadPortCopys=loadPortCopys+loadPortCopy+"&&";
		});
		$('.xiegangAll .charter-add2').each(function(){
			var unloadPort = $(this).find('.unloadPort').val();
			
			// 卸港子字段
			var unloadPortCopy = $(this).find('.unloadPort').val();
			
			/*if (unloadPort == '其它'){
				unloadPort=$(this).find('.unloadPorts').val();
			}*/
			if (unloadPort == null || unloadPort == ''){message($.i18n("JAVASCRIPT008"));flag=false;return false;}
			unloadPorts=unloadPorts+unloadPort+"/";
			
			// 卸港子字段
			unloadPortCopys=unloadPortCopys+unloadPortCopy+"&&";
			
			
		});
		if (!flag){return false};
		var pactBeg =$('#pactBeg').val();
		var pactEnd =$('#pactEnd').val();
		if (pactBeg == null || pactBeg == '' || pactEnd == null || pactEnd == ''){
			message($.i18n("JAVASCRIPT009")) ; return false;
		}
		if (pactBeg > pactEnd){
			message($.i18n("JAVASCRIPT0010"));
			return false;
		}
		var str1 = new Date(pactBeg).Format("yyyy-MM-dd")
		var str2 = new Date(pactEnd).Format("yyyy-MM-dd")
		obj.laycan=str1+"/"+str2;
		obj.quantity=quantitys;
		obj.oilType=oilTypes;
		obj.loadPort=loadPorts;
		obj.unloadPort=unloadPorts;
		
		// 油种
		obj.oilTypeCopy=oilTypeCopys;
		
		// 装港子字段
		obj.loadPortCopy=loadPortCopys;
		// 卸港子字段
		obj.unloadPortCopy=unloadPortCopys;
		delete obj.pactBeg;
		delete obj.pactEnd;
		var flag = check(obj);
		if (!flag){ return false;}
		  $.ajax({
				type: "POST",
				url: shipServer+"/agreement/saveAgreement.json",
				data: JSON.stringify(obj),
				dataType: "json",
				contentType:"application/json",
				success: function(data) {  
						if(data.status == 0){
							if(uuid){
								layer.alert($.i18n("JAVASCRIPT0011"),  function(index) { layer.close(index);
								window.location.href=shipServer+"/agreement/agreementList.htm";
								});
							}else if(palletUuid){
								layer.alert($.i18n("JAVASCRIPT0012"),  function(index) { layer.close(index);
								window.location.href=shipServer+"/agreement/agreementList.htm";
								});
							}
						} else {
							layer.alert($.i18n(data.message),  function(index) {	 layer.close(index);});
						}
				},
				error:function(){
					layer.alert($.i18n("JAVASCRIPT003"),  function(index) { layer.close(index);});
				}
			});
	});

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
    		async:false,
    		success: function(data) {
    			if(data.status == 0){
    				var dd=data.datas;
					$('.signDate').val(dateChangeD(dd.signDate));
					$('.firParty').val(dd.firParty);
					$('.secParty').val(dd.secParty);
					$('.shipName').val(dd.sysShipUuid);
					$('#cunImg').val(dd.accessory);
					$('#cunImg').attr('title',dd.accessory).attr('data-address',dd.accessoryPath);
					
					var laycanBeg = data.datas.map.pactBeg;
					var laycanEnd = data.datas.map.pactEnd;
					$('#pactBeg').val(laycanBeg);
					$('#pactEnd').val(laycanEnd);
					var arrLoad =data.datas.map.listLoad;
					if (arrLoad != null){
						for (var i = 0; i < arrLoad.length; i++) {
							var loadPort = arrLoad[i].loadPort;
							var quantity = arrLoad[i].quantity;
							var oilName = arrLoad[i].oilName;
							var unitName = arrLoad[i].unitName;
							if (i == 0){
								/*//下拉框回显
								var flag=false;
								$('.loadPortOpp').each(function(){ if ($(this).val() == loadPort){ flag=true; } });
								if (flag) {$('.loadPort').val(loadPort)} else {
									$('.loadPort').val('其它') ; $('.loadPortZ').removeAttr('hidden','true') ;
									$('.loadPortZ').find('input').val(loadPort)
								}
								if (oilName != null && oilName != ''){
									var flag2=false;
									$('.oilTypeOpp').each(function(){ if ($(this).val() == oilName){ flag2=true; } });
									if (flag2) {$('.oilType').val(oilName)} else {
										$('.oilType').val('其它') ; 
										$('.oilTypeZ').removeAttr('hidden','true') ;
										$('.oilTypeZ').find('input').val(oilName)
									}
								}*/
								$('.loadPort').val(loadPort);
								$('.oilType').val(oilName); 
								$('.quantity').val(formatNumber(quantity,2,1));
								$('.unitName').val(unitName);
								
							} else {
								var cloneDiv=$('.zhuangDiv:eq(0)').clone();
								cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
								cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
								cloneDiv.find('.inps').attr('hidden','true');
								//下拉框回显
								/*var flag=false;
								cloneDiv.find('.loadPortOpp').each(function(){if ($(this).val() == loadPort){ flag=true; } });
								if (flag) {cloneDiv.find('.loadPort').val(loadPort)} else {cloneDiv.find('.loadPort').val('其它') ; 
									cloneDiv.find('.loadPortZ').removeAttr('hidden','true') ;
									cloneDiv.find('.loadPortZ').find('input').val(loadPort)
								}
								if (oilName != null && oilName != ''){
									var flag2=false;
									cloneDiv.find('.oilTypeOpp').each(function(){if ($(this).val() == oilName){ flag2=true; } });
									if (flag2) {cloneDiv.find('.oilType').val(oilName)} else {cloneDiv.find('.oilType').val('其它') ; 
									cloneDiv.find('.oilTypeZ').removeAttr('hidden','true') ;
									cloneDiv.find('.oilTypeZ').find('input').val(oilName)
									}
								}*/
								cloneDiv.find('.loadPort').val(loadPort);
								cloneDiv.find('.oilType').val(oilName); 
								cloneDiv.find('.quantity').val(formatNumber(quantity,2,1));
								cloneDiv.find('.unitName').val(unitName);
								autocomplete(load,cloneDiv.find('.loadPort'));
							  	autocomplete(oil,cloneDiv.find('.oilType'));
								$('.zhuanggangAll').append(cloneDiv);
							}
						}
					}
					var arrUnload =data.datas.map.listUnload;
					if (arrUnload != null){
						for (var i = 0; i < arrUnload.length; i++) {
							var unloadPort = arrUnload[i].unloadPort;
							if (i == 0){
								/*//下拉框回显
								var flag=false;
								$('.unloadPortOpp').each(function(){ if ($(this).val() == unloadPort){ flag=true; } });
								if (flag) {$('.unloadPort').val(unloadPort)} else {
									$('.unloadPort').val('其它') ; $('.unloadPortZ').removeAttr('hidden','true') ;
									$('.unloadPortZ').find('input').val(unloadPort)
								}*/
								$('.unloadPort').val(unloadPort)
							} else {
								var cloneDiv=$('.xie:eq(0)').parents('.charter-add').clone();
								cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
								cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
								cloneDiv.find('.inps').attr('hidden','true');
								cloneDiv.find('.c-int').val('');
								//下拉框回显
								/*var unloadPort=arrUnload[i].unloadPort;
								var flag=false;
								cloneDiv.find('.unloadPortOpp').each(function(){if ($(this).val() == unloadPort){ flag=true; } });
								if (flag) {cloneDiv.find('.unloadPort').val(unloadPort)} else {cloneDiv.find('.unloadPort').val('其它') ; 
									cloneDiv.find('.unloadPortZ').removeAttr('hidden','true');
									cloneDiv.find('.unloadPortZ').find('input').val(loadPort)
								}*/
								cloneDiv.find('.unloadPort').val(unloadPort)
								autocomplete(unload,cloneDiv.find('.unloadPort'));
								$('.xiegangAll').append(cloneDiv);
							}
						}
					}
				}
			}
	 	});
	}
	//取消
	$('.cancel').on('click',function(){
		window.location.href = document.referrer;
	});
	//查询船名
	function queryShip(){
		var obj = {};
		 $.ajax({
				type: "POST",
				url: shipServer+"/sysShip/findSysShipList.json",
				data:JSON.stringify(obj),
				dataType: "json",
				contentType:"application/json",
				async:false,
				success: function(data2) { 
						if(data2.status == 0){
							var arr = data2.datas;
							if (arr != null && arr.length>0){
								for(var i=0;i<arr.length;i++)
			                    {     
								$("#shipName").append("<option value='"+arr[i].uuid+"'>"+arr[i].name+"</option>");
			      } } } }
			});
	}
	
	//装卸港油种维护
	function ports(){
		//查询值集
		valueSets("5;4;10;");
	  	window.load = [];
	  	window.unload = [];
	  	window.oil = [];
	  	if (arrsFn != null){
	  		for (var i = 0; i < arrsFn.length; i++) {
				if (arrsFn[i].type == '5' && arrsFn[i].value != '其它'){
					load.push(arrsFn[i])
				} else if (arrsFn[i].type == '4' && arrsFn[i].value != '其它'){
					unload.push(arrsFn[i])
				} else if (arrsFn[i].type == '10' && arrsFn[i].value != '其它'){ 
					oil.push(arrsFn[i])
				}
			}
	  	}
	  	autocomplete(load,'.loadPort');
	  	autocomplete(unload,'.unloadPort');
	  	autocomplete(oil,'.oilType');
	}
	//检验必填
	function check(obj){
		var flag = true;
		if (obj.signDate == null || obj.signDate == ''){message($.i18n("JAVASCRIPT0013"));flag= false; return false;};
		if (obj.firParty == null || obj.firParty == ''){message($.i18n("JAVASCRIPT0014"));flag= false; return false;};
		if (obj.secParty == null || obj.secParty == ''){message($.i18n("JAVASCRIPT0015"));flag= false; return false;};
		if (obj.shipName == null || obj.shipName == '' || obj.sysShipUuid == null || obj.sysShipUuid == '' ){message($.i18n("JAVASCRIPT0016"));flag= false; return false;};
		if (obj.quantity == null || obj.quantity == ''){message($.i18n("JAVASCRIPT0017"));flag= false; return false;};
		return flag;
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
	//时间戳转年月日
	 function dateChangeD(dateNum){
		 var date=new Date(dateNum);//如果是秒就*1000
		 return date.getFullYear()+"-"+fixZero(date.getMonth()+1,2)+"-"+fixZero(date.getDate(),2);//+fixZero(date.getHours(),2)+":"+fixZero(date.getMinutes(),2)+":"+fixZero(date.getSeconds(),2)
		 function fixZero(num,length){
			 var str=""+num;
			 var len=str.length;
			 var s="";
				 for(var i=length;i-->len;){
					 s+="0";
				 }
			 return s+str;
			 }
		 }
	//时间戳转换 2017-02-9 14:14:52
    function dateChange(ns){
    	var date = new Date(ns);
    	Y = date.getFullYear() + '-';
    	M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
    	D = date.getDate() <10 ? '0'+date.getDate()+' ':date.getDate() + ' ';
    	h = date.getHours()  <10 ? '0'+date.getHours()+':':date.getHours() +':';
    	m = date.getMinutes() <10 ? '0'+date.getMinutes()+':':date.getMinutes() +':';
    	s = date.getSeconds() <10 ? '0'+date.getSeconds():date.getSeconds() ;
    	return Y+M+D+h+m+s;
		
	}
	//弹框
	function message(mess){
		layer.alert(mess,  function(index) {
			layer.close(index);
		});
	}
	
	/*img 上传*/
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
						max_file_size: '20M',
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
					}
				};
				$(clazz).bsPlupload(options);
			}
	  		//一张图片
	 fileUp('accessory', '#btn','transport','#cunImg','#accessory');
});
