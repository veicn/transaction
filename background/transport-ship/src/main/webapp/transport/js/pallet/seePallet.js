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
				var arr =[];
				var arr2 =[];
				var quantityStr = "";
				var loadPortStr = "";
				var oilTypeStr = "";
				var unloadPortStr = "";
				var type =data.datas.type;
				if(type=='1'){
					arr = data.datas.listLoad;
					arr2 = data.datas.listUnload;
					for(var i = 0 ; i < arr.length ; i++){
						if ( i== 0){
							quantityStr = formatNumber(arr[i].quantity,2,1)+arr[i].unitName;
							loadPortStr = arr[i].loadPort;
							oilTypeStr = arr[i].oilType;
						}else{
							quantityStr += "/"+formatNumber(arr[i].quantity,2,1)+arr[i].unitName;
							loadPortStr += "/" +arr[i].loadPort;
							oilTypeStr	+= "/" + arr[i].oilType;
						}
					}
					for(var i = 0 ; i < arr2.length;i++){
						if ( i== 0){
							unloadPortStr = arr2[i].unloadPort;
						}else{
							unloadPortStr += "/" + arr2[i].unloadPort;
						}
					}
				}else if(type=='2'){
					arr = data.datas.listRegion;
					arr2 = data.datas.listUnregion;
					for(var i = 0 ; i < arr.length ; i++){
						if(i==0){
							quantityStr = formatNumber(arr[i].quantity,2,1)+arr[i].unitName;
							loadPortStr = arr[i].loadRegion;
							oilTypeStr = arr[i].oilType;
						}else{
							quantityStr += "/"+formatNumber(arr[i].quantity,2,1)+arr[i].unitName;
							loadPortStr += "/" +arr[i].loadRegion;
							oilTypeStr	+= "/" + arr[i].oilType;
						}
					}
					for(var i = 0 ; i < arr2.length;i++){
						if(i==0){
							unloadPortStr=arr2[i].unloadRegion;
						}else{
							unloadPortStr += "/" + arr2[i].unloadRegion;
						}
					}
				}
				$(".quantity").html(quantityStr);
				$(".quantity").attr("title",quantityStr);
				$(".loadPort").html(loadPortStr);
				$(".loadPort").attr("title",loadPortStr);
				$(".oilType").html(oilTypeStr);
				$(".oilType").attr("title",oilTypeStr);
				$(".unloadPort").html(unloadPortStr);
				$(".unloadPort").attr("title",unloadPortStr);
				var pallet =data.datas.pallet;
				var laycan = new Date(pallet.laycanBeg).Format('yyyy-MM-dd')+"--"+new Date(pallet.laycanEnd).Format('yyyy-MM-dd');
				$(".laycan").html(laycan);
				$(".laycan").attr("title",laycan);
				$(".remark").html(pallet.remark);
				$(".remark").attr("title",pallet.remark);
				$(".companyName").html(pallet.companyName);
				$(".companyName").attr("title",pallet.companyName);
				$(".tel").html(pallet.tel);
				$(".tel").attr("title",pallet.tel);
				$(".email").html(pallet.email);
				$(".email").attr("title",pallet.email);
				$(".shipAge").html(pallet.shipAge);
				$(".shipAge").attr("title",pallet.shipAge);
				$(".contacts").html(pallet.contacts);
				$(".contacts").attr("title",pallet.contacts);
				$(".shipType").html(pallet.shipType);
				$(".shipType").attr("title",pallet.shipType);
			/*else{
					var listRegion = data.datas.listRegion;
					var listUnregion = data.datas.listUnregion;
					for(var j=0; j<listRegion.length;j++){
						$("#thisBc").append("<li class='c-fcos2'><span class='c-fn' style='float:left !important;'>"+$.i18n("JAVASCRIPT00247")+(i+1)+"：</span><span class='c-int qx-conte' style='display:block; float:left; text-align:justify;word-break:break-all;'>"+listRegion[j].oilType+"</span></li>");
						$("#thisBc").append("<li class='c-fcos2'><span class='c-fn' style='float:left !important;'>"+$.i18n("JAVASCRIPT00183")+(j+1)+"：</span><span class='c-int qx-conte' style='display:block; float:left; text-align:justify;word-break:break-all;'>"+formatStr(listRegion[j].quantity+'',3,1)+listRegion[j].unitName+"</span></li>");
						$("#thisBc").append("<li class='c-fcos2'><span class='c-fn' style='float:left !important;'>"+$.i18n("JAVASCRIPT00184")+(j+1)+"：</span><span class='c-int qx-conte' style='display:block; float:left; text-align:justify;word-break:break-all;'>"+listRegion[j].loadRegion+"</span></li>");
					}
					for(var j=0; j<listUnregion.length;j++){
						$("#thisBc").append("<li class='c-fcos2'><span class='c-fn' style='float:left !important;'>"+$.i18n("JAVASCRIPT00186")+(j+1)+"：</span><span class='c-int qx-conte' style='display:block; float:left; text-align:justify;word-break:break-all;'>"+listUnregion[j].unloadRegion+"</span></li>");
					}
				}
				if(data.datas.pallet.laycanBeg==null&&data.datas.pallet.laycanBeg==''){
					date1="";
				}else{
					var date1 = new Date(data.datas.pallet.laycanBeg).Format("yyyy-MM-dd");
				}
				if(data.datas.pallet.laycanEnd==null&&data.datas.pallet.laycanEnd==''){
					date2="";
				}else{
					var date2 = new Date(data.datas.pallet.laycanEnd).Format("yyyy-MM-dd");
				}
				$("#thisBc").append("<li class='c-fcos2'><span class='c-fn' style='float:left !important;'>"+$.i18n("JAVASCRIPT00188")+"：</span><span class='c-int qx-conte' style='display:block; float:left; text-align:justify;word-break:break-all;'>"+date1+"--"+date2+"</span></li>");
				
				//租船信息
				$("#thisBc2").append("<li class='c-fcos2'><span class='c-fn' style='float:left !important;'>"+$.i18n("JAVASCRIPT00189")+"：</span><span class='c-int qx-conte' style='display:block; float:left; text-align:justify;word-break:break-all;'>"+data.datas.pallet.shipType+"</span></li>");
				$("#thisBc2").append("<li class='c-fcos2'><span class='c-fn' style='float:left !important;'>"+$.i18n("JAVASCRIPT00190")+"：</span><span class='c-int qx-conte' style='display:block; float:left; text-align:justify;word-break:break-all;'>"+data.datas.pallet.shipAge+"</span></li>");
				$("#thisBc2").append("<li class='c-fcos2'><span class='c-fn' style='float:left !important;'>"+$.i18n("JAVASCRIPT00191")+"：</span><span class='c-int qx-conte' style='display:block; float:left; text-align:justify;word-break:break-all;'>"+data.datas.pallet.remark+"</span></li>");
				$("#thisBc3").append("<li class='c-fcos2'><span class='c-fn' style='float:left !important;'>"+$.i18n("JAVASCRIPT00192")+"：</span><span class='c-int qx-conte' style='display:block; float:left; text-align:justify;word-break:break-all;'>"+data.datas.pallet.companyName+"</span></li>");
				$("#thisBc3").append("<li class='c-fcos2'><span class='c-fn' style='float:left !important;'>"+$.i18n("JAVASCRIPT00193")+"：</span><span class='c-int qx-conte' style='display:block; float:left; text-align:justify;word-break:break-all;'>"+data.datas.pallet.contacts+"</span></li>");
				$("#thisBc3").append("<li class='c-fcos2'><span class='c-fn' style='float:left !important;'>"+$.i18n("JAVASCRIPT00194")+"：</span><span class='c-int qx-conte'>"+data.datas.pallet.email+"</span></li>");
				$("#thisBc3").append("<li class='c-fcos2'><span class='c-fn' style='float:left !important;'>"+$.i18n("JAVASCRIPT00195")+"：</span><span class='c-int qx-conte'>"+data.datas.pallet.tel+"</span></li>");*/
			}else{
				message($.i18n(data.message));
			}
		},
		error: function(data) {
			message($.i18n("JAVASCRIPT003"));
        }
 	});
 	form.render();

  //取消
  	$('#returnFn').on('click',function(){
  		window.location.href = document.referrer;
  	});

  //错误信息
	function message(mess){
		layer.alert(mess,{title:$.i18n("001"),btn:$.i18n("002")},function(index) {
			layer.close(index);
		});
	}	
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

