layui.use(['layer', 'form','jquery','element','laydate','layedit'], function(){
//关闭
	$('.closeBtn').on('click',function(){
		if(confirm("您确定要关闭本页吗？")){
			window.opener=null;
			window.open('','_self');
			window.close();
		}
		else{
			
		}
	});
});