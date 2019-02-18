import Login from '../views/login'
import Admin from '../views/admin'
import Register from '../views/register'

import Account from '../views/account'
import TeachInfo from '../views/teachInfo'
import Subject from '../views/subject'
import Question from '../views/question'
import Paper from '../views/paper'

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
  // Form View
  {
    path: '/admin',
    name: 'admin',
    icon: 'bars',
    component: Admin,
    redirect: '/user/',
    meta: {
      label: '个人信息',
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
        path: '/teachInfo/',
        component: TeachInfo,
        name: 'teachInfo',
        meta: {
          label: '教师信息',
          auth: false
        }
      }
    ]
  },
  // Table View
  {
    path: '/question',
    icon: 'table',
    name: 'table',
    component: Admin,
    redirect: '/table/',
    meta: {
      label: '试题板块',
      auth: false
    },
    children: [
      {
        path: '/subject/',
        component: Subject,
        name: 'subject',
        meta: {
          label: '科目管理',
          auth: false
        }
      },
      {
        path: '/question/',
        component: Question,
        name: 'question',
        meta: {
          label: '试题管理',
          auth: false
        }
      },
      {
        path: '/paper/',
        component: Paper,
        name: 'paper',
        meta: {
          label: '试卷管理',
          auth: false
        }
      }

    ]
  },
  {
    path: '*',
    redirect: '/table/'
  }
]

export default routes
