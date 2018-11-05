layui.use(['layer', 'table','form'], function(){
	var table = layui.table,
	form = layui.form;

	// 配置参数
	var tableOptions = {
		id:'listReload',
		url: orderServer+'/interface_member/list.json', //请求地址
		method:'POST',
		page: true
	};
  
	// 初始化
	table.init('demo', tableOptions);
	$('.layui-table-page').attr('align','right');
	
	//重新载入表格
	function tabReload(num){
		table.reload('listReload', {
			page: {
				curr: num //重新从第 1 页开始
			}
		});
		$('.layui-table-page').attr('align','right');
	}

	//查询
	$('.demoTable #btn').on('click', function(){
		table.reload('listReload', {
			page: {
				curr: 1 //重新从第 1 页开始
			}
			,where: {
				sysName: $('#sysName').val(),
				loginName: $('#loginName').val()
			}
		});
		$('.layui-table-page').attr('align','right');
	});

	$('.demoTable #add').on('click', function(){
		addEditFn({
			'echo':false // 新增不需要回显
		});
	});

	//监听工具条
	table.on('tool(demo)', function(obj){
		var oldData = obj.data;  // 一行的数据
		// 删除
		if(obj.event === 'del'){
			layer.confirm($.i18n("orderexecute.code.00138"),{title:$.i18n("orderexecute.code.00133"),btn:[$.i18n("orderexecute.code.00197"),$.i18n("orderexecute.code.00198")]}, function(index){
				obj.del();

				// 数据做处理   以后做公共的  
				var obj2={
					'interfaceMemberId':oldData.interfaceMemberId
				};
				obj2=JSON.stringify(obj2);

				// 提交ajax
				$.ajaxFn({
					url:orderServer+'/interface_member/del.json',
					data:{
						'interfaceMemberId':oldData.interfaceMemberId
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
			url:orderServer+'/orderexecute/bomp/interface/addInterfaceMember.html',
			success:function(data){
				//自带弹框
				layer.open({
					type:1,
					content: data
				});

				// 第二部：回显数据->编辑
				if(obj.echo){
					$('#addInterfaceMember input[name="interfaceMemberId"]').val(obj.data.interfaceMemberId);
					$('#addInterfaceMember input[name="sysName"]').val(obj.data.sysName);
					$('#addInterfaceMember input[name="loginName"]').val(obj.data.loginName);
					$('#addInterfaceMember input[name="password"]').val(obj.data.password);
					$('#addInterfaceMember input[name="memberRole"]').val(obj.data.memberRole);
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
		
		if ($('#addInterfaceMember input[name="interfaceMemberId"]').val()) {
			url = orderServer+'/interface_member/edit.json';
			data = {
					'interfaceMemberId':$('#addInterfaceMember input[name="interfaceMemberId"]').val(),
					'sysName':$('#addInterfaceMember input[name="sysName"]').val(),
					'loginName':$('#addInterfaceMember input[name="loginName"]').val(),
					'password':$('#addInterfaceMember input[name="password"]').val(),
					'memberRole':$('#addInterfaceMember input[name="memberRole"]').val()
					};
		} else {
			url = orderServer+'/interface_member/add.json';
			data = {
					'sysName':$('#addInterfaceMember input[name="sysName"]').val(),
					'loginName':$('#addInterfaceMember input[name="loginName"]').val(),
					'password':$('#addInterfaceMember input[name="password"]').val(),
					'memberRole':$('#addInterfaceMember input[name="memberRole"]').val()
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