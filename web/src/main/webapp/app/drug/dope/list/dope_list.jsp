<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html lang="en">
<head>
    <title>贩毒可疑人员管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/bootstrap-v3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/style/standard/css/eccrm-common-new.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/zTree/css/ztree.css">
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-ztree-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-upload.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/app/base/org/org.js"></script>
    <script>
        window.angular.contextPathURL = '<%=contextPath%>';
    </script>
</head>
<body>
<div class="main condition-row-1" ng-app="drug.dope.list" ng-controller="Ctrl">
    <div class="list-condition">
        <div class="block">
            <div class="block-header">
                <span class="header-text">
                    <span class="glyphicons search"></span>
                </span>
                <span class="header-button">
                        <a type="button" class="btn btn-green btn-min" ng-click="query();">
                                <span class="glyphicons search"></span>
                                查询
                        </a>
                </span>
            </div>
            <div class="block-content">
                <div class="content-wrap">
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>所属地区:</label>
                        </div>
                        <div class="col-2-half">
                            <input class="col-12" type="text" ng-model="condition.orgName"
                                   readonly ztree-single="orgTree"/>
                            <span class="add-on"><i class="icons icon cp fork" ng-click="clearOrg();"
                                                    title="清除"></i></span>
                        </div>
                        <div class="form-label col-1-half">
                            <label>姓名:</label>
                        </div>
                        <input class="col-2-half" type="text" ng-model="condition.name"/>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="list-result ">
        <div class="block">
            <div class="block-header">
                <div class="header-text">
                    <span class="glyphicons list"></span>
                    <span>贩毒可疑人员管理</span>
                </div>
                <span class="header-button">
                        <%--<a type="button" class="btn btn-green btn-min" ng-click="add();">--%>
                            <%--<span class="glyphicons plus"></span> 新建--%>
                        <%--</a>--%>
                    <a type="button" class="btn btn-green btn-min" ng-click="exportData();">
                        <span class="glyphicons plus"></span> 贩毒可疑人员导出
                    </a>
                    <a type="button" class="btn btn-green btn-min" ng-click="remove();" ng-disabled="!anyone"
                       disabled="disabled">
                        <span class="glyphicons plus"></span> 删除
                    </a>
                </span>
            </div>
            <div class="block-content">
                <div class="content-wrap">
                    <div class="table-responsive panel panel-table">
                        <table class="table table-striped table-hover">
                            <thead class="table-header">
                            <tr>
                                <td class="width-min">
                                    <div select-all-checkbox checkboxes="beans.data" selected-items="items"
                                         anyone-selected="anyone"></div>
                                </td>
                                <td>姓名</td>
                                <td>所属地区</td>
                                <td>性别</td>
                                <td>民族</td>
                                <td>是否有前科</td>
                                <td>家庭住址</td>
                                <td>创建时间</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody class="table-body">
                            <tr ng-show="!beans || !beans.total">
                                <td colspan="9" class="text-center">没有查询到数据！</td>
                            </tr>
                            <tr bindonce ng-repeat="foo in beans.data" ng-cloak>
                                <td><input type="checkbox" ng-model="foo.isSelected"/></td>
                                <td title="点击查询明细！" style="cursor: pointer;">
                                    <a ng-click="view(foo.dope.id)" bo-text="foo.user.name"></a>
                                </td>
                                <td bo-text="foo.user.orgName"></td>
                                <td bo-text="foo.user.sex"></td>
                                <td bo-text="foo.user.nation"></td>
                                <td ng-if="foo.dope.record==1">否</td>
                                <td ng-if="foo.dope.record==2">是</td>
                                <td bo-text="foo.user.home"></td>
                                <td bo-text="foo.dope.createdDatetime |eccrmDate"></td>
                                <td>
                                    <a class="btn-op blue" ng-click="modify(foo.dope.id);">编辑</a>
                                    <a class="btn-op red" ng-click="remove(foo.dope.id);">删除</a>
                                </td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="list-pagination" eccrm-page="pager"></div>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/app/drug/dope/dope.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/drug/dope/list/dope_list.js"></script>
</html>