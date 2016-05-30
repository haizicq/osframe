package com.os.osframe.core.auth.service.impl;

import com.os.osframe.frame.common.HqlObject;
import com.os.osframe.frame.dao.IBaseDao;
import com.os.osframe.frame.service.BaseServiceImpl;
import com.os.osframe.frame.util.CurrentUserUtil;
import com.os.osframe.core.auth.domain.MscAuthData;
import com.os.osframe.core.auth.service.IMscAuthDataService;
import com.os.osframe.core.auth.util.AuthRoleConstant;
import com.os.osframe.util.StringUtil;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据权限
 * Created by wangdc on 2016-04-12 16:42.
 */
@Repository("mscAuthDataService")
public class MscAuthDataServiceImp extends BaseServiceImpl<MscAuthData> implements IMscAuthDataService{
    @Resource
    IBaseDao mscAuthDataDao;
    public IBaseDao getBaseDao(){
        return mscAuthDataDao;
    }


    /**
     * 查找指定类型的所有权限数据
     * @param clazzName  类路径名
     * @param modelId 实体id
     * @param type 类型
     * @param isOwner 是否校验当前用户拥有权限 true 校验 false 不校验
     * @return
     */
    public List findTypeData(String clazzName,String modelId,String type,boolean isOwner) throws Exception{
        HqlObject hqlObject = new HqlObject();
        hqlObject.setJoinExtract(" left join mscAuthData.lbAllPersonList users");
        hqlObject.setSelectExtract(" mscAuthData");
        String where =null;
        if(StringUtil.isNotNull(type)){
            where=" mscAuthData.lbType =:lbType";
            hqlObject.setParameter("lbType", type);
        }
        where+=" and mscAuthData.lbModuleKey=:lbModuleKey and  mscAuthData.lbModelId=:lbModelId ";
        if(isOwner){
            where+=" and users.pkId in :userId";
            //阅读者有可能为部门
            List userTreeList=new ArrayList();
            if(StringUtil.isNotNull(CurrentUserUtil.getUser().getUserLevelIds()) && !AuthRoleConstant.DATA_AUTH_TYPE_OWNER.equals(type)){//当不为拥有者时
                String[] levelIds=CurrentUserUtil.getUser().getUserLevelIds().split(";");
                for(int i=0;i<levelIds.length;i++){
                    userTreeList.add(levelIds[i]);
                }
            }else{
                userTreeList.add(CurrentUserUtil.getUser().getUserId());
            }
            hqlObject.setParameter("userId", userTreeList);//要用层级id来处理
        }
        hqlObject.setWhereExtract(where);
        hqlObject.setOrderByExtract("mscAuthData.lbUpdateTime desc");
        hqlObject.setParameter("lbModuleKey",clazzName);
        hqlObject.setParameter("lbModelId", modelId);


        List list = this.find(hqlObject);
        return list;
    }

    /**
     * 查找数据对象
     * @param clazzName
     * @param modelId
     * @return
     */
    public MscAuthData findDataObj(String clazzName,String modelId) throws Exception{
        List list = this.findTypeData(clazzName, modelId, AuthRoleConstant.DATA_AUTH_TYPE_OWNER, false);
        if (list!=null && !list.isEmpty()) {
            MscAuthData mscAuthData=(MscAuthData)list.get(0);
            return mscAuthData;
        }
        return null;
    }

    /**
     * 查找数据对象
     * @param clazzName
     * @param modelId
     * @param type
     * @return
     */
    public MscAuthData findDataObj(String clazzName,String modelId,String type) throws Exception{
        List list = this.findTypeData(clazzName, modelId,type, false);
        if (list!=null && !list.isEmpty()) {
            MscAuthData mscAuthData=(MscAuthData)list.get(0);
            return mscAuthData;
        }
        return null;
    }
    /**
     * 查找所有权限数据
     * @param clazzName
     * @param modelId
     * @return
     */
    public List findReaderData(String clazzName,String modelId) throws Exception{
        return this.findTypeData(clazzName,modelId,null,true);
    }

    /**
     * 查找拥有者数据
     * @param clazzName
     * @param modelId
     * @return
     */
    public MscAuthData findOwner(String clazzName,String modelId) throws Exception{
        List list = this.findTypeData(clazzName, modelId, AuthRoleConstant.DATA_AUTH_TYPE_OWNER,true);
        if (list!=null && !list.isEmpty()) {
            MscAuthData mscAuthData=(MscAuthData)list.get(0);
            return mscAuthData;
        }
        return null;
    }


    /**
     * 是否可阅读者
     * @param clazzName
     * @param modelId
     * @return
     */
    public boolean isReader(String clazzName,String modelId){
        try {
            List list=findReaderData(clazzName, modelId);
            if(list!=null && !list.isEmpty()){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 是否可编辑者
     * @param clazzName
     * @param modelId
     * @return
     */
    public boolean isEditor(String clazzName,String modelId){
        try {
            if(isOwner(clazzName,modelId)){//先校验是否拥有者
                return true;
            }
            List list=findTypeData(clazzName, modelId, AuthRoleConstant.DATA_AUTH_TYPE_EDITOR,true);
            if(list!=null && !list.isEmpty()){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 是否拥有者
     * @param clazzName
     * @param modelId
     * @return
     */
    public boolean isOwner(String clazzName,String modelId){
        //获取拥有者类型的数据，该类型数据仅可能有一条
        try {
            MscAuthData mscAuthData=findOwner(clazzName,modelId);
            if(mscAuthData!=null){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    /**
     * 是否可下载者
     * @param clazzName
     * @param modelId
     * @return
     */
    public boolean isDowner(String clazzName,String modelId){

        try {
            if(isOwner(clazzName,modelId)){//先校验是否拥有者
                return true;
            }
            List list=findTypeData(clazzName, modelId, AuthRoleConstant.DATA_AUTH_TYPE_DOWNER,true);
            if(list!=null && !list.isEmpty()){
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
