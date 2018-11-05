
var saveDemandsFlag = true;

layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;	
	$('#insertBtn').on('click',function(){
        var arr=$('#demandsAddForm');
        commonCPX.FormValidation(arr);
        if(!commonCPX.FormValidation(arr) && saveDemandsFlag){
            saveDemandsFlag = false;
            //if(emptyCheck())
                $.ajax({
                    type : "POST",
                    url : shippingServer + '/pages/back/demands/save.json',
                    data : $("#demandsAddForm").serialize(),// 序列化表单提交input值
                    success : function(msg) {
                        saveDemandsFlag = true;
                        layer.alert(msg.message,{title:'Info', btn:['OK']},function(){
                            window.location.href = shippingServer + "/pages/back/demands/demandsList.htm";
                        });
                    },
                    error : function(msg) {
                        saveDemandsFlag = true;
                        layer.alert("error:" + msg.message, {title:'Error', btn:['OK']});
                    }
                });
		}else{
            saveDemandsFlag = true;
            layer.alert("Fill in the wrong,please try again", {title:'Error', btn:['OK']});

		}
	})

    $(function () {
        commonCPY.select()
    })
    
    $("input:radio[name='builtDate']").change(function (){ 
    	if($(this).val() !=='5'){
    		$("input[name='remark']").attr("disabled","disabled")
    	}else{
    		$("input[name='remark']").attr("disabled",false)
    	}
    	
    });
	
	function emptyCheck() {
		/*var vnDivVal = $("#vesselNameDiv").text();*/
		var vesselNameVal = $('input[name="charteringAgentId"]').val();
		if(vesselNameVal==null || vesselNameVal == ''|| vesselNameVal == undefined){
			layer.alert("The Chartering Agency content cann't be empty",{title:'Info', btn:['OK']});
			return false;
		}
		var corporationNm = $('input[name="corporationNm"]').val();
		if(corporationNm==null || corporationNm == ''|| corporationNm == undefined){
			layer.alert("The Corporation content cann't be empty",{title:'Info', btn:['OK']});
			return false;
		}
		var linkNm = $('input[name="linkNm"]').val();
		if(linkNm==null || linkNm == ''|| linkNm == undefined){
			layer.alert("The Linkman content cann't be empty",{title:'Info', btn:['OK']});
			return false;
		}
		var email = $('input[name="eMial"]').val();
		if(email==null || email == ''|| email == undefined){
			layer.alert("The E-mal content cann't be empty",{title:'Info', btn:['OK']});
			return false;
		}
		var phoneNum = $('input[name="phoneNum"]').val();
		if(phoneNum==null || phoneNum == ''|| phoneNum == undefined){
			layer.alert("The Phone Number content cann't be empty",{title:'Info', btn:['OK']});
			return false;
		}
		return true;
	}

});


