layui.use(['form', 'element' ,'layedit','laydate','laypage'], function(){
  var form = layui.form
  ,layer = layui.layer
  ,layedit = layui.layedit
  ,laydate = layui.laydate
  ,element=layui.element
  ,laypage=layui.laypage
  ,aydate = layui.laydate;
  
element.on('tab(docDemoTabBrief)', function(data){
	if(data.index == 0){
    	window.location.href= infoServer+'/om/baseprice/basePriceTemp.htm';
    }else{
    	window.location.href= infoServer+'/om/baseprice/basePrice.htm';
    }
  });
  
  laydate.render({
    elem: '#date'
    ,format: 'yyyy-MM-dd' //可任意组合
    ,value: ''
  });
  
  //查询
  $('.select').on('click',function(){
	  var baseDateStr = $('#date').val();
	  var baseArea = $('#baseArea').val();
	  window.location.href=infoServer+'/om/baseprice/basePrice.htm?baseDate='+baseDateStr+'&baseArea='+baseArea;
	  return false;
  });
  
  //查询重置
  $('.reset').on('click',function(){
	  $('#date').val('');
	  $('#baseArea').val('');
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
		  url:infoServer+'/baseprice/deleteBasePrice.json',
		  type:'post',
		  headers : {  
              'Content-Type' : 'application/json;charset=utf-8'  
          },
          data:JSON.stringify(list),
		  success:function(res){
			  if(res.status == 0){
				  window.location.href=infoServer+'/om/baseprice/basePrice.htm';
			  }else{
				  layui.alert(res.message);
			  }
		  },
		  error:function(res){
			  
		  }
	  });
  });
  
  
  //新增基价
  form.on('submit(saveOrUpdateBaesPrice)', function(data){
	  
	  var param = {};
	  var list = [];
	  //
	  $('.trOwn').each(function(){
		  var item = {};
		  for(var dt in data.field){
			 item[dt] = $(this).find('.'+dt).val();
		  }
		  list.push(item);
	  })
	  param['baseDate'] = $('#date').val();
	  param['vos'] = list;
	  
	  $.ajax({
		  url:infoServer+'/baseprice/saveOrUpdateBasePrice.json',
		  type:'post',
		  data:JSON.stringify(param),
		  headers : {  
              'Content-Type' : 'application/json;charset=utf-8'  
          },
		  success:function(res){
			  if(res.status == 0){
				  window.location.href=infoServer+'/om/baseprice/basePrice.htm';
			  }else{
				  layer.alert(res.message);
			  }
		  },
		  error:function(res){
		  }
	  });
	  
	  return false;
  });
  
  //全选/全不选
  form.on('checkbox(layTableAllChoose)', function(data){  
      var child = $(data.elem).parents('table').find('tbody input[type="checkbox"]');  
      child.each(function(index, item){  
        item.checked = data.elem.checked;  
      });  
      form.render('checkbox');  
  });  
  //新增
  $('.addFn').on('click',function(){
	  // 请求弹框
	  $.ajax({
	       type:"get",
	       url:"../../info/bomb/baseprice/temp/add.html",
	       success:function(data){
	           //弹框运行
	           layer.open({
	            type: 1,
	            area: ['600px','350px'],
	            title:'保存基价',
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
  
})