<template>
  <!-- 未绑定 -->
    <div v-show="binding==0" >
      <div class="page-logo"> <img src="../../assets/img/logo.jpg" alt=""></div>
    <div class="page-user">
      <div class="t">
        <img :src="datas.headimgurl? datas.headimgurl:'./static/img/1.png'" alt="">
      </div>

      <div class="n">{{datas.nickname ? datas.nickname:"匿名用户"}}
      </div>
    </div>
      <div class="p-c">
      <div class="page-content">
        Your WeChat account has not yet bound the oil net account, please Bindings look for more information.
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
        <mt-button class="btn" @click="sbm(account,password)">Binding</mt-button>
      </div>
</div>
    </div>
</template>


<script>
export default {
  name: "login",
  data() {
    return {
      binding:0,
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
        openid: "",
        password: null,
        role: "",
        updateDate: null,
        updateUser: null,
        userName: null,
        uuid: null,
        version: null,
        wechatUserId: null
      }
    };
  },
  components: {},
  watch: {},
  created() {
     this.init();
  },
  methods: {
    init: function() {
        this.$api.getWx(
          "/callback.json",
          {
            code: this.getQueryVariable("code")
          },
          function(e) {
            if (e.status == 0) {
              sessionStorage.setItem("data", JSON.stringify(e.datas));
              sessionStorage.setItem("epMemberId", e.datas.epMemberId);
              this.datas = e.datas;
              this.powerlode(this.datas.role);
              localStorage.role=this.datas.role;
            }
          }
        );


      //   登陆()
    }, //   登陆(获取头像信息)
    sbm: function(u, p) {
      if(!u||!p){
         this.$messagebox.alert("",{
                  message:'Landing fail',
                  title:'Prompt',
                  confirmButtonText: 'confirm'
              })
      }else{
      this.$api.postWx(
        "/login.json",
        {
          userName: u,
          password: p,
          openid: this.datas.openid||123
        },
        e => {
          console.log(e);
          if (e.status == 0) {
            this.$messagebox.alert("",{
              message:'Binding succeeded',
              title:'Prompt',
              confirmButtonText: 'confirm'
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
              let nname = this.datas.nickname;
              let nurl = this.datas.headimgurl;
              this.datas = e.datas;
              this.datas.nickname = nname;
              this.datas.headimgurl = nurl;
              sessionStorage.setItem("epMemberId", e.datas.epMemberId);
              sessionStorage.setItem("data", JSON.stringify(e.datas));
              this.powerlode(this.datas.role);
              this.binding = 1;
            })
          }else{
             this.$messagebox.alert("",{
                  message:'Landing fail',
                  title:'Prompt',
                  confirmButtonText: 'confirm'
              })
          }
        }
      );
      }
    }, //登陆(获取权限)
    jiebang: function() {
      this.$messagebox.confirm("",{
        message:'remove binding',
        title:'Prompt',
        confirmButtonText: 'confirm',
        cancelButtonText: 'cancel'

      }).then(action => {
        this.$api.postWx(
          "/deleteRelation.json",
          {
            openid: this.datas.openid
          },
          e => {
            console.log(e);
            if (e.status == 0) {
              this.binding = 0;
              this.password = "";
              this.$messagebox.alert("",{
                message:'unbind success',
                title:'Prompt',
                confirmButtonText: 'confirm'
              })

            }
          }
        );
      });
    }, //解绑
    getQueryVariable: function(name) {
      var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
      var r = window.location.search.substr(1).match(reg);
      if (r != null) return unescape(r[2]);
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
          }
          if (v == "buy_trader") {
            this.info8 = 8;
          }
          if (v == "depa_port" || v == "arriv_port") {
            this.info7 = 7;
          }
          if (v == "ship_trader" || v == "ship_executor") {
            this.info2 = 2;
            this.info4 = 4;
          }
        });
        this.info9 = 9;
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
  margin: 16px;
}
.page-user {
  width: 100%;
  height: 100px;
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
    margin:100px 0 0 0 ;
  }

}
</style>
