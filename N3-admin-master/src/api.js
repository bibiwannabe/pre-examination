import {
  IS_DEV
} from './config'

const API = {
  // ROOT: IS_DEV ? '/api' : 'http://abc.com/api',
  ROOT: '/api',

  USER_LOGIN: 'http://119.23.239.46:8080/admin-api-1.0/user/login',
  USER_LOGOUT: 'http://119.23.239.46:8080/admin-api-1.0/user/logout',
  USER_ADD: '/user/add',
  RECORD_LIST: '/user/getRecords',
  QUERY_LIST: '/user/getRecordsBy'
}

export default API
