
/*  开关  */
window.onload=function(){
	var switchDiv=document.getElementById("switchDiv");
	var switchEm=document.getElementById("switchEm");
	switchDiv.onclick=function(){
	  switchDiv.className=(switchDiv.className=="s-form-switch s-form-offswitch")?"s-form-switch s-form-onswitch":"s-form-switch s-form-offswitch";
	  switchEm.innerHTML=(switchEm.innerHTML=="OFF")?"ON":"OFF";
	}
}

/*  radio  */
$(function(){
		$(".s-unradio").click(function(){
			$(this).addClass("s-form-radioed");
			$(this).siblings().removeClass("s-form-radioed");
		});
	});

/*  checkbox  */
$(".s-uncheckbox").click(function(){
		$(this).toggleClass("s-form-checked");
	});