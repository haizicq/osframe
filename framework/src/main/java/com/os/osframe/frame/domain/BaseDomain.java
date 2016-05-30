package com.os.osframe.frame.domain;

import com.os.osframe.frame.common.DomainIDStrategy;
import com.os.osframe.frame.support.SupportDomain;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.web.bind.ServletRequestDataBinder;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 域模型基类,所有的域模型必须继承此类,建议大部分的域模型继承。<br>
 * 规则：<br>
 *      对应数据库表必须为单主键，字段名为sdp_id，域模型字段名为sdpId；
 * Created by wangdc on 14-4-15.
 */
@MappedSuperclass
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BaseDomain implements IBaseDomain{
    @Id
    @Column(name = "pk_id",length = 40)
    protected String pkId=null;
    @Transient
    protected List<SupportDomain> supportList;
    public String getPkId() {
        if (pkId == null) {
            pkId = DomainIDStrategy.strategyID();//自动生成ID
        }
        return pkId;
    }

    public void setPkId(String id) {
        this.pkId = id;
    }

    /**
     * 提交前重新设置域模型字段
     */
    public void domainSetFields() {

    }

    /**
     * 提交时字段转换
     */
    public void convertFields(HttpServletRequest request, ServletRequestDataBinder binder) {

    }
    /**
     * 初始化数据
     */
    public void init() {
        if (pkId == null) {
            pkId = DomainIDStrategy.strategyID();//自动生成ID
        }
    }

    public List<SupportDomain> getSupportList() {
        return supportList;
    }

    public void setSupportList(List<SupportDomain> supportList) {
        this.supportList = supportList;
    }

    /**
     * 转为字符串
     * @return
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
