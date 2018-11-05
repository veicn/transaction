layui.use(['layer', 'form', 'jquery','laypage', 'table', 'laydate','own','upload'], function(){
	var layer = layui.layer,
	form = layui.form,  // 载入form表单
	own = layui.own,
	table = layui.table;
	upload = layui.upload;
	window.$ = layui.jquery;
	var laydate = layui.laydate;
	//执行一个laydate实例
  	laydate.render({
  		lang: lang_ver,
    	elem: '#date1', //指定元素
    	type: 'year'
  	});
    //返回
	$('.returnBtn').on('click',function(){
		window.location.href = document.referrer;
	});
  	
    //查询船型
	ports();
	//查询船型
	function ports(){
		//查询值集
		valueSets("9;");
		window.shipTypeArr = [];
		if (arrsFn != null){
			for (var i = 0; i < arrsFn.length; i++) {
				if (arrsFn[i].type == '9' && arrsFn[i].value != $.i18n("JAVASCRIPT00225")){
					shipTypeArr.push(arrsFn[i])
				}
			}
		}
		autocomplete(shipTypeArr,'.vesselSize');
	}
	
	
	
	/**========================= 文件上传 ===============================*/
	//文件上传
	upload.render({
	    elem: ".fileUpBtn",//绑定元素 id或class
	    url: shipServer+"/common/doc/upload.json",
	    accept: "file",
	    size: 10240, //限制文件大小，单位 KB
	    //exts: 'zip|rar|7z' //允许上传的文件后缀
	    before: function(obj){ 
			layer.load(); //上传loading
		},
	    done: function(res, index, upload){
	    	layer.closeAll('loading'); //关闭loading
		    $(".filePath").val(res.datas.name);
		    $(".filePath").attr("attr-data",res.datas.path);
		    addFile(res.datas);
	    }
	});
	
	//文件队列
	var fileInfoList = new Array();	

	//将文件存入文件队列
	function addFile(fileInfo) {
		var info = {};
		info.path = fileInfo.path;
		info.name = fileInfo.name;
		fileInfoList.push(info);
	}

	//加
	$('.s-icon-plus').on('click',function(){
		var ssdd = Math.round(Math.random() * 100);
		var cloneDiv=$(this).parents('.wsUl').clone();
		//替换 "加号"
		cloneDiv.find('.addIcon').removeClass('s-icon-plus');
		cloneDiv.find('.addIcon').addClass('s-icon-less');
		//替换 "上传按钮"
		cloneDiv.find('.s-icon-up').removeClass('fileUpBtn');
		cloneDiv.find('.s-icon-up').addClass('fileUpBtn'+ssdd);
		//替换 "文件输入项"
		cloneDiv.find('.path').attr("attr-data","");
		cloneDiv.find('.path').attr("value","");
		cloneDiv.find('.path').removeClass('filePath');
		cloneDiv.find('.path').addClass('filePath'+ssdd);
		
		$('.all').append(cloneDiv);
		
		//每加一个，就动态绑定上传按钮
		fileUpload(ssdd);
	});
	
	//动态绑定上传元素
  	function fileUpload(ssdd) {
        var index = "";
        layui.use('upload', function () {
			upload.render({
			    elem: '.fileUpBtn' + ssdd,//绑定元素 id或class
			    url: shipServer+"/common/doc/upload.json",
			    accept: "file",
			    size: 10240, //限制文件大小，单位 KB
			    //exts: 'zip|rar|7z' //允许上传的文件后缀
			    before: function(obj){ 
					layer.load(); //上传loading
				},
			    done: function(res, index, upload){
			    	layer.closeAll('loading'); //关闭loading
				    $('.filePath' + ssdd).val(res.datas.name);	//设置新增上传文件的name、path
				    $('.filePath' + ssdd).attr("attr-data",res.datas.path);
				    addFile(res.datas);
			    }
			});
        });
    }
  	
  	//减
  	$('body').on('click','.s-icon-less',function(){
  		var filePath = $(this).parents('.wsUl').find('.path').attr("attr-data");	//获取删除文件的path
		$(this).parents('.wsUl').remove();
		delFile(filePath);	//在文件队列中，将减去的文件删除
  	});
	
  	//叉
  	$('body').on('click','.fs-close',function(){
  		var filePath = $(this).parents('.wsUl').find('.path').attr("attr-data");	//获取删除文件的path
  		$(this).parents('.wsUl').find('.path').attr("value","");
  		delFile(filePath);	//在文件队列中，将减去的文件删除
  	});
	
	//将文件从文件队列中删除
	function delFile(filePath) {
		if(filePath != "") {
			for(var i = 0; i < fileInfoList.length; i++) {
				if(filePath == fileInfoList[i].path) {
					fileInfoList.splice(i, 1); //从文件队列中，移除index=i，往后长度为1的元素
					break;
				}
			}
		}
	}
	/**========================= 文件上传 ===============================*/
	

	
	//IMO输入事件
	$('#imo').on('blur',function() {
		$('.mmsi').val('');
		var imo = $('#imo').val();
		if (imo) {
			// 初始化MMSI
			initMmsi(imo);
		}
	}); 
	
	
	//提交
	$('.saveBtn').on('click',function(){
		  //校验
		  if($("#imo").val()=="" || $.trim($("#imo").val())=="") {
			  layer.alert($.i18n("JAVASCRIPT0088"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
			  return;
		  }
		  if($("#name").val()=="" || $.trim($("#name").val())=="") {
			  layer.alert($.i18n("JAVASCRIPT0089"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
			  return;
		  }
		  if($("#date1").val()=="") {
			  layer.alert($.i18n("JAVASCRIPT0090"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
			  return;
		  }
		  
		  //数值正则
		  var reg = new RegExp("^[0-9]+.?[0-9]*$");  
		  var length = delNumFormat($("#length").val());
		  var width = delNumFormat($("#wide").val());
		  var draft = delNumFormat($("#draft").val());
		  var loadQuantity = delNumFormat($("#loadQuantity").val());
		  var capacity = delNumFormat($("#capacity").val());
		  
		  if (length!="" && !reg.test(length)) {
			  layer.alert($.i18n("JAVASCRIPT0091"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
			  return;
		  }
		  if (width!="" && !reg.test(width)) {
			  layer.alert($.i18n("JAVASCRIPT0092"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
			  return;
		  }
		  if (draft!="" && !reg.test(draft)) {
			  layer.alert($.i18n("JAVASCRIPT0093"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
			  return;
		  }
		  if (loadQuantity!="" && !reg.test(loadQuantity)) {
			  layer.alert($.i18n("JAVASCRIPT0094"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
			  return;
		  }
		  if (capacity!="" && !reg.test(capacity)) {
			  layer.alert($.i18n("JAVASCRIPT0095"),{title:$.i18n("001"),btn:$.i18n("002")}).style.zIndex = 9999999999;
			  return;
		  }
		  
		  var params = {};
		  params.imo = $("#imo").val();
		  params.name = $("#name").val();
		  //船型值设定
		  params.type =$("#type").val();
		  //mmsi值设定
		  params.mmsi =$("#mmsi").val();
		  
		  params.useType = $("#useType").val();
		  params.completeDate = $("#date1").val();
		  params.hull = $("#hull").val();
		  params.length = delNumFormat($("#length").val());
		  params.wide = delNumFormat($("#wide").val());
		  params.draft = delNumFormat($("#draft").val());
		  params.loadQuantity = delNumFormat($("#loadQuantity").val());
		  params.capacity = delNumFormat($("#capacity").val());
		  params.fileInfoList = fileInfoList;
		  
		  //船舶保存类型（1:待审核，2:有效） 
		  var saveType="1"
		  params.saveType=saveType;
		  
		  $.ajax({
			type: "POST",
			url: shipServer+"/sysShip/saveSysShip.json",
			data: JSON.stringify(params),
			contentType:"application/json",
			async: false,
			success: function(data) {  
					if(data.status == 0){
						layer.alert($.i18n("JAVASCRIPT0096"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
						    layer.close(index);
						    window.location.href = document.referrer;
						});
					}else{
						layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")},function(index) {
							layer.close(index);
						});
					}
			},
			error: function(data) {
				layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
					layer.close(index);
				});
			}
		});
	}); 
	
	  
	
	//初始化MMSI
	function initMmsi(imo) {
		// 查询MMSI列表
		var params = {};
		params.imo= imo;
	 	$.ajax({
			type: "POST",
			url: shipServer+"/sysShip/findSysShipMmsi.json",
			data: JSON.stringify(params),
			contentType:"application/json",
			async:false,
			success: function(data) {
				if (data.status == 0) {
					var datas = data.datas;
					if (datas) {
						var mmsi_arr = data.datas.data;
						var mmsiArr =[];
						if (mmsi_arr != null && mmsi_arr.length > 0){
							for(var i =0; i< mmsi_arr.length; i++){
					            var mmsi = mmsi_arr[i];
					            if (mmsi.IMO == imo) {
					            	var objArr = {};
					            	objArr.value=mmsi.mmsi;
					            	mmsiArr.push(objArr);
					            }
							}
							autocomplete(mmsiArr,'.mmsi');
						}
					}
				}else{
					layer.alert($.i18n(data.message),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
						layer.close(index);
					});
				}
			},
			error: function(data) {
				layer.alert($.i18n("JAVASCRIPT003"),{title:$.i18n("001"),btn:$.i18n("002")}, function(index) {
					layer.close(index);
				});
	        }
	 	});
	   }
	});

