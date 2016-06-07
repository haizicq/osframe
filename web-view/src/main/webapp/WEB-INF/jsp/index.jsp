<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/index_top.jsp"%>

    <!-- navbar -->
    <div data-ng-include=" '${rootTemplate}/blocks/header.htm' " class="app-header navbar">
    </div>
    <!-- / navbar -->
    <!-- content -->
    <div class="page-content">
        <div ui-butterbar></div>
        <a href class="off-screen-toggle hide" ui-toggle-class="off-screen" data-target=".app-aside" ></a>
        <div class="app-content-body fade-in-up" ui-view>

        </div>
    </div>
    <!-- /content -->

<script>
    // seajs config
    G.path.modulePath="./";
    G.path.moduleResPath="${rootPath}/resource/index";
    G.path.moduleTemplatePath="${rootPath}/index";//模块资源位置

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
    <!-- footer -->
    <div class="page-footer wrapper b-t bg-light">
        <span class="pull-right">{{app.version}} <a href ui-scroll="app" class="m-l-sm text-muted"><i class="fa fa-long-arrow-up"></i></a></span>
        &copy; 2014 Copyright.
    </div>
    <!-- / footer -->

    <div data-ng-include=" '${rootTemplate}/blocks/settings.htm' " class="settings panel panel-default">
    </div>


</body>
</html>
