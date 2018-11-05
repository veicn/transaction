$(function(){

	var appServer = "";//查询服务器
	var retFun = "";//回调函数名
	var retIndex = 1;//选择油种行
	var openFlg = 0;//0：窗口未弹出 1：窗口弹出
	$(".crudeOilLayer").click(function () {
		oilLayerClick(this);
    });
	
	function oilLayerClick(lay){
		// 选择油种(防止连续点击多次弹出)
		if(openFlg == 1) {
			return;
		}
		
    	retFun = $(lay).attr("retFun");
		appServer = $(lay).attr("appServer");
        //retIndex = $(lay).data("index");
        retIndex = $(lay).attr("data-index");
		layerOpen();
	}
	
	/**
	function retFunction(data){
		for(var i in data){
			alert(data[i][0]);
		}
	}
	*/
	
	function layerOpen(oilNm){
		openFlg = 1;
		var url = "../";
		if(appServer != undefined && appServer != ""){
			url = appServer;
		}
		var url = url + "crudeoillobby/queryCrudeOilInfos.json";
		$.post(url, {crudeName:oilNm}, function(list){
		  	//console.log(list);
		  	var content = getContent(list,oilNm);
			layui.use('layer', function(){
				// 禁止滚动条
				$(".s-body").css("overflow-y", "hidden");
				$(".s-body").css("margin-right", "21px");
				$(".fabucaigou-con-right").css("margin-right", "10px");
				
			  var layer = layui.layer;
			  var curIndex = layer.open({
				  	type: 1,
				   	title: '原油品种',
				   	shade : [0.6, '#393D49'],
					scrollbar : false,
	                area: ['800px', '550px'],
					content: $(content).html(),
				  	btn: ["确定"],
				  	btnAlign: 'c' ,//按钮居中
					yes: function(index, layero){
						//console.log(layero.html());
						var retData = new Array();
						var checkOilId = layero.find("input:radio:checked").val();
						for(var i = 0; i < list.length; i++) {
							if (checkOilId == list[i].id) {
                                retData = list[i];
                                break;
							}
						}
						
						if (checkOilId) {
							layer.close(index); //如果设定了yes回调，需进行手工关闭
							openFlg = 0;
						} else {
							layer.alert("请选择油种!");
							return false;
						}
						
						if(retFun != undefined && retFun != ""){//调用返回函数
							var f = eval(retFun);
							new f(retData, retIndex);
						}
						
						// 禁止滚动条
						$(".s-body").css("overflow-y", "auto");
						$(".s-body").css("margin-right", "0");
						$(".fabucaigou-con-right").css("margin-right", "0");
	                },
	                cancel: function(index, layero){ 
	    				// 禁止滚动条
	    				$(".s-body").css("overflow-y", "auto");
	    				$(".s-body").css("margin-right", "0");
	    				$(".fabucaigou-con-right").css("margin-right", "0");
	                	
	                	layer.close(index);
                	    openFlg = 0;
	                	return false; 
	                }
				});
				
				$('.searchBtn').unbind("click"); //移除click
			   	$(".searchBtn").click(function(){//重新注册点击事件
			   	   var queryNm = $("#queryCrudeNm").val();
			       //layer.closeAll();
			       layer.close(curIndex);
			       layerOpen(queryNm);
			   	});

                $(document).off('keydown', '#queryCrudeNm'); //keydown
                $(document).on('keydown', '#queryCrudeNm', function() {
                    if(event.keyCode == 13) {
                        var queryNm = $("#queryCrudeNm").val();
                        layer.close(curIndex);
                        layerOpen(queryNm);
                    }
                });
			   	
			   	$(".layui-layer-btn").css("text-align","center");
			   	$(".layui-layer-btn0").css("padding","5px 30px");
			   	$(".layui-layer-btn0").css("background-color","#317ee7");
			   	$(".layui-layer-btn0").css("font-size","16px");
			   	$(".layui-layer-content").css("overflow-x","auto");
			   	$(".layui-layer-content").css("height","390px");
			   	$(".layui-layer-content").css("margin-top","20px");
			   	$(".layui-layer-content").css("margin-left","20px");
			   	$(".layui-layer-btn0").css("margin-top","20px");
//			   	$(".layui-layer-title").css("color","#fff");
//			   	$(".layui-layer-title").css("font-size","16px");
//			   	$(".layui-layer-title").css("background-color","#317ee7");
			});
		});
    }
	
    function getContent(dataList,queryNm){
    	if(queryNm == undefined){
    		queryNm = "";
		}
    	var content = 		'<div style="display:none" class="">';
		//search
		content = content + 	'<div class="layui-form-item">';
		content = content + 		'<label class="layui-form-label">名称</label>';
		content = content + 		'<div class="layui-input-block">';
		content = content + 			'<input type="text" id="queryCrudeNm" placeholder="请输入要查询的油种" value="' + queryNm + '" class="layui-input" style="width:250px;display:inline;"/>';
		content = content + 			'<a class="searchBtn"><i class="c-ico cg-sous1" style="position:relative;right:20px;top:0px"></i></a>';
//		content = content + 			'&nbsp;&nbsp;<button class="layui-btn searchBtn">查询</button>';
		content = content + 		'</div>';
		content = content + 	'</div>';
	
		//table
		content = content + 	'<div class="lay-tabdiv"><table class="lay-tctab">';
		//表头
		content = content + 		'<tr>';
		content = content + 			'<th class="tabl-line">选择</th>';
        content = content + 			'<th>原油产地</th>';
		content = content + 			'<th>油种</th>';
		content = content + 			'<th>原油类型</th>';
		content = content + 			'<th>API度</th>';
		content = content + 			'<th>硫含量/ m%</th>';
		content = content + 			'<th>酸值/ (mgKOH/g)</th>';
		content = content + 			'<th>残炭/%</th>';
		content = content + 			'<th>镍/ ppm</th>';
		content = content + 			'<th>钒/ ppm</th>';
		content = content + 			'<th>> 350°C 质量收率/%</th>';
		// content = content + 			'<th>质量收率/%</th>';
		// content = content + 			'<th>镍/ ppm</th>';
		// content = content + 			'<th>钒/ ppm</th>';
		// content = content + 			'<th>残炭/%</th>';
		// content = content + 			'<th>硫含量/ m%</th>';
		content = content + 		'</tr>';
		//表体数据展示
		var tcos = 1;
		for(var i in dataList){
			if(tcos==1){
				tcos=2
			}else{
				tcos=1
			}
			content = content + 	'<tr class="trdata lay-tabcos' + tcos +'">';
			content = content + 		'<td class="tabl-line"><input name="oilCheck" type="radio" value="' + dataList[i].id +'" /></td>';
            content = content + 		'<td>' + dataList[i].originNameC + '</td>';
			content = content + 		'<td>' + dataList[i].crudeNameE + '</td>';
			content = content + 		'<td>' + dataList[i].catagoryNameC + '</td>';
			content = content + 		'<td>' + dataList[i].api + '</td>';
			content = content + 		'<td>' + dataList[i].sulphur + '</td>';
			content = content + 		'<td>' + dataList[i].acidity + '</td>';
			content = content + 		'<td>' + dataList[i].carbonResidue + '</td>';
			content = content + 		'<td>' + dataList[i].nickel + '</td>';
			content = content + 		'<td>' + dataList[i].vanadium + '</td>';
			content = content + 		'<td>' + dataList[i].yield + '</td>';
			// content = content + 		'<td></td>';//520°C 质量收率
			// content = content + 		'<td></td>';//520°C 镍
			// content = content + 		'<td></td>';//520°C 钒
			// content = content + 		'<td></td>';//520°C 残炭
			// content = content + 		'<td></td>';//520°C 硫含量
			content = content + 	'</tr>';
	  		//break;
	  	}
		content = content + 	'</table></div>';
		
		content = content + '</div>';
		
		return content;
    }
   
    /**
     * 外部调用，重新加载控件的click事件
     */
    window.crudeOilLayerBindClick = function(){
    	$('.crudeOilLayer').unbind("click"); //移除click
    	$(".crudeOilLayer").click(function () {
    		oilLayerClick(this);
        });
    };
});