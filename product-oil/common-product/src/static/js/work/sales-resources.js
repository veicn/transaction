(function () {

    function commonClass() {
        if(!(this instanceof commonClass)){
            return new commonClass()
        }
    }
    commonClass.prototype.throttle = function (fn,interval) {
        var _self = this,
            timer,
            firstTime = true;
        return function () {
            var args = arguments,
                _me = this;
            if(firstTime){
                fn.apply(_me,args);
                return firstTime = false;
            }
            if(timer){
                return false;
            }
            timer = setTimeout(function(){
                clearTimeout(timer);
                timer = null;
                fn.apply(_me,args);
            },interval||300)
        }
    };

    function BasicInfo(){
        if(!(this instanceof BasicInfo)){
            return new BasicInfo()
        }
        this.init();
    }
    BasicInfo.prototype = new commonClass()
    BasicInfo.prototype.init = function(){
        commonClass.call(this)
        this.table  = null;
        this.listTableData = null;
        this.heightBox=[];
        this.getPositionForContentModule();
        this.listenPageScroll()
        this.initRadios();
        this.initDateTimePicker()
        commonCPY.select();
        this.initDatePicker();
        this.checkForm();
        this.bindEvent();
    };

    BasicInfo.prototype.getPositionForContentModule = function () {
        var _self = this;
        $('.form-title').each(function(){
            var height = $(this).offset().top;
            var name = $(this).text();
            _self.heightBox.push({
                height: height,
                name: name
            })
        })
    };
    //监听页面滚动
    BasicInfo.prototype.listenPageScroll = function () {
        var _self= this;
        var _flag = true;
        var _numone_position = _self.heightBox[0].height;
        this.isEnableAutoScroll = true;
        this.isClickNum1 = "true";
        $(window).scroll(function (event) {
            if(_self.isEnableAutoScroll){
                if(_flag){
                    _flag = false;
                    _self.toTop=$(window).scrollTop();
                    $('.left-menu-box li').removeClass('active');
                    var localIndex = localStorage.getItem("TimeAxisIndex");
                    var isoneClick = localStorage.getItem("IsClickNum1");
                    if(localIndex!="-1" && isoneClick=="true"){
                        localStorage.setItem("TimeAxisIndex","-1");
                        localStorage.setItem("IsClickNum1","false");
                        $('.left-menu-box li').eq(parseInt(localIndex)).addClass('active');
                        _flag = true;
                        return void 0;
                    }
                    for(var i = 0; i<_self.heightBox.length;i++){
                        if( _self.toTop > _self.heightBox[i].height && _self.toTop < _self.heightBox[i+1].height){
                            $('.left-menu-box li').eq(i+1).addClass("active");
                            _flag = true;
                            localStorage.setItem("TimeAxisIndex",i+"");
                        }
                    }
                    if(!_flag && _self.toTop<_numone_position){
                        $('.left-menu-box li').eq(0).addClass('active');
                        _flag = true;
                    }
                }
            }

        })
    };
    BasicInfo.prototype.getSingle = function () {
        var result;
        return function(){
            return result||(result = fn.apply(this,arguments))
        }
    };
    BasicInfo.prototype.initRadios = function(){
        var _self = this;
        commonCPY.radios(function(obj){
            if($(obj).parent().hasClass('saleType-wrap')){
                if($(obj).hasClass('Inquiry')){
                    $('.inquiry-private').show();
                }else{
                    $('.inquiry-private').hide();
                }
            }
        });
    };
    BasicInfo.prototype.initDateTimePicker = function (el) {
        commonCPY.dateTimePicker(['DeadlineMonth','ValidMonth'],function(value, date, endDate){

        });
        commonCPY.datePicker(['LaycanMonthS','LaycanMonthE','startEtb','VoyageStart','CreateStartTime','NorTime','CreateEndTime','AnchorTime','startTime','startTime1','FirstTime','startTime2','DateTime','IndTime','DaTime2','ShoreTime','startTime3','endTime3','tableTimes','tableTimee','comTime','ShoreArmTime','CargoTime','LtrTime','DateTime4'],function(value, date, endDate){
            var value=value[0].split(' ').join('/')

        });
    };
    BasicInfo.prototype.initDatePicker = function(){
        var startTime = commonCPY.picker('startTime');
        var endTime = commonCPY.picker('endTime');
        startTime();
        endTime();
    };
    BasicInfo.prototype.checkForm = function(){
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
    BasicInfo.prototype.deleteTr = function(){
        var _self = this;
        $('.inquiry-private-table').on('click','tbody .delete',function(){
            var tbody = $(this).parents('tbody');
            $(this).parents('tr').remove();
            //重新排序
            var len = tbody.find('tr').length
            for(var i = 0; i<len; i++){
                tbody.find('tr').eq(i).find('td:first-child').text(i+1);
            }

        })
    };
    //左侧菜单点击跳转事件
    BasicInfo.prototype.leftMenuclick = function () {
        var _self = this;
        $('.left-menu-box li').click(function(){
            _self.isEnableAutoScroll = false;
            var index = $(this).index();
            $('.left-menu-box li').removeClass('active');
            $('html,body').animate({
                scrollTop:_self.heightBox[index].height - 10
            },300,function () {
                _self.isEnableAutoScroll = true;
            })
            localStorage.setItem("TimeAxisIndex",index+"");
            localStorage.setItem("IsClickNum1",_self.isClickNum1);
            $('.left-menu-box li').eq(index).addClass("active");
        })
    };
    BasicInfo.prototype.uploadStandard = function () {
        commonCPY.uploadFiles(['.upload-standard1','.upload-standard2'],function(file){
            $(this).siblings('.file-name').text(file.name);
        })
    };
    BasicInfo.prototype.createQuoter = function () {
        var _self = this;
        layui.use('layer', function(){

            var layer = layui.layer;

            this.indexCompany = layer.open({
                type: 1
                ,title:'Add Buyer'
                ,shadeClose:true
                ,area:['740px','452px'],
                content: '<div class="basic-info-modal-content">'+
                '<div class="search-wrap">'+
                '<label for="">Company：</label>'+
                '<input type="text" placeholder="Search">'+
                '</div>'+
                '</div>'+
                '<div class="modal-table" id="companyContent">'+
                '<table class="layui-hide" id="Company">'+
                '</table>'+
                '</div>'+ //这里content是一个普通的String
                '<div class="modal-btn clearfix">'+
                '<button class="cancel fr">Cancel</button>'+
                '<button class="on-shelf fr" onclick="_self.addEnterpriseList()">Add</button>'+
                '</div>',
                success:function(){
                    _self.dealModalTitle();
                    _self.createTable();
                    _self.cancel(layer);
                }
            });
        });
    };
    BasicInfo.prototype.cancel = function (layer) {
        var _self = this;
        $('.modal-btn .cancel').on('click',function () {
            layer.close(layer.index);
        })
    };
    BasicInfo.prototype.createTable = function () {
        var url = appServer + '/getAllEnterpriseList.json';
        var _self = this;
        $.get(url, {}, function(data) {
            _self.listTableData = data.datas
            layui.use('table', function() {
                var table = layui.table;
                _self.table = table
                _self.tableRender(data.datas)
            });
        });
    };
    BasicInfo.prototype.tableRender = function (data) {
        this.table.render({
            elem: '#Company'
            ,data: data
            ,limit: 10000
            ,width: 680
            ,height: 220
            ,theme:'#F5650A'
            ,cols: [[
                {type:'checkbox',width:108}
                ,{field:'name', width:552, title: 'Company',align: 'center'}
                ,{field:'memberId', style:'display:none'}
            ]]
        });
    };
    BasicInfo.prototype.addEnterpriseList = function () {

        $('.inquiry-private-table tbody').remove();

        var imageUrl = appServer + '/images/delete-icon.png';
        var enterpriseList = this.table.checkStatus('Company').data;

        for (var i = 0; i < enterpriseList.length; i++) {
            var enterprise = enterpriseList[i];

            var enterpriseNameToAdd = enterprise.name;
            var enterpriseIdToAdd = enterprise.memberId;

            var tr =
                '<tr>' +
                '<td>' + (i + 1) + '</td>' +
                '<td>' + enterpriseNameToAdd + '</td>' +
                '<td class="delete" style="background: url(' + imageUrl + ') no-repeat center center"></td>' +
                '<td style="display:none">' + enterpriseIdToAdd + '</td>'
            '</tr>';

            $('.inquiry-private-table').append(tr);
        }

        layui.layer.closeAll();
    };
    BasicInfo.prototype.dealModalTitle = function () {
        $('.layui-layer-title').css({
            'padding':'0 80px 0 30px'
        })
        $('.layui-layer-title').append('<span class="modal-title-bg" style=""></span>')
    };
    BasicInfo.prototype.addQuoter = function () {
        var _self = this;
        $('.add-quoter').on('click',function () {
            _self.createQuoter()
        })
    };
    BasicInfo.prototype.fuzzyQuery = function (keyWord) {
        if(!this.listTableData || Array.isArray(this.listTableData)||this.listTableData.length==0){
            return;
        }
        var len = this.listTableData.length;
        var arr = [];
        if(!keyWord){
            //如果输入框是空的情况
            this.tableRender(this.listTableData);
            return;
        }
        var reg = new RegExp(keyWord);
        for(var i=0;i<len;i++){
            //如果字符串中不包含目标字符会返回-1
            if(this.listTableData[i].match(reg)){
                arr.push(this.listTableData[i]);
            }
        }
        return arr;
    };
    BasicInfo.prototype.tableSearch = function () {
        var _self = this
        var obj = []
        $(document).on('keyup','.search-wrap input',this.throttle(function () {
            obj = _self.fuzzyQuery();
            this.tableRender(obj);
        },300));
    };
    BasicInfo.prototype.bindEvent = function(){
        this.addQuoter();
        this.deleteTr();
        this.leftMenuclick();
        this.uploadStandard();
        this.tableSearch();


    };
    return {
        BasicInfo : BasicInfo(),
        commonClass:commonClass()
    }
})()