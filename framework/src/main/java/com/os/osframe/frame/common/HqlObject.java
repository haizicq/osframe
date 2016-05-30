package com.os.osframe.frame.common;

import org.hibernate.type.Type;

import java.util.ArrayList;
import java.util.List;


/**
 * HQL对象
 *  便于统一处理，减少hql语句书写
 */
public class HqlObject implements  Cloneable {

	/**
	 * 全局常量：允许重复
	 */
	public final static int ALLOW_REPEAT = 0;
	/**
	 * 全局常量：不允许重复
	 */
	public final static int NOT_REPEAT = 1;

	private int distinctType = ALLOW_REPEAT;
    /**
     * from片段
     */
	private String fromExtract = null;
    /**
     * 是否查询数量
     */
	private boolean isSearchCount = false;

	private String joinExtract = null;
    /**
     * 域模型信息
     */
    private DomainInfo domainInfo=null;

	private String orderByExtract = null;

	private int pageNo = 1;

	private List<HqlParam> paramList = new ArrayList<HqlParam>();

	private int rowSize = 15;

	private String selectExtract = null;

	private String whereExtract = null;
	//是否返回map 仅在find 方法中
	private boolean isReMap = false;

    /**
     * 克隆语句对象
     * @return
     * @throws CloneNotSupportedException
     */
	public Object clone() throws CloneNotSupportedException {
		HqlObject hqlObject = (HqlObject) super.clone();
		hqlObject.paramList = new ArrayList<HqlParam>(paramList);
		return hqlObject;
	}
	public int getDistinctType() {
		return distinctType;
	}

	public String getFromExtract() {
		return fromExtract;
	}

	public String getJoinExtract() {
		return joinExtract;
	}




	public String getOrderByExtract() {
		return orderByExtract;
	}

	public int getPageNo() {
		return pageNo;
	}

	public List<HqlParam> getParamList() {
		return paramList;
	}

	public int getRowSize() {
		return rowSize;
	}

	public String getSelectExtract() {
		return selectExtract;
	}

	public String getWhereExtract() {
		return whereExtract;
	}


	public boolean isSearchCount() {
		return isSearchCount;
	}


	/**
	 * 设置查询时是否去除重复
	 * @param distinctType
	 */
	public void setDistinctType(int distinctType) {
		this.distinctType = distinctType;
	}

	/**
	 * 设置from语句
	 * @param fromExtract
	 */
	public void setFromExtract(String fromExtract) {
		this.fromExtract = fromExtract;
	}

	public void setIsSearchCount(boolean isSearchCount) {
		this.isSearchCount = isSearchCount;
	}


	/**
	 * 设置join语句
	 * 
	 * @param joinExtract
	 */
	public void setJoinExtract(String joinExtract) {
		this.joinExtract = joinExtract;
	}



	/**
	 * 设置排序字段
	 * 
	 * @param orderByExtract
	 */
	public void setOrderByExtract(String orderByExtract) {
		this.orderByExtract = orderByExtract;
	}

	/**
	 * 设置从第几页开始显示
	 * 
	 * @param pageNo
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public void setParameter(String key, Object value) {
		paramList.add(new HqlParam(key, value));
	}

	public void setParameter(String key, Object value, Type type) {
		paramList.add(new HqlParam(key, value, type));
	}

	public void setParameter(List<HqlParam> paramList) {
		this.paramList.addAll(paramList);
	}

	/**
	 * 设置每页显示多少条记录
	 * 
	 * @param rowSize
	 */
	public void setRowSize(int rowSize) {
		this.rowSize = rowSize;
	}

	/**
	 * 设置select语句，不设置则返回域模型
	 * 
	 * @param selectExtract
	 */
	public void setSelectExtract(String selectExtract) {
		this.selectExtract = selectExtract;
	}

	/**
	 * 设置where语句
	 * 
	 * @param whereExtract
	 */
	public void setWhereExtract(String whereExtract) {
		this.whereExtract = whereExtract;
	}

    public DomainInfo getDomainInfo() {
        return domainInfo;
    }

    /**
     * 设置域模型
     * @param domainInfo
     */
    public void setDomainInfo(DomainInfo domainInfo) {
        this.domainInfo = domainInfo;
    }
    /**
     * 设置域模型
     * @param clazz 域模型类
     */
    public void setDomainInfo(Class clazz) {
        DomainInfo domainInfo=new DomainInfo(clazz);
        this.domainInfo = domainInfo;
    }

	public boolean isReMap() {
		return isReMap;
	}

	public void setIsReMap(boolean isReMap) {
		this.isReMap = isReMap;
	}
}
