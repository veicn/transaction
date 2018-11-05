var cflag=cflag;
// 已选数据的存储 新
// sale对比
if(JSON.parse(localStorage.getItem('vsArr')) ){
    var vsArr=JSON.parse(localStorage.getItem('vsArr'))

}else{
    var vsArr = {
        id:[],
        uuid:[],
        name:[],
        src:[]
    };
}
// PURCHASE对比
if(JSON.parse(localStorage.getItem('purvsArr')) ){
    var purvsArr=JSON.parse(localStorage.getItem('purvsArr'))

}else{
    var purvsArr = {
        id:[],
        uuid:[],
        name:[],
        src:[]
    };
}

var turn=0;


$(function(){
    var ul = $('.toolbar-con-img');
    var li = ''
    if($('.sale-pur-kind a:first-of-type').hasClass('active') || cflag==1){
        for(var i=0;i<vsArr.id.length;i++){
            li += "<li  data-id='"+vsArr.id[i]+"' data-uuid='"+vsArr.uuid[i]+"'><img src='"+vsArr.src[i]+"'><i class='layui-icon'>&#x1006;</i><span>"+vsArr.name[i]+"</span> </li>"
        }
        ul.append(li);
        $('.toolbar-vs').find('i').text(vsArr.id.length)
    }else if(cflag==2){
        for(var i=0;i<purvsArr.id.length;i++){
            li += "<li  data-id='"+purvsArr.id[i]+"' data-uuid='"+purvsArr.uuid[i]+"'><img src='"+purvsArr.src[i]+"'><i class='layui-icon'>&#x1006;</i><span>"+purvsArr.name[i]+"</span> </li>"
        }
        ul.append(li);
        $('.toolbar-vs').find('i').text(purvsArr.id.length)
    }else{
        for(var i=0;i<purvsArr.id.length;i++){
            li += "<li  data-id='"+purvsArr.id[i]+"' data-uuid='"+purvsArr.uuid[i]+"'><img src='"+purvsArr.src[i]+"'><i class='layui-icon'>&#x1006;</i><span>"+purvsArr.name[i]+"</span> </li>"
        }
        ul.append(li);
        $('.toolbar-vs').find('i').text(purvsArr.id.length)
    }

    // console.log(li)


});


//实例layer
var layer;
layui.use('layer', function () {
    layer = layui.layer;
});
//侧边栏显示显示
$(document).on('click', '.toolbar-vs', function () {
    if(turn==0){
        $('.toolbar').animate({right:"0px"});
        turn=1;
    }else{
        $('.toolbar').animate({right:"-270px"});
        turn=0;
    }
})
//侧边栏显示隐藏
$('.toolbar-con-t>i').click(function () {
    $('.toolbar').animate({right:"-270px"});
    turn=0;
})
// 点击图片弹出x号
$(document).on('click', '.toolbar-con-img>li>img', function () {
    $('.toolbar-con-img').find('i').removeClass('toolbar-con-i');
    $(this).siblings('i').addClass('toolbar-con-i')
});
// 点击x号删除当前商品
$(document).on('click', '.toolbar-con-img>li>i', function () {
    $(this).closest('li').remove();
    var toll = $('.toolbar-con-img').find('li').length;
    $('.toolbar-vs').find('i').text(toll)
    var num = $(this).closest('li').attr('data-id')
    if($('.sale-pur-kind a:first-of-type').hasClass('active') ||cflag==1) {
        for (key in vsArr.id) {
            if (vsArr.id[key] == num) {
                vsArr.id.splice(key, 1);
                vsArr.uuid.splice(key, 1);
                vsArr.src.splice(key, 1);
                vsArr.name.splice(key, 1);
            }
        }
        localStorage.vsArr = JSON.stringify(vsArr);
    }else if(cflag==2){
        for (key in purvsArr.id) {
            if (purvsArr.id[key] == num) {
                purvsArr.id.splice(key, 1);
                purvsArr.uuid.splice(key, 1);
                purvsArr.src.splice(key, 1);
                purvsArr.name.splice(key, 1);
            }
        }
        localStorage.purvsArr = JSON.stringify(purvsArr);
    }else{
        for (key in purvsArr.id) {
            if (purvsArr.id[key] == num) {
                purvsArr.id.splice(key, 1);
                purvsArr.uuid.splice(key, 1);
                purvsArr.src.splice(key, 1);
                purvsArr.name.splice(key, 1);
            }
        }
        localStorage.purvsArr = JSON.stringify(purvsArr);
    }
});
// 删除所有
var delAll = $('.toolbar-con').find('.delAll');
delAll.on('click', function () {
    $('.toolbar-con').find('li').remove()
    $('.toolbar-vs').find('i').text(0);
    if($('.sale-pur-kind a:first-of-type').hasClass('active') ||cflag==1) {
        vsArr = {
            id: [],
            uuid: [],
            src: [],
            name: []
        };
        localStorage.vsArr = JSON.stringify(vsArr);
    }else if(cflag==2){
        purvsArr = {
            id: [],
            uuid: [],
            src: [],
            name: []
        };
        localStorage.purvsArr = JSON.stringify(purvsArr);
    }else{
        purvsArr = {
            id: [],
            uuid: [],
            src: [],
            name: []
        };
        localStorage.purvsArr = JSON.stringify(purvsArr);
    }
})

