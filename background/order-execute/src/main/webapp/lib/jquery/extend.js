$.extend({
	ajaxFn:function(option){
		var defualts={
			url:'',
			type:'POST',
			data: {},
			async:true,
			contentType: 'application/json',
			success:function(){
				
			},
			error:function(){
				
			}
		}
		var settings=$.extend(true,defualts,option);
		var jsonData = null;
		if(typeof settings.data == 'string' || settings.type.toLowerCase() == 'get'){
			jsonData = settings.data;
		}else{
			jsonData = JSON.stringify(settings.data);
		}
		$.ajax({
			url: settings.url,
			data: jsonData,
			type: settings.type,
			contentType: settings.contentType,
			async:settings.async,
			success: function(data) {
				settings.success(data, status);
			},
			error: function(error) {
				settings.error(error);
			}
		})
	},
	division:function(arg1,arg2){  // 除法
		var t1=0,t2=0,r1,r2;  
		try{t1=arg1.toString().split(".")[1].length}catch(e){}  
		try{t2=arg2.toString().split(".")[1].length}catch(e){}  
		with(Math){  
			r1=Number(arg1.toString().replace(".",""))  
			r2=Number(arg2.toString().replace(".","")) 
			return $.ride((r1/r2),pow(10,t2-t1));  
		}  
	},
	ride:function(arg1,arg2){   //乘法 
	    var m=0,s1=arg1.toString(),s2=arg2.toString();  
	    try{m+=s1.split(".")[1].length}catch(e){}  
	    try{m+=s2.split(".")[1].length}catch(e){}  
	    return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)  
	},  
	add:function(arg1,arg2){    //加法  
		var r1,r2,m;  
		try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}  
		try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}  
		m=Math.pow(10,Math.max(r1,r2))  
		return (arg1*m+arg2*m)/m  
	},  
	subduction:function(arg1,arg2){   //减法  
		var r1,r2,m,n; 
		try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0} 
		try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0} 
		m=Math.pow(10,Math.max(r1,r2)); 
	    n=(r1>=r2)?r1:r2; 
		return ((arg1*m-arg2*m)/m).toFixed(n); 
	}
})
