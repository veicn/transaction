import Axios from 'axios'

//Axios.defaults.headers['Content-Type'] = 'application/json;charset=UTF-8';

//Axios.defaults.headers['Content-Type'] = 'application/json;charset=UTF-8';

export default Axios.create({
	//baseURL: '',
	headers:{  // 设置请求头
		//'Authorization':'211221212'
	},
})
