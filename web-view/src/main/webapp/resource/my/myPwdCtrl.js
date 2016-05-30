/**
 * Created by wangchun on 15/9/16.
 */
define(function (require, exports, module) {
    var global=require("js/global");//加载全局js
    module.exports = function(app){
        //添加页面控制器的ctrl
        app.controller('myPwdCtrl', ['$scope','requestService','$state', function($scope,requestService,$state) {
            $scope.formData={};
            //修改密码
            $scope.submitForm=function(){
                var url='/my/savePwd.html';//这个模版路径会影响到 其他门户首页的引入
                requestService.submit(url,$scope.formData).success(function(response){
                    if(!global.isSuccess(response)){
                        return;
                    }
                    //密码修改成功后调整到个人信息界面
                    $state.go("info",{}, {reload: true});
                });
            };

        }]);
    }

});
