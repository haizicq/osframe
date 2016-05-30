<%--
  Created by IntelliJ IDEA.
  User: wangchun
  Date: 15/9/21
  Time: 下午10:54
  To change this template use File | Settings | File Templates.
--%>
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

            <li ui-sref-active="active">
                <a ui-sref="mscUsersTree">
                    <i class="glyphicon glyphicon-chevron-right icon text-info-dker"></i>
                    <span class="font-bold" >
                         <fmt:message key="mscUsersOrg.module"/>
                    </span>
                 </a>
            </li>


                <li ui-sref-active="active">
                    <a ui-sref="mscUsersGroupList">
                        <i class="glyphicon glyphicon-chevron-right icon text-info-dker"></i>
                    <span class="font-bold" >
                         <fmt:message key="mscUsersGroup.module"/>
                    </span>
                    </a>
                </li>
                <li ui-sref-active="active">
                    <a ui-sref="mscUsersGroupJobList">
                        <i class="glyphicon glyphicon-chevron-right icon text-info-dker"></i>
                    <span class="font-bold" >
                         <fmt:message key="mscUsersGroupJob.module"/>
                    </span>
                    </a>
                </li>
            <li ui-sref-active="active">
                <a ui-sref="mscUsersJobTypeList">
                    <i class="glyphicon glyphicon-chevron-right icon text-info-dker"></i>
                    <span class="font-bold" >
                         <fmt:message key="mscUsersJobType.module"/>
                    </span>
                 </a>
            </li>
            <li ui-sref-active="active">
                <a ui-sref="mscUsersGroupTypeList">
                    <i class="glyphicon glyphicon-chevron-right icon text-info-dker"></i>
                    <span class="font-bold" >
                         <fmt:message key="mscUsersGroupType.module"/>
                    </span>
                 </a>
            </li>

            </ul>
            <!-- / third -->
        </nav>
        <!-- nav -->


    </div>
</div>
