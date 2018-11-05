/*
 //时间组件综合调用
 commonCPY.dateTimePicker(['LaycanMonthS', 'firstLineEtb','LaycanMonthE', 'startEta', 'startEtb', 'VoyageStart', 'CreateStartTime', 'NorTime', 'CreateEndTime', 'AnchorTime', 'startTime', 'startTime1', 'FirstTime', 'startTime2', 'DateTime', 'IndTime', 'DaTime2', 'ShoreTime', 'startTime3', 'endTime3', 'tableTimes', 'tableTimee', 'comTime', 'ShoreArmTime', 'CargoTime', 'LtrTime', 'DateTime4'], function (value, date, endDate) {
 var value = value[0].split(' ').join('/');
 //console.log(value);
 });*/
commonCPY.dateTimePicker(['LaycanMonthS', 'firstLineEtb', 'NorTime', 'AnchorTime', 'LaycanMonthE', 'startEta', 'startEtb', 'VoyageStart', 'startTime', 'startTime1', 'FirstTime', 'startTime2', 'DateTime', 'IndTime', 'DaTime2', 'ShoreTime', 'startTime3', 'endTime3', 'tableTimes', 'tableTimee', 'comTime', 'ShoreArmTime', 'CargoTime', 'LtrTime', 'DateTime4'], function (value, date, endDate) {
    var value = value[0].split(' ').join('/');
});
commonCPY.datePicker(['CreateStartTime', 'CreateEndTime'], function (value, date, endDate) {
    var value = value[0].split(' ').join('/');
});

//数据回写

