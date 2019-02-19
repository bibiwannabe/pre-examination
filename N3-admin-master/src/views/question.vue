<template>
  <section class="history-list">
    <div class="search-bar">
      <div class="search-item" style="float: left">
        <div class="form-item">
          <label for="">科目：</label>
          <select v-model="subjectId" @change="searchChange" style="width: 80px;padding-left: 8px;padding-top: 5px; padding-bottom: 5px; border-color: #dddddd; background-color: white">
            <option :value="item.id" v-for="item in subjectList">{{item.subjectName}}</option>
          </select>
        </div>
        <div class="form-item">
          <label for="">题型：</label>
          <n3-select v-model="questionType" @change="searchChange">
            <n3-option value="0">单选题</n3-option>
            <n3-option value="1">多选题</n3-option>
            <n3-option value="2">填空题</n3-option>
          </n3-select>
        </div>
        <div class="form-item">
          <label for="">排序方式：</label>
          <n3-select v-model="queryType" @change="searchChange">
            <n3-option value="0">最新</n3-option>
            <n3-option value="1">错误率</n3-option>
          </n3-select>
        </div>

      </div>
      <div class="search-submit" style="float: right;width: 100px; margin-right: 50px">
        <n3-button block @click.native="search">新建题目</n3-button>
      </div>
      <div class="search-submit" style="float: left;width: 100px; margin-left: 10px;margin-top: 4px">
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
    >
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

  export default {
    data () {
      return {
        subjectList: [],
        questionList: [],
        loading: false,
        searchChanged: false,
        subjectId: 0,
        queryType: 0,
        questionType: 0,
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
        pagination: {
          current: 1,
          total: 0,
          size: 20
        },
        columns: [
          {
            title: 'ID',
            dataIndex: 'queryRecordId',
            width: '50px',
            render: (text, record, index) => {
              return `<div>${record.id}</div>`
            }
          }, {
            title: '题目描述',
            dataIndex: 'queryDate',
            width: '300px',
            render: (text, record, index) => {
              return `<div>${record.content}</div>`
            }
          },
          {
            title: '科目',
            dataIndex: 'queryType',
            width: '120px',
            render: (text, record, index) => {
              var subjectId = this.getSubjectName(record.subjectId)
              return `<div>${subjectId}</div>`
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
            width: '120px',
            render: (text, record, index) => {
              var rate = record.correctNum / record.counts * 100
              rate = rate.toFixed(2);
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
        this.searchRecord()
      },
      search () {
        var url = '/api/question/list?subjectId=' + this.subjectId + '&type=' + this.questionType
        this.$axios.get(url
        ).then(response => {
          this.questionList = response.data.data.questionList
          this.pagination.total = response.data.data.total
          this.pagination.current = response.data.data.page
          this.pagination.size = response.data.data.size
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
          this.subjectId = response.data.data[0].id
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
        this.pagination.total = response.data.data.total
        this.pagination.current = response.data.data.page
        this.pagination.size = response.data.data.size
        this.questionType = '0'
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
      }
    },
    watch: {
      '$route' () {
        if (this.$route.name === 'question') {
          this.reload()
        }
      }
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

