package com.os.osframe.core.users.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.os.osframe.frame.common.HqlObject;
import com.os.osframe.frame.common.RequiresAuth;
import com.os.osframe.frame.common.RequiresClassAuth;
import com.os.osframe.frame.service.IBaseService;
import com.os.osframe.frame.web.BaseController;
import com.os.osframe.core.users.domain.MscUsersInfo;
import com.os.osframe.core.users.service.IMscUsersInfoService;
import com.os.osframe.core.users.util.MscUsersTypeConstant;
import com.os.osframe.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 组织基础信息
 * Created by wangdc on 2016-03-01 23:39.
 */
@Controller
@RequestMapping(value = "/core/users/mscUsersInfo")
@RequiresClassAuth(common= RequiresClassAuth.CommonType.CONTROL)
public class MscUsersInfoController extends BaseController<MscUsersInfo> {
    @Resource
    IMscUsersInfoService mscUsersInfoService;
    @Override
    protected IBaseService getServiceImp() {
        return mscUsersInfoService;
    }

    @RequestMapping({"/treeList"})
    public String treeList(HttpServletRequest request) throws Exception {
        return this.getDomainInfo().getModulePath() + "/mscUsersInfo/mscUsers_tree";
    }
    @RequiresAuth
    @ResponseBody
    @RequestMapping({"/tree"})
    public String tree(HttpServletRequest request) throws Exception {

        String lv=request.getParameter("lv");
        String id=request.getParameter("id");
        //获取子集部门
        List<MscUsersInfo> list=mscUsersInfoService.getChildDeptList(id);
        JSONArray jsonArray=new JSONArray();
        for(MscUsersInfo mscUsersInfo:list){
            JSONObject jsonObject=new JSONObject();
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

            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }

    @Override
    protected void changeSearchHqlObject(HttpServletRequest request, HqlObject hqlObject) throws Exception {
        super.changeSearchHqlObject(request, hqlObject);
        String id=request.getParameter("id");
        String where="1=1";
        if(StringUtil.isNotNull(id)){
            where+=" and mscUsersInfo.lbParent.pkId =:pid";
            hqlObject.setParameter("pid",id);
        }else{
            where+=" and mscUsersInfo.lbParent.pkId is null";
        }
        String searchKey=request.getParameter("searchKey");
        if(StringUtil.isNotNull(searchKey)){
            where+=" and mscUsersInfo.lbName like :searchKey";
            hqlObject.setParameter("searchKey","%"+searchKey+"%");
        }
        hqlObject.setOrderByExtract("  mscUsersInfo.lbType asc,mscUsersInfo.lbOrder asc,mscUsersInfo.lbCreateTime desc");

        hqlObject.setWhereExtract(StringUtil.linkString(hqlObject.getWhereExtract()," and ",where));
    }

}
