<template>
  <div v-if = "datas.transportInfoVO" class="item-list">
      <div v-title>Details</div>
      <div class="item-tit">
        <div class="my-title">
          <p><span>Deal No :</span>
            <span>{{shuju.serialNumber}}</span>
          </p>
          <p>{{shuju.contractSheetStatus}}</p>
        </div>
        <div class="img-text">
          <img :src=shuju.productCategoryImg>
          <div class="text">
            <p><span>Category :</span><span>{{shuju.productCategory}}</span></p>
              <p><span>Specification :</span><span>{{shuju.productSpecification}}</span></p>
              <p><span>Incoterms :</span><span>{{shuju.tradeTermCode}}</span></p>
              <p><span>Quantity<em>(</em><em>{{shuju.pricingUnit}}</em><em>) :</em></span><span>{{shuju.quantity}}</span></p>
            <p><span>Tolerance :</span><span>+/-</span><span>{{shuju.tolerance}}</span><span>% at OT</span></p>
          </div>
        </div>
      </div>
      <div class="item-price con">
        <h3>Pricing</h3>
        <p><span>Quantity DTER :</span><span>{{datas.pricingInfoVO.settlementQuantityVO.enName}}</span></p>
        <p><span>Benchmark :</span><span>{{datas.pricingInfoVO.pricingBenchmarkVO.enName}}</span></p>
        <p><span>Source :</span><span>{{datas.pricingInfoVO.priceSourceVO.enName}}</span></p>
        <p><span>Region :</span><span>{{datas.pricingInfoVO.priceRegionVO.enName}}</span></p>
        <p><span>Premium / Discount :</span><span>{{datas.pricingInfoVO.premiumsAndDiscountsAsString}}</span><span>{{datas.pricingInfoVO.currencyVO.enName}}</span><span>/</span><span>{{datas.pricingInfoVO.pricingUnitVO.enName}}</span></p>
        <p><span>Pricing Period :</span><span v-if="datas.pricingInfoVO.pricingPeriodVO">{{datas.pricingInfoVO.pricingPeriodVO.enName}}</span></p>
        <p><span>Payment Terms :</span><span>{{datas.pricingInfoVO.paymentTermVO.enName}}</span></p>
      </div>
      <div class="item-trans con">
        <h3>Transportation</h3>
        <p><span>Transport Mode :</span><span>{{datas.transportInfoVO.transportModeVO.enName}}</span></p>
        <p v-if="this.judge.indexOf('FOB')>-1"><span>Load Port :</span><span>{{datas.transportInfoVO.loadingPortVO.enName}}</span><span>&nbsp</span><span>{{datas.transportInfoVO.loadingPortVO.countryVO.enName}}</span></p>
        <p v-if="this.judge.indexOf('CFR')>-1"><span>Discharge Port :</span><span>{{datas.transportInfoVO.dischargePortVO.enName}}</span></p>
        <p><span>Laytime :</span><span>{{datas.transportInfoVO.laytimeAsString}}</span><span>&nbsp</span><span>H+6</span></p>
        <p><span>Laycan :</span><span>{{datas.transportInfoVO.laycanStartDateAsString}}</span><span>-</span><span>{{datas.transportInfoVO.laycanEndDateAsString}}</span></p>
      </div>
      <div class="item-sell con">
        <h3>Seller</h3>
        <p class="companyName"><span>Company :</span><span>{{datas.salerInfoVO.enterpriseName}}</span></p>
        <p><span>Address :</span><span>{{datas.salerInfoVO.address}}</span></p>
        <p><span>Contact Person :</span><span>{{datas.salerInfoVO.traderName}}</span></p>
        <p><span>E-mail :</span><span>{{datas.salerInfoVO.email}}</span></p>
        <p><span>Fax :</span><span>{{datas.salerInfoVO.fax}}</span></p>
        <p><span>Tel :</span><span>{{datas.salerInfoVO.telephone}}</span></p>
      </div>
      <div class="item-buy con">
        <h3>Buyer</h3>
        <p class="companyName"><span>Company :</span><span>{{datas.buyerInfoVO.enterpriseName}}</span></p>
        <p><span>Address :</span><span>{{datas.buyerInfoVO.address}}</span></p>
        <p><span>Contact Person :</span><span>{{datas.buyerInfoVO.traderName}}</span></p>
        <p><span>E-mail :</span><span>{{datas.buyerInfoVO.email}}</span></p>
        <p><span>Fax :</span><span>{{datas.buyerInfoVO.fax}}</span></p>
        <p><span>Tel :</span><span>{{datas.buyerInfoVO.telephone}}</span></p>
      </div>
      <div class="item-other con">
        <h3>Other Info</h3>
        <p><span>Other Terms :</span><span>{{datas.otherInfoVO.otherTerm}}</span></p>
      </div>
  </div>


