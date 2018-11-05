	// 下载列表初始化(mode:0查看 1编辑)
	function downloadList(mode) {
		var uuidShip = $('#uuid').val();
		
		// 初始化下载列表
	  	var downloadListView = $('#downloadList');
	  	
	  	// 查询下载列表
	 	var paramsFile = {};
	 	paramsFile.uuid = uuidShip;
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
						// 隐藏下载列表
					 	$(".layui-upload").hide();
					}
				 	
					for(index=0; index < downloadList.length; index++) {
				  		var file = downloadList[index];
				  		var fileIndex = index + 1;
				  		
				  		var tr = $(['<tr id="download-'+ index +'">'
				          				,'<td style="text-align:center">'+ fileIndex +'</td>'
				          				,'<td>'+ file.name +'</td>'
				          				,'<td>'
				            			//,'<a href="'+file.path+'" class="layui-btn layui-btn-mini file-download" download="">下载</a>'
				            			,'<a href="'+'/common/doc/download.htm?path='+file.path+'" class="layui-btn layui-btn-mini file-download">'+$.i18n("JAVASCRIPT00181")+'</a>'
				            			,'<button type="button" class="layui-btn layui-btn-mini layui-btn-danger file-delete" attr-data="'+file.uuid+'" attr-data1="'+index+'">删除</button>'
				          				,'</td>'
				        				,'</tr>'].join(''));
				  					
				  		// 查看模式
				  		if (mode == '0') {
				  			tr.find('.file-delete').hide();
				  		}
				  		
				  		// 下载事件
				  		tr.find('.file-download').on('click', function(){
				  			var path = $(this).attr("attr-data");
				  			//window.open(path,'_blank');
				  			//window.location.href=shipServer+"/common/doc/download.htm?path="+ encodeURI(path);
				  		});
				  		
				  		// 删除事件
				  		tr.find('.file-delete').on('click', function(){
				  			var accesoryUuid = $(this).attr("attr-data");
				  			var trIndex = $(this).attr("attr-data1");
				  			//弹框运行
				  		    layer.confirm($.i18n("JAVASCRIPT0087"), function(index){
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
				  							layer.alert($.i18n("JAVASCRIPT0019"), function(index) {
				  								//tr.remove();
				  								$("tr#download-" + trIndex).remove();
				  								//console.log($(this));
				  							    layer.close(index);
				  							});
				  						}else{
											layer.alert($.i18n(data.message), function(index) {
												layer.close(index);
											});
										}
				  					},
				  					error: function(data) {
				  						layer.alert($.i18n(data.message), function(index) {
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
				layer.alert($.i18n(data.message), function(index) {
					layer.close(index);
				});
	        }
	 	});
	}