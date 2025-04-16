package com.blog.dao;

import com.blog.model.Blog;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlogDAO {
    public boolean addBlog(Blog blog) {
        String sql = "INSERT INTO blogs (title, contents, user_id, category_id) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, blog.getTitle());
            pstmt.setString(2, blog.getContents());
            pstmt.setInt(3, blog.getUserId());
            pstmt.setInt(4, blog.getCategoryId());
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<Blog> getBlogsByUser(int userId) {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT * FROM blogs WHERE user_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                blogs.add(new Blog(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("contents"),
                    rs.getInt("user_id"),
                    rs.getInt("category_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }
    
    public List<Blog> getAllBlogs() {
        List<Blog> blogs = new ArrayList<>();
        String sql = "SELECT * FROM blogs";
        
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                blogs.add(new Blog(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("contents"),
                    rs.getInt("user_id"),
                    rs.getInt("category_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }
    
    public boolean deleteBlog(int blogId, int userId) {
        String sql = "DELETE FROM blogs WHERE id = ? AND user_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, blogId);
            pstmt.setInt(2, userId);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}