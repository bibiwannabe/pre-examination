<template>
  <section class="history-list">
    <div class="search-bar">
      <div class="search-item" style="float: left">
        <div class="form-item">
          <label for="">科目名称：</label>
          <n3-input v-model="subjectName"></n3-input>
        </div>
      </div>
      <div class="search-submit" style="float: left;width: 200px">
        <n3-button type="primary" block @click.native="addSubject">新增</n3-button>
      </div>
    </div>
    <n3-data-table
      :selection="selection"
      :source="subjectList"
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
    data () {
      return {
        subjectList: [],
        subjectName: '',
        loading: false,
        searchChanged: false,
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
            dataIndex: 'id',
            width: '120px',
            render: (text, record, index) => {
              return `<div>{{'${record.id}'}}</div>`
            }
          }, {
            title: '科目名字',
            dataIndex: 'subjectName',
            width: '120px',
            render: (text, record, index) => {
              return `<div>{{'${record.subjectName}'}}</div>`
            }
          }
        ],
      }
    },
    methods: {
      addSubject() {
        if (this.subjectName === '') {
          alert('请输入正确的科目名称')
          return
        } else {
          var msg = ''
          var result = '0'
          this.$axios({
            method: 'post',
            url: '/api/subject/create',
            crossDomain: true,
            data: JSON.stringify({subjectName: this.subjectName}),
            contentType: 'application/json'
          }).then(response => {
            result = JSON.stringify(response.data.code)
            if (result !== '1000') {
              msg = JSON.stringify(response.data.message)
              alert(msg)
            }else {
              alert('创建科目成功')
              this.reload()
            }
          }).catch((error) => {
            alert('创建失败' + error.toString())
            return
          })
        }
      },
      reload () {
        this.$axios.get('/api/subject/list'
        ).then(response => {
          this.subjectList = response.data.data
        }).catch((error) => {
          alert('获取信息失败' + error.toString())
        })
        this.loading = false
      }
    },
    watch: {
      '$route' () {
        if (this.$route.name === 'subject') {
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

