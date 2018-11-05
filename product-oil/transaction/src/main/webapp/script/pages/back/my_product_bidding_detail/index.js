$(function() {
    $(".qualityStandardName").on("click",function(){
        var path=$(".qualityStandard").html();
        var name= $(".qualityStandardName").html();
        window.location.href = appServer +"common/doc/download.htm?"+"path="+path+"&fileName="+name;
    });
})

