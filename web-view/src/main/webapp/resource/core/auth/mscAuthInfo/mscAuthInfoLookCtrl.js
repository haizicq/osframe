/**
 * Created by wangchun on 15/9/16.
 */
define(function (require, exports, module) {

    var global=require("js/global");//加载全局js
    var dialog=require("js/dialog");//加载对话框
    module.exports = function(app){
        //添加页面控制器的ctrl
        app.controller('mscAuthInfoLookCtrl',  ['$scope','$state','requestService', function($scope,$state,requestService) {

        }]);
    }

});
