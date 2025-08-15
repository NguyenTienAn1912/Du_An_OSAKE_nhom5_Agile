/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model_HoaDon;

/**
 *
 * @author ADMIN
 */
public class Model_GioHang {
   private Integer id;

    private String maSPCT;
    
    private String TenSP;
    
    private String hangSX;

    private Integer nongdo;

    private String namSX;
    
    private String XuatSU;
    
    private String PhanLoai;
    
    private String dungTich;
    
    private String nhaCungCap;
    
    private Integer giaNhap;
    
    private Integer giaBan;
    
    private Integer soLuong;
    
    private boolean trangThai;
    
    private Integer thanhTien;
    
    private Integer idHoaDon;
    
    private Integer idSpct;

    public Model_GioHang() {
    }

    public Model_GioHang(String maSPCT, String TenSP, String hangSX, Integer nongdo, String namSX, String XuatSU, String PhanLoai, String dungTich, String nhaCungCap, Integer giaNhap, Integer giaBan, Integer soLuong, boolean trangThai, Integer thanhTien, Integer idHoaDon, Integer idSpct) {
        this.maSPCT = maSPCT;
        this.TenSP = TenSP;
        this.hangSX = hangSX;
        this.nongdo = nongdo;
        this.namSX = namSX;
        this.XuatSU = XuatSU;
        this.PhanLoai = PhanLoai;
        this.dungTich = dungTich;
        this.nhaCungCap = nhaCungCap;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.thanhTien = thanhTien;
        this.idHoaDon = idHoaDon;
        this.idSpct = idSpct;
    }

    public Model_GioHang(Integer id, String maSPCT, String TenSP, String hangSX, Integer nongdo, String namSX, String XuatSU, String PhanLoai, String dungTich, String nhaCungCap, Integer giaNhap, Integer giaBan, Integer soLuong, boolean trangThai, Integer thanhTien, Integer idHoaDon, Integer idSpct) {
        this.id = id;
        this.maSPCT = maSPCT;
        this.TenSP = TenSP;
        this.hangSX = hangSX;
        this.nongdo = nongdo;
        this.namSX = namSX;
        this.XuatSU = XuatSU;
        this.PhanLoai = PhanLoai;
        this.dungTich = dungTich;
        this.nhaCungCap = nhaCungCap;
        this.giaNhap = giaNhap;
        this.giaBan = giaBan;
        this.soLuong = soLuong;
        this.trangThai = trangThai;
        this.thanhTien = thanhTien;
        this.idHoaDon = idHoaDon;
        this.idSpct = idSpct;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(String maSPCT) {
        this.maSPCT = maSPCT;
    }

    public String getTenSP() {
        return TenSP;
    }

    public void setTenSP(String TenSP) {
        this.TenSP = TenSP;
    }

    public String getHangSX() {
        return hangSX;
    }

    public void setHangSX(String hangSX) {
        this.hangSX = hangSX;
    }

    public Integer getNongdo() {
        return nongdo;
    }

    public void setNongdo(Integer nongdo) {
        this.nongdo = nongdo;
    }

    public String getNamSX() {
        return namSX;
    }

    public void setNamSX(String namSX) {
        this.namSX = namSX;
    }

    public String getXuatSU() {
        return XuatSU;
    }

    public void setXuatSU(String XuatSU) {
        this.XuatSU = XuatSU;
    }

    public String getPhanLoai() {
        return PhanLoai;
    }

    public void setPhanLoai(String PhanLoai) {
        this.PhanLoai = PhanLoai;
    }

    public String getDungTich() {
        return dungTich;
    }

    public void setDungTich(String dungTich) {
        this.dungTich = dungTich;
    }

    public String getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(String nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public Integer getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(Integer giaNhap) {
        this.giaNhap = giaNhap;
    }

    public Integer getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Integer giaBan) {
        this.giaBan = giaBan;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Integer getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(Integer thanhTien) {
        this.thanhTien = thanhTien;
    }

    public Integer getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(Integer idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public Integer getIdSpct() {
        return idSpct;
    }

    public void setIdSpct(Integer idSpct) {
        this.idSpct = idSpct;
    }

   

    
}
