/**
* 禁毒重点整治排查汇总列表
* Created by Rechired on 2016-08-14 09:54:35.
*/
(function (window, angular, $) {
    var app = angular.module('drug.allDrug.list', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'drug.allDrug'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, ModalFactory, AllDrugService, AllDrugParam) {
        $scope.condition = { };

        //查询数据
        $scope.query = function() {
            $scope.pager.query();
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
            window.open(CommonUtils.contextPathURL('/base/user/allDrug/print?' + encodeURI(encodeURI($.param(o)))));
        }


        $scope.pager = {
            fetch: function () {
                var param = angular.extend({}, {start: this.start, limit: this.limit}, $scope.condition);
                $scope.beans = [];
                return CommonUtils.promise(function(defer){
                    var promise = AllDrugService.pageQuery(param, function(data){
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
            ModalFactory.confirm({
                scope: $scope,
                content: '<span class="text-danger">数据一旦删除将不可恢复，请确认!</span>',
                callback: function () {
                    var promise = AllDrugService.deleteByIds({ids: id}, function(){
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
                title: '新增禁毒重点整治排查汇总',
                url: '/drug/allDrug/add',
                onUpdate: $scope.query
            });
        };

        // 更新
        $scope.modify = function (id) {
            CommonUtils.addTab({
                title: '更新禁毒重点整治排查汇总',
                url: '/drug/allDrug/modify?id=' + id,
                onUpdate: $scope.query
            });
        };

        // 查看明细
        $scope.view = function (id) {
            CommonUtils.addTab({
                title: '查看禁毒重点整治排查汇总',
                url: '/drug/allDrug/detail?id=' + id
            });
        }
    });
})(window, angular, jQuery);