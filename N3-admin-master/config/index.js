// see http://vuejs-templates.github.io/webpack for documentation.
var path = require('path')

module.exports = {
  build: {
    env: require('./prod.env'),
    index: path.resolve(__dirname, '../docs/index.html'),
    assetsRoot: path.resolve(__dirname, '../docs'),
    assetsSubDirectory: 'static',
    assetsPublicPath: './',
    productionSourceMap: true,
    productionGzip: false,
    productionGzipExtensions: ['js', 'css']
  },
  dev: {
    env: require('./dev.env'),
    port: 8088,
    assetsSubDirectory: 'static',
    assetsPublicPath: '/',
    proxyTable: {
      '/api': {
        //target: 'http://localhost:8083/admin_api_war_exploded',
        target: 'http://119.23.239.46:8080/admin-api-1.4',
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''
        },
        onProxyRes(proxyRes, req, res) {
          var cookies = proxyRes.headers['set-cookie']
          if (cookies == null || cookies.length == 0) {
            delete proxyRes.headers['set-cookie']
            return
          }
          for (var i = 0,n = cookies.length; i < n; i++) {
            if(cookies[i].match(/^JSESSIONID=[^;]+;[\s\S]*Path=\/[^;]+/)){
              cookies[i] = cookies[i].replace(/Path=\/[^;]+/,'Path=/');
            }
          }

          proxyRes.headers['set-cookie'] = cookies;
        }
      }
    },
    cssSourceMap: false,
    historyApiFallback: true
  }
}
