function emptyCheck() {
    commonCPY.dateValidate()
    /*var vnDivVal = $("#vesselNameDiv").text();*/
    var vesselNameVal = $('input[name="vesselName"]').val();
    if (vesselNameVal == null || vesselNameVal == '' || vesselNameVal == undefined) {
        layer.alert("The Vessel’s name content cann't be empty", {title: 'Info', btn: ['OK']});
        return false;
    }

    var imoVal = $('input[name="imo"]').val();
    if (imoVal == null || imoVal == '' || imoVal == undefined) {
        layer.alert("The IMO content cann't be empty", {title: 'Info', btn: ['OK']});
        return false;
    }

    var laycanStratVal = $('input[name="laycanStrat"]').val();
    if (laycanStratVal == null || laycanStratVal == '' || laycanStratVal == undefined) {
        layer.alert("The laycanStrat content cann't be empty", {title: 'Info', btn: ['OK']});
        return false;
    }

    var laycanEndVal = $('input[name="laycanEnd"]').val();
    if (laycanEndVal == null || laycanEndVal == '' || laycanEndVal == undefined) {
        layer.alert("The laycanEnd content cann't be empty", {title: 'Info', btn: ['OK']});
        return false;
    }

    var uploadQ88FileNmVal = $('input[name="uploadQ88FileNm"]').val();
    if (uploadQ88FileNmVal == null || uploadQ88FileNmVal == '' || uploadQ88FileNmVal == undefined) {
        layer.alert("The upload Q88FileNm content cann't be empty", {title: 'Info', btn: ['OK']});
        return false;
    }

    return true;
}

function getNowFormatDate() {
    var date = new Date();
    var seperator1 = "-";
    var seperator2 = ":";
    var month = date.getMonth() + 1;
    var strDate = date.getDate();
    if (month >= 1 && month <= 9) {
        month = "0" + month;
    }
    if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
    }
    var currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
    return currentdate;
}


//根据祖产信息查询imo
function findImoByShipId(code) {
    console.log(code)
    var data = {};
    data["sysShipId"] = code;
    $.ajax({
        //post  get delete put
        type: "POST",
        //访问后台的路径
        url: shippingServer + "/pages/back/sysShip/findShipVoByShipId.json",
        //访问格式
        contentType: "application/json;charset=utf-8",
        //数据类型
        dataType: "json",
        //json对象 改为字符串形式的json
        data: JSON.stringify(data),
        success: function (data) {
            $("#agreementAddForm").find('input[name=imo]').val(data.imo);
        },
        error: function () {
            console.log("fail");
        }
    })
}

var agreementAddFormFlag = true
$(function () {
    /* action="$!appServer.get('pages/back/agreement/agreementAdd.htm')" method="post"*/
    // 提交form表单
    $('#agreementUpdateForm2').click(function () {
        var arr = $('#agreementAddForm');
        commonCPX.FormValidation(arr);
        if (!commonCPX.FormValidation(arr) && agreementAddFormFlag) {
            agreementAddFormFlag = false;

            if (emptyCheck())
                $.ajax({
                    type: "POST",
                    url: shippingServer + '/pages/back/agreement/agreementAdd.htm',
                    data: $("#agreementAddForm").serialize(),// 序列化表单提交input值
                    success: function (msg) {
                        agreementAddFormFlag = true;
                        layer.alert(msg, {title: 'Info', btn: ['OK']}, function () {
                            window.location.href = document.referrer;
                        });
                    },
                    error: function (msg) {
                        agreementAddFormFlag = true;
                        layer.alert("error:" + msg, {title: 'Error', btn: ['OK']});
                    }
                });
        } else {
            agreementAddFormFlag = true;
            layer.alert("Fill in the wrong,please try again", {title: 'Error', btn: ['OK']});

        }

    })
});


var agreementAddForm2Flag = true;
$(function () {
    // 提交form表单
    $('#agreementAddForm2').click(function () {

        var arr = $('#agreementAddForm');
        commonCPX.FormValidation(arr);
        if (!commonCPX.FormValidation(arr) && agreementAddForm2Flag) {
            if (emptyCheck()) {
                agreementAddForm2Flag = false;
                $.ajax({
                    type: "POST",
                    url: shippingServer + '/pages/back/agreement/agreementAdd.htm',
                    data: $("#agreementAddForm").serialize(),// 序列化表单提交input值
                    success: function (msg) {
                        agreementAddForm2Flag = true;
                        layer.alert(msg, {title: 'Info', btn: ['OK']}, function () {
                            window.location.href = document.referrer;
                        });
                    },
                    error: function (msg) {
                        agreementAddForm2Flag = true;
                        layer.alert("error:" + msg, {title: 'Error', btn: ['OK']});
                    }
                });
            }
        } else {
            agreementAddForm2Flag = true;
            layer.alert("Fill in the wrong,please try again", {title: 'Error', btn: ['OK']});
        }
    })
});


