$(function(){

    
    $('.hx_calc').click(function(){
        $('.ul_hx').slideToggle(500);
        if($(".icon_down").hasClass('flag')){
            $(".icon_down").css('transform','rotate(0deg)');
            $(".icon_down").removeClass('flag')
        }else{
            $(".icon_down").css('transform','rotate(180deg)');   
            $(".icon_down").addClass('flag')
        }
          
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
})
