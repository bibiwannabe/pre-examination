<template>
  <section class="app-cards" style="height: 450px">
    <div>
      <n3-card style="padding:20px">
      <p>xxxxx</p>
      <p>xxxxx</p>
      <p>xxxxx</p>
    </n3-card> </div>
  </section>
</template>

<script>
  export default {
    name: 'subjectCards',
    data () {
      return {
        speed: 0,
        show: false
      }
    }
  }
</script>

<style lang="less">
  .app-cards {
    background: #fff;
    color: #fff;
    display: flex;
    position: fixed;
    width: 100%;
    margin-left: auto;
    margin-right: auto;
    z-index: 3;
    box-shadow: 0px 0px 5px #f9f9f9;

  }
</style>
