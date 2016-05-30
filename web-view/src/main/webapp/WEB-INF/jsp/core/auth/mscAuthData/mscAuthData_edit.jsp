<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md" ng-controller="mscAuthDataEditCtrl">
    <div>
        <form name="mscAuthDataForm" class="form-validation" >
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-edit"></i>
                    <span class="h4"><fmt:message key="mscAuthData.module"/></span>
                    <input ng-model="formData.pkId" type="hidden" ng-init="formData.pkId='${mscAuthData.pkId}'" />
                </div>
                <div class="panel-body">
                    
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscAuthData.lbModule"/></label>
                    <input type="text" class="form-control"  ng-model="formData.lbModule" ng-init="formData.lbModule='${mscAuthData.lbModule}'">
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscAuthData.lbSystem"/></label>
                    <input type="text" class="form-control"  ng-model="formData.lbSystem" ng-init="formData.lbSystem='${mscAuthData.lbSystem}'">
                </div>
            </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscAuthData.lbModelId"/></label>
                    <input type="text" class="form-control"  ng-model="formData.lbModelId" ng-init="formData.lbModelId='${mscAuthData.lbModelId}'">
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscAuthData.lbType"/></label>
                    <input type="text" class="form-control"  ng-model="formData.lbType" ng-init="formData.lbType='${mscAuthData.lbType}'">
                </div>
            </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscAuthData.lbNullType"/></label>
                    <input type="text" class="form-control"  ng-model="formData.lbNullType" ng-init="formData.lbNullType='${mscAuthData.lbNullType}'">
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscAuthData.lbUpdateTime"/></label>
                    <div>
                        <input type="text" class="form-control" ng-model="formData.lbUpdateTime" date-time-select date-type="dateTime" ng-init="formData.lbUpdateTime='${mscAuthData.lbUpdateTime}'">
                    </div>
                </div>
            </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscAuthData.lbPersonList"/></label>
                    <input type="hidden" class="form-control" ng-model="formData.lbPersonList.pkId"  ng-init="formData.lbPersonList.pkId='${mscAuthData.lbPersonListIds}'">
                    <div>
                        <input type="text" class="form-control" ng-model="formData.lbPersonList.lbName"  ng-init="formData.lbPersonList.lbName='${mscAuthData.lbPersonListNames}'" open-dialog-list ng-bean="baseDomainService&domain=com.os.osframe.core.users.domain.MscUsersInfo&name=lbName" ng-value-id="formData.lbPersonList.pkId">
                    </div>
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscAuthData.lbAllPersonList"/></label>
                    <input type="hidden" class="form-control" ng-model="formData.lbAllPersonList.pkId"  ng-init="formData.lbAllPersonList.pkId='${mscAuthData.lbAllPersonListIds}'">
                    <div>
                        <input type="text" class="form-control" ng-model="formData.lbAllPersonList.lbName"  ng-init="formData.lbAllPersonList.lbName='${mscAuthData.lbAllPersonListNames}'" open-dialog-list ng-bean="baseDomainService&domain=com.os.osframe.core.users.domain.MscUsersInfo&name=lbName" ng-value-id="formData.lbAllPersonList.pkId">
                    </div>
                </div>
            </div>

                    <div class="form-group align-center">
                        <button type="button" class="btn btn-default" ng-edit-submit="mscAuthData(formData)"><fmt:message key="button.save"/></button>
                        <button type="reset" class="btn btn-default "><fmt:message key="button.reset"/></button>
                        <button type="button" class="btn btn-default" ng-return><fmt:message key="button.return"/></button>

                    </div>

                </div>
            </div>
        </form>
    </div>
</div>
