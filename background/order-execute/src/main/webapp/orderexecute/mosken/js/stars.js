jQuery(document).ready(function($) {
		var width = window.innerWidth;
	var height = window.innerHeight / 2;
	window.onresize = adjuest;

	function adjuest() {
	    width = window.innerWidth;
	    height = window.innerHeight / 2;
	}

	function RandomNumBoth(Min, Max) {
	    var Range = Max - Min;
	    var Rand = Math.random();
	    var num = Min + Math.round(Rand * Range); //四舍五入
	    return num;
	}
 
	var time1 = RandomNumBoth(4000, 6000)
	var time2 = RandomNumBoth(4000, 6000)
	var time3 = RandomNumBoth(4000, 6000)
	var time4 = RandomNumBoth(4000, 6000)
	var time5 = RandomNumBoth(4000, 6000)
	var time6 = RandomNumBoth(4000, 6000)
	var time7 = RandomNumBoth(4000, 6000)
	var time8 = RandomNumBoth(4000, 6000)
	var time9 = RandomNumBoth(4000, 6000)
	var time10 = RandomNumBoth(4000, 6000)

	function stars(index, time) {
	    setInterval(function() {
	        var starwidth = RandomNumBoth(10, 25)
	        $('#fullpage .star3 img').eq(index).css({
	            'position': 'absolute',
	            'left': RandomNumBoth(10, width),
	            'top': RandomNumBoth(10, height),
	            'width': starwidth,
	            'height': starwidth,
	        });
	        $('#fullpage .star3 img').eq(index).fadeIn(time / 8*3).fadeOut(time / 8*3).delay(time /8*2)
	    }, time)
	}
	stars(0, time1);
	stars(1, time2);
	stars(2, time3);
	stars(3, time4);
	stars(4, time5);
	stars(5, time6);
	stars(6, time7);
	stars(7, time8);
	stars(8, time9);
	stars(9, time10);	
});