package com.os.osframe.frame.service;

import com.os.osframe.frame.common.DomainInfo;
import com.os.osframe.frame.common.HqlObject;
import com.os.osframe.frame.common.PageInfo;
import com.os.osframe.frame.dao.IBaseDao;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

/**
 * Created by wangdc on 14-4-16.
 */
public interface IBaseService<T> {

    public abstract IBaseDao getBaseDao();
    /**
     * 获取spring Bean
     */
    public Object getBean(String beanName) throws Exception;

    /**
     * 根据ID查询
     * @param id
     * @return
     * @throws Exception
     */
    public  T findById(Serializable id)throws Exception;
    /**
     * 查询多项
     * @param ids
     * @return
     * @throws Exception
     */
    public  List<T> findByIds(Serializable[] ids) throws Exception;


    /**
     * 保存域模型
     * @param domain
     */
    public  void save(T domain)throws Exception;

    /**
     * 更改域模型
     * @param domain
     */
    public  void update(T domain)throws Exception;

    /**
     * 保存或域模型
     * @param domain
     */
    public  void saveOrUpdate(T domain) throws Exception;

    /**
     * 保存域模型
     * @param domain
     * @param request
     * @param model
     * @param
     * @throws Exception
     */
    public void save(T domain, HttpServletRequest request, Model model)throws Exception;

    /**
     * 更改域模型
     * @param domain
     */
    public  void update(T domain, HttpServletRequest request, Model model)throws Exception;

    /**
     * 保存或域模型
     * @param domain
     * @param request
     * @param model
     * @param
     * @throws Exception
     */
    public  void saveOrUpdate(T domain, HttpServletRequest request, Model model) throws Exception;
    /**
     * 删除域模型
     * @param domain
     */
    public  void delete(T domain) throws Exception;
    /**
     * 删除域模型
     * @param id
     */
    public  void delete(String id) throws Exception;
    /**
     * 删除域模型
     * @param domain
     */
    public  void delete(T domain, HttpServletRequest request, Model model) throws Exception;
    /**
     * 删除域模型
     * @param id
     */
    public  void delete(String id, HttpServletRequest request, Model model) throws Exception;
    /**
     * 执行HQL查询
     *
     * @param hql
     * @return 查询结果
     */
    public List find(String hql)throws Exception ;

    /**
     * 执行带参的HQL查询
     *
     * @param hql
     * @param params
     * @return 查询结果
     */
    public List find(String hql, Object... params)throws Exception;
    /**
     * 执行HQL对象查询
     *
     * @param hqlObject
     * @return 查询结果
     */
    public List find(HqlObject hqlObject)throws Exception;
    /**
     * 执行分页查询
     * @param hqlObject
     * @return 查询结果
     */
    public PageInfo findPageInfo(HqlObject hqlObject)throws Exception;
    /**
     * sql查询
     * @param sql
     * @return
     * @throws Exception
     */
    public List findBySql(String sql)throws Exception;
    /**
     * 上传附件
     * @param request
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    public String upload(HttpServletRequest request) throws IllegalStateException, IOException;
    /**
     * 获取域模型信息
     * @return
     */
    public DomainInfo getDomainInfo();
}
