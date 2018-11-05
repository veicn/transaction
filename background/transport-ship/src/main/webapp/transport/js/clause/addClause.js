layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	own = layui.own,
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	var url = "";
	var shipPlate = {};
	var pallet = {};
	var sysShip = {};
	//货盘id
	var palletUuid = own.getHref('palletUuid');
	//船盘id
	var shipPlateUuid = own.getHref('shipPlateUuid');
	if(palletUuid&&shipPlateUuid){
		init();
	}else{
		message($.i18n("JAVASCRIPT00200"));
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
	
	//切除字符串最后 "/"
	var splitStr=function(str){
		var result="";
		var index=str.lastIndexOf("/");
		result=str.substring(0,index);
		return result;
	}
	
	
	//查询货盘详情，拼接装货量、装货地点要求、卸货地点要求
	var paramsPallet = {};
	paramsPallet.uuid=palletUuid;
 	$.ajax({
		type: "POST",
		url: shipServer+"/pallet/palleDetail.json",
		data: JSON.stringify(paramsPallet),
		dataType: "json",
		contentType:"application/json",
		async: false,
		success: function(data) {
			if(data.status == 0){
				//货盘信息
				var pallet=data.datas.pallet;
				//装港信息
				var loadArr=data.datas.listLoad;
				//卸港信息
				var unloadArr=data.datas.listUnload;
				//装港区域信息
				var loadRegionArr=data.datas.listRegion;
				//卸港区域信息
				var unloadRegionArr=data.datas.listUnregion;
				//装期LAYCAN
				var laycanBegTimeStamp = pallet.laycanBeg;	//laycanBeg
				if(laycanBegTimeStamp!=null){
					var laycanBeg = new Date(laycanBegTimeStamp).Format("yyyy-MM-dd");
					$("#pactBeg").val(laycanBeg);
				}else{
					$("#pactBeg").val("");
				}
				var laycanEndTimeStamp = pallet.laycanEnd;	//laycanEnd
				if(laycanEndTimeStamp!=null){
					var laycanEnd = new Date(laycanEndTimeStamp).Format("yyyy-MM-dd");
					$("#pactEnd").val(laycanEnd);
				}else{
					$("#pactEnd").val("");
				}
				//拼接装港信息
				var loads="";
				var loadQuantitys=0;
				for(var i=0;i<loadArr.length;i++){
					var loadPort=loadArr[i].loadPort;
					if(loadPort!=null && loadPort!=''){
						loads+=loadPort+"/";
					}
					var quantity=loadArr[i].quantity;	//获取数量
					loadQuantitys+=quantity;
				}
				//拼接卸港信息
				var unloads="";
				for(var i=0;i<unloadArr.length;i++){
					//装港/装港区域
					var unloadPort=unloadArr[i].unloadPort;
					if(unloadPort!=null && unloadPort!=''){
						unloads+=unloadPort+"/";
					}
				}
				
				//拼接装港区域信息
				var loadRegions="";
				var loadRegionQuantitys=0;
				for(var i=0;i<loadRegionArr.length;i++){
					//装港/装港区域
					var loadRegion=loadRegionArr[i].loadRegion;
					if(loadRegion!=null && loadRegion!=''){
						loadRegions+=loadRegion+"/";
					}
					var quantity=loadRegionArr[i].quantity;	//获取数量
					loadRegionQuantitys+=quantity;
				}
				//拼接卸港区域信息
				var unloadRegions="";
				for(var i=0;i<unloadRegionArr.length;i++){
					//装港/装港区域
					var unloadRegion=unloadRegionArr[i].unloadRegion;
					if(unloadRegion!=null && unloadRegion!=''){
						unloadRegions+=unloadRegion+"/";
					}
				}
				
				//切除字符串最后 "/"
				var loadResults=splitStr(loads+loadRegions);
				var unloadResults=splitStr(unloads+unloadRegions);
				var quantitys=loadQuantitys+loadRegionQuantitys;
				
				//装货量
				$("#minQuantity").val(quantitys);
				//装货地点要求
				$("#loadRegion").val(loadResults);
				//卸货地点要求
				$("#unloadRegion").val(unloadResults);
			}else{
				message($.i18n("ITSH405"))
			}
		},
		error: function(data) {
			message($.i18n(data.message));
        }
 	});
	
	// 显示数据
	function init(){
			//查询详细
	 	var params = {};
	 	params.palletUuid = palletUuid;
	 	params.shipPlateUuid = shipPlateUuid;
	 	$.ajax({
			type: "POST",
			url: shipServer+"/clause/toAddClause.json",
			data: JSON.stringify(params),
			dataType: "json",
			contentType:"application/json",
			success: function(data) {
				console.log(data)
				if(data.status == 0){
					shipPlate = data.datas.shipPlate;
					pallet = data.datas.pallet;
					shipPlate = data.datas.shipPlate;
					//pallet = data.datas.pallet;
					if(shipPlate!= null){
						//船盘信息回显
						$(".name").html(shipPlate.name);
						$(".name").attr('title',shipPlate.name);
						
						$(".type").html(shipPlate.type);
						$(".type").attr('title',shipPlate.type);
						
						$(".position").html(shipPlate.position);
						$(".position").attr('title',shipPlate.position);
						
						$(".open").html(shipPlate.open);
						$(".open").attr('title',shipPlate.open);
						
						$(".eta").html(shipPlate.eta);
						$(".eta").attr('title',shipPlate.eta);
						
						$(".etaCabinda").html(shipPlate.etaCabinda);
						$(".etaCabinda").attr('title',shipPlate.etaCabinda);
						
						$(".shipOwner").html(shipPlate.shipOwner);
						$(".shipOwner").attr('title',shipPlate.shipOwner);
						
						$(".shipPlateRemark").html(shipPlate.remark);
						$(".shipPlateRemark").attr('title',shipPlate.remark);
						
					}
					sysShip = data.datas.sysShip;
					if(sysShip){
						$(".VesselName").html(sysShip.name);
						$(".VesselName").attr('title',shipPlate.name);
						
						$(".IMO").html(sysShip.imo);
						$(".IMO").attr('title',sysShip.imo);
						
						var completeDate = sysShip.completeDate;
						if(completeDate!=null){
							var builtDate = new Date(completeDate).Format("yyyy-MM-dd");
							$(".Built").html(builtDate);
							$(".Built").attr('title',builtDate);
						}else{
							$(".Built").html(builtDate);
							$(".Built").attr('title',builtDate);
						}
						
						$(".VesselType").html(sysShip.useType);
						$(".VesselType").attr('title',sysShip.useType);
						
						$(".VesselSize").html(sysShip.type);
						$(".VesselSize").attr('title',sysShip.type);
						
						$(".Cubic").html(sysShip.capacity);
						$(".Cubic").attr('title',sysShip.capacity);
						
						$(".SDWT").html(sysShip.loadQuantity);
						$(".SDWT").attr('title',sysShip.loadQuantity);
						
						$(".LOA").html(sysShip.length);
						$(".LOA").attr('title',sysShip.length);
						
						$(".Beam").html(sysShip.wide);
						$(".Beam").attr('title',sysShip.wide);
						
						$(".Draft").html(sysShip.draft);
						$(".Draft").attr('title',sysShip.draft);
						
						$(".HullType").html(sysShip.hull);
						$(".HullType").attr('title',sysShip.hull);
					}
					var epMemberName = data.datas.epMemberName;
					if(epMemberName){
						$(".epMemberName").text($.i18n("JAVASCRIPT0023")+"("+epMemberName+")");
					}
				}else{
					layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")},function(index) {
						layer.close(index);
						window.history.back(-1);
					});
				}
			},
			error: function(data) {
				layer.alert($.i18n("JAVASCRIPT003"),{title:$.i18n("001"),btn:$.i18n("002")},function(index) {
					layer.close(index);
					window.history.back(-1);
				});
	        }
	 	});
	}
	//执行一个laydate实例
  	laydate.render({
    	elem: '#pactBeg'
    	,lang: lang_ver
	   ,done:function(value, date){
		   var htmlStr='<input type="text"  name="pactEnd" id="pactEnd" placeholder="'+$.i18n("JAVASCRIPT001")+'" readonly="readonly" class="cg-tbzq">';
	    	$('#pactBeg').parent().find('#pactEnd').remove();
	    	$('#pactBeg').parent().append(htmlStr);
	    	laydate.render({
	    		elem: '#pactEnd',
	    		lang: lang_ver,
	    		min:value,
	    		btns: ['clear', 'confirm'],
	    		done:function(value, date){
	    			if( parseInt( new Date($('#pactBeg').val()).getTime()) > parseInt( new Date(value).getTime())){
	    				message($.i18n("JAVASCRIPT00104"));
	    				setTimeout(function(){
	    					$('#pactEnd').val('');
	    				},1000)
	    			}
	    		}
	    	});
       }
  	});
  	$('#save').on('click',function(){ 
		var obj = own.serializeObject($('.form'));
		obj.palletUuid = pallet.uuid;
		obj.palletId = pallet.palletId;
		obj.shipPlateId = shipPlate.shipPlateId;
		obj.shipPlateUuid = shipPlate.uuid;
		if (obj.pactBeg != null && obj.pactBeg != ''){
			obj.pactBeg=new Date(obj.pactBeg);
		}
		if(obj.pactEnd != null && obj.pactEnd != ''){
			obj.pactEnd=new Date(obj.pactEnd);
		}
		var flag =check(obj);
		if (obj.laycanBeg > obj.laycanEnd){
			message($.i18n("JAVASCRIPT00107"));
			return false;
		}
		obj.minQuantity = delNumFormat(obj.minQuantity);
		obj.ws = delNumFormat(obj.ws);
		obj.demurrage = delNumFormat(obj.demurrage);
		obj.dockTime = delNumFormat(obj.dockTime);
		obj.commission = delNumFormat(obj.commission);
		if (!flag){return false};
		if (obj.laycanBeg > obj.laycanEnd){
			message($.i18n("JAVASCRIPT00107"));
			return false;
		}
		url =shipServer+"/clause/saveClause.json";
		layer.confirm($.i18n("JAVASCRIPT00198"),{title:$.i18n("001"),btn:[$.i18n("002"),$.i18n("003")]}, function(index){
			layer.close(index);
			$.ajax({
				type: "POST",
				url: url,
				data: JSON.stringify(obj),
				dataType: "json",
				contentType:"application/json",
				success: function(data) {
					console.log(data)
					if(data.status == 0){
						layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")},function(index) {
							layer.close(index);
							window.location.href=shipServer+"/pallet/palletQueryListShipowner.htm";
						});
					}else{
						message($.i18n(data.message));
					}},
			   error:function(){
				   message($.i18n("JAVASCRIPT003"));
			}});
  		});
  	});
	function check(obj){
		var flag =true;
		if (obj.pactBeg == null || obj.pactBeg == '' || obj.pactEnd == null || obj.pactEnd == ''){message($.i18n("JAVASCRIPT00112"));flag =false;return false;}
		if (obj.minQuantity == null || obj.minQuantity == '' ){message($.i18n("JAVASCRIPT0029"));flag =false;return false;}
		if (obj.loadRegion == null || obj.loadRegion == '' ){message($.i18n("JAVASCRIPT0030"));flag =false;return false;}
		if (obj.unloadRegion == null || obj.unloadRegion == '' ){message($.i18n("JAVASCRIPT0031"));flag =false;return false;}
		return flag;
	}
	
	//错误信息
	function message(mess){
		layer.alert(mess,{title:$.i18n("001"),btn:$.i18n("002")},function(index) {
			layer.close(index);
		});
	}	
	//校验电话号码
	function checkTel(value){  
		var flag =true;
	    var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;  
	    var isMob=/^((\+?86)|(\+86))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;  
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
	//超出的字符隐藏
	$(function(){
		$('.omit').each(function(){
			$(this).omit(20);
		})
	})
	
});