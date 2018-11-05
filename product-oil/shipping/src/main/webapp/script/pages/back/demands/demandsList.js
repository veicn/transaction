$("#submitForm").on("click", function () {
    document.getElementById("search_from").submit();
});

$(".acceptance").click(function() {
    window.location.href = transactionServer + "pages/back/my_contract_list/index.htm";
});

//重置
$("#search_resect").click(function(){
	
	location.href=location;
});
layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	
	$(".conf_del").on('click',function(){
		
		var uuid=$(this).attr("conuuid");
		layer.confirm('Are you sure to Delete?', {title:'Info', btn:['OK','Cancel']}, function(index){
			//$appServer.get('pages/back/vesselAcceptance/deleteLogic.htm').put('confirmationSheetUuid', $!{fon.uuid})
			$.ajax({
				type:"post",
				url:shippingServer+'/pages/back/demands/delete.json',
				data: {uuid:uuid},
				success : function(msg) {
					if (msg != null) {
						layer.alert(msg.message,{title:'Info', btn:['OK']}, function(){
							location.href=location;
						});
					}
				},
				error:function(msg){
					layer.alert("error:" + msg.message, {title:'Error', btn:['OK']});
				}
			})
		})
	})
});