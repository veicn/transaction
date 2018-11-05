/**
 文件跨域上传实现
 */
(function($){
    /**
     * 参数处理
     * @param dom
     * @param params
     */
    var getParams = function(dom, params){
        //基本参数
        var baseParam = {
            uploadUrl: "/upload/file.htm",//上传路径
            submitName: "file",//提交文件名
            submitClass: "",//提交按钮class
            type: 0,//上传文件类型 0：图片 1：文档 2：视频 3：音频，默认0
            accept: "",//MIME_type（"image/*"表示可以选中任意图片类型）
            docType: "",//档案类型
            objectId: "",//目标ID
            beforeUpload: function(path){},
            uploadEnd: function(result){}
        };
        $.extend(baseParam, params);
        params = baseParam;
        //允许dom上使用data-params=""形式设置一些参数
        var dataArr = ["uploadUrl", "submitName", "submitClass", "type", "accept", "docType", "objectId"];
        for(var i = 0;i < dataArr.length;i++){
            var pk = dataArr[i];
            var d = dom.data(pk.toLowerCase());
            if(!!d || d == 0){
                params[pk] = d;
            }
        }
        if("0" == params.type){
            params.accept = params.accept || "image/*";
        }
        return params;
    };

    var FileUploader = function(dom, params){
        params = getParams(dom, params);
        $.extend(this, params);
        var self = this;
        //初始化并取到frame
        var frameName = "frame" + new Date().getTime();
        dom.attr("target", frameName);
        $("body").append('<iframe name="'+ frameName +'" style="display: none;" scrolling="no" allowtransparency="true" frameborder="0"></iframe>');
        $("body").append('<form target="' + frameName + '" style="display: none;" action="'+ self.uploadUrl +'" method="post" enctype="multipart/form-data">'+
                '<input type="file" name="'+ self.submitName +'" class="ignore"/>' +
                '<input type="text" name="origin" value="'+ location.origin +'" class="ignore"/>' +
                '<input type="text" name="type" value="'+ self.type +'" class="ignore"/>' +
                '<input type="text" name="docType" value="'+ self.docType +'" class="ignore"/>' +
                '<input type="text" name="objectId" value="'+ self.objectId +'" class="ignore"/></form>');
        var frame = $("iframe[name='"+ frameName +"']");
        var form = $("form[target='"+ frameName +"']");
        var input = form.find("input:file");
        //设置可选中的文件类型
        if(!!self.accept){
            input.attr("accept", self.accept);
        }
        //监听上传hash变化
        var count = 0;
        var hashInterval;
        var catchHash = function(){
            var uploadEnd = function(result){
                dom.trigger("uploadEnd", result);
                self.uploadEnd(result);
                clearInterval(hashInterval);
            };
            var result;
            try{
                result = window.frames[frameName].location.hash;
            }catch(err){
                uploadEnd({success: false, error: "服务器异常！"});
            }
            if(!!result){
                result = result.substring(1).split("&");
                var fileId = result[0];
                var error = result[1];
                var resultObj = {};
                if(!!fileId && "{id}" != fileId){
                    resultObj = {success: true, id: fileId};
                } else if(!!error && "{error}" != error) {
                    resultObj = {success: false, error: Base64.decode(error)};
                } else {
                    resultObj = {success: false, error: "上传失败！"};
                }
                uploadEnd(resultObj);
            }
            if(count > 300){//超出300秒，算超时
                uploadEnd({success: false, error: "上传超时！"});
            }
            count++;
        };
        //判断提交方式
        if (!!self.submitClass){//指定按钮提交
            $(self.submitClass).click(function(){
                var path = input.val();
                if(!!path){
                    dom.trigger("beforeUpload", path);
                    if(self.beforeUpload(path) == false){
                        return false;
                    }
                    form.submit();//开始上传
                    hashInterval = setInterval(catchHash, 1000);//启动监听
                }
            });
        } else {//选中文件后
            input.change(function(){
                var path = input.val();
                if(!!path){
                    dom.trigger("beforeUpload", path);
                    if(self.beforeUpload(path) == false){
                        return false;
                    }
                    form.submit();//开始上传
                    hashInterval = setInterval(catchHash, 1000);//启动监听
                }
            });
        }
        //点击选择文件
        dom.click(function(){
            input.click();
        });
    };

    /**
     * 显示图片
     * <p>图片上传，完全处理完成会有一秒左右时间，不能立刻显示图片</p>
     * @param src
     *      图片路径
     * @param time
     *      尝试次数，每100毫秒1次，默认100次
     * @param callback
     *      成功以后回调
     */
    jQuery.fn.showImg = function (src, time, callback) {
        var self = $(this);
        var img = new Image();
        img.src = src;
        var count = 0;
        time = parseInt(time) || 100;
        if(time > 0){
            img.onerror = function(){//若失败，尝试指定次数
                if(count > time) return;
                var t = setTimeout(function(){
                    img.src = src;
                    clearTimeout(t);//防止重复执行
                }, 100);
                count++;
            };
        }
        img.onload = function() {
            if(img.complete){
                self.append(img);
                if(!!callback) {
                    callback();
                }
            }
        };
    };

    /**
     * 用于返回异常信息转码
     */
    var Base64 = {
        _keyStr: "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=",
        decode: function (input) {
            var output = "";
            var chr1, chr2, chr3;
            var enc1, enc2, enc3, enc4;
            var i = 0;
            input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
            while (i < input.length) {
                enc1 = this._keyStr.indexOf(input.charAt(i++));
                enc2 = this._keyStr.indexOf(input.charAt(i++));
                enc3 = this._keyStr.indexOf(input.charAt(i++));
                enc4 = this._keyStr.indexOf(input.charAt(i++));
                chr1 = (enc1 << 2) | (enc2 >> 4);
                chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
                chr3 = ((enc3 & 3) << 6) | enc4;
                output = output + String.fromCharCode(chr1);
                if (enc3 != 64) {
                    output = output + String.fromCharCode(chr2);
                }
                if (enc4 != 64) {
                    output = output + String.fromCharCode(chr3);
                }
            }
            output = this.utf8_decode(output);
            return output;
        },
        utf8_decode: function (utftext) {
            var string = "";
            var i = 0;
            var c = c1 = c2 = 0;
            while ( i < utftext.length ) {
                c = utftext.charCodeAt(i);
                if (c < 128) {
                    string += String.fromCharCode(c);
                    i++;
                } else if((c > 191) && (c < 224)) {
                    c2 = utftext.charCodeAt(i+1);
                    string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                    i += 2;
                } else {
                    c2 = utftext.charCodeAt(i+1);
                    c3 = utftext.charCodeAt(i+2);
                    string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                    i += 3;
                }
            }
            return string;
        }
    };

    /**
     * 上传
     * @param params
     */
    jQuery.fn.fileUpload = function(params){
        //初始化
        $(this).each(function(){
            new FileUploader($(this), params);
        });
    };
})(jQuery);