layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	own = layui.own,
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	var palletUuid = own.getHref('palletUuid');
	var shipPlateUuid = "";
	var roleFlag = own.getHref('roleFlag');
	var shipPlate = {};
	var affirm = {};
	var sysShip = {};
	
	// 显示数据
	if(palletUuid){
		init();
	}else{
		message($.i18n("ITSH016"));
	}
	function init(){
		var params = {};
			//查询详细
	 /*	params.uuid = clauseUuid;*/
		params.roleFlag = roleFlag;
	 	params.palletUuid = palletUuid;
	 	$.ajax({
			type: "POST",
			url: shipServer+"/pallet/confirmsDetail.json",
			data: JSON.stringify(params),
			dataType: "json",
			contentType:"application/json",
			success: function(data) {
				console.log(data)
				if(data.status == 0){
					shipPlate = data.datas.shipPlate;
					//pallet = data.datas.pallet;
					if(shipPlate!= null){
						shipPlateUuid = shipPlate.uuid;
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
						$(".epMemberName").text($.i18n("JAVASCRIPT00238")+"("+epMemberName+")");
					}
					affirm = data.datas.affirm
					if(affirm){
						params.uuid = affirm.uuid;
						var pactBeg = affirm.pactBeg;	//回显报盘装期开始
						if(pactBeg!==null){
							var pactBeg1 = new Date(pactBeg).Format("yyyy-MM-dd");
							$("#pactBeg").html(pactBeg1);
							$("#pactBeg").attr('title',pactBeg1);
						}else{
							$("#pactBeg").html("");
							$(".gang").remove();
						}
						var pactEnd = affirm.pactEnd;	//回显报盘装期结束
						if(pactEnd!=null){
							var pactEnd1 = new Date(pactEnd).Format("yyyy-MM-dd");
							$("#pactEnd").html(pactEnd1);
							$("#pactEnd").attr('title',pactEnd1);
						}else{
							$("#pactEnd").html("");
						}
						$('#minQuantity').val(affirm.minQuantity);
						$("#minQuantity").attr('title',affirm.minQuantity);
						$('#loadRegion').val(affirm.loadRegion);
						$("#loadRegion").attr('title',affirm.loadRegion);
						
						$('#unloadRegion').val(affirm.unloadRegion);
						$("#unloadRegion").attr('title',affirm.unloadRegion);
						$('#ws').val(affirm.ws);
						$("#ws").attr('title',affirm.ws);
						
						$('#demurrage').val(affirm.demurrage);
						$("#demurrage").attr('title',affirm.demurrage);
						$('#dockTime').val(affirm.dockTime);
						$("#dockTime").attr('title',affirm.dockTime);
						
						$('#pactSpeed').val(affirm.pactSpeed);
						$("#pactSpeed").attr('title',affirm.pactSpeed);
						$('#commission').val(affirm.commission);
						$("#commission").attr('title',affirm.commission);
						
						$('#remark1').val(affirm.remark);
						$("#remark1").attr('title',affirm.remark);
					}
				}else{
					layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")},function(index) {
						layer.close(index);
						window.history.back(-1);
					});
				}
			},
			error: function(data) {
				layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")},function(index) {
					layer.close(index);
					window.history.back(-1);
				});
	        }
	 	});
	}
	//返回上一页
	$('#return').on('click',function(){
		window.history.back(-1);
	});
	/*//完善租船协议
	$('.s-btn-box').on('click','#addAgreement',function(){
		window.location.href=shipServer+"/agreement/agreementSave.htm?shipPlateUuid="+shipPlateUuid+"&uuid="+clause.uuid+"&type=2";
	});*/
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
	//错误信息
	function message(mess){
		layer.alert(mess,{title:$.i18n("001"),btn:$.i18n("002")},function(index) {
			layer.close(index);
		});
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
});