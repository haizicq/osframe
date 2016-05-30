package com.os.osframe.core.users.domain;

import com.os.osframe.frame.domain.BaseDomain;
import com.os.osframe.util.StringUtil;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 部门组织
 * Created by wangdc on 2016-03-01 23:39.
 */
@Entity
@Table(name="msc_users_org")
public class MscUsersOrg extends BaseDomain{
    
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
     *部门领导
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
    @Override
    public void domainSetFields(){
    
        if(lbUsersInfo!=null && StringUtil.isNull(lbUsersInfo.getPkId())){
            lbUsersInfo=null;
        }
        if(lbLeader!=null && StringUtil.isNull(lbLeader.getPkId())){
            lbLeader=null;
        }
    }
}
