package com.os.osframe.util;


import java.text.DecimalFormat;

/**
 * 数据处理
 * Created by wangdc on 2014-9-8.
 */
public class DataUtil {

    // 转为 String 型
    public static String castString(Object obj) {
        return DataUtil.castString(obj, "");
    }

    // 转为 String 型（提供默认值）
    public static String castString(Object obj, String defaultValue) {
        return obj != null ? String.valueOf(obj) : defaultValue;
    }

    // 转为 double 型
    public static double castDouble(Object obj) {
        return DataUtil.castDouble(obj, 0);
    }

    // 转为 double 型（提供默认值）
    public static double castDouble(Object obj, double defaultValue) {
        double doubleValue = defaultValue;
        if (obj != null) {
            String strValue = castString(obj);
            if (StringUtil.isNotNull(strValue)) {
                try {
                    doubleValue = Double.parseDouble(strValue);
                } catch (NumberFormatException e) {
                    doubleValue = defaultValue;
                }
            }
        }
        return doubleValue;
    }

    // 转为 long 型
    public static long castLong(Object obj) {
        return DataUtil.castLong(obj, 0);
    }

    // 转为 long 型（提供默认值）
    public static long castLong(Object obj, long defaultValue) {
        long longValue = defaultValue;
        if (obj != null) {
            String strValue = castString(obj);
            if (StringUtil.isNotNull(strValue)) {
                try {
                    longValue = Long.parseLong(strValue);
                } catch (NumberFormatException e) {
                    longValue = defaultValue;
                }
            }
        }
        return longValue;
    }

    // 转为 int 型
    public static int castInt(Object obj) {
        return DataUtil.castInt(obj, 0);
    }

    // 转为 int 型（提供默认值）
    public static int castInt(Object obj, int defaultValue) {
        int intValue = defaultValue;
        if (obj != null) {
            String strValue = castString(obj);
            if (StringUtil.isNotNull(strValue)) {
                try {
                    intValue = Integer.parseInt(strValue);
                } catch (NumberFormatException e) {
                    intValue = defaultValue;
                }
            }
        }
        return intValue;
    }

    // 转为 boolean 型
    public static boolean castBoolean(Object obj) {
        return DataUtil.castBoolean(obj, false);
    }

    // 转为 boolean 型（提供默认值）
    public static boolean castBoolean(Object obj, boolean defaultValue) {
        boolean booleanValue = defaultValue;
        if (obj != null) {
            booleanValue = Boolean.parseBoolean(castString(obj));
        }
        return booleanValue;
    }

    // 转为 String[] 型
    public static String[] castStringArray(Object[] objArray) {
        if (objArray == null) {
            objArray = new Object[0];
        }
        String[] strArray = new String[objArray.length];
        if (CollectionUtil.isNotEmpty(objArray)) {
            for (int i = 0; i < objArray.length; i++) {
                strArray[i] = castString(objArray[i]);
            }
        }
        return strArray;
    }

    // 转为 double[] 型
    public static double[] castDoubleArray(Object[] objArray) {
        if (objArray == null) {
            objArray = new Object[0];
        }
        double[] doubleArray = new double[objArray.length];
        if (!CollectionUtil.isEmpty(objArray)) {
            for (int i = 0; i < objArray.length; i++) {
                doubleArray[i] = castDouble(objArray[i]);
            }
        }
        return doubleArray;
    }

    // 转为 long[] 型
    public static long[] castLongArray(Object[] objArray) {
        if (objArray == null) {
            objArray = new Object[0];
        }
        long[] longArray = new long[objArray.length];
        if (!CollectionUtil.isEmpty(objArray)) {
            for (int i = 0; i < objArray.length; i++) {
                longArray[i] = castLong(objArray[i]);
            }
        }
        return longArray;
    }

    // 转为 int[] 型
    public static int[] castIntArray(Object[] objArray) {
        if (objArray == null) {
            objArray = new Object[0];
        }
        int[] intArray = new int[objArray.length];
        if (!CollectionUtil.isEmpty(objArray)) {
            for (int i = 0; i < objArray.length; i++) {
                intArray[i] = castInt(objArray[i]);
            }
        }
        return intArray;
    }

    // 转为 boolean[] 型
    public static boolean[] castBooleanArray(Object[] objArray) {
        if (objArray == null) {
            objArray = new Object[0];
        }
        boolean[] booleanArray = new boolean[objArray.length];
        if (!CollectionUtil.isEmpty(objArray)) {
            for (int i = 0; i < objArray.length; i++) {
                booleanArray[i] = castBoolean(objArray[i]);
            }
        }
        return booleanArray;
    }

    /**
     * 用###,###.##格式化字符串值。
     *
     * @param value- 值
     * @return 被转换后的字符串。
     */
    public static String roundDecimal(Object value) {
        return roundDecimal(value, "###,###.##");
    }

    /**
     * 用指定格式格式化字符串值。
     *
     * @param value
     *            - 值
     * @param pattern
     *            -格式
     * @return 被转换后的字符串。
     */
    public static String roundDecimal(Object value, String pattern) {
        String res = null;
        if (pattern == null || pattern.trim().equals(""))
            pattern = "###,###.##";
        DecimalFormat df = new DecimalFormat(pattern);
        try {
            if (value instanceof String) {
                res = df.format(new Double(value.toString()));
            } else {
                res = df.format(value);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * 保留两位小数。
     *
     * @param fTemp
     *            - 要保留的数据
     * @return 被转换后的字符串。
     */
    public static String roundDecimal(double fTemp) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(fTemp);
    }

    public static String roundDecimal(float fTemp) {
        return roundDecimal((double) fTemp);
    }

    /**
     * 保留指定的位小数。
     *
     * @param fTemp
     *            - 要保留的数据
     * @param pos
     *            - 指定的位数
     * @return 被转换后的字符串。
     */
    public static String roundDecimal(double fTemp, int pos) {
        DecimalFormat df = new DecimalFormat("#.################");
        df.setMaximumFractionDigits(pos);
        return df.format(fTemp);
    }
}
