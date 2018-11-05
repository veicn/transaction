layui.use(['layer', 'form','jquery','laypage'], function(){
	  var layer = layui.layer
	  ,form = layui.form,  // 载入form表单
	  table = layui.table; //表格
	  window.$ = layui.jquery;    //zai'lu'

	  
	  //查询
	  $('.search').on('click',function(){
		  var channelMName = $('#channelMNameKey').val();
		  var channelMDesc = $('#channelMDesc').val();
		  window.location.href=infoServer+"/om/channelM/channelM.htm?channelMName="+channelMName+'&channelMDesc='+channelMDesc;
	  });
	  
	  
	//回车查询
  	$("body").keydown(function() {
	    if (event.keyCode == "13") {//keyCode=13是回车键
	        $('.search').click();
	    }
	});    
	  
	  	//新增函数
		$('.addFn').on('click',function(){
			// 请求弹框
			var url = '../../info/bomb/channelM/add.html';
			
			$.ajax({
			 	type:"get",
			 	url:url,
			 	success:function(data){
			 		//弹框运行
			 		layer.open({
					  type: 1,
					  id:'oneId',
					  area: ['370px','350px'],
					  btn: ['保存', '取消'],
					  title:'新增频道',
					  content: data,
					  yes: function(index, layero){
						  
						  //表单数据
						  var data = $('.layui-form').serializeObject();
						  data['channelMDesc'] = data.channelMDesc[1];
						  //校验
						  if(!validator(data)){
							  return;
						  }
						  
							$.ajax({
								url:infoServer+'/channelM/saveOrUpdateChannelM.json',
								type:'post',
								data:JSON.stringify(data),
								success:function(res){
									if(res.status == 0){
										window.location.reload();
										layer.close(index);// 关闭当前弹框
									}else if(res.status == 9999){
										layer.alert(res.message);
									}
									
								},
								headers : {  
				                    'Content-Type' : 'application/json;charset=utf-8'  
				                }  
							});
						  
					  },
					  btn2: function(index, layero){
						  layer.close(index);
				  	}
					});
			 		
			 		//主频道类型
			 		$.ajax({
						url:infoServer+'/values/codeList.json',
						type:'post',
						data:JSON.stringify({setCode:'CHANNEL_TYPE'}),
						dataType:"JSON",
						async: false, 
						headers : {  
		                    'Content-Type' : 'application/json;charset=utf-8'  
		                },
						success:function(res){
					 		// 下拉框赋值
					 		for(var i = 0; i < res.datas.length;  i++){
					 			$('#channelMDesc1').append('<option value="'+res.datas[i].itemCode+'">'+res.datas[i].itemName+'</option>');
							}
					 		
						}
					});	
					form.render();
			 	}
			 });
		})
	  
	  //将form表单序列化之后,在转为json对象
	  $.fn.serializeObject = function()
		{
			var o = {};
			var a = this.serializeArray();
			$.each(a, function() {
				if (o[this.name] !== undefined) {
					if (!o[this.name].push) {
						o[this.name] = [o[this.name]];
					}
					o[this.name].push(this.value || '');
				} else {
					o[this.name] = this.value || '';
				}
			});
			return o;
		};
		
		
		//编辑函数
		$('.editFn').on('click',function(){
			// 请求弹框
			var url = '../../info/bomb/channelM/edit.html';
			var strData= $(this).attr('attr-data');
			var objData = JSON.parse(strData);
			//初始化编辑框
			$.ajax({
			 	type:"get",
			 	url:url,
			 	success:function(data){
			 		//弹框运行
			 		layer.open({
					  type: 1,
					  area: ['370px', '350px'],
					  btn: ['保存', '取消'],
					  title:'修改频道',
					  content: data,
					  yes: function(index, layero){
				      //表单数据
					  var data = $('.layui-form').serializeObject();
					  data['channelMDesc'] = data.channelMDesc[1];
					  //校验
					  if(!validator(data)){
						  return;
					  }
						
					  $.ajax({
							url:infoServer+'/channelM/saveOrUpdateChannelM.json',
							type:'post',
							data:JSON.stringify(data),
							success:function(res){
								if(res.status == 0){
									//刷新当前页
									window.location.reload();
								}
							},
							headers : {  
			                    'Content-Type' : 'application/json;charset=utf-8'  
			                }  
						});
						
					    layer.close(index);// 关闭当前弹框
					  },
					  btn2: function(index, layero){
						layer.close(index);// 关闭当前弹框
				  	  }
					});
			 		
			 		//主频道类型
			 		 $.ajax({
							url:infoServer+'/values/codeList.json',
							type:'post',
							data:JSON.stringify({setCode:'CHANNEL_TYPE'}),
							dataType:"JSON",
							async: false, 
							headers : {  
			                    'Content-Type' : 'application/json;charset=utf-8'  
			                },
							success:function(res){
						 		// 下拉框赋值
						 		for(var i = 0; i < res.datas.length;  i++){
						 			if(objData.channelMDesc == res.datas[i].itemCode){
						 				$('#channelMDesc2').append('<option selected value="'+res.datas[i].itemCode+'">'+res.datas[i].itemName+'</option>');
						 			}else{
						 				$('#channelMDesc2').append('<option value="'+res.datas[i].itemCode+'">'+res.datas[i].itemName+'</option>');
						 			}
								}
						 		
							}
						});
			 		$("#uuid").val(objData.uuid);
			 		$("#channelMCode").val(objData.channelMCode);
			 		$("#channelMName").val(objData.channelMName);
			 		$("#channelMOrder").val(objData.channelMOrder);
			 		
			 		form.render();
			 	}
			 });
		})
		
		//删除函数
		$('.delFn').on('click',function(){
			var objData = JSON.parse($(this).attr('attr-data'));
			layer.confirm('是否删除？', {icon: 3, title:'请确认'}, function(index){
				$.ajax({
					url:infoServer+'/channelM/deleteChannelM.json?uuid='+objData.uuid,
					type:'get',
					success:function(data){
						console.log(JSON.stringify(data))
						if(data.status == 0){
							layer.alert(data.message,function(){
								window.location.reload();
							});
						}
					}
				});
				layer.close(index);
			}); 
			
		})
		
		//校验
		function validator(data){
			  if(data){
				  if(!data.channelMName){
					  layer.alert('频道名称不为空');
					  return false;
				  }
				  if(!data.channelMCode){
					  layer.alert('频道代码不为空');
					  return false;
				  }
				  if(!data.channelMDesc){
					  layer.alert('主频道类型不为空');
					  return false;
				  }
			  }
			  return true;
		}
		
	}); 

