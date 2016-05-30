/**
 * Created by wangchun on 15/9/16.
 */

    var app = angular.module('loginApp', ['ui.router']);

    //添加页面控制器的ctrl
    app.controller('loginFormController', ['$scope','$state','$location', '$http', function ($scope,$state,$location, $http) {
        $scope.authError=false;
        $scope.loginForm = {};
        $scope.login = function () {
            seajs.use(G.path.ResPath+'/js/global.js',
                function(global){
                    var url = G.path.RootPath+'/submitLogin.html';//这个模版路径会影响到 其他门户首页的引入
                    //获取跳转的url，如果存在则添加上，如果不存在则不处理
                    var targetUrl=global.getParam($location.absUrl(),"targetUrl");
                    if(targetUrl!=undefined && targetUrl!=null && targetUrl!=''){
                        $scope.loginForm.targetUrl=targetUrl;
                    }
                    var postObj=$http({method:"POST",url:url,params:$scope.loginForm});
                    postObj.success(function(response){
                        if(response.error==0){
                            $scope.authError=true;
                            $scope.errorInfo=response.code+"-"+response.msg;
                        }else{
                            if(response.url!=null && response.url!=""){
                                window.open(response.url,"_self");
                            }else{
                                window.open(G.path.RootPath+"/index.html","_self");
                            }

                        }
                    });
                });


        };


    }]);
