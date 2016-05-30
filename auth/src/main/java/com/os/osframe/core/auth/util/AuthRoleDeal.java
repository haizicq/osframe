package com.os.osframe.core.auth.util;

import com.alibaba.fastjson.JSONObject;
import com.os.osframe.frame.common.DomainInfo;
import com.os.osframe.frame.common.RequiresAuth;
import com.os.osframe.frame.common.RequiresClassAuth;
import com.os.osframe.frame.common.UrlRouteUtil;
import com.os.osframe.frame.support.EntityAnnotation;
import com.os.osframe.frame.util.CurrentUserUtil;
import com.os.osframe.frame.web.BaseController;
import com.os.osframe.core.auth.domain.MscAuthData;
import com.os.osframe.core.auth.service.IMscAuthDataService;
import com.os.osframe.util.StringUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by wangchun on 16/4/15.
 */
@Aspect //该注解标示该类为切面类
@Component //注入依赖
public class AuthRoleDeal {
    @Around(value = "@annotation(requiresAuth)")
    public Object auth(ProceedingJoinPoint pjp,RequiresAuth requiresAuth) throws Throwable {
        Object result = null;

        System.out.println(requiresAuth.role());
        try {
            System.out.println("前置通知");
            String method=pjp.getSignature().getName();//方法名
            Object[] args=pjp.getArgs();
            Class domainClass = this.getDomainClass(pjp);//获取实体类

            //获取类上的权限拦截注解
            RequiresClassAuth requiresClassAuth=(RequiresClassAuth)pjp.getTarget().getClass().getAnnotation(RequiresClassAuth.class);

            //根据类找到所属模块，并取出其配置的模块权限前缀，将传入的方法权限链接起来
            //模块前缀默认为："ROLE_"+MSC_AUTH_+传入指
            //获取requst中的id来判断有无阅读权限，需要当model实现了数据接口，且
            //获取模块管理员权限和系统管理员权限，如果拥有则不做相关校验，这个需要在导入时获取到标记
            //模块权限采用默认定义：增删改查、管理员、默认权限等都直接提供、新增的角色权限如何定义待定

            //根据model去获取配置，配置包括：包路径、权限key、作用方法，配置中指定了角色直接用配置中的
            /**
             * 如何做默认的权限规则？主文档、配置文档、模块的权限
             * 模块权限包括：增删改查、管理员、默认权限
             * 配置文档：模块管理员
             * 主文档：增删改查、根据数据校验权限
             * 列表查看权限：直接根据角色控制，数据权限采用默认的控制方式，
             * 现状：增删改查的方法都是在父类中，如何在不修改父类的情况下考虑到主文档和配置文档
             *  1、首先要获取到当前实体时主文档还是配置文件，可以考虑根据实现的不同接口，配置文件类实现一个接口
             *  2、新增的权限如何定义名称和描述：roles.role_name= roles.role_name.name=
             *  3、新增权限修改原有方法的控制:role-class
             *  可以作为第2步实现
             *  还是使用xml来配置，也可以不做任何配置时默认直接可以获取基础的使用权限控制
             *
             */
            /**
             * 1、判断用户权限是否管理员、集团模块管理员、开发者，如果为这三者，则直接放开权限
             * 2、判断是否模块管理员，再判断是否对数据进行校验，如果不校验数据则直接放开，要校验则检查数据是否机构下的数据
             * 3、普通人员则直接校验权限角色和数据
             */

            Boolean bool=false;//是否有权

            /*
                1、当类上的注解权限为模块管理员时直接整个类就是判断管理员权限才能访问  requiresClassAuth.role()
                2、当非管理员时，获取其类型时默认还是admin如果为admin权限时，则所有项必须拥有模块管理员以上权限才能访问 requiresClassAuth.type()
                3、当设置类数据权限校验时，才需要校验数据权限，否则，需要在方法上特殊指定了数据权限 requiresClassAuth.filter()
                4、不同的类使用不一样的增删改查权限角色：requiresClassAuth.rolePref() ;权限前缀
                5、配置列表数据拦截器 在数据查询的通用list方法中来获取这个注解
             */
            //===1、判断是否为管理员或开发者

            //判断是否匿名的简单权限
            if(requiresAuth!=null && CurrentUserUtil.isSimpleRole(requiresAuth.role())){//先校验方法级权限
                bool=true;
            }else if(requiresAuth==null && StringUtil.isNull(requiresAuth.role()) && requiresClassAuth!=null  && CurrentUserUtil.isSimpleRole(requiresClassAuth.role())){//当方法级权限未设置时，再校验类级权限
                bool=true;
            }
            //这个可以放到当前用户角色的缓存中去

            if(CurrentUserUtil.isAdmin()|| CurrentUserUtil.isDeveloper()){//当满足其中
                bool=true;
            }
            if(bool){
                result = pjp.proceed();//调用方法执行
                return result;
            }
            //===2、构造模块管理员和模块默认权限
            String moduleInfo=null;
            if(domainClass!=null){
                DomainInfo domainInfo=new DomainInfo(domainClass);
                moduleInfo=domainInfo.getModuleName().toUpperCase().replaceAll("\\.","_");//构造的模块管理员角色
            }else{//当没有模型时，直接根据控制器的包路径获取
                moduleInfo=UrlRouteUtil.getModuleName(pjp.getTarget().getClass().getName()).toUpperCase().replaceAll("\\.", "_");
            }
            // 拼接模块管理员权限
            String moduleAdminRole="ROLE_"+moduleInfo+"_ADMIN";//构造的模块管理员角色
            String moduleDefaultRole=null;
            if(requiresClassAuth!=null && StringUtil.isNotNull(requiresClassAuth.role())){
                    moduleDefaultRole=requiresClassAuth.role();//实体指定默认角色
            }else if(requiresClassAuth!=null && RequiresClassAuth.CommonType.CONTROL==requiresClassAuth.common()){
                    moduleDefaultRole=moduleAdminRole;//当控制时且没有设置权限
            }else{
                    moduleDefaultRole="ROLE_"+moduleInfo+"_DEFAULT";//构造的模块默认角色
            }

            //获取实体注解
            EntityAnnotation entityAnnotation =null;
            if(domainClass!=null){
                entityAnnotation=(EntityAnnotation) domainClass.getAnnotation(EntityAnnotation.class);
            }

            //===3、判断模块管理权限，1）当拥有集团管理员权限或没有domainclass时或者不需要权限验证
            Boolean isModuleAdmin=CurrentUserUtil.checkPermission(moduleAdminRole);
            if(!bool){

                if(isModuleAdmin && (CurrentUserUtil.checkRole(AuthRoleConstant.ROLE_GROUP_ADMIN) || domainClass==null || entityAnnotation==null || !entityAnnotation.filter())){
                    //对当前模块拥有管理权限 ，且为集团管理员或domainclass未空时
                    bool=true;
                    result = pjp.proceed();//调用方法执行
                    return result;
                }
            }
            //===4、获取request和modelId
            HttpServletRequest request=null;
            String modelId=null;
            if( args!=null && args.length>0){//domainClass!=null && requiresClassAuth.filter() && requiresAuth.filter() &&
                for(int i=0;i<args.length;i++){
                    if(args[i] instanceof HttpServletRequest){//当为request时
                        request=(HttpServletRequest)args[i];
                        if(domainClass!=null){
                            String authId=requiresAuth.id();//默认取配置的id
                            if(StringUtil.isNull(authId)){
                                authId="id";
                            }
                            modelId=request.getParameter("id");
                            if(StringUtil.isNull(modelId)){//当获取不到id时尝试pkId
                                modelId=request.getParameter("pkId");
                            }
                        }
                        break;
                    }
                }
            }

            //===5、管理员权限下，且需要进行权限验证时
            if(!bool && isModuleAdmin){
                //智能判断是否需要校验数据
                if(entityAnnotation==null || !entityAnnotation.filter() || StringUtil.isNull(modelId)) {
                    bool = true;
                }else if(RequiresAuth.RoleType.NONE!=requiresAuth.type()   && StringUtil.isNotNull(modelId) && entityAnnotation.filter()){//当id不为空，且domain存在，判断其是否保存了数据到数据权限表中？
                    //&& IMscAuthData.class.isAssignableFrom(domainClass)
                    MscAuthData mscAuthData=mscAuthDataService.findDataObj(domainClass.getName(), modelId);//获取数据对象
                    if(mscAuthData==null || AuthRoleConstant.DATA_AUTH_NULL_ORG_READER.equals(mscAuthData.getLbNullType())){//当为机构可阅读时，直接为true
                        bool=true;
                    }
                    //当拥有者的所属机构和当前用户所属机构
                    else if(StringUtil.isNotNull(mscAuthData.getLbOrg())&& mscAuthData.getLbOrg().equals(CurrentUserUtil.getUserOrgId())){//判断类型(拥有者)和所属机构 mscAuthData.getLbType()

                        bool=true;
                    }
                }else{
                    bool = true;
                }
                if(bool==true){
                    result = pjp.proceed();//调用方法执行
                    return result;
                }
            }
            //===6、判断当前需要的权限名称
            if(!bool){
                String roleName=null;
                if(requiresClassAuth !=null && RequiresClassAuth.CommonType.CONTROL==requiresClassAuth.common()){//当为控制类型的权限，不需要进行前缀拼接
                    if(requiresAuth.seat()== RequiresAuth.SeatType.COMMON && StringUtil.isNotNull(requiresClassAuth.role())) {
                        roleName = requiresClassAuth.role();
                    }else if(StringUtil.isNotNull(requiresAuth.role())){
                            roleName=requiresAuth.role();
                    }else{
                        roleName = moduleAdminRole;
                    }
                }else{
                    if(RequiresAuth.SeatType.COMMON ==requiresAuth.seat()){
                        if(requiresClassAuth!=null && StringUtil.isNotNull(requiresAuth.role()) ){
                            if(StringUtil.isNotNull(requiresClassAuth.rolePref())){
                                roleName = requiresClassAuth.rolePref() + requiresAuth.role();//构造的模块前缀权限
                            }else{
                                roleName="ROLE_"+moduleInfo+"_"+requiresAuth.role();
                            }

                        }else{
                            roleName = moduleDefaultRole;//构造的模块前缀权限
                        }
                    }else {
                        if(StringUtil.isNotNull(requiresAuth.role())){
                            roleName=requiresAuth.role();
                        }else{
                            roleName = moduleDefaultRole;
                        }
                    }
                }
                //获取到所需要校验的权限
                if(StringUtil.isNotNull(roleName) && CurrentUserUtil.checkPermission(roleName)){//当拥有方法访问权限
                    //继续校验数据访问权限
                    if( entityAnnotation==null || !entityAnnotation.filter() || StringUtil.isNull(modelId)) {
                        bool = true;
                    }else if( RequiresAuth.RoleType.NONE!=requiresAuth.type()  && StringUtil.isNotNull(modelId) && entityAnnotation.filter()){//当id不为空，且domain存在，判断其是否保存了数据到数据权限表中？

                        MscAuthData mscAuthData=mscAuthDataService.findDataObj(domainClass.getName(), modelId);//获取数据对象
                        if(mscAuthData==null || AuthRoleConstant.DATA_AUTH_NULL_ORG_READER.equals(mscAuthData.getLbNullType()) ){//当为机构可阅读时，直接为true
                            bool=true;
                        }
                        if(!bool && StringUtil.isNotNull(mscAuthData.getLbOrg())&& mscAuthData.getLbOrg().equals(CurrentUserUtil.getUserOrgId()) && AuthRoleConstant.DATA_AUTH_NULL_ALL_READER.equals(mscAuthData.getLbNullType())){//当他们在同一个机构，且机构下开放可读
                            bool=true;
                        }
                        //判断校验数据类型，包括：只读、编辑、下载
                        if(!bool &&  RequiresAuth.RoleType.READER!=requiresAuth.type() && mscAuthDataService.isReader(domainClass.getName(), modelId)){
                            bool=true;
                        }else if(!bool && RequiresAuth.RoleType.EDIT!=requiresAuth.type() && mscAuthDataService.isEditor(domainClass.getName(), modelId)){
                            bool=true;
                        }else if(!bool && RequiresAuth.RoleType.DOWN!=requiresAuth.type() && mscAuthDataService.isDowner(domainClass.getName(), modelId)){
                            bool=true;
                        }
                    }else{
                        bool = true;
                    }
                    if(bool==true){
                        result = pjp.proceed();//调用方法执行
                        return result;
                    }
                }
            }
            //跳转到没有权限提示页面
            if(!bool){
                //获取返回类型
                Method methodObj = getMethod(pjp, method, args);
                RequiresAuth.ReturnType returnTypeObj=requiresAuth.returnType();
                String returnType=null;
                if(returnTypeObj== RequiresAuth.ReturnType.DEFAULT){//当为默认时需要重写来取值
                    returnType=methodObj.getReturnType().getSimpleName().toLowerCase();
                }else{
                    returnType=returnTypeObj.name().toLowerCase();
                }
                if("modelandview".equals(returnType)){
                    ModelAndView modelView = new ModelAndView();
                    modelView.setViewName(UrlRouteUtil.getResView()+"403.jsp");//默认直接跳转到登陆界面
                }else if("json".equals(returnType)){
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("status","0");
                    jsonObject.put("code","403");
                    jsonObject.put("msg","没有访问权限");
                    return jsonObject.toJSONString();
                }else if(returnTypeObj== RequiresAuth.ReturnType.String){//当特别指定了返回字符串则返回字符串
                    return UrlRouteUtil.getResView()+"403.jsp";
                }else{
                    ResponseBody responseBody=methodObj.getAnnotation(ResponseBody.class);
                    if(responseBody==null){//获取body注解
                        return UrlRouteUtil.getResView()+"403.jsp";//跳转到没有权限页面，若是ajax则是返回了403字符串
                    }else{
                        //当有ResponseBody注解时，需要返回字符串格式，默认为json，除非指定
                        JSONObject jsonObject=new JSONObject();
                        jsonObject.put("status","0");
                        jsonObject.put("code","403");
                        jsonObject.put("msg","没有访问权限");
                        return jsonObject.toJSONString();
                    }
                }
            }

        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }

    @Resource
    IMscAuthDataService mscAuthDataService;
    /**
     * 获取实体类
     * @param pjp
     * @return
     */
    protected Class getDomainClass(ProceedingJoinPoint pjp){
        try {
            Class clazz = pjp.getTarget().getClass();
            if(!BaseController.class.isAssignableFrom(clazz)){
                return null;
            }
            Type genType = clazz.getGenericSuperclass();
            if(genType!=null){
                Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
                if (params != null && params.length > 0) {
                    Class domainClass = (Class) params[0];
                    return domainClass;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }

    /**
     * 获取当前方法
     * @param pjp
     * @return
     */
    protected Method getMethod(ProceedingJoinPoint pjp){
        String method=pjp.getSignature().getName();//方法名
        Object[] args=pjp.getArgs();
        return getMethod(pjp,method,args);
    }
    /**
     * 获取当前方法
     * @param pjp
     * @param method 方法名
     * @param args 包含参数
     * @return
     */
    protected Method getMethod(ProceedingJoinPoint pjp,String method,Object[] args){
        try {
            MethodSignature signature = (MethodSignature)pjp.getSignature();
            return signature.getMethod();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;

    }
}
