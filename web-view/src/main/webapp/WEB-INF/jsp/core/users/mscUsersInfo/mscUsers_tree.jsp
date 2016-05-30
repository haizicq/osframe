<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<link rel="stylesheet" href="${rootRes}/css/ztree/zTreeStyle.css" type="text/css" />

<link rel="stylesheet" href="${rootPath}/resource/msc/users/mscUsersInfo/mscUsersTree.css" type="text/css" />
<div class="wrapper-md" ng-controller="mscUsersTreeCtrl">
  <div class="panel panel-default">
    <div class="panel-heading">
      <i class="glyphicon glyphicon-edit"></i>
      <span class="h4"><fmt:message key="mscUsersOrg.module"/></span>
    </div>
    <div class="panel-body">
      <div class="col-sm-2"  style="height:500px">
          <fmt:message key="mscUsersOrg.module"/>
          <div class="ztree" id="user-tree" >

          </div>
      </div>
      <div class=" col-sm-10"  ui-view>


      </div>
    </div>
  </div>

</div>