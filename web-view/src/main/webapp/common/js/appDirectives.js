/**
 * 标签等指令
 * Created by wangchun on 15/9/18.
 */
define(function (require, exports, module) {
    var global=require("js/global");//加载全局js
    var dialog=require("js/dialog");//加载对话框
    var apps=angular.module('app.directives', ["ngSea"]);
    apps.directive('uiModule', ['MODULE_CONFIG','$compile','$ngSea', function(MODULE_CONFIG,  $compile,$ngSea) {//加载下拉框的特殊UI
            return {
                restrict: 'A',
                compile: function (el, attrs) {
                    var contents = el.contents().clone();
                    return function(scope, el, attrs){
                        el.contents().remove();
                        seajs.use(MODULE_CONFIG[attrs.uiModule],
                            function(){
                            $compile(contents)(scope, function(clonedElement, scope) {
                                el.append(clonedElement);
                            });
                        });
                    }
                }
            };
        }])

        .directive('uiToggleClass', ['$timeout', '$document', function($timeout, $document) {//右边设置展开和折叠
            return {
                restrict: 'AC',
                link: function(scope, el, attr) {
                    el.on('click', function(e) {
                        e.preventDefault();
                        var classes = attr.uiToggleClass.split(','),
                            targets = (attr.target && attr.target.split(',')) || Array(el),
                            key = 0;
                        angular.forEach(classes, function( _class ) {
                            var target = targets[(targets.length && key)];
                            ( _class.indexOf( '*' ) !== -1 ) && magic(_class, target);
                            $( target ).toggleClass(_class);
                            key ++;
                        });
                        $(el).toggleClass('active');

                        function magic(_class, target){
                            var patt = new RegExp( '\\s' +
                                _class.
                                    replace( /\*/g, '[A-Za-z0-9-_]+' ).
                                    split( ' ' ).
                                    join( '\\s|\\s' ) +
                                '\\s', 'g' );
                            var cn = ' ' + $(target)[0].className + ' ';
                            while ( patt.test( cn ) ) {
                                cn = cn.replace( patt, ' ' );
                            }
                            $(target)[0].className = $.trim( cn );
                        }
                    });
                }
            };
        }])
        .directive('uiNav', ['$timeout', function($timeout) {//左侧导航栏折叠显示控制
            return {
                restrict: 'AC',
                link: function(scope, el, attr) {
                    var _window = $(window),
                        _mb = 768,
                        wrap = $('.app-aside'),
                        next,
                        backdrop = '.dropdown-backdrop';
                    // unfolded
                    el.on('click', 'a', function(e) {
                        next && next.trigger('mouseleave.nav');
                        var _this = $(this);
                        _this.parent().siblings( ".active" ).toggleClass('active');
                        _this.next().is('ul') &&  _this.parent().toggleClass('active') &&  e.preventDefault();
                        // mobile
                        _this.next().is('ul') || ( ( _window.width() < _mb ) && $('.app-aside').removeClass('show off-screen') );
                    });

                    // folded & fixed
                    el.on('mouseenter', 'a', function(e){
                        next && next.trigger('mouseleave.nav');
                        $('> .nav', wrap).remove();
                        if ( !$('.app-aside-fixed.app-aside-folded').length || ( _window.width() < _mb )) return;
                        var _this = $(e.target)
                            , top
                            , w_h = $(window).height()
                            , offset = 50
                            , min = 150;

                        !_this.is('a') && (_this = _this.closest('a'));
                        if( _this.next().is('ul') ){
                            next = _this.next();
                        }else{
                            return;
                        }

                        _this.parent().addClass('active');
                        top = _this.parent().position().top + offset;
                        next.css('top', top);
                        if( top + next.height() > w_h ){
                            next.css('bottom', 0);
                        }
                        if(top + min > w_h){
                            next.css('bottom', w_h - top - offset).css('top', 'auto');
                        }
                        next.appendTo(wrap);

                        next.on('mouseleave.nav', function(e){
                            $(backdrop).remove();
                            next.appendTo(_this.parent());
                            next.off('mouseleave.nav').css('top', 'auto').css('bottom', 'auto');
                            _this.parent().removeClass('active');
                        });

                        $('.smart').length && $('<div class="dropdown-backdrop"/>').insertAfter('.app-aside').on('click', function(next){
                            next && next.trigger('mouseleave.nav');
                        });

                    });

                    wrap.on('mouseleave', function(e){
                        next && next.trigger('mouseleave.nav');
                        $('> .nav', wrap).remove();
                    });
                }
            };
        }])
        .directive('uiScroll', ['$location', '$anchorScroll', function($location, $anchorScroll) {//创建锚点，具体功能未知
            return {
                restrict: 'AC',
                link: function(scope, el, attr) {
                    el.on('click', function(e) {
                        $location.hash(attr.uiScroll);
                        $anchorScroll();
                    });
                }
            };
        }])
        .directive('uiFullscreen', function() {//全屏
            return {
                restrict: 'AC',
                template:'<i class="fa fa-expand fa-fw text"></i><i class="fa fa-compress fa-fw text-active"></i>',
                link: function(scope, el, attr) {
                    el.addClass('hide');
                    seajs.use(G.path.ResPath+'/js/libs/screenfull.js',
                        function(screenfull){
                            if (screenfull.enabled) {
                                el.removeClass('hide');
                            }
                            el.on('click', function(){
                                var target;
                                attr.target && ( target = $(attr.target)[0] );
                                el.toggleClass('active');
                                screenfull.toggle(target);
                            });
                        });
                }
            };
        })
        .directive('uiButterbar', ['$rootScope', '$anchorScroll', function($rootScope, $anchorScroll) {//TODO 未知功能
            return {
                restrict: 'AC',
                template:'<span class="bar"></span>',
                link: function(scope, el, attrs) {
                    el.addClass('butterbar hide');
                    scope.$on('$stateChangeStart', function(event) {
                        $anchorScroll();
                        el.removeClass('hide').addClass('active');
                    });
                    scope.$on('$stateChangeSuccess', function( event, toState, toParams, fromState ) {
                        event.targetScope.$watch('$viewContentLoaded', function(){
                            el.addClass('hide').removeClass('active');
                        })
                    });
                }
            };
        }])
        .directive('setNgAnimate', ['$animate', function ($animate) {//动态图片切换
            return {
                link: function ($scope, $element, $attrs) {
                    $scope.$watch( function() {
                        return $scope.$eval($attrs.setNgAnimate, $scope);
                    }, function(valnew, valold){
                        $animate.enabled(!!valnew, $element);
                    });
                }
            };
        }]).directive('dateTimeSelect',['$compile','$parse', function ($compile,$parse) {//时间日期选择

            return {
                restrict: 'A',
                compile: function ($element, attrs) {
                    var inputNgModel=attrs.ngModel;
                    var dateType=attrs.dateType;

                    if(dateType==undefined || dateType==null){
                        dateType="dateTime";
                    }
                    var template='<div style="position: absolute;float: left;top:100%;left:0;z-index:1010;background-color: #ffffff;display:none" >\
                         <div>'
                    //添加日期控件
                    if(dateType=="date" || dateType=="dateTime"){
                        template+='<datepicker ng-model="myController.date.'+inputNgModel+'" format="yyyy-MM-dd" min-date="minDate" show-weeks="true" class="datepicker" ></datepicker>';

                    }
                    //添加时间控件
                    if(dateType=="time" || dateType=="dateTime"){
                        template+='<timepicker ng-model="myController.time.'+inputNgModel+'" ng-change="changed()" hour-step="1" minute-step="15" show-meridian="false"></timepicker>';

                    }
                       template+=' </div>\
                            <span>\
                            <button  type="button" class="btn btn-default"  >今天</button>\
                            <button  type="button" class="btn btn-default"  >清空</button>\
                            <button  type="button" class="btn btn-default pull-right"  >确定</button>\
                            </span>\
                        </div>\
                        <span class="input-group-btn">\
                        <button  type="button" class="btn btn-default"  ><i class="glyphicon glyphicon-calendar"></i></button>\
                        </span>';
                    return function(scope, $element, attrs){
                            //TODO 待完善点：1、点击其他位置，关闭时间选择(domElement的onclick事件)
                            // 2、双击选择时间后，直接执行确定操作(获取datepicker的事件)
                            // 3、国际化底部3个按钮
                            $compile(template)(scope, function (clonedElement, scope) {
                                var parentArea=$element.parent();
                                var t=$('<div class="input-group w-md" >').append($element);
                                t.append(clonedElement);
                                parentArea.html(t);
                                //$element.after(clonedElement);
                                var dateSelectArea = $element.next();//时间选择层
                                var openButton = $element.next().next();//获取打开按钮
                                openButton.on("click", function (e) {
                                    if (dateSelectArea.css('display') == "" || dateSelectArea.css('display') == "block") {
                                        dateSelectArea.css('display', 'none');
                                        return;
                                    }
                                    dateSelectArea.css('display', '');
                                    seajs.use("js/date", function(date) {//使用指令前先引入时间处理js
                                        var val = $parse(attrs.ngModel)(scope);//获取当前选择的值
                                        if (val == undefined || val == null || val == "") {
                                            val = new Date();
                                        } else {
                                            val=date.strToDate(val);
                                        }

                                        //赋值到控件上去
                                        if(dateType=="date" || dateType=="dateTime"){
                                            $parse('myController.date.' + inputNgModel).assign(scope, val);
                                        }
                                        if(dateType=="time" || dateType=="dateTime"){
                                            $parse('myController.time.' + inputNgModel).assign(scope, val);
                                        }

                                        scope.$apply();//同步
                                    });
                                });
                                var dialogButtonDom = dateSelectArea.children().next().children();//按钮区域

                                dialogButtonDom.first().on("click", function (e) {//关闭按钮
                                    var now=new Date();
                                    seajs.use("js/date", function(date) {//使用指令前先引入时间处理js
                                        var inputDate="";
                                        if(dateType=="date" || dateType=="dateTime"){
                                            var dateVal = date.dateToStr(now,'yyyy-MM-dd');
                                            inputDate=dateVal;
                                        }
                                        if(dateType=="time" || dateType=="dateTime"){
                                            var timeVal=date.dateToStr(now,'hh:mm');
                                            inputDate+=(inputDate==""?"":" ")+timeVal;
                                        }
                                        //然后赋值
                                        $parse(inputNgModel).assign(scope, inputDate);
                                        scope.$apply();//同步
                                    });

                                    dateSelectArea.css('display', 'none');
                                });
                                dialogButtonDom.next().on("click", function (e) {//点击事件，清空按钮
                                    $parse(inputNgModel).assign(scope, "");
                                    scope.$apply();//同步
                                    dateSelectArea.css('display', 'none');
                                });
                                dialogButtonDom.last().on("click", function (e) {//关闭按钮
                                    seajs.use("js/date", function(date) {//使用指令前先引入时间处理js
                                        var dateVal = date.dateToStr($parse('myController.date.' + inputNgModel)(scope),'yyyy-MM-dd');
                                        var timeVal=date.dateToStr($parse('myController.time.' + inputNgModel)(scope),'hh:mm');
                                        var inputDate="";
                                        if(dateType=="date" || dateType=="dateTime"){
                                            var dateVal =  date.dateToStr($parse('myController.date.' + inputNgModel)(scope),'yyyy-MM-dd');
                                            inputDate=dateVal;
                                        }
                                        if(dateType=="time" || dateType=="dateTime"){
                                            var timeVal=date.dateToStr($parse('myController.time.' + inputNgModel)(scope),'hh:mm');
                                            inputDate+=(inputDate==""?"":" ")+timeVal;
                                        }
                                        //然后赋值
                                        $parse(inputNgModel).assign(scope, inputDate);
                                        scope.$apply();//同步
                                    });

                                    dateSelectArea.css('display', 'none');
                                });
                            });
                    }
                }
            };
        }]).directive('openDialogList',['$parse','$log', function ($parse,$log) {//弹出对话框
            var template='<span class="input-group-btn">\
                        <button  type="button" class="btn btn-default"  >选择</button>\
                        </span>';
                function dealSelectItem(selectedItem,id,name,scope){
                    if(selectedItem!=undefined && selectedItem!=null && selectedItem.length>0){
                        var texts = [];
                        var values = [];
                        angular.forEach(selectedItem,function(v,k){
                            texts.push(v.text);
                            values.push(v.value);
                        });
                        $parse(name).assign(scope, texts);//直接根据ng-model赋值
                        $parse(id).assign(scope, values);//直接根据ng-model赋值

                        scope.$apply();//同步
                        $log.info('选择值为: ' + texts);
                    }else{
                        $parse(name).assign(scope, "");//直接根据ng-model赋值
                        $parse(id).assign(scope, "");//直接根据ng-model赋值
                        scope.$apply();//同步
                        $log.info('选择值为空' );
                    }
                }
            return {
                restrict: 'A',
                link: function ($scope, $element, $attrs) {
                    //在parent中插入<div class="input-group w-md" ></div>
                    var parentArea=$element.parent();
                    var t=$('<div class="input-group w-md" >').append($element);
                    t.append(template);
                    parentArea.html(t);
                    var selectButton = $element.next();//选择按钮
                    selectButton.on("click",function(e){
                        var selectText=$parse($attrs.ngModel)($scope);
                        var pkId=$attrs.ngValueId;//默认的pkid  $attrs.ngModel+".id"
                        var dialogType=$attrs.ngDialogType;//对话框类型
                        var _isMultiple=$attrs.ngMultiple;//是否多选
                        var isMultiple;
                        if(_isMultiple!=null && "true"==_isMultiple){isMultiple=true;}
                        else{isMultiple=false;}
                        //id的取值方式，首先取ngValueId,如果有值时直接取，其次取替换为pkId
                        var selectValue=$parse(pkId)($scope);
                        seajs.use('js/dialog',function(dialogList){
                            if("treeList"==dialogType){
                                dialogList.treeList(isMultiple,$attrs.ngTreeBean,$attrs.ngBean,selectText,selectValue,function(selectedItem){//关闭返回值
                                    dealSelectItem(selectedItem,pkId,$attrs.ngModel,$scope);
                                });
                            }else if("tree"==dialogType){
                                dialogList.tree(isMultiple,$attrs.ngBean,selectText,selectValue,function(selectedItem){//关闭返回值
                                    dealSelectItem(selectedItem,pkId,$attrs.ngModel,$scope);
                                });
                            }else if("org"==dialogType){
                                dialogList.orgSelect(isMultiple,selectText,selectValue,$attrs.ngOrgType,function(selectedItem){//关闭返回值
                                    dealSelectItem(selectedItem,pkId,$attrs.ngModel,$scope);
                                });
                            }else{
                                dialogList.list(isMultiple,$attrs.ngBean,selectText,selectValue,function(selectedItem){//关闭返回值
                                    dealSelectItem(selectedItem,pkId,$attrs.ngModel,$scope);
                                });
                            }

                        });
                    });
                }
            }
        }]).directive('dialogAlert',['$parse','$log', function ($parse,$log) {//弹出对话框
            return {
                restrict: 'A',
                link: function ($scope, $element, $attrs) {
                    $element.on("click",function(e){
                        seajs.use('js/dialog',function(dialog){
                            dialog.alert($attrs.dialogAlert);
                        });

                    });
                }
            }
        }])
        .directive('uiNavList', ['$stateProvider', function ($stateProvider) {//导航读取展示
            var template='';//模板内容
            return {
                link: function ($scope, $element, $attrs) {
                    //先读取数据，再循环添加到template参数上，再行设置$stateProvider的配置，地址根路径
                    $compile(template)(scope, function (clonedElement, scope) {

                    });
                }
            };
        }]).directive('ngCheckbox',['$parse','$log', function ($parse,$log) {//复选框勾选及保存

            return {
                restrict: 'A',
                link: function ($scope, $element, $attrs) {
                    $element.on("click",function(e){
                        //取出name
                        var elementName=$attrs.name;
                        var elementValue=$attrs.value;
                        var selectValue=$parse(elementName)($scope);//取值  选中值 用逗号隔开
                        if(selectValue==undefined || selectValue=="undefined"){
                            selectValue="";
                        }
                        //判断是否存在 默认不存在
                        var bool=false;
                        var clearValue="";
                        if(selectValue!=null &&selectValue!=""){
                            var arr=selectValue.split(",");
                            for(var i=0;i<arr.length;i++){
                                if(elementValue==arr[i]){//当等于当前值时剔除
                                    bool=true;
                                    continue;
                                }
                                if(clearValue==""){
                                    clearValue= arr[i];
                                }else{
                                    clearValue+=","+arr[i];
                                }
                            }
                        }

                        //是否选中
                        var isChecked=$(this).is(':checked');
                        if(isChecked && !bool){//如果是选中，且不存在 则增加进去
                            if(selectValue!=null && selectValue!=""){

                                selectValue+=","+elementValue;
                            }else{
                                selectValue=elementValue;
                            }

                            $parse(elementName).assign($scope, selectValue);//赋值
                        }else if(!isChecked && bool){//剔除值
                            $parse(elementName).assign($scope, clearValue);//赋值
                        }
                    });
                }
            }
        }]).directive('ngAllCheckbox',['$parse','$log', function ($parse,$log) {//复选框选择全部,或者全部取消全选

            return {
                restrict: 'A',
                link: function ($scope, $element, $attrs) {
                    $element.on("click",function(e){
                        //取出name
                        var elementName=$attrs.ngAllCheckbox;
                        if($(this).is(':checked')==true){
                            var allIds;
                            //将所有的复选框设置为选中状态
                            $("input[name='"+elementName+"']:checkbox").prop("checked", true);
                            $("input[name='"+elementName+"']").each(function(){
                                if(allIds==null || allIds==""){
                                    allIds=$(this).val();
                                }else{
                                    allIds+=","+$(this).val();
                                }
                            });
                            $parse(elementName).assign($scope, allIds);//赋值
                        }else{//取消全选
                            $("input[name='"+elementName+"']:checkbox").prop("checked", false);
                            $parse(elementName).assign($scope, '');//赋值
                        }

                    });
                }
            }
        }]).directive('ngSearch',['$parse','$location', function ($parse,$location) {//列表中的搜索

            return {
                restrict: 'A',
                link: function ($scope, $element, $attrs) {
                    $element.on("click",function(e){
                        //取出name
                        var searchKey=$attrs.ngSearch;//搜索的关键字
                        if(searchKey==undefined || searchKey==null || searchKey==""){
                            searchKey=$scope.searchKey;
                        }else{
                            searchKey=$parse(searchKey)($scope);//取值
                        }
                        $location.search({'searchKey':searchKey,'pageNo':''});
                        $scope.$apply();
                    });
                }
            }
        }]).directive('ngReturn',['$window', function ($window) {//返回上一页

            return {
                restrict: 'A',
                link: function ($scope, $element, $attrs) {
                    $element.on("click",function(e){
                        $window.history.back();//返回上一页
                    });
                }
            }
        }]).directive('ngRefresh',['$window','$location','$state', function ($window,$location,$state) {//刷新

            return {
                restrict: 'A',
                link: function ($scope, $element, $attrs) {
                    $element.on("click",function(e){
                        var params=$attrs.ngRefresh;
                        if(params==undefined || params==null || params==""){
                            params={};
                        }else{
                            params=eval('('+params+')');
                        }
                        //$state.current;
                        //$state.reload();//这个刷新会请求后台，但界面不会被刷新，除非数据都使用angular加载
                        $state.go('.',params, { reload: true });
                    });
                }
            }
        }]).directive('ngDelete',['$state','$location','requestService', function ($state,$location,requestService) {//删除

            return {
                restrict: 'A',
                link: function ($scope, $element, $attrs) {
                    $element.on("click",function(e){
                        var domain=$attrs.ngDelete;
                        var deleteUrl;
                        var targetState;
                        var params;
                        if(domain!=undefined && domain!=null && domain!=''){
                            var _domain=domain;
                            if(domain.indexOf("(")>-1){//说明传入了参数
                                _domain=domain.substring(0,domain.indexOf("("));
                                params=domain.substring(domain.indexOf("("));
                            }
                            deleteUrl="/"+global.getModel($location.absUrl())+"/"+_domain+"/delete.html";
                            targetState=_domain+"List";
                        }else{
                            deleteUrl=$attrs.ngUrl;
                            targetState=$attrs.ngTarget;
                            params=$attrs.ngParams;

                        }

                        if(params==undefined || params==null || params=='' || deleteUrl==undefined || deleteUrl==null || deleteUrl==''){//当没有参数时不进行处理
                            return;
                        }
                        //将参数转为json
                        if(params.indexOf("(")>-1){
                            params=eval(params);
                        }else{
                            params=eval('('+params+')');
                        }

                        //先要确认是否需要删除
                        dialog.alert("是否确认删除？",function(reVal){
                                if(reVal){//当确认删除时再执行删除
                                    deleteUrl=global.reg(deleteUrl);
                                    requestService.submit(deleteUrl,params).success(function(response){
                                        if(!global.isSuccess(response)){
                                            return;
                                        }
                                        if(targetState!=undefined && targetState!=null && targetState!=''){//页面跳转
                                            $state.go(targetState,{}, {reload: true});//保存后直接跳转到列表页面
                                        }

                                    });
                                }
                        });
                    });
                }
            }
        }]).directive('ngSelectDelete',['$state','$location','requestService','$parse', function ($state,$location,requestService,$parse) {//删除选择的项

            return {
                restrict: 'A',
                link: function ($scope, $element, $attrs) {
                    $element.on("click",function(e){
                        var domain=$attrs.ngSelectDelete;
                        var deleteUrl;
                        var targetState;
                        var params;
                        if(domain!=undefined && domain!=null && domain!=''){
                            var _domain=domain;
                            if(domain.indexOf("(")>-1){//说明传入了参数
                                _domain=domain.substring(0,domain.indexOf("("));
                                params=domain.substring(domain.indexOf("(")+1,domain.length-1);
                            }
                            deleteUrl="/"+global.getModel($location.absUrl())+"/"+_domain+"/delmul.html";
                            targetState=_domain+"List";
                        }else{
                            deleteUrl=$attrs.ngUrl;
                            targetState=$attrs.ngTarget;
                            params=$attrs.ngParams;

                        }
                        var selectParam =$parse(params)($scope);//取值
                        if(selectParam==undefined || selectParam==null || selectParam==""){
                            dialog.alert("没有选择删除项");
                            return ;
                        }

                        var selectParam={"ids":selectParam.split(',')};
                        if(deleteUrl==undefined || deleteUrl==null || deleteUrl==''){//当没有参数时不进行处理
                            return;
                        }
                        //先要确认是否需要删除
                        dialog.alert("是否确认删除？",function (reVal) {
                                if(reVal){//当确认删除时再执行删除
                                    deleteUrl=global.reg(deleteUrl);
                                    requestService.submit(deleteUrl,selectParam).success(function(response){
                                        if(!global.isSuccess(response)){
                                            return;
                                        }
                                        if(targetState!=undefined && targetState!=null && targetState!=''){//页面跳转
                                            $state.go(targetState,{}, {reload: true});//保存后直接跳转到列表页面
                                        }

                                    });
                                }
                        });
                    });
                }
            }
        }]).directive('ngEditSubmit',['$state','$location','requestService','$parse', function ($state,$location,requestService,$parse) {//编辑界面提交

            return {
                restrict: 'A',
                link: function ($scope, $element, $attrs) {
                    $element.on("click",function(e){
                        var domain=$attrs.ngEditSubmit;
                        var submitUrl;
                        var targetState;
                        var params;
                        if(domain!=undefined && domain!=null && domain!=''){
                            var _domain=domain;
                            if(domain.indexOf("(")>-1){//说明传入了参数
                                _domain=domain.substring(0,domain.indexOf("("));
                                params=domain.substring(domain.indexOf("(")+1,domain.length-1);
                            }
                            submitUrl="/"+global.getModel($location.absUrl())+"/"+_domain+"/save.html";
                            targetState=_domain+"List";
                        }else{
                            submitUrl=$attrs.ngUrl;
                            targetState=$attrs.ngTarget;
                            params=$attrs.ngParams;

                        }
                        if(params==undefined || params==null || params==""){
                            dialog.alert("未指定需要提交的对象");
                            return ;
                        }
                        var submitParam =$parse(params)($scope);//取值
                        if(submitUrl==undefined || submitUrl==null || submitUrl==''){//当没有参数时不进行处理
                            return;
                        }
                        //获取支持类数据
                        var supportParam =$parse("supportData")($scope);//取值
                        var supportNum=0;
                        if(supportParam!=undefined && supportParam!=null){
                            $.each(supportParam, function(key) {
                                var supportDomain=supportParam[key];
                                if(supportDomain.hasOwnProperty("data")){//当为data时直接获取data中的属性即可
                                    var jsonArr=global.toJsonArr(supportDomain.data);
                                    if(jsonArr!=null && jsonArr.length>0){
                                        submitParam["supportList["+supportNum+"].data"]=JSON.stringify(jsonArr);//将数据封装进来
                                    }else{
                                        submitParam["supportList["+supportNum+"].data"]=JSON.stringify(supportDomain.data);//将数据封装进来
                                    }
                                }else{//否则按关键字获取
                                    $.each(supportDomain, function(keyword) {
                                        var supportKeywordDomain=supportParam[key];
                                        submitParam["supportList["+supportNum+"].keyword"]=keyword;//将关键字封装进去
                                        if(supportKeywordDomain.hasOwnProperty("data")){
                                            var jsonArr=global.toJsonArr(supportKeywordDomain.data);
                                            if(jsonArr!=null && jsonArr.length>0){
                                                submitParam["supportList["+supportNum+"].data"]=JSON.stringify(jsonArr);//将数据封装进来
                                            }else{
                                                submitParam["supportList["+supportNum+"].data"]=JSON.stringify(supportKeywordDomain.data);//将数据封装进来
                                            }
                                        }
                                    });
                                }
                                submitParam["supportList["+supportNum+"].type"]=key;//将数据封装进来
                                supportNum++;
                            });
                        }

                        submitUrl=global.reg(submitUrl);
                        requestService.submit(submitUrl,submitParam).success(function(response){
                            if(!global.isSuccess(response)){
                                return;
                            }
                            if(targetState!=undefined && targetState!=null && targetState!=''){//页面跳转
                                $state.go(targetState,{}, {reload: true});//保存后直接跳转到列表页面
                            }

                        });
                    });
                }
            }
        }]);
    module.exports = apps;
});