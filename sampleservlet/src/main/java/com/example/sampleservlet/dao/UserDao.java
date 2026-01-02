package com.example.sampleservlet.dao;

import com.example.sampleservlet.config.ConnectionProvider;
import com.example.sampleservlet.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public String getUser(String username) {
        String sql = """
            SELECT password
            FROM 
                users
            WHERE 
                emp_id = ?
            """;
        try (
                Connection con = ConnectionProvider.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return rs.getString("password");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Database error while fetching user", e);
        }
        return null;
    }

}
