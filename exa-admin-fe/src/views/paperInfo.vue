<template>
  <section class="history-list">
    <n3-form
      ref="form"
      validate
    >
      <n3-form-item
        label="试卷名:"
        need
        :label-col="1">
        <n3-input :rules="[{type:'required'}]" v-model="paperName" width="600px">{{paperName}}</n3-input>
      </n3-form-item>
      <n3-form-item label="科目:" :label-col="1">
        <p v-model="subjectName">{{subjectName}}</p>
      </n3-form-item>
      <n3-form-item label="总分:" :label-col="1">
        <p v-model="sum">{{sum}}</p>
      </n3-form-item>
      <n3-form-item
        :label-col="1">
        <n3-button
          @click.native="submitPaper"
          type="primary"
          :loading="loading"
          class="submit-btn"
          style="margin-left:100px; width: 120px"
        >
          {{ loading ? '操作中' : '保存试卷' }}
        </n3-button>
        <n3-button
          @click.native="cancelAndReturn"
          :loading="loading"
          class="submit-btn"
          style="margin-left:150px;width: 120px"

        >
          取消并返回
        </n3-button>
      </n3-form-item>
    </n3-form>
    <n3-tabs :value="0" @change="getQuestionType">
      <n3-tab header="单选题" v-model="choiceNum">
      </n3-tab>
      <n3-tab header="多选题" v-model="selectionNum">
      </n3-tab>
      <n3-tab header="填空题" v-model="fillingNum">
      </n3-tab>
    </n3-tabs>
    <div v-if="questionType === 0">
      <div class="search-bar" style=" margin-top: 15px">
        <div class="search-item" style="float: left">
          <div class="form-item">
            <label for="">每题分值：</label>
            <n3-input-number v-model="choiceEachPoint" width="80px"></n3-input-number>
          </div>
          <div class="form-item">
            <label for="">题目：</label>
            <n3-typeahead
              v-model="keywords"
              :items="searchContentList"
              placeholder="请输入查询关键词" width="800px">
            </n3-typeahead>
          </div>
        </div>
        <div class="search-submit" style="float: left;width: 100px; margin-left: 10px;margin-top: 4px">
          <n3-button type="primary" block @click.native="addChoiceList">添加题目</n3-button>
        </div>
      </div>
      <n3-data-table
        :selection="selection"
        :source="choiceList"
        :columns="columns"
        :filter="false"
        :search="false"
        :page="false"
        :select-col="false"
        :loading="loading"
        responsive
        v-model="choiceList"
        @change="load"
      ></n3-data-table>
    </div>
    <div v-if="questionType === 1">
      <div class="search-bar" style=" margin-top: 15px">
        <div class="search-item" style="float: left">
          <div class="form-item">
            <label for="">每题分值：</label>
            <n3-input :rules="[{type:'required'}]" v-model="selectionEachPoint" width="80px" style="margin-left: 0px">
              {{selectionEachPoint}}
            </n3-input>
          </div>
          <div class="form-item">
            <label for="">题目：</label>
            <n3-typeahead
              v-model="keywords"
              :items="searchContentList"
              placeholder="请输入查询关键词" width="800px">
            </n3-typeahead>
          </div>
        </div>
        <div class="search-submit" style="float: left;width: 100px; margin-left: 10px;margin-top: 4px">
          <n3-button type="primary" block @click.native="addSelectionList">添加题目</n3-button>
        </div>

      </div>
      <n3-data-table
        :selection="selection"
        :source="selectionList"
        :columns="columns"
        :filter="false"
        :search="false"
        :page="false"
        :select-col="false"
        :loading="loading"
        responsive
        v-model="selectionList"
        @change="load"
      ></n3-data-table>
    </div>
    <div v-if="questionType === 2">
      <div class="search-bar" style=" margin-top: 15px">
        <div class="search-item" style="float: left">
          <div class="form-item">
            <label for="">每题分值：</label>
            <n3-input :rules="[{type:'required'}]" v-model="fillingEachPoint" width="80px" style="margin-left: 0px">
              {{fillingEachPoint}}
            </n3-input>
          </div>
          <div class="form-item">
            <label for="">题目：</label>
            <n3-typeahead
              v-model="keywords"
              :items="searchContentList"
              placeholder="请输入查询关键词" width="800px">
            </n3-typeahead>
          </div>
        </div>
        <div class="search-submit" style="float: left;width: 100px; margin-left: 10px;margin-top: 4px">
          <n3-button type="primary" block @click.native="addFillingList">添加题目</n3-button>
        </div>
      </div>
      <n3-data-table
        :selection="selection"
        :source="fillingList"
        :columns="columns"
        :filter="false"
        :search="false"
        :page="false"
        :select-col="false"
        :loading="loading"
        responsive
        v-model="fillingList"
        @change="load"
      ></n3-data-table>
    </div>

  </section>
