layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate', 'upload','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var upload = layui.upload,
	own = layui.own;
	var laydate = layui.laydate;
	var signDate;
	var laycan2;
	var roleType = own.getHref('roleType');
	 
		//日期
		laydate.render({
		    elem: '#signDate',
		    lang: lang_ver,
		    done: function(value, date, endDate){
		        console.log(value); //得到日期生成的值，如：2017-08-18
		        signDate = value;
		        console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
		        console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
		    }
		});
	 
	 
		//加(装港)
		$('.s-icon-zhuang').on('click',function(){
	     var cloneDiv=$(this).parents('.wsUl').clone();
			cloneDiv.find('.s-icon').removeClass('s-icon-plus');
			cloneDiv.find('.s-icon').addClass('s-icon-less');
			cloneDiv.find('.s-input').val('');
			
			autocomplete(load,cloneDiv.find('.loadPort'));
		  	autocomplete(unload,cloneDiv.find('.unloadPort'));
		  	autocomplete(oil,cloneDiv.find('.oilType'));
			$('.all').append(cloneDiv);
		});
		
		//加(卸港)
		$('.s-icon-xie').on('click',function(){
			var cloneDiv=$(this).parents('.wsUl').clone();
			cloneDiv.find('.s-icon').removeClass('s-icon-plus');
			cloneDiv.find('.s-icon').addClass('s-icon-less');
			cloneDiv.find('.s-input').val('');
			
			autocomplete(load,cloneDiv.find('.loadPort'));
		  	autocomplete(unload,cloneDiv.find('.unloadPort'));
		  	autocomplete(oil,cloneDiv.find('.oilType'));
			$('.xiegang').append(cloneDiv);
		});
		
		//减
		$('body').on('click',' .s-icon-less',function(){
		     $(this).parents('.wsUl').remove();
		});
		
	 
	  //开始
	  laydate.render({
		    elem: '#pactBeg' 
		   ,type: 'datetime'
		   ,lang: lang_ver
		   ,format: 'yyyy-MM-dd HH:mm'
		   ,done:function(value, date, endDate){
			   console.log(value); //得到日期生成的值，如：2017-08-18
			   var htmlStr='<input type="text" readonly="readonly" id="pactEnd" placeholder="" name="pactEnds" class="s-input width_40" />';
		    	$('#pactBeg').parent().find('#pactEnd').remove();
		    	$('#pactBeg').parent().append(htmlStr);
		    	laydate.render({
		    		elem: '#pactEnd',
		    		type: 'datetime',
		    		lang: lang_ver,
		    		min:value,
		    		format: 'yyyy-MM-dd HH:mm',
		    		btns: ['clear', 'confirm'],
		    		done:function(value, date, endDate){
		    			console.log(value); //得到日期生成的值，如：2017-08-18
		    			
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
				    		elem: $(This).parent().find('.time2').get(0), 	/**指定元素5*/
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
		
		
		
	  /**==================文件上传==================*/
	  //img上传
	  var temp = '<p>';
	  	temp = temp + '<input type="hidden" class="storePath" value="{{filePath}}" />';
		temp = temp + '<input type="hidden" class="fileName" value="{{fileName}}" />';
		temp = temp + '<input type="hidden" class="fileSize" value="{{fileSize}}" />';
		temp = temp + '<input type="hidden" class="storeName" value="{{fileId}}" /></p>'; 
	  
	  //上传绑定
	  function fileUp(id, clazz,dir,file,id2) {
			var options = {
				multi_selection: false, 	/**设置是否多选*/
				listId: id, 
				itemTemplate: temp, 		/**设置模板*/
				signatureUrl: configUitl.getOssServerPath(shipServer,dir),
				dir: dir,
				filters: {
					max_file_size: '20M',
					mime_types: [ 			/**上传文件后缀名*/
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
	  
	 //协议文件上传
	 fileUp('accessory', '#btn','transport','#cunImg','#accessory');
	 
	//上传文件的删除
	$('body').on('click',' .fs-close',function(){
		$(".accessoryAll").children().remove();		/**移除文件 accessoryAll 标签下文件内容*/
		$("#cunImg").attr("value","");				/**清空 cunImg 各项值*/
		$("#cunImg").attr("title","");
		$("#cunImg").attr("data-address","");
	});
	 
	
	
	 
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
	
   
   
   	/**=============装卸港油种维护=============*/
    ports();	
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
	  	autocomplete(load,'.loadPort');
	  	autocomplete(unload,'.unloadPort');
	  	autocomplete(oil,'.oilType');
	};
	
	
	/**==================查询经纪人船东===============*/
	portss();	
	function portss(){
		 var paramType={};
		 var types=["4","5"];		/**会员资质类型 1炼厂,2贸易商,3商检,4船东,5船经纪人,6船代,7转租船东*/
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
					
					window.brokerArr = [];	/**经纪人*/
					window.ownerArr = [];	/**船东*/
					
					var arr=data.datas;
					if(arr!=null){
						for(var i=0;i<arr.length;i++){
							if(arr[i].type=="4"){			/**船东*/
								arr[i].code=arr[i].traderId;
								arr[i].value=arr[i].traderName;
								arr[i].subGroup=arr[i].traderName;
								ownerArr.push(arr[i]);
								
							}else if(arr[i].type=="5"){		/**经纪人*/
								arr[i].code=arr[i].traderId;
								arr[i].value=arr[i].traderName;
								arr[i].subGroup=arr[i].traderName;
								brokerArr.push(arr[i]);
							}
						}
						autocomplete(brokerArr,'.broker');
						autocomplete(ownerArr,'.owner');
					}
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
	}
	
	
	//返回上一页
	$('.goto').on('click',function(){
		window.location.href = document.referrer;
	});
	
	
	 var type;
	 (function(){
	 	//接收地址栏参数
		function GetRequest(){
		   var url = location.search; 	/**获取url中"?"符后的字串*/   
		   var theRequest = new Object();
		   if (url.indexOf("?") != -1) {
		      var str = url.substr(1);
		      strs = str.split("&");
		      for(var i = 0; i < strs.length; i ++) {
		         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
		      }
		   }
		   return theRequest;   
		}   
		var data = GetRequest();
		uuid = data.uuid;
		shipPlateUuid = data.shipPlateUuid;
		type = data.type;
		$('#uuid').val(uuid);
		$('#shipPlateUuid').val(shipPlateUuid);
	 })();
	 
	 
	 
	 //查询船名
	 queryShip();
	 autocomplete(shipArr,'.shipName');
	 
	 //查询船名
	 function queryShip(){
		 var obj = {};
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
	 };
	 
		
	 //回显数据
	 (function(){
			var params = {};
		 	params.uuid = uuid;		/**询盘或报盘的uuid*/
		 	params.type = type;
			 $.ajax({
					type: "POST",
					url: shipServer+"/pallet/palleDetailByUuid.json",
					data: JSON.stringify(params),
					dataType: "json",
					contentType:"application/json",
					async:false,
					success: function(data) {  
						console.log(data);
						if(data.status == 0){
							if (type == "1"){
								$('#shipName').val(data.datas.ship.name).attr('code',data.datas.ship.sysShipUuid);
								$('#carrierName').val(data.datas.inten.epMemberName).attr('code',data.datas.inten.epMemberId);
								$('#shipOwner').val(data.datas.ship.shipOwner).attr('code',data.datas.ship.shipOwnerId);
								$('#minQuantity').val(formatNumber(data.datas.inten.coMinQuantity,3,1));
								$('#ws').val(formatNumber(data.datas.inten.coWs,2,1));
								$('#demurrage').val(formatNumber(data.datas.inten.coDemurrage,2,1));
								$('#dockTime').val(formatNumber(data.datas.inten.coDockTime,2,1));
								$('#pactSpeed').val(data.datas.inten.coPactSpeed);
								$('#remark').val(data.datas.inten.coRemark);
								var laycanBeg = data.datas.inten.coPactBeg;
								var laycanEnd = data.datas.inten.coPactEnd;
								$('#pactBeg').val(dateChange(laycanBeg));
								$('#pactEnd').val(dateChange(laycanEnd));
							} else {
								$('#shipOwner').val(data.datas.ship.shipOwner).attr('code',data.datas.ship.shipOwnerId);
								$('#shipName').val(data.datas.ship.name).attr('code',data.datas.ship.sysShipUuid);
								$('#carrierName').val(data.datas.clause.palletMemberName).attr('code',data.datas.clause.palletMemberId);
				    			$('#minQuantity').val(formatNumber(data.datas.clause.minQuantity,3,1));
				    			$('#ws').val(formatNumber(data.datas.clause.ws,2,1));
				    			$('#demurrage').val(formatNumber(data.datas.clause.demurrage,2,1));
				    			$('#dockTime').val(formatNumber(data.datas.clause.dockTime,2,1));
				    			$('#pactSpeed').val(data.datas.clause.pactSpeed);
				    			$('#remark').val(data.datas.clause.remark);
				    			var laycanBeg = data.datas.clause.pactBeg;
				    			var laycanEnd = data.datas.clause.pactEnd;
				    			$('#pactBeg').val(dateChange(laycanBeg));
				    			$('#pactEnd').val(dateChange(laycanEnd));
								
							}
							// 回显经纪人
							if(data.datas.ship.brokerName != null){
								$('#brokerName').val(data.datas.ship.brokerName).attr('code',data.datas.ship.brokerId);
							}
							
							var traderName = data.datas.listLoad.traderName;
							if (traderName != null){
								$('#WSRes').val(traderName);
							}
							
							
							//装港,油种,数量回显
							var arrLoad =data.datas.listLoad;
							var listRegion =data.datas.listRegion;
							
							//港口
							if (arrLoad != null && arrLoad.length > 0){
								for (var i = 0; i < arrLoad.length; i++) {
									var loadPort = arrLoad[i].loadPort;
									var quantity = arrLoad[i].quantity;
									var unitName = arrLoad[i].unitName;
									var oilType = arrLoad[i].oilType;
									var loadPortCode = arrLoad[i].loadPortCode;
									var oilTypeCode = arrLoad[i].oilTypeCode;
									
									if (i == 0){
										//回显单条记录
										$('#loadPort').val(loadPort) ;
										$('#loadPort').attr("nameFn",loadPort);
										$('#loadPort').attr("code",loadPortCode);
										$('#oilType').val(oilType) ;
										$('#oilType').attr("nameFn",oilType);
										$('#oilType').attr("code",oilTypeCode);
										$('#quantity').val(formatStr(quantity,3,1));
										$('#unitName').val(unitName);
									} else {
										//多条装港记录回显
										var cloneDiv=$('#quantity').parents('.wsUl').clone();				/**复制输入框*/
										cloneDiv.find('.s-icon').removeClass('s-icon-plus');
										cloneDiv.find('.s-icon').addClass('s-icon-less');
										cloneDiv.find('.s-input').val('');
										
										cloneDiv.find('.loadPort').val(loadPort) ;							/**下拉框回显*/
										cloneDiv.find('.loadPort').attr("nameFn",loadPort);
										cloneDiv.find('.loadPort').attr("code",loadPortCode);
										cloneDiv.find('.oilType').val(oilType) ;
										cloneDiv.find('.oilType').attr("nameFn",oilType);
										cloneDiv.find('.oilType').attr("code",oilTypeCode);
										cloneDiv.find('.quantity').val(formatStr(quantity,3,1));
										cloneDiv.find('.unitName').val(unitName);
										
										autocomplete(load,cloneDiv.find('.loadPort'));						/**填充下拉框*/
									  	autocomplete(oil,cloneDiv.find('.oilType'));
										$('.zhuanggangAll').append(cloneDiv);
									}
								}
							} else if (listRegion != null && listRegion.length > 0){
								//区域
								for (var i = 0; i < listRegion.length; i++) {
									var loadPort = listRegion[i].loadRegion;
									var quantity = listRegion[i].quantity;
									var unitName = listRegion[i].unitName;
									var oilTypeCode = listRegion[i].oilTypeCode;
									var oilType = listRegion[i].oilType;
									
									if (i == 0){
										//单条记录回显
										$('#loadPort').val(loadPort) ;
										$('#loadPort').attr("nameFn",loadPort);
										$('#loadPort').attr("code",loadPort);
										$('#quantity').val(formatStr(quantity,3,1));
										$('#unitName').val(unitName);
										$('#oilType').val(oilType);
										$('#oilType').attr("nameFn",oilType);
										$('#oilType').attr('code',oilTypeCode);
									} else {
										//多条记录回显
										var cloneDiv=$('#quantity').parents('.wsUl').clone();				/**复制输入框*/
										cloneDiv.find('.s-icon').removeClass('s-icon-plus');
										cloneDiv.find('.s-icon').addClass('s-icon-less');
										cloneDiv.find('.s-input').val('');
										
										cloneDiv.find('.loadPort').val(loadPort) ;							/**下拉框回显*/
										cloneDiv.find('.loadPort').attr("nameFn",loadPort);
										cloneDiv.find('.loadPort').attr("code",loadPort);
										cloneDiv.find('.oilType').val(oilType) ;
										cloneDiv.find('.oilType').attr("nameFn",oilType);
										cloneDiv.find('.oilType').attr("code",oilTypeCode);
										cloneDiv.find('.quantity').val(formatStr(quantity,3,1));
										cloneDiv.find('.unitName').val(unitName);
										
										autocomplete(load,cloneDiv.find('.loadPort'));						/**填充下拉框*/
									  	autocomplete(oil,cloneDiv.find('.oilType'));
										$('.zhuanggangAll').append(cloneDiv);
									}
								}
							}
							
							//卸港回显
							var arrUnload =data.datas.listUnload;
							if (arrUnload != null && arrUnload.length > 0){
								for (var i = 0; i < arrUnload.length; i++) {
									var unloadPort = arrUnload[i].unloadPort;
									var unloadPortCode = arrUnload[i].unloadPortCode;
									var oilType = arrUnload[i].oilType;
									
									if (i == 0){
										//单条记录回显
										$('#unloadPort').val(unloadPort);
										$('#unloadPort').attr('nameFn',unloadPort);
										$('#unloadPort').attr('code',unloadPortCode);
									} else {
										//多条记录回显
										var cloneDiv=$('#unloadPort').parents('.wsUl').clone();		/**复制输入框*/
										cloneDiv.find('.s-icon').removeClass('s-icon-plus');
										cloneDiv.find('.s-icon').addClass('s-icon-less');
										cloneDiv.find('.s-input').val('');
										
										cloneDiv.find('.unloadPort').val(unloadPort);				/**下拉框回显*/
										cloneDiv.find('.unloadPort').attr('nameFn',unloadPort);
										cloneDiv.find('.unloadPort').attr('code',unloadPortCode);
										
										autocomplete(unload,cloneDiv.find('#unloadPort'));			/**填充下拉框*/
										$('.xiegangAll').append(cloneDiv);
									}
								}
							}
						} else {
							layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")},function(index){layer.close(index);});
						}
					},
					error:function(){
						layer.alert($.i18n("JAVASCRIPT003"),{title:$.i18n("001"),btn:$.i18n("002")},function(index){layer.close(index);});
					}
				});
		 })();
	   
	 $('#submitsw').on('click', function (){
		 layer.confirm($.i18n("JAVASCRIPT0060"), {title:$.i18n("001"),btn:[$.i18n("002"),$.i18n("003")]},function(index){
			 //校验必填
			 var uuid = $('#uuid').val();
			 var agreementCode = $('#agreementCode').val();
			 var signDate = $('#signDate').val();
			 
			 //获取租船人、船东、经纪人、船名
			 var carrierName = $('#carrierName').val();
			 var carrierId = $('#carrierName').attr('code');
			 var shipOwner = $('#shipOwner').val();
			 var shipOwnerId = $('#shipOwner').attr('code');
			 var brokerName = $('#brokerName').val();
			 var brokerId = $('#brokerName').attr('code');
			 var shipName=$("#shipName").val(); //获取选中的项
			 var sysShipUuid=$("#shipName").attr('code'); //获取选中的项
			 
			 var minQuantity = $('#minQuantity').val();
			 var ws = $('#ws').val();
			 var demurrage = $('#demurrage').val();
			 var dockTime = $('#dockTime').val();
			 var pactSpeed = $('#pactSpeed').val();
			 var palletUuid = $('#palletUuid').val();
			 var remark = $('#remark').val();
			 var shipPlateUuid = $('#shipPlateUuid').val();
			 var accessory = $('#cunImg').val();
			 var wsRes = $('#WSRes').val();
			 var accessoryPath = $('#cunImg').attr('data-address');
			 var pactBeg = new Date($('#pactBeg').val());
			 var pactEnd = new Date($('#pactEnd').val());
			 
			 
			 /**装港、卸港、油种、数量*/
		     var quantitys = "";
		     var unloadPorts = "";
		     var loadPorts = "";
		     var oilTypes = "";
		     var quantitysCopy = "";
		     var unloadPortsCopy = "";
		     var loadPortsCopy = "";
		     var oilTypesCopy = "";
		     
		     
		     // 船名
		     if( shipName == null || shipName == '' || sysShipUuid == null || sysShipUuid == ''){
		    	 message($.i18n("JAVASCRIPT0016"));return false;
		     }
		     // 装期
		     if( pactBeg == null || pactBeg == '' ||  pactEnd == null || pactEnd == ''){
		    	 message($.i18n("JAVASCRIPT009"));return false;
		     }
		     // 合同航速
		     if(pactSpeed == null || pactSpeed == ''){
		    	 message($.i18n("JAVASCRIPT0035"));return false;
		     }
		     // 合同航速
		     if(pactSpeed == null || pactSpeed == ''){
		    	 message($.i18n("JAVASCRIPT0035"));return false;
		     }
		     // 允许装卸时间
		     if(dockTime == null || dockTime == ''){
		    	 message($.i18n("JAVASCRIPT0034"));return false;
		     }
		     // 租约日
		     if(signDate == null || signDate == ''){
		    		message($.i18n("JAVASCRIPT0013"));return false;
		    	}
		     // 租船人
		     if(carrierName == null || carrierName == ''){
		    		message($.i18n("JAVASCRIPT00111"));return false;
		    	}
		     // 船东
		     if(shipOwner == null || shipOwner == '' || shipOwnerId == ''){
		    		message($.i18n("JAVASCRIPT00116"));return false;
		    	}
		     // 最小货量
		     if(minQuantity == null || minQuantity == ''){
		    		message($.i18n("JAVASCRIPT00113"));return false;
		    	}
		     // WS
		     if( ws == null || ws == ''){
		    		message($.i18n("JAVASCRIPT0032"));return false;
		    	}
		     // 滞期费率
		     if( demurrage == null || demurrage == ''){
		    	 message($.i18n("JAVASCRIPT0033"));return false;
		     }
		     var flag1 = true;
		     
		     
		     
		     /**
		      * 装港、卸港、油种
		      */
		     /**================================卸港================================*/
		     var unloadPortCodes="";
		     $('.is-unloadPort').each(function(i, e){
		    	 	var sameFlag = $(e).attr("nameFn");
			    	var unloadPort = $(e).val();		    /**取卸港*/
			    	var unloadPortCode="";   				/**取code*/	
			    	
			    	//判断是否自定义
			    	if(unloadPort==sameFlag){
			    		//否
			    		unloadPortCode=$(e).attr("code");
			    	}else{
			    		//是，code存值
			    		unloadPortCode=sameFlag;
			    	}
			    	
			    	if(unloadPort == null || unloadPort == '' || unloadPortCode=='' || unloadPortCode==null){						
			    		message($.i18n("JAVASCRIPT008"));flag1=false;return false;  
			    	}
			    	unloadPorts = unloadPorts +unloadPort+"/";					/**拼卸港*/
			    	unloadPortCodes = unloadPortCodes +unloadPortCode+"/";		/**拼code*/
			    	
			    	unloadPortsCopy = unloadPortsCopy +unloadPort+"&&";
			 })
		     if (!flag1){
		    	 return false;
		     }
		     var flag2 = true;
		     
			 /**================================装港、数量+单位================================*/
		     var loadPortCodes="";
		     $('.is-loadPort').each(function(i, e){
		    	  var sameFlag=$(e).attr('nameFn');
		    	  var loadPort = $(e).val();
		    	  var quantity =  $(e).parents('li').find('.quantity').val();
		    	  var loadPortCode="";
		    	  
		    	  //判断是否自定义
		    	  if(sameFlag==loadPort){
		    		  //否
		    		  loadPortCode=$(e).attr("code");
		    	  }else{
		    		  //是，code存值
		    		  loadPortCode=sameFlag;
		    	  }
		    	  
		    	  //选中的值
		    	  var unitName = $(e).parents('li').find('.unitName option:selected').val();
		    	 
		    	  if(loadPort == null || loadPort == '' || loadPortCode==null || loadPortCode==''){
			    		message($.i18n("JAVASCRIPT007"));flag2=false;return false;
			    	}
		    	  if(quantity == null || quantity == ''){
		    		  message($.i18n("JAVASCRIPT0017"));flag2=false;return false;
		    	  }
		    	  
		    	  loadPorts = loadPorts + loadPort +"/";			/**拼装港*/
		    	  quantity =  delNumFormat(quantity);			
		    	  quantitys=quantitys+quantity+unitName+"/";		/**拼数量*/
		    	  loadPortCodes = loadPortCodes + loadPortCode +"/";/**拼code*/
		    	  
		    	  loadPortsCopy = loadPortsCopy + loadPort +"&&";
		    	  quantitysCopy=quantitysCopy+quantity+unitName+"&&";
		     })
		     if (!flag2){
		    	 return false;
		     }
		     var flag3= true;
		     
		     /**================================油种================================*/
		     var oilTypeCodes="";
		     $('.is-oilType').each(function(i, e){
		    	 var sameFlag=$(e).attr('nameFn');
		    	 var oilType = $(e).val();
		    	 var oilTypeCode = "";
		    	 
		    	 //判断是否自定义
		    	 if(sameFlag==oilType){
		    		 //否
		    		 oilTypeCode=$(e).attr("code");
		    	 }else{
		    		 //是，code存值
		    		 oilTypeCode=sameFlag;
		    	 }
		    	 
		    	 if(oilType == null || oilType == '' || oilTypeCode==null || oilTypeCode==''){
		    		 message($.i18n("JAVASCRIPT006"));flag3=false;return false;
		    	  }
		    	 
		    	 oilTypes = oilTypes + oilType +"/";				/**拼油种*/
		    	 oilTypeCodes = oilTypeCodes + oilTypeCode +"/";	/**拼code*/
		    	 
		    	 oilTypesCopy = oilTypesCopy + oilType +"&&";
		     })
		     if (!flag3){
		    	 return false;
		     }
		     
		     
			 // 序列化数据
			 var params = {};
		     
			 dockTime=delNumFormat(dockTime);
			 ws=delNumFormat(ws);
			 minQuantity=delNumFormat(minQuantity);
			 demurrage=delNumFormat(demurrage);
			 params.uuid = uuid;
			 params.agreementCode = agreementCode;
			 params.signDate = signDate;
			 params.pactEnd = pactEnd;
			 params.pactBeg = pactBeg;
			 params.laycan = $('#pactBeg').val()+"/"+$('#pactEnd').val();
			 
			 
			 /**租船人、船名、船东、经纪人*/
			 params.carrierName = carrierName;
			 params.shipOwner = shipOwner;
			 params.shipName = shipName;
			 params.brokerName = brokerName;
			 params.carrierId = carrierId;
			 params.shipOwnerId = shipOwnerId;
			 params.sysShipUuid = sysShipUuid;
			 params.brokerId = brokerId;
			 
			 /**油种，装港，卸港*/
			 params.loadPort = loadPorts;
			 params.loadPortCode = loadPortCodes;
			 params.unloadPort = unloadPorts;
			 params.unloadPortCode=unloadPortCodes;
			 params.quantity = quantitys;
			 params.oilType = oilTypes;
			 params.oilTypeCode = oilTypeCodes;
			 
			 
			 params.loadPortCopy = loadPortsCopy;
			 params.unloadPortCopy = unloadPortsCopy;
			 params.minQuantity = minQuantity;
			 params.ws = ws;
			 params.wsRes = wsRes;
			 params.demurrage = demurrage;
			 params.dockTime = dockTime;
			 params.pactSpeed = pactSpeed;
			 params.palletUuid = palletUuid;
			 params.remark = remark;
			 params.shipPlateUuid = shipPlateUuid;
			 params.accessoryPath = accessoryPath;
			 params.accessory = accessory;
			 params.signDate = signDate;
			 params.type = type;
			 params.oilTypeCopy = oilTypesCopy;
			 params.quantityCopy = quantitysCopy;
			 
			 var loadIndex = layer.load(0, {shade: false});
			 
			 $.ajax({
		    		type: "POST",
		    		url: shipServer+"/agreement/agreementSave.json",
		    		data: JSON.stringify(params),
		    		dataType:"json",
		    		contentType:"application/json",
		    		success: function(data) {
		    			layer.close(loadIndex);
						if(data.status == 0){
							layer.alert($.i18n("JAVASCRIPT0012"), {title:$.i18n("001"),btn:$.i18n("002")},function(index) {
							    layer.close(index);
							    if (roleType == '1'){
							    	window.location.href=shipServer+"/shipPactNew/shipPactListNewOwner.htm";
							    } else {
							    	window.location.href=shipServer+"/shipPactNew/shipPactListNew.htm";
							    }
							});
						} else {
							message($.i18n(data.message));
						}
					}
		    	});
 		});
		 
	 })
	 
	 
	//弹框
	function message(mess){
		layer.alert(mess,{title:$.i18n("001"),btn:$.i18n("002")},function(index) {
			layer.close(index);
		});
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
})
	
	
	
