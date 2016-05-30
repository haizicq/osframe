<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md" ng-controller="osDemoInfoEditCtrl">
    <div>
        <form name="osDemoInfoForm" class="form-validation" >
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-edit"></i>
                    <span class="h4"><fmt:message key="osDemoInfo.module"/></span>
                    <input ng-model="formData.pkId" type="hidden" ng-init="formData.pkId='${osDemoInfo.pkId}'" />
                </div>
                <div class="panel-body">
                    
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="osDemoInfo.lbName"/></label>
                    <input type="text" class="form-control"  ng-model="formData.lbName" ng-init="formData.lbName='${osDemoInfo.lbName}'">
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="osDemoInfo.lbType"/></label>
                    <div>
                        <bs:enums name="formData.lbType" key="enumsDemo" value="${osDemoInfo.lbType}" type="radio"/>
                    </div>
                </div>
            </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="osDemoInfo.lbTime"/></label>
                    <div>
                        <input type="text" class="form-control" ng-model="formData.lbTime" date-time-select date-type="dateTime" ng-init="formData.lbTime='${osDemoInfo.lbTime}'">
                    </div>
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="osDemoInfo.lbParent"/></label>
                    <input type="hidden" class="form-control" ng-model="formData['lbParent.pkId']"  ng-init="formData['lbParent.pkId']='${osDemoInfo.lbParent.pkId}'">
                    <div>
                        <input type="text" class="form-control" ng-model="formData['lbParent.lbName']"  ng-init="formData['lbParent.lbName']='${osDemoInfo.lbParent.lbName}'" open-dialog-list ng-bean="baseDomainService&domain=com.os.osframe.os.demo.domain.OsDemoInfo&name=lbName" ng-value-id="formData['lbParent.pkId']">
                    </div>
                </div>
            </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="osDemoInfo.lbDept"/></label>
                    <input type="hidden" class="form-control" ng-model="formData['lbDept.pkId']"  ng-init="formData['lbDept.pkId']='${osDemoInfo.lbDept.pkId}'">
                    <div>
                        <input type="text" class="form-control" ng-model="formData['lbDept.lbName']"  ng-init="formData['lbDept.lbName']='${osDemoInfo.lbDept.lbName}'" open-dialog-list  ng-value-id="formData['lbDept.pkId']" ng-dialog-type="org" />
                    </div>
                </div>
                <div class="col-sm-6"></div>
            </div>

                    <div class="form-group align-center">
                        <button type="button" class="btn btn-default" ng-edit-submit="osDemoInfo(formData)"><fmt:message key="button.save"/></button>
                        <button type="reset" class="btn btn-default "><fmt:message key="button.reset"/></button>
                        <button type="button" class="btn btn-default" ng-return><fmt:message key="button.return"/></button>

                    </div>

                </div>
            </div>
        </form>
    </div>
</div>
