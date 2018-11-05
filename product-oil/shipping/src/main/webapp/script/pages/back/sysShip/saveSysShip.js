
layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	own = layui.own,
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;

$(function() {
	  commonCPY.select();
	// 提交form表单
	$('#saveSysShipForm2').click(function() {
		//检验
		/*if($("#imo").val()=="") {
			  layer.alert("imo cann't be empty");
			  return;
		  }
		  if($("#vesselName").val()=="") {
			  layer.alert("vesselName cann't be empty");
			  return;
		  }
		  if($("#built").val()=="") {
			  layer.alert("builtDate cann't be empty");
			  return;
		  }*/
        var arr=$('#saveSysShipForm');
        commonCPX.FormValidation(arr);
        if(!commonCPX.FormValidation(arr)){
            $.ajax({
                type : "POST",
                url : '/shipping/pages/back/sysShip/updateSaveSysShip.json',//updateSaveSysShip.json
                data : $("#saveSysShipForm").serialize(),// 序列化表单提交input值
                success : function(msg) {
                    layer.alert(msg.message,{title:'Info', btn:['OK']},function(){
                        //返回上一页 并刷新页面
                        window.location.href = "/shipping/pages/back/sysShip/index.htm";
                    });
                },
                error : function(msg) {
                    layer.alert("error:" + msg.message);
                }
            });
		}else{
        	alert('Fill in the wrong,please try again');
		}

	})
	
});

//浏览文件（支持PDF和图片格式）
$(".lookFileDoc").on("click",function(){
	var url = $(this).attr('fileUrl');
	var fileFormat = $(this).attr('fileFormat');
	
	
	if(fileFormat == 'pdf'){				
		//pdf文件浏览
		window.open(shippingServer + "/pdfjs/web/viewer.html?file=" + encodeURIComponent(url),'_blank');
	}else if(fileFormat == 'doc' || fileFormat == 'docx'){
		//word浏览
		layer.alert("不支持word格式，目前支持pdf和图片浏览");
		//word转换pdf
		
		//window.open(orderServer + "/pdfjs/web/viewer.html?file=" + encodeURIComponent(url),'_blank');
	}else if(fileFormat == 'xls' || fileFormat == 'xlsx'){
		//execl浏览
		layer.alert("不支持execl格式，目前支持pdf和图片浏览");
		//execl转换pdf
		
	}else{
		//图片弹框
		layer.open({
			area: ['80%', '80%'],
			title: $(this).parents('.is-flag').find('.subCode').text(),
			btn: ['返回'],
			content:"<html><body><img src='"+ url +"' style='width:100%;height:100%'></img><body><html>"
		});
	}
});


layui.use(['layer','ossUpload'], function(){
	var $ = layui.jquery,upload = layui.ossUpload;
	upload.render({
	    elem: ".br btn-b vs-down",//绑定元素 id或class
	    moduleServer: shippingServer,//绑定元素 id或class
	    exts: 'jpg,jpeg,bmp,pdf,xls,xlsx,doc,docx,png', //允许上传的文件后缀
	    done: function(res, index,upload){
	    	
	    	 if(res.status === 0){
	                var fileName = res.datas.originalName;
	                var fileUrl = res.datas.path;
	                    $("#accessoryFileNm").val(fileName);
	                $("#accessoryFileNm").attr("path", fileName);

	                $("#accessory").attr("path", fileUrl);
	                $("#accessory").val(fileUrl);
	              //  $("#FileDelete").show();

	            }
	    	
	    }
	})
});

});

