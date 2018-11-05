layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate','own','upload'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	own = layui.own,
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
  
  	//执行一个laydate实例
  	laydate.render({
    	elem: '#date1', //指定元素
    	lang: lang_ver,
    	type: 'datetime'
  	});
  	
  	
  	var uuid = own.getHref('uuid');
  	
  	// 查看
  	if (uuid){
  		look(uuid);
  	}
  	
  	// 查看您
  	function look(uuid){
  		//查询详细
	 	var params = {};
	 	params.uuid = uuid;
		
	 	$.ajax({
    		type: "POST",
    		url: shipServer+"/shipPlate/findShipPlateDetail.json",
    		data: JSON.stringify(params),
			dataType: "json",
			contentType:"application/json",
    		async: false,
    		success: function(data) {
				if(data.status == 0){
					var completeDateTimeStamp = data.datas.completeDate;
					var completeDate=new Date(completeDateTimeStamp).Format("yyyy-MM-dd");
					
					//获取 ETA Fujairah 并赋初始化
					var etaTimeStamp = data.datas.eta;
					if(etaTimeStamp!=null){
						$('.eta').html(new Date(etaTimeStamp).Format("yyyy-MM-dd"));
					}else{
						$('.eta').html("");
					}
					
					//获取 ETA Cabinda 并赋初始化
					var etaCabindaTimeStamp = data.datas.etaCabinda;
					if(etaCabindaTimeStamp!=null){
						$('.etaCabinda').html(new Date(etaCabindaTimeStamp).Format("yyyy-MM-dd"));
					}else{
						$('.etaCabinda').html("");
					}
					
					var name = data.datas.name;
					var brokerName = data.datas.brokerName;
					var shipOwner = data.datas.shipOwner;
					//var leaveFlag = data.datas.leaveFlag;
					var epMemberName = data.datas.epMemberName;	 //船盘发布人
					var period = data.datas.period;	  //时效
					
					//获取卸货完成时间，并赋初始化
					var openTimeStamp = data.datas.open;	//到港之后，卸货完成时间
					if(openTimeStamp!=null){
						$(".open").html(new Date(openTimeStamp).Format("yyyy-MM-dd"));
					}else{
						$(".open").html("");
					}
					
					//获取发布日期，并赋初始化
					if(data.datas.status=='5'){		//若船盘的状态为 5未发布，发布日期显示未发布
						$(".publishTime").html($.i18n("JAVASCRIPT00154"));
					}else{
						var publishTimeStamp = data.datas.publishTime;	//发布日期
						if(publishTimeStamp!=null){
							$(".publishTime").html(new Date(publishTimeStamp).Format("yyyy-MM-dd"));
						}else{
							$(".publishTime").html("");
						}
					}
					
					$('.completeDate').html(completeDateTimeStamp);
					$('.name').html(data.datas.name);
					$('.type').html(data.datas.type);
					$('.loadQuantity').html(formatNumber(data.datas.loadQuantity,3,1));
					$('.position').html(data.datas.position);
					$('.owner').html(data.datas.shipOwner);
					$('.broker').html(data.datas.brokerName);
					$('.remark').html(data.datas.remark);
					$(".epMemberName").html(epMemberName);
					$(".period").html(period);
					
				}else{
					layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
						layer.close(index);
					});
				}
			},
			error: function(data) {
				layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
					layer.close(index);
				});
	        }
	 	});
	 	form.render();
  	}
	
	// 返回
	$('.returnBtn').on('click',function(){
		window.location.href = document.referrer;
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
