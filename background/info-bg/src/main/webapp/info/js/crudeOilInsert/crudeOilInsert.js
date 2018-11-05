
layui.use(['layer', 'form','laypage', 'table', 'laydate'], function(){
    var layer = layui.layer,
    form = layui.form,  // 载入form表单
    table = layui.table, //表格
    laydate = layui.laydate,
    laypage = layui.laypage;   // 载入page分页
    
    // 接受前页面数据
    if ($.getHref('uuid')){
        $('#uuid').val($.getHref('uuid'));
        $('#api').val($.getHref('api'));
        $('#sulphur').val(decodeURIComponent($.getHref('sulphur')));
        $('#acidity').val(decodeURIComponent($.getHref('acidity')));
        $('#freezingPoint').val(decodeURIComponent($.getHref('freezingPoint')));
        $('#flashPoint').val(decodeURIComponent($.getHref('flashPoint')));
        $('#viscosity').val(decodeURIComponent($.getHref('viscosity')));
        $('#carbonResidue').val(decodeURIComponent($.getHref('carbonResidue')));
        $('#nickel').val(decodeURIComponent($.getHref('nickel')));
        $('#vanadium').val(decodeURIComponent($.getHref('vanadium')));
        $('#yield').val(decodeURIComponent($.getHref('yield')));
        $('#simpleDate').val($.getHref('simpleDate'));
        $('#extend1').val(decodeURIComponent($.getHref('extend1')));
        $('#extend2').val(decodeURIComponent($.getHref('extend2')));
        $('#extend3').val(decodeURIComponent($.getHref('extend3')));
        $('#extend4').val(decodeURIComponent($.getHref('crudeNameE')));
        $('#extend5').val(decodeURIComponent($.getHref('crudeNameC')));
    } else {
        $('#crudeNameE').val($.getHref('crudeNameE'));
        $('#crudeNameC').val(decodeURIComponent($.getHref('crudeNameC')));
        $('#crudeId').val($.getHref('crudeId'));
    }
   
    //执行一个laypage实例 分页
    laypage.render({
      elem: 'ry-page' //注意，这里的 ry-page 是 ID，不用加 # 号
      ,count: 50 //数据总数，从服务端得到
    });

    // 执行日期
    laydate.render({
        elem: '#simpleDate' //指定元素
    });
    
      //确定
	  $('.addFn').on('click',function(){
		   
	  });
	  
	  //返回列表
	  $('.retutnFn').on('click',function(){
		  window.location.href=infoServer+"/om/crudeOil/crudeOil.htm" ;
	  });
	  
	  
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
		/*function validator(data){
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
		};*/
		  //监听提交
		  form.on('submit(demo1)', function(){
			  
			  var data = $('#addForm').serializeObject();
			  
				layer.confirm('确定当前操作吗？', {icon: 3, title:'请确认'}, function(index){
					$.ajax({
						url:infoServer+'/crudeOilInsert/crudeOilInsertAdd.json',	
						type:'post',
						data:JSON.stringify(data),
						headers : {  
		                    'Content-Type' : 'application/json;charset=utf-8'  
		                } ,
						success:function(data){
							if(data.status == 0){
								window.location.href=infoServer+"/om/crudeOil/crudeOil.htm";
							}
						}
					});
					layer.close(index);
				});
			  
			  
		    return false;
		  });
  
  }); 