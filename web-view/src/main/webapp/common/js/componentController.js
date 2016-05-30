/**
 * 组件控制器
 * Created by wangchun on 15/9/16.
 */
define(function (require, exports, module) {

    var dialog=require("js/dialog");//加载对话框
    var components=angular.module('app.components', ['pascalprecht.translate', 'ngCookies','ngStorage','app.services']);
    components.controller('AppCtrl', ['$scope', '$translate', '$localStorage', '$window',
        function($scope,   $translate, $localStorage,   $window ) {
            // add 'ie' classes to html
            var isIE = !!navigator.userAgent.match(/MSIE/i);
            isIE && angular.element($window.document.body).addClass('ie');
            isSmartDevice( $window ) && angular.element($window.document.body).addClass('smart');
            // config
            $scope.app = {
                name: '企业信息系统',
                version: '1.1.3',
                // for chart colors
                color: {
                    primary: '#7266ba',
                    info:    '#23b7e5',
                    success: '#27c24c',
                    warning: '#fad733',
                    danger:  '#f05050',
                    light:   '#e8eff0',
                    dark:    '#3a3f51',
                    black:   '#1c2b36'
                },
                settings: {
                    themeID: 1,
                    navbarHeaderColor: 'bg-black',
                    navbarCollapseColor: 'bg-white-only',
                    asideColor: 'bg-black',
                    headerFixed: true,
                    asideFixed: false,
                    asideFolded: false
                }
            }
            $scope.userName=G.path.UserName;
            // save settings to local storage
            if ( angular.isDefined($localStorage.settings) ) {
                $scope.app.settings = $localStorage.settings;
            } else {
                $localStorage.settings = $scope.app.settings;
            }
            $scope.$watch('app.settings', function(){ $localStorage.settings = $scope.app.settings; }, true);

            // angular translate
            $scope.lang = { isopen: false };
            $scope.langs = {en:'English', zh_CN:'中文'};
            $scope.selectLang = $scope.langs[$translate.proposedLanguage()] || "中文";
            $scope.setLang = function(langKey, $event) {
                // set the current lang
                $scope.selectLang = $scope.langs[langKey];
                // You can change the language during runtime
                $translate.use(langKey);
                $scope.lang.isopen = !$scope.lang.isopen;
            };

            function isSmartDevice( $window )
            {
                // Adapted from http://www.detectmobilebrowsers.com
                var ua = $window['navigator']['userAgent'] || $window['navigator']['vendor'] || $window['opera'];
                // Checks for iOs, Android, Blackberry, Opera Mini, and Windows mobile devices
                return (/iPhone|iPod|iPad|Silk|Android|BlackBerry|Opera Mini|IEMobile/).test(ua);
            }

        }]).controller('loginFormController', ['$cookies','$cookieStore','$scope','$state','$location', '$http', function ($cookies,$cookieStore,$scope,$state,$location, $http) {//登陆控制器可能随时会出现超时页面

            $scope.authError=false;
            $scope.loginForm = {};
            //这种方式页面被重新跳转了，导致之前的url地址无法重新获取到,采用 $location.absUrl() 获取url，然后拼接到target中去
            var targetUrl=encodeURIComponent($location.absUrl());
            window.open(G.path.RootPath+"/login.html?targetUrl="+targetUrl,"_self");
        }]);
    module.exports = components;

});