<template>
  <section class="history-list">
    <n3-modal-dialog :show="isShow">
      <n3-form ref="form" validate>
        <n3-form-item
          label="题目类型"
          :label-col="3"
          need
        >
          <select v-model="questionType" style="width: 320px;padding-left: 8px;padding-top: 2px; padding-bottom: 2px; border-color: #dddddd; background-color: white">
            <option :value="0">单选题</option>
            <option :value="1">多选题</option>
            <option :value="2">填空题</option>
          </select>
        </n3-form-item>
        <n3-form-item
          label="科目"
          need
          :label-col="3">
          <select v-model="subject.id" style="width: 320px;padding-left: 8px;padding-top: 2px; padding-bottom: 2px; border-color: #dddddd; background-color: white">
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
        <n3-form-item label="选项" need :label-col="3" v-if="questionType === 0 || questionType === 1">
          <tr v-for="(item, index) in options" >
            <td>
              <n3-input :value="item" v-model="options[index]"
                          width="320px"
                        style="margin-bottom: 10px" @blur="changeOption(index, item)">{{item}}
            </n3-input></td>
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
        <n3-form-item label="答案" need :label-col="3" v-if="questionType === 0">
          <select v-model="answer" style="width: 320px;padding-left: 8px;padding-top: 2px; padding-bottom: 2px; border-color: #dddddd; background-color: white">
            <option :value="item" v-for="item in options">{{item}}</option>
          </select>
        </n3-form-item>
        <n3-form-item label="答案" need :label-col="3" v-if="questionType === 1">
          <tr v-for="(item, index) in answers"  v-model="answers">
            <td>
              <select v-model="answers[index]" style="width: 320px;padding-left: 8px;margin-bottom:10px; padding-top: 2px; padding-bottom: 2px; border-color: #dddddd; background-color: white">
                <option :value="item" v-for="item in options">{{item}}</option>
              </select></td>
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
        <n3-form-item label="答案" need :label-col="3" v-if="questionType === 2">
          <n3-input :value="answer" v-model="answer"
                        width="320px"
                        style="margin-bottom: 10px">{{answer}}
          </n3-input>
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
    </n3-modal-dialog>
  </section>
</template>
<script>
  import API from '../api'

  export default {
    data () {
      return {
        isReadonly: true,
        isShow: true,
        subject: {
          id: 0,
          name: ''
        },
        answer: '',
        answers: [],
        options: [],
        content: '',
        subjectList: [],
        questionTypeList: [
          {
            id: 0,
            name: '单选题'
          },
          {
            id: 1,
            name: '多选题'
          },
          {
            id: 2,
            name: '填空题'
          }
        ],
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
        if(this.questionType === 0) {
          return '单选题'
        }
        if(this.questionType === 1) {
          return '多选题'
        }
        if(this.questionType === 2) {
          return '填空题'
        }
      },
      reload1 () {
        this.$axios.get('/api/subject/list'
        ).then(response => {
          this.subjectList = response.data.data
          this.subjectId = response.data.data[0].id
          this.subject.id = this.subjectId
        }).catch((error) => {
          alert('获取信息失败' + error.toString())
        })
        this.loading = false
      },
      created () {
        this.reload1()
      },
      submitQuestion () {
        var result = 0
        var data = ''
        if (this.questionType === 0) {
          data = JSON.stringify({subjectId: this.subject.id, answer: this.answer, options: JSON.stringify(this.options), type: this.questionType, content: this.content})
        }
        if (this.questionType === 1) {
          data = JSON.stringify({subjectId: this.subject.id, answer: JSON.stringify(this.answers), options: JSON.stringify(this.options), type: this.questionType, content: this.content})
        }
        if (this.questionType === 2) {
          data = JSON.stringify({subjectId: this.subject.id, answer: this.answer, type: this.questionType, content: this.content})
        }
        this.$axios({
          method: 'post',
          url: '/api/question/create',
          crossDomain: true,
          data: data,
          contentType: 'application/json'
        }).then(response => {
          result = response.data.code
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
            var id = response.data.data
            this.n3Alert({
              content: '创建成功',
              type: 'success',
              placement: 'center',
              duration: 2000,
              width: '240px' // 内容不确定，建议设置width
            })
            if (this.questionType === 0) {
              this.$router.push({name: 'choiceInfo', params: {id: id}})
            }
            if (this.questionType === 1) {
              this.$router.push({name: 'selectionInfo', params: {id: id}})
            }
            if (this.questionType === 2) {
              this.$router.push({name: 'fillingInfo', params: {id: id}})
            }
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
      changeOption (index, content) {
        this.options.splice(index, 1, content)
      },
      cancelAndReturn () {
        this.$router.go(-1)
      }
    },
    watch: {
      '$route' () {
        if (this.$route.name == 'createQuestion') {
          this.created()
        }
      }
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

