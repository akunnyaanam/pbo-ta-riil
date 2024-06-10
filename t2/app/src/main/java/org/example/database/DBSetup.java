package org.example.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBSetup {
    public static void createTable() {
        String createAnggotaTableQuery = "CREATE TABLE IF NOT EXISTS Anggota ("
                + "idAnggota INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "namaAnggota TEXT NOT NULL, "
                + "alamatAnggota TEXT"
                + ");";
        String createBukuTableQuery = "CREATE TABLE IF NOT EXISTS Buku ("
                + "idBuku INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "judul TEXT NOT NULL, "
                + "penerbit TEXT NOT NULL, "
                + "penulis TEXT NOT NULL, "
                + "tahun_terbit INTEGER NOT NULL, "
                + "status TEXT"
                + ");";
        String createPustakawanTableQuery = "CREATE TABLE IF NOT EXISTS Pustakawan ("
                + "idPustakawan INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "namaPustakawan TEXT NOT NULL, "
                + "alamatPustakawan TEXT"
                + ");";

        try (Connection conn = DBConnect.connect();
                Statement stmt = conn.createStatement()) {
            stmt.execute(createAnggotaTableQuery);
            stmt.execute(createBukuTableQuery);
            stmt.execute(createPustakawanTableQuery);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}