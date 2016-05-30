<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>
<div class="wrapper-md" ng-controller="mscUsersOrgListCtrl">
    <div >
        <!-- breadcrumb -->
        <ul class="breadcrumb bg-white b-a">
            <li ><i class="glyphicon glyphicon-list"></i><fmt:message key="mscUsersOrg.module"/></li>
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
                    <bs:role controller="MscUsersOrgController" function="add" >
                        <button type="button" class="btn btn-default " ui-sref="mscUsersOrgAdd"><fmt:message key="button.add"/></button>
                    </bs:role>
                    <bs:role controller="MscUsersOrgController" function="deleteMul" >
                        <button type="button" class="btn btn-default " ng-select-delete="mscUsersOrg(mscUsersOrgIds)"><fmt:message key="button.delete"/></button>
                    </bs:role>
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
                            <input type="checkbox" ng-all-checkbox="mscUsersOrgIds"><i></i>
                        </label>
                    </th>

                    <th width="20px"><fmt:message key="label.serial.number"/></th>

                    <th><fmt:message key="mscUsersInfo.lbName"/></th>
                    <th><fmt:message key="mscUsersInfo.lbKey"/></th>
                    <th><fmt:message key="mscUsersInfo.lbNo"/></th>
                    <th><fmt:message key="mscUsersInfo.lbOrder"/></th>
                    <th><fmt:message key="mscUsersInfo.lbCreateTime"/></th>
                    <th><fmt:message key="mscUsersInfo.lbParent"/></th>
                    <th ><fmt:message key="label.operation"/></th>
                </tr>
                </thead>
                <tbody>

                    <c:forEach items="${pageInfoList.list}" var="mscUsersOrg" varStatus="status">

                        
     <tr>
            <td><label class="i-checks m-b-none"><input type="checkbox" name="mscUsersOrgIds" ng-checkbox value="${mscUsersOrg.pkId}"><i></i></label></td>
            <td>${status.index+1}</td>
         <td>${mscUsersOrg.lbUsersInfo.lbName} </td>
         <td>${mscUsersOrg.lbUsersInfo.lbKey} </td>
         <td>${mscUsersOrg.lbUsersInfo.lbNo} </td>
         <td>${mscUsersOrg.lbUsersInfo.lbOrder} </td>
         <td>${mscUsersOrg.lbUsersInfo.lbCreateTime} </td>
         <td>${mscUsersOrg.lbUsersInfo.lbParent.lbName} </td>
            <td>
                <a ui-sref="mscUsersOrgLook({id:'${mscUsersOrg.pkId}'})"><span class="font-bold" ><fmt:message key="button.view"/></span>  </a>
            </td>
     </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
        <%@include file="/common/jsp/page_bottom.jsp"%>
    </div>
</div>
