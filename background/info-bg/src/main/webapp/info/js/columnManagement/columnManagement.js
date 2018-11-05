layui.use(['layer', 'table','form','element'], function(){
	var table = layui.table,
	element = layui.element,
	layer = layui.layer,
	form = layui.form;

	  //一些事件监听
	/*  element.on('tab(demo)', function(data){
	    console.log(data);
	  });*/
	
	form.on('radio(radio1)', function(data){
		if(data.value=='01'){
			$("#examineNoDesc").show();
		}else{
			$("#examineNoDesc").hide();
		}
	});
	
	// 选项卡
	element.on('tab(demo)', function(data) {
		if(data){
			if(data.index == 0){
				//window.location.href = infoServer+"/om/columnManagement/columnManagement.htm";
				
				table.reload('listReload', {
					page: {
						curr: 1 // 重新从第 1 页开始
					}  
					,where: {
						status: '10',
					}
				});
				$('.layui-table-page').attr('align','right');
				$('.layui-table-body').css("overflow","hidden");
				
			}
		}
		if(data){
			if(data.index == 1){
				//window.location.href = infoServer+"/om/columnManagement/approved.htm";
				
				table.reload('listReload', {
					page: {
						curr: 1 // 重新从第 1 页开始
					}  
					,where: {
						status: '20',
					}
				});
				$('.layui-table-page').attr('align','right');
				$('.layui-table-body').css("overflow","hidden");
				
			}
		}
		if(data){
			if(data.index == 2){
				//window.location.href = infoServer+"/om/columnManagement/auditFailed.htm";
				
				table.reload('listReload', {
					page: {
						curr: 1 // 重新从第 1 页开始
					}  
					,where: {
						status: '01',
					}
				});
				$('.layui-table-page').attr('align','right');
				$('.layui-table-body').css("overflow","hidden");
				
			}
		}
	});
	
	
	// 配置参数
	var tableOptions = {
		id:'listReload', //生成 Layui table 的标识 id，必须提供，用于后文刷新操作
		url: infoServer + '/om/columnManagement/audit.json', // 请求地址  
		method:'POST',
		page: {layout: ['prev', 'page', 'next', 'skip']
		,first:'首页',last:'尾页',prev:"上一页",next:"下一页"}
	};
	// 初始化
	table.init('demo', tableOptions);
	
	$('.layui-table-page').attr('align','right');
	$('.layui-table-body').css("overflow","hidden");
	
  
	// 重新载入表格
	function tabReload(num){
		table.reload('listReload', {
			page: {
				curr: num // 重新从第 1 页开始
			}
		});
		$('.layui-table-page').attr('align','right');
		$('.layui-table-body').css("overflow","hidden");
 	}
	

	// 查询
	$('#btn').on('click', function(){
		table.reload('listReload', {
			page: {
				curr: 1 // 重新从第 1 页开始
			}  
			,where: {
				columnTitle: $('#columnTitle').val(),
			}
		});
		$('.layui-table-page').attr('align','right');
		$('.layui-table-body').css("overflow","hidden");
	});
  

	$("body").keydown(function() {
	    if (event.keyCode == "13") {//keyCode=13是回车键
	        $('#btn').click();
	    }
	});
	
	// 监听工具条
	table.on('tool(demo)', function(obj){
		var oldData = obj.data;  //获得当前行数据
		
		if(obj.event === 'check'){//审核
			editSetFn({
				'data':oldData
			});
		} else if(obj.event === 'show'){// 查看
			showItemFn({
				'data':oldData
			});
		}
	});
	
	// 审核
	function editSetFn(obj){
		// 第一步： 请求弹框
		$.ajax({
			url:infoServer + '/info/bomb/columnManagement/reviewColumn.html',
			success:function(data){
				// 自带弹框
				var checkLayer = layer.open({
					type:1,
					content: data,
					title: '专栏审核',
					area: '600px'
				});
				
				/*var statusData=$('#editReviewColumn input[name="status"]:checked').val();*/				
				
				// 第二部：回显数据->编辑
				$('#editReviewColumn input[name="id"]').val(obj.data.id);
				$('#editReviewColumn input[name="columnTitle"]').val(obj.data.columnTitle);
				$('#editReviewColumn input[name="columnBrief"]').val(obj.data.columnBrief);
				$('#editReviewColumn input[name="columnCover"]').val(obj.data.columnCover);
				$('#editReviewColumn #image').attr('src',obj.data.columnCover);
				$('#editReviewColumn #image').css('display','block');
				$('.layui-layer').css({'top':($('.layui-layer-shade').height() - $('.layui-layer').height())/2});
				/*$('#editReviewColumn input[name="status"]').val();
				$('#examineNoDesc').val();*/

				$('#c').on('click',function(){
					 layer.close(checkLayer);// 关闭当前弹框
				 });
				/*提交*/
				$('#submit').on('click', function(){
					
						var data = {};
						var params = $('form').serializeArray();
						$.each(params, function() {
							data[this.name] = this.value;
						});
						
						if(!data.status){
							layer.alert("请选择是否通过！")
							return false;
						}
						
						if(data.status == '01' && !data.examineNoDesc){
							layer.alert("请输入不通过原因！")
							return false;
						}
						
						$.ajax({
							url:infoServer+'/om/columnManagement/updateColumn.json',
							type:'post',
							data:JSON.stringify(data),
							dataType:"JSON",
							async: false, 
							headers : {  
								'Content-Type' : 'application/json;charset=utf-8'  
							},
							success:function(res){
								window.location.href = document.referrer;
								/*window.location.reload();*/
							}
						});	
				});
				form.render();
			},
			error:function(data){
				layer.alert('失败');
			}
		})
	}
	
	
	//查看
	function showItemFn(obj){
		$.ajax({
			url:infoServer + '/info/bomb/columnManagement/columnDetail.html',
			success:function(data){
				// 自带弹框
				layer.open({
					type:1,
					content: data,
					title: '详情查看',
					area: '600px'
				});
				// 第二部：回显数据->编辑
				$('#editReviewColumn input[name="id"]').val(obj.data.id);
				$('#editReviewColumn input[name="columnTitle"]').val(obj.data.columnTitle);
				$('#editReviewColumn input[name="columnBrief"]').val(obj.data.columnBrief);
				$('#editReviewColumn input[name="columnCover"]').val(obj.data.columnCover);
				$('#editReviewColumn input[type="radio"][name="status"][value="'+obj.data.status+'"]').attr('checked','checked');
				$('#editReviewColumn #image').attr('src',obj.data.columnCover);
				$('#editReviewColumn #image').css('display','block');
				$('.layui-layer').css({'top':($('.layui-layer-shade').height() - $('.layui-layer').height())/2});
				var statusData=obj.data.status;
				if(statusData=='01'){
					$("#examineNoDesc").show();
				}
				$('#editReviewColumn textarea[id="examineNoDesc"]').val(obj.data.examineNoDesc);
				form.render();
			},
			error:function(data){
				layer.alert('失败');
			}
		})
	}
	
}); 