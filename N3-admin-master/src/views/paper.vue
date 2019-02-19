<template>
  <section class="history-list">
    <div class="search-bar">
      <div class="search-item" style="float: left">
        <div class="form-item">
          <label for="">科目：</label>
          <select v-model="searchKey.subjectId" @change="keyChange" style="width: 80px;padding-left: 8px;padding-top: 5px; padding-bottom: 5px; border-color: #dddddd; background-color: white">
            <option :value="item.id" v-for="item in subjectList">{{item.subjectName}}</option>
          </select>
        </div>
        <div class="form-item">
          <label for="">排序方式：</label>
          <n3-select v-model="searchKey.queryType" @change="keyChange">
            <n3-option value="1" selected>最新</n3-option>
            <n3-option value="2">平均分</n3-option>
          </n3-select>
        </div>
      </div>
      <div class="search-submit" style="float: left;width: 200px">
        <n3-button type="primary" block @click.native="searchRecord">搜索</n3-button>
      </div>
    </div>
    <n3-data-table
      :selection="selection"
      :source="paperList"
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
        paperList: [],
        loading: false,
        searchChanged: false,
        searchKey: {
          subjectId: 0,
          queryType: '1',
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
        pagination: {
          current: 1,
          total: 0,
          pagesize: 20
        },
        columns: [
          {
            title: 'ID',
            dataIndex: 'queryRecordId',
            width: '100px',
            render: (text, record, index) => {
              var id = record.id
              return `<div>${id}</div>`
            }
          }, {
            title: '试卷标题',
            dataIndex: 'queryDate',
            width: '160px',
            render: (text, record, index) => {
              var name = record.name
              return `<div>${name}</div>`
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
            title: '平均分',
            dataIndex: 'userId',
            width: '120px',
            render: (text, record, index) => {
              var points = record.avgPoints
              return `<div>${points}</div>`
            }
          }, {
            title: '做题人数',
            dataIndex: 'ipAddress',
            width: '120px',
            render: (text, record, index) => {
              var counts = record.counts
              return `<div>${counts}</div>`
            }
          },
          {
            title: '创建时间',
            dataIndex: 'timeConsuming',
            width: '120px',
            render: (text, record, index) => {
              var createTime = new Date(record.createTime)
              return `<div>${createTime}</div>`
            }
          }, {
            title: '点击查看详情',
            dataIndex: 'queryRecordId',
            width: '120px',
            render: (text, record, index) => {
              return `<div class="search-submit" style="width: 60px; margin-left: 10px;margin-top: 4px">
                <n3-button  block @click.native="showDetail">详情</n3-button></div>`
            }
          }
        ],
        source: []
      }
    },
    methods: {
      pageChange(page) {
        this.pagination.current = page
        this.searchRecord()
      },
      keyChange () {
        this.searchChanged = true
      },
      searchRecord () {
        this.$axios.get('/api/paper/list?subjectId=' + this.searchKey.subjectId
        ).then(response => {
          this.paperList = response.data.data.paperList
          this.pagination.total = response.data.data.total
          this.pagination.current = response.data.data.page
          this.pagination.pagesize = response.data.data.size
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
        var result = ''
        var msg = ''
        this.$axios.get('/api/subject/list'
        ).then(response => {
          this.subjectList = response.data.data
          this.searchKey.subjectId = response.data.data[0].id
        }).catch((error) => {
          alert('获取信息失败' + error.toString())
        })
        this.$axios.get('/api/paper/list?subjectId=1'
        ).then(response => {
          result = JSON.stringify(response.data.code)
          this.paperList = response.data.data.paperList
          this.pagination.total = response.data.data.total
          this.pagination.current = response.data.data.page
          this.pagination.pagesize = response.data.data.size
        }).catch((error) => {
          this.alert('获取信息失败' + error.toString())
        })
        this.loading = false
      }
    },
    watch: {
      '$route' () {
        if (this.$route.name == 'normalTable') {
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

