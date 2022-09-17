package com.training.hibernate.jdbc;

import com.mysql.cj.jdbc.JdbcConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConnectionTest {

    public static void main(String[] args) {
        String user = "hbstudent";
        String pass = "hbstudent";
        String Url = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni";

        try(Connection connection = DriverManager.getConnection(Url, user, pass)) {
            System.out.println("Connection Successful!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
