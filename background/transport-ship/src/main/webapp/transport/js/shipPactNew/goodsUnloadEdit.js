layui.use(['layer', 'form', 'jquery','laypage', 'upload','table', 'laydate','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var upload = layui.upload,
	laydate = layui.laydate,
	own = layui.own;
	
	var uuid = own.getHref('uuid');
	if (!uuid){message($.i18n("JAVASCRIPT0020"));};
	//货物卸港信息
	$('#load').on('click',function(){
		window.location.href=shipServer+"/shipPactNew/goodsLoadEdit.htm?uuid="+uuid;
	});
	//返回
	$('.returnFn').on('click',function(){
		//window.location.href=shipServer+"/shipPact/shipPactList.htm";
		 layer.confirm($.i18n("JAVASCRIPT0022"), {title:$.i18n("001"),btn:[$.i18n("002"),$.i18n("003")]}, function(index){
			 window.close();
		 });
	});
	//提交
	$('#save').on('click',function(){
		if (!uuid){message($.i18n("JAVASCRIPT0020")); return false;};
		var arr =[];
		var flag =true;
		$('.s-mod-block').each(function(){
			var obj2 = own.serializeObject($(this).find('.form'));
			
			obj2.unloadPort=$(this).find('.unloadPort').html();
			obj2.unloadPortCode=$(this).find('.unloadPort').attr('code');
			obj2.oilType=$(this).find('.oilType').html();
			obj2.oilTypeCode=$(this).find('.oilType').attr('code');
			obj2.agreementCode=$(this).find('.agreementCode').html();
			obj2.accessoryName = $(this).find('.accessory').find('input').attr('title');
			obj2.accessoryPath = $(this).find('.accessory').find('input').attr('data-address');
			
			arr.push(obj2);
		});
		if (!flag){
			return false;
		}
		var obj = {};
		obj.list =arr;
		obj.waybillUuid=uuid;
		console.log(obj);
		  var loadIndex = layer.load(0, {shade: false});
		$.ajax({
			type: "POST",
			url: shipServer+"/disburden/saveDisburdent.json",
			data: JSON.stringify(obj),
			dataType: "json",
			contentType:"application/json",
			success: function(data) {
				console.log(data)
				layer.close(loadIndex);
				if(data.status == 0){
					layer.alert($.i18n("JAVASCRIPT0099"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) { layer.close(index);
						//window.location.href=shipServer+"/shipPact/shipPactList.htm";
					});
				} else { message($.i18n(data.message)); }}
		});
	});
	
	if (uuid != null){
		init(uuid)
	}
	//初始化查询
	function init(uuid){
		var obj={};
		obj.waybillUuid=uuid
		$.ajax({
			type: "POST",
			url: shipServer+"/disburden/findDisburdenDeatil.json",
			data: JSON.stringify(obj),
			dataType: "json",
			contentType:"application/json",
			success: function(data) {
				console.log(data)
				if(data.status == 0){
					var arr =data.datas;
					if (arr != null && arr.length>0){
						$('.all').removeAttr('hidden','true');
						for (var i = 0; i < arr.length; i++) {
							var dd = arr[i];
							if ( i == 0){
								$('.unloadPort').html(dd.unloadPort).attr('code',dd.unloadPortCode);
								$('.oilType').html(dd.oilType).attr('code',dd.oilTypeCode);
								$('.agreementCode').html(dd.agreementCode);
								$('.accessory').find('.accessoryFile').attr('title',dd.accessoryName).attr('data-address',dd.accessoryPath).val(dd.accessoryName);
							} else {
								var indexs=i+1;
								var cloneDiv=$('.s-mod-block:eq(0)').clone(true);
								cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
								cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
								cloneDiv.find('.inps').attr('hidden','true');
								cloneDiv.find('.c-int').val('');	
								
								cloneDiv.find('.oilType').html(dd.oilType).attr('code',dd.oilTypeCode);
								cloneDiv.find('.unloadPort').html(dd.unloadPort).attr('code',dd.unloadPortCode);
								cloneDiv.find('.agreementCode').html(dd.agreementCode);
								cloneDiv.find('.clearFile').attr('hidden','hidden');
								cloneDiv.find('.clearFile').parent('div').find('a').removeClass('file1Fns').addClass('file1Fn');
								//上传附件
								
								//生成动态id
								cloneDiv.find('.unOrders').find('input').attr('data-address','');
								cloneDiv.find('.unOrders').find('input').attr('title','');
								cloneDiv.find('.unOrders').find('input').val('');
								cloneDiv.find('.accessory').find('input').attr('id','accessoryFile'+indexs);
								cloneDiv.find('.accessory').find('a').attr('id','accessoryBtn'+indexs);
								
								//赋值
								cloneDiv.find('.accessory').find('.accessoryFile').attr('title',dd.accessoryName).val(dd.accessoryName).attr('data-address',dd.accessoryPath);
								var cloneDivs=$('.accessoryAcc:eq(0)').clone();
								cloneDivs.attr('id','accessoryAcc'+indexs);
								$('.accAll').append(cloneDivs);
									
								$('.all').append(cloneDiv);
								//初始化上传方法
								fileUp('accessoryAcc'+indexs, '#accessoryBtn'+indexs,'transport','#accessoryFile'+indexs,'#accessoryAcc'+indexs);
							}
						}
					} else {
						$('#save').attr('hidden','true');
						layer.alert($.i18n("JAVASCRIPT00100"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) { layer.close(index);
						//window.location.href=shipServer+"/shipPact/shipPactList.htm";
						window.close();
					});
				}
					
				} else {
					message($.i18n(data.message));
				}}
		});
	}
	
	//弹框
	function message(mess){
		layer.alert(mess ,{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
			layer.close(index);
		});
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
						$(file).next('em').removeAttr('hidden','hidden');
						$(file).parent('div').find('a').removeClass('file1Fn').addClass('file1Fns');
//						$(clazz).parents('div').next().show();
//						$(clazz).parents('div').next().find('a').attr('href',imgUrl);
					}
				};
				$(clazz).bsPlupload(options);
			}
	  		
	 fileUp('accessoryAcc1', '#accessoryBtn1','transport','#accessoryFile1','#accessoryAcc1');
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
});
