(function () {
	commonCPY.dateValidate()
	// 圆圈点击切换
	 $('.vl-nav-l .stowage-steps li').click(function () {
        var index = $(this).index();
        // console.log(index)
        if(index==2){
        }else{
            $('.lib_voyabox>div').eq(index).show().siblings().hide();
            $('.lib_tabox>ul>li').eq(index).addClass('active').siblings().removeClass('active');
        }

    });
    $('.lib_tabox ul li').click(function () {
        var index = $(this).index();
        $(this).addClass('active').siblings().removeClass('active');
        $('.lib_voyabox>div').eq(index).show().siblings().hide();
    });

    // 点击save进行tab页转换
        var tableMaxFlag=$("#tableMaxFlag").val();
        console.log(tableMaxFlag)
        var step=$("#step").val();
        if(step){
        	
        }else{
        	var step=1
        }
        
        console.log(step)
        // tableMaxFlag=tableMaxFlag-1;
        step=step-1;
    function tab(tableMaxFlag,step){
        $('.lib_tabox>ul>li').eq(tableMaxFlag).addClass('active').siblings().removeClass('active');
        $('.lib_voyabox>div').eq(tableMaxFlag).show().siblings().hide();
        $('.lib_Menubox>ul>li').eq(step).addClass('active').siblings().removeClass('active');
        $('.lib_Contentbox>div').eq(step).show().siblings().hide();
    }
    tab(tableMaxFlag,step)

    $('.lib_tabox ul li').click(function () {
        var index = $(this).index();
        $(this).addClass('active').siblings().removeClass('active')
        $('.lib_voyabox>div').eq(index).show().siblings().hide();
    })

    $('.lib_Menubox ul li').click(function () {
        var index = $(this).index();
        $(this).addClass('active').siblings().removeClass('active')
        $('.lib_Contentbox>div').eq(index).show().siblings().hide();
    })

    $('.addBtn').click(function(){
    	var html ="";
    	html += '<tr>'
    	html += '<td><inpu type="text" id="" name ="" /></td>'
    	html += '<td><inpu type="text" id="" name ="" /></td>'
    	html += '<td><inpu type="text" id="" name ="" /></td>'
    	html += '<td><inpu type="text" id="" name ="" /></td>'
    	html += '<td><inpu type="text" id="" name ="" /></td>'
    	html += '<td><inpu type="text" id="" name ="" /></td>'
    	html += '<td><inpu type="text" id="" name ="" /></td>'
    	html += '<td><inpu type="text" id="" name ="" /></td>'
    	html += '</tr>';
    	$("#addTb").append(html)
    })
    //数组转对象 // 王洋
    function arrayToJson(arr,obj){//数组转对象
		if($.isArray(arr)&&arr.length){
			obj=$.isEmptyObject(obj)?obj:{};
			for(var i=0;i<arr.length;i++){
				if(obj[arr[i].name]){
					if(!$.isArray(obj[arr[i].name])){
						obj[arr[i].name]=[obj[arr[i].name]];
					};
					obj[arr[i].name].push(arr[i].value);
				}else{
					obj[arr[i].name]=arr[i].value;
				};
			};
			return obj;
		};
		return arr;
	}


	var savebtnFlag=true;
    layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
    	var layer = layui.layer,
    	form = layui.form,  // 载入form表单
    	table = layui.table;
    	window.$ = layui.jquery;
    //新增船舶在途中
    	$(document).on('click','.savebtn',function(){

    		var flag = true;
			if($(".tableCheck td").length<=0){
                layer.alert("Please add data",{title:'Info', btn:['OK']});
                flag = false;
                return false;
			}
    		
    		$('.tableCheck').find('input[type="text"]').each(function(i,el){
	    		if($(el).val() == ''){
	    			layer.alert("content cann't be empty",{title:'Info', btn:['OK']});
	    			flag = false;
	    			return false;
	    		}
    	})
    	var arr = [];
    	var obj={};
    	$('.table-bordered>tbody>tr').each(function(i,el){
    		arr.push(arrayToJson( $(el).find('input').serializeArray(),obj));
    	})
    	//delete arr[0];
    	
    	var conUuid = $("#conUuid").val();
    	var tableMaxFlag = 0;
    	var params = {};
    	params.volist = arr
    	params.conUuid = conUuid;
    	params.tableMaxFlag = tableMaxFlag;
    	//请求后台
    	if (flag && savebtnFlag){
    		savebtnFlag = false;
        	$.ajax({
     		   	contentType : 'application/json',  
    	        type:"post",
    	        dataType:"json",
    	        url:shippingServer+'/pages/back/TransitLoading/addLoding.json' ,
    	        data: JSON.stringify(params),
    			success : function(msg) {
     		   		savebtnFlag = true;
    				if (msg != null) {
    					layer.alert(msg.message,{title:'Info', btn:['OK']},function(){
    						location.href= shippingServer+"/pages/back/TransitLoading/index.htm?confUUid="+msg.confUUid+"&tableMaxFlag="+msg.tableMaxFlag+"";
    					})
    				}

    			},
    			error:function(){
                    savebtnFlag = true;
                }
         	})
    	}
    })
    


		var subajaxFlag = true;
    $('.asdadsasd').on('click',function(){
    	var flag = true;
    	$('#formid').find('input[type="text"]').each(function(i,el){
    		if($(el).val() == ''){
    			layer.alert("content cann't be empty",{title:'Info', btn:['OK']});
    			flag = false;
    			return false;
    		}
    	})
    	
    	if(flag){
    		var portOfLoading= $("input[name='portOfLoading']")[0].value;
    		var vesselName= $("input[name='vesselName']")[0].value;
    		var imo= $("input[name='imo']")[0].value;
    		var product= $("input[name='product']")[0].value;
    		var quantity= $("input[name='quantity']")[0].value;
    		var quantityTons= $("input[name='quantityTons']")[0].value;
    		var laycanStart= $("input[name='laycanStart']")[0].value;
    		var laycanEnd= $("input[name='laycanEnd']")[0].value;
    		var portOfLoading = $("#portOfLoading").val();
    		var comUuid = $("#comUuid").val();
    		var uuid = $("#uuid").val();
    		var step = 1;
    		
    		var params = {};
    		
    		params.comUuid = comUuid;
    		params.uuid = uuid;
    		params.step = step;
    		params.portOfLoading = portOfLoading;
    		params.vesselName = vesselName;
    		params.product = product;
    		params.quantity = quantity;
    		params.quantityTons = quantityTons;
    		params.imo = imo;
    		params.laycanStart = laycanStart;
    		params.laycanEnd = laycanEnd;
    		params.tableMaxFlag = 1;
    		//提交
			if(subajaxFlag){
				subajaxFlag = false;
                submitAjax(params);
            }

    	}
    	
    	
    })
    //第二步提交
  	$("#theSecind").on('click',function(){
  		var flag = true;
    	$('#fomeTwo').find('input[type="text"]').each(function(i,el){
    		if($(el).val() == ''){
    			layer.alert("content cann't be empty",{title:'Info', btn:['OK']});
    			flag = false;
    			return false;
    		}
    	})
    	if(flag){
    		//预计添加
    		var portOfLoading= $("input[name='portOfLoading']")[0].value;
    		var vesselName= $("input[name='vesselName']")[0].value;
    		var imo= $("input[name='imo']")[0].value;
    		var product= $("input[name='product']")[0].value;
    		var quantity= $("input[name='quantity']")[0].value;
    		var quantityTons= $("input[name='quantityTons']")[0].value;
    		
    		
    		
    		var comUuid = $("#comUuid").val();
    		var uuid = $("#uuid").val();
    		var step = 2;
    		
    		var norTendered= $("input[name='norTendered']")[0].value;
    		var anchorAweigh= $("input[name='anchorAweigh']")[0].value;
    		var pobDatetimeOne= $("input[name='pobDatetimeOne']")[0].value;
    		var pobBerth= $("input[name='pobBerth']")[0].value;
    		var firstLineDatetime= $("input[name='firstLineDatetime']")[0].value;
    		var firstLineEtb= $("input[name='firstLineEtb']")[0].value;
    		var firstLineBerth= $("input[name='firstLineBerth']")[0].value;
    		var allFast= $("input[name='allFast']")[0].value;
    		var params = {};
    		
    		params.comUuid = comUuid;
    		params.uuid = uuid;
    		params.step = step;
    		
    		params.norTendered = norTendered;
    		params.anchorAweigh = anchorAweigh;
    		params.pobDatetimeOne = pobDatetimeOne;
    		params.pobBerth = pobBerth;
    		params.firstLineDatetime = firstLineDatetime;
    		params.firstLineEtb = firstLineEtb;
    		params.firstLineBerth = firstLineBerth;
    		params.allFast = allFast;
    		params.tableMaxFlag = 1;
    		
    		
    		//预计添加
    		params.portOfLoading = portOfLoading;
    		params.vesselName = vesselName;
    		params.product = product;
    		params.quantity = quantity;
    		params.quantityTons = quantityTons;
    		params.imo = imo;

            if(subajaxFlag){
                subajaxFlag = false;
                submitAjax(params);
            }
    		
    	}
  	})
    
  	
  	
    
   // console.log(obj)
   
    //第三步序列号表单 和table
    $("#loadPortAddFormAdd").on('click',function(){
    	var flag = true;
    	$('#formTrsea').find('input[type="text"]').each(function(i,el){
    		if($(el).val() == ''){
    			layer.alert("content cann't be empty",{title:'Info', btn:['OK']});
    			flag = false;
    			return false;
    		}
    	})
    	
    	$('#seiaBody').find('input[type="text"]').each(function(i,el){
    		if($(el).val() == ''){
    			layer.alert("content cann't be empty",{title:'Info', btn:['OK']});
    			flag = false;
    			return false;
    		}
    	})
    	
    	if(flag){
    		//预计添加
    		var portOfLoading= $("input[name='portOfLoading']")[0].value;
    		var vesselName= $("input[name='vesselName']")[0].value;
    		var imo= $("input[name='imo']")[0].value;
    		var product= $("input[name='product']")[0].value;
    		var quantity= $("input[name='quantity']")[0].value;
    		var quantityTons= $("input[name='quantityTons']")[0].value;
    		
    		
    		
    		
    		
    		
    		var comUuid = $("#comUuid").val();
    		var uuid = $("#uuid").val();
    		var step = 3;
    		var tanksInspectionDatetime= $("input[name='tanksInspectionDatetime']")[0].value;
    		var tanksInspectionRemarks= $("input[name='tanksInspectionRemarks']")[0].value;
    		var independentInspection= $("input[name='independentInspection']")[0].value;
    		var independentInspectionObq= $("input[name='independentInspectionObq']")[0].value;
    		var independentInspectionDatetime= $("input[name='independentInspectionDatetime']")[0].value;
    		var independentInspectionRemarks= $("input[name='independentInspectionRemarks']")[0].value;
    		var shoreArmsConnecting= $("input[name='shoreArmsConnecting']")[0].value;
    		var commencedLoadingDatetime= $("input[name='commencedLoadingDatetime']")[0].value;
    		var etc= $("input[name='etc']")[0].value;
    		var completedLoadingDatetime= $("input[name='completedLoadingDatetime']")[0].value;
    		
    		var params = {};
    		
    		params.comUuid = comUuid;
    		params.uuid = uuid;
    		params.step = step;
    		params.tableMaxFlag = 1;
    		params.tanksInspectionDatetime = tanksInspectionDatetime;
    		params.tanksInspectionRemarks = tanksInspectionRemarks;
    		params.independentInspection = independentInspection;
    		params.independentInspectionObq = independentInspectionObq;
    		params.independentInspectionDatetime = independentInspectionDatetime;
    		params.independentInspectionRemarks = independentInspectionRemarks;
    		params.shoreArmsConnecting = shoreArmsConnecting;
    		params.commencedLoadingDatetime = commencedLoadingDatetime;
    		params.etc = etc;
    		params.completedLoadingDatetime = completedLoadingDatetime;
    		
    		//预计添加
    		params.portOfLoading = portOfLoading;
    		params.vesselName = vesselName;
    		params.product = product;
    		params.quantity = quantity;
    		params.quantityTons = quantityTons;
    		params.imo = imo;
    		
    		
    		
    		
    		var arr = [];
    		var obj={};
    		$('.layui-table>tbody>tr').each(function(i,el){
    			
    			arr.push(arrayToJson( $(el).find('input').serializeArray(),obj));
    		})
    		
    		
    		params.progreeList = arr;

            if(subajaxFlag){
                subajaxFlag = false;
                submitAjax(params);
            }
    		
    	}
    })
    
    //第四步 数据
    $("#fourthSteps").on('click',function(){
    	
    	var flag = true;
    	$('#formsia').find('input[type="text"]').each(function(i,el){
    		if($(el).val() == ''){
    			layer.alert("content cann't be empty",{title:'Info', btn:['OK']});
    			flag = false;
    			return false;
    		}
    	})
    	
    	
    	if(flag){
    		//预计添加
    		var portOfLoading= $("input[name='portOfLoading']")[0].value;
    		var vesselName= $("input[name='vesselName']")[0].value;
    		var imo= $("input[name='imo']")[0].value;
    		var product= $("input[name='product']")[0].value;
    		var quantity= $("input[name='quantity']")[0].value;
    		var quantityTons= $("input[name='quantityTons']")[0].value;
    		
    		
    		
    		
    		
    		
    		var comUuid = $("#comUuid").val();
    		var uuid = $("#uuid").val();
    		var step = 4;
    		var shoreArmDisconnecting= $("input[name='shoreArmDisconnecting']")[0].value;
    		var cargoSurvey= $("input[name='cargoSurvey']")[0].value;
    		var blMetricTons= $("input[name='blMetricTons']")[0].value;
    		var blLongTons= $("input[name='blLongTons']")[0].value;
    		var ltr= $("input[name='ltr']")[0].value;
    		var bbls= $("input[name='bbls']")[0].value;
    		var leaveDatetime= $("input[name='leaveDatetime']")[0].value;
    		var nextPortId= $("input[name='nextPortId']")[0].value;
    		var nextPort= $("input[name='nextPort']")[0].value;
    		
    		var params = {};
    		
    		params.comUuid = comUuid;
    		params.uuid = uuid;
    		params.step = step;
    		params.tableMaxFlag = 1;
    		params.shoreArmDisconnecting = shoreArmDisconnecting;
    		params.cargoSurvey = cargoSurvey;
    		params.blMetricTons = blMetricTons;
    		params.blLongTons = blLongTons;
    		params.ltr = ltr;
    		params.bbls = bbls;
    		params.leaveDatetime = leaveDatetime;
    		params.nextPortId = nextPortId;
    		params.nextPort = nextPort;
    		
    		//预计添加
    		params.portOfLoading = portOfLoading;
    		params.vesselName = vesselName;
    		params.product = product;
    		params.quantity = quantity;
    		params.quantityTons = quantityTons;
    		params.imo = imo;





            if(subajaxFlag){
                subajaxFlag = false;
                submitAjax(params);
            }
    		
    	}
		 
    })
    
    
    function submitAjax(params){
    	$.ajax({
		   	contentType : 'application/json',  
		    type:"post",
		    dataType:"json",
		    traditional:true,
		    url:shippingServer+'/pages/back/LoadPort/loadingPortAdd.json' ,
		    data: JSON.stringify(params),
			success : function(msg) {
				if (msg != null) {
					if(msg.status != ''){
						layer.alert(msg.message,{title:'Info', btn:['OK']},function(){
							window.location.href = shippingServer+"/pages/back/TransitLoading/index.htm?confUUid="+msg.confUUid+"&tableMaxFlag="+msg.tableMaxFlag+"&step="+msg.step+"";
						})
						
					}else {
						layer.alert(msg.message,{title:'Info', btn:['OK']},function(){
						})
					}
				}
			},
			error:function(){
				layer.alert("System Error!",{title:'Info', btn:['OK']},function(){});
			}
		})
    }
    });
})();
$(function () {
    commonCPY.select();
    commonCPY.picker();
    var startTime = commonCPY.picker("startTime");
    var endTime = commonCPY.picker("endTime");
    startTime()
    endTime()

});
