<template>
  <section class="history-list">
    <div class="search-bar">
      <div class="search-item" style="float: left">
        <div class="form-item">
          <label for="">科目：</label>
          <n3-input v-model="searchKey.userId" @change="searchChange"></n3-input>
        </div>
        <div class="form-item">
          <label for="">排序方式：</label>
          <n3-select v-model="searchKey.queryResult" @change="searchChange">
            <n3-option value="">最新</n3-option>
            <n3-option value="1">平均分</n3-option>
          </n3-select>
        </div>
      </div>
      <div class="search-submit" style="float: left;width: 200px">
        <n3-button type="primary" block @click.native="searchRecord">搜索</n3-button>
      </div>
    </div>
    <n3-data-table
      :selection="selection"
      :source="source"
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
    data() {
      return {
        loading: false,
        searchChanged: false,
        searchKey: {
          userId: '',
          queryResult: '',
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
            render: text => Date.now()
          }, {
            title: '操作时间',
            dataIndex: 'queryDate',
            width: '160px',
            render: (text, record, index) => {
              return `<div>{{'${text}' | toDateTime}}</div>`
            }
          }, {
            title: '操作用户',
            dataIndex: 'userId',
            width: '120px',
            render: (text, record, index) => {
              text = '测试用户'
              return `<router-link to="/user/${text}" target="_blank">
                        ${text}
                      </router-link>`
            }
          }, {
            title: 'IP',
            dataIndex: 'ipAddress',
            width: '160px',
            render: text => {
              return text || '127.0.0.1'
            }
          },
          {
            title: '类型',
            dataIndex: 'queryType',
            width: '40px',
            render: (text) => {
              if (text == 2) {
                return `<span>普通</span>`
              }
              return `<span style="color: red;">实时</span>`
            }
          }, {
            title: '耗时(ms)',
            dataIndex: 'timeConsuming',
            width: '100px'
          }, {
            title: '操作',
            dataIndex: 'queryRecordId',
            width: '120px',
            render: (text, record, index) => {
              let type = 'primary'
              if (!record.queryResult) {
                type = 'warning'
              }
              return `<router-link to="/record/${text}" target="_blank">
                        <n3-label type="${type}">详情</n3-label>
                      </router-link>`
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
      searchChange () {
        this.searchChanged = true
      },
      searchRecord () {
        if (this.searchChanged) {
          this.pagination.current = 1
          this.searchChanged = false
        }
        let params = Object.assign({}, this.searchKey, {
          page: this.pagination.current
        })
        if (params.queryResult === 1) {
          params.queryResult = true
        }
        if (params.queryResult === 0) {
          params.queryResult = false
        }
        Object.keys(params).forEach(key => {
          let item = params[key]
          if (item === '' || typeof item === 'undefined') {
            delete params[key]
          }
        })
        let url = API.RECORD_LIST
        if (Object.keys(params).length < 2) {
          url = API.QUERY_LIST
        }
        this.loading = true
        this.$http.get(url, {
          params
        }).then(data => {
          console.log(data)
          this.source = data.result.data || []
          this.pagination.total = data.result.total || 0
          this.loading = false
        }).catch(error => {
          this.loading = false
          this.n3Alert({
            content: error || '加载失败，请刷新试试~',
            type: 'danger',
            placement: 'top-right',
            duration: 2000,
            width: '240px' // 内容不确定，建议设置width
          })
        })
      },
      reload () {
        var result = ''
        var msg = ''
        this.$axios.get('/api/paper/list?subjectId=1'
        ).then(response => {
          result = JSON.stringify(response.data.code)
          this.title = JSON.stringify(response.data.data.title)
          this.subject = JSON.stringify(response.data.data.subject)
          this.userId = JSON.stringify(response.data.data.userId)
          this.id = JSON.stringify(response.data.data.id)
          this.teachYear = JSON.stringify(response.data.data.teachYear)
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

