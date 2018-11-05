/*(function () {
	 var progressaa = eval("("+progress+")");//第三页数据
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
//回写第三页
    //实例化layui的date组件对象

    layui.use('laydate', function () {
        var laydate = layui.laydate;
        progressaa.forEach((ele)=>{
        	//随机生成id
//        	var id = 'e'+ele.uuid;
        	var date =ele;
        	
        	//添加dom
        	$('.layui-table>tbody').append(`
        			'\n        <tr>\n                                                    <td class="time1 vl-loa-l">\n                                                        <div class="datetime-wrap">\n                                                            <input name="datetime" id="" value="${date.datetime}" class="DeadlineMonth DeadlineMonth1 datetime" type="text" lay-key="20">\n                                                            <div class="datetime-view">${date.datetime}</div>\n                                                        </div>\n                                                    </td>\n                                                    <td class="cargo">\n                                                        <input type="text" value="${date.cargoLoaded}" name="cargoLoaded" class="br">\n                                                    </td>\n                                                    <td class="cargo">\n                                                        <input type="text" value="${date.theCargoToBeLoaded}" name="theCargoToBeLoaded" class="br">\n                                                    </td>\n                                                    <td class="cargo">\n                                                        <input type="text" value="${date.loadingSpeed}" name="loadingSpeed" class="br">\n                                                    </td>\n                                                    <td class="time3 vl-loa-l">\n                                                        <div class="datetime-wrap">\n                                                            <input name="etc" id="${id+'b'}" class="DeadlineMonth DeadlineMonth1 datetime" value="${date.etc}" type="text" lay-key="21">\n                                                            <div class="datetime-view">${date.etc}</div>\n                                                        </div>\n                                                    </td>\n                                                    <td class="time2">\n                                                        <a href="javascript:;">\n                                                            <i class="layui-icon"><img src="/images/delete-icon.png"></i>\n                                                        </a>\n                                                    </td>\n                                                </tr>');
        	`)
        
        	$('.DeadlineMonth1').each(function(){
    		laydate.render({
    			elem:this,
    			type: 'datetime',
    			lang: 'en',
    			//value: '10 10 2017 00:00',
    		
    			format: 'dd MM yyyy HH:mm',
    			theme: '#F36523',
    			done: function done(value, date, endDate) {
    				console.log(this,value,date,endDate)
    				$(this.elem[0]).val(value)
    				$(this.elem[0]).next().text(value)
    			}
    		});
    	})
        	
       
    });

});
    var obj = {
        dom: [],
        ids: [],
        date: []
    };
    //随机生成id
    function ran() {
        var id = 'aaa' + new Date().getTime();
        obj.ids.push(id);
    }
    //添加dom
    function dom() {
        obj.dom.push('\n        <tr>\n                                                    <td class="time1 vl-loa-l">\n                                                        <div class="datetime-wrap">\n                                                            <input name="datetime" id="' + obj.ids[obj.ids.length - 1] + '" class="DeadlineMonth datetime" type="text" lay-key="20">\n                                                            <div class="datetime-view"></div>\n                                                        </div>\n                                                    </td>\n                                                    <td class="cargo">\n                                                        <input type="text" name="cargoLoaded" class="br">\n                                                    </td>\n                                                    <td class="cargo">\n                                                        <input type="text" name="theCargoToBeLoaded" class="br">\n                                                    </td>\n                                                    <td class="cargo">\n                                                        <input type="text" name="loadingSpeed" class="br">\n                                                    </td>\n                                                    <td class="time3 vl-loa-l">\n                                                        <div class="datetime-wrap">\n                                                            <input name="etc" id="' + (obj.ids[obj.ids.length - 1] + 'a') + '" class="DeadlineMonth datetime" type="text" lay-key="21">\n                                                            <div class="datetime-view"></div>\n                                                        </div>\n                                                    </td>\n                                                    <td class="time2">\n                                                        <a href="javascript:;">\n                                                            <i class="layui-icon"><img src="../images/delete-icon.png"></i>\n                                                        </a>\n                                                    </td>\n                                                </tr>');
        $('.con_one_3').find('tbody').append(obj.dom[obj.dom.length - 1]);
    }
    function layDate() {
        var laydate = layui.laydate;
        obj.date.push(laydate);
    }
    //实例化layui的date组件对象
    // laydate=null
    //点击事件
    //监听dom数组的长度
    //监听dom数组的长度
    var domLen = obj.dom.length;
    var idLen = obj.ids.length;
    var dateLen = obj.date.length;
    $(document).on('click', '.addbtn3',function () {
        ran(idLen);
        dom(domLen);
        layDate();
        var _self = this;
        layui.use('laydate', function () {
        	
        	layDate();
            var laydate = layui.laydate;
        	$('.DeadlineMonth').each(function(){
        	        		laydate.render({
        	        			elem:this,
        	        			type: 'date',
        	        			lang: 'en',
        	        			//value: '10 10 2017',
        	        			btns: ['clear', 'now'],
        	        			format: 'dd MM yyyy',
        	        			theme: '#F36523',
        	        			done: function done(value, date, endDate) {
        	        				  var monthArr = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'June', 'July', 'Aug', 'Sept', 'Oct', 'Nov', 'Dec'];
        	                          var dateCopy = JSON.parse(JSON.stringify(date));
        	                          dateCopy.month = monthArr[parseInt(dateCopy.month) - 1];
        	                          var arr = value.split(' ');
        	                          arr.splice(1, 1, dateCopy.month);
        	                          var evalue = arr.join(' ');
        	                          //console.log(id);
        	                          $(this.elem[0]).css({
        	                              'opacity': 0
        	                          });
        	                          $(this.elem[0]).attr('value',value);
        	                          $(this.elem[0]).next().text(evalue);
        	        			}
        	        		});
        	        })
        });
    });
})();*/