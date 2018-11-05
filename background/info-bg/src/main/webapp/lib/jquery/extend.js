$.extend({
	ajaxFn:function(option){
		var defualts={
			url:'',
			type:'POST',
			data: {},
			async:true,
			contentType: 'application/json',
			success:function(){
				
			},
			error:function(){
				
			}
		}
		var settings=$.extend(true,defualts,option);
		var jsonData = null;
		if(typeof settings.data == 'string' || settings.type.toLowerCase() == 'get'){
			jsonData = settings.data;
		}else{
			jsonData = JSON.stringify(settings.data);
		}
		$.ajax({
			url: settings.url,
			data: jsonData,
			type: settings.type,
			contentType: settings.contentType,
			async:settings.async,
			success: function(data) {
				settings.success(data, status);
			},
			error: function(error) {
				settings.error(error);
			}
		})
	},
	getHref:function(keyStr){  //获取地址拦？后面的vue值  //  $.getHref('name')
      	var str=window.location.href;
      	str=str.substring(str.indexOf('?')+1);
      	var hrefArr=str.split('&');
      	var end = false;
      	for(var i=0; i<hrefArr.length;i++){
      		var linARR=hrefArr[i].split('=');
      		if(linARR[0]==keyStr){
      			end=linARR[1];
      			break;
      		}
      	}
      	return end
    },
    division:function(arg1,arg2){  // 除法
		var t1=0,t2=0,r1,r2;  
		try{t1=arg1.toString().split(".")[1].length}catch(e){}  
		try{t2=arg2.toString().split(".")[1].length}catch(e){}  
		with(Math){  
			r1=Number(arg1.toString().replace(".",""))  
			r2=Number(arg2.toString().replace(".","")) 
			return $.ride((r1/r2),pow(10,t2-t1));  
		}  
	},
	ride:function(arg1,arg2){   //乘法 
	    var m=0,s1=arg1.toString(),s2=arg2.toString();  
	    try{m+=s1.split(".")[1].length}catch(e){}  
	    try{m+=s2.split(".")[1].length}catch(e){}  
	    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)  
	},  
	add:function(arg1,arg2){    //加法  
		var r1,r2,m;  
		try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}  
		try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}  
		m=Math.pow(10,Math.max(r1,r2))  
		return (arg1*m+arg2*m)/m  
	},  
	subduction:function(arg1,arg2){   //减法  
		var r1,r2,m,n; 
		try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0} 
		try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0} 
		m=Math.pow(10,Math.max(r1,r2)); 
	    n=(r1>=r2)?r1:r2; 
		return ((arg1*m-arg2*m)/m).toFixed(n); 
	}
	
})
$.fn.extend({
	bsPlupload: function(option) { //上传文件插件
		var accessid = '',
			host = option.host || 'http://'+globleBucket+'.oss-cn-beijing.aliyuncs.com',
			policyBase64 = '',
			signature = '',
			callbackbody = '',
			key = '',
			expire = 0,
			now = timestamp = Date.parse(new Date()) / 1000;


		function send_request(url) {
			var xmlhttp = null;
			if(window.XMLHttpRequest) {
				xmlhttp = new XMLHttpRequest();
			} else if(window.ActiveXObject) {
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}

			if(xmlhttp != null) {
				/*var auth;
				if(window.localStorage) {
					var tempKey = $.getCookie('bailian-auth-key' + paths.HJ);
					auth = window.localStorage.getItem(tempKey);
				} else if($.getCookie('bailian-auth' + paths.HJ)) {
					auth = $.getCookie('bailian-auth' + paths.HJ);
				}*/
				xmlhttp.open("GET", url, false);
				/*if(auth && auth != 'undefined') {
					xmlhttp.setRequestHeader('Authorization', auth);
				}*/
				xmlhttp.send(null);
				return xmlhttp.responseText
			} else {
				alert("Your browser does not support XMLHTTP.");
			}
		}

		function get_signature() {
			now = timestamp = Date.parse(new Date()) / 1000;
			var url;
			
			if(option && option.signatureUrl) {
				if(option.signatureUrl.indexOf('?')>=0){
					url=option.signatureUrl+'&timenima'+new Date();
				}else{
					url=option.signatureUrl+'?timenima='+new Date();
				}
			} else {
				return false;
			}
			if(expire < now + 3) {
				var body = send_request(url);
				//var obj = eval ("(" + body + ")");
				//obj2=  eval ("(" + obj.data + ")");
				//var obj = JSON.parse(body);
				obj2 = JSON.parse(body);

				//host = obj2['host'];
				policyBase64 = obj2['policy'];
				accessid = obj2['accessid'];
				signature = obj2['signature'];
				expire = parseInt(obj2['expire']);
				callbackbody = obj2['callback'];
				key = obj2['dir'];
				return true;
			}
			return false;
		}

		function set_upload_param(up, filename) {
			get_signature();
			var new_multipart_params = {   // 上传时 附加参数
				'key': key +'/' +filename,
				'policy': policyBase64,
				'OSSAccessKeyId': accessid,
				'success_action_status': '200', //让服务端返回200,不然，默认会返回204
				'callback': callbackbody,
				'signature': signature
			};
			
			up.setOption({
				'url': host,
				'multipart_params': new_multipart_params
			});

			up.start();
		}

		var flash_swf_url = option && option.swfUrl;
		var silverlight_xap_url = option && option.xapUrl;
		var multi_selection = option && option.multi_selection;
		var callbackMethod = option && option.callbackMethod;
		var max_file_size = option && option.max_file_size;
		var filters = option && option.filters;
		var max_file_count = option && option.max_file_count;
		var uploader = new plupload.Uploader({
			runtimes: 'html5,flash,silverlight,html4',
			browse_button: this[0],
			url: "/info",
			max_file_size : max_file_size || '1MB',
			max_file_count : max_file_count || 1,
			multi_selection: multi_selection,   // 多选
			filters: filters || [],
			flash_swf_url: flash_swf_url || '',
			silverlight_xap_url: silverlight_xap_url || '',
			
			init: {
				PostInit: function() {
					set_upload_param(uploader, "");
					if(option && option.submitButtonId) {
						$("#" + option.submitButtonId).unbind().bind("click", function() {
							uploader.start();
							return false;
						});
					}
					if(option && option.listId && option.removeButtonClass) {
						$("#" + option.listId).on("click", "." + option.removeButtonClass, function(e) {
							var id = $(this).data("id");
							uploader.removeFile(id);
							$("#" + id).remove();
							e.preventDefault();
						});
					}
				},
				FilesAdded: function(up, files) {
					$("#" + option.listId).html('');
					if(option && option.listId && option.itemTemplate) {

						plupload.each(files, function(file, i) {
							var strName=file.name.substring(file.name.lastIndexOf('.'));
							var item = option.itemTemplate.replace(/\{\{fileId}}/g, file.id+strName).replace(/\{\{fileName}}/g, file.name).replace(/\{\{fileSize}}/g, file.size).replace(/\{\{filePath}}/g, key + file.id+strName);
							$("#" + option.listId).append(item);

							$("#" + option.listId).append('<span class="jdt"><span class="jdt_ne"></span><p class="upLoadName">' + file.percent + '%</p></span>');

							previewImage(files[i], function(imgsrc) {
								var imageTag = option.imageTemplate || '<img src="' + imgsrc + '" />';
								imageTag = imageTag.replace(/{imageUrl}/g, imgsrc);
								if(option.listId=='shop-banner-ul'){
									$("#" + option.listId).append('<li>'+imageTag+'</li>');
									var $a=$('<a>');
								    if(i==0){
								    	$a.addClass('active');
								    }
								    $a.appendTo('.shop-banner-btn');
								}else{
									$("#" + option.listId).append(imageTag);
								}
							});
							
							set_upload_param(up, file.id+strName); //上传
						});
					}

					function previewImage(file, callback) { //file为plupload事件监听函数参数中的file对象,callback为预览图片准备完成的回调函数
						if(!file || !/image\//.test(file.type)) return; //确保文件是图片
						if(file.type == 'image/gif') { //gif使用FileReader进行预览,因为mOxie.Image只支持jpg和png
							var fr = new mOxie.FileReader();
							fr.onload = function() {
								callback(fr.result);
								fr.destroy();
								fr = null;
							}
							fr.readAsDataURL(file.getSource());
						} else {
							var preloader = new mOxie.Image();
							preloader.onload = function() {
								//preloader.downsize(300, 300); //先压缩一下要预览的图片,宽300，高300
								var imgsrc = preloader.type == 'image/jpeg' ? preloader.getAsDataURL('image/jpeg', 80) : preloader.getAsDataURL(); //得到图片src,实质为一个base64编码的数据
								callback && callback(imgsrc); //callback传入的参数为预览图片的url
								preloader.destroy();
								preloader = null;
							};
							preloader.load(file.getSource());
						}
					}

				},
				BeforeUpload: function(up, file) {
					var strName=file.name.substring(file.name.lastIndexOf('.'));
					set_upload_param(up, file.id+strName);
					if(option.before) {
						option.before();
					}
				},
				Error: function(up, file) {
					if(file.code=='-600'){
						alert('上传的文件必须<'+filters.max_file_size)
					}
				},
				UploadProgress: function(up, file) {
					$("#" + option.listId).find(".jdt_ne").css('width', file.percent + '%');
					$("#" + option.listId).find(".upLoadName").html(file.percent + '%');
				},
				FileUploaded: function(up, file, info) {
					//alert(file.id)
					var strName=file.name.substring(file.name.lastIndexOf('.'));
					$('.jdt').animate({
						'opacity': '0'
					}, 500, function() {
						$(this).remove();
					});
					if(option.success) {
						option.success(host+'/'+option.dir+'/'+file.id+strName);
					}
					if (info.status == 200){
						if ($.isFunction(callbackMethod)) {
							callbackMethod.call(this);
						}
					}
				}
			}
		});
		uploader.init();
		return uploader;
	}
})