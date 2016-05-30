<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md">
    <div ng-controller="mscAuthCategoryLookCtrl">
        <form name="mscAuthCategoryForm" class="form-validation">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-book"></i>
                    <span class="h4"><fmt:message key="mscAuthCategory.module"/></span>
                </div>
                <div class="panel-body">

                    
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthCategory.lbName"/></label>
                <label class="form-control">${mscAuthCategory.lbName}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthCategory.lbKey"/></label>
                <label class="form-control">${mscAuthCategory.lbKey}</label>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthCategory.lbOrder"/></label>
                <label class="form-control">${mscAuthCategory.lbOrder}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscAuthCategory.lbInfo"/></label>
                <label class="form-control">${mscAuthCategory.lbInfo}</label>
            </div>
            </div>
            <div class="form-group pull-in clearfix">
                 <div class="col-sm-6">
                    <label><fmt:message key="mscAuthCategory.lbUpdateTime"/></label>
                    <label class="form-control">${mscAuthCategory.lbUpdateTime}</label>
                </div>
                    <div class="col-sm-6"></div>
                </div>
                    <div class="form-group align-center">
                        <bs:role controller="MscAuthCategoryController" function="delete" >
                            <button type="button" class="btn btn-default" ng-delete="mscAuthCategory({id:'${mscAuthCategory.pkId}'})" ><fmt:message key="button.delete"/></button>
                        </bs:role>
                        <bs:role controller="MscAuthCategoryController" function="edit" >
                            <button type="button" class="btn btn-default "  ui-sref="mscAuthCategoryEdit({id:'${mscAuthCategory.pkId}'})"><span class="font-bold" ><fmt:message key="button.edit"/></span>  </button>
                        </bs:role>
                        <button type="button" class="btn btn-default" ng-return><fmt:message key="button.return"/></button>

                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
