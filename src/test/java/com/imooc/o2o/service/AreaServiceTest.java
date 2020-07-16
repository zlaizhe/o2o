package com.imooc.o2o.service;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Area;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.List;

public class AreaServiceTest extends BaseTest {

    @Autowired
    private AreaService areaService;

    @Autowired
    private CacheService cacheService;

    @Test
    public void testJedis() {//测试远程服务器的Redis是否可以连接，注意需要修改redis.conf中去掉bind 127.0.0.1行，并将保护模式设为no
        Jedis jedis = new Jedis("182.92.57.21", 6379);
        jedis.set("test", "yuancheng");
        System.out.println(jedis.isConnected());
        jedis.close();
    }

    @Test
    public void testGetAreaList() {
        List<Area> areaList = areaService.getAreaList();
        System.out.println(areaList);
        Assert.assertEquals(4, areaList.size());
        cacheService.removeFromCache(AreaService.AREALISTKEY);//删除redis中的key
        areaList = areaService.getAreaList();
    }

    @Test//测试声明式事务是否回滚
    public void testUpdateArea() {
        int rows = areaService.updateArea(2);
        System.out.println(rows);
    }
}
