layui.use([ 'layer', 'form', 'jquery', 'laypage', 'laydate', 'table', 'element' ],
	function() {
		var layer = layui.layer, form = layui.form, // 载入form表单
		table = layui.table, // 表格
		laypage = layui.laypage; // 载入page分页
		window.$ = layui.jquery; // zai'lu'
		laydate = layui.laydate, // 载入时间模块
		element = layui.element; // 选项卡
		
		//根据主频道信息获取子频道列表
		  form.on('select(channelM)', function(data){
			$.ajax({
				url: infoServer+"/audit/getChannels.json",
				type: "GET",
				data: {
					channelMUuid : $('#channelM').val()
				},
					success:function(data){
						var optionstring= "";
						if(data.value == ""){
							$("#channel").html("");
						} else {
							$.each(data, function(index , value){
								optionstring += "<option value=\"" + value.uuid + "\" >" + value.name + "</option>";
								//alert(optionstring);
							});
							$("#channel").html('<option value=""></option>' + optionstring);
						}
						form.render('select');
					}
			});
		  }) 
		  
		  		//获取url(Type)参数数据
				 function GetRequest() {
					var url = location.search; // 获取url中"?"符后的字串
					var theRequest = new Object();
					if (url.indexOf("?") != -1) {
						var str = url.substr(1);
						strs = str.split("&");
						for (var i = 0; i < strs.length; i++) {
							theRequest[strs[i].split("=")[0]] = unescape(strs[i]
									.split("=")[1]);
						}
					}
					return theRequest;
				} 
		  
		//获取查询参数
			function getSearchParam(){
				var params = {};
				var Type = $('#Type').val(); //频道类型
				var channelM = $('#channelM').val(); //主频道uuid
				var channel = $('#channel').val(); //子频道uuid
				var keyWords = $('#keyWords').val(); //关键词
				var pubBeginTime = $('#test1').val(); //发布开始时间
				var pubEndTime = $('#test2').val(); //发布结束时间
				params['Type'] = Type;
				params['channelM'] = channelM;
				params['channel'] = channel;
				params['keyWords'] = keyWords;
				params['pubBeginTime'] = pubBeginTime;
				params['pubEndTime'] = pubEndTime;
				return params;
			}
		  
		  //查询
		$('.searchFn').on('click', function(){
			var params = {};
			var Type = $('#Type').val(); //频道类型
			var channelM = $('#channelM').val(); //主频道uuid
			var channel = $('#channel').val(); //子频道uuid
			var keyWords = $('#keyWords').val(); //关键词
			var pubBeginTime = $('#test1').val(); //发布开始时间
			var pubEndTime = $('#test2').val(); //发布结束时间
			var status = $('#status').val(); //资讯状态
			var Type = $('#Type').val(); //Type频道类型
			
			window.location.href = infoServer+"/om/productOil/ProductOilPublish.htm"
				+ "?Type=" + Type
				+ "&channelM=" + channelM
				+ "&channel=" + channel
				+ "&keyWords=" + keyWords
				+ "&pubBeginTime=" + pubBeginTime
				+ "&pubEndTime=" + pubEndTime
				+ "&status=" + status;
		})
		
		//重置
		$('.resetFn').on('click', function(){
			$('#channelM').val(""); //主频道uuid
			$('#channel').val(""); //子频道uuid
			$('#keyWords').val(""); //关键词
			$('#test1').val(""); //发布开始时间
			$('#test2').val(""); //发布结束时间
			$('#status').val(""); //资讯状态
			form.render("select");
			return false;
		})

		// 新增
		$('.addFn').on('click', function() {
			
			var params = getSearchParam();
			
			window.location.href=infoServer+"/om/info/ProductOil.htm"
			 								+ "?Type=" + params.Type;			

			
			return false;
		})

		// 编辑
		$('.editLink').on('click', function() {
			var key = $(this).attr("attr-data");
			
			var params = getSearchParam();	
			
			window.location.href=infoServer+"/om/info/ProductOilEdit.htm" 
					+ "?uuid=" +key
					 + "&Type=" + params.Type;
		})
		
	    //详情
	    $('.detailLink').on('click',function(){
	    	var params = $(this).attr("attr-data");
	    	var objParams = JSON.parse(params);
			//window.location.href=infoServer+"/om/audit/infoDetail1.htm?uuid="+ objParams.uuid + "&type=" + objParams.type;
	    	window.open(infoServer+"/om/audit/infoDetail1.htm?uuid="+ objParams.uuid + "&type=" + objParams.type);
	    })
	    
	    //精选
	    $('.selected').on('click',function(){
	    	var str = $(this).attr("attr-data");
	    	var obj = JSON.parse(str);
	    	var params = {};
			var channelM = $('#channelM').val(); //主频道uuid
			var channel = $('#channel').val(); //子频道uuid
			var keyWords = $('#keyWords').val(); //关键词
			var pubBeginTime = $('#test1').val(); //发布开始时间
			var pubEndTime = $('#test2').val(); //发布结束时间
			var Type = $('#Type').val(); //Type频道类型
	    	$.ajax({
	    		url:infoServer+'/audit/selected.json?uuid='+obj.uuid,
	    		type:'get',
	    		success:function(res){
	    			if(res.status == 0){
	    				
	    				window.location.href=infoServer+"/om/productOil/ProductOilPublish.htm"
	    					+ "?Type=" + Type
		    				+ "&channelM=" + channelM
		    				+ "&channel=" + channel
		    				+ "&keyWords=" + keyWords
		    				+ "&pubBeginTime=" + pubBeginTime
		    				+ "&pubEndTime=" + pubEndTime; 
	    			}else{
	    				layer.alert(res.message);
	    				return;
	    			}
	    		}
	    	});
	    });
	    
	    //置顶
	    $('.topLink').on('click',function(){
	    	var str = $(this).attr("attr-data");
	    	var obj = JSON.parse(str);
	    	var params = {};
			var channelM = $('#channelM').val(); //主频道uuid
			var channel = $('#channel').val(); //子频道uuid
			var keyWords = $('#keyWords').val(); //关键词
			var pubBeginTime = $('#test1').val(); //发布开始时间
			var pubEndTime = $('#test2').val(); //发布结束时间
			var Type = $('#Type').val(); //Type频道类型
	    	$.ajax({
	    		type:'get',
	    		url:infoServer+'/audit/settingTop.json?uuid='+obj.uuid,
	    		success:function(res){
	    			if(res.status == 0){
	    				
	    				window.location.href=infoServer+"/om/productOil/ProductOilPublish.htm"
	    					+ "?Type=" + Type
		    				+ "&channelM=" + channelM
		    				+ "&channel=" + channel
		    				+ "&keyWords=" + keyWords
		    				+ "&pubBeginTime=" + pubBeginTime
		    				+ "&pubEndTime=" + pubEndTime;
	    			}else{
	    				layer.alert(res.message);
	    				return;
	    			}
	    		}
	    	});
	    });

		// 撤销
		$('.revokeLink').on('click', function() {
			var param = $(this).attr("attr-data");
			var channelM = $('#channelM').val(); //主频道uuid
			var channel = $('#channel').val(); //子频道uuid
			var keyWords = $('#keyWords').val(); //关键词
			var pubBeginTime = $('#test1').val(); //发布开始时间
			var pubEndTime = $('#test2').val(); //发布结束时间
			var Type = $('#Type').val(); //Type频道类型
			
			//弹出确认对话框
			layer.confirm('确认撤销', {icon: 3, title:'提示'},function(){
				$.ajax({
					type: "POST",
					url: infoServer+"/audit/revokeInfo.json",
					data:{
						uuid: param
					},
					dataType: "json",
					success: function(data){
						layer.alert(data.message);
						window.location.href=infoServer+"/om/productOil/ProductOilPublish.htm"
							+ "?Type=" + Type
							+ "&channelM=" + channelM
		    				+ "&channel=" + channel
		    				+ "&keyWords=" + keyWords
		    				+ "&pubBeginTime=" + pubBeginTime
		    				+ "&pubEndTime=" + pubEndTime;
					},
					error: function(data){
						layer.alert("error");
					}
				});
			});
		})

		// 时间插件1
		laydate.render({
			elem : '#test1' // 指定元素
		});
		// 时间插件2
		laydate.render({
			elem : '#test2' // 指定元素
		});
		
		//获取url参数数据
		  function GetRequest() {
			  var url = location.search; //获取url中"?"符后的字串
			  var theRequest = new Object();
			  if (url.indexOf("?") != -1) {
			  var str = url.substr(1);
			  strs = str.split("&");
			  for(var i = 0; i < strs.length; i ++) {
			  theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
			  }
			  }
			  return theRequest;
			  } 
		
		// 选项卡
		element.on('tab(demo)', function(data) {
			if(data){
				if(data.index == 0){
  					var params = getSearchParam();
  					
					window.location.href = infoServer+"/om/productOil/ProductOilAudit.htm"
													 + "?Type=" + params.Type;

				}
			}
		});
	});