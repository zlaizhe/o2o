package com.imooc.o2o.util;

public class PathUtil {
    private static String separator = System.getProperty("file.separator");

    //返回项目路径的图片资源根路径（实际中常把图片资源部署在另一台服务器上）
    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {//windows系统
            basePath = "D:/projectdev/image";
        } else {//其他系统
            basePath = "/home/xiangze/image";
        }
        basePath = basePath.replace("/", separator);
        return basePath;
    }

    //返回店铺图片资源的子路径
    public static String getShopImgBasePath(long shopId) {
        String imagePath = "/upload/item/shop/" + shopId + "/";
        return imagePath.replace("/", separator);
    }

}
