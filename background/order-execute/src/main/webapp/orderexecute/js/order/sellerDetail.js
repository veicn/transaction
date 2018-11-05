layui.use(['layer','laydate','form'], function(){
		form = layui.form;
		
		$('#resetContract').on('click', function(){
			var data = $(this).attr("data");
			data = eval("("+data+")");
			var orderId = data.orderId;
			
			layer.confirm($.i18n("orderexecute.code.00147"), {title:$.i18n("orderexecute.code.00148"),btn:[$.i18n("orderexecute.code.00197"),$.i18n("orderexecute.code.00198")]}, function(index){
				$.ajax({
		      		url:orderServer+"/triggerContractr/resetContract.json",
		      		data:{orderId:orderId},
		      		type:"post",
		      		success:function(result){
		      			if(result.status != 0){
		      				if(result.message == null){
		      					layer.alert($.i18n("orderexecute.code.00005"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
		      				}else{
		      					layer.alert($.i18n(result.code,result.params),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
		      				}
		      			}else{
		      				layer.alert($.i18n("orderexecute.code.00149"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")}, function(index){
		      					layer.closeAll();
		      					location.reload();
		      				});
		      			}
		      		},
		      		error:function(){
		      			layer.alert("request error",{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
		      		}
		      	})
			})
		})
		
		/////////////////////////////////////////////
		//船信息修改 
		$('.editship').on('click',function(){
			var vesselName = $(this).attr("vesselName");
			var vef = $(this).attr("vef");
			var obq = $(this).attr("obq");
			var agentUuid = $(this).attr("agentUuid");
			var obj = {};
			$.ajax({
				url:orderServer+'/bomp/ship/updateShip.htm',
				success:function(data1){
					$.ajax({
						url:shipServer+'/sysShip/findSysShipNameList.json',
						type:'post',
						data: JSON.stringify(obj),
						dataType: "json",
						contentType:"application/json",
						xhrFields: {
		                    withCredentials: true
			            },
						success:function(data2){
							//自带弹框 
							layer.open({
								title: $.i18n("orderexecute.code.00195"),
							      type: 1,
							      btn: [$.i18n("orderexecute.code.00153"), $.i18n("orderexecute.code.00154")],
								  btnAlign: 'c',
								  content: data1,
								  yes: function(){
									editship_yes_callback();
								  },
							      area: ['500px', '500px'],
							      shadeClose: true, 
							});
							var select = document.getElementById("name");
							for(var key in data2.datas){
								var opt = document.createElement("OPTION");
								opt.value = data2.datas[key].name;
								opt.text  = data2.datas[key].name;
								
								if(data2.datas[key].name==vesselName){
									opt.selected ="selected";   
								}
								
								select.options.add(opt);
								
							}

							//回显
							$("#vef").val(vef);
							$("#obq").val(obq);
							
							if(agentUuid != null && agentUuid != ''){
								$("#name").attr("disabled","disabled");
								$("#name").removeAttr("lay-search");
							}
							
							form.render();
							$('dd').css({"text-align":"left"});
						},
						error:function(data2){
							alert('request error');
						}
					})		
					
				},
				error:function(data1){
					alert('request error');
				}
			})
		})
		//修改船信息提交回调
		function editship_yes_callback(){
			
			var orderShipId = $("#orderShipId").val();
			var orderId = $("#orderId").val();
			var name = $("#name").find("option:selected").val();
			var vef = Number(delNumFormat($("#vef").val()));
			var obq = Number(delNumFormat($("#obq").val()));
			if(name == "") {
				layer.alert($.i18n("orderexecute.code.00151"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
				return;
			}
			 $.ajax({
	      		url:orderServer+"/ship/updateShip.json",
	      		data:{name:name, vef:vef, obq:obq, orderShipId: orderShipId, orderId: orderId},
	      		type:"post",
	      		success:function(result){
	      			if(result.status != 0){
	      				if(result.message == null){
	      					layer.alert($.i18n("orderexecute.code.00005"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
	      				}else{
	      					layer.alert($.i18n(result.code,result.params),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
	      				}
	      			}else{
	      				layer.alert($.i18n("orderexecute.code.00149"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")}, function(index){
	      					layer.closeAll();
	      					location.reload();
	      				});
	      			}
	      		},
	      		error:function(){
	      			layer.alert("request error",{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
	      		}
	      	}) 
		}
		
		///////////////////////////////////////////////////////
		var laydate = layui.laydate;
		
		//修改装港信息
		$('.editLoading').on('click',function(){
			//alert("修改装港信息");
			var blStatus = $(this).attr("blStatus");
			//var blStatus = "1";
			var agentUuid = $(this).attr("agentUuid");
			var orderNo = $(this).attr("orderNo");
			var loadingUuid = $(this).attr("loadingUuid");
			var data = $(this).attr("data");
			data = eval("("+data+")");
			//console.log(data);
			$.ajax({
				url:orderServer+'/bomp/ship/updateLoading.htm',
				success:function(data1){
					 $.ajax({
						url:orderServer+'/ship/queryLoadingType.json',
						async:true,
						success:function(data2){ 
							//自带弹框 
							layer.open({
								type:1,
								title: $.i18n("orderexecute.code.00152"),
								area: ['1000px', '480px'],
								content: data1,
								btn: [$.i18n("orderexecute.code.00212"), $.i18n("orderexecute.code.00154")],
								btnAlign: 'c',
								yes: function(){
									editLoading_yes_callback();
								}
							});	
							var select = document.getElementById("loadPort");
							for(var key in data2.datas){
								var opt = document.createElement("OPTION");
								opt.value = data2.datas[key].code;
								opt.text  = data2.datas[key].value;
								
								if(data2.datas[key].code==data.loadPort
										||data2.datas[key].value==data.loadPort){
									opt.selected ="selected";   
								}
								
								select.options.add(opt);
								
							}
							
							if(agentUuid != null && agentUuid != ''){
								$("#loadPort").attr("disabled","disabled");
								$("#loadPort").removeAttr("lay-search");
							}
							$('#blDate').val(data.blDate);
							$('#acStart').val(data.acStart);
							$('#acFinish').val(data.acFinish);
							$('#blHairBarrel').val(data.blHairBarrel);
							$('#blNightstool').val(data.blNightstool);
							$('#blHairTonnage').val(data.blHairTonnage);
							$('#blNetTonnage').val(data.blNetTonnage);
							$('#shGrossCubicMeter').val(data.shGrossCubicMeter);
							$('#shNetCubicMeter').val(data.shNetCubicMeter);
							$('#shDensity').val(data.shDensity);
							$('#api').val(data.api);
							$('#waterImpCon').val(data.waterImpCon);
							$('#waterQuantity').val(data.waterQuantity);
							$('#remark').val(data.remark);
							$('#shHairBarrel').val(data.shHairBarrel);
							$('#shHairTonnage').val(data.shHairTonnage);
							$('#oil').val(data.oil);
							$("input:radio[name='blStatus'][value='"+blStatus+"']").attr("checked","checked");
							$("#orderNo").val(orderNo);
							$('#id').val(data.id);
							$('#uuid').val(data.uuid);
							form.render();
							$('dd').css({"text-align":"left"});
							laydate.render({
						    	elem: '#blDate' //指定元素
						    		,lang: 'en'
						  	});
							laydate.render({
						    	elem: '#acStart' //指定元素
						    		,lang: 'en'
						  	});
							laydate.render({
						    	elem: '#acFinish' //指定元素
						    		,lang: 'en'
						  	});
							
						},
						error:function(data2){
							alert('request error');
						} 
					})		
					
				},
				error:function(data1){
					alert('request error');
				}
			})
		})
		
		function editLoading_yes_callback(){
			$("#loadPort").removeAttr("disabled");
			if($('#blDate').val() == "") {
				layer.alert($.i18n("orderexecute.code.00151"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
				return;
			}
			if($('#loadPort').val() == "") {
				layer.alert($.i18n("orderexecute.code.00151"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
				return;
			}
			$.ajax({
	      		url:orderServer+"/ship/upadteLoading.json",
	      		data:$("#loadingForm").serialize().split("blDateS")[0]+"blDateS"+$("#loadingForm").serialize().split("blDateS")[1].replace(/%2C/g,""),
	      		type:"post",
	      		success:function(result){
	      			if(result.status != 0){
	      				if(result.message == null){
	      					layer.alert($.i18n("orderexecute.code.00005"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
	      				}else{
	      					layer.alert($.i18n(result.code,result.params),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
	      				}
	      			}else{
	      				layer.alert($.i18n("orderexecute.code.00149"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")}, function(index){
	      					layer.closeAll();
	      					location.reload();
	      				});
	      			}
	      		},
	      		error:function(){
	      			layer.alert("request error",{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
	      		}
	      	})  
		}
		

		//删除装港信息
		$('.delLoading').on('click', function(){
			//alert("del");
			var loadingUuid = $(this).attr("loadingUuid");
			var loadingId = $(this).attr("loadingId");
			layer.confirm($.i18n("orderexecute.code.00155"), {title:$.i18n("orderexecute.code.00148"),btn:[$.i18n("orderexecute.code.00197"),$.i18n("orderexecute.code.00198")]}, function(index){
				$.ajax({
		      		url:orderServer+"/ship/delLoading.json",
		      		data:{uuid:loadingUuid,id:loadingId},
		      		type:"post",
		      		success:function(result){
		      			if(result.status != 0){
		      				if(result.message == null){
		      					layer.alert($.i18n("orderexecute.code.00005"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
		      				}else{
		      					layer.alert($.i18n(result.code,result.params),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
		      				}
		      			}else{
		      				layer.alert($.i18n("orderexecute.code.00149"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")}, function(index){
		      					layer.closeAll();
		      					location.reload();
		      				});
		      			}
		      		},
		      		error:function(){
		      			layer.alert("request error",{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
		      		}
		      	})
			})
		});
		
		//新增装港信息
		$('.saveLoading').on('click', function(){
			var orderNo = $(this).attr("orderNo");
			$.ajax({
				url:orderServer+'/bomp/ship/updateLoading.htm',
				success:function(data1){
					$.ajax({
						url:orderServer+'/ship/queryLoadingType.json',
						async:true,
						success:function(data){
							//自带弹框 
							layer.open({
								type:1,
								title: $.i18n("orderexecute.code.00152"),
								area: ['1000px', '480px'],
								content: data1,
								btn: [$.i18n("orderexecute.code.00212"), $.i18n("orderexecute.code.00154")],
								btnAlign: 'c',
								yes: function(){
									saveLoading_yes_callback();
								}
							});	
							
							for(var key in data.datas){
								$("#loadPort").append("<option value='"+ data.datas[key].value +"'>"+ data.datas[key].value +"</option>"); 
							}
							$("#orderNo").val(orderNo);
							form.render();
							$('dd').css({"text-align":"left"});
							laydate.render({
						    	elem: '#blDate' //指定元素
						    		,lang: 'en'
						  	});
							laydate.render({
						    	elem: '#acStart' //指定元素
						    		,lang: 'en'
						  	});
							laydate.render({
						    	elem: '#acFinish' //指定元素	
						    		,lang: 'en'
						  	});
						},
						error:function(data1){
							alert('request error');
						}
					})		
					
				},
				error:function(data){
					alert('request error');
				}
			})
		});
		
		function saveLoading_yes_callback(){
			var data = $("#loadingForm").serialize().split("blDateS")[0]+"blDateS"+$("#loadingForm").serialize().split("blDateS")[1].replace(/%2C/g,""); 
			if($('#blDate').val() == "") {
				layer.alert($.i18n("orderexecute.code.00151"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
				return;
			}
			if($('#loadPort').val() == "") {
				layer.alert($.i18n("orderexecute.code.00151"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
				return;
			}
			$.ajax({
	      		url:orderServer+"/ship/saveLoading.json",
	      		data:data,
	      		type:"post",
	      		success:function(result){
	      			if(result.status != 0){
	      				if(result.message == null){
	      					layer.alert($.i18n("orderexecute.code.00005"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
	      				}else{
	      					layer.alert($.i18n(result.code,result.params),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
	      				}
	      			}else{
	      				layer.alert($.i18n("orderexecute.code.00149"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")}, function(index){
	      					layer.closeAll();
	      					location.reload();
	      				});
	      			}
	      		},
	      		error:function(){
	      			layer.alert("request error",{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
	      		}
	      	}) 
		}
		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//新增卸港信息
		$('.saveUnloading').on('click', function(){
			var orderNo = $(this).attr("orderNo");
			$.ajax({
				url:orderServer+'/bomp/ship/updateUnloading.htm',
				success:function(data1){
					$.ajax({
						url:orderServer+'/ship/queryUnloadingType.json',
						async:true,
						success:function(data){
							//弹框 
							layer.open({
								type:1,
								title: $.i18n("orderexecute.code.00196"),
								area: ['1000px', '480px'],
								content: data1,
								btn: [$.i18n("orderexecute.code.00212"), $.i18n("orderexecute.code.00154")],
								btnAlign: 'c',
								yes: function(){
									saveUnloading_yes_callback();
								}
							});	
							
							for(var key in data.datas){
								$("#unloadPort").append("<option value='"+ data.datas[key].value +"'>"+ data.datas[key].value +"</option>"); 
							}
							$("#orderNo").val(orderNo);
							form.render();
							$('dd').css({"text-align":"left"});
							laydate.render({
						    	elem: '#norDate' //指定元素
						    		,lang: 'en'
						  	});
							laydate.render({
						    	elem: '#cod' //指定元素
						    		,lang: 'en'
						  	});
							laydate.render({
						    	elem: '#acStart' //指定元素
						    		,lang: 'en'
						  	});
							laydate.render({
						    	elem: '#acFinish' //指定元素	
						    		,lang: 'en'
						  	});
						},
						error:function(data){
							alert('request error');
						}
					})		
					
				},
				error:function(data1){
					alert('request error');
				}
			})
		});
		
		function saveUnloading_yes_callback(){
			if($('#unloadPort').val() == "") {
				layer.alert($.i18n("orderexecute.code.00151"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
				return;
			}
			if($('#norDate').val() == "") {
				layer.alert($.i18n("orderexecute.code.00151"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
				return;
			}
			$.ajax({
	      		url:orderServer+"/ship/saveUnloading.json",
	      		data:$("#unloadingForm").serialize().replace(/%2C/g,""),
	      		type:"post",
	      		success:function(result){
	      			if(result.status != 0){
	      				if(result.message == null){
	      					layer.alert($.i18n("orderexecute.code.00005"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
	      				}else{
	      					layer.alert($.i18n(result.code,result.params),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
	      				}
	      			}else{
	      				layer.alert($.i18n("orderexecute.code.00149"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")}, function(index){
	      					layer.closeAll();
	      					location.reload();
	      				});
	      			}
	      		},
	      		error:function(){
	      			layer.alert("request error",{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
	      		}
	      	}) 
		}
		
		//修改卸港信息
		$('.editUnloading').on('click',function(){
			//alert("修改卸港信息");
			var norStatus = $(this).attr("norStatus");
			var codStatus = $(this).attr("codStatus");
			
			var agentUuid = $(this).attr("agentUuid");
			var orderNo = $(this).attr("orderNo");
			
			var data = $(this).attr("data");
			data = eval("("+data+")");
			//console.log(data);
			$.ajax({
				url:orderServer+'/bomp/ship/updateUnloading.htm',
				success:function(data1){
					 $.ajax({
						url:orderServer+'/ship/queryUnloadingType.json',
						async:true,
						success:function(data2){ 
							//弹框 
							layer.open({
								type:1,
								title: $.i18n("orderexecute.code.00152"),
								area: ['1000px', '480px'],
								content: data1,
								btn: [$.i18n("orderexecute.code.00212"), $.i18n("orderexecute.code.00154")],
								btnAlign: 'c',
								yes: function(){
									editUnloading_yes_callback();
								}
							});	
							
							/*for(var key in data2.datas){
								$("#unloadPort").append("<option value='"+ data2.datas[key].value +"'>"+ data2.datas[key].value +"</option>"); 
							}
							
							//修改回显数据
							$("#unloadPort").append("<option selected='selected' value='"+ data.unloadPort +"'>"+ data.unloadPort +"</option>");
							*/
							var select = document.getElementById("unloadPort");
							for(var key in data2.datas){
								var opt = document.createElement("OPTION");
								opt.value = data2.datas[key].code;
								opt.text  = data2.datas[key].value;
								
								if(data2.datas[key].code==data.unloadPort
										||data2.datas[key].value==data.unloadPort){
									opt.selected ="selected";   
								}
								
								select.options.add(opt);
								
							}
							if(agentUuid != null && agentUuid != ''){
								$("#unloadPort").attr("disabled","disabled");
								$("#unloadPort").removeAttr("lay-search");
							}
							
							$('#norDate').val(data.norDate);
							$('#cod').val(data.cod);
							$('#acStart').val(data.acStart);
							$('#acFinish').val(data.acFinish);
							$('#commHairBar').val(data.commHairBar);
							$('#commCleanBar').val(data.commCleanBar);
							$('#commHairTon').val(data.commHairTon);
							$('#commCleanTon').val(data.commCleanTon);
							$('#shGrossCubicMeter').val(data.shGrossCubicMeter);
							$('#shNetCubicMeter').val(data.shNetCubicMeter);
							$('#shDensity').val(data.shDensity);
							$('#api').val(data.api);
							$('#waterImpCon').val(data.waterImpCon);
							$('#waterQuantity').val(data.waterQuantity);
							$('#remark').val(data.remark);
							
							$('#robQuanatity').val(data.robQuanatity);
							$('#shipHairBar').val(data.shipHairBar);
							$('#shipHairTon').val(data.shipHairTon);
							$('#shipHairBarVef').val(data.shipHairBarVef);
							$('#shipHairTonVef').val(data.shipHairTonVef);
							$('#potHairBar').val(data.potHairBar);
							$('#potHairTon').val(data.potHairTon);
							$('#oil').val(data.oil);
							
							$("input:radio[name='codStatus'][value='"+codStatus+"']").attr("checked","checked");
							$("input:radio[name='norStatus'][value='"+norStatus+"']").attr("checked","checked");
							$("#orderNo").val(orderNo);
							$('#id').val(data.id);
							$('#uuid').val(data.uuid);
							form.render();
							$('dd').css({"text-align":"left"});
							laydate.render({
						    	elem: '#norDate' //指定元素
						    		,lang: 'en'
						  	});
							laydate.render({
						    	elem: '#cod' //指定元素
						    		,lang: 'en'
						  	});
							laydate.render({
						    	elem: '#acStart' //指定元素
						    		,lang: 'en'
						  	});
							laydate.render({
						    	elem: '#acFinish' //指定元素
						    		,lang: 'en'
						  	});
							
						},
						error:function(data2){
							alert('request error');
						} 
					})		
					
				},
				error:function(data1){
					alert('request error');
				}
			})
		})
		
		function editUnloading_yes_callback(){
			$("#unloadPort").removeAttr("disabled");
			if($('#unloadPort').val() == "") {
				layer.alert($.i18n("orderexecute.code.00151"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
				return;
			}
			if($('#norDate').val() == "") {
				layer.alert($.i18n("orderexecute.code.00151"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
				return;
			}
			$.ajax({
	      		url:orderServer+"/ship/upadteUnloading.json",
	      		data:$("#unloadingForm").serialize().replace(/%2C/g,""),
	      		type:"post",
	      		success:function(result){
	      			if(result.status != 0){
	      				if(result.message == null){
	      					layer.alert($.i18n("orderexecute.code.00005"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
	      				}else{
	      					layer.alert($.i18n(result.code,result.params),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
	      				}
	      			}else{
	      				layer.alert($.i18n("orderexecute.code.00149"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")}, function(index){
	      					layer.closeAll();
	      					location.reload();
	      				});
	      			}
	      		},
	      		error:function(){
	      			layer.alert("request error",{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
	      		}
	      	})  
		}
		
		//删除装港信息
		$('.delUnloading').on('click', function(){
			//alert("del");
			var unloadingUuid = $(this).attr("unloadingUuid");
			var unloadingId = $(this).attr("unloadingId");
			layer.confirm($.i18n("orderexecute.code.00155"), {title:$.i18n("orderexecute.code.00148"),btn:[$.i18n("orderexecute.code.00197"),$.i18n("orderexecute.code.00198")]}, function(index){
				$.ajax({
		      		url:orderServer+"/ship/delUnloading.json",
		      		data:{uuid:unloadingUuid,id:unloadingId},
		      		type:"post",
		      		success:function(result){
		      			if(result.status != 0){
		      				if(result.message == null){
		      					layer.alert($.i18n("orderexecute.code.00005"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
		      				}else{
		      					layer.alert($.i18n(result.code,result.params),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
		      				}
		      			}else{
		      				layer.alert($.i18n("orderexecute.code.00149"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")}, function(index){
		      					layer.closeAll();
		      					location.reload();
		      				});
		      			}
		      		},
		      		error:function(){
		      			layer.alert("request error",{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
		      		}
		      	})
			})
		});
		///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		$('#editQuantity').on('click', function(){
			var data = $(this).attr("data");
			data = eval("("+data+")");
		 
			layer.open({
			  title: $.i18n("orderexecute.code.00156"),
		      type: 1,
		      btn: [$.i18n("orderexecute.code.00153"), $.i18n("orderexecute.code.00154")],
			  btnAlign: 'c',
			  yes: function(){
				editquantity_yes_callback(data);
			  },
		      area: ['540px', '265px'],
		      shadeClose: true, 
		      content: '<div style="padding:20px">' +
						'<ul class="c-ful">' +
							'<li class="c-fcos1">' +
								'<span>'+$.i18n("orderexecute.code.00157")+'：</span>' + 
								'<input type="text" id="editquantity-quantity-input" value="'+data.quantity+'" onkeyup="clearNoNumThree(this)">' +
								'&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="radio" value="0" checked="checked" />' +
								'<span>'+$.i18n("orderexecute.code.00158")+'</span>' +
								'&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="radio" value="1" />' +
								'<span>'+$.i18n("orderexecute.code.00159")+'</span>' +
								
							'</li>' +
							
						'</ul>' +
		      		   '</div>'
		    });
		    
		     var quantityStatus = $("input[type=radio][name=radio][value='"+data.quantityStatus+"']").attr("checked",true);
		})
		
		function editquantity_yes_callback(data){
			var contractId = data.triggerContractId;
			var quantity = $("#editquantity-quantity-input").val();
			var quantityStatus = $('input:radio:checked').val();
			
			$.ajax({
	      		url:orderServer+"/triggerContractr/updateQuantity.json",
	      		data:{contractId:contractId, quantity:quantity, quantityStatus:quantityStatus},
	      		type:"post",
	      		success:function(result){
	      			if(result.status != 0){
	      				if(result.message == null){
	      					layer.alert($.i18n("orderexecute.code.00005"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
	      				}else{
	      					layer.alert($.i18n(result.code,result.params),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
	      				}
	      			}else{
	      				layer.alert($.i18n("orderexecute.code.00149"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")}, function(index){
	      					layer.closeAll();
	      					location.reload();
	      				});
	      			}
	      		},
	      		error:function(){
	      			layer.alert("request error",{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
	      		}
	      	})
		}
		
		$('.chaidan').on('click', function(){
			var data = $(this).attr("data");
			data = eval("("+data+")");
			
			layer.open({
			  title: $.i18n("orderexecute.code.00160"),
		      type: 1,
		      btn: [$.i18n("orderexecute.code.00153"), $.i18n("orderexecute.code.00154")],
		      btnAlign: 'c',
		      yes: function(){
		      	var lockPrice = $("#cz-lockprice-input").val();
		      	var lockQuantity = $("#cz-quantity-input").val();
		      	
		      	$.ajax({
		      		url:orderServer+"/triggerResult/splitOrder.json",
		      		data:{triggerContractId:data.triggerContractId, lockPrice:lockPrice, lockQuantity:lockQuantity},
		      		type:"post",
		      		success:function(result){
		      			if(result.status != 0){
		      				if(result.message == null){
		      					layer.alert($.i18n("orderexecute.code.00005"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
		      				}else{
		      					layer.alert($.i18n(result.code,result.params),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
		      				}
		      			}else{
		      				layer.alert($.i18n("orderexecute.code.00149"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")}, function(index){
		      					layer.closeAll();
		      					location.reload();
		      				});
		      			}
		      		},
		      		error:function(){
		      			layer.alert("request error",{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
		      		}
		      	})
		      },
		      area: ['600px', '250px'],
		      shadeClose: true, 
		      content: '\<\div style="padding:20px">' +
						'<ul class="c-ful">' +
							'<li class="c-fcos2">' +
								'<span class="c-fn">'+$.i18n("orderexecute.code.00161")+'：</span>' +
								'<input type="text" id="cz-quantity-input" value="'+data.quantity+'" onkeyup="clearNoNumThree(this)"/>' +
							'</li>' +
							'<li class="c-fcos2">' +
								'<span class="c-fn">'+$.i18n("orderexecute.code.00162")+'：</span>' +
								'<input type="text" id="cz-lockprice-input" onkeyup="clearNoNumThreeFu(this)"/>' +
							'</li>' +
						'</ul>' +
		      		   '\<\/div>'
		    });
		});
		
		$('.dianjiaProcess').on('click', function(){
			var data = $(this).attr("data");
			data = eval("("+data+")");
			
			layer.open({
			  title: $.i18n("orderexecute.code.00163"),
		      type: 1,
		      btn: [$.i18n("orderexecute.code.00153"), $.i18n("orderexecute.code.00154")],
		      btnAlign: 'c',
		      yes: function(){
		      	var quantity = $("#djoperate-quantity-input").val();
		      	var lockPrice = $("#djoperate-lockprice-input").val();
		      	
		      	$.ajax({
		      		url:orderServer+"/triggerApply/complete.json",
		      		data:{applyId:data.triggerApplyId, lockQuantity:quantity, lockPrice:lockPrice},
		      		type:"post",
		      		success:function(result){
		      			if(result.status != 0){
		      				if(result.message == null){
		      					layer.alert($.i18n("orderexecute.code.00005"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
		      				}else{
		      					layer.alert($.i18n(result.code,result.params),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
		      				}
		      			}else{
		      				layer.alert($.i18n("orderexecute.code.00149"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")}, function(index){
		      					layer.closeAll();
		      					location.reload();
		      				});
		      			}
		      		},
		      		error:function(){
		      			layer.alert("request error",{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
		      		}
		      	})
		      },
		      area: ['750px', '250px'],
		      shadeClose: true, 
		      content: '<div style="padding:20px">' +
		      			'<ul class="c-ful">' +
							'<li class="c-fcos2">' +
								'<span class="c-fn">'+$.i18n("orderexecute.code.00161")+'（'+data.quantityUnit+'）：</span>' +
								'<input type="text" id="djoperate-quantity-input"/>' +
							'</li>' +
							'<li class="c-fcos2">' +
								'<span class="c-fn">'+$.i18n("orderexecute.code.00162")+'：</span>' +
								'<input type="text" id="djoperate-lockprice-input"/>' +
							'</li>' +
						'</ul>' +
						'</div>'
		    });
		});
		
		$('.noDealProcess').on('click', function(){
			var data = $(this).attr("data");
			data = eval("("+data+")");
			var applyId = data.triggerApplyId;
			
			layer.confirm($.i18n("orderexecute.code.00164"), {title:$.i18n("orderexecute.code.00148"),btn:[$.i18n("orderexecute.code.00197"),$.i18n("orderexecute.code.00198")]}, function(index){
			  $.ajax({
		      		url:orderServer+"/triggerApply/noDeal.json",
		      		data:{applyId:applyId},
		      		type:"post",
		      		success:function(result){
		      			if(result.status != 0){
		      				if(result.message == null){
		      					layer.alert($.i18n("orderexecute.code.00005"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
		      				}else{
		      					layer.alert($.i18n(result.code,result.params),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
		      				}
		      			}else{
		      				layer.alert($.i18n("orderexecute.code.00149"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")}, function(index){
		      					layer.closeAll();
		      					location.reload();
		      				});
		      			}
		      		},
		      		error:function(){
		      			layer.alert("request error",{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
		      		}
		      	})
			});
		})
		
		$('.editTransferQuantity').on('click', function(){
			var data = $(this).attr("data");
			data = eval("("+data+")");
			
			layer.open({
			  title: $.i18n("orderexecute.code.00149"),
		      type: 1,
		      btn: [$.i18n("orderexecute.code.00153"), $.i18n("orderexecute.code.00154")],
			  btnAlign: 'c',
			  yes: function(){
				editTransferQuantity_yes_callback(data);
			  },
		      area: ['850px', '350px'],
		      shadeClose: true, 
		      content: '<div style="padding:20px">' +
		      			'<ul class="c-ful">' +
		      				'<li class="c-fcos1">' +
								'<span class="c-fn">'+$.i18n("orderexecute.code.00166")+'：</span>' +
								data.contractName +
							'</li>' +
							'<li class="c-fcos2">' +
								'<span class="c-fn">'+$.i18n("orderexecute.code.00167")+'（'+data.quantityUnit+'）：</span>' +
								data.quantity +
							'</li>' +
							'<li class="c-fcos2">' +
								'<span class="c-fn">'+$.i18n("orderexecute.code.00168")+'：</span>' +
								data.price +
							'</li>' +
							'<li class="c-fcos2">' +
								'<span class="c-fn">'+$.i18n("orderexecute.code.00161")+'（'+data.quantityUnit+'）：</span>' +
								'<input type="text" id="cd-quantity-input"/>' +
							'</li>' +
							'<li class="c-fcos2">' +
								'<span class="c-fn">'+$.i18n("orderexecute.code.00162")+'：</span>' +
								'<input type="text" id="cd-price-input"/>' +
							'</li>' +
						'</ul>' +
						'</div>'
		    });
			
			function editTransferQuantity_yes_callback(data){
				
				var quantity = $("#cd-quantity-input").val();
				var price = $("#cd-price-input").val();
				
				$.ajax({
		      		url:orderServer+"/triggerResult/updateSplitOrder.json",
		      		data:{triggerResultId:data.triggerResultId,quantity:quantity,price:price},
		      		type:"post",
		      		success:function(result){
		      			if(result.status != 0){
		      				if(result.message == null){
		      					layer.alert($.i18n("orderexecute.code.00005"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
		      				}else{
		      					layer.alert($.i18n(result.code,result.params),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
		      				}
		      			}else{
		      				layer.alert($.i18n("orderexecute.code.00149"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")}, function(index){
		      					layer.closeAll();
		      					location.reload();
		      				});
		      			}
		      		},
		      		error:function(){
		      			layer.alert("request error",{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
		      		}
		      	})
			}
		})
		
		$(".inspection_select").on("click",function(){
			var dataObj = eval("("+$(this).attr("data")+")");
			var portname = dataObj.port;
			var id = dataObj.id;
			var type = dataObj.type;//装港还是卸港
			
			$.get(orderServer+'/bomp/inspection/selectUser.htm', {portname:portname}, function(content) {
				layer.open({
					title: $.i18n("orderexecute.code.00202"),
					type: 1,
					btn: [$.i18n("orderexecute.code.00153"), $.i18n("orderexecute.code.00154")],
					btnAlign: 'c',
					yes: function(){
						var memberId = $("#inspection-select").val();
						if(memberId == ""){
							layer.alert($.i18n("orderexecute.code.00205"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
							return;
						}
						var memberName = $("#inspection-select").find("option:selected").text();
						var port = $("#port").val();
						var terminal = $("#terminal").val();
						
						var contactInfoJson = $("#contact-select").val();
						var contactInfoObj = eval("("+contactInfoJson+")");
						var contactName = contactInfoObj.name;
						var tel = contactInfoObj.tel;
						var mail = contactInfoObj.email;
						
						var url;
						if(type=="loading"){
							url = orderServer+"/api/inspection/saveLoadingInspectionMember.json";
						}else if(type=="unloading"){
							url = orderServer+"/api/inspection/saveUnloadingInspectionMember.json";
						}
						
						$.post(url,
								{id:id,type:type,memberId:memberId,memberName:memberName,contact:contactName,port:port,terminal:terminal,tel:tel,mail:mail},
								function(result){
							if(result.status == 0){
								layer.alert($.i18n("orderexecute.code.00146"),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")},function(){
									layer.closeAll();
									location.reload();
								})
							}else{
								layer.alert($.i18n(result.code),{title:$.i18n("orderexecute.code.00148"),btn:$.i18n("orderexecute.code.00197")});
							}
						})
					},
					area:['650px','360px'],
					content : content
				});
			});
		})
	})
	

	//5位小数数字正则校验
	function clearNoNumFive(obj)    
	{    
	    //先把非数字的都替换掉，除了数字和.    
	    obj.value = obj.value.replace(/[^\d.]/g,"");    
	    //保证只有出现一个.而没有多个.    
	    obj.value = obj.value.replace(/\.{2,}/g,".");    
	    //必须保证第一个为数字而不是.    
	    obj.value = obj.value.replace(/^\./g,"");    
	    //保证.只出现一次，而不能出现两次以上    
	    obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");    
	    //只能输入5个小数  
	    obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d\d\d\d).*$/,'$1$2.$3');
	}  
	
	//三位小数数字正则校验
	function clearNoNumThree(obj)    
	{    
		//先把非数字的都替换掉，除了数字和.    
	    $(obj).val( $(obj).val().replace(/[^\d.]/g,""));    
	    //保证只有出现一个.而没有多个.    
	     $(obj).val($(obj).val().replace(/\.{2,}/g,"."));    
	    //必须保证第一个为数字而不是.    
	     $(obj).val( $(obj).val().replace(/^\./g,""));  
	    //保证.只出现一次，而不能出现两次以上    
	    $(obj).val( $(obj).val().replace(".","$#$").replace(/\./g,"").replace("$#$","."));    
	   // $(obj).val( $(obj).val().replace(/^\-/g,"$#$").replace(/\-/g,"").replace("$#$","-"));    
	    //只能输入两个小数  
	    $(obj).val( $(obj).val().replace(/^(\-)*(\d+)\.(\d\d\d).*$/,'$1$2.$3')); 
	} 

	function clearNoNumThreeFu(obj)    
	{    
		//先把非数字的都替换掉，除了数字和.    
	    $(obj).val( $(obj).val().replace(/[^\d.-]/g,""));    
	    //保证只有出现一个.而没有多个.    
	     $(obj).val($(obj).val().replace(/\.{2,}/g,"."));    
	    //必须保证第一个为数字而不是.    
	     $(obj).val( $(obj).val().replace(/^\./g,""));  
	    //保证.只出现一次，而不能出现两次以上    
	    $(obj).val( $(obj).val().replace(".","$#$").replace(/\./g,"").replace("$#$","."));    
	    $(obj).val( $(obj).val().replace(/^\-/g,"$#$").replace(/\-/g,"").replace("$#$","-"));    
	    //只能输入两个小数  
	    $(obj).val( $(obj).val().replace(/^(\-)*(\d+)\.(\d\d\d).*$/,'$1$2.$3')); 
	} 