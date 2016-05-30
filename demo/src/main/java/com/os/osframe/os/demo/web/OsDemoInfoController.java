package com.os.osframe.os.demo.web;

import com.os.osframe.frame.common.*;
import com.os.osframe.frame.log.OutException;
import com.os.osframe.frame.service.IBaseService;
import com.os.osframe.frame.util.CurrentUserUtil;
import com.os.osframe.frame.web.BaseController;
import com.os.osframe.os.demo.domain.OsDemoInfo;
import com.os.osframe.os.demo.service.IOsDemoInfoService;
import com.os.osframe.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 权限信息
 * Created by wangdc on 2016-06-06 23:06.
 */
@Controller
@RequestMapping(value = "/os/demo/osDemoInfo")
@RequiresClassAuth
public class OsDemoInfoController extends BaseController<OsDemoInfo> {
    @Resource
    IOsDemoInfoService osDemoInfoService;
    @Override
    protected IBaseService getServiceImp() {
        return osDemoInfoService;
    }

    @Override
    protected void changeSearchHqlObject(HttpServletRequest request, HqlObject hqlObject) throws Exception {
        super.changeSearchHqlObject(request, hqlObject);
        String where="";
        String searchKey=request.getParameter("searchKey");
        if(StringUtil.isNotNull(searchKey)){
        where=" osDemoInfo.lbName like :searchKey";
        hqlObject.setParameter("searchKey","%"+searchKey+"%");
        }
        hqlObject.setWhereExtract(StringUtil.linkString(hqlObject.getWhereExtract()," and ",where));
    }
    /**
     * 获取最新测试数据 3条
     */
    @ResponseBody
    @RequiresAuth
    @RequestMapping(value="/new")
    public String newDemo(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
        OutException outException=new OutException("newDemo");
        JsonDataFormat jsonDataFormat=new JsonDataFormat();
        try {
            String rowSizeStr = request.getParameter("rowSize");

            int pageNo = 1;
            int rowSize = 3;
            if (StringUtil.isNotNull(rowSizeStr) && !"null".equals(rowSizeStr)) {
                rowSize = Integer.parseInt(rowSizeStr);
            }
            HqlObject hqlObject=new HqlObject();
//            hqlObject.setOrderByExtract(" osDemoInfo");
            hqlObject.setPageNo(pageNo);
            if(rowSize>0){
                hqlObject.setRowSize(rowSize);
            }
            PageInfo pageInfo=getServiceImp().findPageInfo(hqlObject);
            List list=pageInfo.getList();
            jsonDataFormat.put("data",list);
        }catch (Exception e){
            outException.setError(e);
        }

        logger.executeTime(outException);
        if(outException.hasError()){
            logger.error(outException);
            if(CurrentUserUtil.isDeveloper()){//校验是否开发者，非开发者则不返回错误信息了,仅有
                jsonDataFormat.setErrorMessage(JsonDataFormat.JSON_ERROR_CODE_OTHER, outException.toString());
            }
        }
        return jsonDataFormat.toString();//提示信息
    }
}
