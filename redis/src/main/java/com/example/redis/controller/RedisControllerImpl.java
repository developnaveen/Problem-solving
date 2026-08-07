package com.example.redis.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisControllerImpl implements RedisController {

    // ==========================
    // String Operations
    // ==========================

    @Override
    @PostMapping("/set")
    public String setValue() {
        return "";
    }

    @Override
    @PostMapping("/get")
    public String getValue() {
        return "";
    }

    @Override
    @PostMapping("/delete")
    public String deleteValue() {
        return "";
    }

    @Override
    @PostMapping("/expire")
    public String expireValue() {
        return "";
    }

    @Override
    @PostMapping("/ttl")
    public Long getTTL() {
        return 0L;
    }

    // ==========================
    // Hash Operations
    // ==========================

    @Override
    @PostMapping("/hset")
    public String createUser() {
        return "";
    }

    @Override
    @PostMapping("/hget")
    public Object getUser() {
        return null;
    }

    @Override
    @PostMapping("/hdelete")
    public String deleteUser() {
        return "";
    }

    // ==========================
    // List (Queue) Operations
    // ==========================

    @Override
    @PostMapping("/qadd")
    public String addToQueue() {
        return "";
    }

    @Override
    @PostMapping("/qget")
    public Object getQueue() {
        return null;
    }

    @Override
    @PostMapping("/qremove")
    public String removeFromQueue() {
        return "";
    }

    // ==========================
    // Counter Operations
    // ==========================

    @Override
    @PostMapping("/increment")
    public Long incrementCounter() {
        return 0L;
    }

    @Override
    @PostMapping("/decrement")
    public Long decrementCounter() {
        return 0L;
    }

    // ==========================
    // Set Operations
    // ==========================

    @Override
    @PostMapping("/sadd")
    public String addToSet() {
        return "";
    }

    @Override
    @PostMapping("/sget")
    public Object getSetMembers() {
        return null;
    }

    // ==========================
    // Sorted Set Operations
    // ==========================

    @Override
    @PostMapping("/zadd")
    public String addScore() {
        return "";
    }

    @Override
    @PostMapping("/zget")
    public Object getScores() {
        return null;
    }

    // ==========================
    // Utility Operations
    // ==========================

    @Override
    @PostMapping("/keys")
    public Object getAllKeys() {
        return null;
    }

    @Override
    @PostMapping("/flush")
    public String flushDatabase() {
        return "";
    }
}