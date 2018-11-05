// 已选数据的存储
var vsArr = {
    id:[],
    uuid:[]
};
var turn=0;
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
    for (key in vsArr.id) {
        if (vsArr.id[key] == num) {
            vsArr.id.splice(key, 1);
            vsArr.uuid.splice(key, 1);
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
        uuid:[]
    };
    localStorage.vsArr = JSON.stringify(vsArr);
})

//加入对比
function compare(id,uuid,name,src) {
    for (key in vsArr.id) {
        if (vsArr.id[key] == id) {
            layer.msg('已经添加过');
            return;
        }
    }
    if (vsArr.id.length == '4') {
        layer.msg('最多只能添加4个');
        return;
    }
    vsArr.id.push(id)
    vsArr.uuid.push(uuid)
    var li = '<li  data-id='+id+'>' +
        '<img src='+src+'>' +
        '<i class="layui-icon">&#x1006;</i>' +
        '<span>'+name+'</span>' +
        '</li>'
    var ul = $('.toolbar-con-img');
    ul.append(li);
    $('.toolbar-vs').find('i').text(vsArr.id.length)
    layer.msg('添加成功');
    localStorage.vsArr = JSON.stringify(vsArr);
}
//获取vsArr
function getVsArr(){
    return localStorage.vsArr;
}

