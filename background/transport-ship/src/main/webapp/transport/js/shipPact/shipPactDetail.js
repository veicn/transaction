layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	own = layui.own,
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	
	 laydate.render({		/**租约日*/
	    elem: '#signDate',
	    lang: lang_ver
	  });
	 
	 laydate.render({		/**装期，先选开始，再结束*/
	    elem: '#pactBeg'
	   ,lang: lang_ver
	   ,type: 'datetime'
	   ,format: 'yyyy-MM-dd HH:mm'
	   ,done:function(value, date){
		   var htmlStr='<input type="text" id="pactEnd" name="pactEnds" class="s-input width_40" style="width: 25%;" readonly="readonly"/>';
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
	    				layer.msg($.i18n("JAVASCRIPT00104")); 
	    				setTimeout(function(){
	    					$('#pactEnd').val('');
	    				},1000)
	    			}
	    			
	    		}
	    	});
        }
	  });
 
	 
	//BASIS 日期初始化,选择开始日期，才可选结束日期
	function startDate(){
		$('.time').each(function(){
			var This=this;
			var htmlStr='<input type="text" class="s-input width_40 time2" style="width: 29.3%;" readonly="readonly"/>';
			laydate.render({
			    elem: $(this).get(0), //指定元素5
			    lang: lang_ver,
			    done:function(value, date){
			    	$(This).parent().find('.time2').remove();
			    	$(This).parent().append(htmlStr);
			    	laydate.render({
			    		elem: $(This).parent().find('.time2').get(0), //指定元素5
			    		min:value,
			    		lang: lang_ver,
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
	$('.s-icon-plus').on('click',function(){
		var cloneDiv=$(this).parents('.wsUl').clone();
		cloneDiv.find('.addMinusIcon').removeClass('s-icon-plus');
		cloneDiv.find('.addMinusIcon').addClass('s-icon-less');
		cloneDiv.find('.s-input').val('');
		//cloneDiv.find('.c-intt').val('');
		$('.all').append(cloneDiv);
		more();
	});
	
	//减
	$('body').on('click',' .s-icon-less',function(){
		$(this).parents('.wsUl').remove();
	});
	
	//上传文件的删除
	$('body').on('click',' .fs-close',function(){
		$(".accessoryAll").children().remove();		/**移除文件 accessoryAll 标签下文件内容*/
		$("#cunImg").attr("value","");				/**清空 cunImg 各项值*/
		$("#cunImg").attr("title","");
		$("#cunImg").attr("data-address","");
	});
		  
	
	//获取地址栏数据
	var shipPlateUuid = own.getHref('shipPlateUuid');		  //船盘UUID
	var uuid = own.getHref('uuid');							  //船合同UUID
	var shipIntentionUuid = own.getHref('shipIntentionUuid'); //租船意向UUID
	if(shipIntentionUuid==null || shipIntentionUuid=="" || shipIntentionUuid==false){
		shipIntentionUuid=null;
	}
	
	
	queryShip();	/**查询船舶*/
	portss();		/**查询经纪人船东*/
	
	
	autocomplete(shipArr,'.shipName');	/**查询船名*/
	
	
	//信息回显
	if (shipPlateUuid && !uuid && shipIntentionUuid){initNew(shipPlateUuid); initIntention (shipIntentionUuid);	} 
	if (shipPlateUuid && !uuid && !shipIntentionUuid){initNew(shipPlateUuid);	} 
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
					console.log(data);
					var shipPact =  data.datas.shipPact;
					
					$('#signDate').val(dateChangeD(shipPact.signDate));
					$('#carrierName').val(shipPact.carrierName).attr('code',shipPact.carrierId);
					$('#loadingArea').val(shipPact.loadingArea);
					$('#unloadingArea').val(shipPact.unloadingArea);
					$('#pactBeg').val(dateChange(shipPact.pactBeg));
					$('#pactEnd').val(dateChange(shipPact.pactEnd));
					$('#pactText').val(shipPact.pactText);
					$('#minQuantity').val(formatNumber(shipPact.minQuantity,2,1));
					$('#demurrage').val(formatNumber(shipPact.demurrage,2,1));
					$('#dockTime').val(formatNumber(shipPact.dockTime,2,1));
					$('#pactSpeed').val(shipPact.pactSpeed);
					$('#remark').val(shipPact.remark);
					$('#shipOwner').val(shipPact.shipOwner).attr('code',shipPact.shipOwnerId);
					$('#brokerName').val(shipPact.brokerName).attr('code',shipPact.brokerId);
					$('#shipName').val(shipPact.shipName).attr('code',shipPact.sysShipUuid);
					$('#cunImg').val(shipPact.accessory);
					$('#cunImg').attr('title',shipPact.accessory).attr('data-address',shipPact.accessoryPath);
					
					//回显ws点
					var arr =data.datas.list;
					if (arr != null && arr.length > 0){
						for (var i = 0; i < arr.length; i++) {
							var map = arr[i];
							if (i == 0){
								$('.ws').val(formatNumber(map.ws,2,1));
								$('.wsRes').val(map.wsRes);
								$('.time1').val(map.time1);
								$('.time2').val(map.time2);
							} else {
								var cloneDiv=$('.s-icon-plus:eq(0)').parents('.wsUl').clone();
								cloneDiv.find('.addMinusIcon').removeClass('s-icon-plus');
								cloneDiv.find('.addMinusIcon').addClass('s-icon-less');
								
								cloneDiv.find('.s-input').val('');	/**清空ws、BASIS开始、结束日期*/
								cloneDiv.find('.wsRes').val('');	/**清空wsRes*/
								
								//ws、BASIS开始、结束日期、wsRes 赋值
								cloneDiv.find('.ws').val(formatNumber(map.ws,2,1));
								cloneDiv.find('.wsRes').val(map.wsRes);
								cloneDiv.find('.time1').val(map.time1);
								cloneDiv.find('.time2').val(map.time2);
								
								$('.all').append(cloneDiv);
								more();
							}
						}
					}
				} else {
					layer.alert($.i18n(data.message), {title:$.i18n("001"),btn:$.i18n("002")},  function(index) {layer.close(index);});
				}
			},
			error: function(data) {
				layer.alert($.i18n("JAVASCRIPT003"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {layer.close(index);});
	        }
    	});
	}
	
	
	//查询经纪人船东
	function portss(){
		 var paramType={};
		 var types=["4","5"];	// 会员资质类型 1炼厂,2贸易商,3商检,4船东,5船经纪人,6船代,7转租船东
		 paramType.typeList=types;
		 
		 $.ajax({
    		type: "POST",
    		url: shipServer+"/traderNameListForBack.json",
    		data: JSON.stringify(paramType),
			dataType: "json",
			contentType:"application/json",
    		async: false,
    		success: function(data) {
				if(data.status == 0){
					//经纪人
					window.brokerArr = [];
					//船东
					window.ownerArr = [];	
					
					var arr=data.datas;
					if(arr!=null){
						for(var i=0;i<arr.length;i++){
							if(arr[i].type=="4"){	//船东
								arr[i].code=arr[i].traderId;
								arr[i].value=arr[i].traderName;
								arr[i].subGroup=arr[i].traderName;
								ownerArr.push(arr[i]);
								
							}else if(arr[i].type=="5"){	//经纪人
								arr[i].code=arr[i].traderId;
								arr[i].value=arr[i].traderName;
								arr[i].subGroup=arr[i].traderName;
								brokerArr.push(arr[i]);
							}
						}
						
						autocomplete(brokerArr,'.brokerName');
						autocomplete(ownerArr,'.shipOwner');
					}
					
				}else{
					layer.alert($.i18n(data.message), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
						layer.close(index);
					});
				}
			},
			error: function(data) {
				layer.alert($.i18n(data.message), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
					layer.close(index);
				});
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
					$('#shipName').val(data.datas.name).attr('code',data.datas.sysShipUuid);
					if (data.datas.ext1 != null && data.datas.ext1 != ''){
						$('#carrierName').val(data.datas.ext1);
					}
					$('#shipOwner').val(data.datas.shipOwner).attr('code',data.datas.shipOwnerId);
					$('#brokerName').val(data.datas.brokerName).attr('code',data.datas.brokerId);
				} else {
					layer.alert($.i18n(data.message) ,{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {layer.close(index);});
				}
			},
			error: function(data) {
				layer.alert($.i18n("JAVASCRIPT003"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {layer.close(index);});
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
						//船东
						window.shipArr = [];
						if (arr != null && arr.length>0){
							for(var i=0;i<arr.length;i++){
								arr[i].code=arr[i].uuid;
								arr[i].value=arr[i].name;
								arr[i].subGroup=arr[i].name;
								shipArr.push(arr[i]);	
		                    } 
						} 
					} 
				}
		});
	}
	
	
	//把意向单信息初始化在页面
	function initIntention (shipIntentionUuid){
		var obj ={};
		obj.uuid=shipIntentionUuid;
		$.ajax({
    		type: "POST",
    		url: shipServer+"/intention/findIntentionDetail.json",
    		data: JSON.stringify(obj),
			dataType: "json",
			contentType:"application/json",
    		async:false,
    		success: function(data) {
				if(data.status == 0){
					$('#carrierName').val(data.datas.intention.epMemberName);
					$('#minQuantity').val(data.datas.intention.coMinQuantity);
					$('#demurrage').val(data.datas.intention.coDemurrage);
					$('#pactSpeed').val(data.datas.intention.coPactSpeed);
					$('#dockTime').val(data.datas.intention.coDockTime);
				} else {
					layer.alert($.i18n(data.message), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {layer.close(index);});
				}
			},
			error: function(data) {
				//layer.alert("系统异常", function(index) {layer.close(index);});
	        }
    	});
	}
	
	
	//提交
	$('#submits').on('click',function(){
		var obj = own.serializeObject($(".form"));
		if (uuid){ obj.uuid=uuid; }
		obj.carrierId=$('#carrierName').attr('code');
		obj.shipOwnerId=$('#shipOwner').attr('code');
		obj.brokerId=$('#brokerName').attr('code');
		obj.shipName=$('#shipName').val();
		obj.sysShipUuid=$('#shipName').attr('code')
		obj.shipPlateUuid=shipPlateUuid;
		obj.shipIntentionUuid=shipIntentionUuid;
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
		
		//必填项校验
		var flag = check(obj);
		if (!flag){return false;}

		obj.pactBegs=obj.pactBegs.replace(new RegExp(/-/gm) ,"/");
		obj.pactBeg= new Date(obj.pactBegs);
		obj.pactEnds=obj.pactEnds.replace(new RegExp(/-/gm) ,"/");
		obj.pactEnd= new Date(obj.pactEnds);
		if (obj.pactBeg > obj.pactEnd){
			message($.i18n("JAVASCRIPT0010"));
			return false;
		}
		obj.signDates=obj.signDates.replace(new RegExp(/-/gm) ,"/");
		obj.signDate= new Date(obj.signDates);
		delete obj.pactBegs;delete obj.pactEnds;delete obj.signDates;
		
		obj.accessory=$("#cunImg").attr('title');			/**文件数据*/
		obj.accessoryPath=$("#cunImg").attr('data-address');
		
		//去除千分位，delNumFormat
		obj.minQuantity = delNumFormat(obj.minQuantity);	/**最小货量*/
		obj.demurrage = delNumFormat(obj.demurrage);		/**滞期费率*/
		obj.dockTime = delNumFormat(obj.dockTime);			/**允许装卸时间*/
		
		var fromPage = own.getHref('fromPage');
		
		$.ajax({
    		type: "POST",
    		url: shipServer+"/shipPact/saveShipPact.json",
    		data: JSON.stringify(obj),
			dataType: "json",
			contentType:"application/json",
    		success: function(data) {
				if(data.status == 0){
					if(uuid){
						layer.alert($.i18n("JAVASCRIPT00108"), {title:$.i18n("001"),btn:$.i18n("002")},  function(index) {
						layer.close(index);
						window.location.href = document.referrer;
						});
					}else if(shipPlateUuid){
						layer.alert($.i18n("JAVASCRIPT00109"), {title:$.i18n("001"),btn:$.i18n("002")},  function(index) {
							layer.close(index);
							window.location.href = document.referrer;
						});
					}	
				} else {
					layer.alert($.i18n(data.message), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) { layer.close(index);});
				}
			},
			error: function(data) {
	          layer.alert($.i18n("JAVASCRIPT003"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {layer.close(index);});   
	          } 	
		});
	});
	
	
	//取消
	$('.back').on('click',function(){
		window.location.href = document.referrer;
		// window.location.href=shipServer+"/shipPact/shipPlateList.htm";
	});
	
	
	//必填校验
	function check(obj){
		var flag = true;
		if (obj.signDates == null || obj.signDates == ''){message($.i18n("JAVASCRIPT0013"));flag=false;return false;}
		if (obj.carrierName == null || obj.carrierName == ''){message($.i18n("JAVASCRIPT00111"));flag=false;return false;}
		if (obj.pactBegs == null || obj.pactBegs == ''||obj.pactEnds == null || obj.pactEnds == '' ){message($.i18n("JAVASCRIPT00112"));flag=false;return false;}
		if (obj.minQuantity == null || obj.minQuantity == ''){message($.i18n("JAVASCRIPT00113"));flag=false;return false;}
		if (obj.shipName == null || obj.shipName == ''){message($.i18n("JAVASCRIPT0016"));flag=false;return false;}
		if (obj.demurrage == null || obj.demurrage == ''){message($.i18n("JAVASCRIPT00115"));flag=false;return false;}
		if (obj.shipOwner == null || obj.shipOwner == '' || obj.shipOwnerId == ''){message($.i18n("JAVASCRIPT00116"));flag=false;return false;}
		if (obj.pactSpeed == null || obj.pactSpeed == ''){message($.i18n("JAVASCRIPT00117"));flag=false;return false;}
		if (obj.brokerName == null || obj.brokerName == '' || obj.brokerId == ''){message($.i18n("JAVASCRIPT00118"));flag=false;return false;}
		if (obj.dockTime == null || obj.dockTime == ''){ message($.i18n("JAVASCRIPT00119"));flag=false;return false;}
		if (obj.shipPlateUuid == null || obj.shipPlateUuid == '' || !obj.shipPlateUuid ){message($.i18n("JAVASCRIPT00120"));flag=false;return false;}
		return flag;
	}
	
	
	//弹出框
	function message(mess){
		layer.alert(mess, {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
			layer.close(index);
		});
	}
	
	
	//时间戳转年月日
	function dateChangeD(dateNum){
		 var date=new Date(dateNum);	/**如果是秒就*1000*/
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
   
   
	//日期格式化函数
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
		
	  
	  //一张图片
	  fileUp('accessory', '#btn','transport','#cunImg','#accessory');
		
	  function fileUp(id, clazz,dir,file,id2) {
			var options = {
				multi_selection: false, 	//设置是否多选
				listId: id, 
				itemTemplate: temp, 		//设置模板
				signatureUrl: configUitl.getOssServerPath(shipServer,dir),
				dir: dir,
				filters: {
					max_file_size: '20M',	
					mime_types: [{			//上传文件后缀名
						extensions: "pdf,txt,jpg,xls,xlsx,docx,doc"
					}],
				},
				swfUrl: '../../lib/upload/Moxie.swf',
				xapUrl: '../../lib/upload/Moxie.xap',
				
				success: function(imgUrl) {
					$(file).attr('data-address',imgUrl).val($(id2).find('.fileName').val()).attr('title',$(id2).find('.fileName').val());
				}
			};
			$(clazz).bsPlupload(options);
	  }
	  
});
