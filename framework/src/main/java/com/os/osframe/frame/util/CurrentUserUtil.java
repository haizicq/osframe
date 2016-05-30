package com.os.osframe.frame.util;

import com.os.osframe.frame.common.UserCache;
import com.os.osframe.frame.interfaces.IAuthService;
import com.os.osframe.frame.interfaces.IUserModel;
import com.os.osframe.frame.interfaces.IUserService;
import com.os.osframe.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 当前登录信息的工具类，
 * Created by wangchun on 16/4/16.
 */
public class CurrentUserUtil {

//    private static CacheUtil cache=new CacheUtil();//创建缓存对象 第一级：key-用户名，value-用户缓存对象，第二级：用户缓存

    private static UserCache userCache=new UserCache();//用户缓存

    protected static IAuthService getAuthService(){
        return ModuleBeanUtil.getInstance().getAuthService();
    }

    protected static IUserService getUserService(){
        return ModuleBeanUtil.getInstance().getUserService();
    }

    /**
     * 获取权限工具类
     * @return
     */
    protected static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取登录用户名
     * @return
     */
    public static String getUserName(){
        Subject subject=getSubject();
        if(subject!=null && subject.getPrincipals()!=null){
            return String.valueOf(subject.getPrincipals().getPrimaryPrincipal());
        }
       return null;
    }
    /**
     * 获取登录用户
     * @return
     */
    public static IUserModel getUser(){
        String userName=getUserName();
        //IUserModel userModel=(IUserModel)cache.getValue("USER_"+userName);//从缓存中获取用户
        IUserModel userModel=(IUserModel) userCache.getUserValue(userName,"userObject");//从缓存中获取用户对象
        if(userModel!=null){
            return userModel;
        }
        if(getUserService()!=null){
            userModel=getUserService().findByUserName(userName);
//            cache.put("USER_"+userName,userModel);
            userCache.setUserCache(userName,"userObject",userModel);
            return userModel;
        }

        return null;
    }

    /**
     * 获取用户机构信息
     * @return
     */
    public static String getUserOrgId() {
        String userName=getUserName();
        String userOrgId=(String) userCache.getUserValue(userName,"userOrgId");//从缓存中获取用户机构Id
        if(StringUtil.isNull(userOrgId)){//当不存在机构ID时，去当前用户去获取，当一人多岗跨机构时可以通过切换岗位来将岗位的机构赋值进来
            userOrgId=getUser().getUserOrgId();
            userCache.setUserCache(userName,"userOrgId",userOrgId);
        }
        return userOrgId;
    }

    /**
     * 用于切换用户所属机构
     *  当更改
     * @return
     */
    public static void setUserOrgId(String userOrgId) {
        String userName=getUserName();
        if(StringUtil.isNotNull(userOrgId)){//当不存在机构ID时，去当前用户去获取
            userCache.setUserCache(userName,"userOrgId",userOrgId);
        }
    }


    /**
     * 获取登录用户 权限列表
     * @return
     */
    public static List getPermissionList(){
        String userName=getUserName();
//        List roleList=(List)cache.getValue("PermissionList_"+userName);//从缓存中获取用户
        List roleList=(List)userCache.getUserValue(userName,"PermissionList");//从缓存中获取权限列表
        if(roleList!=null){
            return roleList;
        }
        IUserModel user=getUser();
        if(getAuthService()!=null && user!=null){
            roleList=getAuthService().findByUser(user.getUserId());
//            cache.put("PermissionList_"+userName,roleList);
            userCache.setUserCache(userName,"PermissionList",roleList);
            return roleList;
        }

        return null;
    }

    /**
     * 获取登录用户 权限列表
     * @return
     */
    public static boolean checkRole(String roleType){
        String userName=getUserName();
        String reRole=null;
//        Map roleMap=(Map)cache.getValue("ROLE_"+userName);//从缓存中获取用户
        Map roleMap=(Map)userCache.getUserValue(userName,"ROLE");//从缓存中获取角色权限
        if(roleMap!=null){
            Boolean bool=(Boolean)roleMap.get(roleType);
            if(bool==null){
                bool=false;
            }
            return bool;
        }
        //校验4大主要角色的权限
        List roleAdminList=new ArrayList();
        roleAdminList.add("ROLE_ADMIN");
        roleAdminList.add("ROLE_DEVELOPER");
        roleAdminList.add("ROLE_GROUP_ADMIN");
        roleAdminList.add("ROLE_USERS");
        boolean[] boolArr=getSubject().hasRoles(roleAdminList);
        //将值封装到缓存中
        roleMap=new HashMap();
        roleMap.put("ROLE_ADMIN",boolArr[0]);
        roleMap.put("ROLE_DEVELOPER",boolArr[1]);
        roleMap.put("ROLE_GROUP_ADMIN",boolArr[2]);
        roleMap.put("ROLE_USERS",boolArr[3]);
//        cache.put("ROLE_" + userName, roleMap);
        userCache.setUserCache(userName,"ROLE",roleMap);
        Boolean bool=(Boolean)roleMap.get(roleType);
        if(bool==null){
            bool=false;
        }
        return bool;
    }

