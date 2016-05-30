package com.os.osframe.frame.dao;

import com.os.osframe.frame.common.DomainInfo;
import com.os.osframe.frame.common.HqlObject;
import com.os.osframe.frame.common.PageInfo;
import org.hibernate.Session;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * 常用的数据库操作方法的接口。<br>
 * Created by wangdc on 14-4-15.
 */
public interface IBaseDao<T> {

    /**
     * 保存域模型
     * @param domain
     */
    public void save(T domain)throws Exception;

    /**
     * 更改域模型
     * @param domain
     */
    public void update(T domain)throws Exception;
    /**
     * 保存或域模型
     * @param domain
     */
    public void saveOrUpdate(T domain)throws Exception;
    /**
     * 删除域模型
     * @param domain
     */
    public void remove(T domain)throws Exception;
    /**
     * 删除域模型
     * @param id
     */
    public void remove(Serializable id) throws Exception;
    /**
     * 删除多个域模型
     * @param domains
     */
    public void removeAll(Collection domains) throws Exception;
    /**
     * 根据ID加载域模型实例
     *      存在延时加载
     * @param id
     * @return 返回相应的持久化PO实例
     */
    public T load(Serializable id)throws Exception;

    /**
     * 获取PO的所有对象
     * @return
     */
    public List<T> loadAll()throws Exception;
    /**
     * 根据ID获取PO实例
     *      不存在延时加载
     * @param id
     * @return 返回相应的持久化PO实例
     */
    public T get(Serializable id)throws Exception;

    /**
     * 根据ID查询
     *      不使用延时加载
     * @param id
     * @return
     * @throws Exception
     */
    public T findById(Serializable id) throws Exception;
    /**
     * 根据ID查询
     * @param id
     * @param noLazy 是否缓存 true 不使用延时加载
     * @return
     * @throws Exception
     */
    public T findById(Serializable id, boolean noLazy) throws Exception ;
    /**
     * 查询多项
     * @param ids
     * @return
     * @throws Exception
     */
    public  List<T> findByIds(Serializable[] ids) throws Exception;

    /**
     * 执行HQL查询
     *
     * @param hql
     * @return 查询结果
     */
    public List find(String hql)throws Exception;

    /**
     * 执行带参的HQL查询
     *
     * @param hql
     * @param params
     * @return 查询结果
     */
    public List find(String hql, Object... params)throws Exception;
    /**
     * 执行HQL查询
     * @param hqlObject
     * @return 查询结果
     */
    public List find(HqlObject hqlObject)throws Exception;
    /**
     * 执行HQL查询
     * @param hqlObject
     * @return 查询结果
     */
    public PageInfo findPageInfo(HqlObject hqlObject)throws Exception;
    /**
     * 通过sql语句直接查询
     * @param sql
     * @param reMap true-以map形式返回（默认）
     * @return
     * @throws Exception
     */
    public List findBySql(String sql, Boolean reMap)throws Exception;
    /**
     * sql查询
     * @param sql
     * @return
     * @throws Exception
     */
    public List findBySql(String sql)throws Exception;
    /**
     * 执行sql语句
     * @param sql
     * @throws SQLException
     */
    public void executeBySql(String sql) throws SQLException;
    /**
     * 对延迟加载的实体PO执行初始化
     * @param domain
     */
    public void initialize(Object domain)throws Exception;
    /**
     * 获取session
     * @return
     */
    public Session getSession();
    /**
     * 获取域模型名称
     * @return
     */
    public String getDomainName();
    /**
     * 获取模型类
     * @return
     */
    public Class getDomainClass();
    /**
     * 获取域模型信息
     * @return
     */
    public DomainInfo getDomainInfo();
}
