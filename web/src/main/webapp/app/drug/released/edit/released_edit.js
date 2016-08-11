/**
 * 刑满释放人员编辑
 */
(function (window, angular, $) {
    var app = angular.module('drug.released.edit', [
        'drug.released',
        'eccrm.angular',
        'eccrm.angularstrap',
        'eccrm.angular.ztree',
        'base.org'
    ]);

    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, ModalFactory, ReleasedService, ReleasedParam) {

        var pageType = $('#pageType').val();
        var id = $('#id').val();

        // 清除时间
        $scope.clearDate = function (id) {
            if (id==1){
                $scope.beans.released.inStartTime = null;
            }else{
                $scope.beans.released.releasedDate = null;
            }

        }

        $scope.back = CommonUtils.back;

        // 保存
        $scope.save = function (createNew) {
            var promise = ReleasedService.save($scope.beans, function (data) {
                AlertFactory.success('保存成功!');
                CommonUtils.addTab('update');
                if (createNew === true) {
                    $scope.beans = {};
                } else {
                    $scope.form.$setValidity('committed', false);
                    CommonUtils.delay($scope.back, 2000);
                }
            });
            CommonUtils.loading(promise, '保存中...');
        };


        // 更新
        $scope.update = function () {
            var promise = ReleasedService.update($scope.beans.released, function (data) {
                AlertFactory.success('更新成功!');
                $scope.form.$setValidity('committed', false);
                CommonUtils.addTab('update');
                CommonUtils.delay($scope.back, 2000);
            });
            CommonUtils.loading(promise, '更新中...');
        };

        // 加载数据
        $scope.load = function (id) {
            var promise = ReleasedService.get({id: id}, function (data) {
                $scope.beans = data.data || {};
            });
            CommonUtils.loading(promise, 'Loading...');
        };


        if (pageType == 'add') {
            $scope.beans = {};
        } else if (pageType == 'modify') {
            $scope.load(id);
        } else if (pageType == 'detail') {
            $scope.load(id);
            $('input,textarea,select').attr('disabled', 'disabled');
        } else {
            AlertFactory.error($scope, '错误的页面类型');
        }

    });
})(window, angular, jQuery);