<template>
  <section class="history-list">
    <div class="search-bar">
      <div class="search-item" style="float: left">
        <div class="form-item">
          <label for="">科目：</label>
          <select v-model="searchKey.subjectId" @change="searchChange" style="width: 80px;padding-left: 8px;padding-top: 5px; padding-bottom: 5px; border-color: #dddddd; background-color: white">
            <option :value="item.id.toString()" v-for="item in subjectList">{{item.subjectName}}</option>
          </select>
        </div>
        <div class="form-item">
          <label for="">题型：</label>
          <n3-select v-model="searchKey.questionType" @change="searchChange">
            <n3-option value="0" selected>单选题</n3-option>
            <n3-option value="1">多选题</n3-option>
            <n3-option value="2">填空题</n3-option>
          </n3-select>
        </div>
        <div class="form-item">
          <label for="">排序方式：</label>
          <n3-select v-model="searchKey.queryType" @change="searchChange">
            <n3-option value="0" selected>最新</n3-option>
            <n3-option value="1">错误率</n3-option>
          </n3-select>
        </div>

      </div>
      <div class="search-submit" style="float: right;width: 100px; margin-right: 50px">
        <n3-button block @click.native="searchRecord">新建题目</n3-button>
      </div>
      <div class="search-submit" style="float: left;width: 100px; margin-left: 10px;margin-top: 4px">
        <n3-button type="primary" block @click.native="searchRecord">搜索</n3-button>
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
    >
    </n3-data-table>
  </section>
</template>
<script>
  import API from '../api'

  export default {
    data() {
      return {
        subjectList: [],
        questionList: [],
        loading: false,
        searchChanged: false,
        searchKey: {
          subjectId: 0,
          queryType: 0,
          questionType: 0,
          startDate: '',
          endDate: ''
        },
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
        columns: [
          {
            title: 'ID',
            dataIndex: 'queryRecordId',
            width: '100px',
            render: (text, record, index) => {
              return `<div>${record.id}</div>`
            }
          }, {
            title: '题目描述',
            dataIndex: 'queryDate',
            width: '160px',
            render: (text, record, index) => {
              return `<div>${record.content}</div>`
            }
          }, {
            title: '做题人数',
            dataIndex: 'userId',
            width: '120px',
            render: (text, record, index) => {
              return `<div>${record.counts}</div>`
            }
          }, {
            title: '正确率',
            dataIndex: 'ipAddress',
            width: '160px',
            render: (text, record, index) => {
              var rate = record.correctNum/record.counts * 100
              return `<div>${rate}%</div>`
            }
          }, {
            title: '点击查看详情',
            dataIndex: 'detail',
            width: '100px',
            render: (text, record, index) => {
              var isShow = false

              var choiceAndSelectionAnswer = ''
              var selectionAnswer = []
              if (record.type === 0) {
                choiceAndSelectionAnswer = record.answer
              }
              if (isShow) {
                return `<div class="search-submit" style="width: 60px; margin-left: 10px;margin-top: 4px">
        <n3-button  block @click.native="showDetail">详情</n3-button></div>
        <div  style="width: 1080px;">
      <n3-form-item
        label="题目"
        need
        :label-col="3"
      >
        <n3-input
          width="320px"
          :rules="[{type:'required'}]"
          :custom-validate="passwordValidate"
          class="fl"
        > ${record.content}
        </n3-input>
      </n3-form-item></div>`
              }
              return `<div class="search-submit" style="width: 60px; margin-left: 10px;margin-top: 4px">
        <n3-button  block @click.native="isShow = true">详情</n3-button>
      </div>`
            }
          }
        ],
        source: []
      }
    },
    methods: {
      searchRecord () {
        var url = '/api/question/list?subjectId='+this.searchKey.subjectId+'&type='+this.searchKey.queryType
        this.$axios.get(url
        ).then(response => {
          this.questionList = response.data.data.questionList
        }).catch((error) => {
          this.alert('获取信息失败' + error.toString())
        })
      },
      reload () {
        var result = []
        var msg = ''
        this.$axios.get('/api/subject/list'
        ).then(response => {
          this.subjectList = response.data.data
        }).catch((error) => {
          alert('获取信息失败' + error.toString())
        })
        var millisec = 1
        setTimeout(this.getList, millisec)
        this.loading = false
      },
      getList () {
        this.$axios.get('/api/question/list?subjectId=1&type=0'
      ).then(response => {
        this.questionList = response.data.data.questionList
      }).catch((error) => {
        this.alert('获取信息失败' + error.toString())
      })
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

