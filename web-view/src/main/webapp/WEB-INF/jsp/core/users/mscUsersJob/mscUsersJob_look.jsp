<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md">
    <div ng-controller="mscUsersJobLookCtrl">
        <form name="mscUsersJobForm" class="form-validation">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-book"></i>
                    <span class="h4"><fmt:message key="mscUsersJob.module"/></span>
                </div>
                <div class="panel-body">
                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbName"/></label>
                            <label class="form-control">${mscUsersJob.lbUsersInfo.lbName}</label>
                        </div>
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbKey"/></label>
                            <label class="form-control">${mscUsersJob.lbUsersInfo.lbKey}</label>
                        </div>
                    </div>
                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbNo"/></label>
                            <label class="form-control">${mscUsersJob.lbUsersInfo.lbNo}</label>
                        </div>
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbOrder"/></label>
                            <label class="form-control">${mscUsersJob.lbUsersInfo.lbOrder}</label>
                        </div>
                    </div>

                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbIsVirtual"/></label>
                            <label class="form-control">${mscUsersJob.lbUsersInfo.lbIsVirtual}</label>
                        </div>
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbIsValid"/></label>
                            <label class="form-control">${mscUsersJob.lbUsersInfo.lbIsValid}</label>
                        </div>
                    </div>
                    <div class="form-group pull-in clearfix">

                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbParent"/></label>
                            <label class="form-control">${mscUsersJob.lbUsersInfo.lbParent.lbName}</label>
                        </div>

                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbInfo"/></label>
                            <label class="form-control">${mscUsersJob.lbUsersInfo.lbInfo}</label>
                        </div>
                    </div>


                    <div class="form-group pull-in clearfix">

                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbCreateTime"/></label>
                            <label class="form-control">${mscUsersJob.lbUsersInfo.lbCreateTime}</label>
                        </div>
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbUpdateTime"/></label>
                            <label class="form-control">${mscUsersJob.lbUsersInfo.lbUpdateTime}</label>
                        </div>

                    </div>


                    <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersJob.lbLevel"/></label>
                <label class="form-control">${mscUsersJob.lbLevel}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersJob.lbJobType"/></label>
                <label class="form-control">${mscUsersJob.lbJobType.lbName}</label>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersJob.lbUsersInfo"/></label>
                <label class="form-control">${mscUsersJob.lbUsersInfo.lbName}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersJob.lbPersonList"/></label>
                <label class="form-control">${mscUsersJob.lbPersonListNames}</label>
            </div>
            </div>

                    <div class="form-group align-center">
                        <bs:role controller="MscUsersJobController" function="delete" >
                            <button type="button" class="btn btn-default" ng-delete="mscUsersJob({id:'${mscUsersJob.pkId}'})" ><fmt:message key="button.delete"/></button>
                        </bs:role>
                        <bs:role controller="MscUsersJobController" function="edit" >
                            <button type="button" class="btn btn-default "  ui-sref="mscUsersJobEdit({id:'${mscUsersJob.pkId}'})"><span class="font-bold" ><fmt:message key="button.edit"/></span>  </button>
                        </bs:role>
                        <button type="button" class="btn btn-default" ng-return><fmt:message key="button.return"/></button>

                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
