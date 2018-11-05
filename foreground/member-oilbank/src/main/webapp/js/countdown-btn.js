/**
 * 实现倒计的功能,本身不局限于按钮
 *
 * $(".CountDownBtn").CountDownBtn(methodName);
 *
 */
;(function($,window){
    function CountDownBtn(dom){
        this.init(dom);
    }
    $.extend(CountDownBtn.prototype,{
        el:null,
        secs:60,
        tral:null,
        text:null,
        init:function(dom){
            var _this = this;
            this.el=$(dom);
            this.text = this.el.text();
            this.goCount();
            this.el.click(function () {
                setTimeout(function () {
                    _this.el.attr("valid") == "yes" && _this.startCount();
                })
            })
        },
        stop:function(){
            var _this = this;
            clearInterval(_this.tral)
            delete _this.tral;
            _this.el.text(this.text)
            _this.el.removeAttr("disabled");
            _this.onEnd()
        },
        startCount:function(){
            var _this = this;
            if(!_this.tral){
                var secs = _this.secs;
                _this.tral = setInterval(function(){
                    secs--;
                    if(secs <=0){
                        _this.stop();
                    }else{
                        _this.el.text(secs+"s");
                        _this.el.attr("disabled","disabled");
                        _this.onSetp(secs);
                    }
                },1000)

            }
        },
        noCount:function(){
            this.el.attr("valid","no");
        },
        goCount:function(){
            this.el.attr("valid","yes");
        },
        onEnd:function(){

        },
        onSetp:function() {

        }

    });
    jQuery.fn.CountDownBtn = function(method){
        var args = [].concat(arguments);
        this.each(function(){
            if(!this.comp) {
                var comp = this.comp = new CountDownBtn(this);
            }
            method && this.comp[method].apply(this.comp,args.splice(1));
        });

    }
    $(function(){
        $('.CountDownBtn').CountDownBtn();
    });

})(jQuery,window)