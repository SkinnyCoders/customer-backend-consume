package com.salt.serviceconsumer.Model;

public class Konsument {
    
    private Integer id;
    private String name;
    private String alamat;
    private String kota;
    private String provinsi;
    private String tgl_registrasi;
    private String status;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    public String getKota() {
        return kota;
    }
    public void setKota(String kota) {
        this.kota = kota;
    }
    public String getProvinsi() {
        return provinsi;
    }
    public void setProvinsi(String provinsi) {
        this.provinsi = provinsi;
    }
    public String getTgl_registrasi() {
        return tgl_registrasi;
    }
    public void setTgl_registrasi(String tgl_registrasi) {
        this.tgl_registrasi = tgl_registrasi;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Konsument(Integer id, String name, String alamat, String kota, String provinsi, String tgl_registrasi,
            String status) {
        this.id = id;
        this.name = name;
        this.alamat = alamat;
        this.kota = kota;
        this.provinsi = provinsi;
        this.tgl_registrasi = tgl_registrasi;
        this.status = status;
    }
    

    
}
