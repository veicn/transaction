;(function(window){
    var Checkbox = function(dom){
        this.init(dom);
    };
    $.extend(Checkbox.prototype,{
        li:null,
        checkbox:null,
        el:null,
        init:function(dom){
            this.el = $(dom);
            this.li = $(dom);
            this.checkbox = $(dom).prev();
            this.bindEvents();
            this.isChecked()?
                this.check():this.unCheck();
        },
        bindEvents:function () {
            var _this = this;
            _this.li.click(function () {
                _this.toogleCheck();
            });
        },
        toogleCheck:function(){
            if(!this.isChecked()){
                this.check();
            }else{
                this.unCheck();
            }
        },
        isChecked : function(){
            return !!this.checkbox.get(0).checked;
        },
        check : function () {
            this.checkbox.get(0).checked = true;
            this.li.addClass("s-form-checked");
            this.el.trigger("check");
        },
        unCheck : function () {
            this.checkbox.get(0).checked=false;
            this.li.removeClass("s-form-checked")
            this.el.trigger("unCheck");
        }
    });

    jQuery.fn.Checkbox = function(method){
        var args = [].slice.call(arguments,1);
        this.each(function(){
            if(!this.comp) {
                var comp = this.comp = new Checkbox(this);
            }
            method && this.comp[method].apply(this.comp,args);
        });

    }
    $(function(){
        $('.s-form-checkbox').Checkbox();
    });
})(window);