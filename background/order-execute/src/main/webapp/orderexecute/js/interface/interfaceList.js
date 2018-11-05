layui.use(['layer', 'table','form'], function(){
	var table = layui.table,
	form = layui.form;

	// 配置参数
	var tableOptions = {
		id:'listReload',
		url: orderServer+'/interface_list/list.json', //请求地址
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
				businessType: $('#businessType').val().trim()
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
					'interfaceId':oldData.interfaceId
				};
				obj2=JSON.stringify(obj2);
				
				// 提交ajax
				$.ajaxFn({
					url:orderServer+'/interface_list/del.json',
					data:{
						'interfaceId':oldData.interfaceId
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
			url:orderServer+'/orderexecute/bomp/interface/addInterfaceList.html',
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
							//console.log(res[i].sysName);
							$("#sysName2").append("<option value='"+ data.datas[key].sysName +"'>"+ data.datas[key].sysName +"</option>"); 
						}
						
						// 第二部：回显数据->编辑
						if(obj.echo){
							
							$('#addInterfaceList input[name="interfaceId"]').val(obj.data.interfaceId);
							/*$('#addInterfaceList input[name="sysName"]').val(obj.data.sysName);*/
							$("#sysName2").append("<option selected='selected' value='"+ obj.data.sysName +"'>"+ obj.data.sysName +"</option>");
							$('#addInterfaceList input[name="memRole"]').val(obj.data.memRole);
							$('#addInterfaceList input[name="interfaceType"]').val(obj.data.interfaceType);
							$('#addInterfaceList input[name="businessType"]').val(obj.data.businessType);
							$('#addInterfaceList input[name="interfaceCode"]').val(obj.data.interfaceCode);
							$('#addInterfaceList input[name="interfaceUrl"]').val(obj.data.interfaceUrl);
							$('#addInterfaceList input[name="interfaceDesc"]').val(obj.data.interfaceDesc);
							$('#addInterfaceList input[name="inputDesc"]').val(obj.data.inputDesc);
							$('#addInterfaceList input[name="outputDesc"]').val(obj.data.outputDesc);
							$('#addInterfaceList input[name="interfaceCode"]').attr("disabled","disabled");
							$('#addInterfaceList input[name="businessType"]').attr("disabled","disabled");
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
	form.on('submit(demo1)', function(data){

		var url,data;
		
		if ($('#addInterfaceList input[name="interfaceId"]').val()) {
			url = orderServer + '/interface_list/edit.json';
			data = {
					'interfaceId':$('#addInterfaceList input[name="interfaceId"]').val(),
					/*'sysName':$('#addInterfaceList input[name="sysName"]').val(),*/
					'sysName':$('#addInterfaceList select[name="sysName"]').find("option:selected").text(),
					'memRole':$('#addInterfaceList input[name="memRole"]').val(),
					'interfaceType':$('#addInterfaceList input[name="interfaceType"]').val(),
					'businessType':$('#addInterfaceList input[name="businessType"]').val(),
					'interfaceCode':$('#addInterfaceList input[name="interfaceCode"]').val(),
					'interfaceUrl':$('#addInterfaceList input[name="interfaceUrl"]').val(),
					'interfaceDesc':$('#addInterfaceList input[name="interfaceDesc"]').val(),
					'inputDesc':$('#addInterfaceList input[name="inputDesc"]').val(),
					'outputDesc':$('#addInterfaceList input[name="outputDesc"]').val()
					};
		} else {
			url = orderServer + '/interface_list/add.json';
			data = {	
					/*'sysName':$('#addInterfaceList input[name="sysName"]').val(),*/
					'sysName':$('#addInterfaceList select[name="sysName"]').find("option:selected").text(),
					'memRole':$('#addInterfaceList input[name="memRole"]').val(),
					'interfaceType':$('#addInterfaceList input[name="interfaceType"]').val(),
					'businessType':$('#addInterfaceList input[name="businessType"]').val(),
					'interfaceCode':$('#addInterfaceList input[name="interfaceCode"]').val(),
					'interfaceUrl':$('#addInterfaceList input[name="interfaceUrl"]').val(),
					'interfaceDesc':$('#addInterfaceList input[name="interfaceDesc"]').val(),
					'inputDesc':$('#addInterfaceList input[name="inputDesc"]').val(),
					'outputDesc':$('#addInterfaceList input[name="outputDesc"]').val()
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
							sysName: $('#addInterfaceList input[name="sysName"]').val()
						}
					});
					$('.layui-table-page').attr('align','right');
					$('#sysName').val($('#addInterfaceList input[name="sysName"]').val());
					layer.alert($.i18n(data.code,data.params),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")}, function(index) {
						// 关闭弹框
						layer.closeAll();
					});
				} else {
					layer.alert("error",{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
				}
			},
			error: function(data) {
				layer.close(index);
				layer.alert("error:" + data.message,{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
	        }
			
		})
		return false;
	});
	
	form.verify({
		length16: function(value){
			if(value.length > 16){
				return '最多可以输入16个字符';
			}
	    },
	    length32: function(value){
			if(value.length > 32){
				return '最多可以输入32个字符';
			}
	    },
	    length64: function(value){
			if(value.length > 64){
				return '最多可以输入64个字符';
			}
	    },
	    length128: function(value){
			if(value.length > 128){
				return '最多可以输入128个字符';
			}
	    }
	});
}); 