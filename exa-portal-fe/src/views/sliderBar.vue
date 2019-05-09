<template>
  <section class="app-slider" style="background-color: white; height: auto">
    <n3-carousel :interval="speed" style="height: 450px">
      <n3-slide @click.native="showPaper(recommendHotList[0].id)">
        <p style="position: absolute;font-size: 60px; width: 100%;text-align:center; height: 320px; line-height:320px;text-shadow: -1px -1px white, 1px 1px #999">
          热门试卷第一</p>
        <p style="position: absolute;font-size: 40px; width: 100%;text-align:center; height: 550px; line-height:550px;text-shadow: -1px -1px white, 1px 1px #999">
          {{recommendHotList[0].name}}</p>
        <img class="slide-img" src="../assets/6.jpg">
      </n3-slide>
      <n3-slide @click.native="showPaper(recommendHotList[1].id)">
        <p style="position: absolute;font-size: 60px; width: 100%;text-align:center; height: 320px; line-height:320px;text-shadow: -1px -1px white, 1px 1px #999">
          热门试卷第二</p>
        <p style="position: absolute;font-size: 40px; width: 100%;text-align:center; height: 550px; line-height:550px;text-shadow: -1px -1px white, 1px 1px #999">
          {{recommendHotList[1].name}}</p>
        <img class="slide-img" src="../assets/10.jpg">
      </n3-slide>
      <n3-slide @click.native="showPaper(recommendHotList[2].id)">
        <p style="position: absolute;font-size: 60px; width: 100%;text-align:center; height: 320px; line-height:320px;text-shadow: -1px -1px white, 1px 1px #999">
          热门试卷第三</p>
        <p style="position: absolute;font-size: 40px; width: 100%;text-align:center; height: 550px; line-height:550px;text-shadow: -1px -1px white, 1px 1px #999">
          {{recommendHotList[2].name}}</p>
        <img class="slide-img" src="../assets/11.jpg">
      </n3-slide>
    </n3-carousel>
    <div>
      <div style="height: 60px">
      <p style="color: #5e5e5e; text-align:center;line-height: 60px; font-size: 16px; margin-top: 15px">——  课程科目 ——</p></div>
      <div v-model="subjectList">
      <n3-button v-for="item in subjectList" style="width: 240px; margin-left: 50px; height: 150px; margin-bottom: 30px; color:#2d3035;float: left; padding: 0px; border: white;
      box-shadow:0px 0 10px #ddd" @click.native="goPaperList(item.id)" class="back">
        <n3-icon size="30px" type="book" style="float: left; margin-left: 40px; line-height: 150px"></n3-icon>
      <h2 style="text-align:center; line-height: 150px;float: left; margin-left: 20px">{{item.subjectName}}</h2>
        <p class="front-subject">点击查看该科目下试卷</p>
      </n3-button>
      </div>
      <n3-button
        style="width: 150px; margin-left: 50px; height: 150px; margin-bottom: 30px; color: #2d3035; padding: 0px; border: white; box-shadow:0px 0 10px #ddd "
      @click.native="showAllSubject">
        <h1 style="text-align:center; line-height: 150px">更多></h1>
      </n3-button>
    </div>
    <div style="width: auto; height: auto">
      <div style="height: 60px">
        <p style="color: #5e5e5e; text-align:center;line-height: 60px; font-size: 16px; float: none">——  推荐试题 ——</p></div>
      <div v-if="allSubject!=[]" v-model="recommendPaperList">
        <n3-button v-for="item in recommendPaperList" style="width: 350px; margin-left: 96px; height: 150px; margin-bottom: 30px; color: #2d3035;float: left; padding: 0px; border: white;
      box-shadow:0px 0 10px #ddd" @click.native="showPaper(item.id)" class="back" >
          <p class="front" >点击进入答题</p>
          <h2 style="text-align:center; line-height: 75px">{{item.name}}</h2>
          <p style="margin-bottom: 20px">科目：{{getSubjectName(item.subjectId)}}</p>
          <span style="float: left;margin-left: 80px">难度：</span>
          <n3-rate style="float: right; margin-right: 80px" v-model="recommendPaperRateMap[item.id]" disabled=""></n3-rate>
        </n3-button>
        <div style="width: 10px; height: 150px; float: none"> </div>
      </div>
    </div>
    <div style="width: auto; height: 100px">
    <div style="height: 60px; margin-top: 80px">
      <p style="color: #999999; text-align:center;line-height: 30px; font-size: 14px; float: none">邮箱：bibiwnanabe@foxmail.com</p>
      <p style="color: #999999; text-align:center;line-height: 30px; font-size: 14px; float: none">©️ 2019 黎碧怡 大连理工大学 软件学院</p>
    </div>
  </div>

  </section>
</template>

