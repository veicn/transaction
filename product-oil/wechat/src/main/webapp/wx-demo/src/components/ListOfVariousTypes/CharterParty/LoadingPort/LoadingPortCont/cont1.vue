<template>
  <div class="Interface-cont2">
    <!-- content -->
    <!-- 表格 -->
    <table class="dataintable">
      <tr class="h40">
        <th>DATE
        </th>
        <th>STATE
        </th>
        <th>OPERATE
        </th>
      </tr>
      <tr v-if="datas"
          v-for="(v,index) in datas "
          :key='index'
          class="h50">
        <td>{{v.date}}
        </td>
        <td>done</td>
        <td>
          <div style="width:186px">
            <router-link :to="{ path: 'ltsubmit', query: { uuid:v.uuid,confirmUUid:$route.query.uuid  }}">
              <span class="c1-btn">Amend</span>
            </router-link>
            <span class="c1-btn"
                  @click="delet(v.uuid)">Delete</span>
          </div>
        </td>
      </tr>

      <!-- 增加选择框 -->
      <tr class="h50"
          v-for="(item,index) in arr "
          :key='index'>
        <td>
          {{item.DATE}}
        </td>
        <td>null</td>
        <td>
          <div style="width:186px">
            <router-link :to="{ path: 'ltsubmit1', query:{confirmUUid:$route.query.uuid}}">
              <span class="c1-btn">Amend</span>
            </router-link>
            <span class="c1-btn"
                  @click="remove(index)">Delete</span>
          </div>
        </td>
      </tr>
    </table>
    <mt-button class="c1btn"
               @click="addToTd()">＋Add</mt-button>
  </div>
</template>

<script>
import { MessageBox } from "mint-ui";

export default {
  data () {
    return {
      datas: "",
      startDate: new Date(),
      arr: []
    };
  },
  components: {},
  watch: {},
  created () {
    this.con();
  },
  methods: {
    con: function () {
      this.$api.get(
        "pages/back/TransitLoading/queryTransitLoading.json",
        { confUUid: this.$route.query.uuid },
        e => {
          if (e.status == 0) {
            console.log(e);
            this.datas = e.datas;
          }
        }
      );
    }, //列表
    delet: function (id) {
      MessageBox.confirm('', {
        message: 'Are you sure',
        title: 'Notice',
        confirmButtonText: 'confirm',
        cancelButtonText: 'cancel'
      }).then(action => {
        this.$api.post(
          "pages/back/TransitLoading/transitLoadingDelete.json",
          { uuid: id },
          e => {
            if (e.status == 0) {
              this.con();
            }
          }
        );
      });
    },
    remove: function (index) {
      MessageBox.confirm('', {
        message: 'Are you sure',
        title: 'Notice',
        confirmButtonText: 'confirm',
        cancelButtonText: 'cancel'
      }).then(action => {
        this.arr.splice(index, 1);
      });

    },
    addToTd: function () {
      this.arr.push({ DATE: new Date().format(" dd MM yyyy") });
    }
  },
  mounted () { }
};
</script>

<style lang="less" scoped>
@color: #f36523;

// 表格
table.dataintable {
  margin-top: 15px;
  border-collapse: collapse;
  border: 1px solid #ececec;
  width: 100%;
}
table.dataintable th {
  border: 1px solid #dfdfdf;
  font-family: ArialMT;
  font-size: 14px;
  color: #666666;
  text-align: center;
  padding: 4px;
  background-color: rgb(247, 247, 247);
}
table.dataintable td {
  vertical-align: text-top;
  padding: 6px 15px 6px 6px;
  border: 1px solid #dfdfdf;
}
// table.dataintable tr:nth-child(odd) {
//   background-color: #f5f5f5;
// }
// table.dataintable tr:nth-child(even) {
//   background-color: #fff;
// }
.h40 {
  height: 40px;
}
.h50 {
  height: 50px;
}
.c1-btn {
  padding: 5px 20px;
  // margin: 4px 10px 0 0;
  border: 1px solid @color;
  color: @color;
  font-family: ArialMT;
  font-size: 13px;
  letter-spacing: 0;
  text-align: center;
  border-radius: 3px;
  &:active {
    background-color: rgb(148, 149, 150);
  }
}
.c1btn {
  width: 100%;
  margin-top: 20px;
}
.w173 {
  width: 173px;
}
</style>
