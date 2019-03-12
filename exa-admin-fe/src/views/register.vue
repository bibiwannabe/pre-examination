<template>
  <section class="register-wrap">
    <n3-form ref='form' class="register-form">
      <h3>考前辅导系统后台注册</h3>
      <div class="fields">
        <n3-input class="field" v-model="email" placeholder="邮箱" width="320px"/>
        <n3-input class="field" v-model="code" placeholder="发送验证码" width="210px" style="float:left">
        </n3-input>
        <n3-button @click.native="checkEmail"
          type="primary" style="margin-left:15px;margin-top: 5px"  width="150px" :disabled="sent" v-model="totalTime">
          {{ sent ? totalTime+'s后重发' : '发送' }}
        </n3-button>
        <n3-input class="field" v-model="password" type="password" placeholder="密码" width="320px"  @keyup.native.enter="check">
        </n3-input>
        <n3-input class="field" v-model="confirmPassword" type="password" placeholder="确认密码" width="320px"  @keyup.native.enter="check">
        </n3-input>
      </div>
      <div class="submit">
        <n3-button
          @click.native="check"
          type="primary"
          :loading="loading"
          :disabled="loading"
          block
        >
          {{ loading ? '' : '注册' }}
        </n3-button>
      </div>
      <div class="submit">
        <n3-button
          @click.native="goLogin"
          :loading="loading"
          :disabled="loading"
          block
        >
          {{ loading ? '' : '去登录' }}
        </n3-button>
      </div>
    </n3-form>
    <canvas id="J_loginBackground"></canvas>
  </section>
</template>

