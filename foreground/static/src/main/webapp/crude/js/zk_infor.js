     jQuery(document).ready(function($) {
        var len=$('.info-shgd ul').length; 
        var count=0;
        var timer=null; 
     timer=setInterval(function(){
        $('.info-shgd').animate({
            'top': -count*41+'px',
        },1000);
        count++;   
       if(count>=len){
        count=0; 
       }
     },2000) 
        
       
        
  });
 