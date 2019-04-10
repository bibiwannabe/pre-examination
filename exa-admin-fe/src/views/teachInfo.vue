<template>
  <section class="user-edit">
    <n3-form
      ref="form"
      validate
    >
      <n3-form-item
        label="职称"
        need
        :label-col="3"
      >
        <n3-input
          :rules="[{type:'required'}]"
          v-model="title"
          width="320px"
        >{{title}}
        </n3-input>
      </n3-form-item>
      <n3-form-item
        label="方向"
        need
        :label-col="3"
      >
        <n3-input
          v-model="subject"
          width="320px"
          :rules="[{type:'required'}]"
          class="fl"
        >{{subject}}
        </n3-input>
      </n3-form-item>
      <n3-form-item
        label="教龄"
        need
        :label-col="3"
      >
        <n3-input
          :rules="[{type:'required'}]"
          v-model="teachYear"
          width="320px"
          type="number"
        >{{teachYear}}
        </n3-input>
      </n3-form-item>
      <n3-form-item
        :label-col="3"
      >
        <n3-button
          @click.native="submit"
          type="primary"
          :loading="loading"
          class="submit-btn"
        >
          {{ loading ? '操作中' : '保存' }}
        </n3-button>
      </n3-form-item>
    </n3-form>
  </section>
</template>

<script>
  import API from '../api'
  import qs from 'qs'
  import { mapState } from 'vuex'
  import axios from 'axios'

  export default {
    computed: {
      ...mapState(['user'])
    },
    data () {
      return {
        title: '',
        subject: '',
        teachYear: 0,
        userId: 0,
        id: 0,
        model: {
          username: '',
          password: '',
          phone: '',
          priority: 1,
          limitType: '1',
          cacheExpireTime: '24',
        },
        loading: false
      }
    },
    methods: {

      reload () {
        var result = ''
        var msg = ''
        this.$axios.get('/admin-api-1.4.5/admin_info/info'
        ).then(response => {
          result = response.data.code
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
          this.title = response.data.data.title
          this.subject = response.data.data.subject
          this.userId = response.data.data.userId
          this.id = response.data.data.id
          this.teachYear = response.data.data.teachYear
        }).catch((error) => {
          this.alert('发送失败' + error.toString())
          return
        })
        // 重置表单
        this.model = {
          username: '',
          password: '',
          phone: '',
          priority: 1,
          limitType: '1',
          cacheExpireTime: '24'
        }
        this.loading = false
      },
      submit () {
        var result = ''
        var msg = ''
        this.$axios({
          method: 'post',
          url: '/admin-api-1.4.5/admin_info/create',
          crossDomain: true,
          data: JSON.stringify({teachYear: this.teachYear, title: this.title, subject: this.subject}),
          contentType: 'application/json'
        }).then(response => {
          result = JSON.stringify(response.data.code)
          if (result !== '1000') {
            msg = response.data.message
            this.n3Alert({
              content: msg,
              type: 'success',
              placement: 'center',
              duration: 2000,
              width: '240px'
            })
          }
          if (result === '1000') {
            this.n3Alert({
              content: '修改成功',
              type: 'success',
              placement: 'center',
              duration: 2000,
              width: '240px'
            })
          }
        }).catch((error) => {
          alert('修改失败' + error.toString())
          return
        })
      }
    },
    watch: {
      '$route' () {
        if (this.$route.name === 'teachInfo') {
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
  .user-edit {
    background: #fff;
    .submit-btn {
      width: 320px;
    }
    .refresh {
      cursor: pointer;
    }
    .i-tips {
      float: left;
      padding-left: 10px;
      color: #999;
    }
  }
</style>
