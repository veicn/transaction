layui.use(['layer', 'form', 'jquery','laypage', 'upload','table', 'laydate','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var upload = layui.upload,
	laydate = layui.laydate,
	own = layui.own;
	var typeFn =own.getHref('type');
	if (!typeFn){
		$('.hiddens').removeAttr('hidden','hidden');
	}
	//返回
	$('.returnFn').on('click',function(){
		//window.location.href=shipServer+"/shipPact/shipPactList.htm";
		 layer.confirm($.i18n("JAVASCRIPT0022"), {title:$.i18n("001"),btn:[$.i18n("002"),$.i18n("003")]}, function(index){
			 window.close();
		 });
	});
	//航次开始信息
	$('#start').on('click',function(){
		window.location.href=shipServer+"/shipPactNew/shipLogistics.htm?shipPactUuid="+uuid;
	});
	//卸港
	$('#unload').on('click',function(){
		if (typeFn){
			window.location.href=shipServer+"/shipPactNew/shipUnload.htm?shipPactUuid="+uuid+"&type=1";
		} else {
			window.location.href=shipServer+"/shipPactNew/shipUnload.htm?shipPactUuid="+uuid;
		}
	});
	//在途
	$('#transit').on('click',function(){
		window.location.href=shipServer+"/shipPactNew/shipTransit.htm?shipPactUuid="+uuid;
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
//	startDate();
	function more(){
		$('.time').each(function(index){
			
			$(this).attr('lay-key',new Date().getTime()+index);
		});
		startDate();
	}
	//查询装港，商检，船代
	ports();
	
	//加
	$('.s-icon-plus').on('click',function(){
		var cloneDiv=$(this).parents('.s-mod-block').clone();
		cloneDiv.find('.btnIcon').removeClass('s-icon-plus');
		cloneDiv.find('.btnIcon').addClass('s-icon-less');
		cloneDiv.find('.s-input').val('');
		
		//去除“导出数据”按钮
		cloneDiv.find('.exportData').hide();
		
		autocomplete(load,cloneDiv.find('.loadPort'));
		autocomplete(checks,cloneDiv.find('.inspection'));
		autocomplete(agent,cloneDiv.find('.agency'));
		
		//复选框
		cloneDiv.find('.s-uncheckbox').removeClass('s-form-checked');
		
		//附件
		var indexs = $('.s-mod-block').length+1;
		cloneDiv.find('.accessory1').attr('id','file'+indexs);
		cloneDiv.find('.accessory1').next('a').attr('id','btn'+indexs);
		var accessoryIds= "accessory"+indexs;
		$('.accDivs').append('<div id="'+accessoryIds+'"  style="display:none;"></div>')
		
		//图片
		cloneDiv.find('.imgsAll').find('li').remove();
		var ids = "img"+indexs;
		var idDivs = "imgDiv"+indexs;
		var urls=shipServer+'/transport/mycrudeoil/images/'+$.i18n('JAVASCRIPT00240');
		cloneDiv.find('.imgsAll').append('<li><a href="javascript:;" id="'+ids+'" class="imgClass"><img src="'+urls+'"></a></li>');
		$('.accDivs').append('<div id="'+idDivs+'"  style="display:none;"></div>')
		$('.all').append(cloneDiv);
		fileUp('accessory'+indexs, '#btn'+indexs,'transport','#file'+indexs,'#accessory'+indexs);
		fileUpFn('imgDiv'+indexs, '#img'+indexs,'transport','#img'+indexs,'#imgDiv'+indexs);
		more();
	});
	
	//减
	$('body').on('click','.s-icon-less',function(){
		$(this).parents('.s-mod-block').remove();
	});
	
	var uuid = own.getHref('shipPactUuid');
	if (!uuid){message($.i18n("JAVASCRIPT0097")); return false;};
	queryAgreement(uuid);
	//提交
	$('#save').on('click',function(){
		var flag=true,object = {},arr = [];
		if (!uuid){message($.i18n("JAVASCRIPT0097")); return false;};
		object.shipPactUuid=uuid;
		$('.s-mod-block').each(function(){
			var obj = own.serializeObject($(this).find('.form'));
			//协议
			var str="";
			$(this).find('.checks').each(function(){
				if ($(this).find('.s-uncheckbox').hasClass('s-form-checked')){
					str=str+$(this).find('input').val()+";";
				}
			});
			obj.agreementUuid=str;
			obj.accessory1Path=$(this).find('.accessory1').attr('data-address');
			obj.accessory1=$(this).find('.accessory1').attr('title');
			//装卸港
			var loadPortCode  =  $(this).find('.loadPort').attr('code');
			obj.loadPortCode = loadPortCode;
			obj.oilTypeCode = $(this).find('.oilType').val();
			
			//图片
			var objArr = [];
			$(this).find('.imgsAll').find('li').each(function(){
				var img = $(this).find('a').attr('data-address');
				if (img != null && img != ''){
					objArr.push(img);
				}
			});
			obj.imgList = objArr;
			//校验
			var flagObj = check(obj); 
			if (!flagObj){ flag = false; return false;}
			var loadPortName  =  $(this).find('.loadPort').attr('nameFn');
			if (obj.loadPort != loadPortName){
				obj.loadPortCode = obj.loadPort;
			}
			obj=newDateObj(obj);
			arr.push(obj);
		});
		if(!flag){ return false; }
		object.list=arr;
		var loadIndex = layer.load(0, {shade: false});
		$.ajax({
			type: "POST",
			url: shipServer+"/loadPort/saveLoadPort.json",
			data: JSON.stringify(object),
			dataType: "json",
			contentType:"application/json",
			success: function(data) {
				layer.close(loadIndex);
				//console.log(data)
				if(data.status == 0){
					layer.alert($.i18n("JAVASCRIPT00123"),  {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
						layer.close(index);
						//window.location.href=shipServer+"/shipPact/shipPactList.htm";
					});
				} else {
					message($.i18n(data.message));
				}}
		});
	});
	//查询协议编号
	function queryAgreement(uuid){
		var obj ={};
		obj.shipPactUuid=uuid;
		$.ajax({
			type: "POST",
			url: shipServer+"/waybill/queryWaybillList.json",
			data: JSON.stringify(obj),
			dataType: "json",
			contentType:"application/json",
			async:false,
			success: function(data) { 
				//console.log(data)
				if(data.status == 0){
					var arr = data.datas;
					if (arr != null && arr.length > 0){
						for(var i=0;i<arr.length;i++){ 
							var agreementCode = arr[i].agreementCode;
							var agreementUuid = arr[i].agreementUuid;
							/*$('.agreementCode').append("<p class='char-check '><input class='agree' type='checkbox' name='agreementUuid' value='"+agreementUuid+"'><span>"+agreementCode+"</span></p>");*/
							$('.agreementCode').append(" <div class='s-input-inline s-input-check checks'><input type='checkbox' title='' value='"+agreementUuid+"'><div class='s-uncheckbox s-form-checkbox' lay-skin='primary' title='"+agreementCode+"'><i class='fs-checkbox s-i-check'></i><span class='s-span-check'>"+agreementCode+"</span></div></div>");
						}
					}}}});
		//查询油种
		$.ajax({
			type: "POST",
			url: shipServer+"/voyageStart/findOilList.json",
			data: JSON.stringify(obj),
			dataType: "json",
			contentType:"application/json",
			async:false,
			success: function(data) { 
				if(data.status == 0){
					var arr = data.datas;
					if (arr != null && arr.length > 0){
						for(var i=0;i<arr.length;i++){ 
							$(".oilType").append("<option class='oilTypeOpp' value='"+arr[i].code+"'>"+arr[i].name+"</option>");						}
					}}}});
	}
	if (uuid != null){
		init(uuid)
	}
	//初始化查询
	function init(uuid){
		var obj={};
		obj.shipPactUuid=uuid
		$.ajax({
			type: "POST",
			url: shipServer+"/loadPort/findLoadPortDeatil.json",
			data: JSON.stringify(obj),
			dataType: "json",
			contentType:"application/json",
			success: function(data) {
				
				//console.log(data)
				if(data.status == 0){
					var arr = data.datas.list;
					var loadPortList = data.datas.map.loadPortList;
					var loadAgreeMap = data.datas.map.loadAgreeMap;
					if (arr != null && arr != "" && arr.length>0){
						for (var i = 0; i<arr.length ; i++){
							var agreeUuid = arr[i].agreementUuid;
							var imgList = arr[i].imgList;
							var arrs =[];
							if (agreeUuid != null){
								arrs = agreeUuid.split(";");
							}
							if (i == 0){
								for (var j = 0 ; j<arrs.length ;j++){
									$('.checks').each(function(){
										if ($(this).find('input').val() == arrs[j]){ $(this).find('.s-uncheckbox').addClass('s-form-checked'); }
									});
								}
								$('.loadPort').val(arr[i].loadPort).attr('code',arr[i].loadPortCode).attr('nameFn',arr[i].loadPort);
								$('.inspection').val(arr[i].inspection);
								$('.agency').val(arr[i].agency);
								//日期框回显
								$('.oilType').val(arr[i].oilTypeCode);
								$('.eta').val(date(arr[i].eta));
								$('.exBerth').val(date(arr[i].exBerth));
								$('.norDate').val(date(arr[i].norDate));
								$('.waterDate').val(date(arr[i].waterDate));
								$('.atripDate').val(date(arr[i].atripDate));
								$('.berthDate').val(date(arr[i].berthDate));
								$('.acStart').val(date(arr[i].acStart));
								$('.acFinish').val(date(arr[i].acFinish));
								$('.exLeave').val(date(arr[i].exLeave));
								$('.acLeave').val(date(arr[i].acLeave));
								$('.remTubeDate').val(date(arr[i].remTubeDate));
								$('.accessory1').attr('title',arr[i].accessory1).val(arr[i].accessory1).attr('data-address',arr[i].accessory1Path);
								//图片
								if (imgList != null){
									for (var i2 = 0; i2<imgList.length ; i2++){
										$('.imgClass').parent('li').before('<li><img src="'+imgList[i2]+'"><a href="javascript:;" class="s-ent-close imgEach" title="" data-address="'+imgList[i2]+'"><i class="s-icon"></i></a></li>');
									}
								}
							} else {
								var cloneDiv=$('.s-icon-plus:eq(0)').parents('.s-mod-block').clone();
								cloneDiv.find('.btnIcon').removeClass('s-icon-plus');
								cloneDiv.find('.btnIcon').addClass('s-icon-less');
								cloneDiv.find('.s-input').val('');
								autocomplete(load,cloneDiv.find('.loadPort'));
								autocomplete(checks,cloneDiv.find('.inspection'));
								autocomplete(agent,cloneDiv.find('.agency'));
								
								//去除“导出数据”按钮
								cloneDiv.find('.exportData').hide();
								
								//复选框
								cloneDiv.find('.s-uncheckbox').removeClass('s-form-checked');
								
								//附件
								var indexs = $('.s-mod-block').length+1;
								cloneDiv.find('.accessory1').attr('id','file'+indexs);
								cloneDiv.find('.accessory1').next('a').attr('id','btn'+indexs);
								var accessoryIds= "accessory"+indexs;
								$('.accDivs').append('<div id="'+accessoryIds+'"  style="display:none;"></div>')
								
								//图片
								cloneDiv.find('.imgsAll').find('li').remove();
								var ids = "img"+indexs;
								var idDivs = "imgDiv"+indexs;
								var urls=shipServer+'/transport/mycrudeoil/images/'+$.i18n('JAVASCRIPT00240');
								cloneDiv.find('.imgsAll').append('<li><a href="javascript:;" id="'+ids+'" class="imgClass"><img src="'+urls+'"></a></li>');
								$('.accDivs').append('<div id="'+idDivs+'"  style="display:none;"></div>')
								
								//复选框回显
								for (var j = 0 ; j<arrs.length ;j++){
									cloneDiv.find('.checks').each(function(){
										if ($(this).find('input').val() == arrs[j]){ $(this).find('.s-uncheckbox').addClass('s-form-checked'); }
									});
								}
								cloneDiv.find('.loadPort').val(arr[i].loadPort).attr('code',arr[i].loadPortCode).attr('nameFn',arr[i].loadPort);
								cloneDiv.find('.inspection').val(arr[i].inspection);
								cloneDiv.find('.agency').val(arr[i].agency);
								cloneDiv.find('.oilType').val(arr[i].oilTypeCode);
								cloneDiv.find('.eta').val(date(arr[i].eta));
								cloneDiv.find('.exBerth').val(date(arr[i].exBerth));
								cloneDiv.find('.norDate').val(date(arr[i].norDate));
								cloneDiv.find('.waterDate').val(date(arr[i].waterDate));
								cloneDiv.find('.atripDate').val(date(arr[i].atripDate));
								cloneDiv.find('.berthDate').val(date(arr[i].berthDate));
								cloneDiv.find('.acStart').val(date(arr[i].acStart));
								cloneDiv.find('.acFinish').val(date(arr[i].acFinish));
								cloneDiv.find('.exLeave').val(date(arr[i].exLeave));
								cloneDiv.find('.acLeave').val(date(arr[i].acLeave));
								cloneDiv.find('.remTubeDate').val(date(arr[i].remTubeDate));
								cloneDiv.find('.accessory1').attr('title',arr[i].accessory1).val(arr[i].accessory1).attr('data-address',arr[i].accessory1Path);
								$('.all').append(cloneDiv);
								fileUp('accessory'+indexs, '#btn'+indexs,'transport','#file'+indexs,'#accessory'+indexs);
								fileUpFn('imgDiv'+indexs, '#img'+indexs,'transport','#img'+indexs,'#imgDiv'+indexs);
								more();
								//图片
								if (imgList != null){
									for (var i2 = 0; i2<imgList.length ; i2++){
										cloneDiv.find('.imgClass').parent('li').before('<li><img src="'+imgList[i2]+'"><a href="javascript:;" class="s-ent-close imgEach" title="" data-address="'+imgList[i2]+'"><i class="s-icon"></i></a></li>');
									}
								}
							}
						}
					} else if (loadPortList != null && loadPortList != '' && loadPortList.length > 0 &&
							loadAgreeMap != null && loadAgreeMap != '' && loadAgreeMap.length > 0){
						for (var i = 0; i < loadPortList.length; i++) {
							var loadPort=loadPortList[i];
							if ( i == 0){
								$('.loadPort').val(loadPort).attr('code',loadAgreeMap[i].loadPortCode).attr('nameFn',loadAgreeMap[i].loadPort);
							} else {
								var cloneDiv=$('.s-icon-plus:eq(0)').parents('.s-mod-block').clone();
								cloneDiv.find('.btnIcon').removeClass('s-icon-plus');
								cloneDiv.find('.btnIcon').addClass('s-icon-less');
								cloneDiv.find('.s-input').val('');
								autocomplete(load,cloneDiv.find('.loadPort'));
								autocomplete(checks,cloneDiv.find('.inspection'));
								autocomplete(agent,cloneDiv.find('.agency'));
								cloneDiv.find('.loadPort').val(loadPort)
								
								//去除“导出数据”按钮
								cloneDiv.find('.exportData').hide();
								
								//复选框
								cloneDiv.find('.s-uncheckbox').removeClass('s-form-checked');
								
								//附件
								var indexs = $('.s-mod-block').length+1;
								cloneDiv.find('.accessory1').attr('id','file'+indexs);
								cloneDiv.find('.accessory1').next('a').attr('id','btn'+indexs);
								var accessoryIds= "accessory"+indexs;
								$('.accDivs').append('<div id="'+accessoryIds+'"  style="display:none;"></div>')
								
								//图片
								cloneDiv.find('.imgsAll').find('li').remove();
								var ids = "img"+indexs;
								var idDivs = "imgDiv"+indexs;
								var urls=shipServer+'/transport/mycrudeoil/images/'+$.i18n('JAVASCRIPT00240');
								cloneDiv.find('.imgsAll').append('<li><a href="javascript:;" id="'+ids+'" class="imgClass"><img src="'+urls+'"></a></li>');
								$('.accDivs').append('<div id="'+idDivs+'"  style="display:none;"></div>')
								$('.all').append(cloneDiv);
								fileUp('accessory'+indexs, '#btn'+indexs,'transport','#file'+indexs,'#accessory'+indexs);
								fileUpFn('imgDiv'+indexs, '#img'+indexs,'transport','#img'+indexs,'#imgDiv'+indexs);
														
								more();
							}
						};
						//选中协议编号
						for (var i = 0; i < loadAgreeMap.length; i++) {
								$('.s-mod-block').each(function(){
									var loadPort = $(this).find('.loadPort').val();
								if (loadAgreeMap[i].loadPort == loadPort){
									$(this).find('.checks').each(function(){
										if ($(this).find('input').val() == loadAgreeMap[i].agreementUuid){
											$(this).find('.s-uncheckbox').addClass('s-form-checked');
										}
									});
								}
							});
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
		valueSets("5;2;11;");
		window.load = [];
		window.checks = [];
		window.agent = [];
		if (arrsFn != null){
			for (var i = 0; i < arrsFn.length; i++) {
				if (arrsFn[i].type == '5' && arrsFn[i].value != '其它'){
					load.push(arrsFn[i])
				} else if (arrsFn[i].type == '2' && arrsFn[i].value != '其它'){
					checks.push(arrsFn[i])
				} else if (arrsFn[i].type == '11' && arrsFn[i].value != '其它'){
					agent.push(arrsFn[i])
				}
			}
		}
		autocomplete(load,'.loadPort');
		autocomplete(checks,'.inspection');
		autocomplete(agent,'.agency');
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
		if (obj.loadPort == null || obj.loadPort == '' || obj.loadPortCode == ''){ message($.i18n("JAVASCRIPT00217"));flag=false; return false ;};
		if (obj.oilType == null || obj.oilType == '' || obj.oilTypeCode == ''){ message($.i18n("JAVASCRIPT006"));flag=false; return false ;};
//		if (obj.eta == null || obj.eta == ''){ message('请选择eta时间');flag=false; return false; };
//		if (obj.exBerth == null || obj.exBerth == ''){ message('请选择预计靠泊时间');flag=false; return false; };
//		if (obj.norDate == null || obj.norDate == ''){ message('请选择NOR递交时间');flag=false; return false; };
//		if (obj.waterDate == null || obj.waterDate == ''){ message('请选择引水上传时间');flag=false; return false; };
//		if (obj.atripDate == null || obj.atripDate == ''){ message('请选择起锚时间');flag=false; return false; };
//		if (obj.berthDate == null || obj.berthDate == ''){ message('请选择靠泊完成时间');flag=false; return false; };
//		if (obj.acStart == null || obj.acStart == ''){ message('请选择实际开始装货时间');flag=false; return false; };
//		if (obj.acFinish == null || obj.acFinish == ''){ message('请选择实际装货完成时间');flag=false; return false; };
//		if (obj.exLeave == null || obj.exLeave == ''){ message('请选择拆管时间');flag=false; return false; };
//		if (obj.acLeave == null || obj.acLeave == ''){ message('请选择预计离港时间');flag=false; return false; };
//		if (obj.remTubeDate == null || obj.remTubeDate == ''){ message('请选择实际离港时间');flag=false; return false; };
//		if (obj.inspection == null || obj.inspection == '' ||(obj.inspection == '其它' && (obj.inspections == '' || obj.inspections == null)) ){ message('请选择装港商检');flag=false; return false; };
//		if (obj.agency == null || obj.agency == '' ||(obj.agency == '其它' && (obj.agencys == '' || obj.agencys == null)) ){ message('请选择装港船代');flag=false; return false; };
		if (obj.agreementUuid == null || obj.agreementUuid == ''){ message($.i18n("JAVASCRIPT00124"));flag=false; return false; };
		return flag;
	}
	//日期格式化
	function date(str){
		var str;
		if ( str != null && str != '' && str != 0){
			str = new Date(str).Format("yyyy-MM-dd hh:mm");
		}
		if (str == 0){
			str ='';
		}
		return str;
	}
	//日期转换
	function newDateObj(obj){
		obj.eta=new Date(obj.eta.replace(new RegExp(/-/gm) ,"/"));
		obj.exBerth=new Date(obj.exBerth.replace(new RegExp(/-/gm) ,"/"));
		obj.norDate=new Date(obj.norDate.replace(new RegExp(/-/gm) ,"/"));
		obj.waterDate=new Date(obj.waterDate.replace(new RegExp(/-/gm) ,"/"));
		obj.atripDate=new Date(obj.atripDate.replace(new RegExp(/-/gm) ,"/"));
		obj.berthDate=new Date(obj.berthDate.replace(new RegExp(/-/gm) ,"/"));
		obj.acStart=new Date(obj.acStart.replace(new RegExp(/-/gm) ,"/"));
		obj.acFinish=new Date(obj.acFinish.replace(new RegExp(/-/gm) ,"/"));
		obj.exLeave=new Date(obj.exLeave.replace(new RegExp(/-/gm) ,"/"));
		obj.acLeave=new Date(obj.acLeave.replace(new RegExp(/-/gm) ,"/"));
		obj.remTubeDate=new Date(obj.remTubeDate.replace(new RegExp(/-/gm) ,"/"));
		return obj;
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
//						$(clazz).parents('div').next().show();
//						$(clazz).parents('div').next().find('a').attr('href',imgUrl);
					}
				};
				$(clazz).bsPlupload(options);
			}
	  		
	//导出数据
		$('.exportData').on('click',function(){
			//window.location.href=shipServer+"/shipPact/shipPactList.htm";
			var obj={};
			obj.shipPactUuid=uuid;
			obj.type='3';
			$.ajax({
				type: "POST",
				url: shipServer+"/exportLoadPortData.json",
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
			obj.shipPactUuid=uuid;
			obj.type='3';
			$.ajax({
				type: "POST",
				url: shipServer+"/exportLoadPortTemple.json",
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

		
		 $('body').on('focus', '.datess, .dates', function() {
			
			 //console.log(this)
		      if ($(this).parents('form').children().eq(0).find('input[name="eta"]').val() != '') {
		    	  if ($(this).val() != '') {	
		        	
		          laydate.render({
		            id: new Date().getTime(),
		            elem: this,
		            lang: lang_ver,
		            show: true,//直接显示
		            format: 'yyyy-MM-dd HH:mm',
		            type: 'datetime',
		            value: $(this).val(),
		            closeStop: this //点击 所在元素阻止关闭事件冒泡。如果不设定，则无法弹出控件
		          });
		        } else {
		        	
		          laydate.render({
		            id: new Date().getTime(),
		            elem: this,
		            lang: lang_ver,
		            show: true, //直接显示  
		            format: 'yyyy-MM-dd HH:mm',
		            type: 'datetime',
		            value: $(this).parents('form').children().eq(0).find('input[name="eta"]').val(),
		            closeStop: this
		          });
		        }
		      } else{
		    	 
		          laydate.render({
		            id: new Date().getTime(),
		            elem: '.dates',
		            lang: lang_ver,
		            show: true,//直接显示
		            format: 'yyyy-MM-dd HH:mm',
		            type: 'datetime',
		            value:'',
		            closeStop: this
		          });
		      }
		    });
		    	  
	//导入
	  var loadIndex;
	  var uploaderObj = new plupload.Uploader({ //实例化一个plupload上传对象
		browse_button : 'imports',
		url : shipServer+'/importLoadPortData.json',
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
			url : shipServer+"/import/returnLoadPortDatas.json",
			data:{},
			success : function(data) {
				layer.close(loadIndex);
				if(data.status == 0){
					var arr1 = data.datas;
					var num = 0;
					if (arr1 != null){
						num = arr1.length;
					}
					layer.alert("成功导入数据"+num+"条",  {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
						layer.close(index);
						if (arr1 != null ){
							var t=0;
							$('.charter-cont').each(function(){
								var timeFlag = true;
								$(this).find('.time').each(function(){
									if ($(this).val() != ''){
										timeFlag = false;
										return false;
									}
								});
								if (timeFlag&& arr1.length > t){
									$(this).find('.eta').val(date(arr1[t].eta));
									$(this).find('.exBerth').val(date(arr1[t].exBerth));
									$(this).find('.norDate').val(date(arr1[t].norDate));
									$(this).find('.waterDate').val(date(arr1[t].waterDate));
									$(this).find('.atripDate').val(date(arr1[t].atripDate));
									$(this).find('.berthDate').val(date(arr1[t].berthDate));
									$(this).find('.acStart').val(date(arr1[t].acStart));
									$(this).find('.acFinish').val(date(arr1[t].acFinish));
									$(this).find('.exLeave').val(date(arr1[t].exLeave));
									$(this).find('.acLeave').val(date(arr1[t].acLeave));
									$(this).find('.remTubeDate').val(date(arr1[t].remTubeDate));
									t++;
								}
							});
							if (arr1.length > t){
								for (var i = t; i<arr1.length ; i++){
									//日期框回显
										var cloneDiv=$('.w-ico-plus:eq(0)').parents('.charter-cont').clone();
										cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
										cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
										cloneDiv.find('.inps').attr('hidden','true');
										cloneDiv.find('.loadPort').val('');
										cloneDiv.find('input').val('');
										cloneDiv.find('.c-int').val('');
										autocomplete(load,cloneDiv.find('.loadPort'));
										autocomplete(checks,cloneDiv.find('.inspection'));
										autocomplete(agent,cloneDiv.find('.agency'));
										cloneDiv.find('.eta').val(date(arr1[i].eta));
										cloneDiv.find('.exBerth').val(date(arr1[i].exBerth));
										cloneDiv.find('.norDate').val(date(arr1[i].norDate));
										cloneDiv.find('.waterDate').val(date(arr1[i].waterDate));
										cloneDiv.find('.atripDate').val(date(arr1[i].atripDate));
										cloneDiv.find('.berthDate').val(date(arr1[i].berthDate));
										cloneDiv.find('.acStart').val(date(arr1[i].acStart));
										cloneDiv.find('.acFinish').val(date(arr1[i].acFinish));
										cloneDiv.find('.exLeave').val(date(arr1[i].exLeave));
										cloneDiv.find('.acLeave').val(date(arr1[i].acLeave));
										cloneDiv.find('.remTubeDate').val(date(arr1[i].remTubeDate));
										$('.all').append(cloneDiv);
										more();
										
								}
							}
						}
					});
				}else{
					message($.i18n(data.message));
				}
			}
		});
	});
	
	
	
	 fileUp('accessory1', '#btn1','transport','#file1','#accessory1');
	 fileUpFn('imgDiv1', '#img1','transport','#img1','#imgDiv1');
});
