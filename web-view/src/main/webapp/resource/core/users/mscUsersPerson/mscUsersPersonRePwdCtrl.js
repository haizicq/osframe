/**
 * Created by wangchun on 15/9/16.
 */
define(function (require, exports, module) {
    var global=require("js/global");//加载全局js
    module.exports = function(app){
        //添加页面控制器的ctrl
        app.controller('mscUsersPersonRePwdCtrl', ['$scope','$state','requestService', function($scope,$state,requestService) {
            //重置密码
            $scope.submitForm=function(){
                var url='/msc/users/mscUsersPerson/rePwd.html';//这个模版路径会影响到 其他门户首页的引入
                requestService.submit(url,$scope.formData).success(function(response){
                    if(!global.isSuccess(response)){
                        return;
                    }
                    $state.go("mscUsersPersonLook",{"id":$scope.formData.usersId}, {reload: true});//修改成功后，跳转到查看页面
                });
            };
            //修改密码
            $scope.changeForm=function(){
                var url='/msc/users/mscUsersPerson/myPwd.html';//这个模版路径会影响到 其他门户首页的引入
                requestService.submit(url,$scope.formData).success(function(response){
                    if(!global.isSuccess(response)){
                        return;
                    }
                    $state.go("mscUsersPersonLook",{}, {reload: true});//修改成功后，跳转到个人信息页面
                });
            };
        }]);
    }

});
