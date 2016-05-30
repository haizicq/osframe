package com.os.osframe.frame.common;

import com.os.osframe.frame.service.IBaseService;
import com.os.osframe.util.StringUtil;

/**
 * 获取URL路径规则
 *  url规则：
 *      1，域名/模块路径/方法名.html?参数=值
 *      2， 域名/模块路径/方法名-参数值.html
 * Created by wangdc on 14-4-17.
 */
public class UrlRouteUtil {
    /**
     * 基础包路径数
     */
    protected final static int BASE_PACKAGE_NUM=3;
    /**
     * 获取模块信息
     * @param baseService 模块服务类
     * @return map
     *      key package 包路径信息
     *      key path    视图文件路径前缀
     *      key name    模型简名
     */
    public static DomainInfo getModuleInfo(IBaseService baseService) {
        DomainInfo domainInfo=new DomainInfo();
        String domainName = baseService.getBaseDao().getDomainName();
        domainInfo.setPackageName(domainName);
        domainInfo.setModuleName(getModuleName(domainName));
        domainInfo.setPathPix(getModulePathPix(domainName));
        domainInfo.setSimpleName(getDomainName(domainName));
        return domainInfo;
    }

    /**
     * 获取模块名称
     * @param domainPackageName
     * @return 模块名
     *      示例:com.bladeray.softdp.frame.web.DemoCategoryDomain
     *      路径为：frame(模块)
     */
    public static String getModuleName(String domainPackageName){
        String[] packageArr=domainPackageName.split("\\.");
        String path=null;
        if(packageArr.length>=BASE_PACKAGE_NUM+2){
            for(int i=BASE_PACKAGE_NUM;i<packageArr.length-2;i++){
                path= StringUtil.linkString(path, ".", packageArr[i]);
            }
        }
        return path;
    }
    /**
     * 构造模块url路径
     * @param domainPackageName 包路径
     * @return 路径
     *      示例:com.bladeray.softdp.frame.web.DemoCategoryDomain
     *      路径为：frame(模块)/democategory(实例文件夹)/demoCategory_(文件名前缀)
     */
    public static String getModulePathPix(String domainPackageName) {
        String[] packageArr=domainPackageName.split("\\.");
        String path="";
        if(packageArr.length>=BASE_PACKAGE_NUM+2){
            for(int i=BASE_PACKAGE_NUM;i<packageArr.length-2;i++){
                path=StringUtil.linkString(path,"/",packageArr[i]);
            }
        }
        String domainName = domainPackageName.substring(domainPackageName.lastIndexOf('.') + 1);
        int index=domainName.indexOf("Domain")==-1?domainName.length():domainName.indexOf("Domain");
        String pathPix=domainName.substring(0, 1).toLowerCase() + domainName.substring(1,index);
        if(StringUtil.isNotNull(path)){
            //path+="/"+pathPix.toLowerCase()+"/"+pathPix+"_";
            path+="/"+pathPix+"/"+pathPix+"_";//取消小写转换
        }
        return path;
    }
    /**
     * 获取默认的模型的类名
     * @return
     */
    public static String getDomainName(String domainName) {
        domainName = domainName.substring(domainName.lastIndexOf('.') + 1);
        return domainName.substring(0, 1).toLowerCase() + domainName.substring(1);
    }

    /**
     * 获取资源视图文件位置
     *  如错误页面等
     * @return
     */
    public static String getResView(){
        return "forward:/common/jsp/";
    }
    /**
     * 获取根视图文件位置
     *  如首页、登陆页面
     * @return
     */
    public static String getRootView(){
        return "forward:/";
    }
}
