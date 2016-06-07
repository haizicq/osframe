#osframe 框架介绍
本项目是一个基础后台开发框架，基于springmvc+spring+hibernate搭建，前端采用angular js+sea js +bootstrap展现。基础框架中包含了用户管理、权限管理等基础模块，提供了简单的首页门户，可用于快速实现项目。
启动访问路径：http://localhost:9080/ ，用户名：admin 密码：1

#1. 效果预览
##首页图片
![首页图片](http://git.oschina.net/uploads/images/2016/0607/221759_636c19a2_367605.jpeg "首页图片")
##用户管理
![用户管理](http://git.oschina.net/uploads/images/2016/0607/221909_9ea63784_367605.jpeg "用户管理")
##权限管理
![权限管理](http://git.oschina.net/uploads/images/2016/0607/221954_19f80bec_367605.jpeg "权限管理")
##DEMO列表
![DEMO列表](http://git.oschina.net/uploads/images/2016/0607/222029_41059e12_367605.jpeg "DEMO列表")
##DEMO新建或编辑
![DEMO新建或编辑](http://git.oschina.net/uploads/images/2016/0607/222116_6641b3d9_367605.jpeg "DEMO新建编辑")
##DEMO查看效果
![DEMO查看效果](http://git.oschina.net/uploads/images/2016/0607/222150_3a033400_367605.jpeg "DEMO查看效果")
#2. 适用浏览器
  本项目适用于IE9及以上浏览器、谷歌Chrome、Safari等主流浏览器。注意IE8及以下浏览器无法用。
#3. 项目本地启动
  在web-view模块下，resource中打开dataSource.properties，修改数据库连接、用户名和密码，使用maven jetty插件启动。
  启动成功后访问路径：http://localhost:9080/ ，用户名：admin 密码：1
#4. 开发指引
  参照DEMO模块新建自己的模块，先看下DEMO模块的目录结构
![DEMO目录结构](http://git.oschina.net/uploads/images/2016/0607/224047_3da0ee88_367605.jpeg "DEMO模块的目录结构")
  其中com.os.osframe为基础包路径，os.demo为模块包路径，下面web、service、domain、dao为模块下各层次代码。
  资源文件中的i18n目录下为国际化文件，enums.properties文件为系统枚举定义文件，osDemo-beans.xml为模块的spring配置文件，注意模块的注解扫描不需要再这里配置，默认会扫描的，这里主要预留来做特殊处理。
#5. 开发规范
 * 1）包路径应以com.os.osframe 开头，后面的模块包路径根据自行定义
 * 2）模块首页控制类继承BaseIndexController，普通实体控制类继承BaseController<实体类>
 * 3）实体类继承BaseDomain，并通过hibernate注解定义表名和字段映射
 * 4）DAO层类继承BaseDaoImpl<实体类>，接口继承IBaseDao<实体类>
 * 5）SERVICE层类继承BaseServiceImpl<实体类>，接口继承IBaseService<实体类>
 * 6）权限控制在web层控制类上增加@RequiresClassAuth注解
 * 7）模块默认权限包括管理员权限、默认权限、添加权限、查看权限、编辑权限、删除权限，同时在i18n的资源文件中定义这些权限名称name和描述info,新开发当web类增加了注解后再权限模块点击导入即可自动引入这些权限。参考demo模块的配置如下
```
ROLE_OS_DEMO_ADMIN.name=测试模块_管理员权限
ROLE_OS_DEMO_ADMIN.info=拥有该权限可以对测试模块的所有功能进行管理
ROLE_OS_DEMO_DEFAULT.name=测试模块_默认权限
ROLE_OS_DEMO_DEFAULT.info=拥有该权限才可以对测试模块拥有使用权限
ROLE_OS_DEMO_ADD.name=测试模块_添加权限
ROLE_OS_DEMO_DELETE.name=测试模块_删除权限
ROLE_OS_DEMO_LOOK.name=测试模块_查看权限
ROLE_OS_DEMO_EDIT.name=测试模块_编辑权限
ROLE_OS_DEMO_ADD.info=拥有该权限才可以对用户管理模块进行新增
ROLE_OS_DEMO_DELETE.info=拥有该权限才可以对测试模块进行删除
ROLE_OS_DEMO_LOOK.info=拥有该权限才可以对测试模块进行查看
ROLE_OS_DEMO_EDIT.info=拥有该权限才可以对测试模块进行编辑
```


 * 8）枚举请在enums.properties中定义值，而在i18n中定义名称
```
enumsDemo.simple=简单
enumsDemo.middle=中等
enumsDemo.difficulty=困难
```

