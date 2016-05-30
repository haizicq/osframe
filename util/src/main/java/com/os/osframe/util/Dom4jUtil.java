package com.os.osframe.util;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.util.List;

/**
 * Created by wangdc on 2014-5-10.
 */
public class Dom4jUtil {
    /**
     * 根据路径加载xml
     *
     * @param path 路径，相对于根目录路径
     * @return
     */
    public static Document getDocment(String path) {
        Document document = null;
        try {
            URL classUrl = Thread.currentThread().getContextClassLoader().getResource(
                    Dom4jUtil.class.getName().replace('.', '/') + ".class");
            String xmlPath = classUrl.getPath().substring(0, classUrl.getPath().lastIndexOf("/WEB-INF/"));
            if("/".equals(path.substring(0,1))){
                xmlPath+=path;
            }else{
                xmlPath+="/"+path;
            }
            //String xmlPath = webContentPath + "/WEB-INF/KmssConfig/sys/log/logconfig.xml";
            File file = new File(xmlPath);
            SAXReader saxReader = new SAXReader();// 用来读取xml文档
            document = saxReader.read(file);// 读取xml文档
        }catch (Exception e){
            e.printStackTrace();
        }
        return document;
    }

    /**
     * 获取跟节点信息
     * @param document
     * @return
     */
    public static Element getRootElement(Document document){
        Element rootElement=document.getRootElement();
        return rootElement;
    }

    /**
     * 查询节点信息
     * @param document
     * @param exp
     * @return
     */
    public static Element getElement(Document document,String exp){
        return getElement(getRootElement(document),exp);
    }

    /**
     * 查询节点信息
     * @param element
     * @param exp
     * @return
     */
    public static Element getElement(Element element,String exp){
        List list=element.selectNodes(exp);
        if(list!=null && !list.isEmpty()){
            Element moduleElement=(Element)list.get(0);
            return moduleElement;
        }
        return null;
    }
    /**
     * 把输入字节流转换成可读取的xml对象
     *
     * @param in 输入字节流
     * @return
     */
    public static Document getDocment(InputStream in) {

        SAXReader reader = new SAXReader();
        Document document = null;

        try {
            document = reader.read(in);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return document;
    }

    /**
     * 把输入字符流换成可读取的xml对象
     *
     * @param reader
     *            输入字符流
     * @return
     */
    public static Document getDocment(Reader reader) {
        SAXReader saxReader = new SAXReader();
        Document document = null;

        try {
            document = saxReader.read(reader);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return document;
    }
}
