<template>
  <div class="Interface-cont2">
    <div class="tp-4">
      <ul class="clearfix">
        <li @click="selected='1'">
          <div :class="selected=='1'||selected=='2'||selected=='3'||selected=='4'?'bg':''">1</div>
          Basic information
        </li>
        <li @click="selected='2'">
          <div :class="selected=='2'||selected=='3'||selected=='4'?'bg':''">2</div>
          Berthing
        </li>
        <li @click="selected='3'">
          <div :class="selected=='3'||selected=='4'?'bg':''">3</div>Loading</li>
        <li @click="selected='4'">
          <div :class="selected=='4'?'bg':''">4</div>Departing</li>
      </ul>
      <p class="jdt">
        <span :style="'width:'+(selected-1)*32+'%'"></span>
      </p>
    </div>
    <!-- tab -->

    <!-- tab-container -->
    <mt-tab-container v-model="selected">
      <mt-tab-container-item id="1">
        <div class="box-c1 bgcf">
          <div class="cont2-bd">
            <span>Port Of Loading</span> <input disabled type="text" v-model="message.portOfLoading">
          </div>
          <div class="cont2-bd">
            <span>Vessel Name</span> <input disabled type="text" placeholder="Please enter" v-model="message.vesselName">
          </div>
          <div class="cont2-bd">
            <span>IMO</span>
            <input disabled type="text" placeholder="Please enter" v-model="message.imo">
          </div>
          <div class="cont2-bd">
            <span>Product :</span>
            <input disabled type="text" placeholder="Please enter" v-model="message.product">
          </div>
          <div class="cont2-bd">
            <span>Quantity（Tons）</span>
            <input disabled type="text" placeholder="Please enter" v-model="message.quantity + ' ± ' + message.rangeOfError + '%'">
          </div>
          <div class="cont2-bd h84 cont2-bd-more">
            <span>Laycan</span>
            <span class="s-input" @click="openPicker(2)">Start: {{datas.laycanStart.substring(0,10)}}<img src="../../../../../assets/img/Page 1@2x.png" alt=""></span>
            <div class="s-end" @click="openPicker(3)">End : {{datas.laycanEnd.substring(0,10)}}<img src="../../../../../assets/img/Page 1@2x.png" alt=""></div>
          </div>

        </div>
      </mt-tab-container-item>
      <mt-tab-container-item id="2">
        <div class="box-c2">
          <div class="bgcf">
            <div class="cont2-bd" @click="openPicker(4)">
              <span>Nor Tendered</span>
              <span class="s-input"> {{datas.norTendered.substring(0,10)}}<img src="../../../../../assets/img/Page 1@2x.png" alt=""></span>
            </div>
            <div class="cont2-bd" @click="openPicker(5)">
              <span>Anchor Aweigh</span>
              <span class="s-input"> {{datas.anchorAweigh.substring(0,10)}}<img src="../../../../../assets/img/Page 1@2x.png" alt=""></span>
            </div>
          </div>

          <div class="c2-tit">Pob</div>
          <div class="bgcf">
            <div class="cont2-bd" @click="openPicker(6)">
              <span>Date&Time</span>
              <span class="s-input"> {{datas.pobDatetimeOne.substring(0,10)}}<img src="../../../../../assets/img/Page 1@2x.png" alt=""></span>
            </div>
            <div class="cont2-bd">
              <span>Berth</span> <input type="text" placeholder="Please enter" v-model="datas.pobBerth">
            </div>
          </div>

          <div class="c2-tit">First Line</div>
          <div class="bgcf">
            <div class="cont2-bd" @click="openPicker(7)">
              <span>Date&Time</span>
              <span class="s-input"> {{datas.firstLineDatetime.substring(0,10)}}<img src="../../../../../assets/img/Page 1@2x.png" alt=""></span>
            </div>
            <!-- <div class="cont2-bd">
              <span>ETB</span> <input type="text" placeholder="Please enter" v-model="datas.firstLineEtb">
            </div> -->
            <div class="cont2-bd" @click="openPicker(19)">
              <span>ETB</span>
              <span class="s-input"> {{datas.firstLineEtb.substring(0,10)}}<img src="../../../../../assets/img/Page 1@2x.png" alt=""></span>
            </div>
            <div class="cont2-bd">
              <span>Berth</span> <input type="text" placeholder="Please enter" v-model="datas.firstLineBerth">
            </div>
             <div class="cont2-bd" @click="openPicker(16)">
              <span> All Fast</span>
              <span class="s-input"> {{datas.allFast.substring(0,10)}}<img src="../../../../../assets/img/Page 1@2x.png" alt=""></span>
            </div>
          </div>

        </div>
      </mt-tab-container-item>
      <mt-tab-container-item id="3">
        <div class="box-c3">
          <div class="c2-tit">Tanks Inspection</div>
          <div class="bgcf">
            <div class="cont2-bd" @click="openPicker(8)">
              <span>Date&Time</span>
              <span class="s-input"> {{datas.tanksInspectionDatetime.substring(0,10)}}<img src="../../../../../assets/img/Page 1@2x.png" alt=""></span>
            </div>
            <div class="cont2-bd">
              <span>Remarks</span> <input type="text" placeholder="Please enter" v-model="datas.tanksInspectionRemarks">
            </div>

          </div>
          <div class="c2-tit">Independent Inspection</div>
          <div class="bgcf">
            <!-- <div class="cont2-bd">
              <span>Independent Inspection</span>
              <input style="width:130px" type="text" placeholder="Please enter" v-model="datas.independentInspection">
            </div> -->
            <!-- 新增下拉框 -->

            <div class="cont2-bd" @click="show=!show">
              <span>Independent Inspection</span>
              <input disabled type="text" placeholder="Please Select"
        :value="newDatas">
            </div>
            <ul class="sb-xl" v-show="show">
              <li v-for="(item,index) in inspectionXiaLaText" :key="index"
              @click="xlxz1(item.code,item.enName)">{{ item.enName }}</li>
            </ul>
            <!-- 下拉框结束 -->
            <div class="cont2-bd">
              <span>Previous Product</span> <input type="text" placeholder="Please enter" v-model="datas.independentInspectionRemarks">
            </div>
            <div class="cont2-bd">
              <span>OBQ</span> <input type="text" placeholder="Please enter" v-model="datas.independentInspectionObq">
            </div>
            <div class="cont2-bd" @click="openPicker(9)">
              <span>Date&Time</span>
              <span class="s-input"> {{datas.independentInspectionDatetime.substring(0,10)}}<img src="../../../../../assets/img/Page 1@2x.png" alt=""></span>
            </div>
            <!-- <div class="cont2-bd">
              <span>Other</span> <input type="text" placeholder="Please enter" v-model="datas.Other">
            </div> -->
            <div class="cont2-bd" @click="openPicker(10)">
              <span>Shore Arms Connecting</span>
              <span class="s-input"> {{datas.shoreArmsConnecting.substring(0,10)}}<img src="../../../../../assets/img/Page 1@2x.png" alt=""></span>
            </div>

          </div>

          <div class="c2-tit">Commenced loading</div>
          <div class="bgcf">
            <div class="cont2-bd" @click="openPicker(11)">
              <span>Date&Time</span>
              <span class="s-input"> {{datas.commencedLoadingDatetime.substring(0,10)}}<img src="../../../../../assets/img/Page 1@2x.png" alt=""></span>
            </div>
            <div class="cont2-bd" @click="openPicker(12)">
              <span>ETC</span>
              <span class="s-input"> {{datas.etc.substring(0,10)}}<img src="../../../../../assets/img/Page 1@2x.png" alt=""></span>
            </div>
            <router-link :to="{ path: 'progress', query: { uuid:$route.query.uuid }}">
              <div class="cont2-bd">
                <span>Loading Progress</span>
                <i class="c2-time">＞</i>
              </div>
            </router-link>
            <div class="cont2-bd" @click="openPicker(13)">
              <span>Completed Loading</span>
              <span class="s-input"> {{datas.completedLoadingDatetime.substring(0,10)}}<img src="../../../../../assets/img/Page 1@2x.png" alt=""></span>
            </div>

          </div>

        </div>
      </mt-tab-container-item>
      <mt-tab-container-item id="4">
        <div class="box-c4">
          <div class="bgcf mart20">
            <div class="cont2-bd" @click="openPicker(14)">
              <span>Shore Arm Disconnecting</span>
              <span class="s-input"> {{datas.shoreArmDisconnecting.substring(0,10)}}<img src="../../../../../assets/img/Page 1@2x.png" alt=""></span>
            </div>
            <!-- <div class="cont2-bd" @click="openPicker(15)">
              <span>Cargo Survey</span>
              <span class="s-input"> {{datas.cargoSurvey}}<img src="../../../../../assets/img/Page 1@2x.png" alt=""></span>
            </div> -->
            <div class="cont2-bd" @click="openPicker(17)">
              <span>Cargo Survey</span>
              <span class="s-input"> {{datas.cargoSurvey.substring(0,10)}}<img src="../../../../../assets/img/Page 1@2x.png" alt=""></span>
            </div>
          </div>

          <div class="c2-tit">Cargo documents on board</div>
          <div class="bgcf">
            <div class="cont2-bd">
              <span>Metric Tons</span> <input type="text" placeholder="Please enter" v-model="datas.blMetricTons">
            </div>
            <div class="cont2-bd">
              <span>BBLs</span> <input type="text" placeholder="Please enter" v-model="datas.bbls">
            </div>
          </div>

          <div class="c2-tit"> All lines cast off and vessel sailed</div>
          <div class="bgcf">
            <div class="cont2-bd" @click="openPicker(15)">
              <span>Date&Time</span>
              <span class="s-input"> {{datas.leaveDatetime.substring(0,10)}}<img src="../../../../../assets/img/Page 1@2x.png" alt=""></span>
            </div>
            <!-- <div class="cont2-bd">
              <span>Next Port</span>
              <input type="text" placeholder="Please enter" v-model="datas.nextPort" >
            </div> -->
            <!-- 新增下拉框 -->
            <!-- <div class="cont2-bd" @click="showxl=!showxl">
              <span>Next Port</span>
              <input disabled type="text" placeholder="Please Select" v-model="xialatextOne">
            </div> -->
            <div class="cont2-bd" @click="showxl=!showxl">
              <span>Next Port</span>
              <input disabled type="text" placeholder="Please Select"
              v-model="datas.nextPort">
            </div>
            <ul class="sb-xl" v-show="showxl">
              <li v-for="(item,index) in xialatext" :key="index" @click="xlxz(item.code,item.zhName)">{{item.zhName}} </li>
            </ul>
          </div>

        </div>
      </mt-tab-container-item>
    </mt-tab-container>

    <!-- 时间选择器 -->
    <mt-datetime-picker ref="picker" year-format="{value} 年" month-format="{value} 月" date-format="{value} 日" cancelText="cancel" confirmText="confirm" type="date" @confirm="handleConfirm" v-model="pickerValue">
    </mt-datetime-picker>
    <!-- 切换步骤 -->
    <div class="buzou">
      <span v-if="selected!='1'" class="fl" @click="selected=='4'?selected='3':selected=='3'?selected='2':selected=='2'?selected='1':selected='4'">＜ last step</span>
      <mt-button class="c2-save" @click="baocun9(selected*1)">Save</mt-button>
      <span v-if="selected!='4'" @click="next1(selected)" class="fr"> Next step＞</span>
    </div>
  </div>
