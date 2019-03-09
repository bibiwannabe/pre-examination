<template>
  <section class="history-list">
    <n3-form ref="form" validate>
      <n3-form-item
        label="题目类型"
        :label-col="3"
      >
        <n3-input
          :rules="[{type:'required'}]"
          v-model="questionTypeName"
          width="320px"
          v-bind:readonly="isReadonly"
        >{{questionTypeName}}
        </n3-input>
      </n3-form-item>
      <n3-form-item
        label="科目"
        need
        :label-col="3">
        <select v-model="subject.id"
                style="width: 320px;padding-left: 8px;padding-top: 2px; padding-bottom: 2px; border-color: #dddddd; background-color: white">
          <option :value="item.id" v-for="item in subjectList">{{item.subjectName}}</option>
        </select>
      </n3-form-item>

      <n3-form-item label="题目" need :label-col="3">
        <n3-textarea
          :rules="[{type:'required'}]"
          v-model="content"
          width="320px"
        >{{content}}
        </n3-textarea>
      </n3-form-item>
      <n3-form-item label="选项" need :label-col="3">
        <tr v-for="(item,index) in options">
          <td>
            <n3-input :value="item"
                      width="320px"
                      style="margin-bottom: 10px">{{item}}
            </n3-input>
          </td>
          <td style="padding-left: 10px">
            <n3-button
              @click.native="deleteOption(index)"
              :loading="loading"
              class="submit-btn">
              删除选项
            </n3-button>
          </td>
        </tr>
        <tr>
          <n3-button
            @click.native="addOption"
            :loading="loading"
            class="submit-btn"
            style="width: 320px"
          >
            增加选项+
          </n3-button>
        </tr>
      </n3-form-item>
      <n3-form-item label="答案" need :label-col="3">
        <tr v-for="(item, index) in answers">
          <td>
            <select v-model="answers[index]"
                    style="width: 320px;padding-left: 8px;margin-bottom:10px; padding-top: 2px; padding-bottom: 2px; border-color: #dddddd; background-color: white">
              <option :value="item" v-for="item in options">{{item}}</option>
            </select>
          </td>
          <td style="padding-left: 10px">
            <n3-button
              @click.native="deleteAnswer(index)"
              :loading="loading"
              class="submit-btn">
              删除答案
            </n3-button>
          </td>
        </tr>
        <tr>
          <n3-button
            @click.native="addAnswer"
            :loading="loading"
            class="submit-btn"
            style="width: 320px"
          >
            增加答案+
          </n3-button>
        </tr>
      </n3-form-item>

      <n3-form-item
        :label-col="3"
      >
        <n3-button
          @click.native="submitQuestion"
          type="primary"
          :loading="loading"
          class="submit-btn"
          style="width: 120px"
        >
          {{ loading ? '操作中' : '保存' }}
        </n3-button>
        <n3-button
          @click.native="cancelAndReturn"
          :loading="loading"
          class="submit-btn"
          style="margin-left:80px;width: 120px"

        >
          取消并返回
        </n3-button>
      </n3-form-item>
    </n3-form>
  </section>
</template>
<script>
  import API from '../api'

  export default {
    data () {
      return {
        id: 0,
        isReadonly: true,
        isShow: true,
        subject: {
          id: 0,
          name: ''
        },
        answers: [],
        options: [],
        content: '',
        subjectList: [],
        loading: false,
        subjectId: 0,
        questionType: 0,
        questionTypeName: '',
        readOnly: true,
        subjectName: '',
        questionId: 0
      }
    },
    methods: {
      getSubjectName (id) {
        for (let subject of this.subjectList) {
          if (subject.id === id) {
            return subject.subjectName
          }
        }
        return '未知科目'
      },
      getQuestionTypeName () {
        return '多选题'
      },
      reload3 () {
        this.id = this.$route.params.id
        this.questionId = this.$route.params.id
        this.questionTypeName = this.getQuestionTypeName()
        var url = '/admin-api-1.4.5/question/' + this.$route.params.id
        this.$axios.get(url
        ).then(response => {
          var answerList = JSON.parse(response.data.data.answer)
          for (var i = 0; i < answerList.length; i++) {
            this.answers[i] = answerList[i]
          }
          this.$set(this.subject, 'id', response.data.data.subjectId)
          this.content = response.data.data.content
          var jsonObj = JSON.parse(response.data.data.options)
          for (var j = 0; j < jsonObj.length; j++) {
            this.options[j] = jsonObj[j]
          }
        }).catch((error) => {
          alert('获取信息失败' + error.toString())
        })
        this.$axios.get('/admin-api-1.4.5/subject/list'
        ).then(response => {
          this.subjectList = response.data.data
        }).catch((error) => {
          alert('获取信息失败' + error.toString())
        })
        this.loading = false
      },
      submitQuestion () {
        var data = JSON.stringify({
          id: this.id,
          subjectId: this.subject.id,
          content: this.content,
          answer: JSON.stringify(this.answers),
          options: JSON.stringify(this.options)
        })
        this.$axios({
          method: 'post',
          url: '/admin-api-1.4.5/question/update',
          crossDomain: true,
          data: data,
          contentType: 'application/json'
        }).then(response => {
          var result = response.data.code
          if (result !== 1000) {
            this.n3Alert({
              content: response.data.message,
              type: 'danger',
              placement: 'center',
              duration: 2000,
              width: '240px'
            })
            if (result === 1002) {
              this.$router.push({
                name: 'login'
              })
            }
          } else {
            this.n3Alert({
              content: '保存成功',
              type: 'success',
              placement: 'center',
              duration: 2000,
              width: '240px' // 内容不确定，建议设置width
            })
            this.reload3()
          }
        }).catch((error) => {
          alert('创建失败' + error.toString())
          return
        })
      },
      addOption () {
        this.options.push('')
      },
      deleteOption (id) {
        this.options.splice(id, 1)
      },
      addAnswer () {
        this.answers.push('')
      },
      deleteAnswer (id) {
        this.answers.splice(id, 1)
      },
      cancelAndReturn () {
        this.$router.go(-1)
      }
    },
    watch: {
      '$route' () {
        if (this.$route.name === 'selectionInfo') {
          this.reload3()
        }
      }
    },
    created () {
      this.reload3()
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

