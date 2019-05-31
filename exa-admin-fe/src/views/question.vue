<template>
  <section class="history-list">
    <div class="search-bar">
      <div class="search-item" style="float: left">
        <div class="form-item">
          <label for="">科目：</label>
          <n3-select  v-model="subjectId" v-bind:options="subjectNameList"></n3-select>
        </div>
        <div class="form-item">
          <label for="">题型：</label>
          <n3-select v-model="questionType" @change="searchChange">
            <n3-option value="0">单选题</n3-option>
            <n3-option value="1">多选题</n3-option>
            <n3-option value="2">填空题</n3-option>
          </n3-select>
        </div>

      </div>
      <div class="search-submit" style="float: right;width: 100px; margin-right: 50px">
        <n3-button block @click.native="showUploadWindow">批量上传</n3-button>
      </div>
      <div class="search-submit" style="float: right;width: 100px; margin-right: 50px">
        <n3-button block @click.native="createQuestion">新建题目</n3-button>
      </div>
      <div class="search-submit" style="float: left;width: 100px; margin-left: 30px;margin-top: 4px">
        <n3-button type="primary" block @click.native="search">搜索</n3-button>
      </div>

    </div>
    <n3-data-table
      :selection="selection"
      :source="questionList"
      :columns="columns"
      :filter="false"
      :search="false"
      :page="false"
      :select-col="false"
      :loading="loading"
      responsive
      v-model="questionList"
      @change="load"
    >
      <n3-alert dismissable :show="false" icon="smile-o"
                ref="alertPaper"
                width="400px"
                placement="center"
                message="请选择要添加到的试卷"
                description style="height: 350px">
          <n3-select style="margin-top: 20px"
                     v-model="readyToAddPaperId"
                     search="" v-bind:options="paperList" width="300px">
          </n3-select>
        <n3-button type="primary" block @click.native="addQuestion" style="margin-top: 200px; width:300px" width="100px">添加到试卷</n3-button>
      </n3-alert>

      <n3-alert dismissable :show="false" icon="smile-o"
                ref="alertUpload"
                width="400px"
                placement="center"
                message="请上传CSV格式的试题文件"
                description style="height: 380px">
        <n3-uploader type="drag"  :multiple="false" url="/admin-api-1.4.5/question/batchUpload" @error="onError" @finish="closeUploadWindow" style="margin-top: 30px"></n3-uploader>
        <n3-button type="primary" block @click.native="downloadCSV" style="margin-top: 30px; width:300px" width="100px">下载模板</n3-button>
      </n3-alert>

    </n3-data-table>
    <n3-page
      :total="pagination.total"
      :pagesize="pagination.pagesize"
      :show-total="true"
      v-model="pagination.current"
      @change="pageChange"
    >
    </n3-page>

  </section>
