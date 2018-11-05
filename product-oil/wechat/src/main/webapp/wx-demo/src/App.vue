<template>
  <div class="box">
    <!-- <h3  v-show="!isHome" class="goBack" @click="goBack()"></h3> -->
    <div class="content">
      <router-view transition="expand"></router-view>

    </div>
    <!-- 页脚 -->
    <div class="docs-footer">京ICP备11024593号</div>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  created(){
    console.log(this.$route)
  },
  computed:{
    isHome:function(){
      return this.$route.path=='/home'?true:false;
    }
  },
  methods:{
    goBack:function(){
      this.$router.back(-1)
    }
  }

};
</script>

<style lang="less">
.goBack{
  height:40px;
}
.goBack::after {
    content: "";
    display: block;
    width: 10px;
    height: 10px;
    border-bottom: 1px solid #000;
    border-left: 1px solid #000;
    position: absolute;
    top: 16px;
    -webkit-transform: rotate(45deg);
    transform: rotate(45deg);
    left:4%;
}
@color: #f36523;
//转场动画
.expand-transition {
  transition: all 0.3s ease;
  overflow: hidden;
}

.expand-enter,
.expand-leave {
  height: 0;
  padding: 0px 10px;
  opacity: 0;
}

i.iconfont {
  color: @color;
}
.box {
  background: #f8f9fb;
}

/* 页脚 */
.docs-footer {
  // position: fixed;
  text-align: center;
  bottom: 0px;
  width: 100%;
  height: 60px;
  line-height: 60px;
  font-family: PingFangSC-Regular;
  font-size: 13px;
  color: #cacaca;
  letter-spacing: 0;
}

.mint-searchbar {
  background-color: #f8f9fb !important;

  .mint-searchbar-inner {
    border: 1px solid #666666;
    input {
      padding: 0 0 0 15px;
      opacity: 0.7;

    }
    .mintui-search {
      font-size: 18px;
    }
  }
}
.mint-button--default {
  color: #fff !important;
  background: @color !important;
  box-shadow: 0 0 1px #b8bbbf !important;
}
.mint-button::after {
  background-color: #ccc;
  content: " ";
  opacity: 0;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  position: absolute;
}
//小列表
.l-c {
  margin: 10px 0 0 0;
  background: #ffffff;
  ol {
    padding: 10px 0;
    li {
      font-family: ArialMT;
      font-size: 14px;
      letter-spacing: 0;
      list-style: none;
      padding: 0 10px;
      overflow: hidden;
      span {
        width: 156px;
        //height: 28px;
        line-height: 28px;
        color: #666666;
        text-align: right;
        float: left;
      }
      p {
        color: #151515;
        overflow: hidden;
        padding: 5px 0 0 10px;
        label {
          margin: 0 10px;
        }
      }
    }
  }
}
.tab-c {
  margin: 10px 0 3px 0;
  a {
    font-family: ArialMT;
    font-size: 16px;
    color: #666666;
    letter-spacing: 0;
  }
  .is-selected {
    border-bottom: 3px solid @color !important;
    color: @color !important;
  }
}
.mint-tab-item-label {
  color: inherit;
  font-size: 15px;
  line-height: 1.2;
  text-align: center;
  box-sizing: border-box;
  padding: 0 0 0 10px;
}

// 列表
.l-con {
  width: 100%;
  margin: 10px 0 0 0;
  background: #ffffff;
  padding: 10px 0 0 0;
  .tit {
    height: 30px;
    line-height: 30px;
    span {
      ont-family: Arial-BoldMT;
      font-size: 17px;
      color: #333333;
      letter-spacing: 0.08px;
      margin: 0 0 0 15px;
    }
    .b {
      height: 30px;
      font-family: ArialMT;
      font-size: 14px;
      color: #ffffff;
      letter-spacing: 0.06px;
      margin: 0 5px 0 0;
    }
    .tbs {
      background: #03b675;
    }
    .end {
      width: 100px;
      opacity: 0.6;
      background: #1e1e1e;
    }
  }
  .l-c {
    ol {
      li {
        font-family: ArialMT;
        font-size: 14px;
        letter-spacing: 0;
        list-style: none;
        padding: 0 10px;
        overflow: hidden;
        span {
          // width: 116px;
          width: 133px;
          float: left;
          line-height: 28px;
          color: #666666;
          text-align: right;
        }
        p {
          color: #151515;
          overflow: hidden;
          font-size: 13px;
          padding: 3px 0 0 10px;
        }
      }
    }
  }
  .d {
    // width: 100%;
    height: 40px;
    line-height: 40px;
    font-size: 15px;
    border-top: 1px solid #ececec;
    text-align: center;
    position: relative;
    padding: 0 0 0 0.2rem;
    a {
      color: @color;
      .d-btn {
        width: 49%;
        display: inline-block;
        //float: left;
      }
      // .d-btn3 {
      //   width: 32%;
      //   display: inline-block;
      //   float: left;
      // }
      .sld::after {
        content: "";
        display: block;
        height: 19px;
        position: absolute;
        border-right: 1px solid #ececec;
        top: 10px;
        left: 52%;
      }
      .sld3::after {
        content: "";
        display: block;
        height: 19px;
        position: absolute;
        border-right: 1px solid #ececec;
        top: 8px;
        left: 39%;
      }
      // .sld4::after {
      //   content: "";
      //   display: block;
      //   height: 19px;
      //   position: absolute;
      //   border-right: 1px solid #ececec;
      //   top: 8px;
      //   left: 67%;
      // }
    }
  }
  //权限按钮
  .list-3l {
    height: 40px;
    line-height: 40px;
    font-size: 15px;
    border-top: 1px solid #ececec;
    text-align: center;
    a {
      color: @color;
      padding: 0 0.1rem;
      position: relative;
      &.sld::after {
        content: "";
        display: block;
        height: 19px;
        position: absolute;
        border-right: 1px solid #ececec;
        top: 1px;
        left: 100%;
      }
    }
  }
}
.s-hang2 {
  //height: 56px !important;
  line-height: 24px !important;
}
.p-hang2 {
  //padding: 27px 0 0 10px !important;
}
.p-b2 {
  padding-left: 2px;
  box-sizing: border-box;
}
</style>
