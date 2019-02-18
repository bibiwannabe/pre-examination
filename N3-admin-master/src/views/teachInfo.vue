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
          :custom-validate="usernameValidate"
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
          :custom-validate="passwordValidate"
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
          :custom-validate="phoneValidate"
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
        this.$axios.get('/api/admin_info/info'
        ).then(response => {
          result = JSON.stringify(response.data.code)
          this.title = JSON.stringify(response.data.data.title).replace('"', '').replace('"', '')
          this.subject = JSON.stringify(response.data.data.subject).replace('"', '').replace('"', '')
          this.userId = JSON.stringify(response.data.data.userId)
          this.id = JSON.stringify(response.data.data.id)
          this.teachYear = JSON.stringify(response.data.data.teachYear)
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
      addUser () {
        let cond = Object.assign({}, this.model)
        // cond.expireDate = new Date(cond.expireDate).valueOf()
        this.loading = true
        this.$http.post(API.USER_ADD, qs.stringify(cond))
          .then(data => {
            this.loading = false
            this.n3Alert({
              content: '添加成功~',
              type: 'success',
              placement: 'top-right',
              duration: 2000,
              width:'240px' // 内容不确定，建议设置width
            })
            this.$router.push('/table/')
          })
          .catch(error => {
            this.loading = false
            this.n3Alert({
              content: '添加失败，请刷新重试~',
              type: 'danger',
              placement: 'top-right',
              duration: 2000,
              width: '240px' // 内容不确定，建议设置width
            })
          })
      },
      submit () {
        var result = ''
        var msg = ''
        this.$axios({
          method: 'post',
          url: '/api/admin_info/create',
          crossDomain: true,
          data: JSON.stringify({teachYear: this.teachYear, title: this.title, subject: this.subject}),
          contentType: 'application/json'
        }).then(response => {
          result = JSON.stringify(response.data.code)
          if (result !== '1000') {
            msg = JSON.stringify(response.data.message)
            alert(msg)
          }
          if (result === '1000') {
            alert('修改成功')
          }
        }).catch((error) => {
          alert('登录失败' + error.toString())
          return
        })
      },
      phoneValidate (val) {
        if (/^\d{11}$/.test(val)) {
          return {
            validStatus: 'success'
          }
        } else {
          return {
            validStatus: 'error',
            tips: '请输入11位手机号'
          }
        }
      },
      usernameValidate (val) {
        if (val && val.length > 5 && val.length < 19) {
          return {
            validStatus: 'success'
          }
        } else {
          return {
            validStatus: 'error',
            tips: '账户名长度为6-18位'
          }
        }
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
