import Vue from 'vue'
import Router from 'vue-router'
import login from "@/components/AccountBinding/login"
import home from "@/components/AccountBinding/home"
import company from '../components/AccountBinding/information/company'
import personal from "../components/AccountBinding/information/personal"
import ModifyThePassword from "@/components/AccountBinding/information/ModifyThePassword"


import mytrack from "@/components/LogisticsTracking/mytrack"
import list from "@/components/ListOfVariousTypes/VesselAcceptance/list"
import listSA from "@/components/ListOfVariousTypes/VesselAcceptance/listSA"
import ListOfVariousTypesDetails from "@/components/ListOfVariousTypes/VesselAcceptance/details"
import demand from '@/components/ListOfVariousTypes/CharteringRequirement/demand'
import DemandDetails from '@/components/ListOfVariousTypes/CharteringRequirement/DemandDetails'
import CAlist from '@/components/ListOfVariousTypes/CharterParty/CAlist'
import CAlist1 from '@/components/ListOfVariousTypes/CharterParty/CAlist1'

import CAlistDetails from '@/components/ListOfVariousTypes/CharterParty/CAlistDetails'
//装港装货信息
import Interface from '../components/ListOfVariousTypes/CharterParty/LoadingPort/Interface'
import progress from '../components/ListOfVariousTypes/CharterParty/LoadingPort/progress'
import ltsubmit from '../components/ListOfVariousTypes/CharterParty/LoadingPort/ltsubmit'
import ltsubmit1 from '../components/ListOfVariousTypes/CharterParty/LoadingPort/ltsubmit1'

// 交易路由
import transList from '@/components/transInfo/transList'
import transDetails from '@/components/transInfo/transDetails'
// 测试路由
import Test from '../components/Test/Test'
import map from "@/components/LogisticsTracking/cont/map"

Vue.use(Router)

export default new Router({
  // base: '/app/',
  routes: [
    // <tab栏相关的路由>
    {
      path: "/",
      redirect: "/home"
    },
    {
      path: "/home",
      component: home,
      meta: { keepAlive: true }
    }, //登陆
    {
      path: "/home/company",
      name: "company",
      component: company
    },
    {
      path: "/home/personal",
      name:'personal',
      component: personal
    },
    {
      path: "/home/ModifyThePassword",
      component: ModifyThePassword
    },
    {
      path: "/mytrack",
      component: mytrack
    }, //物流跟踪
    {
      path: "/list",
      component: list
    }, //船舶确认单
    {
      path: "/listSA",
      component: listSA
    }, //船舶确认单
    {
      path: "/list/details",
      component: ListOfVariousTypesDetails
    }, //船舶确认单详情
    {
      path: "/demand",
      component: demand
    }, //租船需求
    {
      path: "/demand/DemandDetails",
      component: DemandDetails
    }, //租船需求详情
    {
      path: "/CAlist",
      component: CAlist
    }, //贸易商 租船协议列表
    {
      path: "/CAlist1",
      component: CAlist1
    }, //租船代理 租船协议列表
    {
      path: "/CAlist/CAlistDetails",
      component: CAlistDetails
    }, //租船协议详情
    {
      path: "/Interface",
      component: Interface
    },
    {
      path: "/progress",
      component: progress
    },
    {
      path: "/ltsubmit",
      component: ltsubmit
    },
    {
      path: "/ltsubmit1",
      component: ltsubmit1
    },
    // 交易路由
    {
      path: "/transInfo",
      component: transList
    },
    {
      path: "/transInfo/transDetails",
      component: transDetails
    },
    {
      path: "/map",
      component: map
    }
  ],
  // mode: 'history',
  // base:'/dist/'
})
