// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import 'es6-shim'

import Vue from 'vue'
import N3 from 'N3-components'
import 'N3-components/dist/index.min.css'
import extend from './extend'

Vue.use(extend)
Vue.use(N3)

import App from './App'
import router from './router'
import './assets/styles/base.css'
import axios from 'axios'
import VCharts from 'v-charts'
Vue.use(VCharts)

/* eslint-disable */
new Vue({
  el: '#app',
  template: '<App />',
  components: { App },
  router,
})
Vue.prototype.$userId = 0
axios.defaults.withCredentials = true;
axios.defaults.headers = {'Content-Type': 'application/json'}
Vue.prototype.$axios = axios
/* eslint-enable */
