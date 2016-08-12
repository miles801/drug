/**
 * 刑满释放人员编辑
 */
(function (window, angular, $) {
    var app = angular.module('drug.drug.edit', [
        'drug.drug',
        'eccrm.angular',
        'eccrm.angularstrap'
    ]);

    app.controller('Ctrl', function ($scope, ParameterLoader,CommonUtils, AlertFactory, ModalFactory, DrugService, DrugParam) {

        var pageType = $('#pageType').val();
        var id = $('#id').val();
        $scope.drugHelp = [];
        $scope.pickEmp=function () {
            DrugParam.setWidget({
            }, function (data) {
                var d=data.modelCfg;
                $scope.drugHelp.push(d);
            });
        }

        // 查看并修改内容z
        $scope.modify = function (index) {
            var da = $scope.drugHelp[index];
            DrugParam.setWidget({
                modelCfg: $.extend({}, da)
            }, function (data) {
                var a = data.modelCfg;
                 $scope.drugHelp[index] = a;
            })
        };

        $scope.addDrugHelp=function () {
            var promise = DrugService.addDrugHelp($scope.drugHelp, function (data) {
                AlertFactory.success('保存成功!');
            });
            CommonUtils.loading(promise, '保存中...');
        }
        $scope.remove=function (a) {
            $scope.drugHelp.splice(a,1);
        }

        $scope.back = CommonUtils.back;
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
        // 涉毒类型参数
        $scope.drugType = [{name: '请选择...'}];
        ParameterLoader.loadBusinessParam("DRUG_TYPE", function (data) {
            $scope.drugType.push.apply($scope.drugType, data);
        });
        // 涉毒种类参数
        $scope.checkboxData = [{name: '请选择...'}];
        ParameterLoader.loadBusinessParam("DRUG_SORT", function (data) {
            $scope.checkboxData.push.apply($scope.checkboxData, data);
        });
        // 吸毒人员管控类别
        $scope.controlXType = [{name: '请选择...'}];
        ParameterLoader.loadBusinessParam("CONTROL_XTYPE", function (data) {
            $scope.controlXType.push.apply($scope.controlXType, data);
        });
        // 贩毒人员管控类别
        $scope.controlFType = [{name: '请选择...'}];
        ParameterLoader.loadBusinessParam("CONTROL_FTYPE", function (data) {
            $scope.controlFType.push.apply($scope.controlFType, data);
        });

        // 清除时间
        $scope.clearDate = function (a) {
            if(a==0){
                $scope.beans.drug.brithDate = null;
            }
            if(a==1){
                $scope.beans.drug.inStartTime = null;
            }
            if(a==2){
                $scope.beans.drug.dealDate = null;
            }
        }
        // 保存
        $scope.save = function (createNew) {
            var promise = DrugService.save($scope.beans, function (data) {
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
            var promise = DrugService.update($scope.beans.drug, function (data) {
                AlertFactory.success('更新成功!');
                $scope.form.$setValidity('committed', false);
                CommonUtils.addTab('update');
                CommonUtils.delay($scope.back, 2000);
            });
            CommonUtils.loading(promise, '更新中...');
        };

        // 加载数据
        $scope.load = function (id) {
            var promise = DrugService.get({id: id}, function (data) {
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