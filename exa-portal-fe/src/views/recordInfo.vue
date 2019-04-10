<template>
  <section class="history-list" style="position: absolute; width: 1240px; margin-top: 0">
    <h3 style="text-align: center; line-height: 80px">{{paperName}}</h3>
    <p style="font-size: 18px; text-align: center; line-height: 40px;color: red;">你的得分：{{grades}}分</p>
    <div v-if="this.choiceResultList.length >0 " style="padding-left: 140px; padding-right: 140px">
      <h3 style="font-size: 22px; line-height: 40px; margin-top: 20px">单选题 <span style="color: red; font-size: 17px">你的得分：{{choiceGrades}} </span></h3>
      <div v-for="(item,index) in this.choiceResultList">
        <n3-icon v-if="item.isCorrect === false" size="20px" type="times" color="red"></n3-icon>
        <span style="font-size: 18px; line-height: 40px">{{index + 1}}. {{item.content}}</span> <br/>
        <div v-if="item.isCorrect === false">
          <n3-radio-group v-model="item.wrongAnswer" type="primary" @change=""><n3-radio v-for="option of JSON.parse(item.options)" :label="option.toString()" disabled=""
                                                                                         style="float: none; font-size: 16px; width: 100%; height: 40px; margin-left: 20px">{{option}}</n3-radio></n3-radio-group>
        </div>
        <div v-if="item.isCorrect === true">
          <n3-radio-group v-model="item.answer" type="primary" @change="">
            <n3-radio v-for="option of JSON.parse(item.options)" :label="option.toString()" disabled=""
                      style="float: none; font-size: 16px; width: 100%; height: 40px; margin-left: 20px">{{option}}
            </n3-radio>
          </n3-radio-group>
        </div>
        <p style="font-size: 18px">正确答案:{{item.answer}}</p>
      </div>
    </div>
    <div v-if="this.selectionResultList.length >0" style="padding-left: 140px; padding-right: 140px; margin-top: 20px">
      <h3 style="font-size: 22px; line-height: 40px; margin-top: 20px">多选题 <span style="color: red; font-size: 17px">你的得分：{{selectionGrades}}</span></h3>
      <div v-for="(item, index) of this.selectionResultList">
        <n3-icon v-if="item.isCorrect === false" size="20px" type="times" color="red"></n3-icon>
        <span style="font-size: 18px; line-height: 40px">{{index + 1}}. {{item.content}}</span> <br/>
        <div v-if="item.isCorrect === false">
          <n3-checkbox-group  @change="" v-model="selectionAnswerMap[item.id]">
            <n3-checkbox v-for="option of JSON.parse(item.options)" :label="option" disabled
                         style="float: none; font-size: 16px; width: 100%; height: 40px; margin-left: 20px">{{option}}</n3-checkbox>
          </n3-checkbox-group>
        </div>
      </div>
      <div v-if="item.isCorrect === true">
        <n3-checkbox-group v-model="selectionAnswerMap[item.id]" @change="">
          <n3-checkbox v-for="option of JSON.parse(item.options)" :label="option" disabled
                       style="float: none; font-size: 16px; width: 100%; height: 40px; margin-left: 20px">{{option}}</n3-checkbox>
        </n3-checkbox-group>
      </div>

    </div>
    <div v-if="this.fillingResultList.length >0" style="padding-left: 140px; padding-right: 140px;margin-top: 20px">
      <h3 style="font-size: 22px; line-height: 40px; margin-top: 20px">填空题 <span style="color: red; font-size: 17px">你的得分：{{fillingGrades}}</span></h3>
      <div v-for="(item,index) in this.fillingResultList">
        <n3-icon v-if="item.isCorrect === false" size="20px" type="times" color="red"></n3-icon> <br/>
        <span style="font-size: 18px; line-height: 40px">{{index + 1}}. {{item.content.split('()')[0]}}</span>
        <div v-if="item.isCorrect === false">
          <n3-input v-model="item.wrongAnswer" disabled=""></n3-input>
        </div>
        <div v-if="item.isCorrect === true">
          <n3-input v-model="item.answer" disabled=""></n3-input>
        </div>
        <span v-if="item.content.split('()').length > 1" style="font-size: 18px; line-height: 40px">{{item.content.split('()')[1]}}</span>
        <p style="font-size: 18px">正确答案:{{item.answer}}</p>
      </div>
    </div>
    <div style="width: 40%; height: 100px; margin-top: 60px; margin-left: auto; margin-right: auto">
      <n3-button style="width: 200px; float: left" block @click.native="goIndex">返回主页</n3-button>
      <n3-button type="primary" style="width: 200px; float: right" block @click.native="goPractice">进入错题练习</n3-button>
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
  export default {
    name: 'recordInfo',
    data () {
      return {
        recordId: 0,
        subject: {
          id: 0,
          name: ''
        },
        selectionAnswerMap: {},
        paperName: '',
        loading: false,
        subjectId: 0,
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
        this.$router.push({name: 'practiceSubject'})
      },
      getRecord () {
        this.recordId = this.$route.params.recordId
        var url = '/portal-api-1.1.1/practice/' + this.$route.params.recordId
        this.$axios.get(url).then(response => {
          this.subject.id = response.data.data.subjectId
          this.paperName = response.data.data.name
          this.choiceResultList = response.data.data.choice.questionList
          this.selectionResultList = response.data.data.selection.questionList
          this.fillingResultList = response.data.data.filling.questionList
          if (this.selectionResultList.length > 0) {
            for (var item of this.selectionResultList) {
              if (item.isCorrect === false) {
                this.selectionAnswerMap[item.id] = JSON.parse(item.wrongAnswer)
              } else {
                this.selectionAnswerMap[item.id] = JSON.parse(item.answer)
              }
            }
          }
          this.choiceGrades = response.data.data.choice.totalPoints
          this.selectionGrades = response.data.data.selection.totalPoints
          this.fillingGrades = response.data.data.filling.totalPoints
          this.grades = response.data.data.grades
        }).catch((error) => { this.alert('获取信息失败' + error.toString()) })
      },
      reload4 () {
        this.loading = true
        this.getRecord()
        this.loading = false
      }
    },
    watch: {
      '$route' () {
        if (this.$route.name === 'recordInfo') {
          this.reload4()
        }
      }
    }
  }
</script>

<style scoped>

</style>
