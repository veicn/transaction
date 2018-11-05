var configURL = null;
var urlStr = window.location.href;
var globleBucket = "sinochem-b2b-uat";
var configUitl = {};

if(urlStr.indexOf('ship9.mycrudeoil')!=-1 || urlStr.indexOf('info9.mycrudeoil')!=-1){   // uat 
	configURL={
		infoUrl:'http://info9.mycrudeoil.com',
		shipUrl:'http://ship9.mycrudeoil.com',
		index:'http://www9.mycrudeoil.com',
		shopHall:"http://trade9.mycrudeoil.com",
		member:'http://member9.mycrudeoil.com',
		sys:'http://sys9.mycrudeoil.com',
		appUrl:'http://app7.1chemic.com',
		
	}
	globleBucket = "sinochem-b2b-uat";
}else if(urlStr.indexOf('info.mycrudeoil')!=-1 || urlStr.indexOf('ship.mycrudeoil')!=-1){    // 生产
	configURL={
		infoUrl:'http://info.mycrudeoil.com',
		shipUrl:'http://ship.mycrudeoil.com',
		index:'http://www.mycrudeoil.com',
		shopHall:"http://trade.mycrudeoil.com",
		member:'http://member.mycrudeoil.com',
		sys:'http://sys.mycrudeoil.com',
		appUrl:'http://app9.1chemic.com',
	}
	globleBucket = "sinochem-b2b-pro";
}else{  // 测试环境
	configURL={
		//infoUrl:'http://infodev.test.mycrudeoil.com',// 资讯
		infoUrl:'http://info.test.mycrudeoil.com',// 资讯
		shipUrl:'http://ship.test.mycrudeoil.com',      //  船务
		index:'http://www.test.mycrudeoil.com',         //  大首页 
		shopHall:"http://trade.test.mycrudeoil.com",      //   原油大厅
		member:'http://member.test.mycrudeoil.com/member',
		sys:'http://member.test.mycrudeoil.com/sys',
		
	}
	globleBucket = "sinochem-b2b-dev";
}

// 图片上传相关配置
/**
 * 获取oss 上传路径
 */
configUitl.getOssServerPath = function(infoServer,dir){
	return configURL.infoUrl + '/oss/policy.json?bucket='+globleBucket+'&dir='+dir;
}

