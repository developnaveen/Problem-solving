package com.example.csvwrite.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.csvwrite.dto.RegisterRequest;

@Repository
public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int saveUser(RegisterRequest request){
        String sql = "INSERT INTO users (name, password) values (?, ?)";

        return jdbcTemplate.update(
                sql,
                request.getName(),
                request.getPassword());
    }

}
