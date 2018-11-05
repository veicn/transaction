/**
 * 初始化页面上的日期控件
 * @param elems
 */
function initDateInput(elems) {

    layui.use('laydate', function () {
        var laydate = layui.laydate;

        elems.each(function () {
            laydate.render({
                elem: this //指定元素
            });
        });

    });
}