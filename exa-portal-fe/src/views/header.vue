<template>
  <div class="app-header clearfix" style="height: 50px;">
    <n3-column :col="0" class="logo-field" style="padding-top: 0px" >
      <img v-on:click="goIndex" src="../assets/logo.png" class="logo" style="height: 25px; width: 25px">
      <p v-on:click="goIndex" style="font-size: 20px; color: #5e5e5e; margin-top: -5px;" @click.native="goIndex">考前辅导</p>
    </n3-column>
    <n3-column class="logout-field" style="margin-right: 40px;padding-top: 0px">
      <div v-if="this.$root.userId == 0">
        <n3-tooltip class="tip-logout" content="未登录，点击登录" placement="bottom" trigger="hover">
          <n3-icon size="25px" type="sign-in" @click.native="login"></n3-icon>
        </n3-tooltip>
      </div>
      <div v-if="this.$root.userId != 0">
        <n3-popover :header="false" placement="bottom" style="position: fixed">
          <div slot="content" @click="">
            <tr>
              <td>
                <n3-icon size="15px" type="edit"></n3-icon>
              </td>
              <td>
                <n3-button style="border-color: white" @click.native="getInfo">账户信息</n3-button>
              </td>
            </tr>
          </div>
          <div slot="content" @click="">
            <tr>
              <td>
                <n3-icon size="15px" type="pencil"></n3-icon>
              </td>
              <td>
                <n3-button style="border-color: white" @click.native="goPractice">做题记录</n3-button>
              </td>
            </tr>
          </div>
          <div slot="content" @click="">
            <tr>
              <td><n3-icon size="15px" type="tasks"></n3-icon></td>
              <td><n3-button style="border-color: white" @click.native="goPracticeSubject">错题训练</n3-button></td>
            </tr>
          </div>
          <div slot="content" @click="">
            <tr>
              <td><n3-icon size="15px" type="sign-out"></n3-icon></td>
              <td><n3-button style="border-color: white" @click.native="logout">退出登录</n3-button></td>
            </tr>
          </div>
          <n3-icon size="25px" type="user" color="#666666"><p style="float: right; width: 60px; font-size: 14px; line-height: 25px;margin-left: 10px">个人中心</p></n3-icon>
        </n3-popover>
      </div>
    </n3-column>
  </div>
</template>

<script>
  import {mapActions} from 'vuex'
  import Vue from 'vue'
  import axios from 'axios'

  export default {
    data () {
      return {
        userId: 0
      }
    },
    methods: {
      ...mapActions(['logout']),
      goPracticeSubject () {
        this.$router.push({
          name: 'practiceSubject'
        })
      },
      login () {
        alert(this.$root.userId)
        this.$router.push({
          name: 'login'
        })
      },
      goIndex () {
        this.$router.push({
          name: 'index'
        })
      },
      getInfo () {
        this.$router.push({name: 'account', params: {id: this.$userId}})
      },
      goPractice () {
        this.$router.push({name: 'practiceRecord'})
      },
      logout () {
        var msg = ''
        var result = 0
        axios({
          method: 'post',
          url: '/portal-api-1.1.1/user/logout',
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
      },
      reload () {
        var result = 0
        axios.get(
          '/portal-api-1.1.1/user/myInfo'
        ).then(response => {
          result = response.data.code
          if (result === 1000) {
            this.userId = response.data.data.id
            this.username = response.data.data.name
            this.email = response.data.data.email
          }
          if (result === 1002) {
            this.$router.push({name: 'login'})
          }
        }).catch((error) => {
          alert('获取信息失败' + error.toString())
          return
        })
      }
    },
    watch: {
      '$route' () {
        if (this.$route.name === 'index') {
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
  .app-header {
    height: 60px;
    line-height: 60px;
    background: white;
    color: #c0ccda;
    display: flex;
    width: 100%;
    z-index: 3;
    box-shadow: 0px 0px 5px #dadada;
    position:fixed;

    .fixed {
      position: absolute;
      top: 0px;
      bottom: 0px;
      width: 100%;
      position:fixed;
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
      width: 80px;
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
