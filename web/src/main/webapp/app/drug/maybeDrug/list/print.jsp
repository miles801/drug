<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html lang="en" >
<head >
    <title>吸毒可疑人员管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8" />
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
</head >
<body >
<div class="main" ng-app="drug.maybeDrug.list" ng-controller="Ctrl">
    <div style="height: 100%;position: relative;overflow: hidden;">
        <div class="block">
            <div class="block-header">

            </div>
            <div class="block-content">
                <div class="content-wrap">
                    <div class="table-responsive panel panel-table">
                        <table class="table table-striped table-hover">
                            <thead class="table-header">
                            <tr>
                                <td>姓名</td>
                                <td>所属地区</td>
                                <td>性别</td>
                                <td>民族</td>
                                <td>是否有前科</td>
                                <td>家庭住址</td>
                                <td>创建时间</td>
                            </tr>
                            </thead>
                            <tbody class="table-body">
                            <tr ng-show="!beans || !beans.total">
                                <td colspan="7" class="text-center">没有查询到数据！</td>
                            </tr>
                            <tr bindonce ng-repeat="foo in beans.data" ng-cloak>
                                <td title="点击查询明细！" style="cursor: pointer;">
                                    <a ng-click="view(foo.id)" bo-text="foo.user.name"></a>
                                </td>
                                <td bo-text="foo.user.orgName"></td>
                                <td bo-text="foo.user.sex"></td>
                                <td bo-text="foo.user.nation"></td>
                                <td ng-if="foo.maybe.record==1">否</td>
                                <td ng-if="foo.maybe.record==2">是</td>
                                <td bo-text="foo.user.home"></td>
                                <td bo-text="foo.user.createdDatetime |eccrmDate"></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="list-pagination" eccrm-page="pager" ng-show="false"></div>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/app/drug/maybeDrug/maybeDrug.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/app/drug/maybeDrug/list/maybeDrug_list.js" ></script>
</html >