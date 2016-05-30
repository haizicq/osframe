<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md">
  <div ng-controller="myPwdCtrl">
    <form name="myPwdForm" class="form-validation" ng-submit="submitForm()">
      <div class="panel panel-default">
        <div class="panel-heading">
          <i class="glyphicon glyphicon-book"></i>
          <span class="h4"><fmt:message key="mscUsersPerson.changeMyPwd"/></span>
        </div>
        <div class="panel-body">
          <div class="form-group pull-in clearfix">
            <div class="col-sm-12">
              <label><fmt:message key="mscUsersPerson.oldPassword"/></label>
              <input type="password" class="form-control"  ng-model="formData.oldPwd" ng-init="formData.oldPwd=''">
            </div>
          </div>
          <div class="form-group pull-in clearfix">
            <div class="col-sm-12">
              <label><fmt:message key="mscUsersPerson.newPassword"/></label>
              <input type="password" class="form-control"  ng-model="formData.newPwd" ng-init="formData.newPwd=''">
            </div>
          </div>
          <div class="form-group pull-in clearfix">
            <div class="col-sm-12">
              <label><fmt:message key="mscUsersPerson.newRepeatPassword"/></label>
              <input type="password" class="form-control"  ng-model="formData.newConfirmPwd" ng-init="formData.newConfirmPwd=''">
            </div>
          </div>

          <div class="form-group align-center">
             <button type="submit" class="btn btn-default "  ><span class="font-bold" ><fmt:message key="button.save"/></span>  </button>

          </div>
        </div>
      </div>
    </form>
  </div>
</div>
