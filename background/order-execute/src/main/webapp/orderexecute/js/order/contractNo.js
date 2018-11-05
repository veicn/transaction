layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer;
	var laydate = layui.laydate;
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;

		
	//录入合同号
	$('.contractNoFn').on('click',function(){
		var uuid = $(this).attr("attr-uuid");
		
		$.ajax({
			url:orderServer+'/bomp/order/editContractNo.htm',
			success:function(data1){
				 $.ajax({
					url:orderServer+'/common/order/editContractNo.json?&uuid='+uuid,
					async:true,
					success:function(data2){ 
						//弹框 
						layer.open({
							type:1,
							title:$.i18n("orderexecute.code.002020"),
							area: ['480px', '246px'],
							content: data1,
							btn: [$.i18n("orderexecute.code.00153"), $.i18n("orderexecute.code.00198")],
							btnAlign: 'c',
							closeBtn: 0,
							yes: function(){
								save_yes_callback();
							}
						});	
						console.log(data2.datas);
						$('#sellerContractNo').val(data2.datas.sellerContractNo);
						$('#buyerContractNo').val(data2.datas.buyerContractNo);
						$('#uuid').val(data2.datas.uuid);
					},
					error:function(data2){
						alert('request error');
					} 
				})	
			},
			error:function(data1){
				alert('request error');
			}
		})
	})
	
	function save_yes_callback(){
		if($('#sellerContractNo').val() == "" && $('#buyerContractNo').val() == "") {
			layer.alert($.i18n("orderexecute.code.002021"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
			return;
		}
		console.log($("#contract_no_form").serialize());
		$.ajax({
      		url:orderServer+"/common/order/saveContractNo.json",
      		data:$("#contract_no_form").serialize(),
      		type:"post",
      		success:function(result){
      			console.log(result);
      			if(result.status != 0){
      				if(result.message == null){
      					layer.alert($.i18n("orderexecute.code.00005"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
      				}else{
      					layer.alert($.i18n(result.code),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
      				}
      			}else{
      				layer.alert($.i18n("orderexecute.code.00149"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")}, function(index){
      					layer.closeAll();
      					location.reload();
      				});
      			}
      		},
      		error:function(){
      			layer.alert("request error",{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
      		}
      	})  
	}	
});	

