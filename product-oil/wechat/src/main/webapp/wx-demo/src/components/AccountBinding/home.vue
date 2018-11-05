<template>
  <div class="login">
    <!-- title 的设置 -->
    <div v-title>Account binding</div>

    <div class="page-logo">
      <img src="../../assets/img/logo.jpg" alt="">
    </div>
    <div class="page-user">
      <div class="t">
        <img :src="datas.headimgurl? datas.headimgurl:'../../../static/img/1.png'" alt="">
      </div>

      <div v-show="binding!=0" class="n">{{datas.nickname ? datas.nickname:"null"}}
        <img @click="jiebang()" class="jiebang" src="../../assets/img/1.png" alt="">
      </div>
    </div>

    <!-- 已绑定 -->
    <div v-show="binding==1" class="p-c-s">
      <div class="home-c">
        <router-link to="/home/company">
          <div class="info">
            <img src="../../assets/img/Group 9@2x.png" alt="">
            <div class="t">
              Company Info
            </div>
          </div>
        </router-link>
        <router-link to="/home/personal">
          <div class="info">
            <img src="../../assets/img/Group 8@2x.png" alt="">
            <div class="t">
              Contact Info
            </div>
          </div>
        </router-link>
      </div>
      <!-- 权限 -->
      <div class="home-d">
          <!-- 交易 -->
        <router-link v-if="info9" :to="{ path: 'transInfo'}">
          <div class="power">
            Deals
            <i><img src="../../assets/img/Group 6@2x.png" alt=""></i>
          </div>
        </router-link>
        <router-link v-if="info1" :to="{ path: 'demand', query: { type:1 }}">
          <div class="power">
            Chartering Req(Seller)
            <i><img src="../../assets/img/Page 1@2x.png" alt=""></i>
          </div>
        </router-link>
        <router-link v-if="info2" :to="{ path: 'demand', query: { type:5 }}">
          <div class="power">
            Chartering Req(Disponent Owner)
            <i><img src="../../assets/img/Page 1@2x.png" alt=""></i>
          </div>
        </router-link>

        <router-link v-if="info3" to="CAlist">
          <div class="power">
            Charter Party(Seller)

            <i><img src="../../assets/img/Group 5@2x.png" alt=""></i>
          </div>
        </router-link>
        <router-link v-if="info4" to="CAlist1">
          <div class="power">
            Charter Party(Disponent Owner)
            <i><img src="../../assets/img/Group 5@2x.png" alt=""></i>
          </div>
        </router-link>

        <router-link v-if="info5" :to="{ path: 'list', query: { type:1 }}">
          <div class="power">
            Vessel Acceptance(Seller)
            <i><img src="../../assets/img/Group 6@2x.png" alt=""></i>
          </div>
        </router-link>
        <router-link v-if="info6" :to="{ path: 'list', query: { type:4 }}">
          <div class="power">
            Vessel Acceptance(Refinery)
            <i><img src="../../assets/img/Group 6@2x.png" alt=""></i>
          </div>
        </router-link>
        <router-link v-if="info7" :to="{ path: 'listSA', query: { type:3 }}">
          <div class="power">
            Vessel Acceptance(Shipping Agent)
            <i><img src="../../assets/img/Group 6@2x.png" alt=""></i>
          </div>
        </router-link>
        <router-link v-if="info8" :to="{ path: 'list', query: { type:2 }}">
          <div class="power">
            Vessel Acceptance(Buyer)
            <i><img src="../../assets/img/Group 6@2x.png" alt=""></i>
          </div>
        </router-link>
      </div>
    </div>

    <!-- 未绑定 -->
    <div v-show="binding==0" class="p-c">
      <div class="page-content">
        Please link to oilbank account with your WeChat ID for further service.
      </div>
      <!-- 绑定 -->
      <div class="page-log">
        <div class="l1">
          <i class="iconfont icon-riqi"></i>
          <input class="" v-model="account" type="text" placeholder="Account">
        </div>
        <div class="l1">
          <i class="iconfont icon-riqi"></i>
          <input type="password" v-model="password" placeholder="Password">
        </div>
        <mt-button class="btn" :disabled="isLoading" @click="sbm(account,password)">Binding</mt-button>
      </div>

    </div>

  </div>
