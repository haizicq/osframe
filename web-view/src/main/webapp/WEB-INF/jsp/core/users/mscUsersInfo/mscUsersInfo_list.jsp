<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>
<div  ng-controller="mscUsersInfoListCtrl">

    <div class="panel panel-default">

        <div class="row wrapper">
            <div class="col-sm-7 m-b-xs">
                <div class="input-group">
                    <input type="text" class="input-sm form-control ng-pristine ng-untouched ng-valid" placeholder="Search" ng-model="searchKey" ng-init="searchKey='${param.searchKey}'">
                      <span class="input-group-btn">
                        <button class="btn btn-sm btn-default" type="button" ng-search><i class="glyphicon glyphicon-search"></i></button>
                      </span>
                </div>
            </div>
            <div class="col-sm-5">
                <div class="pull-right">
                    <a ui-sref="mscUsersTree.orgAdd({pid:'${param.id}'})" title="<fmt:message key="mscUsersTree.orgAdd"/>"><span class="font-bold" ><i class="users-company-add"></i></span>  </a>
                    <a ui-sref="mscUsersTree.jobAdd({pid:'${param.id}'})" title="<fmt:message key="mscUsersTree.jobAdd"/>"><span class="font-bold" ><i class="users-job-add"></i></span>  </a>
                    <a ui-sref="mscUsersTree.personAdd({pid:'${param.id}'})" title="<fmt:message key="mscUsersTree.personAdd"/>"><span class="font-bold" ><i class="users-person-add"></i></span>  </a>

                </div>

            </div>
        </div>
        <div class="table-responsive">
            <table class="table table-striped b-t b-light">
                <thead>
                <tr>
                    <th style="width:20px;">
                        <label class="i-checks m-b-none">
                            <input type="checkbox"  ng-all-checkbox="mscUsersInfoIds"><i></i>
                        </label>
                    </th>

                    <th width="20px"><fmt:message key="label.serial.number"/></th>
                    
            <th><fmt:message key="mscUsersInfo.lbName"/></th>
            <th><fmt:message key="mscUsersInfo.lbType"/></th>
            <th><fmt:message key="mscUsersInfo.lbOrder"/></th>
            <th><fmt:message key="mscUsersInfo.lbCreateTime"/></th>
                    <th ><fmt:message key="label.operation"/></th>
                </tr>
                </thead>
                <tbody>

                    <c:forEach items="${pageInfoList.list}" var="mscUsersInfo" varStatus="status">

                        
     <tr>
            <td><label class="i-checks m-b-none"><input type="checkbox"  name="mscUsersInfoIds" ng-checkbox value="${mscUsersInfo.pkId}"><i></i></label></td>
            <td>${status.index+1}</td>
            <td>
                <c:if test="${mscUsersInfo.lbType=='1' }">
                    <i class="users-company"></i>
                </c:if>
                <c:if test="${mscUsersInfo.lbType=='2' }">
                    <i class="users-dept"></i>
                </c:if>
                <c:if test="${mscUsersInfo.lbType=='3' }">
                    <i class="users-job"></i>
                </c:if>
                <c:if test="${mscUsersInfo.lbType=='4' }">
                    <i class="users-person-man"></i>
                </c:if>
            ${mscUsersInfo.lbName} </td>
            <td>${mscUsersInfo.lbType} </td>
            <td>${mscUsersInfo.lbOrder} </td>
            <td>${mscUsersInfo.lbCreateTime} </td>
            <td>
                <c:if test="${mscUsersInfo.lbType=='1' || mscUsersInfo.lbType=='2' }">
                <a ui-sref="mscUsersTree.orgLook({bid:'${mscUsersInfo.pkId}'})"><span class="font-bold" ><fmt:message key="button.view"/></span>  </a>

                </c:if>
                <c:if test="${mscUsersInfo.lbType=='3' }">
                    <a ui-sref="mscUsersTree.jobLook({bid:'${mscUsersInfo.pkId}'})"><span class="font-bold" ><fmt:message key="button.view"/></span>  </a>

                </c:if>
                <c:if test="${mscUsersInfo.lbType=='4' }">
                    <a ui-sref="mscUsersTree.personLook({bid:'${mscUsersInfo.pkId}'})"><span class="font-bold" ><fmt:message key="button.view"/></span>  </a>

                </c:if>
            </td>
     </tr>
                    </c:forEach>

                </tbody>
            </table>
        </div>
        <%@include file="/common/jsp/page_bottom.jsp"%>
    </div>
</div>