//加入对比
function compare(id,uuid,name,src) {
    if($('.sale-pur-kind a:first-of-type').hasClass('active') ||cflag==1) {
        for (var key in vsArr.id) {
            if (vsArr.id[key] == id) {
                layer.msg('Already in comparison list');
                return;

            }
        }
        if (vsArr.id.length == '4') {
            layer.msg('No more than 4 for comparison');
            return;
        }
        vsArr.id.push(id)
        vsArr.name.push(name)
        vsArr.src.push(src)
        vsArr.uuid.push(uuid)
        var li = "<li  data-id='" + id + "' data-uuid='" + uuid + "'><img src='" + src + "'><i class='layui-icon'>&#x1006;</i><span>" + name + "</span> </li>"
        var ul = $('.toolbar-con-img');
        ul.append(li);
        $('.toolbar-vs').find('i').text(vsArr.id.length)
        layer.msg('Add to comparison list successfully');
        localStorage.vsArr = JSON.stringify(vsArr);
    }else if( cflag==2){
        for (var key in purvsArr.id) {
            if (purvsArr.id[key] == id) {
                layer.msg('Already in comparison list');
                return;

            }
        }
        if (purvsArr.id.length == '4') {
            layer.msg('No more than 4 for comparison');
            return;
        }
        purvsArr.id.push(id)
        purvsArr.name.push(name)
        purvsArr.src.push(src)
        purvsArr.uuid.push(uuid)
        var li = "<li  data-id='" + id + "' data-uuid='" + uuid + "'><img src='" + src + "'><i class='layui-icon'>&#x1006;</i><span>" + name + "</span> </li>"
        var ul = $('.toolbar-con-img');
        ul.append(li);
        $('.toolbar-vs').find('i').text(purvsArr.id.length)
        layer.msg('Add to comparison list successfully');
        localStorage.purvsArr = JSON.stringify(purvsArr);
    }else{
        for (var key in purvsArr.id) {
            if (purvsArr.id[key] == id) {
                layer.msg('Already in comparison list');
                return;

            }
        }
        if (purvsArr.id.length == '4') {
            layer.msg('No more than 4 for comparison');
            return;
        }
        purvsArr.id.push(id)
        purvsArr.name.push(name)
        purvsArr.src.push(src)
        purvsArr.uuid.push(uuid)
        var li = "<li  data-id='" + id + "' data-uuid='" + uuid + "'><img src='" + src + "'><i class='layui-icon'>&#x1006;</i><span>" + name + "</span> </li>"
        var ul = $('.toolbar-con-img');
        ul.append(li);
        $('.toolbar-vs').find('i').text(purvsArr.id.length)
        layer.msg('Add to comparison list successfully');
        localStorage.purvsArr = JSON.stringify(purvsArr);
    }
}
//获取vsArr
function getVsArr(){
    if($('.sale-pur-kind a:first-of-type').hasClass('active') || cflag==1) {
        var idList = localStorage.vsArr;
        var obj = JSON.parse(idList);
        if (obj.uuid.length == 0) {
            layer.msg('Please add at least one to comparison list');
        } else {
            var uuids = "";
            for (var i = 0; i < obj.uuid.length; i++) {
                uuids += obj.uuid[i] + ',';
            }
            uuids = uuids.substring(0, uuids.lastIndexOf(','));
            window.location.href = appServer + 'pages/front/product_compare/index.htm?uuids=' + uuids;
        }
    }else if( cflag==2){
        var idList = localStorage.purvsArr;
        var obj = JSON.parse(idList);
        if (obj.uuid.length == 0) {
            layer.msg('Please add at least one to comparison list');
        } else {
            var uuids = "";
            for (var i = 0; i < obj.uuid.length; i++) {
                uuids += obj.uuid[i] + ',';
            }
            uuids = uuids.substring(0, uuids.lastIndexOf(','));
            window.location.href = appServer + 'pages/front/product_compare/demand_index.htm?uuids=' + uuids;
        }
    }else{
        var idList = localStorage.purvsArr;
        var obj = JSON.parse(idList);
        if (obj.uuid.length == 0) {
            layer.msg('Please add at least one to comparison list');
        } else {
            var uuids = "";
            for (var i = 0; i < obj.uuid.length; i++) {
                uuids += obj.uuid[i] + ',';
            }
            uuids = uuids.substring(0, uuids.lastIndexOf(','));
            window.location.href = appServer + 'pages/front/product_compare/demand_index.htm?uuids=' + uuids;
        }
    }
}

