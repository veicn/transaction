layui.use(['layer', 'form', 'jquery','laypage', 'upload','table', 'laydate','own'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var own = layui.own;
	$('.checkAll').removeAttr('checked');
	$('.check').removeAttr('checked');
	//单选与全选
	$('body').on('click','.checkAll',function(){
		if($(this).is(':checked')){
			$('.check').each(function(){
				$(this).prop('checked',true);
			});
			$('#match').removeClass('c-btncas');
			$('#match').addClass('c-btnsub');
		}else{
			$('.check').each(function(){
				$(this).removeAttr('checked');
			});
			$('#match').addClass('c-btncas');
			$('#match').removeClass('c-btnsub');
		}
	})
	$('body').on('click','.check',function(){
		var flag =true;
		var flags =false;
		$('.check').each(function(){
			if(!$(this).is(':checked')){
				flag = false;
			} else {
				flags=true;
			}
		});
		if (flag){
			$('.checkAll').prop('checked',true);
		} else {
			$('.checkAll').removeAttr('checked');
		}
		if (flags){
			$('#match').removeClass('c-btncas');
			$('#match').addClass('c-btnsub');
		} else {
			$('#match').addClass('c-btncas');
			$('#match').removeClass('c-btnsub');
		}
	})
	
	//匹配
	$('#match').on('click',function(){
		var ids="";
		$('.check').each(function(){
			if($(this).is(':checked')){
				var id= $(this).val();
				ids=ids+id+";";
			} 
		});
		ids=encodeURIComponent(ids);
		if ($(this).hasClass('c-btnsub')){
			var uuid = own.getHref('uuid');
			if (!uuid){
				layer.alert($.i18n("JAVASCRIPT0020"), {title:$.i18n("001"),btn:$.i18n("002")},function(index) { layer.close(index); 
				return false;
				});
			}
			window.location.href=shipServer+"/shipPact/shipPactMatch.htm?shipPactUuid="+uuid+"&agreementUuid="+ids;
		}
	});
	$('.back').on('click',function(){
		window.location.href=shipServer+"/shipPact/shipPactList.htm";
	})
});
