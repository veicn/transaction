<template>
    <div class="details">
        <!-- 租船协议详情 -->
        <div v-title>Charter Party Details</div>

        <!-- content -->

        <div class="l-c">
            <ol>
                <li>
                    <span> Charter Party Number :</span>
                    <p>{{datas.charteringAgentNm}}</p>
                </li>
                <li>
                    <span>Charter Party Date :</span>
                    <p> {{datas.charterPartyDate}}
                    </p>
                </li>
                <li>
                    <span>Consignor :</span>
                    <p>{{datas.consignor}}
                    </p>
                </li>
                <li>
                    <span>Disponent Owner :</span>
                    <p> {{datas.charteringAgentNm}}</p>
                </li>
                <li>
                    <span>Vessel’s name :</span>
                    <p>{{datas.vesselName}}</p>
                </li>
                <li>
                    <span>IMO :</span>
                    <p> {{datas.imo}}</p>
                </li>
            </ol>
        </div>
        <div class="l-c">

            <ol>
                <li>
                    <span> Product :</span>
                    <p>{{datas.productNm}}</p>
                </li>
                <li>
                    <span>Quantity(Tons) :</span>
                    <p> {{datas.quantity}} ± {{datas.rangeOfError}} %
                    </p>
                </li>
                <li>
                    <span>Port Of Loading :</span>
                    <p>{{datas.portOfLoading}}
                    </p>
                </li>
                <li>
                    <span>Port Of Discharge :</span>
                    <p> {{datas.portOfDischarge}}</p>
                </li>
            </ol>
        </div>
        <div class="l-c">
            <ol>
                <li>
                    <span> Laycan :</span>
                    <p>
                        {{datas.laycanStrat}}-{{datas.laycanEnd}}
                    </p>
                </li>
                <li>
                    <span>
                        Q88 :
                    </span>
                    <p v-if="datas.uploadQ88">
                        <a :href="datas.uploadQ88"
                           class="mint-button mint-button--default mint-button--normal">preview </a>
                    </p>
                    <p v-else>
                        <!-- <a href="javascript:;"
                           class="mint-button mint-button--default mint-button--normal">preview </a> -->
                    </p>
                </li>
                <!-- <li>
                    <span>CP :</span>
                    <p v-if="datas.uploadCp">
                        <a :href="datas.uploadCp"
                           class="mint-button mint-button--default mint-button--normal">preview </a>
                    </p>
                    <p v-else>
                        <a href="javascript:;"
                           class="mint-button mint-button--default mint-button--normal">preview </a>
                    </p>
                </li> -->

            </ol>
        </div>
    </div>
</template>

<script>
export default {
    data () {
        return {
            datas: undefined
        };
    },
    components: {},
    watch: {},
    created () { },
    methods: {
        con: function () {
            this.$api.get(
                "pages/back/agreement/wechat/getAgreementByUuid.json",
                { uuid: this.$route.query.uuid },
                e => {
                    if (e.status == 0) {
                        this.datas = e.datas;
                    }
                }
            );
        }
    },
    mounted () {

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
input[type='radio'] + label::before {
  content: '\a0';
  /*不换行空格*/
  display: inline-block;
  vertical-align: middle;
  font-size: 10px;
  width: 1em;
  height: 1em;
  margin-right: 1em;
  border-radius: 50%;
  border: 1px solid @color;
  text-indent: 0.15em;
  line-height: 1;
}

input[type='radio']:checked + label::before {
  background-color: @color;
  background-clip: content-box;
  padding: 0.2em;
}

input[type='radio'] {
  position: absolute;
  clip: rect(0, 0, 0, 0);
}
.h30 {
  height: 28px;
}
.mint-button--normal {
  padding-top: 6px;
}
</style>
