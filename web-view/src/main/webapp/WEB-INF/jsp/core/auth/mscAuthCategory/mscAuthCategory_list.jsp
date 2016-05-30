<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md" ng-controller="mscAuthCategoryListCtrl">
    <div >
        <!-- breadcrumb -->
        <ul class="breadcrumb bg-white b-a">
            <li><i  class="glyphicon glyphicon-list"></i><fmt:message key="mscAuthCategory.module"/></li>

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
                    <bs:role controller="MscAuthCategoryController" function="add" >
                        <button type="button" class="btn btn-default " ui-sref="mscAuthCategoryAdd"><fmt:message key="button.add"/></button>
                    </bs:role>
                    <bs:role controller="MscAuthCategoryController" function="deleteMul" >
                        <button type="button" class="btn btn-default " ng-select-delete="mscAuthCategory(mscAuthCategoryIds)"><fmt:message key="button.delete"/></button>
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
                            <input type="checkbox" ng-all-checkbox="mscAuthCategoryIds"><i></i>
                        </label>
                    </th>

                    <th width="20px"><fmt:message key="label.serial.number"/></th>

            <th><fmt:message key="mscAuthCategory.lbName"/></th>
            <th><fmt:message key="mscAuthCategory.lbKey"/></th>
            <th><fmt:message key="mscAuthCategory.lbOrder"/></th>
            <th><fmt:message key="mscAuthCategory.lbInfo"/></th>
            <th><fmt:message key="mscAuthCategory.lbUpdateTime"/></th>
                    <th ><fmt:message key="label.operation"/></th>
                </tr>
                </thead>
                <tbody>

                    <c:forEach items="${pageInfoList.list}" var="mscAuthCategory" varStatus="status">

                        
     <tr>
            <td><label class="i-checks m-b-none"><input type="checkbox" name="mscAuthCategoryIds" ng-checkbox value="${mscAuthCategory.pkId}"><i></i></label></td>
            <td>${status.index+1}</td>
            <td>${mscAuthCategory.lbName} </td>
            <td>${mscAuthCategory.lbKey} </td>
            <td>${mscAuthCategory.lbOrder} </td>
            <td>${mscAuthCategory.lbInfo} </td>
            <td>${mscAuthCategory.lbUpdateTime} </td>
            <td>
                <a ui-sref="mscAuthCategoryLook({id:'${mscAuthCategory.pkId}'})"><span class="font-bold" ><fmt:message key="button.view"/></span>  </a>
            </td>
     </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>

        <%@include file="/common/jsp/page_bottom.jsp"%>
    </div>
</div>
