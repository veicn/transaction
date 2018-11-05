

$(document).ready(function () {

    //轮播图    
    var swiper = new Swiper('.swiper-container', {
        slidesPerView: "auto",
        spaceBetween: 40,
        loop:true,
        pagination: {
            el: '.swiper-pagination',
            clickable: true,
        },
    });

    //历史进程的圆点闪烁
    // setInterval(function () { ysansuo(); }, 1000);

    //向下箭头闪烁
    setInterval(function () { arrow(); }, 2000);

    //滚动添加监听

    $(window).scroll(function(event){
        // $(".maodian").css("display","none");
        console.log(1111);
        // console.log(event);
        var headerH=$('.header-w').height(),
            scrollTop=$(window).scrollTop(),
            winH=$(window).height(),
            compa=winH/2;

            //获取各个模块到**屏幕**顶部的距离
            

            // fir=$(".platform-w").offset().top-scrollTop,
            // infor=$(".infor-w").offset().top-scrollTop,
            // sec=$(".trading-w").offset().top-scrollTop,
            // thr=$(".shipping-w").offset().top-scrollTop,
            // four=$(".company-w").offset().top-scrollTop,
            // fif=$(".growth-w").offset().top-scrollTop,
            // six=$(".vision-w").offset().top-scrollTop;

        //二级导航固定定位与隐藏，回到顶部箭头的显示与隐藏的设置

        if(scrollTop>headerH){
            $(".nav-title").css({display:"block",position:"fixed",top:0,left:0});
            if(scrollTop>winH){
                $("#my-top").addClass("top-center").removeClass("to-top");
            }else if(scrollTop<winH||scrollTop==winH){
                $("#my-top").addClass("to-top").removeClass("top-center");
            };

        }else if(scrollTop<headerH||scrollTop==headerH){
            $(".nav-title").css({display:"none"});
        };

        // 切换标题


        // if(fir < compa && fir>0){
        //     $('.nav-title').scrollLeft(0);
        //     $(".nav-title li").eq(0).addClass("active").siblings().removeClass("active");
        // }else if(infor < compa && infor>0){
        //     $('.nav-title').scrollLeft(0);
        //     $(".nav-title li").eq(1).addClass("active").siblings().removeClass("active");
        // }else if(sec<compa && sec>0){
        //     $('.nav-title').scrollLeft(0);
        //     $(".nav-title li").eq(2).addClass("active").siblings().removeClass("active");
        // }else if(thr<compa  && thr>0){
        //     $('.nav-title').scrollLeft(200);
        //     $(".nav-title li").eq(3).addClass("active").siblings().removeClass("active");
        // }else if(four<compa && four>0){
        //      $('.nav-title').scrollLeft(500);
        //     $(".nav-title li").eq(4).addClass("active").siblings().removeClass("active");   
        // }else if(fif<compa  && fif>0){
        //     $('.nav-title').scrollLeft(700);
        //     $(".nav-title li").eq(5).addClass("active").siblings().removeClass("active");
        // }else if(six<compa  && six>0){
        //     $('.nav-title').scrollLeft(900);
        //     $(".nav-title li").eq(6).addClass("active").siblings().removeClass("active");
        // };

        for(var i=0;i<=6;i++){
             var  hgh=$("body>div").eq(i+2).offset().top-scrollTop;
             if(i<=2){
                if(hgh < compa && hgh>0){
                    $('.nav-title').scrollLeft(0);
                    $(".nav-title li").eq(i).addClass("active").siblings().removeClass("active");
                }
             }else if(i==3){
                 if(hgh < compa && hgh>0){
                    $('.nav-title').scrollLeft(200);
                    $(".nav-title li").eq(i).addClass("active").siblings().removeClass("active");
                }
             }else if(i==4){
                 if(hgh < compa && hgh>0){
                    $('.nav-title').scrollLeft(500);
                    $(".nav-title li").eq(i).addClass("active").siblings().removeClass("active");
                }
             }else if(i==5){
                 if(hgh < compa && hgh>0){
                    $('.nav-title').scrollLeft(700);
                    $(".nav-title li").eq(i).addClass("active").siblings().removeClass("active");
                }
             }else if(i==6){
                 if(hgh < compa && hgh>0){
                    $('.nav-title').scrollLeft(900);
                    $(".nav-title li").eq(i).addClass("active").siblings().removeClass("active");
                }
             }
            };

        $(".nav-title li").eq(4).click(function(){
        $(this).addClass("active").siblings().removeClass("active");
    });
        $(".nav-title li").eq(5).click(function(){
        $(this).addClass("active").siblings().removeClass("active");
    });

    
    $(".nav-title li").each(
        function(i){
            $(this).click(function(){
                $(".nav-title li").eq(i).addClass("active").siblings().removeClass("active");
            })
        })

    });
    //首页请求数据
    var  news={channelMDesc:"CPY",channelMName:"NEWS"};
    $.ajax({
        url: "//info.mycrudeoil.com/front/infoListOS.json",
        type: "POST",
        dataType:"json",
        contentType:"application/json; charset=utf-8",
        data:JSON.stringify(news),
        success: function(msg){
            var datas=msg.datas.infoList,
                arrId=[];
            // console.log(datas);
            for(var i=0;i<=4;i++){
                var txt=datas[i].title,
                    tim=datas[i].release_date,
                    date=new Date(Number(tim)),
                    Y=date.getFullYear(),
                    M=date.getMonth(),
                    D = date.getDate(),
                    arr=['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sept','Oct','Nov','Dec'],
                    mon=arr[M],
                    timer=D+'  ' +mon+'  '+Y,
                    htmlx='<span>'+timer+'</span>'+'<img src="/wap/oilwap/images/arr_whi.png">',
                    // htmly='<span>'+timer+'</span>'+'<img src="/wap/oilwap/images/arr_bla.png">'
                    myId=datas[i].uuid;
                arrId.push(myId);
                // console.log(Y);
                // console.log(M);
                // console.log(D);
                // $(".lit-ite>div>p").eq(0).html(htmlx);
                 
                 $(".lit-ite>div>p").eq(i).html(htmlx);
                 
                $(".lit-ite>p").eq(i).text(txt);
            };
            // console.log(arrId);
            $(".lit-ite").each(function(i){

                $(this).click(function(){
                    //mydl.eq(i).hide("slow");
                    localStorage.setItem('uid', arrId[i]);
                    // alert("uid");

                })

            });
        }
        
    });

   
        // $(".nav-title a").eq(3).click(function(){
        //    $(".maodian").css("display","block");
        // });
    $("#my-top img").click(function(){
        $('body,html').animate({scrollTop:0},500);
    });

    $(".sec-code").click(function(){
        $(".outer").css("display","block");
    });
    $(".outer").click(function(){
        $(this).css("display","none");
    });
    

      

});
// function ysansuo(){
//     $(".sansuo").animate({opacity:"0.2"},1000);
//     $(".sansuo").animate({opacity:"1"},1000);
// };
// function arrow() {
//       $(".arrow").animate({
//          bottom:18 }).fadeOut("normal");
//       $(".arrow").animate({
//  
function arrow() {
    $(".arrow").animate({
        bottom:22}).fadeOut("normal");
    $(".arrow").animate({
        bottom:42}).fadeIn("normal");
};


