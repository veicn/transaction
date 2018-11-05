layui.use(['layer', 'form', 'jquery','laypage', 'table'], function(){
  var layer = layui.layer,
  form = layui.form,  // 载入form表单
  table = layui.table;
  window.$ = layui.jquery;    //zai'lu'
  
//页面记录的uuid
  var uuid = "";
  
  // 查询函数
  $('.searchBtn').on('click',function(){
	  search();
   }); 
	  
  function search(){
	  var key = $('#roleName').val();
	  window.location.href=infoServer+"/om/rule/rule.htm?roleName="+ key;
  }
  
  //新增页面
  $('.addFn').on('click',function(){
    // 请求弹框
	  $.ajax({
		  type:"get",
		  url:"../../info/bomb/rule/ruleAdd.html",
		  success:function(data) {
			//弹框运行
		 	layer.open({
				  type: 1,
				  area: ['700px','450px'],
				  btn: ['保存', '取消'],
				  btnAlign: 'c' ,//按钮居中
				  title:'新增规则',
				  content: data,
				  yes: function(index, layero){
					  
					  //触发提交按钮，校验必填项
					  $('#addSubmit').trigger("click");
					  
					  var params = {};
					  params.uuid = uuid;
					  params.roleConstant = $("#role").val();
					  params.channelUuid = $("#channel").val();
					  params.isRelease = $("#fabu1").is(":checked")?"1":"0";
					  params.isLimitCount = $("#limit").is(":checked")?"0":"1";
					  params.releaseCount = $("#count").val();
					  params.auditType = $("input[name='shenhe']:checked").val();
					  
					  //TODO
					  params.createUser = "admin";
					  params.updateUser = "admin";
					  
					$.ajax({
						type: "POST",
						url: infoServer+"/rule/ruleSave.json",
						data: params,
						dataType: "json",
						async: false,
						success: function(data) {  
							if(data.status != 0){
								return false;
							}
						},
						error: function(data) {
			                layer.alert("error:" + data.message);
			            }
					});
					layer.close(index);// 关闭当前弹框
					window.location.href = infoServer+"/om/rule/rule.htm";
				  },
				  btn2: function(index, layero){
			    	alert('取消')
			  	}
		 	});
		 	
		 	//加载角色
			 $.ajax({
				 url:infoServer+'/rule/getRoles.json',
				 type:'get',
				 success:function(data){
					 $.each(data, function(index,value){
						 $('#role').append('<option value="' + value.constant + '">' + value.name+'</option>');
				   });
					 form.render('select');
				 }
			 });
			 	
			//加载频道
			 $.ajax({
				 url:infoServer+'/rule/getChannels.json',
				 type:'get',
				 success:function(data){
					 $.each(data,function(index,value){
						 $('#channel').append('<option value="' + value.uuid + '">' + value.channelName+'</option>');
					});
					form.render('select');
				 }
			 });
        form.render();
      }
    })
  });
  
  // 编辑页面
  $('.editFn').on('click',function(){
	  var objRD = JSON.parse($(this).attr("attr-data"));
	  uuid = objRD.uuid;
	  
    // 请求弹框
	  $.ajax({
		  type: "get",
		  url: "../../info/bomb/rule/ruleEdit.html",
		  success: function(data) {
			//弹框运行
		 	layer.open({
				  type: 1,
				  area: ['700px','450px'],
				  btn: ['保存', '取消'],
				  btnAlign: 'c' ,//按钮居中
				  title:'修改规则',
				  content: data,
				  yes: function(index, layero){
					  
					//触发提交按钮，校验必填项
					  $('#editSubmit').trigger("click");
					  
					  var params = {};
					  params.uuid = uuid;
					  params.roleConstant = $("#role").val();
					  params.channelUuid = $("#channel").val();
					  params.isRelease = $("#fabu1").is(":checked")?"1":"0";
					  params.isLimitCount = $("#limit").is(":checked")?"0":"1";
					  params.releaseCount = $("#count").val();
					  params.auditType = $("input[name='shenhe']:checked").val();
					  
					  //TODO
					  params.createUser = "admin";
					  params.updateUser = "admin";
					  
					$.ajax({
						type: "POST",
						url: infoServer+"/rule/ruleSave.json",
						data: params,
						dataType: "json",
						async: false,
						success: function(data) {  
							if(data.status != 0){
								return false;
							}
						},
						error: function(data) {
			                alert("error:" + data.message);
			            }
					});
					layer.close(index);// 关闭当前弹框
				    window.location.href = infoServer+"/om/rule/rule.htm";
				  },
				  btn2: function(index, layero){
			    	alert('取消')
			  	}
        });
		 	
		//加载角色
		 $.ajax({
			 url:infoServer+'/rule/getRoles.json',
			 type:'get',
			 success:function(data){
				 $.each(data,function(index, value){
					 if(objRD.roleName == value.name){
						 $('#role').append('<option value="' + value.constant + '" selected>' + value.name + '</option>');
					 }else{
						 $('#role').append('<option value="' + value.constant + '">' + value.name + '</option>');
					 }
				 });
				 form.render('select');
			 }
		 });
		 	
		//加载频道
		 $.ajax({
			 url:infoServer+'/rule/getChannels.json',
			 type:'get',
			 success:function(data){
				 $.each(data,function(index,value){
					 if(objRD.channelName == value.channelName){
						 $('#channel').append('<option value="'+value.uuid+'" selected >'+value.channelName+'</option>');
					 }else{
						 $('#channel').append('<option value="'+value.uuid+'" >'+value.channelName+'</option>');
					 }
				 });
				 form.render('select');
			 }
		 });
		 
		//是否发布
	 	var fabus = $('input[name="fabu"]');
	 	for(var i = 0 ; i < fabus.length; i ++){
	 		if(objRD.isRelease == fabus[i].value){
	 			fabus[i].checked = true;
				break;
			 }
	 	}
		 
	 	//是否限制当日发布次数
	 	if(objRD.isRelease == 1 && objRD.isLimitCount == 0){ //发布次数没有限制
	 		$("#limit").attr("checked",'true');
	 	}
	 	
		//当日可发布次数
		if(objRD.isRelease == 1 && objRD.isLimitCount == 1 && objRD.releaseCount > 0 ){ //发布次数有限制
			$("#count").val(objRD.releaseCount);
		}
		
		//审核类型
	 	var shenhes = $('input[name="shenhe"]');
	 	for(var i = 0 ; i < shenhes.length; i ++){
	 		if(objRD.auditType == shenhes[i].value){
	 			shenhes[i].checked = true;
				break;
			 }
	 	}
        form.render();
      }
    })
    
  });
  
  // 删除函数
  $('.del').on('click',function(){
	uuid = $(this).attr("attr-data");
    //弹出对话框
    layer.confirm('确认删除吗', function(index){
    	layer.close(index);
    	var params = {};
    	params.uuid = uuid;
    	$.ajax({
    		type: "POST",
    		url: infoServer+"/rule/ruleDel.json",
    		data: params,
    		async: false,
    		success: function(data) {
				if(data.status == 0){
					alert(data.message);
				}
			},
			error: function(data) {
	            alert("error:" + data.message);
	        }
    	});
      location.reload();
    });
  });
});
