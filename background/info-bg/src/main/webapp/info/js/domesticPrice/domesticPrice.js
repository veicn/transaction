layui.use(['layer', 'form','jquery','laypage','element','laydate','layedit', 'table'], function(){
    var layer = layui.layer
    ,form = layui.form,  // 载入form表单
    table = layui.table, //表格
    element = layui.element, // tap
    laydate = layui.laydate, // 日期
    laypage = layui.laypage;   // 载入page分页
    window.$ = layui.jquery;    //zai'lu'
    
	var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
		browse_button : 'file-list',
		url : infoServer+'/mesticProduct/mesticProductDaoru.json',
		flash_swf_url : 'domesticPrice/Moxie.swf',
		silverlight_xap_url : 'domesticPrice/Moxie.xap',
	});
	uploader.init(); //初始化
	//上传按钮
//	$('#upload').click(function(){
//		uploader.start(); //开始上传
//		
//	});
	$('#upload').click(function(){
		if($("#file-list").val() !=null && $("#file-list").val()!=""){
			uploader.start(); //开始上传
		}else{
			//未选中文件
			layer.alert("请选择文件");
		}
	});

	
	// 导出按钮
	$('#export').on('click', function(){
		window.open(infoServer+"/mesticProduct/mesticProductExport.json")
	})
	
	//绑定文件添加进队列事件
	uploader.bind('FilesAdded',function(uploader,files){
		for(var i = 0, len = files.length; i<len; i++){
			var file_name = files[i].name; //文件名
			//构造html来更新UI
			$('#file-list').val(file_name);
		}
	});
	
	uploader.bind('FileUploaded',function(uploader,file,responseObject){
		result = $.parseJSON(responseObject.response);
		layer.alert(result.message,function(){
			window.location.reload();
		});
	});
	
//	// 导出示例
//	$('#exportEx').on('click', function(){
//		window.open(infoServer+"/mesticProduct/mesticProductExportEx.json")
//	})
	
	// 查询
