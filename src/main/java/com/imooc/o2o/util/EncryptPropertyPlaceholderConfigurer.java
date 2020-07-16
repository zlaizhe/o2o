package com.imooc.o2o.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.Arrays;
import java.util.List;

public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
    // 需要加密的字段数组
    private List<String> encryptPropNames = Arrays.asList("jdbc.username", "jdbc.password");

    /**
     * 对关键的属性进行转换
     */
    @Override
    protected String convertProperty(String propertyName, String propertyValue) {
        // 对已加密的字段进行解密工作
        return encryptPropNames.contains(propertyName) ?
                DESUtil.getDecryptString(propertyValue) : propertyValue;
    }

    /**
     * 该属性是否已加密
     *
     * @param propertyName
     * @return
     */
//    private boolean isEncryptProp(String propertyName) {
//        // 若等于需要加密的field，则进行加密
//        return encryptPropNames.contains(propertyName);
//    }
}
