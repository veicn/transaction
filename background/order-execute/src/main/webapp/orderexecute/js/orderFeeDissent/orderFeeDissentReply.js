layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;	
	var feeDissentId;
	
	// 请求弹框
	$('.reply').on('click',function(){
		feeDissentId = $(this).attr("feeDissentId");
		
		// 请求弹框
		$.ajax({
			//type:"get",
			url: orderServer+'/bomp/orderFeeDissent/editReply.htm',
			success:function(data) {
				//弹框运行
			 	layer.open({
					  type: 1,
					  area: ['550px','350px'],
					  btn: [$.i18n("orderexecute.code.00153"), $.i18n("orderexecute.code.00154")],
					  btnAlign: 'c' ,//按钮居中
					  title:$.i18n("orderexecute.code.00189"),
					  content: data,
					  yes: function(index, layero){
							if($("#replyContent").val().trim() == "") {
					    		layer.alert($.i18n("orderexecute.code.00190"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
					    		return;
					    	}
						  
						  saveReplyContent(0);
						  layer.close(index);
					  },
					  btn2: function(index, layero){
						  layer.close(index);
						  return false;
					  },
					  cancel: function(index, layero){
						  layer.close(index);
						  return false;
					  }
			    });
			}
		})
	});
	
	// 回复保存
	function saveReplyContent() {
    	var params = {};
    	params.feeDissentId = feeDissentId;
    	params.orderId = $("#orderId").val();
    	params.dissentType = $("#dissentType").val();
    	params.replyContent = $("#replyContent").val().trim();
		
    	$.ajax({
    		type: "POST",
    		url: orderServer+"/sellerCenter/orderFeeDissent/saveReplyContent.json",
    		data: JSON.stringify(params),
    		async: false,
			contentType:"application/json",
    		success: function(data) {
				if(data.status == 0){
					$("#reply_" + feeDissentId).remove();

					var html = [];
					html.push('<div class="buy-r-b">');
					html.push('<p class="buy-r-tit">');
					html.push('<div class="buy-r-tit" style="display:inline-block; vertical-align: top;">');
					html.push('<pre class="pre_font">['+$.i18n("orderexecute.code.00189")+']</pre>');
					html.push('</div>');
					html.push('<div style="display:inline-block; vertical-align: top; margin-left: 10px;">');
					html.push('<pre class="pre_font">' + $("#replyContent").val() + '</pre>');
					html.push('</div>');
					html.push('</p>');
					html.push('<p class="buy-r-time">'+$.i18n("orderexecute.code.00191")+'：' + data.datas + '</p>');
					html.push('</div>');

					$("#content_" + feeDissentId).append(html.join(""));
					$("#replyDiv").css("display", "none");
					$("#replyContent").val("");
				}
			},
			error: function(data) {
				layer.alert("error:" + data.message,{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
	        }
    	});
	}
	
	// 清空
	$('.clearContent').on('click',function(){
		$("#replyContent").val("");
	});
	
	// 返回
	$('.backPageReply').on('click',function(){
		backPageReply();
	});	
	
	function backPageReply() {
		
	}
});		