(function () {
    $('.voyage-table>tbody').empty();
    if (selectSea != null && selectSea.trim().length > 0) {
        var selectvos = eval("(" + selectSea + ")");//下拉框数据
        var progressaa = '';//第三页数据
        var selects = '';
        var arr = '';
        selectvos.forEach(function (ele) {
            selects += '<li data-value="' + ele.code + '">' + ele.enName + '</li>';
        });
    }


    if (progress != 'undefined' && progress != '' && progress != '${progress}') {
        progressaa = eval("(" + progress + ")");//第三页数据
        threePage();
    }

    if (object != "undefined" && object != '' && object != "${loadPortList}") {
        arr = eval("(" + object + ")");
        Echo()
    }


    /*
     if(progressaa=='undefined'&&arr=='undefined'||progressaa==''&&arr==''){
     progressaa==''&&arr=='undefined'
     }else if(arr!='undefined'&&arr!=''||){
     //第三页
     threePage()
     }else(progressaa||arr){
     Echo2()
     }*/

    function convertEnglishTime(str){
        var sign = " ";
        var array = str.split(sign);
        console.log(array);
        var monthArr = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'June', 'July', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec'];
        var month = parseInt(array[1]);
        return array[0] + sign + monthArr[month-1] + sign + array[2]
    }

    //会 第一页
    function Echo() {

        layui.use('laydate', function () {
            var laydate = layui.laydate;
            var laydate1 = layui.laydate;
            var laydate2 = layui.laydate;
            console.log(laydate)

            arr.forEach((ele) => {
                //随机生成id
                var id = 'c' + ele.uuid;
                var date = ele;
                var selvalue = ''
                for (var i = 0; i < selectvos.length; i++) {
                    if (selectvos[i].code == date.sea)
                        selvalue = selectvos[i].enName;
                }
                //添加dom
                //转月份为英文月份简写
                $('.voyage-table>tbody').append(`
					 <tr>
			 <td class="pd0">
			 <div class="datetime-wrap">
			 <input name="date" id="${id}"  value="${date.date}" class="DeadlineMonth DeadlineMonth2 datetime" type="text" style="width: 127px;" title="${date.date}">
			 <div class="datetime-view datetime-view-vl"  >${date.date}</div>
			 </div>
			 </td>
			 <td class="loadingPort pd0"><input type="text" name="loadingPort" title="${date.loadingPort}"  value="${date.loadingPort}"></td>
			 <td class="pd0">
			 <div class="datetime-wrap">
			 <input name="eta" id="${id + 'a'}" value="${date.eta}"  title="${date.eta}" class="DeadlineMonth  DeadlineMonth2 datetime" type="text" style="width: 127px;">
			 <div class="datetime-view datetime-view-vl">${date.eta}</div>
			 </div>
			 </td>
			 <td class="pd0">
			 <div class="datetime-wrap">
			 <input name="etb" id="${id + 'b'}" value="${date.etb}" class="DeadlineMonth  DeadlineMonth2 datetime" type="text" title="${date.etb}" style="width: 127px;">
			 <div class="datetime-view datetime-view-vl"  >${date.etb}</div>
			 </div>
			 </td>
			 <td class="posntxt-td loadingPort pd0">
			 <input type="text" name="posn"  value="${date.posn}" title="${date.posn}"/>
			 </td>
			 <td class="td-select vo-sel-td ">
			 <div class="my-select cpy-select h-select vo-select clearfix ">
			 <div class="select s-b">
			 <div class="select-box">
			 <div class="select-content">
			 <div class="select-show-text" >${date.sea}</div>
			 <input type="text" name="sea" class="vname"  readonly="readonly" value="${date.sea}" title="${selvalue}">
			 <input type="hidden" name="seaId" class="vcode"  readonly="readonly" value="${date.seaId}" title="${selvalue}">
			 
			 
			 </div>
			 </div>
			 <ul class="select-options">
			 ${selects}
			 </ul>
			 </div>
			 
			 </div>
			 </td>
			 <td class="loadingPort pd0"><input type="text" name="aveSpd24h" maxlength="6"  value="${date.aveSpd24h}" title="${date.aveSpd24h}" ></td>
			 <td class="delImg pd0"><img src="/images/delete-icon.png"/></td>
			 </tr>
			 `)
            });


            $('.DeadlineMonth2').each(function(){

                var time = this.value;
                console.log(time);
                $(this).next().text(convertEnglishTime(time));
            })


            $('.DeadlineMonth2').each(function () {
                laydate.render({
                    elem: this,
                    type: 'date',
                    lang: 'en',
                    //value: '10 10 2017',
                    btns: ['clear', 'now'],
                    format: 'dd MM yyyy',
                    theme: '#F36523',
                    done: function done(value, date, endDate) {
                        var monthArr = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'June', 'July', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec'];
                        var dateCopy = JSON.parse(JSON.stringify(date));
                        dateCopy.month = monthArr[parseInt(dateCopy.month) - 1];
                        var arr = value.split(' ');
                        arr.splice(1, 1, dateCopy.month);
                        var evalue = arr.join(' ');
                        //console.log(id);
                        $(this.elem[0]).css({
                            'opacity': 0
                        });
                        $(this.elem[0]).attr('value', value);
                        $(this.elem[0]).next().text(evalue);
                    }
                });
            })
            // $('.DeadlineMonth2').each(function () {
            //
            //     laydate.render({
            //         elem: this,
            //         type: 'date',
            //         lang: 'en',
            //         //value: '10 10 2017',
            //         btns: ['clear', 'now'],
            //         format: 'dd MM yyyy',
            //         theme: '#F36523',
            //         done: function done(value, date, endDate) {
            //
            //
            //             var monthArr = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'June', 'July', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec'];
            //             var dateCopy = JSON.parse(JSON.stringify(date));
            //             dateCopy.month = monthArr[parseInt(dateCopy.month) - 1];
            //             var arr = value.split(' ');
            //             arr.splice(1, 1, dateCopy.month);
            //             var evalue = arr.join(' ');
            //             $('#' + id).attr('value', value);
            //             $('#' + id).siblings('.datetime-view').text(evalue);
            //             // console.log(this, value, date, endDate)
            //             $(this.elem[0]).val(value)
            //             $(this.elem[0]).next().text(evalue)
            //         }
            //     });
            // })
        });

    }

    // 第三页
    function threePage() {

        //回写第三页
        //实例化layui的date组件对象

        layui.use('laydate', function () {
            var laydate = layui.laydate;
            progressaa.forEach((ele) => {
                var date = ele;
                var buffer = new Array;
                buffer.push("<tr>");

                buffer.push("<td class=\"time1 vl-loa-l\">");
                buffer.push("<div class=\"datetime-wrap\"> ");
                buffer.push("<input name=\"datetime\" value=\"" + date.datetime + "\"" +
                    "class=\" DeadlineMonth1 datetime\" type=\"text\" >");
                buffer.push("<div class=\"datetime-view\" style='font-size:10px' >" + date.datetime + "</div>");
                buffer.push("</div>");
                buffer.push("</td>");

                buffer.push("<td class=\"cargo\">");
                buffer.push(" <input type=\"text\" value=\"" + date.cargoLoaded + "\"" +
                    " name=\"cargoLoaded\" class=\"br\"> ");
                buffer.push("</td>");

                buffer.push(" <td class=\"cargo\">");
                buffer.push(" <input type=\"text\" value=\"" + date.theCargoToBeLoaded + "\"" +
                    "name=\"theCargoToBeLoaded\" class=\"br\">");
                buffer.push("</td>");

                buffer.push("<td class=\"cargo\">");
                buffer.push(" <input type=\"text\" value=\"" + date.loadingSpeed + "\"" + "name=\"loadingSpeed\" class=\"br\">");
                buffer.push("</td>");

                buffer.push(" <td class=\"time3 vl-loa-l\">");
                buffer.push("<div class=\"datetime-wrap\">");
                buffer.push(" <input name=\"etc\"  class=\" DeadlineMonth1 datetime\" value=\"" + date.etc + "\"" + "type=\"text\" >");
                buffer.push("<div class=\"datetime-view\" style='font-size:10px'>" + date.etc + "</div>");
                buffer.push("</div></td>");

                buffer.push(" <td class=\"time2\">");
                buffer.push("<a href=\"javascript:;\"> ");
                buffer.push("<i class=\"layui-icon\">");
                buffer.push("<img src=\"/images/delete-icon.png\"></i>");
                buffer.push("</a>");
                buffer.push("</td>");
                buffer.push("</tr>");
                var dom = buffer.join("");
                $('.layui-table>tbody').append(dom);

                // //添加dom
                // $('.layui-table>tbody').append(`
                //
                // 		'\n        <tr>\n                                                    <td class="time1 vl-loa-l">\n                                                        <div class="datetime-wrap">\n                                                            <input name="datetime" value="${date.datetime}" class=" DeadlineMonth1 datetime" type="text" >\n                                                            <div class="datetime-view">${date.datetime}</div>\n                                                        </div>\n                                                    </td>\n                                                    <td class="cargo">\n                                                        <input type="text" value="${date.cargoLoaded}" name="cargoLoaded" class="br">\n                                                    </td>\n                                                    <td class="cargo">\n                                                        <input type="text" value="${date.theCargoToBeLoaded}" name="theCargoToBeLoaded" class="br">\n                                                    </td>\n                                                    <td class="cargo">\n                                                        <input type="text" value="${date.loadingSpeed}" name="loadingSpeed" class="br">\n                                                    </td>\n                                                    <td class="time3 vl-loa-l">\n                                                        <div class="datetime-wrap">\n                                                            <input name="etc"  class=" DeadlineMonth1 datetime" value="${date.etc}" type="text" >\n                                                            <div class="datetime-view">${date.etc}</div>\n                                                        </div>\n                                                    </td>\n                                                    <td class="time2">\n                                                        <a href="javascript:;">\n                                                            <i class="layui-icon"><img src="/images/delete-icon.png"></i>\n                                                        </a>\n                                                    </td>\n                                                </tr>');
                // `)

                $('.DeadlineMonth1').each(function () {
                    laydate.render({
                        elem: this,
                        type: 'datetime',
                        lang: 'en',
                        //value: '10 10 2017 00:00',
                        format: 'dd MM yyyy HH:mm',
                        theme: '#F36523',

                        closeStop: this,

                        done: function done(value, date, endDate) {
                            console.log(this, value, date, endDate)
                            $(this.elem[0]).val(value)
                            $(this.elem[0]).next().text(value)
                        }
                    });
                })


            });

        });
    }


})();


