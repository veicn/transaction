<template>
  <div class="mytrack-cont2">
    <!-- content -->

    <div class="l-c">
      <ol>
        <li>
          <span>PORT OF LOADING :</span>
          <p>{{datas.loadPortVO.portOfLoading}}</p>
        </li>
        <li>
          <span>VESSEL NAME :</span>
          <p> {{datas.loadPortVO.vesselName}}</p>
        </li>
        <li>
          <span>IMO :</span>
          <p> {{datas.loadPortVO.imo}}</p>
        </li>
        <li>
          <span>Commodity :</span>
          <p> {{datas.loadPortVO.product}} </p>
        </li>
        <li>
          <span>Quantity（Tons）:</span>
          <p>{{datas.loadPortVO.quantity}} </p>
        </li>
        <li>
          <span>Laycan :</span>
          <p>{{datas.loadPortVO.laycanStart}} -- {{datas.loadPortVO.laycanEnd}} </p>
        </li>
        <li>
          <span>NOR Tendered :</span>
          <p>{{datas.loadPortVO.norTendered}}</p>
        </li>
        <li>
          <span>Anchor aweigh :</span>
          <p>{{datas.loadPortVO.anchorAweigh}} </p>
        </li>
      </ol>
    </div>
    <div class="info-t">POB</div>
    <div class="l-c">
      <ol>
        <li>
          <span> Date&Time :</span>
          <p>{{datas.loadPortVO.pobDatetimeOne}}</p>
        </li>
        <li>
          <span> Berth :</span>
          <p>{{datas.loadPortVO.pobBerth}}</p>
        </li>

      </ol>
    </div>
    <div class="info-t">First Line</div>
    <div class="l-c">
      <ol>
        <li>
          <span> Date&Time :</span>
          <p>{{datas.loadPortVO.firstLineDatetime}}</p>
        </li>
        <li>
          <span> ETB :</span>
          <p>{{datas.loadPortVO.firstLineEtb}}</p>
        </li>
        <li>
          <span>Berth :</span>
          <p>{{datas.loadPortVO.firstLineBerth}}</p>
        </li>

       <li>
         <span>All Fast :</span>
         <p>{{datas.loadPortVO.allFast}}</p>
       </li>
      </ol>
    </div>
    <div class="info-t">Tanks Inspection</div>
    <div class="l-c">
      <ol>
        <li>
          <span> Date&Time :</span>
          <p>{{datas.loadPortVO.tanksInspectionDatetime}}</p>
        </li>
        <li>
          <span> Remarks :</span>
          <p>{{datas.loadPortVO.tanksInspectionRemarks}}</p>
        </li>
      </ol>
    </div>

    <div class="info-t">
      Independent Inspection
    </div>
    <div class="l-c">
      <ol>
        <li>
          <span class="s-hang2"> Independent Inspection :</span>
          <p class="p-hang2">{{datas.loadPortVO.independentInspection}} </p>
        </li>
        <li>
          <span>Date&Time :</span>
          <p> {{datas.loadPortVO.independentInspectionDatetime}}</p>
        </li>
        <li>
          <span>OBQ :</span>
          <p> {{datas.loadPortVO.independentInspectionObq}}</p>
        </li>
        <li>
          <span> Other :</span>
          <p>{{datas.loadPortVO.independentInspectionRemarks}}</p>
        </li>
        <li>
          <span class="s-hang2 p-b2"> Shore arms connecting :</span>
          <p class="p-hang2"> {{datas.loadPortVO.shoreArmsConnecting}}</p>
        </li>

      </ol>
    </div>
    <div class="info-t">
      Commenced loading
    </div>
    <div class="l-c">
      <ol>
        <li>
          <span> Date&Time :</span>
          <p>{{datas.loadPortVO.commencedLoadingDatetime}}</p>
        </li>
        <li>
          <span> ETC :</span>
          <p>{{datas.loadPortVO.etc}} </p>
        </li>
      </ol>
    </div>
    <div class="info-t">
      Loading Progress
    </div>
    <!-- 表格 -->
    <table class="dataintable">
      <tr>
        <th>Date& Time
        </th>
        <th>Cargo loaded
        </th>
        <th>the cargo to be loaded
        </th>
        <th>loading speed
        </th>
        <th>ETC</th>
      </tr>
      <tr v-for="v2 in datas.loadingProgressListVO">
        <td>{{v2.datetime}}
        </td>
        <td>{{v2.cargoLoaded}}</td>
        <td>{{v2.theCargoToBeLoaded}}</td>
        <td>{{v2.loadingSpeed}}</td>
        <td>{{v2.etc}}
        </td>
      </tr>

    </table>

    <div class="l-c">
      <ol>
        <li>
          <span> Completed loading :</span>
          <p>{{datas.loadPortVO.Completed}}</p>
        </li>
        <li>
          <span class="s-hang2 p-b2"> Shore arms disconnecting :</span>
          <p class="p-hang2">{{datas.loadPortVO.shoreArmDisconnecting}} </p>
        </li>
        <li>
          <span> Cargo survey :</span>
          <p> {{datas.loadPortVO.cargoSurvey}}</p>
        </li>
      </ol>
    </div>

    <div class="info-t">
      Cargo doc on board
    </div>
    <div class="l-c">
      <ol>
        <li>
          <span> Metric Tons :</span>
          <p>{{datas.loadPortVO.blMetricTons}}</p>
        </li>
        <li>
          <span> Long Tons :</span>
          <p>{{datas.loadPortVO.blLongTons}}</p>
        </li>
        <li>
          <span>LTR at 15℃ :</span>
          <p>{{datas.loadPortVO.ltr}}</p>
        </li>
        <li>
          <span> Bbls at 60℃ :</span>
          <p>{{datas.loadPortVO.bbls}}</p>
        </li>
      </ol>
    </div>
    <div class="info-t">
      All lines cast off and Sailed
    </div>
    <div class="l-c">
      <ol>
        <li>
          <span>Date&Time :</span>
          <p>{{datas.loadPortVO.leaveDatetime}}</p>
        </li>
        <li>
          <span>next port :</span>
          <p>{{datas.loadPortVO.nextPort}}</p>
        </li>
      </ol>
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
.mytrack-cont2 {
  background: #fff;
}
.l-c {
  margin: 0;
}
.info-t {
  width: 156px;
  color: #666666;
  text-align: right;
}
.l-c ol li p {
  color: #151515;
  overflow: hidden;
  padding: 5px 0 0 10px;
}

// 表格
table.dataintable {
  margin-top: 15px;
  border-collapse: collapse;
  border: 1px solid #ececec;
  width: 100%;
}
table.dataintable th {
  border: 1px solid #aaa;
  font-family: ArialMT;
  font-size: 14px;
  color: #666666;
  text-align: center;
  padding: 4px;
  background-color: rgb(247, 247, 247);
}
table.dataintable td {
  vertical-align: text-top;
  padding: 6px 15px 6px 6px;
  border: 1px solid #aaa;
}
// table.dataintable tr:nth-child(odd) {
//   background-color: #f5f5f5;
// }
// table.dataintable tr:nth-child(even) {
//   background-color: #fff;
// }
</style>
