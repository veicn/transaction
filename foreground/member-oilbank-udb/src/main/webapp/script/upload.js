function uploadFile(ossfileName) {
    this.accessid = '';
    this.accesskey = '';
    this.host = '';
    this.policyBase64 = '';
    this.signature = '';
    this.callbackbody = '';
    this.filename = '';
    this.filenames = '';
    this.key = '';
    this.expire = 0;
    this.g_object_name = '';
    this.suffix='';
    this.now = timestamp = Date.parse(new Date()) / 1000;
    //ajax请求
    this.send_request = function () {
        var xmlhttp = null;
        if (window.XMLHttpRequest) {
            xmlhttp = new XMLHttpRequest();
        }else if (window.ActiveXObject) {
            xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
        }

        if (xmlhttp != null) {
            serverUrl = this.judgmentUrl() + '/user/udb/file/upload.json';
            xmlhttp.open("GET", serverUrl, false);
            xmlhttp.send(null);
            return xmlhttp.responseText
        } else {
            alert("Your browser does not support XMLHTTP.");
        }
    };
    // 处理发回数据
    this.get_signature = function (filename) {
        //可以判断当前expire是否超过了当前时间,如果超过了当前时间,就重新取一下.3s 做为缓冲
        this.now = timestamp = Date.parse(new Date()) / 1000;
        if (this.expire < this.now + 3) {
            var body = this.send_request()
            var resBody = eval("(" + body + ")");
            var obj = resBody.datas;

            this.host = obj['host'];
            this.policyBase64 = obj['policy'];
            this.accessid = obj['accessid'];
            this.signature = obj['signature'];
            this.expire = parseInt(obj['expire']);
            this.callbackbody = obj['callback'];
            this.key = obj['dir'];
            this.filenames=obj['fileName']+this.get_suffix(filename);
            return true;
        }
        return false;
    };
//随机字符串方法
    this.random_string = function (len) {
        len = len || 32;
        var chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678';
        var maxPos = chars.length;
        var pwd = '';
        for (i = 0; i < len; i++) {
            pwd += chars.charAt(Math.floor(Math.random() * maxPos));
        }
        return pwd;
    };
    //得到文件后缀
    this.get_suffix = function (filename) {
        var pos = filename.lastIndexOf('.')
        this.suffix = ''
        if (pos != -1) {
            this.suffix = filename.substring(pos)
        }
        return this.suffix;
    };
    // 计算上传对象的名称
    this.calculate_object_name = function (filename) {
        return  this.g_object_name;
        // return ''
    };
    //上传对象的名称
    this.get_uploaded_object_name = function (filename) {
        var tmp_name = this.g_object_name
        // tmp_name = tmp_name.replace("${filename}", filename);
        return tmp_name
    };
    //上传一个文件
    this.uploadOneFiles = function (up){
        if (up.files.length > 1) {
            //document.getElementById(ossfileName).innerHTML=''
            up.removeFile(up.files[0]);
        }

    };
    //url判断函数
    this.judgmentUrl = function () {
        var configURL

        /*var protocol = window.location.protocol;*/
        var protocol = "http://";
        var urlStr = "www.sinochem.com";
        /* var urlStr = window.location.host;*/

        return configURL = protocol+ '//' + urlStr + '/';
        /*if (urlStr.indexOf('udb.sinochem.tech') != -1) {        //uat
         return configURL = 'http://udb.sinochem.tech/udb/';
         } else if (urlStr.indexOf('news.mycrudeoil.') != -1) {     //线上
         return configURL = 'http://udb.sinochem.tech/udb/';
         } else if (urlStr.indexOf('.test.') != -1) {       //sit
         return configURL = 'http://test.udb.sinochem.tech/udb/';
         } else {                                    //本地localhost
         return configURL = 'http://test.udb.sinochem.tech/udb/';
         // return configURL = 'http://localhost:8001/'
         }*/
    };
    //点击开始上传
    this.set_upload_param = function (up, filename, ret) {
        this.uploadOneFiles(up)
        if (ret == false) {
            ret = this.get_signature(filename)
        }
        this.g_object_name = this.key;
        if (filename != '') {
            this.suffix = this.get_suffix(filename)
            up.files[0].name=this.filenames+this.get_suffix(filename)
            this.calculate_object_name(up.files[0].name)
        }


        var new_multipart_params = {
            'key': this.g_object_name+this.filenames+this.get_suffix(filename),
            /*'key': this.g_object_name,*/
            'policy': this.policyBase64,
            'OSSAccessKeyId': this.accessid,
            'success_action_status': '200', //让服务端返回200,不然，默认会返回204
            'callback': this.callbackbody,
            'signature': this.signature,
            'name':this.get_suffix(filename),

        };

        up.setOption({
            'url': this.host,
            'multipart_params': new_multipart_params
        });

        up.start();
        console.log(up);
    };
}

