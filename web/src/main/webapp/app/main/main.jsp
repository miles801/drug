<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>东乡县赵家乡禁毒工作管理平台</title>
    <link rel="stylesheet" href="<%=contextPath%>/vendor/bootstrap-v3.0/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="<%=contextPath%>/style/standard/css/eccrm-common-new.css"/>
    <link rel="stylesheet" href="<%=contextPath%>/app/main/css/main.css"/>

    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js"></script>
    <script type="text/javascript" src="vendor/jquery-v1.8.3/jquery.md5.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/app/base/emp/emp.js"></script>
    <script>
        window.angular.contextPathURL = "<%=contextPath%>";
    </script>
    <style>
        .aside {
            top: inherit !important;
        }
    </style>
</head>

<body id="ng-app" ng-app="eccrm.main">
<div id="container" ng-controller="MainController">
    <input type="hidden" id="contextPath" value="<%=contextPath%>/"/>
    <div id="header">
        <div class="top">
            <div class="logo" style="top:-2px;"></div>
            <div class="tool" style="padding: 5px 10px;;">
                <c:if test="${sessionScope.get('employeeId') ne null}">
                    <a href="<%=contextPath%>/logout">
                        <img src="<%=contextPath%>/app/main/images/icon/h13.png" width="24" height="24" title="退出">
                    </a>
                    <a ng-click="updatePwd();">
                        <img src="<%=contextPath%>/app/main/images/icon/h7.png" width="24" height="24" title="更改密码">
                    </a>
                </c:if>
                <c:if test="${sessionScope.get('employeeId') eq null}">
                    <a ng-click="login();">
                        <img src="<%=contextPath%>/app/main/images/icon/h9.png" width="24" height="24" title="登录">
                    </a>
                </c:if>
            </div>
            <span style="font-size: 30px; position: absolute; left: 100px; color: #fff; top: 8px;">东乡县赵家乡禁毒工作管理平台</span>
        </div>
    </div>
    <div id="main">
        <div class="leftbar">
            <div class="LB_container">
                <a title="首页" ng-click="showHome();" class="current">
                    <img src="<%=contextPath%>/app/main/images/home.png" alt="首页"/>
                </a>
                <a bindonce bo-title="menu.name" ng-repeat="menu in menus" ng-repeat-finish
                   ng-click="showChildren(menu);">
                    <img ng-src="<%=contextPath%>/attachment/download?id={{menu.icon}}" ng-cloak/>
                </a>
            </div>
            <div class="btnT dn"></div>
            <div class="btnB dn"></div>
        </div>
        <div class="mainRight">
            <div id="accordian" class="dn">
                <ul>
                    <li bindonce ng-class="{'current':$parent.currentId==level1.id}" ng-repeat="level1 in subMenus"
                        ng-repeat-finish="subFinish">
                        <h3 nav-click-slide=".nav_menus">
							<span class="menu-text">
                                <i class="icons-sj"></i>
								<a ng-click="addTab(level1.name,level1.url,level1,level1.id)" bo-text="level1.name"></a>
							</span>
							<span class="menu-children" bo-show="level1.children.length>0">
                                    <span class="icon-down">&#9660;</span>
							</span>
                        </h3>
                        <ul class="nav_menus">
                            <li bindonce ng-class="{'current':$parent.$parent.currentId==level2.id}"
                                ng-repeat="level2 in level1.children" bindonce>
                                <div bo-if="level2.children && level2.children.length>0">
                                    <a nav-click-slide="div" style="cursor: pointer;"
                                       ng-click="addTab(level2.name,level2.url,level2,level2.id)">
                                        <span bo-text="level2.name" class="menu-text"></span>
										<span class="menu-children">
											<span style="color:#1893dd;">&#9660;</span>
										</span>
                                    </a>

                                    <div style="margin-left: 10px;display: none;"
                                         bo-if="level2.children && level2.children.length>0">
                                        <a ng-click="addTab(level3.name,level3.url,level3,level2.id)"
                                           style="cursor: pointer;"
                                           bindonce ng-repeat="level3 in level2.children">
                                            <span style="margin-right:3px;color:#1893dd;">&#8627;</span>
                                            <span bo-text="level3.name" class="menu-text"></span>
                                        </a>
                                    </div>
                                </div>
                                <a bo-if="!level2.children || level2.children.length<1"
                                   ng-click="addTab(level2.name,level2.url,level2,level2.id)">
                                    <span bo-text="level2.name" class="menu-text"></span>
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div id="colbar" class="dn">
                <div id="arrow" ng-click="toggle()">
                    <i class="arrow-left" id="fold" title="收起"></i>
                    <i class="arrow-right" id="expand" title="展开" style="display: none;"></i>
                </div>
            </div>
            <div class="content-iframe">
                <iframe id="iframe" style="display: none;" name="iframe" frameborder="0"></iframe>
                <div id="tab" style="height: 100%;width: 100%;overflow: hidden;display: none;"></div>
            </div>
        </div>
    </div>
    <div class="footer" id="footer">
        <div class="left">
            <span><i class="icons user" title="当前用户"
                     style="top:3px;"></i><span>${sessionScope.employeeName}</span></span>
            <span style="margin-left: 15px;font-weight: 700;" ng-cloak eccrm-previlege="PMD">消息：</span>
        </div>

        <div class="center">
            <marquee direction="left" onmouseover="this.stop()" onmouseout="this.start()">
                *** 内部资料,注意保密! ***
            </marquee>
        </div>
        <div class="right" style="width: 300px;">
            <span>软件开发： 兰州倍特信息技术有限公司</span>
        </div>
    </div>
</div>
</body>
<script src="<%=contextPath%>/app/main/js/main.js" type="text/javascript"></script>
</html>