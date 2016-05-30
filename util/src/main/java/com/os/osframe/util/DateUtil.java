package com.os.osframe.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期,时间工具类
 * Created by wangdc on 2014-9-8.
 */
public class DateUtil {

    public static String DATETIME_FORMAT = "yyyy-MM-dd HH:mm";
    public static String DATE_FORMAT = "yyyy-MM-dd";


    /** 日期 */
    public final static String DEFAILT_DATE_PATTERN = "yyyy-MM-dd";
    /** 日期时间 */
    public final static String DEFAILT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /** 时间 */
    public final static String DEFAULT_TIME_PATTERN = "HH:mm:ss";
    /**
     * 每天的毫秒数
     */
    public final static long MILLIS_IN_DAY = 1000 * 60 * 60 * 24;
    /**
     * Get the previous time, from how many days to now.
     *
     * @param days
     *            How many days.
     * @return The new previous time.
     */
    public static Date previous(int days) {
        return new Date(System.currentTimeMillis() - days * 3600000L * 24L);
    }

    /**
     * Convert date and time to string like "yyyy-MM-dd HH:mm".
     */
    public static String formatDateTime(Date d) {
        return new SimpleDateFormat(DATETIME_FORMAT).format(d);
    }

    /**
     * Convert date and time to string like "yyyy-MM-dd HH:mm".
     */
    public static String formatDateTime(long d) {
        return new SimpleDateFormat(DATETIME_FORMAT).format(d);
    }

