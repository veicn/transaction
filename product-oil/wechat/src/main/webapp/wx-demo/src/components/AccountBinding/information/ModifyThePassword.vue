<template>
  <!-- 修改密码 -->
  <div class="ModifyThePassword">
    <mt-field class="xgmm" label="Old password" placeholder="Please enter" type="password" v-model="password1"></mt-field>
    <mt-field class="xgmm" label="New password" placeholder="Please enter" type="password" v-model="password2"></mt-field>
    <div class="pass-Prompt">
      <i>*</i>Allow the importation of 6-20 characters, numbers, letters, and support, _ combination
    </div>
    <div class="mp-bd">
      <span>Re-enter Password</span> <input type="text">
    </div>
    <div class="marb300"></div>
  </div>

  </div>
</template>

<script>
export default {
  data() {
    return {
      password1: "",
      password2: ""
    };
  }
};
</script>

<style lang="less">
@color: #f36523;

.xgmm {
  height: 80px;
  .mint-cell-title {
    width: 150px !important;
  }
}
.pass-Prompt {
  padding: 0 15px;
  background-color: #fff;
  border: none;
  i {
    color: @color;
  }
}
.marb300 {
  margin-bottom: 230px;
}
.mp-bd {
  height: 50px;
  margin-left: 10px;
  background-color: #ffffff;
  border-bottom: 1px solid #ececec;
  position: relative;

  span {
    height: 50px;
    line-height: 50px;
    font-family: ArialMT;
    color: #333333;
    font-family: PingFangSC-Regular;
    font-size: 14px;
  }
  input {
    height: 50px;
    float: right;
    border: 0;
    line-height: 1.6;
    font-size: 15px;
    padding-left: 10px;
    color: #666666;
  }
}
</style>



