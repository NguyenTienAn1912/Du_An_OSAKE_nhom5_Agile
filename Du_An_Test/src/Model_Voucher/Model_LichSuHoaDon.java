/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model_Voucher;

/**
 *
 * @author Admin
 */
public class Model_LichSuHoaDon {
    private int id;
    private String maHD;
    private String tenNV;
    private String tenKH;
    private double tongTien;
    private String ngayTao;    
    private boolean trangThai;

    public Model_LichSuHoaDon() {
    }

    public Model_LichSuHoaDon(int id, String maHD, String tenNV, String tenKH, double tongTien, String ngayTao, boolean trangThai) {
        this.id = id;
        this.maHD = maHD;
        this.tenNV = tenNV;
        this.tenKH = tenKH;
        this.tongTien = tongTien;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
 
    public Object[] toDataRow(){
        return new Object[]{this.getId(),this.getMaHD(),this.getTenNV(),this.getTenKH(),
            this.getTongTien(),this.getNgayTao(),this.isTrangThai()?"Đã thanh toán":"Chưa thanh toán"};
    }
    

    
}
