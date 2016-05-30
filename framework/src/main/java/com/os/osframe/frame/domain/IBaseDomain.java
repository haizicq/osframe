package com.os.osframe.frame.domain;

import com.os.osframe.frame.support.SupportDomain;
import org.springframework.web.bind.ServletRequestDataBinder;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

/**
 * 域模型的基础接口
 * Created by wangdc on 14-4-15.
 */
public interface IBaseDomain extends Serializable {

    /**
     * 获取ID
     * @return ID
     */
    public abstract String getPkId();

    /**
     * 设置ID
     *
     * @param id
     */
    public abstract void setPkId(String id);

    /**
     * 重新计算字段值
     */
    public abstract void domainSetFields();
    /**
     * 提交时字段转换
     */
    public void convertFields(HttpServletRequest request, ServletRequestDataBinder binder);
    /**
     * 初始化数据
     */
    public void init();

    public List<SupportDomain> getSupportList();

    public void setSupportList(List<SupportDomain> supportDomainList);

}
