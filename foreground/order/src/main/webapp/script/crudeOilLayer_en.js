$(function(){

    var appServer = "";//查询服务器
    var retFun = "";//回调函数名
    $(".orderOilLayer").click(function () {
        oilLayerClick(this);
    });

    function oilLayerClick(lay){
        retFun = $(lay).attr("retFun");
        appServer = $(lay).attr("appServer");

        $("#currLid").val($(lay).attr("lid"));
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
        var url = "../";
        if(appServer != undefined && appServer != ""){
            url = appServer;
        }
        var url = url + "longTermContractContain/queryCrudeOilInfos.json";
        $.post(url, {crudeName:oilNm}, function(list){
            //console.log(list);
            var content = getContent(list);
            layui.use('layer', function(){
                var layer = layui.layer;
                var curIndex = layer.open({
                    title: 'Crude Oil Grade',
                    shade : false,
                    area: ['800px', '510px'],
                    content: $(content).html(),
                    btn: ["Confirm"],
                    yes: function(index, layero){
                        //console.log(layero.html());
                        var retDate = new Array();
                        layero.find(".c-tabcos1").each(function () {
                            if ($(this).find("input:radio").is(":checked")) {
                                var data = new Array();
                                data.push($(this).attr("crudeid"));
                                $(this).find("td").each(function(index,element) {
                                    //console.log(index,$(element).text());
                                    if(index>0){
                                        data.push($(element).text());
                                    }
                                })
                                retDate.push(data);
                            }

                        })
                        layer.close(index); //如果设定了yes回调，需进行手工关闭

                        if(retFun != undefined && retFun != ""){//调用返回函数
                            var f = eval(retFun);
                            new f(retDate, $("#crudeDataArray tr").length-1);
                        }
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
                $(".layui-layer-content").css("height","350px");
                $(".layui-layer-btn0").css("margin-top","20px");
            });
        });
    }

    function getContent(dataList){
        var content = 		'<div style="display:none">';
        //search
        content = content + 	'<div class="layui-form-item">';
        content = content + 		'<label class="layui-form-label">Name</label>';
        content = content + 		'<div class="layui-input-block">';
        content = content + 			'<input type="text" id="queryCrudeNm" placeholder="Enter grade" class="layui-input" style="width:250px;display:inline;"/>';
        content = content + 			'&nbsp;&nbsp;<button class="layui-btn searchBtn">Find</button>';
        content = content + 		'</div>';
        content = content + 	'</div>';

        //table
        content = content + 	'<div class="c-tabdiv"><table>';
        //表头
        content = content + 		'<tr>';
        content = content + 			'<th class="tabl-line">Choose</th>';
        content = content + 			'<th>Origin</th>';
        content = content + 			'<th>Grade</th>';
        content = content + 			'<th>Crude type</th>';
        content = content + 			'<th>API @60 F</th>';
        content = content + 			'<th>Sulphur m%</th>';
        content = content + 			'<th>Acid (mgKOH/g)</th>';
        content = content + 			'<th>Carbon Residue/%</th>';
        content = content + 			'<th>Ni/ ppm</th>';
        content = content + 			'<th>Van/ ppm</th>';
        content = content + 			'<th>>350°C mass yield/%</th>';
        // content = content + 			'<th>质量收率/%</th>';
        // content = content + 			'<th>镍/ ppm</th>';
        // content = content + 			'<th>钒/ ppm</th>';
        // content = content + 			'<th>残炭/%</th>';
        // content = content + 			'<th>硫含量/ m%</th>';
        content = content + 		'</tr>';
        //表体数据展示
        for(var i in dataList){
            content = content + 	'<tr class="c-tabcos1" crudeid="' + dataList[i].id + '">';
            content = content + 		'<td class="tabl-line"><input type="radio" value="' + i +'" name="radio"/></td>';
            content = content + 		'<td>' + dataList[i].originNameE + '</td>';
            content = content + 		'<td>' + dataList[i].crudeNameE + '</td>';
            content = content + 		'<td>' + dataList[i].catagoryNameE + '</td>';
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
    window.orderOilLayerBindClick = function(){
        $('.orderOilLayer').unbind("click"); //移除click
        $(".orderOilLayer").click(function () {
            oilLayerClick(this);
        });
    };

});