(function () {
    var mySwiper = new Swiper ('.swiper-container', {
        autoplay: 4000,
        autoplayDisableOnInteraction: false,
        loop: true,
        speed: 3000,
        // 如果需要分页器
        pagination: '.swiper-pagination',
        paginationClickable: true
    });
    $('.swiper-button-prev').on('click',function(){
        mySwiper.swipePrev();
    });
    $('.swiper-button-next').on('click',function(){
        mySwiper.swipeNext();
    });

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
    $('.date').text(setNormalDay());



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

    commonCPY.datePicker(['LaycanMonthS'],function(value, date, endDate,id){
    });

    $('#datepicker').on('change',function(){
        var val = $(this).val();
        var spanVal = val.split('-').reverse().join('/');
        $('.date').text(spanVal)
    })
    commonCPY.select();
    configURL = {
        shipUrl:'http://ship8.1chemic.com',      //  船务
        index:'http://www8.1chemic.com',         //  大首页
        shopHall:"http://trade8.1chemic.com",      //   原油大厅
        member:'http://member8.1chemic.com/member',
        sys:'http://member8.1chemic.com/sys',
    }

    var urlStr = window.location.href;

        if(urlStr.indexOf('news9.mycrudeoil.')!=-1){        //uat
          configURL = {
            infoUrl:'//info9.mycrudeoil.com'
          }
        }else if(urlStr.indexOf('news.mycrudeoil.')!=-1){     //线上
          configURL = {
            infoUrl:'//info.mycrudeoil.com'
          }
        }else if(urlStr.indexOf('.test.')!=-1){       //测试环境
          configURL = {
            infoUrl:'//info.test.mycrudeoil.com'
          }
        }else {        //本地localhost
            configURL = {
                infoUrl: '//info.mycrudeoil.com'
            }
        }


        // infoUrl:'http://info.test.mycrudeoil.com',
        // infoUrl:'https://info.mycrudeoil.com',
        //infoUrl:'http://info8.1chemic.com',
//		infoUrl:'http://localhost:8088', // 资讯

//     };



    var getMdata = ''
    function getData(){
        var _self = this;
        return new Promise(function(resolve,reject){
            var channelMData = {
                "channelMDesc":'CPY'
            }
            //请求数据渲染dom
            $.ajax({
                url:configURL.infoUrl + "/channelM/channelMList.json",
                contentType:'application/json;charset=UTF-8',
                type:'POST',
                data:JSON.stringify(channelMData),
                success:function(res){
                    if(res.status==0){
                        getMdata = res.datas;
                        resolve(getMdata)
                    }
                }
            })
        })
    }
    //渲染主频道列表
    function renderChannelM(data){
        var htm = '';
        for(var i=0;i<data.length;i++){
            var path = newsServer +'pages/front/news/index.htm#/'+ data[i].channelMName.split(' ').join('_').toLowerCase();
            var imgPath = appServer+'images/' + data[i].channelMName.split(' ').join('')+'.png';
            if(data[i].channelMName.indexOf('CRUDEOIL')){
                data[i].channelMName = data[i].channelMName.replace('CRUDEOIL','CRUDE OIL');
            }
            htm+=[
                '<a class="fl" href="',path,'" style="background: url(',imgPath,') no-repeat">',data[i].channelMName,'</a>'
            ].join('')
        }
        $('.information-category').html(htm);
    }

    getData().then(function (val) {
        renderChannelM(val)
    }).then(function () {
        for(var i = 0;i<getMdata.length;i++){
            (function (name) {
                getSubChannelData(name).then(function (value) {
                    getSubListData(value)
                })
            })(getMdata[i]['channelMName'])
        }

    })



    //渲染子频道新闻
    function getSubChannelData(name){
        var _self = this;
        return new Promise(function (resolve, reject) {
            var sendData = {
                "channelMDesc":'CPY',
                "channelMName":name
            }
            $.ajax({
                url:configURL.infoUrl + "/channelM/channelSubList.json",
                contentType:'application/json;charset=UTF-8',
                type:'POST',
                data:JSON.stringify(sendData),
                success:function(res){
                    if(res.status==0) {
                        var data = res.datas;
                        if (data[0]) {
                            data[0].channelMName = name;
                            resolve(data[0]);
                        }


                    }
                }
            })
        })
    }
    
    
    function getSubListData(data) {
        var sendData = {
            "channelMDesc":'CPY',
            "channelCode":data.channelCode
        }
        $.ajax({
            url:configURL.infoUrl + "/front/infoListOS.json",
            contentType:'application/json;charset=UTF-8',
            type:'POST',
            data:JSON.stringify(sendData),
            success:function(res){
                if(res.status==0){
                    var datas = res.datas;
                    if(Array.isArray(datas.infoList)&&datas.infoList.length>0){
                        // console.log(data)
                        renderList(datas.infoList[0],data)
                    }

                }
            }
        })
    }
    var li = ''
    function renderList(datas,data){
        var M = data.channelMName.toLowerCase().split(' ').join('_');
        var sub = data.channelCode.toLowerCase().split(' ').join('_');
        var path = newsServer+'pages/front/news/index.htm#/'+M+'/'+sub
        li+=['<li onclick="location.href=',"'",path,"'",'"><div>',datas.title,'</div><p>',datas.tex,'</p></li>'].join('')
        $('.information-list').html(li)
    }


})();





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