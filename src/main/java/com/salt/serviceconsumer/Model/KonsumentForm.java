package com.salt.serviceconsumer.Model;

public class KonsumentForm {

    private String nama;
    private String alamat;
    private String kota;
    private String provinsi;
    
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
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
    public KonsumentForm(String nama, String alamat, String kota, String provinsi) {
        this.nama = nama;
        this.alamat = alamat;
        this.kota = kota;
        this.provinsi = provinsi;
    }
    public KonsumentForm() {
    }
    
}
