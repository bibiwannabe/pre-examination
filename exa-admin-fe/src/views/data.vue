<template>
  <section class="history-list" :style="{ minHeight: screenHeight }">
      <div class="search-item" style=" margin-left: 100px; margin-top: 10px">
          <label for="">科目：</label>
          <n3-select v-model="chooseSubjectId" search="" @change="searchRecord" v-bind:options="subjectNameList">
          </n3-select>
      </div>
    <div style="margin-top: 20px; width: 1000px; margin-left: auto; margin-right: auto">
      <h3 style="width: 1000px; margin-right: auto; margin-left: auto; text-align: center;margin-bottom: 60px">平均分最低的5份试卷</h3>
      <div style="float: left; width: 400px">
        <ve-histogram :data="paperData" width="400px"> </ve-histogram>
      </div>
      <div style="float: left; width: 500px; margin-left: 100px">
        <ve-radar :data="paperInfoData"  width="500px"></ve-radar>
      </div>
    </div>
    <div class="search-item" style=" margin-left: 100px; margin-top: 40px; width: 1000px">
      <label for="" style="margin-top: 40px">试卷：</label>
      <n3-select v-model="choosePaperId" search="" @change="getQuestion" v-bind:options="paperNameList">
      </n3-select>
    </div>
    <h3 style="width: 1000px; margin-right: auto; margin-left: auto; text-align: center;margin-bottom: 60px">正确率最低的5道试题</h3>
    <ve-bar :data="questionData" width="600px" style="margin-left: auto;margin-right: auto" :settings="chartSettings"></ve-bar>
  </section>
</template>
<script>
  import axios from 'axios'

  export default {
    computed: {
      screenHeight () {
        return window.innerHeight + 10000 + 'px'
      }
    },
    data () {
      return {
        chartSettings: {
          xAxisType: ['KMB', 'percent'],
          xAxisName: ['错误次数', '该卷中错误率'],
          axisSite: {
            top: ['该卷中错误率']
          }
        },
        chooseSubjectId: 1,
        subjectNameList: [],
        choosePaperId: 0,
        paperData: {
          columns: ['试卷名', '平均分'],
          rows: []
        },
        paperNameList: [],
        paperCountsMap: {},
        paperInfoData: {
          columns: ['试卷名', '平均分', '做题次数', '新鲜度'],
          rows: []
        },
        questionList: [],
        questionData: {
          columns: ['题目', '该卷中错误率', '错误次数'],
          rows: []
        },
        subjectList: [],
        paperList: [],
        loading: false,
        searchChanged: false,
        searchKey: {
          subjectId: 0
        }
      }
    },
    methods: {
      showDetail (id) {
        this.chooseSubjectId = id
      },
      pageChange (page) {
        this.pagination.current = page
        this.searchRecord()
      },
      keyChange () {
        this.searchRecord()
      },
      getQuestion () {
        this.questionData.rows = []
        this.$axios.get('/admin-api-1.4.5/question/wrongMax?paperId=' + this.choosePaperId
        ).then(response => {
          if (response.data.code === 1000) {
            this.questionList = response.data.data
            for (let question of this.questionList) {
              this.questionData.rows.push({'题目': question.questionContent, '该卷中错误率': question.counts / this.paperCountsMap[question.paperId], '错误次数': question.counts})
            }
          }
        }).catch((error) => {
          alert('获取题目列表失败' + error.toString())
        })
      },
      searchRecord () {
        this.$axios.get('/admin-api-1.4.5/paper/lowestList?subjectId=' + this.chooseSubjectId
        ).then(response => {
          if (response.data.code === 1000) {
            this.paperList = response.data.data
            this.paperData.rows = []
            this.paperNameList = []
            this.paperInfoData.rows = []
            for (let paper of this.paperList) {
              this.paperData.rows.push({'试卷名': paper.name, '平均分': paper.avgPoints})
              this.paperNameList.push({value: paper.id, label: paper.name})
              this.paperInfoData.rows.push({'试卷名': paper.name, '平均分': paper.avgPoints, '做题次数': paper.counts, '新鲜度': paper.createTime % 1000})
              this.paperCountsMap[paper.id] = paper.counts
            }
            this.choosePaperId = this.paperList[0].id
            this.getQuestion()
          }
        }).catch((error) => {
          this.alert('获取信息失败' + error.toString())
        })
      },
      getSubjectName (id) {
        for (let subject of this.subjectList) {
          if (subject.id === id) {
            return subject.subjectName
          }
        }
        return '未知科目'
      },
      reload () {
        axios.get('/admin-api-1.4.5/subject/list'
        ).then(response => {
          var result = response.data.code
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
          this.subjectList = response.data.data
          this.searchKey.subjectId = response.data.data[0].id
          this.chooseSubjectId = response.data.data[0].id
          this.subjectNameList = []
          for (var subject of this.subjectList) {
            this.subjectNameList.push({value: subject.id, label: subject.subjectName})
          }
        }).catch((error) => {
          alert('获取信息失败' + error.toString())
        })
        this.searchRecord()
        this.loading = false
      },
      createPaper () {
        this.$router.push({name: 'createPaper'})
      }
    },
    watch: {
      '$route' () {
        if (this.$route.name === 'data') {
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

