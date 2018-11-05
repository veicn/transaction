//检查标签是否超过7个字符
  function checkTagListLength(data){
	  var tagArr = data.tagList.split(","); //字符分割
	  for(var i = 0; i < tagArr.length; i ++){
		  if(tagArr[i].length > 7){
			  layer.alert('第'+(i + 1) + '个标签 超过个字符，请检查');
			  return false;
		  } 
	  }
	  return true;
  }
  
  //检查标题长度
  function checkTitleLength(data){
	  if(data.title.length > 100){
		  layer.alert('标题不能 超过100个字符');
		  return false;
	  }
	  return true;
  }
  
  //检查附件
  function checkAttachments(data){
	if(data.displayMode == 1){
		if(data.attachments.length != 1){
			layer.alert('上传图片必须为一张');
			return false;
		}
	}else if(data.displayMode == 2){
		if(data.attachments.length != 3){
			layer.alert('上传图片必须为三张');
			return false;
		}
	}else if(data.displayMode == 3){
		if(data.attachments.length != 1){
			layer.alert('上传广告必须为一张');
			return false;
		}
	}
	return true;
  }
  
  //校验选择的频道
  function checkChannel(data){
	  if(data.channelUUId =='' || data.channelUUId == '-1' || data.channelUUId == null){
		  layer.alert('请选择栏目');
		  return false;
	  }
	  return true;
  }
  
  //获取资讯录入参数
  function getParams(data,status){
	  var checked = $('.layui-form-radioed').prev().val();  //选中的封面模式
	  var oneStorePathObj =  $('#demoOne').find('.storePath'); //单图1
	  var storePathObj1 =  $('#demoTwo1').find('.storePath'); //三图1
	  var storePathObj2 =  $('#demoTwo2').find('.storePath'); //三图2
	  var storePathObj3 =  $('#demoTwo3').find('.storePath'); //三图3
	  var ggStorePathObj =  $('#demoThree').find('.storePath'); //广告     
	  
	  var attachments = []; 
	  
	  if(oneStorePathObj && checked == 1){ //单图模式
		  if(oneStorePathObj.val() != null && oneStorePathObj.val() != 'undefined' && oneStorePathObj.val() != ''){
			  var item = {};
			  item['attachmentPath'] = oneStorePathObj.val();
			  attachments.push(item);
		  }
		 
	  }else if(storePathObj1 && storePathObj2 && storePathObj3 && checked == 2){ //三图模式
		  if(storePathObj1.val() != null && storePathObj1.val() != 'undefined' && storePathObj1.val() != ''){
			  var item1 = {};
			  item1['attachmentPath'] = storePathObj1.val();
			  attachments.push(item1);
			  
		  }
		  
		  if(storePathObj2.val() != null && storePathObj2.val() != 'undefined' && storePathObj2.val() != ''){
			  var item2 = {};
			  item2['attachmentPath'] = storePathObj2.val();
			  attachments.push(item2);
			  
		  }
		  
		  if(storePathObj3.val() != null && storePathObj3.val() != 'undefined' && storePathObj3.val() != ''){
			  var item3 = {};
			  item3['attachmentPath'] = storePathObj3.val();
			  attachments.push(item3);
		  }
		  
	  }else if(ggStorePathObj && checked == 3){ //单图模式
		  var item = {};
		  item['attachmentPath'] = ggStorePathObj.val();
		  attachments.push(item);
	  }
	  
	  data.field['content'] = UM.getEditor('container').getContentTxt();
  	  data.field['contentWithHtml'] = UM.getEditor('container').getContent();
  	  data.field['status'] = ""+status+""; //已发布 (----------------- 重要 --------------)
  	  data.field['attachments'] = attachments;
  	  
  	  return data.field;
  }

function checkSendInfo(params){
	 if(!checkTagListLength(params)){
		  return false;
	  }
	  if(!checkChannel(params)){
		  return false;
	  }
	  if(!(checkTitleLength(params))){
		  return false;
	  }
	  if(!checkAttachments(params)){
		  return false;
	  }
	  return true;
}