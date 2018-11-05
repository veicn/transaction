layui.use(['form', 'layer', 'table', 'element', 'jquery'], function(){
  var form = layui.form //表单
  ,layer = layui.layer //弹层
  ,table = layui.table //表格
  ,element = layui.element; //元素操作
  window.$ = layui.jquery; // zai'lu'
  
  //根据主频道信息获取子频道列表
  form.on('select(channelM)', function(data){
       if(data.value==""){
    	   $('#channel').empty(); 
    	   form.render('select');
    	   return;
       }
       
	$.ajax({
		url: infoServer+"/myInfoList/getChannels.json",
		type: "GET",
		data: {
			channelMUuid : $('#channelM').val()
		},
			success:function(data){
				if(!data){
					$("#channel").empty();
				} else {
					var optionstring= "";
					$.each(data, function(index , value){
						optionstring += "<option value=\"" + value.uuid + "\" >" + value.name + "</option>";
					});
					$("#channel").html('<option value=""></option>' + optionstring);
				}
				form.render('select');
			}
	});
	
  }) 
  
  //监听Tab切换
  element.on('tab(infoTab)', function(data){
	  if(data.index == 1){ //已发布选项页
		  window.location.href=infoServer+"/om/myInfoList/myInfoListReleased.htm";
	  } else if(data.index == 2){ //未发布选项页
		  window.location.href=infoServer+"/om/myInfoList/myInfoListUnreleased.htm";
	  } else if(data.index == 3){ //草稿选项页
		  window.location.href=infoServer+"/om/myInfoList/myInfoListDraft.htm";
	  }
  });
  
  //查询
  $('.searchFn').on('click', function(){
	  var params = {};
	  var channelM = $('#channelM').val(); //主频道uuid
	  var channel = $('#channel').val(); //子频道uuid
	  var status = $('#status').val(); //资讯状态
	  
	  window.location.href = infoServer+"/om/audit/audit.htm"
		    + "?channelM=" + channelM
			+ "&channel=" + channel
			+ "&status=" + status;
  });
  
  //新增
  $('.addFn').on('click', function(){
	 window.location.href=infoServer+"/om/info/info.htm"; 
	 return false;
  });
  
  //点击标题
  $('.titleLink').on('click', function(){
	  var key = $(this).attr("attr-data");
	  window.location.href=infoServer+"/om/myInfoList/myInfoDetail.htm?uuid=" + key; 
  });
  
  //编辑
  $('.editLink').on('click', function(){
	  var key = $(this).attr("attr-data");
	  window.location.href=infoServer+"/om/info/edit.htm?uuid=" + key; 
  });
  
  //详情
  $('.detailLink').on('click', function(){
	  var key = $(this).attr("attr-data");
	  window.location.href=infoServer+"/om/myInfoList/myInfoDetail.htm?uuid=" + key; 
  });
  
  //撤销
  $('.revokeLink').on('click', function(){
	 var key = $(this).attr("attr-data");
	//弹出确认对话框
	layer.confirm('确认撤销', {icon: 3, title:'提示'},function(){
		$.ajax({
			type: "POST",
			url: infoServer+"/myInfoList/revokeInfo.json",
			data:{
				uuid: key
			},
			dataType: "json",
			success: function(data){
				layer.alert(data.message);
				window.location.href=infoServer+"/om/myInfoList/myInfoList.htm";
			},
			error: function(data){
				layer.alert("error");
			}
		});
	});
  });
  
  //删除
  $('.delLink').on('click', function(){
	var key = $(this).attr("attr-data");
  	//弹出确认对话框
	layer.confirm('确认删除', {icon: 3, title:'提示'},function(){
		$.ajax({
			type: "POST",
			url: infoServer+"/myInfoList/delInfo.json",
			data:{
				uuid: key
			},
			dataType: "json",
			success: function(data){
				layer.alert(data.message);
				window.location.href=infoServer+"/om/myInfoList/myInfoList.htm";
			},
			error: function(data){
				layer.alert("error");
			}
		});
	});
  });
  
});