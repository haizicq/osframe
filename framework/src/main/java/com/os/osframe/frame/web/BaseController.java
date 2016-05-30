package com.os.osframe.frame.web;

import com.os.osframe.frame.common.*;
import com.os.osframe.frame.convert.DateEditor;
import com.os.osframe.frame.domain.IBaseDomain;
import com.os.osframe.frame.interfaces.IAuthService;
import com.os.osframe.frame.log.LogInfo;
import com.os.osframe.frame.log.OutException;
import com.os.osframe.frame.service.IBaseService;
import com.os.osframe.frame.util.CurrentUserUtil;
import com.os.osframe.frame.util.ModuleBeanUtil;
import com.os.osframe.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * 所有Controller的基类
 * </pre>
 * @see
 */
@Controller
public abstract class BaseController<T> {
    protected LogInfo logger=new LogInfo(this.getClass());
    /**
     * 获取Request
     * @return
     */
    protected  HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    /**
     * 获取服务层
     */
    protected abstract IBaseService getServiceImp();

    /**
     * 表单中其他格式传入前先解析
     *  如时间类型
     * @param request
     * @param binder
     * @throws Exception
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        //对于需要转换为Date类型的属性，使用DateEditor进行处理
        binder.registerCustomEditor(Date.class, new DateEditor());
        //获取实体类，然后初始化去获取其特定的转换方式
        IBaseDomain baseDomain=(IBaseDomain)getServiceImp().getBaseDao().getDomainClass().newInstance();
        baseDomain.convertFields( request,  binder);

    }

    /**
     * 域模型列表
     *
     * @param request
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(HttpServletRequest request, Model model) throws Exception{
        OutException outException=new OutException("list");
        try{
            String pageNoStr = request.getParameter("pageNo");
            String rowSizeStr = request.getParameter("rowSize");
            String orderByStr = request.getParameter("orderBy");
            String orderTypeStr = request.getParameter("orderType");

            if (StringUtil.isNotNull(orderByStr)  && !"null".equals(orderByStr) && StringUtil.isNotNull(orderTypeStr)  && !"null".equals(orderTypeStr) && orderTypeStr.equalsIgnoreCase("down")) {
                orderByStr += " desc";
            }
            int pageNo = 1;
            int rowSize = 0;
            if (StringUtil.isNotNull(pageNoStr) && !"null".equals(pageNoStr)) {
                pageNo = Integer.parseInt(pageNoStr);
            }
            if (StringUtil.isNotNull(rowSizeStr) && !"null".equals(rowSizeStr)) {
                rowSize = Integer.parseInt(rowSizeStr);
            }
            HqlObject hqlObject=new HqlObject();
            if(StringUtil.isNotNull(orderByStr)  && !"null".equals(orderByStr)){
                hqlObject.setOrderByExtract(orderByStr);
            }
            hqlObject.setPageNo(pageNo);
            if(rowSize>0){
                hqlObject.setRowSize(rowSize);
            }
            changeSearchHqlObject(request,hqlObject);
            //String hql=changeSearchHql(request);

            //获取权限服务实现类
            IAuthService authService= ModuleBeanUtil.getInstance().getAuthService();
            if(authService!=null){//首先要拥有权限服务类，否则就无需进行权限校验
                DomainInfo domainInfo=this.getDomainInfo();
                authService.changeAuthHql(hqlObject,domainInfo,getServiceImp().getBaseDao().getDomainClass());
            }
            PageInfo pageInfoList=getServiceImp().findPageInfo(hqlObject);
            model.addAttribute("pageInfoList",pageInfoList);//页面信息列表统一采用pageInfoList
        }catch (Exception e){
            outException.setError(e);
        }

        logger.executeTime(outException);
        if(outException.hasError()){
            logger.error(outException);
            if(CurrentUserUtil.isDeveloper()){//校验是否开发者，非开发者则不返回错误信息了,仅有
                model.addAttribute("errorInfo",outException.toString());
            }
            return forwardError();//错误文件存在资源文件位置
        }
        return getDomainInfo().getPathPix()+"list";//路径获取为通用
    }

    /**
     * 修改查询hql语句
     *      继承类可通过修改hql直接调整查询
     * @param request
     * @return hql语句
     * @throws Exception
     */
    protected String changeSearchHql(HttpServletRequest request) throws Exception{
        return null;
    }
    /**
     * 修改查询sql语句
     *      继承类可通过修改hql直接调整查询
     * @param request
     * @return hql语句
     * @throws Exception
     */
    protected void changeSearchHqlObject(HttpServletRequest request,HqlObject hqlObject) throws Exception{
    }
    /**
     * 查看
     * @param model
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequiresAuth(role="LOOK",seat = RequiresAuth.SeatType.COMMON,type= RequiresAuth.RoleType.READER)
    @RequestMapping(value="/look")
    public String look(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
        OutException outException=new OutException("look");
        try {
            String id = request.getParameter("id");
            if (StringUtil.isNotNull(id)) {
                IBaseDomain baseDomain = (IBaseDomain)getServiceImp().findById(id);
                model.addAttribute(getDomainInfo().getSimpleName(), baseDomain);
            }
        }catch (Exception e){
            outException.setError(e);
        }

        logger.executeTime(outException);
        if(outException.hasError()){
            logger.error(outException);
            if(CurrentUserUtil.isDeveloper()){//校验是否开发者，非开发者则不返回错误信息了,仅有
                model.addAttribute("errorInfo",outException.toString());
            }
            return forwardError();//错误文件存在资源文件位置
        }
        return getDomainInfo().getPathPix()+"look";//路径获取为通用
    }
    /**
     * 新增/修改save操作
     *
     * @param model
     * @param domain
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequiresAuth(role="EDIT",seat = RequiresAuth.SeatType.COMMON,type= RequiresAuth.RoleType.EDIT)
    @RequestMapping("/update")
    public String saveOrUpdate(Model model,T domain,HttpServletRequest request,HttpServletResponse response) throws Exception{
        OutException outException=new OutException("update");
        JsonDataFormat jsonDataFormat=new JsonDataFormat();
       try{
           if (!request.getMethod().equals("POST")){//禁止直接通过url访问
               jsonDataFormat.setCommonCode(JsonDataFormat.JSON_ERROR_CODE_POST);
               return jsonDataFormat.toString();
           }
           getServiceImp().saveOrUpdate(domain,request,model);
       }catch (Exception e){
           outException.setError(e);
           jsonDataFormat.setCommonCode(JsonDataFormat.JSON_ERROR_CODE_SERVICE, e.getMessage());
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
    /**
     * 新增/修改save操作
     *
     * @param model
     * @param domain
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequiresAuth(role="ADD",seat = RequiresAuth.SeatType.COMMON)
    @RequestMapping("/save")
    public String save(Model model,T domain,HttpServletRequest request,HttpServletResponse response) throws Exception{
        OutException outException=new OutException("save");
        JsonDataFormat jsonDataFormat=new JsonDataFormat();
        try{
            if (!request.getMethod().equals("POST")){//禁止直接通过url访问
                jsonDataFormat.setCommonCode(JsonDataFormat.JSON_ERROR_CODE_POST);
                return jsonDataFormat.toString();
            }
            getServiceImp().saveOrUpdate(domain,request,model);
        }catch (Exception e){
            outException.setError(e);
            jsonDataFormat.setCommonCode(JsonDataFormat.JSON_ERROR_CODE_SERVICE, e.getMessage());
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
    /**
     * 进入修改页面
     */
    @RequiresAuth(role="EDIT",seat = RequiresAuth.SeatType.COMMON,type= RequiresAuth.RoleType.EDIT)
    @RequestMapping(value="/edit")
    public String edit(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
        OutException outException=new OutException("edit");
        try {
            String id = request.getParameter("id");
            System.out.println("id=" + id);
            if (StringUtil.isNotNull(id)) {
                IBaseDomain baseDomain = (IBaseDomain) getServiceImp().findById(id);
                model.addAttribute(getDomainInfo().getSimpleName(), baseDomain);
            }
        }catch (Exception e){
            outException.setError(e);
        }

        logger.executeTime(outException);
        if(outException.hasError()){
            logger.error(outException);
            if(CurrentUserUtil.isDeveloper()){//校验是否开发者，非开发者则不返回错误信息了,仅有
                model.addAttribute("errorInfo",outException.toString());
            }
            return forwardError();//错误文件存在资源文件位置
        }
        return getDomainInfo().getPathPix()+"edit";//路径获取为通用
    }

