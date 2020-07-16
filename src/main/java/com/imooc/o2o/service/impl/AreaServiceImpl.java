package com.imooc.o2o.service.impl;

import com.imooc.o2o.dao.AreaDao;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.service.AreaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private AreaDao areaDao;

    @Autowired
    private RedisTemplate<String, Area> redisTemplate;//由于Area不经常改变，使用Redis进行优化

    private Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);

    @Override
    @Transactional
    public List<Area> getAreaList() {
        String key = AREALISTKEY;
        List<Area> areaList = null;
        if (!redisTemplate.hasKey(key)) {//缓存中没有key
            areaList = areaDao.queryArea();//先从数据库查询
            if (areaList != null && !areaList.isEmpty()) {
                redisTemplate.opsForList().rightPushAll(key, areaList);//将查询数据以链表形式存入缓存
                logger.debug("将areaList存入缓存");
            }
        } else {//缓存中有key，从缓存中查询
            areaList = redisTemplate.opsForList().range(key, 0, -1);
            logger.debug("从缓存中查询areaList");
        }
        return areaList;
    }

    @Override
    @Transactional
    public int updateArea(int areaId) {//仅用来测试声明式事务是否能够回滚
        areaDao.updateArea(areaId);
        if (areaId == 2) {
            throw new RuntimeException("制造异常");
        }
        return areaDao.updateArea(areaId);
    }
}
