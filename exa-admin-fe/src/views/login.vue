<template>
  <section class="login-wrap">
    <n3-form ref='form' class="login-form">
      <h3>考前辅导系统后台系统登录</h3>
      <div class="fields">
        <n3-input class="field" v-model="email" placeholder="账号" width="320px">
        </n3-input>
        <n3-input class="field" v-model="password" type="password" placeholder="密码" width="320px"
                  @keyup.native.enter="check">
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
          {{ loading ? '登录中' : '登录' }}
        </n3-button>
      </div>
      <div class="submit">
        <n3-button
          @click.native="goRegister"
          :loading="loading"
          :disabled="loading"
          block
        >
          {{ loading ? '' : '去注册' }}
        </n3-button>
      </div>
    </n3-form>
    <canvas id="J_loginBackground"></canvas>
  </section>
</template>

<script>
  import {mapActions} from 'vuex'

  // import register from 'register'

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

    function colorValue (min) {
      return Math.floor(Math.random() * 255 + min);
    }

    function createColorStyle (r, g, b) {
      return 'rgba(' + r + ',' + g + ',' + b + ', 0.8)';
    }

    function mixComponents (comp1, weight1, comp2, weight2) {
      return (comp1 * weight1 + comp2 * weight2) / (weight1 + weight2);
    }

    function averageColorStyles (dot1, dot2) {
      var color1 = dot1.color;

      var color2 = dot2.color;

      var r = mixComponents(color1.r, dot1.radius, color2.r, dot2.radius),
        g = mixComponents(color1.g, dot1.radius, color2.g, dot2.radius),
        b = mixComponents(color1.b, dot1.radius, color2.b, dot2.radius);
      return createColorStyle(Math.floor(r), Math.floor(g), Math.floor(b));
    }

    function Color (min) {
      min = min || 0;
      this.r = colorValue(min);
      this.g = colorValue(min);
      this.b = colorValue(min);
      this.style = createColorStyle(this.r, this.g, this.b);
    }

    function Dot () {
      this.x = Math.random() * canvas.width;
      this.y = Math.random() * canvas.height;

      this.vx = -.5 + Math.random();
      this.vy = -.5 + Math.random();

      this.radius = Math.random() * 2;

      this.color = new Color();
    }

    Dot.prototype = {
      draw: function () {
        ctx.beginPath();
        ctx.fillStyle = this.color.style;
        ctx.arc(this.x, this.y, this.radius, 0, Math.PI * 2, false);
        ctx.fill();
      }
    }

    function createDots () {
      for (i = 0; i < dots.nb; i++) {
        dots.array.push(new Dot());
      }
    }

    function moveDots () {
      for (i = 0; i < dots.nb; i++) {

        var dot = dots.array[i];

        if (dot.y < 0 || dot.y > canvas.height) {
          dot.vx = dot.vx;
          dot.vy = -dot.vy;
        } else if (dot.x < 0 || dot.x > canvas.width) {
          dot.vx = - dot.vx;
          dot.vy = dot.vy;
        }
        dot.x += dot.vx;
        dot.y += dot.vy;
      }
    }

    function connectDots () {
      for (i = 0; i < dots.nb; i++) {
        for (j = 0; j < dots.nb; j++) {
          i_dot = dots.array[i];
          j_dot = dots.array[j];

          if ((i_dot.x - j_dot.x) < dots.distance && (i_dot.y - j_dot.y) < dots.distance && (i_dot.x - j_dot.x) > -dots.distance && (i_dot.y - j_dot.y) > -dots.distance) {
            if ((i_dot.x - mousePosition.x) < dots.d_radius && (i_dot.y - mousePosition.y) < dots.d_radius && (i_dot.x - mousePosition.x) > -dots.d_radius && (i_dot.y - mousePosition.y) > -dots.d_radius) {
              ctx.beginPath();
              ctx.strokeStyle = averageColorStyles(i_dot, j_dot)
              ctx.moveTo(i_dot.x, i_dot.y);
              ctx.lineTo(j_dot.x, j_dot.y);
              ctx.stroke();
              ctx.closePath();
            }
          }
        }
      }
    }

    function drawDots () {
      for (i = 0; i < dots.nb; i++) {
        var dot = dots.array[i];
        dot.draw();
      }
    }

    function animateDots () {
      ctx.clearRect(0, 0, canvas.width, canvas.height);
      moveDots();
      connectDots();
      drawDots();

      requestAnimationFrame(animateDots);
    }

    canvas.onmousemove = function (e) {
      mousePosition.x = e.pageX;
      mousePosition.y = e.pageY;
    }

    canvas.onmouseleave = function (e) {
      mousePosition.x = canvas.width / 2;
      mousePosition.y = canvas.height / 2;
    }

    createDots();
    requestAnimationFrame(animateDots);
  }

  export default {
    data () {
      return {
        email: '',
        password: '',
        remember: [],
        loading: false
      }
    },
    // components:{register},
    methods: {
      ...mapActions(['login']),
      goRegister () {
        this.$router.replace({
          name: 'register'
        })
      },
      check () {
        if (!this.email) {
          return this.n3Alert({
            content: '请输入账号',
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
        var msg = ''
        var result = '0'
        this.$axios({
          method: 'post',
          url: '/admin-api-1.4.5/user/login',
          crossDomain: true,
          data: JSON.stringify({email: this.email, password: this.password}),
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
            var name = JSON.stringify(response.data.data.name)
            var email = JSON.stringify(response.data.data.email)
            var id = JSON.stringify(response.data.data.id)
            var accType = JSON.stringify(response.data.data.accType)
            var uuid = response.data.data.uid
            this.$userId = id
            this.$router.push({name: 'account', params: {name: name, email: email, id: id, accType: accType}})
            var exdate = new Date()
            exdate.setTime(exdate.getTime() + 24 * 60 * 60 * 1000 * 100)
            window.document.cookie = 'uuid' + '=' + uuid + ';path=/;expires=' + exdate.toGMTString()
          }
        }).catch((error) => {
          alert('登录失败' + error.toString())
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
  .login-wrap {
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

  .login-form {
    position: absolute;
    z-index: 1;
    top: 0;
    bottom: 0;
    left: 0;
    right: 0;
    margin: auto;
    padding: 16px 20px 0;
    width: 360px;
    height: 300px;
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
