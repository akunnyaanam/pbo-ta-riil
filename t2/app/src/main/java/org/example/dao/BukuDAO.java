package org.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.example.database.DBConnect;
import org.example.entities.Buku;

public class BukuDAO {
    // private static final String URL = "jdbc:sqlite:perpus.db";

    public static void generateDummyData() {
        List<Buku> dummyData = new ArrayList<>();

        // Menambahkan data dummy
        dummyData.add(new Buku(1, "Judul Buku 1", "Penerbit 1", "Penulis 1", 2000, "Tersedia"));
        dummyData.add(new Buku(2, "Judul Buku 2", "Penerbit 2", "Penulis 2", 2010, "Tersedia"));
        dummyData.add(new Buku(3, "Judul Buku 3", "Penerbit 3", "Penulis 3", 2020, "Dipinjam"));
        for (Buku buku : dummyData) {
            insert(buku);
        }

        System.out.println("Dummy data inserted successfully.");
    }

    public static void insert(Buku buku) {
        String insertSQL = "INSERT INTO Buku (judul, penerbit, penulis, tahun_terbit, status) VALUES (?, ?, ?, ?, ?);";

        try (Connection conn = DBConnect.connect();
                PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
            stmt.setString(1, buku.getJudul());
            stmt.setString(2, buku.getPenerbit());
            stmt.setString(3, buku.getPenulis());
            stmt.setInt(4, buku.getTahun_terbit());
            stmt.setString(5, buku.getStatus());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting buku: " + e.getMessage());
        }
    }

    public static List<Buku> getAll() {
        List<Buku> bukuList = new ArrayList<>();
        String selectAllSQL = "SELECT idBuku, judul, penerbit, penulis, tahun_terbit, status FROM Buku;";

        try (Connection conn = DBConnect.connect();
                PreparedStatement stmt = conn.prepareStatement(selectAllSQL)) {

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Buku buku = new Buku(0, selectAllSQL, selectAllSQL, selectAllSQL, 0);
                buku.setIdBuku(rs.getInt("idBuku"));
                buku.setJudul(rs.getString("judul"));
                buku.setPenerbit(rs.getString("penerbit"));
                buku.setPenulis(rs.getString("penulis"));
                buku.setTahun_terbit(rs.getInt("tahun_terbit"));
                buku.setStatus(rs.getString("status"));
                bukuList.add(buku);
            }
        } catch (SQLException e) {
            System.out.println("Error getting buku: " + e.getMessage());
        }

        return bukuList;
    }

    public static void update(Buku buku) {
        String updateSQL = "UPDATE Buku SET judul = ?, penerbit = ?, penulis = ?, tahun_terbit = ?, status = ? WHERE idBuku = ?;";

        try (Connection conn = DBConnect.connect();
                PreparedStatement stmt = conn.prepareStatement(updateSQL)) {

            stmt.setString(1, buku.getJudul());
            stmt.setString(2, buku.getPenerbit());
            stmt.setString(3, buku.getPenulis());
            stmt.setInt(4, buku.getTahun_terbit());
            stmt.setString(5, buku.getStatus());
            stmt.setInt(6, buku.getIdBuku());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating buku failed, no rows affected.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating buku: " + e.getMessage());
        }
    }

    public void delete(int id) {
        String deleteSQL = "DELETE FROM Buku WHERE idBuku = ?;";

        try (Connection conn = DBConnect.connect();
                PreparedStatement stmt = conn.prepareStatement(deleteSQL)) {

            stmt.setInt(1, id);

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting buku failed, no rows affected.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting buku: " + e.getMessage());
        }
    }
}
