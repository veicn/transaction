function disablefn(a) {
    if($(a).find('i').attr('checked')==undefined){
        $('.dem_input').val('');
        $('.dem_input').attr('disabled','disabled');
        $('.dem_input').css('background','#ebebe4');
        $("input[name='transportInfoVO.demurrageRateVO.code']").val(2);
    }else{
        $('.dem_input').removeAttr('disabled')
        $('.dem_input').css('background','#ffffff');
        $("input[name='transportInfoVO.demurrageRateVO.code']").val(1);

    }

}
function addBench() {
    $('.delBench2').css('display','block');
    $('.per-field-Bench').css('display','block');
    $(".isBenchmark").css('display','none');
    $(".isBenchmark2").css('display','block');

    $(".addBench2").css('display','none');
    $('.divBlock').css('display','block');
}



function delBench() {
    $(".addBench2").css('display','block');
    $('.delBench2').css('display','none');
    $('.per-field-Bench').css('display','none');
    $(".isBenchmark").css('display','block');
    $(".isBenchmark2").css('display','none');
    $('.divBlock').css('display','none');

    //值清空
    $('#priceSourceVO').html('');
    $('#priceSourceVO2').attr('value','');
    $('#pricingBenchmarkVO').html('');
    $('#pricingBenchmarkVO2').attr('value','');
    $('#priceRegionVO').html('');
    $('#priceRegionVO2').attr('value','');


}
$(function () {

    if(demurrageRateCode == '2'){
        $('.dem_input').val('');
        $('.dem_input').attr('disabled','disabled');
        $('.dem_input').css('background','#ebebe4');
        $(".demurrage-box .radio i").attr('checked',"checked");
        $(".demurrage-box .radio input").attr('checked',"checked");
        $(".demurrage-box .radio i").css({'backgroundImage':" url('../../../images/tick_L.png')", 'color': 'rgb(243, 101, 35)', 'borderColor': 'rgb(243, 101, 35)'});
        $("input[name='transportInfoVO.demurrageRateVO.code']").val(2);
    }else{
        $('.dem_input').removeAttr('disabled')
        $('.dem_input').css('background','#ffffff');
        $("input[name='transportInfoVO.demurrageRateVO.code']").val(1);
    }

    var priceSourceVO2 = $('#priceSourceVO2').val();
    if(priceSourceVO2){

        $('.divBlock').css('display','block');
        $('.addBench2').css('display','none');
        $('.delBench2').css('display','block');
        $('.per-field-Bench').css('display','block');
        $(".isBenchmark").css('display','none');
        $(".isBenchmark2").css('display','block');
    }

    var countryCode = $('#LoadPortOne').val();
    if(countryCode){
        queryPortList(countryCode, 'loadingPortList');
    }

    var countryCode1 = $('#DischargeOne').val();
    if(countryCode1){
        queryPortList(countryCode1, 'dischargePortList');
    }

    var categoryCode = $('#categoryCode').val();
    if(categoryCode){
        querySpecificationList(categoryCode);
    }

    //显示
    commonCPY.dateValidate()
    deadlineTime()
    var saleTypeCode = $('#saleTypeCode').val();
    if(saleTypeCode == "2"){
        $("#bidvalid").val("");
    }
    if(productInfoVOa==""||productInfoVOa==undefined){
        $("#loadPortShowOrHide").hide();
        $("#dischargeShowOrHide").hide();

    }else if(productInfoVOa=="1"){
        $("#loadPortShowOrHide").show();
        $("#loadPortShowOrHide input").attr('data-rules','required');

        $("#dischargeShowOrHide").hide();
    }else if(productInfoVOa=="2" || productInfoVOa == "3"){
        $("#loadPortShowOrHide").hide();

        $("#dischargeShowOrHide").show();
        $("#dischargeShowOrHide input").attr('data-rules','required');
    }

    if(saleTypeCode == '2'){
        $("#inquiryDeadLine").hide();
        $("#inquiryDeadLine input").attr('data-rules','');
    }else{
        $("#inquiryDeadLine").show();
        $("#inquiryDeadLine input").attr('data-rules','required');
    }

    if(shipPortCode2 != "" && shipPortCode2 !=undefined ){
        queryPortList(shipPortCode2,"loadingPortList");
    }
    if(shipPortCode3 != "" && shipPortCode3 !=undefined ){
        queryPortList(shipPortCode3,"dischargePortList");
    }
    /*if(shipPortCode != "" && shipPortCode !=undefined ){
        $.get(url, {
            'shipPortCode': shipPortCode
        }, function(result) {

            if (result.status == 9999) {
                console.log('Data response error');
                return;
            }

            $('#berthListAsync>tbody').empty();
            var berthList = result.datas;

            for (var i = 0; i < berthList.length; i++) {
                var berth = berthList[i];

                var active = i / 2 == 0 ? "" : "'class = active'";
                var tdList =
                    '<tr>' +
                    '<td ' + active + '>' + (i + 1) + '</td>' +
                    '<td ' + active + '>' + berth.enName + '</td>' +
                    '<td ' + active + '>' + berth.berthTonnage + '</td>' +
                    '<td ' + active + '>' + berth.berthDraft + '</td>' +
                    '<td ' + active + '>' + berth.vesselType + '</td>' +
                    '<td ' + active + '>' + berth.remark + '</td>' +
                    '</tr>';

                $('#berthListAsync tbody').append(tdList);
            }
        })
    }*/

    var cont=$("#QuantityStandardName").html();
    var path= $("#QuantityStandardPath").html();
    var  fileName= cont;
    if(cont!=''||cont!=null){
        $("#QuantityStandardName").on("click",function(){
            window.location.href = appServer +"common/doc/download.htm?"+"path="+path+"&fileName="+fileName;
        });
    }if(cont==''||cont==null){
        $("#QuantityStandardName").off("click");
    }

    var Econt= $("#ExportQuotaName").html();
    var Epath=$("#ExportQuotaPath").html();
    var  EfileName= Econt;
    if(Econt!=''||Econt!=null){
        $("#ExportQuotaName").on("click",function(){
            window.location.href = appServer +"common/doc/download.htm?"+"path="+Epath+"&fileName="+EfileName;
        });
    }
    if(Econt==''||Econt==null){
        $("#ExportQuotaName").off("click");
    }

    layui.use("upload", function () {
        var $ = layui.jquery, upload = layui.upload;

        upload.render({
            elem: ".quantity",
            url: appServer +"common/doc/upload.json",
            accept: "file",
            done: function (res, index, upload) {
                $(this.elem[0]).siblings('.error').remove()
                if (res.status === 0) {
                    var item = this.item;
                    var path = res.datas.path;
                    var fileName = res.datas.originalName;
                    $("#productQuantity").val(res.datas.path);
                    $("#productQuantityName").val(fileName);
                    $("#QuantityStandardName").html(res.datas.originalName);
                    $("#QuantityStandardName").off("click");
                    $("#QuantityStandardName").on("click",function(){
                        window.location.href = appServer +"common/doc/download.htm?"+"path="+path+"&fileName="+fileName;
                    });

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
            url: appServer +"common/doc/upload.json",
            accept: "file",
            done: function (res, index, upload) {
                if (res.status === 0) {
                    var item = this.item;
                    $("#exportQuota").val(res.datas.path);
                    $("#exportQuotaName").val(res.datas.originalName);
                    $("#ExportQuotaName").html(res.datas.originalName);
                    var path = res.datas.path;
                    var fileName = res.datas.originalName;
                    $("#ExportQuotaName").off("click");
                    $("#ExportQuotaName").on("click",function(){
                        window.location.href = appServer +"common/doc/download.htm?"+"path="+path+"&fileName="+fileName;
                    });
                } else {
                    layer.tips(res.message, this.item, {tips: 1});
                    $("#ExportQuotaName").off("click");
                }
            }
        })
    })

    /* onload */
    if ($('#demandTypeCode').val() == '2') {
        inquiry(2);
        var i = $(".saleTypeInquiry").children('i').eq(0);
        $(i).attr("checked", "checked");
        $(i).css("color", "#F36523");
        // $(i).css("border-color", "#F36523");
        $(".add-quoter").css("display", "inline-block");
        $(".inquiry-private-table").css("display", "table");
    }

    var categoryCode = $('[name="productInfoVO.productCategoryVO.code"]').val();
    if (categoryCode) {
        querySpecificationList(categoryCode);
    }

    /* onclick */

    $(document).on('click','.productCategory',function() {
        $("#SpecificationId").html("");
        $("#SpecificatioCode").attr("value","");
        var categoryCode = $(this).attr('data-value');
        querySpecificationList(categoryCode);
    });

    $(document).on('click','.loadli',function() {
        var countryCode = $(this).attr('data-value');
        queryPortList(countryCode, 'loadingPortList');
        $("#LoadPortTwo").html("");
        $("#LoadPortTwoCode").attr("value","");
    });

    $(document).on('click','.dischargeli',function() {
        var countryCode = $(this).attr('data-value');
        queryPortList(countryCode, 'dischargePortList');
        $("#DischargeTwo").html("");
        $("#DischargeTwoCode").attr("value","");
    });

    $('.per-field input').focus(function () {
        if (!$(this).hasClass('add-quoter')) {
            $(this).css("background-color", "#FFFFCC");
        }
    });
    $(".per-field input").blur(function () {
        if (!$(this).hasClass('add-quoter')) {
            $(this).css("background-color", "#FFF");
        }
    });
})



function emptyCheck() {
    var check=true;
    var val=$('input:radio[name="demandType"]:checked').val();
    if(val==null || val == ''|| val == undefined){
        // var aa = $("input:radio").attr("name")
        alert('Please input demandType');
        return false;
    }

    console.log($('#productQuantity').val());
    console.log($('#productQuantityName').val());
    /*var goods=$('input:radio[name="productSource"]:checked').val();
     if(goods==null || goods == ''|| goods == undefined){
     alert('Please input Goods Origin');
     return false;
     }*/
    $("li").each(function (index) {
        if ($(this).find('span').hasClass('must')){
            var self = this;
            $(self).find('input').each(function() {
                if ($(this).attr('name') != 'file') {
                    if ($(this).val() == '' || $(this).val() == null || $(this).val() == undefined) {
                        // if ($.trim($(this))){
                        alert('Please input'+$.trim($(self).find('label').text()));
                        check= false;
                        return ;
                        // }
                    }
                }
            });

        }
    });
    if(!check){
        return;
    }
    return check;
}

function inquiry(code){

    if(code == '2'){
        $("#bidvalid").val("");
        $("#bidvaliddiv").empty();

        $("#inquiryDeadLine").hide();
        $("#inquiryDeadLine input").attr('data-rules','');
    }else{
        $("#inquiryDeadLine").show();
        $("#inquiryDeadLine input").attr('data-rules','required');
    }
}

$(document).on('click','.tradeTermCode',function() {
    var item = $(this).attr("data-value");
    if(item=="1"){
        $("#loadPortShowOrHide").show();
        $("#loadPortShowOrHide input").attr('data-rules','required');

        $("#dischargeShowOrHide").hide();
        $("#dischargeShowOrHide input").attr('data-rules','');
    }else if(item=="2" || item=="3"){
        $("#loadPortShowOrHide").hide();
        $("#loadPortShowOrHide input").attr('data-rules','');
        $("#dischargeShowOrHide").show();
        $("#dischargeShowOrHide input").attr('data-rules','required');
    }
});




function querySpecificationList(categoryCode) {
    var url = appServer + 'pages/front/publish_product/get_specification_list.json?categoryCode=' + categoryCode;

    $.get(url, {

    }, function(result) {
        if (result.status == 0) {
            var specificationList = result.datas;

            if (specificationList) {
                $('.productSpecificationList').empty();
                for (var i = 0; i < specificationList.length; i++) {
                    var specification = specificationList[i];

                    var li = "<li class='select-option' data-value='" + specification.code + "'>" +
                        specification.enName +
                        "</li>";
                    $('.productSpecificationList').append(li);
                }
            }
        }
    });
}

function queryPortList(shipPortCode, berthListId) {
    $.get(appServer + "pages/front/publish_product/port_list.json", {
        "countryCode": shipPortCode
    }, function (result) {
        var shipPortList = result.datas; //解析 json
        if(shipPortList){
            var length = shipPortList.length;

            var dom = "";
            for (var i = 0; i < length; i++) {
                var shipPort = shipPortList[i];

                if (berthListId == 'loadingPortList') {
                    /*dom = dom +
                        "<li class='select-option' data-value='" + shipPort.code + "' onclick='renderBerthList(" + shipPort.code + ")'>" +
                        shipPort.enName +
                        "</li>"*/
                    dom = dom +
                        "<li class='select-option' data-value='" + shipPort.code + "' >" +
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
        }


    })
}

function deadlineTime() {
    layui.use(['laydate'], function () {
        var laydate;
        var Barr = []
        var Aarr = []
        var index = -1;
        var laydate = layui.laydate;
        $('.DeadlineMonth2').each(function () {
            laydate.render({
                elem: this,
                min:$(this).hasClass('minCurrentTime')?'':'1900-1-1',
                type: 'datetime',
                lang: 'en',
                format: 'dd MM yyyy HH:mm:ss',
                theme: '#F36523',
                closeStop: this,
                done: function done(value, date, endDate) {
                    var classList = $(this)[0].elem[0];
                    if ($(classList).hasClass('validateTime1')) {
                        Aarr.push(value)
                        var DeadlineMonth2=$(this.elem[0]).closest('.dateParent').siblings('.dateParent').find('.DeadlineMonth2')
                        if(DeadlineMonth2) {
                            var val = DeadlineMonth2.val()
                            Barr.push(val)
                        }
                    } else if($(classList).hasClass('validateTime2')) {
                        Barr.push(value)
                    }
                    //转月份为英文月份简写
                    var monthArr = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'June', 'July', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec'];
                    var dateCopy = JSON.parse(JSON.stringify(date));
                    dateCopy.month = monthArr[parseInt(dateCopy.month) - 1];
                    var arr = value.split(' ');
                    arr.splice(1, 1, dateCopy.month);
                    var evalue = arr.join(' ');
                    $(this.elem[0]).css({
                        'opacity': 0
                    });
                    $(this.elem[0]).attr('value', value)
                    $(this.elem[0]).siblings('.datetime-view').text(evalue)
                    $(this.elem[0]).closest('.dateParent').siblings('.dateParent').find('.error').remove()
                    var parend = $(this.elem[0]).closest('.dateParent');
                    parend.find('.error1').remove();
                    parend.find('.error').remove();
                    if (Barr.length > 0 && Aarr.length > 0) {
                        if (!TimeContrast(Aarr[Aarr.length - 1], Barr[Barr.length - 1])) {
                            var h=$(this.elem[0]).outerHeight()
                            var w=$(this.elem[0]).parent('.datetime-wrap').prev().outerWidth(true);
                            if(parend.hasClass('laycanTime')){
                                parend.append('<div class="error">Laycan end date must be later than laycan start date.</div>');
                            }else if(parend.hasClass('deadlineTime')){
                                parend.append('<div class="error">The bid valid to date must be later than expired time.</div>');
                            }
                            parend.css({"position":"relative"})
                            parend.children('.error').css({"position":"absolute","top": h,"left": w})
                        } else {
                            parend.children('.error').remove();
                            parend.children('.error1').remove();
                        }
                    }
                    //$(this.elem[0]).blur();
                }
            });

        })
    })
}
function indexs(index){
    index++;
    return index
}  //deadline
function TimeContrast(a,b){
    var a=a.split(' ').reverse();
    var b=b.split(' ').reverse();
    if(a.length>2&&b.length>2){
        var a1=a[0].split(':')
        var b1=b[0].split(':')
        var startTime = new Date(a[1], a[2], a[3],a1[0],a1[1]);
        var startTimes = startTime.getTime();
        var endTime = new Date(b[1], b[2], b[3],b1[0],b1[1]);
        var endTimes = endTime.getTime();
        if (endTimes<startTimes) {
            return false;
        }
        return true;
    }
    var startTime = new Date(a[0], a[1], a[2]);
    var startTimes = startTime.getTime();
    var endTime = new Date(b[0], b[1], b[2]);
    var endTimes = endTime.getTime();
    if (endTimes<startTimes) {
        return false;
    }
    return true;


} //deadline
var flg = false;
function postDemandSheet(demandSheetStatus) {
    var arr=$('#publishProductForm')  //把你要验证的form表单数组赋值给arr
    //var buttonClass='submit'  //提交按钮类名
    var flag = commonCPX.FormValidation(arr)
    if(!flag){
        var demandType = $("input[name='demandType']:checked").val();
        /*if($(".demurrage-box .radio i").attr('checked') == "checked"){
            //$("input[name='transportInfoVO.demurrageRateVO.code']").val("2");
            $("#demurrageRate").val(2);
        }else{
            $("#demurrageRate").val(1);
        }*/
        var enterpriseIds = '';
        if(demandType == 2){
            $("#bidvalid").val("");
            $('.inquiry-private-table tbody').children('tr').each(function(index, value) {
                var enterpriseIdToAdd = $(value).children('td').eq(3).html();
                enterpriseIds += enterpriseIdToAdd;
                enterpriseIds += ',';
            });
            if (enterpriseIds.length != 0) {
                enterpriseIds = enterpriseIds.substr(0, enterpriseIds.length - 1);
            }
        }
        if(enterpriseIds == '' &&  demandType == 2 ){
            layui.use(['layer'], function(){
                var layer = layui.layer;
                layer.alert("Please choose one enterprise at least" ,{title:'Error', btn:['OK']});
            })
        }
        else{
            $('#specifiedEnterpriseIdList').val(enterpriseIds);
            //保存/发布
            $("input[name='demandSheetVO.demandSheetStatusVO.code']").val(demandSheetStatus);
            //销售类型
            var demandTypes = $("[name='demandType']");
            for (var i = 0; i < demandTypes.length; i++) {
                var demandType = demandTypes[i];

                if ($(demandType).siblings('i').attr('checked') == 'checked') {
                    $('[name="demandSheetVO.demandTypeVO.code"]').val($(demandType).val());
                    break;
                }
            }

            //商品来源
            /*if(isSingapore){

                var productSources = $("[name='productSource']");
                var source = $("[name='saleSheetVO.productSourceVO.code']");
                var count = 0;
                for (var i = 0; i < productSources.length; i++) {
                    var productSource = productSources[i];

                    if ($(productSource).siblings('i').attr('checked') == 'checked') {
                        count++;
                        $('[name="saleSheetVO.productSourceVO.code"]').val($(productSource).val());
                        break;
                    }
                }
                if(count == 0){
                    layui.use(['layer'], function(){
                        var layer = layui.layer;
                        layer.alert("Please fix the data of goods origin.",  {title:'Error', btn:['OK']});
                    })
                    return;
                }

            }*/
            //$("#publishProductForm").submit();
            postOrSave();
        }
    }else{
        layui.use(['layer'], function(){
            var layer = layui.layer;
            layer.alert("Data errors. Please fix the data you input.",  {title:'Error', btn:['OK']});
        })
    }
    function postOrSave() {
        layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function() {
            var layer = layui.layer,
                form = layui.form,  // 载入form表单
                table = layui.table;
            window.$ = layui.jquery;
            var laydate = layui.laydate;
            if (flg) {
                return;
            } else {
                flg = true;
            }
            $.ajax({
                type: "POST",
                url: "post_demand_sheet.json",
                data: $('#publishProductForm').serialize(),
                success: function (msg) {
                    flg = false;
                    /*layer.alert(msg.message, {title: 'Info', btn: ['OK']}, function () {
                        window.location.href = "../../back/my_demand_list/index.htm";
                    });*/
                    layer.open({
                        title:'Info',
                        content: msg.message
                        ,btn: ['Ok']
                        ,yes: function(index, layero){
                            window.location.href =  "../../back/my_demand_list/index.htm";
                        }
                        ,cancel: function(){
                            window.location.href =  "../../back/my_demand_list/index.htm";
                        }
                    });
                },
                error: function (msg) {
                    flg = false;
                    layer.alert("error:" + msg.message, {title: 'Error', btn: ['OK']});
                }
            });

        })
    }
    /*function edit() {
        layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function() {
            var layer = layui.layer,
                form = layui.form,  // 载入form表单
                table = layui.table;
            window.$ = layui.jquery;
            var laydate = layui.laydate;
            if (flg) {
                return;
            } else {
                flg = true;
            }
            $.ajax({
                type: "POST",
                url: "commit_sale_sheet_edit.json",
                data: $('#publishProductForm').serialize(),
                success: function (msg) {
                    flg = false;
                    layer.alert(msg.message, {title: 'Info', btn: ['OK']}, function () {
                        window.location.href = "../../back/my_product_list/index.htm";
                    });
                },
                error: function (msg) {
                    flg = false;
                    layer.alert("error:" + msg.message, {title: 'Error', btn: ['OK']});
                }
            });

        })
    }*/
}

