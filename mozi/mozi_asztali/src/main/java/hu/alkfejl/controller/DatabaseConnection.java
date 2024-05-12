package hu.alkfejl.controller;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public static Connection Connector() {
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Fanni\\Documents\\szte\\21\\Alkfejl\\mozi\\mozi_asztali\\src\\main\\resources\\mozi.db");
            return conn;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
