package com.os.osframe.core.users.service.impl;

import com.os.osframe.frame.service.IBaseData;
import com.os.osframe.core.users.domain.MscUsersInfo;
import com.os.osframe.core.users.service.IMscUsersInfoService;
import com.os.osframe.core.users.util.MscUsersTypeConstant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangchun on 16/5/22.
 */
@Service("mscUsersOrgTreeService")
public class MscUsersOrgTreeService implements IBaseData {
    @Resource
    IMscUsersInfoService mscUsersInfoService;

    public List getData(HttpServletRequest request) throws Exception {
        String lv=request.getParameter("lv");
        String id=request.getParameter("id");
        //获取子集部门
        List<MscUsersInfo> list=mscUsersInfoService.getChildDeptList(id);
        List reList=new ArrayList();
        for(MscUsersInfo mscUsersInfo:list){
            Map jsonObject=new HashMap();
            jsonObject.put("id",mscUsersInfo.getPkId());
            jsonObject.put("name",mscUsersInfo.getLbName());
            jsonObject.put("type",mscUsersInfo.getLbType());
            //根据当前部门和机构去获取其是否存在子集部门或机构

            List<MscUsersInfo> hasChild=mscUsersInfoService.getChildDeptList(mscUsersInfo.getPkId());
            if(hasChild==null || hasChild.isEmpty()){
                jsonObject.put("isParent",false);
            }else{
                jsonObject.put("isParent",true);
            }
            if(MscUsersTypeConstant.USERS_TYPE_ORG.equals(mscUsersInfo.getLbType())){
                jsonObject.put("iconSkin","pIcon01");
            }else{
                jsonObject.put("iconSkin","icon10");
            }

            reList.add(jsonObject);
        }
        return reList;
    }
}
