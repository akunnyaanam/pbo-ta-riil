package org.example.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnect {
    private static final String URL = "jdbc:sqlite:perpus.db";
    private static Connection instance;

    private DBConnect() {
        // Private constructor to prevent instantiation
    }

    public static Connection connect() throws SQLException {
        if (instance == null || instance.isClosed()) {
            try {
                instance = DriverManager.getConnection(URL);
                System.out.println("Koneksi ke SQLite berhasil.");
            } catch (SQLException e) {
                System.out.println("Koneksi ke SQLite tidak berhasil.");
                System.out.println(e.getMessage());
            }
        }
        return instance;
    }

    public static boolean isTableExists(String tableName) throws SQLException {
        boolean exists = false;
        try (Connection conn = connect()) {
            DatabaseMetaData metaData = conn.getMetaData();
            exists = metaData.getTables(null, null, tableName, null).next();
        } catch (SQLException e) {
            System.out.println("Error while checking table existence: " + e.getMessage());
        }
        return exists;
    }

    public static void truncateTable(String tableName) {
        String truncateSQL = "DELETE FROM " + tableName + ";";

        try (Connection conn = DriverManager.getConnection(URL);
                Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(truncateSQL);
            System.out.println("Table " + tableName + " truncated successfully.");
        } catch (SQLException e) {
            System.out.println("Error truncating table " + tableName + ": " + e.getMessage());
        }
    }
}