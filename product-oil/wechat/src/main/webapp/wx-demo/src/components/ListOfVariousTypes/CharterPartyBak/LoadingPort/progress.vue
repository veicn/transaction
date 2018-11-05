<template>
  <div class="progress">
    <div class="box-progress">
      <div class="c2-tit">Loading Progress</div>
      <div class="bgcf" v-for='(item,index) in processData' :key="index">
        <div class="cont2-bd" @click="openPicker(item,'datetime')">
          <span>Date&Time</span>
          <span class="s-input">{{item.datetime}}</span>
          <!-- <input class="pr43" type="text" v-model="v.datetime"> -->
          <!-- <input class="pr43" type="text" v-model="v.datetime"> -->
          <i class="c2-time"><img src="../../../../assets/img/Page 1@2x.png" alt=""></i>
        </div>
        <div class="cont2-bd"><input type="text" placeholder="Please enter" v-model="item.cargoLoaded"><span>Cargo Loaded</span></div>
        <div class="cont2-bd"><input type="text" placeholder="Please enter" v-model="item.theCargoToBeLoaded"><span>The Cargo To Be Loaded</span></div>
        <div class="cont2-bd"><input type="text" placeholder="Please enter" v-model="item.loadingSpeed"><span>Loading Speed</span></div>
        <div class="cont2-bd" @click="openPicker(item,'etc')">
          <span>Etc </span>
            <span class="s-input">{{item.etc}}</span>
           <!-- <input class="pr43 " type="text" v-model="v.etc"> -->
          <i class="c2-time"><img src="../../../../assets/img/Page 1@2x.png" alt=""></i>
        </div>
        <div class="cont2-bd">
          <span class="an sld" @click="saveProcess(item)">Save</span>
          <span class="an" @click="deleteProcess(item.uuid,index)">Delete</span>
        </div>
      </div>
    </div>

    <!-- 时间选择器 -->
    <mt-datetime-picker ref="picker" year-format="{value} 年" month-format="{value} 月" date-format="{value} 日" cancelText="cancel" confirmText="confirm" type="date" @confirm="handleConfirm()" v-model="pickerValue">
    </mt-datetime-picker>

    <mt-button class="c1btn" @click="addprocess">＋Add</mt-button>
  </div>

</template>

