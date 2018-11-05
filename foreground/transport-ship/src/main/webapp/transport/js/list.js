layui.use(['layer', 'form','jquery','laypage'], function(){
  var layer = layui.layer
  ,form = layui.form,  // 载入form表单
  table = layui.table, //表格
  laypage = layui.laypage;   // 载入page分页
  window.$ = layui.jquery;    //zai'lu'

  
  //执行一个laypage实例 分页
  laypage.render({
    elem: 'ry-page' //注意，这里的 ry-page 是 ID，不用加 # 号
    ,count: 40, //数据总数，从服务端得到
    jump:function(obj, first){
    	console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
    	console.log(obj.limit); //得到每页显示的条数
    	
    	//首次不执行
	    if(!first){
	      //do something
	    }
    	
    }
  });
  
  //查询
  form.on('submit(formId)', function(data){
  	console.log(data)
	  console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
	  console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
	  console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
	  return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
	});
  
  
  //新增
  $('#addList').on('click',function(){
  	$.ajax({
		 	type:"get",
		 	url:"../bomb/addHtml.html",
		 	success:function(data){
		 		//弹框运行
		 		layer.open({
				  type: 1,
				  area: ['680px','350px'],
				  btn: ['保存', '取消'],
				  title:'新增',
				  content: data,
				  yes: function(index, layero){
				    layer.close(index);// 关闭当前弹框
				  },
				  btn2: function(index, layero){
			    	alert('取消')
			  	}
				});
				form.render();
		 	}
		 });
  	return false;
  })
  
  //修改
  $('.editFn').on('click',function(){
  	$.ajax({
		 	type:"get",
		 	url:"../bomb/addHtml.html",
		 	success:function(data){
		 		//弹框运行
		 		layer.open({
				  type: 1,
				  area: ['680px','350px'],
				  btn: ['保存', '取消'],
				  title:'新增',
				  content: data,
				  yes: function(index, layero){
				    layer.close(index);// 关闭当前弹框
				  },
				  btn2: function(index, layero){
			    	alert('取消')
			  	}
				});
				form.render();
		 	}
		 });
  	return false;
  })
  

		
	//删除函数
	$('.delFn').on('click',function(){
		layer.alert('我是删除');
	})
	
	 
  
  
}); 