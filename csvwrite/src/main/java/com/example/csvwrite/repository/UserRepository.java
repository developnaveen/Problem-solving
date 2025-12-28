package com.example.csvwrite.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.csvwrite.dto.RegisterRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private Logger log = LoggerFactory.getLogger(UserRepository.class);

    public boolean isUserExists(String username) {
        log.info("isUserExists start");
        String sql = "SELECT COUNT(*) FROM users WHERE name = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, username);
        log.info("isUserExists end");
        return count != null && count > 0;
    }

    public int saveUser(RegisterRequest request){
        log.info("saveUser start");
        String sql = "INSERT INTO users (name, password) values (?, ?)";
        log.info("saveUser end");
        return jdbcTemplate.update(
                sql,
                request.getName(),
                request.getPassword());
    }

    public String findPasswordByUser(String name) {
        log.info("findPasswordByUser start");
        String sql = "SELECT password FROM users WHERE name = ?";
        try {
            return jdbcTemplate.queryForObject(sql, String.class, name);
        } catch (EmptyResultDataAccessException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}