// 实例一个对象

var UploaderCall=function(button,postfilesName,containerName,inputId){
    var uploadFn = new uploadFile();
    var uploader = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: button,
        unique_names:true,
        multi_selection: false,
        container: document.getElementById(containerName),
        flash_swf_url: 'lib/plupload-2.1.2/js/Moxie.swf',
        silverlight_xap_url: 'lib/plupload-2.1.2/js/Moxie.xap',
        url: 'http://sinochem-b2b-dev.oss-cn-beijing.aliyuncs.com',


        filters: {
            mime_types: [ //只允许上传图片和zip文件
                {title: "Image files", extensions: "jpg,gif,png,bmp,JPEG"},
                {title: "Zip files", extensions: "zip,rar"},
                {title: "Office  files", extensions: "doc,docx,excel,ppt,txt,mpp,xls,xlsx,pdf"}
            ],
            max_file_size: '10mb', //最大只能上传10mb的文件
            prevent_duplicates: true //不允许选取重复文件
        },

        init: {
            PostInit: function () {
                document.getElementById(postfilesName).onclick = function () {
                    uploadFn.set_upload_param(uploader, '', false);
                    return false;
                };
            },
//上传文件后的信息
            FilesAdded: function (up, files) {
                document.getElementById(postfilesName).click();
            },
//上传前
            BeforeUpload: function (up, file) {
                uploadFn.uploadOneFiles(up)
                file.name=uploadFn.filenames+uploadFn.get_suffix(file.name);
                uploadFn.set_upload_param(up, file.name, true);
            },
//进度条
            UploadProgress: function (up, file) {

            },
//上传后
            FileUploaded: function (up, file, info) {
                uploadFn.uploadOneFiles(up)



                if (info.status == 200) {
                    var data = JSON.parse(info.response);
                    document.getElementById(inputId).value= data.response.url
                    document.getElementById(button).getElementsByTagName('img')[0].src =  data.response.url

                }
                else if (info.status == 203) {
                    document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '上传到OSS成功，但是oss访问用户设置的上传回调服务器失败，失败原因是:' + info.response;
                }
                else {
                    document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = info.response;
                }
            },
//发生错误
            Error: function (up, err) {
                uploadFn.uploadOneFiles(up)

                if (err.code == -600) {
                    document.getElementById(consoleName).appendChild(document.createTextNode("\n选择的文件太大了,可以根据应用情况，在upload.js 设置一下上传的最大大小"));
                }
                else if (err.code == -601) {
                    document.getElementById(consoleName).appendChild(document.createTextNode("\n选择的文件后缀不对,可以根据应用情况，在upload.js进行设置可允许的上传文件类型"));
                }
                else if (err.code == -602) {
                    document.getElementById(consoleName).appendChild(document.createTextNode("\n这个文件已经上传过一遍了"));
                }
                else {
                    document.getElementById(consoleName).appendChild(document.createTextNode("\nError xml:" + err.response));
                }
            }
        }
    });
    uploader.init();
    return uploader

}

window.Sinochemplugins = {
    upload : function (index){
        if(typeof(index)=='undefined'){
            index = "";
        }
        var options = {
            id:'selectfiles'+index,
            files:'postfiles'+index,
            container:'container'+index
        }
        var id = 1
        //var id = options.id,  files = options.files,  container = options.container;
        return UploaderCall(id,files,container);
    }
};