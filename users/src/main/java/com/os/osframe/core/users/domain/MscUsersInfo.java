package com.os.osframe.core.users.domain;

import com.os.osframe.frame.domain.BaseDomain;
import com.os.osframe.util.StringUtil;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

/**
 * 组织基础信息
 * Created by wangdc on 2016-03-01 23:39.
 */
@Entity
@Table(name="msc_users_info")
public class MscUsersInfo extends BaseDomain{
    
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
     *层级
     * 直接使用时会出现无法找到
     */
    @Lob
    @Type(type="org.hibernate.type.MaterializedClobType")
    @Basic(fetch=FetchType.LAZY)
    @Column(name="lb_levels_id",length = 2000)
    protected String lbLevelsId;
    public String getLbLevelsId() {
        return lbLevelsId;
    }
    public void setLbLevelsId(String lbLevelsId) {
        this.lbLevelsId = lbLevelsId;
    }
    /**
     *是否虚拟
     */
    @Column(name="lb_is_virtual",length = 10)
    protected String lbIsVirtual;
    public String getLbIsVirtual() {
        return lbIsVirtual;
    }
    public void setLbIsVirtual(String lbIsVirtual) {
        this.lbIsVirtual = lbIsVirtual;
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
     *创建时间
     */
    @Column(name="lb_create_time")
    protected Date lbCreateTime;
    public Date getLbCreateTime() {
        return lbCreateTime;
    }
    public void setLbCreateTime(Date lbCreateTime) {
        this.lbCreateTime = lbCreateTime;
    }
    /**
     *修改时间
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
     *集成源关键字
     */
    @Column(name="lb_source_key",length = 200)
    protected String lbSourceKey;
    public String getLbSourceKey() {
        return lbSourceKey;
    }
    public void setLbSourceKey(String lbSourceKey) {
        this.lbSourceKey = lbSourceKey;
    }
    /**
     *集成主键
     */
    @Column(name="lb_source_pk",length = 200)
    protected String lbSourcePk;
    public String getLbSourcePk() {
        return lbSourcePk;
    }
    public void setLbSourcePk(String lbSourcePk) {
        this.lbSourcePk = lbSourcePk;
    }
    /**
     *上级组织
     */
    @ManyToOne
    @JoinColumn(name="lb_parent") 
    protected MscUsersInfo lbParent;
    public MscUsersInfo getLbParent() {
        return lbParent;
    }
    public void setLbParent(MscUsersInfo lbParent) {
        this.lbParent = lbParent;
    }
    /**
     *上级组织
     */
    @ManyToOne
    @JoinColumn(name="lb_org_id")
    protected MscUsersInfo lbOrg;
    public MscUsersInfo getLbOrg() {
        return lbOrg;
    }
    public void setLbOrgId(MscUsersInfo lbOrg) {
        this.lbOrg = lbOrg;
    }
    @Override
    public void domainSetFields(){
    
        if(lbParent!=null && StringUtil.isNull(lbParent.getPkId())){
            lbParent=null;
        }
    }
}
