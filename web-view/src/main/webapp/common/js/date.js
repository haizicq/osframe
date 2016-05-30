/**
 * Created by wangchun on 15/9/30.
 */

define(function(require, exports, module) {

    exports.dateToStr = function (date,fmt) { //时间格式化字符串
        if(date==undefined || date==null){
            return "";
        }
        if(fmt==undefined || fmt==null){
            fmt="yyyy-MM-dd hh:mm";//默认转换格式
        }
        var o = {
            "M+": date.getMonth() + 1, //月份
            "d+": date.getDate(), //日
            "h+": date.getHours(), //小时
            "m+": date.getMinutes(), //分
            "s+": date.getSeconds(), //秒
            "q+": Math.floor((date.getMonth() + 3) / 3), //季度
            "S": date.getMilliseconds() //毫秒
        };
        if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
        for (var k in o)
            if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    };
    exports.strToDate = function (str){//默认为 yyyy-MM-dd进行转换
        //当只有时间没有日期时需要补齐日期，日期补当前时间
        if(str.indexOf(":")>-1 && str.length<=8){//str 仅仅为hh:mm
            str=this.dateToStr(new Date(),"yyyy-MM-dd")+" "+str;
        }
        var regEx = new RegExp("\\-","gi");
        str=str.replace(regEx,"/");
        return new Date(str);
    }
});