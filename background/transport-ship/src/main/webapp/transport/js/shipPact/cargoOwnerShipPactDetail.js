layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	own = layui.own,
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	//日期
	 laydate.render({
	    elem: '#signDate',
	    lang: lang_ver
	  });
	 //开始
	  laydate.render({
	    elem: '#pactBeg' 
	    ,lang: lang_ver
	   ,type: 'datetime'
	   ,format: 'yyyy-MM-dd HH:mm'
	   ,done:function(value, date){
		   var htmlStr='<input type="text" readonly="readonly" id="pactEnd" placeholder="请选择时间" style="width: 25%;" name="pactEnds" class="c-intt zq-text" />';
	    	$('#pactBeg').parent().find('#pactEnd').remove();
	    	$('#pactBeg').parent().append(htmlStr);
	    	laydate.render({
	    		elem: '#pactEnd',
	    		lang: lang_ver,
	    		type: 'datetime',
	    		min:value,
	    		format: 'yyyy-MM-dd HH:mm',
	    		btns: ['clear', 'confirm'],
	    		done:function(value, date){
	    			if( parseInt( new Date($('#pactBeg').val()).getTime()) > parseInt( new Date(value).getTime())){
	    				layer.msg($.i18n("JAVASCRIPT002")); 
	    				setTimeout(function(){
	    					$('#pactEnd').val('');
	    				},1000)
	    			}
	    			
	    		}
	    	});
       }
	  });
	 
		//日期；初始化
		function startDate(){
			$('.time').each(function(){
				var This=this;
				var htmlStr='<input readonly="readonly" type="text" style="width: 29.3%;" placeholder="请选择日期" class="c-intt zq-text time2" />';
				laydate.render({
				    elem: $(this).get(0), //指定元素5
				    lang: lang_ver,
				    done:function(value, date){
				    	$(This).parent().find('.time2').remove();
				    	$(This).parent().append(htmlStr);
				    	laydate.render({
				    		elem: $(This).parent().find('.time2').get(0), //指定元素5
				    		lang: lang_ver,
				    		min:value,
				    		btns: ['clear', 'confirm'],
				    	});
			        }
				})
			})
		}
		startDate();
		function more(){
			$('.time').each(function(index){
				console.log()
				$(this).attr('lay-key',new Date().getTime()+index);
			});
			startDate();
		}
		//加
		$('.w-ico-plus').on('click',function(){
			var cloneDiv=$(this).parents('.wsUl').clone();
			cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
			cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
			cloneDiv.find('.c-inpt').val('');
			cloneDiv.find('.c-intt').val('');
			$('.all').append(cloneDiv);
			more();
		});
		
		//减
		$('body').on('click',' .w-ico-Less',function(){
			$(this).parents('.wsUl').remove();
		});
		  
	var shipPlateUuid = own.getHref('shipPlateUuid');
	var uuid = own.getHref('uuid')
	//查询经纪人和船东和船名
	ports();
	queryShip();
	if (shipPlateUuid && !uuid){ initNew(shipPlateUuid); 	} 
	if (uuid){ 	edit(uuid) 	}
	function edit(uuid){
		var obj ={}; obj.uuid=uuid;
		$.ajax({
    		type: "POST", 
    		url: shipServer+"/shipPact/findShipPactDetail.json",
    		data: JSON.stringify(obj),
			dataType: "json",
			contentType:"application/json",
			async:false,
    		success: function(data) {
				if(data.status == 0){
					var shipPact =  data.datas.shipPact;
					$('#signDate').val(dateChangeD(shipPact.signDate));
					$('#carrierName').val(shipPact.carrierName);
					$('#pactBeg').val(dateChange(shipPact.pactBeg));
					$('#pactEnd').val(dateChange(shipPact.pactEnd));
					$('#pactText').val(shipPact.pactText);
					$('#minQuantity').val(formatNumber(shipPact.minQuantity,2,1));
					$('#demurrage').val(formatNumber(shipPact.demurrage,2,1));
					$('#dockTime').val(formatNumber(shipPact.dockTime,2,1));
					$('#pactSpeed').val(formatNumber(shipPact.pactSpeed,2,1));
					$('#remark').val(shipPact.remark);
					$('#cunImg').val(shipPact.accessory);
					$('#cunImg').attr('title',shipPact.accessory).attr('data-address',shipPact.accessoryPath);
					
					//回显ws点
					var arr =data.datas.list;
					if (arr != null && arr.length > 0){
						for (var i = 0; i < arr.length; i++) {
							var map = arr[i];
							if ( i == 0){
								$('.ws').val(formatNumber(map.ws,2,1));
								$('.wsRes').val(map.wsRes);
								$('.time1').val(map.time1);
								$('.time2').val(map.time2);
							} else {
								
								var cloneDiv=$('.w-ico-plus:eq(0)').parents('.wsUl').clone();
								cloneDiv.find('.shipping-ico').removeClass('w-ico-plus');
								cloneDiv.find('.shipping-ico').addClass('w-ico-Less');
								cloneDiv.find('.c-inpt').val('');
								cloneDiv.find('.c-intt').val('');
								
								cloneDiv.find('.ws').val(formatNumber(map.ws,2,1));
								cloneDiv.find('.wsRes').val(map.wsRes);
								cloneDiv.find('.time1').val(map.time1);
								cloneDiv.find('.time2').val(map.time2);
								$('.all').append(cloneDiv);
								more();
							}
						}
					}
					
					$('.ships').each(function(){
						if ($(this).text() == shipPact.shipName){ $(this).attr('selected','selected')}
					});
					//船东和经纪人回显
					$('#shipOwner').val(shipPact.shipOwner);
					$('#brokerName').val(shipPact.brokerName);
					
				} else {
					layer.alert($.i18n(data.message),  {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {layer.close(index);});
				}
			},
			error: function(data) {
				layer.alert($.i18n("JAVASCRIPT003"),  {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {layer.close(index);});
	        }
    	});
	}
	//查询船盘
	function initNew (shipPlateUuid){
		var obj ={};
		obj.uuid=shipPlateUuid;
		$.ajax({
    		type: "POST",
    		url: shipServer+"/shipPlate/findShipPlateDetail.json",
    		data: JSON.stringify(obj),
			dataType: "json",
			contentType:"application/json",
    		async:false,
    		success: function(data) {
				if(data.status == 0){
					$('#shipName').val(data.datas.sysShipUuid);
					if (data.datas.ext1 != null && data.datas.ext1 != ''){
						$('#carrierName').val(data.datas.ext1);
					}
					$('#shipOwner').val(data.datas.shipOwner);
					$('#brokerName').val(data.datas.brokerName);
				} else {
					layer.alert($.i18n(data.message),  {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {layer.close(index);});
				}
			},
			error: function(data) {
				layer.alert($.i18n("JAVASCRIPT003"),  {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {layer.close(index);});
	        }
    	});
	}
	//查询船舶
	function queryShip(){
		var obj ={};
		 $.ajax({
				type: "POST",
				url: shipServer+"/sysShip/findSysShipList.json",
				data:JSON.stringify(obj),
				dataType: "json",
				contentType:"application/json",
				async:false,
				success: function(data2) { 
						if(data2.status == 0){
							var arr = data2.datas;
							if (arr != null && arr.length>0){
								for(var i=0;i<arr.length;i++)   {     
								 $("#shipName").append("<option class='ships' value='"+arr[i].uuid+"'>"+arr[i].name+"</option>");
			           } } } }
			});
	}
	
	//提交
	$('#submits').on('click',function(){
		var obj = own.serializeObject($(".form"));
		if (uuid){ obj.uuid=uuid; }
		obj.shipName=$('#shipName option:selected').text();
		obj.sysShipUuid=$('#shipName').val()
		obj.shipPlateUuid=shipPlateUuid;
		var flags = true;
		var res = "";
		var wsBig ;
		$('.wsUl').each(function(){
			var ws = $(this).find('.ws').val();
			wsBig=ws;
			var wsRes = $(this).find('.wsRes').val();
			var time1 = $(this).find('.time1').val();
			var time2 = $(this).find('.time2').val();
			if (ws == null || ws == ''){message($.i18n("JAVASCRIPT00105"));flags=false;return false;}
			if (time1 != null && time1 != '' && time2 != null && time2 != '' && new Date(time1) > new Date(time2)){
				message($.i18n("JAVASCRIPT00106"));flags=false;return false;
			}
			// 去除千分位WS点
			ws = delNumFormat(ws);
			
			res=res+ws+"/"+wsRes+"/"+time1+"/"+time2+";";
		});
		if (!flags){return false;}
		obj.wsRes=res;
		obj.ws=delNumFormat(wsBig);
		var flag = check(obj);
		if (!flag){return false;}

		obj.pactBegs=obj.pactBegs.replace(new RegExp(/-/gm) ,"/");
		obj.pactBeg= new Date(obj.pactBegs);
		obj.pactEnds=obj.pactEnds.replace(new RegExp(/-/gm) ,"/");
		obj.pactEnd= new Date(obj.pactEnds);
		if (obj.pactBeg > obj.pactEnd){
			message($.i18n("JAVASCRIPT00107"));
			return false;
		}
		obj.signDates=obj.signDates.replace(new RegExp(/-/gm) ,"/");
		obj.signDate= new Date(obj.signDates);
		delete obj.pactBegs;delete obj.pactEnds;delete obj.signDates;
		obj.accessory=$("#cunImg").attr('title');
		obj.accessoryPath=$("#cunImg").attr('data-address');
		
		// 去除千分位
		// 最小货量
		obj.minQuantity = delNumFormat(obj.minQuantity);
		// 滞期费率
		obj.demurrage = delNumFormat(obj.demurrage);
		// 合同航速
		obj.pactSpeed = delNumFormat(obj.pactSpeed);
		// 允许装卸时间
		obj.dockTime = delNumFormat(obj.dockTime);
		
		$.ajax({
    		type: "POST",
    		url: shipServer+"/shipPact/saveShipPact.json",
    		data: JSON.stringify(obj),
			dataType: "json",
			contentType:"application/json",
    		success: function(data) {
				if(data.status == 0){
					if(uuid){
						layer.alert($.i18n("JAVASCRIPT00108"),  {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {layer.close(index);
						window.location.href=shipServer+"/shipPact/cargoOwnerShipPactList.htm";
						});
					}else if(shipPlateUuid){
						layer.alert($.i18n("JAVASCRIPT00109"),  {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {layer.close(index);
					    window.location.href=shipServer+"/shipPact/cargoOwnerShipPactList.htm";
						});
					}	
				} else {
					layer.alert($.i18n(data.message),  {title:$.i18n("001"),btn:$.i18n("002")}, function(index) { layer.close(index);});
				}
			},
			error: function(data) {
	          layer.alert($.i18n("JAVASCRIPT003"),  {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {layer.close(index);});   
	          } 	
		});
	});
	//取消
	$('.back').on('click',function(){
		window.location.href = document.referrer;
		// window.location.href=shipServer+"/shipPact/shipPlateList.htm";
	});
	
	
	//查询经纪人船东
	function ports(){
		//查询值集
		valueSets("1;8;");
		window.brokerArr = [];
		window.ownerArr = [];
		if (arrsFn != null){
			for (var i = 0; i < arrsFn.length; i++) {
				if (arrsFn[i].type == '1' && arrsFn[i].value != '其它'){
					brokerArr.push(arrsFn[i])
				} else if (arrsFn[i].type == '8' && arrsFn[i].value != '其它'){
					ownerArr.push(arrsFn[i])
				}
			}
		}
		autocomplete(brokerArr,'.brokerName');
		autocomplete(ownerArr,'.shipOwner');
	}
	
	//必填校验
	function check(obj){
		var flag = true;
		if (obj.signDates == null || obj.signDates == ''){message($.i18n("JAVASCRIPT0013"));flag=false;return false;}
		if (obj.carrierName == null || obj.carrierName == ''){message($.i18n("JAVASCRIPT00111"));flag=false;return false;}
		if (obj.pactBegs == null || obj.pactBegs == ''||obj.pactEnds == null || obj.pactEnds == '' ){message($.i18n("JAVASCRIPT00112"));flag=false;return false;}
		if (obj.minQuantity == null || obj.minQuantity == ''){message($.i18n("JAVASCRIPT00113"));flag=false;return false;}
		if (obj.shipName == null || obj.shipName == '' || obj.sysShipUuid == ''){message($.i18n("JAVASCRIPT00114"));flag=false;return false;}
		if (obj.demurrage == null || obj.demurrage == ''){message($.i18n("JAVASCRIPT00115"));flag=false;return false;}
		if (obj.shipOwner == null || obj.shipOwner == '' || (obj.shipOwner == '其它' && (obj.shipOwners == null ||obj.shipOwners ==''))){message($.i18n("JAVASCRIPT00116"));flag=false;return false;}
		if (obj.pactSpeed == null || obj.pactSpeed == ''){message($.i18n("JAVASCRIPT00117"));flag=false;return false;}
		if (obj.brokerName == null || obj.brokerName == '' || (obj.brokerName == '其它' && (obj.brokerNames == null || obj.brokerNames == ''))){message($.i18n("JAVASCRIPT00118"));flag=false;return false;}
		if (obj.dockTime == null || obj.dockTime == ''){ message($.i18n("JAVASCRIPT00119"));flag=false;return false;}
		if (obj.shipPlateUuid == null || obj.shipPlateUuid == '' || !obj.shipPlateUuid ){message($.i18n("JAVASCRIPT00120"));flag=false;return false;}
		return flag;
	}
	
	//弹出框
	function message(mess){
		layer.alert(mess,  {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
			layer.close(index);
		});
	}
	//时间戳转年月日
	 function dateChangeD(dateNum){
		 var date=new Date(dateNum);//如果是秒就*1000
		 return date.getFullYear()+"-"+fixZero(date.getMonth()+1,2)+"-"+fixZero(date.getDate(),2);//+fixZero(date.getHours(),2)+":"+fixZero(date.getMinutes(),2)+":"+fixZero(date.getSeconds(),2)
		 function fixZero(num,length){
			 var str=""+num;
			 var len=str.length;
			 var s="";
				 for(var i=length;i-->len;){
					 s+="0";
				 }
			 return s+str;
			 }
		 }
	//时间戳转换 2017-02-9 14:14:52
   function dateChange(ns){
   	var date = new Date(ns);
   	Y = date.getFullYear() + '-';
   	M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
   	D = date.getDate() <10 ? '0'+date.getDate()+' ':date.getDate() + ' ';
   	h = date.getHours()  <10 ? '0'+date.getHours()+':':date.getHours() +':';
   	m = date.getMinutes() <10 ? '0'+date.getMinutes():date.getMinutes();
   	s = date.getSeconds() <10 ? '0'+date.getSeconds():date.getSeconds() ;
   	return Y+M+D+h+m;
		
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
	
	
	
	/*img 上传*/
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
						max_file_size: '3M',
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
	  		//一张图片
	 fileUp('accessory', '#btn','transport','#cunImg','#accessory');
});
