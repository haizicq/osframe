<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md" ng-controller="mscUsersGroupTypeEditCtrl">
    <div>
        <form name="mscUsersGroupTypeForm" class="form-validation" >
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-edit"></i>
                    <span class="h4"><fmt:message key="mscUsersGroupType.module"/></span>
                    <input ng-model="formData.pkId" type="hidden" ng-init="formData.pkId='${mscUsersGroupType.pkId}'" />
                </div>
                <div class="panel-body">
                    
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersGroupType.lbName"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbName" ng-init="formData.lbName='${mscUsersGroupType.lbName}'">
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersGroupType.lbNo"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbNo" ng-init="formData.lbNo='${mscUsersGroupType.lbNo}'">
                </div>
            </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersGroupType.lbKey"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbKey" ng-init="formData.lbKey='${mscUsersGroupType.lbKey}'">
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersGroupType.lbInfo"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbInfo" ng-init="formData.lbInfo='${mscUsersGroupType.lbInfo}'">
                </div>
            </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersGroupType.lbStatus"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbStatus" ng-init="formData.lbStatus='${mscUsersGroupType.lbStatus}'">
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersGroupType.lbUpdateTime"/></label>
                <input type="text" class="form-control" ng-model="formData.lbUpdateTime" date-time-select date-type="dateTime" ng-init="formData.lbUpdateTime='${mscUsersGroupType.lbUpdateTime}'">
                </div>
            </div>


                    <div class="form-group align-center">
                        <button type="button" class="btn btn-default" ng-edit-submit="mscUsersGroupType(formData)"><fmt:message key="button.save"/></button>
                        <button type="reset" class="btn btn-default "><fmt:message key="button.reset"/></button>
                        <button type="button" class="btn btn-default" ng-return><fmt:message key="button.return"/></button>
                    </div>


                </div>
            </div>
        </form>
    </div>
</div>
