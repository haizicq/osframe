package com.os.osframe.frame.log;

import com.os.osframe.util.StringUtil;

import java.util.Date;

/**
 * Created by wangchun on 16/4/28.
 */
public class OutException {
    protected Date date;
    protected String method;
    protected Throwable error;
    protected Boolean isError=false;
    public OutException(){//默认一个创建异常对象的时间
        date=new Date();
    }

    public OutException(String method){//默认一个创建异常对象的时间
        date=new Date();
        this.method=method;
    }
    public void setError(Throwable e){
        this.error=e;
        this.isError=true;
    }
    public boolean hasError(){
        return this.isError;
    }
    public Throwable getError(){
        return this.error;
    }

    public Date getDate() {
        return date;
    }

    public String getMethod() {
        return method;
    }

    /**
     * 将错误信息转为字符串输出
     * @return
     */
    public  String toString() {

        StringBuffer buffer = new StringBuffer();
        if(this.error!=null){
            recursiveGetStack(buffer,this.error,  0);
        }
        return buffer.toString();
    }

    /**
     * 递归获取堆栈信息
     * @param buffer
     * @param e
     * @param num
     */
    private  void recursiveGetStack(StringBuffer buffer,Throwable e,int num) {
        String strMsg = e.toString();
        if (StringUtil.isNotNull(strMsg)) {
            buffer.append(strMsg);
            buffer.append("\r\n\t");
        }
        StackTraceElement[] stack = e.getStackTrace();
        if (stack.length > 0) {
            buffer.append(stack[0].toString());
            for (int i = 1; i < stack.length; i++) {
                buffer.append("\r\n\t");
                buffer.append(stack[i].toString());
            }
        }
        Throwable cause=e.getCause();
        if (cause != null) {
            if (num > 15) {
                buffer.append("\r\n:" + cause);
            } else {
                buffer.append("\r\n:");
                recursiveGetStack(buffer,cause, ++num);
            }
        }
    }
}
