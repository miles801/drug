/**
* 人员管理列表
* Created by Rechired on 2016-08-11 08:34:40.
*/
(function (window, angular, $) {
    var app = angular.module('drug.user.list', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'drug.user',
        'eccrm.angular.ztree',
        'base.org'
    ]);
    app.controller('Ctrl', function ($scope,OrgTree, CommonUtils, AlertFactory, ModalFactory, UserService, UserParam) {
        $scope.condition = { };

        // 导出花名册模板
        $scope.exportExcel = function () {
            window.open(CommonUtils.contextPathURL('/base/user/exportModel'));
        }

        // 导入花名册信息
        $scope.importData = function () {
            UserParam.setWidget({
                scope: $scope
            }, function (data) {
                var excelId = data.modelCfg;
                if (excelId == null) {
                    AlertFactory.error("上传excel出错，请重新上传！！！");
                    return false;
                }
                var promise = UserService.importExcel({ids: excelId}, function () {
                    AlertFactory.success('恭喜您，成功导入数据!');
                    $scope.query();
                });
                CommonUtils.loading(promise, 'Loading...');
            });
        }
        // 设置村民关系
        
        $scope.setRelation=function (id) {
            CommonUtils.addTab({
                title: '设置村民关系',
                url: '/base/user/setRelation?id=' + id,
                onUpdate: $scope.query
            });
        }


        // 导出数据
        $scope.exportData = function () {
            if ($scope.pager.total < 1) {
                AlertFactory.error('未获取到可以导出的数据!请先查询出数据!');
                return;
            }
            var o = angular.extend({}, $scope.condition);
            o.start = null;
            o.limit = null;
            window.open(CommonUtils.contextPathURL('/base/user/exportUserExcel ?' + encodeURI(encodeURI($.param(o)))));
        };


        $scope.orgTree = OrgTree.pick(function (o) {
            $scope.condition.orgId = o.id;
            $scope.condition.orgName = o.name;
        });
        /**
         * 清除机构信息
         */
        $scope.clearOrg = function () {
            $scope.condition.orgId = null;
            $scope.condition.orgName = null;
        };
        //查询数据
        $scope.query = function() {
            $scope.pager.query();
        };

        $scope.pager = {
            fetch: function () {
                var param = angular.extend({}, {start: this.start, limit: this.limit}, $scope.condition);
                $scope.beans = [];
                return CommonUtils.promise(function(defer){
                    var promise = UserService.pageQuery(param, function(data){
                        param = null;
                        $scope.beans = data.data || {total: 0};
                        defer.resolve($scope.beans);
                    });
                    CommonUtils.loading(promise, 'Loading...');
                });
            },
            finishInit: function () {
                this.query();
            }
        };

        // 将人员信息标记为不同的状态记录
        $scope.addLog = function (flag) {
            var id;
            var ids = [];
            angular.forEach($scope.items, function (o) {
                ids.push(o.id);
            });
            id = ids.join(',');
            ModalFactory.confirm({
                scope: $scope,
                content: '<span class="text-danger">您确认在此标记中加入相关人员的记录？</span>',
                callback: function () {
                    var promise = UserService.addLog({ids: id,flag:flag}, function(){
                        AlertFactory.success('记录新增成功!');
                        $scope.query();
                    });
                    CommonUtils.loading((promise));
                }
            });
        };
        // 删除或批量删除
        $scope.remove = function (id) {
            if (!id) {
                var ids = [];
                angular.forEach($scope.items, function (o) {
                    ids.push(o.id);
                });
                id = ids.join(',');
            }
            ModalFactory.confirm({
                scope: $scope,
                content: '<span class="text-danger">数据一旦删除将不可恢复，请确认!</span>',
                callback: function () {
                    var promise = UserService.deleteByIds({ids: id}, function(){
                        AlertFactory.success('删除成功!');
                        $scope.query();
                    });
                    CommonUtils.loading((promise));
                }
            });
        };

        // 新增
        $scope.add = function () {
            CommonUtils.addTab({
                title: '新增人员管理',
                url: '/base/user/add',
                onUpdate: $scope.query
            });
        };

        // 更新
        $scope.modify = function (id) {
            CommonUtils.addTab({
                title: '更新人员管理',
                url: '/base/user/modify?id=' + id,
                onUpdate: $scope.query
            });
        };

        // 查看明细
        $scope.view = function (id) {
            CommonUtils.addTab({
                title: '查看人员管理',
                url: '/base/user/detail?id=' + id
            });
        }
    });
})(window, angular, jQuery);