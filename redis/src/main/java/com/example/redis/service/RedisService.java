package com.example.redis.service;

import org.springframework.data.redis.connection.stream.MapRecord;
import org.springframework.data.redis.connection.stream.StreamOffset;
import org.springframework.data.redis.connection.stream.StreamReadOptions;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    // ==========================
    // String Operations
    // ==========================

    public void setValue(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object getValue(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteValue(String key) {
        redisTemplate.delete(key);
    }

    public boolean expireValue(String key, Duration duration) {
        return redisTemplate.expire(key, duration);
    }

    public Long getTTL(String key) {
        return redisTemplate.getExpire(key);
    }

    // ==========================
    // Hash Operations
    // ==========================

    public void hashSet(String key, String variable, String value) {
        redisTemplate.opsForHash().put(key, variable, value);
    }

    public Object hashGet(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public Object hashGet(String key, String variable) {
        return redisTemplate.opsForHash().get(key, variable);
    }

    public void hashDelete(String key) {
        redisTemplate.delete(key);
    }

    // ==========================
    // List / Queue Operations
    // ==========================

    public void queueAdd(String value) {
        redisTemplate.opsForList().rightPush("queue", value);
    }

    public List<Object> queueGet() {
        return redisTemplate.opsForList().range("queue", 0, -1);
    }

    public Object queueRemove() {
        return redisTemplate.opsForList().leftPop("queue");
    }

    // ==========================
    // Counter Operations
    // ==========================

    public Long increment(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    public Long decrement(String key) {
        return redisTemplate.opsForValue().decrement(key);
    }

    // ==========================
    // Set Operations
    // ==========================

    public void setAdd(String key, String value) {
        redisTemplate.opsForSet().add(key, value);
    }

    public Set<Object> setMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    // ==========================
    // Sorted Set Operations
    // ==========================

    public void sortedSetAdd(String key, String value, double score) {
        redisTemplate.opsForZSet().add(key, value, score);
    }

    public Set<Object> sortedSetGet(String key) {
        return redisTemplate.opsForZSet().range(key, 0, -1);
    }

    // ==========================
    // Utility Operations
    // ==========================

    public Set<String> getAllKeys() {
        return redisTemplate.keys("*");
    }

    public void flushDatabase() {
        redisTemplate.getConnectionFactory().getConnection().serverCommands().flushDb();
    }

    // Publish message
    public void publish(String channel, String message) {
        redisTemplate.convertAndSend(channel, message);
    }

    public String addToStream(String streamKey, String loanId, String customer) {

        Map<String, String> data = Map.of("loanId", loanId, "customer", customer);

        MapRecord<String, String, String> record = MapRecord.create(streamKey, data);

        return redisTemplate.opsForStream().add(record).getValue();
    }

    // Read all messages
    public List<MapRecord<String, Object, Object>> readStream(String streamKey) {

        return redisTemplate.opsForStream().read(StreamReadOptions.empty(), StreamOffset.fromStart(streamKey));
    }

    public List<Object> pipelineExample() {

        return redisTemplate.executePipelined((RedisCallback<Object>) connection -> {

            connection.stringCommands().set("name".getBytes(), "Naveen".getBytes());

            connection.stringCommands().set("role".getBytes(), "Java Developer".getBytes());

            connection.stringCommands().set("project".getBytes(), "Redis".getBytes());

            return null;
        });
    }

    public boolean acquireLock(String lockKey, String lockValue, Duration duration) {

        Boolean acquired = redisTemplate.opsForValue().setIfAbsent(lockKey, lockValue, duration);

        return Boolean.TRUE.equals(acquired);
    }

    public void releaseLock(String lockKey) {
        redisTemplate.delete(lockKey);
    }
}