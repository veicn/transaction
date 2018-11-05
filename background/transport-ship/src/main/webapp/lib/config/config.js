
var globleBucket = "sinochem-b2b-dev";
var configUitl = {};
var urlStr = window.location.href;

if(urlStr.indexOf('ship9.mycrudeoil')!=-1 || urlStr.indexOf('info9.mycrudeoil')!=-1){   // uat
	globleBucket = "sinochem-b2b-uat";
} else if(urlStr.indexOf('info.mycrudeoil')!=-1 || urlStr.indexOf('ship.mycrudeoil')!=-1){    // pro
	globleBucket = "sinochem-b2b-pro";
} else {    // 本地开发   与   测试 
	globleBucket = "sinochem-b2b-dev";
}
console.log(globleBucket)
/**
 * 获取oss 上传路径
 */
configUitl.getOssServerPath = function(shipServer,dir){
	return shipServer + '/oss/policy.json?bucket='+globleBucket+'&dir='+dir;
}
