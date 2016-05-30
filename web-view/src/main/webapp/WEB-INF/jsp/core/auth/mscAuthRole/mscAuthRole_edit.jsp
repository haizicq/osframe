<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md" ng-controller="mscAuthRoleEditCtrl">
    <div>
        <form name="mscAuthRoleForm" class="form-validation" >
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-edit"></i>
                    <span class="h4"><fmt:message key="mscAuthRole.module"/></span>
                    <input ng-model="formData.pkId" type="hidden" ng-init="formData.pkId='${mscAuthRole.pkId}'" />
                </div>
                <div class="panel-body">
                    
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscAuthRole.lbName"/></label>
                    <input type="text" class="form-control"  ng-model="formData.lbName" ng-init="formData.lbName='${mscAuthRole.lbName}'">
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscAuthRole.lbKey"/></label>
                    <input type="text" class="form-control"  ng-model="formData.lbKey" ng-init="formData.lbKey='${mscAuthRole.lbKey}'">
                </div>
            </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscAuthRole.lbType"/></label>
                    <div class="form-control">
                        <bs:enums name="formData.lbType" value="${mscAuthRole.lbType}" key="enumsAuthType" type="select" />
                    </div>
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscAuthRole.lbIsValid"/></label>
                    <%--<input type="text" class="form-control"  ng-model="formData.lbIsValid" ng-init="formData.lbIsValid='${mscAuthRole.lbIsValid}'">--%>
                    <div>
                        <bs:enums name="formData.lbIsValid" value="${mscAuthRole.lbIsValid}" key="enumsCommonOrNot" type="radio" />
                    </div>
                </div>
            </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscAuthRole.lbParent"/></label>
                    <input type="hidden" class="form-control" ng-model="formData['lbParent.pkId']"  ng-init="formData['lbParent.pkId']='${mscAuthRole.lbParent.pkId}'">
                    <div>
                        <input type="text" class="form-control" ng-model="formData['lbParent.lbName']"  ng-init="formData['lbParent.lbName']='${mscAuthRole.lbParent.lbName}'" open-dialog-list ng-bean="baseDomainService&domain=com.os.osframe.core.auth.domain.MscAuthRole&name=lbName" ng-value-id="formData['lbParent.pkId']" />
                    </div>
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscAuthRole.lbRoleCategory"/></label>
                    <input type="hidden" class="form-control" ng-model="formData['lbRoleCategory.pkId']"  ng-init="formData['lbRoleCategory.pkId']='${mscAuthRole.lbRoleCategory.pkId}'">
                    <div>
                        <input type="text" class="form-control" ng-model="formData['lbRoleCategory.lbName']"  ng-init="formData['lbRoleCategory.lbName']='${mscAuthRole.lbRoleCategory.lbName}'" open-dialog-list ng-bean="baseDomainService&domain=com.os.osframe.core.auth.domain.MscAuthCategory&name=lbName" ng-value-id="formData['lbRoleCategory.pkId']"/>
                    </div>
                </div>
            </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscAuthRole.lbAuthList"/></label>
                    <input type="hidden" class="form-control" ng-model="formData.lbAuthList.pkId"  ng-init="formData.lbAuthList.pkId='${mscAuthRole.lbAuthListIds}'">
                    <div>
                        <input type="text" class="form-control" ng-model="formData.lbAuthList.lbName"  ng-init="formData.lbAuthList.lbName='${mscAuthRole.lbAuthListNames}'" open-dialog-list ng-bean="baseDomainService&domain=com.os.osframe.core.auth.domain.MscAuthInfo&name=lbName" ng-value-id="formData.lbAuthList.pkId"  ng-multiple="true"/>
                    </div>
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscAuthRole.lbPersonList"/></label>
                    <input type="hidden" class="form-control" ng-model="formData.lbPersonList.pkId"  ng-init="formData.lbPersonList.pkId='${mscAuthRole.lbPersonListIds}'">
                    <div>
                        <input type="text" class="form-control" ng-model="formData.lbPersonList.lbName"  ng-init="formData.lbPersonList.lbName='${mscAuthRole.lbPersonListNames}'" open-dialog-list ng-bean="baseDomainService&domain=com.os.osframe.core.users.domain.MscUsersInfo&name=lbName" ng-value-id="formData.lbPersonList.pkId"  ng-multiple="true"/>
                    </div>
                </div>
            </div>
                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-12">
                            <label><fmt:message key="mscAuthRole.lbInfo"/></label>
                            <input type="text" class="form-control"  ng-model="formData.lbInfo" ng-init="formData.lbInfo='${mscAuthRole.lbInfo}'">
                            <input type="hidden" ng-model="formData.lbUpdateTime"  ng-init="formData.lbUpdateTime='${mscAuthRole.lbUpdateTime}'"/>
                        </div>
                    </div>

                    <div class="form-group align-center">
                        <button type="button" class="btn btn-default" ng-edit-submit="mscAuthRole(formData)"><fmt:message key="button.save"/></button>
                        <button type="reset" class="btn btn-default "><fmt:message key="button.reset"/></button>
                        <button type="button" class="btn btn-default" ng-return><fmt:message key="button.return"/></button>
                    </div>

                </div>
            </div>
        </form>
    </div>
</div>
