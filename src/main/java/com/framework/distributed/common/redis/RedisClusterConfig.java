package com.framework.distributed.common.redis;

import org.apache.log4j.Logger;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Jingyan
 * @Time: 2017-11-29 10:46
 * @Description: JedisCluster init
 */
@Configuration
@ConditionalOnClass(RedisClusterConfig.class)
@EnableConfigurationProperties(RedisClusterProperties.class)
public class RedisClusterConfig {

    private static final Logger logger = Logger.getLogger(RedisClusterConfig.class);
    @Resource
    private RedisClusterProperties redisClusterProperties;

    @Bean
    public JedisCluster redisCluster() {
        logger.info("RedisClusterConfig init start...");
        Set<HostAndPort> nodes = new HashSet<>();
        for (String node : redisClusterProperties.getNodes()) {
            String[] parts = StringUtils.split(node, ":");
            nodes.add(new HostAndPort(parts[0], Integer.valueOf(parts[1])));
        }
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(redisClusterProperties.getMaxTotal());
        jedisPoolConfig.setMaxIdle(redisClusterProperties.getMaxIdle());
        jedisPoolConfig.setMinIdle(redisClusterProperties.getMinIdle());
        jedisPoolConfig.setMaxWaitMillis(redisClusterProperties.getMaxWaitMillis());
        JedisCluster jedisCluster = new JedisCluster(nodes, 5000, 5000,
                1, redisClusterProperties.getPassword(), jedisPoolConfig);
        logger.info("RedisClusterConfig init successed...");
        return jedisCluster;
    }
}