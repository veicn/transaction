(function () {

    function BasicInfo(){
        if(!this instanceof BasicInfo){
            return new BasicInfo()
        }
        this.init = function(){
            this.getPositionForContentModule()
            this.initRadios();
            commonCPY.select();
            this.initTimePicker();
            this.checkForm();
            this.bindEvent()
        };
        this.getPositionForContentModule = function () {

        };
        this.getSingle = function () {
            var result;
            return function(){
                return result||(result = fn.apply(this,arguments))
            }
        };
        this.initRadios = function(){
            var _self = this;
            commonCPY.radios(function(obj){
                if($(obj).parent().hasClass('saleType-wrap')){
                    if($(obj).hasClass('Inquiry')){
                        $('.Inquiry-table').show();
                    }else{
                        $('.Inquiry-table').hide();
                    }
                }
            });
        };
        this.initTimePicker = function(){

            var startTime = commonCPY.picker('startTime');
            var endTime = commonCPY.picker('endTime');
            startTime();
            endTime();
        };
        this.checkForm = function(){
            $("#basicInfo").validate({
                rules:{
                    Deadline:{
                        required:true
                    }
                },
                messages:{
                    Deadline:{}
                },
                errorPlacement:function(error,element){
                    error.appendTo(element.parents('.per-field-box'));
                }
            })
        };
        this.deleteTr = function(){
            var _self = this;
            $('.Inquiry-table').on('click','tbody .delete',function(){
                var tbody = $(this).parents('tbody');
                $(this).parents('tr').remove();
                //重新排序
                var len = tbody.find('tr').length
                console.log(len)
                for(var i = 0; i<len; i++){
                    tbody.find('tr').eq(i).find('td:first-child').text(i+1);
                }

            })
        };
        this.bindEvent = function(){
            this.deleteTr()

        };
        this.init();
    }

    return {
        BasicInfo : BasicInfo()
    }
})()