/**
 * Created by wangchun on 15/9/25.
 */
define(function (require, exports, module) {
    module.exports = function(app){
        //页面控制器
        app.constant('JQ_CONFIG', {
                easyPieChart:   [G.path.ResPath+'/js/libs/jquery/charts/easypiechart/jquery.easy-pie-chart.js'],
                sparkline:      [G.path.ResPath+'/js/libs/jquery/charts/sparkline/jquery.sparkline.min.js'],
                plot:           [G.path.ResPath+'/js/libs/jquery/charts/flot/jquery.flot.min.js',
                    G.path.ResPath+'/js/libs/jquery/charts/flot/jquery.flot.resize.js',
                    G.path.ResPath+'/js/libs/jquery/charts/flot/jquery.flot.tooltip.min.js',
                    G.path.ResPath+'/js/libs/jquery/charts/flot/jquery.flot.spline.js',
                    G.path.ResPath+'/js/libs/jquery/charts/flot/jquery.flot.orderBars.js',
                    G.path.ResPath+'/js/libs/jquery/charts/flot/jquery.flot.pie.min.js'],
                slimScroll:     [G.path.ResPath+'/js/libs/jquery/slimscroll/jquery.slimscroll.min.js'],
                sortable:       [G.path.ResPath+'/js/libs/jquery/sortable/jquery.sortable.js'],
                nestable:       [G.path.ResPath+'/js/libs/jquery/nestable/jquery.nestable.js',
                    G.path.ResPath+'/js/libs/jquery/nestable/nestable.css'],
                filestyle:      [G.path.ResPath+'/js/libs/jquery/file/bootstrap-filestyle.min.js'],
                slider:         [G.path.ResPath+'/js/libs/jquery/slider/bootstrap-slider.js',
                    G.path.ResPath+'/js/libs/jquery/slider/slider.css'],
                chosen:         [G.path.ResPath+'/js/libs/jquery/chosen/chosen.jquery.min.js',
                    G.path.ResPath+'/js/libs/jquery/chosen/chosen.css'],
                TouchSpin:      [G.path.ResPath+'/js/libs/jquery/spinner/jquery.bootstrap-touchspin.min.js',
                    G.path.ResPath+'/js/libs/jquery/spinner/jquery.bootstrap-touchspin.css'],
                wysiwyg:        [G.path.ResPath+'/js/libs/jquery/wysiwyg/bootstrap-wysiwyg.js',
                    G.path.ResPath+'/js/libs/jquery/wysiwyg/jquery.hotkeys.js'],
                dataTable:      [G.path.ResPath+'/js/libs/jquery/datatables/jquery.dataTables.min.js',
                    G.path.ResPath+'/js/libs/jquery/datatables/dataTables.bootstrap.js',
                    G.path.ResPath+'/js/libs/jquery/datatables/dataTables.bootstrap.css'],
                vectorMap:      [G.path.ResPath+'/js/libs/jquery/jvectormap/jquery-jvectormap.min.js',
                    G.path.ResPath+'/js/libs/jquery/jvectormap/jquery-jvectormap-world-mill-en.js',
                    G.path.ResPath+'/js/libs/jquery/jvectormap/jquery-jvectormap-us-aea-en.js',
                    G.path.ResPath+'/js/libs/jquery/jvectormap/jquery-jvectormap.css'],
                footable:       [G.path.ResPath+'/js/libs/jquery/footable/footable.all.min.js',
                    G.path.ResPath+'/js/libs/jquery/footable/footable.core.css']
            }
        )
            .constant('MODULE_CONFIG', {
                select2:        [ G.path.ResPath+'/js/modules/ui-select2',
                    G.path.ResPath+'/js/libs/jquery/select2/select2.min.js'
                    ,G.path.ResPath+'/js/libs/jquery/select2/select2.css',
                    G.path.ResPath+'/js/libs/jquery/select2/select2-bootstrap.css']
            }
        );


    }

});