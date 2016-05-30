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
                <a ui-sref="osDemoInfoList">
                    <i class="glyphicon glyphicon-chevron-right icon text-info-dker"></i>
                    <span class="font-bold" >
                         <fmt:message key="osDemoInfo.module"/>
                    </span>
                 </a>
            </li>

            </ul>
            <!-- / third -->
        </nav>
        <!-- nav -->


    </div>
</div>
