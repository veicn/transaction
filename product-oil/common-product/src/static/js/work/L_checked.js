$(function(){
    $(".check-b label").click(function(){
        $(".check-b label").children().css("backgroundImage","url()");
        console.log($(this))
        $(this).children().css("backgroundImage","url('../images/tick_L.png')");
    })
    //鼠标选中时边框变色,点击外面时恢复原色
})

// select选框

$(".select1").click(function (e) {
    $(".select1").toggleClass('open');
    e.stopPropagation();
});

$(".select1 ul li").click(function (e) {
    var _this = $(this);
    $(".select1 > .my-p-t").text(_this.attr('data-value'));
    _this.addClass("Selected").siblings().removeClass("Selected");
    $(".select1").removeClass("open");
    e.stopPropagation();
});

$(document).on('click', function () {
    $(".select1").removeClass("open");
})