</template>
<script>
  import API from '../api'

  export default {
    data () {
      return {
        paperId: 0,
        isReadonly: true,
        isShow: true,
        timeout: 200,
        subject: {
          id: 0,
          name: ''
        },
        keywords: '',
        selection: {
          checkRows: [],
          onSelect (record, checked, checkRows) {
          },
          onSelectAll (checked, checkRows, changeRows) {
          },
          getCheckboxProps (record) {
            return {
              checked: false,
              disabled: true
            }
          }
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
            width: '300px',
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
                return `<div>未知数据</div>`
              }
              var rate = record.correctNum / record.counts * 100
              rate = rate.toFixed(2)
              return `<div>${rate}%</div>`
            }
          }, {
            title: '删除题目',
            dataIndex: 'delete',
            width: '100px',
            render: (text, record, index) => {
              this.deleteIndex = index
              return `<div class="search-submit" style="width: 60px; margin-left: 10px;margin-top: 4px">
        <n3-button  @click.native="deleteQuestion(${index})">删除</n3-button></div>`
            }
          }
        ],
        deleteIndex: 0,
        paperName: '',
        content: '',
        subjectList: [],
        choiceList: [],
        selectionList: [],
        fillingList: [],
        choiceNum: 0,
        choiceEachPoint: 0,
        selectionNum: 0,
        selectionEachPoint: 0,
        fillingNum: 0,
        fillingEachPoint: 0,
        loading: false,
        searchChanged: false,
        subjectId: 0,
        questionType: 0,
        questionTypeName: '',
        readOnly: true,
        questionId: 0,
        searchList: [],
        addQuestionId: '0',
        searchContentList: [],
        sum: 0
      }
    },
    methods: {
      mounted () {
        this.$nextTick(function () {
          setInterval(this.getSubjectList, 1000 * 120);
        })
      },
      searchQuestion () {
        for (var i = 0; i < this.searchContentList.length; i++) {
          this.searchContentList.pop()
        }
        var url = '/admin-api-1.4.5/question/search?subjectId=' + this.subject.id + '&type=' + this.questionType + '&key=' + this.keywords
        if (this.keywords === '' || this.keywords === undefined || this.keywords === null) {
          return
        }
        this.$axios.get(url
        ).then(response => {
          var result = response.data.code
          if (result !== 1000) {
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
            } else {
              this.n3Alert({
                content: '出错',
                type: 'danger',
                placement: 'center',
                duration: 2000,
                width: '240px'
              })
            }
          }
          if (response.data.data === undefined) {
            this.searchList = []
            return
          }
          this.searchList = response.data.data
          for (var item of this.searchList) {
            this.searchContentList.push(item.content)
          }
        }).catch((error) => {
          alert('获取信息失败' + error.toString())
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
      getPaper () {
        this.paperId = this.$route.params.id
        var url = '/admin-api-1.4.5/paper/' + this.$route.params.id
        this.$axios.get(url
        ).then(response => {
          this.subject.id = response.data.data.subjectId
          this.subjectName = this.getSubjectName(this.subject.id)
          this.paperName = response.data.data.name
          this.choiceList = response.data.data.choice.questionList
          this.selectionList = response.data.data.selection.questionList
          this.fillingList = response.data.data.filling.questionList
          this.choiceEachPoint = response.data.data.choice.point
          this.choiceNum = this.choiceList.length
          this.selectionNum = this.selectionList.length
          this.fillingNum = this.fillingList.length
          this.selectionEachPoint = response.data.data.selection.point
          this.fillingEachPoint = response.data.data.filling.point
          this.sum = this.choiceEachPoint * this.choiceNum + this.selectionEachPoint * this.selectionNum + this.fillingEachPoint * this.fillingNum
        }).catch((error) => {
          alert('获取信息失败' + error.toString())
        })
      },
      getSubjectList () {
        this.$axios.get('/admin-api-1.4.5/subject/list'
        ).then(response => {
          this.subjectList = response.data.data
        }).catch((error) => {
          alert('获取信息失败' + error.toString())
        })
      },
      reload4 () {
        this.loading = true
        this.getSubjectList()
        this.getPaper()
        this.loading = false
      },
      create () {
        this.reload4()
      },
      addChoiceList () {
        for (var question of this.searchList) {
          if (this.keywords === question.content) {
            this.choiceList.push(question)
            break
          }
        }
        this.searchList = []
        this.keywords = ''
        this.searchContentList = []
      },
      addSelectionList () {
        for (var question of this.searchList) {
          if (this.keywords === question.content) {
            this.selectionList.push(question)
            break
          }
        }
        this.searchList = []
        this.keywords = ''
        this.searchContentList = []
      },
      addFillingList () {
        for (var question of this.searchList) {
          if (this.keywords === question.content) {
            this.fillingList.push(question)
            break
          }
        }
        this.searchList = []
        this.keywords = ''
        this.searchContentList = []
      },
      deleteQuestion (index) {
        if (this.questionType === 0) {
          this.choiceList.splice(index, 1)
        }
        if (this.questionType === 1) {
          this.selectionList.splice(index, 1)
        }
        if (this.questionType === 2) {
          this.fillingList.splice(index, 1)
        }
      },
      getQuestionType (index) {
        if (index === 0) {
          this.questionType = 0
        }
        if (index === 1) {
          this.questionType = 1
        }
        if (index === 2) {
          this.questionType = 2
        }
      },
      cancelAndReturn () {
        this.$router.go(-1)
      },
      submitPaper () {

        var choiceIdList = []
        var selectionIdList = []
        var fillingIdList = []
        for (var choice of this.choiceList) {
          choiceIdList.push(choice.id)
        }
        for (var selection of this.selectionList) {
          selectionIdList.push(selection.id)
        }
        for (var filling of this.fillingList) {
          fillingIdList.push(filling.id)
        }
        var data = JSON.stringify({
          id: this.paperId,
          subjectId: this.subject.id,
          name: this.paperName,
          choiceList: {questionIdList: choiceIdList, eachPoints: this.choiceEachPoint},
          selectionList: {questionIdList: selectionIdList, eachPoints: this.selectionEachPoint},
          fillingList: {questionIdList: fillingIdList, eachPoints: this.fillingEachPoint}
        })
        this.$axios({
          method: 'post',
          url: '/admin-api-1.4.5/paper/update',
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
            var id = response.data.data
            this.n3Alert({
              content: '保存成功',
              type: 'success',
              placement: 'center',
              duration: 2000,
              width: '240px'
            })
            this.reload4()
          }
        })
      }
    },
    watch: {
      '$route' () {
        if (this.$route.name === 'paperInfo') {
          this.create()
        }
      },
      keywords () {
        for (var i of this.searchContentList) {
          if (this.keywords === i) {
            return
          }
        }
        if (this.keywords.length > 0 && this.keywords !== null) {
          clearTimeout(this.timeout)
          this.timeout = setTimeout(() => {
            this.searchQuestion()
          }, 300)
        }
      },
      choiceEachPoint () {
        this.sum = this.choiceEachPoint * this.choiceList.length + this.selectionEachPoint * this.selectionList.length + this.fillingEachPoint * this.fillingList.length
      },
      choiceList () {
        this.sum = this.choiceEachPoint * this.choiceList.length + this.selectionEachPoint * this.selectionList.length + this.fillingEachPoint * this.fillingList.length
      },
      selectionEachPoint () {
        this.sum = this.choiceEachPoint * this.choiceList.length + this.selectionEachPoint * this.selectionList.length + this.fillingEachPoint * this.fillingList.length
      },
      selectionList () {
        this.sum = this.choiceEachPoint * this.choiceList.length + this.selectionEachPoint * this.selectionList.length + this.fillingEachPoint * this.fillingList.length
      },
      fillingEachPoint () {
        this.sum = this.choiceEachPoint * this.choiceList.length + this.selectionEachPoint * this.selectionList.length + this.fillingEachPoint * this.fillingList.length
      },
      fillingList () {
        this.sum = this.choiceEachPoint * this.choiceList.length + this.selectionEachPoint * this.selectionList.length + this.fillingEachPoint * this.fillingList.length
      }
    },
    created () {
      this.reload4()
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

