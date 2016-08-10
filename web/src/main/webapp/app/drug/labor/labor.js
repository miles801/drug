/**
 * 外出务工人员管理
 * Created by Rechired on 2016-08-09 22:46:23.
 */
(function (angular) {
    var app = angular.module('drug.labor', [
        'ngResource',
        'eccrm.angular',
        'eccrm.base.param',
        'eccrm.angularstrap'
    ]);

    app.service('LaborService', function (CommonUtils, $resource) {
        return $resource(CommonUtils.contextPathURL('/base/labor/:method'), {}, {
            // 保存
            save: {method: 'POST', params: {method: 'save'}, isArray: false},

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

    app.service('LaborParam', function(ParameterLoader) {
        return {
            // 系统参数
            busPara: function (paraType, callback) {
                ParameterLoader.loadSysParam(paraType, callback);
            }
        };
    });

})(angular);
