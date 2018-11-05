function checkContract(uuid, id) {
    layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
        var url = shippingServer + "pages/back/demands/checkDemands.json";
        var layer = layui.layer;
        $.post(url, {
            orderUuid: uuid,
            orderId: id
        }, function(result) {
            var status = result.status;

            if (status != '0') {
                layer.alert(result.message,{title:'Info', btn:['OK']});
            } else {
                window.location.href = shippingServer + "pages/back/demands/insert.htm?orderUuid=" + uuid;
            }
        });
    })
}

function checkVesselConfirmation(uuid, id) {

    layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
        var layer = layui.layer;
        var url = shippingServer + "pages/back/vesselAcceptance/checkConfirmation.json";

        $.post(url, {
            orderUuid: uuid,
            orderId: id
        }, function(result) {
            var status = result.status;

            if (status != 0) {
                layer.alert(result.message,{title:'Info', btn:['OK']});
                //alert(result.message);
            } else {
                window.location.href = shippingServer + "pages/back/vesselAcceptance/create.htm?orderUuid=" + uuid;
            }
        });
    })
}

