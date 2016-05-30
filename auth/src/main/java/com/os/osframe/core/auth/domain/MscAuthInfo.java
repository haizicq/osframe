package com.os.osframe.core.auth.domain;

import com.os.osframe.frame.domain.BaseDomain;
import com.os.osframe.frame.interfaces.IAuthDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


/**
 * 权限信息
 * Created by wangdc on 2016-04-12 16:42.
 */
@Entity
@Table(name="msc_auth_info")
public class MscAuthInfo extends BaseDomain implements IAuthDomain{
    
    /**
     *名称
     */
    @Column(name="lb_name",length = 200)
    protected String lbName;
    public String getLbName() {
        return lbName;
    }
    public void setLbName(String lbName) {
        this.lbName = lbName;
    }
    /**
     *描述
     */
    @Column(name="lb_info",length = 255)
    protected String lbInfo;
    public String getLbInfo() {
        return lbInfo;
    }
    public void setLbInfo(String lbInfo) {
        this.lbInfo = lbInfo;
    }
    /**
     *权限代码
     */
    @Column(name="lb_auth_key",length = 200)
    protected String lbAuthKey;
    public String getLbAuthKey() {
        return lbAuthKey;
    }
    public void setLbAuthKey(String lbAuthKey) {
        this.lbAuthKey = lbAuthKey;
    }
    /**
     *URL前缀
     */
    @Column(name="lb_url_prefix",length = 200)
    protected String lbUrlPrefix;
    public String getLbUrlPrefix() {
        return lbUrlPrefix;
    }
    public void setLbUrlPrefix(String lbUrlPrefix) {
        this.lbUrlPrefix = lbUrlPrefix;
    }
    /**
     *所属模块
     */
    @Column(name="lb_module",length = 200)
    protected String lbModule;
    public String getLbModule() {
        return lbModule;
    }
    public void setLbModule(String lbModule) {
        this.lbModule = lbModule;
    }
    /**
     *所属系统
     */
    @Column(name="lb_system",length = 200)
    protected String lbSystem;
    public String getLbSystem() {
        return lbSystem;
    }
    public void setLbSystem(String lbSystem) {
        this.lbSystem = lbSystem;
    }
    /**
     *所属模块
     */
    @Column(name="lb_module_key",length = 200)
    protected String lbModuleKey;
    public String getLbModuleKey() {
        return lbModuleKey;
    }
    public void setLbModuleKey(String lbModuleKey) {
        this.lbModuleKey = lbModuleKey;
    }
    /**
     *所属系统
     */
    @Column(name="lb_system_key",length = 200)
    protected String lbSystemKey;
    public String getLbSystemKey() {
        return lbSystemKey;
    }
    public void setLbSystemKey(String lbSystemKey) {
        this.lbSystemKey = lbSystemKey;
    }

    /**
     *更新时间
     */
    @Column(name="lb_update_time")
    protected Date lbUpdateTime;
    public Date getLbUpdateTime() {
        return lbUpdateTime;
    }
    public void setLbUpdateTime(Date lbUpdateTime) {
        this.lbUpdateTime = lbUpdateTime;
    }
    /**
     *排序号
     */
    @Column(name="lb_order")
    protected Integer lbOrder;
    public Integer getLbOrder() {
        return lbOrder;
    }
    public void setLbOrder(Integer lbOrder) {
        this.lbOrder = lbOrder;
    }

    public String getAuthKey() {
        return this.lbAuthKey;
    }
}
