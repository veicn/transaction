layui.use(['layer', 'table','form'], function(){
	var table = layui.table,
	form = layui.form;

	// 配置参数
	var tableOptions = {
		id:'listReload',
		url: infoServer + '/values/codeSetManageList.json', // 请求地址
		method:'POST',
		page: {layout: ['prev', 'page', 'next', 'skip']
		,first:'首页',last:'尾页',prev:"上一页",next:"下一页"}
	};
	// 初始化
	table.init('demo', tableOptions);
	
	$('.layui-table-page').attr('align','right');
	$('.layui-table-body').css("overflow","hidden");
	
  
	// 重新载入表格
	function tabReload(num){
		table.reload('listReload', {
			page: {
				curr: num // 重新从第 1 页开始
			}
		});
		$('.layui-table-page').attr('align','right');
		$('.layui-table-body').css("overflow","hidden");
 	}
	
	// 重新载入子查询表格
	function subTabReload(){
		table.reload('subListReload');
 	}

	// 查询
	$('#btn').on('click', function(){
		table.reload('listReload', {
			page: {
				curr: 1 // 重新从第 1 页开始
			}  
			,where: {
				module: $('#module').val(),
				setName: $('#setName').val()
			}
		});
		$('.layui-table-page').attr('align','right');
		$('.layui-table-body').css("overflow","hidden");
	});
  
  	// 新增
	$('#add').on('click', function(){
		addFn({
			'echo':false // 新增不需要回显
		});
	});

	$("body").keydown(function() {
	    if (event.keyCode == "13") {//keyCode=13是回车键
	        $('#btn').click();
	    }
	});
	
	// 监听工具条
	table.on('tool(demo)', function(obj){
		var oldData = obj.data;  // 一行的数据
		
		// 显示值集
		if(obj.event === 'show'){
			showItemFn({
				'data':oldData
			});
		} else if (obj.event === 'add') {
			addFn({
				'echo':true,
				'data':oldData
			});
			// 删除
		} else if(obj.event === 'del'){
			layer.confirm('真的删除行么', function(index){

				// 提交ajax
				$.ajaxFn({
					url:infoServer + '/values/delCodeSet.json',
					data:{
						'module':oldData.module,
						'setCode':oldData.setCode
					},
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
						}
					}
				})    
			});
			// 修改
		} else if(obj.event === 'edit'){
			editSetFn({
				'data':oldData
			});
		}
	});
	
	// 监听工具条
	table.on('tool(demo2)', function(obj){
		var oldData = obj.data;  // 一行的数据
		// 修改
		if(obj.event === 'itemEdit'){
			editItemFn({
				'data':oldData
			});
			// 删除
		} else if (obj.event === 'itemDel') {
			
			layer.confirm('真的删除行么', function(index){
				
				// 提交ajax
				$.ajaxFn({
					url:infoServer + '/values/delCodeItem.json',
					data:oldData.id,
					success:function(data){
						
						layer.close(index);
						
						if (data.status != 0) {
							layer.open({
								content: data.message
							});
						} else {
							// 重新载入列表
							if (table.cache['subListReload'].length == 1) {
								layer.closeAll();
								tabReload();
							} else {
								subTabReload();
							}
						}
					}
				})    
			});
		}
		
	});

	// 新增
	function addFn(obj){
		// 第一步： 请求弹框
		$.ajax({
			url:infoServer + '/info/bomb/sysCodeSet/addSysCodeSet.html',
			success:function(data){
				// 自带弹框
				layer.open({
					type:1,
					content: data,
					title: '值集详情（*为必填项）',
					area: '450px'
				});
				// 第二部：回显数据->编辑
				if(obj.echo){
					$('#addSysCodeSet input[name="modelCode"]').val(obj.data.module);
					$('#addSysCodeSet input[name="setCode"]').val(obj.data.setCode);
					$('#addSysCodeSet input[name="setName"]').val(obj.data.setName);
					$('#addSysCodeSet input[name="modelCode"]').attr("readonly","readonly")
					$('#addSysCodeSet input[name="setCode"]').attr("readonly","readonly")
					$('#addSysCodeSet input[name="setName"]').attr("readonly","readonly")
				}
				//主频道类型
				$.ajax({
					url:infoServer+'/values/codeList.json',
					type:'post',
					data:JSON.stringify({setCode:'LANG_VER'}),
					dataType:"JSON",
					async: false, 
					headers : {  
						'Content-Type' : 'application/json;charset=utf-8'  
					},
					success:function(res){
						// 下拉框赋值
						for(var i = 0; i < res.datas.length;  i++){
							if(res.datas[i].itemCode=="zh"){
								$('#langVer').append('<option selected value="'+res.datas[i].itemCode+'">'+res.datas[i].itemName+'</option>');
							}else{
								$('#langVer').append('<option value="'+res.datas[i].itemCode+'">'+res.datas[i].itemName+'</option>');
							}
						}
						
					}
				});	
				form.render();
			},
			error:function(data){
				alert('失败');
			}
		})
	}
	// 类别修改
	function editSetFn(obj){
		// 第一步： 请求弹框
		$.ajax({
			url:infoServer + '/info/bomb/sysCodeSet/editSysCodeSet.html',
			success:function(data){
				// 自带弹框
				layer.open({
					type:1,
					content: data,
					title: '修改信息（*为必填项）',
					area: '450px'
				});
				// 第二部：回显数据->编辑
				$('#editSysCodeSet input[name="modelCode"]').val(obj.data.module);
				$('#editSysCodeSet input[name="setCode"]').val(obj.data.setCode);
				$('#editSysCodeSet input[name="setName"]').val(obj.data.setName);
				$('#editSysCodeSet input[name="oldModule"]').val(obj.data.module);
				$('#editSysCodeSet input[name="oldSetCode"]').val(obj.data.setCode);
			},
			error:function(data){
				alert('失败');
			}
		})
	}
	
	// 值集修改
	function editItemFn(obj){
		// 第一步： 请求弹框
		$.ajax({
			url:infoServer + '/info/bomb/sysCodeSet/editItemSysCodeSet.html',
			success:function(data){
				// 自带弹框
				layer.open({
					type:1,
					content: data,
					title: '修改信息（*为必填项）',
					area: '450px'
				});
				// 第二部：回显数据->编辑
				$('#editItemSysCodeSet input[name="itemCode"]').val(obj.data.itemCode);
				$('#editItemSysCodeSet input[name="itemName"]').val(obj.data.itemName);
				$('#editItemSysCodeSet input[name="itemSort"]').val(obj.data.itemSort);
				$('#editItemSysCodeSet input[name="id"]').val(obj.data.id);
				$('#editItemSysCodeSet select[name="langVer"]').val(obj.data.langVer);
				$('#editItemSysCodeSet input[name="ext1"]').val(obj.data.ext1);
				//主频道类型
				$.ajax({
					url:infoServer+'/values/codeList.json',
					type:'post',
					data:JSON.stringify({setCode:'LANG_VER'}),
					dataType:"JSON",
					async: false, 
					headers : {  
						'Content-Type' : 'application/json;charset=utf-8'  
					},
					success:function(res){
						// 下拉框赋值
						for(var i = 0; i < res.datas.length;  i++){
							if(obj.data.langVer == res.datas[i].itemCode){
								$('#langVer').append('<option selected value="'+res.datas[i].itemCode+'">'+res.datas[i].itemName+'</option>');
							}else{
								$('#langVer').append('<option value="'+res.datas[i].itemCode+'">'+res.datas[i].itemName+'</option>');
							}
						}
						
					}
				});	
				form.render();
			},
			error:function(data){
				alert('失败');
			}
		})
	}
	
	// 值集显示
	function showItemFn(obj){
		// 第一步： 请求弹框
		$.ajax({
			url:infoServer + '/info/bomb/sysCodeSet/subSysCodeSet.html',
			success:function(data){
					
				// 自带弹框
				layer.open({
					type:1
					,content: data
					,title: '值集列表'
					,area: ['70%', '70%']
					,offset: [160,300]
				});
				
				table.render({
					id:'subListReload'
				    ,elem: '#test1'
				    ,url:infoServer + '/values/codeList.json'
			  		,where:{setCode:obj.data.setCode}
				    ,method:'POST'
				    ,cols: [[
				      {field:'itemSort',title:'排序号'}
				      ,{field:'langVer',title:'语言类型'}
				      ,{field:'ext1',title:'值分类'}
				      ,{field:'itemCode',title:'值代码'}
				      ,{field:'itemName',title:'值名称'}
				      ,{field:'editable', templet: '#editableTpl',title:'是否可编辑'}
				      ,{fixed: 'right', width:190, align:'center', toolbar: '#barDemo2'}
				    ]]
				});
				// 添加值集
				form.render();
				$('.layui-table-body').css("overflow","hidden");
			},
			error:function(data){
				alert('失败');
			}
		})
	}

	// 监听提交
	form.on('submit(demo1)', function(data){
		
		var data;
		if($('#addSysCodeSet input[name="modelCode"]').attr("readonly") == 'readonly'){
			data={
					'modelCode':$('#addSysCodeSet input[name="modelCode"]').val(),
					'setCode':$('#addSysCodeSet input[name="setCode"]').val(),
					'setName':$('#addSysCodeSet input[name="setName"]').val(),
					'itemCode':$('#addSysCodeSet input[name="itemCode"]').val(),
					'itemName':$('#addSysCodeSet input[name="itemName"]').val(),
					'itemSort':$('#addSysCodeSet input[name="itemSort"]').val(),
					'langVer':$('#addSysCodeSet select[name="langVer"]').val(),
					'ext1':$('#addSysCodeSet input[name="ext1"]').val(),
					'addflag':'0'
				}
		} else {
			data = {
				'modelCode':$('#addSysCodeSet input[name="modelCode"]').val(),
				'setCode':$('#addSysCodeSet input[name="setCode"]').val(),
				'setName':$('#addSysCodeSet input[name="setName"]').val(),
				'itemCode':$('#addSysCodeSet input[name="itemCode"]').val(),
				'itemName':$('#addSysCodeSet input[name="itemName"]').val(),
				'itemSort':$('#addSysCodeSet input[name="itemSort"]').val(),
				'langVer':$('#addSysCodeSet select[name="langVer"]').val(),
				'ext1':$('#addSysCodeSet input[name="ext1"]').val(),
				'addflag':'1'
			}
		}
		// 提交ajax
		$.ajaxFn({
			url:infoServer + '/values/addCodeSet.json',
			data:data,
			success:function(data){
				
				if (data.status != 0) {
					
					if($('#addSysCodeSet input[name="modelCode"]').attr("readonly") != 'readonly'){
						// 重新载入列表
						table.reload('listReload', {
							page: {
								curr: 1 // 重新从第 1 页开始
							}  
							,where: {
								module: $('#addSysCodeSet input[name="modelCode"]').val(),
								setCode: $('#addSysCodeSet input[name="setCode"]').val()
							}
						});
						// 关闭弹框
						layer.closeAll();
					}

					layer.open({
						content: data.message
					});
					
				} else {
					// 重新载入列表
					tabReload(1);
					// 关闭弹框
					layer.closeAll();
				}
			}
		});
		return false;
	});
	
	// 监听提交
	form.on('submit(demo2)', function(data){
		
		// 提交ajax
		$.ajaxFn({
			url:infoServer + '/values/editCodeSet.json',
			data:{
				'module':$('#editSysCodeSet input[name="modelCode"]').val(),
				'setCode':$('#editSysCodeSet input[name="setCode"]').val(),
				'setName':$('#editSysCodeSet input[name="setName"]').val(),
				'oldModule':$('#editSysCodeSet input[name="oldModule"]').val(),
				'oldSetCode':$('#editSysCodeSet input[name="oldSetCode"]').val()
			},
			success:function(data){
				// 关闭弹框
				layer.closeAll();
				if (data.status != 0) {
					layer.open({
						content: data.message
					});
				} else {
					// 重新载入列表
					tabReload(1);
				}
			}
		});
		return false;
	});
	
	// 监听提交
	form.on('submit(demo3)', function(data){
		
		// 提交ajax
		$.ajaxFn({
			url:infoServer + '/values/editCodeItem.json',
			data:{
				'id':$('#editItemSysCodeSet input[name="id"]').val(),
				'itemCode':$('#editItemSysCodeSet input[name="itemCode"]').val(),
				'itemName':$('#editItemSysCodeSet input[name="itemName"]').val(),
				'itemSort':$('#editItemSysCodeSet input[name="itemSort"]').val(),
				'ext1':$('#editItemSysCodeSet input[name="ext1"]').val(),
				'langVer':$('#editItemSysCodeSet select[name="langVer"]').val()
			},
			success:function(data){
				// 关闭弹框
				layer.close(layer.index);
				if (data.status != 0) {
					layer.open({
						content: data.message
					});
				} else {
					// 重新载入列表
					subTabReload();
				}
			}
		});
		return false;
	});
}); 