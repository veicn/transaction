import Login from './components/Login.vue'
import NotFound from './components/404.vue'
import Home from './components/Home.vue'
import Main from './views/Main.vue'
import Table from './views/nav1/Table.vue'
import Form from './views/nav1/Form.vue'
import user from './views/nav1/user.vue'
import Page4 from './views/nav2/Page4.vue'
import Page5 from './views/nav2/Page5.vue'

// 资讯模块
import informationList from './views/information/list.vue'
import informationAddList from './views/information/addList.vue'
import informationChannel from './views/information/channel.vue'
import informationWeihu from './views/information/weihu.vue' 

//test- 测试
import test from './components/test.vue';




let routes = [
	{
        path: '/test',
        component: test,
        name: '',
        hidden: true
    },
    {
        path: '/login',
        component: Login,
        name: '',
        hidden: true
    },
    {
        path: '/404',
        component: NotFound,
        name: '',
        hidden: true
    },
    {
        path: '/nav1',
        component: Home,
        name: '导航一',
        iconCls: 'el-icon-message', //图标样式class
        leaf: false,
        children: [
            {
            	path: 'main',
            	component: Main,
            	name: '主页', 
            	leaf: true,
            },
            { 
            	path: 'table',
            	component: Table,
            	name: 'Table',
            	leaf: true
            },
            { 
            	path: 'form',
            	component: Form,
            	name: 'Form',
            	leaf: true
            },
            { 
            	path: 'user',
            	component: user,
            	name: '列表',
            	leaf: true
            }
        ]
    },
    {
        path: '/nav2',
        component: Home,
        name: '导航二',
        iconCls: 'fa fa-id-card-o',
        children: [
            { 
            	path: 'page4', 
            	component: Page4,
            	name: '页面4',
            	leaf: true
            },
            {
            	path: 'page5',
            	component: Page5,
            	name: '页面5',
            	leaf: true
            }
        ]
    },
    {
    	path: '/information',
        component: Home,
        name: '资讯',
        iconCls: 'fa fa-id-card-o',
        children: [
            { 
            	path: 'list', 
            	component: informationList,
            	name: '资讯列表',
            	leaf: true,
            },
    		{
    			path: 'addList', 
    			component: informationAddList,
    			name: '添加资讯列表',
    			leaf: true
    		},
    		{
    			path: 'channel', 
    			component: informationChannel,
    			name: '频道维护',
    			leaf: true
    		},
    		{
    			path: 'weihu', 
    			component: informationWeihu,
    			name: '频道分类维护',
    			leaf: true
    		}
    		
        ]
    },
    {
        path: '*',
        hidden: true,
        redirect: { path: '/404' }
    }
];

export default routes;