<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>
<div class="wrapper-md" ng-controller="mscAuthRoleListCtrl">
    <div >
        <!-- breadcrumb -->
        <ul class="breadcrumb bg-white b-a">
            <li><i  class="glyphicon glyphicon-list"></i><fmt:message key="mscAuthRole.module"/></li>

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
                    <a ui-sref="mscAuthRoleAdd({cid:'${param.cid}'})"><span class="font-bold" ><fmt:message key="button.add"/></span>  </a>
                </div>

            </div>
        </div>
        <div class="table-responsive">
            <table class="table table-striped b-t b-light">
                <thead>
                <tr>
                    <th style="width:20px;">
                        <label class="i-checks m-b-none">
                            <input type="checkbox"  ng-all-checkbox="mscAuthRoleIds"><i></i>
                        </label>
                    </th>

                    <th width="20px"><fmt:message key="label.serial.number"/></th>
                    
            <th><fmt:message key="mscAuthRole.lbName"/></th>
            <th><fmt:message key="mscAuthRole.lbKey"/></th>
            <th><fmt:message key="mscAuthRole.lbType"/></th>
            <th><fmt:message key="mscAuthRole.lbIsValid"/></th>
            <th><fmt:message key="mscAuthRole.lbUpdateTime"/></th>
            <th><fmt:message key="mscAuthRole.lbParent"/></th>
            <th><fmt:message key="mscAuthRole.lbRoleCategory"/></th>
                    <th ><fmt:message key="label.operation"/></th>
                </tr>
                </thead>
                <tbody>

                    <c:forEach items="${pageInfoList.list}" var="mscAuthRole" varStatus="status">

                        
     <tr>
            <td><label class="i-checks m-b-none"><input type="checkbox" name="mscAuthRoleIds" ng-checkbox  value="${mscAuthRole.pkId}"><i></i></label></td>
            <td>${status.index+1}</td>
            <td>${mscAuthRole.lbName} </td>
            <td>${mscAuthRole.lbKey} </td>
            <td>${mscAuthRole.lbType} </td>
            <td>${mscAuthRole.lbIsValid} </td>
            <td>${mscAuthRole.lbUpdateTime} </td>
            <td>${mscAuthRole.lbParent.lbName} </td>
            <td>${mscAuthRole.lbRoleCategory.lbName} </td>
            <td>
                <a ui-sref="mscAuthRoleLook({id:'${mscAuthRole.pkId}'})"><span class="font-bold" ><fmt:message key="button.view"/></span>  </a>
                <a ui-sref="mscAuthRoleEdit({id:'${mscAuthRole.pkId}'})"><span class="font-bold" ><fmt:message key="button.edit"/></span>  </a>
            </td>
     </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
        <%@include file="/common/jsp/page_bottom.jsp"%>
    </div>
</div>
