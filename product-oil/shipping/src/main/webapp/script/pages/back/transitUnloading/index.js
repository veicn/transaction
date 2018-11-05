var confirmationSheetUuid="";
var alertInfo= function(message) {
    layer.alert(message,{title:'Info', btn:['OK']});
}

//提交弹出框的信息
var info={
    deleteSuccess:"Delete Successfully",
    submitSuccess :"Submit Successfully",
    deleteFail:"Delete Fail",
    submitFail:"Submit Fail"
}

$(function () {
    commonCPY.select();

    //每点击一个tab页，隐藏其他的页，显示点击页
    $('.lib_Menubox ul li').click(function () {
        var index = $(this).index();
        $(this).addClass('active').siblings().removeClass('active')
        $('.lib_Contentbox>div').eq(index).show().siblings().hide();
    });

    //根据路径，来截取state值，显示页面
    var query = (location.search.length > 0 ? location.search.substring(1) : null);
    if(null!=query)
    {
        var args = new Object( );
        var pairs = query.split("&");
        for(var i = 0; i < pairs.length; i++)
        {
            var pos = pairs[i].indexOf("=");
            if (pos == -1)
                continue;
            var argname = pairs[i].substring(0,pos);
            var value = pairs[i].substring(pos+1);
            value = decodeURIComponent(value);
            args[argname] = value;
        }

        var showIndex =args["state"];
        // confirmationSheetUuid=args["uuid"];
        $('.lib_Contentbox>div').eq(showIndex).show().siblings().hide();
        $('.lib_Menubox ul >li').eq(showIndex).addClass('active').siblings().removeClass('active');
    };


    //*************************** 给上传文件的按钮添加删除按钮的方法*********************************
    $("#voyageFileDelete").on("click",function(){
        alertInfo(info.deleteSuccess);
        $("#voyageFileUrl").val("");
        $("#voyageFileName").val("");
        $("#voyageFileName").attr("path", "");
        $("#voyageFileUrl").attr("path", "");
        $("#voyageFileDelete").hide();
    })

    $("#transitFileDelete").on("click",function(){
        alertInfo(info.deleteSuccess);
        $("#transitFileUrl").val("");
        $("#transitFileName").val("");
        $("#transitFileName").attr("path", "");
        $("#transitFileUrl").attr("path", "");
        $("#transitFileDelete").hide();
    })

    $("#diFileDelete").on("click",function(){
        alertInfo(info.deleteSuccess);
        $("#diFileUrl").val("");
        $("#diFileName").val("");
        $("#diFileName").attr("path", "");
        $("#diFileUrl").attr("path", "");
        $("#diFileDelete").hide();
    })
// *******************************************************************

    // 控制橙色求的状态
//租船确认单的状态 20 已确认，
    var aa = $("#constartu").val()
    var tradeTerms = $("#tradeTerms").val()
    if(aa<=20){
        $(".constatus>li:first-of-type").addClass("doing")
    }
    //30航次开始
    if(aa>=30&&aa<40){
        $(".constatus>li:first-of-type").addClass("done")
        $(".constatus>li:nth-of-type(2)").addClass("doing")
    }
    //40已装港
    if(aa>=40&&aa<50){
        $(".constatus>li:first-of-type").addClass("done")
        $(".constatus>li:nth-of-type(2)").addClass("done")
        if(tradeTerms == "FOB") {
        	$(".constatus>li:nth-of-type(3)").addClass("done")
        }else {
        	$(".constatus>li:nth-of-type(3)").addClass("doing")
        }  
    }
    //50在途中
    if(aa>=50&&aa<60){
        $(".constatus>li:first-of-type").addClass("done")
        $(".constatus>li:nth-of-type(2)").addClass("done")
        $(".constatus>li:nth-of-type(3)").addClass("done")
        $(".constatus>li:nth-of-type(4)").addClass("doing")

    }
    //60 已卸港
    if(aa>=60&&aa<70){
        $(".constatus>li:first-of-type").addClass("done")
        $(".constatus>li:nth-of-type(2)").addClass("done")
        $(".constatus>li:nth-of-type(3)").addClass("done")
        $(".constatus>li:nth-of-type(4)").addClass("done")
        $(".constatus>li:nth-of-type(5)").addClass("doing")
    }
    //70 航次结束
    if(aa>=70){
        $(".constatus>li:first-of-type").addClass("done")
        $(".constatus>li:nth-of-type(2)").addClass("done")
        $(".constatus>li:nth-of-type(3)").addClass("done")
        $(".constatus>li:nth-of-type(4)").addClass("done")
        $(".constatus>li:nth-of-type(5)").addClass("done")
    }
// ***********************************************************************************

});

