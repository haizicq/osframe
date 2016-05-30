<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/jsp/global.jsp"%>

<div class="wrapper-md">
    <div ng-controller="mscUsersPersonLookCtrl">
        <form name="mscUsersPersonForm" class="form-validation">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="glyphicon glyphicon-book"></i>
                    <span class="h4"><fmt:message key="mscUsersPerson.module"/></span>
                </div>
                <div class="panel-body">
                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbName"/></label>
                            <label class="form-control">${mscUsersPerson.lbUsersInfo.lbName}</label>
                        </div>
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbKey"/></label>
                            <label class="form-control">${mscUsersPerson.lbUsersInfo.lbKey}</label>
                        </div>
                    </div>
                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbNo"/></label>
                            <label class="form-control">${mscUsersPerson.lbUsersInfo.lbNo}</label>
                        </div>
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbOrder"/></label>
                            <label class="form-control">${mscUsersPerson.lbUsersInfo.lbOrder}</label>
                        </div>
                    </div>

                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersPerson.lbSex"/></label>
                            <label class="form-control">${mscUsersPerson.lbSex}</label>
                        </div>
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersPerson.lbBirthday"/></label>
                            <label class="form-control">${mscUsersPerson.lbBirthday}</label>
                        </div>
                    </div>

                    <div class="form-group pull-in clearfix">
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbIsVirtual"/></label>
                            <label class="form-control">
                                <bs:enums name="formData['lbUsersInfo.lbIsVirtual']" value="${mscUsersPerson.lbUsersInfo.lbIsVirtual}" key="enumsCommonOrNot" display="label" />
                            </label>
                        </div>
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbIsValid"/></label>
                            <label class="form-control">
                                <bs:enums name="formData['lbUsersInfo.lbIsValid']" value="${mscUsersPerson.lbUsersInfo.lbIsValid}" key="enumsCommonOrNot" display="label" />
                            </label>
                        </div>
                    </div>
                    <div class="form-group pull-in clearfix">

                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbParent"/></label>
                            <label class="form-control">${mscUsersPerson.lbUsersInfo.lbParent.lbName}</label>
                        </div>

                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbInfo"/></label>
                            <label class="form-control">${mscUsersPerson.lbUsersInfo.lbInfo}</label>
                        </div>
                    </div>


                    <div class="form-group pull-in clearfix">

                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbCreateTime"/></label>
                            <label class="form-control">${mscUsersPerson.lbUsersInfo.lbCreateTime}</label>
                        </div>
                        <div class="col-sm-6">
                            <label><fmt:message key="mscUsersInfo.lbUpdateTime"/></label>
                            <label class="form-control">${mscUsersPerson.lbUsersInfo.lbUpdateTime}</label>
                        </div>

                    </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersPerson.lbLoginName"/></label>
                <label class="form-control">${mscUsersPerson.lbLoginName}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersPerson.lbPwd"/></label>
                <label class="form-control">${mscUsersPerson.lbPwd}</label>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersPerson.lbMail"/></label>
                <label class="form-control">${mscUsersPerson.lbMail}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersPerson.lbPhone"/></label>
                <label class="form-control">${mscUsersPerson.lbPhone}</label>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersPerson.lbMobilePhone"/></label>
                <label class="form-control">${mscUsersPerson.lbMobilePhone}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersPerson.lbOthers"/></label>
                <label class="form-control">${mscUsersPerson.lbOthers}</label>
            </div>
            </div>
        <div class="form-group pull-in clearfix">
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersPerson.lbLanguage"/></label>
                <label class="form-control">&nbsp;${mscUsersPerson.lbLanguage}</label>
            </div>
             <div class="col-sm-6">
                <label><fmt:message key="mscUsersPerson.lbUsersInfo"/></label>
                <label class="form-control">${mscUsersPerson.lbUsersInfo.lbName}</label>
            </div>
            </div>

                    <div class="form-group align-center">
                        <bs:role controller="MscUsersPersonController" function="delete" >
                            <button type="button" class="btn btn-default" ng-delete="mscUsersPerson({id:'${mscUsersPerson.pkId}'})" ><fmt:message key="button.delete"/></button>
                        </bs:role>
                        <bs:role controller="MscUsersPersonController" function="edit" >
                            <button type="button" class="btn btn-default "  ui-sref="mscUsersPersonEdit({id:'${mscUsersPerson.pkId}'})"><span class="font-bold" ><fmt:message key="button.edit"/></span>  </button>
                        </bs:role>
                        <bs:role controller="MscUsersPersonController" function="rePwd" >
                            <button type="button" class="btn btn-default "  ui-sref="mscUsersTree.rePwd({id:'${mscUsersPerson.pkId}'})"><span class="font-bold" ><fmt:message key="mscUsersPerson.changeMyPwd"/></span>  </button>
                        </bs:role>

                        <button type="button" class="btn btn-default" ng-return><fmt:message key="button.return"/></button>

                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
