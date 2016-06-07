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

  <!-- jQuery -->
  <script src="${rootRes}/js/libs/jquery/jquery-1.11.0.js"></script>

  <script src="${rootRes}/js/libs/seajs/sea.js"></script>
  <script src="${rootRes}/js/libs/angularjs/angular1.4.0/angular.js"></script>
  <script src="${rootRes}/js/libs/angularjs/angular1.4.0/angular-ui-router.min.js"></script>
  <!--<script src="app/angularjs/ngSea-0.6.1.js"></script>-->
  <script src="${rootRes}/js/libs/angularjs/ngSea-ui-route.js"></script>
  <%--<script src="${rootRes}/i18n/angular-locale_zh-cn.js"></script>--%>

  <script>
    var G = {};
    G.path ={
      RootPath:'${rootPath}',//跟目录
      serverRootPath:"",//服务请求根路径
      ResPath:"${rootRes}",//资源文件根路径
      TemplatePath:"${rootTemplate}",//模板路径
      UserName:"${userName}",//登陆的用户
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
  <script src="${rootRes}/js/angular/angular-translate.js"></script>
  <script src="${rootRes}/js/angular/ngStorage.min.js"></script>
  <script src="${rootRes}/js/angular/ui-bootstrap-tpls.min.js"></script>


</head>
<body ng-controller="AppCtrl">
<div class="app" id="app" ng-class="{'app-header-fixed':app.settings.headerFixed, 'app-aside-fixed':app.settings.asideFixed, 'app-aside-folded':app.settings.asideFolded}" >
