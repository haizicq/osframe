<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md" ng-controller="mscAuthInfoEditCtrl">
    <div>
        <form name="mscAuthInfoForm" class="form-validation" ng-submit="submitForm()">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-edit"></i>
                    <span class="h4"><fmt:message key="mscAuthInfo.module"/></span>
                    <input ng-model="formData.pkId" type="hidden" ng-init="formData.pkId='${mscAuthInfo.pkId}'" />
                </div>
                <div class="panel-body">
                    
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscAuthInfo.lbName"/></label>
                    <input type="text" class="form-control"  ng-model="formData.lbName" ng-init="formData.lbName='${mscAuthInfo.lbName}'">
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscAuthInfo.lbInfo"/></label>
                    <input type="text" class="form-control"  ng-model="formData.lbInfo" ng-init="formData.lbInfo='${mscAuthInfo.lbInfo}'">
                </div>
            </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscAuthInfo.lbAuthKey"/></label>
                    <input type="text" class="form-control"  ng-model="formData.lbAuthKey" ng-init="formData.lbAuthKey='${mscAuthInfo.lbAuthKey}'">
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscAuthInfo.lbUrlPrefix"/></label>
                    <input type="text" class="form-control"  ng-model="formData.lbUrlPrefix" ng-init="formData.lbUrlPrefix='${mscAuthInfo.lbUrlPrefix}'">
                </div>
            </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscAuthInfo.lbModule"/></label>
                    <input type="text" class="form-control"  ng-model="formData.lbModule" ng-init="formData.lbModule='${mscAuthInfo.lbModule}'">
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscAuthInfo.lbSystem"/></label>
                    <input type="text" class="form-control"  ng-model="formData.lbSystem" ng-init="formData.lbSystem='${mscAuthInfo.lbSystem}'">
                </div>
            </div>
                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscAuthInfo.lbModuleKey"/></label>
                            <input type="text" class="form-control"  ng-model="formData.lbModuleKey" ng-init="formData.lbModuleKey='${mscAuthInfo.lbModuleKey}'">
                        </div>
                        <div class="col-sm-6">
                            <label><fmt:message key="mscAuthInfo.lbSystemKey"/></label>
                            <input type="text" class="form-control"  ng-model="formData.lbSystemKey" ng-init="formData.lbSystemKey='${mscAuthInfo.lbSystemKey}'">
                        </div>
                    </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscAuthInfo.lbUpdateTime"/></label>
                    <div>
                        <input type="text" class="form-control" ng-model="formData.lbUpdateTime" date-time-select date-type="dateTime" ng-init="formData.lbUpdateTime='${mscAuthInfo.lbUpdateTime}'">
                    </div>
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscAuthInfo.lbOrder"/></label>
                    <input type="text" class="form-control"  ng-model="formData.lbOrder" ng-init="formData.lbOrder='${mscAuthInfo.lbOrder}'">
                </div>
            </div>

                    <div class="form-group align-center">
                        <button type="button" class="btn btn-default"><fmt:message key="button.return"/></button>
                    </div>

                </div>
            </div>
        </form>
    </div>
</div>
