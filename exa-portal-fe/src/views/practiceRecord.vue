<template>
  <section class="router-view" :style="{ minHeight: screenHeight }">
    <div v-model="practiceList">
      <n3-button v-for="item in practiceList" style="width: 240px; margin-left: 50px;margin-right: 50px; height: 150px; margin-bottom: 30px; color: #2d3035;float: left; padding: 0px; border: white;
      box-shadow:0px 0 10px #ddd" @click.native="goRecordInfo(item.practiceId)" class="back">
        <p class="front-subject">点击查看记录</p>
        <h3 style="text-align:center; line-height: 100px;">{{item.paperName}}</h3>
        <n3-icon size="15px" type="bar-chart-o" ></n3-icon>
        <span style="line-height: 30px;">平均分：{{item.avgPoints}}</span>
      </n3-button>
    </div>
    <div style="width: 100%; height: 180px; float: left">
      <div style="height: 100px; margin-top: 40px">
        <p style="color: #999999; text-align:center;line-height: 30px; font-size: 14px; float: none">邮箱：bibiwnanabe@foxmail.com</p>
        <p style="color: #999999; text-align:center;line-height: 30px; font-size: 14px; float: none">©️ 2019 黎碧怡 大连理工大学 软件学院</p>
      </div>
    </div>
  </section>
</template>
<script>
  import API from '../api'

  export default {
    computed: {
      screenHeight () {
        return window.innerHeight + 200 + 'px'
      }
    },
    data () {
      return {
        practiceList: [],
        subjectName: '',
        loading: false,
        searchChanged: false
      }
    },
    methods: {
      reload () {
        this.$axios.get('/portal-api-1.1.1/practice/list'
        ).then(response => {
          if (response.data.code === 1000) {
            this.practiceList = response.data.data
          } else {
            this.n3Alert({
              content: response.data.message,
              type: 'danger',
              placement: 'center',
              duration: 2000,
              width: '240px'
            })
          }
          if (response.data.code === 1002) {
            this.$router.push({name: 'login'})
          }
        }).catch((error) => {
          alert('获取信息失败' + error.toString())
        })
        this.loading = false
      },
      goRecordInfo (recordId) {
        this.$router.push({name: 'recordInfo', params: {recordId: recordId}})
      }
    },
    watch: {
      '$route' () {
        if (this.$route.name === 'practiceRecord') {
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
  @import "../style/define.less";
  .history-list {
    td a {
      color: @primaryColor;
    }
  }
</style>

