layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate','upload'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	own = layui.own,
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;

	//执行一个laydate实例
	//执行一个laydate实例
  	laydate.render({
    	elem: '#searchPublishBeg',
    	lang: lang_ver,
    	type: 'date'
  	});
  	laydate.render({
  		elem: '#searchPublishEnd',
  		lang: lang_ver,
  		type: 'date'
  	});
  	laydate.render({
  		elem: '#searchOpenBeg',
  		lang: lang_ver,
  		type: 'date'
  	});
  	laydate.render({
  		elem: '#searchOpenEnd',
  		lang: lang_ver,
  		type: 'date'
  	});
  	laydate.render({
  		elem: '#searchETABeg',
  		lang: lang_ver,
  		type: 'date'
  	});
  	laydate.render({
  		elem: '#searchETAEnd',
  		lang: lang_ver,
  		type: 'date'
  	});
  	
  	
	// 页面记录的uuid
	var uuid = "";
	
	$("body").keydown(function() {
		if (event.keyCode == "13") {//keyCode=13是回车键
			 $('.searchBtn').click();
		}
	});
	
	
	// 查询函数
	$('.searchBtn').on('click',function(){
		search();
	}); 
	

	function search(){
		var searchName = $('#searchName').val();
		var searchType = $('#searchType').val();
		var searchAgeBeg = $('#searchAgeBeg').val();
		var searchAgeEnd = $('#searchAgeEnd').val();
		var searchPublisher = $('#searchPublisher').val();
		var searchShipOwner = $('#searchShipOwner').val();
		var searchBroker = $('#searchBroker').val();
		var searchPublishBeg = $('#searchPublishBeg').val();
		var searchPublishEnd = $('#searchPublishEnd').val();
		var searchOpenBeg = $('#searchOpenBeg').val();
		var searchOpenEnd = $('#searchOpenEnd').val();
		var searchETABeg = $('#searchETABeg').val();
		var searchETAEnd = $('#searchETAEnd').val();
		var searchQuantityBeg = delNumFormat($('#searchQuantityBeg').val());
		var searchQuantityEnd = delNumFormat($('#searchQuantityEnd').val());
		var searchStatus = $('#searchStatus').val();
		var searchPublishType = $('#searchPublishType').val();
		
		window.location.href=shipServer+"/om/platform/shipPlateList.htm?name="+searchName+"&type="+$.trim(searchType)+"&ageBeg="+searchAgeBeg+"&ageEnd="+searchAgeEnd+"&epMembername="+searchPublisher+"&shipOwner="+searchShipOwner+"&brokerName="+searchBroker+"&publishBeg="+searchPublishBeg+"&publishEnd="+searchPublishEnd+"&openBeg="+searchOpenBeg+"&openEnd="+searchOpenEnd+"&ETABeg="+searchETABeg+"&ETAEnd="+searchETAEnd+"&quantityBeg="+searchQuantityBeg+"&quantityEnd="+searchQuantityEnd+"&status="+searchStatus+"&relType="+searchPublishType;
	}
	
	
	//条件回显
	var searchData = getQueryString('name');
	if (searchData != null && searchData !=''){
		while(searchData.indexOf("+")>-1){
			searchData=searchData.replace("+"," ");
		}
		$('#searchName').val($.trim(searchData));
	}
	var brokerNameData = getQueryString('brokerName');
	if (brokerNameData != null && brokerNameData !=''){
		while(brokerNameData.indexOf("+")>-1){
			brokerNameData=brokerNameData.replace("+"," ");
		}
		$('#searchBroker').val($.trim(brokerNameData));
	}
	var epMembernameData = getQueryString('epMembername');
	if (epMembernameData != null && epMembernameData !=''){
		while(epMembernameData.indexOf("+")>-1){
			epMembernameData=epMembernameData.replace("+"," ");
		}
		$('#searchPublisher').val($.trim(epMembernameData));
	}
	var shipOwnerData = getQueryString('shipOwner');
	if (shipOwnerData != null && shipOwnerData !=''){
		while(shipOwnerData.indexOf("+")>-1){
			shipOwnerData=shipOwnerData.replace("+"," ");
		}
		$('#searchShipOwner').val($.trim(shipOwnerData));
	}
	var typeData = getQueryString('type');
	if (typeData != null && typeData !=''){
		while(typeData.indexOf("+")>-1){
			typeData=typeData.replace("+"," ");
		}
		$('#searchType').val($.trim(typeData));
	}
	var statusData = getQueryString('status');
	if (statusData != null && statusData !=''){
		$('#searchStatus').val(statusData);
	}
	var quantityBegData = getQueryString('quantityBeg');
	if (quantityBegData != null && quantityBegData !=''){
		$('#searchQuantityBeg').val(formatNumber(quantityBegData,0,1));
	}
	var quantityEndData = getQueryString('quantityEnd');
	if (quantityEndData != null && quantityEndData !=''){
		$('#searchQuantityEnd').val(formatNumber(quantityEndData,0,1));
	}
	var relTypeData = getQueryString('relType');
	if (relTypeData != null && relTypeData !=''){
		$('#searchPublishType').val(relTypeData);
	}
	
	
	
	//重置
	$('.resetBtn').on('click',function(){
		$("input[type='text']").val("");
		$("#searchStatus").attr("value","");
		$("#searchPublishType").attr("value","");
		window.location.href=shipServer+"/om/platform/shipPlateList.htm";
	}); 
	
	
	//导出模板（船盘）
	/*$('.shipPlateExport').on('click',function(){
		var obj={};
		obj.type='5';	//导出类型（1船在途）（2配载计划）（3装港信息）（4卸港信息）（5船盘信息）（6船舶信息）
		$.ajax({
			type: "POST",
			url: shipServer+"/exportShipPlateTemple.json",
			data: JSON.stringify(obj),
			dataType: "json",
			contentType:"application/json",
			success: function(data) {
				if(data.status == 0){
					window.location.href=data.datas;
				} else {
					message($.i18n(data.message));
				}
			},
			error:function(){
			   message($.i18n("JAVASCRIPT003"));
			}
		});
	});*/
	
	
	//批量导入数据（船盘）
	var uploaderObj=null;
	$('.shipPlateImportBtn').on('click',function(){
		//请求弹框
		$.ajax({
			type:"POST",
			url:shipServer+"/transport/bomb/platform/importShipPlateBatch.vm",
			success:function(data) {
				//弹框运行
				layer.open({
					skin: 'backbtn-class',
					type: 1,
					area: ['830px','430px'],
					//btn: [$.i18n("JAVASCRIPT00245"),$.i18n("JAVASCRIPT00246"), $.i18n("JAVASCRIPT0025")],
					btn: '关闭',
					btnAlign: 'c' ,//按钮居中
					title:$.i18n("JAVASCRIPT00246"),
					content: data,
					/*yes: function(index, layero){
						//导出模板（船盘）
						var obj={};
						obj.type='5';	//导出类型（1船在途）（2配载计划）（3装港信息）（4卸港信息）（5船盘信息）（6船舶信息）
						$.ajax({
							type: "POST",
							url: shipServer+"/exportShipPlateTemple.json",
							data: JSON.stringify(obj),
							dataType: "json",
							contentType:"application/json",
							success: function(data) {
								if(data.status == 0){
									window.location.href=data.datas;
								} else {
									message($.i18n(data.message));
								}
							},
							error:function(){
							   message($.i18n("JAVASCRIPT003"));
							}
						});
					},
					 btn2: function(index, layero){
						var epMemberId=$('#selectedArr option:checked').val();
						if(epMemberId!=null && epMemberId!=""){
							*//**走上传文件绑定事件*//*
						}else{
							deleteBing(uploaderObj);	*//**解除按钮上传绑定*//*
							layer.alert($.i18n("JAVASCRIPT00244"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {	*//**弹框提示*//*
								layer.close(index);
							});
						}
						return false;
					},*/
				    cancel: function(index, layero){
					    layer.close(index);
					    return false;
				    }
				});
				
				
				
				//绑定模板导出按钮
				$('#exportTemplateBtn').on('click',function(){
					//导出模板（船盘）
					var obj={};
					obj.type='5';	//导出类型（1船在途）（2配载计划）（3装港信息）（4卸港信息）（5船盘信息）（6船舶信息）
					$.ajax({
						type: "POST",
						url: shipServer+"/exportShipPlateTemple.json",
						data: JSON.stringify(obj),
						dataType: "json",
						contentType:"application/json",
						success: function(data) {
							if(data.status == 0){
								window.location.href=data.datas;
							} else {
								message($.i18n(data.message));
							}
						},
						error:function(){
						   message($.i18n("JAVASCRIPT003"));
						}
					});
				});
				
				
				
				//绑定“选择上传文件”按钮
				$('#importsBtn').on('click',function(){
					var epMemberId=$('#selectedArr option:checked').val();
					if(epMemberId!=null && epMemberId!=""){
						/**走上传文件绑定事件*/
					}else{
						deleteBing(uploaderObj);	/**解除按钮上传绑定*/
						layer.alert($.i18n("JAVASCRIPT00244"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {	/**弹框提示*/
							layer.close(index);
						});
					}
				});
				
				

				//绑定下拉框选中事件
				form.on('select(companyFilter)', function(data){
					
					var epMemberId=$('#selectedArr option:checked').val();
					if(epMemberId!=null && epMemberId!=""){
						
						deleteBing(uploaderObj);								/**先解除按钮上传绑定*/
						
						//var ssdd = Math.round(Math.random() * 10000);
						//$('.layui-layer-btn1').attr('id',"importDataBtn"+ssdd);	/**为按钮添加id*/
						//var idData=$('.layui-layer-btn1').attr('id');
						
						uploaderObj = new plupload.Uploader({ 					/**实例化一个plupload上传对象*/
							browse_button : 'importsBtn',
							url : shipServer+'/importShipPlateDataOM.json',
							headers:{'Authorization':""},
							flash_swf_url : 'js/Moxie.swf',
							silverlight_xap_url : 'js/Moxie.xap',
							multi_selection:false,
							multipart_params: {'epMemberId': epMemberId}
						});
						
						uploaderObj.init();										/**初始化上传对象*/
						uploaderObj.bind('FilesAdded', function(up, files) {	/**开始上传*/
							loadIndex = layer.load(0, {shade: false});
							uploaderObj.start(); 
						});
						uploaderObj.bind('FileUploaded',function(uploader,file){	/**上传完成*/
							$.ajaxFn({
								type : "POST",
								url : shipServer+"/import/returnFlag.json",
								data:{},
								success : function(data) {
									layer.close(loadIndex);
									if(data.status == 0){
										layer.alert($.i18n(data.datas),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
											layer.close(index);
											location.reload();
										});
									}else{
										message($.i18n(data.message));
									}
								},
								error:function(data){
									layer.close(loadIndex);
									layer.alert($.i18n("JAVASCRIPT0027"),{title:$.i18n("001"),btn:$.i18n("002")},function(index) {
										layer.close(index);
										location.reload();
									});
								}
							});
						});
					}
				});
				
				
				//删除绑定实例
				function deleteBing(uploaderObj){
					if(uploaderObj!=null){
						uploaderObj.destroy();
					}
				}
				
				
				//查询企业信息
				searchEpMemberName();
				//查询企业信息
				function searchEpMemberName(){
					 var paramType={};
					 $.ajax({
			    		type: "POST",
			    		url: shipServer+"/queryTranderName.json",
			    		data: JSON.stringify(paramType),
						dataType: "json",
						contentType:"application/json",
			    		async: false,
			    		success: function(data) {
							if(data.status == 0){
								
								var arr=data.datas;
								window.showArr = [];
								window.datasArr = [];
								window.shipOwnerArr = [];
								window.brokerArr = [];
								window.turnOwnerArr = [];
								
								window.showContain = [];
								window.shipOwnerContain = [];
								window.brokerContain = [];
								window.turnOwnerContain = [];
								
								if(arr!=null){
									for(var i=0;i<arr.length;i++){
										if(arr[i].type=="4"){	//船东
											if(isContains(shipOwnerContain,arr[i].traderId)){
												//包含，不添加（一个角色有多个相同公司）
												/*var index = getSameIndex(shipOwnerArr,arr[i]);
												if (index > -1) {
													shipOwnerArr.splice(index, 1);
												}*/
											}else{
												//不包含，添加
												shipOwnerArr.push(arr[i]);
												shipOwnerContain.push(arr[i].traderId);
											}
											
										}else if(arr[i].type=="5"){	//经纪人
											if(!isContains(brokerContain,arr[i].traderId)){
												brokerArr.push(arr[i]);
												brokerContain.push(arr[i].traderId);
											}
											
										}else if(arr[i].type=="7"){	//转租船东
											if(!isContains(turnOwnerContain,arr[i].traderId)){
												turnOwnerArr.push(arr[i]);
												turnOwnerContain.push(arr[i].traderId);
											}
										}
									}
									
									//公司集合
									datasArr=shipOwnerArr.concat(brokerArr).concat(turnOwnerArr);
									//去除多角色的公司（多个角色有相同公司）
									for(var i=0;i<datasArr.length;i++){
										if(isContains(showContain,datasArr[i].traderId)){
											var index = getSameIndex(showArr,datasArr[i]);
											if (index > -1) {
												showArr.splice(index, 1);	//切割，index起始位置，长度为1
											}
											// else：第三次相同，showContain中已存在traderId，不会添加
										}else{
											showArr.push(datasArr[i]);
											showContain.push(datasArr[i].traderId);
										}
									}
									//window.document.write(JSON.stringify(showArr));
									//拼接 option
									for(var i=0;i<showArr.length;i++){
										$('#selectedArr').append("<option value='"+showArr[i].traderId+"'>"+showArr[i].traderName+"</option>");
									}
								}
								
							}else{
								layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
									layer.close(index);
								});
							}
						},
						error: function(data) {
							layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
								layer.close(index);
							});
				        }
				 	});
					form.render();
				}
				
			}
		})
	});
	
	
	
	//批量删除
	$('.batchDelBtn').on('click',function(){
		
		var adIds=null;		/**拼接船盘uuid*/
		
		$("input[name='allSelect']:checked").each(function(i){  
            if(0==i){  
                adIds = $(this).attr('attr-data');  
            }else{  
                adIds += (","+$(this).attr('attr-data'));  
            }  
        });
		
		if(adIds!=null){
		    layer.confirm($.i18n("JAVASCRIPT0018"), {title:$.i18n("001"),btn:[$.i18n("002"),$.i18n("003")]},function(index){
		    	var delParam = {};
		    	delParam.uuidStr = adIds;
		    	$.ajax({
		    		type: "POST",
		    		url: shipServer+"/shipPlate/batchDelShipPlateOM.json",
		    		data: JSON.stringify(delParam),
					dataType: "json",
					contentType:"application/json",
		    		async: false,
		    		success: function(data) {
						if(data.status == 0){
							layer.alert($.i18n("CONSTANTS008"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
							    layer.close(index);
							    location.reload();
							});
						}else{
							layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
							    layer.close(index);
							    location.reload();
							});
						}
					},
					error: function(data) {
						layer.alert($.i18n(data.message), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
							layer.close(index);
						});
			        }
		    	});
		    });
		}else{
			layer.alert("请选择要删除的船盘",{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {	/**弹框提示*/
				layer.close(index);
			});
		}
	});

	
	
	//查看页面
	$('.lookBtn').on('click',function(){
		uuid = $(this).attr("attr-data");
		//请求弹框
		$.ajax({
		  type:"POST",
		  url:shipServer+"/transport/bomb/platform/lookShipPlate.vm",
		  success:function(data) {
			//弹框运行
		 	layer.open({
		 		  skin: 'backbtn-class',
				  type: 1,
				  area: ['850px','580px'],
				  btn: '关闭',
				  btnAlign: 'c' ,//按钮居中
				  title:$.i18n("JAVASCRIPT00174"),
				  content: data,
				  yes: function(index, layero){
					  layer.close(index);
					  return false;
				  },
				  cancel: function(index, layero){
					  layer.close(index);
					  return false;
				  }
		 	});

		 	//查询详细
		 	look(uuid);
		 	
		 	//查询详细
		  	function look(uuid){
			 	var params = {};
			 	params.uuid = uuid;
			 	
			 	$.ajax({
		    		type: "POST",
		    		url: shipServer+"/shipPlate/findShipPlateDetailOM.json",
		    		data: JSON.stringify(params),
					dataType: "json",
					contentType:"application/json",
		    		async: false,
		    		success: function(data) {
						if(data.status == 0){
							var completeDateTimeStamp = data.datas.completeDate;
							var completeDate=new Date(completeDateTimeStamp).Format("yyyy-MM-dd");
							
							//获取 ETA Fujairah 并赋初始化
							var etaTimeStamp = data.datas.eta;
							if(etaTimeStamp!=null){
								$('.eta').val(new Date(etaTimeStamp).Format("yyyy-MM-dd"));
							}else{
								$('.eta').val("");
							}
							
							//获取 ETA Cabinda 并赋初始化
							var etaCabindaTimeStamp = data.datas.etaCabinda;
							if(etaCabindaTimeStamp!=null){
								$('.etaCabinda').val(new Date(etaCabindaTimeStamp).Format("yyyy-MM-dd"));
							}else{
								$('.etaCabinda').val("");
							}
							
							var name = data.datas.name;
							var brokerName = data.datas.brokerName;
							var shipOwner = data.datas.shipOwner;
							var epMemberName = data.datas.epMemberName;	 //船盘发布人
							var period = data.datas.period;	  //时效
							
							//获取卸货完成时间，并赋初始化
							var openTimeStamp = data.datas.open;	//到港之后，卸货完成时间
							if(openTimeStamp!=null){
								$(".open").val(new Date(openTimeStamp).Format("yyyy-MM-dd"));
							}else{
								$(".open").val("");
							}
							
							//获取发布日期，并赋初始化
							var publishTimeStamp = data.datas.publishTime;	//发布日期
							if(publishTimeStamp!=null){
								$(".publishTime").val(new Date(publishTimeStamp).Format("yyyy-MM-dd"));
							}else{
								$(".publishTime").val("");
							}
							
							$('.completeDate').val(completeDateTimeStamp);
							$('.name').val(data.datas.name);
							$('.type').val(data.datas.type);
							$('.loadQuantity').val(formatNumber(data.datas.loadQuantity,3,1));
							$('.position').val(data.datas.position);
							$('.owner').val(data.datas.shipOwner);
							$('.broker').val(data.datas.brokerName);
							$('.remark').val(data.datas.remark);
							$(".epMemberName").val(epMemberName);
							$(".period").val(period);
							$(".shipAge").val(data.datas.shipAge);
							
							var relTypeStr="";
							var relType=data.datas.relType;
							if(relType=="1"){
								relTypeStr="船东/经纪人";
							}else if(relType=="2"){
								relTypeStr="二船东";
							}else if(relType=="3"){
								relTypeStr="平台";
							}
							$(".publishType").val(relTypeStr);
							
						}else{
							layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
								layer.close(index);
							});
						}
					},
					error: function(data) {
						layer.alert($.i18n(data.message), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
							layer.close(index);
						});
			        }
			 	});
			 	form.render();
		  	}
		  }
		})
	});
	
	
	//删除
	$('.delBtn').on('click',function(){
		var uuidDats = $(this).attr("attr-data");
		
		if(uuidDats!=null && uuidDats!=""){
			layer.confirm($.i18n("JAVASCRIPT0018"), {title:$.i18n("001"),btn:[$.i18n("002"),$.i18n("003")]},function(index){
		    	var delParam = {};
		    	delParam.uuid = uuidDats;
		    	$.ajax({
		    		type: "POST",
		    		url: shipServer+"/shipPlate/delShipPlateOM.json",
		    		data: JSON.stringify(delParam),
					dataType: "json",
					contentType:"application/json",
		    		async: false,
		    		success: function(data) {
						if(data.status == 0){
							layer.alert($.i18n("CONSTANTS008"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
							    layer.close(index);
							    location.reload();
							});
						}else{
							layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
							    layer.close(index);
							    location.reload();
							});
						}
					},
					error: function(data) {
						layer.alert($.i18n(data.message), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
							layer.close(index);
						});
			        }
		    	});
		    });
		}
	});
	
	
	
	//全选/全不选
	$("#allSelected").click(function () {
	    if(this.checked){
	        $("[name=allSelect]:checkbox").attr('checked',true)
	    }else {
	        $("[name=allSelect]:checkbox").attr('checked',false)
	    }
	});


	// 日期格式化函数
	Date.prototype.Format = function (fmt) {
	    var o = {
	        "M+": this.getMonth() + 1, 						//月份 
	        "d+": this.getDate(), 							//日 
	        "h+": this.getHours(), 							//小时 
	        "m+": this.getMinutes(), 						//分 
	        "s+": this.getSeconds(), 						//秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), 	//季度 
	        "S": this.getMilliseconds() 					//毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    
	    return fmt;
	}
	
	//获取地址栏参数
	function getQueryString(name) {
	    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	    var r = window.location.search.substr(1).match(reg);
	    if ( r != null ){
	       return decodeURI(r[2]);
	    }else{
	       return null;
	    } 
	 }
	
	//判断是否包含子串
	function isContains(str, substr) {
	    return str.indexOf(substr) >= 0;
	}
	
	//返回数组中，与当前对象traderName相同元素的位置
	function getSameIndex(arr,obj){
		var index=-1;
		if(arr!=null && arr!="" && arr.length>0){
			for(var n=0;n<arr.length;n++){
				index++;
				if(arr[n].traderId==obj.traderId){
					return index;
				}
			}
		}
		return -1;
	}

});