</template>

<script>
import { Toast } from 'mint-ui'
export default {
  data() {
    return {
      type:this.$route.query.type,
      selected: "1",
      datas: undefined,
      pickerValue: new Date(), //当前日期
      datenum: null,
      portOfLoading: undefined,
      message:'',
      // 第三页的下拉开始
      inspectionXiaLaText:"",
      // 下拉开始
      xialatext:[],
      // xialatextOne:"",
      showxl:false,
      show:false,
      newDatas:'',
    };
  },
  props:['message'],
  components: {},
  watch: {},
  created() {
     if(this.type == 'save'){
        this.selected = '3'
    }
    this.con();
    this.xiala();
    this.inspectionXiaLa();
  },
  methods: {
    // Inspection下拉开始
    inspectionXiaLa:function(){

      this.$api.post("pages/back/LoadPort/querySelectIndependent.json",{},(e)=>{
        this.inspectionXiaLaText = e.selectIndependent;
        //console.log(11);
        //console.log(this.inspectionXiaLaText);
        this.inspectionXiaLaText.sort(function(a,b){
            return a.code-b.code;
          });
      })
    },

    xlxz1: function(code,name){
    this.datas.independentInspection=code;
      this.newDatas=name;
      //console.log(code);
      //console.log(this.showx1);
      this.show=false;
     //console.log(this.showx1);
    },
    // Inspection下拉结束

    // nextport下拉开始
    xiala:function(){
      //console.log(259)
      this.$api.post("pages/back/LoadPort/querySelectPort.json",{}, (e) =>{
      //console.log(261)
      //alert(e.selectSeaPort);
      this.xialatext = e.selectSeaPort;
      //console.log(e);
      //console.log(e.selectSeaPort);
      this.xialatext.sort(function(a,b){
          return a.code-b.code;
        });
      })
    },
    xlxz: function(e,obj) {
      //console.log(e-1)
      //console.log(this.xialatext)
      //console.log(this.xialatext[e])
      this.datas.nextPortId = e;
      this.datas.nextPort=obj;
      console.log(e);
      this.showxl = false;
      //this.xialatextOne = this.xialatext[e-1].enName;
      //console.log(this.nextPortId)
    },
    // 下拉结束

    con: function() {
      this.$api.get(
        "pages/back/LoadPort/queryLoadPort.json",
        { comUuid: this.$route.query.uuid },
        e => {
          if (e.status == 0) {
            this.portOfLoading = e.datas.portOfLoading;
            this.datas = e.datas;
             console.log(1)
            console.log(this.datas)
            console.log(2)
            //alert(e.datas.independentInspection);
            //inspection下拉数据遍历
            for (var i = 0; i <this.inspectionXiaLaText.length; i++) {
              //alert(this.inspectionXiaLaText[i].code);
              if(this.inspectionXiaLaText[i].code==e.datas.independentInspection)
              {
                this.newDatas=this.inspectionXiaLaText[i].enName;
                break;
              }
            }
          }

        }
      );
    },
     getData: function() {
       this.$api.get(
        "pages/back/vesselAcceptance/wechat/vesselAcceptanceDetailsApp.json",
        { confirmationSheetUuid: this.$route.query.uuid },
        e => {
          if (e.status == 0) {
            this.message = e.datas;
          }
        }
      );
    },
    baocun9: function(e) {
      this.datas.step = e;
      delete this.datas.domain;
      delete this.datas.Other;
      this.datas.comUuid = this.$route.query.uuid;
      this.$api.post(
        "pages/back/LoadPort/loadingPortAdd.json",
        this.datas,
        e => {
          if (e.status == 0) {
            this.datas.uuid=e.uuid;
            Toast({
              message: 'Insert Successfully!',
              position: 'middle',
            });
          }else{
            Toast({
              message: 'Insert fail!',
              position: 'middle',
            });
          }
        }
      );
    }, //保存
    next1: function(e) {
      this.baocun9(e * 1);
      if (e == "1") {
        this.selected = "2";
      } else if (e == "2") {
        this.selected = "3";
      } else if (e == "3") {
        this.selected = "4";
      } else {
        this.selected = "1";
      }
    }, //下一步
    openPicker(e) {
      this.datenum = e;
      this.$refs.picker.open();
      // 设置开始;
    },
    formatDate(date) {
      let y = date.getFullYear();
      let m = date.getMonth() + 1;
      m = m < 10 ? "0" + m : m;
      let d = date.getDate();
      d = d < 10 ? "0" + d : d;
     return d + " " + m + " " + y + " 00:00";
    }, // 转换时间 d m y;
    handleConfirm() {
      let tm = this.formatDate(this.$refs.picker.value);
      if (this.datenum == 1) {
        this.datas.imo = tm;
      }
      if (this.datenum == 2) {
        this.datas.laycanStart = tm;
      }
      if (this.datenum == 3) {
        this.datas.laycanEnd = tm;
      }
      if (this.datenum == 4) {
        this.datas.norTendered = tm;
      }
      if (this.datenum == 5) {
        this.datas.anchorAweigh = tm;
      }
      if (this.datenum == 6) {
        this.datas.pobDatetimeOne = tm;
      }
      if (this.datenum == 7) {
        this.datas.firstLineDatetime = tm;
      }
      if (this.datenum == 8) {
        this.datas.tanksInspectionDatetime = tm;
      }
      if (this.datenum == 9) {
        this.datas.independentInspectionDatetime = tm;
      }
      if (this.datenum == 10) {
        this.datas.shoreArmsConnecting = tm;
      }
      if (this.datenum == 11) {
        this.datas.commencedLoadingDatetime = tm;
      }
      if (this.datenum == 11) {
        this.datas.commencedLoadingDatetime = tm;
      }
      if (this.datenum == 12) {
        this.datas.etc = tm;
      }
      if (this.datenum == 13) {
        this.datas.completedLoadingDatetime = tm;
      }
      if (this.datenum == 14) {
        this.datas.shoreArmDisconnecting = tm;
      }
      if (this.datenum == 15) {
        this.datas.leaveDatetime = tm;
      }
      if (this.datenum == 16) {
        this.datas.allFast = tm;
      }
      if (this.datenum == 17) {
        this.datas.cargoSurvey = tm;
      }
      if (this.datenum == 18) {
        this.datas.independentInspection = tm;
      }
      if (this.datenum == 19) {
        this.datas.firstLineEtb = tm;
      }

    } // 获取时间
  },
  mounted() {
   this.getData()
  }
};
</script>

