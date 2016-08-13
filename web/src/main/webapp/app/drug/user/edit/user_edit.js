/**
 * 人员管理编辑
 */
(function (window, angular, $) {
    var app = angular.module('drug.user.edit', [
        'drug.user',
        'eccrm.angular',
        'eccrm.angularstrap',
        'eccrm.angular.ztree',
        'base.org'
    ]);

    app.controller('Ctrl', function ($scope,OrgTree,ParameterLoader, CommonUtils, AlertFactory, ModalFactory, UserService, UserParam) {

        var pageType = $('#pageType').val();
        var id = $('#id').val();
        // 头像
        $scope.uploadOptions = {
            // 最大上传文件数
            labelText: '头像',
            maxFile: 1,
            showTable: false,
            bid: id,
            swfOption: {
                // 文件的被允许的大小，单位KB
                buttonText: '选择头像',
                fileSizeLimit: 2048,
                fileTypeExts: '*.png;*.jpg;*.gif'
            },
            onSuccess: function (attr) {
                var remove = $('<i class="icons icon fork" ></i>');
                var $imageId = $('#imageId');
                remove.on('click', function () {
                    $imageId.html('');
                    $scope.uploadOptions.removeAll();
                });
                $scope.attrs=attr.id;
                $("#showImage").hide();
                $imageId.append('<img style="height: 150px;width: 150px;margin-left: 150px" src="' + CommonUtils.contextPathURL('/attachment/download?id=' + attr.id) + '"/>');
                $imageId.append(remove);
            }
        };
        $scope.deleteImage = function () {
            $("#showImage").hide();
            $('#imageId').html('');
            $scope.uploadOptions.removeAll();
        };

        // 获取附件的id
        var wrapAttachment = function () {
            var obj = angular.extend({}, $scope.result);
            obj.icon = $scope.uploadOptions.getAttachment()[0];
            obj.attachmentIds = obj.icon;
            // 取最后一个
            return obj;
        };

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

        // 学历
        $scope.degrees = [{name: '请选择...'}];
        ParameterLoader.loadBusinessParam("BP_EDU", function (data) {
            $scope.degrees.push.apply($scope.degrees, data);
        });

        // 性别
        $scope.sex = [{name: '请选择...'}];
        ParameterLoader.loadBusinessParam('BP_SEX', function (data) {
            $scope.sex.push.apply($scope.sex, data)
        });
        
        $scope.back = CommonUtils.back;

        // 保存
        $scope.save = function (createNew) {
            var obj = wrapAttachment();
            $scope.beans.icon = obj.attachmentIds;
            var promise = UserService.save($scope.beans, function (data) {
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
            var obj = wrapAttachment();
            if (obj.attachmentIds != null) {
                $scope.beans.icon = obj.attachmentIds;
            }
            var promise = UserService.update($scope.beans, function (data) {
                AlertFactory.success('更新成功!');
                $scope.form.$setValidity('committed', false);
                CommonUtils.addTab('update');
                CommonUtils.delay($scope.back, 2000);
            });
            CommonUtils.loading(promise, '更新中...');
        };

        // 加载数据
        $scope.load = function (id) {
            var promise = UserService.get({id: id}, function (data) {
                $scope.beans = data.data || {};
            });
            CommonUtils.loading(promise, 'Loading...');
        };


        if (pageType == 'add') {
            $scope.beans = {};
            $scope.canedit = true;
        } else if (pageType == 'modify') {
            $scope.load(id);
            $scope.canedit = true;
        } else if (pageType == 'detail') {
            $scope.load(id);
            $scope.uploadOptions.readonly=true;
            $scope.canedit = false;
            $('input,textarea,select').attr('disabled', 'disabled');
        } else {
            AlertFactory.error($scope, '错误的页面类型');
        }

    });
})(window, angular, jQuery);