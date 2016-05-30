<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md" ng-controller="mscUsersInfoEditCtrl">
    <div>
        <form name="mscUsersInfoForm" class="form-validation" >
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-edit"></i>
                    <span class="h4"><fmt:message key="mscUsersInfo.module"/></span>
                    <input ng-model="formData.pkId" type="hidden" ng-init="formData.pkId='${mscUsersInfo.pkId}'" />
                </div>
                <div class="panel-body">
                    
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersInfo.lbName"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbName" ng-init="formData.lbName='${mscUsersInfo.lbName}'">
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersInfo.lbKey"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbKey" ng-init="formData.lbKey='${mscUsersInfo.lbKey}'">
                </div>
            </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersInfo.lbType"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbType" ng-init="formData.lbType='${mscUsersInfo.lbType}'">
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersInfo.lbNo"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbNo" ng-init="formData.lbNo='${mscUsersInfo.lbNo}'">
                </div>
            </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersInfo.lbOrder"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbOrder" ng-init="formData.lbOrder='${mscUsersInfo.lbOrder}'">
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersInfo.lbLevelsId"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbLevelsId" ng-init="formData.lbLevelsId='${mscUsersInfo.lbLevelsId}'">
                </div>
            </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersInfo.lbIsVirtual"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbIsVirtual" ng-init="formData.lbIsVirtual='${mscUsersInfo.lbIsVirtual}'">
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersInfo.lbIsValid"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbIsValid" ng-init="formData.lbIsValid='${mscUsersInfo.lbIsValid}'">
                </div>
            </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersInfo.lbInfo"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbInfo" ng-init="formData.lbInfo='${mscUsersInfo.lbInfo}'">
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersInfo.lbCreateTime"/></label>
                    <div>
                        <input type="text" class="form-control" ng-model="formData.lbCreateTime" date-time-select date-type="dateTime" ng-init="formData.lbCreateTime='${mscUsersInfo.lbCreateTime}'">
                    </div>
                </div>
            </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersInfo.lbUpdateTime"/></label>
                    <div>
                        <input type="text" class="form-control" ng-model="formData.lbUpdateTime" date-time-select date-type="dateTime" ng-init="formData.lbUpdateTime='${mscUsersInfo.lbUpdateTime}'">
                    </div>
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersInfo.lbSourceKey"/></label>
                    <input type="text" class="form-control"  ng-model="formData.lbSourceKey" ng-init="formData.lbSourceKey='${mscUsersInfo.lbSourceKey}'">
                </div>
            </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersInfo.lbSourcePk"/></label>
                    <input type="text" class="form-control"  ng-model="formData.lbSourcePk" ng-init="formData.lbSourcePk='${mscUsersInfo.lbSourcePk}'">
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersInfo.lbParent"/></label>
                <input type="hidden" class="form-control" ng-model="formData['lbParent.pkId']"  ng-init="formData['lbParent.pkId']='${mscUsersInfo.lbParent.pkId}'">
                <div>
                    <input type="text" class="form-control" ng-model="formData['lbParent.lbName']"  ng-init="formData['lbParent.lbName']='${mscUsersInfo.lbParent.lbName}'" open-dialog-list ng-bean="baseDomainService&domain=com.os.osframe.core.users.domain.MscUsersInfo&name=lbName" ng-value-id="formData['lbParent.pkId']">
                </div>
                </div>
            </div>

                    <div class="form-group align-center">
                        <button type="button" class="btn btn-default" ng-edit-submit="mscUsersInfo(formData)"><fmt:message key="button.save"/></button>
                        <button type="reset" class="btn btn-default "><fmt:message key="button.reset"/></button>
                        <button type="button" class="btn btn-default" ng-return><fmt:message key="button.return"/></button>
                    </div>


                </div>
            </div>
        </form>
    </div>
</div>
