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

$(document).scroll(function(){
	var scrollTop = $(document).scrollTop();
	var menuTop = $('.zx-myh-right')[0].offsetTop;

	var leftWidth =$('#menu').width();
	if(scrollTop>=menuTop){
		$('#menu').addClass("menu_fix");
		$('.zx-myh-right').css("margin-left",leftWidth);

	}else{
		$('#menu').removeClass("menu_fix");
		$('.zx-myh-right').css("margin-left",0);
		
	}
    // console.log("滚动距离",scrollTop,menuTop);
});
