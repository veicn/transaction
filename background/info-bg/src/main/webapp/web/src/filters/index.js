import Vue from 'vue'
import moment from 'moment'

Vue.filter('zy', function(value) {
  return 'test'
})
Vue.filter('time', function(timestamp, format){
  var result;
	timestamp = parseInt(timestamp);
	
	if (!timestamp){
	    result = '-';
	} else if (format) {
	    result = moment(timestamp).format(format);
	} else {
	    result = moment(timestamp).format('YYYY-MM-DD HH:mm:ss');
	}
	return result;
})
Vue.filter('uppercase',function(str){
	return str.toUpperCase();
})
Vue.filter('lowercase',function(str){
	return str.toLowerCase()
})
Vue.filter('truncate',function(str, length, truncation){
	length = length || 30
    truncation = typeof truncation === "string" ?  truncation : "..." 
    return str.length > length ? str.slice(0, length - truncation.length) + truncation : String(str)
})
Vue.filter('number',function(number, decimals, point, thousands) {
    //form http://phpjs.org/functions/number_format/
    //number	必需，要格式化的数字
    //decimals	可选，规定多少个小数位。
    //point	可选，规定用作小数点的字符串（默认为 . ）。
    //thousands	可选，规定用作千位分隔符的字符串（默认为 , ），如果设置了该参数，那么所有其他参数都是必需的。
    number = (number + '')
            .replace(/[^0-9+\-Ee.]/g, '')
    var n = !isFinite(+number) ? 0 : +number,
            prec = !isFinite(+decimals) ? 3 : Math.abs(decimals),
            sep = thousands || ",",
            dec = point || ".",
            s = '',
            toFixedFix = function(n, prec) {
                var k = Math.pow(10, prec)
                return '' + (Math.round(n * k) / k)
                        .toFixed(prec)
            }
    // Fix for IE parseFloat(0.55).toFixed(0) = 0;
    s = (prec ? toFixedFix(n, prec) : '' + Math.round(n))
            .split('.')
    if (s[0].length > 3) {
        s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep)
    }
    if ((s[1] || '')
            .length < prec) {
        s[1] = s[1] || ''
        s[1] += new Array(prec - s[1].length + 1)
                .join('0')
    }
    return s.join(dec)
})
Vue.filter('camelize',function(target) {
    //提前判断，提高getStyle等的效率
    if (!target || target.indexOf("-") < 0 && target.indexOf("_") < 0) {
        return target
    }
    //转换为驼峰风格
    return target.replace(/[-_][^-_]/g, function (match) {
        return match.charAt(1).toUpperCase()
    })
})
Vue.filter('escape',function(str){
	//将字符串经过 str 转义得到适合在页面中显示的内容, 例如替换 < 为 &lt 
    return String(str).
    replace(/&/g, '&amp;').
    replace(rsurrogate, function(value) {
        var hi = value.charCodeAt(0)
        var low = value.charCodeAt(1)
        return '&#' + (((hi - 0xD800) * 0x400) + (low - 0xDC00) + 0x10000) + ';'
    }).
    replace(rnoalphanumeric, function(value) {
        return '&#' + value.charCodeAt(0) + ';'
    }).
    replace(/</g, '&lt;').
    replace(/>/g, '&gt;')
})
Vue.filter('currency',function(amount, symbol, fractionSize){
	function numberFormat(number, decimals, point, thousands) {
	    //form http://phpjs.org/functions/number_format/
	    //number	必需，要格式化的数字
	    //decimals	可选，规定多少个小数位。
	    //point	可选，规定用作小数点的字符串（默认为 . ）。
	    //thousands	可选，规定用作千位分隔符的字符串（默认为 , ），如果设置了该参数，那么所有其他参数都是必需的。
	    number = (number + '')
	            .replace(/[^0-9+\-Ee.]/g, '')
	    var n = !isFinite(+number) ? 0 : +number,
	            prec = !isFinite(+decimals) ? 3 : Math.abs(decimals),
	            sep = thousands || ",",
	            dec = point || ".",
	            s = '',
	            toFixedFix = function(n, prec) {
	                var k = Math.pow(10, prec)
	                return '' + (Math.round(n * k) / k)
	                        .toFixed(prec)
	            }
	    // Fix for IE parseFloat(0.55).toFixed(0) = 0;
	    s = (prec ? toFixedFix(n, prec) : '' + Math.round(n))
	            .split('.')
	    if (s[0].length > 3) {
	        s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep)
	    }
	    if ((s[1] || '')
	            .length < prec) {
	        s[1] = s[1] || ''
	        s[1] += new Array(prec - s[1].length + 1)
	                .join('0')
	    }
	    return s.join(dec)
	}
	return (symbol || "\uFFE5") + numberFormat(amount, isFinite(fractionSize) ? fractionSize : 2)
})