package com.os.osframe.frame.log;

import com.os.osframe.frame.util.ResourceUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;

/**
 * 类的日志输出
 * Created by wangchun on 16/4/28.
 */
public class LogInfo {
    protected Log logger;

    protected Class clazz;
    public LogInfo(Class clazz){
        logger = LogFactory.getLog(clazz);
        this.clazz=clazz;
    }

    /**
     * 获取日志对象
     * @return
     */
    public Log getLogger() {
        return logger;
    }

    /**
     * 记录debug类的日志
     * @param msg
     */
    public void debug(Object msg){
        this.debug(msg,null);
    }
    /**
     * 记录debug类的日志
     * @param msg
     * @param e
     */
    public void debug(Object msg,Throwable e){
        if(!logger.isDebugEnabled()){
           return;
        }
        if(e!=null){
            logger.debug(msg,e);
        }else{
            logger.debug(msg);
        }

    }

    /**
     * 记录info日志
     * @param msg
     */
    public void info(Object msg){
        this.info(msg,null);
    }
    /**
     * 记录info日志
     * @param msg
     * @param e
     */
    public void info(Object msg,Throwable e){
        if(!logger.isInfoEnabled()){
            return;
        }

        if(e!=null){
            logger.info(msg, e);
        }else{
            logger.info(msg);
        }
    }

    /**
     * 记录警告日志
     * @param msg
     */
    public void warn(Object msg){
        this.warn(msg,null);
    }

    /**
     * 记录警告日志
     * @param msg
     * @param e
     */
    public void warn(Object msg,Throwable e){
        if(!logger.isWarnEnabled()){
            return;
        }
        if(e!=null){
            logger.warn(msg, e);
        }else{
            logger.warn(msg);
        }
    }

    /**
     * 记录错误日志
     * @param msg
     */
    public void error(Object msg){
        this.error(msg,null);
    }
    /**
     * 记录错误日志
     * @param e
     */
    public void error(OutException e){
        this.error(e.getError());
    }
    /**
     * 记录错误日志
     * @param msg
     * @param e
     */
    public void error(Object msg,Throwable e){
        if(!logger.isWarnEnabled()){
            return;
        }
        if(e!=null){
            logger.error(msg, e);
        }else{
            logger.error(msg);
        }
    }

    /**
     * 错误日志
     * @param e
     */
    public void error(Throwable e){
        if(!logger.isWarnEnabled()){
            return;
        }
        String msg=ResourceUtil.getResourceKey("error.code.other")+"  "+e.getMessage();
        logger.error(msg, e);
    }
    /**
     * 记录方法执行时间
     * @param outException 创建异常开始时间
     */

    public  void executeTime(OutException outException){
        if (!logger.isDebugEnabled() || outException==null || outException.getDate()==null)
            return;
        Date now = new Date();
        StringBuffer timeInfo = new StringBuffer("=========类").append(this.clazz.getName()).append(" 的方法 ").append(outException.getMethod()).append(" 从 ")
                .append(outException.getDate()).append(" 开始执行 ").append(now).append(" 结束，共计耗时：")
                .append(now.getTime() - outException.getDate().getTime()).append("====");
        logger.debug(timeInfo);
    }

}
