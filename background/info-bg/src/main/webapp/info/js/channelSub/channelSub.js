layui.use(['layer', 'form','jquery','laypage'], function(){
	  var layer = layui.layer
	  ,form = layui.form,  // 载入form表单
	  table = layui.table; //表格
	  window.$ = layui.jquery;    //zai'lu'
	  
	    //查询
	    $('.search').on('click',function(){
	    	var channelName = $('#channelNameKey').val();
	    	var channelMName = $('#channelMName').val();
	    	var channelDesc = $('#channelDesc').val();
	    	window.location.href=infoServer+"/om/channelSub/channelSub.htm?channelName="+channelName+"&channelDesc="+channelDesc+"&channelMName="+channelMName;
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
			var url = '../../info/bomb/channelSub/add.html';
			
			$.ajax({
			 	type:"get",
			 	url:url,
			 	success:function(data){

			 		//弹框运行
			 		layer.open({
					  type: 1,
					  area: ['400px', '430px'],
					  btn: ['保存', '取消'],
					  title:'新增子频道',
					  content: data,
					  yes: function(index, layero){
						  var data = $('.layui-form').serializeObject();
						  console.log(data);
						  
						  //验证
						  if(!validator(data)){
							  return;
						  }
						  
						  
						  $.ajax({
								url:infoServer+'/channelSub/saveOrUpdateChannelSub.json',
								type:'post',
								data:JSON.stringify(data),
								success:function(res){
									/*if(res.status == 0){
										
									}*/
									window.location.reload();
									layer.close(index);//关闭当前弹框 
								},
								headers : {  
				                    'Content-Type' : 'application/json;charset=utf-8'  
				                }
								
							});
							
					  },
					  btn2: function(index, layero){
						  layer.close(index);// 关闭当前弹框
				  	}
					});	

					//加载主频道信息
					$.ajax({
						url:infoServer+'/channelM/selectListChannelM.json',
						type:'POST',
						async: false,
						success:function(res){
							if(res){
								var list = res;
								for(var i = 0; i < list.length;  i++){
									//$('#channelMId').append('<dd lay-value="'+list[i].id+'" class="layui-this">'+list[i].channelMName+'</dd>');
									$('#channelMUuid').append('<option value="'+list[i].uuid+'">'+list[i].name+'</option>');
								}
							}
						}
					});
					
					form.render();
			 	}
			
			 });
		})
	  
		//编辑函数
		$('.editFn').on('click',function(){
			// 请求弹框
			var url = '../../info/bomb/channelSub/edit.html';
			var strData= $(this).attr('attr-data');
			console.log(strData);
			var objData = JSON.parse(strData);
			//初始化编辑框
			$.ajax({
			 	type:"get",
			 	url:url,
			 	success:function(data){
			 		//弹框运行
			 		layer.open({
					  type: 1,
					  area: ['400px', '430px'],
					  btn: ['保存', '取消'],
					  title:'修改频道',
					  content: data,
					  yes: function(index, layero){
					  
				      //表单数据
					  var data = $('.layui-form').serializeObject();
					  //校验
					  if(!validator(data)){
						  return;
					  }
						
					  $.ajax({
							url:infoServer+'/channelSub/saveOrUpdateChannelSub.json',
							type:'post',
							data:JSON.stringify(data),
							success:function(res){
								/*if(res.status == 0){
									//刷新当前页
									window.location.reload();
									layer.alert(res.message);
									layer.close(index);// 关闭当前弹框
								}else if(res.status == 9999){
									window.location.reload();
									layer.alert(res.message);
									layer.close(index);// 关闭当前弹框
								}*/
								window.location.reload();
								layer.close(index);// 关闭当前弹框
							},
							headers : {  
			                    'Content-Type' : 'application/json;charset=utf-8'  
			                }  
						});
						
					  },
					  btn2: function(index, layero){
						layer.close(index);// 关闭当前弹框
				  	  }
					});
			 		
			 		//加载主频道信息
					$.ajax({
						url:infoServer+'/channelM/selectListChannelM.json',
						type:'POST',
						async: false,
						success:function(res){
							if(res){
								var list = res;
								//console.log(list);
								console.log(objData.channelMUuid);
								for(var i = 0; i < list.length;  i++){
									if(list[i].uuid == objData.channelMUuid){
										$('#channelMUuid').append('<option selected value="'+list[i].uuid+'">'+list[i].name+'</option>');
									}else{
										$('#channelMUuid').append('<option value="'+list[i].uuid+'">'+list[i].name+'</option>');
									}
								}
							}
						}
					});
					
//					// 下拉框赋值（是否公开）
					if(objData.extend1 == '0') {
						$('#extend1').append('<input type="radio" id="extend1" name="extend1" value="1" title="是">');
						$('#extend1').append('<input type="radio" id="extend1" name="extend1" value="0" title="否" checked>');
					} else if (objData.extend1 == '1') {
						$('#extend1').append('<input type="radio" id="extend1" name="extend1" value="1" title="是" checked>');
						$('#extend1').append('<input type="radio" id="extend1" name="extend1" value="0" title="否">');
					} else {
						$('#extend1').append('<input type="radio" id="extend1" name="extend1" value="1" title="是">');
						$('#extend1').append('<input type="radio" id="extend1" name="extend1" value="0" title="否" checked>');
					}
					
					$("#uuid").val(objData.uuid);
			 		$("#channelCode").val(objData.channelCode);
			 		$("#channelName").val(objData.channelName);
			 		$("#channelOrder").val(objData.channelOrder);
					
			 		// 渲染
			 		form.render();
			 	}
			 });
		})
		
		//删除函数
		$('.delFn').on('click',function(){
			var objData = JSON.parse($(this).attr('attr-data'));
			layer.confirm('是否删除？', {icon: 3, title:'请确认'}, function(index){
				$.ajax({
					url:infoServer+'/channelSub/deleteChannelSub.json?uuid='+objData.uuid,
					type:'get',
					success:function(data){
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
		
	  //将form表单序列化之后,在转为json对象
	  $.fn.serializeObject = function(){
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
			
			
		
	//校验
	function validator(data){
		if(data){
			  if(!data.channelMUuid || data.channelMUuid == ""){
				  layer.alert('请选择主频道');
				  return false;
			  }
			  if(!data.channelName){
				  layer.alert('子频道名称不为空');
				  return false;
			  }
			  if(!data.channelCode){
				  layer.alert('子频道代码不为空');
				  return false;
			  }
		  }
		return true;
	}
		//校驗
		/*function it(data,validates){
			if(typeof(data) != 'object' || data == 'undefined'){
				layer.alert('data is error');
				return;	
			}
			if(typeof(validates) != 'object' || validates == 'undefined'){
				layer.alert('validates is error');
				return;	
			}
			var dataIsJson = typeof(data) == "object" && Object.prototype.toString.call(data).toLowerCase() == "[object object]" && !data.length
			var validatesIsJson = typeof(validates) == "object" && Object.prototype.toString.call(validates).toLowerCase() == "[object object]" && !validates.length

			if(dataIsJson && validatesIsJson){
				layer.alert('data and validates must be json object');
				return;	
			}

			if(data.length > validates.length){
				layer.alert('data.length >=  validates.length');
				return;
			}
			
			for(var item in data){
				if(data[item] == null || data[item] == '' || data[item] =="" || data[item] == 'undefined'){
					for(var v in validates){
						var objJSON = JSON.parse(validates[v]);
						for(var i in objJSON){
							if(i == item){
								layer.alert(objJSON[i]);
								return;
							}
						}
					}
				}
			}
		}*/
		
	}); 

