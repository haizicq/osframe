<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>系统登录</title>
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

  <script src="${rootRes}/js/libs/angularjs/angular1.4.0/angular.js"></script>
  <script src="${rootRes}/js/libs/angularjs/angular1.4.0/angular-ui-router.min.js"></script>
  <script src="${rootRes}/js/libs/seajs/sea.js"></script>
  <script src="${rootPath}/resource/core/login/mscLoginCtrl.js" type="text/javascript" ></script>
</head>
<body>
<div ng-app="loginApp">
<div class="container w-xxl w-auto-xs" ng-controller="loginFormController">
  <p class="navbar-brand block m-t"><fmt:message key="mscLogin.module"/> </p>
  <div class="m-b-lg">
    <%--<div class="wrapper text-center">--%>
      <%--<strong>Sign in to get in touch</strong>--%>
    <%--</div>--%>
    <form name="form" class="form-validation">
      <div class="text-danger wrapper text-center" ng-show="authError">
        {{errorInfo}}
      </div>
      <div class="list-group list-group-sm">
        <div class="list-group-item">
          <label ><fmt:message key="mscLogin.userName"/></label>
          <input type="text" placeholder="<fmt:message key="mscLogin.userName"/> " class="form-control no-border" ng-model="loginForm.userName" required>
        </div>
        <div class="list-group-item">
          <label ><fmt:message key="mscLogin.pwd"/></label>
          <input type="password" placeholder="<fmt:message key="mscLogin.pwd"/>" class="form-control no-border" ng-model="loginForm.pwd" required>
        </div>
      </div>
      <button type="submit" class="btn btn-lg btn-primary btn-block" ng-click="login()" ng-disabled='form.$invalid'><fmt:message key="mscLogin.login"/></button>
      <div class="text-center m-t m-b"><a ui-sref="access.forgotpwd"><fmt:message key="mscLogin.forgotPwd"/></a></div>
      <div class="line line-dashed"></div>
      <%--<p class="text-center"><small>Do not have an account?</small></p>--%>
      <%--<a ui-sref="access.signup" class="btn btn-lg btn-default btn-block">mscLogin.register</a>--%>

    </form>
  </div>
</div>
</div>
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
</script>
</body>
</html>

