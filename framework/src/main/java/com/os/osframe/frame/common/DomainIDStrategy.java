package com.os.osframe.frame.common;

import java.util.Date;
import java.util.UUID;

/**
 * ID主键生成策略
 */
public class DomainIDStrategy {
	/**
	 * 生成主键（32位）
	 * @return
	 */
	public static String strategyID() {
		return strategyID(System.currentTimeMillis());
	}

	/**
	 * 根据指定时间生成主键
	 * @param date 时间
	 * @return
	 */
	public static String strategyID(Date date) {
		return strategyID(date.getTime());
	}

	/**
	 * 根据指定时间生成主键
	 * @param time
	 * @return
	 */
	public static String strategyID(long time) {
		String rtnVal = Long.toHexString(time);
		rtnVal += UUID.randomUUID();
		rtnVal = rtnVal.replaceAll("-", "");
		return rtnVal.substring(0, 32);
	}

	/**
	 * 查看主键生成时间
	 * @param id
	 */
	protected static void printDate(String id) {
		String timeInfo = id.substring(0, 11);
		System.out.println(new Date(Long.parseLong(timeInfo, 16)));
	}

	/**
	 * 根据ID获取该ID创建的时间
	 * @param id
	 * @return
	 */
	public static Date getCreateDate(String id) {
		String timeInfo = id.substring(0, 11);
		return new Date(Long.parseLong(timeInfo, 16));
	}

}
