/**
* 刑满释放人员列表
* Created by Rechired on 2016-08-11 16:19:43.
*/
(function (window, angular, $) {
    var app = angular.module('drug.released.list', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'eccrm.angular.ztree',
        'base.org',
        'drug.released'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, OrgTree,AlertFactory, ModalFactory, ReleasedService, ReleasedParam) {
        $scope.condition = { };

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
        // 打印
        $scope.print=function () {
            if ($scope.pager.total < 1) {
                AlertFactory.error('未获取到可以打印的数据!请先查询出数据!');
                return;
            }
            var o = angular.extend({}, $scope.condition);
            o.start = null;
            o.limit = null;
            window.open(CommonUtils.contextPathURL('/base/released/print?' + encodeURI(encodeURI($.param(o)))));
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
            window.open(CommonUtils.contextPathURL('/base/released/exportReleasedExcel ?' + encodeURI(encodeURI($.param(o)))));
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
                    var promise = ReleasedService.pageQuery(param, function(data){
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
                    console.log(o)
                    ids.push(o.released.id);
                });
                id = ids.join(',');
            }
            ModalFactory.confirm({
                scope: $scope,
                content: '<span class="text-danger">数据一旦删除将不可恢复，请确认!</span>',
                callback: function () {
                    var promise = ReleasedService.deleteByIds({ids: id}, function(){
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
                title: '新增刑满释放人员',
                url: '/base/released/add',
                onUpdate: $scope.query
            });
        };

        // 更新
        $scope.modify = function (id) {
            CommonUtils.addTab({
                title: '更新刑满释放人员',
                url: '/base/released/modify?id=' + id,
                onUpdate: $scope.query
            });
        };

        // 查看明细
        $scope.view = function (id) {
            CommonUtils.addTab({
                title: '查看刑满释放人员',
                url: '/base/released/detail?id=' + id
            });
        }
    });
})(window, angular, jQuery);