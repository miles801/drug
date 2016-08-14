/**
 * 禁毒重点整治排查汇总
 * Created by Rechired on 2016-08-14 09:54:35.
 */
(function (angular) {
    var app = angular.module('drug.allDrug', [
        'ngResource',
        'eccrm.angular',
        'eccrm.base.param',
        'eccrm.angularstrap'
    ]);

    app.service('AllDrugService', function (CommonUtils, $resource) {
        return $resource(CommonUtils.contextPathURL('/base/user/allDrug/:method'), {}, {
            // 保存
            save: {method: 'POST', params: {method: 'save'}, isArray: false},

            // 更新
            update: {method: 'POST', params: {method: 'update'}, isArray: false},

            // 根据id查询信息
            get: {method: 'GET', params: {method: 'get', id: '@id'}, isArray: false},

            // 分页查询
            pageQuery: {method: 'POST', params: {method: 'pageQuery', limit: '@limit', start: '@start'}, isArray: false},
            pageQueryBO: {method: 'POST', params: {method: 'pageQueryBO',limit: '@limit', start: '@start'}, isArray: false},

            // 根据id字符串（使用逗号分隔多个值）
            deleteByIds: {method: 'DELETE', params: {method: 'delete', ids: '@ids'}, isArray: false}
        })
    });

    app.service('AllDrugParam', function(ParameterLoader) {
        return {

        };
    });

})(angular);
