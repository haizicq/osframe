<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md">
    <div ng-controller="mscUsersGroupTypeLookCtrl">
        <form name="mscUsersGroupTypeForm" class="form-validation">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-book"></i>
                    <span class="h4"><fmt:message key="mscUsersGroupType.module"/></span>
                </div>
                <div class="panel-body">

                    
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersGroupType.lbName"/></label>
                <label class="form-control">${mscUsersGroupType.lbName}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersGroupType.lbNo"/></label>
                <label class="form-control">${mscUsersGroupType.lbNo}</label>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersGroupType.lbKey"/></label>
                <label class="form-control">${mscUsersGroupType.lbKey}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersGroupType.lbInfo"/></label>
                <label class="form-control">${mscUsersGroupType.lbInfo}</label>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersGroupType.lbStatus"/></label>
                <label class="form-control">${mscUsersGroupType.lbStatus}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersGroupType.lbUpdateTime"/></label>
                <label class="form-control">${mscUsersGroupType.lbUpdateTime}</label>
            </div>
            </div>

                    <div class="form-group align-center">
                        <bs:role controller="MscUsersGroupTypeController" function="delete" >
                            <button type="button" class="btn btn-default" ng-delete="mscUsersGroupType({id:'${mscUsersGroupType.pkId}'})" ><fmt:message key="button.delete"/></button>
                        </bs:role>
                        <bs:role controller="MscUsersGroupTypeController" function="edit" >
                            <button type="button" class="btn btn-default "  ui-sref="mscUsersGroupTypeEdit({id:'${mscUsersGroupType.pkId}'})"><span class="font-bold" ><fmt:message key="button.edit"/></span>  </button>
                        </bs:role>
                        <button type="button" class="btn btn-default" ng-return><fmt:message key="button.return"/></button>

                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
