$(function () {
        var slider = new SliderUnlock(".slider",{
                successLabelTip : "验证成功" 
            },function(){
                alert("验证成功");
            });
        slider.init();
    })