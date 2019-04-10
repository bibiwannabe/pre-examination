<template>
  <div class="app-layout" style="background-color: white">
    <PaperInfo></PaperInfo>
    <app-header></app-header>

  </div>
</template>

<script>
  import AppHeader from './header'
  import PaperInfo from './paperInfo'
  export default {
    components: {
      AppHeader,
      PaperInfo
    }
  }
</script>

