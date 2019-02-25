<template>
  <div class="app-header clearfix">
    <n3-column :col="10" class="logo-field">
      <img src="../assets/logo.png" class="logo"> <span>考前辅导后台管理系统</span>
    </n3-column>
    <n3-column class="logout-field">
      <n3-tooltip class="tip-logout" content="登出" placement="bottom" trigger="hover">
        <n3-icon size="20px" type="sign-out" @click.native="submit"></n3-icon>
      </n3-tooltip>
    </n3-column>
  </div>
</template>

<script>
  import { mapActions } from 'vuex'
  export default {
    methods: {
      ...mapActions(['logout']),
      submit () {
        var msg = ''
        var result = 0
        this.$axios({
          method: 'post',
          url: '/api/user/logout',
          crossDomain: true,
          contentType: 'application/json'
        }).then(response => {
          result = response.data.code
          if (result !== 1000) {
            msg = response.data.message
            this.n3Alert({
              content: msg,
              type: 'success',
              placement: 'center',
              duration: 2000,
              width: '240px'
            })
          }
          if (result === 1000 || result === 1002) {
            this.$router.push({
              name: 'login'
            })
          }
        }).catch((error) => {
          alert('登录失败' + error.toString())
          return
        })
      }
    }
  }
</script>

<style lang="less">
  .app-header {
    height: 60px;
    line-height: 60px;
    background: #fff;
    color: #c0ccda;
    display: flex;
    position: fixed;
    width:100%;
    z-index:3;
    box-shadow: 0px 0px 5px #f9f9f9;
    .fixed {
      position: absolute;
      top: 0px;
      bottom: 0px;
      width: 100%;
    }
    .logo-field {
      flex: 1;
      font-size: 26px;
    }
    .logo {
      width: 40px;
      float: left;
      margin: 10px 10px 10px 18px;
    }
    .logout-field {
      width: 40px;
      padding-right: 20px;
      padding-top: 5px;
      i {
        cursor: pointer;
      }
    }
    .username {
      font-size: 18px;
    }
  }
</style>
