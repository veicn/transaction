<template>
    <div class="Interface">
        <div v-title>Voyage Tracking</div>
        <!-- content -->
        <div class="con-Interface">
            <div class="l-c" >
                <ol v-if="datas">
                    <li>
                        <span> Vessel's name :</span>
                        <p>{{datas.vesselName}}</p>
                    </li>
                    <li>
                        <span> Product :</span>
                        <p>{{datas.product}}
                        </p>
                    </li>
                    <li>
                        <span>Quantity :</span>
                        <p>{{datas.quantity}}
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
                      <!-- <li>
                        <span>Order Number :</span>
                        <p>{{datas.orderNumber}}</p>
                    </li> -->
                    <!-- <li>
                        <span>Laycan :</span>
                        <p>{{datas.laycanStrat}} - {{datas.laycanEnd}}</p>
                    </li> -->

                </ol>
            </div>
        </div>
        <!-- tab -->
        <mt-navbar v-model="selected" class="tab-c">
            <mt-tab-item id="1">Voyage Start</mt-tab-item>
            <mt-tab-item id="2">Loading Port</mt-tab-item>
        </mt-navbar>

        <!-- tab-container -->
        <mt-tab-container v-model="selected">
            <mt-tab-container-item id="1"><con1></con1></mt-tab-container-item>
            <mt-tab-container-item id="2" ><con2 :message='datas'></con2></mt-tab-container-item>
        </mt-tab-container>
    </div>
</template>
<script>
import con1 from "./LoadingPortCont/cont1.vue";
import con2 from "./LoadingPortCont/cont2.vue";
export default {
  data() {
    return {
      selected: "1",
      datas:''
    };
  },
  components: {
    con1,
    con2
  },
  watch: {},
  created() {
    this.getData();
  },
  methods: {
    getData:  function() {
       this.$api.get(
        "pages/back/vesselAcceptance/wechat/vesselAcceptanceDetailsApp.json",
        { confirmationSheetUuid: this.$route.query.uuid },
        e => {
          if (e.status == 0) {
            this.datas = e.datas;
            console.log(this.datas)
          }
        }
      );
    }
  }
};
</script>

<style lang="less" scoped>
// table.dataintable tr:nth-child(odd) {
//   background-color:#F5F5F5;
// }
// table.dataintable tr:nth-child(even) {
//  background: #FCFCFC;
// }
</style>
