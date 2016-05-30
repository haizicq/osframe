/**
 * Created by wangchun on 15/9/16.
 */
define(function (require, exports, module) {
    var global=require("js/global");//加载全局js
    var isFirst=true;
    module.exports = function(app){
        //加载时再加载子页面需要的链接
        // 第二次加载时，会重复注册导致报错
        if(isFirst){
            isFirst=false;
            app.stateProvider.state('mscUsersTree.list', {
                url: '/mscUsersTreeList?id&pageNo&searchKey',
                views:{
                    "":{
                        controller: 'mscUsersInfoListCtrl',
                        cache:'false',
                        templateUrl:
                            function($routeParams){
                                var url=G.path.moduleTemplatePath+'/mscUsersInfo/list.html';
                                if($routeParams.id!=null && $routeParams.id!=''){
                                    url+="?id="+$routeParams.id;
                                }
                                url=global.setParam(url,'pageNo',$routeParams.pageNo);
                                url=global.setParam(url,'searchKey',$routeParams.searchKey);
                                return url;},
                        controllerUrl: G.path.moduleResPath+'/mscUsersInfo/mscUsersInfoListCtrl.js'
                    }
                }
            }).state('mscUsersTree.orgEdit', {
                url: '/mscUsersOrgEdit?bid',
                views:{
                    "":{
                        controller: 'mscUsersOrgEditCtrl',
                        cache:'false',
                        templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscUsersOrg/edit.html?bid='+$routeParams.bid;},
                        controllerUrl: G.path.moduleResPath+'/mscUsersOrg/mscUsersOrgEditCtrl.js'
                    }
                }
            })
                .state('mscUsersTree.orgLook', {
                    url: '/mscUsersOrgLook?bid',
                    views:{
                        "":{
                            controller: 'mscUsersOrgLookCtrl',
                            cache:'false',
                            templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscUsersOrg/look.html?bid='+$routeParams.bid;},
                            controllerUrl: G.path.moduleResPath+'/mscUsersOrg/mscUsersOrgLookCtrl.js'
                        }
                    }
                })
                .state('mscUsersTree.orgAdd', {
                    url: '/mscUsersOrgAdd?pid',
                    views:{
                        "":{
                            controller: 'mscUsersOrgEditCtrl',
                            cache:'false',
                            templateUrl:function($routeParams){return  G.path.moduleTemplatePath+'/mscUsersOrg/add.html?pid='+$routeParams.pid;},
                            controllerUrl: G.path.moduleResPath+'/mscUsersOrg/mscUsersOrgEditCtrl.js'
                        }
                    }
                })
                .state('mscUsersTree.personEdit', {
                    url: '/mscUsersPersonEdit?bid',
                    views:{
                        "":{
                            controller: 'mscUsersPersonEditCtrl',
                            cache:'false',
                            templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscUsersPerson/edit.html?bid='+$routeParams.bid;},
                            controllerUrl: G.path.moduleResPath+'/mscUsersPerson/mscUsersPersonEditCtrl.js'
                        }
                    }
                })
                .state('mscUsersTree.personLook', {
                    url: '/mscUsersPersonLook?bid',
                    views:{
                        "":{
                            controller: 'mscUsersPersonLookCtrl',
                            cache:'false',
                            templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscUsersPerson/look.html?bid='+$routeParams.bid;},
                            controllerUrl: G.path.moduleResPath+'/mscUsersPerson/mscUsersPersonLookCtrl.js'
                        }
                    }
                })
                .state('mscUsersTree.personAdd', {
                    url: '/mscUsersPersonAdd?pid',
                    views:{
                        "":{
                            controller: 'mscUsersPersonEditCtrl',
                            cache:'false',
                            templateUrl:function($routeParams){return   G.path.moduleTemplatePath+'/mscUsersPerson/add.html?pid='+$routeParams.pid;},
                            controllerUrl: G.path.moduleResPath+'/mscUsersPerson/mscUsersPersonEditCtrl.js'
                        }
                    }
                })
                .state('mscUsersTree.jobEdit', {
                    url: '/mscUsersJobEdit?bid',
                    views:{
                        "":{
                            controller: 'mscUsersJobEditCtrl',
                            cache:'false',
                            templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscUsersJob/edit.html?bid='+$routeParams.bid;},
                            controllerUrl: G.path.moduleResPath+'/mscUsersJob/mscUsersJobEditCtrl.js'
                        }
                    }
                })
                .state('mscUsersTree.jobLook', {
                    url: '/mscUsersJobLook?bid',
                    views:{
                        "":{
                            controller: 'mscUsersJobLookCtrl',
                            cache:'false',
                            templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscUsersJob/look.html?bid='+$routeParams.bid;},
                            controllerUrl: G.path.moduleResPath+'/mscUsersJob/mscUsersJobLookCtrl.js'
                        }
                    }
                })
                .state('mscUsersTree.jobAdd', {
                    url: '/mscUsersJobAdd?pid',
                    views:{
                        "":{
                            controller: 'mscUsersJobEditCtrl',
                            cache:'false',
                            templateUrl:function($routeParams){return  G.path.moduleTemplatePath+'/mscUsersJob/add.html?pid='+$routeParams.pid;},
                            controllerUrl: G.path.moduleResPath+'/mscUsersJob/mscUsersJobEditCtrl.js'
                        }
                    }
                })
                .state('mscUsersTree.rePwd', {
                    url: '/mscUsersPersonRePwd?id',
                    views:{
                        "":{
                            controller: 'mscUsersPersonRePwdCtrl',
                            cache:'false',
                            templateUrl: function($routeParams){return G.path.moduleTemplatePath+'/mscUsersPerson/rePwd.html?id='+$routeParams.id;},
                            controllerUrl: G.path.moduleResPath+'/mscUsersPerson/mscUsersPersonRePwdCtrl.js'
                        }
                    }
                });
        }

                //添加页面控制器的ctrl
        app.controller('mscUsersTreeCtrl', ['$scope','$state', function($scope,$state) {

            seajs.use(['js/libs/ztree/jquery.ztree.all.min'],function(){
                var setting = {
                    async: {
                        enable: true,
                        url: G.path.modulePath+"/mscUsersInfo/tree.html",
                        autoParam:["id", "name=n", "level=lv"],
                        otherParam:{"otherParam":"zTreeAsyncTest"},
                        dataFilter: filter
                    },
                    callback: {
                        onClick: function(e1,e2,e3){
                            //console.info(" e3="+e3.id)
                            $state.go("mscUsersTree.list",{id:e3.id});//点击确认后直接跳转到列表页面
                        }
                    }
                };

                function filter(treeId, parentNode, childNodes) {
                    if (!childNodes) return null;
                    for (var i=0, l=childNodes.length; i<l; i++) {
                        childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
                    }
                    return childNodes;
                }


                $(document).ready(function(){
                    var t = $("#user-tree");
                    t = $.fn.zTree.init(t, setting);
                    var zTree = $.fn.zTree.getZTreeObj("tree");
                });

            });

            $state.go('mscUsersTree.list');//点击确认后直接跳转到列表页面

        }]);
    }

});
