layui.use(['layer', 'form','jquery','laypage','element','laydate','layedit', 'table'], function(){
    var layer = layui.layer
    ,form = layui.form,  // 载入form表单
    table = layui.table, //表格
    element = layui.element, // tap
    laydate = layui.laydate, // 日期
    laypage = layui.laypage;   // 载入page分页
    window.$ = layui.jquery;    //zai'lu'

	
	
	// 查询
	$('.searchBtn').on('click', function(){
		var menuDes = $("#menuDesQuery").val();
		var menuUrl = $("#menuUrlQuery").val();
		var menuType = $("#menuTypeQuery").val();
		window.location.href=infoServer+"/om/tpMenu/tpMenu.htm?menuDes="+menuDes+"&menuType="+menuType+"&menuUrl="+menuUrl;
	})
	
    // 新增函数
    $('.addFn').on('click',function(){
		// 请求弹框
		var url = '../../info/bomb/tpMenu/tpMenu.html';
		$.ajax({
		 	type:"get",
		 	url:url,
		 	success:function(data){
		 		
		 		//弹框运行
		 		var bobm=layer.open({
		 			id:'bobm',
				  type: 1,
				  area: ['800px', '350px'],
				  title:'新增菜单',
				  content: data,
				  success: function(index, layero){
					  
					  form.on('submit(btn_edit)', function(data){

						  var data = $('#editForm').serializeObject();
							$.ajax({
								url:infoServer+'/tpMenu/saveOrUpdateMenu.json',
								type:'post',
								data:JSON.stringify(data),
								success:function(res){
									layer.alert(res.message,function(){
										window.location.reload();
									});
								},
								headers : {  
				                    'Content-Type' : 'application/json;charset=utf-8'  
				                }  
							});
					  return false;
					  });
					  
					  $('#btn_close').on('click',function(){
						 layer.close(bobm);// 关闭当前弹框
					  });
				  	},
				});
		 		// 手动渲染
				form.render();
		 	}
		 });
	})


    // 编辑函数
    $('.editFn').on('click',function(){
		// 请求弹框
		var url = '../../info/bomb/tpMenu/tpMenu.html';
		var strData= $(this).attr('attr-data');
		var objData = JSON.parse(strData);
		$.ajax({
		 	type:"get",
		 	url:url,
		 	success:function(data){
		 		
		 		//打开弹框
		 		var bobm =layer.open({
		 			  type: 1,
					  area: ['800px', '350px'],
					  title:'编辑报价',
					  content: data,
					   success: function(index, layero){
				 		// 编辑弹框初始值
				 		$('#menuId').val(objData.menuId);
				 		$('#sort').val(objData.sort);
				 		$('#menuType').val(objData.menuType);
				 		$('#menuDes').val(objData.menuDes);
				 		$('#menuIcon').val(objData.menuIcon);
				 		$('#menuUrl').val(objData.menuUrl);
				 		$('#menuArgu').val(objData.menuArgu);
				 		$('#langVer').val(objData.langVer);
				 		$('#tradeId').val(objData.tradeId);
				 		$('#tradeParentId').val(objData.tradeParentId);
				 		if(objData.tradeLevel == 1){
				 			$('#tradeLevel').attr("checked",true);
				 		}else{
				 			$('#tradeLevel').removeAttr("checked");
				 			
				 		}
				 		$('#isPersonal').val(objData.isPersonal);
				 		$('#marketId').val(objData.marketId);
							 		
						  
						  // 监听
						  form.on('submit(btn_edit)', function(data){
							  
							//表单数据
							  var data = $('#editForm').serializeObject();
								$.ajax({
									url:infoServer+'/tpMenu/saveOrUpdateMenu.json',
									type:'post',
									data:JSON.stringify(data),
									success:function(res){
										layer.alert(res.message,function(){
											window.location.reload();
										});
									},
									headers : {  
					                    'Content-Type' : 'application/json;charset=utf-8'  
					                }  
								});
						  return false;
						  }); 
						  
						  $('#btn_close').on('click',function(){
								 layer.close(bobm);// 关闭当前弹框
							  });
					  	}
					});
		 		
	 			form.render();

		 	}
		 });
	})
    
	//删除函数
	$('.delFn').on('click',function(){
		var objData = JSON.parse($(this).attr('attr-data'));
		console.log(objData);
		layer.confirm('是否删除？', {icon: 3, title:'请确认'}, function(index){
			$.ajax({
				url:infoServer+'/tpMenu/tpMenuDelete.json?menuId='+objData.menuId,
				type:'get',
				success:function(data){
					console.log(JSON.stringify(data))
					if(data.status == 0){
						window.location.reload();
					}
				}
			});
			layer.close(index);
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
		
  }); 