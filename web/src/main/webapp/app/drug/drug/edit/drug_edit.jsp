<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en" >

<head >
    <title>编辑涉毒人员</title>
    <meta content="text/html" charset="utf-8" >
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/bootstrap-v3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/style/standard/css/eccrm-common-new.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/zTree/css/ztree.css">
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-ztree-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/vendor/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/app/base/org/org.js"></script>
    <script type="text/javascript">
        window.angular.contextPathURL = "<%=contextPath%>";
    </script>
</head>
<body>
<div class="main" ng-app="drug.drug.edit" ng-controller="Ctrl">
    <div class="block">
        <div class="block-header">
                <span class="header-text">
                    <span class="glyphicons info-sign"></span>
                </span>
                <span class="header-button">
                    <c:if test="${pageType eq 'add'}">
                    <button type="button" class="btn btn-green btn-min" ng-click="save()" ng-disabled="form.$invalid">
                        <span class="glyphicons disk_save"></span> 保存
                    </button>
                        <button type="button" class="btn btn-green btn-min" ng-click="save(true)"
                                ng-disabled="form.$invalid">
                            <span class="glyphicons disk_open"></span> 保存并新建
                        </button>
                    </c:if>
                    <c:if test="${pageType eq 'modify'}">
                    <button type="button" class="btn btn-green btn-min" ng-click="update()" ng-disabled="form.$invalid">
                        <span class="glyphicons claw_hammer"></span> 更新
                    </button>
                    </c:if>
                    <a type="button" class="btn btn-green btn-min" ng-click="back();">
                        <span class="glyphicons message_forward"></span> 返回
                    </a>
                </span>
        </div>
        <div class="block-content">
            <div class="content-wrap">
                <form name="form" class="form-horizontal" role="form">
                    <div style="display: none;">
                        <input type="hidden" id="pageType" value="${pageType}"/>
                        <input type="hidden" id="id" value="${id}"/>
                    </div>
                    <div class="row">
                        <div class="col-7">
                            <div class="block-header" style="background-color: transparent;border-width: 0px;">
                                <span class="header-text">
                                     <span class="glyphicons info-sign"> </span>
                                     <span>基本信息</span>
                                </span>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>姓名:</label>
                        </div>
                        <input class="col-3-half" type="text" ng-disabled="true" ng-model="beans.user.name"
                               maxlength="10"/>
                        <div class="form-label col-1-half">
                            <label>性别:</label>
                        </div>
                        <select ng-model="beans.user.sex" class="col-3-half" ng-disabled="true"
                                ng-options="foo.value as foo.name for foo in sex">
                        </select>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>民族:</label>
                        </div>
                        <select ng-model="beans.user.nation" class="col-3-half" ng-disabled="true"
                                ng-options="foo.value as foo.name for foo in nation">
                        </select>
                        <div class="form-label col-1-half">
                            <label>身份证号:</label>
                        </div>
                        <input class="col-3-half" type="text" ng-disabled="true" ng-model="beans.user.idCard"
                               maxlength="18"
                               oninput="if(value.length>18)value=value.slice(0,18)"/>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>所属地区:</label>
                        </div>
                        <div class="col-3-half">
                            <input class="col-12" type="text" ng-disabled="true" ng-model="beans.user.orgName"
                                   readonly ztree-single="orgTree"/>
                        </div>
                        <div class="form-label col-1-half">
                            <label>家庭详细地址:</label>
                        </div>
                        <input class="col-3-half" type="text" ng-disabled="true" ng-model="beans.user.home"
                               maxlength="50"/>

                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>联系方式:</label>
                        </div>
                        <input class="col-3-half" type="text" ng-disabled="true" ng-model="beans.user.phone"
                               maxlength="11" validate-msg="手机号码格式不正确"/>
                        <div class="form-label col-1-half">
                            <label>别名:</label>
                        </div>
                        <input class="col-3-half" type="text" ng-model="beans.drug.userName" maxlength="20"/>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>出生日期:</label>
                        </div>
                        <div class="col-3-half">
                            <input type="text" class="col-12" ng-model="beans.drug.brithDate" id="brithDate"
                                   eccrm-my97="{el:'brithDate',dateFmt:'yyyy-MM-dd HH:mm:ss'}"
                                   readonly/>
                            <span class="add-on"><i class="icons icon cp fork" ng-click="clearDate(0);"
                                                    title="清除"></i></span>
                        </div>
                        <div class="form-label col-1-half">
                            <label>人员现状:</label>
                        </div>
                        <input class="col-3-half" type="text" ng-model="beans.drug.userStatus" maxlength="50"/>
                    </div>

                    <div class="row">
                        <div class="col-7">
                            <div class="block-header" style="background-color: transparent;border-width: 0px;">
                                <span class="header-text">
                                     <span class="glyphicons info-sign"> </span>
                                     <span>涉毒类型与种类</span>
                                </span>
                            </div>
                        </div>
                    </div>
                    <hr>

                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>涉毒类型:</label>
                        </div>
                        <select ng-model="beans.drug.drugType" class="col-3-half"
                                ng-options="foo.value as foo.name for foo in drugType">
                        </select>
                        <div class="form-label col-1-half">
                            <label>涉毒种类:</label>
                        </div>
                        <select ng-model="beans.drug.drugSorts" class="col-3-half"
                                ng-options="foo.value as foo.name for foo in checkboxData">
                        </select>
                    </div>

                    <div class="row">
                        <div class="col-7">
                            <div class="block-header" style="background-color: transparent;border-width: 0px;">
                                <span class="header-text">
                                     <span class="glyphicons info-sign"> </span>
                                     <span>查获信息</span>
                                </span>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>初次查获时间:</label>
                        </div>
                        <div class="col-3-half">
                            <input type="text" class="col-12" ng-model="beans.drug.firstTime" id="firstTime"
                                   eccrm-my97="{el:'firstTime',dateFmt:'yyyy-MM-dd HH:mm:ss'}"
                                   readonly/>
                            <span class="add-on"><i class="icons icon cp fork" ng-click="clearDate(1);"
                                                    title="清除"></i></span>
                        </div>
                        <div class="form-label col-1-half">
                            <label>查获地区:</label>
                        </div>
                        <input class="col-3-half" type="text" ng-model="beans.drug.drugArea" maxlength="50"/>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>查获地址:</label>
                        </div>
                        <input class="col-3-half" type="text" ng-model="beans.drug.drugAdress" maxlength="50"/>

                        <div class="form-label col-1-half">
                            <label>查获单位:</label>
                        </div>
                        <input class="col-3-half" type="text" ng-model="beans.drug.drugCompany" maxlength="50"/>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>处置时间:</label>
                        </div>
                        <div class="col-3-half">
                            <input type="text" class="col-12" ng-model="beans.drug.dealDate" id="dealDate"
                                   eccrm-my97="{el:'dealDate',dateFmt:'yyyy-MM-dd HH:mm:ss'}"
                                   readonly/>
                            <span class="add-on"><i class="icons icon cp fork" ng-click="clearDate(2);"
                                                    title="清除"></i></span>
                        </div>
                        <div class="form-label col-1-half">
                            <label>处置情况:</label>
                        </div>
                        <input class="col-3-half" type="text" ng-model="beans.drug.deal" maxlength="50"/>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>违法事实:</label>
                        </div>
                        <textarea class="col-8-half" rows="3" ng-model="beans.drug.illegalFacts" maxlength="255"></textarea>
                    </div>

                    <div class="row">
                        <div class="col-7">
                            <div class="block-header" style="background-color: transparent;border-width: 0px;">
                                <span class="header-text">
                                     <span class="glyphicons info-sign"> </span>
                                     <span>管控类别</span>
                                </span>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>吸毒管控类型:</label>
                        </div>
                        <select ng-model="beans.drug.controlXType" class="col-3-half"
                                ng-options="foo.value as foo.name for foo in controlXType">
                        </select>
                        <div class="form-label col-1-half">
                            <label>贩毒管控类型:</label>
                        </div>
                        <select ng-model="beans.drug.controlFType" class="col-3-half"
                                ng-options="foo.value as foo.name for foo in controlFType">
                        </select>
                    </div>
                    <div class="row">
                        <div class="col-7">
                            <div class="block-header" style="background-color: transparent;border-width: 0px;">
                                <span class="header-text">
                                     <span class="glyphicons info-sign"> </span>
                                     <span>帮教信息</span>
                                </span>
                            </div>
                        </div>
                    </div>
                    <hr>
                    <div class="row" style="margin-top: 20px;">
                        <div class="form-label col-1-half">
                            <label>帮教信息:</label>
                        </div>
                        <button type="button" class="btn btn-danger" ng-click="pickEmp();">新增</button>
                    </div>
                    <div class="row" style="margin-left: 9%">
                        <div class="col-9-half">
                            <div class="table-responsive panel panel-table">
                                <table class="table table-striped table-hover">
                                    <thead class="table-header">
                                    <tr>
                                        <td>姓名</td>
                                        <td>单位名称</td>
                                        <td>职务</td>
                                        <td>联系方式</td>
                                        <td>操作</td>
                                    </tr>
                                    </thead>
                                    <tbody class="table-body">
                                    <tr ng-show="!drugHelp">
                                        <td colspan="5" class="text-center">没有设置数据！</td>
                                    </tr>
                                    <tr ng-show="drugHelp" ng-repeat="foo in drugHelp">
                                        <td>
                                            {{foo.helpName}}
                                        </td>
                                        <td>{{foo.helpCopmany}}</td>
                                        <td>{{foo.helpPosition}}</td>
                                        <td>{{foo.helpPhone}}</td>
                                        <td>
                                            <a class="btn-op blue" ng-click="modify($index);">编辑</a>
                                            <a class="btn-op red" ng-click="remove($index);">删除</a>
                                        </td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>帮扶情况:</label>
                        </div>
                        <textarea disabled class="col-8-half" rows="3" ng-model="beans.drug.helpStatus" maxlength="255"></textarea>
                    </div>
                    <br>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/app/drug/drug/drug.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/app/drug/drug/edit/drug_edit.js" ></script>
</html >