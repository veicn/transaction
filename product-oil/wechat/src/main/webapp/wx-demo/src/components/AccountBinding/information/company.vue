<template>
    <!--  公司信息-->
    <div class="company">
        <!-- content -->
        <ul class="con">
            <li class="clearfix">
                <span> Company Name : </span>
                <p> {{datas.englishName}}
                </p>
            </li>
            <li class="clearfix">
                <span>abbr : </span>
                <p>{{datas.abbEnglishName}}
                </p>
            </li>
            <li class="clearfix" v-show="datas.country">
                <span> Country: </span>
                <p> {{datas.country}}
                </p>
            </li>
            <li class="clearfix">
                <span>Company Profile :</span>
                <p> {{datas.description}}
                </p>
            </li>
        </ul>
    </div>
</template>

<script>
export default {
  name: "company",
  data() {
    return {
      memberId: "",
      datas: ""
    };
  },
  components: {},
  watch: {},
  created() {
    this.memberId =sessionStorage.getItem("epMemberId")
    this.getData();
  },
  methods: {
    getData: function() {
      this.$api.getWx(
        "/enterprise.json",
        { memberId: this.memberId },
        e=> {
          if (e.status == 0) {
            console.log(e)
              this.datas=e.datas
          }
        }
      );
    }
  },
  mounted() {

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
