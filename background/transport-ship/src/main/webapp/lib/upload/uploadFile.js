/**
 * Created by GHuang on 16/3/9.
 */
;(function($, plupload){
    $.fn.bsPlupload = function(option){
        var accessid = '',
            host = '',
            policyBase64 = '',
            signature = '',
            callbackbody = '',
            key = '',
            expire = 0,
            now = timestamp = Date.parse(new Date()) / 1000;

        function send_request(url) {
            var xmlhttp = null;
            if (window.XMLHttpRequest) {
                xmlhttp=new XMLHttpRequest();
            }
            else if (window.ActiveXObject) {
                xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
            }

            if (xmlhttp!=null) {
            	var auth;
				/*if(window.localStorage){
					var tempKey = $.getCookie('bailian-auth-key' + paths.HJ);
					auth = window.localStorage.getItem(tempKey);
				} else if($.getCookie('bailian-auth' + paths.HJ)) {
					auth = $.getCookie('bailian-auth' + paths.HJ);
				}
				if(auth && auth != 'undefined') {
					xmlhttp.setRequestHeader('Authorization', auth);
				}*/
                xmlhttp.open( "GET", url, false );
                xmlhttp.send(null);
                return xmlhttp.responseText
            } else {
                alert("Your browser does not support XMLHTTP.");
            }
        }

        function get_signature() {
            now = timestamp = Date.parse(new Date()) / 1000;
            var url;
            if (option && option.signatureUrl) {
                url = option.signatureUrl;
            } else {
                return false;
            }
            if (expire < now + 3) {
                var body = send_request(url);
                var obj = eval ("(" + body + ")");
                host = obj['host'];
                policyBase64 = obj['policy'];
                accessid = obj['accessid'];
                signature = obj['signature'];
                expire = parseInt(obj['expire']);
                callbackbody = obj['callback'];
                key = obj['dir'];
                return true;
            }
            return false;
        }

        function set_upload_param(up, filename) {
            get_signature();
            var new_multipart_params = {
                'key' : key + filename,
                'policy': policyBase64,
                'OSSAccessKeyId': accessid,
                'success_action_status' : '200', //让服务端返回200,不然，默认会返回204
                'callback' : callbackbody,
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
        var multi_selection=option && option.multi_selection;
        var uploader = new plupload.Uploader({
            runtimes : 'html5,flash,silverlight,html4',
            browse_button: this[0],
            url: "test",
            multi_selection:multi_selection,
            flash_swf_url: flash_swf_url || '',
            silverlight_xap_url: silverlight_xap_url || '',
            init: {
                PostInit: function() {
                    set_upload_param(uploader, "");
                    if (option && option.submitButtonId) {
                        $("#"+option.submitButtonId).unbind().bind("click", function () {
                            uploader.start();
                            return false;
                        });
                    }
                    if (option && option.listId && option.removeButtonClass) {
                        $("#"+option.listId).on("click", "."+option.removeButtonClass, function (e) {
                            var id = $(this).data("id");
                            uploader.removeFile(id);
                            $("#" + id).remove();
                            e.preventDefault();
                        });
                    }
                },
                FilesAdded: function(up, files){
                	 $("#"+option.listId).html('');
                    if (option && option.listId && option.itemTemplate) { 
                        plupload.each(files, function (file,i) {
                            var item = option.itemTemplate.replace(/\{\{fileId}}/g, file.id).replace(/\{\{fileName}}/g, file.name).replace(/\{\{fileSize}}/g, file.size).replace(/\{\{filePath}}/g, key+file.id+'_'+file.name);
                            $("#"+option.listId).append(item);
                        });
                    }
                },
                BeforeUpload: function(up, file) {
                    set_upload_param(up, file.id+'_'+file.name);
                },
                UploadProgress: function(up, file){
                    $("#"+file.id).find("b").html("<span>" + file.percent + "%</span>");
                },
                FileUploaded: function(up, file, info){
                	  var fileName = file.name;
                      var num	= lenByte(fileName);
                	if(num>350)  
                	{  
                		 $("#"+file.id).find("b").html('上传失败，文件名称过长。');
                        return null;
                	}else if (info.status >= 200 || info.status < 200) {
                        $("#"+file.id).find("b").html('上传成功！');
                        $("#"+file.id).attr("status","1");
                    }
                    else {
                        $("#"+file.id).find("b").html(info.response);
                    }
                }
            }
        });
        uploader.init();
        return uploader;
    }
}(jQuery, plupload));

function lenByte(tempvalue){
	if(tempvalue==null || tempvalue.length==0 || tempvalue=="")  
	{  
	return 0;  
	}else{
	 
	var i,sum;     
	sum=0;     
	for(i=0;i<tempvalue.length;i++)     
	{     
	 if ((tempvalue.charCodeAt(i)>=0) && (tempvalue.charCodeAt(i)<=255))     
	sum=sum+1;     
	  else
	sum=sum+9;     
	}
	return sum;
	}
	}
