<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md">
    <div ng-controller="mscUsersJobTypeLookCtrl">
        <form name="mscUsersJobTypeForm" class="form-validation">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-book"></i>
                    <span class="h4"><fmt:message key="mscUsersJobType.module"/></span>
                </div>
                <div class="panel-body">

                    
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersJobType.lbName"/></label>
                <label class="form-control">${mscUsersJobType.lbName}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersJobType.lbNo"/></label>
                <label class="form-control">${mscUsersJobType.lbNo}</label>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersJobType.lbLevel"/></label>
                <label class="form-control">${mscUsersJobType.lbLevel}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersJobType.lbKey"/></label>
                <label class="form-control">${mscUsersJobType.lbKey}</label>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersJobType.lbInfo"/></label>
                <label class="form-control">${mscUsersJobType.lbInfo}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersJobType.lbStatus"/></label>
                <label class="form-control">${mscUsersJobType.lbStatus}</label>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersJobType.lbUpdateTime"/></label>
                <label class="form-control">${mscUsersJobType.lbUpdateTime}</label>
            </div>
                <div class="col-sm-6"></div>
            </div>


                    <div class="form-group align-center">
                        <bs:role controller="MscUsersJobTypeController" function="delete" >
                            <button type="button" class="btn btn-default" ng-delete="mscUsersJobType({id:'${mscUsersJobType.pkId}'})" ><fmt:message key="button.delete"/></button>
                        </bs:role>
                        <bs:role controller="MscUsersJobTypeController" function="edit" >
                            <button type="button" class="btn btn-default "  ui-sref="mscUsersJobTypeEdit({id:'${mscUsersJobType.pkId}'})"><span class="font-bold" ><fmt:message key="button.edit"/></span>  </button>
                        </bs:role>
                        <button type="button" class="btn btn-default" ng-return><fmt:message key="button.return"/></button>

                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
