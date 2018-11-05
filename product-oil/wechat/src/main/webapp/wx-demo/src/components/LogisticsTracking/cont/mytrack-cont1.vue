<template>
    <div class="mytrack-cont1">
        <div class="cont1-box">
            <div class="tit">STOWAGE PLAN</div>
            <div class="l-c">
                <ol>
                    <li>
                        <span>Stowage Plan Product :</span>
                        <p>{{datas.confirmationSheetVO.product}}
                        </p>
                    </li>
                    <li>
                        <span> Metric Tons :</span>
                        <p> {{datas.voyageStartVO.metricTons}} ± {{datas.voyageStartVO.rangeOfError}} %
                        </p>
                    </li>
                    <li>
                        <span>BBLS :</span>
                        <p> {{datas.voyageStartVO.bbls}}
                        </p>
                    </li>
                    <li>
                        <span>LOADING TEMP :</span>
                        <p> {{datas.voyageStartVO.loadingTemp}}</p>
                    </li>
                    <li>
                        <span id="cont-h">DRAFT restriction OF LOADING PORT</span>
                        <p> {{datas.voyageStartVO.draftRestrictionOfLoadingPort}}</p>
                    </li>
                    <li>
                        <span id="cont-h">DRAFT restriction OF DISCHARGING PORT</span>
                        <p> {{datas.voyageStartVO.draftRestrictionOfDischargingPort}}</p>
                    </li>
                    <li>
                        <span class="pt6">Accessory :</span>
                        <p>
                            <!-- <mt-button class="h30" size="small">{{datas.voyageStartVO.accessory}}</mt-button>
                             -->
                             <a :href="datas.voyageStartVO.accessory" class="mint-button mint-button--default mint-button--normal mint-button-preview" >preview </a>

                        </p>
                    </li>

                </ol>
            </div>
        </div>
        <!-- 航次跟踪 -->
        <div class="cont1-box mt10">
            <div class="tit">
                VOYAGE TRACKING
            </div>
            <ul class="hcgz">
                <li class="clearfix" v-for="v2 in datas.transitLoadingVOList">
                    <i class="iconfont icon-riqi"></i>
                    <div class="time">{{v2.date}}</div>
                    <div class="f fl">
                        <span class="t">ETA :
                            <em>{{v2.eta}}</em>
                        </span>
                        <span>SEA :
                            <em>{{v2.sea}}</em>
                        </span>
                      </br>
                      </br>
                        <span>POSN :
                            <em>{{v2.posn}}</em>
                        </span>
                    </div>
                    <div class="f fl textRight">
                        <span>ETB :
                            <em>{{v2.etb}}</em>
                        </span>
                        <span>Ave spd(24h):
                            <em>{{v2.aveSpd24h}}</em>
                        </span>
                    </div>
                </li>             
            </ul>

        </div>
        <!-- 地图 -->
        <div class="dtbox">
            <areaMap :imo='imo'></areaMap>
        </div>
    </div>
</template>

<script>
import map from "./map.vue";
export default {
  data() {
    return {
        datas:this.title
    };
  },
  components: {
    'areaMap': map
  },
  watch: {},
  props:["title",'imo'],
  created() {
  },
  methods: {
  },
  mounted() {}
};
</script>

<style lang="less">
@color: #f36523;
.cont1-box {
  background: #ffffff;
  .tit {
    font-family: Arial-BoldMT;
    font-size: 16px;
    color: #333333;
    letter-spacing: 0.07px;
    padding: 15px 0 0 15px;
  }
}
.hcgz {
  li {
    border-left: 2px dashed #ececec;
    margin: 14px 20px 0;
    padding: 0 26px;
    position: relative;
    .time {
      height: 24px;
      font-family: ArialMT;
      font-size: 15px;
      color: #333333;
      letter-spacing: 0;
      line-height: 16px;
    }
    i {
      position: absolute;
      top: 0px;
      left: -10px;
    }
    .f {
      width: 50%;
      font-family: Arial-BoldMT;
      font-size: 13px;
      color: #999999;
      padding: 5px 0;
      line-height: 13px;
      &.textRight{
        text-align: right;
      }
      span {
        height: 30px;
        min-width: 50%;
        &.t {
          color: #f36523;
          font-weight: 600;
          em {
            color: #f36523;
            font-weight: 600;
          }
        }
      }
    }
  }
}
#cont-h {
  height: 48px;
  position: relative;
  line-height: 20px;
  padding-right: 10px;
  box-sizing: border-box;
  &::after {
    content: ":";
    display: block;
    position: absolute;
    right: 0px;
    top: 10px;
  }
}
.pt6 {
  padding: 6px 0 0 0;
}
.h30 {
  height: 30px;
}
.mt10 {
  margin-top: 10px;
}
.dtbox {
  width: 100%;
  height: 250px;
  margin: 10px auto;
  img {
    width: 100%;
    height: 100%;
  }
}

.mint-button--normal.mint-button-preview{
  padding-top:6px;
}
</style>
