<template>
<div class="tran-detail">
  <div v-title>Deal List</div>
  <mt-search class="h100" v-model="search" cancel-text="cancel" placeholder="Please enter the Category">
  </mt-search>
  <!-- <mt-loadmore :top-method="loadTop" ref="loadmore"> -->
  <div class="det-list" v-for="item in datas">
    <router-link :to=" {path: 'transInfo/transDetails', query: { uuid:(item.uuid).toString()}} ">
      <div class="list-con">
        <div class="tit">
          <p><span>Deal No :</span><span>{{item.serialNumber}}</span></p>
          <p>{{item.contractSheetStatus}}</p>
        </div>
        <div class="fig-txt">
          <img :src="item.productCategoryImg">
          <div class="my-con">
            <p><span>Category :</span><span>{{item.productCategory}}</span></p>
            <p><span>Specification :</span><span>{{item.productSpecification}}</span></p>
            <p><span>Quantity<em>(</em><em>{{item.pricingUnit}}</em><em>) :</em></span><span>{{item.quantity}}</span></p>
            <p><span>Premium / Discount :</span><span>{{item.premiumsAndDiscounts}}</span></p>
            <p class="lay-time"><span>Laycan :</span><span class="time-start" ref="timeStart">{{item.laycanStartDateAsString}}</span><span>-</span><span class="time-end" ref="timeEnd">{{item.laycanEndDateAsString}}</span></p>
          </div>
        </div>
      </div>
    </router-link>
  </div>
  <!-- </mt-loadmore> -->
</div>
</template>
<style lang="less" scoped>
body {
  width: 100%;
  background: #f8f9fb;
}
* {
  box-sizing: border-box;
}
.h100 {
  height: 100% !important;
}
.list-con {
  width: 100%;
  height: 194px;
  background: #fff;
  padding-left: 16px;
  padding-top: 8px;
  margin-top: 10px;
}
.tit {
  width: 100%;
  font-family: ArialMT;
  font-size: 14px;
  margin-bottom: 13px;
  display: flex;
  justify-content: space-between;
}
.tit p:first-of-type {
  height: 30px;
  line-height: 30px;
  color: #151515;
}
.tit p:first-of-type span:first-of-type {
  color: #666666;
}
.tit p:nth-of-type(2) {
  height: 30px;
  line-height: 30px;
  background: #03b675;
  color: #ffffff;
  width: 100px;
  text-align: center;
}
.fig-txt {
  display: flex;
}
.fig-txt img {
  display: block;
  margin-top: 4px;
  width: 94px;
  height: 125px;
}
.fig-txt .my-con {
  margin-left: 10px;
}
.my-con p {
  font-size: 14px;
  height: 16px;
  line-height: 16px;
  margin-bottom: 12px;
  font-family: ArialMT;
  font-size: 14px;
  color: #666666;
}
.my-con p:last-of-type {
  margin-bottom: 0;
}
.my-con p span:nth-of-type(2) {
  margin-left: 4px;
  color: #151515;
}
.lay-time span:not(:first-of-type) {
  color: #151515;
}
</style>

<script>
export default {
  name: "transList",
  data() {
    return {
      datas: "",
      search: "",
      timer: ""
    };
  },
  watch: {
    search: function(e) {
      let self=this;
      clearTimeout(this.timer)
      this.timer = setTimeout(function() {
        self.getData(e);
      }, 2000);
    }
  },
  created() {
    this.getData();
  },
  methods: {
    getData: function(keywords) {
      this.$api.getInfo(
        "/pages/back/my_contract_list/contractSheetList.json",
        {
          buyerId: sessionStorage.getItem("epMemberId"),
          keywords: this.search
        },
        e => {
          if (e.status == 0) {
            this.datas = e.datas;
            // alert(this.datas);
            for (var i = 0; i < this.datas.length; i++) {
              var timeStart = this.datas[i].laycanStartDateAsString,
                timeEnd = this.datas[i].laycanEndDateAsString,
                newStart = this.done(timeStart);
              this.datas[i].laycanStartDateAsString = newStart;
              var newEnd = this.done(timeEnd);
              this.datas[i].laycanEndDateAsString = newEnd;
            }
          }
        }
      );
    },
    done: function(date) {
      var monthArr = [
        "Jan",
        "Feb",
        "Mar",
        "Apr",
        "May",
        "June",
        "July",
        "Aug",
        "Sept",
        "Oct",
        "Nov",
        "Dec"
      ];
      var dateArr = date.split(" "),
        dateMonth = dateArr[1],
        dateNum = parseInt(dateMonth),
        mon = monthArr[dateNum - 1];
      dateArr.splice(1, 1, mon);
      date = dateArr.join(" "); //转月份为英文月份简写
      return date;
    },

    loadTop() {
      // 加载更多数据
      this.$refs.loadmore.onTopLoaded();
    }
  },
  mounted() {
    // 转换日期月份
  }
};
</script>
