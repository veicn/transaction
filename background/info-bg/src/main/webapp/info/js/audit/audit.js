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
							});
							$("#channel").html('<option value=""></option>' + optionstring);
						}
						form.render('select');
					}
			});
		})
		
		//删除
		$('.deleteInfo').on('click',function(){
			var dataStr = $(this).attr('attr-data');
			var dataObj = JSON.parse(dataStr);
			$.ajax({
				url: infoServer + '/audit/delInfo.json?uuid='+dataObj.uuid,
				type:'get',
				success:function(res){
					if(res){
						if(res.status == 0){
							var param = getSearchParam();
							window.location.href = infoServer+"/om/audit/audit.htm"
							+ "?channelM=" + param.channelM
							+ "&channel=" + param.channel
							+ "&keyWords=" + param.keyWords
							+ "&pubBeginTime=" + param.pubBeginTime
							+ "&pubEndTime=" + param.pubEndTime;
						}else{
							layer.alert(res.message);
						}
					}
				}
			});
		});
		
		//获取查询参数
		function getSearchParam(){
			var params = {};
			var channelM = $('#channelM').val(); //主频道uuid
			var channel = $('#channel').val(); //子频道uuid
			var keyWords = $('#keyWords').val(); //关键词
			var pubBeginTime = $('#test1').val(); //发布开始时间
			var pubEndTime = $('#test2').val(); //发布结束时间
			params['channelM'] = channelM;
			params['channel'] = channel;
			params['keyWords'] = keyWords;
			params['pubBeginTime'] = pubBeginTime;
			params['pubEndTime'] = pubEndTime;
			return params;
		}
		
		//查询
		$('.searchFn').on('click', function(){
			var params = getSearchParam();
			
			window.location.href = infoServer+"/om/audit/audit.htm"
				+ "?channelM=" + params.channelM
				+ "&channel=" + params.channel
				+ "&keyWords=" + params.keyWords
				+ "&pubBeginTime=" + params.pubBeginTime
				+ "&pubEndTime=" + params.pubEndTime;
		})
		
		//重置
		$('.resetFn').on('click', function(){
			//alert($('#test1').val());
			$('#channelM').val(""); //主频道uuid
			$('#channel').val(""); //子频道uuid
			$('#keyWords').val(""); //关键词
			$('#test1').val(''); //发布开始时间
			$('#test2').val(""); //发布结束时间
			$('#status').val(""); //资讯状态
			//alert($('#test1').val());
			form.render('select');//重置频道栏目选中
			return false;
		});
		
		//新增
		$('.addFn').on('click', function() {
			window.location.href=infoServer+"/om/info/info.htm";
			return false;
		})

		//审核
		$('.auditLink').on('click', function() {
			var params = $(this).attr("attr-data");
			var objParams = JSON.parse(params);
			window.location.href=infoServer+"/om/audit/infoDetail1.htm?uuid="+ objParams.uuid + "&type=" + objParams.type;
		})
		
		//编辑
		$('.editLink').on('click', function() {
			var key = $(this).attr("attr-data");
			window.location.href=infoServer+"/om/info/edit.htm?uuid=" +key;
		})
		
	    //详情
	    $('.detailLink').on('click',function(){
	    	var params = $(this).attr("attr-data");
	    	var objParams = JSON.parse(params);
			//window.location.href=infoServer+"/om/audit/infoDetail1.htm?uuid="+ objParams.uuid + "&type=" + objParams.type;
	    	window.open(infoServer+"/om/audit/infoDetail1.htm?uuid="+ objParams.uuid + "&type=" + objParams.type);
	    })

		// 批量审核通过
		$('.batchAuditBtn').on('click', function() {
			var arrUuids = [];
			$("input:checkbox[name='test']:checked").each(function() { // 遍历name=test的多选框
				arrUuids.push($(this).val());  // 每一个被选中项的值
			});
			
			var params = {};
			params.uuids = arrUuids;
			
			//弹出确认对话框
			layer.confirm('是否批量审核通过', {icon: 3, title:'提示'},function(){
				$.ajax({
					type : "POST",
					url : infoServer+"/audit/batchAudit.json",
					traditional: true, //阻止深入序列化
					data: params,
					success : function(data) {
						if(data) {
							layer.alert("审核通过" + data.successCount + "条，失败" + data.failCount + "条");
						} else {
							layer.alert("操作失败");
						}
						window.location.href=infoServer+"/om/audit/audit.htm";
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

		// 选项卡
		element.on('tab(demo)', function(data) {
			if(data){
				if(data.index == 1){
					window.location.href = infoServer+"/om/audit/publish.htm";
				}
			}
		});

		// 全选
		$('.checkBoxAll .layui-form-checkbox').on('click', function() {
			if ($('.checkBoxAll').find('input').get(0).checked) {
				$('.children').each(function() {
					$(this).find('.layui-form-checkbox').addClass('layui-form-checked');
					$(this).find('input[type="checkbox"]').get(0).checked = true;
				})
			} else {
				$('.children').each(
					function() {
						$(this).find('.layui-form-checkbox').removeClass('layui-form-checked');
						$(this).find('input[type="checkbox"]').get(0).checked = false;
					})
			    }
			}
		)

		// 单选
		$('.children').on('click', function() {
			if ($(this).find('input').get(0).checked) {
				if ($(this).parents('tbody').find('.layui-form-checkbox').length == $(this).parents('tbody').find('.layui-form-checked').length) {
					$('.checkBoxAll').find('.layui-form-checkbox').addClass('layui-form-checked');
					$('.checkBoxAll').find('input[type="checkbox"]').get(0).checked = true;
				}
			} else {
				$('.checkBoxAll').find('.layui-form-checkbox').removeClass('layui-form-checked');
				$('.checkBoxAll').find('input[type="checkbox"]').get(0).checked = false;
			}
		})

	});
