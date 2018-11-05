layui.use(['layer', 'form','jquery','laypage','element','laydate','layedit', 'table','upload'], function(){
	var form = layui.form
	  ,layer = layui.layer
	  ,table = layui.table
	  ,layedit = layui.layedit
	  ,laydate = layui.laydate
	  ,element=layui.element
	  ,laypage=layui.laypage
	  ,aydate = layui.laydate
	  ,upload = layui.upload;
    window.$ = layui.jquery;    //zai'lu'

	
	
	// 查询
	$('.searchBtn').on('click', function(){
		var productionNumber = $("#productionNumberQuery").val();
		window.location.href=infoServer+"/om/harbor/oilToHarbor.htm?productionNumber="+productionNumber;
	})
	
    // 新增函数
    $('.addFn').on('click',function(){
		// 请求弹框
		var url = '../../info/bomb/harbor/add.html';
		$.ajax({
		 	type:"get",
		 	url:url,
		 	success:function(data){
		 		
		 		//弹框运行
		 		var bobm=layer.open({
		 			id:'bobm',
				  type: 1,
				  area: ['800px', '400px'],
				  title:'新增菜单',
				  content: data,
				  success: function(index, layero){
					  
					  form.on('submit(btn_edit)', function(data){

						  var data = $('#editForm').serializeObject();
							$.ajax({
								url:infoServer+'/harbor/saveOrUpdate.json',
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
		var url = '../../info/bomb/harbor/add.html';
		var strData= $(this).attr('attr-data');
		var objData = JSON.parse(strData);
		$.ajax({
		 	type:"get",
		 	url:url,
		 	success:function(data){
		 		
		 		//打开弹框
		 		var bobm =layer.open({
		 			  type: 1,
					  area: ['800px', '400px'],
					  title:'编辑报价',
					  content: data,
					   success: function(index, layero){
				 		// 编辑弹框初始值
				 		$('#id').val(objData.id);
				 		$('#productionNumber').val(objData.productionNumber);
				 		$('#quantity').val(objData.quantity);
				 		$('#shipName').val(objData.shipName);
				 		$('#pierOrCompany').val(objData.pierOrCompany);
				 		$('#source').val(objData.source);
				 		$('#receiver').val(objData.receiver);
				 		$('#tradingWay').val(objData.tradingWay);
				 		$('#estimatedArrival').val(objData.estimatedArrival);
						  
						  // 监听
						  form.on('submit(btn_edit)', function(data){
							  
							//表单数据
							  var data = $('#editForm').serializeObject();
								$.ajax({
									url:infoServer+'/harbor/saveOrUpdate.json',
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
				url:infoServer+'/harbor/deleteHarbor.json?id='+objData.id,
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
	$("#download").click(function (){  
	        var path = encodeURI(infoServer+"/info/template/oilToHarbor.xls","UTF-8");
	
	        var form = $("<form>");   //定义一个form表单
	            form.attr('style','display:none');   //在form表单中添加查询参数
	      		form.attr('target','');
	            form.attr('method','post');
	            form.attr('action',path);  
	        
	             $('body').append(form);  //将表单放置在web中
	//              form.append(input1);   //将查询参数控件提交到表单上  
	             form.submit();   //表单提交
	
	    }); 
	var load;
	//Excel文件上传组件
	upload.render({
	    elem: '#importExcel'
	    ,url: infoServer + '/harbor/importExcel.json'
	    ,auto: false
	    ,exts: 'xls|xlsx'
	    ,multiple: true
	    ,bindAction: '#ensure'
  	,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
  		if($(".layui-upload-choose").text() !=null && $(".layui-upload-choose").text() !=""){
  			load = layer.load(); //上传loading
  		}else{
  			//未选中文件
  			layer.alert("请选择文件");
  		}
  	 }
	    ,done: function(res){
	    	if(res){
	    		layer.close(load);
	    		 layer.open({
	    	            type: 1,
	    	            shadeClose: true, //点击遮罩关闭
	    	            shade: 0.8,
	    	            area: ['30%', '30%'],
	    	            closeBtn: 1,
	    	            content: res.message,
	    	            end: function () {
	    	                location.reload();
	    	            }

	    	        });
	    	}
	    }
	 });	
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