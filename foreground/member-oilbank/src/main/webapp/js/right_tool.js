

$().ready(function() {
    var COOKIE_NAME = window.CompareGoodsName || "COMPAREGOODS"

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

    $.setCookie = setCookie;
    $.getCookie = getCookie;
    $.removeCookie = removeCookie;
    $.createCompareGoods = function (id, sn, name, imgPath, dealType) {
        var compareGoods = new Object();
        compareGoods.id = id;
        compareGoods.sn = sn;
        compareGoods.name = name;
        compareGoods.imgPath = imgPath;
        compareGoods.getCookieId = id + "_" + sn;
        compareGoods.dealType = dealType;
        return compareGoods;
    };
    $.addCompareGoods = function (id, sn, name, imgPath, dealType) {
        var compareGoodsCookie = getCookie(COOKIE_NAME);
        if (compareGoodsCookie != null && compareGoodsCookie.indexOf(id + "_" + sn) > -1) {
            layer.msg("该商品已经加入对比!");
            return !1;
        }
        var compareGoods = $.createCompareGoods(id, sn, name, imgPath, dealType);
        if (compareGoodsCookie != null) {
            compareGoodsCookie = eval(compareGoodsCookie);
            compareGoodsCookie.push(compareGoods);
        } else {
            compareGoodsCookie = new Array();
            compareGoodsCookie.push(compareGoods);
        }
        setCookie(COOKIE_NAME, JSON.stringify(compareGoodsCookie));
        layer.msg("商品加入对比成功!");
        return !0;
    };
    $.delCompareGoods = function (goods) {
        var compareGoodsCookie = getCookie(COOKIE_NAME);
        compareGoodsCookie = eval(compareGoodsCookie);
        if (compareGoodsCookie != null) {
            for (var i = 0; i < compareGoodsCookie.length; i++) {
                var compare = compareGoodsCookie[i];
                if (compare != null && goods == compare.getCookieId) {
                    var removeTemp = JSON.stringify(compareGoodsCookie[i]);
                    var c = JSON.stringify(compareGoodsCookie);

                    if (c.indexOf(removeTemp + ",") > -1) {
                        c = c.replace(removeTemp + ",", "");
                    } else if (c.indexOf("," + removeTemp) > -1) {
                        c = c.replace("," + removeTemp, "");
                    } else {
                        c = c.replace(removeTemp, "");
                    }
                    //delete compareGoodsCookie[i];
                    //compareGoodsCookie.length=compareGoodsCookie.length-1;
                    setCookie(COOKIE_NAME, c);
                    layer.msg("商品取消对比成功!");
                    break;
                    //compareGoodsCookie.remove(i);
                    //compareGoodsCookie.length-=1;
                }
            }
        }
    };
    $.getCompareGoodsCount = function () {
        var compareGoodsCookie = getCookie(COOKIE_NAME);
        if (compareGoodsCookie == null)
            return 0;
        else
            return eval(compareGoodsCookie).length;
    };
    $.setCompareCheck = function () {
        var compareGoodsCookie = getCookie(COOKIE_NAME);
        compareGoodsCookie = eval(compareGoodsCookie);
        if (compareGoodsCookie != null) {
            for (var i = 0; i < compareGoodsCookie.length; i++) {
                var compare = compareGoodsCookie[i];
                if (compare != null && compare.id && compare.sn) {
                    $("#compareC_" + compare.id + "_" + compare.sn).attr("checked", true);
                }
            }
        }
    };
    $.vs = function () {
        var compareGoodsCookie = getCookie(COOKIE_NAME);
        compareGoodsCookie = eval(compareGoodsCookie);
        if (compareGoodsCookie != null) {
            var idArr = new Array();
            for (var i = 0; i < compareGoodsCookie.length; i++) {
                var compare = compareGoodsCookie[i];
                if (compare != null && compare.id && compare.sn) {
                    //$("#compareC_"+compare.id+"_"+compare.sn).attr("checked", true);
                    idArr.push(compare.id);
                }
            }
            if(idArr.length > 1){
                window.compare && window.compare(idArr, compareGoodsCookie[0].dealType);
            }else{
                layer.msg("最少两条对比数据!");
            }

        }
    };
    $.delVSAll = function () {
        removeCookie(COOKIE_NAME);
        $("input.compareC:checked").prop("checked", false);
        $("#vsBox").empty();
        $("#vsall").hide();
        $("#delvsall").hide();
        $(".vs-num").text(0);
        layer.msg("对比栏已全部清除!");
    };
    $.setCompareBox = function (box, imgServer) {
        box = $("#" + box);
        if (box.length == 0) return !1;
        box.children().remove();
        var compareGoodsCookie = getCookie(COOKIE_NAME);
        compareGoodsCookie = eval(compareGoodsCookie);
        if (compareGoodsCookie != null) {
            for (var i = 0; i < compareGoodsCookie.length; i++) {
                var compare = compareGoodsCookie[i];
                if (compare != null && compare.id && compare.sn) {
                    //$("#compareC_"+compare.id+"_"+compare.sn).attr("checked", true);
                    var idsn = compare.id + "_" + compare.sn;
                    var shortname = compare.name;
                    if (shortname.length > 10)
                        shortname = shortname.substring(0, 10) + "...";
                    var h = "<li class=\"vs-item\"><img src=\"" + compare.imgPath + "\" alt=\"" + compare.name + "\" title=\"" + compare.name + "\" /><span title=\"" + compare.name + "\">" + shortname + "</span><a class=\"icon y-icon ico-delete\" href=\"javascript:;\" onclick=\"vs.delCom('" + idsn + "')\"></a></li>";
                    box.append(h);
                }
            }
        }
    };
});