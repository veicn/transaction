
$(function(){
	var toggleyes = true;
	$('.c-menu>li>a').attr('moren', 'true');
	$('.c-ml .c-mlf > .cw-ico').on('click', toggle) 
	function toggle() {
	    if ($('.c-ml').css('width') == '250px') {
	        toggleyes = false;         
	        $('.c-ml').animate({ 'left': '-35px' }, 200).animate({ 'width': '85px' }, 400);
	        $('.c-mr').animate({ 'margin': '0 50px 0 70px' }, 600);
	        $('.c-mlb').animate({ 'width': '50px' }, 600);
	        $('.c-complogo a img').animate({ 'height': '80%' }, 200);
	        $('.c-company p').animate({ 'width': '0px' }, 0);
	        $('.c-company p').animate({ 'height': '0px' }, 0);
	        $('.c-company p').animate({ 'paddingLeft': '0px' }, 0);
	        $('.c-menu .m-number').fadeOut(0);
	        $('.c-menu li ul').fadeOut(300);
	        $('.c-menu li em').fadeOut(300);
	        $('.c-menu i').animate({ 'marginLeft': '11px' }, 100);
	        $('.c-header .c-logo').css({ 'backgroundSize': '130%' });
	        $('.c-header .c-logo').animate({ 'width': '50px' }, 600);
	        $('.c-nav .li-search').css({ 'position':'relative' });
	        $('.c-nav .li-search').animate({'left': '-200px' }, 600);
	    } else {
	        toggleyes = true;
	        $('.c-menu>li>a').attr('moren', 'true');
	        $('.c-ml').animate({ 'left': '0px' }, 200).animate({ 'width': '250px' }, 400);
	        $('.c-mr').animate({ 'margin': '0 50px 0 270px' }, 600);
	        $('.c-mlb').animate({ 'width': '250px' }, 600);
	        $('.c-complogo  a img').animate({ 'height': '100%' }, 200);
	        $('.c-company p').delay(400).animate({ 'width': '100px' }, 0);
	        $('.c-company p').delay(400).animate({ 'height': '49px' }, 0);
	        $('.c-company p').animate({ 'paddingLeft': '16px' }, 0);
	        $('.c-menu .m-number').fadeIn(300);
	        $('.c-menu li ul').fadeIn(300);
	        $('.c-menu li em').fadeIn(300);
	        $('.c-menu i').animate({ 'marginLeft': '0px' }, 100);
	        $('.c-header .c-logo').css({ 'backgroundSize': '100%' });
	        $('.c-header .c-logo').animate({ 'width': '250px' }, 600);
	        $('.c-nav .li-search').animate({ 'position':'relative','left': '0px' }, 600);
	        $('.c-menu>li>ul').hide()
	    }
	}
	//  侧边栏收缩之后的移入效果
	$('.c-menu li').css({
	    'zIndex': '999999'
	});
	$('.c-menu>li').hover(function() {
	    if (toggleyes == false) {

	        $(this).find('ul').css({
	            'display': 'block',
	            'position': 'absolute',
	            'zIndex': '999999',
	            'top': '0px',
	            'left': '80px',
	        })
	        $(this).find('ul li a').css({
	            'padding': '8px 20px 8px 30px'
	        });
	        $(this).find('ul').animate({ 'left': '85px' }, 400)
	    }
	}, function() {
	    if (toggleyes == false) {
	        $('.c-menu li ul').css({
	            'display': 'none',
	            'position': 'static',
	            'zIndex': '999999',
	            'top': '0px',
	            'left': '80px',
	        })
	        $('.c-menu li ul').fadeOut(0)
	        $('.c-menu li ul li a').css({
	            'padding': '8px 20px 8px 76px'
	        });
	    }
	});
})

