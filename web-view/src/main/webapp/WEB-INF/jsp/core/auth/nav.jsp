<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>
<div class="aside-wrap">
    <div class="navi-wrap">


        <!-- nav -->
        <nav ui-nav class="navi">
            <!-- first -->
            <ul class="nav">
                  <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                      <span >
                        <fmt:message key="label.nav"/>
                      </span>
                  </li>

                <li class="active">
                    <a  href class="auto">
                        <i class="glyphicon glyphicon-chevron-right icon text-info-dker"></i>
                    <span class="font-bold" >
                         <fmt:message key="mscAuthCategory.module"/>
                    </span>
                    </a>

                    <ul class="nav nav-sub dk">
                        <c:forEach items="${mscAuthCategoryList}" var="mscAuthCategory" varStatus="status">
                            <li ui-sref-active="active">
                                <a ui-sref="mscAuthRoleList({cid:'${mscAuthCategory.pkId}'})">
                                    <span>${mscAuthCategory.lbName}</span>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                    <b class="badge bg-info nav-tip-right" ui-sref="mscAuthCategoryList" >
                        <fmt:message key="button.admin"/>
                    </b>
                </li>
                <li >
                    <a >
                        <i class="glyphicon glyphicon-chevron-right icon text-info-dker"></i>
                        <span class="font-bold" >
                             <fmt:message key="mscAuthInfo.module"/>
                        </span>
                     </a>


                    <ul class="nav nav-sub dk">
                        <c:forEach items="${authModuleList}" var="authModule" varStatus="status">
                            <li ui-sref-active="active">
                                <a ui-sref="mscAuthInfoList({module:'${authModule.lbModuleKey}'})">
                                    <span>
                                        <bs:message key="${authModule.lbModule}"/>
                                    </span>
                                </a>
                            </li>
                        </c:forEach>
                    </ul>
                    <b class="badge bg-info nav-tip-right"  ui-sref="mscAuthInfoExport">
                        <fmt:message key="button.import"/>
                    </b>
                </li>


            </ul>
            <!-- / third -->
        </nav>
        <!-- nav -->


    </div>
</div>
