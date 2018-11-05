layui.use(['layer', 'form','jquery','laypage','element','laydate','layedit'], function(){
  var layer = layui.layer,
  form = layui.form,  // 载入form表单
  table = layui.table, //表格
  element = layui.element, // tap
  laydate = layui.laydate, // 日期
  laypage = layui.laypage;   // 载入page分页

  window.$ = layui.jquery;    //zai'lu'

  // 百度编辑器实例
  var ue = UM.getEditor('container');

  $('.fabuFn').on('click', function(data){
    form.verify({
      content: function() {
        if(UM.getEditor('container').getContentTxt() === "") {
        // 红框
        $('.edui-container').css('border','solid 1px red')
          return '请输入内容'
        }else{
          $('.edui-container').css('border','')
        }
      }
    });
  });

    //监听提交
    form.on('submit(demo1)', function(data){
    	data.field['content'] = UM.getEditor('container').getContentTxt();
    	data.field['contentWithHtml'] = UM.getEditor('container').getContent();
    	//data.field.splice('upfile',1);
//    	alert(JSON.stringify(data.field));
//    	return;
    	$.ajax({
    		contentType:"application/json",
    		url:infoServer+'/notice/saveNotice.json',
    		type:'post',
    		data:JSON.stringify(data.field),
    		success:function(res){
    			 layer.alert(res.message,function(){
    				 window.location.href=infoServer+'/om/notice/historyNotice.htm';
  			     });
    		}
    	});
    	
    	return false; //防止重复提交
    });

}); 