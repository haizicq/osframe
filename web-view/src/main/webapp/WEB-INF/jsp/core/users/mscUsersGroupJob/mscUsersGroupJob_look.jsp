<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md">
    <div ng-controller="mscUsersGroupJobLookCtrl">
        <form name="mscUsersGroupJobForm" class="form-validation">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-book"></i>
                    <span class="h4"><fmt:message key="mscUsersGroupJob.module"/></span>
                </div>
                <div class="panel-body">

                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbName"/></label>
                            <label class="form-control">${mscUsersGroupJob.lbUsersInfo.lbName}</label>
                        </div>
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbKey"/></label>
                            <label class="form-control">${mscUsersGroupJob.lbUsersInfo.lbKey}</label>
                        </div>
                    </div>
                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbNo"/></label>
                            <label class="form-control">${mscUsersGroupJob.lbUsersInfo.lbNo}</label>
                        </div>
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbOrder"/></label>
                            <label class="form-control">${mscUsersGroupJob.lbUsersInfo.lbOrder}</label>
                        </div>
                    </div>

                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbIsVirtual"/></label>
                            <label class="form-control">${mscUsersGroupJob.lbUsersInfo.lbIsVirtual}</label>
                        </div>
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbIsValid"/></label>
                            <label class="form-control">${mscUsersGroupJob.lbUsersInfo.lbIsValid}</label>
                        </div>
                    </div>
                    <div class="form-group pull-in clearfix">

                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbParent"/></label>
                            <label class="form-control">${mscUsersGroupJob.lbUsersInfo.lbParent.lbName}</label>
                        </div>

                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbInfo"/></label>
                            <label class="form-control">${mscUsersGroupJob.lbUsersInfo.lbInfo}</label>
                        </div>
                    </div>


                    <div class="form-group pull-in clearfix">

                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbCreateTime"/></label>
                            <label class="form-control">${mscUsersGroupJob.lbUsersInfo.lbCreateTime}</label>
                        </div>
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbUpdateTime"/></label>
                            <label class="form-control">${mscUsersGroupJob.lbUsersInfo.lbUpdateTime}</label>
                        </div>

                    </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersGroupJob.lbLevel"/></label>
                <label class="form-control">${mscUsersGroupJob.lbLevel}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersGroupJob.lbUsersInfo"/></label>
                <label class="form-control">${mscUsersGroupJob.lbUsersInfo.lbName}</label>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersGroupJob.lbPersonList"/></label>
                <label class="form-control">${mscUsersGroupJob.lbPersonList.lbName}</label>
            </div>
                <div class="col-sm-6"></div>
            </div>

                    <div class="form-group align-center">
                        <bs:role controller="MscUsersGroupJobController" function="delete" >
                            <button type="button" class="btn btn-default" ng-delete="mscUsersGroupJob({id:'${mscUsersGroupJob.pkId}'})" ><fmt:message key="button.delete"/></button>
                        </bs:role>
                        <bs:role controller="MscUsersGroupJobController" function="edit" >
                            <button type="button" class="btn btn-default "  ui-sref="mscUsersGroupJobEdit({id:'${mscUsersGroupJob.pkId}'})"><span class="font-bold" ><fmt:message key="button.edit"/></span>  </button>
                        </bs:role>
                        <button type="button" class="btn btn-default" ng-return><fmt:message key="button.return"/></button>

                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
