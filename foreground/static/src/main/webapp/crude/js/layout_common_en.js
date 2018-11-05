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
                ,lang: 'en'
            });
        });

    });
}

/**
 * 初始化页面上的日期控件,精确到分钟
 * @param elems
 */
function initDateTimeInputValue(elems) {

    layui.use('laydate', function () {
        var laydate = layui.laydate;

        elems.each(function () {
            laydate.render({
                elem: this //指定元素
                ,type: 'datetime'
                //,value: new Date(new Date(new Date().toLocaleDateString()).getTime()+24*60*60*1000-1)
                ,lang: 'en'
            });
        });

    });
}
/**
 * 初始化页面上的日期控件,精确到分钟，无默认值
 * @param elems
 */
function initDateTimeInput(elems) {

    layui.use('laydate', function () {
        var laydate = layui.laydate;

        elems.each(function () {
            laydate.render({
                elem: this //指定元素
                ,type: 'datetime'
                ,lang: 'en'
            });
        });

    });
}