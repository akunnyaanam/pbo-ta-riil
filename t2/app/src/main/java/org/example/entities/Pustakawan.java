package org.example.entities;

public class Pustakawan {
    int idPustakawan;
    String namaPustakawan;
    String alamatPustakawan;

    public Pustakawan(int id, String namaPustakawan, String alamatPustakawan) {
        this.idPustakawan = id;
        this.namaPustakawan = namaPustakawan;
        this.alamatPustakawan = alamatPustakawan;
    }

    public int getIdPustakawan() {
        return idPustakawan;
    }

    public void setIdPustakawan(int idPustakawan) {
        this.idPustakawan = idPustakawan;
    }

    public String getNamaPustakawan() {
        return namaPustakawan;
    }

    public void setNamaPustakawan(String namaPustakawan) {
        this.namaPustakawan = namaPustakawan;
    }

    public String getAlamatPustakawan() {
        return alamatPustakawan;
    }

    public void setAlamatPustakawan(String alamatPustakawan) {
        this.alamatPustakawan = alamatPustakawan;
    }
}