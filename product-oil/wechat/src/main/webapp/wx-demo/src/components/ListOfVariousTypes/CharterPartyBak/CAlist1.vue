<template>
    <div class="list">
        <!--租船代理  租船协议列表 -->
        <div v-title>Charter Party</div>
        <mt-search class="h100" v-model="search" cancel-text="cancel" placeholder="search">
        </mt-search>
        <!-- content -->
        <!-- <mt-loadmore :top-method="loadTop" ref="loadmore"> -->

            <div v-for="v in datas" class="l-con">
                <div class="tit clearfix">
                    <span>{{v.vesselName}}</span>
                     <!-- <span>{{doStatus[parseInt(v.status/10)-1].txt}}</span> -->
                    <mt-button v-if="v.status" :class="'fr b '+doStatus[parseInt(v.status/10-1)].col">{{doStatus[parseInt(v.status/10-1)].txt}}</mt-button>
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
                            <p>{{v.quantity}}</p>
                        </li>
                    </ol>
                </div>

                <div class="d" v-if="v.status=='10'||v.status=='20'||v.status=='80'">
                    <router-link :to="{ path: 'CAlist/CAlistDetails', query: { uuid:v.uuid }}">
                        <div>Details</div>
                    </router-link>
                </div>
                <div class="d" v-if="v.status=='30'||v.status=='40'||v.status=='50'||v.status=='60'||v.status=='70'">
                    <router-link :to="{ path: 'mytrack', query: { uuid:v.uuid,type:2 }}">
                        <div class="d-btn  sld">Logistics Tracking</div>
                    </router-link>
                    <router-link :to="{ path: 'CAlist/CAlistDetails', query: { uuid:v.uuid }}">
                        <div class="d-btn ">Details</div>
                    </router-link>
                </div>
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
        { num: 10, txt: "To Be Confirmed", col: "tbs" },
        { num: 20, txt: "Confirmed", col: "tbs" },
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
        console.log(e)
      this.con(e);
    }
  },
  created() {
  },
  methods: {
    con: function(e) {
      this.$api.post(
        "pages/back/agreement/wechat/getAgreementListZcdl.json",
        {
            "epMemberId":sessionStorage.getItem('epMemberId'),
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
    loadTop() {
      // 加载更多数据
      this.$refs.loadmore.onTopLoaded();
    }
  },
  mounted() {
    this.con();
  }
};
</script>

<style lang="less">
@color: #f36523;

.h100 {
  height: 100% !important;
}
</style>
