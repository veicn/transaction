//***********************js丢失精度***********
function division (arg1,arg2){  // 除法
		var t1=0,t2=0,r1,r2;  
		try{t1=arg1.toString().split(".")[1].length}catch(e){}  
		try{t2=arg2.toString().split(".")[1].length}catch(e){}  
		with(Math){  
			r1=Number(arg1.toString().replace(".",""))  
			r2=Number(arg2.toString().replace(".","")) 
			return ride((r1/r2),pow(10,t2-t1));  
		}  
}
function ride(arg1,arg2){   //乘法 
    var m=0,s1=arg1.toString(),s2=arg2.toString();  
    try{m+=s1.split(".")[1].length}catch(e){}  
    try{m+=s2.split(".")[1].length}catch(e){}  
    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)  
}  
function add(arg1,arg2){    //加法  
	var r1,r2,m;  
	try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}  
	try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}  
	m=Math.pow(10,Math.max(r1,r2))  
	return (arg1*m+arg2*m)/m  
}
function subduction(arg1,arg2){   //减法  
	var r1,r2,m,n; 
	try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0} 
	try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0} 
	m=Math.pow(10,Math.max(r1,r2)); 
    n=(r1>=r2)?r1:r2; 
	return ((arg1*m-arg2*m)/m).toFixed(n); 
}
//***********************js丢失精度***********


//保留三位有效数字
function noNumbers(e) {
	return checkNum(e,3,false);
}

//保留三位有效数字 可输入正负数
function noNumbersZF(e) {
	return checkNum(e,3);
}

//只能输入整数数字
function noNumbersInt(e) {
    return checkNum(e,0,false);
}

function clearPhono(obj){
    //响应鼠标事件，允许左右方向键移动
    // event = window.event || event;
    // if (event.keyCode == 37 | event.keyCode == 39) {
    //     return;
    // }
    // var t = obj.value.charAt(0);
    // //先把非数字的都替换掉，除了数字和.
    // obj.value = obj.value.replace(/[^0-9a-zA-Z*#-+=,;:()（）]/g, "");
}


//JS限制input输入的为数字并且有小数的时候最多保留三位小数
/**
 * 
 * @param e 事件
 * @param len 限制输入几位小数
 * @param isN 可输入负数:true,false 默认可以输入
 * @returns
 */
function checkNum(e,len,isN) {
    var obj=e.srcElement || e.target;
    var dot=obj.value.indexOf(".");//alert(e.which);
    len =(typeof(len)=="undefined")?3:len;
    isN =(typeof(isN)=="undefined")?true:isN;
    var  key=e.keyCode || e.which;
    if(key==8 || key==9 || (key>=37  && key<=40)){//这里为了兼容Firefox的backspace,tab,del,方向键
    	return true;
    }
    if(len==0 && key==46){
    	return false;
    }
    if(key==46 && obj.value.indexOf('.') == 0){
    	obj.value = ''; //解决多 .号问题
    	return true;
    }
    //是否可以输入负数
    if(key == 45 && isN){
    	if(obj.value.indexOf('-') == 0){
    		obj.value = ''; //解决多 负号问题
    	}
    	return true;
    }
    if (key<=57 && key>=48) { //数字
        if(dot==-1)//没有小数点
            return true;
        else if(obj.value.length<=dot+len)//小数位数
            return true;
        } else if((key==46) && dot==-1){//小数点
            return true;
    }       
    return false;
}