    /**
     * 获取登录用户 权限列表
     * @return
     */
    public static boolean checkPermission(String permissionType){
        String userName=getUserName();
        String reRole=null;
//        Map roleMap=(Map)cache.getValue("Permission_"+userName);//从缓存中获取用户
        Map roleMap=(Map)userCache.getUserValue(userName,"Permission");//从缓存中获取角色权限
        if(roleMap!=null){
            Boolean bool=(Boolean)roleMap.get(permissionType);
            if(bool!=null){
                return bool;
            }
        }else{//新开辟用户权限的缓存
            roleMap=new HashMap();
//            cache.put("Permission_" + userName, roleMap);
            userCache.setUserCache(userName,"Permission",roleMap);
        }
        boolean bool=getSubject().isPermitted(permissionType);
        //将值封装到缓存中
        roleMap.put(permissionType,bool);

        return bool;
    }
    /**
     * 是否管理员
     * @return
     */
    public static boolean isAdmin(){
        if(checkRole("ROLE_ADMIN") ){
            return true;
        }
        return false;
    }
    /**
     * 是否开发者
     * @return
     */
    public static boolean isDeveloper(){
        if(checkRole("ROLE_DEVELOPER") ){
            return true;
        }
        return false;
    }
    /**
     * 是否集团模块管理员
     * @return
     */
    public static boolean isGroupAdmin(String module){
        if(checkRole("ROLE_GROUP_ADMIN") && isModuleAdmin(module) ){
            return true;
        }
        return false;
    }
    /**
     * 是否模块管理员
     * @return
     */
    public static boolean isModuleAdmin(String module){
        if(StringUtil.isNull(module)){//没有模块时则不存在模块管理员
            return false;
        }
        String moduleAdminRole="ROLE_"+module.toUpperCase().replaceAll(".","_")+"_ADMIN";//构造的模块管理员角色
        if( checkPermission(moduleAdminRole) ){
            return true;
        }
        return false;
    }

    /**
     * 是否拥有模块指定权限
     * @return
     */
    public static boolean checkModuleRole(String module,String role){
        //当权限为匿名或登陆用户可用时，直接返回
        if(isSimpleRole(role)){
            return true;
        }
        //当权限需要校验时
        if(isAdmin() || isDeveloper() ||isModuleAdmin(module)){
            return true;
        }
        if(checkRole(role) ||checkPermission(role)){
            return true;
        }
        return false;
    }

    /**
     * 判断是否为匿名和普通用户权限
     * @param role 权限角色
     * @return
     */
    public static boolean isSimpleRole(String role){
        //当权限为匿名或登陆用户可用时，直接返回
        if("ROLE_ANONYMOUS".equals(role)){
            return true;
        }else if("ROLE_USERS".equals(role) && StringUtil.isNotNull(getUserName())){//当校验用户权限时只要登陆了即可
            return true;
        }
        return false;
    }

    /**
     * 根据类获取控制类，并找到方法的注解
     * @param controller
     * @param function
     * @param modelId
     * @return
     * @throws Exception
     */
    public static boolean checkAuth(String controller,String function,String modelId){
        return getAuthService().auth(controller,function,modelId);
    }

    /**
     * 清空用户的缓存对象
     */
    public static void clearUserCache(){
        String userName=getUserName();
        userCache.clearUserCache(userName);
    }
    /**
     * 获取用户的缓存
     */
    public static Object getUserCache(String key){
        return userCache.getUserValue(getUserName(),key);
    }
    /**
     * 设置用户缓存
     */
    public static void setUsersCache(String key,Object value){
         userCache.setUserCache(getUserName(), key, value);
    }
}
