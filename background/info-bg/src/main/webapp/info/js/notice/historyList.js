layui.use(['layer', 'form','jquery','laypage','element','laydate','layedit'], function(){
    var layer = layui.layer
    ,form = layui.form,  // 载入form表单
    table = layui.table, //表格
    element = layui.element, // tap
    laydate = layui.laydate // 日期
    window.$ = layui.jquery;    //zai'lu'
  
    // 执行tap
    element.on('tab(demo)', function(data){
        if(data.index){
        	window.location.href=infoServer+"/om/notice/historyNotice.htm";
        }else{
        	window.location.href=infoServer+"/om/notice/notice.htm"
        }
    });
    
    //回车查询
	$("body").keydown(function() {
	    if (event.keyCode == "13") {//keyCode=13是回车键
	        $('.historyBtw').click();
	    }
	});    

    // 执行日期
    laydate.render({
        elem: '.test1' //指定元素
    });
    laydate.render({
        elem: '.test2' //指定元素
    });

      //编辑函数
      $('.editilFn').on('click',function(){
    	  var data = $(this).attr('attr-data');
    	  var objData = JSON.parse(data);
	        // 请求弹框
	    	window.location.href = infoServer+"/om/notice/edit.htm?uuid="+ objData.uuid;
      })
      

        // 发布函数
        $('.faFn').on('click',function(){
        	 var data = $(this).attr('attr-data');
        	 var objData = JSON.parse(data);
        	 
//        	 alert(JSON.stringify(objData));
//        	 return;
            //弹框运行
            layer.confirm('确定要发布该公告吗', function(index){
            	$.ajax({
            		url:infoServer+'/notice/pushNotice.json?uuid='+objData.uuid,
            		type:'get',
            		success:function(res){
            			if(res.status == 0){
            				layer.alert(res.message);
            				window.location.href=infoServer+"/om/notice/notice.htm";
            			}else{
            				layer.alert(res.message);
            			}
            		}
            	});
                layer.close(index);
            });
          });

          // 删除函数
          $('.delFn').on('click',function(){
        	 var data = $(this).attr('attr-data');
         	 var objData = JSON.parse(data);
//         	 alert(JSON.stringify(objData));
//         	 return;
            //弹框运行
            layer.confirm('确认删除吗', function(index){
            	$.ajax({
            		url:infoServer+'/notice/deleteNotice.json?uuid='+objData.uuid,
            		type:'get',
            		success:function(res){
            			if(res){
            				if(res.status == 0){
            					layer.alert(res.message);
            					window.location.href=infoServer+"/om/notice/historyNotice.htm";
            				}else{
            					layer.alert(res.message);
            				}
            			}
            		}
            	});
                layer.close(index);
            });
          });

          // 新增函数
          $('.addFn').on('click', function(){
            window.location.href=infoServer+"/om/notice/addlist.htm";
          })
          
          //平台维护查询
          $('.historyBtw').on('click', function(){
        	var validEnd = $('#validEnd').val();
        	var validBegin =  $('#validBegin').val(); 
            window.location.href=infoServer+'/om/notice/historyNotice.htm?validBegin='+validBegin+'&validEnd='+validEnd;
          })
    
    
  }); 