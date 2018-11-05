$(function(){
	$(".trading-ul li").click(function(){
		$(this).addClass("active").siblings().removeClass("active");
	})
	//鼠标选中时边框变色,点击外面时恢复原色
})
