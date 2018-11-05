layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer;
	var laydate = layui.laydate;	

	//getPortList();
	
	function getPortList(){
		$.ajax({
      		url:orderServer+'/ship/queryUnloadingType.json',
      		async:true,
      		success:function(data){
      			for(var key in data.datas){
					$("#query_port").append("<option value='"+ data.datas[key].code +"'>"+ data.datas[key].value +"</option>"); 
				}
      		},
      		error:function(){
      			layer.alert("error");
      		}
      	})
	}
	
	//新增
	$('.saveFn').on('click',function(){
		$.ajax({
			url:orderServer+'/bomp/inspection/addStaff.htm',
			success:function(data1){
				$.ajax({
					url:orderServer+'/ship/queryUnloadingType.json',
					async:true,
					success:function(data){
						//弹框 $.i18n("orderexecute.code.00196") 
						layer.open({
							type:1,
							id:'addWin',
							title:$.i18n("orderexecute.code.002008"),
							area: ['740px', '230px'],
							content: data1,
							btn: [$.i18n("orderexecute.code.00153"), $.i18n("orderexecute.code.00198")],
							btnAlign: 'c',
							closeBtn: 0,
							yes: function(){
								saveStaff_yes_callback();
							}
						});	
						
						for(var key in data.datas){
							$("#port").append("<option value='"+ data.datas[key].code +"'>"+ data.datas[key].value +"</option>"); 
						}
					},
					error:function(data){
						alert('request error');
					}
				})		
				
			},
			error:function(data1){
				alert('request error');
			}
		})
	});
	
	function saveStaff_yes_callback(){
		if($('#port').val() == "") {
			layer.alert($.i18n("orderexecute.code.002010"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
			return;
		}
		if($('#contactName').val() == "") {
			layer.alert($.i18n("orderexecute.code.002011"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
			return;
		}
		if($('#tel').val() == "") {
			layer.alert($.i18n("orderexecute.code.002012"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
			return;
		}
		if($('#email').val() == "") {
			layer.alert($.i18n("orderexecute.code.002013"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
			return;
		}
		
		var ff = checkTel($('#tel').val());
		if (!ff){ layer.alert($.i18n("orderexecute.code.00200"));flag =false;return false; }

		var fff = checkEmail($('#email').val());
		if (!fff){ layer.alert($.i18n("orderexecute.code.00201"));flag =false;return false; }
		
		
		$.ajax({
      		url:orderServer+"/inspection/staff/add.json",
      		data:$("#add_staff_form").serialize(),
      		type:"post",
      		success:function(result){
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
	
	
	//修改
	$('.updateFn').on('click',function(){
		var data = $(this).attr("data");
		data = eval("("+data+")");
		$.ajax({
			url:orderServer+'/bomp/inspection/addStaff.htm',
			success:function(data1){
				 $.ajax({
					url:orderServer+'/ship/queryUnloadingType.json',
					async:true,
					success:function(data2){ 
						//弹框 
						layer.open({
							type:1,
							title:$.i18n("orderexecute.code.002009"),
							area: ['740px', '230px'],
							content: data1,
							btn: [$.i18n("orderexecute.code.00153"), $.i18n("orderexecute.code.00198")],
							btnAlign: 'c',
							closeBtn: 0,
							yes: function(){
								editStaff_yes_callback();
							}
						});	
						
						for(var key in data2.datas){
							if(data2.datas[key].code == data.port){
								$("#port").append("<option selected='selected' value='"+ data.port +"'>"+ data2.datas[key].value +"</option>");
								//break;
							}else{
								$("#port").append("<option value='"+ data2.datas[key].code +"'>"+ data2.datas[key].value +"</option>"); 
							}
						}
						//修改回显数据
						//
							$('#contactName').val(data.contactName);
							$('#tel').val(data.tel);
							$('#email').val(data.email);
							$('#id').val(data.id);
						//form.render();
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
	
	function editStaff_yes_callback(){
		if($('#port').val() == "") {
			layer.alert($.i18n("orderexecute.code.002010"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
			return;
		}
		if($('#contactName').val() == "") {
			layer.alert($.i18n("orderexecute.code.002011"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
			return;
		}
		if($('#tel').val() == "") {
			layer.alert($.i18n("orderexecute.code.002012"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
			return;
		}
		if($('#email').val() == "") {
			layer.alert($.i18n("orderexecute.code.002013"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
			return;
		}
		
		var ff = checkTel($('#tel').val());
		if (!ff){ layer.alert($.i18n("orderexecute.code.00200"));flag =false;return false; }

		var fff = checkEmail($('#email').val());
		if (!fff){ layer.alert($.i18n("orderexecute.code.00201"));flag =false;return false; }
		
		$.ajax({
      		url:orderServer+"/inspection/staff/edit.json",
      		data:$("#add_staff_form").serialize(),
      		type:"post",
      		success:function(result){
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
	
	//删除
	$('.delFn').on('click', function(){
		var data = $(this).attr("data");
		data = eval("("+data+")");
		layer.confirm($.i18n("orderexecute.code.00170"), {title:$.i18n("orderexecute.code.00148"),btn:[$.i18n("orderexecute.code.00197"),$.i18n("orderexecute.code.00198")]}, function(index){
			$.ajax({
	      		url:orderServer+"/inspection/staff/del.json",
	      		data:{id:data.id},
	      		type:"post",
	      		success:function(result){
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
		})
	});
	
	$('.queryInsp').on('click',function(){
		$('.queryInspForm').submit();
	  });
	
	//校验电话号码
	function checkTel(value){  
		var flag =true;
	    var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/; 
	    var isMob= /^((0\d{2,3}-\d{7,8})|(1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}))$/;  
	   // var isMob=/^((\+?86)|(\+86))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;  
	    if(isMob.test(value)||isPhone.test(value)){  
	    	flag=true;  
	    } else{  
	    	flag=false;  
	    }  
	    return flag;
	 }  
	//校验邮箱
	function checkEmail(value){  
		var flag =true;
		var isEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/; 	
		if(!isEmail.test(value)){  
			flag=false;  
		}  
		 return flag;
	} 
});	

