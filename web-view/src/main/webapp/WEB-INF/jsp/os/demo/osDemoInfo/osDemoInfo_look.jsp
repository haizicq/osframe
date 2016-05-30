<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md">
    <div ng-controller="osDemoInfoLookCtrl">
        <form name="osDemoInfoForm" class="form-validation">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-book"></i>
                    <span class="h4"><fmt:message key="osDemoInfo.module"/></span>
                </div>
                <div class="panel-body">

                    
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="osDemoInfo.lbName"/></label>
                <label class="form-control">${osDemoInfo.lbName}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="osDemoInfo.lbType"/></label>

                 <div class="form-control">
                     <bs:enums name="formData.lbType" key="enumsDemo" value="${osDemoInfo.lbType}" display="label"/>
                 </div>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="osDemoInfo.lbTime"/></label>
                <label class="form-control">${osDemoInfo.lbTime}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="osDemoInfo.lbParent"/></label>
                <label class="form-control">${osDemoInfo.lbParent.lbName}</label>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="osDemoInfo.lbDept"/></label>
                <label class="form-control">${osDemoInfo.lbDept.lbName}</label>
            </div>
                <div class="col-sm-6"></div>
            </div>

                        <div class="form-group align-center">
                            <bs:role controller="OsDemoInfoController" function="delete" >
                                <button type="button" class="btn btn-default" ng-delete="osDemoInfo({id:'${osDemoInfo.pkId}'})" ><fmt:message key="button.delete"/></button>
                            </bs:role>
                            <bs:role controller="OsDemoInfoController" function="edit" >
                                <button type="button" class="btn btn-default "  ui-sref="osDemoInfoEdit({id:'${osDemoInfo.pkId}'})"><span class="font-bold" ><fmt:message key="button.edit"/></span>  </button>
                            </bs:role>
                            <button type="button" class="btn btn-default" ng-return><fmt:message key="button.return"/></button>

                        </div>
                </div>
            </div>
        </form>
    </div>
</div>
