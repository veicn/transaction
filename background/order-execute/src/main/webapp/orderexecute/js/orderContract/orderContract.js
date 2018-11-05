layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form, // 载入form表单
	table = layui.table; // 表格
	window.$ = layui.jquery; // 
	
	var laydate = layui.laydate;	
 	//执行一个laydate实例

	// 添加
	$('.saveFn').on('click',function(){
		$.ajax({
    		type: "POST",
    		url: orderServer+"/buyerCenter/contract/add.htm",
    		data: $('#contractForm').serialize(),
    		success: function(data) {
				alert($.i18n("orderexecute.code.00169"));
				window.location.href = orderServer+"/buyerCenter/contract/list.htm"
			},
			error: function(data) {
	            alert("request error");
	        }
    	});
	 });

	// 修改
	$('.updateFn').on('click',function(){
		
		$.ajax({
    		type: "POST",
    		url: orderServer+"/buyerCenter/contract/edit.htm" + "?cid=" + $("#contractId").val(),
    		data: $('#contractForm').serialize(),
    		success: function(data) {
					alert($.i18n("orderexecute.code.00169"));
					window.location.href = orderServer+"/buyerCenter/contract/list.htm"
			},
			error: function(data) {
	            alert("request error");
	        }
    	});
		
	 });
	
	
	
	function back(){	
	}
});

var fileName;

function showUpdate(name) {
	fileName = name;
}

var fileInfoList = new Array();	
function uploadSuccess(fileInfo) {
	for(var i = 0; i < fileInfoList.length; i++) {
		if(filePath == fileInfoList[i].filePath) {
			fileInfoList.splice(i, 1);
			break;
		}
	}
	var filePath = fileInfo.path;
	var info = {};
	info.fileName = fileName;
	info.filePath = filePath;
	info.fileFormat = fileInfo.suffix;
	info.fileSize = fileInfo.size;
	
	fileInfoList.push(info);
	
	$("#input_" + fileName).val(filePath);
	$("#down_" + fileName).css("display", "block");
	$("#downLink_" + fileName).removeAttr("href");
	$("#downLink_" + fileName).attr("href", orderServer+"/common/doc/download.htm?path=" + filePath);
}

