$(function(){

    
    $('.hx_calc').click(function(){
        $('.ul_hx').slideToggle(500);
        $('.tf_form').slideUp(500);
        $('.icon_down').removeClass('reverse_180');
        $('.hx_calc .icon_down').toggleClass('reverse_180');         
    })
    $('.tf_search').click(function(){
        $('.tf_form').slideToggle(500);
        $('.ul_hx').slideUp(500);
        $('.icon_down').removeClass('reverse_180');
        $('.tf_search .icon_down').toggleClass('reverse_180');         
    })

    $('.side-btn').click(function(){
        if($(this).hasClass('open')){
            $(this).css('z-index','999')
            $(this).removeClass('open');
            $(".sr-side-scroll").animate({width:"0"},500);
            $(".sr-side").animate({width:"0"},500);
            $(this).animate({left:0},500);
            $('.sr-body').animate({'margin-left':"-260px"},500);
        }else{
            $(this).addClass('open');
            $(".sr-side-scroll").animate({width:"260px"},500);
            $(".sr-side").animate({width:"260px"},500);
            $(this).animate({left:'260px'},500);
            $('.sr-body').animate({'margin-left':"0px"},500);
          
            // $(this).css('z-index','999')
            // $(this).addClass('open');
            // $(".sr-side-scroll").animate({width:"0"},500);
            // $(".sr-side").animate({width:"0"},500);
            // $(this).animate({left:0},500);
            // $('.sr-body').animate({'margin-left':"-260px"},500);
        }
      
    })

    

    $(".pannel_1_radio").on("click", function(e) {
        var value = $(this).data("value");
        $(this).parent().find("i").attr("class","fs-circle");
        $(this).find("i").attr("class","fs-circle-select");
        if(value==2){
            $("#sailState").fadeIn();
        }else{
            $("#sailState").fadeOut();
        }
    });


    //菜单隐藏
    $(".pannelMenu .item").on("click", function(e) {
        var index = $(this).data("index");
        $(".pannel,.pannel_table,.pannel_two,.pannel_three,.pannel_table_2").hide();
        $(".pannel_"+index).show();

    });
    
    $('.tableBody').on('scroll', function () {
        var left = $(this).scrollLeft();
        $(this).prev().scrollLeft(left);
    });

    
    
    //页面初始化
    function pageInit(){
        // $('.side-btn').click();
        focusPort("port_1");

    }
    pageInit();

    //聚焦港口
    function focusPort(id){
        $("#"+id).focus();
        $("#"+id).next().show();
    }

    $(".portChoose").focus(function(e){  
        console.log("聚焦",$(this).attr("id"));
        // console.log($(this).attr("id"));
        // e.stopPropagation();
      // $(this).next().show();
    });
    $(".portChoose").blur(function(e){  
        console.log("失去焦点",$(this).attr("id"));
        // $(this).next().hide();
        // var input_id = $(this).attr("id");
        // var arr = input_id.split("_");
        // var next = arr[0]+"_"+(parseInt(arr[1])+1);  
        // focusPort(next);
    });

    //菜单隐藏
    $(".pannel_9 .operate .item").on("click", function(e) {
        var sign = $(this).data("sign");
        $("."+sign).show();
    });





})
