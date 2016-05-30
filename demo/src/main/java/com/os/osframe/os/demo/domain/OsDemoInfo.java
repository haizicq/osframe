package com.os.osframe.os.demo.domain;

import com.os.osframe.frame.domain.BaseDomain;
import com.os.osframe.core.users.domain.MscUsersInfo;
import com.os.osframe.util.StringUtil;

import javax.persistence.*;
import java.util.Date;

/**
 * 权限信息
 * Created by wangdc on 2016-06-06 23:06.
 */
@Entity
@Table(name="os_demo_info")
public class OsDemoInfo extends BaseDomain{
    
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
     *类型
     */
    @Column(name="lb_type",length = 2)
    protected String lbType;
    public String getLbType() {
        return lbType;
    }
    public void setLbType(String lbType) {
        this.lbType = lbType;
    }
    /**
     *测试时间
     */
    @Column(name="lb_time")
    protected Date lbTime;
    public Date getLbTime() {
        return lbTime;
    }
    public void setLbTime(Date lbTime) {
        this.lbTime = lbTime;
    }
    /**
     *上级
     */
    @ManyToOne
    @JoinColumn(name="lb_parent") 
    protected OsDemoInfo lbParent;
    public OsDemoInfo getLbParent() {
        return lbParent;
    }
    public void setLbParent(OsDemoInfo lbParent) {
        this.lbParent = lbParent;
    }
    /**
     *所属组织
     */
    @ManyToOne
    @JoinColumn(name="lb_dept") 
    protected MscUsersInfo lbDept;
    public MscUsersInfo getLbDept() {
        return lbDept;
    }
    public void setLbDept(MscUsersInfo lbDept) {
        this.lbDept = lbDept;
    }
    @Override
    public void domainSetFields(){
    
        if(lbParent!=null && StringUtil.isNull(lbParent.getPkId())){
            lbParent=null;
        }
        if(lbDept!=null && StringUtil.isNull(lbDept.getPkId())){
            lbDept=null;
        }
    }
}
