package com.os.osframe.frame.service;

import com.os.osframe.frame.common.DomainInfo;
import com.os.osframe.frame.common.HqlObject;
import com.os.osframe.frame.common.PageInfo;
import com.os.osframe.frame.dao.IBaseDao;
import com.os.osframe.frame.domain.BaseDomain;
import com.os.osframe.frame.domain.IBaseDomain;
import com.os.osframe.frame.support.ISupportService;
import com.os.osframe.frame.support.SupportDomain;
import com.os.osframe.frame.support.RegisterCenter;
import com.os.osframe.frame.util.SpringUtils;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by wangdc on 14-4-16.
 */
public abstract class BaseServiceImpl<T> implements IBaseService<T> {
    public abstract IBaseDao getBaseDao();

    /**
     * 获取spring Bean
     */
    public Object getBean(String beanName) throws Exception {
        if (beanName == null)
            return null;
        return SpringUtils.getBean(beanName);
    }


    /**
     * 根据ID查询
     * @param id
     * @return
     * @throws Exception
     */
    public  T findById(Serializable id)throws Exception {
        return (T)getBaseDao().findById(id);
    }
    /**
     * 查询多项
     * @param ids
     * @return
     * @throws Exception
     */
    public  List<T> findByIds(Serializable[] ids) throws Exception{
        return getBaseDao().findByIds(ids);
    }


    /**
     * 保存域模型
     * @param domain
     */
    public void save( T domain)throws Exception {
        getBaseDao().save(domain);
        //保存后需要保存支撑数
        saveSupportDomain(domain);
    }

    /**
     * 更改域模型
     * @param domain
     */
    public void update(T domain)throws Exception {
        getBaseDao().update(domain);
        //保存后需要保存支撑数
        saveSupportDomain(domain);
    }

    /**
     * 保存或域模型
     * @param domain
     */
    public void saveOrUpdate(T domain) throws Exception{
        getBaseDao().saveOrUpdate(domain);
        //保存后需要保存支撑数
        saveSupportDomain(domain);
    }

    /**
     * 保存支撑类的数据
     * @param domain
     */
    private void saveSupportDomain(T domain) throws Exception{
        BaseDomain baseDomain=(BaseDomain)domain;
        //支撑数据不为空时
        if(baseDomain!=null && baseDomain.getSupportList()!=null && !baseDomain.getSupportList().isEmpty()){
            String clazz=baseDomain.getClass().getName();
            for(int i=0,len=baseDomain.getSupportList().size();i<len;i++){
                SupportDomain supportDomain=baseDomain.getSupportList().get(i);
                ISupportService supportService=(ISupportService) RegisterCenter.getInstance().getBean(supportDomain.getType());
                if(supportService==null){
                    continue;
                }
                supportService.saveSupport(supportDomain,baseDomain.getPkId(),clazz);
            }
        }
    }
    /**
     * 保存域模型
     * @param domain
     * @param request
     * @param model
     * @param
     * @throws Exception
     */
    public void save( T domain,HttpServletRequest request,Model model)throws Exception {
        this.save(domain);
    }

    /**
     * 更改域模型
     * @param domain
     */
    public void update(T domain,HttpServletRequest request,Model model)throws Exception {
        this.update(domain);
    }

    /**
     * 保存或域模型
     * @param domain
     * @param request
     * @param model
     * @param
     * @throws Exception
     */
    public void saveOrUpdate(T domain,HttpServletRequest request,Model model) throws Exception{
        this.saveOrUpdate(domain);
    }
    /**
     * 删除域模型
     * @param domain
     */
    public void delete(T domain) throws Exception{
        getBaseDao().remove(domain);
    }
    /**
     * 删除域模型
     * @param id
     */
    public void delete(String id) throws Exception{
        IBaseDomain baseDomain=(IBaseDomain)getBaseDao().getDomainClass().newInstance();//根据类直接初始化
        baseDomain.setPkId(id);
        this.delete((T)baseDomain);
    }
    /**
     * 删除域模型
     * @param domain
     */
    public  void delete(T domain,HttpServletRequest request,Model model) throws Exception{
        this.delete(domain);
    }
    /**
     * 删除域模型
     * @param id
     */
    public  void delete(String id,HttpServletRequest request,Model model) throws Exception{
        IBaseDomain baseDomain=(IBaseDomain)getBaseDao().getDomainClass().newInstance();//根据类直接初始化
        baseDomain.setPkId(id);
        this.delete((T)baseDomain, request, model);
    }
    /**
     * 执行HQL查询
     *
     * @param hql
     * @return 查询结果
     */
    public List find(String hql)throws Exception {
        return getBaseDao().find(hql);
    }

    /**
     * 执行带参的HQL查询
     *
     * @param hql
     * @param params
     * @return 查询结果
     */
    public List find(String hql, Object... params)throws Exception {
        return getBaseDao().find(hql, params);
    }

    /**
     * 执行HQL对象查询
     *
     * @param hqlObject
     * @return 查询结果
     */
    public List find(HqlObject hqlObject)throws Exception {
        return getBaseDao().find(hqlObject);
    }
    /**
     * 执行分页查询
     * @param hqlObject
     * @return 查询结果
     */
    public PageInfo findPageInfo(HqlObject hqlObject)throws Exception {
        return getBaseDao().findPageInfo(hqlObject);
    }
    /**
     * sql查询
     * @param sql
     * @return
     * @throws Exception
     */
    public List findBySql(String sql)throws Exception {
        return getBaseDao().findBySql(sql);
    }
    /**
     * 获取域模型信息
     * @return
     */
    public DomainInfo getDomainInfo() {
        return getBaseDao().getDomainInfo();
    }

    /**
     * 上传附件
     * @param request
     * @return
     * @throws IllegalStateException
     * @throws IOException
     */
    public String upload(HttpServletRequest request) throws IllegalStateException, IOException {
        ResourceBundle projectConfig = ResourceBundle.getBundle("config");
        String filePath=projectConfig.getString("osframe.file.path");
        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if(multipartResolver.isMultipart(request)){
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while(iter.hasNext()){
                //记录上传过程起始时的时间，用来计算上传时间
                int pre = (int) System.currentTimeMillis();
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if(file != null){
                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if(myFileName.trim() !=""){
                        System.out.println(myFileName);
                        //重命名上传后的文件名
                        String fileName = "demoUpload\\" + file.getOriginalFilename();
                        //定义上传路径
                        String path = filePath+ fileName;//"E:\\upload\\"
                        File localFile = new File(path);
                        file.transferTo(localFile);
                    }
                }
                //记录上传该文件后的时间
                int finaltime = (int) System.currentTimeMillis();
                System.out.println(finaltime - pre);
            }

        }
        return "/success";
    }

    /**
     * 1、添加exccel操作
     * 2、添加分页操作
     */
}
