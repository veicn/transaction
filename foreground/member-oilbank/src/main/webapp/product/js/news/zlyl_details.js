
(function(){
	var screenHeight = $(window).height();
	var articleHeight = $('#rightArticle').height();
	var reserveHeight = screenHeight-articleHeight;
	$($('.footer')[0]).prepend('<div class="scrool_mark" id="scroolMark" style="top:'+reserveHeight+'px;"></div>');
})();
var leftWidth = $("#rightArticle").offset().left;
$(document).scroll(function(){
	var screenHeight = $(window).height();
	var scrollTop = $(document).scrollTop();
	var articleTop = $($('.zx-hyzl-dl')[0]).offset().top;
	var scroolMarkTop = $('#scroolMark').offset().top;

	var padding_right_width = parseInt($($('.inner')[0]).css('padding-right'));

	if(scrollTop<articleTop){
		$('#rightArticle').removeClass("menu_fix menu_bottom").css("left",0);
	}else if(scrollTop>=articleTop && scrollTop+screenHeight<scroolMarkTop){
		$('#rightArticle').removeClass("menu_bottom").addClass("menu_fix").css("left",leftWidth);
	}else{
		$('#rightArticle').removeClass("menu_fix").addClass("menu_bottom").css("left",leftWidth-padding_right_width);
	}

});
