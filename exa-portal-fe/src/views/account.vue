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
          class="fl" v-bind:readonly="isReadonly"
        >{{email}}
        </n3-input>
      </n3-form-item>
      <n3-form-item
        :label-col="3"
      >
        <n3-button
          @click.native="submitUserInfo"
          type="primary"
          :loading="loading"
          class="submit-btn"
        >
          {{ loading ? '操作中' : '保存' }}
        </n3-button>
      </n3-form-item>
    </n3-form>
    <div style="width: auto; height: 100px">
      <div style="height: 60px; margin-top: 80px">
        <p style="color: #999999; text-align:center;line-height: 30px; font-size: 14px; float: none">邮箱：bibiwnanabe@foxmail.com</p>
        <p style="color: #999999; text-align:center;line-height: 30px; font-size: 14px; float: none">©️ 2019 黎碧怡 大连理工大学 软件学院</p>
      </div>
    </div>
  </section>
</template>

<script>
  import { mapState } from 'vuex'
  import header from './header'

  export default {
    computed: {
      ...mapState(['user'])
    },
    data () {
      return {
        username: '',
        email: '',
        loading: false,
        isReadonly: true
      }
    },
    methods: {
      reload () {
        var result = 0
        this.$axios.get(
          '/portal-api-1.1.1/user/myInfo'
        ).then(response => {
          result = response.data.code
          if (result === 1000) {
            this.$userId = response.data.data.id
            this.username = response.data.data.name
            this.email = response.data.data.email
          }
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
        }).catch((error) => {
          alert('获取信息失败' + error.toString())
          return
        })
      },
      submitUserInfo () {
        var result = 0
        this.$axios({
          method: 'post',
          url: '/portal-api-1.1.1/user/info',
          crossDomain: true,
          data: JSON.stringify({name: this.username}),
          contentType: 'application/json'
        }).then(response => {
          result = response.data.code
          if (result === 1000) {
            this.n3Alert({
              content: '修改成功',
              type: 'success',
              placement: 'center',
              duration: 2000,
              width: '240px'
            })
            this.reload()
          }
        }).catch((error) => {
          this.alert('修改失败' + error.toString())
          return
        })
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
