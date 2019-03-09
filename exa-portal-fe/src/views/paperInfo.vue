<template>
  <section class="history-list" style="position: absolute">
    <div v-if="resultRecord == 0">
    <h1 style="margin-top: 20px; text-align: center; line-height: 80px">{{paperName}}</h1>
    <p style="font-size: 20px; text-align: center; line-height: 40px">总分：{{sum}}分</p>
    <n3-step round :labels="steps" :current="currentStep" style="" v-model="currentStep"></n3-step>
    <div v-if="this.choiceList.length >0 " style="padding-left: 140px; padding-right: 140px">
      <h3 style="font-size: 22px; line-height: 40px">单选题（每题{{choiceEachPoint}}分）</h3>
      <div v-for="(item,index) in this.choiceList">
        <p style="font-size: 18px; line-height: 40px">{{index + 1}}. {{item.content}}</p>
        <n3-radio-group v-model="choiceAnswerMap[item.id]" type="primary"
                        @change="choiceAnswerChange(choiceAnswerMap[item.id], item.id)">
          <n3-radio v-for="option of JSON.parse(item.options)" :label="option.toString()"
                    style="float: none; font-size: 16px; width: 100%; height: 40px; margin-left: 20px">{{option}}
          </n3-radio>
        </n3-radio-group>
      </div>

    </div>
    <div v-if="this.selectionList.length >0" style="padding-left: 140px; padding-right: 140px; margin-top: 20px">
      <h3 style="font-size: 22px; line-height: 40px">多选题（每题{{selectionEachPoint}}分）</h3>
      <div v-for="(item,index) in this.selectionList">
        <p style="font-size: 18px; line-height: 40px">{{index + 1}}. {{item.content}}</p>
        <n3-checkbox-group v-model="selectionAnswerMap[item.id]" @change="selectionAnswerChange(selectionAnswerMap[item.id], item.id)">
          <n3-checkbox v-for="option of JSON.parse(item.options)" :label="option"
                           style="float: none; font-size: 16px; width: 100%; height: 40px; margin-left: 20px">{{option}}</n3-checkbox>
        </n3-checkbox-group>
      </div>

    </div>
    <div v-if="this.fillingList.length >0" style="padding-left: 140px; padding-right: 140px;margin-top: 20px">
      <h3 style="font-size: 22px; line-height: 40px">填空题（每题{{fillingEachPoint}}分）</h3>
      <div v-for="(item,index) in this.fillingList">
        <span style="font-size: 18px; line-height: 40px">{{index + 1}}. {{item.content.split('()')[0]}}</span>
        <n3-input v-model="fillingAnswerMap[item.id]"></n3-input>
        <span v-if="item.content.split('()').length > 1" style="font-size: 18px; line-height: 40px">{{item.content.split('()')[1]}}</span>
      </div>
    </div>
    <div style="width: 100%; height: 100px; margin-top: 60px">
    <n3-button type="primary" style="width: 200px; margin: auto" block @click.native="submitPaper">提交试卷</n3-button>
    </div>
    </div>
    <div v-if="resultRecord != 0">
    <h1 style="margin-top: 20px; text-align: center; line-height: 80px">{{paperName}}</h1>
    <p style="font-size: 18px; text-align: center; line-height: 40px">总分：{{sum}}分</p>
    <p style="font-size: 18px; text-align: center; line-height: 40px;color: red;">你的得分：{{grades}}分</p>

    <div v-if="this.choiceResultList.length >0 " style="padding-left: 140px; padding-right: 140px">
      <h3 style="font-size: 22px; line-height: 40px; margin-top: 20px">单选题（每题{{choiceEachPoint}}分）<span style="color: red; font-size: 17px">你的得分：{{choiceGrades}} </span></h3>
      <div v-for="(item,index) in this.choiceResultList">
        <n3-icon v-if="item.isCorrect == false" size="20px" type="times" color="red"></n3-icon>
        <span style="font-size: 18px; line-height: 40px">{{index + 1}}. {{item.content}}</span> <br/>
        <n3-radio-group v-model="choiceAnswerMap[item.id]" type="primary">
          <n3-radio v-for="option of JSON.parse(item.options)" :label="option.toString()" disabled=""
                    style="float: none; font-size: 16px; width: 100%; height: 40px; margin-left: 20px">{{option}}
          </n3-radio>
        </n3-radio-group>
        <p style="font-size: 18px">正确答案:{{item.answer}}</p>
      </div>

    </div>
    <div v-if="this.selectionResultList.length >0" style="padding-left: 140px; padding-right: 140px; margin-top: 20px">
      <h3 style="font-size: 22px; line-height: 40px; margin-top: 20px">多选题（每题{{selectionEachPoint}}分）<span style="color: red; font-size: 17px">你的得分：{{selectionGrades}}</span></h3>
      <div v-for="(item,index) in this.selectionResultList">
        <n3-icon v-if="item.isCorrect == false" size="20px" type="times" color="red"></n3-icon>
        <span style="font-size: 18px; line-height: 40px">{{index + 1}}. {{item.content}}</span> <br/>
        <n3-checkbox-group v-model="selectionAnswerMap[item.id]">
          <n3-checkbox v-for="option of JSON.parse(item.options)" :label="option" disabled
                           style="float: none; font-size: 16px; width: 100%; height: 40px; margin-left: 20px">{{option}}</n3-checkbox>
        </n3-checkbox-group>
        <tr><td><p style="font-size: 18px">正确答案:&nbsp&nbsp</p></td>
          <td><span style="font-size: 18px" v-for="answer of JSON.parse(item.answer)">{{answer}}&nbsp&nbsp</span></td></tr>
      </div>

    </div>
    <div v-if="this.fillingResultList.length >0" style="padding-left: 140px; padding-right: 140px;margin-top: 20px">
      <h3 style="font-size: 22px; line-height: 40px; margin-top: 20px">填空题（每题{{fillingEachPoint}}分）<span style="color: red; font-size: 17px">你的得分：{{fillingGrades}}</span></h3>
      <div v-for="(item,index) in this.fillingResultList">
        <n3-icon v-if="item.isCorrect == false" size="20px" type="times" color="red"></n3-icon> <br/>
        <span style="font-size: 18px; line-height: 40px">{{index + 1}}. {{item.content.split('()')[0]}}</span>
        <n3-input v-model="fillingAnswerMap[item.id]" disabled=""></n3-input>
        <span v-if="item.content.split('()').length > 1" style="font-size: 18px; line-height: 40px">{{item.content.split('()')[1]}}</span>
        <p style="font-size: 18px">正确答案:{{item.answer}}</p>
      </div>
    </div>
      <div style="width: 40%; height: 100px; margin-top: 60px; margin-left: auto; margin-right: auto">
        <n3-button style="width: 200px; float: left" block @click.native="goIndex">返回主页</n3-button>
        <n3-button type="primary" style="width: 200px; float: right" block @click.native="goPractice">进入错题练习</n3-button>
      </div>
    </div>
    <div style="width: auto; height: 100px">
      <div style="height: 60px; margin-top: 80px">
        <p style="color: #999999; text-align:center;line-height: 30px; font-size: 14px; float: none">邮箱：bibiwnanabe@foxmail.com</p>
        <p style="color: #999999; text-align:center;line-height: 30px; font-size: 14px; float: none">©️ 2019 黎碧怡 大连理工大学 软件学院</p>
      </div>
    </div>
  </section>
