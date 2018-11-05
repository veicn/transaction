
function bidFun(uuid){
    var flg = false;
    $.ajax({
        type: "POST",
        url: appServer+"/pages/front/product_bidding/bid.json",
        data: {
            'uuid': uuid
        },
        success: function (msg) {
            flg = false;
            if(msg.status==1){
                layer.confirm("You have bid for this sales resource. Are you sure to re-bid ?", {
                    title:'Info',
                    area: ['535px'],
                    type: 1,
                    icon: 2,
                    //anim: 6, //默认动画风格
                    skin: 'demo-class',
                    closeBtn: 3,
                    shadeClose: false,
                    shade: [0.5, '#000000'],
                    btnAlign: 'c',
                    btn: ['Yes','No','View Existed Bid'] //按钮
                    ,btn3:function(){
                        window.location.href =appServer + "pages/back/my_bidding_detail/index.htm?uuid="+msg.message;

                    }
                }, function(){
                    window.location.href =appServer + "pages/front/product_bidding/index.htm?uuid="+uuid+"&&biddingSheetUuid="+msg.message;

                },function(){
                    return;
                })
            }else{

                window.location.href = appServer+"pages/front/product_bidding/index.htm?uuid="+uuid;
            }


            /* layer.alert(msg.message, {title: 'Info', btn: ['OK']}, function () {
                 window.location.href = "appServer/pages/back/my_product_list/index.htm";
             });*/
        },
        error: function (msg) {
            window.location.href = appServer+"pages/front/product_bidding/index.htm?uuid="+uuid;
        }
    });


}


function bidDemandFun(uuid){
    var flg = false;
    $.ajax({
        type: "POST",
        url: appServer+"/pages/front/product_bidding/demand_bid.json",
        data: {
            'uuid': uuid
        },
        success: function (msg) {
            flg = false;
            if(msg.status==1){
                layer.confirm("You have offered for this purchase demand.<br> Are you sure to re-offer ？", {
                    title:'Info',
                    area: ['535px'],
                    type: 1,
                    icon: 2,
                    //anim: 6, //默认动画风格
                    skin: 'demo-class',
                    closeBtn: 3,
                    shadeClose: false,
                    shade: [0.5, '#000000'],
                    btnAlign: 'c',
                    btn: ['Yes','No','View Existed Bid'] //按钮
                    ,btn3:function(){
                        window.location.href =appServer + "pages/back/my_demand_bidding_detail/index.htm?uuid="+msg.message;

                    }
                }, function(){
                    window.location.href =appServer + "pages/front/product_bidding/demand_index.htm?uuid="+uuid+"&&biddingSheetUuid="+msg.message;

                },function(){
                    return;
                })
            }else{

                window.location.href = appServer+"pages/front/product_bidding/demand_index.htm?uuid="+uuid;
            }


            /* layer.alert(msg.message, {title: 'Info', btn: ['OK']}, function () {
                 window.location.href = "appServer/pages/back/my_product_list/index.htm";
             });*/
        },
        error: function (msg) {
            window.location.href = appServer+"pages/front/product_bidding/index.htm?uuid="+uuid;
        }
    });


}

function sendQueryData(inputId, inputValue,event) {
    $('#' + inputId).val(inputValue);

    // querySpecificationList(inputValue);
    // if(inputId == 'sheetFlag'){
    //     localStorage.removeItem('indexs')
    // }
    if(inputId != 'saleTypeCode'){
        localStorage.removeItem('indexMsg')

        if($(event.target).hasClass('categoryShow')){
            localStorage.setItem('indexsb', 'c');
        }
        if(!$(event.target).hasClass('cateName')&&!$(event.target).hasClass('line-cate')){
            localStorage.setItem('indexMsg', $(event.target).parents('.line-cate').index());

        }
        if(inputId == 'sheetFlag'){
            localStorage.setItem('indexsb', 'f');
        }
    }


    if($(event.target).parents('.line-cate').hasClass('line-specif')){
        //$('#productSpecificationCode').val('');
        localStorage.setItem('indexsb', $(event.target).parents('.line-cate').index());
    }
    $('#saleSheetQueryForm').submit();

}
function sendQueryDataRange(inputId, inputValue) {

    $('#' + inputId).val(inputValue);
    $('#saleSheetQueryForm').submit();

}
function querySpecificationList(categoryCode) {
    var url = appServer + 'pages/front/publish_product/get_specification_list.json?categoryCode=' + categoryCode;

    $.get(url, function(result) {
        if (result.status == 0) {
            var specificationList = result.datas;
            if (specificationList) {
                $('.SpecificationList').empty();
                for (var i = 0; i < specificationList.length; i++) {
                    var specification = specificationList[i];
                var div="<div class='line-cate >"+
                        "<a href='javascript:void(0)' class='c-elect' onclick='sendQueryData('productSpecificationCode',specification.code)'>"+specification.enName+"</a>"+
                        "<a onclick='sendCancelData('productSpecificationCode')'>"
                        +"<i class='del-cate'>"+"</i>"
                        +"</a>"+
                "</div>"
                    $('.SpecificationList').append(div);
                }
            }
        }
    });
}

function sendQueryOrderData(orderByColumn, dom) {
    $('#orderByColumn').val(orderByColumn);

    if ($(dom).siblings('i').hasClass("triangle-up")) {
        $('#orderType').val('DESC');
    } else {
        $('#orderType').val('ASC');
    }

    $('#saleSheetQueryForm').submit();
}

function sendCancelData(inputId,dom) {

    $('#' + inputId).val('');
    if(inputId == 'productCategoryCode'){
        $('#productSpecificationCode').val('');
    }
    localStorage.removeItem('indexMsg')
    // if(inputId=='saleTypeCode'){
    //     // localStorage.setItem('indexMsg', 3);
    // }

    $(dom).find('.del-cate').css("opacity",0);
    $('#saleSheetQueryForm').submit();
}