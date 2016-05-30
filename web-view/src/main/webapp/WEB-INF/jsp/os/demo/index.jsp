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
<div class="app" id="app" ng-class="{'app-header-fixed':app.settings.headerFixed, 'app-aside-fixed':app.settings.asideFixed, 'app-aside-folded':app.settings.asideFolded}" >
    <!-- navbar -->
    <div data-ng-include=" '${rootTemplate}/blocks/header.htm' " class="app-header navbar">
    </div>
    <!-- / navbar -->

    <!-- menu -->
    <div data-ng-include=" '${rootPath}/os/demo/nav.html' " class="app-aside hidden-xs {{app.settings.asideColor}}">
    </div>
    <!-- / menu -->

    <!-- content -->
    <div class="app-content">
        <div ui-butterbar></div>
        <a href class="off-screen-toggle hide" ui-toggle-class="off-screen" data-target=".app-aside" ></a>
        <div class="app-content-body fade-in-up" ui-view></div>
    </div>
    <!-- /content -->

    <!-- aside right -->
    <div class="app-aside-right pos-fix no-padder w-md w-auto-xs bg-white b-l animated fadeInRight hide">
        <div class="vbox">
            <div class="wrapper b-b b-light m-b">
                <a href class="pull-right text-muted text-md" ui-toggle-class="show" target=".app-aside-right"><i class="icon-close"></i></a>
                Chat
            </div>
            <div class="row-row">
                <div class="cell">
                    <div class="cell-inner padder">
                        <!-- chat list -->
                        <div class="m-b">
                            <a href class="pull-left thumb-xs avatar"><img src="common/img/a2.jpg" alt="..."></a>
                            <div class="clear">
                                <div class="pos-rlt wrapper-sm b b-light r m-l-sm">
                                    <span class="arrow left pull-up"></span>
                                    <p class="m-b-none">Hi John, What's up...</p>
                                </div>
                                <small class="text-muted m-l-sm"><i class="fa fa-ok text-success"></i> 2 minutes ago</small>
                            </div>
                        </div>
                        <div class="m-b">
                            <a href class="pull-right thumb-xs avatar"><img src="common/img/a3.jpg" class="img-circle" alt="..."></a>
                            <div class="clear">
                                <div class="pos-rlt wrapper-sm bg-light r m-r-sm">
                                    <span class="arrow right pull-up arrow-light"></span>
                                    <p class="m-b-none">Lorem ipsum dolor :)</p>
                                </div>
                                <small class="text-muted">1 minutes ago</small>
                            </div>
                        </div>
                        <div class="m-b">
                            <a href class="pull-left thumb-xs avatar"><img src="common/img/a2.jpg" alt="..."></a>
                            <div class="clear">
                                <div class="pos-rlt wrapper-sm b b-light r m-l-sm">
                                    <span class="arrow left pull-up"></span>
                                    <p class="m-b-none">Great!</p>
                                </div>
                                <small class="text-muted m-l-sm"><i class="fa fa-ok text-success"></i>Just Now</small>
                            </div>
                        </div>
                        <!-- / chat list -->
                    </div>
                </div>
            </div>
            <div class="wrapper m-t b-t b-light">
                <form class="m-b-none">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="Say something">
            <span class="input-group-btn">
              <button class="btn btn-default" type="button">SEND</button>
            </span>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- / aside right -->

    <!-- footer -->
    <div class="app-footer wrapper b-t bg-light">
        <span class="pull-right">{{app.version}} <a href ui-scroll="app" class="m-l-sm text-muted"><i class="fa fa-long-arrow-up"></i></a></span>
        &copy; 2014 Copyright.
    </div>
    <!-- / footer -->

    <div data-ng-include=" '${rootTemplate}/blocks/settings.htm' " class="settings panel panel-default">
    </div>

</div>

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
