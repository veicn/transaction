<template>
    <!-- 个人信息 -->
    <div class="personal">
        <!-- content -->
        <ul class="con">
            <li class="clearfix">
                <span> Username : </span>
                <p> {{datas.username}}
                </p>
            </li>
            <li class="clearfix">
                <span> Name : </span>
                <p>{{datas.contactName}}</p>
            </li>
            <li class="clearfix">
                <span> Phone No. : </span>
                <p>{{datas.contactPhone}}</p>
            </li>
            <li class="clearfix">
                <span>Email : </span>
                <p>{{datas.contactMail}}</p>
            </li>
        </ul>
    </div>

</template>

<script>
export default {
  name: "personal",
  data() {
    return {
       memberId: "",
       openId:'',
       datas: ""
    };
  },
  created() {
    this.memberId =sessionStorage.getItem("epMemberId");
    this.openId =sessionStorage.getItem("openId");
    this.getData();
  },
  methods: {
    getData: function() {
      this.$api.getWx(
        "/enterprise.json",
        { memberId: this.memberId ,openId:this.openId},
        e => {
          if (e.status == 0) {
            this.datas = e.datas;
          }
        }
      );
    }
  }
};
</script>

<style lang="less" scoped>
ul.con {
  width: 100%;
  background-color: #ffffff;
  li {
    font-family: ArialMT;
    font-size: 15px;
    letter-spacing: 0;
    padding: 0 15px;
    border-bottom: 1px solid #ececec;
    span {
      color: #333333;
      height: 50px;
      line-height: 50px;
      float: left;
    }
    p {
      overflow: hidden;
      color: #666666;
      text-align: right;
      padding-top: 16px;
    }
  }
}
</style>
