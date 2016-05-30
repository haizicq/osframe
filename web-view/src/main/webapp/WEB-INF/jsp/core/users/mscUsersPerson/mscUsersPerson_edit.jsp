<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md" ng-controller="mscUsersPersonEditCtrl">
    <div>
        <form name="mscUsersPersonForm" class="form-validation" >
            <div class="panel panel-default">
                <div class="panel-heading">
                    <span class="h4"><fmt:message key="mscUsersPerson.module"/></span>
                    <input ng-model="formData.pkId" type="hidden" ng-init="formData.pkId='${mscUsersPerson.pkId}'" />
                </div>
                <div class="panel-body">

                            <div class="form-group pull-in clearfix">
                                <div class="col-sm-6">
                                    <label><fmt:message key="mscUsersInfo.lbName"/></label>
                                    <input ng-model="formData['lbUsersInfo.pkId']" type="hidden" ng-init="formData['lbUsersInfo.pkId']='${mscUsersPerson.lbUsersInfo.pkId}'" />
                                    <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbName']" ng-init="formData['lbUsersInfo.lbName']='${mscUsersPerson.lbUsersInfo.lbName}'">
                                </div>

                                <div class="col-sm-6">
                                    <label><fmt:message key="mscUsersInfo.lbNo"/></label>
                                    <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbNo']" ng-init="formData['lbUsersInfo.lbNo']='${mscUsersPerson.lbUsersInfo.lbNo}'">
                                    <input type="hidden" class="form-control"  ng-model="formData['lbUsersInfo.lbType']" ng-init="formData['lbUsersInfo.lbType']='${mscUsersPerson.lbUsersInfo.lbType}'">
                                </div>
                            </div>

                            <div class="form-group pull-in clearfix">
                                <div class="col-sm-6">
                                    <label><fmt:message key="mscUsersPerson.lbSex"/></label>
                                    <div>
                                        <bs:enums name="formData.lbSex" value="${mscUsersPerson.lbSex}" key="enumsSexType" type="radio" />
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <label><fmt:message key="mscUsersPerson.lbBirthday"/></label>
                                    <input type="text" class="form-control"  ng-model="formData.lbBirthday" ng-init="formData.lbPwd='${mscUsersPerson.lbBirthday}'">
                                </div>
                            </div>
                            <div class="form-group pull-in clearfix">
                                <div class="col-sm-6">
                                    <label><fmt:message key="mscUsersInfo.lbKey"/></label>
                                    <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbKey']" ng-init="formData['lbUsersInfo.lbKey']='${mscUsersPerson.lbUsersInfo.lbKey}'">
                                </div>

                                <div class="col-sm-6">
                                    <label><fmt:message key="mscUsersInfo.lbOrder"/></label>
                                    <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbOrder']" ng-init="formData['lbUsersInfo.lbOrder']='${mscUsersPerson.lbUsersInfo.lbOrder}'">
                                    <input type="hidden" class="form-control"  ng-model="formData['lbUsersInfo.lbLevelsId']" ng-init="formData['lbUsersInfo.lbLevelsId']='${mscUsersPerson.lbUsersInfo.lbLevelsId}'">
                                </div>
                            </div>
                            <div class="form-group pull-in clearfix">
                                <div class="col-sm-6">
                                    <label><fmt:message key="mscUsersInfo.lbIsVirtual"/></label>
                                    <div>
                                   <bs:enums name="formData['lbUsersInfo.lbIsVirtual']" value="${mscUsersPerson.lbUsersInfo.lbIsVirtual}" key="enumsCommonOrNot" type="radio" />
                                    </div>
                                </div>
                                <div class="col-sm-6">
                                    <label><fmt:message key="mscUsersInfo.lbIsValid"/></label>
                                    <div>
                                        <bs:enums name="formData['lbUsersInfo.lbIsValid']" value="${mscUsersPerson.lbUsersInfo.lbIsValid}" key="enumsCommonOrNot" type="radio" />
                                    </div>

                                </div>
                            </div>
                            <div class="form-group pull-in clearfix">
                                <div class="col-sm-6">
                                    <label><fmt:message key="mscUsersInfo.lbInfo"/></label>
                                    <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbInfo']" ng-init="formData['lbUsersInfo.lbInfo']='${mscUsersPerson.lbUsersInfo.lbInfo}'">
                                    <input type="hidden" class="form-control" ng-model="formData.lbCreateTime" ng-init="formData.lbCreateTime='${mscUsersPerson.lbUsersInfo.lbCreateTime}'">
                                </div>

                                <div class="col-sm-6">
                                    <label><fmt:message key="mscUsersInfo.lbParent"/></label>
                                    <input type="hidden" class="form-control" ng-model="formData['lbUsersInfo.lbParent.pkId']"  ng-init="formData['lbUsersInfo.lbParent.pkId']='${mscUsersPerson.lbUsersInfo.lbParent.pkId}'">
                                    <div>
                                        <input type="text" class="form-control" ng-model="formData['lbUsersInfo.lbParent.lbName']"  ng-init="formData['lbUsersInfo.lbParent.lbName']='${mscUsersPerson.lbUsersInfo.lbParent.lbName}'" open-dialog-list ng-dialog-type="org"  ng-bean="baseDomainService&domain=com.os.osframe.core.users.domain.MscUsersInfo&name=lbName" ng-value-id="formData['lbUsersInfo.lbParent.pkId']">
                                    </div>
                                </div>
                            </div>

            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersPerson.lbLoginName"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbLoginName" ng-init="formData.lbLoginName='${mscUsersPerson.lbLoginName}'">
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersPerson.lbPwd"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbPwd" ng-init="formData.lbPwd='${mscUsersPerson.lbPwd}'">
                </div>
            </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersPerson.lbMail"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbMail" ng-init="formData.lbMail='${mscUsersPerson.lbMail}'">
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersPerson.lbPhone"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbPhone" ng-init="formData.lbPhone='${mscUsersPerson.lbPhone}'">
                </div>
            </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersPerson.lbMobilePhone"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbMobilePhone" ng-init="formData.lbMobilePhone='${mscUsersPerson.lbMobilePhone}'">
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersPerson.lbOthers"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbOthers" ng-init="formData.lbOthers='${mscUsersPerson.lbOthers}'">
                </div>
            </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersPerson.lbLanguage"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbLanguage" ng-init="formData.lbLanguage='${mscUsersPerson.lbLanguage}'">
                </div>
                <div class="col-sm-6">

                </div>
            </div>

                    <div class="form-group align-center">
                        <button type="button" class="btn btn-default" ng-edit-submit="mscUsersPerson(formData)"><fmt:message key="button.save"/></button>
                        <button type="reset" class="btn btn-default "><fmt:message key="button.reset"/></button>
                        <button type="button" class="btn btn-default" ng-return><fmt:message key="button.return"/></button>
                    </div>

                </div>
            </div>
        </form>
    </div>
</div>
