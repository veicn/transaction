
var globleBucket = "sinochem-b2b-uat";
var configUitl = {};
var urlStr = window.location.href;

if(urlStr.indexOf('info.mycrudeoil')!=-1 || urlStr.indexOf('ship.mycrudeoil')!=-1){   // 生产环境 
	globleBucket = "sinochem-b2b-pro";
} else if(urlStr.indexOf('ship9.mycrudeoil')!=-1 || urlStr.indexOf('info9.mycrudeoil')!=-1){    // uat
	globleBucket = "sinochem-b2b-uat";
} else {    // 本地开发   与   测试 
	globleBucket = "sinochem-b2b-dev";
}

/**
 * 获取oss 上传路径
 */
configUitl.getOssServerPath = function(infoServer,dir){
	return infoServer + '/oss/policy.json?bucket='+globleBucket+'&dir='+dir;
}
