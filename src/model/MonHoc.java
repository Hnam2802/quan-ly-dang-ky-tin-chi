/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class MonHoc {
    private static int tmp = 1;
    private String maMonHoc, tenMon, loaiMonHoc;
    private int soTiet;
    public MonHoc() {
        
    }
    public MonHoc(String ten, String loai, int sl) {
        this.maMonHoc = String.format("%03d", tmp++);
        this.tenMon = ten;
        this.loaiMonHoc = loai;
        this.soTiet = sl;
    }
    public void getTenMonHoc(String name) {
        this.tenMon = name;
    }
    public void getLoaiMonHoc(String type) {
        this.loaiMonHoc = type;
    }
    public void getSoTiet(int sl) {
        this.soTiet = sl;
    }
    public String getTenMonHoc() {
        return tenMon;
    }
    public String getMaMonHoc() {
        return maMonHoc;
    }
    public String getLoaiMonHoc() {
        return loaiMonHoc;
    }
    public int getSoTiet() {
        return soTiet;
    }
    @Override 
    public String toString() {
        return getMaMonHoc() + " " + getTenMonHoc() + " " + getSoTiet() + " " + getLoaiMonHoc();
    } 
}
