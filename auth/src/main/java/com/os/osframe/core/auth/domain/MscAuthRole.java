package com.os.osframe.core.auth.domain;

import com.os.osframe.frame.convert.ManyToManyListEditor;
import com.os.osframe.frame.domain.BaseDomain;
import com.os.osframe.core.users.domain.MscUsersInfo;
import com.os.osframe.frame.interfaces.IRoleDomain;
import com.os.osframe.util.EntityUtil;
import com.os.osframe.util.StringUtil;
import org.springframework.web.bind.ServletRequestDataBinder;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 角色信息
 * Created by wangdc on 2016-04-12 16:42.
 */
@Entity
@Table(name="msc_auth_role")
public class MscAuthRole extends BaseDomain implements IRoleDomain{
    
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
     *类型
     */
    @Column(name="lb_type",length = 200)
    protected String lbType;
    public String getLbType() {
        return lbType;
    }
    public void setLbType(String lbType) {
        this.lbType = lbType;
    }
    /**
     *是否有效
     */
    @Column(name="lb_is_valid",length = 10)
    protected String lbIsValid;
    public String getLbIsValid() {
        return lbIsValid;
    }
    public void setLbIsValid(String lbIsValid) {
        this.lbIsValid = lbIsValid;
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
    /**
     *上级角色
     */
    @ManyToOne
    @JoinColumn(name="lb_parent") 
    protected MscAuthRole lbParent;
    public MscAuthRole getLbParent() {
        return lbParent;
    }
    public void setLbParent(MscAuthRole lbParent) {
        this.lbParent = lbParent;
    }
    /**
     *角色分类
     */
    @ManyToOne
    @JoinColumn(name="lb_role_category") 
    protected MscAuthCategory lbRoleCategory;
    public MscAuthCategory getLbRoleCategory() {
        return lbRoleCategory;
    }
    public void setLbRoleCategory(MscAuthCategory lbRoleCategory) {
        this.lbRoleCategory = lbRoleCategory;
    }
    /**
     *角色权限
     */
    @ManyToMany(targetEntity=com.os.osframe.core.auth.domain.MscAuthInfo.class)
    @JoinTable(name="msc_auth_role_auth", joinColumns = { @JoinColumn(name = "lb_source_id") }, inverseJoinColumns = { @JoinColumn(name = "lb_target_id") })
    protected List<MscAuthInfo> lbAuthList;
    public List<MscAuthInfo> getLbAuthList() {
        return lbAuthList;
    }
    public void setLbAuthList(List<MscAuthInfo> lbAuthList) {
        this.lbAuthList = lbAuthList;
    }
    public String getLbAuthListIds() {
        return EntityUtil.getEntityFieldList(this.lbAuthList,"pkId");
    }
    public String getLbAuthListNames() {
        return EntityUtil.getEntityFieldList(this.lbAuthList,"lbName");
    }
    /**
     *角色成员
     */
    @ManyToMany(targetEntity=com.os.osframe.core.users.domain.MscUsersInfo.class)
    @JoinTable(name="msc_auth_role_person", joinColumns = { @JoinColumn(name = "lb_source_id") }, inverseJoinColumns = { @JoinColumn(name = "lb_target_id") })
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
    public void domainSetFields(){
    
        if(lbParent!=null && StringUtil.isNull(lbParent.getPkId())){
            lbParent=null;
        }
        if(lbRoleCategory!=null && StringUtil.isNull(lbRoleCategory.getPkId())){
            lbRoleCategory=null;
        }
    }
    @Override
    public void convertFields(HttpServletRequest request, ServletRequestDataBinder binder) {
       super.convertFields( request,  binder);
    
    binder.registerCustomEditor(List.class,"lbAuthList", new ManyToManyListEditor(MscAuthInfo.class));
    binder.registerCustomEditor(List.class,"lbPersonList", new ManyToManyListEditor(MscUsersInfo.class));
    }

    public String getType() {
        return this.lbType;
    }

    public List getAuthList() {
        return this.lbAuthList;
    }
}
