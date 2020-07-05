package com.tugasppb.bukualamat;

import java.io.Serializable;

public class Alamat implements Serializable {

    private String nama;
    private String alamat;
    private String no_hp;
    private String email;
    private String key;

    public Alamat(){

    }

    public String getKey() {

        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

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

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return " "+nama+"\n" +
                " "+alamat +"\n" +
                " "+no_hp +"\n" +
                " "+email;
    }

    public Alamat(String nm, String alm, String no, String em){
        nama = nm;
        alamat = alm;
        no_hp = no;
        email= em;
    }
}
