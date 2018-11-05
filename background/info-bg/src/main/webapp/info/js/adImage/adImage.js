layui.use(['layer', 'table','form','upload'], function(){
	var table = layui.table,
	form = layui.form

	// 配置参数
	var tableOptions = {
		id:'listReload',
		url: infoServer + '/carousel/queryCarousels.json', // 请求地址
		method:'POST',
		page: {layout: ['prev', 'page', 'next', 'skip']
		,first:'首页',last:'尾页',prev:"上一页",next:"下一页"}
	};
	
	table.init('demo', tableOptions);
	setTypeCode();
	setPageCode();
	$('.layui-table-page').attr('align','right');
	

	// 重新载入表格
	function tabReload(num){
		table.reload('listReload', {
			page: {
				curr: num // 重新从第 1 页开始
			}
		});
		$('.layui-table-page').attr('align','right');
	}

	// 查询
	$('#search').on('click', function(){
		table.reload('listReload', {
			page: {
				curr: 1 // 重新从第 1 页开始
			}  
			,where: {
				typeCode: $('#typeCode').val(),
				pageCode: $('#pageCode').val(),
				imageDes: $('#imageDes').val()
			}
		});
		$('.layui-table-page').attr('align','right');
	});

	// 新增
	$('#add').on('click', function(){
		addFn({
			'echo':false // 新增不需要回显
		});
	});

	// 系统类型
	form.on('select(typeCode)', function () {
		setPageCode();
	});
	
	$("body").keydown(function() {
	    if (event.keyCode == "13") {//keyCode=13是回车键
	        $('#search').click();
	    }
	});
	
	// 监听工具条
	table.on('tool(demo)', function(obj){
		var oldData = obj.data;  // 一行的数据

		// 删除
		if(obj.event === 'del'){
			layer.confirm('真的删除行么', function(index){

				// 提交ajax
				$.ajaxFn({
					url:infoServer + '/carousel/deleteCarousl.json',
					data:oldData.imageId,
					success:function(data){

						// 关闭弹框
						layer.close(index);
						
						if (data.status != 0) {
							layer.open({
								content: data.message
							});
						} else {
							// 重新载入列表
							tabReload(1);
							setTypeCode();
							setPageCode();
						}
					}
				})
			});
			// 修改
		} else if(obj.event === 'edit'){
			addFn({
				'echo':true,
				'data':oldData
			});
		}
	});

	// 新增
	function addFn(obj){
		// 第一步： 请求弹框
		$.ajax({
			url:infoServer + '/info/bomb/adImage/addAdImage.html',
			success:function(data){
				// 自带弹框
				layer.open({
					type:1,
					area: '700',
					content: data,
					title: '图片管理详情'
				});
				
				// 第二部：回显数据->编辑
				if(obj.echo){
					$('#addAdImage input[name="typeCode"]').val(obj.data.typeCode);
					$('#addAdImage input[name="sortCode"]').val(obj.data.sortCode);
					$('#addAdImage input[name="imageDes"]').val(obj.data.imageDes);
					$('#addAdImage input[name="pageCode"]').val(obj.data.pageCode);
					$('#addAdImage input[name="gotoUrl"]').val(obj.data.gotoUrl);
					$('#addAdImage input[name="imageUrl"]').val(obj.data.imageUrl);
					$('#addAdImage #image').attr('src',obj.data.imageUrl);
					$('#addAdImage #image').css('display','block');
					$('#addAdImage input[name="imageId"]').val(obj.data.imageId);
					$('.layui-layer').css({'top':($('.layui-layer-shade').height() - $('.layui-layer').height())/2});
				}
				//一张图片
				imgUp('image', '#uploadImage','info');
			},
			error:function(data){
				alert('失败');
			}
		})
	}

	// 监听提交
	form.on('submit(demo1)', function(data){
		
		var url,data;

		if ($('#addAdImage input[name="imageId"]').val()) {
			url = infoServer + '/carousel/updateAdImagel.json';
			data={
				'typeCode':$('#addAdImage input[name="typeCode"]').val(),
				'sortCode':$('#addAdImage input[name="sortCode"]').val(),
				'imageDes':$('#addAdImage input[name="imageDes"]').val(),
				'pageCode':$('#addAdImage input[name="pageCode"]').val(),
				'gotoUrl':$('#addAdImage input[name="gotoUrl"]').val(),
				'imageUrl':$('#addAdImage input[name="imageUrl"]').val(),
				'imageId':$('#addAdImage input[name="imageId"]').val()
			}
		} else {
			url = infoServer + '/carousel/addAdImagel.json';
			data={
				'typeCode':$('#addAdImage input[name="typeCode"]').val(),
				'sortCode':$('#addAdImage input[name="sortCode"]').val(),
				'imageDes':$('#addAdImage input[name="imageDes"]').val(),
				'pageCode':$('#addAdImage input[name="pageCode"]').val(),
				'gotoUrl':$('#addAdImage input[name="gotoUrl"]').val(),
				'imageUrl':$('#addAdImage input[name="imageUrl"]').val()
			}
		}
		
		// 提交ajax
		$.ajaxFn({
			url:url,
			data:data,
			success:function(data){
				// 重新载入列表
				tabReload(1);
				setTypeCode();
				setPageCode();
				// 关闭弹框
				layer.closeAll();
			}
		});
		return false;
	});
	
	var temp = '<p>';
  	temp = temp + '<input type="hidden" class="storePath" value="{{filePath}}" />';
	temp = temp + '<input type="hidden" class="fileName" value="{{fileName}}" />';
	temp = temp + '<input type="hidden" class="fileSize" value="{{fileSize}}" />';
	temp = temp + '<input type="hidden" class="storeName" value="{{fileId}}" /></p>'; 
	
	function imgUp(id, clazz,dir) {
		var options = {
			multi_selection: false, //设置是否多选
			listId: id, 
			itemTemplate: temp, //设置模板
			signatureUrl: configUitl.getOssServerPath(infoServer,dir),
			dir: dir,
			filters: {
				max_file_size: '3M',
				mime_types: [ //只允许上传图片文件和rar压缩文件
					{
						extensions: "jpg,png,jpeg,gif"
					}
				],
			},
			swfUrl: '../../lib/upload/Moxie.swf',
			xapUrl: '../../lib/upload/Moxie.xap',
			success: function(imgUrl) {
				$('#image').attr('src',imgUrl);
				$('#image').css('display','block');
				$('#addAdImage input[name="imageUrl"]').val(imgUrl);
				$('.layui-layer').css({'top':($('.layui-layer-shade').height() - $('.layui-layer').height())/2});
			}
		};
		$(clazz).bsPlupload(options);
	}
	
	//自定义验证规则
	form.verify({
		length10: function(value){
			if(value.length > 10){
				return '最多可以输入10个字符';
			}
	    },
		url: [ /^(https?:\/\/)?([\da-z\.-]+)\.([a-z\.]{2,6})([\/\w \.-]*)*\/?$/, '请输入有效的URL']
	});
	
	// 下拉框
	function setTypeCode() {
		
		$("#typeCode").find("option").remove();
		
		$.ajaxFn({
			url:infoServer + '/carousel/queryTypecode.json',
			success:function(data){
				layui.each(data.datas, function (index) {
					$("#typeCode").append("<option value=''></option>")
					$("#typeCode").append("<option value='" + data.datas[index] + "'>" + data.datas[index] + "</option>");
				});
				
				form.render();
			}
		});
		
		
	}
	
	function setPageCode() {
		
		$("#pageCode").find("option").remove();
		
		$.ajaxFn({
			url:infoServer + '/carousel/queryPageCode.json',
			data:{
				typeCode: $("#typeCode").val()
			},
			success:function(data){
				layui.each(data.datas, function (index) {
					$("#pageCode").append("<option value=''></option>")
					$("#pageCode").append("<option value='" + data.datas[index] + "'>" + data.datas[index] + "</option>");
				});

				form.render();
			}
		});
	}

}); 