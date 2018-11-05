
(function () {
    window.commonCPY.datePicker(['charterDate','ddr'],function(value, date, endDate){
        var value=value[0].split(' ').join('/')
    });


})();

//根据祖产信息查询imo
function findImoByShipId(code){
	var data = {};
	data["sysShipId"] = code;
    $.ajax({
        //post  get delete put
        type: "POST",
        //访问后台的路径
        url: shippingServer + "/pages/back/sysShip/findShipVoByShipId.json",
        //访问格式
		contentType: "application/json;charset=utf-8",
        //数据类型
		dataType: "json",
        //json对象 改为字符串形式的json
        data: JSON.stringify(data),
        success: function(data){
        	var imoInput = $("#creatVesselAcceptanceForm").find('input[name=imo]');
        	if(imoInput){
        		imoInput.val(data.imo);
			}else{
        		imoInput = $("#modifyVesselAcceptanceForm").find('input[name=imo]');
        		imoInput.val(data.imo);
			}
		},
        error: function () {
           console.log("fail");
        }
    })
}
$(function() {

	//删除附件
	$("#Q88Deleted").on("click",function(){
		
		layer.alert("Successfully deleted", {title:'Info', btn:['OK']});
	   	$("#uploadQ88FileNm").val("");
	   	$("#uploadQ88FileNm").attr("path", "");
	   	$("#uploadQ88").val("");
	   	$("#uploadQ88").attr("path", "");
		$("#Q88Deleted").hide();
	})

	$("#CpDeleted").on("click",function(){
		
		layer.alert("Successfully deleted", {title:'Info', btn:['OK']});
	   	$("#uploadCpFileNm").val("");
	   	$("#uploadCpFileNm").attr("path", "");
		$("#uploadCp").val("");
	   	$("#uploadCp").attr("path", "");
		$("#CpDeleted").hide();
	})

	//浏览文件（支持PDF和图片格式）
	$(".lookFileDoc").on("click",function(){
		var url = $(this).attr('fileUrl');
		var formatStr = $(this).attr('fileFormat').toString();
		var fileFormat = formatStr.substring(formatStr.indexOf(".")+1);
		var errorMessage = "Only support preview of  image/PDF format.";
		
		if(fileFormat == 'pdf'){				
			//pdf文件浏览
			window.open(shippingServer + "/pdfjs/web/viewer.html?file=" + encodeURIComponent(url),'_blank');
		}else if(fileFormat == 'doc' || fileFormat == 'docx'){
			//word浏览
			layer.alert(errorMessage,{title:'Info', btn:['OK']});
		}else if(fileFormat == 'xls' || fileFormat == 'xlsx'){
			//execl浏览
			layer.alert(errorMessage,{title:'Info', btn:['OK']});
		}else{
			//图片弹框
			layer.open({
				area: ['80%', '80%'],
				title: $(this).parents('.is-flag').find('.subCode').text(),
				btn: ['Return'],
				content:"<html><body><img src='"+ url +"' style='width:100%;height:100%'></img><body><html>"
			});
		}
	});


	layui.use(['layer','ossUpload'], function(){
		var $ = layui.jquery,upload = layui.ossUpload;
		upload.render({
		    elem: ".file",//绑定元素 id或class
		    moduleServer: shippingServer,//绑定元素 id或class
		    exts: 'png,jpg,jpeg,bmp,pdf,xls,xlsx,doc,docx', //允许上传的文件后缀
		    done: function(res,index,upload){
		    	if(res.status === 0){
				   	 var fileName = res.datas.originalName;
				   	 var fileUrl = res.datas.path;
				   	 if (index.id.indexOf('Q88')>-1) {
						   	$("#uploadQ88FileNm").val(fileName);
						   	$("#uploadQ88FileNm").attr("path",fileUrl);
						   	$("#uploadQ88").val(fileUrl);
						   	$("#uploadQ88").attr("path",fileUrl);
						   	$("#Q88Deleted").show();
				   	 } else {
				   		 	$("#uploadCpFileNm").val(fileName);
				   		 	$("#uploadCpFileNm").attr("path",fileUrl);
				   		 	$("#uploadCp").val(fileUrl);
				   		 	$("#uploadCp").attr("path",fileUrl);
				   		 	$("#CpDeleted").show();
				   	 }
		    	}
		    }
		})
	});

    commonCPY.selectReg();

	// 隐藏、显示 是否在线填写信息
	$('#listMsg').hide();
	$('#hideList').click(function() {
		$('#listMsg').show();
	});
	$('#showList').click(function() {
		$('#listMsg').hide();
	})

	var saveVesselAcceptanceFlag = true;
	// 新增船舶确认单提交form表单
	$('#saveVesselAcceptanceForm').unbind('click').click(function() {//每次绑定click前，先解绑上次的绑定，否则执行两次

		//校验表单
		//if(emptyCheck())
			var arr=$('#creatVesselAcceptanceForm')  //把你要验证的form表单数组赋值给arr
        console.log(commonCPX.FormValidation(arr))
		if(!commonCPX.FormValidation(arr) && saveVesselAcceptanceFlag){
            saveVesselAcceptanceFlag = false;
            //提交表单
            $.ajax({
                type : "POST",
                url : shippingServer + "/pages/back/vesselAcceptance/saveVA.json",
                data : $("#creatVesselAcceptanceForm").serialize(),// 序列化表单提交input值
                success : function(msg) {
                    saveVesselAcceptanceFlag = true;
                    layer.alert(msg.message,{title:'Info', btn:['OK']},function(){
                        //window.location.href = shippingServer + "/pages/back/vesselAcceptance/ConfirmationAgencyIndex.htm";
                        //返回上一页 并刷新页面
                        window.location.href = shippingServer + "/pages/back/vesselAcceptance/index.htm";
                    });
                },
                error : function(msg) {
                    saveVesselAcceptanceFlag = true;
                    layer.alert("error:" + msg.message);
                }
            });

		}

	})
	
	// 修改船舶确认单提交form表单
	$('#saveModifyVesselAcceptanceForm').unbind('click').click(function() {//每次绑定click前，先解绑上次的绑定，否则执行两次
		//校验表单
		//if(emptyCheck())
        var arr=$('#saveVesselAcceptanceForm')  //把你要验证的form表单数组赋值给arr
        commonCPX.FormValidation(arr)
        if(!commonCPX.FormValidation(arr)){
            //提交表单
            $.ajax({
                type : "POST",
                url : shippingServer + "/pages/back/vesselAcceptance/saveModify.json",
                data : $("#modifyVesselAcceptanceForm").serialize(),// 序列化表单提交input值
                success : function(msg) {
                    layer.alert(msg.message,{title:'Info', btn:['OK']},function(){
                        //window.location.href = shippingServer + "/pages/back/vesselAcceptance/ConfirmationAgencyIndex.htm";
                        //返回上一页 并刷新页面
                        window.location.href = shippingServer + "/pages/back/vesselAcceptance/index.htm";
                    });
                },
                error : function(msg) {
                    layer.alert("error:" + msg.message);
                }
            });
        }else{
            layer.alert("Fill in the wrong,please try again");

		}

	})
	
	//返回按钮
	$('#clickBack').unbind('click').click(function(){
		window.history.back();
	})
	
	//确认按钮
	$('#affirmButton').unbind('click').click(function(){
		$.ajax({
			type : "POST",
			url : shippingServer + "/pages/back/vesselAcceptance/affirmOrRefuse.json",
			data: {confirmationSheetUuid:$("#uuid").val(),flag:"1"},//确认：flag=1；拒绝：flag=0
			success : function(msg) {
				layer.alert(msg.message,{title:'Info', btn:['OK']},function(){
					//window.location.href = shippingServer + "/pages/back/vesselAcceptance/ConfirmationAgencyIndex.htm";
					//返回上一页 并刷新页面
					window.location.href = document.referrer;
				});
			},
			error : function(msg) {
				layer.alert("error:" + msg.message);
			}
		});
		return false;
	})
	
	//拒绝按钮
	$('#refuseButton').unbind('click').click(function(){
		$.ajax({
			type : "POST",
			url : shippingServer + "/pages/back/vesselAcceptance/affirmOrRefuse.json",
			data: {confirmationSheetUuid:$("#uuid").val(),flag:"0"},//确认：flag=1；拒绝：flag=0
			success : function(msg) {
				layer.alert(msg.message,{title:'Info', btn:['OK']},function(){
					//window.location.href = shippingServer + "/pages/back/vesselAcceptance/ConfirmationAgencyIndex.htm";
					//返回上一页 并刷新页面
					window.location.href = document.referrer;
				});
			},
			error : function(msg) {
				layer.alert("error:" + msg.message);
			}
		});
		return false;
	})
	
	//根据Confirm Online 返回的值，选中按钮：1选中no，0选中yes
	if($.trim($('#confirmOnline').val()) == '0'){
		$("input:radio[value='0']").attr('checked','true');
		$('#listMsg').show();
	}else{
		$("input:radio[value='1']").attr('checked','true');
		$('#listMsg').hide();
	}
	
	
});

