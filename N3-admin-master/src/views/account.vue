<template>
  <section class="user-edit">
    <n3-form
      ref="form"
      validate
    >
      <n3-form-item
        label="用户名"
        need
        :label-col="3"
      >
        <n3-input
          :rules="[{type:'required'}]"
          v-model="username"
          width="320px"
          :custom-validate="usernameValidate"
        >{{username}}
        </n3-input>
      </n3-form-item>
      <n3-form-item
        label="邮箱"
        need
        :label-col="3"
      >
        <n3-input
          v-model="email"
          width="320px"
          :rules="[{type:'required'}]"
          :custom-validate="passwordValidate"
          class="fl" readonly=true
        >{{email}}
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

  export default {
    computed: {
      ...mapState(['user'])
    },
    data () {
      return {
        username: this.$route.params.name.replace('"', '').replace('"', ''),
        email: this.$route.params.email.replace('"', '').replace('"', ''),
        loading: false
      }
    },
    methods: {
      reload () {
        var msg = ''
        var result = '0'
        this.$axios({
          method: 'post',
          url: '/api/user/userInfo',
          crossDomain: true,
          data: JSON.stringify({email: this.email, password: this.password}),
          contentType: 'application/json'
        }).then(response => {
          result = JSON.stringify(response.data.code)
          if (result === '1000') {
            this.username = JSON.stringify(response.data.data.name)
            this.email = JSON.stringify(response.data.data.email)
          }
        }).catch((error) => {
          this.alert('登录失败' + error.toString())
          return
        })
        // 重置表单
        this.model = {
          username: '',
          password: ''
        }
        this.loading = false
      },
      submit () {
        this.$refs.form.validateFields(result => {
          if (!result.isvalid) {
            return
          }
          return this.addUser()
        })
      },
      passwordValidate (val) {
        if (val && val.length > 5 && val.length < 19) {
          return {
            validStatus: 'success'
          }
        } else {
          return {
            validStatus: 'error',
            tips: '密码长度为6-18位'
          }
        }
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
        if (this.$route.name === 'account') {
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
