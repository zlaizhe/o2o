package com.imooc.o2o.util;

@Deprecated
public class PageCalculator2 {
    //将页码转为数据库行数
    public static int calculateRowIndex(int pageIndex, int pageSize) {
        return pageIndex > 0 ? (pageIndex - 1) * pageSize : 0;
    }
}
