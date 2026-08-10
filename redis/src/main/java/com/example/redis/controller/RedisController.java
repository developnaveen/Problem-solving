package com.example.redis.controller;

import org.springframework.http.ResponseEntity;

public interface RedisController {

    // String Operations
    ResponseEntity<String> setValue();

    ResponseEntity<String> getValue();

    ResponseEntity<String> deleteValue();

    String expireValue();

    Long getTTL();

    // Hash Operations
    String createUser();

    Object getUser();

    String deleteUser();

    // List Operations
    String addToQueue();

    Object getQueue();

    String removeFromQueue();

    // Counter Operations
    Long incrementCounter();

    Long decrementCounter();

    // Set Operations
    String addToSet();

    Object getSetMembers();

    // Sorted Set Operations
    String addScore();

    Object getScores();

    // Utility Operations
    Object getAllKeys();

    String flushDatabase();
}