</template>


<script>
import { Indicator } from 'mint-ui';
import { Toast } from 'mint-ui'
export default {
  name: "home",
  data() {
    return {
      binding: 2,
      //账号密码
      account: "",
      password: "",
      data: sessionStorage.getItem("data")
        ? sessionStorage.getItem("data")
        : "",
      datas: {
        liveFlag: null,
        createDate: null,
        createUser: null,
        epMemberId: null,
        etx1: null,
        headimgurl: "",
        langVer: null,
        memberId: null,
        nickname: "",
        openid: "123",
        password: null,
        role: "",
        updateDate: null,
        updateUser: null,
        userName: null,
        uuid: null,
        version: null,
        wechatUserId: null
      },
      isLoading:false,
      info1: null,
      info2: null,
      info3: null,
      info4: null,
      info5: null,
      info6: null,
      info7: null,
      info8: null,
      info9: null,
      info10: null,
    };
  },
  components: {},
  watch: {},
  created() {

    let userData = sessionStorage.getItem("data");
    let userId = sessionStorage.getItem("epMemberId");
    if (userData && userId) {
      this.binding = 1
    }else{
      this.binding = 0
    }
  },
  methods: {
    aaa: function() {
      if (this.data == "") {
        this.$api.getWx(
          "/callback.json",
          {
            code: this.getQueryVariable("code")
          },
          e => {
            if (e.status == 0) {
              sessionStorage.setItem("data", JSON.stringify(e.datas));
              sessionStorage.setItem("epMemberId", e.datas.epMemberId);
              sessionStorage.setItem("openId", e.datas.openid);
              this.datas = e.datas;
              if (e.datas.memberId) {
                //已绑定
                this.binding = 1;
              } else {
                this.binding = 0;
              }
              this.powerlode(this.datas.role);
            }
          }
        );
      }
    }, //  登陆(获取头像信息)
    sbm: function(u, p) {
       if (!u || !p) {
        this.$messagebox.alert("", {
          message: "Loading fail",
          title: "Prompt",
          confirmButtonText: "confirm"
        });
      } else {
        Indicator.open('loading...');
        this.$api.postWx(
          "/login.json",
          {
            userName:u,
            password: p,
            openid: this.datas.openid
           //openid: sessionStorage.getItem("openId")

          },
          e => {
             Indicator.close();
            if (e.status == 0) {
              this.$messagebox.alert("", {
                message: "Binding successed",
                title: "Prompt",
                confirmButtonText: "confirm"
              }).then(action => {
                this.info1 = null;
                this.info2 = null;
                this.info3 = null;
                this.info4 = null;
                this.info5 = null;
                this.info6 = null;
                this.info7 = null;
                this.info8 = null;
                this.info9 = null;
                this.info10 = null;
                let nname = this.datas.nickname;
                let nurl = this.datas.headimgurl;
                this.datas = e.datas;
                this.datas.nickname = nname;
                this.datas.headimgurl = nurl;
                sessionStorage.setItem("epMemberId", e.datas.epMemberId);
                sessionStorage.setItem("data", JSON.stringify(e.datas));
                this.powerlode(this.datas.role);
                this.binding = 1;
              });
            }else{
               Toast({
                message: 'Loading fail!',
                position: 'middle',
              });
            }
          },e => {
             Indicator.close();
             Toast({
                message: 'Loading fail!',
                position: 'middle',
              });
          }
        );
      }
    }, //登陆(获取权限)
    jiebang: function() {
       //Indicator.open('loading...');
       this.$messagebox
        .confirm("", {
          message: "remove binding",
          title: "Prompt",
          confirmButtonText: "confirm",
          cancelButtonText: "cancel"
        }).then(action => {
        this.$api.postWx(
          "/deleteRelation.json",
          {
            openid: this.datas.openid
          },
          e => {
            if (e.status == 0) {
              // Indicator.close();
              // sessionStorage.clear()
              this.binding = 0;
              this.password = "";
               this.$messagebox.alert("", {
                  message: "unbind success",
                  title: "Prompt",
                  confirmButtonText: "confirm"
                });
            }
          }
        );
      }).catch(action=>{
        //  Indicator.close();
      });
    }, //解绑
    getQueryVariable: function(name) {
      var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
      var r = window.location.search.substr(1).match(reg);
      if (r != null) return unescape(r[2]);
      alert(unescape(r[2]))
      return null;
    }, //获取url
    powerlode: function(e) {
      if (e) {
        var power = e.split(",");
        // "enter_master,buy_trader,enterprise,enter_admin,sales_trader,trade_executor"
        power.forEach(v => {
          if (v == "sales_trader" || v == "trade_executor") {
            this.info1 = 1;
            this.info3 = 3;
            this.info5 = 5; 
            this.info9 = 9;

          }
          if (v == "buy_trader") {
            this.info8 = 8;
            this.info9 = 9;
          }
          if (v == "depa_port" || v == "arriv_port") {
            this.info7 = 7;
            // this.info9 = 9;
          }
          if (v == "ship_trader" || v == "ship_executor") {
            this.info2 = 2;
            this.info4 = 4;
          }
          if (v == "sales_trader") {
            this.info10 = 10;
            this.info9 = 9;
          }
        });
       
      }
    } //权限的判断
  },
  mounted() {
    this.aaa();
    if (this.data !== "") {
      this.datas = JSON.parse(this.data);
      this.powerlode(this.datas.role);
    }
  }
};
</script>

