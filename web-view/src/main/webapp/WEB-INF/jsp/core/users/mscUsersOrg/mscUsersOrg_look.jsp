<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md">
    <div ng-controller="mscUsersOrgLookCtrl">
        <form name="mscUsersOrgForm" class="form-validation">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-book"></i>
                    <span class="h4"><fmt:message key="mscUsersOrg.module"/></span>
                </div>
                <div class="panel-body">
                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbName"/></label>
                            <label class="form-control">${mscUsersOrg.lbUsersInfo.lbName}</label>
                        </div>
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbKey"/></label>
                            <label class="form-control">${mscUsersOrg.lbUsersInfo.lbKey}</label>
                        </div>
                    </div>
                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbNo"/></label>
                            <label class="form-control">${mscUsersOrg.lbUsersInfo.lbNo}</label>
                        </div>
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbOrder"/></label>
                            <label class="form-control">${mscUsersOrg.lbUsersInfo.lbOrder}</label>
                        </div>
                    </div>

                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbIsVirtual"/></label>
                            <label class="form-control">${mscUsersOrg.lbUsersInfo.lbIsVirtual}</label>
                        </div>
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbIsValid"/></label>
                            <label class="form-control">${mscUsersOrg.lbUsersInfo.lbIsValid}</label>
                        </div>
                    </div>
                    <div class="form-group pull-in clearfix">

                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbParent"/></label>
                            <label class="form-control">${mscUsersOrg.lbUsersInfo.lbParent.lbName}</label>
                        </div>

                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbInfo"/></label>
                            <label class="form-control">${mscUsersOrg.lbUsersInfo.lbInfo}</label>
                        </div>
                    </div>


                    <div class="form-group pull-in clearfix">

                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbCreateTime"/></label>
                            <label class="form-control">${mscUsersOrg.lbUsersInfo.lbCreateTime}</label>
                        </div>
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbUpdateTime"/></label>
                            <label class="form-control">${mscUsersOrg.lbUsersInfo.lbUpdateTime}</label>
                        </div>

                    </div>
                    
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersInfo.lbType"/></label>
                <label class="form-control">${mscUsersOrg.lbUsersInfo.lbType}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersOrg.lbLeader"/></label>
                <label class="form-control">${mscUsersOrg.lbLeader.lbName}</label>
            </div>
            </div>

                    <div class="form-group align-center">
                        <bs:role controller="MscUsersOrgController" function="delete" >
                            <button type="button" class="btn btn-default" ng-delete="mscUsersOrg({id:'${mscUsersOrg.pkId}'})" ><fmt:message key="button.delete"/></button>
                        </bs:role>
                        <bs:role controller="MscUsersOrgController" function="edit" >
                            <button type="button" class="btn btn-default "  ui-sref="mscUsersOrgEdit({id:'${mscUsersOrg.pkId}'})"><span class="font-bold" ><fmt:message key="button.edit"/></span>  </button>
                        </bs:role>
                        <button type="button" class="btn btn-default" ng-return><fmt:message key="button.return"/></button>

                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
