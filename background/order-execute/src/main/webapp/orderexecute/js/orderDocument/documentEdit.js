//layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
//	var layer = layui.layer,
//	form = layui.form,  // 载入form表单
//	table = layui.table;
//	window.$ = layui.jquery;
//	var laydate = layui.laydate;	
//
//  	//执行一个laydate实例
//  	laydate.render({
//    	elem: '#billDate' //指定元素
//  	});	
//	
//	// 提交函数
//	$('.saveBtn').on('click',function(){
//		if($("#contractNo").val() == "") {
//			layer.alert($.i18n("orderexecute.code.00172"));
//			return;
//		}
//		
//    	var params = {};s
//    	params.uuid = $("#uuid").val();
//    	params.orderDocumentId = $("#orderDocumentId").val();
//    	params.documentType = $("#documentType").val();
//    	params.tradeCategory = $("#tradeCategory").val();
//    	
//    	params.orderNo = $("#orderNo").val();
//    	params.contractNo = $("#contractNo").val();
//    	params.shipName = $("#shipName").val().trim();
//    	params.remark = $("#remark").val().trim();
//    	
//    	if($("#billDate").val()) {
//        	params.billDate = $("#billDate").val();
//    	}
//    	
//    	params.fileInfoList = fileInfoList;
//    	var index = 0;
//    	
//    	$.ajax({
//    		type: "POST",
//    		url: orderServer+"/buyerCenter/orderDocument/saveDocument.json",
//    		data: JSON.stringify(params),
//    		async: false,
//			contentType:"application/json",
//			beforeSend: function (request) {
//				index = layer.load();
//			},
//    		success: function(data) {
//    			layer.close(index);
//    			
//				if(data.status == 0){
//					layer.alert($.i18n("orderexecute.code.00146"), function(index) {
//						back();
//					});
//				}
//			},
//			error: function(data) {
//				layer.close(index);
//				layer.alert("request error");
//	        }
//    	});
//	});
//	
//	// 提交函数
//	$('.backBtn').on('click',function(){
//		back();
//	});
//	
//	function back() {
//		// DOCUMENTLIST  FILELIST
//		if($("#formPage").val() == "DOCUMENTLIST") {
//			window.location.href = orderServer+"/buyerCenter/orderDocument/documentList.htm"
//				+ "?uuid=" + $("#uuid").val();
//		} else {
//			window.location.href = orderServer+"/buyerCenter/orderDocumentFile/documentFileList.htm";
//		}
//	}
//});
//  	
//	var fileInfoList = new Array();	
//
//	function gotoPage(documentType, orderDocumentId) {
//		window.location.href = orderServer+"/buyerCenter/orderDocument/documentEdit.htm"
//			+ "?uuid=" + $("#uuid").val()
//			+ "&orderDocumentId=" + orderDocumentId
//			+ "&documentType=" + documentType
//			+ "&tradeCategory=" + $("#tradeCategory").val()
//			+ "&formPage=" + $("#formPage").val()
//			+ "&showDetail=" + $("#showDetail").val();
//	}
//	
//	var fileCode;
//	var fileName;
//	
//	function showUpdate(code, name) {
//		fileCode = code;
//		fileName = name;
//	}
//	
//	function uploadSuccess(fileInfo) {
//		for(var i = 0; i < fileInfoList.length; i++) {
//			if(fileCode == fileInfoList[i].fileCode) {
//				fileInfoList.splice(i, 1);
//				break;
//			}
//		}
//		
//		var filePath = fileInfo.path;
//		var info = {};
//		info.fileName = fileName;
//		info.fileCode = fileCode;
//		info.originalName = fileInfo.originalName;
//		info.filePath = filePath;
//		info.fileFormat = fileInfo.suffix;
//		info.fileSize = fileInfo.size;
//
//		fileInfoList.push(info);
//		
//		$("#input_" + fileCode).val(fileInfo.originalName);
//		$("#down_" + fileCode).css("display", "block");
//		$("#downLink_" + fileCode).removeAttr("href");
//		$("#downLink_" + fileCode).attr("href", filePath);
//	}