</template>

<script>
  export default {
  name: "transDetails",
  props:[],
  data() {
    return {
      datas:'',
      shuju:'',
      uuid:this.$route.query.uuid,
      judge:''
      //newUuid:this.$route.query.uuid,
       }
  },
  watch: {
    search: function(e) {
      this.getData(e);
    }
  },
   created() {
    this.getData();
  },
  methods:{
    getData:function(){
      //console.log(this.uuid)
      // let reqUrl= window.location.href;
      // let arrUrl=reqUrl.split('?');
      // let queryString=arrUrl[1];
      // let arrString=queryString.split("=");
      // let newUuid=arrString[1].toString();
      // alert(newUuid);
      this.$api.getInfo("/pages/back/my_contract_detail/contractSheet.json",
      {uuid:this.uuid},
      e =>{
        if(e.status==0) {
          this.datas=e.datas;
          var  timeStart=this.datas.transportInfoVO.laycanStartDateAsString,
               timeEnd=this.datas.transportInfoVO.laycanEndDateAsString,
               newStart=this.done(timeStart);
               this.judge=this.datas.productInfoVO.tradeTermVO.enName;
        this.datas.transportInfoVO.laycanStartDateAsString=newStart;
        var newEnd=this.done(timeEnd);
        this.datas.transportInfoVO.laycanEndDateAsString=newEnd;
        }
        //console.log(this.datas.contractSheetVO.serialNumber);
      }
      );
       this.$api.getInfo("/pages/back/my_contract_detail/contractSheetSummarily.json",
        {uuid:this.uuid},
      e =>{
        if(e.status==0) {
          this.shuju=e.datas;
        }
        //console.log(this.shuju);
      }
      );

    },
    done: function(date){
         var monthArr = ['Jan','Feb','Mar','Apr','May','June','July','Aug','Sept','Oct','Nov','Dec'];
         var dateArr=date.split(' '),
            dateMonth=dateArr[1],
            dateNum=parseInt(dateMonth),
            mon=monthArr[dateNum-1];
            dateArr.splice(1,1,mon);
            date=dateArr.join(' ');                       //转月份为英文月份简写
            return date;
        }
  },
mounted() {
}
};
</script>
<style lang="less" scoped>
  body{
    width:100%;
    background:#F8F9FB;
    font-family:ArialMT;
    font-size:14px;
    color:#666666;
  }
  *{
    box-sizing:border-box;
  }
  .item-list>div{
    width:100%;
    background:#fff;
    padding-bottom:15px;
    margin-bottom:10px;
    padding-left:16px;
  }
  .item-list>div:first-of-type{
    padding-top:8px;
  }
  .my-title{
    display:flex;
    justify-content:space-between;
    margin-bottom:13px;
  }
  .my-title p:first-of-type span:nth-of-type(2){
    color:#151515;

  }
  .my-title p{
    height:30px;
    line-height:30px;
  }
  .my-title p:nth-of-type(2) {
    width:100px;
    background:#03B675;
    text-align:center;
    color:#fff;
  }
  .img-text{
    display:flex;
  }
  .img-text img{
    width:94px;
    height:125px;
    margin-top:4px;
  }
  .text{
    margin-left:10px;
  }
  .text p{
    height:16px;
    line-height:16px;
    margin-bottom:12px;
  }
  .text p:last-of-type{
    margin-bottom:0;
  }
  .con{
    padding-top:15px;
  }
  .con h3{
    font-size:17px;
    color:#333333;
    height:28px;
    line-height:28px;
    text-align:left;
    margin-bottom:10px;
  }
  .con p{
    //height:28px;
    line-height:28px;
  }
  .con p span:first-of-type{
    margin-right:10px;
    text-align:right;
    width:132px;
  }
  .con p span:nth-of-type(2){
    color:#333333;
  }

  .item-sell .companyName,.item-buy .companyName {
    height:100%;
  }
  .item-sell .companyName span:first-child,.item-buy .companyName span:first-child{
    vertical-align:top;
  }
  .item-sell .companyName span:nth-child(2),.item-buy .companyName span:nth-child(2){
    vertical-align:middle;
    width:183px;
  }
</style>
