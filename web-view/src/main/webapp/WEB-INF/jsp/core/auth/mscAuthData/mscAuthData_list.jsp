<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>
<div class="wrapper-md" ng-controller="mscAuthDataListCtrl">
    <div >
        <!-- breadcrumb -->
        <ul class="breadcrumb bg-white b-a">
            <li><i  class="glyphicon glyphicon-list"></i><fmt:message key="mscAuthData.module"/></li>

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
                    <a ui-sref="mscAuthDataAdd"><span class="font-bold" ><fmt:message key="button.add"/></span>  </a>
                    <bs:role controller="MscAuthDataController" function="add" >
                        <button type="button" class="btn btn-default " ui-sref="mscAuthDataAdd"><fmt:message key="button.add"/></button>
                    </bs:role>
                    <bs:role controller="MscAuthDataController" function="deleteMul" >
                        <button type="button" class="btn btn-default " ng-select-delete="mscAuthData(mscAuthDataIds)"><fmt:message key="button.delete"/></button>
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
                            <input type="checkbox" ng-all-checkbox="mscAuthDataIds"><i></i>
                        </label>
                    </th>

                    <th width="20px"><fmt:message key="label.serial.number"/></th>
                    
            <th><fmt:message key="mscAuthData.lbModule"/></th>
            <th><fmt:message key="mscAuthData.lbSystem"/></th>
            <th><fmt:message key="mscAuthData.lbModelId"/></th>
            <th><fmt:message key="mscAuthData.lbType"/></th>
            <th><fmt:message key="mscAuthData.lbNullType"/></th>
            <th><fmt:message key="mscAuthData.lbUpdateTime"/></th>
            <th><fmt:message key="mscAuthData.lbPersonList"/></th>
            <th><fmt:message key="mscAuthData.lbAllPersonList"/></th>
                    <th ><fmt:message key="label.operation"/></th>
                </tr>
                </thead>
                <tbody>

                    <c:forEach items="${pageInfoList.list}" var="mscAuthData" varStatus="status">

                        
     <tr>
            <td><label class="i-checks m-b-none"><input type="checkbox" name="mscAuthDataIds" ng-checkbox value="${mscAuthData.pkId}"><i></i></label></td>
            <td>${status.index+1}</td>
            <td>${mscAuthData.lbModule} </td>
            <td>${mscAuthData.lbSystem} </td>
            <td>${mscAuthData.lbModelId} </td>
            <td>${mscAuthData.lbType} </td>
            <td>${mscAuthData.lbNullType} </td>
            <td>${mscAuthData.lbUpdateTime} </td>
            <td>${mscAuthData.lbPersonListNames} </td>
            <td>${mscAuthData.lbAllPersonListNames} </td>
            <td>
                <a ui-sref="mscAuthDataLook({id:'${mscAuthData.pkId}'})"><span class="font-bold" ><fmt:message key="button.view"/></span>  </a>
            </td>
     </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>

        <%@include file="/common/jsp/page_bottom.jsp"%>
    </div>
</div>
