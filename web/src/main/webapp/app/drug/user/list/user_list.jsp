<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
%>
<html lang="en" >
<head >
    <title>人员管理</title>
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
<div class="main condition-row-3" ng-app="drug.user.list" ng-controller="Ctrl">
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
                        <div class="form-label col-1-half">
                            <label>是否是户主:</label>
                        </div>
                        <select class="col-2-half"  ng-model="condition.isLeader">
                            <option value="">全部</option>
                            <option value="否">否</option>
                            <option value="是">是</option>
                        </select>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>是否外出务工:</label>
                        </div>
                        <select class="col-2-half"  ng-model="condition.isLabor">
                            <option value="">全部</option>
                            <option value="否">否</option>
                            <option value="是">是</option>
                        </select>
                        <div class="form-label col-1-half">
                            <label>是否涉毒:</label>
                        </div>
                        <select class="col-2-half"  ng-model="condition.isDrugs">
                            <option value="">全部</option>
                            <option value="否">否</option>
                            <option value="是">是</option>
                        </select>
                        <div class="form-label col-1-half">
                            <label>是否贩毒可疑:</label>
                        </div>
                        <select class="col-2-half"  ng-model="condition.isFDrug">
                            <option value="">全部</option>
                            <option value="否">否</option>
                            <option value="是">是</option>
                        </select>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>是否吸毒可疑:</label>
                        </div>
                        <select class="col-2-half"  ng-model="condition.isXDrug">
                            <option value="">全部</option>
                            <option value="否">否</option>
                            <option value="是">是</option>
                        </select>
                        <div class="form-label col-1-half">
                            <label>是否正在服刑:</label>
                        </div>
                        <select class="col-2-half"  ng-model="condition.isPrison">
                            <option value="">全部</option>
                            <option value="否">否</option>
                            <option value="是">是</option>
                        </select>
                        <div class="form-label col-1-half">
                            <label>是否刑满释放:</label>
                        </div>
                        <select class="col-2-half"  ng-model="condition.isReleased">
                            <option value="">全部</option>
                            <option value="否">否</option>
                            <option value="是">是</option>
                        </select>
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
                    <span>人员管理</span>
                </div>
                <span class="header-button">
                        <a type="button" class="btn btn-green btn-min" ng-click="add();">
                            <span class="glyphicons plus"></span> 新建
                        </a>
                    <a type="button" class="btn btn-green btn-min" ng-click="exportExcel();">
                        <span class="glyphicons plus"></span> 下载导入模板
                    </a>
                    <a type="button" class="btn btn-green btn-min" ng-click="importData();">
                        <span class="glyphicons plus"></span> 村民档案导入
                    </a>
                    <a type="button" class="btn btn-green btn-min" ng-click="exportData();">
                        <span class="glyphicons plus"></span> 村民档案导出
                    </a>
                        <a type="button" class="btn btn-green btn-min" ng-click="addLog(1);" ng-disabled="!anyone" disabled="disabled">
                            <span class="glyphicons plus"></span> 务工人员
                        </a>
                        <a type="button" class="btn btn-green btn-min" ng-click="addLog(2);" ng-disabled="!anyone" disabled="disabled">
                            <span class="glyphicons plus"></span> 可疑贩毒
                        </a>
                        <a type="button" class="btn btn-green btn-min" ng-click="addLog(3);" ng-disabled="!anyone" disabled="disabled">
                            <span class="glyphicons plus"></span> 可疑吸毒
                        </a>
                        <a type="button" class="btn btn-green btn-min" ng-click="addLog(4);" ng-disabled="!anyone" disabled="disabled">
                            <span class="glyphicons plus"></span> 服刑人员
                        </a>
                        <a type="button" class="btn btn-green btn-min" ng-click="addLog(5);" ng-disabled="!anyone" disabled="disabled">
                            <span class="glyphicons plus"></span> 刑满释放
                        </a>
                        <a type="button" class="btn btn-green btn-min" ng-click="addLog(6);" ng-disabled="!anyone" disabled="disabled">
                            <span class="glyphicons plus"></span> 涉毒人员
                        </a>
                        <a type="button" class="btn btn-green btn-min" ng-click="remove();" ng-disabled="!anyone" disabled="disabled">
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
                                <td>是否外出务工</td>
                                <td>是否涉毒</td>
                                <td>是否可疑吸毒</td>
                                <td>是否可疑贩毒</td>
                                <td>是否正在服刑</td>
                                <td>是否刑满释放</td>
                                <td>是否是户主</td>
                                <td>操作</td>
                            </tr>
                            </thead>
                            <tbody class="table-body">
                            <tr ng-show="!beans || !beans.total">
                                <td colspan="10" class="text-center">没有查询到数据！</td>
                            </tr>
                            <tr bindonce ng-repeat="foo in beans.data" ng-cloak>
                                <td><input type="checkbox" ng-model="foo.isSelected"/></td>
                                <td title="点击查询明细！" style="cursor: pointer;">
                                    <a ng-click="view(foo.id)" bo-text="foo.name"></a>
                                </td>
                                <td bo-text="foo.orgName"></td>
                                <td bo-text="foo.isLabor">
                                </td>
                                <td bo-text="foo.isDrugs"></td>
                                <td bo-text="foo.isXDrug"></td>
                                <td bo-text="foo.isFDrug"></td>
                                <td bo-text="foo.isPrison"></td>
                                <td bo-text="foo.isReleased"></td>
                                <td bo-text="foo.isLeader"></td>
                                <td>
                                    <a ng-disabled="foo.isLeader!='是'" class="btn-op green"
                                       ng-click="foo.isLeader!='是'||setRelation(foo.id);">设置家庭关系</a>
                                    <a class="btn-op blue" ng-click="modify(foo.id);">编辑</a>
                                    <a class="btn-op red" ng-click="remove(foo.id);">删除</a>
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
<script type="text/javascript" src="<%=contextPath%>/app/drug/user/user.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/app/drug/user/list/user_list.js" ></script>
</html >