package com.os.osframe.core.users.domain;

import com.os.osframe.frame.domain.BaseDomain;
import com.os.osframe.frame.interfaces.IUserModel;
import com.os.osframe.util.StringUtil;

import javax.persistence.*;
import java.util.Date;

/**
 * 人员信息
 * Created by wangdc on 2016-03-01 23:39.
 */
@Entity
@Table(name="msc_users_person")
public class MscUsersPerson extends BaseDomain implements IUserModel {
    
    /**
     *登录名
     */
    @Column(name="lb_login_name",length = 200)
    protected String lbLoginName;
    public String getLbLoginName() {
        return lbLoginName;
    }
    public void setLbLoginName(String lbLoginName) {
        this.lbLoginName = lbLoginName;
    }

    public String getUserName() {
        return lbLoginName;
    }
    public String getUserId() {
        if(lbUsersInfo!=null){
            return lbUsersInfo.getPkId();
        }
        return null;
    }

    public String getName() {
        if(lbUsersInfo!=null){
            return lbUsersInfo.getLbName();
        }
        return null;
    }
    public String getUserLevelIds() {
        if(lbUsersInfo!=null){
            return lbUsersInfo.getLbLevelsId();
        }
        return null;
    }

    public String getUserOrgId() {
        if(lbUsersInfo!=null && lbUsersInfo.getLbOrg()!=null){
            return lbUsersInfo.getLbOrg().getPkId();
        }
        return null;
    }
    public String getPwd() {
        return lbPwd;
    }
    /**
     *密码
     */
    @Column(name="lb_pwd",length = 200)
    protected String lbPwd;
    public String getLbPwd() {
        return lbPwd;
    }
    public void setLbPwd(String lbPwd) {
        this.lbPwd = lbPwd;
    }
    /**
     * 性别
     */
    @Column(name="lb_sex",length = 2)
    protected String lbSex;
    public String getLbSex() {
        return lbSex;
    }
    public void setLbSex(String lbSex) {
        this.lbSex = lbSex;
    }
    /**
     *生日
     */
    @Column(name="lb_birthday")
    protected Date lbBirthday;
    public Date getLbBirthday() {
        return lbBirthday;
    }
    public void setLbBirthday(Date lbBirthday) {
        this.lbBirthday = lbBirthday;
    }
    /**
     *邮箱
     */
    @Column(name="lb_mail",length = 200)
    protected String lbMail;
    public String getLbMail() {
        return lbMail;
    }
    public void setLbMail(String lbMail) {
        this.lbMail = lbMail;
    }
    /**
     *电话
     */
    @Column(name="lb_phone",length = 200)
    protected String lbPhone;
    public String getLbPhone() {
        return lbPhone;
    }
    public void setLbPhone(String lbPhone) {
        this.lbPhone = lbPhone;
    }
    /**
     *手机
     */
    @Column(name="lb_mobile_phone",length = 200)
    protected String lbMobilePhone;
    public String getLbMobilePhone() {
        return lbMobilePhone;
    }
    public void setLbMobilePhone(String lbMobilePhone) {
        this.lbMobilePhone = lbMobilePhone;
    }
    /**
     *其他通讯
     */
    @Column(name="lb_others",length = 255)
    protected String lbOthers;
    public String getLbOthers() {
        return lbOthers;
    }
    public void setLbOthers(String lbOthers) {
        this.lbOthers = lbOthers;
    }
    /**
     *默认语言
     */
    @Column(name="lb_language",length = 10)
    protected String lbLanguage;
    public String getLbLanguage() {
        return lbLanguage;
    }
    public void setLbLanguage(String lbLanguage) {
        this.lbLanguage = lbLanguage;
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
    @Override
    public void domainSetFields(){
    
        if(lbUsersInfo!=null && StringUtil.isNull(lbUsersInfo.getPkId())){
            lbUsersInfo=null;
        }
    }
}
