/*船代*/
$("#submitFormByAgent").on("click", function () {
    document.getElementById("argFromByAgent").submit();
});


$("#empty").on("click", function () {
	/*$("input").val('');
	$(".select-show-text").html('');*/
	location.href=location;
});


/*贸易商*/
$("#submitFormByMerchant").on("click", function () {
    document.getElementById("argFromByMerchant").submit();
});

layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	
	$("#conf_del").on('click',function(){
		
		var uuid=$(this).attr("conuuid");
		layer.confirm('Are you sure to Delete?', {title:'Info', btn:['OK','Cancel']}, function(index){
			//$appServer.get('pages/back/vesselAcceptance/deleteLogic.htm').put('confirmationSheetUuid', $!{fon.uuid})
			$.ajax({
				contentType : 'application/json',
				type:"post",
				url:shippingServer+'/pages/back/agreement/deleteAgreement.json',
				data: uuid,
				success : function(msg) {
					if (msg != null) {
						layer.alert(msg.message,function(){
							location.href=location;
						});
					}
				},
				error:function(msg){
					layer.alert("error:" + msg.message);
				}
			})
			})
		})
});

function uploadDoc(orderId){
	if(orderId == null){
		console.error("orderId is empty");
		return;
	}
    $.ajax({
        url:shippingServer + "/pages/back/agreement/agreement/orderuuid.json?orderId=" + orderId,
        type:"GET",
        dataType: "json",    //数据类型
        data:null,
        success: function(data){
            var uuid = data.datas;
            if(uuid != null && uuid.length>0 ){
                var url = transactionServer+ "pages/back/billCoreUpload/index.htm?uuid=" +uuid;
                window.location.href = url;
			}else{
            	console.log("uuid is empty");
			}

        },
        error: function (e) {

        }
    })
}
