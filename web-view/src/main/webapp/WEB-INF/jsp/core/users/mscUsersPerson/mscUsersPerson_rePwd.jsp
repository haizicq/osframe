<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md">
    <div ng-controller="mscUsersPersonRePwdCtrl">
        <form name="mscUsersPersonForm" class="form-validation"  ng-submit="submitForm()">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-book"></i>
                    <span class="h4"><fmt:message key="mscUsersPerson.replacePassword"/></span>
                </div>
                <div class="panel-body">
                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-12">
                            <label><fmt:message key="mscUsersPerson.newPassword"/></label>
                            <input type="password" class="form-control"  ng-model="formData.newPassword" ng-init="formData.newPassword=''">
                            <input type="hidden" class="form-control"  ng-model="formData.usersId" ng-init="formData.usersId='${param.id}'">
                        </div>
                    </div>
                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-12">
                            <label><fmt:message key="mscUsersPerson.newRepeatPassword"/></label>
                            <input type="password" class="form-control"  ng-model="formData.newRepeatPassword" ng-init="formData.newRepeatPassword=''">
                        </div>
                    </div>

                    <div class="form-group align-center">
                        <bs:role controller="MscUsersPersonController" function="rePwd" >
                            <button type="submit"  class="btn btn-default "><span class="font-bold" ><fmt:message key="button.save"/></span>  </button>
                        </bs:role>
                        <button type="button" class="btn btn-default" ng-return><fmt:message key="button.return"/></button>

                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
