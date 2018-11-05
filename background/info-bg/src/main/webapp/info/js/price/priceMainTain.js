//JS限制input输入的为数字并且有小数的时候最多保留三位小数
function myNumberic(e) {
	if(e.value == "e"){
		e.value = "";
	}
}
	
layui.use(['form','layer','upload', 'element' ,'layedit','laydate','laypage','table'], function(){
  var form = layui.form
  ,layer = layui.layer
  ,table = layui.table
  ,layedit = layui.layedit
  ,laydate = layui.laydate
  ,element=layui.element
  ,laypage=layui.laypage
  ,upload = layui.upload
  ,aydate = layui.laydate;
  
	element.on('tab(demo)', function(data){
		 if(data.index == 0){
		     	window.location.href=infoServer+"/om/price/priceTemp.htm";
		     }else{
		    	 var indexDate = (new Date()).Format("yyyy-MM-dd");
		    	 //alert(indexDate);
		     	window.location.href=infoServer+"/om/price/priceMainTain.htm?indexDate="+indexDate;
		}
	});
	//计算涨跌幅事件监听 "两位小数点:"+a.toFixed(2)
	$('.trOwn').each(function(){
		var ele1=$(this).find('.smeiValue');//指数值(>=0，可保留3位小数)	
		var ele2=$(this).find('.riseFall'); //涨跌*(可保留4位小数)
		var eleRes=$(this).find('.result');
		$(this).find('.smeiValue,.riseFall').on('input propertychange',function(){
			if(ele1.val()!='' && ele2.val()!='' && ele1.val() != 0){
				eleRes.val(parseFloat(parseFloat(ele2.val()) / (parseFloat(ele1.val()) + parseFloat(ele2.val())) * 100).toFixed(2) + '%');
			}
		});
	})
	
	var load;
	//Excel文件上传组件
	upload.render({
	    elem: '#importExcel'
	    ,url: infoServer + '/price/importExcel.json'
	    ,auto: false
	    ,exts: 'xls|xlsx'
	    ,multiple: true
	    ,bindAction: '#ensure'
    	,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
    		if($(".layui-upload-file").val() !=null && $(".layui-upload-file").val()!=""){
    			load = layer.load(2); //上传loading
    		}else{
    			//未选中文件
    			layer.alert("请选择文件");
    		}
    	 }
	    ,done: function(res){
	    	if(res){
	    		layer.close(load);
	    		layer.alert(res.message);
	    	}
	    }
	 });
	
  
  $('#exportExcel').on('click',function(){
	  var html  = '';
	  var date1  = '<div class="layui-inline">';
	  	  date1 += ' <label class="layui-form-label" style="color:red; white-space:nowrap;">开始日期 *：</label>';
	   	  date1 += ' <input  type="text" name="startTime" class="layui-input" id="date1">';
	   	  date1 += '</div>';
      var date2  = '<div class="layui-inline">';
      	  date2 += ' <label class="layui-form-label" style="color:red; white-space:nowrap;">结束日期 *：</label>';
	   	  date2 += ' <input  type="text" name="endTime" class="layui-input" id="date2">';
	   	  date2 += '</div>'; 
	   	  
	  html = date1 + date2;
	  
	  layer.open({
	    title: '导出指数Excel数据'
	    ,content: html
	    ,btn: ['确定导出', '取消']
	  	,yes: function(index, layero){
	  		// 数据校验
	  		var d1 = $('#date1').val();
	  		var d2 = $('#date2').val();
	  		if(!d1){
	  			alert('请选择开始时间');
	  		}
	  		if(!d2){
	  			alert('请选择结束时间');
	  		}
	  		
	  		var param = {};
	  		param['startTime'] = d1;
	  		param['endTime'] = d2;
	  		var load = layer.load();
	  		window.location.href = infoServer + '/om/price/exportExcel.htm?startTime='+d1+'&endTime='+d2;
	  		layer.close(index);
	  		layer.close(load);
	  		
	    }
	  }); 
	  
	  
	  laydate.render({
		    elem: '#date1'
		    ,format: 'yyyy-MM-dd' //可任意组合
		    ,value: ''
		    ,theme: 'grid'
	  });
	  
	  laydate.render({
		    elem: '#date2'
		    ,format: 'yyyy-MM-dd' //可任意组合
		    ,value: ''
		    ,theme: 'grid'
	  });
  });
	
   //时间选择控件	
  laydate.render({
    elem: '#date'
    ,format: 'yyyy-MM-dd' //可任意组合
    ,value: ''
  });
	
  //查询
  $('.selectFn').on('click',function(){
	  var indexDateStr = $('#date').val();
	  var indexName = $('#indexName').val();
	  window.location.href=infoServer+'/om/price/priceMainTain.htm?indexDate='+indexDateStr+'&indexName='+indexName;
	  return false;
  });
  
  //查询重置
  $('#reset').on('click',function(){
	  $('#date').val('');
	  $('#indexName').val('');
  });
  
  //检查指数值 和涨跌* 值
  function checkPriceIndexParams(i,item){
	  if((item.smeiValue != '' && item.riseFall == '') || (item.smeiValue == '' && item.riseFall != '')){
		  layer.alert("第"+ (i + 1) +"行 指数值和涨跌* 必填");
		  return false;
	  }
	  return true;
  }
  //新增指数模板
  form.on('submit(saveOrUpdatePriceIndex)', function(data){
	
	  var param = {};
	  var list = [];
	  //
	  $('.trOwn').each(function(){
		  var item = {};
		  for(var dt in data.field){
			  	if(dt == 'riseFallRate'){
			  		var riseFallRate = $(this).find('.'+dt).val();
			  		riseFallRate = riseFallRate.substr(0,riseFallRate.indexOf('%'));
			  		item[dt] = riseFallRate;
			  	}else{
			  		item[dt] = $(this).find('.'+dt).val();
			  	}
		  }
		  list.push(item);
	  })
	  param['indexDate'] = $('#date').val();
	  param['vos'] = list;
	  
	  //检查指数值 和 涨跌* 值
	  for(var i = 0; i < list.length; i ++){
		  if(!checkPriceIndexParams(i,list[i])){
			  return false;
		  }
	  }
	  
	  $.ajax({
		  url:infoServer+'/price/saveOrUpdatePriceIndex.json',
		  type:'post',
		  data:JSON.stringify(param),
		  headers : {  
              'Content-Type' : 'application/json;charset=utf-8'  
          },
		  success:function(res){
			  if(res.status == 0){
				  var indexDate = (new Date()).Format("yyyy-MM-dd");
				  //alert(indexDate);
				  window.location.href=infoServer+'/om/price/priceMainTain.htm?indexDate='+indexDate;
			  }else{
				  layer.alert(res.message);
			  }
		  },
		  error:function(res){
		  }
	  });
	  
	  return false;
  });
  
  //跳转到新增
  $('.addFn').on('click',function(){
      // 请求弹框
      $.ajax({
           type:"get",
           url:"../../info/bomb/price/temp/add.html",
           success:function(data){
               //弹框运行
               layer.open({
            	id:'dlog',
                type: 1,
                area: ['400px','300px'],
//                btn: ['保存', '取消'],
                title:'新增行业指数',
                btnAlign: 'c' ,//按钮居中
                content: data
              });
              //手动渲染
              form.render();
              
             //取消
              $('#ext').on('click',function(){
            	 $('.layui-layer-shade').remove();
            	 $('.layui-layer').remove();
              });
           }
       });
  })
  
  //全选/全不选
  form.on('checkbox(layTableAllChoose)', function(data){  
      var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');  
       child.each(function(index, item){  
        item.checked = data.elem.checked;  
      });  
      form.render('checkbox');  
  });  
   
  //表格数据重置
  $('#tableReset').on('click',function(){
	  var list = [];
	  var child = $('.checkAll').parents('table').find('tbody input[type="checkbox"]');  
      child.each(function(index, item){  
    	  if(item.checked){
    		  list.push( $(item).next().next().val());
    	  }
      });  
	  
	  $.ajax({
		  url:infoServer+'/price/deletePriceIndex.json',
		  type:'post',
		  headers : {  
              'Content-Type' : 'application/json;charset=utf-8'  
          },
          data:JSON.stringify(list),
		  success:function(res){
			  if(res.status == 0){
				  window.location.href=infoServer+'/om/price/priceMainTain.htm';
			  }else{
				  layui.alert(res.message);
			  }
		  },
		  error:function(res){
			  
		  }
	  });
  });
  
  //跳转到查询列表
//  $('.selectFn').on('click',function(){
//	  var key = $('#indexName').val();
//	  window.location.href=infoServer+'/om/price/priceTemp.htm?indexName='+key;
//  });
  
  Date.prototype.Format = function (fmt) {
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt))
	fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o){
	    if (new RegExp("(" + k + ")").test(fmt)) {
	fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	}
	    }
	    return fmt;
	}
  
})