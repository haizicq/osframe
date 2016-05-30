<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md" ng-controller="mscUsersGroupEditCtrl">
    <div>
        <form name="mscUsersGroupForm" class="form-validation">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-edit"></i>
                    <span class="h4"><fmt:message key="mscUsersGroup.module"/></span>
                    <input ng-model="formData.pkId" type="hidden" ng-init="formData.pkId='${mscUsersGroup.pkId}'" />
                </div>
                <div class="panel-body">
                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbName"/></label>
                            <input ng-model="formData['lbUsersInfo.pkId']" type="hidden" ng-init="formData['lbUsersInfo.pkId']='${mscUsersGroup.lbUsersInfo.pkId}'" />
                            <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbName']" ng-init="formData['lbUsersInfo.lbName']='${mscUsersGroup.lbUsersInfo.lbName}'">
                        </div>

                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbNo"/></label>
                            <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbNo']" ng-init="formData['lbUsersInfo.lbNo']='${mscUsersGroup.lbUsersInfo.lbNo}'">
                            <input type="hidden" class="form-control"  ng-model="formData['lbUsersInfo.lbType']" ng-init="formData['lbUsersInfo.lbType']='${mscUsersGroup.lbUsersInfo.lbType}'">
                        </div>
                    </div>
                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbKey"/></label>
                            <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbKey']" ng-init="formData['lbUsersInfo.lbKey']='${mscUsersGroup.lbUsersInfo.lbKey}'">
                        </div>

                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbOrder"/></label>
                            <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbOrder']" ng-init="formData['lbUsersInfo.lbOrder']='${mscUsersGroup.lbUsersInfo.lbOrder}'">
                            <input type="hidden" class="form-control"  ng-model="formData['lbUsersInfo.lbLevelsId']" ng-init="formData['lbUsersInfo.lbLevelsId']='${mscUsersGroup.lbUsersInfo.lbLevelsId}'">
                        </div>
                    </div>
                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbIsVirtual"/></label>
                            <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbIsVirtual']" ng-init="formData['lbUsersInfo.lbIsVirtual']='${mscUsersGroup.lbUsersInfo.lbIsVirtual}'">
                        </div>
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbIsValid"/></label>
                            <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbIsValid']" ng-init="formData['lbUsersInfo.lbIsValid']='${mscUsersGroup.lbUsersInfo.lbIsValid}'">
                        </div>
                    </div>
                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbInfo"/></label>
                            <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbInfo']" ng-init="formData['lbUsersInfo.lbInfo']='${mscUsersGroup.lbUsersInfo.lbInfo}'">
                            <input type="hidden" class="form-control" ng-model="formData.lbCreateTime" ng-init="formData.lbCreateTime='${mscUsersGroup.lbUsersInfo.lbCreateTime}'">
                        </div>

                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbParent"/></label>
                            <input type="hidden" class="form-control" ng-model="formData['lbUsersInfo.lbParent.pkId']"  ng-init="formData['lbUsersInfo.lbParent.pkId']='${mscUsersGroup.lbUsersInfo.lbParent.pkId}'">
                            <div>
                                <input type="text" class="form-control" ng-model="formData['lbUsersInfo.lbParent.lbName']"  ng-init="formData['lbUsersInfo.lbParent.lbName']='${mscUsersGroup.lbUsersInfo.lbParent.lbName}'" open-dialog-list ng-bean="baseDomainService&domain=com.os.osframe.core.users.domain.MscUsersInfo&name=lbName" ng-value-id="formData['lbUsersInfo.lbParent.pkId']">
                            </div>
                        </div>
                    </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersGroup.lbLeader"/></label>
                <input type="hidden" class="form-control" ng-model="formData['lbLeader.pkId']"  ng-init="formData['lbLeader.pkId']='${mscUsersGroup.lbLeader.pkId}'">
                <div>
                    <input type="text" class="form-control" ng-model="formData['lbLeader.lbName']"  ng-init="formData['lbLeader.lbName']='${mscUsersGroup.lbLeader.lbName}'" open-dialog-list ng-bean="baseDomainService&domain=com.os.osframe.core.users.domain.MscUsersInfo&name=lbName" ng-value-id="formData['lbLeader.pkId']">
                </div>
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersGroup.lbGroupType"/></label>
                <input type="hidden" class="form-control" ng-model="formData['lbGroupType.pkId']"  ng-init="formData['lbGroupType.pkId']='${mscUsersGroup.lbGroupType.pkId}'">
                <div>
                    <input type="text" class="form-control" ng-model="formData['lbGroupType.lbName']"  ng-init="formData['lbGroupType.lbName']='${mscUsersGroup.lbGroupType.lbName}'" open-dialog-list ng-bean="baseDomainService&domain=com.os.osframe.core.users.domain.MscUsersGroupType&name=lbName" ng-value-id="formData['lbGroupType.pkId']">
                </div>
                </div>
            </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersGroup.lbUsersInfo"/></label>
                <input type="hidden" class="form-control" ng-model="formData['lbUsersInfo.pkId']"  ng-init="formData['lbUsersInfo.pkId']='${mscUsersGroup.lbUsersInfo.pkId}'">
                <div>
                    <input type="text" class="form-control" ng-model="formData['lbUsersInfo.lbName']"  ng-init="formData['lbUsersInfo.lbName']='${mscUsersGroup.lbUsersInfo.lbName}'" open-dialog-list ng-bean="baseDomainService&domain=com.os.osframe.core.users.domain.MscUsersInfo&name=lbName" ng-value-id="formData['lbUsersInfo.pkId']">
                </div>
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersGroup.lbPersonList"/></label>
                <input type="hidden" class="form-control" ng-model="formData['lbPersonList.pkId']"  ng-init="formData['lbPersonList.pkId']='${mscUsersGroup.lbPersonList.pkId}'">
                <div>
                    <input type="text" class="form-control" ng-model="formData['lbPersonList.lbName']"  ng-init="formData['lbPersonList.lbName']='${mscUsersGroup.lbPersonList.lbName}'" open-dialog-list ng-bean="baseDomainService&domain=com.os.osframe.core.users.domain.MscUsersInfo&name=lbName" ng-value-id="formData['lbPersonList.pkId']">
                </div>
                </div>
            </div>

                    <div class="form-group align-center">
                        <button type="button" class="btn btn-default" ng-edit-submit="mscUsersGroup(formData)"><fmt:message key="button.save"/></button>
                        <button type="reset" class="btn btn-default "><fmt:message key="button.reset"/></button>
                        <button type="button" class="btn btn-default" ng-return><fmt:message key="button.return"/></button>
                    </div>


                </div>
            </div>
        </form>
    </div>
</div>
