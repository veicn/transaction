var alertInfo= function(message) {
    layer.alert(message,{title:'Info', btn:['OK']});
}

$(function(){
    commonCPY.select();
    $('.lib_Menubox ul li').click(function () {
        var index = $(this).index();
        $(this).addClass('active').siblings().removeClass('active')
        $('.lib_Contentbox>div').eq(index).show().siblings().hide();
        var flag = $(this).attr('flag');
        if (flag == 3) {
            $('#myMaps').css('display', 'none');
            $('#myMaps1').css('display', 'block');
        }
        else if (flag == 1) {
            $('#myMaps').css('display', 'block');
            $('#myMaps1').css('display', 'none');

        } else {
            $('#myMaps').css('display', 'none');
            $('#myMaps1').css('display', 'none');

        }
    });

    //弹出装港的进程信息
    $('.button-check').on('click',function () {
        $('.con_one_pop').show()
    })
    $('.con_pop-t>i').on('click',function () {
        $('.con_one_pop').hide()
    })
    $('.button-pop>a').on('click',function () {
        $('.con_one_pop').hide()
    })



//浏览文件（支持PDF和图片格式）
    $(".lookFileDoc").on("click",function(){
        var url = $(this).attr('fileUrl');
        var formatStr = $(this).attr('fileFormat').toString();
        var fileFormat = formatStr.substring(formatStr.indexOf(".")+1);

        //http://www.sinochem.com/order-execute/common/doc/browserFile.htm?path=orderexecute/DhkhRnwKkw.pdf&fileName=API手册 - 调用.pdf

        if(fileFormat == 'pdf'){
            //pdf文件浏览
            window.open(shippingServer + "/pdfjs/web/viewer.html?file=" + encodeURIComponent(url),'_blank');
        }else if(fileFormat == 'doc' || fileFormat == 'docx'){
            //word浏览
            alertInfo("Only support preview of  image/PDF format.");
            //word转换pdf

            //window.open(orderServer + "/pdfjs/web/viewer.html?file=" + encodeURIComponent(url),'_blank');
        }else if(fileFormat == 'xls' || fileFormat == 'xlsx'){
            //execl浏览
            alertInfo("Only support preview of  image/PDF format.");
            //execl转换pdf

        }else{
            //图片弹框
            layer.open({
                area: ['80%', '80%'],
                title: $(this).parents('.is-flag').find('.subCode').text(),
                btn: ['Return'],
                content:"<html><body><img src='"+ url +"' style='width:100%;height:100%;text-align:center;'></img><body><html>"
            });
        }
    });

//橙色圆球的 控制
    var aa = $("#constatu").val()
    var tradeTerms = $("#tradeTerms").val()
    if(aa<=20){
        $(".constatus>li:first-of-type").addClass("doing")
    }
    if(aa>=30&&aa<40){
        $(".constatus>li:first-of-type").addClass("done")
        $(".constatus>li:nth-of-type(2)").addClass("doing")
    }
    if(aa>=40&&aa<50){
        $(".constatus>li:first-of-type").addClass("done")
        $(".constatus>li:nth-of-type(2)").addClass("done")
        if(tradeTerms == "FOB") {
        	$(".constatus>li:nth-of-type(3)").addClass("done")
        }else {
        	$(".constatus>li:nth-of-type(3)").addClass("doing")
        }        
    }
    if(aa>=50&&aa<60){
        $(".constatus>li:first-of-type").addClass("done")
        $(".constatus>li:nth-of-type(2)").addClass("done")
        $(".constatus>li:nth-of-type(3)").addClass("done")
        $(".constatus>li:nth-of-type(4)").addClass("doing")

    }
    if(aa>=60&&aa<70){
        $(".constatus>li:first-of-type").addClass("done")
        $(".constatus>li:nth-of-type(2)").addClass("done")
        $(".constatus>li:nth-of-type(3)").addClass("done")
        $(".constatus>li:nth-of-type(4)").addClass("done")
        $(".constatus>li:nth-of-type(5)").addClass("doing")
    }
    if(aa>=70){
        $(".constatus>li:first-of-type").addClass("done")
        $(".constatus>li:nth-of-type(2)").addClass("done")
        $(".constatus>li:nth-of-type(3)").addClass("done")
        $(".constatus>li:nth-of-type(4)").addClass("done")
        $(".constatus>li:nth-of-type(5)").addClass("done")
    }
})




//删除上传文件的按钮
$("#voyageFileDelete").on("click",function(){
    layer.alert("Successfully deleted");
    $("#voyageFileUrl").val("");
    $("#voyageFileName").val("");
    $("#voyageFileName").attr("path", "");
    $("#voyageFileUrl").attr("path", "");
    $("#voyageFileDelete").hide();
})


//文件
layui.use(['layer','ossUpload'], function(){
    var $ = layui.jquery,upload = layui.ossUpload;
    upload.render({
        elem: ".file",//绑定元素 id或class
        moduleServer: shippingServer,//绑定元素 id或class
        exts: 'jpg,jpeg,bmp,pdf,xls,xlsx,doc,docx,png', //允许上传的文件后缀
        done: function(res, index,upload){
            if(res.status === 0){
                var fileName = res.datas.originalName;
                var fileUrl = res.datas.path;
                $("#voyageFileName").val(fileName);
                $("#voyageFileName").attr("path", fileUrl);

                $("#voyageFileUrl").attr("path", fileUrl);
                $("#voyageFileUrl").val(fileUrl);
                $("#voyageFileDelete").show();

            }
        }
    })
});


if (!("classList" in document.documentElement)) {
    Object.defineProperty(HTMLElement.prototype, 'classList', {
        get: function() {
            var self = this;
            function update(fn) {
                return function(value) {
                    var classes = self.className.split(/\s+/g),
                        index = classes.indexOf(value);

                    fn(classes, index, value);
                    self.className = classes.join(" ");
                }
            }

            return {
                add: update(function(classes, index, value) {
                    if (!~index) classes.push(value);
                }),

                remove: update(function(classes, index) {
                    if (~index) classes.splice(index, 1);
                }),

                toggle: update(function(classes, index, value) {
                    if (~index)
                        classes.splice(index, 1);
                    else
                        classes.push(value);
                }),

                contains: function(value) {
                    return !!~self.className.split(/\s+/g).indexOf(value);
                },

                item: function(i) {
                    return self.className.split(/\s+/g)[i] || null;
                }
            };
        }
    });
}