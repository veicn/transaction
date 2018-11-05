$(function() {
	var sildeWidth_b = parseInt($('.s-side').css('width'));
	var textWidth = parseInt($('.s-peoname').css('width'));
	var textHeight = parseInt($('.s-peoname').css('height'));
	var imgWidth = parseInt($('.s-headimglink').css('width'));
	var user_img_l = parseInt($('.s-peoimg').css('padding-left'))
	var Accordion = function(el, multiple) {
		this.el = el || {};
		this.multiple = multiple || false;

		// Variables privadas
		var links = this.el.find('.s-menu-link');
		// Evento
		links.on('click', {el: this.el, multiple: this.multiple}, this.dropdown);
		
	};

	Accordion.prototype.dropdown = function(e) {
		var $el = e.data.el;
			$this = $(this);
			$next = $this.next();

		$next.slideToggle();
		$this.parent().toggleClass('open');

		if (!e.data.multiple) {
			$el.find('.s-accordion').not($next).slideUp().parent().removeClass('open');
		}
	};
	$('.s-link').click(function(){
		console.log($(this).parent().find('.s-submenu'))
	})
	var accordion = new Accordion($('.s-nav-tree'), false);
	$('.s-accordion li').click(function () {
		$(this).addClass('current').siblings('li').removeClass('current');
	});
	$('.s-menu-link').click(function () {
		$(this).addClass('s-menu-cur').parents('.s-nav-li').siblings('').children('.s-menu-link').removeClass('s-menu-cur');
	});

	var toggleyes=true;
	$('.s-mlf > .fs').on('click', toggle) 
	function toggle() {
	
		sildeWidth = 50;//缩小侧边栏宽度
		picWidth_x = -(parseInt($('.s-side').css('width'))-sildeWidth);//margin-left
		user_pic = 40
	    if (parseInt($('.s-side').css('width'))>100) {
			toggleyes = false;       
			$('.s-side-scroll').animate({ 'width': ''+sildeWidth+'px' }, 400);//侧边栏 
			$('.fs-sort').animate({'margin-left':''+picWidth_x+'px'}, 600)//图标
			$('.s-side').animate({ 'width': ''+sildeWidth+'px' }, 300)
			$('.s-body').animate({ 'margin': '0px 0px 0 '+picWidth_x+'px' }, 600);//content
			$('.s-logo').animate({ 'width': ''+sildeWidth+'px' }, 600);//logo
			$('.s-peoname').animate({ 'width': '0px','height':'0px' }, 0);//头像文字
			$('.s-peoname').animate({ 'padding-left': '0px'}, 0);//头像文字
			$('.s-nav-tree li ul').fadeOut(300);
			$('.s-nav-tree i').animate({ 'marginLeft': '-18px' }, 100);//一级icon
			$('.s-logo').css({ 'backgroundPosition': '-'+user_pic+'px center' });//logo
			$('.s-peoimgspan a img').animate({ 'width': ''+user_pic+'px','height':''+user_pic+'px' }, 200);
			$('.s-peoimg').animate({ 'padding-left':'0px' }, 200)
			$('.s-peoimgspan a img').animate({ 'margin-left':'-10px' }, 200);
		

        } else {
			toggleyes = true;
			$('.s-menu-link').attr(('moren', 'true'));
			$('.s-side-scroll').animate({ 'width': ''+sildeWidth_b+'px' }, 600);
			$('.s-body').animate({ 'margin': '0px 0px 0 0px' }, 400);//content
			$('.fs-sort').animate({'margin-left':'0px'}, 400)//图标
			$('.s-side').animate({ 'width': ''+sildeWidth_b+'px' }, 300)
			
			$('.s-logo').animate({ 'width': ''+sildeWidth_b+'px' }, 400);//logo
			$('.s-peoname').animate({ 'padding-left': '16px'}, 400);
			$('.s-peoname').animate({ 'width': ''+textWidth+'px','height':''+textHeight+'px' },600);//头像文字
	
			
			//$('.s-nav-tree li ul').fadeIn(300);

			$('.s-nav-tree i').animate({ 'marginLeft': '0px' }, 100);//一级icon
			
			$('.s-logo').css({ 'backgroundPosition': 'center center' });
			$('.s-peoimgspan a img').animate({ 'width': ''+imgWidth+'px','height':''+imgWidth+'px' }, 400);
			$('.s-peoimg').animate({ 'padding-left':''+user_img_l+'px' }, 200)
			$('.s-peoimgspan a img').animate({ 'margin-left':'0px' }, 200)
			$('.s-side-scroll>li>ul').hide();
		}
		
		$('.s-nav-tree>li').css({
	    'zIndex': '999999'
	   });
	   $('.s-nav-tree>li').hover(function() {  
		   var sum = 100+($(this).attr('flag')*50)
	    if (toggleyes == false) {	
	        $(this).find('.s-accordion').css({
				'position':'absolute',
				'display': 'block',
				'width':'150px',
	            'zIndex': '999999',
	            'top': '0px',
				'left': '50px',
				'background':'#fff',
	        })
	        $(this).find('ul li div').css({
	            'padding': '8px 20px 8px 30px'
			});
			$(this).find('ul').animate({ 'top': ''+sum+'px' }, 0);

			$(this).find('ul').animate({ 'left': '50px' }, 400)
			
		}
	}, function() {
	    if (toggleyes == false) {
	        $('.s-accordion').css({
	            'display': 'none',
	            'position': 'static',
	            'zIndex': '999999',
	            'top': '0px',
	            'left': '50px',
	        })
			$('.ul li div').fadeOut(0)
			
	        $('.ul li div').css({
	            'padding': '8px 20px 8px 76px'
	        });
		}
		
	
	});
	}


	
});