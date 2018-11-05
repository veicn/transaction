layui.use(['layer', 'form', 'jquery','laypage', 'upload','table', 'laydate','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var upload = layui.upload,
	laydate = layui.laydate,
	own = layui.own;
	//油种查询
	ports();
	var uuid = own.getHref('shipPactUuid');
	if (!uuid){message($.i18n("JAVASCRIPT0097"));};
	//装港信息
	$('#load').on('click',function(){
		window.location.href=shipServer+"/shipPact/shipLoad.htm?shipPactUuid="+uuid;
	});
	//返回
	$('.returnFn').on('click',function(){
		//window.location.href=shipServer+"/shipPact/shipPactList.htm";
		 layer.confirm($.i18n("JAVASCRIPT0022"), function(index){
			 window.close();
		 });
	});
	//卸港
	$('#unload').on('click',function(){
		window.location.href=shipServer+"/shipPact/shipUnload.htm?shipPactUuid="+uuid;
	});
	//在途
	$('#transit').on('click',function(){
		window.location.href=shipServer+"/shipPact/shipTransit.htm?shipPactUuid="+uuid;
	});
	//加
	$('.haha').on('click',function(){
		var cloneDiv=$(this).parents('.charter-cont2').clone();
		cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
		cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
		cloneDiv.find('.inps').attr('hidden','true');
		cloneDiv.find('input').val('');
		autocomplete(oil,cloneDiv.find('.oilType'));
		$('.all').append(cloneDiv);
	});
	$('.hehe').on('click',function(){
		var cloneDiv=$(this).parents('.charter-cont2').clone();
		cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
		cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
		//cloneDiv.find('.inps').attr('hidden','true');
		cloneDiv.find('input').val('');
		cloneDiv.find('.type1').val("1");
		autocomplete(load,cloneDiv.find('.loadPort'));
		$('.all2').append(cloneDiv);
	});
	$('.heihei').on('click',function(){
		var cloneDiv=$(this).parents('.charter-cont2').clone();
		cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
		cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
		//cloneDiv.find('.inps').attr('hidden','true');
		cloneDiv.find('input').val('');
		cloneDiv.find('.type2').val("2");
		autocomplete(unload,cloneDiv.find('.unloadPort'));
		$('.all3').append(cloneDiv);
	});
	//减
	$('body').on('click',' .w-ico-Less',function(){
		$(this).parents('.charter-cont2').remove();
	});
	
	cdValueSets(6);
	
	function cdValueSets(key){
		
		window.cdArrsFn = [];
		var data = {"type": key};
		$.ajax({
			type: "POST",
			url: shipServer+'/traderNameListForBack.json',
			async:false,
			data: JSON.stringify(data),
			dataType: 'JSON',
			success: function (res) {
				if (res.datas != null){
					for (var i = 0; i < res.datas.length; i++) {
						//cdArrsFn.push(res.datas[i].traderName);
						 $(".sele").append("<option class='' value='"+res.datas[i].traderId+"'>"+res.datas[i].traderName+"</option>");
						 $(".sele2").append("<option class='' value='"+res.datas[i].traderId+"'>"+res.datas[i].traderName+"</option>");
					}
				}
				
			
			},
			headers: {
				'Content-Type': 'application/json;charset=utf-8'
			}
		});
		/*autocomplete(oil,'.zgcd');
		autocomplete(oil,'.xgcd');*/
	};
	
	//提交
	$('#save').on('click',function(){
		var object = {};
		if (!uuid){message($.i18n("JAVASCRIPT0020")); return false;};
		object.shipPactUuid=uuid;
		var arr = [];
		var listAgencyArr = [];
		
		var flag=true;
		$('.charter-cont').each(function(){
			var obj = own.serializeObject($(this).find('.form'));
			/*if (obj.quantity == null || obj.quantity == ''||obj.quantityCask == null || obj.quantityCask == ''){ message('请输入装量');flag=false; return false; };
			if (obj.api == null || obj.api == ''){ message('请输入api');flag=false; return false; };
			if (obj.loadTemp == null || obj.loadTemp == ''){ message('请输入装港温度');flag=false; return false; };
			if (obj.loadDraft == null || obj.loadDraft == ''){ message('请输入装港吃水');flag=false; return false; };
			if (obj.unloadDraft == null || obj.unloadDraft == ''){ message('请输入卸港吃水');flag=false; return false; };*/
			if (obj.oilType == null || obj.oilType == '' ){ message($.i18n("JAVASCRIPT00121"));flag=false; return false; };
			
			// 去除千分位
			// 配载计划装量(桶)
			obj.quantityCask = delNumFormat(obj.quantityCask);
			// 配载计划装量(吨)
			obj.quantity = delNumFormat(obj.quantity);
			// 配载计划API
			obj.api = delNumFormat(obj.api);
			// 配载计划装货温度(℃)
			obj.loadTemp = delNumFormat(obj.loadTemp);
			// 配载计划装港吃水(米)
			obj.loadDraft = delNumFormat(obj.loadDraft);
			// 配载计划卸港吃水(米)
			obj.unloadDraft = delNumFormat(obj.unloadDraft);
			
			arr.push(obj);
		});
		$('.charter-cont-my').each(function(){
			var obj = own.serializeObject($(this).find('.form'));
			if (obj.port == null || obj.port == '' ){ message('请选择装卸港');flag=false; return false; };
			if (obj.name == null || obj.name == '' ){ message('请选择船代');flag=false; return false; };
			var myObj={};
			myObj.id = $(this).find("option:selected").val();
			myObj.port=obj.port;
			myObj.type =obj.type;
			myObj.name=$(this).find("option:selected").text();
			listAgencyArr.push(myObj);
		});
		if (!flag){return fasle;}
		object.list=arr;
		object.listAgency=listAgencyArr;
		
		object.accessory=$("#cunImg").attr('title');
		object.accessoryPath=$("#cunImg").attr('data-address');
		var loadIndex = layer.load(0, {shade: false});
		$.ajax({
			type: "POST",
			url: shipServer+"/voyageStart/saveVoyageStart.json",
			data: JSON.stringify(object),
			dataType: "json",
			contentType:"application/json",
			success: function(data) {
				layer.close(loadIndex);
				if(data.status == 0){
					layer.alert($.i18n("JAVASCRIPT00122"), {title:$.i18n("001"),btn:$.i18n("002")},   function(index) {
						layer.close(index);
						//window.location.href=shipServer+"/shipPact/shipPactList.htm";
					});
				} else {
					message($.i18n(data.message));
				}}});
	});
	
	//初始化查询
	if (uuid != null){
		init(uuid)
	}
	function init(uuid){
		var obj={};
		obj.shipPactUuid=uuid
		$.ajax({
			type: "POST",
			url: shipServer+"/voyageStart/findVoyageStartDeatil.json",
			data: JSON.stringify(obj),
			dataType: "json",
			contentType:"application/json",
			success: function(data) {
				console.log(data)
				if(data.status == 0){
					var arr = data.datas.list;
					var arrOil = data.datas.oilList;
					var arrAgency = data.datas.listAgency;//alert(arrAgency);
					if (data.datas.accessory != null && data.datas.accessoryPath != null){
						$('#cunImg').attr('title',data.datas.accessory).val(data.datas.accessory).attr('data-address',data.datas.accessoryPath);
						$('.xiazai').find('a').attr('href',data.datas.accessoryPath);
						$('.xiazai').show();
					}
					if (arr != null && arr.length > 0){
						for (var i = 0; i<arr.length ; i++){
							if (i == 0){
								$('.oilType').val(arr[i].oilType);
								$('.quantity').val(formatNumber(arr[i].quantity,3,1));
								$('.quantityCask').val(formatNumber(arr[i].quantityCask,2,1));
								$('.api').val(formatNumber(arr[i].api,2,1));
								$('.loadTemp').val(formatNumber(arr[i].loadTemp,2,1));
								$('.loadDraft').val(formatNumber(arr[i].loadDraft,2,1));
								$('.unloadDraft').val(formatNumber(arr[i].unloadDraft,2,1));
							} else {
								var cloneDiv=$('.w-ico-plus:eq(0)').parents('.charter-cont2').clone();
								cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
								cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
								cloneDiv.find('.inps').attr('hidden','true');
								cloneDiv.find('.c-int').val('');
								cloneDiv.find('.oilType').val(arr[i].oilType);
								cloneDiv.find('.quantity').val(formatNumber(arr[i].quantity,3,1));
								cloneDiv.find('.quantityCask').val(formatNumber(arr[i].quantityCask,2,1));
								cloneDiv.find('.api').val(formatNumber(arr[i].api,2,1));
								cloneDiv.find('.loadTemp').val(formatNumber(arr[i].loadTemp,2,1));
								cloneDiv.find('.loadDraft').val(formatNumber(arr[i].loadDraft,2,1));
								cloneDiv.find('.unloadDraft').val(formatNumber(arr[i].unloadDraft,2,1));
								autocomplete(oil,cloneDiv.find('.oilType'));
								$('.all').append(cloneDiv);
							}
						}
					} else if (arrOil != null && arrOil.length > 0 ){
						for (var i = 0; i<arrOil.length ; i++){
							var oilType = arrOil[i];
							if (i == 0){
								$('.oilType').val(oilType);
							} else {
								var cloneDiv=$('.w-ico-plus:eq(0)').parents('.charter-cont2').clone();
								cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
								cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
								cloneDiv.find('.inps').attr('hidden','true');
								cloneDiv.find('.c-int').val('');
								cloneDiv.find('.oilType').val(oilType);
								autocomplete(oil,cloneDiv.find('.oilType'));
								$('.all').append(cloneDiv);
							}
						}
					}if (arrAgency != null && arrAgency.length > 0){
						var a = 0;
						var b = 0;
						for (var i = 0; i<arrAgency.length ; i++){
							var agency = arrAgency[i];//alert(agency.type);
							if (i == 0){
								if(agency.type==1){
									$('.type1').val(agency.type);
									$('.loadPort').val(agency.port);
									//autocomplete(oil,cloneDiv.find('.oilType'));
									$(".sele").val(agency.epMemberId);
									a=1;
								}else if(agency.type==2){
									$('.type2').val(agency.type);
									$('.unloadPort').val(agency.port);
									$(".sele2").val(agency.epMemberId);
									b=1;
								}
							}else{
								if(agency.type==1){
									if(a==0){
										$('.type1').val(agency.type);
										$('.loadPort').val(agency.port);
										$(".sele").val(agency.epMemberId);
										a=1;
									}else{
									var cloneDiv=$('.hehe:eq(0)').parents('.charter-cont2').clone();
									cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
									cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
									//cloneDiv.find('.inps').attr('hidden','true');
									//cloneDiv.find('.c-int').val('');
									$('.all2').append(cloneDiv);
									cloneDiv.find('.type1').val(agency.type);
									cloneDiv.find('.loadPort').val(agency.port);
									autocomplete(load,'.loadPort');
									cloneDiv.find(".sele").val(agency.epMemberId);
									a=1;
									}
									
								}else if(agency.type==2){
									if(b==0){
										$('.type2').val(agency.type);
										$('.unloadPort').val(agency.port);
										$(".sele2").val(agency.epMemberId);
										b=1;	
									}else{
									var cloneDiv=$('.heihei:eq(0)').parents('.charter-cont2').clone();
									cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
									cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
									//cloneDiv.find('.inps').attr('hidden','true');
									//cloneDiv.find('.c-int').val('');
									$('.all3').append(cloneDiv);
									cloneDiv.find('.type2').val(agency.type);
									cloneDiv.find('.unloadPort').val(agency.port);
									autocomplete(unload,'.unloadPort');
									cloneDiv.find(".sele2").val(agency.epMemberId);
									
									b=1;
									}
								}
							}
						}
					}
				} else {
					message($.i18n(data.message));
				}}
		});
	}
	//弹框
	function message(mess){
		layer.alert(mess,  {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
			layer.close(index);
		});
	}
	//装卸港油种维护
	function ports(){
		//查询值集
		valueSets("5;4;10;");
	  	window.load = [];
	  	window.unload = [];
	  	window.oil = [];
	  	if (arrsFn != null){
	  		for (var i = 0; i < arrsFn.length; i++) {
				if (arrsFn[i].type == '5' && arrsFn[i].value != '其它'){
					load.push(arrsFn[i])
				} else if (arrsFn[i].type == '4' && arrsFn[i].value != '其它'){
					unload.push(arrsFn[i])
				} else if (arrsFn[i].type == '10' && arrsFn[i].value != '其它'){ 
					oil.push(arrsFn[i])
				}
			}
	  	}
	  	//console.log(oil)
	  	autocomplete(load,'.loadPort');
	  	autocomplete(unload,'.unloadPort');
	  	autocomplete(oil,'.oilType');
	}
/*	function ports(){
		//查询值集
		valueSets("10;");
	  	window.oil = [];
	  	if (arrsFn != null){
	  		for (var i = 0; i < arrsFn.length; i++) {
				if (arrsFn[i].type == '10' && arrsFn[i].value != '其它'){ 
					oil.push(arrsFn[i])
				}
			}
	  	}
	  	autocomplete(oil,'.oilType');
	}*/
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
	//导出数据
		$('.exportData').on('click',function(){
			//window.location.href=shipServer+"/shipPact/shipPactList.htm";
			var obj={};
			obj.shipPactUuid=uuid;
			obj.type='2';
			$.ajax({
				type: "POST",
				url: shipServer+"/exportVoyageStartData.json",
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
		//导出模板
		$('.export').on('click',function(){
			//window.location.href=shipServer+"/shipPact/shipPactList.htm";
			var obj={};
			obj.type='2';
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
	 fileUp('accessory', '#btn','transport','#cunImg','#accessory');
	//航行开始导入
	  var loadIndex;
	  var uploaderObj = new plupload.Uploader({ //实例化一个plupload上传对象
		browse_button : 'imports',
		url : shipServer+'/importVoyageStart.json',
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
