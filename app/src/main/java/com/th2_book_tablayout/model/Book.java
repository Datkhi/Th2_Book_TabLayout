package com.th2_book_tablayout.model;

import java.io.Serializable;

public class Book implements Serializable {
    private int id;
    private String ten, tacgia, phamvi, doituong, danhgia;
    public Book() {
    }

    public Book(int id, String ten, String tacgia, String phamvi, String doituong, String danhgia) {
        this.id = id;
        this.ten = ten;
        this.tacgia = tacgia;
        this.phamvi = phamvi;
        this.doituong = doituong;
        this.danhgia = danhgia;
    }

    public Book(String ten, String tacgia, String phamvi, String doituong, String danhgia) {
        this.ten = ten;
        this.tacgia = tacgia;
        this.phamvi = phamvi;
        this.doituong = doituong;
        this.danhgia = danhgia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getPhamvi() {
        return phamvi;
    }

    public void setPhamvi(String phamvi) {
        this.phamvi = phamvi;
    }

    public String getDoituong() {
        return doituong;
    }

    public void setDoituong(String doituong) {
        this.doituong = doituong;
    }

    public String getDanhgia() {
        return danhgia;
    }

    public void setDanhgia(String danhgia) {
        this.danhgia = danhgia;
    }
}