<style lang="less" scoped>
.page-logo {
  font-family: ArialMT;
  font-size: 28px;
  color: #333333;
  padding: 16px;
}
.page-user {
  width: 100%;

  text-align: center;

  .t {
    width: 60px;
    height: 60px;
    margin: 0 auto;
    img {
      width: 100%;
      height: 100%;
      display: block;
      border-radius: 50%;
    }
  }
  .n {
    font-family: PingFangSC-Regular;
    font-size: 18px;
    color: #000000;
    letter-spacing: 0;
    margin: 10px;
    i {
      margin: 0 0 0 10px;
    }
  }
}
.info {

  margin: 50px 0 0 0;
  display: inline-block;
  box-sizing: border-box;
  padding: 0 10px;
  text-align: center;
  font-family: PingFangSC-Regular;
  font-size: 14px;
  color: #333333;
  img {
    width: 50px;
    height: 50px;
    border-radius: 4px;
  }
}
.home-c{
  display: flex;
  flex-direction:row;
  a{
    flex:1;
    text-align: center;
  }
}
.home-d {
  margin: 10px 0 100px;
  width: 100%;
  overflow: hidden;
  a {
    .power {
      width: 100%;
      height: 50px;
      line-height: 50px;
      opacity: 0.8;
      font-family: PingFangTC-Regular;
      font-size: 15px;
      color: #000000;
      letter-spacing: 0;
      background: #ffffff;
      border-bottom: 1px solid #ececec;
      box-sizing: border-box;
      padding: 0 47px;
      margin: 2px 0;
      position: relative;
      &::after {
        content: "";
        display: block;
        width: 10px;
        height: 10px;
        border-bottom: 1px solid #000;
        border-left: 1px solid #000;
        position: absolute;
        top: 16px;
        transform: rotate(-135deg);
        left: 90%;
      }
      i {
        position: absolute;
        left: 16px;
        top: 3px;
        img {
          width: 19px;
        }
      }
    }
  }
}
.p-c {
  width: 300px;
  margin: 0 auto;
  .page-content {
    font-family: PingFangSC-Regular;
    font-size: 15px;
    color: #333333;
    line-height: 25px;
    text-align: center;
    margin: 15px 0;
  }
  .page-log {
    .l1 {
      width: 100%;
      height: 70px;
      border-bottom: 1px solid #919297;
      position: relative;
      i {
        position: absolute;
        top: 38%;
        left: 10px;
        color: #f36523;
      }
      input {
        width: 80%;
        height: 100%;
        opacity: 0.3;
        font-family: PingFangSC-Regular;
        font-size: 15px;
        color: #000000;
        padding-left: 15%;
        overflow: hidden;
        outline: none;
        border: 0px;
        -webkit-transition: all 0.1s;
        transition: all 0.1s;
      }
    }
  }
  .btn {
    width: 100%;
    height: 44px;
    margin: 100px 0 0 0;
  }
}

.jiebang {
  width: 15px;
  height: 15px;
  padding: 0 0 0 7px;
}
</style>
