package com.os.osframe.core.auth.util;

import com.os.osframe.frame.common.RequiresAuth;
import com.os.osframe.frame.common.RequiresClassAuth;
import com.os.osframe.frame.common.UrlRouteUtil;
import com.os.osframe.frame.util.LoadPackageClasses;
import com.os.osframe.frame.web.BaseController;
import com.os.osframe.frame.web.BaseIndexController;
import com.os.osframe.util.CollectionUtil;
import com.os.osframe.util.StringUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 扫描包获取权限校验注解
 * Created by wangchun on 16/4/21.
 */
public class ScanAnnotationUtil {
    /**
     * 获取包路径下的所有注解了的方法，并获取权限返回
     * @param pckgname
     * @return
     * @throws ClassNotFoundException
     */
    public static   List sanRoles(String pckgname) {
        List roles=new ArrayList();//返回的权限列表
        try {
            LoadPackageClasses loadPackageClasses=new LoadPackageClasses(pckgname,RequiresClassAuth.class);//"com.os.osframe.**.web"
            Set<Class<?>> classSet=loadPackageClasses.getClassSet();
            Iterator it=classSet.iterator();
            while (it.hasNext()){
                Class clazz=(Class)it.next();
                getAnnotation(clazz,roles);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return roles;
    }

    /**
     * 获取注解的权限
     * @param clazz
     * @param roles
     */
    protected static void getAnnotation(Class clazz,List roles){
        try {
            //为类提升扫描速度，要求所有再类上增加了RequiresClassAuth注解的类才会继续扫描方法
            RequiresClassAuth requiresClassAuth=(RequiresClassAuth)clazz.getAnnotation(RequiresClassAuth.class);
            if(requiresClassAuth!=null){
                String moduleInfo= UrlRouteUtil.getModuleName(clazz.getName()).toUpperCase().replaceAll("\\.", "_");
                //1、类上定义的权限
                if(StringUtil.isNotNull(requiresClassAuth.role()) && !isContains(roles, requiresClassAuth.role(), moduleInfo)){
                    roles.add(moduleInfo.toLowerCase()+":"+requiresClassAuth.role());
                }
                //2、默认权限、管理员权限 需要先获取模块信息
                // 拼接模块管理员权限
                String moduleAdminRole="ROLE_"+moduleInfo+"_ADMIN";//构造的模块管理员角色
                String moduleDefaultRole="ROLE_"+moduleInfo+"_DEFAULT";//构造的模块默认角色
                if(!isContains(roles, moduleAdminRole, moduleInfo)){
                    roles.add(moduleInfo.toLowerCase()+":"+moduleAdminRole);
                }
                if(!isContains(roles,moduleDefaultRole,moduleInfo)){
                    roles.add(moduleInfo.toLowerCase()+":"+moduleDefaultRole);
                }
                //3、获取方法上的权限 增删改查权限
                Method[] methods=clazz.getDeclaredMethods();//只能获取到当前类中的方法
                List methodList= CollectionUtil.convertArrayToList(methods);
                Class superClazz=clazz.getSuperclass();//获取上级父类中的
                while (superClazz!=null && !superClazz.getName().equals(Object.class.getName())){
                    methodList.addAll(CollectionUtil.convertArrayToList(superClazz.getDeclaredMethods()));
                    if(superClazz.getName().equals(BaseController.class.getName()) || superClazz.getName().equals(BaseIndexController.class.getName())){
                        break;
                    }
                    superClazz=superClazz.getSuperclass();
                }
                int len=methodList.size();
                for(int i=0;i<len;i++){
                    Method method=(Method)methodList.get(i);
                    //获取方法注解
                    RequiresAuth requiresAuth=(RequiresAuth)method.getAnnotation(RequiresAuth.class);
                    if(requiresAuth!=null && StringUtil.isNotNull(requiresAuth.role())){
                        if(requiresAuth.seat()== RequiresAuth.SeatType.DEFAULT && !isContains(roles,requiresAuth.role(),moduleInfo)){
                            roles.add(moduleInfo.toLowerCase()+":"+requiresAuth.role());
                        }else if(requiresAuth.seat()== RequiresAuth.SeatType.COMMON && requiresClassAuth.common()!= RequiresClassAuth.CommonType.CONTROL) {//当为对公共权限控制时
                            String methodRole=null;
                            if(StringUtil.isNotNull(requiresClassAuth.rolePref())){
                                methodRole=requiresClassAuth.rolePref()+requiresAuth.role();
                            }else{
                                methodRole="ROLE_"+moduleInfo+"_"+requiresAuth.role();
                            }
                            if(!isContains(roles, requiresAuth.role(), moduleInfo)){
                                roles.add(moduleInfo.toLowerCase()+":"+methodRole);
                            }
                        }

                    }
                }

            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断是否包含
     * @param roles
     * @param role
     * @param module
     * @return
     */
    protected static boolean isContains(List roles,String role,String module){
        if(roles.contains(module.toLowerCase()+":"+role)){
            return true;
        }
        return false;
    }
    public static void main(String[] args){
        try{
            List<Class> classes = sanRoles("com.os.osframe.**.web");
            System.out.println("len="+classes.size());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
