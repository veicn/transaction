<template>
  <div class="list">
    <!-- 贸易商 租船协议列表 -->
    <div v-title>Charter Party</div>
    <mt-search class="h100"
               v-model="search"
               cancel-text="cancel"
               placeholder="search">
    </mt-search>
    <!-- content -->
    <!-- <mt-loadmore :top-method="loadTop" ref="loadmore"> -->

    <div v-for="v in datas"
         class="l-con">
      <div class="tit clearfix">
        <span>{{v.vesselName}}</span>
        <mt-button v-if="v.status"
                   :class="'fr b '+ getStatus(v.status).col">{{ getStatus(v.status).txt}}</mt-button>
        <!-- <mt-button :class=" 'fr b' " ></mt-button> -->
      </div>
      <div class="l-c">
        <ol>
          <li>
            <span> Product :</span>
            <p>{{v.productNm}}</p>
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
            <p>{{v.laycanStrat}}-{{v.laycanEnd}} </p>
          </li>
          <li>
            <span class="s-hang2">Disponent Owner :</span>
            <p class="p-hang2 p-b2"> {{v.charteringAgentNm}}</p>
          </li>
          <li>
            <span>Quantity(Tons) :</span>
            <p>{{v.quantity}} ± {{v.rangeOfError}} %</p>
          </li>
        </ol>
      </div>
      <!-- <div class="d" v-if="!getStatus(v.status)">
          <router-link :to="{ path: 'CAlist/CAlistDetails', query: { uuid:v.uuid }}">
            <div>Details</div>
          </router-link>
        </div> -->
      <div class="d">
        <router-link v-if="getStatus(v.status).isMytrack"
                     :to="{ path: 'mytrack', query: { uuid:v.uuid,type:2 }}">
          <div class="d-btn  sld">Logistics Tracking</div>
        </router-link>
        <router-link :to="{ path: 'CAlist/CAlistDetails', query: { uuid:v.uuid }}">
          <div :class="getStatus(v.status)?'d-btn':'' ">Details</div>
        </router-link>
      </div>
    </div>
    <!-- </mt-loadmore> -->

  </div>
</template>

<script>
export default {
  data () {
    return {
      search:'',
      datas: undefined,
      //status对照表
      doStatus: [
        { num: 10, txt: "To Be Confirmed", col: "tbs" },
        { num: 20, txt: "Confirmed", col: "tbs" },
        { num: 30, txt: "Voyage Begins", col: "tbs" },
        { num: 40, txt: "Loaded", col: "tbs" },
        { num: 50, txt: "In Transit", col: "tbs" },
        { num: 60, txt: "Discharged", col: "tbs" },
        { num: 70, txt: "Voyage Finish", col: "tbs" },
        { num: 80, txt: "Suspended", col: "end" }
      ],
      isShow: true,
    };
  },
  components: {},
  watch: {
    search: function (e) {
      this.con(e);
    }
  },
  created () {
    this.con();
  },
  methods: {
    getStatus (keys) {
      keys = Number.parseInt(keys)
      if (keys < 20) {
        return { txt: "To Be Confirmed", col: "tbs", isMytrack: false }
      }
      if (keys < 30) {
        return { num: 20, txt: "Confirmed", col: "tbs", isMytrack: false }
      }
      if (keys < 40) {
        return { num: 30, txt: "Voyage Begins", col: "tbs", isMytrack: true }
      }
      if (keys < 50) {
        return { num: 40, txt: "Loaded", col: "tbs", isMytrack: true }
      }
      if (keys < 60) {
        return { num: 50, txt: "In Transit", col: "tbs", isMytrack: true }
      }
      if (keys < 70) {
        return { num: 60, txt: "Discharged", col: "tbs", isMytrack: true }
      }
      if (keys < 80) {
        return { num: 70, txt: "Voyage Finish", col: "tbs", isMytrack: true }
      }
      if (keys < 90) {
        return { num: 80, txt: "Suspended", col: "end", isMytrack: false }
      }
      return { num: 999, txt: "other status", col: "end", isMytrack: false }
    },
    con: function (e) {
      this.$api.get(
        "pages/back/agreement/wechat/getAgreementList.json",
        // {productLoadingAndDischarge:e},
        {
          epMemberId: sessionStorage.getItem("epMemberId"),
          productLoadingAndDischarge : this.search
          
        },
        e => {
          if (e.status == 0) {
            this.datas = e.datas;
          }
          console.log(e);
        }
      );
    },
    loadTop () {
      // 加载更多数据
      this.$refs.loadmore.onTopLoaded();
    }
  },
  mounted () { }
};
</script>

<style lang="less">
@color: #f36523;

.h100 {
  height: 100% !important;
}
</style>
