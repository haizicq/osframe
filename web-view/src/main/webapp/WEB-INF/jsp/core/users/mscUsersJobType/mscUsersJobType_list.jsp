<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>
<div class="wrapper-md" ng-controller="mscUsersJobTypeListCtrl">
    <div >
        <!-- breadcrumb -->
        <ul class="breadcrumb bg-white b-a">
            <li ><i class="glyphicon glyphicon-list"></i><fmt:message key="mscUsersJobType.module"/></li>
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
                    <bs:role controller="MscUsersJobTypeController" function="add" >
                        <button type="button" class="btn btn-default " ui-sref="mscUsersJobTypeAdd"><fmt:message key="button.add"/></button>
                    </bs:role>
                    <bs:role controller="MscUsersJobTypeController" function="deleteMul" >
                        <button type="button" class="btn btn-default " ng-select-delete="mscUsersJobType(mscUsersJobTypeIds)"><fmt:message key="button.delete"/></button>
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
                            <input type="checkbox" ng-all-checkbox="mscUsersJobTypeIds"><i></i>
                        </label>
                    </th>

                    <th width="20px"><fmt:message key="label.serial.number"/></th>
                    
            <th><fmt:message key="mscUsersJobType.lbName"/></th>
            <th><fmt:message key="mscUsersJobType.lbNo"/></th>
            <th><fmt:message key="mscUsersJobType.lbLevel"/></th>
            <th><fmt:message key="mscUsersJobType.lbKey"/></th>
            <th><fmt:message key="mscUsersJobType.lbInfo"/></th>
            <th><fmt:message key="mscUsersJobType.lbStatus"/></th>
            <th><fmt:message key="mscUsersJobType.lbUpdateTime"/></th>
                    <th ><fmt:message key="label.operation"/></th>
                </tr>
                </thead>
                <tbody>

                    <c:forEach items="${pageInfoList.list}" var="mscUsersJobType" varStatus="status">

                        
     <tr>
            <td><label class="i-checks m-b-none"><input type="checkbox" name="mscUsersJobTypeIds" ng-checkbox value="${mscUsersJobType.pkId}"><i></i></label></td>
            <td>${status.index+1}</td>
            <td>${mscUsersJobType.lbName} </td>
            <td>${mscUsersJobType.lbNo} </td>
            <td>${mscUsersJobType.lbLevel} </td>
            <td>${mscUsersJobType.lbKey} </td>
            <td>${mscUsersJobType.lbInfo} </td>
            <td>${mscUsersJobType.lbStatus} </td>
            <td>${mscUsersJobType.lbUpdateTime} </td>
            <td>
                <a ui-sref="mscUsersJobTypeLook({id:'${mscUsersJobType.pkId}'})"><span class="font-bold" ><fmt:message key="button.view"/></span>  </a>
            </td>
     </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
        <%@include file="/common/jsp/page_bottom.jsp"%>
    </div>
</div>
