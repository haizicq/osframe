<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md">
    <div ng-controller="mscAuthInfoLookCtrl">
        <form name="mscAuthInfoForm" class="form-validation">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-book"></i>
                    <span class="h4"><fmt:message key="mscAuthInfo.module"/></span>
                </div>
                <div class="panel-body">

                    
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthInfo.lbName"/></label>
                <label class="form-control"><bs:message key="${mscAuthInfo.lbName}"/></label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthInfo.lbInfo"/></label>
                <label class="form-control"><bs:message key="${mscAuthInfo.lbInfo}"/></label>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthInfo.lbAuthKey"/></label>
                <label class="form-control">${mscAuthInfo.lbAuthKey}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthInfo.lbUrlPrefix"/></label>
                <label class="form-control">${mscAuthInfo.lbUrlPrefix}</label>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthInfo.lbModule"/></label>
                <label class="form-control"><bs:message key="${mscAuthInfo.lbModule}"/></label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthInfo.lbSystem"/></label>
                <label class="form-control">${mscAuthInfo.lbSystem}</label>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthInfo.lbUpdateTime"/></label>
                <label class="form-control">${mscAuthInfo.lbUpdateTime}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthInfo.lbOrder"/></label>
                <label class="form-control">${mscAuthInfo.lbOrder}</label>
            </div>
            </div>
                    <div class="form-group align-center">
                        <button type="button" class="btn btn-default" ng-click="mscAuthInfoReturn()"><fmt:message key="button.return"/></button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
