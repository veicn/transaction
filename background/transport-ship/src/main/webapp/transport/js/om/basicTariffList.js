layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	var upload = layui.upload;  
	

	/*文件 上传*/
	  var temp = '<p>';
	  	temp = temp + '<input type="hidden" class="storePath" value="{{filePath}}" />';
		temp = temp + '<input type="hidden" class="fileName" value="{{fileName}}" />';
		temp = temp + '<input type="hidden" class="fileSize" value="{{fileSize}}" />';
		temp = temp + '<input type="hidden" class="storeName" value="{{fileId}}" /></p>'; 
	  
	  function fileUp(id, clazz,dir,file,id2) {
				var options = {
					multi_selection: false, //设置是否多选
					listId: id, 
					itemTemplate: temp, //设置模板
					signatureUrl: ossServer+'/oss/policy.json?bucket=sinochem-b2b-dev&dir='+dir,
					dir: dir,
					filters: {
						max_file_size: '3M',
						mime_types: [ //上传文件后缀名
							{
								extensions: "pdf,txt,jpg,xls,xlsx,docx,doc"
							}
						],
					},
					swfUrl: '../../lib/upload/Moxie.swf',
					xapUrl: '../../lib/upload/Moxie.xap',
					success: function(imgUrl) {
						$(file).attr('data-address',imgUrl).val($(id2).find('.fileName').val()).attr('title',$(id2).find('.fileName').val());
					}
				};
				//$(clazz).bsPlupload(options);
			}
	  		
	 fileUp('accessory', '#btn','transport','#file','#accessory');
	 
	//在途导入
	  var loadIndex;
	  var uploaderObj = new plupload.Uploader({ //实例化一个plupload上传对象
		browse_button : 'imports',
		url : shipServer+'/importBt.json',
		headers:{'Authorization':""},
		flash_swf_url : 'js/Moxie.swf',
		silverlight_xap_url : 'js/Moxie.xap',
		multi_selection:false,
	    multipart_params: {'uuid':uuid }  
	});
	  uploaderObj.init(); //初始化
	//开始上传
	uploaderObj.bind('FilesAdded', function(up, files) {
		loadIndex = layer.load(0, {shade: false});
		uploaderObj.start(); 
	});
	//上传完成
	uploaderObj.bind('FileUploaded',function(uploader,file){
		$.ajax({
			type : "POST",
			url : shipServer+"/import/returnFlag.json",
			data:{},
			success : function(data) {
				layer.close(loadIndex);
				if(data.status == 0){
					layer.alert(data.datas,  function(index) {
						layer.close(index);
						location.reload();
					});
				}else{
					message(data.message);
				}
			},
			error:function(data){
				layer.close(loadIndex);
				layer.alert("失败",  function(index) {
					layer.close(index);
					location.reload();
				});
			}
		});
	});
	
	//导出
	$('#exportEx').on('click',function(){
		var obj={};
		obj.type='1';
		$.ajax({
			type: "POST",
			url: shipServer+"/exportBt.json",
			data: JSON.stringify(obj),
			dataType: "json",
			contentType:"application/json",
			success: function(data) {
				if(data.status == 0){
					window.location.href=data.datas;
				} else {
					message(data.message);
				}},
		   error:function(){
			   message('系统异常');
			   }
		});
	});
	
	//页面记录的uuid
	var uuid = "";
	//乐观锁
	var versionu = "";
	
	var load1 = "";
	var unload1 = "";
	var load2 = "";
	var unload2 = "";
	var load3 = "";
	var unload3 = "";
	var yearcx = "";
	
	// 查询函数
	$('.searchBtn').on('click',function(){
		$('.loadPortClass1 dd').each(function(){
			if ($(this).hasClass('layui-this') ){ 
				if($(this).html() == "请选择"){
					load1 = "";
				}else{
					load1=$(this).html(); 
				}
			}
		});
		$('.loadPortClass2 dd').each(function(){
			if ($(this).hasClass('layui-this') ){ 
				if($(this).html() == "请选择"){
					load2 = "";
				}else{
					load2=$(this).html(); 
				}
			}
		});
		$('.loadPortClass3 dd').each(function(){
			if ($(this).hasClass('layui-this') ){ 
				if($(this).html() == "请选择"){
					load3 = "";
				}else{
					load3=$(this).html(); 					
				}
			}
		});
		$('.unloadPortClass1 dd').each(function(){
			if ($(this).hasClass('layui-this') ){ 
				if($(this).html() == "请选择"){
					unload1 = "";
				}else{
					unload1=$(this).html(); 					
				}
			}
		});
		$('.unloadPortClass2 dd').each(function(){
			if ($(this).hasClass('layui-this') ){ 
				if($(this).html() == "请选择"){
					unload2 = "";
				}else{
					unload2=$(this).html(); 
				}
			}
		});
		$('.unloadPortClass3 dd').each(function(){
			if ($(this).hasClass('layui-this') ){ 
				if($(this).html() == "请选择"){
					unload3 = "";
				}else{
					unload3=$(this).html(); 
				}
			}
		});
		
		$('.yearcxClass dd').each(function(){
			if ($(this).hasClass('layui-this') ){ 
				if($(this).html() == "请选择"){
					yearcx = "";
				}else{
					yearcx=$(this).html(); 
				}
			}
		});
		
		search(load1, unload1, load2, unload2, load3, unload3, yearcx);
		
	});
	//重置查询
	$('.searchBtn2').on('click',function(){
		search(load1, unload1, load2, unload2, load3, unload3, yearcx);
	});

	function search(load1, unload1, load2, unload2, load3, unload3, yearcx){
		window.location.href=shipServer+"/om/basicTariff/basicTariffList.htm?loadPort1="+ load1 + "&unloadPort1="+ unload1 + "&loadPort2=" + load2 + "&unloadPort2=" + unload2 + "&loadPort3=" + load3 + "&unloadPort3=" + unload3 + "&year=" +yearcx;
	}
	
	//获取url参数
	function getUrlParam(name) {
		  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
		  var r = window.location.search.substr(1).match(reg); //匹配目标参数
		  if (r != null) return decodeURI(r[2]); return null; //返回参数值
	}
	var one = getUrlParam('loadPort1');
	var two = getUrlParam('loadPort2');
	var three = getUrlParam('loadPort3');
	var oneun = getUrlParam('unloadPort1');
	var twoun = getUrlParam('unloadPort2');
	var threeun = getUrlParam('unloadPort3');
	var yearaa = getUrlParam('year');
	
	//搜索框 装港一回显
	$('.loadPortClass1 dd').each(function(){
		$(this).removeClass('layui-this')
		if ($(this).attr('lay-value') == one){
			$(this).addClass('layui-this');
			$('.loadPortClass1').find(".layui-unselect").val(one);
		}
	});
	//搜索框 装港二回显
	$('.loadPortClass2 dd').each(function(){
		$(this).removeClass('layui-this')
		if ($(this).attr('lay-value') == two){
			$(this).addClass('layui-this');
			$('.loadPortClass2').find(".layui-unselect").val(two);
		}
	});
	//搜索框 装港三回显
	$('.loadPortClass3 dd').each(function(){
		$(this).removeClass('layui-this')
		if ($(this).attr('lay-value') == three){
			$(this).addClass('layui-this');
			$('.loadPortClass3').find(".layui-unselect").val(three);
		}
	});
	//搜索框 卸港一回显
	$('.unloadPortClass1 dd').each(function(){
		$(this).removeClass('layui-this')
		if ($(this).attr('lay-value') == oneun){
			$(this).addClass('layui-this');
			$('.unloadPortClass1').find(".layui-unselect").val(oneun);
		}
	});
	//搜索框 卸港二回显
	$('.unloadPortClass2 dd').each(function(){
		$(this).removeClass('layui-this')
		if ($(this).attr('lay-value') == twoun){
			$(this).addClass('layui-this');
			$('.unloadPortClass2').find(".layui-unselect").val(twoun);
		}
	});
	//搜索框 卸港三回显
	$('.unloadPortClass3 dd').each(function(){
		$(this).removeClass('layui-this')
		if ($(this).attr('lay-value') == threeun){
			$(this).addClass('layui-this');
			$('.unloadPortClass3').find(".layui-unselect").val(threeun);
		}
	});
	//搜索框 年份回显
	$('.yearcxClass dd').each(function(){
		$(this).removeClass('layui-this')
		if ($(this).attr('lay-value') == yearaa){
			$(this).addClass('layui-this');
			$('.yearcxClass').find(".layui-unselect").val(yearaa);
		}
	});
	
	
	//新增
	$(".insertBtn").on('click', function(){
		uuid = $(this).attr("attr-data");
		$.ajax({
			type:"get",
			url:shipServer+"/transport/bomb/editBasicTariff.vm",
			success:function(data){
				//弹框运行
				layer.open({
					type: 1,
					area: ['600px','650px'],
					btn: ['提交', '返回'],
					btnAlign: 'c' ,//按钮居中
					title:'添加信息（*为必填项）',
					content: data,
					yes: function(index, layero){
						  // 校验 
						  var reg = new RegExp("^([1-9]\d*(\.\d*[1-9])?)|(0.\d*[1-9])$");
						  var reg2 = new RegExp("^[0-9]{4}$");
						  var loadPort1 = $("#loadPort1").val();
						  var unloadPort1 = $("#unloadPort1").val();
						  var loadPort2 = $("#loadPort2").val();
						  var unloadPort2 = $("#unloadPort2").val();
						  var loadPort3 = $("#loadPort3").val();
						  var unloadPort3 = $("#unloadPort3").val();
						  var price = $("#price").val();
						  var region = $("#region").val();
						  var year = $("#year").val();
						  var mileage = $("#mileage").val();
						  
						  var params = {};
						  params.loadPort1 = loadPort1;
						  params.loadPort2 = loadPort2;
						  params.loadPort3 = loadPort3;
						  params.unloadPort1 = unloadPort1;
						  params.unloadPort2 = unloadPort2;
						  params.unloadPort3 = unloadPort3;
						  params.price = price;
						  params.year = year;
						  params.mileage = mileage;
						  params.region = region;
						  params.remark = $("#remark").val();
						  params.remark2 = $("#remark2").val();
						  //校检第一装港非空，长度
						  if(loadPort1=="" || loadPort1.length >= 50) {
							  layer.alert("第一装港必须选，或者不能不选第一装港就选第二装港！").style.zIndex = 9999999999;
					  		  return;
					      }//如果选择其它选项，判断输入框
						  if(loadPort1=="其它"){
							  if($("#loadPort1q").val() == "" || $("#loadPort1q").val().length > 10){
								  layer.alert("第一装港必填,且50个字符以内").style.zIndex = 9999999999;
							  }
							  params.loadPort1 = $("#loadPort1q").val();
						  }
						  
						  //校检第一卸港非空，长度
						  if(unloadPort1=="" || unloadPort1.length >= 50) {
							  layer.alert("第一卸港必须填，或者不能不填第一卸港就填第二卸港！").style.zIndex = 9999999999;
					  		  return;
					      }//如果选择其它选项，判断输入框
						  if(unloadPort1=="其它"){
							  if($("#unloadPort1q").val() == "" || $("#unloadPort1q").val().length > 50){
								  layer.alert("第一卸港必填,且50个字符以内").style.zIndex = 9999999999;
							  }
							  params.unloadPort1 = $("#unloadPort1q").val();
						  }
						  
						  //判断顺序（第二装港），输入港口
						  if(loadPort2=='' && loadPort3!=''){
							  layer.alert("请按照顺序填写！不能不填第二装港就填第三装港！").style.zIndex = 9999999999;
					  		  return;
						  }//如果选择其它选项，判断输入框
						  if(loadPort2=="其它"){
							  if($("#loadPort2q").val() == "" || $("#loadPort2q").val().length > 50){
								  layer.alert("第二装港必填,且50个字符以内").style.zIndex = 9999999999;
							  }
							  params.loadPort2 = $("#loadPort2q").val();
						  }
						  
						  //判断顺序（第二卸港），输入港口
						  if(unloadPort2=='' && unloadPort3!=''){
							  layer.alert("请按照顺序填写！不能不填第二卸港就填、第三卸港！").style.zIndex = 9999999999;
					  		  return;
						  }//如果选择其它选项，判断输入框
						  if(unloadPort2=="其它"){
							  if($("#unloadPort2q").val() == "" || $("#unloadPort2q").val().length > 50){
								  layer.alert("第二卸港必填,且50个字符以内").style.zIndex = 9999999999;
							  }
							  params.unloadPort2 = $("#unloadPort2q").val();
						  }
						  
						  //（第三装港）
						  //如果选择其它选项，判断输入框
						  if(loadPort3=="其它"){
							  if($("#loadPort3q").val() == "" || $("#loadPort3q").val().length > 50){
								  layer.alert("第三装港必填,且50个字符以内").style.zIndex = 9999999999;
							  }
							  params.loadPort3 = $("#loadPort3q").val();
						  }
						  
						  //（第三卸港）
						  //如果选择其它选项，判断输入框
						  if(unloadPort3=="其它"){
							  if($("#unloadPort3q").val() == "" || $("#unloadPort3q").val().length > 50){
								  layer.alert("第三卸港必填,且50个字符以内").style.zIndex = 9999999999;
							  }
							  params.unloadPort3 = $("#unloadPort3q").val();
						  }
						  
						  if(price=="" || !reg.test(price)) {
							  layer.alert("费率不能为空，且为10位以内数字！").style.zIndex = 9999999999;
					  		  return;
					      }
						  if(year=="") {
							  layer.alert("请选择年份！").style.zIndex = 9999999999;
					  		  return;
					      }
						  if(region=="" || region.length >10) {
							  layer.alert("请输入地区10字符以内").style.zIndex = 9999999999;
					  		  return;
					      }
						  if(mileage==""|| !reg.test(mileage)) {
							  layer.alert("请填写正确的里程！").style.zIndex = 9999999999;
					  		  return;
					      }
						  if($("#remark").length > 50){
							  layer.alert("参考路线内容过长！").style.zIndex = 9999999999;
					  		  return;
						  }
						  if($("#remark2").length > 255){
							  layer.alert("注意事项内容过长！").style.zIndex = 9999999999;
					  		  return;
						  }

						  $.ajax({
								type: "POST",
								url: shipServer+"/om/basicTariff/saveBasicTariff.json",
								data: JSON.stringify(params),
								dataType: "json",
								contentType:"application/json",
								async: false,
								success: function(data) {  
										if(data.status == 0){
											layer.alert('保存成功!', function(index) {
											    layer.close(index);
											    location.reload();
											});
										}else{
											layer.alert("错误:" + data.message, function(index) {
												layer.close(index);
											});
										}
								},
								error: function(data) {
					                alert("错误:" + data.message);
					            }
							});
					},
				btn2: function(index, layero){
					  layer.alert('放弃保存吗!', function(index) {
							    layer.close(index);
							    location.reload();
						  });
						  return false;
				  }
				});    
			form.render();
			}
		});
	});
	
	//删除
	$(".delFn").on('click', function(){
		uuid = $(this).attr("attr-data");
		//弹框询问
		 layer.confirm('确定删除吗？', function(index){
		    	var params = {};
		    	params.uuid = uuid;
		    	
		    	$.ajax({
		    		type: "POST",
		    		url: shipServer+"/om/basicTariff/deleteBasicTariff.json",
		    		data: JSON.stringify(params),
					dataType: "json",
					contentType:"application/json",
		    		async: false,
		    		success: function(data) {
						if(data.status == 0){
							//layer.alert("删除成功!").style.zIndex = 9999999999;
							layer.alert('删除成功!', function(index) {
							    layer.close(index);
							    location.reload();
							});
						}else{
							layer.alert("错误:" + data.message, function(index) {
								layer.close(index);
							});
						}
					},
					error: function(data) {
			            alert("错误:" + data.message);
			        }
		    	});
		    });
	});
	
	//修改
	$(".editFn").on('click', function(){
		uuid = $(this).attr("attr-data");
		
		// 请求弹框
		$.ajax({
			type:"get",
			url:"/transport/bomb/editBasicTariff.vm",
			success:function(data){
				//弹框运行
				layer.open({
					type: 1,
					area: ['600px','650px'],
					btn: ['提交', '返回'],
					btnAlign: 'c' ,//按钮居中
					title:'修改信息（*为必填项）',
					content: data,
					yes: function(index, layero){
						  // 校验 
						  var reg = new RegExp("^([1-9]\d*(\.\d*[1-9])?)|(0.\d*[1-9])$");
						  var loadPort1 = $("#loadPort1").val();
						  var unloadPort1 = $("#unloadPort1").val();
						  var loadPort2 = $("#loadPort2").val();
						  var unloadPort2 = $("#unloadPort2").val();
						  var loadPort3 = $("#loadPort3").val();
						  var unloadPort3 = $("#unloadPort3").val();
						  var price = $("#price").val();
						  var year = $("#year").val();
						  var region = $("#region").val();
						  var mileage = $("#mileage").val();
						  
						  var params = {};
						  params.uuid = uuid;
						  params.loadPort1 = loadPort1;
						  params.loadPort2 = loadPort2;
						  params.loadPort3 = loadPort3;
						  params.unloadPort1 = unloadPort1;
						  params.unloadPort2 = unloadPort2;
						  params.unloadPort3 = unloadPort3;
						  params.price = price;
						  params.year = year;
						  params.region = region;
						  params.mileage = mileage;
						  params.remark = $("#remark").val();
						  params.remark2 = $("#remark2").val();
						  params.version = versionu;
						  //校检第一装港非空，长度
						  if(loadPort1=="" || loadPort1.length >= 50) {
							  layer.alert("第一装港必须选，或者不能不选第一装港就选第二装港！").style.zIndex = 9999999999;
					  		  return;
					      }//如果选择其它选项，判断输入框
						  if(loadPort1=="其它"){
							  if($("#loadPort1q").val() == "" || $("#loadPort1q").val().length > 50){
								  layer.alert("第一装港必填,且50个字符以内").style.zIndex = 9999999999;
							  }
							  params.loadPort1 = $("#loadPort1q").val();
						  }
						  
						  //校检第一卸港非空，长度
						  if(unloadPort1=="" || unloadPort1.length >= 50) {
							  layer.alert("第一卸港必须填，或者不能不填第一卸港就填第二卸港！").style.zIndex = 9999999999;
					  		  return;
					      }//如果选择其它选项，判断输入框
						  if(unloadPort1=="其它"){
							  if($("#unloadPort1q").val() == "" || $("#unloadPort1q").val().length > 50){
								  layer.alert("第一卸港必填,且50个字符以内").style.zIndex = 9999999999;
							  }
							  params.unloadPort1 = $("#unloadPort1q").val();
						  }
						  
						  //判断顺序（第二装港），输入港口
						  if(loadPort2=='' && loadPort3!=''){
							  layer.alert("请按照顺序填写！不能不填第二装港就填第三装港！").style.zIndex = 9999999999;
					  		  return;
						  }//如果选择其它选项，判断输入框
						  if(loadPort2=="其它"){
							  if($("#loadPort2q").val() == "" || $("#loadPort2q").val().length > 50){
								  layer.alert("第二装港必填,且50个字符以内").style.zIndex = 9999999999;
							  }
							  params.loadPort2 = $("#loadPort2q").val();
						  }
						  
						  //判断顺序（第二卸港），输入港口
						  if(unloadPort2=='' && unloadPort3!=''){
							  layer.alert("请按照顺序填写！不能不填第二卸港就填、第三卸港！").style.zIndex = 9999999999;
					  		  return;
						  }//如果选择其它选项，判断输入框
						  if(unloadPort2=="其它"){
							  if($("#unloadPort2q").val() == "" || $("#unloadPort2q").val().length > 50){
								  layer.alert("第二卸港必填,且50个字符以内").style.zIndex = 9999999999;
							  }
							  params.unloadPort2 = $("#unloadPort2q").val();
						  }
						  
						  //（第三装港）
						  //如果选择其它选项，判断输入框
						  if(loadPort3=="其它"){
							  if($("#loadPort3q").val() == "" || $("#loadPort3q").val().length > 50){
								  layer.alert("第三装港必填,且50个字符以内").style.zIndex = 9999999999;
							  }
							  params.loadPort3 = $("#loadPort3q").val();
						  }
						  
						  //（第三卸港）
						  //如果选择其它选项，判断输入框
						  if(unloadPort3=="其它"){
							  if($("#unloadPort3q").val() == "" || $("#unloadPort3q").val().length > 50){
								  layer.alert("第三卸港必填,且50个字符以内").style.zIndex = 9999999999;
							  }
							  params.unloadPort3 = $("#unloadPort3q").val();
						  }
						  
						  if(price=="" || !reg.test(price)) {
							  layer.alert("费率不能为空，且为10位以内数字！").style.zIndex = 9999999999;
					  		  return;
					      }
						  if(year=="") {
							  layer.alert("请选择年份！").style.zIndex = 9999999999;
					  		  return;
					      }
						  if(mileage==""|| !reg.test(mileage)) {
							  layer.alert("请填写正确的里程！").style.zIndex = 9999999999;
					  		  return;
					      }
						  if(region=="" || region.length >10) {
							  layer.alert("请输入地区10字符以内").style.zIndex = 9999999999;
					  		  return;
					      }
						  if($("#remark").length > 50){
							  layer.alert("参考路线内容过长！").style.zIndex = 9999999999;
					  		  return;
						  }
						  if($("#remark2").length > 255){
							  layer.alert("注意事项内容过长！").style.zIndex = 9999999999;
					  		  return;
						  }
						  
						  $.ajax({
								type: "POST",
								url: shipServer+"/om/basicTariff/updateBasicTariff.json",
								data: JSON.stringify(params),
								dataType: "json",
								contentType:"application/json",
								async: false,
								success: function(data) {  
										if(data.status == 0){
											layer.alert('保存成功!', function(index) {
											    layer.close(index);
											    location.reload();
											});
										}else{
											layer.alert("错误:" + data.message, function(index) {
												layer.close(index);
											});
										}
								},
								error: function(data) {
					                alert("错误:" + data.message);
					            }
							});
					},
				btn2: function(index, layero){
					  layer.alert('放弃保存吗!', function(index) {
							    layer.close(index);
							    location.reload();
						  });
						  return false;
				  }
				});    
				
				//查询详细
			 	var params = {};
			 	params.uuid = uuid;
	    	
			 	$.ajax({
		    		type: "POST",
		    		url: shipServer+"/om/basicTariff/getBasicTariff.json",
		    		data: JSON.stringify(params),
					dataType: "json",
					contentType:"application/json",
		    		async: false,
		    		success: function(data) {
						if(data.status == 0){
							
							uuid = data.datas.uuid;
							$("#loadPort1").val(data.datas.loadPort1);
							$("#loadPort2").val(data.datas.loadPort2);
							$("#loadPort3").val(data.datas.loadPort3);
							$("#unloadPort1").val(data.datas.unloadPort1);
							$("#unloadPort2").val(data.datas.unloadPort2);
							$("#unloadPort3").val(data.datas.unloadPort3);
							$("#price").val(data.datas.price);
							$("#year").val(data.datas.year);
							$("#region").val(data.datas.region);
							$("#mileage").val(data.datas.mileage);
							$("#remark").val(data.datas.remark);
							$("#remark2").val(data.datas.remark2);
							versionu = data.datas.version;
						}else{
							layer.alert("错误:" + data.message, function(index) {
								layer.close(index);
							});
						}
					},
					error: function(data) {
			            alert("错误:" + data.message);
			        }
			 	});
			form.render();
			}
		});
	});
	
	// 日期格式化函数
	Date.prototype.Format = function (fmt) {
	    var o = {
	        "M+": this.getMonth() + 1, 						//月份 
	        "d+": this.getDate(), 							//日 
	        "h+": this.getHours(), 							//小时 
	        "m+": this.getMinutes(), 						//分 
	        "s+": this.getSeconds(), 						//秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), 	//季度 
	        "S": this.getMilliseconds() 					//毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    
	    return fmt;
	}

	
});