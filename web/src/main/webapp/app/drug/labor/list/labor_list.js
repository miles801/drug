/**
* 外出务工人员管理列表
* Created by Rechired on 2016-08-09 22:46:23.
*/
(function (window, angular, $) {
    var app = angular.module('drug.labor.list', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'eccrm.angular.ztree',
        'base.org',
        'drug.labor'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, ModalFactory, LaborService, LaborParam,OrgTree) {
        $scope.condition = {
        };

        // 导出数据
        $scope.exportData = function () {
            if ($scope.pager.total < 1) {
                AlertFactory.error('未获取到可以导出的数据!请先查询出数据!');
                return;
            }
            var o = angular.extend({}, $scope.condition);
            o.start = null;
            o.limit = null;
            window.open(CommonUtils.contextPathURL('/base/labor/exportLaborExcel ?' + encodeURI(encodeURI($.param(o)))));
        };

        // 打印
        $scope.print=function () {
            if ($scope.pager.total < 1) {
                AlertFactory.error('未获取到可以打印的数据!请先查询出数据!');
                return;
            }
            var o = angular.extend({}, $scope.condition);
            o.start = null;
            o.limit = null;
            window.open(CommonUtils.contextPathURL('/base/labor/print?' + encodeURI(encodeURI($.param(o)))));
        }

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
                    var promise = LaborService.pageQuery(param, function(data){
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

        // 删除或批量删除
        $scope.remove = function (id) {
            if (!id) {
                var ids = [];
                angular.forEach($scope.items, function (o) {
                    ids.push(o.labor.id);
                });
                id = ids.join(',');
            }
            ModalFactory.confirm({
                scope: $scope,
                content: '<span class="text-danger">数据一旦删除将不可恢复，请确认!</span>',
                callback: function () {
                    var promise = LaborService.deleteByIds({ids: id}, function(){
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
                title: '新增外出务工人员管理',
                url: '/base/labor/add',
                onUpdate: $scope.query
            });
        };

        // 更新
        $scope.modify = function (id) {
            CommonUtils.addTab({
                title: '更新外出务工人员管理',
                url: '/base/labor/modify?id=' + id,
                onUpdate: $scope.query
            });
        };

        // 查看明细
        $scope.view = function (id) {
            CommonUtils.addTab({
                title: '查看外出务工人员管理',
                url: '/base/labor/detail?id=' + id
            });
        }
    });
})(window, angular, jQuery);