<script>
  import { mapActions } from 'vuex'

  function render () {
    var canvas = document.querySelector('#J_loginBackground');
    if (!canvas) {
      return;
    }
    var ctx = canvas.getContext('2d')

    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;
    ctx.lineWidth = .3;
    ctx.strokeStyle = (new Color(150)).style;

    var i, j, i_dot, j_dot;

    var mousePosition = {
      x: 30 * canvas.width / 100,
      y: 30 * canvas.height / 100
    };

    var dots = {
      nb: 750,
      distance: 50,
      d_radius: 100,
      array: []
    };

    function colorValue(min) {
      return Math.floor(Math.random() * 255 + min);
    }

    function createColorStyle(r,g,b) {
      return 'rgba(' + r + ',' + g + ',' + b + ', 0.8)';
    }

    function mixComponents(comp1, weight1, comp2, weight2) {
      return (comp1 * weight1 + comp2 * weight2) / (weight1 + weight2);
    }

    function averageColorStyles(dot1, dot2) {
      var color1 = dot1.color,
        color2 = dot2.color;

      var r = mixComponents(color1.r, dot1.radius, color2.r, dot2.radius),
        g = mixComponents(color1.g, dot1.radius, color2.g, dot2.radius),
        b = mixComponents(color1.b, dot1.radius, color2.b, dot2.radius);
      return createColorStyle(Math.floor(r), Math.floor(g), Math.floor(b));
    }

    function Color(min) {
      min = min || 0;
      this.r = colorValue(min);
      this.g = colorValue(min);
      this.b = colorValue(min);
      this.style = createColorStyle(this.r, this.g, this.b);
    }

    function Dot(){
      this.x = Math.random() * canvas.width;
      this.y = Math.random() * canvas.height;

      this.vx = -.5 + Math.random();
      this.vy = -.5 + Math.random();

      this.radius = Math.random() * 2;

      this.color = new Color();
    }

    Dot.prototype = {
      draw: function() {
        ctx.beginPath();
        ctx.fillStyle = this.color.style;
        ctx.arc(this.x, this.y, this.radius, 0, Math.PI * 2, false);
        ctx.fill();
      }
    };

    function createDots(){
      for(i = 0; i < dots.nb; i++) {
        dots.array.push(new Dot());
      }
    }

    function moveDots() {
      for(i = 0; i < dots.nb; i++) {

        var dot = dots.array[i];

        if(dot.y < 0 || dot.y > canvas.height) {
          dot.vx = dot.vx;
          dot.vy = - dot.vy;
        }
        else if(dot.x < 0 || dot.x > canvas.width) {
          dot.vx = - dot.vx;
          dot.vy = dot.vy;
        }
        dot.x += dot.vx;
        dot.y += dot.vy;
      }
    }

    function connectDots() {
      for(i = 0; i < dots.nb; i++) {
        for(j = 0; j < dots.nb; j++) {
          i_dot = dots.array[i];
          j_dot = dots.array[j];

          if((i_dot.x - j_dot.x) < dots.distance && (i_dot.y - j_dot.y) < dots.distance && (i_dot.x - j_dot.x) > - dots.distance && (i_dot.y - j_dot.y) > - dots.distance){
            if((i_dot.x - mousePosition.x) < dots.d_radius && (i_dot.y - mousePosition.y) < dots.d_radius && (i_dot.x - mousePosition.x) > - dots.d_radius && (i_dot.y - mousePosition.y) > - dots.d_radius){
              ctx.beginPath();
              ctx.strokeStyle = averageColorStyles(i_dot, j_dot);
              ctx.moveTo(i_dot.x, i_dot.y);
              ctx.lineTo(j_dot.x, j_dot.y);
              ctx.stroke();
              ctx.closePath();
            }
          }
        }
      }
    }

    function drawDots() {
      for(i = 0; i < dots.nb; i++) {
        var dot = dots.array[i];
        dot.draw();
      }
    }

    function animateDots() {
      ctx.clearRect(0, 0, canvas.width, canvas.height);
      moveDots();
      connectDots();
      drawDots();

      requestAnimationFrame(animateDots);
    }

    canvas.onmousemove = function(e) {
      mousePosition.x = e.pageX;
      mousePosition.y = e.pageY;
    };

    canvas.onmouseleave = function(e) {
      mousePosition.x = canvas.width / 2;
      mousePosition.y = canvas.height / 2;
    };

    createDots();
    requestAnimationFrame(animateDots);
  }

  export default {
    data () {
      return {
        totalTime: 60,
        email: '',
        code: '',
        confirmPassword: '',
        password: '',
        remember: [],
        loading: false,
        sent: false,
        emailParam: new this.EmailParam(this.email)
      }
    },
    methods: {
      ...mapActions(['register']),
      EmailParam (email) {
         this.email = email
      },
    create () {
        render()
    },
      checkEmail () {
        if (!this.email) {
          return this.n3Alert({
            content: '请输入正确邮箱',
            type: 'success',
            placement: 'center',
            duration: 2000,
            width: '240px'
          })
        }
        var msg = '该邮箱已被注册'
        var result = '0'
        this.$axios('/admin-api-1.4.5/user/checkEmailExist',
            {params: {email: this.email}}
          ).then(response => {
            result = JSON.stringify(response.data.code)
          if (result == 1001) {
            msg = '该邮箱已被注册'
            this.n3Alert({
              content: msg,
              type: 'success',
              placement: 'center',
              duration: 2000,
              width:'240px' // 内容不确定，建议设置width
            })
          } else if (result == 1000) {
            this.sendCode()
          }
          }).catch((error) => {
            this.alert('发送失败' + error.toString())
            return
          })
      },
      countDown () {
        if (this.totalTime > 0) {
          this.totalTime = this.totalTime - 1
          setTimeout(this.countDown, 1000)
        } else {
          this.sent = false
          this.totalTime = 60
        }
      },
      sendCode () {
        var result = 0
        this.$axios({
          method: 'post',
          url: '/admin-api-1.4.5/user/sendCode',
          crossDomain: true,
          data: JSON.stringify({email: this.email}),
          contentType: 'application/json'
        }).then(response => {
          result = response.data.code
          if (result === 1000) {
            this.n3Alert({
              content: '发送成功',
              type: 'success',
              placement: 'center',
              duration: 2000,
              width: '240px'
            })
            this.sent = true
            this.countDown()
          } else {
            result = response.data.message
            this.n3Alert({
              content: result,
              type: 'success',
              placement: 'center',
              duration: 2000,
              width: '240px'
            })
          }
        }).catch((error) => {
          alert('发送失败' + error.toString())
        })
      },
      goLogin () {
        this.$router.replace({
          name: 'login'
        })
      },
      check () {
        if (!this.email) {
          return this.n3Alert({
            content: '请输入正确邮箱',
            type: 'success',
            placement: 'top-right',
            duration: 2000,
            width: '240px'
          })
        }
        if (!this.code) {
          return this.n3Alert({
            content: '请输验证码',
            type: 'success',
            placement: 'top-right',
            duration: 2000,
            width: '240px'
          })
        }
        if (!this.password) {
          return this.n3Alert({
            content: '请输入密码',
            type: 'success',
            placement: 'top-right',
            duration: 2000,
            width: '240px'
          })
        }
        if (!this.confirmPassword) {
          return this.n3Alert({
            content: '请确认密码',
            type: 'success',
            placement: 'top-right',
            duration: 2000,
            width: '240px'
          })
        }
        if (this.confirmPassword !== this.password) {
          return this.n3Alert({
            content: '两次密码不正确',
            type: 'success',
            placement: 'top-right',
            duration: 2000,
            width: '240px'})
        }
        this.submit()
      },
      submit () {
        var msg = ''
        var result = '0'
        this.$axios({
          method: 'post',
          url: '/admin-api-1.4.5/user/register',
          crossDomain: true,
          data: JSON.stringify({email: this.email, password: this.password, code: new Number(this.code)}),
          contentType: 'application/json'
        }).then(response => {
          result = JSON.stringify(response.data.code)
          if (result !== '1001') {
            msg = response.data.message
            this.n3Alert({
              content: msg,
              type: 'success',
              placement: 'center',
              duration: 2000,
              width: '240px' // 内容不确定，建议设置width
            })
          }
          if (result === '1000') {
            this.$router.replace({name: 'login'})
          }
        }).catch((error) => {
          this.alert('注册失败' + error.toString())
          return
        })
      }
    },
    created () {
    },
    mounted () {
      render()
    }
  }
</script>

<style lang="less">
  .register-wrap {
    position: fixed;
    z-index: 0;
    top: 0;
    left: 0;
    overflow: hidden;
    width: 100%;
    height: 100%;
    background: #111;
    canvas {
      width: 100%;
      height: 100%;
      position: absolute;
    }
  }
  .register-form {
    position: absolute;
    z-index: 1;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    margin: auto;
    padding: 16px 20px 0;
    width: 360px;
    height: 400px;
    font-size: 14px;
    background: #fff;
    border: 1px solid #ccc;
    border-radius: 12px;
    box-shadow: 2px 2px 32px 1px rgba(0, 0, 0, .45);
    opacity: .75;
    h3 {
      margin-top: 0;
      margin-bottom: 0;
      padding: 12px 0;
      font-weight: normal;
      font-size: 22px;
      text-align: center;
    }
    .field {
      display: block;
      margin: 0 auto;
      padding: 6px 0;
    }
    .submit {
      padding: 12px 0;
    }
  }
</style>

