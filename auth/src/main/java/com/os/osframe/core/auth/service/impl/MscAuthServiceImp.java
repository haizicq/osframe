package com.os.osframe.core.auth.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.os.osframe.frame.common.*;
import com.os.osframe.frame.interfaces.IAuthService;
import com.os.osframe.frame.interfaces.IUserModel;
import com.os.osframe.frame.log.LogInfo;
import com.os.osframe.frame.support.EntityAnnotation;
import com.os.osframe.frame.support.ISupportService;
import com.os.osframe.frame.support.SupportDomain;
import com.os.osframe.frame.util.CurrentUserUtil;
import com.os.osframe.frame.web.BaseController;
import com.os.osframe.core.auth.domain.MscAuthData;
import com.os.osframe.core.auth.service.IMscAuthDataService;
import com.os.osframe.core.auth.service.IMscAuthRoleService;
import com.os.osframe.core.auth.util.AuthRoleConstant;
import com.os.osframe.core.users.domain.MscUsersInfo;
import com.os.osframe.util.StringUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangchun on 16/4/26.
 */

@Repository("mscAuthService")
public class MscAuthServiceImp implements IAuthService,ISupportService {

    protected  LogInfo logger=new LogInfo(MscAuthServiceImp.class);
    @Resource
    IMscAuthRoleService mscAuthRoleService;
    @Resource
    IMscAuthDataService mscAuthDataService;
    /**
     * 根据用户获取权限角色
     * @param userId
     * @return
     */
    public List findByUser(String userId){
        try{
            HqlObject hqlObject=new HqlObject();
            hqlObject.setSelectExtract("mscAuthRole");
            hqlObject.setJoinExtract(" left join mscAuthRole.lbPersonList persons");
            hqlObject.setWhereExtract("persons.pkId=:personId");
            hqlObject.setParameter("personId",userId);
            List list=mscAuthRoleService.find(hqlObject);
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修改权限hql
     * @param hqlObject
     * @param domainInfo
     * @param domainClass 实体类
     */
    public void changeAuthHql(HqlObject hqlObject,DomainInfo domainInfo,Class domainClass){
        if(domainInfo==null || StringUtil.isNull(domainInfo.getPackageName())){//必须存在实体类才需要进行权限校验
            return;
        }
        //获取实体注解
        EntityAnnotation entityAnnotation =null;
        if(domainClass!=null){//是否进行数据权限校验
            entityAnnotation=(EntityAnnotation) domainClass.getAnnotation(EntityAnnotation.class);
        }
        if(entityAnnotation==null || !entityAnnotation.filter()){//当没有权限注解或者权限注解标记不进行数据权限控制
            return ;
        }
        //如果数据拦截权限时   如果管理数据信息是否需要进行集团管理员校验
            Boolean bool=false;
            if(CurrentUserUtil.isAdmin()|| CurrentUserUtil.isDeveloper()){//当为管理员或开发者时
                return ;
            }
            String module=domainInfo.getModuleName();
            if(CurrentUserUtil.isGroupAdmin(module)){//当为模块的集团管理员
                return ;
            }
        //对普通模块管理员校验机构数据，其他角色校验阅读权限
        if(CurrentUserUtil.isModuleAdmin(module) ){//管理员时去级联拥有者的
            //暂时先写死具体的权限数据表名，待测试完善后再设计修改为由模块类来指定这段代码，即可写成 authService.filterHql(hqlObject);
            hqlObject.setJoinExtract(" ,MscAuthData mscAuthData");
            String authDataWhere=" (mscAuthData.lbModuleKey=:moduleKey and mscAuthData.lbModelId="+domainInfo.getSimpleName()+" and mscAuthData.lbType=:lbType and (mscAuthData.lbNullType='0' or mscAuthData.lbOrg=:lbOrgId))";
            if(StringUtil.isNotNull(hqlObject.getWhereExtract())){
                hqlObject.setWhereExtract(StringUtil.linkString("("+hqlObject.getWhereExtract()+")", " and ", authDataWhere));
            }else{
                hqlObject.setWhereExtract(authDataWhere);
            }

            hqlObject.setParameter("moduleKey",domainInfo.getPackageName());
            hqlObject.setParameter("lbType","1");
            hqlObject.setParameter("lbOrgId",CurrentUserUtil.getUser().getUserOrgId());

        }else{// 去级联表中所有的角色数据，包括拥有者、阅读者、编辑者、下载者
            hqlObject.setJoinExtract(" ,MscAuthData mscAuthData left join mscAuthData.lbAllPersonList allPersonList");
            String authDataWhere=" (mscAuthData.lbModuleKey=:moduleKey and mscAuthData.lbModelId="+domainInfo.getSimpleName()+" and (mscAuthData.lbNullType='0' or mscAuthData.lbNullType='1' or allPersonList.pkId=:userId))";
            if(StringUtil.isNotNull(hqlObject.getWhereExtract())){
                hqlObject.setWhereExtract(StringUtil.linkString("("+hqlObject.getWhereExtract()+")", " and ", authDataWhere));
            }else{
                hqlObject.setWhereExtract(authDataWhere);
            }
            hqlObject.setParameter("moduleKey",domainInfo.getPackageName());
            hqlObject.setParameter("userId",CurrentUserUtil.getUser().getUserId());

        }

    }

    /**
     * 根据类获取控制类，并找到方法的注解
     * @param controllerClassStr
     * @param function
     * @param modelId
     * @return
     * @throws Exception
     */
    public  boolean auth(String controllerClassStr,String function,String modelId)  {
        try {
            //1、获取控制器的类
            String cacheKey=controllerClassStr+"_"+function;//重新授权时需要重新登录，所以在退出登录时需要清理权限缓存数据
            if(StringUtil.isNotNull(cacheKey)){
                cacheKey+="_"+modelId;
            }
            Boolean reAuth=(Boolean)CurrentUserUtil.getUserCache(cacheKey);
            if(reAuth!=null){//当缓存中存在时，直接返回缓存的权限
                return reAuth;
            }
            Class controllerClass = Class.forName(controllerClassStr);
            //获取类上的权限拦截注解
            RequiresClassAuth requiresClassAuth = (RequiresClassAuth) controllerClass.getAnnotation(RequiresClassAuth.class);

            //2、根据类获取方法注解
            Method[] methods =controllerClass.getMethods();

            Method method=null;
            for(int i=0,len=methods.length;i<len;i++){
                if(methods[i].getName().equals(function)){
                    method=methods[i];
                    break;
                }
            }
            if(method==null){//当方法不存在时，直接返回true，标示有权限
                return true;
            }
            RequiresAuth requiresAuth = (RequiresAuth) method.getAnnotation(RequiresAuth.class);
            //获取模块类
            Class domainClass=getDomainClass(controllerClass);
            //3、获取模块信息
            DomainInfo domainInfo = new DomainInfo(domainClass);
            String moduleInfo = domainInfo.getModuleName().toUpperCase().replaceAll("\\.", "_");//构造的模块管理员角色
            reAuth=auth(domainClass, requiresClassAuth, requiresAuth, moduleInfo, modelId);
            CurrentUserUtil.setUsersCache(cacheKey,reAuth);//将权限信息设置到用户的缓存中
            return reAuth;
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return false;
    }

    /**
     * 权限校验
     * @param domainClass
     * @param requiresClassAuth
     * @param requiresAuth
     * @param moduleInfo
     * @param modelId
     * @return
     * @throws Exception
     */
    public  boolean auth(Class domainClass,RequiresClassAuth requiresClassAuth,RequiresAuth requiresAuth,String moduleInfo,String modelId) throws Exception {

        if(requiresAuth!=null && CurrentUserUtil.isSimpleRole(requiresAuth.role())){//先校验方法级权限
            return true;
        }else if((requiresAuth==null || StringUtil.isNull(requiresAuth.role())) && (requiresClassAuth!=null  || CurrentUserUtil.isSimpleRole(requiresClassAuth.role()))){//当方法级权限未设置时，再校验类级权限
            return true;
        }
        boolean bool=false;//是否有权
        if(CurrentUserUtil.isAdmin()|| CurrentUserUtil.isDeveloper()){//当满足其中
            bool=true;
            return bool;
        }
        //===2、构造模块管理员和模块默认权限
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
                return bool;
            }
        }
        //===4、获取request和modelId
        //===5、管理员权限下，且需要进行权限验证时
        if(!bool && isModuleAdmin){
            //智能判断是否需要校验数据
            if( entityAnnotation==null || !entityAnnotation.filter() || StringUtil.isNull(modelId)) {
                bool = true;
            }else if(RequiresAuth.RoleType.NONE!=requiresAuth.type()   && StringUtil.isNotNull(modelId) && entityAnnotation.filter() ){//当id不为空，且domain存在，判断其是否保存了数据到数据权限表中？
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
                return bool;
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
                if(entityAnnotation==null || !entityAnnotation.filter() || StringUtil.isNull(modelId)) {//当注解为空或者id为空时不进行数据权限校验
                    bool = true;
                }else if( RequiresAuth.RoleType.NONE!=requiresAuth.type() && StringUtil.isNotNull(modelId) && entityAnnotation.filter()){//当id不为空，且domain存在，判断其是否保存了数据到数据权限表中？

                    MscAuthData mscAuthData=mscAuthDataService.findDataObj(domainClass.getName(), modelId);//获取数据对象
                    if(mscAuthData==null || AuthRoleConstant.DATA_AUTH_NULL_ORG_READER.equals(mscAuthData.getLbNullType()) ){//当为机构可阅读时，直接为true
                        bool=true;
                    }
                    if(!bool && StringUtil.isNotNull(mscAuthData.getLbOrg())&& mscAuthData.getLbOrg().equals(CurrentUserUtil.getUserOrgId()) && AuthRoleConstant.DATA_AUTH_NULL_ALL_READER.equals(mscAuthData.getLbNullType())){//当他们在同一个机构，且机构下开放可读
                        bool=true;
                    }
                    //判断校验数据类型，包括：只读、编辑、下载
                    if(RequiresAuth.RoleType.READER!=requiresAuth.type() && mscAuthDataService.isReader(domainClass.getName(), modelId)){
                        bool=true;
                    }else if(RequiresAuth.RoleType.EDIT!=requiresAuth.type() && mscAuthDataService.isEditor(domainClass.getName(), modelId)){
                        bool=true;
                    }else if(RequiresAuth.RoleType.DOWN!=requiresAuth.type() && mscAuthDataService.isDowner(domainClass.getName(),modelId)){
                        bool=true;
                    }
                }else{
                    bool = true;
                }
                if(bool==true){
                    return bool;
                }
            }
        }
        return false;
    }

    /**
     * 获取域模型
     * @param clazz
     * @return
     */
    protected  Class getDomainClass(Class clazz){
        try {
            if(BaseController.class.isAssignableFrom(clazz)){//当继承了基础类的控制器才默认取其实体
                Type genType = clazz.getGenericSuperclass();
                if(genType!=null){
                    Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
                    if (params != null && params.length > 0) {
                        Class domainClass = (Class) params[0];
                        return domainClass;
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error(e);
        }
        return null;

    }


    public void saveSupport(SupportDomain supportDomain, String id, String clazz) throws Exception {
        if(StringUtil.isNotNull(supportDomain.getData())){
            JSONArray jsonArray=JSONArray.parseArray(supportDomain.getData());//存储数据要求为
            if(jsonArray!=null && !jsonArray.isEmpty()){
                for(int i=0,len=jsonArray.size();i<len;i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    //对象内容：模块关键字lbModuleKey、模块id lbModelId、类别 lbType、为空处理 lbNullType、所属组织 lbOrg、人员列表 lbPersonList
                    String lbType=jsonObject.getString("type");
                    String lbNullType=jsonObject.getString("isNull");
                    String persons=jsonObject.getString("persons");//存的是人员ID列表
                    //XXX 根据EntityAnnotation注解去获取是否分表
                    //赋值
                    MscAuthData mscAuthData =mscAuthDataService.findDataObj(clazz,id,lbType);//查找对象
                    if(mscAuthData==null){//当没有的对象时直接新new
                        mscAuthData=new MscAuthData();
                        mscAuthData.init();
                    }
                    mscAuthData.setLbModuleKey(clazz);
                    mscAuthData.setLbModelId(id);
                    mscAuthData.setLbType(lbType);
                    if(AuthRoleConstant.DATA_AUTH_TYPE_OWNER.equals(lbType)){//当为拥有者时才设置
                        if(StringUtil.isNotNull(lbNullType)){
                            mscAuthData.setLbNullType(lbNullType);//默认是相关人
                        }else{
                            mscAuthData.setLbNullType(AuthRoleConstant.DATA_AUTH_NULL_RELEVANT_READER);//默认是相关人
                        }
                            mscAuthData.setLbOrg(CurrentUserUtil.getUserOrgId());
                    }


                    if(StringUtil.isNotNull(persons)){
                        String[] personArr=persons.split(",");
                        List<MscUsersInfo> mscUsersInfoList=new ArrayList<MscUsersInfo>();
                        for(int j=0,len1=personArr.length;j<len1;j++){
                            String personId=personArr[j];
                            if(StringUtil.isNull(personId)){
                                continue;
                            }
                            MscUsersInfo mscUsersInfo=new MscUsersInfo();
                            mscUsersInfo.setPkId(personId);
                            mscUsersInfoList.add(mscUsersInfo);
                        }
                        mscAuthData.setLbAllPersonList(mscUsersInfoList);
//                        mscAuthData.setLbPersonList(mscUsersInfoList);
                    }else if(AuthRoleConstant.DATA_AUTH_TYPE_OWNER.equals(lbType)){//当为拥有者时，默认为当前登录人
                        IUserModel user=CurrentUserUtil.getUser();
                        if(user!=null){
                            List<MscUsersInfo> mscUsersInfoList=new ArrayList<MscUsersInfo>();
                            MscUsersInfo mscUsersInfo=new MscUsersInfo();
                            mscUsersInfo.setPkId(user.getUserId());
                            mscUsersInfoList.add(mscUsersInfo);
                            mscAuthData.setLbAllPersonList(mscUsersInfoList);
                        }
                    }

                    mscAuthDataService.saveOrUpdate(mscAuthData);
                }
            }
        }
    }

    public List<SupportDomain> readSupport(String id, String type, String keyword) throws Exception {
        return null;
    }
}
