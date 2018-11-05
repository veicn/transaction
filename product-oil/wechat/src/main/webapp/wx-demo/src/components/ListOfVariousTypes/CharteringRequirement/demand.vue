<template>
  <div class="list">
    <!-- 租船需求列表 -->
    <div v-title>Chartering Requirement</div>

    <mt-search class="h100" v-model="search" cancel-text="cancel" placeholder="search">
    </mt-search>
    <!-- content -->

    <!-- <mt-loadmore :top-method="loadTop" ref="loadmore"> -->

      <div v-for="v in datas">
        <router-link :to="{ path: 'demand/DemandDetails', query: { uuid:v.uuid }}">
          <div class="l-con">
            <div class="tit clearfix">
              <span>{{v.prodoctNm}}</span>
              <mt-button v-if="v.status" :class="'fr b '+doStatus[v.status/10-1].col">{{doStatus[v.status/10-1].txt}}</mt-button>
            </div>
            <div class="l-c">
              <ol>
                <li>
                  <span> Quantity(Tons) :</span>
                  <p>{{v.quantity}} ± {{v.rangeOfError}} %</p>
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
                  <p>{{v.laycanStart}}-{{v.laycanEnd}} </p>
                </li>
                <li>
                  <span>Trade Terms :</span>
                  <p>{{v.tradeTerms}} </p>
                </li>
              </ol>
            </div>
          </div>
        </router-link>

      </div>

    <!-- </mt-loadmore> -->

  </div>
</template>

<script>
export default {
  data() {
    return {
      search: "",
      datas: undefined,
      //status对照表
      doStatus: [
        { num: 10, txt: "Pengding", col: "tbs" },
        { num: 20, txt: "To Be Confirmed", col: "tbs" },
        { num: 30, txt: "Confirmed", col: "tbs" },
        { num: 40, txt: "Underway", col: "tbs" },
        { num: 50, txt: "Completed", col: "tbs" }
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
        "pages/back/demands/wechat/getDemandsList.json",
        {
          type: this.$route.query.type,
          epMemberId: sessionStorage.getItem("epMemberId"),
          keywords:keywords
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
