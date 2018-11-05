layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	table = layui.table;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	
	//title数字格式化2位小数
	 $(".title2").each(function(){
	       if ($(this).attr('title') != null && $(this).attr('title') != ''){
	    	   $(this).attr("title",formatStr($(this).attr('title'),2,1));
	       }
	   });
	 //title数字格式化3位小数
	 $(".title3").each(function(){
		 if ($(this).attr('title') != null && $(this).attr('title') != ''){
			 $(this).attr("title",formatStr($(this).attr('title'),3,1));
		 }
	 });
	 //span数字格式化2位小数
	 $(".num2").each(function(){
		 if ($(this).text() != null && $(this).text() != ''){
			 $(this).text(formatStr($(this).text(),2,1));
		 }
	 });
	 //span数字格式化3位小数
	 $(".num3").each(function(){
		 if ($(this).text() != null && $(this).text() != ''){
			 $(this).text(formatStr($(this).text(),3,1));
		 }
	 });
	 
	//清空上传
	/*$('.clearFile').on('click',function(){
		$(this).parent('p').find('input').attr('data-address','').attr('title','').val('');
		$(this).attr('hidden','hidden');
	});*/
	//清空上传
	$('body').on('click','.clearFile',function(){
		$(this).parent('p').find('input').attr('data-address','').attr('title','').val('');
		$(this).attr('hidden','hidden');
	});
	//清空上传
	$('body').on('click','.s-ent-close',function(){
		$(this).parent('li').remove();
	});
	//复选框
	$('body').on('click','.s-uncheckbox',function(){
		$(this).toggleClass("s-form-checked");
	});
	//下载附件
	$('body').on('click','.downloadFn',function(){
		var address = $(this).attr('address');
		window.location.href = shipServer+"/download.htm?path="+address;
	});
	
});

//图片上传
/*文件 上传*/
var temp = '<p>';
	temp = temp + '<input type="hidden" class="storePath" value="{{filePath}}" />';
	temp = temp + '<input type="hidden" class="fileName" value="{{fileName}}" />';
	temp = temp + '<input type="hidden" class="fileSize" value="{{fileSize}}" />';
	temp = temp + '<input type="hidden" class="storeName" value="{{fileId}}" /></p>'; 

function fileUpFn(id, clazz,dir,file,id2) {
	 var loadIndex ;
			var options = {
				multi_selection: false, //设置是否多选
				listId: id, 
				itemTemplate: temp, //设置模板
				signatureUrl: configUitl.getOssServerPath(shipServer,dir),
				dir: dir,
				filters: {
					max_file_size: '20M',
					mime_types: [ //上传文件后缀名
						{
							extensions: "jpg,jpeg,bmp,png,rm,rmvb,avi,wmv,mp4,FLV"
						}
					],
				},
				swfUrl: '../../lib/upload/Moxie.swf',
				xapUrl: '../../lib/upload/Moxie.xap',
				success: function(imgUrl) {
					layer.close(loadIndex);
					$(file).parent('li').before('<li><img src="'+imgUrl+'"><a href="javascript:;" class="s-ent-close imgEach" title="" data-name="'+$(id2).find('.fileName').val()+'" data-address="'+imgUrl+'"><i class="s-icon"></i></a></li>');
					/*if ($(file).parent('li').parent('ul li').length >=9){
						$(file).parent('li').remove();
					}*/
					/*$(file).attr('data-address',imgUrl).val($(id2).find('.fileName').val()).attr('title',$(id2).find('.fileName').val());*/
				},
				before:function(){
				    loadIndex = layer.load(0, {shade: false});
				}
			};
			$(clazz).bsPlupload(options);
		}

var arrFn=[];
//值集单个搜索
function valueSet(key){
	
	 var data = {"valueSetType": key};
     $.ajax({
         type: "POST",
         url: shipServer+'/shipPact/queryValueSet.json',
         async:false,
         data: JSON.stringify(data),
         dataType: 'JSON',
         success: function (res) {
        	 if (res.datas != null){
        		 for (var i = 0; i < res.datas.length; i++) {
        			 if (res.datas[i].value != "其它"){
        				 arrFn.push(res.datas[i]);
        			 }
        		 }
        	 }
         },
         headers: {
             'Content-Type': 'application/json;charset=utf-8'
         }
     });
	
}
//值集多个搜索
var arrsFn=[];
function valueSets(key){
	var data = {"valueSetTypes": key};
	$.ajax({
		type: "POST",
		url: shipServer+'/shipPact/queryValueSet.json',
		async:false,
		data: JSON.stringify(data),
		dataType: 'JSON',
		success: function (res) {
			if (res.datas != null){
				for (var i = 0; i < res.datas.length; i++) {
					//if (res.datas[i].value != "其它"){
						arrsFn.push(res.datas[i]);
					//}
				}
			}
		},
		headers: {
			'Content-Type': 'application/json;charset=utf-8'
		}
	});
	
}
//搜索事件
function autocomplete(arrZ,classZ){
	if (arrZ == null){
		arrZ=arrsFn;
	}
	 $(classZ).autocomplete(
			 arrZ,
             {	
             	//width: $(classZ).outerWidth(),
                 minChars: 0,	
                 matchContains: true,
                 scroll: true,
                 mustMatch: false,
                 scrollHeight: 122,
                 formatItem: function (row, i, max) {
                     return  row.value ;
                 },
                 formatMatch: function (row, i, max) {
                     return row.value;
                 },
                 formatResult: function (row) {
                     return row.value;
                 }
             }).result(function (event, row, formatted) {
            	 $(classZ).attr('code',row.code).attr('nameFn',row.value); 
     });
}
//文件上传添加x
function filex(value,obj){
	/*if (value != null && value != ''){
		$(obj).find('.clearFile').removeAttr('hidden','hidden');
		$(obj).find('a').removeClass('file1Fn').addClass('file1Fns');
	}*/
}
function initClass(){
    $(".s-mod-bcont .s-inline").each(function(){
        var father_width = $(this).width();
        var first_width = $(this).children(":nth(0)").width();
        $(this).children(":nth(1)").css("width",father_width-first_width-20);

    });
}