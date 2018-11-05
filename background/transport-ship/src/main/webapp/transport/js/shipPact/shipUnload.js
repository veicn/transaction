layui.use(['layer', 'form', 'jquery','laypage', 'upload','table', 'laydate','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var upload = layui.upload,
	laydate = layui.laydate,
	own = layui.own;
	
	//航次开始信息
	$('#start').on('click',function(){
		window.location.href=shipServer+"/shipPact/shipLogistics.htm?shipPactUuid="+uuid;
	});
	//返回
	$('.returnFn').on('click',function(){
		//window.location.href=shipServer+"/shipPact/shipPactList.htm";
		 layer.confirm($.i18n("JAVASCRIPT0022"), {title:$.i18n("001"),btn:[$.i18n("002"),$.i18n("003")]}, function(index){
			 window.close();
		 });
	});
	//装港
	$('#load').on('click',function(){
		window.location.href=shipServer+"/shipPact/shipLoad.htm?shipPactUuid="+uuid;
	});
	//在途
	$('#transit').on('click',function(){
		window.location.href=shipServer+"/shipPact/shipTransit.htm?shipPactUuid="+uuid;
	});
	//日期初始化
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
	//查询卸港，商检，船代，监卸
	ports();
	//查询卸港，商检，船代，监卸
	function ports(){
		//查询值集
		valueSets("4;3;6;7;");
		window.unloadArr = [];
		window.checkArr = [];
		window.agentArr = [];
		window.lockArr = [];
		if (arrsFn != null){
			for (var i = 0; i < arrsFn.length; i++) {
				if (arrsFn[i].type == '4' && arrsFn[i].value != '其它'){
					unloadArr.push(arrsFn[i])
				} else if (arrsFn[i].type == '3' && arrsFn[i].value != '其它'){
					checkArr.push(arrsFn[i])
				} else if (arrsFn[i].type == '6' && arrsFn[i].value != '其它'){
					agentArr.push(arrsFn[i])
				} else if (arrsFn[i].type == '7' && arrsFn[i].value != '其它'){
					lockArr.push(arrsFn[i])
				}
			}
		}
		autocomplete(unloadArr,'.unloadPort');
		autocomplete(checkArr,'.inspection');
		autocomplete(agentArr,'.agency');
		autocomplete(lockArr,'.monitor');
	}
	
	//加
	$('.w-ico-plus').on('click',function(){
		var cloneDiv=$(this).parents('.charter-cont').clone();
		cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
		cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
		cloneDiv.find('.form-inp').val('');
		cloneDiv.find('.inps').attr('hidden','true');
		cloneDiv.find('.daili').remove();
		cloneDiv.find('.agree').attr("checked",false);
		autocomplete(unloadArr,cloneDiv.find('.unloadPort'));
		$('.all').append(cloneDiv);
		more();
	});
	//减
	$('body').on('click',' .w-ico-Less',function(){
		$(this).parents('.charter-cont2').remove();
	});
	//复选框选中事件
	$('body').on('click','.agree',function(){
		var code = $(this).next('span').html();
		var id = $(this).val();
		if($(this).is(':checked')){
			var cloneDiv=$('.daili:eq(0)').clone();
			cloneDiv.removeAttr('hidden','true');	
			cloneDiv.find('.unloadPortSel').val('');	
			cloneDiv.find('input').val('');	
			cloneDiv.find('.unloadPortHid').attr('hidden','true');	
			cloneDiv.find('.agreeCode').html(code);
			cloneDiv.find('.agreeUuids').val(id);
			autocomplete(checkArr,cloneDiv.find('.inspection'));
			autocomplete(agentArr,cloneDiv.find('.agency'));
			autocomplete(lockArr,cloneDiv.find('.monitor'));
			$(this).parents('.un').append(cloneDiv);
		}else{
			$(this).parents('.un').find('.daili').each(function(){
				if ($(this).find('.agreeUuids').val() ==id ){
					$(this).remove();
				}
			});
		}
	})
	
	var uuid = own.getHref('shipPactUuid');
	if (!uuid){message($.i18n("JAVASCRIPT0097"));};
	//提交
	$('#save').on('click',function(){
		var flag=true;
		if (!uuid){message($.i18n("JAVASCRIPT0097")); return false;};
		var arr = [];
		$('.charter-cont').each(function(){
			var obj = own.serializeObject($(this).find('.form'));
			var str="";
			var list = [];
			var flags2=true;
			$(this).find('.daili').each(function(){
					var shangjian = {};
					var agreeUuids = $(this).find('.agreeUuids').val();
					str=str+agreeUuids+";";
					var daili = $(this);
					var inspection = daili.find('.inspection').val();
					var inspectionTel = daili.find('.inspectionTel').val();
					var agency = daili.find('.agency').val();
					var agencyTel = daili.find('.agencyTel').val();
					var monitor = daili.find('.monitor').val();
					var monitorTel = daili.find('.monitorTel').val();
					shangjian.agreementUuid=agreeUuids;
					shangjian.inspection=inspection;
					shangjian.inspectionTel=inspectionTel;
					shangjian.agency=agency;
					shangjian.agencyTel=agencyTel;
					shangjian.monitor=monitor;
					shangjian.monitorTel=monitorTel;
					/*var flags = checkShangjian(shangjian);
					if (!flags){
						flags2=false;return false;
					}*/
					list.push(shangjian);
			});
			obj.list=list;
			obj.agreementUuid=str;
			var flags3 = check(obj);
			if(!flags3 || !flags2){flag =false;return false;}
			obj = newDateObj(obj);
			obj.remTubeDate=new Date(obj.remTubeDate);
			arr.push(obj);
		});
		if(!flag){ return false; }
		var obj2 = own.serializeObject($('.form2'));
		var object = {};
		object.shipPactUuid=uuid;
		object.list=arr;
		object.inspection=obj2.inspection;
		object.agency=obj2.agency;
		object.monitor=obj2.monitor;
		object.inspectionTel=obj2.inspectionTel;
		object.agencyTel=obj2.agencyTel;
		object.monitorTel=obj2.monitorTel;
		object.accessory1=$("#file1").attr('title');
		object.accessory1Path=$("#file1").attr('data-address');
		object.accessory2=$("#file2").attr('title');
		object.accessory2Path=$("#file2").attr('data-address');
		object.accessory3=$("#file3").attr('title');
		object.accessory3Path=$("#file3").attr('data-address');
		object.accessory4=$("#file4").attr('title');
		object.accessory4Path=$("#file4").attr('data-address');
		var loadIndex = layer.load(0, {shade: false});
		$.ajax({
			type: "POST",
			url: shipServer+"/unloadPort/saveUnloadPort.json",
			data: JSON.stringify(object),
			dataType: "json",
			contentType:"application/json",
			success: function(data) {
				layer.close(loadIndex);
				if(data.status == 0){
					layer.alert($.i18n("JAVASCRIPT00127"), {title:$.i18n("001"),btn:$.i18n("002")},   function(index) {
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
				if(data.status == 0){
					var arr = data.datas;
					if (arr != null && arr.length > 0){
						for(var i=0;i<arr.length;i++){ 
							var agreementCode = arr[i].agreementCode;
							var agreementUuid = arr[i].agreementUuid;
							$('.agreementCode').append("<p class='char-check'><input class='agree' type='checkbox' name='agreementUuid' value='"+agreementUuid+"'><span>"+agreementCode+"</span></p>");
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
							$(".oilType").append("<option class='oilTypeOpp' value='"+arr[i]+"'>"+arr[i]+"</option>");						}
					}}}});
	}
	if (uuid){
		queryAgreement(uuid);
		init(uuid)
	}
	//初始化查询
	function init(uuid){
		var obj={};
		obj.shipPactUuid=uuid
		$.ajax({
			type: "POST",
			url: shipServer+"/unloadPort/findUnloadPortDeatil.json",
			data: JSON.stringify(obj),
			dataType: "json",
			contentType:"application/json",
			success: function(data) {
				console.log(data)
				if(data.status == 0){
					var arr = data.datas.list;
					var unloadPortList = data.datas.map.unloadPortList;
					var unloadAgreeMap = data.datas.map.unloadAgreeMap;
					//附件回显
					if (data.datas.accessory1 != null && data.datas.accessory1Path != null){
						$('#file1').attr('title',data.datas.accessory1).val(data.datas.accessory1).attr('data-address',data.datas.accessory1Path);
						$('.xiazai1').find('a').attr('href',data.datas.accessory1Path);
						$('.xiazai1').show();
					}
					if (data.datas.accessory2 != null && data.datas.accessory2Path != null){
						$('#file2').attr('title',data.datas.accessory2).val(data.datas.accessory2).attr('data-address',data.datas.accessory2Path);
						$('.xiazai2').find('a').attr('href',data.datas.accessory2Path);
						$('.xiazai2').show();
					}
					if (data.datas.accessory3 != null && data.datas.accessory3Path != null){
						$('#file3').attr('title',data.datas.accessory3).val(data.datas.accessory3).attr('data-address',data.datas.accessory3Path);
						$('.xiazai3').find('a').attr('href',data.datas.accessory3Path);
						$('.xiazai3').show();
					}
					if (data.datas.accessory4 != null && data.datas.accessory4Path != null){
						$('#file4').attr('title',data.datas.accessory4).val(data.datas.accessory4).attr('data-address',data.datas.accessory4Path);
						$('.xiazai4').find('a').attr('href',data.datas.accessory4Path);
						$('.xiazai4').show();
					}
					
					if (arr != null && arr != "" && arr.length > 0){
						for (var i = 0; i<arr.length ; i++){
							var agreeUuid = arr[i].agreementUuid;
							var arrs =arr[i].list;
							if (i == 0){
								$('.unloadPort').val(arr[i].unloadPort);
								//回显复选框中的下拉框和输入框
								for (var j = 0 ; j<arrs.length ;j++){
									$('.agree').each(function(){
										if ($(this).val() == arrs[j].agreementUuid){ $(this).attr('checked','checked');
										var cloneDiv=$('.daili:eq(0)').clone();
										cloneDiv.removeAttr('hidden','true');	
										cloneDiv.find('.unloadPortSel').val('');	
										cloneDiv.find('.unloadPortInp').val('');	
										cloneDiv.find('.unloadPortHid').attr('hidden','true');	
										cloneDiv.find('.agreeCode').html($(this).next('span').html());	
										cloneDiv.find('.agreeUuids').val($(this).val());	
										autocomplete(checkArr,cloneDiv.find('.inspection'));
										autocomplete(agentArr,cloneDiv.find('.agency'));
										autocomplete(lockArr,cloneDiv.find('.monitor'));
										cloneDiv.find('.inspection').val(arrs[j].inspection) ;
										cloneDiv.find('.agency').val(arrs[j].agency) ;
										cloneDiv.find('.monitor').val(arrs[j].monitor) ;
										cloneDiv.find('.inspectionTel').val(arrs[j].inspectionTel);
										cloneDiv.find('.agencyTel').val(arrs[j].agencyTel);
										cloneDiv.find('.monitorTel').val(arrs[j].monitorTel);
										$(this).parents('.un').append(cloneDiv);
										}
									});
								}
								$('.oilType').val(arr[i].oilType);
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
							} else {
								var cloneDiv=$('.w-ico-plus:eq(0)').parents('.charter-cont').clone();
								cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
								cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
								cloneDiv.find('.xiegang').attr('hidden','true');
								cloneDiv.find('.daili').remove();
								cloneDiv.find('.c-int').val('');
								autocomplete(unloadArr,cloneDiv.find('.unloadPort'));
								//复选框回显
								cloneDiv.find('.agree').each(function(){
								var checkFn = false;
									for(var j=0;j<arrs.length;j++){
									 if(arrs[j].agreementUuid==$(this).val()){
										 checkFn= true;
										var cloneDivs=$('.daili:eq(0)').clone();
										cloneDivs.removeAttr('hidden','true');	
										cloneDivs.find('.unloadPortSel').val('');	
										cloneDivs.find('.daili').remove();
										cloneDivs.find('.agree').attr("checked",false);
										cloneDivs.find('.unloadPortInp').val('');	
										cloneDivs.find('.unloadPortHid').attr('hidden','true');	
										cloneDivs.find('.agreeCode').html($(this).next('span').html());	
										cloneDivs.find('.agreeUuids').val($(this).val());	
										autocomplete(checkArr,cloneDivs.find('.inspection'));
										autocomplete(agentArr,cloneDivs.find('.agency'));
										autocomplete(lockArr,cloneDivs.find('.monitor'));
										cloneDivs.find('.inspection').val(arrs[j].inspection);
										cloneDivs.find('.agency').val(arrs[j].agency);
										cloneDivs.find('.monitor').val(arrs[j].monitor);
										cloneDivs.find('.inspectionTel').val(arrs[j].inspectionTel);
										cloneDivs.find('.agencyTel').val(arrs[j].agencyTel);
										cloneDivs.find('.monitorTel').val(arrs[j].monitorTel);
										$(this).parents('.un').append(cloneDivs);
										}
								}
									if (checkFn){
										$(this).attr('checked',true);
									} else {
										$(this).attr('checked',false);
									}
								});
								cloneDiv.find('.unloadPort').val(arr[i].unloadPort); 
								cloneDiv.find('.oilType').val(arr[i].oilType);
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
								$('.all').append(cloneDiv);
								more();
							}
						}
					} else if (unloadPortList != null && unloadPortList != '' && unloadPortList.length > 0 &&
							unloadAgreeMap != null && unloadAgreeMap != '' && unloadAgreeMap.length > 0){
						for (var i = 0; i < unloadPortList.length; i++) {
							var loadPort=unloadPortList[i];
							if ( i == 0){
								$('.unloadPort').val(loadPort)
							} else {
								var cloneDiv=$('.w-ico-plus:eq(0)').parents('.charter-cont').clone();
								cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
								cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
								cloneDiv.find('.inps').attr('hidden','true');
								cloneDiv.find('.c-int').val('');
								autocomplete(unloadArr,cloneDiv.find('.unloadPort'));
								cloneDiv.find('.unloadPort').val(loadPort)
								$('.all').append(cloneDiv);
								more();
							}
						};
						//选中协议编号
						for (var i = 0; i < unloadAgreeMap.length; i++) {
						   $('.charter-cont2').each(function(){
									var loadPort = $(this).find('.unloadPort').val();
								if (loadPort == '其它'){
									loadPort =$(this).find('.unloadPorts').val();
								}
								if (unloadAgreeMap[i].unloadPort == loadPort){
									$(this).find('.agree').each(function(){
										if ($(this).val() == unloadAgreeMap[i].agreementUuid){
											$(this).attr('checked','checked'); 
											var cloneDiv=$('.daili:eq(0)').clone();
											cloneDiv.removeAttr('hidden','true');	
											cloneDiv.find('.unloadPortSel').val('');	
											cloneDiv.find('.unloadPortInp').val('');	
											cloneDiv.find('.unloadPortHid').attr('hidden','true');	
											cloneDiv.find('.agreeCode').html($(this).next('span').html());
											cloneDiv.find('.agreeUuids').val($(this).val());
											autocomplete(checkArr,cloneDiv.find('.inspection'));
											autocomplete(agentArr,cloneDiv.find('.agency'));
											autocomplete(lockArr,cloneDiv.find('.monitor'));
											$(this).parents('.un').append(cloneDiv);
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
	
	//弹框
	function message(mess){
		layer.alert(mess,   {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
			layer.close(index);
		});
	}
	//格式化日期
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
		return obj;
	}
	
	//校验商检必填
	function checkShangjian(obj2){
		var flag = true;
		if (obj2.inspection == null || obj2.inspection == '' ){ message($.i18n("JAVASCRIPT00128"));flag=false; return false;}
		if (obj2.agency == null || obj2.agency == '' ){ message($.i18n("JAVASCRIPT00129"));flag=false; return false;}
		if (obj2.monitor == null || obj2.monitor == ''){ message($.i18n("JAVASCRIPT00130"));flag=false; return false;}
		//if (obj2.inspectionTel == null || obj2.inspectionTel == ''){ message('请输入卸港商检联系方式');flag=false; return false;}
		//if (obj2.agencyTel == null || obj2.agencyTel == ''){ message('请输入卸港船代联系方式');flag=false; return false;}
		//if (obj2.monitorTel == null || obj2.monitorTel == ''){ message('请输入卸港监卸联系方式');flag=false; return false;}
		return flag;
	}
	
	//校验必填
	function check(obj){
		var flag= true;
		if (obj.unloadPort == null || obj.unloadPort == ''|| (obj.unloadPort == '其它' &&( obj.unloadPorts == null || obj.unloadPorts == ''))){ message('请选择卸港信息');flag=false; return false ;};
		if (obj.oilType == null || obj.oilType == ''){ message($.i18n("JAVASCRIPT00131"));flag=false; return false ;};
//		if (obj.eta == null || obj.eta == ''){ message('请选择eta时间');flag=false; return false; };
//		if (obj.exBerth == null || obj.exBerth == ''){ message('请选择预计靠泊时间');flag=false; return false; };
//		if (obj.norDate == null || obj.norDate == ''){ message('请选择NOR递交时间');flag=false; return false; };
//		if (obj.waterDate == null || obj.waterDate == ''){ message('请选择引水上传时间');flag=false; return false; };
//		if (obj.atripDate == null || obj.atripDate == ''){ message('请选择起锚时间');flag=false; return false; };
//		if (obj.berthDate == null || obj.berthDate == ''){ message('请选择靠泊完成时间');flag=false; return false; };
//		if (obj.acStart == null || obj.acStart == ''){ message('请选择实际开始卸货时间');flag=false; return false; };
//		if (obj.acFinish == null || obj.acFinish == ''){ message('请选择实际卸货完成时间');flag=false; return false; };
//		if (obj.exLeave == null || obj.exLeave == ''){ message('请选择拆管时间');flag=false; return false; };
//		if (obj.acLeave == null || obj.acLeave == ''){ message('请选择预计离港时间');flag=false; return false; };
//		if (obj.remTubeDate == null || obj.remTubeDate == ''){ message('请选择实际离港时间');flag=false; return false; };
		if (obj.agreementUuid == null || obj.agreementUuid == ''){ message($.i18n("JAVASCRIPT00132"));flag=false; return false; };
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
//						$(clazz).parents('div').next().show();
//						$(clazz).parents('div').next().find('a').attr('href',imgUrl);
					}
				};
				$(clazz).bsPlupload(options);
			}
	  		
	 fileUp('accessory1', '#btn1','transport','#file1','#accessory1');
	 fileUp('accessory2', '#btn2','transport','#file2','#accessory2');
	 fileUp('accessory3', '#btn3','transport','#file3','#accessory3');
	 fileUp('accessory4', '#btn4','transport','#file4','#accessory4');
		//导出数据
		$('.exportData').on('click',function(){
			//window.location.href=shipServer+"/shipPact/shipPactList.htm";
			var obj={};
			obj.shipPactUuid=uuid;
			obj.type='4';
			$.ajax({
				type: "POST",
				url: shipServer+"/exportUnloadPortData.json",
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
			obj.type='4';
			$.ajax({
				type: "POST",
				url: shipServer+"/exportUnloadPortTemple.json",
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
	//卸港信息导入
	  var loadIndex;
	  var uploaderObj = new plupload.Uploader({ //实例化一个plupload上传对象
		browse_button : 'imports',
		url : shipServer+'/importUnloadPortData.json',
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
			url : shipServer+"/import/returnUnLoadPortDatas.json",
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
									var cloneDiv=$('.w-ico-plus:eq(0)').parents('.charter-cont').clone();
									cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
									cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
									cloneDiv.find('.inps').attr('hidden','true');
									cloneDiv.find('.daili').remove();
									cloneDiv.find('.agree').attr("checked",false);
									cloneDiv.find('.unloadPort').val('');
									cloneDiv.find('.oilType').val('');
									cloneDiv.find('.time').val('');
									autocomplete(unloadArr,cloneDiv.find('.unloadPort'));
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
});