// 时间

//    第一分页点击添加表格行
(function () {
    var obj1 = {
        dom: [],
        ids: [''],
        date: []
    };
    //随机生成id
    function ran1() {
        var id = 'bbb' + new Date().getTime();
        obj1.ids.push(id);
    }

    //添加dom
    function dom1() {
        var arr = [];
        $.ajax({
            contentType: 'application/json',
            async: false,
            type: "post",
            dataType: "json",
            traditional: true,
            url: shippingServer + '/pages/back/LoadPort/querySelect.json',
            success: function (msg) {
                arr = msg.selectSea;
            },
            error: function () {
                alert("系统异常")
            }
        })
        var htm = '';
        arr.forEach(function (ele) {
            htm += '<li data-value="' + ele.code + '">' + ele.enName + '</li>';
        });
        var buffer  = new Array();
        buffer.push("<tr>");
        buffer.push("<td class=\"pd0\">");
        buffer.push("<div class=\"datetime-wrap\">");
        buffer.push("<input name=\"date\" id=\"" + obj1.ids[obj1.ids.length - 1] + "c\""  +
    "class=\"DeadlineMonth  DeadlineMonth3 datetime\" type=\"text\" style=\"width: 127px;\" >");
        buffer.push("<div class=\"datetime-view datetime-view-vl\">");
        buffer.push("</div></div></td>");
        buffer.push("<td class=\"loadingPort pd0\">");
        buffer.push(" <input type=\"text\" name=\"loadingPort\" value=\"\">");
        buffer.push("</td>");
        buffer.push("<td class=\"pd0\">");
        buffer.push("<div class=\"datetime-wrap\"> ");
        buffer.push("<input name=\"eta\" id=\"" + obj1.ids[obj1.ids.length - 1] + "b\""  +
        "class=\"DeadlineMonth  DeadlineMonth3 datetime\" type=\"text\" style=\"width: 127px;\" >");
        buffer.push("<div class=\"datetime-view datetime-view-vl\">");
        buffer.push("</div></div></td>");
        buffer.push("<td class=\"pd0\">");
        buffer.push("<div class=\"datetime-wrap\">");
        buffer.push("<input name=\"etb\" id=\"" + obj1.ids[obj1.ids.length - 1] + "\"" +
        "class=\"DeadlineMonth  DeadlineMonth3 datetime\" type=\"text\" style=\"width: 127px;\" >");

        buffer.push("<div class=\"datetime-view datetime-view-vl\">");
        buffer.push("</div> </div> </td>");
        buffer.push(" <td class=\"posntxt-td loadingPort pd0\">");
        buffer.push("<div class=\"posntxt\">");
        buffer.push("<input type=\"text\" name=\"posn\"  value=\"\">");
        buffer.push("</div></td>");
        buffer.push("<td class=\"td-select vo-sel-td\">");
        buffer.push("<div class=\"my-select cpy-select h-select vo-select clearfix\">");
        buffer.push(" <div class=\"select s-b\"><div class=\"select-box\"><div class=\"select-content\"> ");
        buffer.push("<div class=\"select-show-text\"></div>");
        buffer.push(" <input type=\"text\" readonly=\"readonly\" class=\"vname\" name=\"sea\"   value=\"\">");
        buffer.push("<input type=\"hidden\" readonly=\"readonly\" class=\"vcode\" name=\"seaId\"   value=\"\">");
        buffer.push(" </div></div>");
        buffer.push("<ul class=\"select-options\">" + htm + "</ul>");
        buffer.push("</div> </div> </td>");
        buffer.push("<td class=\"loadingPort pd0\">");
        buffer.push("<input type=\"text\" name=\"aveSpd24h\"  maxlength=\"6\"  value=\"\">");
        buffer.push("</td> <td class=\"delImg pd0\"><img src=\"/images/delete-icon.png\"></td></tr>");


var strDom = buffer.join("").toString();

obj1.dom.push(strDom);
        // obj1.dom.push('\n        <tr>\n                                <td class="pd0">\n                                    <div class="datetime-wrap">\n                                        <input name="date" id="' + (obj1.ids[obj1.ids.length - 1] + 'c') + '" class="DeadlineMonth  DeadlineMonth3 datetime" type="text" style="width: 127px;" >\n                                        <div class="datetime-view datetime-view-vl"></div>\n                                    </div>\n                                </td>\n                                <td class="loadingPort pd0"><input type="text" name="loadingPort"   value=""></td>\n                                <td class="pd0">\n                                    <div class="datetime-wrap">\n                                        <input name="eta" id="' + (obj1.ids[obj1.ids.length - 1] + 'b') + '" class="DeadlineMonth  DeadlineMonth3 datetime" type="text" style="width: 127px;" >\n                                        <div class="datetime-view datetime-view-vl"></div>\n                                    </div>\n                                </td>\n                                <td class="pd0">\n                                    <div class="datetime-wrap">\n                                        <input name="etb" id="' + obj1.ids[obj1.ids.length - 1] + '" class="DeadlineMonth  DeadlineMonth3 datetime" type="text" style="width: 127px;" >\n                                        <div class="datetime-view datetime-view-vl"></div>\n                                    </div>\n                                </td>\n                                <td class="posntxt-td loadingPort pd0">\n                                    <div class="posntxt"><input type="text" name="posn"  value=""></div>\n                                </td>\n                                <td class="td-select vo-sel-td ">\n                                    <div class="my-select cpy-select h-select vo-select clearfix">\n                                        <div class="select s-b">\n                                            <div class="select-box">\n                                                <div class="select-content">\n                                                    <div class="select-show-text"></div>\n                                                    <input type="text" readonly="readonly" class="vname" name="sea"   value="">\n       <input type="hidden" readonly="readonly" class="vcode" name="seaId"   value="">\n                                             </div>\n                                            </div>\n                                            <ul class="select-options">\n                         ' + htm + '                                      </ul>\n                                        </div>\n\n                                    </div>\n                                </td>\n                                <td class="loadingPort pd0"><input type="text" name="aveSpd24h"  maxlength="6"  value=""></td>\n                                <td class="delImg pd0"><img src="/images/delete-icon.png"></td>\n                            </tr>');
        $('.con_one_1').find('tbody').append(obj1.dom[obj1.dom.length - 1]);
    }

    function layDate(idLen) {
        var laydate = layui.laydate
        obj1.date.push(layui.laydate);

    }

    //实例化layui的date组件对象
    // laydate=null
    //点击事件
    //监听dom数组的长度
    //监听dom数组的长度
    var domLen = obj1.dom.length;
    var idLen = obj1.ids.length;
    var dateLen = obj1.date.length;

    $(document).on('click', '.startAdd', function () {
        ran1(idLen);
        dom1(domLen);
        var _self = this;
        layui.use('laydate', function () {
            layDate();
            var laydate = layui.laydate;

            $('.DeadlineMonth3').each(function () {
                laydate.render({
                    elem: this,
                    type: 'date',
                    lang: 'en',
                    //value: '10 10 2017',
                    btns: ['clear', 'now'],
                    format: 'dd MM yyyy',
                    theme: '#F36523',
                    done: function done(value, date, endDate) {
                        var monthArr = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'June', 'July', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec'];
                        var dateCopy = JSON.parse(JSON.stringify(date));
                        dateCopy.month = monthArr[parseInt(dateCopy.month) - 1];
                        var arr = value.split(' ');
                        arr.splice(1, 1, dateCopy.month);
                        var evalue = arr.join(' ');
                        //console.log(id);
                        $(this.elem[0]).css({
                            'opacity': 0
                        });
                        $(this.elem[0]).attr('value', value);
                        $(this.elem[0]).next().text(evalue);
                    }
                });
            })


        });
    });
    $(document).on('click', '.delImg>img', function () {
        this.closest('tr').remove();
    });
})()
//    点击删除
$(function () {
    $(document).on('click', '.time2>a>i', function () {
        //console.log($(this));
        $(this).closest('tr').remove();
    });
});
//    2个切换
(function () {
    // 圆圈点击切换
    $('.vl-nav-l .stowage-steps li').click(function () {
        var index = $(this).index();
        // console.log(index)
        if (index == 2) {
        } else {
            $('.lib_voyabox>div').eq(index).show().siblings().hide();
            $('.lib_tabox>ul>li').eq(index).addClass('active').siblings().removeClass('active');
        }

    });
    $('.lib_tabox ul li').click(function () {
        var index = $(this).index();
        $(this).addClass('active').siblings().removeClass('active');
        $('.lib_voyabox>div').eq(index).show().siblings().hide();
    });


    // 4个切换
    $('.lib_Menubox ul li').click(function () {
        var index = $(this).index();
        //console.log(index)
        $(this).addClass('active').siblings().removeClass('active');
        $('.lib_Contentbox>div').eq(index).show().siblings().hide();
    });
    $("a:contains('Next Step')").click(function () {
        var index = $(this).closest('.con_one').attr('class').slice(0, 9).slice(-1);
        if (index == 4) {
            $('.lib_Menubox>ul>li').eq(0).addClass('active').siblings().removeClass('active');
            $('.lib_Contentbox>div').eq(0).show().siblings().hide();
        }
        $('.lib_Menubox>ul>li').eq(index).addClass('active').siblings().removeClass('active');
        $('.lib_Contentbox>div').eq(index).show().siblings().hide();
    });
    $("a:contains('Previous step')").click(function () {
        var index = $(this).closest('.con_one').attr('class').slice(0, 9).slice(-1) - 2;
        //console.log(index);
        $('.lib_Menubox>ul>li').eq(index).addClass('active').siblings().removeClass('active');
        $('.lib_Contentbox>div').eq(index).show().siblings().hide();
    });
})();
//    第三分页点击添加表格行
(function () {
    var obj = {
        dom: [],
        ids: [],
        date: []
    };
    //随机生成id
    function ran() {
        var id = 'aaa' + new Date().getTime();
        obj.ids.push(id);
    }

    //添加dom
    function dom() {
        var buffer = new Array();
        buffer.push("<tr>");
        buffer.push("<td class=\"time1 vl-loa-l\">");
        buffer.push("<div class=\"datetime-wrap\">");
        buffer.push("<input name=\"datetime\" id=\"" + obj.ids[obj.ids.length - 1] +
            "\"" + "class=\"DeadlineMonth  DeadlineMonth4 datetime\" type=\"text\" >");
        buffer.push("<div class=\"datetime-view\" style='font-size: 12px;'> </div>");
        buffer.push("</div></td>");
        buffer.push("<td class=\"cargo\">");
        buffer.push("<input type=\"text\" name=\"cargoLoaded\" class=\"br\">");
        buffer.push("</td>");

        buffer.push("<td class=\"cargo\">");
        buffer.push("<input type=\"text\" readonly name=\"theCargoToBeLoaded\" class=\"br\">");
        buffer.push("</td>");
        buffer.push("<td class=\"cargo\">");
        buffer.push("<input type=\"text\" name=\"loadingSpeed\" class=\"br\">");
        buffer.push("</td>");
        buffer.push(" <td class=\"time3 vl-loa-l\"> ");
        buffer.push("<div class=\"datetime-wrap\">");
        buffer.push("<input name=\"etc\" id=\"" + obj.ids[obj.ids.length - 1] + "a" + "\""
            + "class=\"DeadlineMonth  DeadlineMonth4 datetime\" type=\"text\" >");
        buffer.push("<div class=\"datetime-view\" style='font-size: 12px;'></div>");
        buffer.push("</div></td> ");
        buffer.push("<td class=\"time2\">");
        buffer.push("<a href=\"javascript:;\">");
        buffer.push("<i class=\"layui-icon\">");
        buffer.push("<img src=\"/images/delete-icon.png\">");
        buffer.push("</i></a></td></tr>");
        obj.dom.push(buffer.join(""));
        // obj.dom.push('\n        <tr>\n                                                    <td class="time1 vl-loa-l">\n                                                        <div class="datetime-wrap">\n                                                            <input name="datetime" id="' + obj.ids[obj.ids.length - 1] + '" class="DeadlineMonth  DeadlineMonth4 datetime" type="text" >\n                                                            <div class="datetime-view"></div>\n                                                        </div>\n                                                    </td>\n                                                    <td class="cargo">\n                                                        <input type="text" name="cargoLoaded" class="br">\n                                                    </td>\n                                                    <td class="cargo">\n                                                        <input type="text" readonly name="theCargoToBeLoaded" class="br">\n                                                    </td>\n                                                    <td class="cargo">\n                                                        <input type="text" name="loadingSpeed" class="br">\n                                                    </td>\n                                                    <td class="time3 vl-loa-l">\n                                                        <div class="datetime-wrap">\n                                                            <input name="etc" id="' + (obj.ids[obj.ids.length - 1] + 'a') + '" class="DeadlineMonth  DeadlineMonth4 datetime" type="text" >\n                                                            <div class="datetime-view"></div>\n                                                        </div>\n                                                    </td>\n                                                    <td class="time2">\n                                                        <a href="javascript:;">\n                                                            <i class="layui-icon"><img src="/images/delete-icon.png"></i>\n                                                        </a>\n                                                    </td>\n                                                </tr>');
        $('.con_one_3').find('tbody').append(obj.dom[obj.dom.length - 1]);
    }

    function layDate() {
        var laydate = layui.laydate;
        obj.date.push(laydate);
    }

    //实例化layui的date组件对象
    // laydate=null
    //点击事件
    //监听dom数组的长度
    //监听dom数组的长度
    var domLen = obj.dom.length;
    var idLen = obj.ids.length;
    var dateLen = obj.date.length;
    $(document).on('click', '.addbtn3', function () {
        ran(idLen);
        dom(domLen);
        layDate();
        var _self = this;
        layui.use('laydate', function () {
            layDate();
            var laydate = layui.laydate;
            $('.DeadlineMonth4').each(function () {
                laydate.render({
                    elem: this,
                    type: 'datetime',
                    lang: 'en',
                    //value: '10 10 2017',

                    format: 'dd MM yyyy HH:mm',
                    theme: '#F36523',
                    done: function done(value, date, endDate) {
                        var monthArr = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'June', 'July', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec'];
                        var dateCopy = JSON.parse(JSON.stringify(date));
                        dateCopy.month = monthArr[parseInt(dateCopy.month) - 1];
                        var arr = value.split(' ');
                        arr.splice(1, 1, dateCopy.month);
                        var evalue = arr.join(' ');
                        //console.log(id);
                        $(this.elem[0]).css({
                            'opacity': 0
                        });
                        $(this.elem[0]).attr('value', value);
                        $(this.elem[0]).next().text(evalue);
                    }
                });
            })
        });
    });

    //根据货物总量，自动计算货物的剩余量
    $('#seiaBody').on('keyup', 'input[name=cargoLoaded]', function () {
        var quantity = parseFloat($('.quantity').text());
        var thisVal = parseFloat($(this).val());
        var theCargoToBeLoaded = $(this).parents('tr').find('input[name=theCargoToBeLoaded]');
        if (!quantity) {
            theCargoToBeLoaded.val('');
            return;
        }
        if (!thisVal) {
            theCargoToBeLoaded.val('');
            return;
        }
        theCargoToBeLoaded.val(quantity - thisVal);
    })
    // 输入最后的内容后，得出日期
    $('#seiaBody').on('keyup', 'input[name=loadingSpeed]', function () {

        var etc = $(this).parents('tr').find('input[name=etc]');
        var thisVal = parseFloat($(this).val());
        if (!thisVal) {
            etc.val("");
            etc.val(time).siblings('.datetime-view').text("");
            return;
        }
        var datetime = $(this).parents('tr').find('input[name=datetime]').val();
        if(!datetime){
            etc.val("");
            etc.val(time).siblings('.datetime-view').text("");
            return;
        }
        var theCargoToBeLoaded = $(this).parents('tr').find('input[name=theCargoToBeLoaded]');

        var str1 = datetime.split(' ')[2] + '/' + datetime.split(' ')[1] + '/' + datetime.split(' ')[0] + ' ' + datetime.split(' ')[3];
        var tt = new Date(str1).getTime();
        var time = tt + (parseFloat(theCargoToBeLoaded.val()) / thisVal * 3600000);
        if (theCargoToBeLoaded.val()) {
            var dd = new Date(time);
            var year = dd.getFullYear();
            var month = dd.getMonth();
            var day = dd.getDate();
            var hour = dd.getHours();
            var minte = dd.getMinutes();

            if (day < 10) {
                day = "0" + day.toString();
            }

            if (hour < 10) {
                hour = "0" + hour.toString();
            }
            if (minte < 10) {
                minte = "0" + minte.toString();
            }
            var sign = " ";
            var monthArr = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'June', 'July', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec'];

            var strTime = day + sign + "{month}" + sign + year + sign + hour + ":" + minte;
            var divStr = strTime.replace("{month}",monthArr[month]);
            if (month < 10) {
                month = "0" + month.toString();
            }
            var inputStr = strTime.replace("{month}",month);
            etc.val(inputStr).siblings('.datetime-view').text(divStr);
        }
    })
})();

