package com.libiyi.exa.admin.api.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {

        @Autowired
        private RedisTemplate<String, Object> redisTemplate;

        /**
         * 指定缓存失效时间
         * @param key 键
         * @param time 时间(秒)
         * @return
         */
        public boolean expire(String key,long time){
            try {
                if(time>0){
                    redisTemplate.expire(key, time, TimeUnit.SECONDS);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        /**
         * 根据key 获取过期时间
         * @param key 键 不能为null
         * @return 时间(秒) 返回0代表为永久有效
         */
        public long getExpire(String key){
            return redisTemplate.getExpire(key,TimeUnit.SECONDS);
        }

        /**
         * 判断key是否存在
         * @param key 键
         * @return true 存在 false不存在
         */
        public boolean hasKey(String key){
            try {
                return redisTemplate.hasKey(key);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        /**
         * 删除缓存
         * @param key 可以传一个值 或多个
         */
        @SuppressWarnings("unchecked")
        public void del(String ... key){
            if(key!=null&&key.length>0){
                if(key.length==1){
                    redisTemplate.delete(key[0]);
                }else{
                    redisTemplate.delete(CollectionUtils.arrayToList(key));
                }
            }
        }


        /**
         * 普通缓存获取
         * @param key 键
         * @return 值
         */
        public Object get(String key){
            return key==null?null:redisTemplate.opsForValue().get(key);
        }

        /**
         * 普通缓存放入
         * @param key 键
         * @param value 值
         * @return true成功 false失败
         */
        public boolean set(String key,Object value) {
            try {
                redisTemplate.opsForValue().set(key, value);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }

        }

        /**
         * 普通缓存放入并设置时间
         * @param key 键
         * @param value 值
         * @param time 时间(秒) time要大于0 如果time小于等于0 将设置无限期
         * @return true成功 false 失败
         */
        public boolean set(String key,Object value,long time){
            try {
                if(time>0){
                    redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
                }else{
                    set(key, value);
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        /**
         * 递增
         * @param key 键
         * @param by 要增加几(大于0)
         * @return
         */
        public long incr(String key, long delta){
            if(delta<0){
                throw new RuntimeException("递增因子必须大于0");
            }
            return redisTemplate.opsForValue().increment(key, delta);
        }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @return
     */
    public boolean lSet(String key, List<Object> value) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 将list放入缓存
     * @param key 键
     * @param value 值
     * @param time 时间(秒)
     * @return
     */
    public boolean lSet(String key, List<Object> value, long time) {
        try {
            redisTemplate.opsForList().rightPushAll(key, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     * @param key 键
     * @param index 索引
     * @param value 值
     * @return
     */
    public boolean lUpdateIndex(String key, long index,Object value) {
        try {
            redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 移除N个值为value
     * @param key 键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public long lRemove(String key,long count,Object value) {
        try {
            Long remove = redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取list缓存的内容
     * @param key 键
     * @param start 开始
     * @param end 结束  0 到 -1代表所有值
     * @return
     */
    public List<Object> lGet(String key, long start, long end){
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
