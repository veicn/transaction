<template>
  <div class="mytrack-cont3">
    <!-- 航次跟踪 -->
    <ul class="hcgz">
       <li class="clearfix" v-if="datas.transitUnLoadingVOList.length==0">
        This page is empty
        </li>
      <li class="clearfix" v-for="v2 in datas.transitUnLoadingVOList">
        <i class="iconfont icon-riqi"></i>
        <div class="time">{{v2.datetime}}</div>
        <div class="f fl">
          <span class="t">ETA :
            <em>{{v2.eta}}</em>
          </span>
        </br>
        </br>
        </br>
          <span>G.AVE :
            <em>{{v2.gAveSpd}}</em>
          </span>
          <span>POSN :
            <em>{{v2.posn}}</em>
          </span>
        </div>
        <div class="f fl">
          <span>SEA :
            <em>{{v2.sea}}</em>
          </span>
        <!-- </br>
        </br> -->
          <span>Ave spd:
            <em>{{v2.aveSpd}}</em>
          </span>
          <span>RPM :
            <em>{{v2.rpm}}</em>
          </span>
        </div>
      </li>
      <!-- <li class="clearfix">
        <i class="iconfont icon-riqi"></i>
        <div class="time">{{}}</div>
        <div class="f fl">
          <span class="t">ETA :
            <em>2018-02-04</em>
          </span>
          <span>SEA :
            <em>NNE./- NE/4</em>
          </span>
          <span>POSN :
            <em>LAT :23 53</em>
          </span>
        </div>
        <div class="f fl">
          <span>ETA :
            <em>2018-02-04</em>
          </span>
          <span>Ave spd(24h):
            <em>12.5</em>
          </span>
          <span>N/LONG :
            <em>119 28 E</em>
          </span>
        </div>
      </li> -->
      <!-- <li class="clearfix">
        <i class="iconfont icon-riqi"></i>
        <div class="time">2018-2-23 12:30</div>
        <div class="f fl">
          <span class="t">ETA :
            <em>2018-02-04</em>
          </span>
          <span>SEA :
            <em>NNE./- NE/4</em>
          </span>
          <span>POSN :
            <em>LAT :23 53</em>
          </span>
        </div>
        <div class="f fl">
          <span>ETA :
            <em>2018-02-04</em>
          </span>
          <span>Ave spd(24h):
            <em>12.5</em>
          </span>
          <span>N/LONG :
            <em>119 28 E</em>
          </span>
        </div>
      </li>
      <li class="clearfix">
        <i class="iconfont icon-riqi"></i>
        <div class="time">2018-2-23 12:30</div>
        <div class="f fl">
          <span class="t">ETA :
            <em>2018-02-04</em>
          </span>
          <span>SEA :
            <em>NNE./- NE/4</em>
          </span>
          <span>POSN :
            <em>LAT :23 53</em>
          </span>
        </div>
        <div class="f fl">
          <span>ETA :
            <em>2018-02-04</em>
          </span>
          <span>Ave spd(24h):
            <em>12.5</em>
          </span>
          <span>N/LONG :
            <em>119 28 E</em>
          </span>
        </div>
      </li> -->
    </ul>
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

<style lang="less">
@color: #f36523;
.mytrack-cont3 {
  background: #ffffff;
  overflow: hidden;
}
</style>
