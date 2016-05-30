<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>
<div class="wrapper-md" ng-controller="mscUsersGroupTypeListCtrl">
    <div >
        <!-- breadcrumb -->
        <ul class="breadcrumb bg-white b-a">
           <li ><i class="glyphicon glyphicon-list"></i><fmt:message key="mscUsersGroupType.module"/></li>
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
                    <bs:role controller="MscUsersGroupTypeController" function="add" >
                        <button type="button" class="btn btn-default " ui-sref="mscUsersGroupTypeAdd"><fmt:message key="button.add"/></button>
                    </bs:role>
                    <bs:role controller="MscUsersGroupTypeController" function="deleteMul" >
                        <button type="button" class="btn btn-default " ng-select-delete="mscUsersGroupType(mscUsersGroupTypeIds)"><fmt:message key="button.delete"/></button>
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
                            <input type="checkbox" ng-all-checkbox="mscUsersGroupTypeIds"><i></i>
                        </label>
                    </th>

                    <th width="20px"><fmt:message key="label.serial.number"/></th>
                    
            <th><fmt:message key="mscUsersGroupType.lbName"/></th>
            <th><fmt:message key="mscUsersGroupType.lbNo"/></th>
            <th><fmt:message key="mscUsersGroupType.lbKey"/></th>
            <th><fmt:message key="mscUsersGroupType.lbInfo"/></th>
            <th><fmt:message key="mscUsersGroupType.lbStatus"/></th>
            <th><fmt:message key="mscUsersGroupType.lbUpdateTime"/></th>
                    <th ><fmt:message key="label.operation"/></th>
                </tr>
                </thead>
                <tbody>

                    <c:forEach items="${pageInfoList.list}" var="mscUsersGroupType" varStatus="status">

                        
     <tr>
            <td><label class="i-checks m-b-none"><input type="checkbox"  name="mscUsersGroupTypeIds" ng-checkbox value="${mscUsersGroupType.pkId}"><i></i></label></td>
            <td>${status.index+1}</td>
            <td>${mscUsersGroupType.lbName} </td>
            <td>${mscUsersGroupType.lbNo} </td>
            <td>${mscUsersGroupType.lbKey} </td>
            <td>${mscUsersGroupType.lbInfo} </td>
            <td>${mscUsersGroupType.lbStatus} </td>
            <td>${mscUsersGroupType.lbUpdateTime} </td>
            <td>
                <a ui-sref="mscUsersGroupTypeLook({id:'${mscUsersGroupType.pkId}'})"><span class="font-bold" ><fmt:message key="button.view"/></span>  </a>
            </td>
     </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
        <%@include file="/common/jsp/page_bottom.jsp"%>
    </div>
</div>
