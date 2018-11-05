<template>
    <!-- 租船需求详情查看 -->
    <div class="d-details">
        <div v-title>Chartering Enquiry Details</div>

        <!-- content -->
        <div class="l-c">
            <ol>
                <li>
                    <span> Enquiry Number :</span>
                    <p>{{datas.demandsCd}}</p>
                </li>
                <li>
                    <span>Order Number :</span>
                    <p> {{datas.orderNumber}}
                    </p>
                </li>
                <li>
                    <span>Trade Terms :</span>
                    <p>{{datas.tradeTerms}}
                    </p>
                </li>
                <li>
                    <span>Product :</span>
                    <p>{{datas.prodoctNm}}</p>
                </li>
                <li>
                    <span>Quantity(Tons) :</span>
                    <p>{{datas.quantity}} ± {{datas.rangeOfError}}%</p>
                </li>
                <li>
                    <span> Port of Loading :</span>
                    <p>{{datas.portOfLoading}}</p>
                </li>
                <li>
                    <span> Port of Discharge :</span>
                    <p> {{datas.portOfDischarge}}</p>
                </li>
                <li>
                    <span>Laycan :</span>
                    <p>{{datas.laycanStart}} -- {{datas.laycanEnd}}</p>
                </li>
            </ol>
        </div>
        <div class="l-c">
            <ol>
                <li>
                    <span>Date Built :</span>
                    <p> {{datas.builtDate}}
                    </p>
                </li>
                <li>
                    <span>Other :</span>
                    <p>{{datas.other}}</p>
                </li>
                <li>
                    <span>Disponent Owner :</span>
                    <p>{{datas.charteringAgentNm}}</p>
                </li>
            </ol>
        </div>
        <div class="l-c">
            <ol>
                <li>
                    <span> Corporation :</span>
                    <p>
                        {{datas.corporationNm}}
                    </p>
                </li>
                <li>
                    <span>Linkman :</span>
                    <p> {{datas.linkNm}}
                    </p>
                </li>
                <li>
                    <span>E-mail :</span>
                    <p>{{datas.eMial}}
                    </p>
                </li>
                <li>
                    <span>Phone Number :</span>
                    <p>{{datas.phoneNum}}</p>
                </li>

            </ol>
        </div>
    </div>
</template>
<script>
export default {
  data() {
    return {
      datas: undefined
    };
  },
  components: {},
  watch: {},
  created() {},
  methods: {
    con: function() {
      this.$api.get(
        "pages/back/demands/demandsDetail.json",
        { uuid: this.$route.query.uuid },
        e => {
          if (e.status == 0) {
            this.datas = e.datas;
          }
          console.log(e);
        }
      );
    }
  },
  mounted() {
    this.con();
  }
};
</script>

<style lang="less" scoped>
@color: #f36523;

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
        height: 28px;
        line-height: 28px;
        color: #666666;
        text-align: right;
        float: left;
      }
      p {
        color: #151515;
        overflow: hidden;
        // padding: 7px 0 0 10px;
        label {
          margin: 0 10px;
        }
      }
    }
  }
}
.xz {
  width: 200px;
}
</style>
