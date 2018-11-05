
// 设置Cookie
function setCookie(name, value, expires) {
    var expires = (arguments.length > 2) ? arguments[2] : null;
    document.cookie = name + "=" + encodeURIComponent(value) + ((expires == null) ? "" : ("; expires=" + expires.toGMTString())) + ";path=/";
}

// 获取Cookie
function getCookie(name) {
    var value = document.cookie.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
    if (value != null) {
        return decodeURIComponent(value[2]);
    } else {
        return null;
    }
}

// 删除cookie
function removeCookie(name) {
    var expires = new Date();
    expires.setTime(expires.getTime() - 1000 * 60);
    setCookie(name, "", expires);
}

mfj={base:"",currencySign:"\uffe5",currencyUnit:"\u5143",priceScale:"2",priceRoundType:"roundHalfUp"};function currencyFormat(j){j=setScale(j,mfj.priceScale,mfj.priceRoundType);return mfj.currencySign+j+mfj.currencyUnit}
$().ready(function(){
    $("body");
    $.memberVerify=function(){
        /* // TODO null!=getCookie("memberUsername") 目前没有放到cokie 里*/
        if(true){
            var a=!1;
            $.ajax({url:mfj.base+"/member/member_verify.json",type:"POST",dataType:"json",async:!1,cache:!1,success:function(f){
                if(f.status !=1 || f.message=='false')return !1;
                f.status&&(a=!0)
            }});
            return a}
        return!1};
    $.showLoginWin=function(a){
        var index = layer.open({
            type: 2,
            title: '<h1>邮箱账号登录</h1>',
            shadeClose: true,
            //offset:['0px','0px'],
            shade: 0.8,
            area: ['300px', '328px'],
            maxmin: true,
            content: a,
            end: function () {

            }
        });
        layer.full(index);
    };
    if(0<$("#header").size()){var j=$("#headerRegister"),u=$("#headerShowLoginWindow"),l=$("#headerLoginMemberUsername"),v=$("#headerMemberCenter"),q=$("#headerLogout"),h=$("#goodsSearchForm"),e=$("#goodsSearchKeyword");$.flushHeaderInfo=
    function(){null!=getCookie("memberUsername")?(l.text("\u60a8\u597d, "+getCookie("memberUsername")),l.show(),v.show(),q.show()):(l.text(""),u.show(),j.show(),q.hide())};$.flushHeaderInfo();$.showLoginWindow=function(a){$.dialog({title:"\u4f1a\u5458\u767b\u5f55",content:'<form id="loginWindowForm"> <table> <tr> <th>\u7528\u6237\u540d: </th> <td><input type="hidden" id="loginWindowType" name="login.type" value="ajax" /> <input type="text" id="loginWindowMemberUsername" name="member.username" class="formText" /> </td> </tr> <tr> <th>\u5bc6&nbsp;&nbsp;&nbsp;\u7801: </th> <td> <input type="password" id="loginWindowMemberPassword" name="member.password" class="formText" /> </td> </tr> <\!-- <tr> <th>\u9a8c\u8bc1\u7801: </th> <td> <input type="text" id="loginWindowCaptcha" name="j_captcha" class="formText loginWindowCaptcha" /> <img id="loginWindowCaptchaImage" class="loginWindowCaptchaImage" src="'+
mfj.base+"/captcha.jpeg?timestamp="+(new Date).valueOf()+'" alt="\u6362\u4e00\u5f20" /> </td> </tr> --\> <tr> <th>&nbsp;</th> <td> <span class="warnIcon">&nbsp;</span> <\!--<a href="'+mfj.base+'/shop/member/passwordRecover.html">\u5fd8\u8bb0\u4e86\u5bc6\u7801? \u70b9\u51fb\u627e\u56de!</a>--\> </td> </tr> </table> </form>',ok:"\u767b \u5f55",cancel:"\u53d6 \u6d88",id:"loginWindow",className:"loginWindow",width:420,okCallback:function(){if(""==$.trim(b.val()))return b.focus(),$.message({type:"warn",
    content:"\u8bf7\u8f93\u5165\u7528\u6237\u540d!"}),!1;if(""==$.trim(d.val()))return d.focus(),$.message({type:"warn",content:"\u8bf7\u8f93\u5165\u5bc6\u7801!"}),!1;$.ajax({url:mfj.base+"/shop/member/login_check.html",data:f.serialize(),type:"POST",dataType:"json",cache:!1,beforeSend:function(){f.find("button").attr("disabled",!0)},success:function(b){"success"==b.status&&($.flushHeaderInfo(),$.closeDialog("loginWindow"),null!=a&&(location.href=a));$.message({type:b.status,content:b.message})},complete:function(){f.find("button").attr("disabled",
    !1)}});return!1},modal:!0});var f=$("#loginWindowForm"),b=$("#loginWindowMemberUsername"),d=$("#loginWindowMemberPassword")};goodsSearchKeywordDefaultValue=e.val();e.focus(function(){e.val()==goodsSearchKeywordDefaultValue&&e.val("")});e.blur(function(){""==e.val()&&e.val(goodsSearchKeywordDefaultValue)});h.submit(function(){if(""==$.trim(e.val())||$.trim(e.val())==goodsSearchKeywordDefaultValue)return!1})}$.addFavorite=function(a, inner, count, imageServer){
        if(!$.memberVerify()){
            return /*$.showLoginWin("/loginBox.htm")*/layer.msg("请登录!"),!1;
        }
    $.ajax({url:mfj.base+"/member/favorite/add.json",data:{id:a},type:"POST",dataType:"json",cache:!1,success:function(r){
        //layer.alert(a.message, { closeBtn: false, icon: a.status, skin: 'layer-ext-moon'})
        // r.status == 1 收藏成功
        if(r.status==1 && count && jQuery("#"+count).attr("id") != undefined){
            var num = jQuery("#"+count).text();
            if(num==undefined || num=='' || isNaN(num) || num=='0'){
                jQuery("#"+count).text(1);
            }else{
                num = ++num;
                jQuery("#"+count).text(num);
            }

        }
        if(jQuery("#favorite_"+a).attr("id") != undefined){
            setTimeout(function(){layer.tips(r.message, "#favorite_"+a+"");}, 200);
        }else{
            //layer.alert(r.message, { closeBtn: false, icon: r.status, skin: 'layer-ext-moon'})
            layer.msg(r.message);
        }
        if(inner != undefined && inner.length>0)
            $.showFavorite(inner, imageServer);
    }})};
    $.showFavorite = function(inner, imgServer) {
        if(!$.memberVerify()){
            jQuery("#delfavall").hide();
            return layer.msg("请登录后查看收藏!"),!1;
        }
        jQuery.ajax({
            url: "/shop/favorite/favoriteBoxJson.json",
            data: {"id": inner},
            type: "post",
            success: function (data) {
                var innerContent = jQuery("#"+inner);
                innerContent.children().remove();
                if(data!=null && data.length>0){
                    $("#delfavall").show();
                    for(var i = 0; i<data.length; i++) {
                        var goods = data[i];
                        var h = "<li class=\"y-vs-item\" id=\"DF_"+goods.goodsSn+"\" ><img src=\""+imgServer+"/img/"+goods.sourceImagePath+".img_100_100.img\" alt='"+goods.name+"' title='"+goods.name+"' /> <span title='"+goods.name+"'>"+goods.shortName+"</span><a class=\"y-icon ico-delete\" href=\"javascript:\" onclick=\"GoodsCategory.delFavorite('"+goods.goodsSn+"', 'favoritesBox', 'favoriteCount')\"></a></li>"
                        innerContent.append(h);
                    }
                }else{
                    $("#delfavall").hide();
                    var h = "<span style=\"color: #ffffff\">还没有收藏,快去<a href='/shop/goods/search.htm' style=\"color: #ffffff;\">看看吧</a>......</span>";
                    innerContent.append(h);
                }
            }
        });
    };
    $.deleteFavorite=function(a, inner, count){
        if(!$.memberVerify()){
            return layer.msg("请登录!"),!1;
        }
        $.ajax({url:mfj.base+"/member/favorite/delete.json",data:{id:a},type:"POST",dataType:"json",cache:!1,success:function(r){
            //if(jQuery("#favorite_"+a).attr("id") != undefined){
             //   setTimeout(function(){layer.tips(r.message, "#favorite_"+a+"");}, 200);
            //}else{
                //layer.alert(r.message, { closeBtn: false, icon: r.status, skin: 'layer-ext-moon'});
                layer.msg(r.message);
            //}
            if(r.status==1 && count && jQuery("#"+count).attr("id") != undefined){
                var num = jQuery("#"+count).text();
                if(num==undefined || num=='' || isNaN(num) || num=='0'){
                    jQuery("#"+count).text(0);
                }else{
                    num = --num;
                    jQuery("#"+count).text(num);
                }

            }
            if(inner != undefined && inner.length>0){
                //$.showFavorite(inner);
                var df = jQuery("#DF_"+a);
                var dfp = df.parent();
                df.remove();
                if(dfp.find("li").size()<1){
                    $("#delfavall").hide();
                    dfp.append("<span style='color: #ffffff'>还没有收藏,快去<a href='/shop/goods/search.htm' style='color: #ffffff'>看看吧</a>......</span>");
                }
            }
        }})
    };
    $.delFavAll=function(a, inner, count){
        if(!$.memberVerify()){
            return layer.msg("请登录!"),!1;
        }
        $.ajax({url:mfj.base+"/member/favorite/delete.json",data:{id:'0', delAll: 'true'},type:"POST",dataType:"json",cache:!1,success:function(r){
            layer.msg(r.message);
            if(r.status==1){
                /*var num = jQuery("#"+count).text();
                if(num==undefined || num=='' || isNaN(num) || num=='0'){
                    jQuery("#"+count).text(0);
                }else{
                    num = --num;
                    jQuery("#"+count).text(num);
                }*/
                jQuery('.y-srh-fav.y-srh-fav1').empty().removeClass("y-srh-fav1").append("<i class=\"y-icon icon-fav\"></i>收藏");

                $("#favoriteCount").text(0);
                $("#favoriteBoxLi").empty();
                $("#delfavall").hide();
            }
            $("#delfavall").hide();
            $("#favoriteBoxLi").empty();
            $("#favoriteBoxLi").append("<span class='showfavtext' style='color: #ffffff'>还没有收藏,快去<a href='/shop/goods/search.htm' style='color: #ffffff'>看看吧</a>......</span>");
            /*if(inner != undefined && inner.length>0){
                var df = jQuery("#DF_"+a);
                var dfp = df.parent();
                df.remove();
                if(dfp.find("li").size()<1){
                    dfp.append("<span style='color: #ffffff'>还没有收藏,快去<a href='/shop/goods/search.htm' style='color: #ffffff'>看看吧</a>......</span>");
                }
            }*/
        }})
    };
    $.createCompareGoods = function(id,sn,name,imgPath) {
        var compareGoods = new Object();
        compareGoods.id = id;
        compareGoods.sn = sn;
        compareGoods.name = name;
        compareGoods.imgPath = imgPath;
        compareGoods.getCookieId = id+"_"+sn;
        return compareGoods;
    };
    $.addCompareGoods = function(id,sn,name,imgPath) {
        var compareGoodsCookie = getCookie("COMPAREGOODS");
        if(compareGoodsCookie!=null&&compareGoodsCookie.indexOf(id+"_"+sn)>-1){
            layer.msg("该商品已经加入对比!");
            return !1;
        }
        var compareGoods = $.createCompareGoods(id,sn,name,imgPath);
        if(compareGoodsCookie!=null){
            compareGoodsCookie = eval(compareGoodsCookie);
            compareGoodsCookie.push(compareGoods);
        }else{
            compareGoodsCookie = new Array();
            compareGoodsCookie.push(compareGoods);
        }
        setCookie("COMPAREGOODS", JSON.stringify(compareGoodsCookie));
        layer.msg("商品加入对比成功!");
        return !0;
    };
    $.delCompareGoods = function(goods) {
        var compareGoodsCookie = getCookie("COMPAREGOODS");
        compareGoodsCookie = eval(compareGoodsCookie);
        if(compareGoodsCookie!=null){
            for(var i = 0; i<compareGoodsCookie.length; i++) {
                var compare = compareGoodsCookie[i];
                if(compare !=null && goods==compare.getCookieId){
                    var removeTemp = JSON.stringify(compareGoodsCookie[i]);
                    var c = JSON.stringify(compareGoodsCookie);

                    if(c.indexOf(removeTemp+",")>-1){
                        c=c.replace(removeTemp+",","");
                    }else if(c.indexOf(","+removeTemp)>-1){
                        c=c.replace(","+removeTemp,"");
                    }else{
                        c=c.replace(removeTemp,"");
                    }
                    //delete compareGoodsCookie[i];
                    //compareGoodsCookie.length=compareGoodsCookie.length-1;
                    setCookie("COMPAREGOODS", c);
                    layer.msg("商品取消对比成功!");
                    break;
                    //compareGoodsCookie.remove(i);
                    //compareGoodsCookie.length-=1;
                }
            }
        }
    };
    $.getCompareGoodsCount = function(){
        var compareGoodsCookie = getCookie("COMPAREGOODS");
        if(compareGoodsCookie==null)
        return 0;
        else
        return eval(compareGoodsCookie).length;
    };
    $.setCompareCheck = function(){
        var compareGoodsCookie = getCookie("COMPAREGOODS");
        compareGoodsCookie = eval(compareGoodsCookie);
        if(compareGoodsCookie!=null){
            for(var i = 0; i<compareGoodsCookie.length; i++) {
                var compare = compareGoodsCookie[i];
                if(compare !=null && compare.id && compare.sn){
                    $("#compareC_"+compare.id+"_"+compare.sn).attr("checked", true);
                }
            }
        }
    };
    $.vs = function() {
        var compareGoodsCookie = getCookie("COMPAREGOODS");
        compareGoodsCookie = eval(compareGoodsCookie);
        if(compareGoodsCookie!=null){
            var idArr = new Array();
            for(var i = 0; i<compareGoodsCookie.length; i++) {
                var compare = compareGoodsCookie[i];
                if(compare !=null && compare.id && compare.sn){
                    //$("#compareC_"+compare.id+"_"+compare.sn).attr("checked", true);
                    idArr.push(compare.id);
                }
            }
            window.open(mfj.base+"/shop/goods/compare.htm?items="+idArr.toString());
        }
    };
    $.delVSAll = function() {
        removeCookie("COMPAREGOODS");
        $("input.compareC:checked").prop("checked",false);
        $("#vsBox").empty();
        $("#vsall").hide();
        $("#delvsall").hide();
        $(".y-vs-num").text(0);
        layer.msg("对比栏已全部清除!");
    };
    $.setCompareBox = function(box, imgServer){
        box = $("#"+box);
        if(box.length==0)return !1;
        box.children().remove();
        var compareGoodsCookie = getCookie("COMPAREGOODS");
        compareGoodsCookie = eval(compareGoodsCookie);
        if(compareGoodsCookie!=null){
            for(var i = 0; i<compareGoodsCookie.length; i++) {
                var compare = compareGoodsCookie[i];
                if(compare !=null && compare.id && compare.sn){
                    //$("#compareC_"+compare.id+"_"+compare.sn).attr("checked", true);
                    var idsn = compare.id+"_"+compare.sn;
                    var shortname = compare.name;
                    if(shortname.length>10)
                        shortname = shortname.substring(0,10)+"...";
                    var h = "<li class=\"y-vs-item\"><img src=\""+imgServer+"/img/"+compare.imgPath+".img_100_100.img\" alt=\""+compare.name+"\" title=\""+compare.name+"\" /><span title=\""+compare.name+"\">"+shortname+"</span><a class=\"y-icon ico-delete\" href=\"javascript:;\" onclick=\"vs.delCom('"+idsn+"')\"></a></li>";
                    box.append(h);
                }
            }
        }
    };

    $.addCartItem=function(a,f){if(""==$.trim(a))return $.dialog({type:"warn",content:"\u8bf7\u9009\u62e9\u8d2d\u4e70\u5546\u54c1!",modal:!0,autoCloseTime:3E3}),!1;if(!/^[0-9]*[1-9][0-9]*$/.test($.trim(f)))return $.dialog({type:"warn",content:"\u5546\u54c1\u54c1\u6570\u91cf\u5fc5\u987b\u4e3a\u6b63\u6574\u6570!",modal:!0,autoCloseTime:3E3}),!1;$.ajax({url:mfj.base+
"/shop/cart_item/ajaxAdd.html",data:{id:a,quantity:f},type:"POST",dataType:"json",cache:!1,success:function(a){"success"==a.status?$.dialog({type:"success",content:'<span class="red">'+a.message+"</span><br />\u8d2d\u7269\u8f66\u4e2d\u5546\u54c1\u603b\u8ba1: "+a.totalProductQuantity+" \u4ef6",width:360,modal:!0,autoCloseTime:2E3}):$.dialog({type:a.status,content:a.message,modal:!0,autoCloseTime:2E3})}})};var i=$("#goodsListForm");if(0<i.size()){var r=$("#brandId"),g=$("#filter .brand"),c=$("#filter .goodsAttributeOption"),
    s=$("#pageNumber"),m=$("#pageSize"),n=$("#orderType"),t=$("#viewType"),w=$("#tableType"),x=$("#pictureType"),h=$("input.addFavorite");g.click(function(){var a=$(this);a.hasClass("all")?r.val(""):r.val(a.attr("brandId"));i.submit();return!1});c.click(function(){var a=$(this);a.parent().parent().find(":hidden").attr("disabled",!0);a.hasClass("all")||a.prev(":hidden").attr("disabled",!1);i.submit();return!1});m.change(function(){s.val("1");i.submit()});n.change(function(){s.val("1");i.submit()});w.click(function(){t.val("tableType");
    i.submit();return!1});x.click(function(){t.val("pictureType");i.submit();return!1});h.click(function(){var a=$(this);$.addFavorite(a.attr("goodsId"))})}0<$("#goodsContent").size()&&(g=$("a.zoom"),c=$("#thumbnailGoodsImageScrollable"),m=$("#goodsImageTab"),n=$("#goodsParameterTab"),h=$("#addFavorite"),c.scrollable({speed:600}),m.tabs(".goodsImage .tabContent",{event:"mouseover"}),g.zoomimage({opacity:0.6,controlsTrigger:"mouseover",controls:!0,centered:!0,hideSource:!0}),n.tabs(".goodsBottom .tabContent",
    {event:"mouseover"}),h.click(function(){var a=$(this);$.addFavorite(a.attr("goodsId"))}));if(0<$("#goodsHistory").size()){h=$("#goodsHistoryListDetail");$.addGoodsHistory=function(a,f){var b={name:a,htmlPath:f},d=[],e=getCookie("goodsHistoryList");e&&(d=eval(e));for(var c in d);for(c in d)if(d[c].htmlPath==f)return;5<=d.length&&d.shift();d.push(b);b="";for(c in d)b+=',{name: "'+d[c].name+'", htmlPath: "'+d[c].htmlPath+'"}';b="["+b.substring(1,b.length)+"]";setCookie("goodsHistoryList",b)};g=[];(c=
    getCookie("goodsHistoryList"))&&(g=eval(c));var c="",p;for(p in g)c+='<li><span class="icon">&nbsp;</span><a href="'+g[p].htmlPath+'">'+g[p].name+"</a></li>";h.html(c)}0<$("#articleSearch").size()&&($articleSearchForm=$("#articleSearchForm"),$articleSearchKeyword=$("#articleSearchKeyword"),articleSearchKeywordDefaultValue=$("#articleSearchKeyword").val(),$articleSearchKeyword.focus(function(){$articleSearchKeyword.val()==articleSearchKeywordDefaultValue&&$articleSearchKeyword.val("")}),$articleSearchKeyword.blur(function(){""==
$articleSearchKeyword.val()&&$articleSearchKeyword.val(articleSearchKeywordDefaultValue)}),$articleSearchForm.submit(function(){if(""==$.trim($articleSearchKeyword.val())||$.trim($articleSearchKeyword.val())==articleSearchKeywordDefaultValue)return!1}));var k=$("#articleContent");0<k.size()&&($changeBigFontSize=$("#changeBigFontSize"),$changeNormalFontSize=$("#changeNormalFontSize"),$changeSmallFontSize=$("#changeSmallFontSize"),$changeBigFontSize.click(function(){k.css({"font-size":"16px"})}),$changeNormalFontSize.click(function(){k.css({"font-size":"14px"})}),
    $changeSmallFontSize.click(function(){k.css({"font-size":"12px"})}))});

