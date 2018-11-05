
layui.use('element', function () {
    var element = layui.element;

    //一些事件监听
    element.on('tab(demo)', function (data) {
        //console.log(data);
    });
});
var tdMouse=$('.table-active>a>img')
//console.log(tdMouse)
tdMouse.on("mouseenter", function(){
    $(this).next('span').css("opacity",1)
});
tdMouse.on("mouseleave", function(){
    $(this).next('span').css("opacity",0)
});

$("#back").on("click", function () {
    window.history.back()
});

var flg = false;
function accept(biddingUuid) {
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
            url: appServer+"/pages/back/my_demand_list/accept.json",
            data: {
                'biddingUuid': biddingUuid
            },
            success: function (msg) {
                flg = false;
                /*layer.alert(msg.message, {title: 'Info', btn: ['OK']}, function () {
                    window.location.href = appServer+ "/pages/back/my_contract_list/index.htm";
                });*/
                layer.open({
                    title:'Info',
                    content: msg.message
                    ,btn: ['Ok']
                    ,yes: function(index, layero){
                        window.location.href = appServer+ "/pages/back/my_contract_list/index.htm";
                    }
                    ,cancel: function(){
                        window.location.href = appServer+ "/pages/back/my_contract_list/index.htm";
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
function cancel(uuid) {
    layui.use(['layer'], function(){
        layer.confirm('Cancelling the demand will make all the offers lost. Are you sure to cancel it?', {
            title:'Info',
            area: ['535px'],
            type: 1,
            icon: 2,
            ////anim: 6, //默认动画风格
            skin: 'demo-class',
            closeBtn: 3,
            shadeClose: false,
            shade: [0.5, '#000000'],
            btnAlign: 'c',
            btn: ['YES','NO'] //按钮
        }, function(){
            if (flg) {
                return;
            } else {
                flg = true;
            }
            $.ajax({
                type: "POST",
                url:  appServer+"/pages/back/my_demand_list/cancel.json",
                data: {
                    'demandUuid': uuid
                },
                success: function (data) {
                    flg = false;
                    if(data!= null && data.status == 1){
                        /*layer.alert(data.message, {title: 'Info', btn: ['OK']}, function () {
                            window.location.href = appServer+ "/pages/back/my_product_list/index.htm";
                        });*/
                        layer.open({
                            title:'Info',
                            content: data.message
                            ,btn: ['Ok']
                            ,yes: function(index, layero){
                                window.location.href = appServer+ "/pages/back/my_demand_list/index.htm";
                            }
                            ,cancel: function(){
                                window.location.href = appServer+ "/pages/back/my_demand_list/index.htm";
                            }
                        });
                    }else{
                        layer.alert("failed", {title: 'Info', btn: ['OK']});
                    }

                },
                error: function (msg) {
                    flg = false;
                    layer.alert("error:" + msg.message, {title: 'Error', btn: ['OK']});
                }
            });
        }, function(){

        });
    });
}

$(function(){

    $(".upload").on("click",function(){
        var path = "";
        var fileName = this.innerHTML;
        var path = $(this).siblings(".file-name").html();
        console.log(fileName);
        console.log(path);
        window.location.href = appServer +"common/doc/download.htm?"+"path="+path+"&fileName="+fileName;
    });




})

