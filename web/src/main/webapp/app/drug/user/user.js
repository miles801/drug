/**
 * 人员管理
 * Created by Rechired on 2016-08-11 08:34:40.
 */
(function (angular) {
    var app = angular.module('drug.user', [
        'ngResource',
        'eccrm.angular',
        'eccrm.base.param',
        'eccrm.angularstrap'
    ]);

    app.service('UserService', function (CommonUtils, $resource) {
        return $resource(CommonUtils.contextPathURL('/base/user/:method'), {}, {
            // 保存
            save: {method: 'POST', params: {method: 'save'}, isArray: false},

            // 更新
            update: {method: 'POST', params: {method: 'update'}, isArray: false},

            // 根据id查询信息
            get: {method: 'GET', params: {method: 'get', id: '@id'}, isArray: false},

            // 分页查询
            pageQuery: {method: 'POST', params: {method: 'pageQuery', limit: '@limit', start: '@start'}, isArray: false},

            // 根据id字符串（使用逗号分隔多个值）
            deleteByIds: {method: 'DELETE', params: {method: 'delete', ids: '@ids'}, isArray: false},

            //导入花名册
            importExcel: {method: 'POST', params: {method: 'saveUserFromExcel', ids: '@ids'}, isArray: false},

            // 新增记录
            addLog: {method: 'GET', params: {method: 'addLog', ids: '@ids',flag:'@flag'}, isArray: false}
        })
    });

    app.service('UserParam', function($modal, CommonUtils, ParameterLoader, $compile, AlertFactory, ModalFactory) {
        return {
            setWidget: function (options, callback) {
                // 读取弹出层的内容
                var model = $modal({
                    template: CommonUtils.contextPathURL('app/drug/user/template/modal-edit.ftl.html'),
                    backdrop: 'static'
                });
                var $scope = model.$scope;
                $scope.options = options;

                // 附件上传
                $scope.fileName = true;
                $scope.fileId = "";
                $scope.uploadOptions = CommonUtils.defer();
                ModalFactory.afterShown(model, function () {
                    $scope.uploadOptions.resolve({
                        // 最大上传文件数
                        maxFile: 1,
                        bid: "",
                        swfOption: {
                            // 文件的被允许的大小，单位KB
                            buttonText: '上传表格',
                            fileSizeLimit: 50000,
                            fileTypeExts: '*.xlsx'
                        },
                        onSuccess: function (attr) {
                            $scope.$apply(function () {
                                $scope.fileName = false;
                                $scope.fileId = attr.id;
                            });
                        }
                    });
                });

                // 点击保存
                $scope.confirm = function () {
                    callback({
                        isConfirm: true,
                        modelCfg: $scope.fileId,
                        compile: $compile,
                        scope: options.scope
                    });
                    model.hide();
                }
            }
        };
    });

})(angular);
