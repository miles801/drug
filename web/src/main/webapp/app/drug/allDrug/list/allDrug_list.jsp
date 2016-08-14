<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html lang="en">
<head>
    <title>禁毒重点整治排查汇总</title>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/bootstrap-v3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/style/standard/css/eccrm-common-new.css">
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js"></script>
    <script>
        window.angular.contextPathURL = '<%=contextPath%>';
    </script>
</head>
<body>
<div class="main " ng-app="drug.allDrug.list" ng-controller="Ctrl">
    <div class="list-result ">
        <div class="block">
            <div class="block-header">
                  <span class="header-button">
                        <a type="button" class="btn btn-green btn-min" ng-click="print();">
                            <span class="glyphicons plus"></span> 打印
                        </a>
                  </span>
            </div>
            <div class="block-content">
                <div class="content-wrap">
                    <div class="table-responsive panel panel-table">
                        <table class="table table-striped table-hover">
                            <thead class="table-header">
                            <tr style="font-weight: bold">
                                <td>地区</td>
                                <td colspan="2">入户情况</td>
                                <td colspan="2">登记外出人员</td>
                                <td colspan="2">排查可疑人数</td>
                                <td colspan="2">排除涉毒打击人数</td>
                                <td colspan="8">排除涉毒打击人数</td>
                            </tr>
                            <tr>
                                <td>村社</td>
                                <td>总户数</td>
                                <td>已入户</td>
                                <td>外出省外</td>
                                <td>外出州外省内</td>
                                <td>贩毒可疑数</td>
                                <td>吸毒可疑数</td>
                                <td>服刑数</td>
                                <td>刑满释放数</td>
                                <td>强制戒毒数</td>
                               <%-- <td>正在服刑数</td>--%>
                                <td>药物治疗数</td>
                                <td>看守所羁押数</td>
                                <td>戒断三年数</td>
                                <td>社区戒毒数</td>
                                <td>社区康复数</td>
                                <td>失控数</td>
                                <td>死亡数</td>
                            </tr>
                            </thead>
                            <tbody class="table-body">
                            <tr ng-show="!beans || !beans.total">
                                <td colspan="18" class="text-center">没有查询到数据！</td>
                            </tr>
                            <tr bindonce ng-repeat="foo in beans.data" ng-cloak>
                                <td bo-text="foo.orgName"></td>
                                <td bo-text="foo.coutParent"></td>
                                <td bo-text="foo.countPeople"></td>
                                <td bo-text="foo.outOfSheng"></td>
                                <td bo-text="foo.inOfSheng"></td>
                                <td bo-text="foo.fDrug"></td>
                                <td bo-text="foo.xDrug"></td>
                                <td bo-text="foo.prison"></td>
                                <td bo-text="foo.released"></td>
                                <td bo-text="foo.jiedu"></td>
                                <td bo-text="foo.yaowu"></td>
                                <td bo-text="foo.jiya"></td>
                                <td bo-text="foo.jieduan"></td>
                                <td bo-text="foo.shequjiedu"></td>
                                <td bo-text="foo.shequkangfu"></td>
                                <td bo-text="foo.outOfControl"></td>
                                <td bo-text="foo.died"></td>

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
<script type="text/javascript" src="<%=contextPath%>/app/drug/allDrug/allDrug.js"></script>
<script type="text/javascript" src="<%=contextPath%>/app/drug/allDrug/list/allDrug_list.js"></script>
</html>