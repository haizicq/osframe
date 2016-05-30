<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md" ng-controller="mscUsersGroupJobEditCtrl">
    <div>
        <form name="mscUsersGroupJobForm" class="form-validation" >
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-edit"></i>
                    <span class="h4"><fmt:message key="mscUsersGroupJob.module"/></span>
                    <input ng-model="formData.pkId" type="hidden" ng-init="formData.pkId='${mscUsersGroupJob.pkId}'" />
                </div>
                <div class="panel-body">
                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbName"/></label>
                            <input ng-model="formData['lbUsersInfo.pkId']" type="hidden" ng-init="formData['lbUsersInfo.pkId']='${mscUsersGroupJob.lbUsersInfo.pkId}'" />
                            <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbName']" ng-init="formData['lbUsersInfo.lbName']='${mscUsersGroupJob.lbUsersInfo.lbName}'">
                        </div>

                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbNo"/></label>
                            <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbNo']" ng-init="formData['lbUsersInfo.lbNo']='${mscUsersGroupJob.lbUsersInfo.lbNo}'">
                            <input type="hidden" class="form-control"  ng-model="formData['lbUsersInfo.lbType']" ng-init="formData['lbUsersInfo.lbType']='${mscUsersGroupJob.lbUsersInfo.lbType}'">
                        </div>
                    </div>
                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbKey"/></label>
                            <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbKey']" ng-init="formData['lbUsersInfo.lbKey']='${mscUsersGroupJob.lbUsersInfo.lbKey}'">
                        </div>

                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbOrder"/></label>
                            <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbOrder']" ng-init="formData['lbUsersInfo.lbOrder']='${mscUsersGroupJob.lbUsersInfo.lbOrder}'">
                            <input type="hidden" class="form-control"  ng-model="formData['lbUsersInfo.lbLevelsId']" ng-init="formData['lbUsersInfo.lbLevelsId']='${mscUsersGroupJob.lbUsersInfo.lbLevelsId}'">
                        </div>
                    </div>
                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbIsVirtual"/></label>
                            <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbIsVirtual']" ng-init="formData['lbUsersInfo.lbIsVirtual']='${mscUsersGroupJob.lbUsersInfo.lbIsVirtual}'">
                        </div>
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbIsValid"/></label>
                            <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbIsValid']" ng-init="formData['lbUsersInfo.lbIsValid']='${mscUsersGroupJob.lbUsersInfo.lbIsValid}'">
                        </div>
                    </div>
                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbInfo"/></label>
                            <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbInfo']" ng-init="formData['lbUsersInfo.lbInfo']='${mscUsersGroupJob.lbUsersInfo.lbInfo}'">
                            <input type="hidden" class="form-control" ng-model="formData.lbCreateTime" ng-init="formData.lbCreateTime='${mscUsersGroupJob.lbUsersInfo.lbCreateTime}'">
                        </div>

                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbParent"/></label>
                            <input type="hidden" class="form-control" ng-model="formData['lbUsersInfo.lbParent.pkId']"  ng-init="formData['lbUsersInfo.lbParent.pkId']='${mscUsersGroupJob.lbUsersInfo.lbParent.pkId}'">
                            <div>
                                <input type="text" class="form-control" ng-model="formData['lbUsersInfo.lbParent.lbName']"  ng-init="formData['lbUsersInfo.lbParent.lbName']='${mscUsersGroupJob.lbUsersInfo.lbParent.lbName}'" open-dialog-list ng-bean="baseDomainService&domain=com.os.osframe.core.users.domain.MscUsersInfo&name=lbName" ng-value-id="formData['lbUsersInfo.lbParent.pkId']">
                            </div>
                        </div>
                    </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersGroupJob.lbLevel"/></label>
                <input type="text" class="form-control"  ng-model="formData.lbLevel" ng-init="formData.lbLevel='${mscUsersGroupJob.lbLevel}'">
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersGroupJob.lbUsersInfo"/></label>
                <input type="hidden" class="form-control" ng-model="formData['lbUsersInfo.pkId']"  ng-init="formData['lbUsersInfo.pkId']='${mscUsersGroupJob.lbUsersInfo.pkId}'">
                <div>
                    <input type="text" class="form-control" ng-model="formData['lbUsersInfo.lbName']"  ng-init="formData['lbUsersInfo.lbName']='${mscUsersGroupJob.lbUsersInfo.lbName}'" open-dialog-list ng-bean="baseDomainService&domain=com.os.osframe.core.users.domain.MscUsersInfo&name=lbName" ng-value-id="formData['lbUsersInfo.pkId']">
                </div>
                </div>
            </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersGroupJob.lbPersonList"/></label>
                <input type="hidden" class="form-control" ng-model="formData['lbPersonList.pkId']"  ng-init="formData['lbPersonList.pkId']='${mscUsersGroupJob.lbPersonList.pkId}'">
                <div>
                    <input type="text" class="form-control" ng-model="formData['lbPersonList.lbName']"  ng-init="formData['lbPersonList.lbName']='${mscUsersGroupJob.lbPersonList.lbName}'" open-dialog-list ng-bean="baseDomainService&domain=com.os.osframe.core.users.domain.MscUsersInfo&name=lbName" ng-value-id="formData['lbPersonList.pkId']">
                </div>
                </div>
                <div class="col-sm-6"></div>
            </div>

                    <div class="form-group align-center">
                        <button type="button" class="btn btn-default" ng-edit-submit="mscUsersGroupJob(formData)"><fmt:message key="button.save"/></button>
                        <button type="reset" class="btn btn-default "><fmt:message key="button.reset"/></button>
                        <button type="button" class="btn btn-default" ng-return><fmt:message key="button.return"/></button>
                    </div>


                </div>
            </div>
        </form>
    </div>
</div>
