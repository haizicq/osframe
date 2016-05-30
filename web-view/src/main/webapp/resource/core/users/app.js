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

            //app.controller = $controllerProvider.register;
            //app.directive  = $compileProvider.directive;
            //app.filter     = $filterProvider.register;
            //app.factory    = $provide.factory;
            //app.service    = $provide.service;
            //app.constant   = $provide.constant;
            //app.value      = $provide.value;
                //配置模块链接路径：中文名、链接名、图标地址、链接地址、控制器名、模版url、控制器url
                //链接名：app作为根项目路径，app.module.name
                //如何将这些路径放到配置文件中来读取
                //首页地址读取配置 链接配置，通过AJAX读取这些配置
            $urlRouterProvider.otherwise('/mscUsersTree');
            $stateProvider
                .state('mscUsersTree', {
                    url: '/mscUsersTree',
                    views:{
                        "":{
                            controller: 'mscUsersTreeCtrl',
                            cache:'false',
                            templateUrl: function($routeParams){
                                var url=G.path.moduleTemplatePath+'/mscUsersInfo/treeList.html';
                                return url;
                            },
                            controllerUrl: G.path.moduleResPath+'/mscUsersInfo/mscUsersTreeCtrl.js'
                        }
                    }
                })

            .state('mscUsersOrgList', {
                url: '/mscUsersOrgList?pageNo&searchKey',
                views:{
                    "":{
                        controller: 'mscUsersOrgListCtrl',
                        cache:'false',
                        templateUrl: function($routeParams){
                            var url=G.path.moduleTemplatePath+'/mscUsersOrg/list.html';
                            url=global.setParam(url,'pageNo',$routeParams.pageNo);
                            url=global.setParam(url,'searchKey',$routeParams.searchKey);
                            return url;
                        },
                        controllerUrl: G.path.moduleResPath+'/mscUsersOrg/mscUsersOrgListCtrl.js'
                    }
                }
            })
            .state('mscUsersOrgEdit', {
                url: '/mscUsersOrgEdit?id',
                views:{
                    "":{
                        controller: 'mscUsersOrgEditCtrl',
                        cache:'false',
                        templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscUsersOrg/edit.html?id='+$routeParams.id;},
                        controllerUrl: G.path.moduleResPath+'/mscUsersOrg/mscUsersOrgEditCtrl.js'
                    }
                }
            })
            .state('mscUsersOrgLook', {
                url: '/mscUsersOrgLook?id',
                views:{
                    "":{
                        controller: 'mscUsersOrgLookCtrl',
                        cache:'false',
                        templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscUsersOrg/look.html?id='+$routeParams.id;},
                        controllerUrl: G.path.moduleResPath+'/mscUsersOrg/mscUsersOrgLookCtrl.js'
                    }
                }
            })
            .state('mscUsersOrgAdd', {
                url: '/mscUsersOrgAdd',
                views:{
                    "":{
                        controller: 'mscUsersOrgEditCtrl',
                        cache:'false',
                        templateUrl: G.path.moduleTemplatePath+'/mscUsersOrg/add.html',
                        controllerUrl: G.path.moduleResPath+'/mscUsersOrg/mscUsersOrgEditCtrl.js'
                    }
                }
            })
            .state('mscUsersPersonList', {
                url: '/mscUsersPersonList?pageNo&searchKey',
                views:{
                    "":{
                        controller: 'mscUsersPersonListCtrl',
                        cache:'false',
                        templateUrl: function($routeParams){
                            var url=G.path.moduleTemplatePath+'/mscUsersPerson/list.html';
                            url=global.setParam(url,'pageNo',$routeParams.pageNo);
                            url=global.setParam(url,'searchKey',$routeParams.searchKey);
                            return url;
                        },
                        controllerUrl: G.path.moduleResPath+'/mscUsersPerson/mscUsersPersonListCtrl.js'
                    }
                }
            })
            .state('mscUsersPersonEdit', {
                url: '/mscUsersPersonEdit?id',
                views:{
                    "":{
                        controller: 'mscUsersPersonEditCtrl',
                        cache:'false',
                        templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscUsersPerson/edit.html?id='+$routeParams.id;},
                        controllerUrl: G.path.moduleResPath+'/mscUsersPerson/mscUsersPersonEditCtrl.js'
                    }
                }
            })
            .state('mscUsersPersonLook', {
                url: '/mscUsersPersonLook?id',
                views:{
                    "":{
                        controller: 'mscUsersPersonLookCtrl',
                        cache:'false',
                        templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscUsersPerson/look.html?id='+$routeParams.id;},
                        controllerUrl: G.path.moduleResPath+'/mscUsersPerson/mscUsersPersonLookCtrl.js'
                    }
                }
            })
            .state('mscUsersPersonAdd', {
                url: '/mscUsersPersonAdd',
                views:{
                    "":{
                        controller: 'mscUsersPersonEditCtrl',
                        cache:'false',
                        templateUrl: G.path.moduleTemplatePath+'/mscUsersPerson/add.html',
                        controllerUrl: G.path.moduleResPath+'/mscUsersPerson/mscUsersPersonEditCtrl.js'
                    }
                }
            })
            .state('mscUsersJobList', {
                url: '/mscUsersJobList?pageNo&searchKey',
                views:{
                    "":{
                        controller: 'mscUsersJobListCtrl',
                        cache:'false',
                        templateUrl: function($routeParams){
                            var url=G.path.moduleTemplatePath+'/mscUsersJob/list.html';
                            url=global.setParam(url,'pageNo',$routeParams.pageNo);
                            url=global.setParam(url,'searchKey',$routeParams.searchKey);
                            return url;
                        },
                        controllerUrl: G.path.moduleResPath+'/mscUsersJob/mscUsersJobListCtrl.js'
                    }
                }
            })
            .state('mscUsersJobEdit', {
                url: '/mscUsersJobEdit?id',
                views:{
                    "":{
                        controller: 'mscUsersJobEditCtrl',
                        cache:'false',
                        templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscUsersJob/edit.html?id='+$routeParams.id;},
                        controllerUrl: G.path.moduleResPath+'/mscUsersJob/mscUsersJobEditCtrl.js'
                    }
                }
            })
            .state('mscUsersJobLook', {
                url: '/mscUsersJobLook?id',
                views:{
                    "":{
                        controller: 'mscUsersJobLookCtrl',
                        cache:'false',
                        templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscUsersJob/look.html?id='+$routeParams.id;},
                        controllerUrl: G.path.moduleResPath+'/mscUsersJob/mscUsersJobLookCtrl.js'
                    }
                }
            })
            .state('mscUsersJobAdd', {
                url: '/mscUsersJobAdd',
                views:{
                    "":{
                        controller: 'mscUsersJobEditCtrl',
                        cache:'false',
                        templateUrl: G.path.moduleTemplatePath+'/mscUsersJob/add.html',
                        controllerUrl: G.path.moduleResPath+'/mscUsersJob/mscUsersJobEditCtrl.js'
                    }
                }
            })
            .state('mscUsersJobTypeList', {
                url: '/mscUsersJobTypeList?pageNo&searchKey',
                views:{
                    "":{
                        controller: 'mscUsersJobTypeListCtrl',
                        cache:'false',
                        templateUrl:function($routeParams){
                            var url=G.path.moduleTemplatePath+'/mscUsersJobType/list.html';
                            url=global.setParam(url,'pageNo',$routeParams.pageNo);
                            url=global.setParam(url,'searchKey',$routeParams.searchKey);
                            return url;
                        },
                        controllerUrl: G.path.moduleResPath+'/mscUsersJobType/mscUsersJobTypeListCtrl.js'
                    }
                }
            })
            .state('mscUsersJobTypeEdit', {
                url: '/mscUsersJobTypeEdit?id',
                views:{
                    "":{
                        controller: 'mscUsersJobTypeEditCtrl',
                        cache:'false',
                        templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscUsersJobType/edit.html?id='+$routeParams.id;},
                        controllerUrl: G.path.moduleResPath+'/mscUsersJobType/mscUsersJobTypeEditCtrl.js'
                    }
                }
            })
            .state('mscUsersJobTypeLook', {
                url: '/mscUsersJobTypeLook?id',
                views:{
                    "":{
                        controller: 'mscUsersJobTypeLookCtrl',
                        cache:'false',
                        templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscUsersJobType/look.html?id='+$routeParams.id;},
                        controllerUrl: G.path.moduleResPath+'/mscUsersJobType/mscUsersJobTypeLookCtrl.js'
                    }
                }
            })
            .state('mscUsersJobTypeAdd', {
                url: '/mscUsersJobTypeAdd',
                views:{
                    "":{
                        controller: 'mscUsersJobTypeEditCtrl',
                        cache:'false',
                        templateUrl: G.path.moduleTemplatePath+'/mscUsersJobType/add.html',
                        controllerUrl: G.path.moduleResPath+'/mscUsersJobType/mscUsersJobTypeEditCtrl.js'
                    }
                }
            })
            .state('mscUsersGroupTypeList', {
                url: '/mscUsersGroupTypeList?pageNo&searchKey',
                views:{
                    "":{
                        controller: 'mscUsersGroupTypeListCtrl',
                        cache:'false',
                        templateUrl: function($routeParams){
                            var url=G.path.moduleTemplatePath+'/mscUsersGroupType/list.html';
                            url=global.setParam(url,'pageNo',$routeParams.pageNo);
                            url=global.setParam(url,'searchKey',$routeParams.searchKey);
                            return url;
                        },
                        controllerUrl: G.path.moduleResPath+'/mscUsersGroupType/mscUsersGroupTypeListCtrl.js'
                    }
                }
            })
            .state('mscUsersGroupTypeEdit', {
                url: '/mscUsersGroupTypeEdit?id',
                views:{
                    "":{
                        controller: 'mscUsersGroupTypeEditCtrl',
                        cache:'false',
                        templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscUsersGroupType/edit.html?id='+$routeParams.id;},
                        controllerUrl: G.path.moduleResPath+'/mscUsersGroupType/mscUsersGroupTypeEditCtrl.js'
                    }
                }
            })
            .state('mscUsersGroupTypeLook', {
                url: '/mscUsersGroupTypeLook?id',
                views:{
                    "":{
                        controller: 'mscUsersGroupTypeLookCtrl',
                        cache:'false',
                        templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscUsersGroupType/look.html?id='+$routeParams.id;},
                        controllerUrl: G.path.moduleResPath+'/mscUsersGroupType/mscUsersGroupTypeLookCtrl.js'
                    }
                }
            })
            .state('mscUsersGroupTypeAdd', {
                url: '/mscUsersGroupTypeAdd',
                views:{
                    "":{
                        controller: 'mscUsersGroupTypeEditCtrl',
                        cache:'false',
                        templateUrl: G.path.moduleTemplatePath+'/mscUsersGroupType/add.html',
                        controllerUrl: G.path.moduleResPath+'/mscUsersGroupType/mscUsersGroupTypeEditCtrl.js'
                    }
                }
            })
            .state('mscUsersGroupList', {
                url: '/mscUsersGroupList?pageNo&searchKey',
                views:{
                    "":{
                        controller: 'mscUsersGroupListCtrl',
                        cache:'false',
                        templateUrl:function($routeParams){
                            var url=G.path.moduleTemplatePath+'/mscUsersGroup/list.html';
                            url=global.setParam(url,'pageNo',$routeParams.pageNo);
                            url=global.setParam(url,'searchKey',$routeParams.searchKey);
                            return url;
                        },
                        controllerUrl: G.path.moduleResPath+'/mscUsersGroup/mscUsersGroupListCtrl.js'
                    }
                }
            })
            .state('mscUsersGroupEdit', {
                url: '/mscUsersGroupEdit?id',
                views:{
                    "":{
                        controller: 'mscUsersGroupEditCtrl',
                        cache:'false',
                        templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscUsersGroup/edit.html?id='+$routeParams.id;},
                        controllerUrl: G.path.moduleResPath+'/mscUsersGroup/mscUsersGroupEditCtrl.js'
                    }
                }
            })
            .state('mscUsersGroupLook', {
                url: '/mscUsersGroupLook?id',
                views:{
                    "":{
                        controller: 'mscUsersGroupLookCtrl',
                        cache:'false',
                        templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscUsersGroup/look.html?id='+$routeParams.id;},
                        controllerUrl: G.path.moduleResPath+'/mscUsersGroup/mscUsersGroupLookCtrl.js'
                    }
                }
            })
            .state('mscUsersGroupAdd', {
                url: '/mscUsersGroupAdd',
                views:{
                    "":{
                        controller: 'mscUsersGroupEditCtrl',
                        cache:'false',
                        templateUrl: G.path.moduleTemplatePath+'/mscUsersGroup/add.html',
                        controllerUrl: G.path.moduleResPath+'/mscUsersGroup/mscUsersGroupEditCtrl.js'
                    }
                }
            })
            .state('mscUsersGroupJobList', {
                url: '/mscUsersGroupJobList?pageNo&searchKey',
                views:{
                    "":{
                        controller: 'mscUsersGroupJobListCtrl',
                        cache:'false',
                        templateUrl: function($routeParams){
                            var url=G.path.moduleTemplatePath+'/mscUsersGroupJob/list.html';
                            url=global.setParam(url,'pageNo',$routeParams.pageNo);
                            url=global.setParam(url,'searchKey',$routeParams.searchKey);
                            return url;
                        },
                        controllerUrl: G.path.moduleResPath+'/mscUsersGroupJob/mscUsersGroupJobListCtrl.js'
                    }
                }
            })
            .state('mscUsersGroupJobEdit', {
                url: '/mscUsersGroupJobEdit?id',
                views:{
                    "":{
                        controller: 'mscUsersGroupJobEditCtrl',
                        cache:'false',
                        templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscUsersGroupJob/edit.html?id='+$routeParams.id;},
                        controllerUrl: G.path.moduleResPath+'/mscUsersGroupJob/mscUsersGroupJobEditCtrl.js'
                    }
                }
            })
            .state('mscUsersGroupJobLook', {
                url: '/mscUsersGroupJobLook?id',
                views:{
                    "":{
                        controller: 'mscUsersGroupJobLookCtrl',
                        cache:'false',
                        templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscUsersGroupJob/look.html?id='+$routeParams.id;},
                        controllerUrl: G.path.moduleResPath+'/mscUsersGroupJob/mscUsersGroupJobLookCtrl.js'
                    }
                }
            })
            .state('mscUsersGroupJobAdd', {
                url: '/mscUsersGroupJobAdd',
                views:{
                    "":{
                        controller: 'mscUsersGroupJobEditCtrl',
                        cache:'false',
                        templateUrl: G.path.moduleTemplatePath+'/mscUsersGroupJob/add.html',
                        controllerUrl: G.path.moduleResPath+'/mscUsersGroupJob/mscUsersGroupJobEditCtrl.js'
                    }
                }
            })
                .state('mscUsersPersonMyPwd', {
                    url: '/mscUsersPersonMyPwd',
                    views:{
                        "":{
                            controller: 'mscUsersPersonMyPwdCtrl',
                            cache:'false',
                            templateUrl: G.path.moduleTemplatePath+'/mscUsersPerson/myPwd.html',
                            controllerUrl: G.path.moduleResPath+'/mscUsersPerson/mscUsersPersonRePwdCtrl.js'
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
