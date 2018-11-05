layui.use(['layer', 'form', 'jquery','laypage', 'upload','table', 'laydate','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var upload = layui.upload,
	laydate = layui.laydate,
	own = layui.own;
	var uuids ="";
	laydate.render({
	    elem: '.timeNow' //指定元素
	   ,type: 'time'
	   ,lang: lang_ver
	   ,format: 'HH:mm'
	})
	laydate.render({
		elem: '.dateNow' //指定元素
		,lang: lang_ver
	})
	var uuid = own.getHref('shipPactUuid');
	if (!uuid){message($.i18n("JAVASCRIPT0020")); return false;};
	
	//返回
	$('.returnFn').on('click',function(){
		//window.location.href=shipServer+"/shipPact/shipPactList.htm";
		 layer.confirm($.i18n("JAVASCRIPT0022"), {title:$.i18n("001"),btn:[$.i18n("002"),$.i18n("003")]}, function(index){
			 window.close();
		 });
	});
	//导出模板
	$('.export').on('click',function(){
		//window.location.href=shipServer+"/shipPact/shipPactList.htm";
		var obj={};
		obj.type='1';
		$.ajax({
			type: "POST",
			url: shipServer+"/export.json",
			data: JSON.stringify(obj),
			dataType: "json",
			contentType:"application/json",
			success: function(data) {
				if(data.status == 0){
					window.location.href=data.datas;
				} else {
					message($.i18n(data.message));
				}}
		});
	});
	//导出数据
	$('.exportData').on('click',function(){
		//window.location.href=shipServer+"/shipPact/shipPactList.htm";
		var obj={};
		obj.shipPactUuid=uuid;
		obj.type='1';
		$.ajax({
			type: "POST",
			url: shipServer+"/exportTrasitData.json",
			data: JSON.stringify(obj),
			dataType: "json",
			contentType:"application/json",
			success: function(data) {
				if(data.status == 0){
					window.location.href=data.datas;
				} else {
					message($.i18n(data.message));
				}}
		});
	});
	//删除
	$('.delete').on('click',function(){
		var tranUuid = $(this).attr('data-uuid');
		 layer.confirm($.i18n("JAVASCRIPT0018"),{title:$.i18n("001"),btn:[$.i18n("002"),$.i18n("003")]}, function(index){
			 var obj={};
				obj.uuid=tranUuid
				$.ajax({
					type: "POST",
					url: shipServer+"/transit/deleteTransitByUuid.json",
					data: JSON.stringify(obj),
					dataType: "json",
					contentType:"application/json",
					success: function(data) {
						if(data.status == 0){
							layer.alert($.i18n("JAVASCRIPT00133"), {title:$.i18n("001"),btn:$.i18n("002")},   function(index) { 	layer.close(index);
							location.reload();
						});
						} else {
							message($.i18n(data.message));
						}}
				});
		 });
	});
	//航次开始信息
	$('#start').on('click',function(){
		window.location.href=shipServer+"/shipPact/shipLogistics.htm?shipPactUuid="+uuid;
	});
	//卸港
	$('#unload').on('click',function(){
		window.location.href=shipServer+"/shipPact/shipUnload.htm?shipPactUuid="+uuid;
	});
	//装港
	$('#load').on('click',function(){
		window.location.href=shipServer+"/shipPact/shipLoad.htm?shipPactUuid="+uuid;
	});
	//新增
	$('.addFn').on('click',function(){
		$('.transit2').show();
		//查询卸港
		ports();
		uuids ='';
		var obj={};
		obj.shipPactUuid=uuid
		$.ajax({
			type: "POST",
			url: shipServer+"/transit/unloadList.json",
			data: JSON.stringify(obj),
			dataType: "json",
			contentType:"application/json",
			success: function(data) {
				if(data.status == 0){
					var dd = data.datas;
					 if (dd != null && dd.length > 0){
						for (var i = 0; i <dd.length ; i++){
							if ( i == 0){
								$('.unloadPort').val(dd[i]);
							} else {
								var cloneDiv=$('.w-ico-plus:eq(0)').parents('.charter-add2').clone();
								cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
								cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
								cloneDiv.find('input').val('');
								autocomplete(unload,cloneDiv.find('.unloadPort'));
								cloneDiv.find('.unloadPort').val(dd[i]) ;
								$('.all').append(cloneDiv);
								more();
							}
						}
					}
				} else {
					message($.i18n(data.message));
				}}
		});
	
	});
	//取消编辑
	$('.cancel').on('click',function(){
		$('.add-pop').hide();
		location.reload();
	});
	//日期；初始化
	function startDate(){
		$('.time').each(function(){
			laydate.render({
			    elem: $(this).get(0) //指定元素
			   ,type: 'datetime'
			   ,lang: lang_ver
			   ,format: 'yyyy-MM-dd HH:mm'
			})
		})
	}
	startDate();
	function more(){
		$('.time').each(function(index){ 
			$(this).attr('lay-key',new Date().getTime()+index);
		});
		startDate();
	}
	//加
	$('.w-ico-plus').on('click',function(){
		var cloneDiv=$(this).parents('.charter-add2').clone();
		cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
		cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
		cloneDiv.find('input').val('');
		autocomplete(unload,cloneDiv.find('.unloadPort'));
		$('.all').append(cloneDiv);
		more();
	});
	
	//减
	$('body').on('click',' .w-ico-Less',function(){
		$(this).parents('.charter-add2').remove();
	});
	//提交
	$('.save').on('click',function(){
		
		var flag=true;
		if (!uuid){message($.i18n("JAVASCRIPT0020")); return false;};
		var obj = own.serializeObject($('.form'));
		obj.shipPactUuid=uuid;
		obj.uuid = uuids;
		var unloadEta= "";
		$('.charter-add2').each(function(){
			var unloadPort = $(this).find('.unloadPort').val();
			var eta = $(this).find('.eta').val();
			if (unloadPort == null || unloadPort == ''){message($.i18n("JAVASCRIPT00134")); flag = false;return false;}
			if (eta == null || eta == '' ){message($.i18n("JAVASCRIPT00135")); flag = false;return false;}
			unloadEta=unloadEta+unloadPort+"/"+eta+";";
		});
		
		/*// 去除千分位
		// 平均速度(24H)
		obj.speedH = delNumFormat(obj.speedH);
		// 平均速度（全程）
		obj.speedAll = delNumFormat(obj.speedAll);
		// RPM( 转/每分 )
		obj.rpm = delNumFormat(obj.rpm);*/
		
		var flags = check(obj);
		if(!flags || !flag){ return false; }
		obj.unloadEta=unloadEta;
		obj.timeNow=obj.timeNow;
		obj.dateNow=new Date(obj.dateNow.replace(new RegExp(/-/gm) ,"/"));
		obj.accessory=$("#file").attr('title');
		obj.accessoryPath=$("#file").attr('data-address');
		var loadIndex = layer.load(0, {shade: false});
		$.ajax({
			type: "POST",
			url: shipServer+"/transit/saveTransit.json",
			data: JSON.stringify(obj),
			dataType: "json",
			contentType:"application/json",
			success: function(data) {
				layer.close(loadIndex);
				if(data.status == 0){
					layer.alert($.i18n("JAVASCRIPT00136"), {title:$.i18n("001"),btn:$.i18n("002")},   function(index) { 	layer.close(index);
						location.reload();
					});
				} else {
					message($.i18n(data.message));
				}}
		});
	});
	//修改
	$('.edit').on('click',function(){
		uuids = $(this).attr('data-uuid');
		$('.transit2').show();
		edit(uuids)
		
	});
	//修改回显
	function edit(uuid){
		//查询卸港
		ports();
		var obj={};
		obj.uuid=uuid
		$.ajax({
			type: "POST",
			url: shipServer+"/transit/findTransitDeatil.json",
			data: JSON.stringify(obj),
			dataType: "json",
			contentType:"application/json",
			success: function(data) {
				console.log(data)
				if(data.status == 0){
					var dd = data.datas;
					if (data.datas.accessory != null && data.datas.accessoryPath != null){
						$('#file').attr('title',data.datas.accessory).val(data.datas.accessory).attr('data-address',data.datas.accessoryPath);
					}
					$('.dateNow').val(new Date(dd.dateNow).Format('yyyy-MM-dd'));
					$('.timeNow').val(dd.timeNow);
					$('.position').val(dd.position);
					$('.speedH').val(dd.speedH);
					$('.speedAll').val(dd.speedAll);
					$('.rpm').val(dd.rpm);
					$('.sea').val(dd.sea);
					$('.water').val(dd.water);
					$('.sulfide').val(dd.sulfide);
					$('.remark').val(dd.remark);
					//回显下拉框
					var unloadEta = dd.unloadEta;
					var unloadPortList = dd.unloadPortList;
					if (unloadEta != null && unloadEta.length > 0){
						var arr1 = unloadEta.split(';');
						for (var i = 0; i <arr1.length-1 ; i++){
							var arr2 = arr1[i].split('/');
							if ( i == 0){
								$('.unloadPort').val(arr2[0])
								$('.eta').val(arr2[1]);
							} else {
								var cloneDiv=$('.w-ico-plus:eq(0)').parents('.charter-add2').clone();
								cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
								cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
								cloneDiv.find('input').val('');
								autocomplete(unload, cloneDiv.find('.unloadPort'));
								cloneDiv.find('.unloadPort').val(arr2[0])
								cloneDiv.find('.eta').val(arr2[1]);
								$('.all').append(cloneDiv);
								more();
							}
						}
					} else if (unloadPortList != null && unloadPortList.length > 0){
						for (var i = 0; i <unloadPortList.length ; i++){
							if ( i == 0){
								$('.unloadPort').val(unloadPortList[i])
							} else {
								var cloneDiv=$('.w-ico-plus:eq(0)').parents('.charter-add2').clone();
								cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
								cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
								cloneDiv.find('input').val('');
								autocomplete(unload, cloneDiv.find('.unloadPort'));
								cloneDiv.find('.unloadPort').val(unloadPortList[i])
								$('.all').append(cloneDiv);
								more();
							}
						}
					}
				} else {
					message($.i18n(data.message));
				}}
		});
	}
	//查询装港，商检，船代
	function ports(){
		//查询值集
		valueSets("4;");
		window.unload = [];
		if (arrsFn != null){
			for (var i = 0; i < arrsFn.length; i++) {
				if (arrsFn[i].type == '4' && arrsFn[i].value != '其它'){
					unload.push(arrsFn[i])
				}
			}
		}
		autocomplete(unload,'.unloadPort');
	}
	//弹框
	function message(mess){
		layer.alert(mess,  {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
			layer.close(index);
		});
	}
	//校验
	function check(obj){
		var flag= true;
		if (obj.dateNow == null || obj.dateNow == ''){ message($.i18n("JAVASCRIPT001"));flag=false; return false; };
		if (obj.timeNow == null || obj.timeNow == ''){ message('请选择时间');flag=false; return false; };
		if (obj.position == null || obj.position == ''){ message($.i18n("JAVASCRIPT00137"));flag=false; return false; };
		if (obj.speedH == null || obj.speedH == ''){ message($.i18n("JAVASCRIPT00138"));flag=false; return false; };
		if (obj.speedAll == null || obj.speedAll == ''){ message($.i18n("JAVASCRIPT00139"));flag=false; return false; };
		if (obj.rpm == null || obj.rpm == ''){ message($.i18n("JAVASCRIPT00140"));flag=false; return false; };
		if (obj.sea == null || obj.sea == ''){ message($.i18n("JAVASCRIPT00141"));flag=false; return false; };
		return flag;
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
	

	/*文件 上传*/
	  var temp = '<p>';
	  	temp = temp + '<input type="hidden" class="storePath" value="{{filePath}}" />';
		temp = temp + '<input type="hidden" class="fileName" value="{{fileName}}" />';
		temp = temp + '<input type="hidden" class="fileSize" value="{{fileSize}}" />';
		temp = temp + '<input type="hidden" class="storeName" value="{{fileId}}" /></p>'; 
	  
	  function fileUp(id, clazz,dir,file,id2) {
				var options = {
					multi_selection: false, //设置是否多选
					listId: id, 
					itemTemplate: temp, //设置模板
					signatureUrl: configUitl.getOssServerPath(shipServer,dir),
					dir: dir,
					filters: {
						max_file_size: '20M',
						mime_types: [ //上传文件后缀名
							{
								extensions: "pdf,txt,jpg,xls,xlsx,docx,doc"
							}
						],
					},
					swfUrl: '../../lib/upload/Moxie.swf',
					xapUrl: '../../lib/upload/Moxie.xap',
					success: function(imgUrl) {
						$(file).attr('data-address',imgUrl).val($(id2).find('.fileName').val()).attr('title',$(id2).find('.fileName').val());
					}
				};
				$(clazz).bsPlupload(options);
			}
	  		
	 fileUp('accessory', '#btn','transport','#file','#accessory');
	 
	//在途导入
	  var loadIndex;
	  var uploaderObj = new plupload.Uploader({ //实例化一个plupload上传对象
		browse_button : 'imports',
		url : shipServer+'/import.json',
		headers:{'Authorization':""},
		flash_swf_url : 'js/Moxie.swf',
		silverlight_xap_url : 'js/Moxie.xap',
		multi_selection:false,
	    multipart_params: {'uuid':uuid }  
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
					layer.alert(data.datas,  {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
						layer.close(index);
						location.reload();
					});
				}else{
					message($.i18n(data.message));
				}
			}
		});
	});
});
