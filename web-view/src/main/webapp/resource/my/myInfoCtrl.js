/**
 * Created by wangchun on 15/9/16.
 */
define(function (require, exports, module) {
    var global=require("js/global");//加载全局js
    module.exports = function(app){
        //添加页面控制器的ctrl
        app.controller('myInfoCtrl', ['$scope','requestService','$state', function($scope,requestService,$state) {
            $scope.saveInfo=function(){
                var url='/my/saveInfo.html';//这个模版路径会影响到 其他门户首页的引入
                requestService.submit(url,$scope.formData).success(function(response){
                    if(!global.isSuccess(response)){
                        return;
                    }
                    $state.go("info",{}, {reload: true});//修改成功后，跳转到个人信息页面
                });
            }


        }]);
    }

});
