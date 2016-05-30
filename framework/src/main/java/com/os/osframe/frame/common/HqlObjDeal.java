package com.os.osframe.frame.common;

import com.os.osframe.util.StringUtil;

/**
 * 无权限过滤的HQL语句的拼装器。
 */
public class HqlObjDeal {
    /**
     * 处理hql对象转换为hql语句
     * @param hqlObject
     * @return
     * @throws Exception
     */
	public static String dealHqlObj(HqlObject hqlObject) throws Exception {
		String hql = null;
		if (hqlObject.getDistinctType() == HqlObject.NOT_REPEAT)
            hql = buildDistinctHQL(hqlObject, hqlObject.getWhereExtract());
		else
            hql = buildNormalHQL(hqlObject, hqlObject.getWhereExtract());
		return hql;
	}

    /**
     * 构造去重的hql语句
     * @param hqlObject
     * @param hqlWhere
     * @return
     * @throws Exception
     */
	private static String buildDistinctHQL(HqlObject hqlObject, String hqlWhere)throws Exception {
		StringBuffer hql = new StringBuffer();
        String domainSimpleName=hqlObject.getDomainInfo().getSimpleName();
		if (hqlObject.isSearchCount()) {
			hql.append("select count(distinct " + domainSimpleName+ ".pkId) ");
			hql.append("from " +hqlObject.getDomainInfo().getPackageName() + " "+domainSimpleName+ " ");
		} else {
            if (StringUtil.isNotNull(hqlObject.getSelectExtract()))
				hql.append("select " + hqlObject.getSelectExtract()+ " ");
			hql.append("from " +hqlObject.getDomainInfo().getPackageName() + " " + domainSimpleName+ " ");
			hql.append("where " + domainSimpleName + ".pkId in (");//当查询语句过大时是否存在性能问题 待测
			hql.append("select " + domainSimpleName+ ".pkId ");
			if (StringUtil.isNull(hqlObject.getFromExtract()))
				hql.append("from " + hqlObject.getDomainInfo().getPackageName() + " " +domainSimpleName + " ");
			else
				hql.append("from " + hqlObject.getFromExtract() + " ");
		}

		if (!StringUtil.isNull(hqlObject.getJoinExtract()))
			hql.append(hqlObject.getJoinExtract() + " ");

		if (!StringUtil.isNull(hqlWhere))
			hql.append("where " + hqlWhere);

		if (!hqlObject.isSearchCount()) {
			hql.append(")");
			if (!StringUtil.isNull(hqlObject.getOrderByExtract()))
				hql.append(" order by "+ hqlObject.getOrderByExtract());
		}
		return hql.toString();
	}

    /**
     * 直接查询
     * @param hqlObject
     * @param hqlWhere
     * @return
     * @throws Exception
     */
	private static String buildNormalHQL(HqlObject hqlObject, String hqlWhere)
			throws Exception {
		StringBuffer hql = new StringBuffer();
        String domainSimpleName=hqlObject.getDomainInfo().getSimpleName();
		if (hqlObject.isSearchCount()) {
			hql.append("select count(*) ");
			if (StringUtil.isNull(hqlObject.getFromExtract()))
                hql.append("from " +hqlObject.getDomainInfo().getPackageName() + " "+domainSimpleName+ " ");
			else
				hql.append("from " + hqlObject.getFromExtract() + " ");
		} else {
			if (StringUtil.isNotNull(hqlObject.getSelectExtract()))
				hql.append("select " + hqlObject.getSelectExtract() + " ");

			if (StringUtil.isNull(hqlObject.getFromExtract()))
                hql.append("from " +hqlObject.getDomainInfo().getPackageName() + " "+domainSimpleName+ " ");
			else
				hql.append("from " + hqlObject.getFromExtract() + " ");
		}

		if (!StringUtil.isNull(hqlObject.getJoinExtract()))
			hql.append(hqlObject.getJoinExtract() + " ");

		if (!StringUtil.isNull(hqlWhere))
			hql.append("where " + hqlWhere + " ");

		if (!hqlObject.isSearchCount()
				&& !StringUtil.isNull(hqlObject.getOrderByExtract()))
			hql.append("order by " + hqlObject.getOrderByExtract());
		return hql.toString();
	}

}
