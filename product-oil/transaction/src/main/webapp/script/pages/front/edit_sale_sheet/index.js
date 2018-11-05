function editSaleSheet() {
    editSaleSheet1('1');
}
var flg = false;
function editSaleSheet1(saleSheetStatus) {
    var arr=$('#publishProductForm')  //把你要验证的form表单数组赋值给arr
    //var buttonClass='submit'  //提交按钮类名
    var flag = commonCPX.FormValidation(arr)
    if(!flag){
        var saleType = $("input[name='saleType']:checked").val();
        var enterpriseIds = '';
        if(saleType == 2){
            $('.inquiry-private-table tbody').children('tr').each(function(index, value) {
                var enterpriseIdToAdd = $(value).children('td').eq(3).html();
                enterpriseIds += enterpriseIdToAdd;
                enterpriseIds += ',';
            });
            if (enterpriseIds.length != 0) {
                enterpriseIds = enterpriseIds.substr(0, enterpriseIds.length - 1);
            }
        }

        if(enterpriseIds == '' &&  saleType == 2 ){
            layui.use(['layer'], function(){
                var layer = layui.layer;
                layer.alert("Please choose one enterprise at least" ,{title:'Error', btn:['OK']});
            })
        }
        else{
            $('#specifiedEnterpriseIdList').val(enterpriseIds);
            //保存/发布
            $("input[name='saleSheetVO.saleSheetStatusVO.code']").val(saleSheetStatus);

            //销售类型
            var saleTypes = $("[name='saleType']");
            for (var i = 0; i < saleTypes.length; i++) {
                var saleType = saleTypes[i];

                if ($(saleType).siblings('i').attr('checked') == 'checked') {
                    $('[name="saleSheetVO.saleTypeVO.code"]').val($(saleType).val());
                    break;
                }
            }

            //商品分类
            /* var categories = $("[name='category']");
             for (var i = 0; i < categories.length; i++) {
                 var category = categories[i];

                 if ($(category).siblings('i').attr('checked') == 'checked') {
                     $('[name="productInfoVO.productCategoryVO.code"]').val($(category).val());
                     break;
                 }
             }*/

            //商品来源
            if(isSingapore == 'true'){
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

            }
            //$("#publishProductForm").submit();

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
            url: "commit_sale_sheet_edit.json",
            data: $('#publishProductForm').serialize(),
            success: function (msg) {
                flg = false;
                /*layer.alert(msg.message, {title: 'Info', btn: ['OK']}, function () {
                    window.location.href = "../../back/my_product_list/index.htm";
                });*/
                layer.open({
                    title:'Info',
                    content: msg.message
                    ,btn: ['Ok']
                    ,yes: function(index, layero){
                        window.location.href = "../../back/my_product_list/index.htm";
                    }
                    ,cancel: function(){
                        window.location.href ="../../back/my_product_list/index.htm";
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
