<template>
  <div class="app-layout" style="background-color: white">
    <Slider></Slider>
    <app-header></app-header>

  </div>
</template>

<script>
  import AppHeader from './header'
  import Slider from './sliderBar'
  import Subject from './subjectCards'
  export default {
    components: {
      AppHeader,
      Slider,
      Subject
    }
  }
</script>

