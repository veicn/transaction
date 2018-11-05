layui.use(['layer', 'form', 'jquery','laypage', 'table'], function(){
  var layer = layui.layer,
  form = layui.form,  // 载入form表单
  table = layui.table;
  window.$ = layui.jquery;    //zai'lu'

  //页面记录的uuid
  var uuid = "";
  
  //关闭
  $('.closeBtn').on('click',function(){
		window.close();
	});
  
  //审核通过
  $('.auditYesBtn').on('click',function(){
	  var params = {};
	  params.uuid = $(this).attr("attr-data");
	  $.ajax({
		  type: "POST",
		  url: infoServer+"/audit/auditYes.json",
		  data: params,
		  dataType: "json",
		  success: function(data){
			  if(data.status == 0) {
				  layer.alert("已通过审核");
			  }else{
				  layer.alert("错误");
			  }
			  window.location.href=infoServer+"/om/audit/audit.htm";
		  },
		  error: function(data){
			  layer.alert("error");
		  }
	  });
  });
	  
  //审核驳回
  $('.auditNoBtn').on('click',function(){
	  var param = $(this).attr("attr-data");
	  var objParam = JSON.parse(param);
	  
	  $.ajax({
		  type: "POST",
		  url: "../../info/bomb/audit/rejection.html",
		  success: function(data){
			  layer.open({
				  type: 1,
				  area:['600px','300px'],
				  btn: ['确定', '取消'],
				  btnAlign: 'c',
				  title:'驳回理由',
				  content: data,
				  yes: function(index, layero){
					  
					  //触发提交按钮，校验必填项 
					  $('#rejectionSubmit').trigger("click");
					  
					  objParam['resufeRemark'] = $("#content").val(); //驳回理由
					  $.ajax({
						  type: "POST",
						  url: infoServer+"/audit/auditNo.json",
						  data: JSON.stringify(objParam),
						  dataType: "json",
						  headers: {
							  'Content-Type':'application/json;charset=utf-8'
						  },
						  success: function(data){
							  if(data.status == 0){
								  layer.alert(data.message);
								  layer.close(index);
							  }else{
								  layer.alert(data.message);
							  }
							  window.location.href=infoServer+"/om/audit/audit.htm";
						  },
						  error: function(data){
							  layer.alert("error");
						  }
					  });
				  },
				  btn2: function(index, layero){
					  layer.alert('取消');
				  }
			  });
		  },
		  error:function(data){
			  layer.alert("error");
		  }
	  });
   }); 
  
});
