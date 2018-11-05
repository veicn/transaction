<template>
  <div class="list">
    <!-- 船舶确认单列表 -->
    <div v-title>Vessel Acceptance</div>

    <mt-search class="h100" v-model="search" cancel-text="cancel" placeholder="search">
    </mt-search>
    <!-- content -->
    <!-- <mt-loadmore :top-method="loadTop" ref="loadmore"> -->
      <div v-for="v in datas" class="l-con">
        <div class="tit clearfix">
          <span>{{v.vesselName}}</span>
          <mt-button :class="'fr b '+doStatus[v.status/10-1].col">{{doStatus[v.status/10-1].txt}}</mt-button>
        </div>
        <div class="l-c">
          <ol>
            <li>
              <span> Product :</span>
              <p>{{v.product}}</p>
            </li>
            <li>
              <span>Port Of Loading :</span>
              <p>{{v.portOfLoading}}</p>
            </li>
            <li>
              <span>Port of Discharge :</span>
              <p> {{v.portOfDischarge}}</p>
            </li>
            <li>
              <span>Laycan :</span>
              <p>{{v.laycanStrat}}-{{v.laycanEnd}}</p>
            </li>
            <li>
              <span>Shipping Agent :</span>
              <p>{{v.shippingAgent}} </p>
            </li>
            <li>
              <span>Quantity(Tons) :</span>
              <p>{{v.quantity}}±{{v.rangeOfError}}% </p>
            </li>
          </ol>
        </div>
        <!-- <div class="d">
          <router-link :to="{ path: 'mytrack', query: { uuid:v.uuid }}">
            <div class="d-btn  sld">Logistics Tracking</div>
          </router-link>
          <router-link :to="{ path: 'list/details', query: { uuid:v.uuid }}">
            <div class="d-btn ">Details</div>
          </router-link>
        </div> -->
        <!-- <div class="d">
          <router-link :to="{ path: 'list/details', query: { uuid:v.uuid }}">
            <div>Details</div>
          </router-link>
        </div>
        <div class="list-xx">
          <router-link class="sld" :to="{ path: 'mytrack', query: { uuid:v.uuid }}">
            <span>Logistics Tracking</span>
          </router-link>
          <router-link class="sld" :to="{ path: 'Interface', query: { uuid:v.uuid }}">
            <span>Voyage Tracking</span>
          </router-link>
          <router-link class="w20 " :to="{ path: 'list/details', query: { uuid:v.uuid }}">
            <span>Details</span>
          </router-link>
        </div> -->
        <div class="d" v-if="v.status=='10'||v.status=='20'||v.status=='80'">
          <router-link :to="{ path: 'list/details', query: { uuid:v.uuid }}">
            <div>Details</div>
          </router-link>
        </div>
        <div class="d" v-if="v.status=='70'">
          <router-link :to="{ path: 'mytrack', query: { uuid:v.uuid,type:1 }}">
            <div class="d-btn  sld">Logistics Tracking</div>
          </router-link>
          <router-link :to="{ path: 'list/details', query: { uuid:v.uuid }}">
            <div class="d-btn ">Details</div>
          </router-link>
        </div>
        <div class="list-3l" v-if="v.status=='30'||v.status=='40'||v.status=='50'||v.status=='60'">
          <router-link class="sld" :to="{ path: 'mytrack', query: { uuid:v.uuid,type:1 }}">
            <span>Logistics Tracking</span>
          </router-link>
          <router-link class="sld" :to="{ path: 'Interface', query: { uuid:v.uuid }}">
            <span>Voyage Tracking</span>
          </router-link>
          <router-link class="w20 " :to="{ path: 'list/details', query: { uuid:v.uuid }}">
            <span>Details</span>
          </router-link>
        </div>

      </div>
    <!-- </mt-loadmore> -->

    <!-- <div v-for="v in datas" class="l-con">
      <div class="tit clearfix">
        <span>Eagla Matsuyama</span>
        <mt-button class="fr b tbs">To be started</mt-button>
      </div>
      <div class="l-c">
        <ol>
          <li>
            <span> Product :</span>
            <p>{{v.product}}</p>
          </li>
          <li>
            <span>Port Of Loading :</span>
            <p>{{v.portOfLoading}}</p>
          </li>
          <li>
            <span class="s-hang2">Port of Discharge :</span>
            <p class="p-hang2 p-b2"> {{v.portOfDischarge}}</p>
          </li>
          <li>
            <span>Laycan :</span>
            <p>{{v.laycanStart|fmtTime}}-{{v.laycanEnd|fmtTime}} </p>
          </li>
          <li>
            <span>Shipping Agent :</span>
            <p>{{v.shippingAgent}} </p>
          </li>
          <li>
            <span>Quantity(Tons) :</span>
            <p>{{v.quantity}} </p>
          </li>
        </ol>
      </div>
      <div class="d">

        <router-link to="/list/details">
          <div>Details</div>
        </router-link>
      </div>
    </div> -->

  </div>
