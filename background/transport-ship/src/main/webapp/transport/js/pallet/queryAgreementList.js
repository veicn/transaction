layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;

	//回车查询
	$("body").keydown(function() {
        if (event.keyCode == "13") {//keyCode=13是回车键
            $('.searchBtn').click();
        }
    });
	
	//按钮展开
	$('body').on('click','.gengduoFn',function(){
		var val  = $(this).next().css('display');
		$('.gengduo').each(function(){
			$(this).hide();
		});
		if (val =='none'){
			 $(this).next().show();
		} else {
			 $(this).next().hide();
		}
	});
	//关闭
	$('body').on('click','.ico-open-b',function(){
		$(this).removeClass('ico-open-b');
		$(this).addClass('ico-close-b');
		$(this).parents('div').next().hide();
	});
	
	//超出的字符隐藏
	$(function(){
		$('.omit').each(function(){
			$(this).omit(20);
		})
	})
	
	//物流跟踪
	$('.track').on('click',function(){
		var uuid = $(this).attr('attr-data');
		window.open(shipServer+"/page/#/transport?agreementUuid="+uuid);
	});

	// 查询函数
	$('.searchBtn').on('click',function(){
		search();
	}); 
	// 重置函数
	$('.searchBtn2').on('click',function(){
		window.location.href=shipServer+"/pallet/agreementList.htm";
	}); 
	function search(){
		var searchCode = $('#search').val();
		//var status = $('#status').val();
		var oilType = $('#oilType').val();
		//window.location.href=shipServer+"/pallet/agreementList.htm?code="+ searchCode+"&status="+status;
		window.location.href=shipServer+"/pallet/agreementList.htm?code="+ searchCode+"&oilType="+oilType;
	}
	var code = getQueryString('code');
	if (code != null && code !=''){
		$('#search').val(code);
	}
	var oilType = getQueryString('oilType');
	if (oilType != null && oilType !=''){
		$('#oilType').val(oilType);
	}
	

	//查询页面
	$('.queryBtn').on('click',function(){
		uuid = $(this).attr("attr-data");
		// 查询代理协议
		window.location.href=shipServer+"/agreement/agreementDetailQuery.htm?uuid="+uuid;
	});
	
	// 修改代理协议
	$('.editBtn').on('click',function(){
		var uuid = $(this).attr("attr-data");
		var palletUuid =  $(this).attr("attr-palletUuid");
		// 请求弹框
		$.ajax({
			type:"get",
			url:"../transport/bomb/agreement.vm",
			success:function(data) {
				//弹框运行
			 	layer.open({
					  type: 1,
					  area: ['600px','650px'],
					  btn: ['提交', '关闭'],
					  btnAlign: 'c' ,//按钮居中
					  title:'修改代理协议',
					  content: data,
					  yes: function(index, layero){
						  var params = {};
						  params.uuid = uuid;
						  params.palletUuid = palletUuid;
						  if ($('#signDate').val() != "" && $('#signDate').val() != null){
							  params.signDate =new Date($('#signDate').val() );
						  }
						  params.firParty=$('#firParty').val();
						  params.secParty=$('#secParty').val();
						  params.quantity=$('#quantity').val();
						  params.accessory=$('#accessory').val();
						  params.sysShipUuid = $(".selectData .layui-this").attr('lay-value');
						  params.shipName= $(".selectData .layui-this").text();
						  $.ajax({
							type: "POST",
							url: shipServer+"/agreement/saveAgreement.json",
							data: JSON.stringify(params),
							dataType: "json",
							contentType:"application/json",
							async: false,
							success: function(data) {  
									if(data.status == 0){
										layer.alert('成功', function(index) {
										    layer.close(index);
										    location.reload();
										});
									} else {
										layer.alert(data.message, function(index) {
											 layer.close(index);
										});
									}
							},
							error:function(){
								layer.alert("系统异常", function(index) {
									 layer.close(index);
								});
							}
						});
					  },
					  btn2: function(index, layero){
						   layer.confirm('尚未保存，确定关闭？', function(index){
							   layer.close(index);
							    location.reload();
						   });
							  return false;
					  }
			    });
			 	
			 	 //查询代理协议
			 	 var obj={};
			 	 obj.uuid=uuid;
			 	 $.ajax({
			 		 type: "POST",
			 		 url: shipServer+"/agreement/findAgreementDetail.json",
			 		data: JSON.stringify(obj),
					dataType: "json",
					contentType:"application/json",
			 		 success: function(data) {  
			 			 if(data.status == 0){
			 				 var dd = data.datas;
			 				 $('#signDate').val( new Date(dd.signDate).Format("yyyy-MM-dd"))
			 				 $('#firParty').val(dd.firParty)
			 				 $('#secParty').val(dd.secParty)
			 				 $('#quantity').val(dd.quantity)
			 				 $('#accessory').val(dd.accessory)
			 				  $('#accessory').attr("title",dd.accessory);
			 				$('.layui-unselect').val(dd.shipName)
			 				$(".selectData dd").each(function(){
			 					$(this).removeClass('layui-this')
			 					if ($(this).text() == dd.shipName){
			 						$(this).addClass('layui-this')
			 					}
			 				 })					 					 
			 			 } else {
			 				layer.alert(data.message, function(index) {
							    layer.close(index);
							});
			 			 }
			 		 }
			 	 });
			 	form.render();
			}
		})
  });
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
	//获取地址栏参数
	function getQueryString(name) {
	    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	    var r = window.location.search.substr(1).match(reg);
	    if ( r != null ){
	       return decodeURI(r[2]);
	    }else{
	       return null;
	    } 
	 }
});
