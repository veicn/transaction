// 已选数据的存储


if(JSON.parse(localStorage.getItem('vsArr'))){
    var vsArr=JSON.parse(localStorage.getItem('vsArr'))

}else{
    var vsArr = {
        id:[],
        uuid:[],
        name:[],
        src:[]
    };
}

$(function(){
    var ul = $('.toolbar-con-img');
    var li = ''
        for(var i=0;i<vsArr.id.length;i++){
            li += "<li  data-id='"+vsArr.id[i]+"' data-uuid='"+vsArr.uuid[i]+"'><img src='"+vsArr.src[i]+"'><i class='layui-icon'>&#x1006;</i><span>"+vsArr.name[i]+"</span> </li>"
        }
       // console.log(li)
    ul.append(li);
    $('.toolbar-vs').find('i').text(vsArr.id.length)

});


//实例layer
var layer;
layui.use('layer', function () {
    layer = layui.layer;
});
//侧边栏显示显示
$(document).on('click', '.toolbar-vs', function () {
    var con = $(this).siblings('.toolbar-con');
    con.toggle('slow');
})
//侧边栏显示隐藏
$('.toolbar-con-t>i').click(function () {
    $(this).closest('.toolbar-con').toggle('slow');
})
// 点击图片弹出x号
$(document).on('click', '.toolbar-con-img>li>img', function () {
    $('.toolbar-con-img').find('i').removeClass('toolbar-con-i');
    $(this).siblings('i').addClass('toolbar-con-i')
});
// 点击x号删除当前商品
$(document).on('click', '.toolbar-con-img>li>i', function () {
    var num = $(this).closest('li').attr('data-id')
    $(this).closest('li').remove();
    var toll = $('.toolbar-con-img').find('li').length;
    $('.toolbar-vs').find('i').text(toll)
    for (var key in vsArr.id) {
        if (vsArr.id[key] == num) {
            vsArr.id.splice(key, 1);
            vsArr.uuid.splice(key, 1);
            vsArr.src.splice(key, 1);
            vsArr.name.splice(key, 1);
        }
    }
    localStorage.vsArr = JSON.stringify(vsArr);
});
// 删除所有
var delAll = $('.toolbar-con').find('.delAll');
delAll.on('click', function () {
    $('.toolbar-con').find('li').remove()
    $('.toolbar-vs').find('i').text(0);
    vsArr = {
        id:[],
        uuid:[],
        src:[],
        name:[]
    };
    localStorage.vsArr = JSON.stringify(vsArr);
})

//加入对比
function compare(id,uuid,name,src) {
    for (var key in vsArr.id) {
        if (vsArr.id[key] == id) {
                layer.msg('Already in comparison list',{time: 1000});
                return;

        }
    }
    if (vsArr.id.length == '4') {
        layer.msg('No more than 4 items');
        return;
    }
    vsArr.id.push(id)
    vsArr.name.push(name)
    vsArr.src.push(src)
    vsArr.uuid.push(uuid)
    var li = "<li  data-id='"+id+"' data-uuid='"+uuid+"'><img src='"+src+"'><i class='layui-icon'>&#x1006;</i><span>"+name+"</span> </li>"
    var ul = $('.toolbar-con-img');
    ul.append(li);
    $('.toolbar-vs').find('i').text(vsArr.id.length)
    layer.msg('Add to comparison list successfully');
    localStorage.vsArr = JSON.stringify(vsArr);

}
//获取vsArr
function getVsArr(){
    var idList = localStorage.vsArr;
    var obj = JSON.parse(idList)
    var uuids="";
    for(var i=0;i<obj.uuid.length; i++){
        uuids += obj.uuid[i] + ',';
    }
    uuids = uuids.substring(0, uuids.lastIndexOf(','));
    window.location.href = appServer + '/pages/front/product_compare/index.htm?uuids=' + uuids;
}

