/**
 * 村民人员关系
 * Created by Rechired on 2016-08-12 15:27:39.
 */
(function (angular) {
    var app = angular.module('drug.relation', [
        'ngResource',
        'eccrm.angular',
        'eccrm.base.param',
        'eccrm.angularstrap'
    ]);

    app.service('RelationService', function (CommonUtils, $resource) {
        return $resource(CommonUtils.contextPathURL('/base/user/:method'), {}, {
            updateUserImg: {method: 'POST', params: {method: 'updateUserImg', id:'@id',attachmentIds: '@userImg'}, isArray: false},


            // 根据id查询信息
            get: {method: 'GET', params: {method: 'get', id: '@id'}, isArray: false},

            // 分页查询
            pageQueryRelation: {method: 'POST', params: {method: 'pageQueryRelation', limit: '@limit', start: '@start'}, isArray: false},

            // 根据id字符串（使用逗号分隔多个值）
            resetRelation: {method: 'GET', params: {method: 'resetRelation', ids: '@ids'}, isArray: false}
        })
    });
    app.service('LeaveWindowService', function ($modal, CommonUtils, ModalFactory) {
        return {
            createWindow: function (options, callback) {
                var model = $modal({
                    template: CommonUtils.contextPathURL('app/drug/relation/templete/imgUpload-edit.ftl.html')
                });
                var $scope = model.$scope;
                $scope.title = options.title;
                $scope.limit = options.limit;
                $scope.imgIds = [];
                $scope.bid = options.bid;
                $scope.btype = options.btype;

                // 以英文逗号分割字符串
                $scope.getImgIds = function (ids) {
                    var sArr = ids && ids.length > 0 ? ids.split(',') : [];
                    for (var i = 0; i < sArr.length; i++) {
                        if (sArr[i].length > 0) {
                            $scope.imgIds.push(sArr[i]);
                        }
                    }
                };
                // 获取分割后的imgId 数组
                $scope.getImgIds(options.imgIds);
                //获取单个图片的地址
                $scope.getImgUrl = function (imgId) {
                    return CommonUtils.contextPathURL('/attachment/download?id=' + imgId);
                };

                // 上传组件配置对象
                $scope.uploadOptions = CommonUtils.defer();
                ModalFactory.afterShown(model, function () {
                    $scope.uploadOptions.resolve({
                        // 最大上传文件数
                        maxFile: 10,
                        bid: $scope.bid,
                        btype: $scope.btype,
                        thumbWidth: 800,
                        thumbHeight: 300,
                        thumb: true,
                        swfOption: {
                            // 文件的被允许的大小，单位KB
                            buttonText: '上传图片',
                            fileSizeLimit: 4096,
                            fileTypeExts: '*.png;*.jpg;*.gif'
                        },
                        onSuccess: function (attr) {
                            $scope.$apply(function () {
                                $scope.imgIds.push(attr.id);
                            });
                        }
                    });
                });

                $scope.deleteShowDegreeImg = function ($index) {
                    $scope.uploadOptions.remove($scope.imgIds[$index] + '');
                    $scope.imgIds.splice($index, 1);
                };

                $scope.sub = function () {
                    if (callback) {
                        callback({model: model, imgIds: $scope.imgIds});
                    }
                };
                $scope.hide = function () {
                    model.hide();
                };
            }
        };
    });
    app.service('RelationParam', function($modal, ModalFactory, AlertFactory, CommonUtils, RelationService, EmployeeModal) {
            var commonAddEntering = function (options, callback) {
                var defaults = {
                    id: null,//id
                    pageType: null,     // 必填项,页面类型add/modify/view
                    callback: null,     // 点击确定后要执行的函数
                    afterShown: null    // 模态对话框显示完成后要执行的函数
                };
                options = angular.extend({}, defaults, options);

                var modal = $modal({
                    template: CommonUtils.contextPathURL('app/drug/user/template/express-external.tpl.html'),
                    backdrop: 'static'
                });
                var $scope = modal.$scope;
                var pageTypes = ['add', 'modify', 'view'];
                if ($.inArray(options.pageType, pageTypes) == -1) {
                    CommonUtils.errorDialog('不合法的页面类型!');
                    throw '不合法的页面类型，仅支持[' + pageTypes.join(',') + ']类型!';
                }

                var beans = $scope.beans = {};

                if(options.pageType == 'add'){
                    $scope.title = '设置村民人员关系';
                    $scope.bln = false;
                    $scope.btn = false;

                    // 选择收件人
                    $scope.pickEmpConsignee = function () {
                        EmployeeModal.pickEmployee({}, function (o) {
                            $scope.beans.id = o.id;
                            $scope.beans.name = o.name;
                        })
                    };

                    // 清除收件人
                    $scope.clearEmpConsignee = function () {
                        $scope.beans.id = null;
                        $scope.beans.name = null;
                    };

                    // 保存
                    $scope.save = function (addNew) {
                        var id = $('#id').val();
                        $scope.beans.isParent=id;
                        var promise = RelationService.save($scope.beans, function (data) {
                            AlertFactory.success('保存成功!');
                            angular.isFunction(callback) && callback();
                        });
                         CommonUtils.loading(promise, '保存中...');
                        modal.hide();
                        window.location.reload();
                    };

                }
            };
        return {
            addEntering: function (options, callback) {
                var o = angular.extend({}, options, {pageType: 'add'});
                commonAddEntering(o, callback);
            }
        }
    });

})(angular);
