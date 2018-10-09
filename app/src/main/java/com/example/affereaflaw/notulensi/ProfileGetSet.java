package com.example.affereaflaw.notulensi;

/**
 * Created by Affe Reaflaw on 11/5/2017.
 */
public class ProfileGetSet {
    private String nama, email;

    public ProfileGetSet(String nama, String email) {
        this.nama = nama;
        this.email = email;
    }

    public ProfileGetSet(){

    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
