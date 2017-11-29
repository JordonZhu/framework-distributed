package com.framework.distributed.idempotent.service;

import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;

/**
 * @Author: Jingyan
 * @Time: 2017-11-29 11:15
 * @Description:
 */
@Service
public class IdempotentService {

    private static final int EXPIRE_SECONDS = 86400;
    @Resource
    private JedisCluster jedisCluster;


    /**
     * Created with Jingyan
     * Time: 2017-11-29 14:19
     * Description: redis 幂等数据
     */
    public boolean checkIsConsumed(String uuid) {
        int flag = jedisCluster.setnx(uuid, uuid).intValue();
        if (1 == flag) {
            jedisCluster.expire(uuid, EXPIRE_SECONDS);
            return true;
        }
        return false;
    }

}