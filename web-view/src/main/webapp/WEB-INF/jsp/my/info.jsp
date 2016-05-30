<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md">
  <div ng-controller="myInfoCtrl">
    <form name="myInfoForm" class="form-validation" ng-submit="submitForm()">
      <div class="panel panel-default">
        <div class="panel-heading">
          <i class="glyphicon glyphicon-book"></i>
          <span class="h4"><fmt:message key="label.myInfo"/> </span>
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
              <label class="form-control">${userInfo.lbBirthday}</label>
            </div>
            <div class="col-sm-6">
              <label><fmt:message key="mscUsersPerson.lbSex"/></label>
              <div class="form-control">
                <bs:enums display="label" name="fromData.sex" key="enumsSexType" type="select" value="${userInfo.lbSex}"/>
              </div>

            </div>
          </div>
          <div class="form-group pull-in clearfix">
            <div class="col-sm-6">
              <label><fmt:message key="mscUsersPerson.lbPhone"/></label>
              <label class="form-control">${userInfo.lbPhone}</label>
            </div>
            <div class="col-sm-6">
              <label><fmt:message key="mscUsersPerson.lbMobilePhone"/></label>
              <label class="form-control">${userInfo.lbMobilePhone}</label>
            </div>
          </div>
          <div class="form-group pull-in clearfix">
            <div class="col-sm-6">
              <label><fmt:message key="mscUsersPerson.lbMail"/></label>
              <label class="form-control">${userInfo.lbMail}</label>
            </div>
            <div class="col-sm-6">
              <label><fmt:message key="mscUsersPerson.lbOthers"/></label>
              <label class="form-control">${userInfo.lbOthers}</label>
            </div>
          </div>
          <div class="form-group pull-in clearfix">
            <div class="col-sm-12">
              <label><fmt:message key="mscUsersPerson.lbLanguage"/></label>

              <div class="form-control">
                <bs:enums display="label" name="fromData.language" key="enumsLanguage" type="select" value="${userInfo.lbLanguage}"/>
              </div>
            </div>
          </div>

          <div class="form-group align-center">
              <button type="button" class="btn btn-default "  ui-sref="edit"><span class="font-bold" >
                <fmt:message key="button.edit"/>
              </span>  </button>
          </div>
        </div>
      </div>
    </form>
  </div>
</div>
