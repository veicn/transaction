
var picker = new Pikaday({
    field: document.getElementById('datepicker'),
    // format: 'M/D/YYYY',
    toString(date, format) {
        // you should do formatting based on the passed format,
        // but we will just return 'D/M/YYYY' for simplicity
        var day = date.getDate();
        var month = date.getMonth() + 1;
        var year = date.getFullYear();
        return `${month}/${day}/${year}`;
    }, //自定义格式的功能
    parse(dateString, format) {
        // dateString is the result of `toString` method
        var parts = dateString.split('/');
        var day = parseInt(parts[0], 10);

        month = parseInt(parts[1] - 1, 10);
        var year = parseInt(parts[1], 10);
        return new Date(year, month, day);
    }, //函数将用于解析输入字符串并从中获取日期对象。此功能将优先moment
    onSelect: function (e) {
        console.log(e);
    }

});


$(function () {
    // select选框
    for (let index = 1; index < 5; index++) {
        $(".select" + index).click(function (e) {
            $(".select" + index).toggleClass('open');
            e.stopPropagation();
        });

        $(".select1 ul li").click(function (e) {
            var _this = $(this);
            $(".select" + index + " > .my-p-t").text(_this.attr('data-value'));
            _this.addClass("Selected").siblings().removeClass("Selected");
            $(".select" + index).removeClass("open");
            e.stopPropagation();
        });

        $(document).on('click', function () {
            $(".select" + index).removeClass("open");
        })


    }

});
