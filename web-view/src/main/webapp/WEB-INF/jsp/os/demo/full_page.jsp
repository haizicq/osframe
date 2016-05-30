<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>
<!DOCTYPE html>
<html  >
<head lang="en">
  <meta charset="UTF-8">
  <title>模块首页</title>

  <META HTTP-EQUIV="Pragma" CONTENT="no-cache">
  <META HTTP-EQUIV="Cache-Control" CONTENT="no-cache">
  <META HTTP-EQUIV="Expires" CONTENT="0">
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <link rel="stylesheet" href="${rootRes}/css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="${rootRes}/css/animate.css" type="text/css" />
  <link rel="stylesheet" href="${rootRes}/css/font-awesome.min.css" type="text/css" />
  <link rel="stylesheet" href="${rootRes}/css/simple-line-icons.css" type="text/css" />
  <link rel="stylesheet" href="${rootRes}/css/font.css" type="text/css" />
  <link rel="stylesheet" href="${rootRes}/css/app.css" type="text/css" />
</head>
<body ng-controller="AppCtrl">
<div class="app" id="app"  >

  <!-- content -->
  <div class="page-content">
    <div ui-butterbar></div>
    <a href class="off-screen-toggle hide" ui-toggle-class="off-screen" data-target=".app-aside" ></a>
    <div class="app-content-body fade-in-up" ui-view>

    </div>
  </div>
  <!-- /content -->

  <!-- footer -->
  <div class="page-footer wrapper b-t bg-light">
    <span class="pull-right">{{app.version}} <a href ui-scroll="app" class="m-l-sm text-muted"><i class="fa fa-long-arrow-up"></i></a></span>
    &copy; 2014 Copyright.
  </div>
  <!-- / footer -->


</div>

<!-- jQuery -->
<script src="${rootRes}/js/libs/jquery/jquery-1.11.0.js"></script>

<script src="${rootRes}/js/libs/seajs/sea.js"></script>
<script src="${rootRes}/js/libs/angularjs/angular1.4.0/angular.js"></script>
<script src="${rootRes}/js/libs/angularjs/angular1.4.0/angular-ui-router.min.js"></script>
<!--<script src="app/angularjs/ngSea-0.6.1.js"></script>-->
<script src="${rootRes}/js/libs/angularjs/ngSea-ui-route.js"></script>
<script src="${rootRes}/i18n/angular-locale_zh-cn.js"></script>

<script>
  var G = {};
  G.path ={
    RootPath:'${rootPath}',//跟目录
    serverRootPath:"",//服务请求根路径
    ResPath:"${rootRes}",//资源文件根路径
    TemplatePath:"${rootTemplate}",//模板路径
    modulePath:"",//模块模板路径 由模块设定
    moduleResPath:"",//模块资源位置
    moduleTemplatePath:""//模块资源位置
  };
  //seajs配置
  seajs.config({
    base: G.path.ResPath,
    alias: {
      "jquery": "js/libs/jquery/jquery.min",//"lib/jquery-ui/js/jquery-1.10.2"
      "jquery-ui": "js/libs/jquery/jquery-ui-1.10.4.custom",
      "jquery-ui-css": "lib/jquery-ui/js/jquery-ui-1.10.4.custom.css"
    }
  });
</script>
<script src="${rootRes}/js/libs/angularjs/angular1.4.0/angular-cookies.min.js"></script>
<script src="${rootRes}/js/libs/angularjs/angular1.4.0/angular-animate.min.js"></script>
<script src="${rootRes}/js/angular/angular-translate.js"></script>
<script src="${rootRes}/js/angular/ngStorage.min.js"></script>
<script src="${rootRes}/js/angular/ui-load.js"></script>
<script src="${rootRes}/js/angular/ui-jq.js"></script>
<script src="${rootRes}/js/angular/ui-validate.js"></script>
<script src="${rootRes}/js/angular/ui-bootstrap-tpls.min.js"></script>


<script>
  // seajs config
  G.path.modulePath="./";
  G.path.moduleResPath="${rootPath}/resource/os/demo";
  G.path.moduleTemplatePath="${rootPath}/os/demo";//模块资源位置

  seajs.on('error', function(module){
    if(module.status!=5){
      console.error('seajs error: ', module);
    }
  });
  //引入基础组件控制器和基础配置js，然后启动angular-js
  //Step2: bootstrap youself

  seajs.use(['js/componentController','js/appDirectives',
    'js/appFilters','js/appServices',
    G.path.moduleResPath+'/app'], function(appControllers,appDirectives,appFilters,appServices,app){
    angular.bootstrap(document, ['indexApp']);

  });

</script>
</body>
</html>