</template>

<script>
export default {
  name: "list",
  data() {
    return {
      search: "",
      datas: undefined,
      //status对照表
      doStatus: [
        { num: 10, txt: "To Be Confirmed", col: "tbs" },
        { num: 20, txt: "To Be Begins", col: "tbs" },
        { num: 30, txt: "Voyage Begins", col: "tbs" },
        { num: 40, txt: "Loaded", col: "tbs" },
        { num: 50, txt: "In Transit", col: "tbs" },
        { num: 60, txt: "Discharged", col: "tbs" },
        { num: 70, txt: "Voyage Finish", col: "tbs" },
        { num: 80, txt: "Suspended", col: "end" }
      ]
    };
  },
  components: {},
  watch: {
    search: function(e) {
      this.con(e);
    }
  },
  created() {
    this.con();
  },
  methods: {
    con: function(keywords) {
      this.$api.get(
        "pages/back/vesselAcceptance/wechat/getConfirmationSheetList.json",
        {
          type: this.$route.query.type,
          epMemberId: sessionStorage.getItem("epMemberId"),
          //   epMemberId: 100008254,
          keywords: keywords
        },
        e => {
          if (e.status == 0) {
            this.datas = e.datas;
          }
          console.log(e);
        }
      );
    },
    loadTop() {
      // 加载更多数据
      this.$refs.loadmore.onTopLoaded();
    }
  },
  mounted() {}
};
</script>

<style lang="less">
@color: #f36523;

.h100 {
  height: 100% !important;
}
.l-con .l-c ol li p {
    padding: 6px 0 0 10px !important; ;
}

// .list-xx {
//   width: 100%;
//   height: 40px;
//   line-height: 40px;
//   font-size: 15px;
//   border-top: 1px solid #ececec;
//   text-align: center;
//   a {
//     width: 33%;
//     height: 40px;
//     line-height: 40px;
//     color: @color;
//     display: inline-block;
//     position: relative;
//     &.sld::after {
//       content: "";
//       display: block;
//       height: 19px;
//       position: absolute;
//       border-right: 1px solid #ececec;
//       top: 10px;
//       left: 103%;
//     }
//   }
//   .w20 {
//     width: 20%;
//   }
// }
// .l-con {
//   width: 100%;
//   margin: 10px 0 0 0;
//   background: #ffffff;
//   padding: 10px 0 0 0;
//   .tit {
//     height: 30px;
//     line-height: 30px;
//     span {
//       ont-family: Arial-BoldMT;
//       font-size: 17px;
//       color: #333333;
//       letter-spacing: 0.08px;
//       margin: 0 0 0 15px;
//     }
//     .b {
//       height: 30px;
//       font-family: ArialMT;
//       font-size: 14px;
//       color: #ffffff;
//       letter-spacing: 0.06px;
//       margin: 0 5px 0 0;
//     }
//     .tbs {
//       background: #03b675;
//     }
//     .end {
//       width: 100%;
//       opacity: 0.6;
//       background: #1e1e1e;
//     }
//   }
//   .l-c {
//     ol {
//       li {
//         font-family: ArialMT;
//         font-size: 14px;
//         letter-spacing: 0;
//         list-style: none;
//         padding: 0 10px;
//         overflow: hidden;
//         span {
//           width: 116px;
//           height: 28px;
//           float: left;
//           line-height: 28px;
//           color: #666666;
//           text-align: right;
//         }
//         p {
//           color: #151515;
//           overflow: hidden;
//           font-size: 13px;
//           padding: 3px 0 0 10px;
//         }
//       }
//     }
//   }
//   .d {
//     width: 100%;
//     height: 40px;
//     line-height: 40px;
//     font-size: 15px;
//     border-top: 1px solid #ececec;
//     text-align: center;
//     position: relative;
//     a {
//       color: @color;
//       .d-btn {
//         width: 49%;
//         display: inline-block;
//         float: left;
//       }
//       .sld::after {
//         content: "";
//         display: block;
//         height: 19px;
//         position: absolute;
//         border-right: 1px solid #ececec;
//         top: 8px;
//         left: 50%;
//       }
//     }
//   }
// }
// .s-hang2 {
//   height: 56px !important;
//   line-height: 24px !important;
// }
// .p-hang2 {
//   padding: 27px 0 0 10px !important;
// }
// .p-b2 {
//   padding-left: 2px;
//   box-sizing: border-box;
// }
</style>
