package com.os.osframe.core.auth.service;

import com.os.osframe.frame.service.IBaseService;
import com.os.osframe.core.auth.domain.MscAuthData;

import java.util.List;

/**
 * 数据权限
 * Created by wangdc on 2016-04-12 16:42.
 */
public interface IMscAuthDataService extends IBaseService<MscAuthData> {

    /**
     * 查找指定类型的所有权限数据
     * @param clazzName  类路径名
     * @param modelId 实体id
     * @param type 类型
     * @return
     */
    public List findTypeData(String clazzName, String modelId, String type, boolean isOwner) throws Exception;
    /**
     * 查找所有权限数据
     * @param clazzName
     * @param modelId
     * @return
     */
    public List findReaderData(String clazzName, String modelId) throws Exception;

    /**
     * 查找拥有者数据
     * @param clazzName
     * @param modelId
     * @return
     */
    public MscAuthData findOwner(String clazzName, String modelId) throws Exception;

    /**
     * 查找数据对象
     * @param clazzName
     * @param modelId
     * @param type
     * @return
     */
    public MscAuthData findDataObj(String clazzName, String modelId, String type) throws Exception;


    /**
     * 是否可阅读者
     * @param clazzName
     * @param modelId
     * @return
     */
    public boolean isReader(String clazzName, String modelId);

    /**
     * 是否可编辑者
     * @param clazzName
     * @param modelId
     * @return
     */
    public boolean isEditor(String clazzName, String modelId);

    /**
     * 是否拥有者
     * @param clazzName
     * @param modelId
     * @return
     */
    public boolean isOwner(String clazzName, String modelId);
    /**
     * 是否可下载者
     * @param clazzName
     * @param modelId
     * @return
     */
    public boolean isDowner(String clazzName, String modelId);

    /**
     * 查找数据对象
     * @param clazzName
     * @param modelId
     * @return
     */
    public MscAuthData findDataObj(String clazzName, String modelId) throws Exception;

}
