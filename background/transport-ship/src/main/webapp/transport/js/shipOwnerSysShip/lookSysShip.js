layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate','own','upload'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	own = layui.own,
	table = layui.table;
	upload = layui.upload;
	window.$ = layui.jquery;
	var laydate = layui.laydate;

	//船舶uuid
	var uuid=own.getHref('uuid');
	
	
 	//查询详细
 	var params = {};
 	params.uuid = uuid;

 	$.ajax({
		type: "POST",
		url: shipServer+"/sysShip/findSysShipDetail.json",
		data: JSON.stringify(params),
		contentType:"application/json",
		async: false,
		success: function(data) {
			if(data.status == 0){
				//$("#uuid").val(data.datas.uuid);
				$("#imo").html(data.datas.imo);
				$("#mmsi").html(data.datas.mmsi);
				$("#name").html(data.datas.name);
				$("#type").html(data.datas.type);
				$("#useType").html(data.datas.useType);
				
				$("#date1").html(data.datas.completeDate);
				$("#hull").html(data.datas.hull);
				$("#length").html(formatNumber(data.datas.length,2,1));
				$("#wide").html(formatNumber(data.datas.wide,2,1));
				$("#draft").html(formatNumber(data.datas.draft,2,1));
				$("#loadQuantity").html(formatNumber(data.datas.loadQuantity,3,1));
				$("#capacity").html(formatNumber(data.datas.capacity,2,1));
				
				//驳回信息回显
				var rejectMsg=data.datas.ext1;
				if(rejectMsg!=null && rejectMsg!="" && data.datas.status=='3'){
					$("#rejectMsg").html(rejectMsg);
				}else{
					$(".rejectInfo").hide();
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
 	
 	//下载列表初始化（查看模式）
 	downloadList('0');
 	form.render();
	
 	
 	//下载列表初始化(mode:0查看 1编辑)
	function downloadList(mode) {
		// 初始化下载列表
	  	var downloadListView = $('#downloadList');
	  	// 查询下载列表
	 	var paramsFile = {};
	 	paramsFile.uuid = uuid;
	 	paramsFile.type = "1";	//业务类型（1船舶2船航次开始3船装港4船在途5船卸港）
	 	
	 	$.ajax({
    		type: "POST",
    		url: shipServer+"/accessory/findAccessoryList.json",
    		data: JSON.stringify(paramsFile),
			contentType:"application/json",
			async: false,
    		success: function(data) {
				if(data.status == 0){
					var downloadList = [];
					downloadList = data.datas;
					
					if (downloadList.length==0) {
					 	$(".layui-upload").hide();	/**隐藏下载列表*/
					}else{
						$(".layui-upload").show();	/**显示下载列表*/
					}
					
					for(index=0; index < downloadList.length; index++) {
				  		var file = downloadList[index];
				  		var fileIndex = index + 1;
				  		
				  		var tr = $(['<tr id="download-'+ index +'">'
				          				,'<td>'+ fileIndex +'</td>'
				          				,'<td style="text-align:center">'+ file.name +'</td>'
				          				,'<td style="text-align:center">'
				            			,'<a href="'+'/common/doc/download.htm?path='+file.path+'" class="file-download">'+$.i18n("JAVASCRIPT00181")+'</a>'
				            			//,'<button type="button" class="layui-btn layui-btn-mini layui-btn-danger file-delete" attr-data="'+file.uuid+'" attr-data1="'+index+'">'+$.i18n("JAVASCRIPT00196")+'</button>'
				          				,'</td>'
				        				,'</tr>'].join(''));
				  		
				  		//查看模式
				  		if (mode == '0') {
				  			tr.find('.file-delete').hide();
				  		}
				  		
				  		//下载事件
				  		tr.find('.file-download').on('click', function(){
				  			var path = $(this).attr("attr-data");
				  		});
				  		
				  		//删除事件
				  		tr.find('.file-delete').on('click', function(){
				  			var accesoryUuid = $(this).attr("attr-data");
				  			var trIndex = $(this).attr("attr-data1");
				  			//弹框运行
				  		    layer.confirm($.i18n("JAVASCRIPT0087"), {title:$.i18n("001"),btn:[$.i18n("002"),$.i18n("003")]},function(index){
				  		    	var params = {};
				  		    	params.uuid = accesoryUuid;
				  		    	
				  		    	$.ajax({
				  		    		type: "POST",
				  		    		url: shipServer+"/accessory/delAccessory.json",
				  		    		data: JSON.stringify(params),
				  					contentType:"application/json",
				  					async: false,
				  		    		success: function(data) {
				  						if(data.status == 0){
				  							layer.alert($.i18n("JAVASCRIPT0019"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
				  								$("tr#download-" + trIndex).remove();
				  							    layer.close(index);
				  							});
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
				  		    });
						});
				        downloadListView.append(tr);		
				  	}
				}
			},
			error: function(data) {
				layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
					layer.close(index);
				});
	        }
	 	});
	}
	
	
    //返回
	$('.returnBtn').on('click',function(){
		window.location.href = document.referrer;
	});

});