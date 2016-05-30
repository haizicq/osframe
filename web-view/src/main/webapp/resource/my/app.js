/**
 * Created by wangchun on 15/9/16.
 */
define(function (require, exports, module) {
    var appConstant=require("js/appConstant");//常量配置

    var global=require("js/global");//加载全局js
    var app = angular.module('indexApp', ['ui.router','ngSea','ui.bootstrap','pascalprecht.translate',
        'ngCookies','app.directives','app.filters','app.services','app.components']);
    app.config(['$stateProvider', '$urlRouterProvider', '$controllerProvider', '$compileProvider', '$filterProvider', '$provide',
            function ($stateProvider,   $urlRouterProvider,   $controllerProvider,   $compileProvider,   $filterProvider,   $provide) {

            $urlRouterProvider.otherwise('/info');
            $stateProvider
                .state('info', {
                    url: '/info',
                    views:{
                        "":{
                            controller: 'myInfoCtrl',
                            cache:'false',
                            templateUrl:G.path.RootPath+'/my/info.html',
                            controllerUrl: G.path.RootPath+'/resource/my/myInfoCtrl.js'
                        }
                    }
                }).state('edit', {
                    url: '/edit',
                    views:{
                        "":{
                            controller: 'myInfoCtrl',
                            cache:'false',
                            templateUrl:function($routeParams){
                                return global.reg(G.path.RootPath+'/my/info/edit.html');
                            },
                            controllerUrl: G.path.RootPath+'/resource/my/myInfoCtrl.js'
                        }
                    }
                }).state('pwd', {
                    url: '/pwd',
                    views:{
                        "":{
                            controller: 'myPwdCtrl',
                            cache:'false',
                            templateUrl:G.path.RootPath+'/my/pwd.html',
                            controllerUrl: G.path.RootPath+'/resource/my/myPwdCtrl.js'
                        }
                    }
                });

            }]
        ).config(['$translateProvider', function($translateProvider){

            // Register a loader for the static files
            // So, the module will search missing translation tables under the specified urls.
            // Those urls are [prefix][langKey][suffix].
            //$translateProvider.useStaticFilesLoader({
            //    prefix: G.path.ResPath+'/l10n/',
            //    suffix: '.json'
            //});
            //${appJsTranslateCode}
            // Tell the module what language to use by default
            //$translateProvider.preferredLanguage('en');

            // Tell the module to store the language in the local storage
            //$translateProvider.useLocalStorage();

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
