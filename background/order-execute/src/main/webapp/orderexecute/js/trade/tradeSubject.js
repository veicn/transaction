layui.use(['layer', 'table','form'], function(){
	var table = layui.table,
	 form = layui.form;
		form.render();
	// 配置参数
	var tableOptions = {
		id:'listReload',
		url: orderServer+'/trade/tradeSubject.json', //请求地址
		method:'POST',
		page: true
	};
  
	// 初始化
	table.init('demo', tableOptions);
	$('.layui-table-page').attr('align','right');
	$('.layui-table-body').css("overflow","hidden");
	
	//重新载入表格
	function tabReload(num){
		table.reload('listReload', {
			page: {
				curr: num //重新从第 1 页开始
			}
		});
		$('.layui-table-page').attr('align','right');
		$('.layui-table-body').css("overflow","hidden");
 	}

	//查询
	$('#btn').on('click', function(){
		table.reload('listReload', {
			page: {
				curr: 1 //重新从第 1 页开始
			}  
			,where: {
				productType: $('#productTypeId').val(),
				subjectName: $('#subjectName').val()
			}
		});
		$('.layui-table-page').attr('align','right');
		$('.layui-table-body').css("overflow","hidden");
	});
  
	$("body").keydown(function() {
	    if (event.keyCode == "13") {//keyCode=13是回车键
	        $('#btn').click();
	    }
	});
	
	//新增
	$('#add').on('click', function(){
		addEditFn({
			'echo':false // 新增不需要回显
		});
	});
	
	//监听工具条
	table.on('tool(demo)', function(obj){
		var oldData = obj.data;  // 一行的数据
		// 删除
		if(obj.event === 'del'){
			layer.confirm($.i18n("orderexecute.code.00138"),{title:$.i18n("orderexecute.code.00148"),btn:[$.i18n("orderexecute.code.00197"),$.i18n("orderexecute.code.00198")]}, function(index){
				obj.del();

				// 数据做处理   以后做公共的  
				var obj2={
					'tradeSubjectId':oldData.tradeSubjectId
				};
				obj2=JSON.stringify(obj2);

				// 提交ajax
				$.ajaxFn({
					url:orderServer+'/trade_subject/del.json',
					data:{
						'tradeSubjectId':oldData.tradeSubjectId
					},
					success:function(data){
						//重新载入列表
						tabReload(1);
						// 关闭弹框
						layer.close(index);
					}
				})
			});
		} else if(obj.event === 'edit'){
			addEditFn({
				'data':oldData,
				'echo':true
			});
		}
	});

	// 新增 与 编辑
	function addEditFn(obj){
		// 第一步： 请求弹框
		$.ajax({
			url:orderServer+'/orderexecute/bomp/trade/addTradeSubject.html',
			success:function(data){
				
				//自带弹框
				layer.open({
					type:1,
					area: '700px;',
					content: data
				});

				// 第二部：回显数据->编辑
				if(obj.echo){
					
					$('input[name="tradeSubjectId"]').val(obj.data.tradeSubjectId);
					$('input[name="edit_subjectName"]').val(obj.data.subjectName);
					$('input[name="edit_subjectCode"]').val(obj.data.subjectCode);
					$('#edit_productTypeId').val(obj.data.productType);

					form.render('select', 'refreshSelect'); 
				}
			},
			error:function(data){
				alert('request error');
			}
		})
	}
	
	//监听提交
	form.on('submit(demo1)', function(data){

		var url,data;
		
		if ($('input[name="tradeSubjectId"]').val()) {
			url = orderServer+'/trade_subject/edit.json';
			data = {
					'tradeSubjectId':$('input[name="tradeSubjectId"]').val(),
					
					'subjectName':$('input[name="edit_subjectName"]').val(),
					'subjectCode':$('input[name="edit_subjectCode"]').val(),
					'productType':$('#edit_productTypeId').val()
				
				};
		} else {
			url = orderServer+'/trade_subject/add.json';
			data = {
					'subjectName':$('input[name="edit_subjectName"]').val(),
					'subjectCode':$('input[name="edit_subjectCode"]').val(),
					'productType':$('#edit_productTypeId').val()
					};
		}
	
		// 提交ajax
		$.ajaxFn({
			url:url,
			data:data,
			success:function(data){
				//重新载入列表
				tabReload(1);
				// 关闭弹框
				layer.closeAll();
			}
		})
		return false;
	});
}); 