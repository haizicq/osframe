package com.os.osframe.core.users.web;

import com.os.osframe.frame.common.RequiresClassAuth;
import com.os.osframe.frame.web.BaseIndexController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 首页入口
 * Created by wangdc on 2016-03-01 23:39.
 */
@Controller
@RequestMapping(value = "/core/users")
@RequiresClassAuth(common= RequiresClassAuth.CommonType.CONTROL)
public class mscUsersIndexController extends BaseIndexController {

    protected Class getSubClass(){
        return mscUsersIndexController.class;
    }
}
