layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	own = layui.own,
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	var clauseUuid = own.getHref('clauseUuid');
	var shipPlateUuid = own.getHref('shipPlateUuid');
	var roleFlag = own.getHref('roleFlag');
	var url = "";
	var shipPlate = {};
	var clause = {};
	var sysShip = {};
	var params = {};
	var agreementFlag = "";
	// 显示数据
	if(clauseUuid){
		init();
	}else{
		message($.i18n("ITSH608"));
	}
	function init(){
			//查询详细
	 /*	params.uuid = clauseUuid;
	 	params.roleFlag = roleFlag;*/
		params.roleFlag = roleFlag;
	 	params.uuid = clauseUuid;
	 	$.ajax({
			type: "POST",
			url: shipServer+"/clause/findClauseDetails.json",
			data: JSON.stringify(params),
			dataType: "json",
			contentType:"application/json",
			success: function(data) {
				console.log(data)
				if(data.status == 0){
					shipPlate = data.datas.shipPlate;
					agreementFlag = data.datas.agreementFlag;
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
					clause = data.datas.clause
					if(clause){
						params.uuid = clause.uuid;
						
						var pactBeg = clause.pactBeg;	//回显报盘装期开始
						if(pactBeg!==null){
							var pactBeg1 = new Date(pactBeg).Format("yyyy-MM-dd");
							$("#pactBeg").html(pactBeg1);
							$("#pactBeg").attr('title',pactBeg1);
						}else{
							$("#pactBeg").html("");
							$(".gang").remove();
						}
						var pactEnd = clause.pactEnd;	//回显报盘装期结束
						if(pactEnd!=null){
							var pactEnd1 = new Date(pactEnd).Format("yyyy-MM-dd");
							$("#pactEnd").html(pactEnd1);
							$("#pactEnd").attr('title',pactEnd1);
						}else{
							$("#pactEnd").html("");
						}
						
						$('#minQuantity').val(clause.minQuantity);
						$("#minQuantity").attr('title',clause.minQuantity);
						$('#loadRegion').val(clause.loadRegion);
						$("#loadRegion").attr('title',clause.loadRegion);
						
						$('#unloadRegion').val(clause.unloadRegion);
						$("#unloadRegion").attr('title',clause.unloadRegion);
						$('#ws').val(clause.ws);
						$("#ws").attr('title',clause.ws);
						
						$('#demurrage').val(clause.demurrage);
						$("#demurrage").attr('title',clause.demurrage);
						$('#dockTime').val(clause.dockTime);
						$("#dockTime").attr('title',clause.dockTime);
						
						$('#pactSpeed').val(clause.pactSpeed);
						$("#pactSpeed").attr('title',clause.pactSpeed);
						$('#commission').val(clause.commission);
						$("#commission").attr('title',clause.commission);
						
						$('#remark1').val(clause.remark);
						$("#remark1").attr('title',clause.remark);
					}
					if(roleFlag == '2'){
						if(clause.status=='2'&&agreementFlag=='0'){
							$('.s-btn-box').append("<button class='s-btn s-btn-blue' id='addAgreement'>"+$.i18n("JAVASCRIPT0026")+"</button><button class='s-btn s-btn-blue' id='return'>"+$.i18n("JAVASCRIPT0025")+"</button>");
						}else{
							$('.s-btn-box').append("<button class='s-btn s-btn-blue return' id='return'>"+$.i18n("JAVASCRIPT0025")+"</button>");
						}
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
	
	//返回上一页
	$('.s-btn-box').on('click','#return',function(){
		window.history.back(-1);
	});
	//完善租船协议
	$('.s-btn-box').on('click','#addAgreement',function(){
		window.location.href=shipServer+"/agreement/agreementSave.htm?shipPlateUuid="+shipPlateUuid+"&uuid="+clause.uuid+"&type=2";
	});
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
});