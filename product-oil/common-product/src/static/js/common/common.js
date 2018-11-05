;(function(){
    /*input框输入加title*/


    window.commonCPY = {
        // input输入加title
        addtitle:function(){
            $(document).on('input propertychange',function(e){
                $(e.target).attr('title',$(e.target).val())
            })
        },
   // 下拉框
        select:function(){
            $(document).on('click','.select',function(e){
                e.stopPropagation();
                e.preventDefault();
                if($(this).hasClass('s-r')){
                    $(this).removeClass('s-r').addClass('s-b');
                    $(this).find('.select-options').removeClass('open');
                    $(this).find('input').trigger("focus");
                }else if($(this).hasClass('s-b')){
                    //所有的select先关闭
                    $('.select').removeClass('s-r').addClass('s-b');
                    $('.select-options').removeClass('open')
                    //当前的select打开
                    $(this).addClass('s-r').removeClass('s-b');
                    $(this).find('.select-options').addClass('open');
                    //匹配高亮
                    var inputVal = $(this).find('input').val()
                    for(var i=0;i<=$(this).find('.select-options>li').length-1;i++){
                        if(inputVal == $(this).find('.select-options>li').eq(i).data('value')){
                            $(this).find('.select-options>li').removeClass('selected').eq(i).addClass('selected')
                        }
                    }
                }
            });
            $('.cpy-select input').attr('unselectable','on');
            $('.cpy-select input').attr('onfocus','this.blur()');
            $(document).on('click',function(){
                $('.select').removeClass('s-r').addClass('s-b')
                $('.select').find('.select-options').removeClass('open');
            });
            $(document).on('click','.select-options',function(e){
                $(this).siblings('.select-box').find('input').attr('value', '');
                $(this).siblings('.select-box').find('.select-show-text').text('');
                if(e.target.nodeName=='LI'){
                    var text = $(e.target).text();
                    var code = $(e.target).data('value');
                    $(this).closest('.select').next('.error').remove();
                    $(e.target).addClass('selected').siblings().removeClass('selected');
                    $(this).siblings('.select-box').find('.vname').attr('value', text);
                    $(this).siblings('.select-box').find('input').attr('title', text);
                    $(this).siblings('.select-box').find('.vcode').attr('value', code);
                    $(this).siblings('.select-box').find('.select-show-text').text(text);
                    if($(this).parents('.select').hasClass('select-ajax')){
                        var funName = $(this).siblings('.select-box').find('.vcode').attr('fun');
                        if(eval(funName)){
                            eval(funName+'("'+code+'")');
                        }
                    }
                }else{
                    return;
                }

            })
        },
        //下拉框模糊查询
        selectReg: function () {
            var firstFruits = [];
            $('.select').each(function (i, el) {
                firstFruits[i] = [];
            })
            $(document).on('click', '.select', function (e) {
                $(this).find('input').addClass('s-select');
                var oTxt = $(this).find('.select-show-text');
                var oList = $(this).find('.select-options');
                var inputsb = $(this).find('vname');
                var fruits = firstFruits[$('.select').index($(this))];
                var fruitsbox = $(this).find('.select-options>li');
                var dataClass=$(this).find('.select-options').data('class')
                if ($(this).hasClass('select_search')) {
                    oList.css('z-index', 9999)
                    inputsb.focus()
                    oTxt.attr('contentEditable','true');
                    $(this).find('.select-options').scrollTop(0)
                }
                if (fruitsbox.eq(0).hasClass('select-option')) {
                    if(fruits.length>0){
                        fruits=[]
                    }
                    for (var i = 0; i < fruitsbox.length; i++) {
                        fruits.push({code: $(fruitsbox[i]).data('value'), name: $(fruitsbox[i]).html()})
                    }
                    bindEnter($(this), fruits,dataClass);
                }else{
                    bindEnter($(this));
                }
                e.stopPropagation();
                e.preventDefault();
                if ($(this).hasClass('s-r')) {
                    $(this).removeClass('s-r').addClass('s-b');
                    $(this).find('.select-options').removeClass('open');
                    $(this).find('input').trigger("focus");
                } else if ($(this).hasClass('s-b')) {

                    renderFruits(fruits, oList,dataClass);
                    //所有的select先关闭
                    $('.select').removeClass('s-r').addClass('s-b');
                    $('.select-options').removeClass('open')
                    //当前的select打开
                    $(this).addClass('s-r').removeClass('s-b');
                    $(this).find('.select-options').addClass('open');
                    //匹配高亮
                    if($(this).hasClass('select_search')){
                        if($(this).find('input').val().length>0){
                            var inputVal = $(this).find('input').val();
                            for (var i = 0; i <= $(this).find('.select-options>li').length - 1; i++) {
                                if (inputVal == $(this).find('.select-options>li').eq(i).data('value')) {
                                    $(this).find('.select-options>li').removeClass('selected').eq(i).addClass('selected')
                                }
                            }
                        }else{
                            $(this).find('.select-options>li').first().addClass('selected');
                        }
                        $(this).find('.select-show-text').html('');
                        $(this).find('input').attr('value','');
                        $(this).find('.select-show-text').attr('title','');
                    }else{
                        var inputVal = $(this).find('input').val();
                        for (var i = 0; i <= $(this).find('.select-options>li').length - 1; i++) {
                            if (inputVal == $(this).find('.select-options>li').eq(i).data('value')) {
                                $(this).find('.select-options>li').removeClass('selected').eq(i).addClass('selected')
                            }
                        }
                    }

                }
            });
            function bindEnter(that, arr,dataClass) {
                that.on('input', '.select-show-text', function (e) {
                    var showtext=$(this).text()
                    $(this).siblings('.vname').attr('value',showtext)
                    var oList = $(this).closest('.select-box').siblings('.select-options');
                    var keyWord = $(this).text().trim();
                    var fruitList = searchByRegExp(keyWord, arr);
                    renderFruits(fruitList, oList,dataClass);
                })
                var index=1;
                that.on('keyup','.select-show-text',function (evt) {
                    var k = window.event ? evt.keyCode : evt.which;
                    var _this=$(this).closest('.select-box').next('.select-options').find('.selected');
                    var _input=$(this).next('input');
                    var _thisLength=$(this).closest('.select-box').next('.select-options').find('li').length-1
                    var _thisLi=$(this).closest('.select-box').next('.select-options').find('.selected').outerHeight()
                    if (k == 38) {//上箭头
                        if( _this.prev().length>0){
                            index--;
                            _this.prev().addClass("selected");
                            _this.removeClass("selected");

                            $(this).html(_this.prev('li').text());
                            _input.attr('value',_this.prev('li').data('value'))
                        }
                    } else if (k == 40) {//下箭头
                        if(_this.next().length>0){
                            index++;
                            _this.next().addClass("selected");
                            _this.removeClass("selected");
                            $(this).html(_this.next('li').text());
                            _input.attr('value',_this.next('li').data('value'))
                        }
                    }
                    var h=(index-1)/_thisLength*(_thisLi*_thisLength);
                    $(this).closest('.select-box').next('.select-options').scrollTop(h)
                })
            }
            function renderFruits(list, oList,dataClass) {
                if (!(list instanceof Array)) {
                    return;
                }
                oList[0].innerHTML = '';
                var len = list.length;
                var item = null;
                if (len == 0) {
                    item = document.createElement('li');
                    item.innerHTML = '';
                    oList[0].appendChild(item);
                } else {
                    var item = '';
                    for (var i = 0; i < len; i++) {
                        item += '<li class="'+dataClass+'" data-value="' + list[i].code + '" title="' + list[i].name + '" >' + list[i].name + '</li>';
                    }
                    oList.append(item);

                }

            }
            //正则匹配
            function searchByRegExp(keyWord, list) {
                if (!(list instanceof Array)) {
                    return;
                }
                var len = list.length;
                var arr = [];
                var reg = new RegExp(keyWord, 'i');
                for (var i = 0; i < len; i++) {
                    //如果字符串中不包含目标字符会返回-1
                    if (list[i].name.match(reg)) {
                        arr.push({code: list[i].code, name: list[i].name});
                    }
                }
                return arr;
            }
            $(document).on('click', function () {
                $('.s-select').blur();
                $('.select').find('input').removeClass('s-select')
                $('.select').removeClass('s-r').addClass('s-b')
                $('.select').find('.select-options').removeClass('open');
            });
            $(document).on('click','.select-options',function(e){
                $(this).siblings('.select-box').find('input').attr('value', '');
                $(this).siblings('.select-box').find('.select-show-text').text('');
                if(e.target.nodeName=='LI'){
                    var text = $(e.target).text();
                    var code = $(e.target).data('value');
                    $(this).closest('.select').next('.error').remove();
                    $(e.target).addClass('selected').siblings().removeClass('selected');
                    $(this).siblings('.select-box').find('.vname').attr('value', text);
                    $(this).siblings('.select-box').find('.vcode').attr('value', code);
                    $(this).siblings('.select-box').find('input').attr('title', text);
                    $(this).siblings('.select-box').find('.select-show-text').text(text);
                    if($(this).parents('.select').hasClass('select-ajax')){
                        var funName = $(this).siblings('.select-box').find('.vcode').attr('fun');
                        if(eval(funName)){
                            eval(funName+'("'+code+'")');
                        }
                    }
                }else{
                    return;
                }

            })
        },

        picker: function (id, format) {
            var picker,
                format = !!format ? format : 'DD/MM/YYYY';
            return function () {
                var picker = new Pikaday({
                    field: document.getElementById(id),
                    // format: format,
                    toString: function toString(date, format) {
                        var emonth = date.toString().substr(4, 3)
                        var day = date.getDate() < 10 ? '0' + date.getDate() : date.getDate();
                        var month = date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1;
                        var year = date.getFullYear();
                        return day + ' ' + emonth + ' ' + year;
                    }
                });
            }
        },
        // 年月日(ID)
        datePicker: function (idArr, fn) {
            var aaaa = idArr
            if (!Array.isArray(idArr)) {
                    throw new Error('The timePicker parameter format not correct!')
                return;
            }
            layui.use(['laydate'], function () {
                var laydate;
                for (var i = 0; i <= idArr.length - 1; i++) {
                    (function (id) {
                        laydate = layui.laydate;
                        laydate.render({
                            elem: '#' + id,
                            type: 'date',
                            lang: 'en',
                            format: 'dd MM yyyy',
                            btns: ['clear', 'now'],
                            theme: '#F36523',
                            // showBottom: false,
                            done: function (value, date, endDate) {
                                // console.log(value); //得到日期生成的值，如：2017-08-18
                                // console.log(date); //得到日期时间对象：{year: 2017, month: 8, date: 18, hours: 0, minutes: 0, seconds: 0}
                                // console.log(endDate); //得结束的日期时间对象，开启范围选择（range: true）才会返回。对象成员同上。
                                //转月份为英文月份简写
                                var monthArr = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'June', 'July', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec'];
                                var dateCopy = JSON.parse(JSON.stringify(date));
                                dateCopy.month = monthArr[parseInt(dateCopy.month) - 1];
                                var arr = value.split(' ');
                                arr.splice(1, 1, dateCopy.month);
                                var evalue = arr.join(' ');
                                $('#' + id).css({
                                    'opacity': 0
                                })

                                $('#' + id).attr('value', value);
                                $('#' + id).siblings('.datetime-view').text(evalue);
                                $('#' + id).blur();
                                !!fn && fn.call(this, arguments)
                            }
                        });
                    })(idArr[i])
                }
            })
        },
        dateTimePicker: function (idArr, fn) {
            var aaaa = idArr
            if (!Array.isArray(idArr)) {
                throw new Error('The timePicker parameter format not correct!')
                return;
            }
            layui.use(['laydate'], function () {
                var laydate;
                for (var i = 0; i <= idArr.length - 1; i++) {
                    (function (id) {
                        laydate = layui.laydate;
                        laydate.render({
                            elem: '#' + id,
                            type: 'datetime',
                            lang: 'en',
                            format: 'dd MM yyyy HH:mm',
                            theme: '#F36523',
                            closeStop: this,
                            done: function (value, date, endDate) {
                                //转月份为英文月份简写
                                var monthArr = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'June', 'July', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec'];
                                var dateCopy = JSON.parse(JSON.stringify(date));
                                dateCopy.month = monthArr[parseInt(dateCopy.month) - 1];
                                var arr = value.split(' ')
                                arr.splice(1, 1, dateCopy.month);
                                var evalue = arr.join(' ')
                                $('#' + id).css({
                                    'opacity': 0
                                })

                                $('#' + id).attr('value', value);
                                $('#' + id).siblings('.datetime-view').text(evalue)
                                $('#' + id).blur();
                                !!fn && fn.call(this, arguments, id)
                            }
                        });
                    })(idArr[i])
                }
            })
        },
        datePickerClass:function(){
            layui.use('laydate', function () {
                var laydate = layui.laydate;
                $('.DeadlineMonth').each(function () {
                    laydate.render({
                        elem: this,
                        type: 'date',
                        lang: 'en',
                        //value: '10 10 2017',
                        btns: ['clear', 'now'],
                        format: 'dd MM yyyy',
                        theme: '#F36523',
                        done: function done(value, date, endDate) {
                            // /转月份为英文月份简写
                            var monthArr = ['Jan','Feb','Mar','Apr','May','June','July','Aug','Sept','Oct','Nov','Dec'];
                            var dateCopy = JSON.parse(JSON.stringify(date));
                            dateCopy.month = monthArr[parseInt(dateCopy.month)-1];
                            var arr = value.split(' ');
                            arr.splice(1,1,dateCopy.month);
                            var evalue = arr.join(' ');
                            $(this.elem[0]).css({
                                'opacity':0
                            })
                            $(this.elem[0]).attr('value',value);
                            $(this.elem[0]).next().text(evalue)

                        }
                    });
                })
            })
        },
        // 时间验证v
        dateValidate: function () {
            layui.use(['laydate'], function () {
                var laydate;
                var Barr = []
                var Aarr = []
                var index = -1;
                var laydate = layui.laydate;
                    $('.DeadlineMonth').each(function () {
                    laydate.render({
                        elem: this,
                        min:$(this).hasClass('minCurrentTime')?'':'1900-1-1',
                        type: 'date',
                        lang: 'en',
                        btns: ['clear', 'now'],
                        format: 'dd MM yyyy',
                        theme: '#F36523',
                        closeStop: this,
                        done: function done(value, date, endDate) {
                            var classList = $(this)[0].elem[0];
                            if ($(classList).hasClass('CreateStartTime')) {
                                Aarr.push(value)
                                var DeadlineMonth=$(this.elem[0]).closest('.dateParent').siblings('.dateParent').find('.DeadlineMonth')
                                if(DeadlineMonth) {
                                    var val = DeadlineMonth.val()
                                    Barr.push(val)
                                }
                            } else {
                                    Barr.push(value)
                            }
                            //转月份为英文月份简写
                            var monthArr = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'June', 'July', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec'];
                            var dateCopy = JSON.parse(JSON.stringify(date));
                            dateCopy.month = monthArr[parseInt(dateCopy.month) - 1];
                            var arr = value.split(' ');
                            arr.splice(1, 1, dateCopy.month);
                            var evalue = arr.join(' ');
                            $(this.elem[0]).css({
                                'opacity': 0
                            });
                            $(this.elem[0]).attr('value', value)
                            $(this.elem[0]).siblings('.datetime-view').text(evalue)
                            $(this.elem[0]).closest('.dateParent').siblings('.dateParent').find('.error').remove()
                            var parend = $(this.elem[0]).closest('.m-r-span');
                            if (Barr.length > 0 && Aarr.length > 0) {
                                if (!TimeContrast(Aarr[Aarr.length - 1], Barr[Barr.length - 1])) {
                                    var h=$(this.elem[0]).outerHeight()
                                    var w=$(this.elem[0]).outerWidth()
                                    parend.children('.error').remove();
                                    parend.append('<div class="error">Laycan end date must be later than laycan start date.</div>');
                                    parend.children('.error').css({"position":"absolute","top": h, "right": 0 })
                                } else {
                                    parend.children('.error').remove();
                                    parend.children('.error1').remove();
                                }
                            }
                            $(this.elem[0]).blur();
                        }
                    });
                })
            })
        },
        //分秒控件
        timePicker: function (idArr, fn) {
            //使用前必须确定文件引入了layui
            if (!Array.isArray(idArr)) {
                throw new Error('The timePicker parameter format not correct!')
            }
            layui.use(['laydate'], function () {
                var laydate;
                for (var i = 0; i <= idArr.length - 1; i++) {
                    laydate = layui.laydate;
                    laydate.render({
                        elem: '#' + idArr[i],
                        type: 'time',
                        lang: 'en',
                        format: 'HH:mm',
                        btns: ['clear', 'now'],
                        theme: '#F36523',
                        closeStop: this,
                        done: !!fn && fn
                    });
                }
            });
        },
        //updated by Yichen Zhao
        radios:function (fn) {
            $(document).on('click','.check-b .radio',function (e) {
                if(e.target.nodeName=='LABEL'){

                }else{
                    $(this).siblings('.radio').children('i').css("backgroundImage", "url()");
                    $(this).siblings('.radio').children('i').removeAttr('checked');
                    $(this).children('i').css("backgroundImage", "url('../../../images/tick_L.png')");
                    $(this).children('i').attr('checked', 'checked');
                    if($(this).hasClass('demurrage-radio')){
                        if($(this).find('input').attr('checked')==undefined){
                            $(this).find('input').attr('checked','checked');
                            $(this).find('i').css({    'color':' #F36523',
                            'borderColor':' #F36523'});
                        }else{
                            $(this).find('input').removeAttr('checked');
                            $(this).children('i').css("backgroundImage", "url()");
                            $(this).children('i').removeAttr('checked');
                            $(this).find('i').css({    'color':' #FFFFFF',
                                'borderColor':' #E8E8E8'});
                        }
                    }
                }
                !!fn && fn(this,e)
            })
        } ,
        getObjectURL:function (file) {
            var url = null;
            if (window.createObjectURL != undefined) { // basic
                url = window.createObjectURL(file);
            } else if (window.URL != undefined) { // mozilla(firefox)
                url = window.URL.createObjectURL(file);
            } else if (window.webkitURL != undefined) { // webkit or chrome
                url = window.webkitURL.createObjectURL(file);
            }
            return url;
        } ,
        uploadFiles:function (filename, fn) {
            var fileClass, fileValue, _self = this;
            if (Array.isArray(filename)) {
                fileClass = filename.join(',');
            }
            if (Object.prototype.toString.call(filename) == '[Object String]') {
                fileClass = filename;
            }
            $(fileClass).on('change', function (e) {
                e = e || window.event;
                var files = e.target.files || e.dataTransfer.files
                if (!files.length) return;
                fileValue = files[0];
                if (fileValue.size > 5242880) {
                    //上传文件大于5M
                }
                _self.url = _self.getObjectURL(fileValue);
                var Filedata = new FormData();
                Filedata.append('Filedata', fileValue);
                !!fn && fn.call(this, fileValue)
            })
        }
        }
    function indexs(index){
        index++;
        return index
    }
    function TimeContrast(a,b){
        var a=a.split(' ').reverse();
        var b=b.split(' ').reverse();
        var startTime = new Date(a[0], a[1], a[2]);
        var startTimes = startTime.getTime();
        var endTime = new Date(b[0], b[1], b[2]);
        var endTimes = endTime.getTime();
        if (endTimes<startTimes) {
            return false;
        }
        return true;


    }

})();