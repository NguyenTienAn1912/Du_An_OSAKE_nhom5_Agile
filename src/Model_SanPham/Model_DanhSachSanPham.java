/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model_SanPham;

import java.util.Date;

/**
 *
 * @author ADMIN
 */
public class Model_DanhSachSanPham {
    private int id;
    private String MaSP;
    private String TenSP;
    private int SoLuongCon;
    private boolean TrangThai;
    private Date ngayNhap;
   
    public Model_DanhSachSanPham() {
    }

    public Model_DanhSachSanPham(String MaSP, String TenSP, int SoLuongCon, boolean TrangThai, Date ngayNhap) {
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.SoLuongCon = SoLuongCon;
        this.TrangThai = TrangThai;
        this.ngayNhap = ngayNhap;
    }

    public Model_DanhSachSanPham(int id, String MaSP, String TenSP, int SoLuongCon, boolean TrangThai, Date ngayNhap) {
        this.id = id;
        this.MaSP = MaSP;
        this.TenSP = TenSP;
        this.SoLuongCon = SoLuongCon;
        this.TrangThai = TrangThai;
        this.ngayNhap = ngayNhap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaSP() {
        return MaSP;
    }

    public void setMaSP(String MaSP) {
        this.MaSP = MaSP;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public int getSoLuongCon() {
        return SoLuongCon;
    }

    public void setSoLuongCon(int SoLuongCon) {
        this.SoLuongCon = SoLuongCon;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    public Date getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    
    
    public Object [] toDataRow(){
        return new Object[] {this.getId(),this.getMaSP(),this.getTenSP(),this.getSoLuongCon(),this.isTrangThai()?"Còn Hàng":"Hết Hàng",this.getNgayNhap()};
    }
    
}
