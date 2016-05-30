package com.os.osframe.os.demo.web;

import com.os.osframe.frame.web.BaseIndexController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页入口
 * Created by wangdc on 2016-06-06 23:06.
 */
@Controller
@RequestMapping(value = "/os/demo")
public class osDemoIndexController extends BaseIndexController {

    protected Class getSubClass(){
        return osDemoIndexController.class;
    }
}
