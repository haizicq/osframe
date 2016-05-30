<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md">
    <div ng-controller="mscAuthDataLookCtrl">
        <form name="mscAuthDataForm" class="form-validation">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-book"></i>
                    <span class="h4"><fmt:message key="mscAuthData.module"/></span>
                </div>
                <div class="panel-body">

                    
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthData.lbModule"/></label>
                <label class="form-control">${mscAuthData.lbModule}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthData.lbSystem"/></label>
                <label class="form-control">${mscAuthData.lbSystem}</label>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthData.lbModelId"/></label>
                <label class="form-control">${mscAuthData.lbModelId}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthData.lbType"/></label>
                <label class="form-control">${mscAuthData.lbType}</label>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthData.lbNullType"/></label>
                <label class="form-control">${mscAuthData.lbNullType}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthData.lbUpdateTime"/></label>
                <label class="form-control">${mscAuthData.lbUpdateTime}</label>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthData.lbPersonList"/></label>
                <label class="form-control">${mscAuthData.lbPersonListNames}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthData.lbAllPersonList"/></label>
                <label class="form-control">${mscAuthData.lbAllPersonListNames}</label>
            </div>
            </div>
                    <div class="form-group align-center">
                        <bs:role controller="MscAuthDataController" function="delete" >
                            <button type="button" class="btn btn-default" ng-delete="mscAuthData({id:'${mscAuthData.pkId}'})" ><fmt:message key="button.delete"/></button>
                        </bs:role>
                        <bs:role controller="MscAuthDataController" function="edit" >
                            <button type="button" class="btn btn-default "  ui-sref="mscAuthDataEdit({id:'${mscAuthData.pkId}'})"><span class="font-bold" ><fmt:message key="button.edit"/></span>  </button>
                        </bs:role>
                        <button type="button" class="btn btn-default" ng-return><fmt:message key="button.return"/></button>

                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
