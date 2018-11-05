$(document).ready(function(){
    var hdw = $('.s-tab-title li');
    hdw.click(function(){
        var otherLi= $(this).addClass('s-this').siblings();
		otherLi.removeClass('s-this');
        var item =$('.s-tab-item').eq(hdw.index(this));
		item.addClass("s-show").show();
		item.siblings().removeClass("s-show").hide();	
    });
});
