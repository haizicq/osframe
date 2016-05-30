package com.os.osframe.core.users.domain;

import com.os.osframe.frame.domain.BaseDomain;
import com.os.osframe.core.users.domain.MscUsersInfo;
import com.os.osframe.util.StringUtil;

import javax.persistence.*;
import java.util.List;

/**
 * 群组岗位类型
 * Created by wangdc on 2016-03-01 23:39.
 */
@Entity
@Table(name="msc_users_group_job")
public class MscUsersGroupJob extends BaseDomain{
    
    /**
     *岗位级别
     */
    @Column(name="lb_level",length = 200)
    protected String lbLevel;
    public String getLbLevel() {
        return lbLevel;
    }
    public void setLbLevel(String lbLevel) {
        this.lbLevel = lbLevel;
    }
    /**
     *基础信息
     */
    @ManyToOne
    @JoinColumn(name="lb_users_info") 
    protected MscUsersInfo lbUsersInfo;
    public MscUsersInfo getLbUsersInfo() {
        return lbUsersInfo;
    }
    public void setLbUsersInfo(MscUsersInfo lbUsersInfo) {
        this.lbUsersInfo = lbUsersInfo;
    }
    /**
     *成员列表
     */
    @ManyToMany(targetEntity=com.os.osframe.core.users.domain.MscUsersInfo.class)
    @JoinTable(name="msc_users_group_job_person", joinColumns = { @JoinColumn(name = "lb_source_id") }, inverseJoinColumns = { @JoinColumn(name = "lb_target_id") })
    protected List<MscUsersInfo> lbPersonList;
    public List<MscUsersInfo> getLbPersonList() {
        return lbPersonList;
    }
    public void setLbPersonList(List<MscUsersInfo> lbPersonList) {
        this.lbPersonList = lbPersonList;
    }
    @Override
    public void domainSetFields(){
    
        if(lbUsersInfo!=null && StringUtil.isNull(lbUsersInfo.getPkId())){
            lbUsersInfo=null;
        }
    }
}
