<template>
  <section class="history-list">
    <div class="search-bar">
      <div class="search-item" style="float: left">
        <div class="form-item">
          <label for="">科目：</label>
          <n3-select  v-model="searchKey.subjectId" v-bind:options="subjectNameList"></n3-select>
        </div>
      </div>
      <div class="search-submit" style="float: left;width: 100px; margin-top: 3px">
        <n3-button type="primary" block @click.native="searchRecord">搜索</n3-button>
      </div>
      <div class="search-submit" style="float: right;width: 100px; margin-right: 50px">
        <n3-button block @click.native="createPaper">新建试卷</n3-button>
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
        subjectNameList: [],
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
              if (record.counts === 0) {
                return `<div>暂无数据</div>`
              }
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
              var id = record.id
              return `<div class="search-submit" style="width: 60px; margin-left: 10px;margin-top: 4px">
                <n3-button  block @click.native="showDetail(${id})">详情</n3-button></div>`
            }
          }
        ],
        source: []
      }
    },
    methods: {
      showDetail (id) {
        this.$router.push({name: 'paperInfo', params: {id: id}})
      },
      pageChange (page) {
        this.pagination.current = page
        this.searchRecord()
      },
      keyChange () {
        this.searchChanged = true
      },
      searchRecord () {
        this.$axios.get('/admin-api-1.4.5/paper/list?subjectId=' + this.searchKey.subjectId + '&page=' + this.pagination.current
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
        this.$axios.get('/admin-api-1.4.5/subject/list'
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
          this.subjectNameList = []
          for (var subject of this.subjectList) {
            this.subjectNameList.push({value: subject.id, label: subject.subjectName})
          }
        }).catch((error) => {
          alert('获取信息失败' + error.toString())
        })
        this.$axios.get('/admin-api-1.4.5/paper/list?subjectId=1'
        ).then(response => {
          result = response.data.code
          this.paperList = response.data.data.paperList
          this.pagination.total = response.data.data.total
          this.pagination.current = response.data.data.page
          this.pagination.pagesize = response.data.data.size
        }).catch((error) => {
          this.alert('获取信息失败' + error.toString())
        })
        this.loading = false
      },
      createPaper () {
        this.$router.push({name: 'createPaper'})
      }
    },
    watch: {
      '$route' () {
        if (this.$route.name === 'paper') {
          this.reload()
        }
      },
      'subjectList' () {
        if (this.subjectList.length === 0) {
          this.$axios.get('/admin-api-1.4.5/subject/list'
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
            this.searchKey.subjectId = response.data.data[0].id
          }).catch((error) => {
            alert('获取信息失败' + error.toString())
          })
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

