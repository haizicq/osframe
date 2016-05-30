<%@ page import="com.os.osframe.frame.util.ResourceUtil" %>
<%@ page import="com.os.osframe.frame.util.CurrentUserUtil" %>
<%@ page import="com.os.osframe.frame.interfaces.IUserModel" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="bs" uri="http://www.bladeray.com/tags/bs" %>
<%
  //全局路径文件
  String rootPath=request.getContextPath();
  String cdnUrl= ResourceUtil.getCdnUrl();//"http://localhost:8080/cdn/";
  String rootRes=null;
  if(cdnUrl==null || "".equals(cdnUrl)){
    rootRes=rootPath+"/common";
  }else{
    if(cdnUrl.substring(cdnUrl.length()-1).equals("/")){
      rootRes=cdnUrl+"common";
    }else{
      rootRes=cdnUrl+"/common";
    }
  }
  request.setAttribute("rootPath",rootPath);//项目根路径
//  request.setAttribute("rootRes",rootPath+"/common");//资源文件路径 默认取本地的，也可以取CDN中的
  request.setAttribute("rootRes",rootRes);//资源文件路径 默认取本地的，也可以取CDN中的
  request.setAttribute("rootTemplate",rootPath+"/common/template");
  //获取当前用户信息
  IUserModel user=CurrentUserUtil.getUser();
  if(user!=null){
    request.setAttribute("userName",user.getName());
  }
%>