layui.use(['layer','ossUpload'], function(){
    var $ = layui.jquery,upload = layui.ossUpload;
    upload.render({
        elem: ".file",//绑定元素 id或class
        moduleServer: shippingServer,//绑定元素 id或class
        exts: 'jpg,jpeg,bmp,pdf,xls,xlsx,doc,docx,png,txt', //允许上传的文件后缀
        done: function(res, index,upload){
            if(res.status === 0){
                var fileName = res.datas.originalName;
                var fileUrl = res.datas.path;
                if(index.id =="voyagestartUpload"){
                    $("#voyageFileName").val(fileName);
                    $("#voyageFileUrl").val(fileUrl);
                    $("#voyageFileDelete").show();

                }else if(index.id =="transitUpload"){
                    $("#transitFileName").val(fileName);
                    $("#transitFileUrl").val(fileUrl);
                    $("#transitFileDelete").show();

                }else{
                    $("#diFileName").val(fileName);
                    $("#diFileUrl").val(fileUrl);
                    $("#diFileDelete").show();
                }
            }
        }
    })
});
//transitUnloading页面js对象unloading
var unloading = {
    unloadingFlag:true,
    urls:{stowagePlan:shippingServer+"/pages/back/VoyageStart/insertStowagePlan.json",
        transitUnloading:shippingServer+"/pages/back/TransitUnloading/intransit.json",
        discharge:shippingServer+"/pages/back/unloadport/saveDischarge.json",
        deleteTransit:shippingServer+"/pages/back/TransitUnloading/deleteTransitUnloading.json"
    },
    amend: function (tranId, index) {
        var arr = {};
        $('#' + tranId + ' td').each(function (j) {
            arr[this.title] = this.innerText;
        });
        $('#uldata').find('input').each(function (j) {
            this.value = arr[this.name];
            if(this.name=="eta" || this.name=="datetime"){
                $(this).next('div').text(this.value);
            }
        });

    },
    //删除方法
    deleteTransit: function (uuid, aElement) {
        if(uuid == null || uuid.trim()=="") {
            aElement.closest('tr').remove();
            return;
        }
        var obj = this;
            layer.confirm('Are you sure to Delete?', {title:'Info', btn:['OK','Cancel']}, function(index){
                    var data = {uuid:uuid};
                    var url = obj.urls.deleteTransit;
                    var type = "POST"
                    obj.ajax(url, type, data, function (data) {
                        if (data.status == 0) {
                            alertInfo(info.deleteSuccess);
                            aElement.closest('tr').remove();
                        }else{
                            alertInfo(info.deleteFail);
                        }
                    })


            })
    },
    //添加方法
    addTransit: function () {
        //declare string of Tr Element
        var strHtml = "<tr><td>" + this.getDate() + "</td><td>null</td><td><em class=\"button-sp\"> <a href=\"javascript:;\" class=br onclick=\"unloading.deleteTransit('',this)\">Delete</a> </em></td></tr>"
        var name = "shipConfirmationSheetId";
        var tt = document.createElement('tr');
        tt.innerHTML = strHtml;
        //append Tr Element
        $('#transit').append(tt);
        //clear input
        $('#uldata').find('input').each(function () {
            if (this.name != name) {
                this.value = "";
            }
        });
    },
    //对日期进行处理 yyyy-MM-dd形式
    getDate: function () {
        var date = new Date();
        var seperator1 = " ";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = strDate +seperator1 +  month + seperator1 + year ;
        return currentdate;
    },
    //提交表单
    submit:function(id,url){
        var arr=$('#'+id)  //把你要验证的form表单数组赋值给arr
        //var buttonClass='submit'  //提交按钮类名
        var flag = commonCPX.FormValidation(arr)
       if(flag){
            return;
       }
        var data = {};
        $('#'+id).find('input').each(function (j) {
            if(this.name != null && this.name!=""){
               data[this.name] = this.value;
            }
        });
        var url = url;
        var type = "POST";
        if(this.unloadingFlag) {
            this.unloadingFlag = false;
            this.ajax(url, type, data, function (data) {
             unloading.unloadingFlag = true;
                if (data.status == 0) {
                    var uuid = $('#constatusUuid').val();
                    layer.alert(info.submitSuccess, {title: 'Info', btn: ['OK']}, function () {
                        location.href = shippingServer + "/pages/back/TransitUnloading/index.htm?state=0&uuid=" + uuid;
                    });
                } else {
                    unloading.unloadingFlag = true;
                    alertInfo(info.submitFail);
                }
            })
        }
    },
    //第四个tab页提交
    dischargeSubmit:function(id,url){
        var arr=$('#'+id)  //把你要验证的form表单数组赋值给arr
        //var buttonClass='submit'  //提交按钮类名
        var flag = commonCPX.FormValidation(arr)
        if(flag){
            return;
        }
        var data = {};
        var flag = false;
        $('#'+id).find('input').each(function (j) {
            if(this.name != null && this.name!=""){
                    data[this.name] = this.value;
            }
        });
        var url = url;
        var type = "POST";
        if(this.unloadingFlag) {
            this.unloadingFlag = false;
            this.ajax(url, type, data, function (data) {
                unloading.unloadingFlag = true;
                if (data.status == 0) {
                    var uuid = $('#constatusUuid').val();
                    layer.alert(info.submitSuccess,{title:'Info', btn:['OK']},function(){
                        location.href =  shippingServer + "/pages/back/TransitUnloading/index.htm?state=3&uuid="+ uuid;
                    });

                }else{
                    unloading.unloadingFlag = true;
                    alertInfo(info.submitFail);
                }
            })
        }

    },
    //第三个tab页提交
    transitSubmit: function (id,url) {
        var arr=$('#'+id)  //把你要验证的form表单数组赋值给arr
        //var buttonClass='submit'  //提交按钮类名
        var flag = commonCPX.FormValidation(arr)
        if(flag){
            return;
        }
        var data = {};
        $('#'+id).find('input').each(function (j) {
            if(this.name != null && this.name!="") {
                    data[this.name] = this.value;
            }
        });
        var url = url;
        var type = "POST";
        if(this.unloadingFlag) {
            this.unloadingFlag = false;
            this.ajax(url, type, data, function (data) {
                unloading.unloadingFlag = true;
                if (data.status == 0) {
                    var uuid = $('#constatusUuid').val();
                    layer.alert(info.submitSuccess,{title:'Info', btn:['OK']},function(){
                        location.href =  shippingServer + "/pages/back/TransitUnloading/index.htm?state=2&uuid="+ uuid;
                    });
                }else{
                    unloading.unloadingFlag = true;
                    alertInfo(info.submitFail);
                }
            })

        }

    },

    ajax: function (url, type, data, Func) {
            $.ajax({
                type: type,  //post  get delete put
                url: url,   //访问后台的路径
                contentType: "application/json;charset=utf-8",  //访问格式
                dataType: "json",    //数据类型
                data: JSON.stringify(data),   //json对象 改为字符串形式的json
                success: Func,
                error: function () {
                    unloading.unloadingFlag = true;
                    alertInfo(info.submitFail);
                }
            })
    }

}

if (!("classList" in document.documentElement)) {
    Object.defineProperty(HTMLElement.prototype, 'classList', {
        get: function () {
            var self = this;

            function update(fn) {
                return function (value) {
                    var classes = self.className.split(/\s+/g),
                        index = classes.indexOf(value);

                    fn(classes, index, value);
                    self.className = classes.join(" ");
                }
            }

            return {
                add: update(function (classes, index, value) {
                    if (!~index) classes.push(value);
                }),

                remove: update(function (classes, index) {
                    if (~index) classes.splice(index, 1);
                }),

                toggle: update(function (classes, index, value) {
                    if (~index)
                        classes.splice(index, 1);
                    else
                        classes.push(value);
                }),

                contains: function (value) {
                    return !!~self.className.split(/\s+/g).indexOf(value);
                },

                item: function (i) {
                    return self.className.split(/\s+/g)[i] || null;
                }
            };
        }
    });
}



// ***********************初始化时间弹出框的方法****************************
$(function () {
  //time1 等对应界面每个日期框的ID
    window.commonCPY.dateTimePicker(['time1','time2','time3','time4','time5','time6','time7','time8','time9'],function(value, date, endDate){
        var value1=value[0].split(' ').join('/')
        console.log(value);

    });

});