    /**
     * 删除操作
     */
    @ResponseBody
    @RequiresAuth(role="DELETE",seat = RequiresAuth.SeatType.COMMON,type= RequiresAuth.RoleType.EDIT)
    @RequestMapping(value="/delete")
    public String delete(HttpServletRequest request, Model model) throws Exception {
        // 执行删除
        OutException outException=new OutException("delete");
        JsonDataFormat jsonDataFormat=new JsonDataFormat();
        try {
            if (!request.getMethod().equals("POST")){//禁止直接通过url访问
                jsonDataFormat.setCommonCode(JsonDataFormat.JSON_ERROR_CODE_POST);
                return jsonDataFormat.toString();
            }
            String id=request.getParameter("id");
            if(StringUtil.isNotNull(id)){
                getServiceImp().delete(id,request,model);

            }else{
                jsonDataFormat.setCommonCode(JsonDataFormat.JSON_ERROR_CODE_PARAM,"id");
            }
        } catch (Exception e){
            outException.setError(e);
            jsonDataFormat.setCommonCode(JsonDataFormat.JSON_ERROR_CODE_SERVICE, e.getMessage());
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

    /**
     * 删除多条记录
     *
     * @param request
     * @param model
     * @return
     * @author Mr.Hao<Auto generate>
     * @version  2013-07-29 11:36:46
     */
    @ResponseBody
    @RequiresAuth(role="DELETE",seat = RequiresAuth.SeatType.COMMON)
    @RequestMapping("/delmul")
    public String deleteMul(HttpServletRequest request, Model model) throws Exception{
        OutException outException=new OutException("deleteMul");
        JsonDataFormat jsonDataFormat=new JsonDataFormat();
       try{
           if (!request.getMethod().equals("POST")){//禁止直接通过url访问
               jsonDataFormat.setCommonCode(JsonDataFormat.JSON_ERROR_CODE_POST);
               return jsonDataFormat.toString();
           }
            String[] ids = request.getParameterValues("ids");
           if(ids!=null && ids.length>0){
                for(int i=0,len=ids.length;i<len;i++){
                    getServiceImp().delete((String)ids[i],request,model);
                }
           }else{
               jsonDataFormat.setCommonCode(JsonDataFormat.JSON_ERROR_CODE_PARAM,"ids");
           }
       }catch (Exception e){
           outException.setError(e);
           jsonDataFormat.setCommonCode(JsonDataFormat.JSON_ERROR_CODE_SERVICE, e.getMessage());
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
    @RequiresAuth(role="ADD",seat = RequiresAuth.SeatType.COMMON)
    @RequestMapping("/add")
    public String add(Model model,HttpServletRequest request,HttpServletResponse response)throws Exception {
        OutException outException=new OutException("add");
        try {
            IBaseDomain baseDomain = initDomain(request, response);
            if (null != baseDomain)
                model.addAttribute(getDomainInfo().getSimpleName(), baseDomain);
        } catch (Exception e){
            outException.setError(e);
        }

        logger.executeTime(outException);
        if(outException.hasError()){
            logger.error(outException);
            if(CurrentUserUtil.isDeveloper()){//校验是否开发者，非开发者则不返回错误信息了,仅有
                model.addAttribute("errorInfo",outException.toString());
            }
            return forwardError();//错误文件存在资源文件位置
        }
        return getDomainInfo().getPathPix()+"edit";//路径获取为通用
    }

    /**
     * 初始化表单数据
     * @param request
     * @return
     */
    protected  IBaseDomain initDomain(HttpServletRequest request,HttpServletResponse response)throws  Exception{
        IBaseDomain baseDomain=(IBaseDomain)getServiceImp().getBaseDao().getDomainClass().newInstance();
        baseDomain.init();
        return baseDomain;
    }
    /**
     * 获取默认的模型的类名
     * @return
     */
    protected DomainInfo getDomainInfo() {
        DomainInfo domainInfo= getServiceImp().getDomainInfo();
        return domainInfo;
    }
    protected final static String Controller_viewType_res="res";
    protected final static String Controller_viewType_root="root";
    /**
     * 所有跳转方法的函数
     *      支持格式：
     *          1、前缀+方法
     *          2、模块前缀+方法
     * @param method 跳转的方法名
     * @param viewType 跳转视图类型
     *                 普通视图为空，res 资源文件，root为根项目文件
     * @return
     */
    protected String forward(String method,String viewType) {
        String forward=null;
        if(StringUtil.isNull(method)){//当为空时跳转到系统主页
            forward="index";
        }else if("index".equals(method)){
                forward= getDomainInfo().getModulePath()+"/"+getDomainInfo().getModuleSimpleName()+"_index";
        }else if(StringUtil.isNotNull(viewType)){
            if(viewType.toLowerCase().equals("res")){
                forward=UrlRouteUtil.getResView()+method;
            }else if(viewType.toLowerCase().equals("root")){
                forward=UrlRouteUtil.getRootView()+method;
            }
        }else{
            forward=getDomainInfo().getPathPix()+method;
        }
       return forward;
    }

    /**
     * 所有跳转方法的函数
     * @param method
     * @return
     */
    protected String forward(String method) {
        return forward(method,null);
    }

    /**
     * 跳转到错误页面
     * @return
     */
    protected String forwardError(){
        return "forward:/common/jsp/error.jsp";
    }
    /**
     * 跳转到成功页面
     * @return
     */
    protected String forwardSuccess(){
        return "forward:/common/jsp/success.jsp";
    }
    /**
     * 直接访问jsp文件
     * @param request
     * @param redirect
     * @return
     * @throws Exception
     */
    @RequiresAuth
    @RequestMapping("/jsp/{redirect}")
    public String redirect(HttpServletRequest request,@PathVariable String redirect)throws Exception {
        int i=StringUtil.indexOf(redirect,"edit|index|look|list|upload");
        System.out.println("==============i="+i);
        if(i>-1){
            return null;//默认的jsp格式包含了这些字符时，不允许直接返回
        }
        return getDomainInfo().getModulePath()+"/"+redirect;//路径获取为通用
    }

}
