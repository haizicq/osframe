<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md">
    <div ng-controller="mscUsersGroupLookCtrl">
        <form name="mscUsersGroupForm" class="form-validation">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-book"></i>
                    <span class="h4"><fmt:message key="mscUsersGroup.module"/></span>
                </div>
                <div class="panel-body">

                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbName"/></label>
                            <label class="form-control">${mscUsersGroup.lbUsersInfo.lbName}</label>
                        </div>
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbKey"/></label>
                            <label class="form-control">${mscUsersGroup.lbUsersInfo.lbKey}</label>
                        </div>
                    </div>
                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbNo"/></label>
                            <label class="form-control">${mscUsersGroup.lbUsersInfo.lbNo}</label>
                        </div>
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbOrder"/></label>
                            <label class="form-control">${mscUsersGroup.lbUsersInfo.lbOrder}</label>
                        </div>
                    </div>

                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbIsVirtual"/></label>
                            <label class="form-control">${mscUsersGroup.lbUsersInfo.lbIsVirtual}</label>
                        </div>
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbIsValid"/></label>
                            <label class="form-control">${mscUsersGroup.lbUsersInfo.lbIsValid}</label>
                        </div>
                    </div>
                    <div class="form-group pull-in clearfix">

                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbParent"/></label>
                            <label class="form-control">${mscUsersGroup.lbUsersInfo.lbParent.lbName}</label>
                        </div>

                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbInfo"/></label>
                            <label class="form-control">${mscUsersGroup.lbUsersInfo.lbInfo}</label>
                        </div>
                    </div>


                    <div class="form-group pull-in clearfix">

                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbCreateTime"/></label>
                            <label class="form-control">${mscUsersGroup.lbUsersInfo.lbCreateTime}</label>
                        </div>
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbUpdateTime"/></label>
                            <label class="form-control">${mscUsersGroup.lbUsersInfo.lbUpdateTime}</label>
                        </div>

                    </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersGroup.lbLeader"/></label>
                <label class="form-control">${mscUsersGroup.lbLeader.lbName}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersGroup.lbGroupType"/></label>
                <label class="form-control">${mscUsersGroup.lbGroupType.lbName}</label>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersGroup.lbUsersInfo"/></label>
                <label class="form-control">${mscUsersGroup.lbUsersInfo.lbName}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersGroup.lbPersonList"/></label>
                <label class="form-control">${mscUsersGroup.lbPersonList.lbName}</label>
            </div>
            </div>

                    <div class="form-group align-center">
                        <bs:role controller="MscUsersGroupController" function="delete" >
                            <button type="button" class="btn btn-default" ng-delete="mscUsersGroup({id:'${mscUsersGroup.pkId}'})" ><fmt:message key="button.delete"/></button>
                        </bs:role>
                        <bs:role controller="MscUsersGroupController" function="edit" >
                            <button type="button" class="btn btn-default "  ui-sref="mscUsersGroupEdit({id:'${mscUsersGroup.pkId}'})"><span class="font-bold" ><fmt:message key="button.edit"/></span>  </button>
                        </bs:role>
                        <button type="button" class="btn btn-default" ng-return><fmt:message key="button.return"/></button>

                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
