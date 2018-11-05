<template>
    <div class="footer">
        <!-- 页脚 -->
        <div class="view-page view-current"></div>
        <div class="view-page"></div>
        <div class="view-page"></div>
        <div class="docs-footer">京ICP备11024593号</div>
    </div>
</template>

<script>
export default {
  name: "footer",
  data() {
    return {};
  }
};
</script>

<style lang="less">
@color: #f36523;
.view-page {
  display: none;
  position: absolute;
  width: 100%;
  height: 100%;
  left: 0;
  top: 0;
}
.view-current {
  display: block;
}
</style>


