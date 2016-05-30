package com.os.osframe.frame.service;

import com.os.osframe.frame.common.DomainInfo;
import com.os.osframe.frame.common.HqlObject;
import com.os.osframe.frame.common.PageInfo;
import com.os.osframe.frame.domain.BaseDomain;
import com.os.osframe.util.StringUtil;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  获取 实体数据的service
 * Created by wangchun on 16/3/1.
 */
@Service("baseDomainService")
public class BaseDomainServiceImpl implements IBaseData{
    @Resource
    protected IBaseSimpleService baseSimpleService;

    public List getData(HttpServletRequest request) throws Exception {
        String domain=request.getParameter("domain");//domain的包和类的全路径
        if(StringUtil.isNull(domain) || domain.indexOf(".")==-1){
            return null;
        }
        String search=request.getParameter("search");//搜索关键词
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
        hqlObject.setRowSize(150);//最大150条记录，更多的情况不推荐使用
        hqlObject.setOrderByExtract(domainInfo.getSimpleName()+"."+id+ " desc");
        String searchSql= domainInfo.getSimpleName()+"."+name+" like :searchKey ";

        //添加where条件，获取传递的where，
        if(search!=null && !"".equals(search)){
            hqlObject.setWhereExtract(searchSql);//添加查询条件
            hqlObject.setParameter("searchKey",search);
        }

        //1、先取总数量，2、当数量过大主动设置分页，3、获取分页参数设置分页
        PageInfo pageInfo=baseSimpleService.findPageInfo(hqlObject);//查出对象列表，使用findpage有权限控制，避免了数据因无权而被查询
        List<BaseDomain> baseDomainList=pageInfo.getList();
        List reList=new ArrayList();
        for(BaseDomain baseDomain:baseDomainList){
            Map map=new HashMap();
            map.put("value",baseDomain.getPkId());
            map.put("text", PropertyUtils.getProperty(baseDomain, name));
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
        return reList;
    }
}
