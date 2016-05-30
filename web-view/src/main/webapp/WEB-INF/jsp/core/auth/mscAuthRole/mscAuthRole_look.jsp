<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md">
    <div ng-controller="mscAuthRoleLookCtrl">
        <form name="mscAuthRoleForm" class="form-validation">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-book"></i>
                    <span class="h4"><fmt:message key="mscAuthRole.module"/></span>
                </div>
                <div class="panel-body">

                    
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthRole.lbName"/></label>
                <label class="form-control">${mscAuthRole.lbName}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthRole.lbKey"/></label>
                <label class="form-control">${mscAuthRole.lbKey}</label>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthRole.lbType"/></label>
                 <div class="form-control">
                     <bs:enums name="formData.lbType" value="${mscAuthRole.lbType}" key="enumsAuthType" type="select" display="label" />
                 </div>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthRole.lbIsValid"/></label>

                 <div class="form-control">
                     <bs:enums name="formData.lbIsValid" value="${mscAuthRole.lbIsValid}" key="enumsCommonOrNot" type="select" display="label" />
                 </div>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthRole.lbInfo"/></label>
                <label class="form-control">${mscAuthRole.lbInfo}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthRole.lbUpdateTime"/></label>
                <label class="form-control">${mscAuthRole.lbUpdateTime}</label>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthRole.lbParent"/></label>
                <label class="form-control">${mscAuthRole.lbParent.lbName}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthRole.lbRoleCategory"/></label>
                <label class="form-control">${mscAuthRole.lbRoleCategory.lbName}</label>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthRole.lbAuthList"/></label>
                <label class="form-control">${mscAuthRole.lbAuthListNames}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthRole.lbPersonList"/></label>
                <label class="form-control">${mscAuthRole.lbPersonListNames}</label>
            </div>
            </div>
                    <div class="form-group align-center">
                        <bs:role controller="MscAuthRoleController" function="delete" >
                            <button type="button" class="btn btn-default" ng-delete="mscAuthRole({id:'${mscAuthRole.pkId}'})" ><fmt:message key="button.delete"/></button>
                        </bs:role>
                        <bs:role controller="MscAuthRoleController" function="edit" >
                            <button type="button" class="btn btn-default "  ui-sref="mscAuthRoleEdit({id:'${mscAuthRole.pkId}'})"><span class="font-bold" ><fmt:message key="button.edit"/></span>  </button>
                        </bs:role>
                        <button type="button" class="btn btn-default" ng-return><fmt:message key="button.return"/></button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
