/**
 * 数据获取
 * Created by wangdc on 2015-5-7.
 */

define(function(require, exports, module) {
    //var $ = require('jquery');
    /**
     * 通过ajax获取json数据，后拼装对话框展示
     * @param bean 类名及参数 参数以&链接 ，如：beanName&param1=aa&param2=bb
     * @param callback 回调函数
     */
    exports.bean=function(bean,callback,search){
        if(bean==null || bean=="" || bean==undefined || bean=="undefined"){//当不存在bean时，不做任何操作
            return;
        }
        //获取bean中的参数
        var params=bean.split("&");
        var url= G.path.RootPath+"/ajax/data/bean.html?beanName="+params[0];//在cdn中是否支持跨域呢？
        if(params.length>1){//循环添加bean中传递的参数
            for(var i=1;i<params.length;i++){
                url+="&"+params[i];
            }
        }
        if(search!=null && search!=undefined && search!=""){
            url+="&search="+search;//添加搜索
        }
        $.ajax({
            url: url ,
            success: function(obj){
                //同步和异步
                if(typeof(callback)=="function"){//判断为函数时执行回调
                    callback(obj);
                }
            },error:function(){
                alert("error");
            }
        });
    }
    /**
     * 根据域模型获取列表数据
     *
     *      可能存在风险问题，用户可以通过这个获取到所有表的数据
     *      控制：1、模型配置是否允许这种方式获取，2、数据权限配置，是否有权限访问该调数据（查询拦截器）
     * @param domain
     * @param name
     * @param fields
     * @param callback
     * @param search
     */
    exports.domain=function(domain,name,fields,callback,search){
        //获取bean中的参数
        var url=G.path.RootPath+"/ajax/data/domain.html?domain="+domain+"&name="+name+"&fields="+fields+"&search="+search;

        $.ajax({
            url: url ,
            success: function(obj){
                if(typeof(callback)=="function"){//判断为函数时执行回调
                    callback(obj);
                }
            },error:function(){
                alert("error");
            }
        });
    }
});