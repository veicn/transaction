layui.use(['layer', 'form','jquery','laypage','element','laydate','layedit'], function(){
    var layer = layui.layer
    ,form = layui.form,  // 载入form表单
    table = layui.table, //表格
    element = layui.element, // tap
    laydate = layui.laydate, // 日期
    laypage = layui.laypage;   // 载入page分页
    window.$ = layui.jquery;    //zai'lu'

    // 选择函数
    $('.selFn').on('click', function(){
    	var strData= $(this).attr('attr-data');
		var objData = JSON.parse(strData);
        window.location.href=infoServer+"/om/crudeOilInsert/crudeOilInsert.htm?crudeNameE="+objData.crudeNameE+
        																	"&crudeNameC="+objData.crudeNameC+
        																	"&crudeId="+objData.id;

      });
    
      //返回
	  $('.retutnFn').on('click',function(){
		  window.location.href=infoServer+"/om/crudeOil/crudeOil.htm" ;
	  });
      
  }); 