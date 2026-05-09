/*
 * Name: Max Ramos
 * Date: May 9, 2026
 * Assignment: Phase Final Project - Employee Management Application
 * Purpose: Provides the SQLite database connection for the Employee Management Application.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDatabase {
    public static Connection connect(String databaseName) {
        Connection conn = null;

        try {
            String url = "jdbc:sqlite:" + databaseName;
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Database connection error: " + e.getMessage());
        }

        return conn;
    }
}