package com.os.osframe.core.users.domain;

import com.os.osframe.frame.domain.BaseDomain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


/**
 * 群组类型
 * Created by wangdc on 2016-03-01 23:39.
 */
@Entity
@Table(name="msc_users_group_type")
public class MscUsersGroupType extends BaseDomain{
    
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
     *编码
     */
    @Column(name="lb_no",length = 200)
    protected String lbNo;
    public String getLbNo() {
        return lbNo;
    }
    public void setLbNo(String lbNo) {
        this.lbNo = lbNo;
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
     *备注
     */
    @Column(name="lb_info",length = 200)
    protected String lbInfo;
    public String getLbInfo() {
        return lbInfo;
    }
    public void setLbInfo(String lbInfo) {
        this.lbInfo = lbInfo;
    }
    /**
     *状态
     */
    @Column(name="lb_status",length = 20)
    protected String lbStatus;
    public String getLbStatus() {
        return lbStatus;
    }
    public void setLbStatus(String lbStatus) {
        this.lbStatus = lbStatus;
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
    @Override
    public void domainSetFields(){
    
    }
}
