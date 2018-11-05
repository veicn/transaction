;(function(window){
    var SearchBox = function(dom){
        this.init(dom);
    };
    $.extend(SearchBox.prototype,{
        lines:[],
        linemax:2,
        init:function(dom){
            _this = this;
            _this.dom = dom;

            for(var i in _this){
                if(!(_this[i] instanceof Function)){
                    if($(dom).data(i) ){
                        _this[i] = $(dom).data[i];
                    }
                }
            }

            $(_this.dom).find('ul li').each(function(){
                _this.lines.push(new SearchLine(this,_this.getLineMax()));
            })
        },
        getLineMax:function(){
            return this.linemax;
        },
        expandAll:function(){
            $(this.lines).each(function(){
                this.expand();
            })
        },
        collapseAll:function(){
            $(this.lines).each(function(){
                this.collapse();
            })
        }
    });


    var SearchLine = function(dom,linemax){
        this.max = linemax || 5;
        this.init(dom)
    };
    $.extend(SearchLine.prototype,{
        max:5,
        init:function(dom){
            this.dom = $(dom);
            this.con = this.dom.find('.search-box-con');
            this.moreBtn = this.dom.find('.btn-more');
            this.initEvents();
        },
        initEvents:function(){
            var _this = this;
            $(this.dom).on('click','.btn-more',function(){
                _this.toogleExpand();
            })
        },
        toogleExpand :function(){
            this.dom.hasClass("ctl-expanded") ?
                this.collapse():this.expand();

        },
        expand:function(){
            this.con.children('a:gt(' + (this.max-1) + ')').show();
            this.moreBtn.text("折叠∧");
            this.dom.addClass("ctl-expanded");
        },
        collapse:function(){
            this.con.children('a:gt(' + (this.max-1) + ')').hide();
            this.moreBtn.text("展开∨");
            this.dom.removeClass("ctl-expanded");

        }
    });

    jQuery.fn.searchBox = function(method){
        var args = [].concat(arguments);
        this.each(function(){
            if(!this.comp) {
                var comp = this.comp = new SearchBox(this);
            }
            this.comp[method].apply(this.comp,args.splice(1));
        });

    }
    $(function(){
        $('.search-box').searchBox("collapseAll");
    });
})(window);