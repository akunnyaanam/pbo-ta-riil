package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.database.DBConnect;
import org.example.entities.Anggota;

public class AnggotaDAO {
    // private static final String URL = "jdbc:sqlite:perpus.db";

    public static void insertDummyData() {
        // Data dummy yang ingin disisipkan
        List<Anggota> dummyAnggotaList = new ArrayList<>();
        dummyAnggotaList.add(new Anggota(0, "John Doe", "123 Main St"));
        dummyAnggotaList.add(new Anggota(0, "Jane Smith", "456 Elm St"));
        dummyAnggotaList.add(new Anggota(0, "Alice Johnson", "789 Oak St"));

        // Looping untuk menyisipkan setiap anggota ke dalam tabel
        for (Anggota anggota : dummyAnggotaList) {
            insert(anggota);
        }

        System.out.println("Dummy data inserted successfully.");
    }

    public static void insert(Anggota anggota) {
        String insertSQL = "INSERT INTO Anggota (namaAnggota, alamatAnggota) VALUES (?, ?);";

        try (Connection conn = DBConnect.connect();
                PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
            stmt.setString(1, anggota.getNamaAnggota());
            stmt.setString(2, anggota.getAlamatAnggota());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting anggota: " + e.getMessage());
        }
    }

    public static List<Anggota> getAll() {
        List<Anggota> anggotaList = new ArrayList<>();
        String selectAllSQL = "SELECT idAnggota, namaAnggota, alamatAnggota FROM Anggota;";

        try (Connection conn = DBConnect.connect();
                PreparedStatement stmt = conn.prepareStatement(selectAllSQL)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Anggota anggota = new Anggota(0, selectAllSQL, selectAllSQL);
                anggota.setIdAnggota(rs.getInt("idAnggota"));
                anggota.setNamaAnggota(rs.getString("namaAnggota"));
                anggota.setAlamatAnggota(rs.getString("alamatAnggota"));
                anggotaList.add(anggota);
            }
        } catch (SQLException e) {
            System.out.println("Error getting anggota: " + e.getMessage());
        }

        return anggotaList;
    }

    public static void update(Anggota anggota) {
        String updateSQL = "UPDATE Anggota SET namaAnggota = ?, alamatAnggota = ? WHERE idAnggota = ?;";

        try (Connection conn = DBConnect.connect();
                PreparedStatement stmt = conn.prepareStatement(updateSQL)) {

            stmt.setString(1, anggota.getNamaAnggota());
            stmt.setString(2, anggota.getAlamatAnggota());
            stmt.setInt(3, anggota.getIdAnggota());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating anggota failed, no rows affected.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating anggota: " + e.getMessage());
        }
    }

    public static void delete(int id) {
        String deleteSQL = "DELETE FROM Anggota WHERE idAnggota = ?;";

        try (Connection conn = DBConnect.connect();
                PreparedStatement stmt = conn.prepareStatement(deleteSQL)) {

            stmt.setInt(1, id);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting anggota failed, no rows affected.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting anggota: " + e.getMessage());
        }
    }
}
