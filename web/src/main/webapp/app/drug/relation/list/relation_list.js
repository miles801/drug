/**
* 村民人员关系列表
* Created by Rechired on 2016-08-12 15:27:39.
*/
(function (window, angular, $) {
    var app = angular.module('drug.relation.list', [
        'eccrm.angular',
        'eccrm.angularstrap',
        'eccrm.angular.ztree',
        'eccrm.base.user.modal',
        'drug.relation'
    ]);
    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, ModalFactory, RelationService, RelationParam) {
        var id = $('#id').val();
        $scope.condition = {
            id:id
        };

        //查询数据
        $scope.query = function() {
            $scope.pager.query();
        };
        $scope.addEntering = function () {
            RelationParam.addEntering({}, $scope);
        }
        
        // 删除
        $scope.remove=function (id) {
            ModalFactory.confirm({
                scope: $scope,
                content: '<span class="text-danger">请确认是否真的解除人员关系!</span>',
                callback: function () {
                    var promise = RelationService.resetRelation({ids: id}, function () {
                        AlertFactory.success('解除成功!');
                        $scope.query();
                    });
                    CommonUtils.loading((promise));
                }
            });
        }
        $scope.pager = {
            fetch: function () {
                var param = angular.extend({}, {start: this.start, limit: this.limit}, $scope.condition);
                $scope.beans = [];
                return CommonUtils.promise(function(defer){
                    var promise = RelationService.pageQueryRelation(param, function(data){
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

        // // 删除或批量删除
        // $scope.remove = function (id) {
        //     ModalFactory.confirm({
        //         scope: $scope,
        //         content: '<span class="text-danger">数据一旦删除将不可恢复，请确认!</span>',
        //         callback: function () {
        //             var promise = RelationService.deleteByIds({ids: id}, function(){
        //                 AlertFactory.success('删除成功!');
        //                 $scope.query();
        //             });
        //             CommonUtils.loading((promise));
        //         }
        //     });
        // };

        // 新增
        $scope.add = function () {
            CommonUtils.addTab({
                title: '新增家庭成员关系',
                url: '/drug/relation/add',
                onUpdate: $scope.query
            });
        };

        // 更新
        $scope.modify = function (id) {
            CommonUtils.addTab({
                title: '更新家庭成员关系',
                url: '/drug/relation/modify?id=' + id,
                onUpdate: $scope.query
            });
        };

        // 查看明细
        $scope.view = function (id) {
            CommonUtils.addTab({
                title: '查看家庭成员关系',
                url: '/drug/relation/detail?id=' + id
            });
        }
    });
})(window, angular, jQuery);