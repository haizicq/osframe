<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>
<div class="wrapper-md" ng-controller="mscAuthInfoListCtrl">
    <div >
        <!-- breadcrumb -->
        <ul class="breadcrumb bg-white b-a">
            <li><i  class="glyphicon glyphicon-list"></i><fmt:message key="mscAuthInfo.module"/></li>

        </ul>
        <!-- / breadcrumb -->
    </div>
    <div class="panel panel-default">

        <div class="row wrapper">
            <div class="col-sm-5 m-b-xs">
                <div class="input-group">

                    <input type="text" class="input-sm form-control" placeholder="Search" ng-model="searchKey" ng-init="searchKey='${param.searchKey}'">
                      <span class="input-group-btn">
                        <button class="btn btn-sm btn-default" type="button" ng-search><i class="glyphicon glyphicon-search"></i></button>
                      </span>
                </div>
            </div>
            <div class="col-sm-7">
                <div class="pull-right">
                    <button type="button" class="btn btn-default " ng-refresh ><fmt:message key="button.refresh"/> </button>

                </div>

            </div>
        </div>
        <div class="table-responsive">
            <table class="table table-striped b-t b-light">
                <thead>
                <tr>
                    <th style="width:20px;">
                        <label class="i-checks m-b-none">
                            <input type="checkbox" ng-all-checkbox="mscAuthInfoIds" ><i></i>
                        </label>
                    </th>

                    <th width="20px"><fmt:message key="label.serial.number"/></th>
                    
            <th><fmt:message key="mscAuthInfo.lbName"/></th>
            <th><fmt:message key="mscAuthInfo.lbInfo"/></th>
            <th><fmt:message key="mscAuthInfo.lbUpdateTime"/></th>
                    <th ><fmt:message key="label.operation"/></th>
                </tr>
                </thead>
                <tbody>

                    <c:forEach items="${pageInfoList.list}" var="mscAuthInfo" varStatus="status">

                        
     <tr>
            <td><label class="i-checks m-b-none"><input type="checkbox" name="mscAuthInfoIds" ng-checkbox   value="${mscAuthInfo.pkId}"><i></i></label></td>
            <td>${status.index+1}</td>
            <td><bs:message key="${mscAuthInfo.lbName}"/> </td>
            <td><bs:message key="${mscAuthInfo.lbInfo}"/> </td>
            <td>${mscAuthInfo.lbUpdateTime}</td>
            <td>
                <a ui-sref="mscAuthInfoLook({id:'${mscAuthInfo.pkId}'})"><span class="font-bold" ><fmt:message key="button.view"/></span>  </a>
                <a ng-click="mscAuthInfoDelete({pkId:'${mscAuthInfo.pkId}'})"><span class="font-bold" ><fmt:message key="button.delete"/></span>  </a>

            </td>
     </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
        <%@include file="/common/jsp/page_bottom.jsp"%>
    </div>
</div>
