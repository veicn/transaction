layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;

	// 查询函数
	$('.chaxun').on('click',function(){
		search16();
	}); 
	
	// 返回上一页
	$('.goto').on('click',function(){
		window.location.href = document.referrer;
	}); 
	
	(function(){
		
		function GetRequest() {   
		   var url = location.search; //获取url中"?"符后的字串   
		   var theRequest = new Object();   
		   if (url.indexOf("?") != -1) {   
		      var str = url.substr(1);   
		      strs = str.split("&");   
		      for(var i = 0; i < strs.length; i ++) {   
		         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);   
		      }   
		   }   
		   return theRequest;   
		}   
		var data = GetRequest()
		console.log(data.uuid);
		$.ajax({
			type: "POST",
			url: shipServer+"/shipperstatements/shipperfindStatementsByUuid.json",
			data:{uuid:data.uuid},
			dataType: "json",
				success: function(data) {
					console.log(data)
					$('.receiptCode').html(data.datas.receiptCode).parent().parent().attr('title',data.datas.receiptCode);
					$('.pactCode').html(data.datas.agreementCode).parent().parent().attr('title',data.datas.agreementCode);
					$('.payee').html(data.datas.payee).parent().parent().attr('title',data.datas.payee);
					$('.payer').html(data.datas.payer).parent().parent().attr('title',data.datas.payer);
					$('.receiptAccessory').html(data.datas.receiptAccessory).parent().parent().attr('title',data.datas.receiptAccessory);
					if (data.datas.receiptAccessoryPath != null){
						$('.receiptAccessory').attr("title",data.datas.receiptAccessory);
						$('.receiptAccessory').html(data.datas.receiptAccessory);
						$('#btn').removeAttr('hidden','true');
						$('#btn').find('a').attr('href', data.datas.receiptAccessoryPath);
					}
					$('.freightQuantily').html(data.datas.freightQuantily);
					$('.freightFlatrate').html(data.datas.freightFlatrate);
					$('.freightRate').html(data.datas.freightRate);
					$('.freightTotal').html(data.datas.freightTotal);
					$('.freightOverageQuantily').html(data.datas.freightOverageQuantily);
					$('.freightOverageFlatrate').html(data.datas.freightOverageFlatrate);
					$('.freightOverageRate').html(data.datas.freightOverageRate);
					$('.freightOveragePct').html(data.datas.freightOveragePct);
					$('.freightOverageTotal').html(data.datas.freightOverageTotal);
					$('.addressCommissionQuantily').html(data.datas.addressCommissionQuantily);
					$('.addressCommissionPct').html(data.datas.addressCommissionPct);
					$('.addressCommissionTotal').html(data.datas.addressCommissionTotal);
					$('.balance').html(data.datas.balance);
				}
		})
	})();

	function search16(){
		var receiptCode = $('#receiptCode').val();
		var pactCode = $('#pactCode').val();
		console.log(receiptCode);
		console.log(pactCode);
		window.location.href= shipServer + "/shipperstatements/shipperpactList.htm?receiptCode=" + receiptCode+'&'+ "pactCode=" + pactCode;
	};
	
	// 查看结算数据
	$('.check').on('click',function(){
		var uuid = $('#uuid').val();
		window.location.href= shipServer +"/shipperstatements/shipperfindPactStatementsByUuid.json?uuid="+uuid;
	}); 
	
});