$(".s-exec-tit").click(function(){
	if($(this).siblings(".s-exec-cont").is(":visible")){
		$(this).siblings(".s-exec-cont").slideUp();
		$(this).children("i").removeClass('s-exec-cur');
	}else{
		$(this).siblings('.s-exec-cont').slideDown().parent().siblings().children(".s-exec-cont").slideUp();
		$(this).children("i").addClass('s-exec-cur').parent().siblings().children(".s-exec-tit i").removeClass('s-exec-cur');
	}
});