<template>
  <section class="history-list" :style="{ minHeight: screenHeight }">
    <div class="search-bar">
      <div class="search-item" style="height: 100px">
        <div class="form-item" style="height: 100px; margin-top: 30px">
          <label for="" style="font-size: 16px">搜索科目：</label>
          <n3-select v-model="chooseSubjectId" search="" @change="onChange" v-bind:options="subjectNameList">
          </n3-select>
        </div>
      </div>
    </div>
    <div v-model="paperList">
      <n3-button v-for="item in paperList" style="width: 240px; margin-left: 45px; margin-right:45px; height: 150px; margin-bottom: 30px; color: #2d3035;float: left; padding: 0px; border: white;
      box-shadow:0px 0 10px #ddd" @click.native="showPaper(item.id)" class="back">
        <p class="front-subject">点击进入答题</p>
        <h3 style="text-align:center; line-height: 75px" class="back">{{item.name}}</h3>
        <p style="margin-bottom: 20px" class="back">科目：{{getSubjectName(item.subjectId)}}</p>
        <span style="float: left;margin-left: 30px" class="back">难度：</span>
        <n3-rate style="float: right; margin-right: 30px" v-model="paperRateMap[item.id]" disabled=""></n3-rate>
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
    <div style="width: 100%; height: 100px;float: left; margin-top: 40px; bottom: 0px">
      <div style="height: 60px">
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
        return window.innerHeight + 'px'
      }
    },
    data () {
      return {
        subjectList: [],
        chooseSubjectId: 1,
        loading: false,
        searchChanged: false,
        paperList: [],
        pagination: {
          current: 1,
          total: 0,
          pagesize: 20
        },
        showSubjectList: [],
        subjectNameList: [],
        paperRateMap: {}
      }
    },
    methods: {
      getSubjectName (id) {
        for (let subject of this.subjectList) {
          if (subject.id === id) {
            return subject.subjectName
          }
        }
        return '未知科目'
      },
      showPaper (id) {
        this.$router.push({name: 'paperInfo', params: {paperId: id}})
      },
      onChange () {
        this.getPaperList()
      },
      pageChange () {},
      reload () {
        this.getSubjectList()
        this.getPaperListOnCreate()
      },
      getSubjectList () {
        this.$axios.get('/portal-api-1.1.1/subject/list'
        ).then(response => {
          if (response.data.code === 1002) {
            this.n3Alert({
              content: '你似乎未登录',
              type: 'success',
              placement: 'center',
              duration: 2000,
              width: '240px'
            })
            this.$router.push({name: 'login'})
          }
          this.subjectList = response.data.data
          for (var subject of this.subjectList) {
            this.subjectNameList.push({value: subject.id, label: subject.subjectName})
          }
        }).catch((error) => {
          alert('获取信息失败' + error.toString())
        })
        this.loading = false
      },
      getPaperList () {
        this.$axios.get('/portal-api-1.1.1/paper/list?subjectId=' + this.chooseSubjectId + '&size=12'
        ).then(response => {
          if (response.data.code === 1002) {
            this.n3Alert({
              content: '你似乎未登录',
              type: 'success',
              placement: 'center',
              duration: 2000,
              width: '240px'
            })
            this.$route.push({name: 'login'})
          }
          this.paperList = response.data.data.paperList
          for (var paper of this.paperList) {
            this.paperRateMap[paper.id] = (5 - (paper.avgPoints / 20).toFixed(0))
          }
          this.pagination.total = response.data.data.total
          this.pagination.current = response.data.data.page
          this.pagination.pagesize = response.data.data.size
        }).catch((error) => {
          this.alert('获取信息失败' + error.toString())
        })
      },
      getPaperListOnCreate () {
        if (this.$route.params.subjectId != null && this.$route.params.subjectId != undefined) {
          this.chooseSubjectId = this.$route.params.subjectId
        }
        this.getPaperList()
      }
    },
    watch: {
      '$route' () {
        if (this.$route.name === 'paperList') {
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
    position: absolute;
    margin-top: 50px;
    width: 100%;
    background-color: white;
    padding-left: 40px;
    td a {
      color: @primaryColor;
    }
  }
  .front-paper{
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
  .front-paper:hover{
    background-color: rgba(0, 0, 0, .75);
    display-model: show;
    color: rgba(256,256,256,1);
  }
</style>

