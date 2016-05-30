package com.os.osframe.frame.web;

import com.alibaba.fastjson.JSONArray;
import com.os.osframe.frame.common.DomainInfo;
import com.os.osframe.frame.common.HqlObject;
import com.os.osframe.frame.domain.BaseDomain;
import com.os.osframe.frame.service.IBaseData;
import com.os.osframe.frame.service.IBaseSimpleService;
import com.os.osframe.frame.util.SpringUtils;
import com.os.osframe.util.StringUtil;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取数据的接口控制类
 * Created by wangdc on 2015-5-6.
 */
@Controller
@RequestMapping(value = "/ajax/data")
public class BaseDataController {
    @Autowired
    protected IBaseSimpleService baseSimpleService;

    /**
     * 根据域模型获取数据
     *      该类可以获取任何对象，存在安全隐患，待权限系统完善后，再将权限功能加入进来
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/domain",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public  String dataByDomain(HttpServletRequest request,HttpServletResponse response)throws Exception{
        String domain=request.getParameter("domain");//domain的包和类的全路径
        if(StringUtil.isNull(domain) || domain.indexOf(".")==-1){
            return null;
        }
        String id=request.getParameter("id");
        String name=request.getParameter("name");
        if(StringUtil.isNull(id))id="pkId";//当未传入ID和name时，直接获取pkId返回
        if(StringUtil.isNull(name))name="pkId";
        String fields=request.getParameter("fields");//还需要返回的字段
        String[] fieldArr=null;
        if(StringUtil.isNotNull(fields) && "undefined".equals(fields.toLowerCase())){
            fieldArr=fields.split(";");//用分号分隔多个需要返回的字段
        }
        //根据domain获取其服务类，然后查询出数据列表
        DomainInfo domainInfo=new DomainInfo(domain);
        HqlObject hqlObject=new HqlObject();
        hqlObject.setDomainInfo(domainInfo);
        //添加where条件，获取传递的where，上下级
        //hqlObject.setWhereExtract(" "+domain+".");//如何添加条件
        //1、先取总数量，2、当数量过大主动设置分页，3、获取分页参数设置分页
        List<BaseDomain> baseDomainList=baseSimpleService.find(hqlObject);//查出对象列表
        List reList=new ArrayList();
        for(BaseDomain baseDomain:baseDomainList){
            Map map=new HashMap();
            map.put("id",baseDomain.getPkId());
            map.put("name", PropertyUtils.getProperty(baseDomain, name));
            if(fieldArr!=null && fieldArr.length>0){
                for(int i=0;i<fieldArr.length;i++){//循环获取字段列表的值，为避免
                    try{
                        map.put(fieldArr[i],PropertyUtils.getProperty(baseDomain,fieldArr[i]));
                    }catch (Exception e){
                        map.put(fieldArr[i],"error");//当字段值获取失败时直接返回错误
                        e.printStackTrace();
                    }
                }
            }
            reList.add(map);
        }
        //将list转为json输出
        if(reList!=null && !reList.isEmpty()){
            JSONArray jsonArray = (JSONArray) JSONArray.toJSON(reList);
            return jsonArray.toJSONString();
        }
        return null;
    }

    /**
     * 根据bean获取数据返回
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/bean",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public  String dataByBean(HttpServletRequest request,HttpServletResponse response)throws Exception{
        String bean=request.getParameter("beanName");
        if(StringUtil.isNull(bean) || !SpringUtils.isExist(bean)){//当为空或不存在bean时，不执行后面
            return null;
        }
        Object beanObj= SpringUtils.getBean(bean);//获取bean对象
        if(beanObj instanceof IBaseData){
            IBaseData baseData=(IBaseData)beanObj;
            List reList=baseData.getData(request);//执行获取数据

            //将list转为json输出
            if(reList!=null && !reList.isEmpty()){
                JSONArray jsonArray = (JSONArray) JSONArray.toJSON(reList);
                return jsonArray.toJSONString();
            }
        }

        return null;
    }
}
