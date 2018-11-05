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
		lang: lang_ver,
	    elem: '.timeNow' //指定元素
	   ,type: 'time'
	   ,format: 'HH:mm'
	})
	laydate.render({
		lang: lang_ver,
		elem: '.dateNow' //指定元素
	})
	var uuid = own.getHref('shipPactUuid');
	if (!uuid){message($.i18n("JAVASCRIPT0097")); return false;};
	
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
		 layer.confirm($.i18n("JAVASCRIPT0018"), {title:$.i18n("001"),btn:[$.i18n("002"),$.i18n("003")]}, function(index){
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
							layer.alert($.i18n("JAVASCRIPT00133"),  {title:$.i18n("001"),btn:$.i18n("002")}, function(index) { 	layer.close(index);
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
		window.location.href=shipServer+"/shipPactNew/shipLogistics.htm?shipPactUuid="+uuid;
	});
	//卸港
	$('#unload').on('click',function(){
		window.location.href=shipServer+"/shipPactNew/shipUnload.htm?shipPactUuid="+uuid;
	});
	//装港
	$('#load').on('click',function(){
		window.location.href=shipServer+"/shipPactNew/shipLoad.htm?shipPactUuid="+uuid;
	});
	//新增
	$('.addFn').on('click',function(){
		$('.transit2').show();
		alertInit();
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
								$('.unloadPort').val(dd[i].name).attr('code',dd[i].code).attr('nameFn',dd[i].name);
							} else {
								var cloneDiv=$('.s-icon-plus:eq(0)').parents('.s-form-item').clone();
								cloneDiv.find('.btnIcon').removeClass('s-icon-plus');
								cloneDiv.find('.btnIcon').addClass('s-icon-less');
								cloneDiv.find('.s-input').val('');
								autocomplete(unload,cloneDiv.find('.unloadPort'));
								cloneDiv.find('.unloadPort').val(dd[i].name).attr('code',dd[i].code).attr('nameFn',dd[i].name) ;
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
				lang: lang_ver,
			    elem: $(this).get(0) //指定元素
			   ,type: 'datetime'
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
	$('.s-icon-plus').on('click',function(){
		var cloneDiv=$(this).parents('.s-form-item').clone();
		cloneDiv.find('.btnIcon').removeClass('s-icon-plus');
		cloneDiv.find('.btnIcon').addClass('s-icon-less');
		cloneDiv.find('.s-input').val('');
		autocomplete(unload,cloneDiv.find('.unloadPort'));
		$('.all').append(cloneDiv);
		more();
	});
	
	//减
	$('body').on('click',' .s-icon-less',function(){
		$(this).parents('.s-form-item').remove();
	});
	//提交
	$('.save').on('click',function(){
		
		var flag=true;
		if (!uuid){message($.i18n("JAVASCRIPT0020")); return false;};
		var obj = own.serializeObject($('.form2'));
		obj.shipPactUuid=uuid;
		obj.uuid = uuids;
		var unloadEta= "";
		$('.xiegangList').each(function(){
			var unloadPort = $(this).find('.unloadPort').val();
			var unloadPortCode = $(this).find('.unloadPort').attr('code');
			var unloadPortName = $(this).find('.unloadPort').attr('nameFn');
			var eta = $(this).find('.eta').val();
			if (unloadPort == null || unloadPort == ''){message($.i18n("JAVASCRIPT00134")); flag = false;return false;}
			if (unloadPort != unloadPortName){
				unloadPortCode = unloadPort;
			}
			if (eta == null || eta == '' ){message($.i18n("JAVASCRIPT00135")); flag = false;return false;}
			unloadEta=unloadEta+unloadPortCode+"/"+eta+";";
		});
		
		//图片
		var objArr = [];
		$('.imgsAll').find('li').each(function(){
			var img = $(this).find('a').attr('data-address');
			if (img != null && img != ''){
				objArr.push(img);
			}
		});
		var flags = check(obj);
		if(!flags || !flag){ return false; }
		obj.unloadEtaCode=unloadEta;
		obj.timeNow=obj.timeNow;
		obj.dateNow=new Date(obj.dateNow.replace(new RegExp(/-/gm) ,"/"));
		obj.imgList = objArr;
		obj.accessory=$("#file1").attr('title');
		obj.accessoryPath=$("#file1").attr('data-address');
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
					layer.alert($.i18n("JAVASCRIPT00136"),  {title:$.i18n("001"),btn:$.i18n("002")}, function(index) { 	layer.close(index);
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
		alertInit();
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
					var imgList = dd.imgList;
					var lists = dd.lists;
					if (data.datas.accessory != null && data.datas.accessoryPath != null){
						$('#file1').attr('title',data.datas.accessory).val(data.datas.accessory).attr('data-address',data.datas.accessoryPath);
					}
					$('.dateNow').val(new Date(dd.dateNow).Format('yyyy-MM-dd')).attr('title',new Date(dd.dateNow).Format('yyyy-MM-dd'));
					$('.timeNow').val(dd.timeNow).attr('title',dd.timeNow);
					$('.position').val(dd.position).attr('title',dd.position);
					$('.speedH').val(dd.speedH).attr('title',dd.speedH);
					$('.speedAll').val(dd.speedAll).attr('title',dd.speedAll);
					$('.rpm').val(dd.rpm).attr('title',dd.rpm);
					$('.sea').val(dd.sea).attr('title',dd.sea);
					$('.water').val(dd.water).attr('title',dd.water);
					$('.sulfide').val(dd.sulfide).attr('title',dd.sulfide);
					$('.remark').val(dd.remark).attr('title',dd.remark);
					//图片
					if (imgList != null){
						for (var i2 = 0; i2<imgList.length ; i2++){
							$('.imgClass').parent('li').before('<li><img src="'+imgList[i2]+'"><a href="javascript:;" class="s-ent-close imgEach" title="" data-address="'+imgList[i2]+'"><i class="s-icon"></i></a></li>');
						}
					}
					//回显下拉框
					var unloadPortList = dd.unloadPortList;
					if (lists != null && lists.length > 0){
						for (var i = 0; i <lists.length ; i++){
							if ( i == 0){
								$('.unloadPort').val(lists[i].name).attr('code',lists[i].code).attr('nameFn',lists[i].name);
								$('.eta').val(lists[i].eta);
							} else {
								var cloneDiv=$('.s-icon-plus:eq(0)').parents('.s-form-item').clone();
								cloneDiv.find('.btnIcon').removeClass('s-icon-plus');
								cloneDiv.find('.btnIcon').addClass('s-icon-less');
								cloneDiv.find('.s-input').val('');
								autocomplete(unload, cloneDiv.find('.unloadPort'));
								cloneDiv.find('.unloadPort').val(lists[i].name).attr('code',lists[i].code).attr('nameFn',lists[i].name);
								cloneDiv.find('.eta').val(lists[i].eta);
								$('.all').append(cloneDiv);
								more();
							}
						}
					} else if (unloadPortList != null && unloadPortList.length > 0){
						for (var i = 0; i <unloadPortList.length ; i++){
							if ( i == 0){
								$('.unloadPort').val(unloadPortList[i].name).attr('code',unloadPortList[i].code).attr('nameFn',unloadPortList[i].name);
							} else {
								var cloneDiv=$('.s-icon-plus:eq(0)').parents('.s-form-item').clone();
								cloneDiv.find('.btnIcon').removeClass('s-icon-plus');
								cloneDiv.find('.btnIcon').addClass('s-icon-less');
								cloneDiv.find('.s-input').val('');
								autocomplete(unload, cloneDiv.find('.unloadPort'));
								cloneDiv.find('.unloadPort').val(unloadPortList[i].name).attr('code',unloadPortList[i].code).attr('nameFn',unloadPortList[i].name)
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
	
	function alertInit(){
		$(".s-mod-bcont .s-inline").each(function(){
			  var father_width = $(this).width();
			  var first_width = $(this).children(":nth(0)").width();
			  $(this).children(":nth(1)").css("width",father_width-first_width-20);
		   
		  });
	}
	//校验
	function check(obj){
		var flag= true;
		if (obj.dateNow == null || obj.dateNow == ''){ message($.i18n("JAVASCRIPT001"));flag=false; return false; };
		if (obj.timeNow == null || obj.timeNow == ''){ message($.i18n("JAVASCRIPT00221"));flag=false; return false; };
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
	  		
	  fileUp('accessory1', '#btn1','transport','#file1','#accessory1');
	  fileUpFn('imgDiv1', '#img1','transport','#img1','#imgDiv1');
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
