//JS限制input输入的为数字并且有小数的时候最多保留三位小数
function myNumberic(e,len) {
    var obj=e.srcElement || e.target;
    var dot=obj.value.indexOf(".");//alert(e.which);
    len =(typeof(len)=="undefined")?3:len;
    var  key=e.keyCode || e.which;
    if(key==8 || key==9 || key==46 || (key>=37  && key<=40))//这里为了兼容Firefox的backspace,tab,del,方向键
        return true;
    if (key<=57 && key>=48) { //数字
        if(dot==-1){//没有小数点
            return true;
        }else if(obj.value.length<=dot+len){//小数位数
            return true;
        }else if(obj.value.length>=dot+len){//小数位数
        	obj.value="";
            return true;
        }else if((key==46) && dot==-1){//小数点
            return true;
        }
    }       
    return false;
}
	
layui.use(['form', 'element' ,'layedit','laydate','laypage','table','upload'], function(){
  var form = layui.form
  ,layer = layui.layer
  ,table = layui.table
  ,layedit = layui.layedit
  ,laydate = layui.laydate
  ,element=layui.element
  ,laypage=layui.laypage
  ,aydate = layui.laydate
  ,upload = layui.upload;
  
  
	element.on('tab(demo)', function(data){
		 if(data.index == 0){
		     	window.location.href=infoServer+"/om/symbol/symbolIndex.htm";
		     }else{
		    	 var tradeDate = (new Date()).Format("yyyy-MM-dd");
		    	 //alert(indexDate);
		     	window.location.href=infoServer+"/om/symbol/symbolPriceMainTain.htm?tradeDate="+tradeDate;
		}
	});
	//计算涨跌幅事件监听 "两位小数点:"+a.toFixed(2)
	$('.trOwn').each(function(){
		var ele1=$(this).find('.smeiValue');//指数值(>=0，可保留3位小数)	
		var ele2=$(this).find('.riseFall'); //涨跌*(可保留4位小数)
		var eleRes=$(this).find('.result');
		$(this).find('.smeiValue,.riseFall').on('input propertychange',function(){
			if(ele1.val()!='' && ele2.val()!=''){
				eleRes.val(parseFloat(parseFloat(ele2.val()) / (parseFloat(ele1.val()) + parseFloat(ele2.val())) * 100).toFixed(2) + '%');
			}
		});
	})
	
   //时间选择控件	
  laydate.render({
    elem: '#date'
    ,format: 'yyyy-MM-dd' //可任意组合
    ,value: ''
  });
	
  //查询
  $('.selectFn').on('click',function(){
	  var tradeDateStr = $('#date').val();
	  var symbolName = $('#symbolName').val();
	  window.location.href=infoServer+'/om/symbol/symbolPriceMainTain.htm?tradeDate='+tradeDateStr+'&symbolName='+symbolName;
	  return false;
  });
  
  //查询重置
  $('#reset').on('click',function(){
	  $('#date').val('');
	  $('#symbolName').val('');
  });
  
  //新增指数模板
  form.on('submit(saveOrUpdateSymbolPrice)', function(data){
	
	  var param = {};
	  var list = [];
	  //
	  $('.trOwn').each(function(){
		  var item = {};
		  for(var dt in data.field){
			  	if(dt == 'riseFallRate'){
			  		var riseFallRate = $(this).find('.'+dt).val();
			  		riseFallRate = riseFallRate.substr(0,riseFallRate.indexOf('%') -1);
			  		item[dt] = riseFallRate;
			  	}else{
			  		item[dt] = $(this).find('.'+dt).val();
			  	}
		  }
		  list.push(item);
	  })
	  param['tradeDate'] = $('#date').val();
	  param['vos'] = list;
	  
	  $.ajax({
		  url:infoServer+'/symbolPrice/saveOrUpdateSymbolPrice.json',
		  type:'post',
		  data:JSON.stringify(param),
		  headers : {  
              'Content-Type' : 'application/json;charset=utf-8'  
          },
		  success:function(res){
			  if(res.status == 0){
				  var tradeDate = (new Date()).Format("yyyy-MM-dd");
				  //alert(indexDate);
				  window.location.href=infoServer+'/om/symbol/symbolPriceMainTain.htm?tradeDate='+tradeDate;
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
		  url:infoServer+'/symbolPrice/deleteSymbolPrice.json',
		  type:'post',
		  headers : {  
              'Content-Type' : 'application/json;charset=utf-8'  
          },
          data:JSON.stringify(list),
		  success:function(res){
			  if(res.status == 0){
				  window.location.href=infoServer+'/om/symbol/symbolPriceMainTain.htm';
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
  $("#export").on('click',function(){
	 var tradeDate= $("#date").val();
	 window.location.href=infoServer+"/symbol/exportList.htm?tradeDate="+tradeDate;
  })
  var load;
	//Excel文件上传组件
	upload.render({
	    elem: '#importExcel'
	    ,url: infoServer + '/symbol/importExcel.json'
	    ,auto: false
	    ,exts: 'xls|xlsx'
	    ,multiple: true
	    ,bindAction: '#ensure'
  	,before: function(obj){ //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
  		if($(".layui-upload-choose").text() !=null && $(".layui-upload-choose").text() !=""){
  			load = layer.load(); //上传loading
  		}else{
  			//未选中文件
  			layer.alert("请选择文件");
  		}
  	 }
	    ,done: function(res){
	    	if(res){
	    		layer.close(load);
	    		 layer.open({
	    	            type: 1,
	    	            shadeClose: true, //点击遮罩关闭
	    	            shade: 0.8,
	    	            area: ['30%', '30%'],
	    	            closeBtn: 1,
	    	            content: res.message,
	    	            end: function () {
	    	                location.reload();
	    	            }

	    	        });
	    	}
	    }
	 });
})