
/*  开关  */
window.onload=function(){
	var switchDiv=document.getElementById("switchDiv");
	var switchEm=document.getElementById("switchEm");
if(switchDiv){
	switchDiv.onclick=function(){
	  switchDiv.className=(switchDiv.className=="s-form-switch s-form-offswitch")?"s-form-switch s-form-onswitch":"s-form-switch s-form-offswitch";
	  switchEm.innerHTML=(switchEm.innerHTML=="OFF")?"ON":"OFF";
	}
}
}

/*  radio  */
$(function(){
		$(".s-unradio").click(function(){
			$(this).addClass("s-form-radioed");
			$(this).siblings().removeClass("s-form-radioed");
		});
	});

/*  radio  */
$(function(){
		$(".s-zxr-blo2 li .s-form-radio").click(function(){
			$('.s-zxr-blo2 li .s-form-radio').removeClass("s-form-radioed");
			$(this).addClass("s-form-radioed");
		});
	});


/*  checkbox  */
$(".s-uncheckbox").click(function(){
		$(this).toggleClass("s-form-checked");
	});


/*订单管理左右滑动*/
$(document).ready(function(){
	(function(){
		$(".s-all-jt").each(function(index,element){
			var width = $(this).width();
			var right = 38-width;
			$(this).attr("data-right",right);
		})
	})();
	$(".s-all-jt .s-all-zk").click(function(event){
		var _this = this;
		var timeItem = 250;
		var maxTime = 2000;
		var minTime = 500;
		var hoverObj = $(this).parent();
		var pannelObj = $(hoverObj).parent();
		var right_now = parseInt($(pannelObj).css("right"));
		var right = parseInt($(pannelObj).attr("data-right"));
		if(right_now==0){	//收缩
			$(pannelObj).animate({right: right}, 1000,'linear',function(){
				$(_this).removeClass("s-all-sq");
			});
			//$(pannelObj).parent().css("z-index","-1");

		}else if(Math.abs(right_now-right)<=5){	//展开
			closeExpandFrame();
			var childCount = $(hoverObj).next().children("a").length;
			var runTime = timeItem*childCount>=maxTime ? maxTime : (timeItem*childCount<=minTime ? minTime : timeItem*childCount);
			//$(pannelObj).parent().css("z-index","100");
			$(pannelObj).animate({right: '0'}, runTime,'linear',function(){
				$(_this).addClass("s-all-sq");
			});
		}
	});
	// 关闭展开的框
	function closeExpandFrame(){
		$(".s-all-sq").each(function(index,obj){
			var  pannelObj = $(this).parent().parent();
			var right = $(pannelObj).attr("data-right");
			$(pannelObj).animate({right: right}, 1000,'linear',function(){
				$(obj).removeClass("s-all-sq");
			});
			//$(pannelObj).parent().css("z-index","-1");
		});
	}
});

/*算宽
$(document).ready(function(){
  $(".s-mod-bcont .s-inline").each(function(){
	  var father_width = $(this).width();
	  var first_width = $(this).children(":nth(0)").width();
	  $(this).children(":nth(1)").css("width",father_width-first_width-20);
   
  });
})*/

// 左右抖动
function shake(id){
	var obj = $("#"+id);
	for (var i = 5; i >= 0; i--) {
		obj.animate({left:5*i},100);
		obj.animate({left:-5*i},100);
	}
}
shake("addTrader");

// 展开、收缩切换
$("#upOrDown").click(function(){
	$(".s-mod-bcont1").removeClass("s-visibility");
	$(".s-mod-bcont1").slideToggle();
	$(this).find("i").toggleClass("s-reverse");
});
$(".s-visibility").slideToggle();

// 选项设置
$("#optionSet").click(function(){
	var pannelObj = $(this).parent();
	var right = parseInt($(pannelObj).css('right'));
	if(right<0){
		$(pannelObj).animate({right: '0'});
	}
});
// 隐藏选项设置
function hideOptionSet(){
	$('#optionSet').parent().animate({right: '-140px'});
}