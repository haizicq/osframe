package com.os.osframe.frame.service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 获取数据基础接口
 * Created by wangdc on 2014-5-13.
 */
public interface IBaseData {
    public abstract List getData(HttpServletRequest request) throws Exception;
}