</template>
<script>
  import API from '../api'
  import axios from 'axios'

  export default {
    data () {
      return {
        subject: {
          id: 0,
          name: ''
        },
        subjectList: [],
        questionList: [],
        subjectNameList: [],
        loading: false,
        searchChanged: false,
        subjectId: 0,
        queryType: 0,
        questionType: 0,
        questionTypeName: '',
        questionContent: '',
        questionSelections: [],
        questionAnswer: '',
        readOnly: true,
        subjectName: '',
        showPaper: false,
        selection: {
          checkRows: [],
          onSelect (record, checked, checkRows) {},
          onSelectAll (checked, checkRows, changeRows) {},
          getCheckboxProps (record) {
            return {
              checked: false,
              disabled: true
            }
          }
        },
        paperList: [],
        readyToAddQuestionId: 0,
        readyToAddPaperId: 0,
        pagination: {
          current: 1,
          total: 0,
          size: 20
        },
        columns: [
          {
            title: 'ID',
            dataIndex: 'id',
            width: '50px',
            render: (text, record, index) => {
              return `<div>${record.id}</div>`
            }
          }, {
            title: '题目描述',
            dataIndex: 'content',
            width: '250px',
            render: (text, record, index) => {
              return `<div>${record.content}</div>`
            }
          },
          {
            title: '科目',
            dataIndex: 'subject',
            width: '120px',
            render: (text, record, index) => {
              var subjectId = this.getSubjectName(record.subjectId)
              return `<div>${subjectId}</div>`
            }
          }, {
            title: '做题人数',
            dataIndex: 'counts',
            width: '120px',
            render: (text, record, index) => {
              return `<div>${record.counts}</div>`
            }
          }, {
            title: '正确率',
            dataIndex: 'rate',
            width: '120px',
            render: (text, record, index) => {
              if (record.counts === 0) {
                return `<div>暂无数据</div>`
              }
              var rate = record.correctNum / record.counts * 100
              rate = rate.toFixed(2);
              return `<div>${rate}%</div>`
            }
          }, {
            title: '点击查看详情',
            dataIndex: 'detail',
            width: '100px',
            render: (text, record, index) => {
              return `<div class="search-submit" style="width: 60px; margin-left: 10px;margin-top: 4px">
        <n3-button  block @click.native="showDetail(${record.id})">详情</n3-button></div>`
            }
          }, {
            title: '添加到试卷',
            dataIndex: 'addToPaper',
            width: '100px',
            render: (text, record, index) => {
              return `<div class="search-submit" style="width: 120px; margin-left: 10px;margin-top: 4px">
        <n3-button  block @click.native="addToPaper(${record.id})">添加到试卷</n3-button></div>`
            }
          }
        ],
        source: []
      }
    },
    methods: {
      load () {
        var url = '/admin-api-1.4.5/question/list?subjectId=' + this.subjectId + '&type=' + this.questionType + '&page=' + this.pagination.current
        axios.get(url
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
          this.questionList = response.data.data.questionList
          this.pagination.total = response.data.data.total
          this.pagination.current = response.data.data.page
          this.pagination.size = response.data.data.size
        }).catch((error) => {
          alert('获取信息失败' + error.toString())
        })
        this.loading = false
      },
      getSubjectName (id) {
        for (let subject of this.subjectList) {
          if (subject.id === id) {
            return subject.subjectName
          }
        }
        return '未知科目'
      },
      pageChange (page) {
        this.pagination.current = page
        this.search()
      },
      search () {
        this.loading = true
        this.load()
      },
      getQuestionTypeName () {
        if (this.questionType === 0) {
          return '单选题'
        }
        if (this.questionType === 1) {
          return '多选题'
        }
        if (this.questionType === 2) {
          return '填空题'
        }
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
          this.subjectNameList = []
          for (var subject of this.subjectList) {
            this.subjectNameList.push({value: subject.id, label: subject.subjectName})
          }
          this.subjectId = response.data.data[0].id
          this.subject.id = this.subjectId
        }).catch((error) => {
          alert('获取信息失败' + error.toString())
        })
        var millisec = 1
        setTimeout(this.getList, millisec)
        this.loading = false
      },
      getList () {
        axios.get('/admin-api-1.4.5/question/list?subjectId=1&type=0'
      ).then(response => {
        this.questionList = response.data.data.questionList
        this.pagination.total = response.data.data.total
        this.pagination.current = response.data.data.page
        this.pagination.size = response.data.data.size
        this.questionType = '0'
        this.questionTypeName = '单选题'
        this.queryType = '0'
      }).catch((error) => {
        this.alert('获取信息失败' + error.toString())
      })
      },
      searchChange () {
        this.searchChanged = true
      },
      created () {
        this.reload()
      },
      deactivated () {
        this.$destroy()
      },
      showDetail (id) {
        if (this.questionType === '0') {
          this.$router.push({name: 'choiceInfo', params: {id: id}})
        }
        if (this.questionType === '1') {
          this.$router.push({name: 'selectionInfo', params: {id: id}})
        }
        if (this.questionType === '2') {
          this.$router.push({name: 'fillingInfo', params: {id: id}})
        }
      },
      addToPaper (id) {
        var url = '/admin-api-1.4.5/paper/all?subjectId=' + this.subjectId
        axios.get(url
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
          var papers = response.data.data
          this.paperList = []
          for (var paper of papers) {
            this.paperList.push({value: paper.id, label: paper.name})
          }
        })
        this.readyToAddQuestionId = id
        this.$refs.alertPaper.open()
      },
      showUploadWindow () {
        this.$refs.alertUpload.open()
      },
      closeUploadWindow () {
        this.$refs.alertUpload.close()
        this.n3Alert({
          content: '上传成功',
          type: 'success',
          placement: 'center',
          duration: 2000,
          width: '240px'
        })
      },
      addQuestion () {
        var data = JSON.stringify({id: this.readyToAddPaperId, questionType: this.questionType, questinId: this.readyToAddQuestionId})
        this.$axios({
          method: 'post',
          url: '/admin-api-1.4.5/paper/addQuestion',
          crossDomain: true,
          data: data,
          contentType: 'application/json'
        }).then(response => {
          var result = response.data.code
          if (result !== 1000) {
            this.n3Alert({
              content: response.data.message,
              type: 'danger',
              placement: 'center',
              duration: 2000,
              width: '240px'
            })
            if (result === 1002) {
              this.$router.push({
                name: 'login'
              })
            }
          } else {
            this.$refs.alertPaper.close()
            this.n3Alert({
              content: '添加试题成功',
              type: 'success',
              placement: 'center',
              duration: 2000,
              width: '240px'
            })
          }
        }).catch((error) => {
          alert('添加试题失败' + error.toString())
          return
        })
      },
      downloadCSV () {
        var url = '/admin-api-1.4.5/template/download/template.csv'
        this.$axios({
          method: 'get',
          url: url,
          crossDomain: true,
          responseType: 'arraybuffer',
          contentType: 'application/json'
        }).then(response => {
          let blob = new Blob([response.data], {
            type: 'application/json'
          })
          let link = document.createElement('a');
          link.href = window.URL.createObjectURL(blob);
          var title = '批量上传模板.csv'
          link.download = title
          link.click()
        }).catch((error) => {
          alert('添加试题失败' + error.toString())
          return
        })
      },
      createQuestion () {
        this.$router.push({name: 'createQuestion'})
      }
    },
    watch: {
      '$route' () {
        if (this.$route.name === 'question') {
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

