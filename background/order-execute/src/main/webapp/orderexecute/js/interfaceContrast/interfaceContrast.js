layui.use(['layer', 'table','form'], function(){
	var table = layui.table,
	form = layui.form;
	
	// 配置参数
	var tableOptions = {
		id:'listReload',
		url: orderServer+'/interface_contrast/list.json', //请求地址
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
				sysName: $('#sysName').val().trim(),
				paraCode: $('#paraCode').val().trim(),
				otherCode: $('#otherCode').val().trim()
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
			layer.confirm($.i18n("orderexecute.code.00135"), {icon: 3, title:$.i18n("orderexecute.code.00133"),btn:[$.i18n("orderexecute.code.00197"),$.i18n("orderexecute.code.00198")]}, function(index){
				obj.del();
				// 数据做处理   以后做公共的  
				var obj2={
					'contrastId':oldData.contrastId
				};
				obj2=JSON.stringify(obj2);
				
				// 提交ajax
				$.ajaxFn({
					url:orderServer+'/interface_contrast/del.json',
					data:{
						'contrastId': oldData.contrastId
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
			'echo':true,
			'data':oldData
		});
	}
	});

	// 新增 与 编辑
	function addEditFn(obj){
		// 第一步： 请求弹框
		$.ajax({
			url:orderServer+'/orderexecute/bomp/interfaceContrast/interfaceContrast.html',
			success:function(data1){
				$.ajax({
					url:orderServer+'/interface_system/querySysName.json',
					success:function(data){
						//自带弹框 
						layer.open({
							type:1,
							area: '700px;',
							content: data1
						});	
						
						for(var key in data.datas){
							$("#sysName2").append("<option value='"+ data.datas[key].sysName +"'>"+ data.datas[key].sysName +"</option>"); 
						}
						
						// 第二部：回显数据->编辑
						if(obj.echo){
							$('#editForm input[name="contrastId"]').val(obj.data.contrastId);
							/*$('#editForm input[name="sysName"]').val(obj.data.sysName);*/
							$("#sysName2").append("<option selected='selected' value='"+ obj.data.sysName +"'>"+ obj.data.sysName +"</option>");
							$('#editForm input[name="paraType"]').val(obj.data.paraType);
							$('#editForm input[name="paraCode"]').val(obj.data.paraCode);
							$('#editForm input[name="paraDesc"]').val(obj.data.paraDesc);
							$('#editForm input[name="otherCode"]').val(obj.data.otherCode);
							$("#sysName2").attr("disabled","disabled");
						}
						form.render();
					},
					error:function(data2){
						alert('request error');
					}
				})		
				
			},
			error:function(data){
				alert('request error');
			}
		})
	}

	//监听提交
	form.on('submit(btn_edit)', function(data){

		var url,data;
		console.log("0000");
		if ($('#editForm input[name="contrastId"]').val() != null && $('#editForm input[name="contrastId"]').val() != "") {
			url = orderServer + '/interface_contrast/edit.json';
			data = {
					'contrastId':$('#editForm input[name="contrastId"]').val(),
					/*'sysName':$('#editForm input[name="sysName"]').val(),*/
					'sysName':$('#editForm select[name="sysName"]').find("option:selected").text(),
					'paraType':$('#editForm input[name="paraType"]').val(),
					'paraCode':$('#editForm input[name="paraCode"]').val(),
					'paraDesc':$('#editForm input[name="paraDesc"]').val(),
					'otherCode':$('#editForm input[name="otherCode"]').val()
					};
		} else {
			url = orderServer + '/interface_contrast/add.json';
			data = {
					/*'sysName':$('#editForm input[name="sysName"]').val(),*/
					'sysName':$('#editForm select[name="sysName"]').find("option:selected").text(),
					'paraType':$('#editForm input[name="paraType"]').val(),
					'paraCode':$('#editForm input[name="paraCode"]').val(),
					'paraDesc':$('#editForm input[name="paraDesc"]').val(),
					'otherCode':$('#editForm input[name="otherCode"]').val()
					};
		}
	
		// 提交ajax
		$.ajaxFn({
			url:url,
			data:data,
			success:function(data){
				if (data.status != 9999) {
					// 重新载入列表
					table.reload('listReload', {
						page: {
							curr: 1 // 重新从第 1 页开始
						}  
						,where: {
							sysName: $('#editForm input[name="sysName"]').val()
						}
					});
					$('.layui-table-page').attr('align','right');
					$('#sysName').val($('#editForm input[name="sysName"]').val());
					layer.alert(data.message,{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")}, function(index) {
						// 关闭弹框
						layer.closeAll();
					});
				} else {
					layer.alert("error",{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
				}
			},
			error: function(data) {
				layer.close(index);
				layer.alert("request error",{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
	        }
		})
		return false;
	});
	
	form.verify({
		length8: function(value){
			if(value.length > 8){
				return $.i18n("orderexecute.code.00137","8");
			}
	    },
	    length16: function(value){
			if(value.length > 16){
				return $.i18n("orderexecute.code.00137","16");
			}
	    },
	    length32: function(value){
			if(value.length > 32){
				return $.i18n("orderexecute.code.00137","32");
			}
	    },
	    length64: function(value){
			if(value.length > 64){
				return $.i18n("orderexecute.code.00137","64");
			}
	    },
	    length100: function(value){
			if(value.length > 100){
				return $.i18n("orderexecute.code.00137","100");
			}
	    }
		
	});
}); 