<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en" >

<head >
    <title>编辑人员管理</title>
    <meta content="text/html" charset="utf-8" >
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/bootstrap-v3.0/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/style/standard/css/eccrm-common-new.css">
    <link rel="stylesheet" type="text/css" href="<%=contextPath%>/vendor/zTree/css/ztree.css">
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/jquery-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-ztree-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-strap-all.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/static/ycrl/javascript/angular-upload.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/vendor/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=contextPath%>/app/base/org/org.js"></script>

    <script type="text/javascript">
        window.angular.contextPathURL = "<%=contextPath%>";
    </script>
</head>
<body>
<div class="main" ng-app="drug.user.edit" ng-controller="Ctrl">
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
                    <div>
                        <div class="row">
                            <div eccrm-upload="uploadOptions" ng-cloak></div>
                        </div>
                        <div id="imageId" class="row" >
                            <div id="showImage">
                                <img style="height: 150px;width: 150px;margin-left:150px" ng-if="${id==null}"
                                     ng-src="/app/drug/user/img/demo.gif "/>
                                <div ng-if="${id!=null}">
                                    <img style="height: 150px;width: 150px;margin-left:150px"
                                         ng-src="/attachment/download?id={{beans.icon}} "/>
                                    <i class="icons icon fork" ng-click="deleteImage()" ng-hide="!canedit"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>姓名:</label>
                        </div>
                        <input class="col-3-half" type="text" ng-model="beans.name" maxlength="10" validate validate-required/>
                        <div class="form-label col-1-half">
                            <label>性别:</label>
                        </div>
                        <select ng-model="beans.sex" class="col-3-half"
                                ng-options="foo.value as foo.name for foo in sex">
                        </select>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>民族:</label>
                        </div>
                        <select ng-model="beans.nation" class="col-3-half"
                                ng-options="foo.value as foo.name for foo in nation">
                        </select>
                        <div class="form-label col-1-half">
                            <label>文化程度:</label>
                        </div>
                        <select ng-model="beans.degrees" class="col-3-half"
                                ng-options="foo.value as foo.name for foo in degrees">
                        </select>
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>身份证号:</label>
                        </div>
                        <input class="col-3-half" type="text" ng-model="beans.idCard" maxlength="18"
                               oninput="if(value.length>18)value=value.slice(0,18)" />
                        <div class="form-label col-1-half">
                            <label>联系方式:</label>
                        </div>
                        <input class="col-3-half" type="text" ng-model="beans.phone" maxlength="11" validate validate-int validate-msg="手机号码格式不正确" />

                    </div>


                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>所属地区:</label>
                        </div>
                        <div class="col-3-half">
                            <input class="col-12" type="text" ng-model="beans.orgName" validate validate-required
                                   readonly ztree-single="orgTree"/>
                            <span class="add-on"><i class="icons icon cp fork" ng-click="clearOrg();"
                                                    title="清除"></i></span>
                        </div>
                        <div class="form-label col-1-half">
                            <label>是否是户主:</label>
                        </div>
                        <select class="col-3-half" ng-model="beans.isLeader" validate validate-required>
                            <option value="">请选择</option>
                            <option value="否">否</option>
                            <option value="是">是</option>
                        </select>
                        &nbsp;<a style="color: red">*</a>
                    </div>

                   <%-- <div class="row">
                        <div class="form-label col-1-half">
                            <label>务工情况:</label>
                        </div>
                        <input class="col-3-half" type="text" ng-model="beans.workStatus" maxlength="18" />
                        <div class="form-label col-1-half">
                            <label>务工地址:</label>
                        </div>
                        <input class="col-3-half" type="text" ng-model="beans.workAdress" maxlength="40" />

                    </div>--%>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>家庭详细地址:</label>
                        </div>
                        <input class="col-8-half" type="text" ng-model="beans.home" maxlength="100" />
                    </div>
                    <div class="row">
                        <div class="form-label col-1-half">
                            <label>备注:</label>
                        </div>
                        <textarea class="col-8-half" rows="3" ng-model="beans.context" maxlength="255"></textarea>
                    </div>


                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="<%=contextPath%>/app/drug/user/user.js" ></script>
<script type="text/javascript" src="<%=contextPath%>/app/drug/user/edit/user_edit.js" ></script>
</html >