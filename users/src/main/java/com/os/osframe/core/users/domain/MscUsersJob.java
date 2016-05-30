package com.os.osframe.core.users.domain;

import com.os.osframe.frame.convert.ManyToManyListEditor;
import com.os.osframe.frame.domain.BaseDomain;
import com.os.osframe.core.users.domain.MscUsersInfo;
import com.os.osframe.util.EntityUtil;
import com.os.osframe.util.StringUtil;
import org.springframework.web.bind.ServletRequestDataBinder;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 岗位信息
 * Created by wangdc on 2016-03-01 23:39.
 */
@Entity
@Table(name="msc_users_job")
public class MscUsersJob extends BaseDomain{
    
    /**
     *级别
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
     *岗位类型
     */
    @ManyToOne
    @JoinColumn(name="lb_job_type") 
    protected MscUsersJobType lbJobType;
    public MscUsersJobType getLbJobType() {
        return lbJobType;
    }
    public void setLbJobType(MscUsersJobType lbJobType) {
        this.lbJobType = lbJobType;
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
    @JoinTable(name="msc_users_job_person", joinColumns = { @JoinColumn(name = "lb_source_id") }, inverseJoinColumns = { @JoinColumn(name = "lb_target_id") })
    protected List<MscUsersInfo> lbPersonList;
    public List<MscUsersInfo> getLbPersonList() {
        return lbPersonList;
    }
    public void setLbPersonList(List<MscUsersInfo> lbPersonList) {
        this.lbPersonList = lbPersonList;
    }
    public String getLbPersonListIds() {
        return EntityUtil.getEntityFieldList(this.lbPersonList,"pkId");
    }
    public String getLbPersonListNames() {
        return EntityUtil.getEntityFieldList(this.lbPersonList,"lbName");
    }

    @Override
    public void convertFields(HttpServletRequest request, ServletRequestDataBinder binder) {
        super.convertFields( request,  binder);
        binder.registerCustomEditor(List.class,"lbPersonList", new ManyToManyListEditor(MscUsersInfo.class,"lbName"));
    }

    @Override
    public void domainSetFields(){
    
        if(lbJobType!=null && StringUtil.isNull(lbJobType.getPkId())){
            lbJobType=null;
        }
        if(lbUsersInfo!=null && StringUtil.isNull(lbUsersInfo.getPkId())){
            lbUsersInfo=null;
        }
    }
}
