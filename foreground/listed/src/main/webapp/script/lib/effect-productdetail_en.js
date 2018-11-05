(function(){
	var ProductDetail=function(options){
		if(this.init){
			this.init.call(this,options);
		}
	};


	ProductDetail.prototype={
		 $toolbar:$(".toolbar")
		,$toolbarWipe:$(".toolbar-wide")
		,$toolbarButton:$("#toolbarButton")
		,$contrastBox:$("#contrastBox")//对比栏
		,$favoritesBox:$("#favoritesBox")//收藏夹
		,$contrastButton:$("#contrastButton")//对比栏
		,$favoritesButton:$("#favoritesButton")//收藏夹
		
		,init:function(){
			this.bindEvents();
		}
	   ,showToolbar:function(){
	   		var _this=this;
            var right=_this.$toolbar.css("right");
            if(right.replace('px','') >= 0  ) return ;
			this.$toolbar.animate({
				right:"0px"
			},500,function(){
				$("span",_this.$toolbarButton).html("H<br>i<br>d<br>e");
				$("i",_this.$toolbarButton).removeClass('ico-arrow-unfold').addClass("ico-arrow-fold");
			})
		}
	   ,hideToolbar:function(callback){
	   		var _this=this;
            var right=_this.$toolbar.css("right");
            if(right.replace('px','') < 0  ) return ;
            this.$toolbar.animate({
				right:"-280px"
			},500,function(){
				$("span",_this.$toolbarButton).html("O<br>p<br>e<br>n");
				$("i",_this.$toolbarButton).removeClass('ico-arrow-fold').addClass("ico-arrow-unfold");
				callback&&callback.call(_this);
			})
	    }
	    ,toogleToolbar:function(){
            var _this=this;
            var right=_this.$toolbar.css("right");
            if(right.replace('px','') < 0  ){
                _this.showToolbar();
            }else{
                _this.hideToolbar();
            }
		}
	   //收藏夹
	   ,addToFavorites:function(){
	   	
	   }
	   //对比栏
	   ,addToContrast:function(){
	   	
	   }
	   ,bindEvents:function(){
		   	var _this=this;
		   	this.$toolbarButton.bind("click",function(){
		   		_this.toogleToolbar();
		   	});
		   	this.$contrastButton.bind("click",function(){
		   		var width=_this.$toolbar.css("width");

				_this.showToolbar();

		   		 if(_this.$contrastBox.hasClass("action")&&width=="0px"){
		   		 	_this.hideToolbar();
		   		 	
		   		 	return ;
		   		 }
		   		_this.$contrastBox.addClass("action").siblings().removeClass("action");
		   		$(this).addClass("action").siblings().removeClass("action");
		   	});
		   	
		   	this.$favoritesButton.bind("click",function(){
		   		var width=_this.$toolbar.css("width");
  		   		if(width=="-280px"){
		   			_this.showToolbar();
		   		} 
		   		 if(_this.$favoritesBox.hasClass("action")&&width=="0px"){
		   		 	_this.hideToolbar();
		   		 	
		   		 	return ;
		   		 }
		   		_this.$favoritesBox.addClass("action").siblings().removeClass("action");
		   		$(this).addClass("action").siblings().removeClass("action");
		   	});
		   	
		   	$(".toolbar-top").bind("click",function(){
		   		$(window).scrollTop(0);
		   	});
		   	
		   	$(".fav-btn").bind("click",function(e){
				if(!jQuery.memberVerify())return false;
		   		var offset = $('#favoritesButton').offset(), flyer = $('<img width="100" height="100" />');
				 flyer.attr({
				 	"src":$(".picimg-big").attr("src")
				 }).fly({
					    start: {
					        left: e.pageX-50,
					        top: e.page50
					    },
					    end: {
					        left: offset.left,
					        top: offset.top+50,
					        width: 20,
					        height: 20
					    },
					 onStart:function(){
					 },
					 onEnd: function(){
					 }
				})
		   	});
		   	
		   	$(".vs-btn").bind("click",function(e){
		   		var offset = $('#contrastButton').offset(), flyer = $('<img width="100" height="100"/>');
				 flyer.attr({
				 	"src":$(".picimg-big").attr("src")
				 }).fly({
					    start: {
					        left: e.pageX-50,
					        top: e.page50
					    },
					    end: {
					        left: offset.left,
					        top: offset.top+100,
					        width: 20,
					        height: 20
					    },
					 onStart:function(){
					 },
					 onEnd: function(){
					 }
				})
		   	});
		   	
		   	$(".gd-pic-s li").bind("click",function(){
		   		$(this).addClass("select").siblings("li").removeClass("select");
		   		$(".picimg-big").attr({
		   			src:$("img",this).attr("src")
		   		})
		   	});
		   	
		   	$(".ico-close").bind("click",function(){
		   		_this.hideToolbar();
		   	})
	   }
	}
	
	window.ProductDetail=ProductDetail;
})();

$(function(){
	var productDetail=new ProductDetail();
});
