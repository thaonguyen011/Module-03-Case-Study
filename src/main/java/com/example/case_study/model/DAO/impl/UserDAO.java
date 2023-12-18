package com.example.case_study.model.DAO.impl;

import com.example.case_study.model.DAO.IUserDAO;
import com.example.case_study.model.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {
    private final static String jdbcURL="jdbc:mysql://localhost:3306/case_study_module_03";
    private final static String jdbcUsername="root";
    private final static String jdbcPassword = "Modicung2486!";
    private final static String SELECT_ALL_USERS = "select * from user;";
    private final static String SELECT_USER_BY_ID = "select * from user where id = ?;";
    private final static String INSERT_USER = "insert into user(username, password, phone, email) values (?,?,?,?);";
    private final static String UPDATE_USER = "update user set username= ?, password =?, phone =?, email=? where id = ?;";
    private final static String DELETE_USER = "delete user where id = ?;";
    private final static String SELECT_USER_BY_USERNAME = "select * from user where username = ? or email = ?;";
    @Override
    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    @Override
    public List<User> selectAll() {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                User user = new User(id, username, password, phone, email);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User selectById(int id) {
        User user = null;
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_ID);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                user = new User(username, password, phone, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean insert(User user) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(INSERT_USER);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getEmail());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(User user) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(UPDATE_USER);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getEmail());
            ps.setInt(5, user.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean remove(int id) {
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(DELETE_USER);
            ps.setInt(1, id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUserByUsername(String username) {
        User user = null;
        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(SELECT_USER_BY_USERNAME);
            ps.setString(1, username);
            ps.setString(2, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String password = rs.getString("password");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                user = new User(id, username, password, phone, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }


}
