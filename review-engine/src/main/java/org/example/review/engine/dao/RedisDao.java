package org.example.review.engine.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import io.lettuce.core.FlushMode;
import io.lettuce.core.RedisCommandTimeoutException;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;

import java.util.List;
import java.util.Map;

@Repository
public class RedisDao {
    private final StatefulRedisConnection<String, String> redisConnection;

    static final Logger logger = LoggerFactory.getLogger(String.valueOf(RedisDao.class));

    public RedisDao(StatefulRedisConnection<String, String> redisConnection) {
        this.redisConnection = redisConnection;
    }
    public boolean storeObjectInCache(List<String> keyArguments, Map<String, String> valueMap, Long ttl) {
        logger.info("Storing object in cache");

        String key = String.join("#", keyArguments);
        logger.info("Redis object ttl " + ttl + " for key " + key);
        try {
            redisConnection.reactive().hmset(key, valueMap);
        } catch (Exception e) {
            logger.error("hmset failed for key: " + key + ", value: " + valueMap, e);
            return false;
        }

        if (ttl != null) {
            try {
                redisConnection.reactive().expire(key, ttl);
            } catch (Exception e) {
                logger.error("set expire failed for key: " + key + ", ttl: " + ttl, e);
                return false;
            }
        }
        return true;
    }

    public Map<String, String> fetchObjectFromCache(List<String> keyArguments) {
        String key = String.join("#", keyArguments);

        try {
            logger.info("Fetching from cache");
            return redisConnection.sync().hgetall(key);
        } catch (RedisCommandTimeoutException rte) {
            logger.error("hgetall(" + key + ") failed due to timeout", rte);
            return null;
        } catch (Exception e) {
            logger.error("hgetall(" + key + ") failed.", e);
            return null;
        }
    }

    public boolean storeFieldInCache(List<String> keyArguments, String value, Long ttl) {

        String key = String.join("#", keyArguments);
        try {
            logger.info("Redis field ttl " + ttl + " for key " + key);
            if (ttl != null) {
                redisConnection.reactive().set(key, value, SetArgs.Builder.ex(ttl));
            } else {
                redisConnection.reactive().set(key, value);
            }
        } catch (Exception e) {
            logger.error("set failed for key: " + key + ", value: " + value + ", ttl: " + ttl, e);
            return false;
        }
        return true;
    }

    public String fetchFieldFromCache(List<String> keyArguments) {

        String key = String.join("#", keyArguments);
        try {
            return redisConnection.sync().get(key);
        } catch (Exception e) {
            logger.error("get(" + key + ") failed.", e);
            return null;
        }
    }

    public boolean removeDataFromCache(List<String> keyArguments) {

        String key = String.join("#", keyArguments);
        try {
            redisConnection.reactive().unlink(key); // .blockFirst();
            return true;
        } catch (Exception e) {
            logger.error("unlink failed for key: " + key, e);
            return false;
        }
    }

    public boolean clearCache() {

        try {
            redisConnection.reactive().flushall(FlushMode.ASYNC); // .blockFirst();
            return true;
        } catch (Exception e) {
            logger.error("Clearing redis cache failed.", e);
            return false;
        }
    }


}