<style lang="less" scoped>
@color: #f36523;
.tp-4 {
  width: 100%;
  height: 108px;
  position: relative;
  ul {
    width: 100%;
    position: absolute;
    z-index: 10;
    li {
      width: 25%;
      height: 108px;
      text-align: center;
      float: left;
      font-family: ArialMT;
      font-size: 14px;
      color: #999999;
      div {
        width: 28px;
        height: 28px;
        line-height: 28px;
        text-align: center;
        font-size: 14px;
        color: #ffffff;
        border-radius: 50%;
        background-color: #cccccc;
        margin: 20px auto 5px;
      }
      .bg {
        background-color: @color;
      }
    }
  }
  .jdt {
    width: 77%;
    height: 3px;
    background-color: #cccccc;
    position: absolute;
    top: 32px;
    left: 11%;
    span {
      height: 3px;
      background-color: @color;
      position: absolute;
      transition: all 0.5s;
    }
  }
}
.cont2-bd {
  height: 50px;
  margin-left: 10px;
  background-color: #ffffff;
  border-bottom: 1px solid #ececec;
  position: relative;
  display:flex;
  flex-direction: row;
  flex-wrap: nowrap;
  justify-content: space-between;
  &.cont2-bd-more{
    display:block;
  }
  span {
    height: 50px;
    line-height: 50px;
    font-family: ArialMT;
    font-size: 15px;
    color: #333333;
    letter-spacing: 0;
    display:inline-block;
  }
  input {
    height: 50px;
    border: 0;
    line-height: 1.6;
    font-size: 15px;
    text-align: right;
    padding-right: 10px;
    color: #666666;
    background-color: #ffffff;
    width:50%;
    flex:1;
  }
  .s-input {
    float: right;
    position: relative;
    color: #666666;

    padding: 0 30px 0 0;
    img {
      width: 18px;
      margin: 1px 0 0 0;
      position: absolute;
      top: 14px;
      right: 4px;
    }
  }

  .pot64 {
    top: 64px;
  }
}
.h84 {
  height: 84px;
  .s-end {
    position: relative;
    color: #666666;
    font-size: 15px;
    text-align: right;
    padding: 0 30px 0 0;
    img {
      width: 18px;
      margin: 1px 0 0 0;
      position: absolute;
      top: -2px;
      right: 4px;
    }
  }
}

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
.bgcf {
  background-color: #fff;
}
.c2-tit {
  font-family: Arial-BoldMT;
  font-size: 17px;
  color: #333333;
  height: 40px;
  padding-left: 10px;
  margin-top: 10px;
  line-height: 40px;
  background-color: #fff;
}
.mart20 {
  margin-top: 10px;
}

//切换步骤
.buzou {
  height: 80px;
  padding: 40px 10px;
  text-align: center;
  position: relative;
  span {
    font-family: ArialMT;
    font-size: 14px;
    color: #f36523;
    margin-top: 10px;
  }
  .c2-save {
    width: 155px;
    height: 44px;
    border-radius: 4px;
    position: absolute;
    left: 50%;
    margin-left: -80px;
  }
}
// 新增下拉框
.sb-xl {
  transition: all 0.5;
  li {
    width: 100%;
    height: 40px;
    line-height: 40px;
    text-align: center;
    border-bottom: 1px solid #333;
    transition: all 0.2;

    &:hover {
      background-color: @color;
    }
  }
  .active {
    background-color: @color;
  }
}
</style>
