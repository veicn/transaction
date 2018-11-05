layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate', 'upload'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var upload = layui.upload;
	
	//发票附件上传
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
								extensions: "pdf,jpg,png"
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
	  
	 fileUp('receiptAccessory', '#btn','transport','#cunImg','#receiptAccessory');
	
	 //计算费用
	 $('#jsfy').on('click', function (){
		 var freightQuantily = $('#freightQuantily').val();
		 var freightOverageQuantily = $('#freightOverageQuantily').val();
		 var freightOverageFlatrate = $('#freightOverageFlatrate').val();
		 var freightOverageRate = $('#freightOverageRate').val();
		 var freightOveragePct = $('#freightOveragePct').val();
		 var addressCommissionPct = $('#addressCommissionPct').val();
		 
		 var params = {};
		 if (freightQuantily == '' || freightOverageQuantily == '' ||
			 freightOverageFlatrate == '' ||  freightOverageRate == '' ||
			 freightOveragePct == '' || addressCommissionPct== ''){
			 return false;
		 }
		 params.freightQuantily = delNumFormat(freightQuantily);
		 params.freightOverageQuantily = delNumFormat(freightOverageQuantily);
		 params.freightOverageFlatrate = delNumFormat(freightOverageFlatrate);
		 params.freightOverageRate = delNumFormat(freightOverageRate);
		 params.freightOveragePct = delNumFormat(freightOveragePct);
		 params.addressCommissionPct = delNumFormat(addressCommissionPct);
		 //后台计算
		 $.ajax({
	    		type: "POST",
	    		url: shipServer+"/statements/jsfy.json",
	    		data: JSON.stringify(params),
	    		dataType:"json",
	    		contentType:"application/json",
	    		success: function(data) {
					if(data.status == 0){
						 $('#freightTotal').val(formatStr(data.datas.freightTotal,2,1));
						 $('#freightOverageTotal').val(formatStr(data.datas.freightOverageTotal,2,1));
						 $('#addressCommissionTotal').val(formatStr(data.datas.addressCommissionTotal,2,1));
						 $('#balance').val(formatStr(data.datas.balance,2,1));
					} else {
						layer.alert($.i18n(data.message), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
						    layer.close(index);
						});
					}
				},
				error: function(data) {
					layer.alert($.i18n("JAVASCRIPT003"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
					    layer.close(index);
					});
		        }
	    	});
	 });
	   // 返回上一页
		$('.goto').on('click',function(){
			window.location.href = document.referrer;
		});
	
	 (function(){
			function GetRequest() {   
			   var url = location.search; //获取url中"?"符后的字串   
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
			$('#shipPactUuid').val(data.shipPactUuid);
			$('#shipAgreementuuid').val(data.agreementUuid);
			$('#agreementStatementsId').val(data.code);
		 })();
	 $('#submit').on('click', function (){
		 //校验必填
		 var receiptCode = $('#receiptCode').val();
		 var receiptAccessoryPath = $('#cunImg').attr('data-address');
		 var agreementCode = $('#agreementStatementsId').val();
		 var payee = $('#payee').val();
		 var payer = $('#payer').val();
		 var receiptAccessory = $('#cunImg').val();
		 var freightQuantily = $('#freightQuantily').val();
		 var freightOverageQuantily = $('#freightOverageQuantily').val();
		 var Flatrate = $('#freightOverageFlatrate').val();
		 var Rate = $('#freightOverageRate').val();
		 var freightOveragePct = $('#freightOveragePct').val();
		 var addressCommissionPct = $('#addressCommissionPct').val();
		 var freightTotal = $('#freightTotal').val();
		 var freightOverageTotal = $('#freightOverageTotal').val();
		 var addressCommissionTotal = $('#addressCommissionTotal').val();
		 var balance = $('#balance').val();
		 var shipAgreementUuid = $('#shipAgreementuuid').val();
		 var shipPactUuid = $('#shipPactUuid').val();
		 
		 if(receiptCode == "") {
	  		 layer.alert($.i18n("JAVASCRIPT00157"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
	  			  layer.close(index);
	  			  return ;
	  		  });
	     }
		 if(agreementCode == "") {
	  		  layer.alert($.i18n("JAVASCRIPT00156"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
	  			  layer.close(index);
	  			  return ;
	  		  });
	     }
		 if(payee == "") {
	  		  layer.alert($.i18n("JAVASCRIPT00158"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
	  			  layer.close(index);
	  			  return ;
	  		  });
	     }
		 if(payer == "") {
	  		  layer.alert($.i18n("JAVASCRIPT00159"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
	  			  layer.close(index);
	  			  return ;
	  		  });
	     }
		 if(freightQuantily == "") {
	  		  layer.alert($.i18n("ITSH337"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
	  			  layer.close(index);
	  			  return ;
	  		  });
	     }
		 if(freightOverageQuantily == "") {
	  		  layer.alert($.i18n("ITSH338"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
	  			  layer.close(index);
	  			  return ;
	  		  });
	     }
		 if(Flatrate == "") {
	  		  layer.alert($.i18n("ITSH339"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
	  			  layer.close(index);
	  			  return ;
	  		  });
	     }
		 if(Rate == "") {
	  		  layer.alert($.i18n("ITSH340"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
	  			  layer.close(index);
	  			  return ;
	  		  });
	     }
		 if(freightOveragePct == "") {
	  		  layer.alert($.i18n("ITSH341"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
	  			  layer.close(index);
	  			  return ;
	  		  });
	     }
		 if(addressCommissionPct == "") {
	  		  layer.alert($.i18n("ITSH342"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
	  			  layer.close(index);
	  			  return ;
	  		  });
	     }
		 if(freightTotal == "") {
	  		  layer.alert($.i18n("ITSH344"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
	  			  layer.close(index);
	  			  return ;
	  		  });
	     }
		 if(freightOverageTotal == "") {
	  		  layer.alert($.i18n("ITSH345"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
	  			  layer.close(index);
	  			  return ;
	  		  });
	     }
		 if(addressCommissionTotal == "") {
	  		  layer.alert($.i18n("ITSH346"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
	  			  layer.close(index);
	  			  return ;
	  		  });
	     }
		 if(balance == "") {
	  		  layer.alert($.i18n("ITSH350"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
	  			  layer.close(index);
	  			  return ;
	  		  });
	     }
		 
		 
		 var params = {};
		 params.receiptCode = receiptCode;
		 params.agreementCode = agreementCode;
		 params.payee = payee;
		 params.payer = payer;
		 params.receiptAccessory = receiptAccessory;
		 params.receiptAccessoryPath = receiptAccessoryPath;
		 params.freightQuantily = delNumFormat(freightQuantily);
		 params.freightOverageQuantily = delNumFormat(freightOverageQuantily);
		 params.freightOverageFlatrate = delNumFormat(Flatrate);
		 params.freightOverageRate =delNumFormat(Rate);
		 params.freightOveragePct = delNumFormat(freightOveragePct);
		 params.addressCommissionPct = delNumFormat(addressCommissionPct);
		 params.freightTotal = delNumFormat(freightTotal);
		 params.freightOverageTotal = delNumFormat(freightOverageTotal);
		 params.addressCommissionTotal = delNumFormat(addressCommissionTotal);
		 params.balance =delNumFormat(balance);
		 params.shipAgreementUuid = shipAgreementUuid;
		 params.shipPactUuid = shipPactUuid;
		 
		 $.ajax({
	    		type: "POST",
	    		url: shipServer+"/statements/saveStatements.json",
	    		data: JSON.stringify(params),
	    		dataType:"json",
	    		contentType:"application/json",
	    		success: function(data) {
					if(data.status == 0){
						layer.alert($.i18n("JAVASCRIPT0021"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
								window.location.href = document.referrer;		
						});
					} else {
						layer.alert($.i18n(data.message), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
						    layer.close(index);
						});
					}
				},
				error: function(data) {
					layer.alert($.i18n("JAVASCRIPT003"), {title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
					    layer.close(index);
					});
		        }
	    	});
		 
	 })
	 
	 
	 
})
	
	
	
