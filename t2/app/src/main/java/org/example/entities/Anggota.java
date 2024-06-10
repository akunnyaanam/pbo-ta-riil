package org.example.entities;

public class Anggota {
    int idAnggota;
    String namaAnggota;
    String alamatAnggota;

    public Anggota(int id, String namaAnggota, String alamatAnggota) {
        this.idAnggota = id;
        this.namaAnggota = namaAnggota;
        this.alamatAnggota = alamatAnggota;
    }

    public int getIdAnggota() {
        return idAnggota;
    }

    public void setIdAnggota(int idAnggota) {
        this.idAnggota = idAnggota;
    }

    public String getNamaAnggota() {
        return namaAnggota;
    }

    public void setNamaAnggota(String namaAnggota) {
        this.namaAnggota = namaAnggota;
    }

    public String getAlamatAnggota() {
        return alamatAnggota;
    }

    public void setAlamatAnggota(String alamatAnggota) {
        this.alamatAnggota = alamatAnggota;
    }
}