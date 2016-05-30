/**
 * Created by wangchun on 15/10/2.
 */

define(function(require, exports, module) {


    var angularDialog = angular.module('angularDialog', ['ui.router','ui.bootstrap','app.services']);
    angularDialog.factory('angularDialog', function ($modal,$timeout,dataService) {
        /**
         * 拼装已经选中对象
         * @param selectText 选择文本
         * @param selectVal 选值
         * @returns {*}
         */
        function assembleSelectObj(selectText,selectVal){
            if((selectText==undefined || selectText==null) && (selectVal==undefined || selectVal==null) ){//当传入值都为空时返回空
                return null;
            }
            //判断传入对象是否为字符串，如果是字符串时，先处理为数组
            var selectVal1,selectText1;
            if(typeof selectVal =="string"  ){//将字符串类型转为数组
                selectVal1=selectVal;
                selectVal=[];
                if(selectVal1!=null && selectVal1!=''){
                    selectVal=selectVal1.split(",");
                }
            }
            if(typeof selectText =="string"){
                selectText1=selectText;
                selectText=[];
                if(selectText1!=null && selectText1!=''){
                    selectText=selectText1.split(",");
                }
            }
            //根据值和文本看谁的长度长，根据长的来做循环
            var tlen= 0,vlen= 0,flen=0;
            if(selectVal!=undefined && selectVal!=null){
                vlen=selectVal.length;
            }
            if(selectText!=undefined && selectText!=null){
                tlen=selectText.length;
            }
            flen=tlen;
            if(tlen<vlen){
                flen=vlen;
            }
            var arr=[];
            for(var i= 0;i<flen;i++){//循环将字符串数组转换为对象数组
                var selectObj={};
                if(tlen>i){
                    selectObj.text=selectText[i];
                }else{
                    selectObj.text=selectVal[i];
                }
                if(vlen>i){
                    selectObj.value=selectVal[i];
                }else{
                    selectObj.value="";
                }
                arr.push(selectObj);
            }

            return arr;
        };
        /**
         * 获取返回的值
         * @param isMultiple 是否多选
         * @param selectValue 选中值对象
         * @param selectList 选择列表
         * @returns {Array}
         */
        function returnSelectValue(isMultiple,selectValue,selectList,dialogObj){
            var arr = [];
            if(isMultiple){
                if(selectValue==undefined || selectValue==null || selectValue.length==0){
                    arr=selectList;
                }else{
                    arr=selectValue;
                }
            }else{
                if(selectValue!=undefined && selectValue!=null && selectValue.length>0){//获取对话框的选中值
                    arr.push(selectValue[0]);
                }
                //当不存在选中值时，直接获取select框中的值
                else if(selectList!=undefined && selectList!=null && selectList.length>0){
                    if(selectList.length>1){//当选值项超过1项时，不允许确定
                        dialogObj.alert("只能选择一个值");
                        return null;
                    }
                    arr=selectList;
                }
            }
            if(arr==null || arr.length==0){
                dialogObj.alert("未选择任何值");
                return null;
            }
            return arr;
        }

        /**
         * 设置选择项
         * @param isMultiple
         * @param selectValue
         * @param selectList
         */
        function selectOption(isMultiple,selectValue,selectList){
            if(selectList==undefined || selectList==null || selectList.length==0){
                return null;
            }
            var arr=[];
            if(isMultiple){
                //判断是否存在相同的选项
                var bool=false;
                angular.forEach(selectList, function (listObj) {
                    bool=false;
                    angular.forEach(selectValue, function (valueObj) {
                        if(listObj.value==valueObj.value){
                            bool=true;
                        }
                    });
                    if(!bool){//当不存在重复时才进行写入
                        arr.push(listObj);
                    }
                });

            }else{
                arr.push(selectList[0]);
            }
            return arr;
        }
        return {
            /**
             * 展示列表对话框
             * @param isMultiple
             * @param bean
             * @param selectText
             * @param selectVal
             * @param callback
             * @param isLoad
             * @param size
             */
            list: function (isMultiple,bean, selectText, selectVal, callback,isLoad,size) {
                var dialogObj=this
                var ModalInstanceCtrl = function ($scope, $modalInstance, selectVal, ngBean,isLoad) {
                    $scope.dialog = {
                        title: "列表选择",
                        data: null,
                        selectValue: selectVal,
                        selectList: null,
                        isMultiple:isMultiple

                    };

                    if(selectVal==undefined || selectVal==null || selectVal==""){
                        $scope.dialog.selectValue=[];
                    }
                    $scope.search = function () {//后台还未实现搜索
                        dataService.bean(ngBean, $scope.dialog.searchValue).success(function (response) {//传入搜索内容查找
                            $scope.dialog.data = response;
                        });
                    };

                    $scope.selectOption=function(obj){
                        var arr=selectOption(isMultiple,$scope.dialog.selectValue,$scope.dialog.selectList);
                        if(arr!=null && arr.length>0){
                            if(isMultiple){
                                $scope.dialog.selectValue.push.apply($scope.dialog.selectValue, arr);
                            }else{
                                $scope.dialog.selectValue= arr;
                            }
                        }
                    };

                    $scope.deleteSelect=function(num){//删除指定位置项
                        $scope.dialog.selectValue.splice(num,1);
                    };
                    $scope.ok = function () {
                        var arr = returnSelectValue(isMultiple,$scope.dialog.selectValue,$scope.dialog.selectList,dialogObj);
                        if(arr!=null){
                            $modalInstance.close(arr);
                        }
                    };
                    $scope.cancelSelect = function () {
                        $modalInstance.close(null);
                    };
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                    if(isLoad==null || !isLoad){//默认自动加载，当设置了false时默认就不加载数据了
                        dataService.bean(bean).success(function (response) {//baseAngulrWeb
                            $scope.dialog.data=response;
                        });
                    }

                };
                var modalInstance = $modal.open({
                    templateUrl: G.path.TemplatePath + '/dialogList.htm',//TODO 是否可以尝试将模板的url地址拿进来，而且要注意规避重复加载 myModalContent.html
                    controller: ModalInstanceCtrl,
                    size: size,
                    resolve: {
                        selectVal: function () {
                            return assembleSelectObj(selectText,selectVal);
                        },
                        ngBean: function () {
                            return bean;
                        },
                        isLoad:function(){
                            return isLoad;
                        }
                    }
                });
                //回调函数执行
                if(callback!=undefined && callback!=null && typeof callback =='function'){
                    modalInstance.result.then(callback);
                }
            },
            treeList:function(isMultiple,treeBean,bean, selectText, selectVal, callback,size){//左边导航树，右边列表
                //先引入css和js
                seajs.use('js/loadRes',function(loadRes){
                    loadRes.css(G.path.ResPath+"/css/ztree/zTreeStyle.css");
                });
                //根据treebean先获取导航数据，展现在对话框中
                //对话框中点击导航，加载数据展现到右侧
                var dialogObj=this;
                var ModalInstanceCtrl = function ($scope, $modalInstance, selectVal, ngBean,ngTreeBean) {

                    $scope.dialog = {
                        title: "导航列表选择对话框",
                        data: null,
                        selectValue: selectVal,
                        selectList: null,
                        isMultiple:isMultiple

                    };
                    if(selectVal==undefined || selectVal==null || selectVal==""){
                        $scope.dialog.selectValue=[];
                    }
                    $scope.search = function () {//后台还未实现搜索
                        dataService.bean(ngBean, $scope.dialog.searchValue).success(function (response) {//传入搜索内容查找
                            $scope.dialog.data = response;
                        });
                    };
                    $scope.selectOption=function(obj){
                        var arr=selectOption(isMultiple,$scope.dialog.selectValue,$scope.dialog.selectList);
                        if(arr!=null && arr.length>0){
                            if(isMultiple){
                                $scope.dialog.selectValue.push.apply($scope.dialog.selectValue, arr);
                            }else{
                                $scope.dialog.selectValue= arr;
                            }
                        }
                        //$scope.dialog.selectList=[];//将下拉框选值清空
                    };
                    $scope.deleteSelect=function(num){//删除指定位置项
                        $scope.dialog.selectValue.splice(num,1);
                    };

                    $scope.ok = function () {
                        var arr = returnSelectValue(isMultiple,$scope.dialog.selectValue,$scope.dialog.selectList,dialogObj);
                        if(arr!=null){
                            $modalInstance.close(arr);
                        }
                    };
                    $scope.cancelSelect = function () {
                        $modalInstance.close(null);
                    };
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                    $timeout(function() {//导航树采用延时的方式加载出来
                            var treeBeanUrl= G.path.RootPath+"/ajax/data/bean.html?beanName="+ngTreeBean;
                            seajs.use(['js/libs/ztree/jquery.ztree.all.min','js/global'],function(ztreeAll,global){
                                treeBeanUrl=global.reg(treeBeanUrl);
                                var setting = {
                                    async: {
                                        enable: true,
                                        url: treeBeanUrl,
                                        autoParam:["id", "name=n", "level=lv"]
                                    },
                                    callback: {
                                        onClick: function(e1,e2,e3){
                                            //点击发起ajax请求，将数据写入到右侧选择select中
                                            dataService.bean(ngBean+"&treeId="+e3.id).success(function (response) {
                                                //单独写一个获取树下的数据，只获取当前层级下的
                                                $scope.dialog.data=response;
                                            });
                                        }
                                    }
                                };
                                $.fn.zTree.init($("#dialog-ztree"), setting);
                            });
                        },
                        50
                    );
                };
                var modalInstance = $modal.open({
                    templateUrl: G.path.TemplatePath + '/dialogTreeList.htm',//TODO 是否可以尝试将模板的url地址拿进来，而且要注意规避重复加载 myModalContent.html
                    controller: ModalInstanceCtrl,
                    size: size,
                    resolve: {
                        selectVal: function () {
                            return assembleSelectObj(selectText,selectVal);
                        },
                        ngBean: function () {
                            return bean;
                        },
                        ngTreeBean: function () {
                            return treeBean;
                        }
                    }
                });
                //回调函数执行
                if(callback!=undefined && callback!=null && typeof callback =='function'){
                    //callback(modalInstance);
                    modalInstance.result.then(callback);
                }
            },
            tree:function(isMultiple,treeBean, selectText, selectVal, callback,size){//导航树
                if(size==null){
                    size='sm';
                }
                //先引入css和js
                seajs.use('js/loadRes',function(loadRes){
                    loadRes.css(G.path.ResPath+"/css/ztree/zTreeStyle.css");
                });
                //根据treebean先获取导航数据，展现在对话框中
                //对话框中点击导航，加载数据展现到右侧
                var dialogObj=this;
                var ModalInstanceCtrl = function ($scope, $modalInstance, selectVal,ngTreeBean) {

                    $scope.dialog = {
                        title: "导航选择对话框",
                        data: null,
                        selectValue: selectVal,
                        selectList: null,
                        isMultiple:isMultiple

                    };
                    if(selectVal==undefined || selectVal==null || selectVal==""){
                        $scope.dialog.selectValue=[];
                    }
                    $scope.deleteSelect=function(num){//删除指定位置项
                        $scope.dialog.selectValue.splice(num,1);
                    };

                    $scope.ok = function () {
                        var arr = returnSelectValue(isMultiple,$scope.dialog.selectValue,$scope.dialog.selectList,dialogObj);
                        if(arr!=null){
                            $modalInstance.close(arr);
                        }
                    };
                    $scope.cancelSelect = function () {
                        $modalInstance.close(null);
                    };
                    $scope.cancel = function () {
                        $modalInstance.dismiss('cancel');
                    };
                    $timeout(function() {//导航树采用延时的方式加载出来
                            var treeBeanUrl= G.path.RootPath+"/ajax/data/bean.html?beanName="+ngTreeBean;
                            seajs.use(['js/libs/ztree/jquery.ztree.all.min','js/global'],function(ztreeAll,global){
                                treeBeanUrl=global.reg(treeBeanUrl);
                                var setting = {
                                    async: {
                                        enable: true,
                                        url: treeBeanUrl,
                                        autoParam:["id", "name=n", "level=lv"]
                                    },
                                    callback: {
                                        onClick: function(e1,e2,e3){
                                            var arr=[];
                                            arr.push({"value":e3.id,"text":e3.name});
                                            $scope.dialog.selectList=arr;
                                        },
                                        onDblClick:function(e1,e2,e3){
                                            var arr=[];
                                            arr.push({"value":e3.id,"text":e3.name});
                                            var arr1=selectOption(isMultiple,$scope.dialog.selectValue,arr);
                                            if(arr1!=null && arr1.length>0){
                                                if(isMultiple){
                                                    $scope.dialog.selectValue.push.apply($scope.dialog.selectValue, arr1);
                                                }else{
                                                    $scope.dialog.selectValue= arr1;
                                                }
                                                $scope.$apply();//同步
                                            }
                                        }
                                    }
                                };
                                $.fn.zTree.init($("#dialog-ztree"), setting);
                            });
                        },
                        50
                    );
                };
                var modalInstance = $modal.open({
                    templateUrl: G.path.TemplatePath + '/dialogTree.htm',//TODO 是否可以尝试将模板的url地址拿进来，而且要注意规避重复加载 myModalContent.html
                    controller: ModalInstanceCtrl,
                    size: size,
                    resolve: {
                        selectVal: function () {
                            return assembleSelectObj(selectText,selectVal);
                        },
                        ngTreeBean: function () {
                            return treeBean;
                        }
                    }
                });
                //回调函数执行
                if(callback!=undefined && callback!=null && typeof callback =='function'){
                    //callback(modalInstance);
                    modalInstance.result.then(callback);
                }
            },
            alert: function(content,callback,size){
                var ModalInstanceCtrl = function ($scope, $modalInstance, content) {
                    $scope.alert={title: "提示对话框",content :content};

                    $scope.ok = function () {
                        $modalInstance.close(true);
                    };

                    $scope.cancel = function () {
                        $modalInstance.close(false);
                    };

                };
                var modalInstance = $modal.open({
                    templateUrl: G.path.TemplatePath+'/alert.htm',
                    controller: ModalInstanceCtrl,
                    size: size,
                    resolve: {
                        content:function(){
                            return content;
                        }
                    }
                });
                if(callback!=undefined && callback!=null && typeof callback =='function'){
                    //callback(modalInstance);
                    modalInstance.result.then(callback);
                }
            }
        };
    });
    /**
     * 获取对话框服务对象
     * @returns {*}
     */
    var getAngularDialog=function(){
        var injector = angular.injector(['angularDialog', 'ng']);
        return injector.get('angularDialog');
    }
    /**
     * 弹出列表选择对话框
     * @param bean
     * @param selectText
     * @param selectVal
     * @param callback
     */
    exports.list=function(isMultiple,bean, selectText, selectVal, callback,isLoad,size){
        getAngularDialog().list(isMultiple,bean, selectText, selectVal, callback,isLoad,size);
    }
    /**
     * 弹出提示对话框
     * @param content
     * @param callback
     */
    exports.alert=function(content,callback,size){
        getAngularDialog().alert(content,callback,size);
    }
    exports.treeList=function(isMultiple,treebean,bean, selectText, selectVal, callback,size){
        getAngularDialog().treeList(isMultiple,treebean,bean, selectText, selectVal, callback,size);
    }

    exports.tree=function(isMultiple,treebean, selectText, selectVal, callback,size){
        getAngularDialog().tree(isMultiple,treebean, selectText, selectVal, callback,size);
    }
    exports.orgSelect=function(isMultiple,selectText, selectVal,type, callback,size){//组织结构选择
        var listBean="mscUsersOrgListService";
        if(type!=undefined && type!=null && type!=''){
            listBean+="&type="+type;
        }
        getAngularDialog().treeList(isMultiple,"mscUsersOrgTreeService",listBean, selectText, selectVal, callback,size);
    }
});