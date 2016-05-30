<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md" ng-controller="mscUsersOrgEditCtrl">
    <div>
        <form name="mscUsersOrgForm" class="form-validation" >
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-edit"></i>
                    <span class="h4"><fmt:message key="mscUsersOrg.module"/></span>
                    <input ng-model="formData.pkId" type="hidden" ng-init="formData.pkId='${mscUsersOrg.pkId}'" />
                </div>
                <div class="panel-body">

                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbName"/></label>
                            <input ng-model="formData['lbUsersInfo.pkId']" type="hidden" ng-init="formData['lbUsersInfo.pkId']='${mscUsersOrg.lbUsersInfo.pkId}'" />
                            <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbName']" ng-init="formData['lbUsersInfo.lbName']='${mscUsersOrg.lbUsersInfo.lbName}'">
                        </div>

                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbNo"/></label>
                            <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbNo']" ng-init="formData['lbUsersInfo.lbNo']='${mscUsersOrg.lbUsersInfo.lbNo}'">
                            <input type="hidden" class="form-control"  ng-model="formData['lbUsersInfo.lbType']" ng-init="formData['lbUsersInfo.lbType']='${mscUsersOrg.lbUsersInfo.lbType}'">
                        </div>
                    </div>
                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbKey"/></label>
                            <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbKey']" ng-init="formData['lbUsersInfo.lbKey']='${mscUsersOrg.lbUsersInfo.lbKey}'">
                        </div>

                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbOrder"/></label>
                            <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbOrder']" ng-init="formData['lbUsersInfo.lbOrder']='${mscUsersOrg.lbUsersInfo.lbOrder}'">
                            <input type="hidden" class="form-control"  ng-model="formData['lbUsersInfo.lbLevelsId']" ng-init="formData['lbUsersInfo.lbLevelsId']='${mscUsersOrg.lbUsersInfo.lbLevelsId}'">
                        </div>
                    </div>
                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbIsVirtual"/></label>
                            <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbIsVirtual']" ng-init="formData['lbUsersInfo.lbIsVirtual']='${mscUsersOrg.lbUsersInfo.lbIsVirtual}'">
                        </div>
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbIsValid"/></label>
                            <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbIsValid']" ng-init="formData['lbUsersInfo.lbIsValid']='${mscUsersOrg.lbUsersInfo.lbIsValid}'">
                        </div>
                    </div>
                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbInfo"/></label>
                            <input type="text" class="form-control"  ng-model="formData['lbUsersInfo.lbInfo']" ng-init="formData['lbUsersInfo.lbInfo']='${mscUsersOrg.lbUsersInfo.lbInfo}'">
                            <input type="hidden" class="form-control" ng-model="formData.lbCreateTime" ng-init="formData.lbCreateTime='${mscUsersOrg.lbUsersInfo.lbCreateTime}'">
                        </div>

                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbParent"/></label>
                            <input type="hidden" class="form-control" ng-model="formData['lbUsersInfo.lbParent.pkId']"  ng-init="formData['lbUsersInfo.lbParent.pkId']='${mscUsersOrg.lbUsersInfo.lbParent.pkId}'">
                            <div>
                                <input type="text" class="form-control" ng-model="formData['lbUsersInfo.lbParent.lbName']"  ng-init="formData['lbUsersInfo.lbParent.lbName']='${mscUsersOrg.lbUsersInfo.lbParent.lbName}'" open-dialog-list ng-bean="baseDomainService&domain=com.os.osframe.core.users.domain.MscUsersInfo&name=lbName" ng-value-id="formData['lbUsersInfo.lbParent.pkId']">
                            </div>
                        </div>
                    </div>

                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbType"/></label>
                            <div>
                                <label class="checkbox-inline i-checks">
                                    <input type="radio" class="form-control"  ng-model="formData['lbUsersInfo.lbType']" value="1" ng-init="formData['lbUsersInfo.lbType']='${mscUsersOrg.lbUsersInfo.lbType}'" />
                                    <i></i> 机构
                                </label>
                                <label class="checkbox-inline i-checks">
                                <input type="radio" class="form-control"  ng-model="formData['lbUsersInfo.lbType']" value="2" ng-checked="true"/>
                                    <i></i>部门
                                </label>

                            </div>
                        </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscUsersOrg.lbLeader"/></label>
                <input type="hidden" class="form-control" ng-model="formData['lbLeader.pkId']"  ng-init="formData['lbLeader.pkId']='${mscUsersOrg.lbLeader.pkId}'">
                <div>
                    <input type="text" class="form-control" ng-model="formData['lbLeader.lbName']"  ng-init="formData['lbLeader.lbName']='${mscUsersOrg.lbLeader.lbName}'" open-dialog-list ng-bean="baseDomainService&domain=com.os.osframe.core.users.domain.MscUsersInfo&name=lbName" ng-value-id="formData['lbLeader.pkId']">
                </div>
                </div>
            </div>

                    <div class="form-group align-center">
                        <button type="button" class="btn btn-default" ng-edit-submit="mscUsersOrg(formData)"><fmt:message key="button.save"/></button>
                        <button type="reset" class="btn btn-default "><fmt:message key="button.reset"/></button>
                        <button type="button" class="btn btn-default" ng-return><fmt:message key="button.return"/></button>
                    </div>


                </div>
            </div>
        </form>
    </div>
</div>