var agreementConfirmForm2Flag = true;
$(function () {
    /* action="$!appServer.get('pages/back/agreement/agreementAdd.htm')" method="post"*/
    // 提交form表单
    $('#agreementConfirmForm2').click(function () {
        var arr = $('#agreementAddForm');
        commonCPX.FormValidation(arr);
        if (!commonCPX.FormValidation(arr) && agreementConfirmForm2Flag) {
            agreementConfirmForm2Flag = false;
            $.ajax({
                type: "POST",
                url: shippingServer + '/pages/back/agreement/agreementConfirm.htm',
                data: $("#agreementConfirmForm").serialize(),// 序列化表单提交input值
                success: function (msg) {
                    agreementConfirmForm2Flag = true;
                    layer.alert(msg, {title: 'Info', btn: ['OK']}, function () {
                        window.location.href = document.referrer;
                    });
                },
                error: function (msg) {
                    agreementConfirmForm2Flag = true;
                    layer.alert("error:" + msg, {title: 'Error', btn: ['OK']});
                }
            });
        }
    })

    $('.m-nav-info>li>.timeInsert').html(getNowFormatDate());
    $('.m-nav-info>li>.charterPartyDate').val(getNowFormatDate());

    //删除附件
    $("#Q88Deleted").on("click", function () {

        layer.alert("Successfully deleted", {title: 'Info', btn: ['OK']});
        $("#uploadQ88FileNm").val("");
        $("#uploadQ88FileNm").attr("path", "");
        $("#uploadQ88").val("");
        $("#uploadQ88").attr("path", "");
        $("#Q88Deleted").hide();
    })

    $("#CpDeleted").on("click", function () {

        layer.alert("Successfully deleted", {title: 'Info', btn: ['OK']});
        $("#uploadCpFileNm").val("");
        $("#uploadCpFileNm").attr("path", "");
        $("#uploadCp").val("");
        $("#uploadCp").attr("path", "");
        $("#CpDeleted").hide();
    })
});

//浏览文件（支持PDF和图片格式）
$(".lookFileDoc").on("click", function () {
    var url = $(this).attr('fileUrl');
    var fileFormat = $(this).attr('fileFormat');

    //http://www.sinochem.com/order-execute/common/doc/browserFile.htm?path=orderexecute/DhkhRnwKkw.pdf&fileName=API手册 - 调用.pdf

    if (fileFormat == 'pdf') {
        //pdf文件浏览
        window.open(shippingServer + "/pdfjs/web/viewer.html?file=" + encodeURIComponent(url), '_blank');
    } else if (fileFormat == 'doc' || fileFormat == 'docx') {
        //word浏览
        layer.alert("Only support preview of  image/PDF format.");
        //word转换pdf

        //window.open(orderServer + "/pdfjs/web/viewer.html?file=" + encodeURIComponent(url),'_blank');
    } else if (fileFormat == 'xls' || fileFormat == 'xlsx') {
        //execl浏览
        layer.alert("Only support preview of  image/PDF format.");
        //execl转换pdf

    } else {
        //图片弹框
        layer.open({
            area: ['850px', '650px'],
            title: $(this).parents('.is-flag').find('.subCode').text(),
            btn: ['return'],
            content: "<html><body><img src='" + url + "' style='width:60%;height:60%'></img><body><html>"
        });
    }
});


layui.use(['layer', 'ossUpload'], function () {
    var $ = layui.jquery, upload = layui.ossUpload;
    upload.render({
        elem: ".file",//绑定元素 id或class
        moduleServer: shippingServer,//绑定元素 id或class
        exts: 'jpg,jpeg,bmp,pdf,xls,xlsx,doc,docx', //允许上传的文件后缀
        done: function (res, index, upload) {
            if (res.status === 0) {
                var fileName = res.datas.originalName;
                var fileUrl = res.datas.path;
                if (index.id.indexOf('Q88') > -1) {
                    $("#uploadQ88FileNm").val(fileName);
                    $("#uploadQ88FileNm").attr("path", fileUrl);
                    $("#uploadQ88").val(fileUrl);
                    $("#uploadQ88").attr("path", fileUrl);
                    $("#Q88Deleted").show();
                } else {
                    $("#uploadCpFileNm").val(fileName);
                    $("#uploadCpFileNm").attr("path", fileUrl);
                    $("#uploadCp").val(fileUrl);
                    $("#uploadCp").attr("path", fileUrl);
                    $("#CpDeleted").show();
                }
            }
        }
    })
});

