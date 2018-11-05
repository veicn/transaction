
//日期比较验证
jQuery.validator.addMethod("afterDate", function(value, element, param) {
	//输入不合法的日期格式，不进行此验证
	var endDate = $(element).val();
	var startDate = $("#"+param).val();
	 
	var _end_date = new Date(endDate.replace("-","/").replace("-","/"));
	var _begin_date =new Date(startDate.replace("-","/").replace("-","/"));
	
	if(_end_date<_begin_date){  
        return false;  
    }
	return true;
	
},"结束日期不能早于开始日期！");

jQuery.validator.addMethod("isPhone", function(value,element) {
    var length = value.length;
    var mobile =  /^(((1[0-9]{1}))+\d{9})$/;
    var tel = /^\d{3,4}-?\d{7,9}$/;

    if(/-/.test(value)){
        return this.optional(element) || tel.test(value);
    }else{
        return this.optional(element) || (length == 11 && mobile.test(value));
    }

}, "请正确填写您的联系电话");
