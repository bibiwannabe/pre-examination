<template>
  <div class="app-layout" style="background-color: white">
    <PaperList></PaperList>
    <app-header></app-header>

  </div>
</template>

<script>
  import AppHeader from './header'
  import PaperList from './paperList'
  export default {
    components: {
      AppHeader,
      PaperList
    }
  }
</script>

