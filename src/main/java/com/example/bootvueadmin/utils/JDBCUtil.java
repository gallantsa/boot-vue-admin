package com.example.bootvueadmin.utils;

import com.example.bootvueadmin.entity.User;

import java.sql.*;

public class JDBCUtil {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // 初始化对象
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/boot-vue-admin?useUnicode=true&characterEncoding=utf-8&useSSL=false" + "&&serverTimezone=GMT%2B8";
        return DriverManager.getConnection(url, "root", "hsp");
    }

    public static User executeQueryUser(String username, String password) {
        // 参数要加单引号
        String sql = "select * from user where username = '" + username + "' and password = '" + password + "'";
        // 语法糖 try-resourse

        Connection conn = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            if (rs.next()) {
                String username1 = rs.getString("username");
                String password1 = rs.getString("password");
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(username1);
                user.setPassword(password1);
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (st != null) {
                    st.close();
                }
                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
