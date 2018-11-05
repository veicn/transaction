/*个人首页*/

//搜索框聚焦
$("#showInput").on("click", function(e) {
	$(".search_container,#searchText").show();
	$("#showInput").hide();
	$("#searchInput").focus();
	e.stopPropagation();
});
$("body").on("click", function(e) {
	if($(e.target).closest(".search_container").length==0){
		$(".search_container,#searchText").hide();
		$("#showInput").show();
	}
});
$("#searchText").on("click", function(e) {
	var searchText = $("#searchInput").val();
	alert('搜索：'+searchText);
	e.stopPropagation();
	//e.stopPropagation();//停止冒泡  非ie
	//e.cancelBubble = true;//停止冒泡 ie
});


(function(){
	var screenHeight = $(window).height();
	var menuHeight = $('#menu').height();
	var reserveHeight = screenHeight-menuHeight;
	$($('.footer')[0]).prepend('<div class="scrool_mark" id="scroolMark" style="top:'+reserveHeight+'px;"></div>');
})();

$(document).scroll(function(){
	var screenHeight = $(window).height();
	var scrollTop = $(document).scrollTop();
	var menuTop = $($('.zx-myh-right')[0]).offset().top;
	var scroolMarkTop = $('#scroolMark').offset().top;
	var leftWidth = $('#menu').width();

	if(scrollTop<menuTop){
		$('#menu').removeClass("menu_fix menu_bottom");
		$('.zx-myh-right').css("margin-left",0);
	}else if(scrollTop>=menuTop && scrollTop+screenHeight<scroolMarkTop){
		$('#menu').removeClass("menu_bottom").addClass("menu_fix");
		$('.zx-myh-right').css("margin-left",leftWidth);
	}else{
		// console.log("滚动距离",scrollTop,menuTop);
		$('#menu').removeClass("menu_fix").addClass('menu_bottom');
		$('.zx-myh-right').css("margin-left",leftWidth);
	}
	 // console.log("滚动距离",scrollTop,menuTop);
});
