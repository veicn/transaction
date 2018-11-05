layui.use(['layer', 'table','form'], function(){
	var table = layui.table,
	form = layui.form;
	
	// 配置参数
	var tableOptions = {
		id:'listReload',
		url: orderServer+'/interface_output/list.json', //请求地址
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
		$('.layui-table-body').css("overflow","hidden");
 	}
	
	$("body").keydown(function() {
	    if (event.keyCode == "13") {//keyCode=13是回车键
	        $('#btn').click();
	    }
	});
	
	//查询
	$('#btn').on('click', function(){
		table.reload('listReload', {
			page: {
				curr: 1 //重新从第 1 页开始
			}  
			,where: {
				sysName: $('#sysName').val().trim(),
				inputInfo: $('#inputInfo').val().trim(),
				status: $('#status').val().trim()
				
			}
		});
		$('.layui-table-page').attr('align','right');
	});
	
	//监听工具条
	table.on('tool(demo)', function(obj){
		var oldData = obj.data;  // 一行的数据
		// 删除
		if(obj.event === 'edit'){
			layer.confirm($.i18n("orderexecute.code.00132"), {icon: 3, title:$.i18n("orderexecute.code.00133"),btn:[$.i18n("orderexecute.code.00197"),$.i18n("orderexecute.code.00198")]}, function(index){
				//obj.del();
				// 提交ajax
				$.ajax({
					type: "POST", 
					url:orderServer+'/interface/interfaceOutputEdit.json?outputId='+ oldData.outputId,
					beforeSend: function(){ 
						layer.alert($.i18n("orderexecute.code.00134"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
						return true; 
					},
					success:function(data){
						if (data.status == 0) {
							// 重新载入列表
							tabReload(1);
							layer.alert($.i18n(data.code,data.params),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")}, function(index) {
								// 关闭弹框
								layer.closeAll();
							});
						} else {
							tabReload(1);
							layer.close(index);
							layer.alert("error:" + $.i18n(data.code,data.params),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")}, function(index) {
								table.reload();
								// 关闭弹框
								layer.closeAll();
							});
						}
					},
					error: function(data) {
						tabReload(1);
						layer.close(index);
						layer.alert("request error",{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
			        }
				})    
			});
		}
	});
}); 