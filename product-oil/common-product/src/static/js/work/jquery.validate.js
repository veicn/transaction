(function (global, factory) {
    "use strict";
    if (typeof module === "object" && typeof module.exports === "object") {

        module.exports = global.document ?
            factory(global, true) :
            function (w) {
                if (!w.document) {
                    throw new Error("validata requires a window with a document");
                }
                return factory(w);
            };
    } else {
        factory(global);
    }

// Pass this if window is not defined yet
})(typeof window !== "undefined" ? window : this, function (window, noGlobal) {
    // 时间比对
    (function () {
        window.commonCPX = {
            FormValidation: function (FormArr) {
                if (!FormArr.is("form")) return;
                var obj = {FormArr: FormArr};
                var flagV = true;
                // form表单数组对象
                var inputArr = obj.FormArr.find('input');
                var radioArr = obj.FormArr.find(':radio:checked');
                var errorArr = [];
                var date = getNowFormatDate();
                var datetime = getNowFormatDate1();
                var minTimeArr = $('.minCurrentTime');
                var flagtime = true
                var _RULES = {
                    "regexp": function (data) {    //自己匹配正则
                        if (this.val().length > 0) {
                            return new RegExp(data).test(this.val())
                        } else {
                            return true
                        }
                    },
                    "equalto": function (data) {   //确认密码
                        if (this.val().length > 0) {
                            return this.val() == $(data).val()
                        } else {
                            return true
                        }
                    },
                    "requird": function () {   //必填项
                        return this.val()
                    }
                };
                for (var i = 0; i < inputArr.length; i++) {
                    var _this = $(inputArr[i]);
                    _this.siblings('.error').remove();
                    // 正则
                    $.each(_RULES, function (key, fn) {
                        var filaName = _this.data(key);
                        var fileMessage = _this.data(key + "-message");
                        if (filaName) {
                            var result = fn.call(_this, filaName);
                            if (!result) {
                                validateFunc(_this, fileMessage, errorArr)
                            }
                        }
                    })
                    var timeValue = _this.val();
                    /* 先后时间对比*/
                    if (_this.hasClass('validateTime1')) {
                        var time1 = _this.val();
                        var time2 = _this.closest('.dateParent').siblings('.dateParent').find('.validateTime2').val();
                        errorArr.push(TimeContrast(time1, time2));
                        var parend = _this.closest('.dateParent');
                        if (time1.length > 0 && time2.length > 0) {
                            if (!TimeContrast(time1, time2)) {
                                var h = _this.outerHeight();
                                var w = _this.parent('.datetime-wrap').prev().outerWidth(true);
                                parend.children('.error').remove();
                                parend.children('.error1').remove();
                                parend.next('.error').remove();
                                parend.siblings('.dateParent').next('.error').remove();
                                parend.css({"position": "relative"});
                                if (parend.hasClass('laycanTime')) {
                                    parend.append('<div class="error">Laycan end date must be later than laycan start date.</div>');
                                    parend.children('.error').css({"width": 300, "position": "absolute", "top": h,"white-space":"nowrap"})
                                } else if (parend.hasClass('deadlineTime')) {
                                    parend.append('<div class="error">The bid valid to date must be later than expired time.</div>');
                                    parend.children('.error').css({"position": "absolute", "top": h, "left": w,"white-space":"nowrap"})
                                }

                            } else {
                                parend.children('.error').remove();
                                parend.children('.error1').remove();
                            }
                        }
                    }
                    if (_this.attr('type') == 'hidden' && _this.hasClass('validHidden') || inputArr[i].dataset.rules && !_this.is(':hidden')) {
                        var inputValue = inputArr[i].value;
                        var Attrdecabove = _this.attr(':decabove');
                        var AttrdecAfter = _this.attr(':decAfter');
                        var digitsMax = _this.attr(':digitsMax');
                        var e = _this;
                        var rulesArr = inputArr[i].dataset.rules.split(';');
                        rulesArr.push(inputValue);
                        var flag = true;
                        for (var k = 0; k < rulesArr.length - 1; k++) {
                            if (rulesArr[k] == '') {
                                continue;
                            }
                            if (rulesArr[k] !== "required") {
                                if (!validataType(inputValue, rulesArr[k], Attrdecabove, AttrdecAfter, digitsMax)) {
                                    // 错误信息位置控制
                                    var fileMessage = valiMessage(inputValue, rulesArr[k], Attrdecabove, AttrdecAfter, digitsMax);
                                    validateFunc(e, fileMessage, errorArr);
                                    break
                                }
                            } else {
                                if (!validataType(inputValue, rulesArr[k])) {
                                    var fileMessage = valiMessage(inputValue, rulesArr[k], Attrdecabove, AttrdecAfter, digitsMax);
                                    validateFunc(e, fileMessage, errorArr);
                                    break
                                } else {

                                }
                            }
                        }

                        if (flag) {
                            errorArr.push(flag);
                            var $b = _this;
                            if (!$b.closest('.laycanTime')) {
                                $b.siblings('.error').remove();
                                $b.siblings('.error-after').remove();
                            }

                        }
                    }
                }
                /*页面回写时间不能小于当前时间*/
                for (var i = 0; i < minTimeArr.length - 1; i++) {
                    var parend = $(minTimeArr[i]).closest('.dateParent');
                    var timeValue = $(minTimeArr[i]).val();
                    if (TimeContrast(datetime, timeValue) && TimeContrast(date, timeValue)) {
                        flagtime = true
                    } else {
                        flagtime = false
                    }
                    errorArr.push(flagtime);
                    if (!TimeContrast(datetime, timeValue) || !TimeContrast(date, timeValue)) {
                        var h = $(minTimeArr[i]).outerHeight();
                        var w = $(minTimeArr[i]).parent('.datetime-wrap').prev().outerWidth(true);
                        parend.children('.error1').remove();
                        parend.append('<div class="error1">This time must be greater than the current time.</div>');
                        parend.css({"position": "relative"});
                        if (parend.hasClass('laycanTime')) {
                            parend.children('.error1').css({"width": 300, "position": "absolute", "top": h,"white-space":"nowrap"})
                        } else {
                            parend.children('.error1').css({"position": "absolute", "top": h, "left": w,"white-space":"nowrap"})
                        }

                    } else {
                        parend.children('.error1').remove();
                    }
                }
                if ((errorArr.indexOf(false) == -1)) {
                    flagV = false;
                } else {
                    flagV = true;
                }
                return flagV
            },
        }
    })();
    // 验证：正则     字体颜色   类名提交优化

    $(document).on('blur', 'input', function () {
        var e = $(this);
        validfn(e);
    });
    $(document).on('blur', 'textarea', function () {
        var e = $(this);
        validfn(e);
    });

    function validfn(e) {
        // 正则
        var _RULES = {
            "regexp": function (data) {    //自己匹配正则
                if (this.val().length > 0) {
                    return new RegExp(data).test(this.val())
                } else {
                    return true
                }
            },
            "equalto": function (data) {   //确认密码
                if (this.val().length > 0) {
                    return this.val() == $(data).val()
                } else {
                    return true
                }
            },
            "requird": function () {   //必填项
                return this.val()
            }
        };
        $.each(_RULES, function (key, fn) {
            var filaName = e.data(key);
            var fileMessage = e.data(key + "-message");
            if (filaName) {
                var result = fn.call(e, filaName);
                e.siblings('.error').remove();
                if (!result) {
                    validateFunc(e, fileMessage)
                }
            }
        });
        var value = e.val().trim(); //去空
        if (e.attr('type') == 'hidden' && e.hasClass('validHidden ') || e.attr('data-rules') && !e.is(':hidden')) {
            if (e.attr('data-rules').indexOf(';') == -1) {
                validata = [e.attr('data-rules')]
            } else {
                var validata = e.attr('data-rules').split(';'); //类型和提示
            }
            var flag = true;
            var arr=[]
            for (var i = 0; i < validata.length; i++) {
                var Attrdecabove = e.attr(':decabove');
                var Attrlength = e.attr(':length');
                var AttrdecAfter = e.attr(':decAfter');
                var digitsMax = e.attr(':digitsMax');
                if (validata[i] != "required") {
                    if (!validataType(value, validata[i], Attrdecabove, AttrdecAfter, digitsMax, Attrlength)) {
                        // 错误信息位置控制
                        e.siblings('.error').remove();
                        var fileMessage = valiMessage(value, validata[i], Attrdecabove, AttrdecAfter, digitsMax, Attrlength);
                        validateFunc(e, fileMessage,arr);
                        flag = false;
                        arr.push(flag)
                        break
                    }
                } else {
                    if (!validataType(value, validata[i])) {
                        e.siblings('.error').remove();
                        var fileMessage = valiMessage(value, validata[i]);
                        validateFunc(e, fileMessage,arr);
                        flag = false;
                        arr.push(flag)
                        break
                    }
                }
            }
            if (flag) {
                e.siblings('.error-after').remove();
                e.siblings('.error').remove();
                e.siblings('.error1').remove();
                e.closest('.my-select').siblings('.error').remove();
                e.closest('.select_search').siblings('.error').remove();
            }
        }
    }

    // 验证结构判断
    function validateFunc(_this, fileMessage, errorArr) {
        if (_this.closest('.select').length >= 1) {
            var selectError = _this.closest('.select')
            var h = selectError.outerHeight();
            var etop = h + 'px';
            var eleft = 0;
            selectError.siblings('.error').remove();
            $('<div>').addClass('error').html(fileMessage).insertAfter(selectError);
            selectError.next().css({
                "position": "absolute",
                "top": etop,
                "left": eleft,"white-space":"nowrap"
            });
            flag = false;
            if(errorArr){
                errorArr.push(flag)
            }
        } else if (_this.hasClass('DeadlineMonth') || _this.hasClass('DeadlineMonth2')) {
            var parent = _this.closest('.datetime-wrap');
            if(_this.closest('.dateParent').hasClass('laycanTime')){
                parent.children('.error1').remove();
                var h = _this.outerHeight();
                var etop = h + 'px';
                var eleft = 0;
                $('<div>').addClass('error1').html(fileMessage).appendTo(_this.closest('.dateParent'));
                // _this.closest('.dateParent').next().css({"position":"absolute","top": etop, "left": eleft,"white-space":"nowrap"});
            }else if(!_this.closest('.dateParent').hasClass('laycanTime')){
                var parent = _this.closest('.datetime-wrap');
                var h = parent.outerHeight();
                var etop = h + 'px';
                var eleft = 154 + 'px';
                parent.siblings('.error').remove();
                parent.children('.error').remove();
                _this.siblings('.error').remove();
                $('<div>').addClass('error').html(fileMessage).insertAfter(parent);
                parent.next().css({
                    "position": "absolute", 'top': etop,
                    'left': eleft,"white-space":"nowrap"
            });
        }
            flag = false;
            if(errorArr){
                errorArr.push(flag)
            }
        } else if (_this.hasClass('upload')) {
            var etop = 36 + 'px';
            var eleft = 155 + 'px';
            _this.siblings('.error').remove();
            $('<div>').addClass('error').html(fileMessage).insertAfter(_this);
            _this.next().css({
                "position": "absolute", 'top': etop,
                'left': eleft,"white-space":"nowrap"
            });
            flag = false;
            if(errorArr){
                errorArr.push(flag)
            }
        } else {
            var h = _this.outerHeight();
            var etop = _this.offset().top + h + 'px';
            var eleft = _this.offset().left;
            var sleft = _this.position().left;
            _this.siblings('.error').remove()
            $('<div>').addClass('error').html(fileMessage).insertAfter(_this);
            if(_this.hasClass('abs-class')){
                _this.next().css({
                    "position": "absolute","top":etop,
                    'left': eleft,"white-space":"nowrap"
                });
            }else if(_this.hasClass('abs-class-rea')){
                _this.next().css({
                    "left":sleft,
                    "position": "absolute","white-space":"nowrap"
                });
            }else{
                _this.next().offset({"top": etop, "left": eleft,"white-space":"nowrap"});
            }
            flag = false;
            if(errorArr){
                errorArr.push(flag)
            }
        }

    };

    // 验证规则
    function validataType(value, tips, above, After, digitsMax, length) {
        switch (tips) {
            case 'string':
                if (value.length > length) {
                    return false;
                } else {
                    return true;
                }
                break;
            case 'digits':   //整数
                if (/^(0|[1-9][0-9]*|-[1-9][0-9]*)$/.test(value)) {
                    if (value.length > digitsMax) {
                        return false;
                    } else {
                        return true;
                    }
                }
                return (/^-?\d+$/.test(value)
                );
                break;
            case 'upload':  //上传必填项
                return (!value.length == 0);
                break;
            case 'required':  //必填项
                return (!value.length == 0);
                break;
            case 'email':
                return (/^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$/.test(value)
                );
                break;
            case 'tell':
                // 要求 1.如果存在'+'号，必须在第一位
                // 2.如果存在 '(' ，必须存在 ')'
                // 3.'-'可以存在任意位置，但不能是第一位和最后一位
                // 4.可以存在空格
                // 5.必须有数字
                // 6.长度不限
                // 7.出现其他符号不能通过验证
                return (/^\s*\+?\s*(\(\s*\d+\s*\)|\d+)(\s*-?\s*(\(\s*\d+\s*\)|\s*\d+\s*))*\s*$/.test(value)
                );
                break;
            case 'decimal':  //小数
            function decimal(above, After) {
                var max1 = After;
                var above1 = above;

                function inc() {
                    var returnVal = true;
                    var inputZ = value;
                    var ArrMen = inputZ.split(".");    //截取字符串
                    if (/^(-?\d+)(\.\d+)?$/.test(value)) {
                        if (ArrMen.length == 2) {
                            if (ArrMen[1].length > max1 || ArrMen[1].length == 0 || ArrMen[0].length > above1) {    //判断小数点后面的字符串长度
                                returnVal = false;
                                return false;
                            }
                        } else if (ArrMen.length == 1) {
                            var a = parseInt(above1) - 1;
                            if (ArrMen[0].length > a) {    //判断小数点后面的字符串长度
                                returnVal = false;
                                return false;
                            }
                        }
                        return returnVal;
                    }

                }

                return inc();
            }

                return decimal(above, After);
                break;
        }
    }

// 错误信息
    function valiMessage(value, tips, above, After, digitsMax, length) {
        switch (tips) {
            case 'string':
                if (value.length > length) {
                    var s = 'Please input no more than ' + length + ' bytes';
                    return s;
                }
                break;
            case 'digits':   //整数
                if (digitsMax) {
                    if (!(/^(0|[1-9][0-9]*|-[1-9][0-9]*)$/.test(value))) {
                        var a = 'Please input integer.';
                        return a
                    } else {
                        var a = 'The number you input should be less than ' + digitsMax + ' places';
                        return a
                    }
                } else {
                    return ('Please input number.');
                    break;
                }
            case 'upload':  //上传必填项
                return ('Please upload file.');
                break;
            case 'required':  //必填项
                return ('mandatory');
                break;
            case 'email':
                return ('Please input valid email address.');
                break;
            case 'tell':  //不识别（）+ -数字以外的事//电话
                return ('Please input valid phone number.');
                break;
            case 'decimal':  //浮点数
                if (!(/^[+-]?(0|([1-9]\d*))(\.\d+)?$/.test(value))) {
                    var a = 'Please input number.';
                    return a
                } else {
                    if (After && above == undefined) {
                        var after1 = 'Correct to ' + After + ' decimal places.';
                        return after1;
                    } else if (above && After == undefined) {
                        if (value)
                            var above = 'The number you input should be less than ' + above + 'places';
                        return above;
                    } else if (above && After) {
                        var s = value.split('.');
                        if (s.length == 1) {
                            if (s[0].length > above) {
                                var above1 = 'The integer part should be less than ' + above + ' places';
                                return above1;
                            }
                        } else {
                            if (s[0].length > above && s.length == 1 || s[1].length < After) {
                                var above1 = 'The integer part should be less than ' + above + ' places';
                                return above1;
                            } else if (s[0].lengthabove && s.length == 1 || s[1].length > After) {
                                var above2 = 'Correct to' + After + 'decimal places';
                                return above2;
                            } else {
                                var above3 = 'The integer part should be less than' + above + 'places and correct to ' + After + ' decimal places.';
                                return above3;
                            }
                        }


                    }
                }
                break;
        }
    }

    // 时间对比
    function TimeContrast(a, b) {
        if (a && b) {
            var a = a.split(' ').reverse();
            var b = b.split(' ').reverse();

            if (a.length > 3 && b.length > 3 && a.length == b.length) {
                var a1 = a[0].split(':');
                var b1 = b[0].split(':');
                var startTimes=parseInt(a[1]+a[2]+ a[3]+a1[0]+ a1[1]);
                var endTimes=parseInt(b[1]+b[2]+ b[3]+b1[0]+ b1[1]);
                // var startTime = new Date(a[1], a[2], a[3], a1[0], a1[1]);
                // var startTimes = startTime.getTime();
                // var endTime = new Date(b[1], b[2], b[3], b1[0], b1[1]);
                // var endTimes = endTime.getTime();
                if (endTimes < startTimes) {
                    return false;
                }
                return true;
            }
            // var startTime = new Date(a[0], a[1], a[2]);
            // var startTimes = startTime.getTime();
           // var startTimes = a[0]+a[1]+ a[2]
            // var endTime = new Date(b[0], b[1], b[2]);
            // var endTimes = endTime.getTime();
            var startTimes=parseInt(a[0]+a[1]+ a[2]);
            var endTimes = parseInt(b[0]+b[1]+ b[2]);
            if (endTimes < startTimes) {
                return false;
            }
            return true;

        }
        return true;

    }

    // 取当前时间2018-04-23
    function getNowFormatDate() {
        var date = new Date();
        var seperator1 = " ";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = strDate + seperator1 + month + seperator1 + date.getFullYear();
        return currentdate;
    }

// js获取当前日期时间“yyyy-MM-dd HH:MM:SS”
    function getNowFormatDate1() {
        var date = new Date();
        var seperator1 = " ";
        var seperator2 = ":";
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = strDate + seperator1 + month + seperator1 + date.getFullYear()
            + " " + date.getHours() + seperator2 + date.getMinutes();
        return currentdate;
    }
})


