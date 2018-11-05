//查询
$("#submitForm").on("click", function () {
    document.getElementById("search_from").submit();
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
	//删除确认单
	$(".conf_del").on('click',function(){
		
		var uuid=$(this).attr("conuuid");
		layer.confirm('Are you sure to Delete?', {title:'Info', btn:['OK','Cancel']}, function(index){
			$.ajax({
				type:"post",
				url:shippingServer+'/pages/back/vesselAcceptance/deleteLogic.json',
				data: uuid,
				success : function(msg) {
					if (msg != null) {
						layer.alert(msg.message,{title:'Info', btn:['OK']},function(){
							location.href=location;
						})
					}
				},
				error:function(msg){
					layer.alert("error:" + msg.message);
				}
			})
			})
		});
		
		//航程结束ajax
    $(".voyage_end").click(function (){
    	var uuid=$(this).attr("conuuid");
    	var status=$(this).attr("constatus");
    	
    	var params = {};
    	
    	params.uuid = uuid;
		params.status = status;
		layer.confirm('The end of this voyage?', {title:'Info', btn:['OK','Cancel']}, function(index){
				$.ajax({
					contentType : 'application/json', 
					type:"post",
					dataType:"json",
					url:shippingServer+'/pages/back/vesselAcceptance/updateConfiram.json',
					data: JSON.stringify(params),
					success : function(msg) {
						if (msg != null) {
							layer.alert(msg.message,{title:'Info', btn:['OK']},function(){
								location.href=location;
							})
						}
					},
					error:function(msg){
						layer.alert("error:" + msg.message);
					}
				})
			});
    });
    
    //卖家确认
    $("#confirmcli").click(function(){
    	var uuid=$(this).attr("conuuid");
    	var status=$(this).attr("constatus");
    	
    	var params = {};
    	
    	params.uuid = uuid;
		params.status = status;
    	
		layer.confirm('Do you want to confirm?', {title:'Info', btn:['OK','Cancel']}, function(index){
				$.ajax({
					contentType : 'application/json', 
					type:"post",
					dataType:"json",
					url:shippingServer+'/pages/back/vesselAcceptance/conconfirmcli.json',
					data: JSON.stringify(params),
					success : function(msg) {
						if (msg != null) {
							layer.alert(msg.message,{title:'Info', btn:['OK']},function(){
								location.href=location;
							})
						}
					},
					error:function(msg){
						layer.alert("error:" + msg.message);
					}
				})
			});
    })
		
});
$(".acceptance").click(function() {
	 window.location.href = transactionServer + "pages/back/my_contract_list/index.htm";
});

$(function () {
   
	  window.commonCPY.datePicker(['LaycanS','LaycanD'],function(value, date, endDate){
	        var value=value[0].split(' ').join('/')
	        console.log(value)
	    });
	    commonCPY.select()
	$(".uploadDoc").click(function(){

		var orderId = parseFloat($(this).find("span").text());
        if(orderId == null || orderId.length==0){
            console.error("orderId is empty");
            return;
        }
		$.ajax({
            url:shippingServer + "/pages/back/agreement/agreement/orderuuid.json?orderId=" + orderId,
            type:"POST",
            dataType: "json",    //数据类型
			data:null,
            success: function(data){

				var uuid = data.datas;
				if(uuid != null && uuid.length>0){
                    var url = transactionServer+ "pages/back/billCoreUpload/index.htm?uuid=" +uuid;
                    window.location.href = url;
				}else{
					console.log("uuid is empty");
				}
            },
            error: function (e) {

            }
		})
	})

    //航程结束ajax
   /* $(".voyage_end").click(function (){
    	var uuid=$(this).attr("conuuid");
    	var status=$(this).attr("constatus");
    	
    	var params = {};
    	
    	params.uuid = uuid;
		params.status = status;
    	
    	$.ajax({
 		   	contentType : 'application/json',  
	        type:"post",
	        dataType:"json",
	        traditional:true,
	        url:shippingServer+'/pages/back/vesselAcceptance/updateConfiram.json' ,
	        data: JSON.stringify(params),
			success : function(msg) {
				if (msg != null) {
					alert(msg.message)
					location.href=location;
				}
			},
			error:function(){
			}
     	})
    	
    })*/
    /*$(".conf_del").click(function(){
    	 var msg = "您真的确定要删除吗？\n\n请确认！"; 
    	 if (confirm(msg)==true){ 
    	  return true; 
    	 }else{ 
    	  return false; 
    	 } 
    	
    	var uuid=$(this).attr("conuuid");
    	$.ajax({
 		   	contentType : 'application/json',  
	        type:"post",
	        dataType:"json",
	        traditional:true,
	        url:shippingServer+'/pages/back/vesselAcceptance/deleteLogic.json' ,
	        data: uuid,
			success : function(msg) {
				if (msg != null) {
					alert(msg.message)
					location.href=location;
				}

			},
			error:function(){
			}
     	})
    	
    })*/
    
    //卖家确认
   /* $("#confirmcli").click(function(){
    	var uuid=$(this).attr("conuuid");
    	var status=$(this).attr("constatus");
    	
    	var params = {};
    	
    	params.uuid = uuid;
		params.status = status;
    	
    	$.ajax({
 		   	contentType : 'application/json',  
	        type:"post",
	        dataType:"json",
	        traditional:true,
	        url:shippingServer+'/pages/back/vesselAcceptance/conconfirmcli.json' ,
	        data: JSON.stringify(params),
			success : function(msg) {
				if (msg != null) {
					alert(msg.message)
					location.href=location;
				}

			},
			error:function(){
			}
     	})
    })*/
    
});