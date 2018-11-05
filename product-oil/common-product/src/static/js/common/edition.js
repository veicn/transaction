// 版本控制
;(function (w) {
var browser=navigator.appName
var b_version=navigator.appVersion
var version=b_version.split(";");
var trim_Version=version[1].replace(/[ ]/g,"");
var urlStr = window.location.href;
var errUrl;
var homeUrl;
var lowVersion = 1;
    if(urlStr.indexOf('news9.mycrudeoil.')!=-1){        //uat
        errUrl = '//news.mycrudeoil.com/version/versionUpgrade.htm';
        homeUrl = '//transaction.mycrudeoil.com/pages/portal/home/index.htm';
    }else if(urlStr.indexOf('.test.')!=-1){       //测试环境
        errUrl = '//news.test.mycrudeoil.com/version/versionUpgrade.htm';
        homeUrl = '//transaction.test.mycrudeoil.com/pages/portal/home/index.htm';
    }else if(urlStr.indexOf('www.sinochem.com')!=-1){        //本地localhost
        errUrl = 'https://www.sinochem.com/version/versionUpgrade.htm';
        homeUrl = 'https://www.sinochem.com/transaction/pages/portal/home/index.htm';
    }else if(urlStr.indexOf('news.mycrudeoil.')!=-1){     //线上
        errUrl = '//news.mycrudeoil.com/version/versionUpgrade.htm';
        homeUrl = '//transaction.mycrudeoil.com/pages/portal/home/index.htm';
    }

    if(browser=="Microsoft Internet Explorer" && trim_Version=="MSIE9.0")
    {
        lowVersion = 0;
    }else if(browser=="Microsoft Internet Explorer" && trim_Version=="MSIE8.0")
    {

        lowVersion = 0;
    }else if(browser=="Microsoft Internet Explorer" && trim_Version=="MSIE7.0")
    {

        lowVersion = 0;
    }else if(browser=="Microsoft Internet Explorer" && trim_Version=="MSIE6.0")
    {

        lowVersion = 0;
    }else if(browser=="Microsoft Internet Explorer" && trim_Version=="MSIE5.0")
    {

        lowVersion = 0;
    }
    if(urlStr.indexOf("versionUpgrade")!=-1){
        if(lowVersion===0){
            return;
        }else{
            window.location.href = homeUrl
        }
    }else{
        if(lowVersion===0){
            window.location.href = errUrl
        }else{
            return;
        }
    }
})(window);

