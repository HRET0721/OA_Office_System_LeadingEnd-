package org.hret.pojo;

import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

/**
 * Author:HRET Milky Way
 * Date:2024/1/14
 * version:1.0
 * redis工具类
 * @author HRET
 */
@Component
public class RedisDeleteUtils {

    /**
     * 模糊删除redis中的数据
     *
     * @param pattern 模糊匹配的字符串
     */
    public void deleteByPattern(RedisTemplate redisTemplate, String pattern) {
        // 设置模糊匹配的字符串
        ScanOptions scanOptions = ScanOptions.scanOptions().match(pattern).build();
        // 获取redis中的数据
        Cursor<String> cursor = redisTemplate.opsForValue().getOperations().scan(scanOptions);

        // 遍历redis中的数据
        while (cursor.hasNext()) {
            // 获取key
            String key = cursor.next();
            // 删除key
            redisTemplate.delete(key);
        }
    }

}
