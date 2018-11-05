<template>
  <div id="map"></div>

</template>

<script>
export default {
  data () {
    return {
      imo: "imo",
      openId:''
    };
  },
  props: ['imo'],
  components: {},
  watch: {},
  created () {
    this.openId = sessionStorage.getItem("openId")
    console.log(this.openId)
  },
  methods: {},
  mounted () {

    var flightPlanCoordinates, center, len;

    //初始化
    function initMap () {
      var map = new google.maps.Map(document.getElementById("map"), {
        zoom: 6,
        zoomControl: true,
        center: center,
        mapTypeId: "terrain",
        disableDefaultUI: true
      });
      //标记起点和终点
      var marker = new google.maps.Marker({
        position: flightPlanCoordinates[0],
        map: map,
        icon: "../../../../static/img/start.png"
      });
      var marker1 = new google.maps.Marker({
        position: flightPlanCoordinates[len - 1],
        map: map,
        icon: "../../../../static/img/end.png"
      });


      // 箭头svg
      var symbolArrows = {
        path: google.maps.SymbolPath.FORWARD_OPEN_ARROW,
        strokeWeight: 4,
        strokeColor: "#F00",
        fillColor: "#eaedec",
        fillOpacity: 1,
        scale: 1
      };
      // 轨迹 
      new google.maps.Polyline({
        map: map,
        strokeColor: "#FF0000",
        strokeWeight: 2,
        icons: [
          {
            icon: "",
            offset: "0%"
          },
          {
            icon: symbolArrows,
            offset: "0px",
            repeat: "800px"
          },
          {
            icon: '',
            offset: "100%"
          }
        ],
        path: flightPlanCoordinates
      });
    }

    this.$api.post(
      // "//192.168.1.184/api/geojson/imowaypoint.json",
      "pages/back/LogisticsTracking/wechat/imowaypoint.json?openId="+this.openId,
      // s"pages/back/LogisticsTracking/wechat/imowaypoint.json?openId=oNfOis59Y48rNK1RXZKRWl-vxs2g",
      {
        imo: this.imo
      },
      e => {
        console.log(e)
         len = e.datas.length;
        center = e.datas[parseInt(len / 2)];
        flightPlanCoordinates = e.datas;
        initMap();
      }
    );

  }
};
</script>

<style>
#map {
  width: 100%;
  height: 600px;
}
</style>
