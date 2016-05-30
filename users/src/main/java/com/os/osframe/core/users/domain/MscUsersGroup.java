package com.os.osframe.core.users.domain;

import com.os.osframe.frame.domain.BaseDomain;
import com.os.osframe.core.users.domain.MscUsersInfo;
import com.os.osframe.util.StringUtil;

import javax.persistence.*;
import java.util.List;

/**
 * 群组组织
 * Created by wangdc on 2016-03-01 23:39.
 */
@Entity
@Table(name="msc_users_group")
public class MscUsersGroup extends BaseDomain{
    
    /**
     *群组领导
     */
    @ManyToOne
    @JoinColumn(name="lb_leader") 
    protected MscUsersInfo lbLeader;
    public MscUsersInfo getLbLeader() {
        return lbLeader;
    }
    public void setLbLeader(MscUsersInfo lbLeader) {
        this.lbLeader = lbLeader;
    }
    /**
     *群组类型
     */
    @ManyToOne
    @JoinColumn(name="lb_group_type") 
    protected MscUsersGroupType lbGroupType;
    public MscUsersGroupType getLbGroupType() {
        return lbGroupType;
    }
    public void setLbGroupType(MscUsersGroupType lbGroupType) {
        this.lbGroupType = lbGroupType;
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
    @JoinTable(name="msc_users_group_persons", joinColumns = { @JoinColumn(name = "lb_group_id") }, inverseJoinColumns = { @JoinColumn(name = "lb_person_id") })
    protected List<MscUsersInfo> lbPersonList;
    public List<MscUsersInfo> getLbPersonList() {
        return lbPersonList;
    }
    public void setLbPersonList(List<MscUsersInfo> lbPersonList) {
        this.lbPersonList = lbPersonList;
    }
    @Override
    public void domainSetFields(){
    
        if(lbLeader!=null && StringUtil.isNull(lbLeader.getPkId())){
            lbLeader=null;
        }
        if(lbGroupType!=null && StringUtil.isNull(lbGroupType.getPkId())){
            lbGroupType=null;
        }
        if(lbUsersInfo!=null && StringUtil.isNull(lbUsersInfo.getPkId())){
            lbUsersInfo=null;
        }
    }
}
