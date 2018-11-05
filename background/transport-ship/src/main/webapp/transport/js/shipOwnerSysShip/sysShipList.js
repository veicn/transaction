layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate','own','upload'], function(){	
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	own = layui.own,
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;

	//页面记录的uuid
	var uuid = "";
	
	
	$('.searchBtn').on('click',function(){
		search();
	}); 
	
	//回车查询
	/*$("body").keydown(function() {
		if (event.keyCode == "13") {//keyCode=13是回车键
			 $('.searchBtn').click();
		}
	});*/
	
	//查询函数
	function search(){
		var searchName = $.trim($('#searchName').val());
		var searchStatus = $.trim($('#searchStatus').val());
		window.location.href=shipServer+"/shipOwnerSysShip/sysShipList.htm?name="+searchName+"&status="+searchStatus;
	}
	//条件回显
	var searchData = getQueryString('name');
	if (searchData != null && searchData !=''){
		while(searchData.indexOf("+")>-1){
			searchData=searchData.replace("+"," ");
		}
		$('#searchName').val($.trim(searchData));
	}
	var searchStatusData = getQueryString('status');
	if (searchStatusData != null && searchStatusData !=''){
		while(searchStatusData.indexOf("+")>-1){
			searchStatusData=searchStatusData.replace("+"," ");
		}
		$('#searchStatus').val(searchStatusData);
	}
	

	
	//新规页面
	$('.insertBtn').on('click',function(){
		window.location.href=shipServer+"/shipOwnerSysShip/addSysShip.htm";
	});
	
	
	//导出模板
	$('.exportBtn').on('click',function(){
		var obj={};
		obj.type='6';	//导出类型（1船在途）（2配载计划）（3装港信息）（4卸港信息）（5船盘信息）（6船舶信息）
		$.ajax({
			type: "POST",
			url: shipServer+"/exportSysShipTemple.json",
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
	
	
	//批量导入船舶
	var loadIndex;
	var uploaderObj = new plupload.Uploader({ //实例化一个plupload上传对象
		browse_button : 'batchImportBtn',
		url : shipServer+'/importSysShipData.json',
		headers:{'Authorization':""},
		flash_swf_url : 'js/Moxie.swf',
		silverlight_xap_url : 'js/Moxie.xap',
		multi_selection:false,
		multipart_params: {'type':"6"}	//6船舶信息：船东/经纪人
	});
	uploaderObj.init(); //初始化
	//开始上传
	uploaderObj.bind('FilesAdded', function(up, files) {
		loadIndex = layer.load(0, {shade: false});
		uploaderObj.start(); 
	});
	//上传完成
	uploaderObj.bind('FileUploaded',function(uploader,file){
		$.ajaxFn({
			type : "POST",
			url : shipServer+"/import/returnFlag.json",
			data:{},
			success : function(data) {
				layer.close(loadIndex);
				if(data.status == 0){
					layer.alert($.i18n(data.datas), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
						layer.close(index);
						location.reload();
					});
				}else{
					message($.i18n(data.message));
				}
			},
			error:function(data){
				layer.close(loadIndex);
				layer.alert($.i18n("JAVASCRIPT0027"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
					layer.close(index);
					location.reload();
				});
			}
		});
	});
	
	
	//查看页面
	$('.lookBtn').on('click',function(){
		uuid = $(this).attr("attr-data");
		window.location.href=shipServer+"/shipOwnerSysShip/lookSysShip.htm?uuid="+uuid;
	});
	
	
	
	//删除
	$('.delBtn').on('click',function(){
		uuid = $(this).attr("attr-data");
		var params = {};
    	params.uuid = uuid;
    	
		// 校验船舶状态
		$.ajax({
    		type: "POST",
    		url: shipServer+"/sysShip/findSysShipStatus.json",
    		data: JSON.stringify(params),
			contentType:"application/json",
			async: false,
    		success: function(data) {
				if(data.status == 0){
					var status = [];
					status = data.datas;
					// 判断是否关联船盘
					if (status != null && status.length > 0) {
						layer.alert($.i18n("JAVASCRIPT0086"), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
						    layer.close(index);
						});
						return;
					} else {
						//弹框运行
					    layer.confirm($.i18n("JAVASCRIPT0018"),{title:$.i18n("001"),btn:[$.i18n("002"),$.i18n("003")]}, function(index){
					    	$.ajax({
					    		type: "POST",
					    		url: shipServer+"/sysShip/delSysShip.json",
					    		data: JSON.stringify(params),
								contentType:"application/json",
								async: false,
					    		success: function(data) {
									if(data.status == 0){
										layer.alert($.i18n("JAVASCRIPT0019"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
										    layer.close(index);
										    location.reload();
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
					}
				}
			},
			error: function(data) {
				layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
					layer.close(index);
				});
	        }
    	});
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

});
