<template>
    <div class="mytrack">
        <div v-title>Logistics Tracking</div>
        <!-- content -->
        <div class="l-c">
            <ol>
                <li>
                    <span> Vessel's name :</span>
                    <p>{{datas.vesselName}}</p>
                </li>
                <li>
                    <span> Product :</span>
                    <p> {{datas.product}}
                    </p>
                </li>
                <li>
                    <span>Quantity（Tons）:</span>
                    <p>{{datas.quantity}} ± {{datas.rangeOfError}} %
                    </p>
                </li>
                <li>
                    <span>Port Of Loading :</span>
                    <p>{{datas.portOfLoading}}</p>
                </li>
                 <li>
                    <span>Port Of Discharge :</span>
                    <p>{{datas.portOfDischarge}}</p>
                </li>
            </ol>
        </div>
        <!-- tab -->
        <mt-navbar v-model="selected" class="tab-c">
            <mt-tab-item id="1">Voyage <br> Start
            </mt-tab-item>
            <mt-tab-item id="2">Loading <br> Port
            </mt-tab-item>
            <mt-tab-item v-if="!isFOB" id="3">Voyage Tracking
            </mt-tab-item>
            <mt-tab-item v-if="!isFOB" id="4">Port Of Discharge
            </mt-tab-item>

        </mt-navbar>

        <!-- tab-container -->
        <mt-tab-container v-model="selected">
            <mt-tab-container-item id="1" >
                <tem1 :title="toChildData" :imo='imo'></tem1>
            </mt-tab-container-item>
            <mt-tab-container-item id="2">
                <tem2 :title="toChildData"></tem2>
            </mt-tab-container-item>
            <mt-tab-container-item id="3">
                <tem3 :title="toChildData"></tem3>
            </mt-tab-container-item>
            <mt-tab-container-item id="4">
                <tem4 :title="toChildData"></tem4>
            </mt-tab-container-item>
        </mt-tab-container>

    </div>
</template>

<script>
import tem1 from "./cont/mytrack-cont1.vue";
import tem2 from "./cont/mytrack-cont2.vue";
import tem3 from "./cont/mytrack-cont3.vue";
import tem4 from "./cont/mytrack-cont4.vue";
export default {
  name: "mytrack",
  data() {
    return {
      selected: "1",
      datas: undefined,
      isFOB:false,
      toChildData:null,
      imo:null
    };
  },
  components: {
    tem1,
    tem2,
    tem3,
    tem4
  },
  watch: {},
  created() {},
  methods: {
    con: function() {
      this.$api.post(
        // "shipping/pages/back/LogisticsTracking/wechat/getStowagePlan.json",
        // /pages/back/LogisticsTracking/wechat/getAllDatas.json
        "pages/back/LogisticsTracking/wechat/getAllDatas.json",
        {
          uuid: this.$route.query.uuid,
          type:this.$route.query.type
        },
        e => {
          if (e.status == 0) {
            this.datas = e.datas.confirmationSheetVO;
            this.toChildData = e.datas;
            this.unLoadDatas = e.datas.transitUnLoadingVOList;
            this.imo=e.datas.confirmationSheetVO.imo
            if(e.datas.confirmationSheetVO.tradeTerms == "FOB"){
              this.isFOB = true;
            }
          }

        }
      );
    }
  },
  mounted() {
    this.con();
  }
};
</script>

<style lang="less">
@color: #f36523;
</style>