<script>
import { Toast } from 'mint-ui'
export default {
  data() {
    return {
      datas: undefined,
      pickerValue: new Date(), //当前日期,
      datenum: " ",
      newIndex: " ",
      uuid:this.$route.query.uuid,
      //增加框关闭
      addToTd: 0,
      datasnew: {
        // pickerValue: new Date(),
        // datenum: " ",
        cargoLoaded: "",
        confirmUuid: this.$route.query.uuid,
        datetime: "",
        etc: "",
        ext1: null,
        loadingSpeed: "",
        shipConfirmationSheetId: null,
        shipLoadPort: "",
        shipLoadPortId: null,
        theCargoToBeLoaded: null,
      },
      activeInputIndex: null,
      processData:[
        {
          datetime:"",
          cargoLoaded:'',
          theCargoToBeLoaded:'',
          loadingSpeed:'',
          etc:'',
          uuid:'',
        }
      ],
      _pageUuid:this.$route.query.uuid,
      _tempDate:{},
      _tempDateType:'',
      isSaveIng:false
    };
  },
  components: {},
  watch: {},
  created() {
    this.datas
    this.chaxun();

  },
  methods: {
    checkData(item) {
      if(!item.datetime || !item.cargoLoaded ||!item.theCargoToBeLoaded ||!item.loadingSpeed ||!item.etc){
        return false
      }else{
        return true
      }
    },
    addprocess() {
      this.processData.push({
        datetime:"",
        cargoLoaded:'',
        theCargoToBeLoaded:'',
        loadingSpeed:'',
        etc:'',
        uuid:'',
        confirmUuid:this.$route.query.uuid
      })
    },
    deleteProcess(uuid,index) {
      if(this.isSaveIng){
        return
      }
      if(uuid === ''){
        this.processData.splice(index, 1)
        return
      }
      this.$api.get(
        "pages/back/progress/deleteLoadingProgerss.json",
         { progressUuid: uuid },
        // this.datas[index],
        e => {
          if (e.status == 0) {
            Toast({
              message: 'Delete Successfully!',
              position: 'middle',
            });
            this.processData.splice(index, 1)
          }else{
            Toast({
              message: 'Delete faile!',
              position: 'middle',
            });
          }
          this.isSaveIng = false
        }
      );
    },
    saveProcess (item) {
      if(this.isSaveIng){
        return
      }
      this.isSaveIng = true
      if(this.checkData(item)){
        if(item.domain){
          delete item.domain
        }
        item.confirmUuid = this.$route.query.uuid
        this.$api.post(
          "pages/back/progress/saveLoadingProgress.json",
          item,
          e => {
            if (e.status == 1) {
              Toast({
                message: 'Insert Successfully!',
                position: 'middle',
              });
              this.isSaveIng = false
            }else{
              Toast({
                message: 'Insert faile!',
                position: 'middle',
              });
              this.isSaveIng = false
            }
          }
        )
      }else{
        this.$messagebox.alert("", {
            message: "Content cann't be empty",
            title: "Prompt",
            confirmButtonText: "confirm"
        })
        this.isSaveIng = false
      }
    },
    chaxun: function() {
      this.$api.get(
        "pages/back/progress/queryLoadingProgerss.json",
        { confirmUUid: this.$route.query.uuid },
        e => {
          if (e.status == 0) {
            this.processData = e.datas
          }
        }
      )
    },
    // 时间
    openPicker(e,dataType) {
      console.log(e)
      this._tempDate = e
      this._tempDateType = dataType
       //alert('点击')
      // this.datenum = e;
      // this.newIndex=index;
      this.$refs.picker.open();
      // 设置开始;
      //this.activeInputIndex = index;
    },
     formatDate(date) {
      let y = date.getFullYear();
      let m = date.getMonth() + 1;
      m = m < 10 ? "0" + m : m;
      let d = date.getDate();
      d = d < 10 ? "0" + d : d;
      return d + " " + m + " " + y +" 00:00";
    }, // 转换时间 d m y;
    handleConfirm() {
      this._tempDate[this._tempDateType] = this.formatDate(this.$refs.picker.value);
    } // 获取时间

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
  background-color: #ffffff;
  border-bottom: 1px solid #ececec;
  position: relative;

  span {
    line-height:50px;
    font-family: ArialMT;
    font-size: 15px;
    color: #333333;
    letter-spacing: 0;
    padding-left:10px
  }
  input {
    line-height:50px;
    float: right;
    border: 0;
    font-size: 15px;
    padding-right: 10px;
    color: #666666;
    text-align:right;
    width:30%;
  }

  .c2-time {
    position: absolute;
    top: 14px;
    // right: 12px;
    right: 10px;
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
// .progress-bx {
//   width: 100%;
//   height: 40px;
//   line-height: 40px;
//   font-size: 15px;
//   border-top: 1px solid #ececec;
//   text-align: center;
//   position: relative;
//   background: #ffffff;
//   span {
//     height: 100%;
//     width: 49%;
//     float: left;
//     color: @color;
//   }
// }
.an {
  width: 48%;
  height: 100%;
  color: #f36523 !important;
  text-align: center;
}
.sld::after {
  content: "";
  display: block;
  height: 19px;
  position: absolute;
  border-right: 1px solid #ececec;
  top: 16px;
  left: 50%;
}
.w130 {
  width: 130px;
}
.c1btn {
  width: 100%;
  margin-top: 43px;
}
.s-input{
  float:right;
  padding-right:40px;
  padding-left:0
}
.cont2-bd .an{
  padding-left:0
}
</style>
