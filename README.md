## backstage-dashboard Series
* 带权限管理的前后端分离统计看板练手项目

### 前端项目 
* Vue + Vue-Router + Vuex: [backstage-dashboard-fe](https://github.com/PierceTsu/backstage-dashboard-fe)

### 后端项目
* SpringBoot + MyBatis + Shiro: [backstage-dashboard-be](https://github.com/PierceTsu/backstage-dashboard-be)

### Introduction
* RBAC(Resource-Based Access Control)基于资源的权限管理
* url配置控制鉴权(粗粒度)与注解控制授权(细粒度)二者结合实现权限管理

## TODO
- [ ] 部署
    - [ ] 基于session
    - [ ] 基于token

### FE
- [x] element-ui
- [x] vue-router
- [x] vuex
- [ ] 整合vue-echarts

### BE
- [x] MyBatis-Generator
- [x] swagger2
- [x] logback
- [x] 多环境配置
- [x] 验证码
- [x] 密码盐值
- [ ] 权限缓存
- [ ] 使用JWT进行鉴权

## 参考
* [RBAC新解](http://globeeip.iteye.com/blog/1236167)
