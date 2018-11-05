$(function() {
    commonCPY.selectReg();
    commonCPY.radios();
    commonCPY.dateValidate();

    if(shipPortCode2 != "" && shipPortCode2 !=undefined ){
        queryPortList(shipPortCode2,"loadingPortList");
    }
    if(shipPortCode3 != "" && shipPortCode3 !=undefined ){
        queryPortList(shipPortCode3,"dischargePortList");
    }

    $(".qualityStandardName").on("click",function(){
        var path=$(".qualityStandard").html();
        var name= $(".qualityStandardName").html();
        window.location.href = appServer +"common/doc/download.htm?"+"path="+path+"&fileName="+name;
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


    $(document).on('click','.tradeTermCode',function() {
        var item = $(this).attr("data-value");
        if(item=="1"){
            $("#loadPortShowOrHide").show();
            $("#loadPortShowOrHide input").attr('data-rules','required');

            $("#dischargeShowOrHide").hide();
            $("#dischargeShowOrHide input").attr('data-rules','');
        }else if(item=="2" || item == '3'){
            $("#loadPortShowOrHide").hide();
            $("#loadPortShowOrHide input").attr('data-rules','');
            $("#dischargeShowOrHide").show();
            $("#dischargeShowOrHide input").attr('data-rules','required');
        }
    });


    if(productInfoVOa==""||productInfoVOa==undefined){
        $("#loadPortShowOrHide").hide();
        $("#dischargeShowOrHide").hide();

    }else if(productInfoVOa=="1"){
        $("#loadPortShowOrHide").show();
        $("#loadPortShowOrHide input").attr('data-rules','required');

        $("#dischargeShowOrHide").hide();
    }else if(productInfoVOa == "2" || productInfoVOa == "3"){
        $("#loadPortShowOrHide").hide();

        $("#dischargeShowOrHide").show();
        $("#dischargeShowOrHide input").attr('data-rules','required');
    }
})

function addBench() {
    $('.addmark2').css('display','block');
    $(".addmarkicon").css('opacity','0');
}



function delBench() {
    $('.addmark2').css('display','none');
    $(".addmarkicon").css('opacity','1');

    //值清空
    $('#priceSourceVO').html('');
    $('#priceSourceVO2').attr('value','');
    $('#pricingBenchmarkVO').html('');
    $('#pricingBenchmarkVO2').attr('value','');
    $('#priceRegionVO').html('');
    $('#priceRegionVO2').attr('value','');


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
                } else if (berthListId == 'dischargePortList'){
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

function disablefn(a) {
    if($(a).find('i').attr('checked')==undefined){
        $('.dem_input').val('')
        $('.dem_input').attr('disabled','disabled')
        $('.dem_input').css('background','#ebebe4')
    }else{
        $('.dem_input').removeAttr('disabled')
        $('.dem_input').css('background','#ffffff')

    }

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
                format: 'dd MM yyyy HH:mm',
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

