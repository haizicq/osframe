package com.os.osframe.frame.dao;

import com.os.osframe.frame.common.*;
import com.os.osframe.frame.domain.IBaseDomain;
import com.os.osframe.util.StringUtil;
import org.hibernate.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateObjectRetrievalFailureException;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * DAO基类，其它DAO可以直接继承这个DAO，不但可以复用共用的方法，还可以获得泛型的好处。
 * @param <T>
 *Created by wangdc on 14-4-15.
 */
public class BaseDaoImpl<T> implements IBaseDao<T> {
    /**
     * 全局函数 获取域模型信息
     */
    private DomainInfo domainInfo;
    private Class<T> domainClass;
    @Autowired
    private SessionFactory sessionFactory;
    private Session session;
    /**
     * 通过反射获取子类确定的泛型类
     */
    public BaseDaoImpl() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        domainClass = (Class) params[0];
        domainInfo=new DomainInfo(domainClass);
    }

    /**
     * 获取session
     * @return
     */
    public Session getSession(){
        return  session = sessionFactory.getCurrentSession();
    }



    /**
     * 保存域模型
     * @param domain
     */
    public void save(T domain)throws Exception {
        if(domain instanceof IBaseDomain){
            ((IBaseDomain)domain).domainSetFields();//统一设置域模型字段值
        }
        getSession().save(domain);
    }

    /**
     * 更改域模型
     * @param domain
     */
    public void update(T domain)throws Exception {
        if(domain instanceof IBaseDomain){
            ((IBaseDomain)domain).domainSetFields();//统一设置域模型字段值
        }
        getSession().update(domain);
    }

    /**
     * 保存或域模型
     * @param domain
     */
    public void saveOrUpdate(T domain) throws Exception{
        if(domain instanceof IBaseDomain){
            ((IBaseDomain)domain).domainSetFields();//统一设置域模型字段值
        }
        getSession().saveOrUpdate(domain);
    }
    /**
     * 删除域模型
     * @param domain
     */
    public void remove(T domain) throws Exception{
        getSession().delete(domain);
    }
    /**
     * 删除多个域模型
     * @param domains
     */
    public void removeAll(Collection domains) throws Exception{
        for(Object entity : domains){
            getSession().delete(entity);
        }
    }
    /**
     * 删除域模型
     * @param id
     */
    public void remove(Serializable id) throws Exception{
        IBaseDomain baseDomain=(IBaseDomain)domainClass.newInstance();//根据类直接初始化
        baseDomain.setPkId((String)id);
        getSession().delete(baseDomain);
    }
    /**
     * 根据ID加载域模型实例
     *      存在延时加载
     * @param id
     * @return 返回相应的持久化PO实例
     */
    public T load(Serializable id) throws Exception{
        return (T) getSession().load(domainClass, id);
    }

    /**
     * 获取PO的所有对象
     * @return
     */
    public List<T> loadAll() throws Exception{
        return null;//session.loadAll(domainClass);
    }
    /**
     * 根据ID获取PO实例
     *      不存在延时加载
     * @param id
     * @return 返回相应的持久化PO实例
     */
    public T get(Serializable id) throws Exception {
        return (T) getSession().get(domainClass, id);
    }

    /**
     * 根据ID查询
     *      不使用延时加载
     * @param id
     * @return
     * @throws Exception
     */
    public T findById(Serializable id) throws Exception{
        return findById(id, false);
    }
    /**
     * 根据ID查询
     * @param id
     * @param noLazy 是否缓存 true 不使用延时加载
     * @return
     * @throws Exception
     */
    public T findById(Serializable id,boolean noLazy) throws Exception {
        T rtnVal = null;
        if (id != null) {
            try {
                    if (noLazy){
                        rtnVal = (T)getSession().get(domainClass, id);
                    }else{
                        rtnVal = (T)getSession().load(domainClass, id);
                    }
            } catch (HibernateObjectRetrievalFailureException e) {
            }
        }
        return  rtnVal;
    }

    /**
     * 查询多项
     * @param ids
     * @return
     * @throws Exception
     */
    public List<T> findByIds(Serializable[] ids) throws Exception {
        ArrayList modelList = new ArrayList();
        T model;
        for (int i = 0; i < ids.length; i++) {
            model = findById(ids[i]);
            if (model != null)
                modelList.add(model);
        }
        return modelList;
    }

    /**
     * 执行HQL查询
     *
     * @param hql
     * @return 查询结果
     */
    public List find(String hql)throws Exception {
        return this.find(hql, (Object[]) null);
    }
    /**
     * 执行HQL查询
     * @param hqlObject
     * @return 查询结果
     */
    public List find(HqlObject hqlObject)throws Exception {
        //此操作意义不大，暂时不用
        //hqlObject.setFromExtract(null);//直接用dao查询对象时默认使用当前域模型，如确实需要修改formExtract请自行调用转换为hql直接查询
        Query query=hqlObjToQuery(hqlObject);
        if(StringUtil.isNotNull(hqlObject.getSelectExtract()) && hqlObject.isReMap()){//当定义了查询语句时，且设置了返回map时生效
            query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        }
        return query.list();
    }
    /**
     * 执行HQL查询
     * @param hqlObject
     * @return 查询结果
     */
    public PageInfo findPageInfo(HqlObject hqlObject)throws Exception {
        PageInfo page = null;
        hqlObject.setIsSearchCount(true);
        int  total = ((Long) hqlObjToQuery(hqlObject).iterate().next()).intValue();
        hqlObject.setIsSearchCount(false);
        if (total > 0) {
            String order = hqlObject.getOrderByExtract();
            String tableName = getDomainInfo().getSimpleName();
            if (StringUtil.isNotNull(order)) {
                Pattern p = Pattern.compile(",\\s*" + tableName
                        + "\\.pkId\\s*|,\\s*pkId\\s*");
                if (!p.matcher("," + order).find() && !"basedomain".equals(tableName.toLowerCase())) {//分页查询默认将id添加上去
                    hqlObject.setOrderByExtract(order + "," + tableName + ".pkId desc");
                }
            }else{
                hqlObject.setOrderByExtract(order + "," + tableName + ".pkId desc");
            }
            page = new PageInfo();
            page.setRowsize(hqlObject.getRowSize());
            page.setPageno(hqlObject.getPageNo());
            page.setTotalrows(total);
            page.excecute();
            Query q = hqlObjToQuery(hqlObject);
            q.setFirstResult(page.getStart());
            q.setMaxResults(page.getRowsize());
            page.setSimpleDomain(domainInfo.getSimpleName());//获取简单的模型变量来构造列表的界面
            page.setList(q.list());
        }
        if (page == null) {
            page = PageInfo.getEmptyPage();
        }
        return page;
    }
    /**
     * 执行带参的HQL查询
     *
     * @param hql
     * @param params
     * @return 查询结果
     */
    public List find(String hql, Object... params)throws Exception {
        Query queryObject = getSession().createQuery(hql);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                queryObject.setParameter(i, params[i]);
            }
        }
        return queryObject.list();
    }

    /**
     * 将hql对象转为query对象
     * @param hqlObject
     * @return
     * @throws Exception
     */
    protected Query hqlObjToQuery(HqlObject hqlObject) throws Exception {
        if(hqlObject.getDomainInfo()==null){
            hqlObject.setDomainInfo(domainInfo);
        }
        String hql= HqlObjDeal.dealHqlObj(hqlObject);
        Query query = getSession().createQuery(hql);
        for (HqlParam param : hqlObject.getParamList()) {
            if (param.getType() == null) {
                if (param.getValue() instanceof Collection<?>) {
                    Collection<?> value = (Collection<?>) param.getValue();
                    query.setParameterList(param.getName(), value);
                } else {
                    query.setParameter(param.getName(), param
                            .getValue());
                }
            } else {
                if (param.getValue() instanceof Collection<?>) {
                    Collection<?> value = (Collection<?>) param.getValue();
                    query.setParameterList(param.getName(), value,
                            param.getType());
                } else {
                    query.setParameter(param.getName(), param.getValue(), param.getType());
                }
            }
        }
        return query;
    }
    /**
     * 通过sql语句直接查询
     * @param sql
     * @param reMap true-以map形式返回（默认）
     * @return
     * @throws Exception
     */
    public List findBySql(String sql,Boolean reMap)throws Exception {
        SQLQuery sqlQuery = (SQLQuery)this.getSession().createSQLQuery(sql);
        if(reMap==null || true==reMap){
            sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        }
        List<Map> list=sqlQuery.list();
        return list;
    }

    /**
     * sql查询
     * @param sql
     * @return
     * @throws Exception
     */
    public List findBySql(String sql)throws Exception {
        System.out.println("执行sql语句="+domainClass.getName());
        return null;
        //return this.findBySql(sql, null);
    }

    /**
     * 执行sql语句
     * @param sql
     * @throws SQLException
     */
    public void executeBySql(String sql) throws SQLException {
        SQLQuery sqlQuery = (SQLQuery)getSession().createSQLQuery(sql);
        sqlQuery.executeUpdate();
    }
    /**
     * 对延迟加载的实体PO执行初始化
     * @param domain
     */
    public void initialize(Object domain)throws Exception {
        Hibernate.initialize(domain);
    }

    /**
     * 获取域模型名称
     * @return
     */
    public String getDomainName() {
        return domainClass.getName();
    }
    /**
     * 获取模型类
     * @return
     */
    public Class getDomainClass(){
        return domainClass;
    }

    /**
     * 获取域模型信息
     * @return
     */
    public DomainInfo getDomainInfo() {
        return domainInfo;
    }

/**
 * 1、添加分页操作
 * 2、添加分库操作（待定）
 * 3、添加sql查询
 */

}