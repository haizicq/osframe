<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md">
  <div ng-controller="myInfoCtrl">
    <form name="myInfoForm" class="form-validation" ng-submit="submitForm()">
      <div class="panel panel-default">
        <div class="panel-heading">
          <i class="glyphicon glyphicon-book"></i>
          <span class="h4"><fmt:message key="label.myInfo"/></span>
        </div>
        <div class="panel-body">
          <div class="form-group pull-in clearfix">
            <div class="col-sm-6">
              <label><fmt:message key="mscUsersInfo.lbName"/></label>
              <label class="form-control">${userInfo.lbUsersInfo.lbName}</label>
            </div>
            <div class="col-sm-6">
              <label><fmt:message key="mscUsersInfo.lbDept"/></label>
              <label class="form-control">${userInfo.lbUsersInfo.lbParent.lbName}</label>

            </div>
          </div>
          <div class="form-group pull-in clearfix">
            <div class="col-sm-6">
              <label><fmt:message key="mscUsersPerson.lbBirthday"/></label>
                <input type="text"  class="form-control"  ng-model="formData.birthday" ng-init="formData.birthday='${userInfo.lbBirthday}'">

            </div>
            <div class="col-sm-6">
              <label><fmt:message key="mscUsersPerson.lbSex"/></label>
              <div class="form-control">
                <bs:enums name="formData.sex" key="enumsSexType" type="select" value="${userInfo.lbSex}"/>
              </div>
            </div>
          </div>
          <div class="form-group pull-in clearfix">
            <div class="col-sm-6">
              <label><fmt:message key="mscUsersPerson.lbPhone"/></label>
              <input type="text"  class="form-control"  ng-model="formData.phone" ng-init="formData.phone='${userInfo.lbPhone}'">

            </div>
            <div class="col-sm-6">
              <label><fmt:message key="mscUsersPerson.lbMobilePhone"/></label>
              <input type="text"  class="form-control"  ng-model="formData.mobilePhone" ng-init="formData.mobilePhone='${userInfo.lbMobilePhone}'">

            </div>
          </div>
          <div class="form-group pull-in clearfix">
            <div class="col-sm-6">
              <label><fmt:message key="mscUsersPerson.lbMail"/></label>
              <input type="text"  class="form-control"  ng-model="formData.email" ng-init="formData.email='${userInfo.lbMail}'">

            </div>
            <div class="col-sm-6">
              <label><fmt:message key="mscUsersPerson.lbOthers"/></label>
              <input type="text"  class="form-control"  ng-model="formData.other" ng-init="formData.other='${userInfo.lbOthers}'">

            </div>
          </div>
          <div class="form-group pull-in clearfix">
            <div class="col-sm-12">
              <label><fmt:message key="mscUsersPerson.lbLanguage"/></label>
              <div class="form-control">
                <bs:enums name="formData.language" key="enumsLanguage" type="select" value="${userInfo.lbLanguage}"/>
              </div>
            </div>
          </div>

          <div class="form-group align-center">
              <button type="button" class="btn btn-default "  ng-click="saveInfo()"><span class="font-bold" >
                <fmt:message key="button.save"/>
              </span>  </button>
          </div>
        </div>
      </div>
    </form>
  </div>
</div>
