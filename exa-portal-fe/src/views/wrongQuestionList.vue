<template>
  <section class="router-view" :style="{ minHeight: screenHeight }">
    <div  style="padding-left: 140px; padding-right: 140px">
      <div v-for="(item, index) in questionList">
        <div v-if="item.type == 0">
          <n3-icon v-if="questionAnswerMap[item.id]!=null && questionAnswerMap[item.id]!= undefined && questionAnswerMap[item.id] != '' && item.answer != questionAnswerMap[item.id]"
                   size="20px" type="times" color="red" v-model="questionAnswerMap[item.id]"></n3-icon>
          <span style="font-size: 18px; line-height: 40px">{{index+1}}、{{item.content}}</span> <br/>
          <n3-radio-group v-model="questionAnswerMap[item.id]" type="primary"
                          @change="">
            <n3-radio v-for="option of JSON.parse(item.options)" :label="option.toString()"
                      style="float: none; font-size: 16px; width: 100%; height: 40px; margin-left: 20px">{{option}}
            </n3-radio>
          </n3-radio-group>
          <p v-if="questionAnswerMap[item.id]!=null && questionAnswerMap[item.id]!= undefined && questionAnswerMap[item.id] != ''"
          style="font-size: 18px">正确答案:{{item.answer}}</p>
        </div>
        <div v-if="item.type == 1">
          <n3-icon v-if="isCorrect(item.id, item.answer)"
                   size="20px" type="times" color="red" v-model="questionAnswerMap[item.id]"></n3-icon>
          <span style="font-size: 18px; line-height: 40px">{{index+1}}、{{item.content}}</span> <br/>
          <n3-checkbox-group v-model="questionAnswerMap[item.id]" @change="">
            <n3-checkbox v-for="option of JSON.parse(item.options)" :label="option"
                         style="float: none; font-size: 16px; width: 100%; height: 40px; margin-left: 20px">{{option}}</n3-checkbox>
          </n3-checkbox-group>
          <tr v-if="isCorrect(item.id, item.answer)">
            <td><p style="font-size: 18px">正确答案:&nbsp&nbsp</p></td>
            <td><span style="font-size: 18px" v-for="answer of JSON.parse(item.answer)">{{answer}}&nbsp&nbsp</span></td>
          </tr>
        </div>
        <div v-if="item.type == 2">
          <n3-icon v-if="questionAnswerMap[item.id]!=null && questionAnswerMap[item.id]!= undefined && questionAnswerMap[item.id] != '' && item.answer != questionAnswerMap[item.id]"
                   size="20px" type="times" color="red" v-model="questionAnswerMap[item.id]"></n3-icon>
          <span style="font-size: 18px; line-height: 40px">{{index + 1}}、 {{item.content.split('()')[0]}}</span>
          <n3-input v-model="questionAnswerMap[item.id]"></n3-input>
          <span v-if="item.content.split('()').length > 1" style="font-size: 18px; line-height: 40px">{{item.content.split('()')[1]}}</span>
          <p v-if="questionAnswerMap[item.id]!=null && questionAnswerMap[item.id]!= undefined && questionAnswerMap[item.id] != ''"
             style="font-size: 18px">正确答案:{{item.answer}}</p>
        </div>
      </div>
    </div>
    <div style="width: 40%; height: 100px; margin-top: 60px; margin-left: auto; margin-right: auto">
        <n3-button style="width: 200px; float: left" block @click.native="goIndex">返回主页</n3-button>
      </div>
    <div style="width: 100%; height: 180px; float: left">
      <div style="height: 100px; margin-top: 40px">
        <p style="color: #999999; text-align:center;line-height: 30px; font-size: 14px; float: none">邮箱：bibiwnanabe@foxmail.com</p>
        <p style="color: #999999; text-align:center;line-height: 30px; font-size: 14px; float: none">©️ 2019 黎碧怡 大连理工大学 软件学院</p>
      </div>
    </div>
  </section>
</template>
<script>
  import API from '../api'

  export default {
    computed: {
      screenHeight () {
        return window.innerHeight + 'px'
      }
    },
    data () {
      return {
        questionList: [],
        subjectName: '',
        loading: false,
        searchChanged: false,
        questionAnswerMap: {}
      }
    },
    methods: {
      isCorrect (id, answer) {
        var resultAnswer = new Set(this.questionAnswerMap[id])
        var correctAnswer = new Set(JSON.parse(answer))
        if (resultAnswer != null && JSON.stringify(this.questionAnswerMap[id]) !== '[]' && !this.eqSet(resultAnswer, correctAnswer)) {
          return true
        } else {
          return false
        }
      },
      eqSet (as, bs) {
        if (as.size !== bs.size) return false
        for (var a of as) {
          if (!bs.has(a)) return false
        }
        return true
      },
      pageChange () {
        this.reload()
      },
      reload () {
        this.$axios.get('/portal-api-1.1.1/question/list?subjectId=' + this.$route.params.subjectId
        ).then(response => {
          if (response.data.code !== 1000) {
            this.n3Alert({
              content: '未知错误',
              type: 'danger',
              placement: 'center',
              duration: 2000,
              width: '240px'
            })
            return
          }
          this.questionList = response.data.data
        }).catch((error) => {
          alert('获取信息失败' + error.toString())
        })
        this.loading = false
      },
      goIndex () {
        this.$router.push({name: 'index'})
      }
    },
    watch: {
      '$route' () {
        if (this.$route.name === 'questionList') {
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

