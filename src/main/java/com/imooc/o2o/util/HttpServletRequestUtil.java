package com.imooc.o2o.util;

import javax.servlet.http.HttpServletRequest;

@Deprecated
public class HttpServletRequestUtil {

    public static int getInt(HttpServletRequest request, String key) {
        try {
            String result = request.getParameter(key);
            return Integer.decode(result);
        } catch (Exception e) {
            return -1;
        }
    }

    public static long getLong(HttpServletRequest request, String key) {
        try {
            String result = request.getParameter(key);
            return Long.valueOf(result);
        } catch (Exception e) {
            return -1;
        }
    }

    public static double getDouble(HttpServletRequest request, String key) {
        try {
            String result = request.getParameter(key);
            return Double.valueOf(result);
        } catch (Exception e) {
            return -1d;
        }
    }

    public static boolean getBoolean(HttpServletRequest request, String key) {
        try {
            String result = request.getParameter(key);
            return Boolean.valueOf(result);
        } catch (Exception e) {
            return false;
        }
    }

    public static String getString(HttpServletRequest request, String key) {
        try {
            String result = request.getParameter(key);
            if (result != null) {
                result = result.trim();
            }
            if ("".equals(result)) {
                result = null;
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