function Base64() {

    // private property
    _keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";

    // public method for encoding
    this.encode = function (input) {
        var output = "";
        var chr1, chr2, chr3, enc1, enc2, enc3, enc4;
        var i = 0;
        input = _utf8_encode(input);
        while (i < input.length) {
            chr1 = input.charCodeAt(i++);
            chr2 = input.charCodeAt(i++);
            chr3 = input.charCodeAt(i++);
            enc1 = chr1 >> 2;
            enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
            enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
            enc4 = chr3 & 63;
            if (isNaN(chr2)) {
                enc3 = enc4 = 64;
            } else if (isNaN(chr3)) {
                enc4 = 64;
            }
            output = output +
            _keyStr.charAt(enc1) + _keyStr.charAt(enc2) +
            _keyStr.charAt(enc3) + _keyStr.charAt(enc4);
        }
        return output;
    }

    // public method for decoding
    this.decode = function (input) {
        var output = "";
        var chr1, chr2, chr3;
        var enc1, enc2, enc3, enc4;
        var i = 0;
        input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");
        while (i < input.length) {
            enc1 = _keyStr.indexOf(input.charAt(i++));
            enc2 = _keyStr.indexOf(input.charAt(i++));
            enc3 = _keyStr.indexOf(input.charAt(i++));
            enc4 = _keyStr.indexOf(input.charAt(i++));
            chr1 = (enc1 << 2) | (enc2 >> 4);
            chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
            chr3 = ((enc3 & 3) << 6) | enc4;
            output = output + String.fromCharCode(chr1);
            if (enc3 != 64) {
                output = output + String.fromCharCode(chr2);
            }
            if (enc4 != 64) {
                output = output + String.fromCharCode(chr3);
            }
        }
        output = _utf8_decode(output);
        return output;
    }

    // private method for UTF-8 encoding
    _utf8_encode = function (string) {
        string = string.replace(/\r\n/g,"\n");
        var utftext = "";
        for (var n = 0; n < string.length; n++) {
            var c = string.charCodeAt(n);
            if (c < 128) {
                utftext += String.fromCharCode(c);
            } else if((c > 127) && (c < 2048)) {
                utftext += String.fromCharCode((c >> 6) | 192);
                utftext += String.fromCharCode((c & 63) | 128);
            } else {
                utftext += String.fromCharCode((c >> 12) | 224);
                utftext += String.fromCharCode(((c >> 6) & 63) | 128);
                utftext += String.fromCharCode((c & 63) | 128);
            }

        }
        return utftext;
    }

    // private method for UTF-8 decoding
    _utf8_decode = function (utftext) {
        var string = "";
        var i = 0;
        var c = c1 = c2 = 0;
        while ( i < utftext.length ) {
            c = utftext.charCodeAt(i);
            if (c < 128) {
                string += String.fromCharCode(c);
                i++;
            } else if((c > 191) && (c < 224)) {
                c2 = utftext.charCodeAt(i+1);
                string += String.fromCharCode(((c & 31) << 6) | (c2 & 63));
                i += 2;
            } else {
                c2 = utftext.charCodeAt(i+1);
                c3 = utftext.charCodeAt(i+2);
                string += String.fromCharCode(((c & 15) << 12) | ((c2 & 63) << 6) | (c3 & 63));
                i += 3;
            }
        }
        return string;
    }
}