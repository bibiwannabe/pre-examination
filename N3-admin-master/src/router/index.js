/**
 * 路由配置
 */
import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)

import routes from './routes'

const router = new VueRouter({
  // mode: 'history',
  // root: 'N3-admin/',
  routes
})

/**
 * 路由拦截
 */
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'


router.beforeEach((to, from, next) => {
  NProgress.start()
  const shouldAuth = to.meta && to.meta.auth
  if (to.name === 'login' || shouldAuth === false || to.name === 'register') {  // 无需拦截该路由
    return next()
  }

  return next()
})

router.afterEach(transition => {
  NProgress.done()
})

export default router
