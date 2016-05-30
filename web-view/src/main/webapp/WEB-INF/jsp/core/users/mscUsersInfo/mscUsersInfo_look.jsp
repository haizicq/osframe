<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md">
    <div ng-controller="mscUsersInfoLookCtrl">
        <form name="mscUsersInfoForm" class="form-validation">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-book"></i>
                    <span class="h4"><fmt:message key="mscUsersInfo.module"/></span>
                </div>
                <div class="panel-body">

                    
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersInfo.lbName"/></label>
                <label class="form-control">${mscUsersInfo.lbName}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersInfo.lbKey"/></label>
                <label class="form-control">${mscUsersInfo.lbKey}</label>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersInfo.lbType"/></label>
                <label class="form-control">${mscUsersInfo.lbType}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersInfo.lbNo"/></label>
                <label class="form-control">${mscUsersInfo.lbNo}</label>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersInfo.lbOrder"/></label>
                <label class="form-control">${mscUsersInfo.lbOrder}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersInfo.lbLevelsId"/></label>
                <label class="form-control">${mscUsersInfo.lbLevelsId}</label>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersInfo.lbIsVirtual"/></label>
                <label class="form-control">${mscUsersInfo.lbIsVirtual}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersInfo.lbIsValid"/></label>
                <label class="form-control">${mscUsersInfo.lbIsValid}</label>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersInfo.lbInfo"/></label>
                <label class="form-control">${mscUsersInfo.lbInfo}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersInfo.lbCreateTime"/></label>
                <label class="form-control">${mscUsersInfo.lbCreateTime}</label>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersInfo.lbUpdateTime"/></label>
                <label class="form-control">${mscUsersInfo.lbUpdateTime}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersInfo.lbSourceKey"/></label>
                <label class="form-control">${mscUsersInfo.lbSourceKey}</label>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersInfo.lbSourcePk"/></label>
                <label class="form-control">${mscUsersInfo.lbSourcePk}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersInfo.lbParent"/></label>
                <label class="form-control">${mscUsersInfo.lbParent.lbName}</label>
            </div>
            </div>

                    <div class="form-group align-center">
                        <bs:role controller="MscUsersInfoController" function="delete" >
                            <button type="button" class="btn btn-default" ng-delete="mscUsersInfo({id:'${mscUsersInfo.pkId}'})" ><fmt:message key="button.delete"/></button>
                        </bs:role>
                        <bs:role controller="MscUsersInfoController" function="edit" >
                            <button type="button" class="btn btn-default "  ui-sref="mscUsersInfoEdit({id:'${mscUsersInfo.pkId}'})"><span class="font-bold" ><fmt:message key="button.edit"/></span>  </button>
                        </bs:role>
                        <button type="button" class="btn btn-default" ng-return><fmt:message key="button.return"/></button>

                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
