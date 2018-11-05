/*页面js*/

//页面初始化
function pageInit(){
  var bannerCount = 4;
  for (var i = 1; i <= bannerCount; i++) {
    initBanner("swiper"+i);
  }
}
//初始化轮播图
function initBanner(containerId){
  var mySwiper = new Swiper ('#'+containerId, {
    initialSlide :0,          //初始化索引
    direction: 'horizontal',  //滑动方向（horizontal，vertical）
    speed:300,                //滑动时间
    grabCursor : false,        //显示手掌形状
    //自动播放
    autoplay : {
      delay:3000,
      disableOnInteraction: false,
    },
    loop: true,
    // 如果需要分页器
    pagination: {
      el: '.swiper-pagination',
      clickable: true,
    },
  });
}

pageInit();