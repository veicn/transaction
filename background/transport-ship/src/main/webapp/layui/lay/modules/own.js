layui.define(['layer','jquery'],function(exports){ //提示：模块也可以依赖其它模块，如：layui.define('layer', callback);
  var own = {
      test: function(str){
      	alert(str||'成功');
      },
      getHref:function(keyStr){  //获取地址拦？后面的vue值  //  $.getHref('name')
      	var str=window.location.href;
      	str=str.substring(str.indexOf('?')+1);
      	var hrefArr=str.split('&');
      	var end = false;
      	for(var i=0; i<hrefArr.length;i++){
      		var linARR=hrefArr[i].split('=');
      		if(linARR[0]==keyStr){
      			end=linARR[1];
      			break;
      		}
      	}
      	return end
     },
    chainHandler:function(name, value) {
				var o = {};
				var key, val;
				if(name) {
					var offset = name.indexOf('.');
					if(offset != -1) {
						key = name.substr(0, offset);
						val = own.chainHandler(name.substr(offset + 1, name.length), value);
					} else {
						key = name;
						val = value;
					}
					o[key] = val || '';
					return o;
				}
			},
     serializeObject: function(obj) {
				var o = {};
				var a = obj.serializeArray();
				$.each(a, function() {
					var r = own.chainHandler(this.name, this.value);
					for(var key in r) {
						var reg = /\[([^\[\]]*)\]/;
						var indexArr = reg.exec(key);
						if(indexArr) {
							var index = indexArr[1];
							keyMain = key.replace(reg, '');
							o[keyMain] || (o[keyMain] = []);
							if(index) {
								if(o[keyMain][index]) {
									o[keyMain][index] = $.extend(o[keyMain][index], r[key]);
								} else {
									o[keyMain][index] = r[key] || '';
								}
							} else {
								o[key].push(r[key] || '');
							}
						} else {
							if(o[key]) {
								if(!o[key].push) {
									o[key] = [o[key]];
								}
								o[key] = $.extend(o[key], r[key]);
							} else {
								o[key] = r[key] || '';
							}
						}
					}
				});
				return o;
			},
  };
  
  //输出test接口
  exports('own', own);
});  