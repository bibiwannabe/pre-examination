import Login from '../views/login'
import Admin from '../views/admin'
import Register from '../views/register'

import Account from '../views/account'
import Subject from '../views/subject'
import AllSubject from '../views/allSubject'
import QuestionList from '../views/wrongQuestionList'
import PaperShow from '../views/paperShow'
import Index from '../views/index'
import PaperPagination from '../views/paperListPagination'
import PracticeRecord from '../views/practiceRecord'
import RecordInfo from '../views/recordInfo'

const routes = [
  // Login View
  {
    path: '/login',
    component: Login,
    name: 'login',
    menu: false
  },
  {
    path: '/register',
    component: Register,
    name: 'register',
    menu: false
  },
  // Normal View
  {
    path: '/test',
    // 异步载入组件
    component: function (resolve, reject) {
      require.ensure([], function (require) {
          let comm = require('../views/test/query')
          resolve(comm)
      })
    },
    name: 'testQuery',
    menu: false,  // 禁止在导航(navbar / levelbar)中显示
    meta: {
      auth: false // 无需登录校验
    }
  },
  {
    path: '/index',
    name: 'index',
    component: Index,
    menu: false
  },
  {
    path: '/allSubject',
    name: 'allSubject',
    component: AllSubject,
    menu: false
  },
  {
    path: '/paperList',
    name: 'paperList',
    component: PaperPagination,
    menu: false
  },
  {
    path: '/paperInfo',
    name: 'paperInfo',
    component: PaperShow,
    menu: false
  },
  // Form View
  {
    path: '/admin',
    name: 'admin',
    icon: 'bars',
    component: Admin,
    redirect: '/user/',
    meta: {
      label: '个人中心',
      auth: false
    },
    children: [
      {
        path: '/account/',
        component: Account,
        name: 'account',
        meta: {
          label: '登录账号',
          auth: false
        }
      },
      {
        path: '/practiceSubject/',
        component: Subject,
        name: 'practiceSubject',
        meta: {
          label: '错题训练',
          auth: false
        }
      },
      {
        path: '/practiceRecord/',
        component: PracticeRecord,
        name: 'practiceRecord',
        meta: {
          label: '做题记录',
          auth: false
        }
      },
      {
        path: '/questionList/',
        component: QuestionList,
        name: 'questionList',
        meta: {
          label: '错题列表',
          auth: false
        },
        menu: false
      },
      {
        path: '/recordInfo/',
        component: RecordInfo,
        name: 'recordInfo',
        meta: {
          label: '记录详情',
          auth: false
        },
        menu: false
      }
    ]
  },
  {
    path: '*',
    redirect: '/table/'
  }
]

export default routes
