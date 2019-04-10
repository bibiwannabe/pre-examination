<template>
  <div class="app-layout" style="background-color: white">
    <SubjectList></SubjectList>
    <app-header></app-header>

  </div>
</template>

<script>
  import AppHeader from './header'
  import Slider from './sliderBar'
  import Subject from './subjectCards'
  import SubjectList from './subjectList'
  export default {
    components: {
      AppHeader,
      Slider,
      SubjectList
    }
  }
</script>

