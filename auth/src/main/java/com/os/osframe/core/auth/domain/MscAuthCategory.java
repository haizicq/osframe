package com.os.osframe.core.auth.domain;

import com.os.osframe.frame.domain.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


/**
 * 角色分组
 * Created by wangdc on 2016-04-12 16:42.
 */
@Entity
@Table(name="msc_auth_category")
public class MscAuthCategory extends BaseDomain{
    
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
     *关键字
     */
    @Column(name="lb_key",length = 200)
    protected String lbKey;
    public String getLbKey() {
        return lbKey;
    }
    public void setLbKey(String lbKey) {
        this.lbKey = lbKey;
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
    /**
     *备注
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
}
