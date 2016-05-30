<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>
<!DOCTYPE html>
<html  >
<head lang="en">
  <meta charset="UTF-8">
  <title>系统初始化操作页面</title>

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
<body >
<div class="app" >
  <!-- content -->
  <div class="page-content">

    <div class="app-content-body fade-in-up" >

      <div class="wrapper-md">
            <div class="panel panel-default">
              <div class="panel-heading">
                <i class="glyphicon glyphicon-book"></i>
                <span class="h4">系统初始化</span>
              </div>
              <div class="panel-body">
                <div class="form-group pull-in clearfix">
                  <div class="col-sm-6">
                    <a href="${rootPath}/core/auth/"/>
                    初始化管理员账号和权限
                    </a>
                  </div>

                </div>
              </div>
            </div>
      </div>

    </div>
  </div>
  <!-- /content -->



</div>

</body>
</html>
