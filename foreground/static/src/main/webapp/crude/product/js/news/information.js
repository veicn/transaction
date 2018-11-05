/*资讯首页*/

  //Tab页切换
  $(".tab_menu li").on("click", function(e) {
  	var index = $(this).data("index");
  	var tabMenu = $(this).parent();
  	var contentArea = $(this).parent().next();

  	$(tabMenu).find("a").removeClass("cur");
  	$(this).find("a").addClass("cur");

  	$(contentArea).find(".tab_page").fadeOut();
  	$(contentArea).find(".tab_page:nth-child("+index+")").fadeIn();

  });

  var mySwiper1 = new Swiper ('#slide_1', {
    initialSlide :0,          //初始化索引
    slidesPerView: 2,
    spaceBetween: 0,
    navigation: {
      prevEl: '.last_1',
      nextEl: '.next_1',
    },
  });

  var mySwiper2 = new Swiper ('#slide_2', {
    initialSlide :0,          //初始化索引
    // slidesPerView: 3,
    slidesPerView: 'auto',
    spaceBetween: 8,
    navigation: {
      prevEl: '.last_2',
      nextEl: '.next_2',
    },
  });


  $("#slide_1 .swiper-slide").on("click", function(e) {
    $(this).parent().find("a").removeClass("cur");
    $(this).find("a").addClass("cur");
  });

  $("#slide_2 .swiper-slide").on("click", function(e) {
    $(this).parent().find(".swiper-slide").removeClass("cur");
    $(this).addClass("cur");
  });




