<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html lang="en">
<head>
    <title>家庭成员基本信息</title>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/bootstrap-v3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/style/standard/css/eccrm-common-new.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/zTree/css/ztree.css">
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-ztree-all.js"></script>
    <script>
        window.angular.contextPathURL = '<%=contextPath%>';
    </script>
</head>
<body>
<div class="main " ng-app="drug.relation.list" ng-controller="Ctrl">
    <input type="hidden" id="id" value="${id}"/>
    <div class="list-result ">
        <div class="block">
            <div class="block-header">
                <div class="header-text">
                    <span class="glyphicons list"></span>
                    <span>家庭成员基本信息</span>
                </div>
                <span class="header-button">
                        <a type="button" class="btn btn-green btn-min" ng-click="addEntering();">
                            <span class="glyphicons plus"></span> 新建
                        </a>
                </span>
            </div>
            <div class="block-content">
                <div class="content-wrap">
                    <div class="table-responsive panel panel-table">
                        <table class="table table-striped table-hover">
                            <thead class="table-header">
                            <tr>
                                <td>头像</td>
                                <td>姓名</td>
                                <td>性别</td>
                                <td>身份证号</td>
                                <td>与户主关系</td>
                                <td>文化程度</td>
                                <td>务工状态</td>
                                <td>务工地点</td>
                                <td>是否涉毒</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody class="table-body">
                            <tr ng-show="!beans || !beans.total">
                                <td colspan="10" class="text-center">没有查询到数据！</td>
                            </tr>
                            <tr bindonce ng-repeat="foo in beans.data" ng-cloak>
                                <td>
                                    <img style="width: 50px;height: 50px;" ng-src="/attachment/download?id={{foo.icon}}">
                                </td>
                                <td title="点击查询明细！" style="cursor: pointer;">
                                    <a ng-click="view(foo.id)" bo-text="foo.name"></a>
                                </td>
                                <td bo-text="foo.sex"></td>
                                <td bo-text="foo.idCard"></td>
                                <td bo-text="foo.relation"></td>
                                <td bo-text="foo.degrees"></td>
                                <td bo-text="foo.workStatus"></td>
                                <td bo-text="foo.workAdress"></td>
                                <td bo-text="foo.isDrugs"></td>
                                <td>
                                   <%-- <a class="btn-op blue" ng-click="modify(foo.id);">编辑</a>--%>
                                    <a class="btn-op red" ng-click="remove(foo.id);">解除关系</a>
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
<script type="text/javascript" src="<%=contextPath%>/app/drug/relation/relation.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/drug/user/user-modal.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/drug/relation/list/relation_list.js"></script>
</html>