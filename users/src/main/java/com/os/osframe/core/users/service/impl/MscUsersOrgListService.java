package com.os.osframe.core.users.service.impl;

import com.os.osframe.frame.common.HqlObject;
import com.os.osframe.frame.service.IBaseData;
import com.os.osframe.core.users.domain.MscUsersInfo;
import com.os.osframe.core.users.service.IMscUsersInfoService;
import com.os.osframe.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangchun on 16/5/23.
 */
@Service("mscUsersOrgListService")
public class MscUsersOrgListService implements IBaseData {

    @Resource
    IMscUsersInfoService mscUsersInfoService;
    public List getData(HttpServletRequest request) throws Exception {
        String treeId=request.getParameter("treeId");//获取导航树的id
        String type=request.getParameter("type");//获取类型
        String searchKey=request.getParameter("search");//获取类型
        if(StringUtil.isNull(treeId) && StringUtil.isNull(searchKey)){
            return null;
        }
        List reList=new ArrayList();
        HqlObject hqlObject=new HqlObject();
        String where="  mscUsersInfo.lbIsValid!=:lbIsValid";
        if(StringUtil.isNull(searchKey)){
            where+=" and mscUsersInfo.lbParent.pkId=:parentId";
            hqlObject.setParameter("parentId",treeId);
        }
        if(StringUtil.isNotNull(type)){
            where+=" and mscUsersInfo.lbType=:type";
            hqlObject.setParameter("type",type);
        }
        if(StringUtil.isNotNull(searchKey)){
            where+=" and mscUsersInfo.lbName like :searchKey";
            hqlObject.setParameter("searchKey","%"+searchKey+"%");
        }
        hqlObject.setWhereExtract(where);
        hqlObject.setParameter("lbIsValid","0");
        List<MscUsersInfo> list=mscUsersInfoService.find(hqlObject);
        if(list!=null && !list.isEmpty()){
            for(MscUsersInfo mscUsersInfo:list){
                Map map=new HashMap();
                map.put("value",mscUsersInfo.getPkId());
                map.put("text",mscUsersInfo.getLbName());
                reList.add(map);
            }
        }
        return reList;
    }
}
