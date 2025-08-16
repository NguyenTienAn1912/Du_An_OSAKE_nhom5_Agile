/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model_Voucher;

/**
 *
 * @author Admin
 */
public class Model_ChiTietHoaDon {

    private int id;
    private String maSP;
    private String tenSP;
    private int soLuong;
    private double donGia;
    private double tongTien;
    private boolean trangThai;

    public Model_ChiTietHoaDon() {
    }

    public Model_ChiTietHoaDon(String maSP, String tenSP, int soLuong, double donGia, double tongTien, boolean trangThai) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    public Model_ChiTietHoaDon(int id, String maSP, String tenSP, int soLuong, double donGia, double tongTien, boolean trangThai) {
        this.id = id;
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Object[] toDataRow(){
        return new Object[]{this.getId(),this.getMaSP(),this.getTenSP(),this.getSoLuong(),this.getDonGia(),
        this.getTongTien(),this.isTrangThai()};
    }
 
}
