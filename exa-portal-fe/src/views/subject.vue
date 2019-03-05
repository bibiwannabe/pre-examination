<template>
  <section class="router-view" :style="{ minHeight: screenHeight }">
    <div v-model="subjectList">
      <n3-button v-for="item in subjectList" style="width: 240px; margin-left: 50px;margin-right: 50px; height: 150px; margin-bottom: 30px; color: #2d3035;float: left; padding: 0px; border: white;
      box-shadow:0px 0 10px #ddd" @click.native="goQuestionList(item.subjectId)" class="back">
        <p class="front-subject">点击进入练习</p>
        <h3 style="text-align:center; line-height: 100px;">{{item.subjectName}}</h3>
        <n3-icon size="15px" type="times-circle" ></n3-icon>
        <span style="line-height: 30px;">错题数：{{item.questionCounts}}</span>
      </n3-button>
    </div>
    <n3-page
      :total="pagination.total"
      :pagesize="pagination.pagesize"
      :show-total="true"
      v-model="pagination.current"
      @change="pageChange"
      style="float: right; width: 100%; margin-bottom: 30px; margin-top: 30px"
    ></n3-page>
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
        subjectList: [],
        subjectName: '',
        loading: false,
        searchChanged: false,
        pagination: {
          current: 1,
          total: 0,
          pagesize: 20
        }
      }
    },
    methods: {
      pageChange () {
        this.reload()
      },
      reload () {
        this.$axios.get('/portal-api-1.1.1/question/subjectList?page=' + this.pagination.current
        ).then(response => {
          this.subjectList = response.data.data.subjectList
          this.pagination.current = response.data.data.page
          this.pagination.total = response.data.data.total
          this.pagination.size = response.data.data.size
        }).catch((error) => {
          alert('获取信息失败' + error.toString())
        })
        this.loading = false
      },
      goQuestionList (subjectId) {
        this.$router.push({name: 'questionList', params: {subjectId: subjectId}})
      }
    },
    watch: {
      '$route' () {
        if (this.$route.name === 'practiceSubject') {
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

