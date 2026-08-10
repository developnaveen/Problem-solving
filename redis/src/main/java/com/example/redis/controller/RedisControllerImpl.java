package com.example.redis.controller;

import com.example.redis.service.RedisService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping("/redis")
public class RedisControllerImpl implements RedisController {

    private final RedisService redisService;

    public RedisControllerImpl(RedisService redisService) {
        this.redisService = redisService;
    }

    // ==========================
    // String Operations
    // ==========================

    @Override
    @PostMapping("/set")
    public ResponseEntity<String> setValue() {
        redisService.setValue("cache1", "loans");
        return ResponseEntity.ok("Value stored successfully");
    }

    @Override
    @PostMapping("/get")
    public ResponseEntity<String> getValue() {
        Object value = redisService.getValue("cache1");

        if (value == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(value.toString());
    }

    @Override
    @PostMapping("/delete")
    public ResponseEntity<String> deleteValue() {
        redisService.deleteValue("cache1");
        return ResponseEntity.ok("Value deleted successfully");
    }

    @Override
    @PostMapping("/expire")
    public String expireValue() {
        boolean result = redisService.expireValue("cache1", Duration.ofSeconds(60));

        return result
                ? "Key will expire in 60 seconds"
                : "Key not found";
    }

    @Override
    @PostMapping("/ttl")
    public Long getTTL() {
        return redisService.getTTL("cache1");
    }

    // ==========================
    // Hash Operations
    // ==========================

    @Override
    @PostMapping("/hset")
    public String createUser() {

        String key = "user:1";

        redisService.hashSet(key, "name", "Naveen");
        redisService.hashSet(key, "role", "Java Developer");
        redisService.hashSet(key, "project", "Redis");

        return "User created successfully";
    }

    @Override
    @PostMapping("/hget")
    public Object getUser() {
        return redisService.hashGet("user:1");
    }

    @Override
    @PostMapping("/hdelete")
    public String deleteUser() {

        redisService.hashDelete("user:1");

        return "User deleted successfully";
    }

    // ==========================
    // List (Queue) Operations
    // ==========================

    @Override
    @PostMapping("/qadd")
    public String addToQueue() {

        redisService.queueAdd("loan-1");
        redisService.queueAdd("loan-2");
        redisService.queueAdd("loan-3");

        return "Items added to queue successfully";
    }

    @Override
    @PostMapping("/qget")
    public Object getQueue() {
        return redisService.queueGet();
    }

    @Override
    @PostMapping("/qremove")
    public String removeFromQueue() {

        Object value = redisService.queueRemove();

        if (value == null) {
            return "Queue is empty";
        }

        return "Removed: " + value;
    }

    // ==========================
    // Counter Operations
    // ==========================

    @Override
    @PostMapping("/increment")
    public Long incrementCounter() {
        return redisService.increment("counter");
    }

    @Override
    @PostMapping("/decrement")
    public Long decrementCounter() {
        return redisService.decrement("counter");
    }

    // ==========================
    // Set Operations
    // ==========================

    @Override
    @PostMapping("/sadd")
    public String addToSet() {

        redisService.setAdd("skills", "Java");
        redisService.setAdd("skills", "Spring Boot");
        redisService.setAdd("skills", "Redis");

        return "Values added to set successfully";
    }

    @Override
    @PostMapping("/sget")
    public Object getSetMembers() {
        return redisService.setMembers("skills");
    }

    // ==========================
    // Sorted Set Operations
    // ==========================

    @Override
    @PostMapping("/zadd")
    public String addScore() {

        redisService.sortedSetAdd("leaderboard", "Naveen", 100);
        redisService.sortedSetAdd("leaderboard", "John", 80);
        redisService.sortedSetAdd("leaderboard", "David", 90);

        return "Scores added successfully";
    }

    @Override
    @PostMapping("/zget")
    public Object getScores() {
        return redisService.sortedSetGet("leaderboard");
    }

    // ==========================
    // Utility Operations
    // ==========================

    @Override
    @PostMapping("/keys")
    public Object getAllKeys() {
        return redisService.getAllKeys();
    }

    @Override
    @PostMapping("/flush")
    public String flushDatabase() {

        redisService.flushDatabase();

        return "Redis database flushed successfully";
    }
}