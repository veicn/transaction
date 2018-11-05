layui.use(['layer', 'table','form'], function(){
	var table = layui.table,
	form = layui.form;

	// 配置参数
	var tableOptions = {
		id:'listReload',
		url: orderServer+'/interface_system/list.json', //请求地址
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
				sysDec: $('#sysDec').val().trim()
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
					'sysId':oldData.sysId
				};
				obj2=JSON.stringify(obj2);
				
				// 提交ajax
				$.ajaxFn({
					url:orderServer+'/interface_system/del.json',
					data:{
						'sysId':oldData.sysId
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
			url:orderServer+'/orderexecute/bomp/interface/addInterfaceSystem.html',
			success:function(data1){
				$.ajax({
					url:orderServer+'/interface_system/queryMemberType.json',
					success:function(data){
						//自带弹框
						layer.open({
							type:1,
							area: '700px;',
							content: data1
						});
						
						/*var res = [];
						$.ajax({
							url:orderServer+'/interface_system/querySysName.json',
							async:false,
							success:function(data2){
								res=data2.datas;
							},
							error:function(data2){
								alert('失败');
							}
						})
						
						for(var i in res){
							console.log(res[i].sysName);
							$("#sysName2").append("<option value='"+ res[i].sysName +"'>"+ res[i].sysName +"</option>"); 
						}*/
						
						
						for(var key in data.datas){
							var temp = data.datas[key];
							$("#memberType").append("<option value='"+ temp.code +"'>"+ temp.value +"</option>");  
						}
						
						// 第二部：回显数据->编辑
						if(obj.echo){	
					
							$('#addInterfaceSystem input[name="sysId"]').val(obj.data.sysId);
							$('#addInterfaceSystem input[name="sysName"]').val(obj.data.sysName);
							/*$("#sysName2").append("<option selected='selected' value='"+ obj.data.sysName +"'>"+ obj.data.sysName +"</option>");*/
							$('#addInterfaceSystem input[name="sysDec"]').val(obj.data.sysDec);
							$("#memberType").append("<option selected='selected'>"+ obj.data.memberType +"</option>");
							$("#memberName").append("<option selected='selected' value='"+ obj.data.memberId +"'>"+ obj.data.memberName +"</option>");
							$('#addInterfaceSystem input[name="loginName"]').val(obj.data.loginName);
							$('#addInterfaceSystem input[name="password"]').val(obj.data.password);
							$('#addInterfaceSystem input[name="sysUrl"]').val(obj.data.sysUrl);
							$('#addInterfaceSystem input[name="sysName"]').attr('readonly','readonly');
						}
						form.render();
					},
					error:function(data){
						alert('request error');
					}
				})
				form.on('select(typeCode)', function () {
					//console.log(".........." );
					var memberType = $("#memberType").val(); 
					$.ajax({
						url:orderServer+'/interface_system/queryMemberInfoByType.json',
						type:"POST",
						data:{
							memberType: memberType
						},
						success:function(data){
							//清空
							$("#memberName").empty();
							//给会员赋值					
							for(var key in data.datas){
								$("#memberName").append("<option value='"+ data.datas[key].memberId +"'>"+ data.datas[key].memberName +"</option>");//企业名称 
							}
							form.render();
						},
						error:function(data){
							alert('request error');
						}
					
					});
				});
			},
			error:function(data1){
				alert('request error');
			}
		})
	}
	
	//监听提交
	form.on('submit(demo1)', function(data){
		var url,data;
		
		if ($('#addInterfaceSystem input[name="sysId"]').val()) {
			url = orderServer + '/interface_system/edit.json';
			data = {
					'sysId':$('#addInterfaceSystem input[name="sysId"]').val(),
					'sysName':$('#addInterfaceSystem input[name="sysName"]').val(),
					/*'sysName':$('#addInterfaceSystem select[name="sysName"]').find("option:selected").text(),*/
					'sysDec':$('#addInterfaceSystem input[name="sysDec"]').val(),
					'memberId':$('#addInterfaceSystem select[name="memberName"]').val(),
					'memberName':$('#addInterfaceSystem select[name="memberName"]').find("option:selected").text(),
					'memberType':$('#addInterfaceSystem select[name="memberType"]').find("option:selected").text(),
					'loginName':$('#addInterfaceSystem input[name="loginName"]').val(),
					'password':$('#addInterfaceSystem input[name="password"]').val(),
					'sysUrl':$('#addInterfaceSystem input[name="sysUrl"]').val()
					};
		} else {
			url = orderServer + '/interface_system/add.json';
			data = {
					'sysName':$('#addInterfaceSystem input[name="sysName"]').val(),
					/*'sysName':$('#addInterfaceSystem select[name="sysName"]').find("option:selected").text(),*/
					'sysDec':$('#addInterfaceSystem input[name="sysDec"]').val(),
					'memberId':$('#addInterfaceSystem select[name="memberName"]').val(),
					'memberName':$('#addInterfaceSystem select[name="memberName"]').find("option:selected").text(),
					'memberType':$('#addInterfaceSystem select[name="memberType"]').find("option:selected").text(),
					'loginName':$('#addInterfaceSystem input[name="loginName"]').val(),
					'password':$('#addInterfaceSystem input[name="password"]').val(),
					'sysUrl':$('#addInterfaceSystem input[name="sysUrl"]').val()
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
							sysName: $('#addInterfaceSystem input[name="sysName"]').val()
						}
					});
					$('#sysName').val($('#addInterfaceSystem input[name="sysName"]').val());
					$('.layui-table-page').attr('align','right');
					layer.alert($.i18n(data.code,data.params),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")}, function(index) {
						// 关闭弹框
						layer.closeAll();
					});
					/*layer.open({
						content: data.message
					});*/
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
	    length20: function(value){
			if(value.length > 20){
				return $.i18n("orderexecute.code.00137","20");
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


