/**
 * Created by wangchun on 15/9/16.
 */
define(function (require, exports, module) {
    var global=require("js/global");//加载全局js
    module.exports = function(app){
        //添加页面控制器的ctrl
        app.controller('portalCtrl', ['$scope','$http', function($scope,$http) {

            //获取当前时间
            var now1=new Date();
            //让时间在页面显示
            $scope.nowDate=now1.getFullYear()+"-"+(now1.getMonth()+1)+"-"+now1.getDate()+" "+now1.getHours()+':'+now1.getMinutes()+':'+now1.getSeconds();

            //写一个方法获取当前时间
            $scope.SetTimer=function(){
                //angularJs的特性，需要手动把变化映射到html元素上面
                $scope.$apply(function(){
                    var now=new Date();
                    $scope.nowDate=now1.getFullYear()+"-"+(now1.getMonth()+1)+"-"+now1.getDate()+" "+now.getHours()+':'+now.getMinutes()+':'+now.getSeconds();
                });
            };
            //每隔1秒刷新一次时间
            $scope.SetTimerInterval=setInterval($scope.SetTimer,1000);


            //图片滚动
            $scope.myInterval = 5000;
            var slides = $scope.slides = [];
            $scope.addSlide = function() {
                slides.push({
                    image: G.path.RootPath+'/common/img/y' + (slides.length+1) + '.jpg'
                });
            };
            for (var i=1; i<=4; i++) {
                $scope.addSlide();
            }

            //发起ajax请求获取最新的DEMO数据展示在portlet窗口
            var url=global.reg(G.path.RootPath+"/os/demo/osDemoInfo/new.html");
            $http({method:'GET',url:url,params:null}).success(function (response) {
                if(response==null){
                    $scope.demoStatus=0;
                    $scope.demoMessage="没有加载到数据数据";
                    return ;
                }else if(response.status==0){
                    dialog.alert("操作失败,"+response.code+"-"+response.message);
                    $scope.demoStatus=0;
                    $scope.demoMessage="加载失败,"+response.code+"-"+response.message;
                    return;
                }
                var data=response.data;
                if(data==undefined || data==null || data.length<=0){//没有数据时提示没有加载到数据

                    $scope.demoStatus=0;
                    $scope.demoMessage="没有加载到数据数据";
                    return ;
                }
                //循环输出数据到portlet中
                $scope.demoStatus=1;
                $scope.demoData=response.data;;

                //<article class="media">
                //    <div class="media-body">
                //    <a href class="h4">Bootstrap 3: What you need to know</a>
                //<small class="block m-t-xs">Sleek, intuitive, and powerful mobile-first front-end framework for faster and easier web development.</small>
                //<em class="text-xs">Posted on <span class="text-danger">Feb 23, 2013</span></em>
                //</div>
                //</article>
            });
        }]);
    }

});
