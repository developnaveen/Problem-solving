package com.example.sampleservlet.dao;

import com.example.sampleservlet.config.ConnectionProvider;
import com.example.sampleservlet.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDao {

    public void saveUser(User user) {

        String sql = """
            INSERT INTO users (
                email,
                emp_id,
                password
            )
            VALUES (?,?,?)
            """;

        try (
                Connection con = ConnectionProvider.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, user.getEmail());
            ps.setString(2, user.getEmpId());
            ps.setString(3, user.getPassword());

            ps.executeUpdate(); // 🔑

        } catch (SQLException e) {
            throw new RuntimeException("Error saving user", e);
        }
    }
}
