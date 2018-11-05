layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate','own','upload'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	own = layui.own,
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	//获取url参数
	function getUrlParam(name) {
		  var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
		  var r = window.location.search.substr(1).match(reg); //匹配目标参数
		  if (r != null) return decodeURI(r[2]); return null; //返回参数值
	}
	
	var uuid = getUrlParam('uuid');
	var params = {};
	params.uuid = uuid;
 	$.ajax({
		type: "POST",
		url: shipServer+"/pallet/palleDetail.json",
		data: JSON.stringify(params),
		dataType: "json",
		contentType:"application/json",
		async: false,
		success: function(data) {
			if(data.status == 0){
				//租船港口信息
				var listRegion = data.datas.listRegion;
				for(var i=0; i<listRegion.length;i++){
					$("#thisBc").append("<li class='c-fcos2'><span class='c-fn'>数量"+(i+1)+"：</span><span class='c-int qx-conte'>"+listRegion[i].quantity+"（"+listLoad[i].unitName+"）"+"</span></li>");
					$("#thisBc").append("<li class='c-fcos2'><span class='c-fn'>装港区域"+(i+1)+"：</span><span class='c-int qx-conte'>"+listRegion[i].loadRegion+"</span></li>");
				}
				var listUnregion = data.datas.listUnregion;
				for(var i=0; i<listUnregion.length;i++){
					$("#thisBc").append("<li class='c-fcos2'><span class='c-fn'>卸港区域"+(i+1)+"：</span><span class='c-int qx-conte'>"+listUnregion[i].unloadRegion+"</span></li>");
				}
				var date1 = new Date(data.datas.pallet.laycanBeg).Format("yyyy-MM-dd");
				var date2 = new Date(data.datas.pallet.laycanEnd).Format("yyyy-MM-dd");
				$("#thisBc").append("<li class='c-fcos2'><span class='c-fn'>装港layCan：</span><span class='c-int qx-conte'>"+date1+"-"+date2+"</span></li>");
				
				//租船信息
				$("#thisBc2").append("<li class='c-fcos2'><span class='c-fn'>船型：</span><span class='c-int qx-conte'>"+data.datas.pallet.shipType+"</span></li>");
				$("#thisBc2").append("<li class='c-fcos2'><span class='c-fn'>船龄：</span><span class='c-int qx-conte'>"+data.datas.pallet.shipAge+"</span></li>");
				$("#thisBc2").append("<li class='c-fcos2'><span class='c-fn'>租船其他要求：</span><span class='c-int qx-conte'>"+data.datas.pallet.ramark+"</span></li>");
				$("#thisBc2").append("<li class='c-fcos2'><span class='c-fn'>指定承运商：</span><span class='c-int qx-conte'>"+data.datas.pallet.traderName+"</span></li>");
				$("#thisBc3").append("<li class='c-fcos2'><span class='c-fn'>公司：</span><span class='c-int qx-conte'>"+data.datas.pallet.companyName+"</span></li>");
				$("#thisBc3").append("<li class='c-fcos2'><span class='c-fn'>联系人：</span><span class='c-int qx-conte'>"+data.datas.pallet.contacts+"</span></li>");
				$("#thisBc3").append("<li class='c-fcos2'><span class='c-fn'>邮箱：</span><span class='c-int qx-conte'>"+data.datas.pallet.email+"</span></li>");
				$("#thisBc3").append("<li class='c-fcos2'><span class='c-fn'>电话：</span><span class='c-int qx-conte'>"+data.datas.pallet.tel+"</span></li>");
			}
		},
		error: function(data) {
            alert("error:" + data.message);
        }
 	});
 	form.render();

  	

  //取消
  	$('.returnBtn').on('click',function(){
  		window.location.href=shipServer+"/pallet/palletList.htm";
  	});
	
	
  	
});

//日期格式化函数
Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, 						//月份 
        "d+": this.getDate(), 							//日 
        "h+": this.getHours(), 							//小时 
        "m+": this.getMinutes(), 						//分 
        "s+": this.getSeconds(), 						//秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), 	//季度 
        "S": this.getMilliseconds() 					//毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    
    return fmt;
}
