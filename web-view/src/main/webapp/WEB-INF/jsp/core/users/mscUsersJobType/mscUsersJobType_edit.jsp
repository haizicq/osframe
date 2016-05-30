<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md" ng-controller="mscUsersJobTypeEditCtrl">
    <div>
        <form name="mscUsersJobTypeForm" class="form-validation" >
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-edit"></i>
                    <span class="h4"><fmt:message key="mscUsersJobType.module"/></span>
                    <input ng-model="formData.pkId" type="hidden" ng-init="formData.pkId='${mscUsersJobType.pkId}'" />
                </div>
                <div class="panel-body">
                    
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersJobType.lbName"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbName" ng-init="formData.lbName='${mscUsersJobType.lbName}'">
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersJobType.lbNo"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbNo" ng-init="formData.lbNo='${mscUsersJobType.lbNo}'">
                </div>
            </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersJobType.lbLevel"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbLevel" ng-init="formData.lbLevel='${mscUsersJobType.lbLevel}'">
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersJobType.lbKey"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbKey" ng-init="formData.lbKey='${mscUsersJobType.lbKey}'">
                </div>
            </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersJobType.lbInfo"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbInfo" ng-init="formData.lbInfo='${mscUsersJobType.lbInfo}'">
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersJobType.lbStatus"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbStatus" ng-init="formData.lbStatus='${mscUsersJobType.lbStatus}'">
                </div>
            </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersJobType.lbUpdateTime"/></label>
                <input type="text" class="form-control" ng-model="formData.lbUpdateTime" date-time-select date-type="dateTime" ng-init="formData.lbUpdateTime='${mscUsersJobType.lbUpdateTime}'">
                </div>
                <div class="col-sm-6"></div>
            </div>

                    <div class="form-group align-center">
                        <button type="button" class="btn btn-default" ng-edit-submit="mscUsersJobType(formData)"><fmt:message key="button.save"/></button>
                        <button type="reset" class="btn btn-default "><fmt:message key="button.reset"/></button>
                        <button type="button" class="btn btn-default" ng-return><fmt:message key="button.return"/></button>
                    </div>


                </div>
            </div>
        </form>
    </div>
</div>
