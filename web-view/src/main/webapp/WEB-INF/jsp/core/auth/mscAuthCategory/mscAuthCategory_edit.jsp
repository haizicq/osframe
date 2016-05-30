<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md" ng-controller="mscAuthCategoryEditCtrl">
    <div>
        <form name="mscAuthCategoryForm" class="form-validation" >
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-edit"></i>
                    <span class="h4"><fmt:message key="mscAuthCategory.module"/></span>
                    <input ng-model="formData.pkId" type="hidden" ng-init="formData.pkId='${mscAuthCategory.pkId}'" />
                </div>
                <div class="panel-body">
                    
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscAuthCategory.lbName"/></label>
                    <input type="text" class="form-control"  ng-model="formData.lbName" ng-init="formData.lbName='${mscAuthCategory.lbName}'">
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscAuthCategory.lbKey"/></label>
                    <input type="text" class="form-control"  ng-model="formData.lbKey" ng-init="formData.lbKey='${mscAuthCategory.lbKey}'">
                </div>
            </div>
            <div class="form-group pull-in clearfix">
                <div class="col-sm-6">
                    <label><fmt:message key="mscAuthCategory.lbOrder"/></label>
                    <input type="text" class="form-control"  ng-model="formData.lbOrder" ng-init="formData.lbOrder='${mscAuthCategory.lbOrder}'">
                </div>
                <div class="col-sm-6">
                    <label><fmt:message key="mscAuthCategory.lbInfo"/></label>
                    <input type="text" class="form-control"  ng-model="formData.lbInfo" ng-init="formData.lbInfo='${mscAuthCategory.lbInfo}'">
                    <input type="hidden" ng-model="formData.lbUpdateTime"  ng-init="formData.lbUpdateTime='${mscAuthCategory.lbUpdateTime}'"/>
                </div>
            </div>


                    <div class="form-group align-center">
                        <button type="button" class="btn btn-default" ng-edit-submit="mscAuthCategory(formData)"><fmt:message key="button.save"/></button>
                        <button type="reset" class="btn btn-default "><fmt:message key="button.reset"/></button>
                        <button type="button" class="btn btn-default" ng-return><fmt:message key="button.return"/></button>

                    </div>

                </div>
            </div>
        </form>
    </div>
</div>
