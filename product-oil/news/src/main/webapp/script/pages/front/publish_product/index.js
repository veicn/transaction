$(function () {
    layui.use("upload", function () {
        var $ = layui.jquery, upload = layui.upload;

        upload.render({
            elem: ".quantity",
            url: "/transaction/common/doc/upload.json",
            accept: "file",
            done: function (res, index, upload) {
                if (res.status === 0) {
                    var item = this.item;
                  var path = res.datas.path;
                  var fileName = res.datas.originalName;
                    $("#productQuantity").val(res.datas.path);
                    $("#QuantityStandardName").html(res.datas.originalName);
                    
                    ///
                    $("#QuantityStandardName").on("click",function(){
                        var path = res.datas.path;
                        var fileName = res.datas.originalName;
                        $("#QuantityStandardName").on("click",function(){
                        	window.location.href = "/transaction/common/doc/download.htm?"+"path="+path+"&fileName="+fileName;
                        });
                    	
                       
                    });
///

                } else {
                    layer.tips(res.message, this.item, {tips: 1});
                    $("#QuantityStandardName").off("click");
                }
            }
        })
    })

    layui.use("upload", function () {
        var $ = layui.jquery, upload = layui.upload;

        upload.render({
            elem: ".ExportQuota",
            url: "/transaction/common/doc/upload.json",
            accept: "file",
            done: function (res, index, upload) {
                if (res.status === 0) {
                    var item = this.item;
                    $("#exportQuota").val(res.datas.path);
                    alert(res.datas.originalName)
                    $("#ExportQuotaName").html(res.datas.originalName);
                    var path = res.datas.path;
                    var fileName = res.datas.originalName;
                    $("#ExportQuotaName").on("click",function(){
                    	window.location.href = "/transaction/common/doc/download.htm?"+"path="+path+"&fileName="+fileName;
                    });
                } else {
                    layer.tips(res.message, this.item, {tips: 1});
                }
            }
        })
    })

    $('.loadli').click(function() {
        const countryCode = $(this).attr('data-value');
        queryPortList(countryCode, 'loadingPortList');
    });

    $('.dischargeli').click(function() {
        const countryCode = $(this).attr('data-value');
        queryPortList(countryCode, 'dischargePortList');
    });
})

function queryPortList(shipPortCode, berthListId) {

    $.get(appServer + "pages/front/publish_product/port_list.json", {
        "countryCode": shipPortCode
    }, function (result) {
        const shipPortList = result.datas; //解析 json
        const length = shipPortList.length;

        var dom = "";
        for (var i = 0; i < length; i++) {
            const shipPort = shipPortList[i];

            if (berthListId == 'loadingPortList') {
                dom = dom +
                    "<li class='select-option' data-value='" + shipPort.code + "' onclick='renderBerthList(" + shipPort.code + ")'>" +
                    shipPort.enName +
                    "</li>"
            } else {
                dom = dom +
                    "<li class='select-option' data-value='" + shipPort.code + "'>" +
                    shipPort.enName +
                    "</li>"
            }
        }

        $('#' + berthListId).empty();
        $('#' + berthListId).append(dom);
    })
}

function postSaleSheet(saleSheetStatus) {

    //定向发布
    var enterpriseIds = '';

    $('#Company').children('tr').each(function() {
        const enterpriseIdToAdd = $(this).children('td')[2];
        enterpriseIds += enterpriseIdToAdd;
        enterpriseIds += ',';
    });
    if (enterpriseIds.length != 0) {
        enterpriseIds = enterpriseIds.substr(0, enterpriseIds.length - 1);
    }

    $('#specifiedEnterpriseIdList').val(enterpriseIds);

    //保存/发布
    $("input[name='saleSheetVO.saleSheetStatusVO.code']").val(saleSheetStatus);

    //销售类型
    var els = document.getElementsByName("SaleType"),
        hEl = document.getElementById("SaleTypeId");
    for (var i = els.length; i--;) {
        var el = els[i];
        if (el.checked) {
            hEl.value = el.getAttribute("id");
            //alert(el.getAttribute("id"))
            break;
        }
    }

    var saleTypes = $("[name='saleTypeName']");

    //商品分类
    var elsCategory = document.getElementsByName("Category"),
        hElCategory = document.getElementById("CategoryId");
    for (var i = elsCategory.length; i--;) {
        var elCategory = elsCategory[i];
        if (elCategory.checked) {
            hElCategory.value = elCategory.getAttribute("id");
            //alert(elCategory.getAttribute("id"))
            break;
        }
    }

    //商品来源
    var elsP = document.getElementsByName("productSource"),
        hElP = document.getElementById("productSourceId");
    for (var i = elsP.length; i--;) {
        var elP = elsP[i];
        if (elP.checked) {
            hElP.value = elP.getAttribute("id");
            //alert(elP.getAttribute("id"))
            break;
        }
    }

    $("#publishProductForm").submit();
}
