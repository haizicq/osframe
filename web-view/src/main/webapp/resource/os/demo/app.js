/**
 * Created by wangchun on 15/9/16.
 */
define(function (require, exports, module) {

    var appConstant=require("js/appConstant");//常量配置
    var global=require("js/global");//加载全局js
    var appConstant=require("js/appConstant");//常量配置
    var app = angular.module('indexApp', ['ui.router','ngSea','ui.bootstrap','pascalprecht.translate',
        'ngCookies','app.directives','app.filters','app.services','app.components']);
    app.config(['$stateProvider', '$urlRouterProvider', '$controllerProvider', '$compileProvider', '$filterProvider', '$provide',
            function ($stateProvider,   $urlRouterProvider,   $controllerProvider,   $compileProvider,   $filterProvider,   $provide) {

            app.controller = $controllerProvider.register;
            app.directive  = $compileProvider.directive;
            app.filter     = $filterProvider.register;
            app.factory    = $provide.factory;
            app.service    = $provide.service;
            app.constant   = $provide.constant;
            app.value      = $provide.value;
                //配置模块链接路径：中文名、链接名、图标地址、链接地址、控制器名、模版url、控制器url
                //链接名：app作为根项目路径，app.module.name
                //如何将这些路径放到配置文件中来读取
                //首页地址读取配置 链接配置，通过AJAX读取这些配置
                
            $urlRouterProvider.otherwise('/osDemoInfoList');
            $stateProvider
            .state('osDemoInfoList', {
                url: '/osDemoInfoList',
                views:{
                    "":{
                        controller: 'osDemoInfoListCtrl',
                        cache:'false',
                        templateUrl:function($routeParams){
                            var url= G.path.moduleTemplatePath+'/osDemoInfo/list.html';
                            url=global.setParam(url,'module',$routeParams.module);
                            url=global.setParam(url,'pageNo',$routeParams.pageNo);
                            url=global.setParam(url,'searchKey',$routeParams.searchKey);
                            return global.reg(url);
                        },
                        controllerUrl: G.path.moduleResPath+'/osDemoInfo/osDemoInfoListCtrl.js'
                    }
                }
            })
            .state('osDemoInfoEdit', {
                url: '/osDemoInfoEdit?id',
                views:{
                    "":{
                        controller: 'osDemoInfoEditCtrl',
                        cache:'false',
                        templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/osDemoInfo/edit.html?id='+$routeParams.id;},
                        controllerUrl: G.path.moduleResPath+'/osDemoInfo/osDemoInfoEditCtrl.js'
                    }
                }
            })
            .state('osDemoInfoLook', {
                url: '/osDemoInfoLook?id',
                views:{
                    "":{
                        controller: 'osDemoInfoLookCtrl',
                        cache:'false',
                        templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/osDemoInfo/look.html?id='+$routeParams.id;},
                        controllerUrl: G.path.moduleResPath+'/osDemoInfo/osDemoInfoLookCtrl.js'
                    }
                }
            })
            .state('osDemoInfoAdd', {
                url: '/osDemoInfoAdd',
                views:{
                    "":{
                        controller: 'osDemoInfoEditCtrl',
                        cache:'false',
                        templateUrl: function($routeParams){ return global.reg(G.path.moduleTemplatePath+'/osDemoInfo/add.html');},
                        controllerUrl: G.path.moduleResPath+'/osDemoInfo/osDemoInfoEditCtrl.js'
                    }
                }
            });

            }]
        ).config(['$translateProvider', function($translateProvider){
        }]);
    appConstant(app);
    app.run(function ($rootScope, $ngSea) {
        $ngSea(app,{
            route_start:'$stateChangeStart',
            mod_root:G.path.modulePath
        });
    });

    module.exports = app;

})
