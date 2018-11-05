layui.use(['form', 'element' ,'layedit','laydate','laypage'], function(){
  var form = layui.form
  ,layer = layui.layer
  ,layedit = layui.layedit
  ,laydate = layui.laydate
  ,element=layui.element
  ,laypage=layui.laypage
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
  
  //分页
  laypage.render({
    elem: 'demo4'
    ,count: 100
    ,first: false
    ,last: false
  });
  
   laydate.render({
    elem: '#date'
  });
  laydate.render({
    elem: '#date1'
  });
  
  //新增指数模板
  form.on('submit(savePriceIndexTemp)', function(data){
	  $.ajax({
		  url:infoServer+'/price/saveOrUpdatePriceIndexTemp.json',
		  type:'post',
		  data:JSON.stringify(data.field),
		  headers : {  
              'Content-Type' : 'application/json;charset=utf-8'  
          },
		  success:function(res){
			  if(res.status == 0){
				  window.location.href=infoServer+'/om/price/priceTemp.htm';
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
		  url:infoServer+'/price/setPriceIndexTempStatus.json?uuid='+obj.uuid,
		  type:'get',
		  success:function(res){
			 if(res.status == 0){
				 window.location.href = infoServer+'/om/price/priceTemp.htm';
			 } else{
				 layer.alert(res.message);
			 }
		  }
	  });
  });
  
  //修改
  $('.editFn').on('click',function(){
	var str = $(this).attr('attr-data');
	var obj = JSON.parse(str);
	// 请求弹框
      $.ajax({
           type:"get",
           url:"../../info/bomb/price/temp/edit.html",
           success:function(data){
               //弹框运行
               layer.open({
                type: 1,
                area: ['400px','320px'],
//                btn: ['保存', '取消'],
                title:'编辑指数模板',
                btnAlign: 'c' ,//按钮居中
                content: data,
                yes: function(index, layero){
                  layer.close(index);// 关闭当前弹框
                },
                btn2: function(index, layero){
                  alert('取消')
                }
              });
              //手动渲染
              form.render();
              //取消
              $('#ext').on('click',function(){
            	 $('.layui-layer-shade').remove();
            	 $('.layui-layer').remove();
              });
              
              $('#uuid').val(obj.uuid);
              $('#indexName1').val(obj.indexName);
              $('#indexCode').val(obj.indexCode);
              $('#priority').val(obj.priority);
           }
       });
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
                area: ['400px','320px'],
//                btn: ['保存', '取消'],
                title:'新增行业指数',
                btnAlign: 'c' ,//按钮居中
                content: data,
                yes: function(index, layero){
                  layer.close(index);// 关闭当前弹框
                },
                btn2: function(index, layero){
                  alert('取消')
                }
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
   
  
  //跳转到查询列表
  $('.selectFn').on('click',function(){
	  var key = $('#indexName').val();
	  window.location.href=infoServer+'/om/price/priceTemp.htm?indexName='+key;
  });
  
    // 删除函数
//  $('.delFn').on('click',function(){
//      //弹框运行
//      layer.confirm('真的删除行么', function(index){
//      obj.del(); //删除对应行（tr）的DOM结构
//      layer.close(index);
//      });
//  });
//    
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