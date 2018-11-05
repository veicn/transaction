layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	// 文本框 点击事件、去掉千分位
	$('.format-t3').on('focus', function() {
		this.value = delNumFormat(this.value);
	});
	
	// 文本框焦点离开事件、增加千分位
	$('.format-t3').on('blur', function() {
		this.value = formatNumber(this.value,3,1);
	});
	
	// 文本框 点击事件、去掉千分位
	$('.format-t2').on('focus', function() {
		this.value = delNumFormat(this.value);
	});
	
	// 文本框焦点离开事件、增加千分位
	$('.format-t2').on('blur', function() {
		this.value = formatNumber(this.value,2,1);
	});

});

/**
 * 将字符串中的数值四舍五入后格式化为千分位
 * @param str 数值(Number或者String)
 * @param cent 要保留的小数位(Number)
 * @param isThousand 是否需要千分位 0:不需要,1:需要(数值类型);
 * @return 格式的字符串,如'1,234,567.45桶'
 * @type String
 */
function formatStr(str,cent,isThousand){
	var numStartIndex = -1
	var numEndIndex = -1
	var strStartIndex = -1;
	var strEndIndex = -1;
	var tempStr = "";
	var re = /^\d+(?=\.{0,1}\d+$|$)/;
	str=str+'';
	for (var i=0; i < str.length; i++) {
		  if (re.test(str[i]) || str[i] == ".") {
			// 数值
			  if (numStartIndex == -1) {
				  numStartIndex = i;
			  }
			  if (i == str.length-1) {
				  numEndIndex = str.length;
			  }
			  strEndIndex = i;
		  } else {
			  // 非数值
			  if (strStartIndex == -1) {
				  strStartIndex = i;
			  }
			  if (i == str.length-1) {
				  strEndIndex = str.length;
			  }
			  numEndIndex = i;
		  }

		  if (numStartIndex>=0 && numEndIndex>=0 && numEndIndex >= numStartIndex) {
		     tempStr = tempStr + formatNumber(str.substring(numStartIndex,numEndIndex),cent,isThousand);
		     numStartIndex = -1;
		     numEndIndex = -1;
		  }

		  if (strStartIndex>=0 && strEndIndex>=0 && strEndIndex >= strStartIndex) {
		     tempStr = tempStr + str.substring(strStartIndex,strEndIndex);
		     strStartIndex = -1;
		     strEndIndex = -1;
		  }
	}
	
	return tempStr;
}

/**
 * 将数值四舍五入后格式化为千分位
 * @param num 数值(Number或者String)
 * @param cent 要保留的小数位(Number)
 * @param isThousand 是否需要千分位 0:不需要,1:需要(数值类型);
 * @return 格式的字符串,如'1,234,567.45'
 * @type String
 */
function formatNumber(num,cent,isThousand) {

	// 检查传入数值为数值类型
	if(isNaN(num) || !num) {
		//num = "0";
		return "";
	}
	
	num = num.toString().replace(/\$|\,/g,'');
	
    // 获取符号(正/负数)
    var sign = (num == (num = Math.abs(num)));
 
    num = Math.floor(num*Math.pow(10,cent)+0.50000000001);	// 把指定的小数位先转换成整数.多余的小数位四舍五入
    var cents = num%Math.pow(10,cent);						// 求出小数位数值
    num = Math.floor(num/Math.pow(10,cent)).toString();		// 求出整数位数值
    cents = cents.toString();								// 把小数位转换成字符串,以便求小数位长度
 
    // 补足小数位到指定的位数
	while(cents.length<cent) {
		cents = "0" + cents;
	}

    if(isThousand) {
     	// 对整数部分进行千分位格式化.
		for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++) {
			num = num.substring(0,num.length-(4*i+3))+','+ num.substring(num.length-(4*i+3));
		}
    }
 
    if (cent > 0) {
		return (((sign)?'':'-') + num + '.' + cents);
	} else  {
		return (((sign)?'':'-') + num);
	}
}

/**
 * 去除千分位
 *@param{Object}num
 */
 
function delNumFormat(num) {
	// 删除左右两端的空格
	if(trim(num+"")=="") {
		return"";
	}
	// 删除','
	num = num.replace(/,/gi,'');
	
	return num;
}

/**
 * 删除左右两端的空格
 *@param{Object}num
 */
function trim(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
}