    /**
     * Parse date like "yyyy-MM-dd".
     */
    public static Date parseDate(String d) {
        try {
            return new SimpleDateFormat(DATE_FORMAT).parse(d);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * Parse date and time like "yyyy-MM-dd hh:mm".
     */
    public static Date parseDateTime(String dt) {
        try {
            return new SimpleDateFormat(DATETIME_FORMAT).parse(dt);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 转换日期字符串得到指定格式的日期类型
     *
     * @param formatString
     *            需要转换的格式字符串
     * @param targetDate
     *            需要转换的时间
     * @return
     * @throws ParseException
     */
    public static final Date convertStringToDate(String formatString,
                                                 String targetDate) throws ParseException {
        if (StringUtil.isNull(targetDate))
            return null;
        SimpleDateFormat format = null;
        Date result = null;
        format = new SimpleDateFormat(formatString);
        try {
            result = format.parse(targetDate);
        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }
        return result;
    }

    public static final Date convertStringToDate(String[] formatString,
                                                 String targetDate) throws ParseException {
        if (StringUtil.isNull(targetDate)) {
            return null;
        }
        SimpleDateFormat format = null;
        Date result = null;
        String errorMessage = null;
        Integer errorOffset = null;
        for (String dateFormat : formatString) {
            try {
                format = new SimpleDateFormat(dateFormat);
                result = format.parse(targetDate);
            } catch (ParseException pe) {
                result = null;
                errorMessage = pe.getMessage();
                errorOffset = pe.getErrorOffset();
            } finally {
                if (result != null && result.getTime() > 1) {
                    break;
                }
            }
        }
        if (result == null) {
            throw new ParseException(errorMessage, errorOffset);
        }
        return result;
    }

    /**
     * 转换字符串得到默认格式的日期类型
     *
     * @param strDate
     * @return
     * @throws ParseException
     */
    public static Date convertStringToDate(String strDate) throws ParseException {
        Date result = null;
        try {
            result = convertStringToDate(DEFAILT_DATE_PATTERN, strDate);
        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }
        return result;
    }

    /**
     * 转换日期得到指定格式的日期字符串
     *
     * @param formatString
     *            需要把目标日期格式化什么样子的格式。例如,yyyy-MM-dd HH:mm:ss
     * @param targetDate
     *            目标日期
     * @return
     */
    public static String convertDate2String(String formatString, Date targetDate) {
        SimpleDateFormat format = null;
        String result = null;
        if (targetDate != null) {
            format = new SimpleDateFormat(formatString);
            result = format.format(targetDate);
        } else {
            return null;
        }
        return result;
    }

    /**
     * 转换日期,得到默认日期格式字符串
     *
     * @param targetDate
     * @return
     */
    public static String convertDate2String(Date targetDate) {
        return convertDate2String(DEFAILT_DATE_PATTERN, targetDate);
    }

    /**
     * 比较日期大小
     *
     * @param src
     * @param src
     * @return int; 1:DATE1>DATE2;
     */
    public static int compare_date(Date src, Date src1) {

        String date1 = convertDate2String(DEFAILT_DATE_TIME_PATTERN, src);
        String date2 = convertDate2String(DEFAILT_DATE_TIME_PATTERN, src1);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     *  日期比较
     *
     * 判断时间date1是否在时间date2之前
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isDateBefore(String date1, String date2) {
        try {
            DateFormat df = DateFormat.getDateTimeInstance();
            return df.parse(date1).before(df.parse(date2));
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     *
     * 日期比较
     *
     * 判断当前时间是否在时间date2之前 <br/>
     * @param date2
     * @return
     */
    public static boolean isDateBefore(String date2) {
        if (date2 == null) {
            return false;
        }
        try {
            Date date1 = new Date();
            DateFormat df = DateFormat.getDateTimeInstance();
            return date1.before(df.parse(date2));
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 比较当前时间与时间date2的天相等 时间格式 2008-11-25 16:30:10 如:当前时间是2008-11-25
     * 16:30:10与传入时间2008-11-25 15:31:20 相比较,返回true即相等
     *
     * @param date2
     * @return boolean; true:相等
     * @author zhangjl
     */
    public static boolean equalDate(String date2) {
        try {
            String date1 = convertDate2String(DEFAILT_DATE_TIME_PATTERN,
                    new Date());
            date1.equals(date2);
            Date d1 = convertStringToDate(DEFAILT_DATE_PATTERN, date1);
            Date d2 = convertStringToDate(DEFAILT_DATE_PATTERN, date2);
            return d1.equals(d2);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * 比较时间date1与时间date2的天相等 时间格式 2008-11-25 16:30:10
     *
     * @param date1
     * @param date2
     * @return boolean; true:相等
     * @author zhangjl
     */
    public static boolean equalDate(String date1, String date2) {
        try {

            Date d1 = convertStringToDate(DEFAILT_DATE_PATTERN, date1);
            Date d2 = convertStringToDate(DEFAILT_DATE_PATTERN, date2);

            return d1.equals(d2);
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 比较时间date1是否在时间date2之前 时间格式 2008-11-25 16:30:10
     *
     * @param date1
     * @param date2
     * @return boolean; true:在date2之前
     * @author
     */
    public static boolean beforeDate(String date1, String date2) {
        try {
            Date d1 = convertStringToDate(DEFAILT_DATE_PATTERN, date1);
            Date d2 = convertStringToDate(DEFAILT_DATE_PATTERN, date2);
            return d1.before(d2);
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * 获取上个月开始时间
     *
     * @param currentDate
     *            当前时间
     * @return 上个月的第一天
     */
    public static Date getBoferBeginDate(Calendar currentDate) {
        Calendar result = Calendar.getInstance();
        result.set(currentDate.get(Calendar.YEAR), (currentDate
                .get(Calendar.MONTH)) - 1, result
                .getActualMinimum(Calendar.DATE), 0, 0, 0);
        return result.getTime();
    }

    /**
     * 获取指定月份的第一天
     *
     * @param currentDate
     * @return
     */
    public static Date getBeginDate(Calendar currentDate) {
        Calendar result = Calendar.getInstance();
        result.set(currentDate.get(Calendar.YEAR), (currentDate
                .get(Calendar.MONTH)), result.getActualMinimum(Calendar.DATE));
        return result.getTime();
    }


    /**
     * 获取上个月结束时间
     *
     * @param currentDate
     *            当前时间
     * @return 上个月最后一天
     */
    public static Date getBoferEndDate(Calendar currentDate) {
        Calendar result = currentDate;
        // result.set(currentDate.get(Calendar.YEAR), currentDate
        // .get(Calendar.MONTH) - 1);
        result.set(Calendar.DATE, 1);
        result.add(Calendar.DATE, -1);
        return result.getTime();
    }

    /**
     * 获取两个时间的时间间隔
     *
     * @param beginDate
     *            开始时间
     * @param endDate
     *            结束时间
     * @return
     */
    public static int getDaysBetween(Calendar beginDate, Calendar endDate) {
        if (beginDate.after(endDate)) {
            Calendar swap = beginDate;
            beginDate = endDate;
            endDate = swap;
        }
        int days = endDate.get(Calendar.DAY_OF_YEAR)
                - beginDate.get(Calendar.DAY_OF_YEAR) + 1;
        int year = endDate.get(Calendar.YEAR);
        if (beginDate.get(Calendar.YEAR) != year) {
            beginDate = (Calendar) beginDate.clone();
            do {
                days += beginDate.getActualMaximum(Calendar.DAY_OF_YEAR);
                beginDate.add(Calendar.YEAR, 1);
            } while (beginDate.get(Calendar.YEAR) != year);
        }
        return days;
    }

    /**
     * 获取两个时间的时间间隔(月份)
     *
     * @param beginDate
     *            开始时间
     * @param endDate
     *            结束时间
     * @return
     */
    public static int getMonthsBetween(Date beginDate, Date endDate) {
        if (beginDate.after(endDate)) {
            Date swap = beginDate;
            beginDate = endDate;
            endDate = swap;
        }
        int months = endDate.getMonth() - beginDate.getMonth();
        int years = endDate.getYear() - beginDate.getYear();

        months += years * 12;

        return months;
    }

    /**
     * 获取两个时间内的工作日
     *
     * @param beginDate
     *            开始时间
     * @param endDate
     *            结束时间
     * @return
     */
    public static int getWorkingDay(Calendar beginDate, Calendar endDate) {
        int result = -1;
        if (beginDate.after(endDate)) {
            Calendar swap = beginDate;
            beginDate = endDate;
            endDate = swap;
        }
        int charge_start_date = 0;
        int charge_end_date = 0;
        int stmp;
        int etmp;
        stmp = 7 - beginDate.get(Calendar.DAY_OF_WEEK);
        etmp = 7 - endDate.get(Calendar.DAY_OF_WEEK);
        if (stmp != 0 && stmp != 6) {
            charge_start_date = stmp - 1;
        }
        if (etmp != 0 && etmp != 6) {
            charge_end_date = etmp - 1;
        }
        result = (getDaysBetween(getNextMonday(beginDate),
                getNextMonday(endDate)) / 7)
                * 5 + charge_start_date - charge_end_date;
        return result;
    }

    /**
     * 根据当前给定的日期获取当前天是星期几(中国版的)
     *
     * @param date
     *            任意时间
     * @return
     */
    public static String getChineseWeek(Calendar date) {
        final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
                "星期六" };
        int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
        return dayNames[dayOfWeek - 1];

    }

    /**
     * 获得日期的下一个星期一的日期
     *
     * @param date
     *            任意时间
     * @return
     */
    public static Calendar getNextMonday(Calendar date) {
        Calendar result = null;
        result = date;
        do {
            result = (Calendar) result.clone();
            result.add(Calendar.DATE, 1);
        } while (result.get(Calendar.DAY_OF_WEEK) != 2);
        return result;
    }

    /**
     * 获取两个日期之间的休息时间
     *
     * @param beginDate
     *            开始时间
     * @param endDate
     *            结束时间
     * @return
     */
    public static int getHolidays(Calendar beginDate, Calendar endDate) {
        return getDaysBetween(beginDate, endDate)
                - getWorkingDay(beginDate, endDate);

    }

    public static boolean isDateEnable(Date beginDate, Date endDate,
                                       Date currentDate) {
        // 开始日期
        long beginDateLong = beginDate.getTime();
        // 结束日期
        long endDateLong = endDate.getTime();
        // 当前日期
        long currentDateLong = currentDate.getTime();
        if (currentDateLong >= beginDateLong && currentDateLong <= endDateLong) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 获取当前月份的第一天
     *
     * @param currentDate
     *            当前时间
     * @return
     */
    public static Date getMinDate(Calendar currentDate) {
        Calendar result = Calendar.getInstance();
        result.set(currentDate.get(Calendar.YEAR), currentDate
                .get(Calendar.MONTH), currentDate
                .getActualMinimum(Calendar.DATE));
        return result.getTime();
    }

    public static Calendar getDate(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, date);
        return calendar;
    }

    public static Calendar getDate(int year, int month) {
        return getDate(year, month, 0);
    }

    public static Date getCountMinDate(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, calendar.getActualMinimum(Calendar.DATE));
        return calendar.getTime();
    }

    public static Date getCountMaxDate(int year, int month) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), 0);
        return calendar2.getTime();
    }

    /**
     * 获取当前月份的第一天
     * @return
     */
    public static Date getMinDate() {
        Calendar currentDate = Calendar.getInstance();
        return getMinDate(currentDate);
    }

    /**
     * 获取当前月分的最大天数
     *
     * @param currentDate
     *            当前时间
     * @return
     */
    public static Date getMaxDate(Calendar currentDate) {
        Calendar result = Calendar.getInstance();
        result.set(currentDate.get(Calendar.YEAR), currentDate
                .get(Calendar.MONTH), currentDate
                .getActualMaximum(Calendar.DATE));
        return result.getTime();
    }

    /**
     * 获取当前月分的最大天数
     *
     * @return
     */
    public static Date getMaxDate() {
        Calendar currentDate = Calendar.getInstance();
        return getMaxDate(currentDate);
    }

    /**
     * 获取今天最大的时间
     *
     * @return
     */
    public static String getMaxDateTimeForToDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar
                .getMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getMaximum(Calendar.MINUTE));
        return convertDate2String(DEFAILT_DATE_TIME_PATTERN, calendar.getTime());
    }

    /**
     * 获取日期最大的时间
     *
     * @return
     */
    public static Date getMaxDateTimeForToDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar
                .getMaximum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getMaximum(Calendar.MINUTE));
        return calendar.getTime();
    }

    /**
     * 获取今天最小时间
     *
     * @return
     */
    public static String getMinDateTimeForToDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar
                .getMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getMinimum(Calendar.MINUTE));
        return convertDate2String(DEFAILT_DATE_TIME_PATTERN, calendar.getTime());
    }

    /**
     * 获取 date 最小时间
     *
     * @return
     */
    public static Date getMinDateTimeForToDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, calendar
                .getMinimum(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, calendar.getMinimum(Calendar.MINUTE));
        return calendar.getTime();
    }

    /**
     * 获取发生日期的结束时间 根据用户设置的日期天数来判定这这个日期是什么(例如 (getHappenMinDate = 2008-10-1) 的话
     * 那么 (getHappenMaxDate = 2008-11-1) 号)
     *
     * @return
     */
    public static Date getHappenMaxDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
        return calendar.getTime();
    }

    /**
     * 加减天数
     *
     * @param num
     * @param Date
     * @return
     */
    public static Date addDay(int num, Date Date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(Date);
        calendar.add(Calendar.DATE, num);// 把日期往后增加 num 天.整数往后推,负数往前移动
        return calendar.getTime(); // 这个时间就是日期往后推一天的结果
    }

	/*
	 * public static void main(String[] args) {
	 * System.out.println(getMaxDateTimeForToDay());
	 * System.out.println(getMinDateTimeForToDay()); }
	 */

    /**
     * 计算两端时间的小时差
     *
     * @param begin
     * @param end
     * @return
     */
    public static int getHour(Date begin, Date end) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(begin);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(end);
        Long millisecond = c2.getTimeInMillis() - c1.getTimeInMillis();
        Long hour = millisecond / 1000 / 60 / 60;
        Long minute = (millisecond / 1000 / 60) % 60;
        if (minute >= 30) {
            hour++;
        }

        return hour.intValue();
    }

    /**
     * 格式化日期
     */
    public static String dateFormat(Date date) {
        if (date == null) {
            return null;
        }
        return DateFormat.getDateInstance().format(date);
    }

    /**
     * 取得指定时间的给定格式()
     * @return String
     * @throws ParseException
     */
    public static String setDateFormat(Date myDate, String strFormat)
            throws ParseException {
        if (myDate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
        String sDate = sdf.format(myDate);
        return sDate;
    }

    public static String setDateFormat(String myDate, String strFormat)
            throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(strFormat);
        String sDate = sdf.format(myDate);

        return sDate;
    }

    /*****************************************
     * @功能 计算某年某月的结束日期
     * @return interger
     * @throws ParseException
     ****************************************/
    public static String getYearMonthEndDay(int yearNum, int monthNum)
            throws ParseException {

        // 分别取得当前日期的年、月、日
        String tempYear = Integer.toString(yearNum);
        String tempMonth = Integer.toString(monthNum);
        String tempDay = "31";
        if (tempMonth.equals("1") || tempMonth.equals("3")
                || tempMonth.equals("5") || tempMonth.equals("7")
                || tempMonth.equals("8") || tempMonth.equals("10")
                || tempMonth.equals("12")) {
            tempDay = "31";
        }
        if (tempMonth.equals("4") || tempMonth.equals("6")
                || tempMonth.equals("9") || tempMonth.equals("11")) {
            tempDay = "30";
        }
        if (tempMonth.equals("2")) {
            if (isLeapYear(yearNum)) {
                tempDay = "29";
            } else {
                tempDay = "28";
            }
        }
        String tempDate = tempYear + "-" + tempMonth + "-" + tempDay;
        return tempDate;// setDateFormat(tempDate,"yyyy-MM-dd");
    }

    /*****************************************
     * @功能 判断某年是否为闰年
     * @return boolean
     * @throws ParseException
     ****************************************/
    public static boolean isLeapYear(int yearNum) {
        boolean isLeep = false;
        /** 判断是否为闰年，赋值给一标识符flag */
        if ((yearNum % 4 == 0) && (yearNum % 100 != 0)) {
            isLeep = true;
        } else if (yearNum % 400 == 0) {
            isLeep = true;
        } else {
            isLeep = false;
        }
        return isLeep;
    }

    /**
     * 格式化日期
     *
     * @throws ParseException
     *
     *             例: DateUtils.formatDate("yyyy-MM-dd HH",new Date())
     *             "yyyy-MM-dd HH:00:00"
     */
    public static Date formatDate(String formatString, Date date)
            throws ParseException {
        if (date == null) {
            date = new Date();
        }
        if (StringUtil.isNull(formatString))
            formatString = DateUtil.DEFAILT_DATE_PATTERN;

        date = DateUtil.convertStringToDate(formatString, DateUtil
                .convertDate2String(formatString, date));

        return date;
    }

    /**
     * 格式化日期 yyyy-MM-dd
     *
     * @throws ParseException
     *             例： DateUtils.formatDate(new Date()) "yyyy-MM-dd 00:00:00"
     */
    public static Date formatDate(Date date) throws ParseException {
        date = formatDate(DateUtil.DEFAILT_DATE_PATTERN, date);
        return date;
    }

    /**
     * @throws ParseException
     *             根据日期获得 星期一的日期
     *
     */
    public static Date getMonDay(Date date) throws ParseException {

        Calendar cal = Calendar.getInstance();
        if (date == null)
            date = new Date();
        cal.setTime(date);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
            cal.add(Calendar.WEEK_OF_YEAR, -1);

        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

        date = formatDate(cal.getTime());

        return date;
    }

    /**
     * @throws ParseException
     *             根据日期获得 星期日 的日期
     *
     */
    public static Date getSunDay(Date date) throws ParseException {

        Calendar cal = Calendar.getInstance();
        if (date == null)
            date = new Date();
        cal.setTime(date);
        if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
            cal.add(Calendar.WEEK_OF_YEAR, 1);

        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);

        date = formatDate(cal.getTime());
        return date;
    }

    /**
     * 获得 下个月的第一天
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date getNextDay(Date date) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DATE, 1);
        return formatDate(cal.getTime());
    }


}
