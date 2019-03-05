<template>
  <section class="history-list" :style="{ minHeight: screenHeight }">
    <div class="search-bar">
      <div class="search-item" style="height: 100px">
        <div class="form-item" style="height: 100px; margin-top: 30px">
          <label for="" style="font-size: 16px">搜索科目：</label>
          <n3-select v-model="chooseSubjectId" search="" @change="onChange" v-bind:options="subjectNameList" multiple>
          </n3-select>
        </div>
      </div>
    </div>
    <div v-model="showSubjectList">
      <n3-button v-for="item in showSubjectList" style="width: 240px; margin-left: 50px;margin-right: 50px; height: 150px; margin-bottom: 30px; color: #2d3035;float: left; padding: 0px; border: white;
      box-shadow:0px 0 10px #ddd" @click.native="goPaperList(item.value)" class="back">
        <n3-icon size="30px" type="book" style="float: left; margin-left: 40px; line-height: 150px"></n3-icon>
        <h2 style="text-align:center; line-height: 150px;float: left; margin-left: 20px">{{item.label}}</h2>
        <p class="front-subject">点击查看该科目下试卷</p>
      </n3-button>
    </div>
    <div style="width: 100%; height: 180px; float: left">
      <div style="height: 60px; margin-top: 80px">
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
        chooseSubjectId: [],
        loading: false,
        searchChanged: false,
        subjectNameList: [],
        showSubjectList: []
      }
    },
    methods: {
      onChange () {
        this.showSubjectList = []
        for (var subject of this.chooseSubjectId) {
          for (var subject2 of this.subjectList) {
            if (subject === subject2.id) {
              this.showSubjectList.push({value: subject2.id, label: subject2.subjectName})
            }
          }
        }
      },
      goPaperList (subjectId) {
        this.$router.push({name: 'paperList', params: {subjectId: subjectId}})
      },
      reload () {
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
            this.showSubjectList.push({value: subject.id, label: subject.subjectName})
            this.subjectNameList.push({value: subject.id, label: subject.subjectName})
          }
        }).catch((error) => {
          alert('获取信息失败' + error.toString())
        })
        this.loading = false
      }
    },
    watch: {
      '$route' () {
        if (this.$route.name === 'allSubject') {
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
    height: auto;
    td a {
      color: @primaryColor;
    }
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

