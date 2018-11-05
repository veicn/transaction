layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;

	//页面记录的uuid
	var uuid = "";
  
	//超出隐藏变为...
	$(function(){
		$('.omit').each(function(){
			$(this).omit(20);
		})
	})
	//
	// 合同查看结算数据
	$('.htcheck').on('click',function(){
		var that = this;
		htchecked(that);
	}); 
	function htchecked(that){
		var uuid = $(that).parents('.s-mod-bcont').find('#htuuid').val();
		
		window.location.href= shipServer + "/statements/tofindPactStatementsByUuid.htm?uuid="+uuid;
	}
	function hideElement(){
		if($('#isData').val()){
			$('.c-mod').hide();
		}
	}
	hideElement()
		
	// 代理查看结算数据
	$('.dlcheck').on('click',function(){
		var that = this;
		dlchecked(that);
	}); 
	function dlchecked(that){
		var uuid = $(that).parents('.s-mod-bcont').find('#dluuid').val();
		window.location.href= shipServer + "/statements/toagreementsStatementsByUuid.htm?uuid="+uuid;
	}
	
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
	
	// 查询函数
	$('.searchBtn').on('click',function(){
		search();
	}); 
	
	// 重置函数
	$('.searchBtn2').on('click',function(){
		window.location.href=shipServer+"/pallet/palletQueryList.htm";
	}); 
	// 申请租船
	$('#insert').on('click',function(){
		window.location.href=shipServer+"/pallet/orderPageList.htm?palletType=1";
	}); 
	// 修改
	$('.editBtn').on('click',function(){
		var uuid = $(this).attr('attr-data');
		window.location.href=shipServer+"/pallet/palletEdit.htm?palletUuid="+uuid+"&type=true";
	}); 
	function search(){
		var pactCode = $('#pactCode').val();
		var receiptCode = $('#receiptCode').val();
		var agreementCode = $('#agreementCode').val();
		window.location.href=shipServer+"/statements/agreementPactList.htm?receiptCode="+ receiptCode+'&'+"agreementCode="+agreementCode+'&'+"pactCode="+pactCode;
	}

	//查询协议页面
	$('.queryBtna').on('click',function(){
		uuid = $(this).attr("attr-data");
		// 查询代理协议
	  window.location.href=shipServer+"/agreement/agreementDetailQuery.htm?palletUuid="+uuid;
	});
	
	//查询需求页面
	$('.queryBtn').on('click',function(){
		uuid = $(this).attr("attr-data");
			window.location.href=shipServer+"/pallet/seePallet.htm?uuid="+uuid;
	});
	

	// 删除函数
	$('.delBtn').on('click',function(){
		var uuid = $('#dluuid').val();
	    //弹框运行
	    layer.confirm($.i18n("JAVASCRIPT0022"), {title:$.i18n("001"),btn:[$.i18n("002"),$.i18n("003")]}, function(index){
	    	var params = {};
	    	params.uuid = uuid;
	    	$.ajax({
	    		type: "POST",
	    		url: shipServer+"/statements/deleteStatements.json",
	    		data: JSON.stringify(params),
	    		dataType:"json",
	    		contentType:"application/json",
	    		success: function(data) {
					if(data.status == 0){
						layer.alert($.i18n("JAVASCRIPT0021"), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
						    layer.close(index);
						    location.reload();
						});
					} else {
						layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
						    layer.close(index);
						});
					}
				},
				error: function(data) {
					layer.alert($.i18n("JAVASCRIPT003"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
					    layer.close(index);
					});
		        }
	    	});
	    });
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

//跳转页面
$('.insertBtns').on('click',function(){
	window.location.href=shipServer+"/palletList.htm"
})
$('.insertBtnsd').on('click',function(){
	window.location.href=shipServer+"/palletQueryList.htm"
})


