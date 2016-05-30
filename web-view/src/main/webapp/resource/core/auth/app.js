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
                
            $urlRouterProvider.otherwise('/mscAuthInfoList');
            $stateProvider
            .state('mscAuthInfoList', {
                url: '/mscAuthInfoList?module&pageNo&searchKey',
                views:{
                    "":{
                        controller: 'mscAuthInfoListCtrl',
                        cache:'false',
                        templateUrl: function($routeParams){
                            var url=G.path.moduleTemplatePath+'/mscAuthInfo/list.html';
                            url=global.setParam(url,'module',$routeParams.module);
                            url=global.setParam(url,'pageNo',$routeParams.pageNo);
                            url=global.setParam(url,'searchKey',$routeParams.searchKey);
                            return global.reg(url);
                        },
                        controllerUrl: G.path.moduleResPath+'/mscAuthInfo/mscAuthInfoListCtrl.js'
                    }
                }
            })
            .state('mscAuthInfoEdit', {
                url: '/mscAuthInfoEdit?id',
                views:{
                    "":{
                        controller: 'mscAuthInfoEditCtrl',
                        templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscAuthInfo/edit.html?id='+$routeParams.id;},
                        controllerUrl: G.path.moduleResPath+'/mscAuthInfo/mscAuthInfoEditCtrl.js'
                    }
                }
            })
            .state('mscAuthInfoLook', {
                url: '/mscAuthInfoLook?id',
                views:{
                    "":{
                        controller: 'mscAuthInfoLookCtrl',
                        templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscAuthInfo/look.html?id='+$routeParams.id;},
                        controllerUrl: G.path.moduleResPath+'/mscAuthInfo/mscAuthInfoLookCtrl.js'
                    }
                }
            })
                .state('mscAuthInfoDelete', {
                    url: '/mscAuthInfoDelete?id',
                    views:{
                        "":{
                            controller: 'mscSuccessCtrl',
                            templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscAuthInfo/delete.html?id='+$routeParams.id;},
                            controllerUrl: G.path.RootPath+'/common/jsp/success.js'

                        }
                    }
                })
                .state('mscAuthInfoAdd', {
                    url: '/mscAuthInfoAdd?module',
                    views:{
                        "":{
                            controller: 'mscAuthInfoEditCtrl',
                            cache:'false',
                            templateUrl: function($routeParams){
                                var url=G.path.moduleTemplatePath+'/mscAuthInfo/add.html';
                                url=global.setParam(url,'module',$routeParams.module);
                                return global.reg(url);
                            },
                            controllerUrl: G.path.moduleResPath+'/mscAuthInfo/mscAuthInfoEditCtrl.js'
                        }
                    }
                })
                .state('mscAuthInfoExport', {
                    url: '/mscAuthInfoExport',
                    views:{
                        "":{
                            controller: 'mscSuccessCtrl',
                            cache:'false',
                            templateUrl: function($routeParams){return global.reg(G.path.moduleTemplatePath+'/mscAuthInfo/export.html');},
                            controllerUrl:  G.path.RootPath+'/common/jsp/success.js'
                        }
                    }
                })
            .state('mscAuthCategoryList', {
                url: '/mscAuthCategoryList?pageNo&searchKey',
                views:{
                    "":{
                        controller: 'mscAuthCategoryListCtrl',
                        templateUrl: function($routeParams){
                            var url=G.path.moduleTemplatePath+'/mscAuthCategory/list.html';
                            url=global.setParam(url,'pageNo',$routeParams.pageNo);
                            url=global.setParam(url,'searchKey',$routeParams.searchKey);
                            return global.reg(url);
                        },
                        controllerUrl: G.path.moduleResPath+'/mscAuthCategory/mscAuthCategoryListCtrl.js'
                    }
                }
            })
            .state('mscAuthCategoryEdit', {
                url: '/mscAuthCategoryEdit?id',
                views:{
                    "":{
                        controller: 'mscAuthCategoryEditCtrl',
                        templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscAuthCategory/edit.html?id='+$routeParams.id;},
                        controllerUrl: G.path.moduleResPath+'/mscAuthCategory/mscAuthCategoryEditCtrl.js'
                    }
                }
            })
            .state('mscAuthCategoryLook', {
                url: '/mscAuthCategoryLook?id',
                views:{
                    "":{
                        controller: 'mscAuthCategoryLookCtrl',
                        templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscAuthCategory/look.html?id='+$routeParams.id;},
                        controllerUrl: G.path.moduleResPath+'/mscAuthCategory/mscAuthCategoryLookCtrl.js'
                    }
                }
            })
            .state('mscAuthCategoryAdd', {
                url: '/mscAuthCategoryAdd',
                views:{
                    "":{
                        controller: 'mscAuthCategoryEditCtrl',
                        cache:'false',
                        templateUrl:  function($routeParams){
                            var url=G.path.moduleTemplatePath+'/mscAuthCategory/add.html';
                            return global.reg(url);
                        },
                        controllerUrl: G.path.moduleResPath+'/mscAuthCategory/mscAuthCategoryEditCtrl.js'
                    }
                }
            })
            .state('mscAuthRoleList', {
                url: '/mscAuthRoleList?cid&pageNo&searchKey',
                views:{
                    "":{
                        controller: 'mscAuthRoleListCtrl',
                        cache:'false',
                        templateUrl: function($routeParams){
                            var url=G.path.moduleTemplatePath+'/mscAuthRole/list.html';
                            url=global.setParam(url,'cid',$routeParams.cid);
                            url=global.setParam(url,'pageNo',$routeParams.pageNo);
                            url=global.setParam(url,'searchKey',$routeParams.searchKey);
                            return global.reg(url);
                        },
                        controllerUrl: G.path.moduleResPath+'/mscAuthRole/mscAuthRoleListCtrl.js'
                    }
                }
            })
            .state('mscAuthRoleEdit', {
                url: '/mscAuthRoleEdit?id',
                views:{
                    "":{
                        controller: 'mscAuthRoleEditCtrl',
                        templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscAuthRole/edit.html?id='+$routeParams.id;},
                        controllerUrl: G.path.moduleResPath+'/mscAuthRole/mscAuthRoleEditCtrl.js'
                    }
                }
            })
            .state('mscAuthRoleLook', {
                url: '/mscAuthRoleLook?id',
                views:{
                    "":{
                        controller: 'mscAuthRoleLookCtrl',
                        templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscAuthRole/look.html?id='+$routeParams.id;},
                        controllerUrl: G.path.moduleResPath+'/mscAuthRole/mscAuthRoleLookCtrl.js'
                    }
                }
            })
            .state('mscAuthRoleAdd', {
                url: '/mscAuthRoleAdd?cid',
                views:{
                    "":{
                        controller: 'mscAuthRoleEditCtrl',
                        cache:'false',
                        templateUrl: function($routeParams){
                            var url=G.path.moduleTemplatePath+'/mscAuthRole/add.html';
                            url=global.setParam(url,'cid',$routeParams.cid);
                            return global.reg(url);
                        },
                        controllerUrl: G.path.moduleResPath+'/mscAuthRole/mscAuthRoleEditCtrl.js'
                    }
                }
            })
            .state('mscAuthDataList', {
                url: '/mscAuthDataList?pageNo&searchKey',
                views:{
                    "":{
                        controller: 'mscAuthDataListCtrl',
                        cache:'false',
                        templateUrl: function($routeParams){
                            var url=G.path.moduleTemplatePath+'/mscAuthData/list.html';
                            url=global.setParam(url,'pageNo',$routeParams.pageNo);
                            url=global.setParam(url,'searchKey',$routeParams.searchKey);
                            return global.reg(url);
                        },
                        controllerUrl: G.path.moduleResPath+'/mscAuthData/mscAuthDataListCtrl.js'
                    }
                }
            })
            .state('mscAuthDataEdit', {
                url: '/mscAuthDataEdit?id',
                views:{
                    "":{
                        controller: 'mscAuthDataEditCtrl',
                        templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscAuthData/edit.html?id='+$routeParams.id;},
                        controllerUrl: G.path.moduleResPath+'/mscAuthData/mscAuthDataEditCtrl.js'
                    }
                }
            })
            .state('mscAuthDataLook', {
                url: '/mscAuthDataLook?id',
                views:{
                    "":{
                        controller: 'mscAuthDataLookCtrl',
                        templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscAuthData/look.html?id='+$routeParams.id;},
                        controllerUrl: G.path.moduleResPath+'/mscAuthData/mscAuthDataLookCtrl.js'
                    }
                }
            })
            .state('mscAuthDataAdd', {
                url: '/mscAuthDataAdd',
                views:{
                    "":{
                        controller: 'mscAuthDataEditCtrl',
                        cache:'false',
                        templateUrl: function($routeParams){
                            var url=G.path.moduleTemplatePath+'/mscAuthData/add.html';
                            return global.reg(url);
                        },
                        controllerUrl: G.path.moduleResPath+'/mscAuthData/mscAuthDataEditCtrl.js'
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
