(function () {

    var mySwiper = new Swiper ('.swiper-container', {
        autoplay: 3000,
        autoplayDisableOnInteraction: false,
        loop: true,
        speed: 1000,
        // 如果需要分页器
        pagination: '.swiper-pagination',
        paginationClickable: true
    })
    $('.swiper-button-prev').on('click',function(){
        mySwiper.swipePrev();
    })
    $('.swiper-button-next').on('click',function(){
        mySwiper.swipeNext();
    })

    $(".m").mouseenter(function(){
        $(this).find('.tip-content').addClass("hover");
    }).mouseleave(function(){
        $(this).find('.tip-content').removeClass("hover");
    });

    //设置默认时间
    function setNormalDay(){
        var date = new Date();
        var year = date.getFullYear();
        var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
        var day = date.getDate()<10?'0'+date.getDate():date.getDate();
        return day+'/'+month+'/'+year;
    }
    $('.date').text(setNormalDay())



    var picker = new Pikaday({
        field: document.getElementById('datepicker'),
        format: 'D/M/YYYY',
        toString: function toString(date, format) {
            var day = date.getDate()<10?'0'+date.getDate():date.getDate();
            var month = date.getMonth()+1<10?'0'+(date.getMonth()+1):date.getMonth()+1;
            var year = date.getFullYear();
            return day+'/'+ month+ '/' + year;
        }
    });


    $('#datepicker').on('change',function(){
        var val = $(this).val()
        var spanVal = val.split('-').reverse().join('/');
        $('.date').text(spanVal)
    })
    commonCPY.select()

})()


// 时间组件综合调用
(function () {
	// var val='23 Mar 2018'
	var a=new Date()
	var month=a.toDateString().slice(4).slice(0,7).split(' ').reverse().join(' ');
	var val=a.toDateString().slice(4).slice(-4);
	var eval=month+' '+val
	$('.tide-datetime').text(eval)
	$('#startEtb').siblings('.datetime-view').text(eval);
	commonCPY.datePicker(['startEtb'], function (value, date, endDate) {
		var value = value[0].split(' ').join('/');
	});
})();