<script>
  import Vue from 'vue'
  import axios from 'axios'
  export default {
    name: 'sliderBar',
    computed: {
      screenHeight () {
        return window.innerHeight + 10000 + 'px'
      }
    },
    data () {
      return {
        speed: 0,
        show: false,
        subjectList: [],
        allSubject: [],
        userId: 0,
        recommendPaperList: [],
        recommendHotList: [],
        recommendPaperRateMap: {},
        hotPaperRateMap: {}
      }
    },
    methods: {
      getSubjectName (id) {
        for (let subject of this.allSubject) {
          if (subject.id === id) {
            return subject.subjectName
          }
        }
        return '未知科目'
      },
      showPaper (id) {
        this.$router.push({name: 'paperInfo', params: {paperId: id}})
      },
      reload () {
        this.getUserInfo()
        this.getSubjectList()
        this.getHot()
      },
      submitUserInfo () {
        var result = 0
        this.$axios({
          method: 'post',
          url: '/portal-api-1.1.1/user/info',
          crossDomain: true,
          data: JSON.stringify({name: this.username}),
          contentType: 'application/json'
        }).then(response => {
          result = response.data.code
          if (result === 1000) {
            this.n3Alert({
              content: '修改成功',
              type: 'success',
              placement: 'center',
              duration: 2000,
              width: '240px'
            })
            this.reload()
          }
        }).catch((error) => {
          this.alert('修改失败' + error.toString())
          return
        })
      },
      getSubjectList () {
        var result = 0
        axios.get('/portal-api-1.1.1/subject/list'
        ).then(response => {
          result = response.data.code
          if (result === 1002) {
            this.$router.push({
              name: 'login'
            })
          } else {
            this.subjectList = response.data.data.slice(0, 4)
            this.allSubject = response.data.data
          }
        }).catch((error) => {
          alert('获取信息失败' + error.toString())
        })
        this.loading = false
      },
      getUserInfo () {
        var result = 0
        axios.get(
          '/portal-api-1.1.1/user/myInfo'
        ).then(response => {
          result = response.data.code
          if (result === 1000) {
            this.userId = response.data.data.id
            this.username = response.data.data.name
            this.email = response.data.data.email
          }
          if (result === 1002) {
            this.n3Alert({
              content: '登录失效',
              type: 'success',
              placement: 'center',
              duration: 2000,
              width: '240px'
            })
            this.$router.push({
              name: 'login'
            })
          }
        }).catch((error) => {
          alert('获取信息失败' + error.toString())
          return
        })
      },
      getRecommend () {
        var result = 0
        axios.get(
          '/portal-api-1.1.1/paper/recommend'
        ).then(response => {
          result = response.data.code
          if (result === 1000) {
            this.recommendPaperList = response.data.data.paperList
            for (var paper of this.recommendPaperList) {
              this.recommendPaperRateMap[paper.id] = (5 - (paper.avgPoints / 20).toFixed(0))
            }
          }
          if (result === 1002) {
            this.$router.push({
              name: 'login'
            })
          }
        }).catch((error) => {
          alert('获取信息失败' + error.toString())
          return
        })
      },
      getHot () {
        var result = 0
        axios.get(
          '/portal-api-1.1.1/paper/hot'
        ).then(response => {
          result = response.data.code
          if (result === 1000) {
            this.recommendHotList = response.data.data.paperList
            for (var paper of this.recommendHotList) {
              this.hotPaperRateMap[paper.id] = (5 - (paper.avgPoints / 20).toFixed(0))
            }
            this.getRecommend()
          }
          if (result === 1002) {
            this.$router.push({
              name: 'login'
            })
          }
        }).catch((error) => {
          alert('获取信息失败' + error.toString())
          return
        })
      },
      showAllSubject () {
        this.$router.push({name: 'allSubject'})
      },
      goPaperList (subjectId) {
        this.$router.push({name: 'paperList', params: {subjectId: subjectId}})
      }
    },
    watch: {
      '$route' () {
        if (this.$route.name === 'index') {
          this.reload()
        }
      }
    },
    created () {
      this.reload()
    }
  }
</script>

<style lang="less">
  .app-slider {
    background: #fff;
    color: #fff;
    width: 100%;
    margin-left: auto;
    margin-right: auto;
    z-index: 3;
    box-shadow: 0px 0px 5px #f9f9f9;
    margin-top: 50px;
    position: absolute;
    height: 100%;
  }
  .front{
    width: 350px;
    height: 150px;
    border-radius: 5px;
    border-width: 2px;
    float: left;
    position:absolute;
    color: rgba(256,256,256,0);
    margin: 0;
    text-align: center;
    line-height: 150px;
    transition:all 0.3s linear 0;
    -moz-transition:all 0.3s linear 0;
    -webkit-transform:all 0.3s linear 0;
    -o-transition:all 0.3s linear 0;
    font-size: 20px;
  }
  .front:hover{
    background-color: rgba(0, 0, 0, .7);
    display-model: show;
    color: rgba(256,256,256,1);
  }
  .front-subject{
    width: 240px;
    height: 150px;
    border-radius: 5px;
    border-width: 2px;
    float: left;
    position:absolute;
    color: rgba(256,256,256,0);
    margin: 0;
    text-align: center;
    line-height: 150px;
    transition:all 0.3s linear 0;
    -moz-transition:all 0.3s linear 0;
    -webkit-transform:all 0.3s linear 0;
    -o-transition:all 0.3s linear 0;
    font-size: 20px;
  }
  .front-subject:hover{
    background-color: rgba(0, 0, 0, .75);
    display-model: show;
    color: rgba(256,256,256,1);
  }
</style>
