package com.os.osframe.core.taglib.bs;

import com.os.osframe.frame.util.ResourceUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**
 * 获取国际化编码
 * Created by wangchun on 16/4/26.
 */
public class I18nTag  extends BodyTagSupport {
    /**
     * 资源代码
     */
    protected String key = null;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void release()
    {
        super.release();
        this.key = null;
    }

    @Override
    public int doStartTag() throws JspException {
        return 1;
    }

    @Override
    public int doEndTag() throws JspException {
        this.outEndTag();
        return super.doEndTag();
    }


    /**
     * 输出结束标签
     *
     * @throws javax.servlet.jsp.JspException
     */
    public void outEndTag()
            throws JspException {
        JspWriter writer = pageContext.getOut();
        try {
            String message= ResourceUtil.getResourceKey(key);
            writer.print(message);

        } catch (Exception ex) {
            throw new JspException(ex);
        }
    }
}