</template>
<script>
  import API from '../api'

  export default {
    data () {
      return {
        currentStep: 0,
        paperId: 0,
        isReadonly: true,
        isShow: true,
        timeout: 200,
        subject: {
          id: 0,
          name: ''
        },
        resultRecord: 0,
        steps: [],
        paperName: '',
        choiceList: [],
        selectionList: [],
        fillingList: [],
        choiceNum: 0,
        choiceEachPoint: 0,
        selectionNum: 0,
        selectionEachPoint: 0,
        fillingNum: 0,
        fillingEachPoint: 0,
        loading: false,
        searchChanged: false,
        subjectId: 0,
        questionType: 0,
        questionTypeName: '',
        readOnly: true,
        choiceAnswerMap: {},
        selectionAnswerMap: {},
        fillingAnswerMap: {},
        sum: 0,
        grades: 0,
        choiceResultList: [],
        selectionResultList: [],
        fillingResultList: [],
        choiceGrades: 0,
        selectionGrades: 0,
        fillingGrades: 0
      }
    },
    methods: {
      goIndex () {
        this.$router.push({
          name: 'index'
        })
      },
      goPractice () {
        this.$router.push({name: 'practiceRecord'})
      },
      submitPaper () {
        var selectionAnswerMapStr = {}
        for (var key of Object.keys(this.selectionAnswerMap)) {
          selectionAnswerMapStr[key] = JSON.stringify(this.selectionAnswerMap[key])
        }
        var data = JSON.stringify({paperId: this.paperId, choice: this.choiceAnswerMap, selection: selectionAnswerMapStr, filling: this.fillingAnswerMap})
        var result = 0
        this.$axios({
          method: 'post',
          url: '/portal-api-1.1.1/paper/practice',
          crossDomain: true,
          data: data,
          contentType: 'application/json'
        }).then(response => {
          result = response.data.code
          if (result === 1000) {
            this.n3Alert({
              content: '提交成功',
              type: 'success',
              placement: 'center',
              duration: 2000,
              width: '240px'
            })
            this.resultRecord = 1
            this.choiceResultList = response.data.data.choice.questionList
            this.selectionResultList = response.data.data.selection.questionList
            this.fillingResultList = response.data.data.filling.questionList
            this.choiceGrades = response.data.data.choice.totalPoints
            this.selectionGrades = response.data.data.selection.totalPoints
            this.fillingGrades = response.data.data.filling.totalPoints
            this.grades = response.data.data.grades
          }
        }).catch((error) => {
          this.alert('修改失败' + error.toString())
          return
        })
      },
      choiceAnswerChange (value, id) {
        this.$set(this.choiceAnswerMap, id, value)
      },
      selectionAnswerChange (value, id) {
        this.$set(this.selectionAnswerMap, id, value)
      },
      fillingAnswerChange (value, id) {
        this.$set(this.fillingAnswerMap, id, value)
      },
      mounted () {
        this.$nextTick(function () {
          setInterval(this.getSubjectList, 1000 * 120)
        })
      },
      getSubjectName (id) {
        for (let subject of this.subjectList) {
          if (subject.id === id) {
            return subject.subjectName
          }
        }
        return '未知科目'
      },
      getPaper () {
        this.paperId = this.$route.params.paperId
        var url = '/portal-api-1.1.1/paper/' + this.$route.params.paperId
        this.$axios.get(url
        ).then(response => {
          this.subject.id = response.data.data.subjectId
          this.paperName = response.data.data.name
          this.choiceList = response.data.data.choice.questionList
          this.selectionList = response.data.data.selection.questionList
          this.fillingList = response.data.data.filling.questionList
          if (this.choiceList != null && this.choiceList.length > 0) {
            this.steps.push('单选题')
          }
          if (this.selectionList != null && this.selectionList.length > 0) {
            this.steps.push('多选题')
          }
          if (this.fillingList != null && this.fillingList.length > 0) {
            this.steps.push('填空题')
          }
          this.choiceEachPoint = response.data.data.choice.point
          this.choiceNum = this.choiceList.length
          this.selectionNum = this.selectionList.length
          this.fillingNum = this.fillingList.length
          this.selectionEachPoint = response.data.data.selection.point
          this.fillingEachPoint = response.data.data.filling.point
          this.sum = this.choiceEachPoint * this.choiceNum + this.selectionEachPoint * this.selectionNum + this.fillingEachPoint * this.fillingNum
        }).catch((error) => {
          alert('获取信息失败' + error.toString())
        })
      },
      reload4 () {
        this.loading = true
        this.getPaper()
        this.loading = false
      },
      create () {
        this.reload4()
      },

      load () {
      },
      getQuestionType (index) {
        if (index === 0) {
          this.questionType = 0
        }
        if (index === 1) {
          this.questionType = 1
        }
        if (index === 2) {
          this.questionType = 2
        }
      },
      cancelAndReturn () {
        this.$router.go(-1)
      }
    },
    created () {
      this.reload4()
    },
    watch: {
      '$route' () {
        if (this.$route.name == 'paperInfo') {
          this.create()
        }
      },
      choiceAnswerMap: {
        handler (val, oldVal) {
          if (this.choiceAnswerMap !== undefined && Object.keys(this.choiceAnswerMap).length === this.choiceList.length) {
            for (var answer of Object.values(this.choiceAnswerMap)) {
              if (answer == null || answer === undefined || answer === '') {
                return
              }
            }
            this.currentStep = 1
          }
        },
        deep: true
      },
      selectionAnswerMap: {
        handler (val, oldVal) {
          if (this.selectionAnswerMap !== undefined && Object.keys(this.selectionAnswerMap).length === this.selectionList.length) {
            for (var answer of Object.values(this.selectionAnswerMap)) {
              if (answer == null || answer === undefined || answer.length === 0) {
                return
              }
            }

            this.currentStep = 2
          }
        },
        deep: true
      },
      fillingAnswerMap: {
        handler (val, oldVal) {
          if (this.fillingAnswerMap !== undefined && Object.keys(this.fillingAnswerMap).length === this.fillingList.length) {
            for (var answer of Object.values(this.fillingAnswerMap)) {
              if (answer == null || answer === undefined || answer === '') {
                return
              }
            }
            this.currentStep = 3
          }
        },
        deep: true
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

