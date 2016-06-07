#osframe 框架介绍
本项目是一个基础后台开发框架，基于springmvc+spring+hibernate搭建，前端采用angular js+sea js +bootstrap展现。基础框架中包含了用户管理、权限管理等基础模块，提供了简单的首页门户，可用于快速实现项目。
启动访问路径：http://localhost:9080/ ，用户名：admin 密码：1
1. 效果预览
![首页图片](http://git.oschina.net/uploads/images/2016/0607/221759_636c19a2_367605.jpeg "首页图片")
![用户管理](http://git.oschina.net/uploads/images/2016/0607/221909_9ea63784_367605.jpeg "用户管理")
![权限管理](http://git.oschina.net/uploads/images/2016/0607/221954_19f80bec_367605.jpeg "权限管理")
![DEMO列表](http://git.oschina.net/uploads/images/2016/0607/222029_41059e12_367605.jpeg "DEMO列表")
![DEMO新建或编辑](http://git.oschina.net/uploads/images/2016/0607/222116_6641b3d9_367605.jpeg "DEMO新建编辑")
![DEMO查看效果](http://git.oschina.net/uploads/images/2016/0607/222150_3a033400_367605.jpeg "DEMO查看效果")
2. 适用浏览器
  本项目适用于IE9及以上浏览器、谷歌Chrome、Safari等主流浏览器。注意IE8及以下浏览器无法用。
3. 项目本地启动
  在web-view模块下，resource中打开dataSource.properties，修改数据库连接、用户名和密码，使用maven jetty插件启动。
  启动成功后访问路径：http://localhost:9080/ ，用户名：admin 密码：1
