<template>
  <div class="ltsubmit">
    <!-- con1的保存 -->
    <div class="box-progress">
      <div class="bgcf">
        <div class="cont2-bd" @click="openPicker(1)">
          <span>Date</span>
          <span class="s-input"> {{datas.date}}<img src="../../../../assets/img/Page 1@2x.png" alt=""></span>
        </div>
        <div class="cont2-bd">
          <span>Loading Port</span> <input type="text" placeholder="Please enter" v-model="datas.loadingPort">
        </div>
        <div class="cont2-bd" @click="openPicker(2)">
          <span>Eta</span>
          <span class="s-input"> {{datas.eta}}<img src="../../../../assets/img/Page 1@2x.png" alt=""></span>
        </div>
        <div class="cont2-bd" @click="openPicker(3)">
          <span>Etb</span>
          <span class="s-input"> {{datas.etb}}<img src="../../../../assets/img/Page 1@2x.png" alt=""></span>

        </div>
        <div class="cont2-bd">
          <span>Posn</span> <input type="text" placeholder="Please enter" v-model="datas.posn">
        </div>
        <div class="cont2-bd" @click="showxl=!showxl">
          <span>Sea</span>
          <input disabled type="text" placeholder="Please enter" :value="seaEnName">
          <!-- <span>Sea {{xialatxt[datas.sea].enName}}</span> -->
          <!-- <input disabled type="text" placeholder="Please enter" v-model="datas.sea"> -->
          <!-- <span v-if="xialatxt" class="s-input"> {{xialatxt[datas.sea].enName}}</span> -->
        </div>
        <ul class="sb-xl" v-show="showxl">
          <li :class="datas.seaId== v2.code * 1? 'active':''" v-for=" (v2,index) in xialatxt" :key="index"  @click="xlxz(v2.code,v2.enName)">{{v2.enName}}</li>
        </ul>

        <div class="cont2-bd">
          <span>Ave spd(24)</span>
          <input type="text" placeholder="Please enter" v-model="datas.aveSpd24h">
        </div>

      </div>

    </div>
    <mt-button class="lt-sub" @click="sub()">Submit</mt-button>
    <!-- 时间选择器 -->
    <mt-datetime-picker ref="picker" year-format="{value} 年" month-format="{value} 月" date-format="{value} 日" cancelText="cancel" confirmText="confirm" type="date" @confirm="handleConfirm" v-model="pickerValue">
    </mt-datetime-picker>
  </div>
</template>
<script>
import { Toast } from 'mint-ui'
import myfooter from "../../../common/footer.vue";
export default {
  data() {
    return {
      datas: {
        aliveFlag: "",
        aveSpd24h: "",
        createDate: null,
        createUser: null,
        date: null,
        eta: 1521618977000,
        etb: 1521618981000,
        ext1: "",
        loadingPort: "",
        posn: "",
        sea: "",
        seaId: "",
        shipConfirmationSheetId: 321324,
        transitLoadingId: 1,
        updateDate: null,
        updateUser: null,
        version: "1"
      },
      pickerValue: new Date(), //当前日期
      datenum: null,
      seaEnName:"",
      xialatxt: undefined,
      showxl: false,
      seaId:0
    };
  },
  components: {
    myfooter
  },
  watch: {},
  created() {
    this.con();
    this.xiala();
  },
  methods: {
    checkData() {
      if(!this.datas.posn || !this.datas.loadingPort ||!this.datas.aveSpd24h ){
        return false
      }else{
        return true
      }
    },
    con: function() {
      this.$api.get(
        "pages/back/TransitLoading/getTransitLoading.json",
        { uuid: this.$route.query.uuid },
        e => {
          if (e.status == 0) {
            this.datas = e.datas;
            this.seaEnName = e.datas.sea
          }
          //console.log(e);
        }
      );
    }, //获取
    sub: function() {
      if(this.checkData()){
        delete this.datas.domain;
        this.datas.confirmUUid = this.$route.query.confirmUUid;
        this.$api.post(
        "pages/back/TransitLoading/saveTransitLoading.json",
        // " http://192.168.8.171:8080/pages/back/TransitLoading/saveTransitLoading.json",
        this.datas,
        e => {
          if (e.status == 0) {
            Toast({
              message: 'Insert Successfully!',
              position: 'middle',
            });
            this.$router.go(-1);
          }
        });
      }else{
        this.$messagebox.alert("", {
            message: "Content cann't be empty",
            title: "Prompt",
            confirmButtonText: "confirm"
          })
      }
    }, //提交
    openPicker(e) {
      this.datenum = e;
      this.$refs.picker.open();
    },// 打开时间控件 设置开始;
    formatDate(date) {
      let y = date.getFullYear();
      let m = date.getMonth() + 1;
      m = m < 10 ? "0" + m : m;
      let d = date.getDate();
      d = d < 10 ? "0" + d : d;
      return d + " " + m + " " + y;
    }, // 转换时间 d m y;
    handleConfirm() {
      console.log(this.formatDate(this.$refs.picker.value));
      if (this.datenum == 1) {
        this.datas.date = this.formatDate(this.$refs.picker.value);
      }
      if (this.datenum == 2) {
        this.datas.eta = this.formatDate(this.$refs.picker.value);
      }
      if (this.datenum == 3) {
        this.datas.etb = this.formatDate(this.$refs.picker.value);
      }
    }, // 获取时间
    xiala: function() {
      this.$api.post(
        "pages/back/LoadPort/querySelect.json",
        { uuid: this.$route.query.uuid },
        e => {
          this.xialatxt = e.selectSea;
          //console.log(e.selectSea);
          this.xialatxt.sort(function(a,b){
              return a.code-b.code;
            });
          
        }
      );
    }, //下拉框
    xlxz: function(e,c) {
      this.datas.seaId = e;
      this.datas.sea = c;
      this.showxl = false;
      this.seaEnName = this.xialatxt[e].enName
    }
  },
  mounted() {}
};
</script>

<style lang="less" scoped>
@color: #f36523;
.box-progress {
  background-color: #fff;
  padding: 5px;
}
.cont2-bd {
  height: 50px;
  margin-left: 10px;
  background-color: #ffffff;
  border-bottom: 1px solid #ececec;
  position: relative;

  span {
    height: 50px;
    line-height: 50px;
    font-family: ArialMT;
    font-size: 15px;
    color: #333333;
    letter-spacing: 0;
  }
  input {
    height: 50px;
    float: right;
    border: 0;
    line-height: 1.6;
    font-size: 15px;
    text-align: right;
    padding-right: 10px;
    color: #666666;
    background-color: #ffffff;
    &.pr43 {
      padding-right: 43px;
    }
  }
  .s-input {
    float: right;
    position: relative;
    color: #666666;
    font-size: 15px;
    padding: 0 30px 0 0;
    img {
      width: 18px;
      margin: 1px 0 0 0;
      position: absolute;
      top: 14px;
      right: 4px;
    }
  }

  .c2-time {
    position: absolute;
    top: 14px;
    right: 12px;
    img {
      width: 18px;
    }
  }
  .pot64 {
    top: 64px;
  }
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
.lt-sub {
  width: 80%;
  margin: 30px 10%;
}
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
