package com.example.affereaflaw.notulensi;

/**
 * Created by Affe Reaflaw on 11/5/2017.
 */
public class NotulensiListGetSet {
    private String judul, tanggal, notulen, notulensi;

    public NotulensiListGetSet(String judul, String tanggal, String notulen, String notulensi) {
        this.judul = judul;
        this.tanggal = tanggal;
        this.notulen = notulen;
        this.notulensi = notulensi;
    }

    public NotulensiListGetSet(){

    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getNotulen() {
        return notulen;
    }

    public void setNotulen(String notulen) {
        this.notulen = notulen;
    }

    public String getNotulensi() {
        return notulensi;
    }

    public void setNotulensi(String notulensi) {
        this.notulensi = notulensi;
    }
}
