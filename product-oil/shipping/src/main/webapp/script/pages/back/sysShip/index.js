//search
$("#search").on("click", function () {
    document.getElementById("argFromByAgent").submit();
});

//empty
$("#empty").on("click", function () {
	$("input").val('');
	$(".select-show-text").html('');
});

//删除
$(".del").on('click',function(){
	var uuid = $(this).attr("attr-data");
	layer.confirm('Are you sure to Delete?', {title:'Info', btn:['OK','Cancel']}, function(index){
		$.ajax({
			contentType : 'application/json',
			type:"post",
			url:'/shipping/pages/back/sysShip/delSysShip.json?uuid='+ uuid,
			data: uuid,
			success : function(msg) {
			//	layer.alert(msg);
				if (msg != null) {
					layer.alert(msg.message,function(){
						location.href=location;
					});
				}
			},
			error:function(msg){
				layer.alert("error:" + msg.message);
			}
		})
		})
	})

layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	own = layui.own,
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;

	//获取查询参数
	function getSearchParam(){
		var params = {};
		var uuid = $('#datauuid').val(); //uuid
		params['uuid'] = uuid;
		return params;
	}

	//清空 重置

	//查询
	$('.br btn').on('click', function(){
		var shipNm = $('#shipNm').val();
		
		window.location.href = infoServer+"/shipping/pages/back/sysShip/index.htm"
										 + '?shipNm=' + shipNm;
	})

	//新增
/*	$('.br btn').on('click', function() {
		
		window.location.href="/shipping/pages/back/sysShip/saveSysShip.htm"
		//return false;
	})*/
	
  
	//编辑页面
	$('.editor').on('click',function(){
		
		var params = getSearchParam();
		//  /shipping/pages/back/sysShip/editSysShip.htm?uuid=$!{data.uuid}  
		window.location.href="/shipping/pages/back/sysShip/editSysShip.htm"
										+ "?uuid=" + params.uuid;
			
			
	});
	
	
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

	// 船型选择事件
	form.on('select(typeFilter)', function (data) {
		// 船型值
  		var typeVal = $("#type option:checked").val();
  		// 船型名
  		var typeText = $("#type option:checked").text();
  		if (typeText == "其它") {
  			$("#typeOther").show();
  		} else {
  			$("#typeOther").hide();
  		}
	});
	
	// IMO输入事件
	$('#imo').on('blur',function() {
		var imo = $('#imo').val();
		if (imo) {
			// 初始化MMSI
			initMmsi(imo);
		}
	}); 
	
	// MMSI选择事件
	form.on('select(mmsiFilter)', function (data) {
		// 船型值
  		var mmsiVal = $("#mmsi option:checked").val();
  		// 船型名
  		var mmsiText = $("#mmsi option:checked").text();
  		if (mmsiText == "其它") {
  			$("#mmsiOther").show();
  		} else {
  			$("#mmsiOther").hide();
  		}
  		form.render();
	});
	
	//初始化MMSI
	function initMmsi(imo) {
		// 清空处理
		$("#mmsi option:not(:first)").remove();
		
		// 查询MMSI列表
		var params = {};
		params.imo= imo;
	 	$.ajax({
			type: "POST",
			url: shipServer+"/sysShip/findSysShipMmsi.json",
			data: JSON.stringify(params),
			contentType:"application/json",
			async: false,
			success: function(data) {
				if (data.status == 0) {
					var datas = data.datas;
					if (datas) {
						var mmsi_arr = data.datas.data;
						if (mmsi_arr != null && mmsi_arr.length > 0){
							for(var i =0; i< mmsi_arr.length; i++){
					            var mmsi = mmsi_arr[i];
					            if (mmsi.IMO == imo) {
					            	$("#mmsi").append("<option value='"+mmsi.mmsi+"'>"+mmsi.mmsi+"</option>");
					            }
							}
						}
					}
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
	 	
	 	// MMSI其他
	 	$("#mmsi").append("<option value='other'>其它"+"</option>");
	 	$("#mmsiOther").hide();
	 	
	 	if ($("#mmsi option").size() > 2) {
	 		$("#mmsi").find("option:eq(1)").attr("selected",true);
	 	}
	 	
	 	form.render();
	}
	
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
	
	function testFindShipByImoName() {
		var params = {};
		params.imo = "";
		params.name = "";
		params.type = "";
		params.pageSize = "5";
		params.pageNum ="2";
		$.ajax({
			type: "POST",
			url: shipServer+"/sysShip/findSysShipByImoName.json",
			data: JSON.stringify(params),
			contentType:"application/json",
			async: false,
			success: function(data) {  
					if(data.status == 0){
						alert(JSON.stringify(data.datas));
						alert(data.total);
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
	}

});


//上传文件
var fileInfoList = new Array();	


//加上传
function addFile(fileInfo) {
	var info = {};
	info.path = fileInfo.path;
	info.name = fileInfo.name;
	fileInfoList.push(info);
}


// 减上传
function delFile(filePath) {
	if(filePath != "") {
		for(var i = 0; i < fileInfoList.length; i++) {
			if(filePath == fileInfoList[i].path) {
				fileInfoList.splice(i, 1);
				break;
			}
		}
	}
	//alert(JSON.stringify(fileInfoList));
}
