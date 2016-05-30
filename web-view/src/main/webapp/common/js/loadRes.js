/**
 * js和css加载方法
 */
define(function(require, exports, module) {
    exports.css=function (path) {//还需要判断已经引入了，引入了之后就不需要重复引入
        if (!path || path.length == 0) {
            throw new Error('argument "path" is required !');
        }
        var head = document.getElementsByTagName('head')[0];
        var link = document.createElement('link');
        link.href = path;
        link.rel = 'stylesheet';
        link.type = 'text/css';
        head.appendChild(link);
    }
    exports.js=function (path) {
        if (!path || path.length == 0) {
            throw new Error('argument "path" is required !');
        }
        var head = document.getElementsByTagName('head')[0];
        var script = document.createElement('script');
        script.src = path;
        script.type = 'text/javascript';
        head.appendChild(script);
    }
    exports.removeCss= function (path) {
        var link = document.getElementsByTagName("link");
        for (var i = 0; i < link.length; i++) {
            if (null != link[i].getAttribute("href") && link[i].getAttribute("href").indexOf(path) != -1) {// &&i!=0 存在重复 只保留第一个link
                link[i].parentNode.removeChild(link[i]);
            }
        }
    }
    exports.removeJs=function (path) {
        var scriptSrc = document.getElementsByTagName("script");
        for (var i = 0; i < script.length; i++) {
            if (null != link[i].getAttribute("src") && link[i].getAttribute("src").indexOf(path) != -1) {// &&i!=0 存在重复 只保留第一个link
                link[i].parentNode.removeChild(link[i]);
            }
        }
    }
});