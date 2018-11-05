function editDemandSheet() {
    editDemandSheet1('1');
}
var flg = false;
function editDemandSheet1(demnadSheetStatus) {
    var arr=$('#editDemandForm')  //把你要验证的form表单数组赋值给arr
    //var buttonClass='submit'  //提交按钮类名
    var flag = commonCPX.FormValidation(arr);
    if(!flag){
        var demandType = $("input[name='demandType']:checked").val();
        var enterpriseIds = '';
        if(demandType == 2){
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

            if($(".demurrage-box .radio i").attr('checked') == "checked"){
                //$("input[name='transportInfoVO.demurrageRateVO.code']").val("2");
                $("#demurrageRate").val(2);
            }
            //保存/发布
            $("input[name='demandSheetVO.demandSheetStatusVO.code']").val(demnadSheetStatus);
            //销售类型
            var demandTypes = $("[name='demandType']");
            for (var i = 0; i < demandTypes.length; i++) {
                var demandType = demandTypes[i];

                if ($(demandType).siblings('i').attr('checked') == 'checked') {
                    $('[name="demandSheetVO.demandTypeVO.code"]').val($(demandType).val());
                    break;
                }
            }
            edit();
        }
    }else{
        layui.use(['layer'], function(){
            var layer = layui.layer;
            layer.alert("Data errors. Please fix the data you input.",  {title:'Error', btn:['OK']});
        })
    }
}
function edit() {
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
            url: "commit_demand_sheet_edit.json",
            data: $('#editDemandForm').serialize(),
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
                        window.location.href = "../../back/my_demand_list/index.htm";
                    }
                    ,cancel: function(){
                        window.location.href = "../../back/my_demand_list/index.htm";
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
