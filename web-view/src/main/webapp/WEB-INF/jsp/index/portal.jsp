<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>
<div class="wrapper-md"  ng-controller="portalCtrl">
  <div class="row">
    <div class="col-sm-12 connected" ui-jq="sortable" ui-options="{items:'.panel', handle:'.panel-heading', connectWith:'.connected'}">
      <div class="panel ">
        <div class="list-group bg-white">
          <!-- 滚动图片 -->
            <carousel interval="myInterval">
              <slide ng-repeat="slide in slides" active="slide.active" >
                <img ng-src="{{slide.image}}"   style="width:100%;height: 230px">
              </slide>
            </carousel>
          <!-- / 滚动图片 -->
        </div>
      </div>
    </div>
  </div>
  <div class="row">
    <div class="col-sm-6 connected" ui-jq="sortable" ui-options="{items:'.panel', handle:'.panel-heading', connectWith:'.connected'}">
      <div class="panel  panel-info">

        <div class="list-group bg-white">
          {{userName}}，欢迎你。<br>
          {{nowDate}}
        </div>
      </div>
      <div class="panel panel-default">
        <div class="panel-heading">
          最新DEMO数据
        </div>
        <div class="panel-body" ng-if="demoStatus==0">
          {{demoMessage}}
        </div>
        <div class="panel-body" ng-if="demoStatus==1">
          <article class="media" ng-repeat="item in demoData" >
            <div class="media-body">
              <a href="${rootPath}/os/demo/index.html#/osDemoInfoLook?id={{item.pkId}}" target="_blank" class="h4">{{item.lbName}}</a>
            </div>
          </article>

        </div>
      </div>
    </div>
    <div class="col-sm-6 connected" ui-jq="sortable" ui-options="{items:'.panel', handle:'.panel-heading', connectWith:'.connected'}">
      <div class="panel panel-default">
        <div class="panel-heading">
          快速链接
        </div>
        <div class="list-group bg-white">
          <a href="javascript:window.open(G.path.RootPath+'/msc/users/index.html','_blank')" class="list-group-item">
            <i class="fa fa-fw fa-envelope"></i> 用户管理
          </a>
          <a href="javascript:window.open(G.path.RootPath+'/msc/auth/index.html','_blank')" class="list-group-item">
            <i class="fa fa-fw fa-eye"></i> 权限管理
          </a>
          <a href class="list-group-item">
            <i class="fa fa-fw fa-phone"></i> DEMO
          </a>
        </div>
      </div>

    </div>
  </div>
</div>
