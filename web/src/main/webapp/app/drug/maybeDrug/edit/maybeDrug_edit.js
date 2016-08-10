/**
 * 吸毒可疑人员管理编辑
 */
(function (window, angular, $) {
    var app = angular.module('drug.maybeDrug.edit', [
        'drug.maybeDrug',
        'eccrm.angular',
        'eccrm.angularstrap',
        'eccrm.angular.ztree',
        'base.org'
    ]);

    app.controller('Ctrl', function ($scope, CommonUtils, AlertFactory, ModalFactory, MaybeDrugService,OrgTree,ParameterLoader, MaybeDrugParam) {

        var pageType = $('#pageType').val();
        var id = $('#id').val();

        $scope.orgTree = OrgTree.pick(function (o) {
            $scope.beans.orgId = o.id;
            $scope.beans.orgName = o.name;
        });
        /**
         * 清除机构信息
         */
        $scope.clearOrg = function () {
            $scope.beans.orgId = null;
            $scope.beans.orgName = null;
        };

        // 民族参数
        $scope.nation = [{name: '请选择...'}];
        ParameterLoader.loadBusinessParam("BP_NATION", function (data) {
            $scope.nation.push.apply($scope.nation, data);
        });

        // 性别
        $scope.sex = [{name: '请选择...'}];
        ParameterLoader.loadBusinessParam('BP_SEX', function (data) {
            $scope.sex.push.apply($scope.sex, data)
        });
        // 学历
        $scope.degree = [{name: '请选择...'}];
        ParameterLoader.loadBusinessParam('BP_XW', function (data) {
            $scope.degree.push.apply($scope.degree, data)
        });

        $scope.back = CommonUtils.back;

        // 保存
        $scope.save = function (createNew) {
            var promise = MaybeDrugService.save($scope.beans, function (data) {
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
            var promise = MaybeDrugService.update($scope.beans, function (data) {
                AlertFactory.success('更新成功!');
                $scope.form.$setValidity('committed', false);
                CommonUtils.addTab('update');
                CommonUtils.delay($scope.back, 2000);
            });
            CommonUtils.loading(promise, '更新中...');
        };

        // 加载数据
        $scope.load = function (id) {
            var promise = MaybeDrugService.get({id: id}, function (data) {
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