// 微信配置

// wx.config({

//     debug: false, 

//     jsApiList: ['onMenuShareTimeline', 'onMenuShareAppMessage'] // 功能列表，我们要使用JS-SDK的什么功能

// });

// // config信息验证后会执行ready方法，所有接口调用都必须在config接口获得结果之后，config是一个客户端的异步操作，所以如果需要在 页面加载时就调用相关接口，则须把相关接口放在ready函数中调用来确保正确执行。对于用户触发时才调用的接口，则可以直接调用，不需要放在ready 函数中。

// wx.ready(function(){

//     // 获取“分享到朋友圈”按钮点击状态及自定义分享内容接口

//     wx.onMenuShareTimeline({

//         title: '分享标题', // 分享标题

//         link:"分享的url,以http或https开头",

//         imgUrl: "分享图标的url,以http或https开头" // 分享图标

//     });

//     // 获取“分享给朋友”按钮点击状态及自定义分享内容接口

//     wx.onMenuShareAppMessage({

//         title: '分享标题', // 分享标题

//         desc: "分享描述", // 分享描述

//         link:"分享的url,以http或https开头",

//         imgUrl: "分享图标的url,以http或https开头", // 分享图标

//         type: 'link', // 分享类型,music、video或link，不填默认为link

//     });

// });



