package com.os.osframe.core.auth.domain;

import com.os.osframe.frame.convert.ManyToManyListEditor;
import com.os.osframe.frame.domain.BaseDomain;
import com.os.osframe.core.users.domain.MscUsersInfo;
import com.os.osframe.util.EntityUtil;
import org.springframework.web.bind.ServletRequestDataBinder;

import javax.persistence.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 数据权限
 * Created by wangdc on 2016-04-12 16:42.
 */
@Entity
@Table(name="msc_auth_data")
public class MscAuthData extends BaseDomain{
    
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
     *模块ID
     */
    @Column(name="lb_model_id",length = 200)
    protected String lbModelId;
    public String getLbModelId() {
        return lbModelId;
    }
    public void setLbModelId(String lbModelId) {
        this.lbModelId = lbModelId;
    }
    /**
     *类型（阅读、编辑、下载、拥有者）
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
     *为空处理
     */
    @Column(name="lb_null_type",length = 2)
    protected String lbNullType;
    public String getLbNullType() {
        return lbNullType;
    }
    public void setLbNullType(String lbNullType) {
        this.lbNullType = lbNullType;
    }

    /**
     * 所属组织
     * 保存时，只有当为拥有者时才会自动保存，取拥有人员向上的第一个属于机构类型的组织，除非自行传入了所属组织
     */
    @Column(name="lb_org",length = 40)
    protected String lbOrg;
    public String getLbOrg() {
        return lbOrg;
    }
    public void setLbOrg(String lbOrg) {
        this.lbOrg = lbOrg;
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
     *角色成员
     */
//    @ManyToMany(targetEntity=com.os.osframe.msc.users.domain.MscUsersInfo.class)
//    @JoinTable(name="msc_auth_data_person", joinColumns = { @JoinColumn(name = "lb_source_id") }, inverseJoinColumns = { @JoinColumn(name = "lb_target_id") })
//    protected List<MscUsersInfo> lbPersonList;
//    public List<MscUsersInfo> getLbPersonList() {
//        return lbPersonList;
//    }
//    public void setLbPersonList(List<MscUsersInfo> lbPersonList) {
//        this.lbPersonList = lbPersonList;
//    }
//    public String getLbPersonListIds() {
//        return EntityUtil.getEntityFieldList(this.lbPersonList,"pkId");
//    }
//    public String getLbPersonListNames() {
//        return EntityUtil.getEntityFieldList(this.lbPersonList,"lbName");
//    }
    /**
     *角色成员
     */
    @ManyToMany(targetEntity=com.os.osframe.core.users.domain.MscUsersInfo.class)
    @JoinTable(name="msc_auth_data_all_person", joinColumns = { @JoinColumn(name = "lb_source_id") }, inverseJoinColumns = { @JoinColumn(name = "lb_target_id") })
    protected List<MscUsersInfo> lbAllPersonList;
    public List<MscUsersInfo> getLbAllPersonList() {
        return lbAllPersonList;
    }
    public void setLbAllPersonList(List<MscUsersInfo> lbAllPersonList) {
        this.lbAllPersonList = lbAllPersonList;
    }
    public String getLbAllPersonListIds() {
        return EntityUtil.getEntityFieldList(this.lbAllPersonList,"pkId");
    }
    public String getLbAllPersonListNames() {
        return EntityUtil.getEntityFieldList(this.lbAllPersonList,"lbName");
    }
    @Override
    public void convertFields(HttpServletRequest request, ServletRequestDataBinder binder) {
       super.convertFields( request,  binder);
    
    binder.registerCustomEditor(List.class,"lbPersonList", new ManyToManyListEditor(MscUsersInfo.class));
    binder.registerCustomEditor(List.class,"lbAllPersonList", new ManyToManyListEditor(MscUsersInfo.class));
    }
}
