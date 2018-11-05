$(function(){
	var path=$("#qualityStandard").html();
	var name= $("#qualityStandardName").html();
	$("#qualityStandardName").on("click",function(){

	window.location.href = appServer +"common/doc/download.htm?"+"path="+path+"&fileName="+name;
});
})