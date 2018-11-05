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
    	var indexDate = (new Date()).Format("yyyy-MM-dd");
    	window.location.href= infoServer+'/om/baseprice/basePrice.htm?indexDate='+indexDate;
    }
});
  
  laydate.render({
    elem: '#date'
  });
  laydate.render({
    elem: '#date1'
  });
  
  //新增基价模板
  form.on('submit(saveBasePriceTemp)', function(data){
	  
	  $.ajax({
		  url:infoServer+'/baseprice/saveOrUpdateBasePriceTemp.json',
		  type:'post',
		  data:JSON.stringify(data.field),
		  headers : {  
              'Content-Type' : 'application/json;charset=utf-8'  
          },
		  success:function(res){
			  if(res.status == 0){
				  window.location.href=infoServer+'/om/baseprice/basePriceTemp.htm';
			  }else{
				  layer.alert(res.message);
			  }
		  },
		  error:function(res){
		  }
	  });
	  
	  return false;
  });
  
  //设置启用/禁用
  $('.setStatusFn').on('click',function(){
	  var str = $(this).attr('attr-data');
	  var obj = JSON.parse(str);
	  $.ajax({
		  url:infoServer+'/baseprice/setBasePriceTempStatus.json?uuid='+obj.uuid,
		  type:'get',
		  success:function(res){
			 if(res.status == 0){
				 window.location.href = infoServer+'/om/baseprice/basePriceTemp.htm';
			 } else{
				 layer.alert(res.message);
			 }
		  }
	  });
  });
  
//跳转到查询列表
  $('.selectFn').on('click',function(){
	  var key = $('#baseName').val();
	  window.location.href=infoServer+'/om/baseprice/basePriceTemp.htm?baseName='+key;
	  return false;
  });
  
  //修改
  $('.editFn').on('click',function(){
	var str = $(this).attr('attr-data');
	var obj = JSON.parse(str);
	
	// 请求弹框
      $.ajax({
           type:"get",
           url:"../../info/bomb/baseprice/temp/edit.html",
           success:function(data){
               //弹框运行
               layer.open({
                type: 1,
                area: ['370px','350px'],
//                btn: ['保存', '取消'],
                title:'编辑基价模板',
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
              
              $('#uuid').val(obj.uuid);
              $('#baseName1').val(obj.baseName);
              $('#baseCode').val(obj.baseCode);
              $('#baseArea').val(obj.baseArea);
           }
       });
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
	            area: ['370px','350px'],
	            title:'保存基价模板',
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