//表单验证
function emptyCheck() {
	// 如果没有租船协议，则验证
	var shipAgreementIdVal=$('input[name="shipAgreementId"]').val();
	if(shipAgreementIdVal==null || shipAgreementIdVal == ""|| shipAgreementIdVal == undefined){
//		var vnDivVal = $("#vesselNameDiv").text();
		var vesselNameVal = null;
		vesselNameVal=$('input[name="vesselName"]').val();
		if(vesselNameVal==null || vesselNameVal == "" || vesselNameVal == undefined){
			layer.alert("The Vessel’s name content cann't be empty",{title:'Info', btn:['OK']});
			return false;
		}
		
		var imoVal=$('input[name="imo"]').val();
		if(imoVal==null || imoVal == ''|| imoVal == undefined){
			layer.alert("The IMO content cann't be empty",{title:'Info', btn:['OK']});
			return false;
		}
		
		var portOfDischargeVal=$('input[name="portOfDischarge"]').val();
		if(portOfDischargeVal==null || portOfDischargeVal == ''|| portOfDischargeVal == undefined){
			layer.alert("The Port of Discharge content cann't be empty",{title:'Info', btn:['OK']});
			return false;
		}
	}
	
	var rangeOfErrorVal=$('input[name="rangeOfError"]').val();
	if(rangeOfErrorVal==null || rangeOfErrorVal == ''|| rangeOfErrorVal == undefined){
		layer.alert("The Quantity (Tons) content cann't be empty",{title:'Info', btn:['OK']});
		return false;
	}
	
    // 如果在线 则验证
	var confirmOnlineVal = $('input:radio[name="confirmOnline"]:checked').val();
	var confirmOnlineDefultVal = $('input[name="confirmOnlineDefult"]').val();
	if(confirmOnlineVal != confirmOnlineDefultVal){
//		var ddrDivVal = $("#ddrDiv").text();
		var ddrVal=$('input[name="ddr"]').val();
		if(ddrVal==null || ddrVal == ''|| ddrVal == undefined){
			layer.alert("The DDR content cann't be empty",{title:'Info', btn:['OK']});
			return false;
		}
		
//		var pmDivVal = $("#pricingMethodDiv").text();
		var pricingMethodVal=$('input[name="pricingMethod"]').val();
		if(pricingMethodVal==null || pricingMethodVal == ''|| pricingMethodVal == undefined){
			layer.alert("The Pricing method content cann't be empty",{title:'Info', btn:['OK']});
			return false;
		}
		
//		var rtDivVal = $("#revenueTonDiv").text();
		var revenueTonVal=$('input[name="revenueTon"]').val();
		if(revenueTonVal==null || revenueTonVal == ''|| revenueTonVal == undefined){
			layer.alert("The REVENUE TON(WS) content cann't be empty",{title:'Info', btn:['OK']});
			return false;
		}
		
		var basicFreightRateVal=$('input[name="basicFreightRate"]').val();
		if(basicFreightRateVal==null || basicFreightRateVal == ''|| basicFreightRateVal == undefined){
			layer.alert("The Basic Freight Rate(WS) content cann't be empty",{title:'Info', btn:['OK']});
			return false;
		}
		
		var demurrageRatesVal=$('input[name="demurrageRates"]').val();
		if(demurrageRatesVal==null || demurrageRatesVal == ''|| demurrageRatesVal == undefined){
			layer.alert("The Demurrage Rates content cann't be empty",{title:'Info', btn:['OK']});
			return false;
		}
		
		var laytimeHoursVal=$('input[name="laytimeHours"]').val();
		if(laytimeHoursVal==null || laytimeHoursVal == ''|| laytimeHoursVal == undefined){
			layer.alert("The Laytime (hours) content cann't be empty",{title:'Info', btn:['OK']});
			return false;
		}
		
		
//		var cdDivVal = $("#charterDateDiv").text();
		var charterDateVal=$('input[name="charterDate"]').val();
		if(charterDateVal==null || charterDateVal == ''|| charterDateVal == undefined){
			layer.alert("The Charter Date content cann't be empty",{title:'Info', btn:['OK']});
			return false;
		}
	}

	//如果没有租船协议，则验证
	if(shipAgreementIdVal==null || shipAgreementIdVal == ""|| shipAgreementIdVal == undefined){
		var uploadQ88FileNmVal=$('input[name="uploadQ88FileNm"]').val();
		if(uploadQ88FileNmVal==null || uploadQ88FileNmVal == ''|| uploadQ88FileNmVal == undefined){
			layer.alert("The Upload Q88 content cann't be empty",{title:'Info', btn:['OK']});
			return false;
		}
		
//		var uploadCpFileNmVal=$('input[name="uploadCpFileNm"]').val();
//		if(uploadCpFileNmVal==null || uploadCpFileNmVal == ''|| uploadCpFileNmVal == undefined){
//			layer.alert("The Upload Vessel Acceptance content cann't be empty",{title:'Info', btn:['OK']});
//			return false;
//		}
	}
    
	
    var shippingAgentIdVal=$('input[name="shippingAgentId"]').val();
//    var saDivVal = $("#shippingAgentDiv").text();
    if(shippingAgentIdVal==null || shippingAgentIdVal == ''|| shippingAgentIdVal == undefined){
    	layer.alert("The Shipping Agent content cann't be empty",{title:'Info', btn:['OK']});
    	return false;
    }
    
    var linkmanVal=$('input[name="linkman"]').val();
    if(linkmanVal==null || linkmanVal == ''|| linkmanVal == undefined){
    	layer.alert("The Linkman content cann't be empty",{title:'Info', btn:['OK']});
    	return false;
    }
    
    var phoneNumberVal=$('input[name="phoneNumber"]').val();
    if(phoneNumberVal==null || phoneNumberVal == ''|| phoneNumberVal == undefined){
    	layer.alert("The Phone Number content cann't be empty",{title:'Info', btn:['OK']});
    	return false;
    }
    
    return true;
}

