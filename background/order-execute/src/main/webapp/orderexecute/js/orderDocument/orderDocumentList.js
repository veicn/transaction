//layui.use(['layer', 'table','form'], function(){
//	var table = layui.table,
//	form = layui.form;
//
//	// 配置参数
//	var tableOptions = {
//		id:'listReload',
//		url: orderServer + '/order/documentList.json', // 请求地址
//		method:'POST',
//		page: {layout: ['prev', 'page', 'next', 'skip'],
//			first:$.i18n("orderexecute.code.00173"),
//			last:$.i18n("orderexecute.code.00174"),
//			prev:$.i18n("orderexecute.code.00175"),
//			next:$.i18n("orderexecute.code.00176")}
//	};
//	// 初始化
//	table.init('demo', tableOptions);
//	
//	$('.layui-table-page').attr('align','right');
//	$('.layui-table-body').css("overflow","hidden");
//	
//  
//	// 重新载入表格
//	function tabReload(num){
//		table.reload('listReload', {
//			page: {
//				curr: num // 重新从第 1 页开始
//			}
//		});
//		$('.layui-table-page').attr('align','right');
//		$('.layui-table-body').css("overflow","hidden");
// 	}
//	
//	// 重新载入子查询表格
//	function subTabReload(){
//		table.reload('documentListReload');
// 	}
//
//	// 查询
//	$('#btn').on('click', function(){
//		table.reload('listReload', {
//			page: {
//				curr: 1 // 重新从第 1 页开始
//			}  
//			,where: {
//				tradeCategory: $('#tradeCategory').val(),
//				documentTypeDesc: $('#documentTypeDesc').val()
//			}
//		});
//		$('.layui-table-page').attr('align','right');
//		$('.layui-table-body').css("overflow","hidden");
//	});
//  
//  	// 新增
//	$('#add').on('click', function(){
//		addFn({
//			'echo':false // 新增不需要回显
//		});
//	});
//
//	$("body").keydown(function() {
//	    if (event.keyCode == "13") {//keyCode=13是回车键
//	        $('#btn').click();
//	    }
//	});
//	
//	// 监听工具条
//	table.on('tool(demo)', function(obj){
//		var oldData = obj.data;  // 一行的数据
//		
//		// 显示值集
//		if(obj.event === 'show'){
//			showItemFn({
//				'data':oldData
//			});
//		} else if (obj.event === 'add') {
//			addFn({
//				'echo':true,
//				'data':oldData
//			});
//			// 删除
//		} else if(obj.event === 'del'){
//			layer.confirm($.i18n("orderexecute.code.00138"), function(index){
//
//				// 提交ajax
//				$.ajaxFn({
//					url:orderServer + '/order/delDocumentList.json',
//					data:{
//						'documentId':oldData.documentId,
//						'tradeCategory':oldData.tradeCategory,
//						'documentTypeDesc':oldData.documentTypeDesc,
//						'documentType':oldData.documentType
//					},
//					success:function(data){
//
//						// 关闭弹框
//						layer.close(index);
//						
//						if (data.status != 0) {
//							layer.open({
//								content: $.i18n(data.code)
//							});
//						} else {
//							// 重新载入列表
//							tabReload(1);
//						}
//					}
//				})    
//			});
//			// 修改
//		} else if(obj.event === 'edit'){
//			editSetFn({
//				'data':oldData
//			});
//		}
//	});
//	
//	// 监听工具条
//	table.on('tool(demo2)', function(obj){
//		var oldData = obj.data;  // 一行的数据
//		// 修改
//		if(obj.event === 'itemEdit'){
//			editItemFn({
//				'data':oldData
//			});
//			// 删除
//		} else if (obj.event === 'itemDel') {
//			
//			layer.confirm($.i18n("orderexecute.code.00138"), function(index){
//				
//				// 提交ajax
//				$.ajaxFn({
//					url:orderServer + '/order/delDocumentListItem.json',
//					data:{'documentId':oldData.documentId,
//						'tradeCategory':oldData.tradeCategory,
//						'documentTypeDesc':oldData.documentTypeDesc},
//					success:function(data){
//						
//						layer.close(index);
//						
//						if (data.status != 0) {
//							layer.open({
//								content: data.message
//							});
//						} else {
//							// 重新载入列表
//							if (table.cache['documentListReload'].length == 1) {
//								layer.closeAll();
//								tabReload();
//							} else {
//								subTabReload();
//							}
//						}
//					}
//				})    
//			});
//		}
//		
//	});
//
//	// 新增
//	function addFn(obj){
//		// 第一步： 请求弹框
//		$.ajax({
//			url:orderServer + '/orderexecute/bomp/order/addDocumentList.html',
//			success:function(data){
//				// 自带弹框
//				layer.open({
//					type:1,
//					content: data,
//					title: $.i18n("orderexecute.code.00177"),
//					area: '450px'
//				});
//				// 第二部：回显数据->编辑
//				if(obj.echo){
//		
//					/*$('input[name="tradeCategory"]').val(obj.data.tradeCategory);
//					$('input[name="fileName"]').attr("readonly","readonly")*/
//					//只读
//					if(obj.data.tradeCategory == 1){
//						$('input[name="tradeCategory"]').val($.i18n("orderexecute.code.00178"));
//					}else if(obj.data.tradeCategory == 2){
//						$('input[name="tradeCategory"]').val($.i18n("orderexecute.code.00179"));
//					}else{
//						$('input[name="tradeCategory"]').val($.i18n("orderexecute.code.00180"));
//					}
//
//					$('input[name="documentTypeDesc"]').val(obj.data.documentTypeDesc).attr("readonly","readonly");
//					$('input[name="documentType"]').val(obj.data.documentType).attr("readonly","readonly");
//					//请输入					
//					$('input[name="fileName"]').attr("fileName","readonly");
//					$('input[name="fileCode"]').attr("fileCode","readonly");
//					$('input[name="fromUser"]').attr("fromUser","readonly");
//					$('input[name="toUser"]').attr("toUser","readonly")
//					
//					layui.form.render('select');
//				}
//			},
//			error:function(data){
//				alert('request error');
//			}
//		})
//	}
//	// 类别修改
//	function editSetFn(obj){
//		// 第一步： 请求弹框
//		$.ajax({
//			url:orderServer + '/orderexecute/bomp/order/editDocumentList.html',
//			success:function(data){
//				// 自带弹框
//				layer.open({
//					type:1,
//					content: data,
//					title: $.i18n("orderexecute.code.00181"),
//					area: '450px',
//					success:function(){
//							
//						// 第二部：回显数据->编辑
//						$('input[name="documentId"]').val(obj.data.documentId);
//						$('#edit-tradeCategory').val(obj.data.tradeCategory);
//						$('input[name="edit-documentTypeDesc"]').val(obj.data.documentTypeDesc);
//						$('input[name="edit-documentType"]').val(obj.data.documentType);
//						
//						$('input[name="oldTradeCategory"]').val(obj.data.tradeCategory);
//						$('input[name="oldDocumentTypeDesc"]').val(obj.data.documentTypeDesc);
//						$('input[name="oldDocumentType"]').val(obj.data.documentType);
//						
//						layui.form.render('select');
//					}
//				});
//			},
//			error:function(data){
//				alert('request error');
//			}
//		})
//	}
//	
//	// 值集修改
//	function editItemFn(obj){
//		// 第一步： 请求弹框
//		$.ajax({
//			url:orderServer + '/orderexecute/bomp/order/editDocumentListItem.html',
//			success:function(data){
//				// 自带弹框
//				layer.open({
//					type:1,
//					content: data,
//					title: $.i18n("orderexecute.code.00181"),
//					area: '450px'
//				});
//			
//					// 第二部：回显数据->编辑
//					$('input[name="documentId"]').val(obj.data.documentId);
//					$('input[name="fileName"]').val(obj.data.fileName);
//					$('input[name="fileCode"]').val(obj.data.fileCode);
//					$('input[name="fromUser"]').val(obj.data.fromUser);
//					$('input[name="toUser"]').val(obj.data.toUser);
//			},
//			error:function(data){
//				alert('request error');
//			}
//		})
//	}
//	
//	// 值集显示
//	function showItemFn(obj){
//		// 第一步： 请求弹框
//		$.ajax({
//			url:orderServer + '/orderexecute/bomp/order/documentListSet.html',
//			success:function(data){
//					
//				// 自带弹框
//				layer.open({
//					type:1
//					,content: data
//					,title: $.i18n("orderexecute.code.00182")
//					,area: ['70%', '70%']
//					,offset: [160,300]
//				});
//				
//				//subListReload
//				table.render({
//					id:'documentListReload'
//				   ,elem: '#test1'
//				   ,url:orderServer + '/order/documentListById.json'
//				   ,where:{documentType:obj.data.documentType}
//				   ,method:'POST'
//				   ,cols: [[
//				       {field:'fileName',title:$.i18n("orderexecute.code.00183")}
//				      ,{field:'fileCode',title:$.i18n("orderexecute.code.00184")}
//				      ,{field:'fromUser', templet: '#editableTp2',title:$.i18n("orderexecute.code.00185")}
//				      ,{field:'toUser', templet: '#editableTp3',title:$.i18n("orderexecute.code.00186")}
//				      ,{fixed: 'right', width:190, align:'center', toolbar: '#barDemo2'}
//				    ]]
//				});
//				// 添加值集
//				form.render();
//				$('.layui-table-body').css("overflow","hidden");
//			},
//				error:function(data){
//				alert('request error');
//			}
//		})
//	}
//
//	// 监听提交  新增
//	form.on('submit(demo1)', function(data){
//		var data;
//		var addtradeCategory;
//			if($('#add_tradeCategoryId').val()=="原油"){
//				addtradeCategory = "1";
//			}else if($('#add_tradeCategoryId').val()=="成品油"){
//				addtradeCategory = "2";
//			}else{
//				addtradeCategory = "3";
//			}
//		if($('input[name="documentType"]').attr("readonly") == 'readonly'){
//
//			data={
//					
//					'tradeCategory':addtradeCategory,
//					'documentTypeDesc':$('input[name="documentTypeDesc"]').val(),
//					'documentType':$('input[name="documentType"]').val(),
//					'fileName':$('input[name="fileName"]').val(),
//					'fileCode':$('input[name="fileCode"]').val(),
//					'fromUser':$('input[name="fromUser"]').val(),
//					'toUser':$('input[name="toUser"]').val(),
//					'aliveFlag':'1'
//					
//				}
//		} else {
//			data = {
//
//					'tradeCategory':addtradeCategory,
//					'documentTypeDesc':$('input[name="documentTypeDesc"]').val(),
//					'documentType':$('input[name="documentType"]').val(),
//					'fileName':$('input[name="fileName"]').val(),
//					'fileCode':$('input[name="fileCode"]').val(),
//					'fromUser':$('input[name="fromUser"]').val(),
//					'toUser':$('input[name="toUser"]').val(),
//					'aliveFlag':'0'
//			}
//		}
//		// 提交ajax
//		$.ajaxFn({
//			url:orderServer + '/order/addDocumentList.json',
//			data:data,
//			success:function(data){
//				
//				if (data.status != 0) {
//					
//					if($('input[name="documentType"]').attr("readonly") != 'readonly'){
//						// 重新载入列表
//						table.reload('listReload', {
//							page: {
//								curr: 1 // 重新从第 1 页开始
//							}  
//							,where: {
//								tradeCategory: $('input[name="tradeCategory"]').val(),
//								documentTypeDesc: $('input[name="documentTypeDesc"]').val()
//							}
//						});
//						layer.closeAll();
//					}
//
//					layer.open({
//						content: data.message
//					});
//					
//				} else {
//					// 重新载入列表
//					tabReload(1);
//					// 关闭弹框
//					layer.closeAll();
//				}
//			}
//		});
//		return false;
//	});
//	
//	// 监听提交  修改
//	form.on('submit(demo2)', function(data){
//		// 提交ajax
//		$.ajaxFn({
//			url:orderServer + '/order/editDocumentList.json',
//			data:{
//				
//				'documentId':$('input[name="documentId"]').val(),
//				'tradeCategory':$('#edit-tradeCategory').val(),
//				'documentTypeDesc':$('input[name="edit-documentTypeDesc"]').val(),
//				'documentType':$('input[name="edit-documentType"]').val(),
//				
//				'oldTradeCategory':$('input[name="oldTradeCategory"]').val(),
//				'oldDocumentTypeDesc':$('input[name="oldDocumentTypeDesc"]').val(),
//				'oldDocumentType':$('input[name="oldDocumentType"]').val()
//				
//			},
//			success:function(data){
//				// 关闭弹框
//				layer.closeAll();
//				if (data.status != 0) {
//					layer.open({
//						content: data.message
//					});
//				} else {
//					// 重新载入列表
//					tabReload(1);
//				}
//			}
//		});
//		return false;
//	});
//	
//	// 监听提交  修改子集
//	form.on('submit(demo3)', function(data){
//		
//		// 提交ajax
//		$.ajaxFn({
//			url:orderServer + '/order/editDocumentListItem.json',
//			data:{	
//				/*'documentId':oldData.documentId,*/
//				'documentId':$('input[name="documentId"]').val(),
//				'fileName':$('input[name="fileName"]').val(),
//				'fileCode':$('input[name="fileCode"]').val(),
//				'fromUser':$('input[name="fromUser"]').val(),
//				'toUser':$('input[name="toUser"]').val()
//			},
//			success:function(data){
//				// 关闭弹框
//				layer.close(layer.index);
//				if (data.status != 0) {
//					layer.open({
//						content: data.message
//					});
//				} else {
//					// 重新载入列表
//					subTabReload();
//				}
//			}
//		});
//		return false;
//	});
//});		
//
//	
// 