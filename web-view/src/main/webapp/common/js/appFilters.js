/**
 * 用于拦截字符串等操作
 * Created by wangchun on 15/9/18.
 */
define(function (require, exports, module) {
    var app=angular.module('app.filters', [])
        .filter('fromNow', function() {
            return function(date) {
                return moment(date).fromNow();
            }
        });
    module.exports = app;
});