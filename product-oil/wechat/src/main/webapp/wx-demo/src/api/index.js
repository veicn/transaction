import axios from 'axios'
axios.defaults.withCredentials = true

// 验证环境 并设置请求入口
const dataFormURL = (function(glob){
  let _host = window.location.host.split(".");
  return {
      wechat: '//wechat.test.mycrudeoil.com',
      shipping: '//shipping.test.mycrudeoil.com',
      transaction: '//transaction.test.mycrudeoil.com'
  }
  if(_host.includes('wechat') && _host.includes('test')){
    return {
      wechat: '//wechat.test.mycrudeoil.com',
      shipping: '//shipping.test.mycrudeoil.com',
      transaction: '//transaction.test.mycrudeoil.com',
    }
  }
  if(_host.includes('wechat9')){
    return {
      wechat: '//wechat9.mycrudeoil.com',
      shipping: '//shipping9.mycrudeoil.com',
      transaction: '//transaction9.mycrudeoil.com',
    }
  }
  return {
    wechat: '//wechat.mycrudeoil.com',
    shipping: '//shipping.mycrudeoil.com',
    transaction: '//transaction.mycrudeoil.com',
  }
})(window)

// 船务
function apiAxios(method, url, params, callback) {
  url = url || '';
  axios({
      method: method,
      url:dataFormURL.shipping+'/'+url,
      data: method === 'POST' || method === 'PUT' ? params : null,
      params: method === 'GET' || method === 'DELETE' ? params : null,
      withCredentials: false
    })
    .then(function (res) {
      callback && callback(res.data);
    })
    .catch(function (err) {
      callback
    })
}
// 微信
function apiAxiosWx(method, url, params, callback,errback) {
  url = url || '';
  axios({
      method: method,
      url:dataFormURL.wechat +url,
      data: method === 'POST' || method === 'PUT' ? params : null,
      params: method === 'GET' || method === 'DELETE' ? params : null,
      withCredentials: false
    })
    .then(function (res) {
      callback && callback(res.data);
    })
    .catch(function (err) {
      errback && errback();
    })
};
// 交易
function apiAxiosInfo(method, url, params, callback) {
  url = url || '';
  axios({
      method: method,
      url:dataFormURL.transaction+url,
      data: method === 'POST' || method === 'PUT' ? params : null,
      params: method === 'GET' || method === 'DELETE' ? params : null,
      withCredentials: false
    })
    .then(function (res) {
      callback && callback(res.data);
    })
    .catch(function (err) {
      callback
    })
}
// 原油
function apiAxiosCrude(method, url, params, callback) {
  url = url || '';
  axios({
      method: method,
      url:url,
      data: method === 'POST' || method === 'PUT' ? params : null,
      params: method === 'GET' || method === 'DELETE' ? params : null,
      withCredentials: false
    })
    .then(function (res) {
      callback && callback(res.data);
    })
    .catch(function (err) {
      callback
    })
}

export default {
  get: function (url, params, callback) {
    return apiAxios('GET', url, params, callback)
  },
  post: function (url, params, callback) {
    return apiAxios('POST', url, params, callback)
  },
  getWx: function (url, params, callback) {
    return apiAxiosWx('GET', url, params, callback)
  },
  postWx: function (url, params, callback,errback) {
    return apiAxiosWx('POST', url, params, callback,errback)
  },
  getInfo: function (url, params, callback) {
    return apiAxiosInfo('GET', url, params, callback)
  },
  crudePost:function(url, params, callback,errback) {
    return apiAxiosCrude('POST', url, params, callback,errback)
  },

}
