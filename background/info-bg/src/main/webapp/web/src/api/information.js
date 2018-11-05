import axios from '../axios';

let base = 'http://192.168.8.105:8080';

//export const getList = params => { return axios.get(`${base}/info/tChannelM/channelMList.json`, { params: params }); };


export const postList = params => { return axios.post(`${base}/info/tChannelM/channelMList.json`, params).then(res => res.data); };

export const addList = params => { return axios.post(`${base}/info/tChannelM/saveChannelM.json`, params).then(res => res.data); };

export const removeList = params => { return axios.get(`${base}/info/tChannelM/delChannelM.json`, { params: params }).then(res => res.data); };

export const changeList = params => { return axios.post(`${base}/info/tChannelM/saveChannelM.json`, params).then(res => res.data); };