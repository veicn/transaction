<template>
  <div class="mytrack-cont4">
    <div class="cont4-box">
      <!-- content -->
      <div class="l-c">
        <ol>
          <li>
            <span> DISCHARGING PORT :</span>
            <p> {{datas.unloadPortVo.dischargingPort}} </p>
          </li>
          <li>
            <span>Anchor aweigh :</span>
            <p>{{datas.unloadPortVo.anchorAweigh}} </p>
          </li>
          <li>
            <span>POB :</span>
            <p>{{datas.unloadPortVo.pob}} </p>
          </li>
          <li>
            <span>All Fast :</span>
            <p>{{datas.unloadPortVo.allFast}}</p>
          </li>
          <li>
            <span class="s-hang2 p-b2"> Shore arm disconnecting :
            </span>
            <p class="p-hang2"> {{datas.unloadPortVo.shoreArmDisconnecting}}</p>
          </li>

          <li>
            <span class="s-hang2 p-b2">Completed DISCHARGED :
            </span>
            <p class="p-hang2">{{datas.unloadPortVo.completedDischarged}} </p>
          </li>
          <li>
            <span class="s-hang2 p-b2"> All lines cast off and vessel sailed :
            </span>
            <p class="p-hang2">{{datas.unloadPortVo.allLinesCastOffAndVesselSailed}} </p>
          </li>
          <li>
            <span>Metric Tons :</span>
            <p>{{datas.unloadPortVo.metricTons}}</p>
          </li>

        </ol>
      </div>

    </div>

  </div>
</template>

<script>
export default {
  data() {
    return {
         datas:this.title,
    };
  },
  components: {},
  watch: {},
  props:["title"],
  created() {
  },
  methods: {
  },
  mounted() {}
};
</script>

<style lang="less" scoped>
@color: #f36523;
.cont4-box {
  background: #ffffff;
}
.l-c {
  margin: 0;
}
.s-hang2 {
  height: 56px !important;
  line-height: 24px !important;
}
.p-hang2 {
  padding: 27px 0 0 10px !important;
}
.p-b2 {
  padding-left: 2px !important;
  box-sizing: border-box;
}
</style>
