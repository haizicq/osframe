/**
 * Created by wangchun on 15/9/18.
 */
define(function (require, exports, module) {
    // Demonstrate how to register services

    //var global=require("js/global");//加载全局js
    var app= angular.module('app.services', []);
    app.factory('requestService',function($http){
        //请求数据
        var runRequest = function(url,formData,method){

            //url=global.reg(url);
            if(G.path.RootPath!=null && G.path.RootPath!="" && G.path.RootPath!="/"){
                //当url不为空，且以/开头的，直接拼接项目根路径
                if(url!=null && url!="" && url.substring(0,1)=="/"){
                    url=G.path.RootPath+url;
                }
            }
            if(method==undefined || method==null || method==""){
                method='POST';//默认为post
            }
            return $http({method:method,url:url,params:formData});
        };
        return {
            postData:function(url,formData){
                return runRequest(url,formData,'POST');
            },
            getData:function(url,formData){
                return runRequest(url,formData,'GET');
            },
            submit:function(url,formData){//暂时所有提交直接使用，页面跳转自行处理
                //TODO 自带成功和失败的页面跳转，待后续实现
                return runRequest(url,formData);
            }
        };
    }).factory('dataService',function($http){

        //请求数据
        var runRequest = function(url,method){
            if(method==undefined || method==null || method==""){
                method='POST';//默认为post
            }
            //url=global.reg(url);
            return $http({method:method,url:url,params:null});
        };
        return {
            bean:function(bean,search){
                if(bean==null || bean=="" || bean==undefined || bean=="undefined"){//当不存在bean时，不做任何操作
                    return ;
                }
                //获取bean中的参数
                var params=bean.split("&");
                var url= G.path.RootPath+"/ajax/data/bean.html?beanName="+params[0];
                if(params.length>1){//循环添加bean中传递的参数
                    for(var i=1;i<params.length;i++){
                        url+="&"+params[i];
                    }
                }
                if(search!=null && search!=undefined && search!=""){
                    url+="&search="+search;//添加搜索
                }
                return runRequest(url,'GET');
            },
            domain:function(domain,name,fields,search){
                var url=G.path.RootPath+"/ajax/data/domain.html?domain="+domain+"&name="+name+"&fields="+fields+"&search="+search;

                return runRequest(url,'GET');
            }
        };
    });
    module.exports = app;
});