//	$('.searchBtn').on('click', function(){
//		
//		var selProductName = $('#selProductName').val();
//		window.location.href=infoServer+"/om/domesticPrice/domesticPrice.htm?selProductName="+selProductName;
//	})
	
    // 新增函数
    $('.addFn').on('click',function(){
		// 请求弹框
		var url = '../../info/bomb/domesticPrice/domesticPrice.html';
		$.ajax({
		 	type:"get",
		 	url:url,
		 	success:function(data){
		 		
		 		//弹框运行
		 		var bobm=layer.open({
		 			id:'bobm',
				  type: 1,
				  area: ['800px', '370px'],
				  title:'新增报价',
				  content: data,
				  success: function(index, layero){
					  // 请求下拉数据
					  $.ajax({
							url:infoServer+'/values/codeItemList.json',
							type:'post',
							data:JSON.stringify({setCode:'DOMESTIC_PRODUCT'}),
							dataType:"JSON",
							async: false, 
							headers : {  
			                    'Content-Type' : 'application/json;charset=utf-8'  
			                },
							success:function(res){
						 		
						 		// 下拉框赋值
						 		for(var i = 0; i < res.datas.length;  i++){
					 				$('#productCode').append('<option value="'+res.datas[i].itemCode+'">'+res.datas[i].itemName+'</option>');
								}
						 		
						 		$('#priceTrend').append('<option value="0">持平</option>');
						 		$('#priceTrend').append('<option value="1">上涨</option>');
						 		$('#priceTrend').append('<option value="-1">下跌</option>');
							}
						});
					  
					  form.on('submit(btn_edit)', function(data){

						  //表单数据
						  var data = $('#editForm').serializeObject();
						  var checkText=$("#productCode").find("option:selected").text(); 
						  data["productName"]=checkText;
							$.ajax({
								url:infoServer+'/mesticProduct/mesticProductAdd.json',
								type:'POST',
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
		 		form.verify({
		 			highPriceChange:function(value){
						var i = new Number($('input[name=lowPriceChange]').val());
						var j = new Number(value);
						if(i > j)
						    return '不能小于最低价格变动';
					},
					price:function(value){
						var i = new Number($('input[name=lowPrice]').val());
						var j = new Number(value);
						if(i > j)
							return '不能小于最低价格'
					}
				});
		 		// 手动渲染
				form.render();
			    // 执行日期
			    laydate.render({
			        elem: '#priceDate' //指定元素
			    });
		 	}
		 });
	})


    // 编辑函数
    $('.editFn').on('click',function(){
		// 请求弹框
		var url = '../../info/bomb/domesticPrice/domesticPrice.html';
		var strData= $(this).attr('attr-data');
		var objData = JSON.parse(strData);
		$.ajax({
		 	type:"get",
		 	url:url,
		 	success:function(data){
		 		
		 		//打开弹框
		 		var bobm =layer.open({
		 			  type: 1,
					  area: ['800px', '370px'],
					  title:'编辑报价',
					  content: data,
					   success: function(index, layero){
						   
						  // 请求下拉数据
						  $.ajax({
								url:infoServer+'/values/codeItemList.json',
								type:'post',
								data:JSON.stringify({setCode:'DOMESTIC_PRODUCT'}),
								dataType:"JSON",
								async: false, 
								headers : {  
				                    'Content-Type' : 'application/json;charset=utf-8'  
				                },
								success:function(res){
							 		//回显 - 下拉框赋值
							 		for(var i = 0; i < res.datas.length;  i++){
							 			if(res.datas[i].itemName == objData.productName) {
							 				$('#productCode').append('<option selected value="'+res.datas[i].itemCode+'">'+res.datas[i].itemName+'</option>');
							 			} else {
							 				$('#productCode').append('<option value="'+res.datas[i].itemCode+'">'+res.datas[i].itemName+'</option>');
							 			}
									}
							 		
							 		if(objData.priceTrend == "0") {
							 			$('#priceTrend').append('<option selected value="0">持平</option>');
								 		$('#priceTrend').append('<option value="1">上涨</option>');
								 		$('#priceTrend').append('<option value="-1">下跌</option>');
							 		} else if(objData.priceTrend == "1"){
							 			$('#priceTrend').append('<option value="0">持平</option>');
								 		$('#priceTrend').append('<option selected value="1">上涨</option>');
								 		$('#priceTrend').append('<option value="-1">下跌</option>');
							 		} else if(objData.priceTrend == "-1") {
							 			$('#priceTrend').append('<option value="0">持平</option>');
								 		$('#priceTrend').append('<option value="1">上涨</option>');
								 		$('#priceTrend').append('<option selected value="-1">下跌</option>');
							 		} else {
							 			$('#priceTrend').append('<option value="0">持平</option>');
								 		$('#priceTrend').append('<option value="1">上涨</option>');
								 		$('#priceTrend').append('<option value="-1">下跌</option>');
							 		}
							 		console.log(objData.uuid);
							 		// 编辑弹框初始值
							 		$('#uuid').val(objData.uuid);
							 		$('#highPrice').val(objData.highPrice);
							 		$('#lowPrice').val(objData.lowPrice);
							 		$('#priceUnit').val(objData.priceUnit);
							 		$('#priceDate').val(objData.priceDate);
							 		$('#areaName').val(objData.areaName);
							 		$('#priceSource').val(objData.priceSource);
							 		$('#highPriceChange').val(objData.highPriceChange);
							 		$('#lowPriceChange').val(objData.lowPriceChange);
							 		
								}
							});
						  
						  // 监听
						  form.on('submit(btn_edit)', function(data){
							  
							//表单数据
							  var data = $('#editForm').serializeObject();
							  var checkText=$("#productCode").find("option:selected").text(); 
							  data["productName"]=checkText;
								$.ajax({
									url:infoServer+'/mesticProduct/mesticProductUpdate.json',
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
		 		
				form.verify({
					highPriceChange:function(value){
						var i = new Number($('input[name=lowPriceChange]').val());
						var j = new Number(value);
						if(i > j)
						    return '不能小于最低价格变动';
					},
					price:function(value){
						var i = new Number($('input[name=lowPrice]').val());
						var j = new Number(value);
						if(i > j)
							return '不能小于最低价格'
					}
				});
		 		// 渲染
	 			laydate.render({elem:'#priceDate'});
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
				url:infoServer+'/mesticProduct/mesticProductDelete.json?uuid='+objData.uuid,
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