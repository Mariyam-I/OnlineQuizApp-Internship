package services;

import database.Database;
import java.sql.*;
import java.security.MessageDigest;

public class UserService {

    private Connection conn;

    public UserService() {
        conn = Database.getConnection();
    }

    // Register user
    public int registerUser(String username, String password) {
        try {
            String hashed = hashPassword(password);
            String query = "INSERT INTO users (username, password_hash) VALUES (?, ?)";
            PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, username);
            ps.setString(2, hashed);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int userId = rs.getInt(1);
                System.out.println("✅ Registration successful!");
                return userId; // Return userId for immediate login
            }

        } catch (SQLException e) {
            System.out.println("❌ Username already exists. Try a different one.");
        }
        return -1;
    }

    // Login user
    public int loginUser(String username, String password) {
        try {
            String hashed = hashPassword(password);
            String query = "SELECT user_id FROM users WHERE username=? AND password_hash=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, hashed);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("user_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // Password hashing (SHA-256)
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
