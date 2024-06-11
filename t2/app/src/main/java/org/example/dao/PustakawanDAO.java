package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.database.DBConnect;
import org.example.entities.Pustakawan;

public class PustakawanDAO {
    // private static final String URL = "jdbc:sqlite:perpus.db";

    public static void generateDummyData() {
        List<Pustakawan> dummyData = new ArrayList<>();
        dummyData.add(new Pustakawan(1, "John Doe", "Jl. Buku No. 123"));
        dummyData.add(new Pustakawan(2, "Jane Smith", "Jl. Pustaka No. 456"));
        for (Pustakawan pustakawan : dummyData) {
            insert(pustakawan);
        }
        System.out.println("Dummy data for Pustakawan inserted successfully.");
    }

    public static void insert(Pustakawan pustakawan) {
        String insertSQL = "INSERT INTO Pustakawan (namaPustakawan, alamatPustakawan) VALUES (?, ?);";

        try (Connection conn = DBConnect.connect();
                PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
            stmt.setString(1, pustakawan.getNamaPustakawan());
            stmt.setString(2, pustakawan.getAlamatPustakawan());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting pustakawan: " + e.getMessage());
        }
    }

    public static List<Pustakawan> getAll() {
        List<Pustakawan> pustakawanList = new ArrayList<>();
        String selectAllSQL = "SELECT idPustakawan, namaPustakawan, alamatPustakawan FROM Pustakawan;";

        try (Connection conn = DBConnect.connect();
                PreparedStatement stmt = conn.prepareStatement(selectAllSQL)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Pustakawan pustakawan = new Pustakawan(0, selectAllSQL, selectAllSQL);
                pustakawan.setIdPustakawan(rs.getInt("idPustakawan"));
                pustakawan.setNamaPustakawan(rs.getString("namaPustakawan"));
                pustakawan.setAlamatPustakawan(rs.getString("alamatPustakawan"));
                pustakawanList.add(pustakawan);
            }
        } catch (SQLException e) {
            System.out.println("Error getting pustakawan: " + e.getMessage());
        }

        return pustakawanList;
    }

    public static void update(Pustakawan pustakawan) {
        String updateSQL = "UPDATE Pustakawan SET namaPustakawan = ?, alamatPustakawan = ? WHERE idPustakawan = ?;";

        try (Connection conn = DBConnect.connect();
                PreparedStatement stmt = conn.prepareStatement(updateSQL)) {

            stmt.setString(1, pustakawan.getNamaPustakawan());
            stmt.setString(2, pustakawan.getAlamatPustakawan());
            stmt.setInt(3, pustakawan.getIdPustakawan());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating pustakawan failed, no rows affected.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating pustakawan: " + e.getMessage());
        }
    }

    public static void delete(int id) {
        String deleteSQL = "DELETE FROM Pustakawan WHERE idPustakawan = ?;";

        try (Connection conn = DBConnect.connect();
                PreparedStatement stmt = conn.prepareStatement(deleteSQL)) {

            stmt.setInt(1, id);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting pustakawan failed, no rows affected.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting pustakawan: " + e.getMessage());
        }
    }
}
