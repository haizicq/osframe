package com.os.osframe.frame.common;

import com.os.osframe.util.StringUtil;

/**
 * 描述域模型信息
 * Created by wangdc on 14-4-17.
 */
public class DomainInfo {
    /**
     * 包路径名称
     */
    protected String packageName;
    /**
     * 模块名，多级模块以"."分隔
     */
    protected String moduleName;

    /**
     * 模块简名
     *      模块的最后名称
     */
    protected String moduleSimpleName;
    /**
     * 模块根路径，多级模块以"/"分隔
     */
    protected String modulePath;
    /**
     * 域模型路径信息前缀，路径以"/"分隔，最后附带一个下划线
     */
    protected String pathPix;
    /**
     * 域模型简名
     */
    protected String simpleName;
	public DomainInfo(Class clazz){
		packageName=clazz.getName();
	}
    public DomainInfo(String packageName){
        this.packageName=packageName;
    }
	public DomainInfo(){
		packageName=null;
		moduleName=null;
		pathPix=null;
		simpleName=null;
        moduleSimpleName=null;
	}


    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getModuleName() {
        if(StringUtil.isNull(moduleName)){
            moduleName= UrlRouteUtil.getModuleName(packageName);
        }
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getPathPix() {
		if(StringUtil.isNull(pathPix)){
			pathPix= UrlRouteUtil.getModulePathPix(packageName);
		}
        return pathPix;
    }

    public String getModuleSimpleName() {
        if(StringUtil.isNull(moduleSimpleName)){
            int index=getModuleName().lastIndexOf("/");
            if(index>-1){//当没有/时直接返回路径
                moduleSimpleName=getModuleName().substring(index+1);
            }else{
                moduleSimpleName=getModuleName();

            }
        }
        return moduleSimpleName;
    }

    public void setModuleSimpleName(String moduleSimpleName) {
        this.moduleSimpleName = moduleSimpleName;
    }

    public void setPathPix(String pathPix) {
        this.pathPix = pathPix;
    }

    public String getSimpleName() {
		if(StringUtil.isNull(simpleName)){
			simpleName=UrlRouteUtil.getDomainName(packageName);
		}
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getModulePath() {
        if(StringUtil.isNull(modulePath)){
            modulePath=getModuleName().replace(".","/");
        }
        return modulePath;
    }

    public void setModulePath(String modulePath) {
        this.modulePath = modulePath;
    }
}
