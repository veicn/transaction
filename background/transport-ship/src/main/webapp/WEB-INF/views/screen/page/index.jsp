<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8"%><!DOCTYPE html><html><head><meta http-equiv=Content-Type content="text/html; charset=UTF-8"><meta http-equiv=X-UA-Compatible content="IE=edge,chrome=1"><meta name=viewport content="width=device-width,initial-scale=1,user-scalable=no"><link type=text/css rel=stylesheet href=../static/css/flag-icon.css><link type=text/css rel=stylesheet href=../static/css/style.css><title></title><script>function getCookie(name) {
            var strCookie = document.cookie.replace(/\s/,"");
            var arr = strCookie.split(';');
            for (var i = 0; i < arr.length; i++) {
                var t = arr[i].split("=");
                if(t[0].trim() == name) {
                    return t[1];
                }
            };
            return null;
        }
        if(getCookie("_l_")){
            var lang = getCookie("_l_").substr(0,2)
            if(lang == 'en'){
                document.write("<script type='text/javascript' src='../static/configIP.js?"+Math.random().toString(36).substr(2)+"'><\/script><script type='text/javascript' src='http://maps.google.cn/maps/api/js?v=3.exp&region=cn&language=EN&key=AIzaSyBeovhzBxHuGP_lijmkKhb0kqTQZJLlWlY' async defer><\/script>")
            }else{
                document.write("<script type='text/javascript' src='../static/configIP.js?"+Math.random().toString(36).substr(2)+"'><\/script><script type='text/javascript' src='http://maps.google.cn/maps/api/js?v=3.exp&region=cn&language=zh-CN&key=AIzaSyBeovhzBxHuGP_lijmkKhb0kqTQZJLlWlY' async defer><\/script>")
            }
        }else{
            document.write("<script type='text/javascript' src='../static/configIP.js?"+Math.random().toString(36).substr(2)+"'><\/script><script type='text/javascript' src='http://maps.google.cn/maps/api/js?v=3.exp&region=cn&key=AIzaSyBeovhzBxHuGP_lijmkKhb0kqTQZJLlWlY' async defer><\/script>")
        }
        //  document.write("<script type='text/javascript' src='../static/configIP.js?"+Math.random().toString(36).substr(2)+"'><\/script>")</script><script>var _hmt = _hmt || [];
        (function() {
            var hm = document.createElement("script");
            hm.src = "https://hm.baidu.com/hm.js?469b0210be7581c5a68ba34d43e1b3bc";
            var s = document.getElementsByTagName("script")[0];
            s.parentNode.insertBefore(hm, s);
        })();</script><link href=/static/css/app.d41d8cd98f00b204e9800998ecf8427e.css rel=stylesheet></head><body><div id=app></div><style>#cnzz_stat_icon_1272857056{display:none}</style><script type=text/javascript>var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1272857056'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s13.cnzz.com/z_stat.php%3Fid%3D1272857056%26show%3Dpic1' type='text/javascript'%3E%3C/script%3E"));</script><script type=text/javascript src=/static/js/manifest.3a1ed7b9c64359678eea.js></script><script type=text/javascript src=/static/js/vendor.dd351b54ae78a20f4c34.js></script><script type=text/javascript src=/static/js/common.js.e5f06558766904ef88fd.js></script><script type=text/javascript src=/static/js/app.8057cf04b04244da7029.js></script><script type=text/javascript src=/static/js/slib.e4be367acc6f8d79e228.js></script></body></html>
