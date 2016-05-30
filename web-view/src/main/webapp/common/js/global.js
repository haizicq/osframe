/***********************************************
JS文件说明：
全局函数JS文件，默认已经在head.jsp中引入。
作者：王大春
***********************************************/

define(function(require, exports, module) {

    /***********************************************
     功能：获取函数
     ***********************************************/
    exports.this=function reThis(){
        return exports;
    }
    /***********************************************
     功能：关闭窗口
     ***********************************************/
    exports.close=function closeWindow() {
        window.close();
    }
    /***********************************************
     功能：刷新页面
     ***********************************************/
    exports.refresh=function refreshWindow() {
        //history.go(0);
        location.reload();
    }

    /***********************************************
     功能：设置窗口的标题
     各子页面可自行设置自己的标题
     默认获取LbSubject或LbName设置
     参数：
     Title：标题文本
     ***********************************************/
    exports.setTitle=function setWindowTitle(title){
        try{
            document.title = title;
        }catch(err){
        }
    }

    /***********************************************
     功能：打开一个新窗口
     页面模式：基于浏览器的页面模式
     框架模式：所有链接都在框架中打开(后期实现)
     参数：
     url：打开页面链接
     target：目标
     返回：新窗口
     ***********************************************/
    exports.open=function windowOpen(url, target){
        if(target==null){
            target='_self';
        }
        return window.open(url, target);
    }
    /**
     * 在新窗口打开
     *  用于当在框架内时的打开新窗口的调整
     *  当简单框架可以直接打开self，外部可以直接_blank，当需要其他页签框架可以用响应的方法
     * @param url 打开地址
     * @returns {Window} 新窗口
     */
    exports.newopen=function windowNewOpen(url){
        return window.open(url, '_self');
    }
    /**
     * 给url添加随机数，
     * @param url 打开地址
     * @returns url
     */
    exports.reg=function urlReg(url){
        //var regNum=parseInt(this.getParam(url,"regNum"));
        //regNum = isNaN(regNum)?1:regNum+1;
        var regNum=new Date().getTime();
        url=this.setParam(url,"regNum",regNum);
        return url;
    }
    /**
     * 获取随机数对象
     * @returns {{regNum: number}}
     */
    exports.regNum=function urlRegNum(){
        var regNum=new Date().getTime();
        return {"regNum":regNum};
    }
    /***********************************************
     功能：拷贝当前URL的参数拷贝到指定的URL中
     参数：
     url：目标URL
     返回：拷贝后的新的URL
     ***********************************************/
    exports.copyParam=function Com_CopyParameter(url, except){
        if(location.search=="")
            return url;
        var paraList = location.search.substring(1).split("&");
        var i, j, k, para, value;
        copyParameterOutLoop:
            for(i=0; i<paraList.length; i++){
                j = paraList[i].indexOf("=");
                if(j==-1)
                    continue;
                para = paraList[i].substring(0, j);
                if(except!=null){
                    if(except[0]!=null){
                        for(k=0; k<except.length; k++)
                            if(para==except[k])
                                continue copyParameterOutLoop;
                    }else if(para==except){
                        continue;
                    }
                }
                value = this.getParam(url, para);
                if(value==null)
                    url = this.setParam(url, para, decodeURIComponent(paraList[i].substring(j+1)));
            }
        return url;
    }
    /***********************************************
     功能：设置URL参数，若参数不存在则添加一个，否则覆盖原有参数
     参数：
     url：URL
     param：参数名
     value：参数值
     返回：URL
     ***********************************************/
    exports.setParam=function setUrlParam(url, param,value){
        var re = new RegExp();
        re.compile("([\\?&]"+param+"=)[^&]*", "i");
        if(value==null){
            if(re.test(url)){
                url = url.replace(re, "");
            }
        }else{
            value = encodeURIComponent(value);
            if(re.test(url)){
                url = url.replace(re, "$1"+value);
            }else{
                url += (url.indexOf("?")==-1?"?":"&") + param + "=" + value;
            }
        }
        if(url.charAt(url.length-1)=="?")
            url = url.substring(0, url.length-1);
        return url;
    }

    /***********************************************
     功能：获取链接参数
     参数：
     url：链接
     param：参数名
     返回：参数值
     ***********************************************/
    exports.getParam=function getUrlParam(url, param){
        var re = new RegExp();
        re.compile("[\\?&]"+param+"=([^&]*)", "i");
        var arr = re.exec(url);
        if(arr==null)
            return null;
        else
            return decodeURIComponent(arr[1]);
    }
    /***********************************************
     功能：去除空格
     ***********************************************/
    exports.trim=function trim(s){
        return s.replace(/(^\s*)|(\s*$)/g, "");
    }
    /***********************************************
     功能：是否成功返回json
     ***********************************************/
    exports.isSuccess=function success(json){
        var bool=true;
        seajs.use('js/dialog',function(dialog){
            if(json==null){
                dialog.alert("操作失败");
                bool=false;
                return false;
            }else if(json.status==0){
                    dialog.alert("操作失败,"+json.code+"-"+json.message);
                    bool=false;
            }else{
                    dialog.alert("操作成功");
            }
        });
        return bool;
    }

    /***********************************************
     功能：根据url获取指定的模型路径
     ***********************************************/
    exports.getModel=function model(url){
        var path=null;
        if(G.path.RootPath!='' && G.path.RootPath!='/' && url.indexOf(G.path.RootPath)>-1){//判断是否包含项目根路径
            path=url.substring(url.indexOf(G.path.RootPath)+G.path.RootPath.length+1);
        }else if(G.path.RootPath=='' || G.path.RootPath=='/'){
            if(url.indexOf("http://")>-1){
                path=url.substring(7);
            }else if(url.indexOf("https://")>-1) {
                path = url.substring(8);
            }
            if(path!=null){
                path=path.substring(path.indexOf("/")+1);
            }
        }
        if(path==null){
            return;
        }
        if(path.indexOf("#")>-1){
            path=path.substring(0,path.indexOf("#"));
        }
        if(path.indexOf("?")>-1){//去除？或#
            path=path.substring(0,path.indexOf("?"));
        }
        path=path.substring(0,path.lastIndexOf("/"));
        return path;
    }

    /**
     * 转换json数组
     * @param json json对象
     */
    exports.toJsonArr=function(json){
        var jsonArr=[];
        $.each(json, function(key) {
            var intKey=parseInt(key);
            if(intKey!="NaN" && intKey>=0){//当key为数字时，当做数组处理
                jsonArr[intKey]=json[key];
            }
        });
        return jsonArr;
    }
});

