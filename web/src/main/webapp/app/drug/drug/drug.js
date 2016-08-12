/**
 * 刑满释放人员
 * Created by Rechired on 2016-08-11 19:35:45.
 */
(function (angular) {
    var app = angular.module('drug.drug', [
        'ngResource',
        'eccrm.angular',
        'eccrm.base.param',
        'eccrm.angularstrap'
    ]);

    app.service('DrugService', function (CommonUtils, $resource) {
        return $resource(CommonUtils.contextPathURL('/base/drug/:method'), {}, {
            // 保存
            save: {method: 'POST', params: {method: 'save'}, isArray: false},
            // 保存
            addDrugHelp: {method: 'POST', params: {method: 'addDrugHelp'}, isArray: false},

            // 更新
            update: {method: 'POST', params: {method: 'update'}, isArray: false},

            // 根据id查询信息
            get: {method: 'GET', params: {method: 'get', id: '@id'}, isArray: false},

            // 分页查询
            pageQuery: {method: 'POST', params: {method: 'pageQuery', limit: '@limit', start: '@start'}, isArray: false},

            // 根据id字符串（使用逗号分隔多个值）
            deleteByIds: {method: 'DELETE', params: {method: 'delete', ids: '@ids'}, isArray: false}
        })
    });

    app.service('DrugParam', function($modal,DrugService, CommonUtils, ParameterLoader, $compile, AlertFactory) {
        return {
            setWidget: function (options, callback) {
                var model = $modal({
                    template: CommonUtils.contextPathURL('app/drug/drug/template/modal-edit.ftl.html')
                });
                var $scope = model.$scope;
                $scope.options = options;
                $scope.modelCfg=options.modelCfg;
                $scope.confirm = function () {
                    // var promise = DrugService.addDrugHelp($scope.modelCfg, function (data) {
                    //     AlertFactory.success('保存成功!');
                    //     CommonUtils.loading(promise, '保存中...');
                    // });
                    callback({
                        isConfirm: true,
                        modelCfg: $scope.modelCfg,
                        compile: $compile,
                        scope: options.scope,
                        index: options ? options.index : null
                    });
                    model.hide();
                }
            }
    }
    });

})(angular);
