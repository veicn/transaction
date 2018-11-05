layui.use(['layer', 'form','jquery','laypage','element','laydate','layedit', 'table'], function(){
    var layer = layui.layer
    ,form = layui.form,  // 载入form表单
    table = layui.table, //表格
    element = layui.element, // tap
    laydate = layui.laydate, // 日期
    laypage = layui.laypage;   // 载入page分页
    window.$ = layui.jquery;    //zai'lu'
    var uploader = new plupload.Uploader({ //实例化一个plupload上传对象
		browse_button : 'file-list',
		url : infoServer+'/om/crudeOil/crudeOilImport.json',
		flash_swf_url : 'domesticPrice/Moxie.swf',
		silverlight_xap_url : 'domesticPrice/Moxie.xap',
	});
	uploader.init(); //初始化
	//上传按钮
	$('#upload').click(function(){
		if($("#file-list").val() !=null && $("#file-list").val()!=""){
			uploader.start(); //开始上传
		}else{
			//未选中文件
			layer.alert("请选择文件");
		}
	});
	//下载模板
	$("#download").click(function (){  
        var path = encodeURI(infoServer+"/info/template/crudeOilTemplate.xlsx","UTF-8");

        var form = $("<form>");   //定义一个form表单
            form.attr('style','display:none');   //在form表单中添加查询参数
      		form.attr('target','');
            form.attr('method','post');
            form.attr('action',path);  
        
             $('body').append(form);  //将表单放置在web中
//              form.append(input1);   //将查询参数控件提交到表单上  
             form.submit();   //表单提交

    }); 
	//上传按钮
	$('#export').click(function(){
		window.location.href=infoServer+"/om/crudeOil/exportList.htm"
	});
	//绑定文件添加进队列事件
	uploader.bind('FilesAdded',function(uploader,files){
		for(var i = 0, len = files.length; i<len; i++){
			var file_name = files[i].name; //文件名
			//构造html来更新UI
			$('#file-list').val(file_name);
		}
	});
	
	//绑定文件添加进队列事件
	uploader.bind('FileUploaded',function(uploader,file,responseObject){
		result = $.parseJSON(responseObject.response);
		if(result.message == null){
			window.location.reload();
		}else{
			layer.alert(result.message,function(){
				window.location.reload();
			});
		}
	});

	    // 执行日期
	    laydate.render({
	        elem: '#dateStart' //指定元素
	    });
	    laydate.render({
	        elem: '#dateEnd' //指定元素
	    });

	    // 编辑函数
	    $('.editFn').on('click', function(){
	    	var strData= $(this).attr('attr-data');
			var objData = JSON.parse(strData);
	        window.location.href=infoServer+"/om/crudeOilInsert/crudeOilInsert.htm?uuid="+objData.uuid+
	        																	"&api="+objData.api+
	        																	"&sulphur="+objData.sulphur+
	        																	"&acidity="+objData.acidity+
	        																	"&freezingPoint="+objData.freezingPoint+
	        																	"&flashPoint="+objData.flashPoint+
	        																	"&viscosity="+objData.viscosity+
	        																	"&carbonResidue="+objData.carbonResidue+
	        																	"&nickel="+objData.nickel+
	        																	"&vanadium="+objData.vanadium+
	        																	"&yield="+objData.yield+
	        																	"&simpleDate="+objData.simpleDate+
	        																	"&crudeNameE="+objData.crudeNameE+
            																	"&crudeNameC="+encodeURIComponent(objData.crudeNameC)+
            																	"&crudeId="+objData.crudeId+
            																	"&extend1="+objData.extend1+
            																	"&extend2="+objData.extend2+
            																	"&extend3="+objData.extend3;

	      })
      
		//删除函数
		$('.delFn').on('click',function(){
			var objData = JSON.parse($(this).attr('attr-data'));	
			layer.confirm('是否删除？', {icon: 3, title:'请确认'}, function(index){
				$.ajax({
					url:infoServer+'/crudeOil/crudeOilDelete.json?uuid='+objData.uuid,
					type:'get',
					success:function(data){
						console.log(JSON.stringify(data))
						if(data.status == 0){
							window.location.reload();
						}
					}
				});
				layer.close(index);
			}); 
			
		})

    // 新增函数
    $('.addFn').on('click', function(){
        window.location.href=infoServer+"/om/crudeOilInsert/crudeOilInsert.htm";
       // window.location.href=infoServer+"/om/crudeOilAdd/crudeOilAdd.htm";
      
      })
      
      
	  //将form表单序列化之后,在转为json对象
	  $.fn.serializeObject = function()
		{
			var o = {};
			var a = this.serializeArray();
			$.each(a, function() {
				if (o[this.name] !== undefined) {
					if (!o[this.name].push) {
						o[this.name] = [o[this.name]];
					}
					o[this.name].push(this.value || '');
				} else {
					o[this.name] = this.value || '';
				}
			});
			return o;
		};
       
		//校验
		function validator(data){
			  if(data){
				  if(isNaN(data.api)){
					  layer.alert('API度必须是数字类型');
					  return false;
				  }
				  if(isNaN(data.sulphur)){
					  layer.alert('含硫量必须是数字');
					  return false;
				  }
				  if(isNaN(data.acidity)){
					  layer.alert('酸值必须是数字');
					  return false;
				  }
			  }
			  return true;
		}
		
  }); 