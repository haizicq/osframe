<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md" ng-controller="mscUsersJobEditCtrl">
    <div>
        <form name="mscUsersJobForm" class="form-validation" >
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-edit"></i>
                    <span class="h4"><fmt:message key="mscUsersJob.module"/></span>
                    <input ng-model="formData.pkId" type="hidden" ng-init="formData.pkId='${mscUsersJob.pkId}'" />
                </div>
                <div class="panel-body">

                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbName"/></label>
                            <input ng-model="formData['lbUsersInfo.pkId']" type="hidden" ng-init="formData['lbUsersInfo.pkId']='${mscUsersJob.lbUsersInfo.pkId}'" />
                            <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbName']" ng-init="formData['lbUsersInfo.lbName']='${mscUsersJob.lbUsersInfo.lbName}'">
                        </div>

                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbNo"/></label>
                            <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbNo']" ng-init="formData['lbUsersInfo.lbNo']='${mscUsersJob.lbUsersInfo.lbNo}'">
                            <input type="hidden" class="form-control"  ng-model="formData['lbUsersInfo.lbType']" ng-init="formData['lbUsersInfo.lbType']='${mscUsersJob.lbUsersInfo.lbType}'">
                        </div>
                    </div>
                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbKey"/></label>
                            <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbKey']" ng-init="formData['lbUsersInfo.lbKey']='${mscUsersJob.lbUsersInfo.lbKey}'">
                        </div>

                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbOrder"/></label>
                            <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbOrder']" ng-init="formData['lbUsersInfo.lbOrder']='${mscUsersJob.lbUsersInfo.lbOrder}'">
                            <input type="hidden" class="form-control"  ng-model="formData['lbUsersInfo.lbLevelsId']" ng-init="formData['lbUsersInfo.lbLevelsId']='${mscUsersJob.lbUsersInfo.lbLevelsId}'">
                        </div>
                    </div>
                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbIsVirtual"/></label>
                            <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbIsVirtual']" ng-init="formData['lbUsersInfo.lbIsVirtual']='${mscUsersJob.lbUsersInfo.lbIsVirtual}'">
                        </div>
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbIsValid"/></label>
                            <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbIsValid']" ng-init="formData['lbUsersInfo.lbIsValid']='${mscUsersJob.lbUsersInfo.lbIsValid}'">
                        </div>
                    </div>
                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbInfo"/></label>
                            <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbInfo']" ng-init="formData['lbUsersInfo.lbInfo']='${mscUsersJob.lbUsersInfo.lbInfo}'">
                            <input type="hidden" class="form-control" ng-model="formData.lbCreateTime" ng-init="formData.lbCreateTime='${mscUsersJob.lbUsersInfo.lbCreateTime}'">
                        </div>

                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbParent"/></label>
                            <input type="hidden" class="form-control" ng-model="formData['lbUsersInfo.lbParent.pkId']"  ng-init="formData['lbUsersInfo.lbParent.pkId']='${mscUsersJob.lbUsersInfo.lbParent.pkId}'">
                            <div>
                                <input type="text" class="form-control" ng-model="formData['lbUsersInfo.lbParent.lbName']"  ng-init="formData['lbUsersInfo.lbParent.lbName']='${mscUsersJob.lbUsersInfo.lbParent.lbName}'" open-dialog-list ng-bean="baseDomainService&domain=com.os.osframe.core.users.domain.MscUsersInfo&name=lbName" ng-value-id="formData['lbUsersInfo.lbParent.pkId']">
                            </div>
                        </div>
                    </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersJob.lbLevel"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbLevel" ng-init="formData.lbLevel='${mscUsersJob.lbLevel}'">
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersJob.lbJobType"/></label>
                <input type="hidden" class="form-control" ng-model="formData['lbJobType.pkId']"  ng-init="formData['lbJobType.pkId']='${mscUsersJob.lbJobType.pkId}'">
                <div>
                    <input type="text" class="form-control" ng-model="formData['lbJobType.lbName']"  ng-init="formData['lbJobType.lbName']='${mscUsersJob.lbJobType.lbName}'" open-dialog-list ng-bean="baseDomainService&domain=com.os.osframe.core.users.domain.MscUsersJobType&name=lbName" ng-value-id="formData['lbJobType.pkId']">
                </div>
                </div>
            </div>
            <div class="form-group pull-in clearfix">

                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersJob.lbPersonList"/></label>
                <input type="hidden" class="form-control" ng-model="formData.lbPersonList.pkId"  ng-init="formData.lbPersonList.pkId='${mscUsersJob.lbPersonListIds}'">
                <div>
                    <input type="text" class="form-control" ng-model="formData.lbPersonList.lbName"  ng-init="formData.lbPersonList.lbName='${mscUsersJob.lbPersonListNames}'" open-dialog-list ng-bean="baseDomainService&domain=com.os.osframe.core.users.domain.MscUsersInfo&name=lbName" ng-value-id="formData.lbPersonList.pkId">
                </div>
                </div>
            </div>

                    <div class="form-group align-center">
                        <button type="button" class="btn btn-default" ng-edit-submit="mscUsersJob(formData)"><fmt:message key="button.save"/></button>
                        <button type="reset" class="btn btn-default "><fmt:message key="button.reset"/></button>
                        <button type="button" class="btn btn-default" ng-return><fmt:message key="button.return"/></button>
                    </div>


                </div>
            </div>
        </form>
    </div>
</div>
