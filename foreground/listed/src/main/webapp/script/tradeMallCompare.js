$(function(){
    jQuery.setCompareCheck();
    //复选框类型
    jQuery(".compareC[type='checkbox']").click(function(){
        // 对比个数
        var compareGoods = jQuery(this);
        var id = compareGoods.val();
        var sn = compareGoods.attr("sn");

        var comChecked = compareGoods.is(":checked");
        if(comChecked){
            var name = compareGoods.attr("name");
            var imgPath = compareGoods.attr("img");
            var total = jQuery.getCompareGoodsCount();
            if(total>3){
                layer.msg("已经到达对比个数上限!");
                compareGoods.attr("checked", r);
                return !1;
            }
            var r = jQuery.addCompareGoods(id,sn,name,imgPath);
        }else{
            jQuery.delCompareGoods(id+"_"+sn);
        }
        jQuery.setCompareBox("vsBox", "$!imgServer");
        var total = jQuery.getCompareGoodsCount();
        total = total==null?0:total;
        if(total==0){
            jQuery("#vsall").hide();
            jQuery("#delvsall").hide();
        }else{
            jQuery("#vsall").show();
            jQuery("#delvsall").show();
        }
        jQuery(".vs-num").text(total);
        

    });
    
  //超链接类型对比
    jQuery(" .compareC").click(function(event){
        // 对比个数
        var compareGoods = jQuery(this);
        var id = compareGoods.attr("value");
        var sn = compareGoods.attr("sn");
        var dealType = compareGoods.attr("dealType");

        var comChecked = compareGoods.is(":checked");
        
        var name = compareGoods.attr("name");
        var imgPath = compareGoods.attr("img");
        var total = jQuery.getCompareGoodsCount();
        
        var compareGoodsCookie = $.getCookie(window.CompareGoodsName || "COMPAREGOODS");
        if(total>0){
            if (compareGoodsCookie != null && compareGoodsCookie.indexOf("\"" + dealType + "\"") == -1) {
                layer.msg("采购和销售不能同时比较！");
                return !1;
            }
        }
        
        if (compareGoodsCookie != null && compareGoodsCookie.indexOf(id + "_" + sn) > -1) {
            layer.msg("该商品已经加入对比!");
            return !1;
        }
        
        if(total>3){
            layer.msg("已经到达对比个数上限!");
            compareGoods.attr("checked", r);
            return !1;
        }
        var r = jQuery.addCompareGoods(id, sn, name, imgPath, dealType);
        
        jQuery.setCompareBox("vsBox", "$!imgServer");
        var total = jQuery.getCompareGoodsCount();
        total = total==null?0:total;
        if(total==0){
            jQuery("#vsall").hide();
            jQuery("#delvsall").hide();
        }else{
            jQuery("#vsall").show();
            jQuery("#delvsall").show();
        }
        jQuery(".vs-num").text(total);
        //阻止事件冒泡
        event.stopPropagation();

    });


    })